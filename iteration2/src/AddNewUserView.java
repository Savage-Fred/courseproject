import javax.swing.*;

public class AddNewUserView {

    private JLabel addNewUserLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel userTypeLabel;

    private JTextField usernameField;
    private JTextField passwordField;
    private JTextField userTypeField;

    private JButton addUserButton;
    private JButton closeButton;

    public AddNewUserView() {

    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JTextField getPasswordField() {
        return passwordField;
    }

    public JTextField getUserTypeField() {
        return userTypeField;
    }

    public JButton getAddUserButton() {
        return addUserButton;
    }

    public JButton getCloseButton() {
        return closeButton;
    }
}
