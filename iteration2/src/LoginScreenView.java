import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.*;

public class LoginScreenView extends JFrame {

    private JLabel storeLabel = new JLabel("Login");

    private JTextField usernameField = new JTextField(30);
    private JTextField passwordField = new JTextField(30);

    private JButton loginButton = new JButton("Login");

    public LoginScreenView(){
        this.setTitle("Store Manager");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(500, 200);

        JPanel panelUsername = new JPanel();
        panelUsername.add(new JLabel("Username:"));
        panelUsername.add(usernameField);
        usernameField.setHorizontalAlignment(JTextField.RIGHT);
        this.getContentPane().add(panelUsername);

        JPanel panelPassword = new JPanel();
        panelPassword.add(new JLabel("Password:"));
        panelPassword.add(passwordField);
        passwordField.setHorizontalAlignment(JTextField.RIGHT);
        this.getContentPane().add(panelPassword);

        JPanel panelButton = new JPanel();
        panelButton.add(loginButton);
        this.getContentPane().add(panelButton);


    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JTextField getUsernameField() {
        return this.usernameField;
    }

    public JTextField getPasswordField() {
        return this.passwordField;
    }


}
