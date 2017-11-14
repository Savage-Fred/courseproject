import javax.swing.*;

public class CashPaymentView extends JFrame {

    private JLabel cashPaymentLabel = new JLabel("Cash Payment");
    private JLabel cashGivenLabel = new JLabel("Cash Given");
    private JLabel changeLabel = new JLabel("Change Label");

    private JTextField cashGivenField = new JTextField(10);
    private JTextField changeField = new JTextField(10);

    private JButton printReceiptButton = new JButton("Print Receipt");
    private JButton closeButton = new JButton("Close");

    public CashPaymentView() {
        this.setTitle("Store Manager");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(500, 200);

        JPanel panelPaymentFields = new JPanel();
        panelPaymentFields.add(cashGivenField);
        panelPaymentFields.add(cashGivenLabel);
        panelPaymentFields.add(changeField);
        panelPaymentFields.add(changeLabel);
        this.getContentPane().add(panelPaymentFields);

        JPanel panelButton = new JPanel();
        panelButton.add(printReceiptButton);
        panelButton.add(closeButton);
        this.getContentPane().add(panelButton);

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
