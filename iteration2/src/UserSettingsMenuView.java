import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.*;

public class UserSettingsMenuView {

    private JLabel userSettingsLabel;

    private JTextField usernameField;
    private JTextField jobTitleField;

    private BufferedImage profilePicture;

    private JButton changePictureButton;
    private JButton changePasswordButton;

    public UserSettingsMenuView() {

    }

    public JButton getChangePictureButton() {
        return changePictureButton;
    }

    public JButton getChangePasswordButton() {
        return changePasswordButton;
    }
}
