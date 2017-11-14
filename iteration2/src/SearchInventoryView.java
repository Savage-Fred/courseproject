import javax.swing.*;

public class SearchInventoryView extends JFrame {
    private JLabel searchItemLabel;
    private JLabel itemLabel;

    private JTextField itemField;

    private JTable currentInventory;

    private JButton closeButton;

    SearchInventoryView() {
        this.setTitle("Store Manager");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(500, 200);

    }

    public JTextField getItemField() {
        return itemField;
    }

    public JButton getCloseButton() {
        return closeButton;
    }
}
