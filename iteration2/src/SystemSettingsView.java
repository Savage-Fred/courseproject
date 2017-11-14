import javax.swing.*;

public class SystemSettingsView extends JFrame {
    private JLabel systemSettingsLabel;
    private JLabel newUserLabel;

    private JButton newUserButton;
    private JButton closeButton;

    public SystemSettingsView() {
        this.setTitle("Store Manager");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(500, 200);

    }

    public JButton getNewUserButton() {
        return newUserButton;
    }

    public JButton getCloseButton() {
        return closeButton;
    }
}


