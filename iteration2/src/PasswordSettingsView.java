import javax.swing.*;

public class PasswordSettingsView extends JFrame {

    private JLabel changePasswordLabel = new JLabel("Change Password");

    private JTextField oldPasswordField = new JTextField(20);
    private JTextField newPasswordField = new JTextField(20);
    private JTextField verifyPasswordField = new JTextField(20);

    private JButton changePasswordButton = new JButton("Change Password");
    private JButton closeButton = new JButton("Close");

    public PasswordSettingsView() {
        this.setTitle("Store Manager");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(500, 200);

        JPanel panelUserFields = new JPanel();
        panelUserFields.add(oldPasswordField);
        panelUserFields.add(newPasswordField);
        panelUserFields.add(verifyPasswordField);
        this.getContentPane().add(panelUserFields);

        JPanel panelButton = new JPanel();
        panelButton.add(changePasswordButton);
        panelButton.add(closeButton);
        this.getContentPane().add(panelButton);

    }

    public JTextField getNewPasswordField() {
        return newPasswordField;
    }

    public JTextField getOldPasswordField() {
        return oldPasswordField;
    }

    public JTextField getVerifyPasswordField() {
        return verifyPasswordField;
    }

    public JButton getChangePasswordButton() {
        return changePasswordButton;
    }

    public JButton getCloseButton() {
        return closeButton;
    }

}
