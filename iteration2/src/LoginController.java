import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




public class LoginController implements ActionListener {
    private LoginScreenView loginScreen;
    private DataAdapter dataAdapter;


    public LoginController(LoginScreenView loginScreen, DataAdapter dataAdapter) {
        this.loginScreen = loginScreen;
        this.dataAdapter = dataAdapter;
        this.loginScreen.getLoginButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginScreen.getLoginButton()) {
            String username = loginScreen.getUsernameField().getText().trim();
            String password = loginScreen.getPasswordField().getText().trim();

            System.out.println("Login with username = " + username + " and password = " + password);
            UserModel user = dataAdapter.loadUser(username, password);

            if (user == null) {
                JOptionPane.showMessageDialog(null, "This user does not exist!");
            }
            else {
                Application.getInstance().setCurrentUser(user);
                this.loginScreen.setVisible(false);
                Application.getInstance().getMainScreen().setVisible(true);
            }
        }
    }
}