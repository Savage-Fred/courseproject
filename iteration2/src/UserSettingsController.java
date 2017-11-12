import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserSettingsController implements ActionListener  {

    private UserSettingsMenuView userSettingsMenuView;
    private DataAdapter dataAdapter;

    public UserSettingsController(UserSettingsMenuView view, DataAdapter data) {
        this.userSettingsMenuView = view;
        this.dataAdapter = data;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void loadChangePassword() {

    }

    public void loadChangePicture() {

    }

    public void loadClose() {

    }

}
