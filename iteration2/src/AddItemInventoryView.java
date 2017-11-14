import javax.swing.*;

public class AddItemInventoryView extends JFrame {

    private JLabel addItemInventoryLabel = new JLabel("Add Item");
    private JLabel itemNameLabel = new JLabel("Item Name");
    private JLabel itemPriceLabel = new JLabel("Item Price");
    private JLabel itemQuantityLabel = new JLabel("Item Quantity");

    private JTextField itemNameField = new JTextField(20);
    private JTextField itemQuantityField = new JTextField(20);
    private JTextField itemPriceField = new JTextField(20);

    // TODO: Add method to insert Inventory to JTable
    private JTable currentInventory;

    private JButton addItemButton = new JButton("Add Item");
    private JButton closeButton = new JButton("Close");

    public AddItemInventoryView() {
        this.setTitle("Store Manager");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(500, 200);

        JPanel panelUserFields = new JPanel();
        panelUserFields.add(itemNameField);
        panelUserFields.add(itemNameLabel);
        panelUserFields.add(itemPriceField);
        panelUserFields.add(itemPriceLabel);
        panelUserFields.add(itemQuantityField);
        panelUserFields.add(itemQuantityLabel);
        this.getContentPane().add(panelUserFields);

        JPanel panelButton = new JPanel();
        panelButton.add(addItemButton);
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

    public JButton getAddItemButton() {
        return addItemButton;
    }

    public JButton getCloseButton() {
        return closeButton;
    }
}
