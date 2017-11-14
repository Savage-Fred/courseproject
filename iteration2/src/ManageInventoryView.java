import javax.swing.*;

public class ManageInventoryView extends JFrame {
    private JLabel manageInventoryLabel;

    private JTable currentInventory;

    private JButton addItemButton;
    private JButton editItemButton;
    private JButton deleteItemButton;
    private JButton closeButton;

    public ManageInventoryView() {

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
}
