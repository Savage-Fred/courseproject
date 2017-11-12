import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordSettingsController implements ActionListener {

    private PasswordSettingsView passwordSettingsView;
    private DataAdapter dataAdapter;

    public PasswordSettingsController(PasswordSettingsView view, DataAdapter data) {
        this.passwordSettingsView = view;
        this.dataAdapter = data;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void changePassword() {

    }

    public void loadClose() {

    }
}
