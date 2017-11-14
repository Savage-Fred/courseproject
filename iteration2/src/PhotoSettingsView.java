import javax.swing.*;
import java.awt.image.BufferedImage;

public class PhotoSettingsView extends JFrame {

    private JLabel photoSettingsLabel = new JLabel("Photo Settings");

    private BufferedImage profilePicture;

    private JTextField newImageURLField = new JTextField(20);

    private JButton closeButton = new JButton("Close");
    private JButton changePicture = new JButton("Change Photo");

    public PhotoSettingsView() {
        this.setTitle("Store Manager");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(500, 200);

        JPanel panelUserInput = new JPanel();
        panelUserInput.add(newImageURLField);
        newImageURLField.setHorizontalAlignment(JTextField.RIGHT);
        this.getContentPane().add(panelUserInput);

        JPanel panelButton = new JPanel();
        panelButton.add(changePicture);
        panelButton.add(closeButton);
        this.getContentPane().add(panelButton);

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
