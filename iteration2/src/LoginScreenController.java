import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreenController implements ActionListener {

    private LoginScreenView loginScreenView;
    private DataAdapter dataAdapter;

    public LoginScreenController(LoginScreenView view, DataAdapter data) {
        this.loginScreenView = view;
        this.dataAdapter = data;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void login() {

    }
}
