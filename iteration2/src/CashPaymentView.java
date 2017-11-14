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
