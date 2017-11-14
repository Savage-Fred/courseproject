import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserSettingsController implements ActionListener  {

    private UserSettingsMenuView userSettingsMenuView;
    private DataAdapter dataAdapter;

    public UserSettingsController(UserSettingsMenuView view, DataAdapter data) {
        this.userSettingsMenuView = view;
        this.dataAdapter = data;

        userSettingsMenuView.getChangePasswordButton().addActionListener(this);
        userSettingsMenuView.getChangePictureButton().addActionListener(this);
        userSettingsMenuView.getCloseButton().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == userSettingsMenuView.getChangePictureButton()) {
            loadChangePicture();
        } else if (e.getSource() == userSettingsMenuView.getChangePasswordButton()) {
            loadChangePassword();
        } else if (e.getSource() == userSettingsMenuView.getCloseButton()) {
            loadClose();
        }

    }

    public void loadChangePassword() {
        Application.getInstance().getPasswordSettingsView().setVisible(true);

    }

    public void loadChangePicture() {
        Application.getInstance().getPhotoSettingsView().setVisible(true);

    }

    public void loadClose() {
        Application.getInstance().getLoginScreenView().setVisible(true);

    }

}
