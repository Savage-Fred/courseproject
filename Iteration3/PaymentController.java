import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PaymentController implements ActionListener {

    private PaymentView paymentView;
    private DataAdapter dataAdapter;

    private OrderModel order = null;

    private int currentOrderId;

    public PaymentController(PaymentView view, DataAdapter data ) {
        this.paymentView = view;
        this.dataAdapter = data;

        paymentView.getCashPaymentButton().addActionListener(this);
        paymentView.getCardPaymentButton().addActionListener(this);
        paymentView.getCloseButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == paymentView.getCardPaymentButton()) {
            loadCardPayment();
        } else if (e.getSource() == paymentView.getCashPaymentButton()) {
            loadCashPayment();
        }
        else if (e.getSource() == paymentView.getCloseButton())
        {
            loadClose();
        }

    }

    public void loadCardPayment() {
        Application.getInstance().getCardPaymentView().setVisible(true);
        Application.getInstance().getPaymentView().setVisible(false);
        paymentView.clearTable();
    }

    public void loadCashPayment() {
        Application.getInstance().getCashPaymentController().setCurrentOrderId(Application.getInstance().getCheckoutController().getCurrentOrderId());
        Application.getInstance().getCashPaymentController().calculateTotal();
        Application.getInstance().getCashPaymentView().setVisible(true);
        Application.getInstance().getPaymentView().setVisible(false);
        paymentView.clearTable();

    }

    public void loadClose() {
        Application.getInstance().getPaymentView().setVisible(false);
        paymentView.clearTable();
        Application.getInstance().getCheckoutView().setVisible(true);
    }

    public void loadTable(int orderId) throws IOException {
        order = new OrderModel();
        if (dataAdapter.loadOrder(orderId) != null) {
            order = dataAdapter.loadOrder(orderId);

            try {
            /* New File to save Text Receipt */

               PrintWriter writer = new PrintWriter(new FileWriter("receipt.txt"));
               writer.println("********* Receipt **********");
                writer.println("\nName\tPrice\tQuantity\tCost");




                for (OrderLine line : order.getLines()) {
                    Object[] row = new Object[5];
                    row[0] = line.getProductID();
                    if (dataAdapter.loadProduct(line.getProductID()) != null) {
                        ProductModel product = dataAdapter.loadProduct(line.getProductID());

                        row[1] = product.getProductName();
                        writer.print(product.getProductName() + "\t");

                        row[2] = product.getProductPrice();
                        writer.print(product.getProductPrice() + "\t");

                        row[3] = line.getQuantity();
                        writer.print(line.getQuantity() + "\t");

                        line.setCost(product.getProductPrice() * line.getQuantity());
                        row[4] = line.getCost();
                        writer.print(line.getCost() + "\t");
                    }
                    paymentView.addRow(row);
                    writer.print("\n");

                }
                this.paymentView.getTotalLabel().setText("Total: " + order.getTotalPrice());
                paymentView.invalidate();
                writer.close();

            } catch (IOException e) {
                System.out.println("Exception Occurred: ");
                e.printStackTrace();
            }
        }

    }
}
