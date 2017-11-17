import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserSettingsController implements ActionListener  {

    private UserSettingsMenuView userSettingsMenuView;
    private DataAdapter dataAdapter;

    private UserModel user = null;

    public UserSettingsController(UserSettingsMenuView view, DataAdapter data) {
        this.userSettingsMenuView = view;
        this.dataAdapter = data;

        user = new UserModel();

        userSettingsMenuView.getChangePasswordButton().addActionListener(this);
        userSettingsMenuView.getChangePictureButton().addActionListener(this);
        userSettingsMenuView.getCloseButton().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateUserFields();
        if (e.getSource() == userSettingsMenuView.getChangePictureButton()) {
            loadChangePicture();
        } else if (e.getSource() == userSettingsMenuView.getChangePasswordButton()) {
            loadChangePassword();
        } else if (e.getSource() == userSettingsMenuView.getCloseButton()) {
            loadClose();
        }

    }

    public void updateUserFields() {
        user = dataAdapter.getCurrentUser();

        userSettingsMenuView.setJobTitleField(user.getJobTitle());
        userSettingsMenuView.setUsernameField(user.getName());

    }

    public void loadChangePassword() {
        Application.getInstance().getPasswordSettingsView().setVisible(true);
        Application.getInstance().getUserSettingsMenuView().setVisible(false);

    }

    public void loadChangePicture() {
        Application.getInstance().getPhotoSettingsView().setVisible(true);
        Application.getInstance().getUserSettingsMenuView().setVisible(false);

    }

    public void loadClose() {
        Application.getInstance().getUserSettingsMenuView().setVisible(false);

        if (dataAdapter.getCurrentUser().getIsManager() == 0 || dataAdapter.getCurrentUser().getIsManager() == 1) {
            if (dataAdapter.getCurrentUser().getIsManager() == 1) {
                Application.getInstance().getManagerMenuView().setVisible(true);
            } else {
                Application.getInstance().getCashierMenuView().setVisible(true);

            }
        }
    }

}
