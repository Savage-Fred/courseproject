import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class CheckoutController implements ActionListener {
    private CheckoutView view;
    private DataAdapter dataAdapter; // to save and load product
    private OrderModel order = null;

    private int currentOrderId;

    public CheckoutController(CheckoutView view, DataAdapter dataAdapter) {
        this.dataAdapter = dataAdapter;
        this.view = view;

        order = new OrderModel();

        view.getPaymentButton().addActionListener(this);
        view.getLoyaltyButton().addActionListener(this);
        view.getManualEntryButton().addActionListener(this);
        view.getCloseButton().addActionListener(this);

    }


    public void actionPerformed(ActionEvent e) {
       if (e.getSource() == view.getPaymentButton()) {
           loadPayment();
       }
       else if (e.getSource() == view.getLoyaltyButton()) {
           loadLoyaltyProgram();
       }
       else if (e.getSource() == view.getManualEntryButton()) {
           loadManualEntry();
       }
       else if (e.getSource() == view.getCloseButton()) {
           loadClose();
       }
    }

    public void loadPayment() {
        Application.getInstance().getPaymentController().loadTable(currentOrderId);
        Application.getInstance().getPaymentView().setVisible(true);
        Application.getInstance().getCheckoutView().setVisible(false);
    }

    public void loadLoyaltyProgram() {

        JOptionPane.showMessageDialog(null, "Available in Future Versions");

    }

    public void loadManualEntry() {
        String id = JOptionPane.showInputDialog("Enter ProductID: ");
        ProductModel product = null;
        assert dataAdapter.loadProduct(Integer.parseInt(id)) != null;
        if (dataAdapter.loadProduct(Integer.parseInt(id)) == null) {
            JOptionPane.showMessageDialog(null, "This product does not exist!");
            return;
        } else {
            product = dataAdapter.loadProduct(Integer.parseInt(id));


            double quantity = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter quantity: "));

            if (quantity < 0 || quantity > product.getProductQuantity()) {
                JOptionPane.showMessageDialog(null, "This quantity is not valid!");
                return;
            }

            order.setOrderID(currentOrderId);



            OrderLine line = new OrderLine();
            line.setOrderID(this.order.getOrderID());
            line.setProductID(product.getProductID());
            line.setQuantity(quantity);
            line.setCost(quantity * product.getProductPrice());

            product.setProductQuantity(product.getProductQuantity() - quantity); // update new quantity!!
            dataAdapter.saveProduct(product); // and store this product back right away!!!

            order.getLines().add(line);
            order.setTotalPrice(order.getTotalPrice() + line.getCost());



            Object[] row = new Object[5];
            row[0] = line.getProductID();
            row[1] = product.getProductName();
            row[2] = product.getProductPrice();
            row[3] = line.getQuantity();
            row[4] = line.getCost();

            dataAdapter.saveOrder(order);

            this.view.addRow(row);
            this.view.getTotalLabel().setText("Total: " + order.getTotalPrice());
            this.view.invalidate();


        }
    }

    public void loadClose() {
        Application.getInstance().getCheckoutView().setVisible(false);
        view.clearTable();
        view.setTotalLabel("0");
        Application.getInstance().getOrderSelectionView().setVisible(true);
    }

    public void loadTable(int orderId) {
        order = new OrderModel();
        if (dataAdapter.loadOrder(orderId) != null) {
            order = dataAdapter.loadOrder(orderId);
            order.setTotalPrice(0);
            for (OrderLine line: order.getLines()) {
                Object[] row = new Object[5];
                row[0] = line.getProductID();
                if (dataAdapter.loadProduct(line.getProductID()) != null) {
                    ProductModel product = dataAdapter.loadProduct(line.getProductID());
                    row[1] = product.getProductName();
                    row[2] = product.getProductPrice();
                    row[3] = line.getQuantity();
                    line.setCost(product.getProductPrice()*line.getQuantity());
                    row[4] = line.getCost();
                    order.setTotalPrice(line.getCost()+order.getTotalPrice());
                 }
                view.addRow(row);

            }

            this.view.getTotalLabel().setText("Total: " + order.getTotalPrice());
            view.invalidate();
        }

    }

    public void setCurrentOrderId(int currentOrderId) {
        this.currentOrderId = currentOrderId;
    }

    public int getCurrentOrderId() {
        return currentOrderId;
    }
}