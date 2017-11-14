import javax.swing.*;

public class EditItemInventoryView extends JFrame{
    private JLabel editItemInventoryLabel;
    private JLabel itemNameLabel;
    private JLabel itemPriceLabel;
    private JLabel itemQuantityLabel;

    private JTextField itemNameField;
    private JTextField itemQuantityField;
    private JTextField itemPriceField;

    private JTable currentInventory;

    private JButton editItemButton;
    private JButton closeButton;

    public EditItemInventoryView() {
        this.setTitle("Store Manager");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(500, 200);

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
