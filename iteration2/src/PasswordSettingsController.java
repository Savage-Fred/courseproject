import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordSettingsController implements ActionListener {

    private PasswordSettingsView passwordSettingsView;
    private DataAdapter dataAdapter;

    private UserModel user = null;

    public PasswordSettingsController(PasswordSettingsView view, DataAdapter data) {
        this.passwordSettingsView = view;
        this.dataAdapter = data;

        user = new UserModel();

        passwordSettingsView.getChangePasswordButton().addActionListener(this);
        passwordSettingsView.getCloseButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == passwordSettingsView.getChangePasswordButton()) {
            changePassword();
        } else if (e.getSource() == passwordSettingsView.getCloseButton()) {
            loadClose();
        }
    }

    public void changePassword() {
        user = dataAdapter.getCurrentUser();
        String currentPassword = passwordSettingsView.getCurrentPasswordField().getText().trim();

        // Check for non-empty current password
        if (currentPassword.length() == 0) {
            JOptionPane.showMessageDialog(null, "Invalid current password! Please provide a non-empty password!");
            return;
        }

        String newPassword = passwordSettingsView.getNewPasswordField().getText().trim();

        // Check for non-empty new password
        if (newPassword.length() == 0) {
            JOptionPane.showMessageDialog(null, "Invalid new password! Please provide a non-empty password!");
            return;
        }

        String verifyPassword = passwordSettingsView.getVerifyPasswordField().getText().trim();

        // Check for non-empty verify password
        if (verifyPassword.length() == 0) {
            JOptionPane.showMessageDialog(null, "Invalid verify password! Please provide a non-empty password!");
            return;
        }

        // Validates current password with database
        if (user.getPassword().compareTo(currentPassword) != 0) {
            JOptionPane.showMessageDialog(null, "Invalid current password! Please provide the correct password for your account!");
            return;
        }

        if (newPassword.compareTo(verifyPassword) != 0) {
            JOptionPane.showMessageDialog(null, "New Password does not match verify password! Please make sure the same password for both fields!");
            return;
        }

        user.setPassword(newPassword);

        if( dataAdapter.saveUser(user)) {

            JOptionPane.showMessageDialog(null, "Your password has been updated!");
            Application.getInstance().getUserSettingsMenuView().setVisible(true);
            Application.getInstance().getPasswordSettingsView().setVisible(false);
        }



    }

    public void loadClose() {
        Application.getInstance().getPasswordSettingsView().setVisible(false);
        Application.getInstance().getUserSettingsMenuView().setVisible(true);

    }
}
