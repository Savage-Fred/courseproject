import javax.swing.*;
import java.awt.image.BufferedImage;

public class PhotoSettingsView extends JFrame {

    private JLabel photoSettingsLabel = new JLabel("Photo Settings");
    private JLabel newPhotoURLLabel = new JLabel("Please enter the url for the new photo");

    private BufferedImage profilePicture;

    private JTextField newImageURLField = new JTextField(20);

    private JButton closeButton = new JButton("Close");
    private JButton changePicture = new JButton("Change Photo");

    public PhotoSettingsView() {
        this.setTitle("Store Manager");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setSize(500, 200);

        JPanel panelTitle = new JPanel();
        panelTitle.add(photoSettingsLabel);
        this.getContentPane().add(panelTitle);

        JPanel panelUserInput = new JPanel();
        //panelUserInput.setLayout(new BoxLayout(panelUserInput, BoxLayout.Y_AXIS));
        panelUserInput.add(newImageURLField);
        panelUserInput.add(newPhotoURLLabel);
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
