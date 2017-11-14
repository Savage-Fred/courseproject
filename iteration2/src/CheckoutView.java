import javax.swing.*;

public class CheckoutView extends JFrame {
    private JLabel checkoutLabel;

    private JTable currentCart;

    private JButton paymentButton;
    private JButton loyaltyButton;
    private JButton manualEntryButton;
    private JButton closeButton;

    public CheckoutView() {
        this.setTitle("Store Manager");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(500, 200);

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
