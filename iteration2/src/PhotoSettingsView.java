import javax.swing.*;
import java.awt.image.BufferedImage;

public class PhotoSettingsView extends JFrame {

    private JLabel photoSettingsLabel;

    private BufferedImage profilePicture;

    private JTextField newImageURLField;

    private JButton closeButton;
    private JButton changePicture;

    public PhotoSettingsView() {

    }

    public JTextField getNewImageURLField() {
        return newImageURLField;
    }

    public JButton getChangePicture() {
        return changePicture;
    }

    public JButton getCloseButton() {
        return closeButton;
    }
}
