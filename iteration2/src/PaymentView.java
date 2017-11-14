import javax.swing.*;

public class PaymentView extends JFrame {

    private JLabel paymentLabel = new JLabel("Payment Type");

    private JTable currentCart;

    private JButton cashPaymentButton = new JButton("Cash");
    private JButton cardPaymentButton = new JButton("Card");
    private JButton closeButton = new JButton("Close");

    public PaymentView() {
        this.setTitle("Store Manager");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(500, 200);

        JPanel panelButton = new JPanel();
        panelButton.add(cashPaymentButton);
        panelButton.add(cardPaymentButton);
        panelButton.add(closeButton);
        this.getContentPane().add(panelButton);

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
