import javax.swing.*;

public class CashPaymentView extends JFrame {

    private JLabel cashPaymentLabel;
    private JLabel cashGivenLabel;
    private JLabel changeLabel;

    private JTextField cashGivenField;
    private JTextField changeField;

    private JButton printReceiptButton;
    private JButton closeButton;

    public CashPaymentView() {
        this.setTitle("Store Manager");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(500, 200);

    }

    public JTextField getCashGivenField() {
        return cashGivenField;
    }

    public JTextField getChangeField() {
        return changeField;
    }

    public JButton getPrintReceiptButton() {
        return printReceiptButton;
    }

    public JButton getCloseButton() {
        return closeButton;
    }
}
