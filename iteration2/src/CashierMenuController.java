import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CashierMenuController implements ActionListener {

    private CashierMenuView cashierMenuView;
    private DataAdapter dataAdapter;

    public CashierMenuController(CashierMenuView view, DataAdapter data) {
        this.cashierMenuView = view;
        this.dataAdapter = data;

        cashierMenuView.getCheckoutButton().addActionListener(this);
        cashierMenuView.getSettingsButton().addActionListener(this);
        cashierMenuView.getCloseButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

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
    }

    public void loadSettings() {
        Application.getInstance().getUserSettingsMenuView().setVisible(true);
    }

    public void loadClose() {
        Application.getInstance().getLoginScreenView().setVisible(true);
    }
}
