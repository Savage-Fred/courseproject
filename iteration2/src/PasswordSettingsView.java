import javax.swing.*;

public class PasswordSettingsView extends JFrame {

    private JLabel changePasswordLabel = new JLabel("Change Password");

    private JLabel currentPasswordLabel = new JLabel("Current Password");
    private JLabel newPasswordLabel = new JLabel("New Password");
    private JLabel verifyPasswordLabel = new JLabel("Verify Password");



    private JTextField currentPasswordField = new JTextField(20);
    private JTextField newPasswordField = new JTextField(20);
    private JTextField verifyPasswordField = new JTextField(20);

    private JButton changePasswordButton = new JButton("Change Password");
    private JButton closeButton = new JButton("Close");

    public PasswordSettingsView() {
        this.setTitle("Store Manager");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(500, 200);

        JPanel panelCurrPass = new JPanel();
        panelCurrPass.add(currentPasswordLabel);
        panelCurrPass.add(currentPasswordField);
        this.getContentPane().add(panelCurrPass);


        JPanel panelNewPass = new JPanel();
        panelNewPass.add(newPasswordLabel);
        panelNewPass.add(newPasswordField);
        this.getContentPane().add(panelNewPass);


        JPanel panelVerPass = new JPanel();
        panelVerPass.add(verifyPasswordLabel);
        panelVerPass.add(verifyPasswordField);
        this.getContentPane().add(panelVerPass);

        JPanel panelButton = new JPanel();
        panelButton.add(changePasswordButton);
        panelButton.add(closeButton);
        this.getContentPane().add(panelButton);

    }

    public JTextField getNewPasswordField() {
        return newPasswordField;
    }

    public JTextField getCurrentPasswordField() {
        return currentPasswordField;
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
