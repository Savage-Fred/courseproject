import javax.swing.*;

public class PasswordSettingsView extends JFrame {

    private JLabel changePasswordLabel;

    private JTextField oldPasswordField;
    private JTextField newPasswordField;
    private JTextField verifyPasswordField;

    private JButton changePasswordButton;
    private JButton closeButton;

    public PasswordSettingsView() {

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
