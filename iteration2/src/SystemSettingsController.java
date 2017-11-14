import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SystemSettingsController implements ActionListener {

    private SystemSettingsView systemSettingsView;
    private DataAdapter dataAdapter;

    public SystemSettingsController(SystemSettingsView view, DataAdapter data) {
        this.systemSettingsView = view;
        this.dataAdapter = data;

        systemSettingsView.getNewUserButton().addActionListener(this);
        systemSettingsView.getCloseButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == systemSettingsView.getNewUserButton()) {
            loadAddNewUser();
        } else if (e.getSource() == systemSettingsView.getCloseButton()) {
            loadClose();
        }

    }

    public void loadAddNewUser() {
        Application.getInstance().getAddNewUserView().setVisible(true);
    }


    public void loadClose() {
        Application.getInstance().getLoginScreenView().setVisible(true);
    }
}
