import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.*;

public class CashierMenuView extends JFrame {

    private JLabel storeLabel = new JLabel("Bob's Groceries");

    private JTextField usernameField = new JTextField(10);
    private JTextField jobTitleField = new JTextField( 10);

    private BufferedImage profilePicture;

    private JButton checkoutButton = new JButton("Checkout");
    private JButton settingsButton = new JButton("Settings");
    private JButton closeButton = new JButton("Close");

    public CashierMenuView() {
        this.setTitle("Store Manager");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(500, 200);

        // TODO: Create JPanel for profile picture

        JPanel panelUsername = new JPanel();
        panelUsername.add(usernameField);
        this.getContentPane().add(panelUsername);

        JPanel panelJobTitle = new JPanel();
        panelJobTitle.add(jobTitleField);
        this.getContentPane().add(panelJobTitle);

        JPanel panelButton = new JPanel();
        panelButton.add(checkoutButton);
        panelButton.add(settingsButton);
        panelButton.add(closeButton);
        this.getContentPane().add(panelButton);

    }

    public JButton getCheckoutButton() {
        return checkoutButton;
    }

    public JButton getSettingsButton() {
        return settingsButton;
    }

    public JButton getCloseButton() {
        return closeButton;
    }
}
