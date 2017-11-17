import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddNewUserController implements ActionListener {

    private AddNewUserView addNewUserView;
    private DataAdapter dataAdapter;

    public AddNewUserController(AddNewUserView view, DataAdapter data) {
        this.addNewUserView = view;
        this.dataAdapter = data;

        addNewUserView.getAddUserButton().addActionListener(this);
        addNewUserView.getCloseButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addNewUserView.getAddUserButton()) {
            addNewUser();
        } else if (e.getSource() == addNewUserView.getCloseButton()) {
            loadClose();
        }

    }

    public void addNewUser() {
        String newUserName = addNewUserView.getUsernameField().getText().trim();

        if (newUserName.length() == 0) {
            JOptionPane.showMessageDialog(null, "Invalid Username! Please provide a non-empty username!");
            return;
        }

        String newPassword = addNewUserView.getPasswordField().getText().trim();

        if (newPassword.length() == 0) {
            JOptionPane.showMessageDialog(null, "Invalid Password! Please provide a non-empty password!");
            return;
        }

        String newUserType = addNewUserView.getUserTypeField().getText().trim();

        if (newUserType.length() == 0) {
            JOptionPane.showMessageDialog(null, "Invalid User Type! Please provide a non-empty user type!");
            return;
        }

        if (newUserType.toLowerCase().compareTo("cashier") != 0 || newUserType.toLowerCase().compareTo("manager") != 0) {
            JOptionPane.showMessageDialog(null, "Invalid User type! Please enter Manager or Cashier!");
            return;
        }

        UserModel user = new UserModel();
        user.setName(newUserName);
        user.setPassword(newPassword);
        user.setUserType(newUserType);

        if(dataAdapter.saveUser(user)) {
            JOptionPane.showMessageDialog(null, "New user saved to the System!");
        }

        Application.getInstance().getSystemSettingsView().setVisible(true);
        Application.getInstance().getAddNewUserView().setVisible(false);
    }

    public void loadClose() {
        Application.getInstance().getAddNewUserView().setVisible(false);
        //Application.getInstance().getLoginScreenView().setVisible(true);
    }
}
