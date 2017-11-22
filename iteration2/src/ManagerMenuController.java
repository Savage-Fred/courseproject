import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerMenuController implements ActionListener {

    private ManagerMenuView managerMenuView;
    private DataAdapter dataAdapter;

    private UserModel user = null;

    public ManagerMenuController(ManagerMenuView view, DataAdapter data ) {
        this.managerMenuView = view;
        this.dataAdapter = data;

        managerMenuView.getManageInventoryButton().addActionListener(this);
        managerMenuView.getReportsButton().addActionListener(this);
        managerMenuView.getSystemSettingsButton().addActionListener(this);
        managerMenuView.getSettingsButton().addActionListener(this);
        managerMenuView.getCloseButton().addActionListener(this);


        user = new UserModel();

    }

    public void updateUserFields() {
        user = dataAdapter.getCurrentUser();

        managerMenuView.getProfilePicture().setIcon(ResizeImage(user.getProfilePicture()));


        managerMenuView.setJobTitleField(user.getJobTitle());
        managerMenuView.setUsernameField(user.getName());

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateUserFields();
        if (e.getSource() == managerMenuView.getManageInventoryButton()) {
            loadManageInventory();
        } else if (e.getSource() == managerMenuView.getReportsButton()) {
            loadReports();
        } else if (e.getSource() == managerMenuView.getSystemSettingsButton()) {
            loadSystemSettings();
        } else if (e.getSource() == managerMenuView.getSettingsButton()) {
            loadSettings();
        } else if (e.getSource() == managerMenuView.getCloseButton()) {
            loadClose();
        }

    }


    public void loadManageInventory() {

        Application.getInstance().getManageInventoryController().loadTable();
        Application.getInstance().getManageInventoryView().setVisible(true);
        Application.getInstance().getManagerMenuView().setVisible(false);
    }

    public void loadSystemSettings() {
        Application.getInstance().getSystemSettingsView().setVisible(true);
        Application.getInstance().getManagerMenuView().setVisible(false);

    }

    public void loadReports() {
        Application.getInstance().getReportMenuView().setVisible(true);
        Application.getInstance().getManagerMenuView().setVisible(false);

    }

    public void loadSettings() {
        Application.getInstance().getUserSettingsController().updateUserFields();

        Application.getInstance().getUserSettingsMenuView().setVisible(true);
        Application.getInstance().getManagerMenuView().setVisible(false);

    }

    public void loadClose() {
        Application.getInstance().getManagerMenuView().setVisible(false);
        dataAdapter.logoutUser();
        Application.getInstance().getLoginScreenView().setVisible(true);
    }

    public ImageIcon ResizeImage(String imgPath){
        ImageIcon MyImage = new ImageIcon(imgPath);
        Image img = MyImage.getImage();
        managerMenuView.getProfilePicture().setBounds(10,10,200,100);
        Image newImage = img.getScaledInstance(managerMenuView.getProfilePicture().getWidth(), managerMenuView.getProfilePicture().getHeight(),Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImage);
        return image;
    }
}
