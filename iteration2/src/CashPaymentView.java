import com.sun.deploy.panel.JavaPanel;

import javax.swing.*;

public class CashPaymentView extends JFrame {

    private JLabel cashPaymentLabel = new JLabel("Cash Payment");
    private JLabel cashGivenLabel = new JLabel("Cash Given");
    private JLabel changeLabel = new JLabel("Change Label");
    private JLabel totalLabel = new JLabel("Total: ");

    private JTextField cashGivenField = new JTextField(10);
    private JTextField changeField = new JTextField(10);

    private JButton printReceiptButton = new JButton("Checkout");
    private JButton closeButton = new JButton("Close");

    public CashPaymentView() {
        this.setTitle("Store Manager");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setSize(500, 200);

        JPanel panelTitle = new JPanel();
        panelTitle.add(cashPaymentLabel);
        panelTitle.add(totalLabel);
        this.getContentPane().add(panelTitle);


        JPanel panelCashGiven = new JPanel();
        panelCashGiven.add(cashGivenField);
        panelCashGiven.add(cashGivenLabel);
        this.getContentPane().add(panelCashGiven);

        JPanel panelChange = new JPanel();
        panelChange.add(changeField);
        panelChange.add(changeLabel);
        this.getContentPane().add(panelChange);

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

    public void setChangeField(Double changeField) {
        this.changeField.setText(String.valueOf(changeField));
    }

    public JButton getPrintReceiptButton() {
        return printReceiptButton;
    }

    public JButton getCloseButton() {
        return closeButton;
    }

    public JLabel getTotalLabel() {
        return totalLabel;
    }


}
