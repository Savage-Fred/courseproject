import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CheckoutView extends JFrame {
    private JLabel checkoutLabel = new JLabel("Checkout");
    private JLabel totalLabel = new JLabel("Total: ");

    private DefaultTableModel cart = new DefaultTableModel();

    private JTable currentCart = new JTable(cart);

    private JButton paymentButton = new JButton("Finish and Pay");
    private JButton loyaltyButton = new JButton("Loyalty Program");
    private JButton manualEntryButton = new JButton("Manual Entry");
    private JButton closeButton = new JButton("Close");

    public CheckoutView() {
        this.setTitle("Store Manager");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(500, 200);

        cart.addColumn("Product ID");
        cart.addColumn("Name");
        cart.addColumn("Price");
        cart.addColumn("Quantity");
        cart.addColumn("Cost");

        JPanel panelOrder = new JPanel();
        panelOrder.setPreferredSize(new Dimension(400, 450));
        panelOrder.setLayout(new BoxLayout(panelOrder, BoxLayout.PAGE_AXIS));
        currentCart.setBounds(0, 0, 400, 350);
        panelOrder.add(currentCart.getTableHeader());
        panelOrder.add(currentCart);
        panelOrder.add(totalLabel);
        currentCart.setFillsViewportHeight(true);
        this.getContentPane().add(panelOrder);

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

    public void addRow(Object[] row) {
        cart.addRow(row);              // add a row to list of item!
        cart.fireTableDataChanged();
    }

}
