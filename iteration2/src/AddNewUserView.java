import javax.swing.*;

public class AddNewUserView extends JFrame {

    private JLabel addNewUserLabel = new JLabel("Add New User");
    private JLabel usernameLabel = new JLabel("Username");
    private JLabel passwordLabel = new JLabel("Password");
    private JLabel userTypeLabel = new JLabel("User Type");

    private JTextField usernameField = new JTextField(20);
    private JTextField passwordField = new JTextField(20);
    private JTextField userTypeField = new JTextField(20);

    private JButton addUserButton = new JButton("Add User");
    private JButton closeButton = new JButton("Close");

    public AddNewUserView() {
        this.setTitle("Store Manager");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(500, 200);

        JPanel panelUserFields = new JPanel();
        panelUserFields.add(usernameField);
        panelUserFields.add(usernameLabel);
        panelUserFields.add(passwordField);
        panelUserFields.add(passwordLabel);
        panelUserFields.add(userTypeField);
        panelUserFields.add(userTypeLabel);
        this.getContentPane().add(panelUserFields);

        JPanel panelButton = new JPanel();
        panelButton.add(addUserButton);
        panelButton.add(closeButton);
        this.getContentPane().add(panelButton);


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
