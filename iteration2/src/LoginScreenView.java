import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.*;

public class LoginScreenView extends JFrame {

    private JLabel storeLabel;

    private JTextField usernameField;
    private JTextField passwordField;

    private JButton loginButton;

    public LoginScreenView(){

    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JTextField getPasswordField() {
        return passwordField;
    }


}
