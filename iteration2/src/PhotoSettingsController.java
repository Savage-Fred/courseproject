import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class PhotoSettingsController implements ActionListener {

    private PhotoSettingsView photoSettingsView;
    private DataAdapter dataAdapter;

    private UserModel user = null;

    public PhotoSettingsController(PhotoSettingsView view, DataAdapter data) {
        this.photoSettingsView = view;
        this.dataAdapter = data;

        user = new UserModel();

        photoSettingsView.getChangePicture().addActionListener(this);
        photoSettingsView.getCloseButton().addActionListener(this);

        // Equivalent to System.exit is JFrame
        //frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == photoSettingsView.getChangePicture()) {
            changePicture();
        } else if (e.getSource() == photoSettingsView.getCloseButton()) {
            loadClose();
        }

    }

    public void changePicture() {


        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE","*.jpg", "*.gif", "*.png" );
        fileChooser.addChoosableFileFilter(filter);
        int result = fileChooser.showSaveDialog(null);

        String newPhotoUrl = photoSettingsView.getNewImageURLField().getText().trim();


        if (result == JFileChooser.APPROVE_OPTION){
            File selectedFile = fileChooser.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            photoSettingsView.getProfilePicture().setIcon(ResizeImage(path));

            newPhotoUrl = path;
        } else if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("no data");
        }

        if (newPhotoUrl.length() == 0)
        {
                JOptionPane.showMessageDialog(null, "Invalid URL Path! Please provide a non-empty URL path!");
                return;
        }

        user = dataAdapter.getCurrentUser();

        user.setProfilePicture(newPhotoUrl);


        System.out.println( "User ID: " + user.getUserID()
                + "\nName: " + user.getName()
                + "\nIs Manager: " + user.getIsManager()
                + "\nFullname: " + user.getDisplayName()
                + "\nPassword: " + user.getPassword()
                + "\nProfile Picture: " + user.getProfilePicture());


        if (dataAdapter.saveUser(user)) {
            dataAdapter.saveUser(user);
            JOptionPane.showMessageDialog(null, "Your Profile Picture has been saved!");

        }

    }

    public void loadClose() {
        Application.getInstance().getPhotoSettingsView().setVisible(false);
        Application.getInstance().getUserSettingsMenuView().setVisible(true);
    }

    //Method To Resize The ImageIcon
    public ImageIcon ResizeImage(String imgPath){
        ImageIcon MyImage = new ImageIcon(imgPath);
        Image img = MyImage.getImage();
        photoSettingsView.getProfilePicture().setBounds(10,10,200,100);
        Image newImage = img.getScaledInstance(photoSettingsView.getProfilePicture().getWidth(), photoSettingsView.getProfilePicture().getHeight(),Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImage);
        return image;
    }
}
