import javax.swing.*;

public class CheckoutView extends JFrame {
    private JLabel checkoutLabel = new JLabel("Checkout");

    // TODO: Generate Table based on User's Cart
    private JTable currentCart;

    private JButton paymentButton = new JButton("Finish and Pay");
    private JButton loyaltyButton = new JButton("Loyalty Program");
    private JButton manualEntryButton = new JButton("Manual Entry");
    private JButton closeButton = new JButton("Close");

    public CheckoutView() {
        this.setTitle("Store Manager");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(500, 200);

        JPanel panelButton = new JPanel();
        panelButton.add(paymentButton);
        panelButton.add(loyaltyButton);
        panelButton.add(manualEntryButton);
        panelButton.add(closeButton);
        this.getContentPane().add(panelButton);

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
