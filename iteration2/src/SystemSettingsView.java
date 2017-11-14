import javax.swing.*;

public class SystemSettingsView extends JFrame {
    private JLabel systemSettingsLabel;
    private JLabel newUserLabel;

    private JButton newUserButton;
    private JButton closeButton;

    public SystemSettingsView() {

    }

    public JButton getNewUserButton() {
        return newUserButton;
    }

    public JButton getCloseButton() {
        return closeButton;
    }
}


