import javax.swing.*;

public class ManageInventoryView extends JFrame {
    private JLabel manageInventoryLabel = new JLabel("Manage Inventory");

    // TODO: Add method to add current inventory to JTable
    private JTable currentInventory;

    private JButton addItemButton = new JButton("Add Item");
    private JButton editItemButton = new JButton("Edit Item");
    private JButton deleteItemButton = new JButton("Delete Item");
    private JButton closeButton = new JButton("Close");

    public ManageInventoryView() {
        this.setTitle("Store Manager");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(500, 200);

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
}
