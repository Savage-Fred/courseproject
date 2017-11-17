import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SearchInventoryView extends JFrame {
    private JLabel searchItemLabel = new JLabel("Search Inventory");
    private JLabel itemLabel = new JLabel("Enter Item ID:");

    private JTextField itemField = new JTextField(20);

    private DefaultTableModel items = new DefaultTableModel();

    private JTable currentInventory = new JTable(items);

    private JButton searchButton = new JButton("Search");
    private JButton closeButton = new JButton("Close");

    SearchInventoryView() {
        this.setTitle("Store Manager");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(500, 300);

        items.addColumn("Product ID");
        items.addColumn("Name");
        items.addColumn("Price");
        items.addColumn("Quantity");

        JPanel panelOrder = new JPanel();
        panelOrder.setPreferredSize(new Dimension(400, 450));
        panelOrder.setLayout(new BoxLayout(panelOrder, BoxLayout.PAGE_AXIS));
        currentInventory.setBounds(0, 0, 400, 350);
        panelOrder.add(currentInventory.getTableHeader());
        panelOrder.add(currentInventory);
        currentInventory.setFillsViewportHeight(true);
        this.getContentPane().add(panelOrder);

        JPanel panelUserFields = new JPanel();
        panelUserFields.add(itemLabel);
        panelUserFields.add(itemField);
        this.getContentPane().add(panelUserFields);

        JPanel panelButton = new JPanel();
        panelButton.add(searchButton);
        panelButton.add(closeButton);
        this.getContentPane().add(panelButton);

    }

    public JTextField getItemField() {
        return itemField;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JButton getCloseButton() {
        return closeButton;
    }

    public void addRow(Object[] row) {
        items.addRow(row);              // add a row to list of item!
        items.fireTableDataChanged();
    }
}
