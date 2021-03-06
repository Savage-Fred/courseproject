import javax.swing.*;

import java.awt.image.*;

public class CashierMenuView extends JFrame {

    private JLabel storeLabel = new JLabel("Cashier Menu");
    private JLabel usernameLabel = new JLabel("Username");
    private JLabel jobTitleLabel = new JLabel("Job Title");

    private JTextField usernameField = new JTextField(10);
    private JTextField jobTitleField = new JTextField( 10);

    private JLabel profilePicture = new JLabel();

    private JButton checkoutButton = new JButton("Checkout");
    private JButton settingsButton = new JButton("Settings");
    private JButton closeButton = new JButton("Close");

    public CashierMenuView() {
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

        JPanel panelUsername = new JPanel();
        panelUsername.add(usernameLabel);
        panelUsername.add(usernameField);
        this.getContentPane().add(panelUsername);

        JPanel panelJobTitle = new JPanel();
        panelJobTitle.add(jobTitleLabel);
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
