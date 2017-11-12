import javax.swing.*;

public class CheckoutView {
    private JLabel checkoutLabel;

    private JTable currentCart;

    private JButton paymentButton;
    private JButton loyaltyButton;
    private JButton manualEntryButton;
    private JButton closeButton;

    public CheckoutView() {

    }

    public JButton getPaymentButton() {
        return paymentButton;
    }

    public JButton getLoyaltyButton() {
        return loyaltyButton;
    }

    public JButton getCloseButton() {
        return closeButton;
    }

    public JButton getManualEntryButton() {
        return manualEntryButton;
    }

}
