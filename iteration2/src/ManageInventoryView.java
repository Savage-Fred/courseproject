import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ManageInventoryView extends JFrame {
    private JLabel manageInventoryLabel = new JLabel("Manage Inventory");

    private DefaultTableModel items = new DefaultTableModel();

    private JTable currentInventory = new JTable(items);

    private JButton addItemButton = new JButton("Add Item");
    private JButton editItemButton = new JButton("Edit Item");
    private JButton deleteItemButton = new JButton("Delete Item");
    private JButton closeButton = new JButton("Close");

    public ManageInventoryView() {
        this.setTitle("Store Manager");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(500, 200);

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

        JPanel panelButton = new JPanel();
        panelButton.add(addItemButton);
        panelButton.add(editItemButton);
        panelButton.add(deleteItemButton);
        panelButton.add(closeButton);
        this.getContentPane().add(panelButton);

    }

    public JButton getAddItemButton() {
        return addItemButton;
    }

    public JButton getEditItemButton() {
        return editItemButton;
    }

    public JButton getDeleteItemButton() {
        return deleteItemButton;
    }

    public JButton getCloseButton() {
        return closeButton;
    }

    public void addRow(Object[] row) {
        items.addRow(row);              // add a row to list of item!
        items.fireTableDataChanged();
    }
}
