import javax.swing.*;

public class PaymentView {

    private JLabel paymentLabel;

    private JTable currentCart;

    private JButton cashPaymentButton;
    private JButton cardPaymentButton;
    private JButton closeButton;

    public PaymentView() {

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
