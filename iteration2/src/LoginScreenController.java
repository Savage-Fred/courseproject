import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginScreenController implements ActionListener {

    private LoginScreenView loginScreenView;
    private DataAdapter dataAdapter;

    public LoginScreenController(LoginScreenView view, DataAdapter data) {
        this.loginScreenView = view;
        this.dataAdapter = data;

        loginScreenView.getLoginButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginScreenView.getLoginButton()) {
            login();
        }

    }

    /*
     *  TODO:Checks if the user is added to the User table
     *  TODO:Displays Cashier or Manager Menu based on user type
     *  TODO:Message prompts for invalid username and password
     */
    public void login() {
        String usernameIn = loginScreenView.getUsernameField().getText().trim();
        String passwordIn = loginScreenView.getPasswordField().getText().trim();

        if (usernameIn.length() == 0) {
            JOptionPane.showMessageDialog(null, "Invalid Username! Please provide a non-empty Username!");
            return;
        }

        if (passwordIn.length() == 0) {
            JOptionPane.showMessageDialog(null, "Invalid Password! Please provide a non-empty Password!");
            return;
        }


        UserModel user = dataAdapter.loadUser(usernameIn, passwordIn);

        if (user == null) {
            JOptionPane.showMessageDialog(null, "This User does not exist in the database!");
            return;
        }

        if (user.getPassword().compareTo(passwordIn) != 0) {
            JOptionPane.showMessageDialog(null, "The password entered is Invalid!");
            return;
        }

        System.out.println( "User ID: " + user.getUserID()
                + "\nName: " + user.getName()
                + "\nIs Manager: " + user.getIsManager()
                + "\nFullname: " + user.getDisplayName()
                + "\nPassword: " + user.getPassword());


        if (user.getIsManager() == 1) {


            Application.getInstance().getManagerMenuView().setVisible(true);
            Application.getInstance().getLoginScreenView().setVisible(false);

            dataAdapter.loginUser(user);

            Application.getInstance().getManagerMenuController().updateUserFields();


            loginScreenView.getUsernameField().setText("");
            loginScreenView.getPasswordField().setText("");



        } else {
            Application.getInstance().getCashierMenuView().setVisible(true);
            Application.getInstance().getLoginScreenView().setVisible(false);


            dataAdapter.loginUser(user);

            Application.getInstance().getCashierMenuController().updateUserFields();

            loginScreenView.getUsernameField().setText("");
            loginScreenView.getPasswordField().setText("");

        }

    }

}
