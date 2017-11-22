import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PaymentView extends JFrame {

    private JLabel paymentLabel = new JLabel("Payment Type");
    private JLabel totalLabel = new JLabel("Total: ");

    private DefaultTableModel cart = new DefaultTableModel();

    private JTable currentCart = new JTable(cart);

    private JButton cashPaymentButton = new JButton("Cash");
    private JButton cardPaymentButton = new JButton("Card");
    private JButton closeButton = new JButton("Close");

    public PaymentView() {
        this.setTitle("Store Manager");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setSize(500, 300);

        cart.addColumn("Product ID");
        cart.addColumn("Name");
        cart.addColumn("Price");
        cart.addColumn("Quantity");
        cart.addColumn("Cost");

        JPanel panelTitle  = new JPanel();
        panelTitle.add(paymentLabel);

        JPanel panelOrder = new JPanel();
        panelOrder.setPreferredSize(new Dimension(500, 450));
        panelOrder.setLayout(new BoxLayout(panelOrder, BoxLayout.PAGE_AXIS));
        currentCart.setBounds(0, 0, 400, 350);
        panelOrder.add(currentCart.getTableHeader());
        panelOrder.add(currentCart);
        panelOrder.add(totalLabel);
        currentCart.setFillsViewportHeight(true);

        JPanel panelTotal = new JPanel();
        panelTitle.add(totalLabel);


        JPanel panelButton = new JPanel();
        panelButton.add(cashPaymentButton);
        panelButton.add(cardPaymentButton);
        panelButton.add(closeButton);

        this.getContentPane().add(panelTitle);
        this.getContentPane().add(panelOrder);
        this.getContentPane().add(panelTotal);
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

    public JLabel getTotalLabel() {
        return totalLabel;
    }

    public void addRow(Object[] row) {
        cart.addRow(row);              // add a row to list of item!
        cart.fireTableDataChanged();
    }

    public void clearTable(){
        cart.setRowCount(0);
    }
}
