import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EditItemInventoryView extends JFrame{
    private JLabel editItemInventoryLabel = new JLabel("Edit Item");
    private JLabel itemNameLabel = new JLabel("Item Name");
    private JLabel itemPriceLabel = new JLabel("Item Price");
    private JLabel itemQuantityLabel = new JLabel("Item Quantity");
    private JLabel itemIDLabel = new JLabel("Item ID");

    private DefaultTableModel items = new DefaultTableModel();

    private JTable currentInventory = new JTable(items);

    private JTextField itemNameField = new JTextField(20);
    private JTextField itemQuantityField = new JTextField(20);
    private JTextField itemPriceField = new JTextField(20);
    private JTextField itemIDField = new JTextField(20);


    private JButton editItemButton = new JButton("Edit Item");
    private JButton closeButton = new JButton("Close");

    public EditItemInventoryView() {
        this.setTitle("Store Manager");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(500, 400);

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

        JPanel panelItemId = new JPanel();
        panelItemId.add(itemIDLabel);
        panelItemId.add(itemIDField);

        JPanel panelItemName = new JPanel();
        panelItemName.add(itemNameLabel);
        panelItemName.add(itemNameField);

        JPanel panelItemPrice = new JPanel();
        panelItemPrice.add(itemPriceLabel);
        panelItemPrice.add(itemPriceField);

        JPanel panelItemQuantity = new JPanel();
        panelItemQuantity.add(itemQuantityLabel);
        panelItemQuantity.add(itemQuantityField);

        JPanel panelUserFields = new JPanel();
        panelUserFields.setLayout(new BoxLayout(panelUserFields, BoxLayout.Y_AXIS));
        panelUserFields.add(panelItemId);
        panelUserFields.add(panelItemName);
        panelUserFields.add(panelItemPrice);
        panelUserFields.add(panelItemQuantity);
        this.getContentPane().add(panelUserFields);

        JPanel panelButton = new JPanel();
        panelButton.add(editItemButton);
        panelButton.add(closeButton);
        this.getContentPane().add(panelButton);

    }

    public JTextField getItemNameField() {
        return itemNameField;
    }

    public JTextField getItemPriceField() {
        return itemPriceField;
    }

    public JTextField getItemQuantityField() {
        return itemQuantityField;
    }

    public JTextField getItemIDField() {
        return itemIDField;
    }

    public JButton getEditItemButton() {
        return editItemButton;
    }

    public JButton getCloseButton() {
        return closeButton;
    }

    public void setItemIDField(JTextField itemIDField) {
        this.itemIDField = itemIDField;
    }

    public void setItemPriceField(JTextField itemPriceField) {
        this.itemPriceField = itemPriceField;
    }

    public void setItemQuantityField(JTextField itemQuantityField) {
        this.itemQuantityField = itemQuantityField;
    }

    public void setItemNameField(JTextField itemNameField) {
        this.itemNameField = itemNameField;
    }

    public void addRow(Object[] row) {
        items.addRow(row);              // add a row to list of item!
        items.fireTableDataChanged();
    }
}
