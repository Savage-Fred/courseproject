import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddNewUserController implements ActionListener {

    private AddNewUserView addNewUserView;
    private DataAdapter dataAdapter;

    public AddNewUserController(AddNewUserView view, DataAdapter data) {
        this.addNewUserView = view;
        this.dataAdapter = data;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void addNewUser() {

    }

    public void loadClose() {

    }
}
