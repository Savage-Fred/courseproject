import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordSettingsController implements ActionListener {

    private PasswordSettingsView passwordSettingsView;
    private DataAdapter dataAdapter;

    public PasswordSettingsController(PasswordSettingsView view, DataAdapter data) {
        this.passwordSettingsView = view;
        this.dataAdapter = data;

        passwordSettingsView.getChangePasswordButton().addActionListener(this);
        passwordSettingsView.getCloseButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == passwordSettingsView.getChangePasswordButton()) {
            changePassword();
        } else if (e.getSource() == passwordSettingsView.getCloseButton()) {
            loadClose();
        }
    }

    public void changePassword() {
        Application.getInstance().getUserSettingsMenuView().setVisible(true);
    }

    public void loadClose() {
        Application.getInstance().getLoginScreenView().setVisible(true);

    }
}
