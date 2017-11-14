import javax.swing.*;

public class AddItemInventoryView extends JFrame {

    private JLabel addItemInventoryLabel;
    private JLabel itemNameLabel;
    private JLabel itemPriceLabel;
    private JLabel itemQuantityLabel;

    private JTextField itemNameField;
    private JTextField itemQuantityField;
    private JTextField itemPriceField;

    private JTable currentInventory;

    private JButton addItemButton;
    private JButton closeButton;

    public AddItemInventoryView() {
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

    public JButton getAddItemButton() {
        return addItemButton;
    }

    public JButton getCloseButton() {
        return closeButton;
    }
}
