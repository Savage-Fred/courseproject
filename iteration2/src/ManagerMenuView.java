import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.*;


public class ManagerMenuView extends JFrame {

    private JLabel storeLabel = new JLabel("Manager Mode");

    private JTextField usernameField = new JTextField(10);
    private JTextField jobTitleField = new JTextField(10);

    private BufferedImage profilePicture;

    private JButton manageInventoryButton = new JButton("Manage Inventory");
    private JButton reportsButton = new JButton("Reports Menu");
    private JButton systemSettingsButton = new JButton("System Settings");
    private JButton settingsButton = new JButton("User Settings");
    private JButton closeButton = new JButton("Close");

    public ManagerMenuView() {
        this.setTitle("Store Manager");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(500, 200);

        JPanel panelUserInfo = new JPanel();
        panelUserInfo.add(usernameField);
        panelUserInfo.add(jobTitleField);
        this.getContentPane().add(panelUserInfo);

        JPanel panelButton = new JPanel();
        panelButton.add(manageInventoryButton);
        panelButton.add(reportsButton);
        panelButton.add(systemSettingsButton);
        panelButton.add(settingsButton);
        panelButton.add(closeButton);
        this.getContentPane().add(panelButton);

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
