import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.*;


public class ManagerMenuView {

    private JLabel storeLabel;

    private JTextField usernameField;
    private JTextField jobTitleField;

    private BufferedImage profilePicture;

    private JButton manageInventoryButton;
    private JButton systemSettingsButton;
    private JButton reportsButton;
    private JButton settingsButton;
    private JButton closeButton;

    public ManagerMenuView() {

    }

    public JButton getManageInventoryButton() {
        return manageInventoryButton;
    }

    public JButton getSystemSettingsButton() {
        return systemSettingsButton;
    }

    public JButton getReportsButton() {
        return reportsButton;
    }

    public JButton getSettingsButton() {
        return settingsButton;
    }

    public JButton getCloseButton() {
        return closeButton;
    }
}
