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
        // TODO: Add method to check user inputs
        // TODO: ADD method to check is user exist in Database
        // TODO: Add method to add user to user table
        Application.getInstance().getSystemSettingsView().setVisible(true);
    }

    public void loadClose() {
        Application.getInstance().getLoginScreenView().setVisible(true);
    }
}
