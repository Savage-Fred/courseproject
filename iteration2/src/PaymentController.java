import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public void loadTable(int orderId) {
        order = new OrderModel();
        if (dataAdapter.loadOrder(orderId) != null) {
            order = dataAdapter.loadOrder(orderId);

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
                }
                paymentView.addRow(row);

            }
            this.paymentView.getTotalLabel().setText("Total: " + order.getTotalPrice());
            paymentView.invalidate();
        }

    }
}
