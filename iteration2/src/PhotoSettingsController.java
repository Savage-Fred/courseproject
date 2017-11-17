import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PhotoSettingsController implements ActionListener {

    private PhotoSettingsView photoSettingsView;
    private DataAdapter dataAdapter;

    private UserModel user = null;

    public PhotoSettingsController(PhotoSettingsView view, DataAdapter data) {
        this.photoSettingsView = view;
        this.dataAdapter = data;

        user = new UserModel();

        photoSettingsView.getChangePicture().addActionListener(this);
        photoSettingsView.getCloseButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == photoSettingsView.getChangePicture()) {
            changePicture();
        } else if (e.getSource() == photoSettingsView.getCloseButton()) {
            loadClose();
        }

    }

    public void changePicture() {
        String newPhotoUrl = photoSettingsView.getNewImageURLField().getText().trim();

        if (newPhotoUrl.length() == 0)
        {
                JOptionPane.showMessageDialog(null, "Invalid URL Path! Please provide a non-empty URL path!");
                return;
        }

        user = dataAdapter.getCurrentUser();

        System.out.println( "User ID: " + user.getUserID()
                + "\nName: " + user.getName()
                + "\nIs Manager: " + user.getIsManager()
                + "\nFullname: " + user.getDisplayName()
                + "\nPassword: " + user.getPassword());

        user.setProfilePicture(newPhotoUrl);

        if (dataAdapter.saveUser(user)) {
            JOptionPane.showMessageDialog(null, "Your Profile Picture has been saved!");

        }


        Application.getInstance().getUserSettingsMenuView().setVisible(true);
        Application.getInstance().getPhotoSettingsView().setVisible(false);
    }

    public void loadClose() {
        Application.getInstance().getPhotoSettingsView().setVisible(false);
        //Application.getInstance().getLoginScreenView().setVisible(true);
    }
}
