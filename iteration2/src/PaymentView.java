import javax.swing.*;

public class PaymentView extends JFrame {

    private JLabel paymentLabel;

    private JTable currentCart;

    private JButton cashPaymentButton;
    private JButton cardPaymentButton;
    private JButton closeButton;

    public PaymentView() {
        this.setTitle("Store Manager");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(500, 200);

    }

    public JButton getCardPaymentButton() {
        return cardPaymentButton;
    }

    public JButton getCashPaymentButton() {
        return cashPaymentButton;
    }

    public JButton getCloseButton() {
        return closeButton;
    }
}
