import javax.swing.*;
import java.awt.image.*;

public class UserSettingsMenuView extends JFrame {

    private JLabel userSettingsLabel = new JLabel("User Settings");

    private JTextField usernameField = new JTextField(10);
    private JTextField jobTitleField = new JTextField(10);

    private BufferedImage profilePicture;


    private JButton changePictureButton = new JButton("Change Picture");
    private JButton changePasswordButton = new JButton("Change Password");
    private JButton closeButton = new JButton("Close");

    public UserSettingsMenuView() {
        this.setTitle("Store Manager");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(500, 200);

        JPanel panelTitle = new JPanel();
        panelTitle.add(userSettingsLabel);
        this.getContentPane().add(panelTitle);

        JPanel panelUserInfo = new JPanel();
        panelUserInfo.add(usernameField);
        panelUserInfo.add(jobTitleField);
        this.getContentPane().add(panelUserInfo);

        JPanel panelButton = new JPanel();
        panelButton.add(changePictureButton);
        panelButton.add(changePasswordButton);
        panelButton.add(closeButton);
        this.getContentPane().add(panelButton);


    }


    public JButton getChangePictureButton() {
        return changePictureButton;
    }

    public JButton getChangePasswordButton() {
        return changePasswordButton;
    }

    public JButton getCloseButton() {
            return closeButton;
    }

    public void setUsernameField(String usernameField) {
        this.usernameField.setText(usernameField);
    }

    public void setJobTitleField(String jobTitleField) {
        this.jobTitleField.setText(jobTitleField);
    }
}
