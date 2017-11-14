import javax.swing.*;

public class SearchInventoryView extends JFrame {
    private JLabel searchItemLabel = new JLabel("Search Inventory");
    private JLabel itemLabel = new JLabel("Enter Item Name:");

    private JTextField itemField = new JTextField(20);

    // TODO: Add method to insert Inventroy to Jtable
    private JTable currentInventory;

    private JButton searchButton = new JButton("Search");
    private JButton closeButton = new JButton("Close");

    SearchInventoryView() {
        this.setTitle("Store Manager");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(500, 200);

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
}
