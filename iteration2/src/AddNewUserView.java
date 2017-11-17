import javax.swing.*;

public class AddNewUserView extends JFrame {

    private JLabel addNewUserLabel = new JLabel("Add New User");
    private JLabel usernameLabel = new JLabel("Username");
    private JLabel passwordLabel = new JLabel("Password");
    private JLabel userTypeLabel = new JLabel("User Type");
    private JLabel userTypePromptLabel = new JLabel("(Manager or Cashier)");


    private JTextField usernameField = new JTextField(20);
    private JTextField passwordField = new JTextField(20);
    private JTextField userTypeField = new JTextField(20);

    private JButton addUserButton = new JButton("Add User");
    private JButton closeButton = new JButton("Close");

    public AddNewUserView() {
        this.setTitle("Store Manager");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(500, 200);

        JPanel panelUsername = new JPanel();
        panelUsername.add(usernameLabel);
        panelUsername.add(usernameField);

        JPanel panelPassword = new JPanel();
        panelPassword.add(passwordLabel);
        panelPassword.add(passwordField);

        JPanel panelUserType = new JPanel();
        panelUserType.add(userTypeLabel);
        panelUserType.add(userTypeField);

        JPanel panelUserFields = new JPanel();
        panelUserFields.setLayout(new BoxLayout(panelUserFields, BoxLayout.Y_AXIS));
        panelUserFields.add(addNewUserLabel);
        panelUserFields.add(panelUsername);
        panelUserFields.add(panelPassword);
        panelUserFields.add(userTypePromptLabel);
        panelUserFields.add(panelUserType);
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
