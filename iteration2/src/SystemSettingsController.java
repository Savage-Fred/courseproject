import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SystemSettingsController implements ActionListener {

    private SystemSettingsView systemSettingsView;
    private DataAdapter dataAdapter;

    public SystemSettingsController(SystemSettingsView view, DataAdapter data) {
        this.systemSettingsView = view;
        this.dataAdapter = data;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void loadAddNewUser() {

    }

    public void loadClose() {

    }
}
