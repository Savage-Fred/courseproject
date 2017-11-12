import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PhotoSettingsController implements ActionListener {

    private PhotoSettingsView photoSettingsView;
    private DataAdapter dataAdapter;

    public PhotoSettingsController(PhotoSettingsView view, DataAdapter data) {
        this.photoSettingsView = view;
        this.dataAdapter = data;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void changePicture() {

    }

    public void loadClose() {

    }
}
