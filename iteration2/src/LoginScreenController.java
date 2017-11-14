import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        if ("C" == loginScreenView.getUsernameField().getText()) {
            Application.getInstance().getCashierMenuView().setVisible(true);
        } else if ("M" == loginScreenView.getUsernameField().getText()) {
            Application.getInstance().getManagerMenuView().setVisible(true);
        }
    }

}
