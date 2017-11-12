import javax.swing.*;

public class SearchInventoryView {
    private JLabel searchItemLabel;
    private JLabel itemLabel;

    private JTextField itemField;

    private JTable currentInventory;

    private JButton closeButton;

    SearchInventoryView() {

    }

    public JTextField getItemField() {
        return itemField;
    }

    public JButton getCloseButton() {
        return closeButton;
    }
}
