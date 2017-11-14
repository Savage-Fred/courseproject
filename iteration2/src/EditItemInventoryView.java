import javax.swing.*;

public class EditItemInventoryView extends JFrame{
    private JLabel editItemInventoryLabel = new JLabel("Edit Item");
    private JLabel itemNameLabel = new JLabel("Item Name");
    private JLabel itemPriceLabel = new JLabel("Item Price");
    private JLabel itemQuantityLabel = new JLabel("Item Quantity");

    private JTextField itemNameField = new JTextField(20);
    private JTextField itemQuantityField = new JTextField(20);
    private JTextField itemPriceField = new JTextField(20);

    // TODO: Add Method to inset selected product into JTable
    private JTable currentInventory;

    private JButton editItemButton = new JButton("Edit Item");
    private JButton closeButton = new JButton("Close");

    public EditItemInventoryView() {
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

    public JButton getEditItemButton() {
        return editItemButton;
    }

    public JButton getCloseButton() {
        return closeButton;
    }
}
