import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PhotoSettingsController implements ActionListener {

    private PhotoSettingsView photoSettingsView;
    private DataAdapter dataAdapter;

    public PhotoSettingsController(PhotoSettingsView view, DataAdapter data) {
        this.photoSettingsView = view;
        this.dataAdapter = data;

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
        Application.getInstance().getUserSettingsMenuView().setVisible(true);
    }

    public void loadClose() {
        Application.getInstance().getPhotoSettingsView().setVisible(false);
        //Application.getInstance().getLoginScreenView().setVisible(true);
    }
}
