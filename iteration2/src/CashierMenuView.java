import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.*;

public class CashierMenuView {

    private JLabel storeLabel;

    private JTextField usernameField;
    private JTextField jobTitleField;

    private BufferedImage profilePicture;

    private JButton checkoutButton;
    private JButton settingsButton;

    public CashierMenuView() {

    }

    public JButton getCheckoutButton() {
        return checkoutButton;
    }

    public JButton getSettingsButton() {
        return settingsButton;
    }
}
