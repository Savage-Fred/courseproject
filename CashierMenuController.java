import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CashierMenuController implements ActionListener {

    private CashierMenuView cashierMenuView;
    private DataAdapter dataAdapter;

    private UserModel user = null;

    public CashierMenuController(CashierMenuView view, DataAdapter data) {
        this.cashierMenuView = view;
        this.dataAdapter = data;

        cashierMenuView.getCheckoutButton().addActionListener(this);
        cashierMenuView.getSettingsButton().addActionListener(this);
        cashierMenuView.getCloseButton().addActionListener(this);

        user = new UserModel();
    }
    public void updateUserFields() {
        user = dataAdapter.getCurrentUser();

        cashierMenuView.getProfilePicture().setIcon(ResizeImage(user.getProfilePicture()));

        cashierMenuView.setJobTitleField(user.getJobTitle());
        cashierMenuView.setUsernameField(user.getName());

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        updateUserFields();

        if (e.getSource() == cashierMenuView.getCheckoutButton()) {
            loadCheckout();
        } else if (e.getSource() == cashierMenuView.getSettingsButton()) {
            loadSettings();
        } else if (e.getSource() == cashierMenuView.getCloseButton()) {
            loadClose();
        }

    }

    public void loadCheckout() {

        Application.getInstance().getOrderSelectionView().setVisible(true);
        Application.getInstance().getCashierMenuView().setVisible(false);

    }

    public void loadSettings() {

        Application.getInstance().getUserSettingsController().updateUserFields();
        Application.getInstance().getUserSettingsMenuView().setVisible(true);
        Application.getInstance().getCashierMenuView().setVisible(false);

    }

    public void loadClose() {

        Application.getInstance().getLoginScreenView().setVisible(true);
        Application.getInstance().getCashierMenuView().setVisible(false);

        dataAdapter.logoutUser();
    }

    public ImageIcon ResizeImage(String imgPath){
        ImageIcon MyImage = new ImageIcon(imgPath);
        Image img = MyImage.getImage();
        cashierMenuView.getProfilePicture().setBounds(10,10,200,100);
        Image newImage = img.getScaledInstance(cashierMenuView.getProfilePicture().getWidth(), cashierMenuView.getProfilePicture().getHeight(),Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImage);
        return image;
    }
}
