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

        Application.getInstance().getCheckoutView().setVisible(true);
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
}
