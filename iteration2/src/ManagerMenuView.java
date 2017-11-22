import javax.swing.*;
import java.awt.image.*;


public class ManagerMenuView extends JFrame {

    private JLabel storeLabel = new JLabel("Manager Mode");

    private JTextField usernameField = new JTextField(10);
    private JTextField jobTitleField = new JTextField(10);

    private JLabel profilePicture = new JLabel();

    private JButton manageInventoryButton = new JButton("Manage Inventory");
    private JButton reportsButton = new JButton("Reports Menu");
    private JButton systemSettingsButton = new JButton("System Settings");
    private JButton settingsButton = new JButton("User Settings");
    private JButton closeButton = new JButton("Close");

    public ManagerMenuView() {
        this.setTitle("Store Manager");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(500, 400);

        JPanel panelTitle = new JPanel();
        panelTitle.add(storeLabel);
        this.getContentPane().add(panelTitle);

        JPanel panelPhoto = new JPanel();
        profilePicture.setBounds(10,10,200,100);
        panelPhoto.add(profilePicture);
        this.getContentPane().add(panelPhoto);

        JPanel panelUserInfo = new JPanel();
        panelUserInfo.add(new JLabel("Username: "));
        panelUserInfo.add(usernameField);
        panelUserInfo.add(new JLabel("Jobtitle: "));
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

    public void setJobTitleField(String jobTitleField) {
        this.jobTitleField.setText(jobTitleField);
    }

    public void setUsernameField(String usernameField) {
        this.usernameField.setText(usernameField);
    }

    public JLabel getProfilePicture() {
        return profilePicture;
    }
}
