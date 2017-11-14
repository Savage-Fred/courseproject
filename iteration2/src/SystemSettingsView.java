import javax.swing.*;

public class SystemSettingsView extends JFrame {
    private JLabel systemSettingsLabel = new JLabel("System Settings");

    private JButton newUserButton = new JButton("New User");
    private JButton closeButton = new JButton("Close");

    public SystemSettingsView() {
        this.setTitle("Store Manager");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(500, 200);

        JPanel panelButton = new JPanel();
        panelButton.add(newUserButton);
        panelButton.add(closeButton);
        this.getContentPane().add(panelButton);

    }

    public JButton getNewUserButton() {
        return newUserButton;
    }

    public JButton getCloseButton() {
        return closeButton;
    }
}


