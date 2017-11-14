import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerMenuController implements ActionListener {

    private ManagerMenuView managerMenuView;
    private DataAdapter dataAdapter;

    public ManagerMenuController(ManagerMenuView view, DataAdapter data ) {
        this.managerMenuView = view;
        this.dataAdapter = data;

        managerMenuView.getManageInventoryButton().addActionListener(this);
        managerMenuView.getReportsButton().addActionListener(this);
        managerMenuView.getSystemSettingsButton().addActionListener(this);
        managerMenuView.getSettingsButton().addActionListener(this);
        managerMenuView.getCloseButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
        Application.getInstance().getManageInventoryView();
    }

    public void loadSystemSettings() {
        Application.getInstance().getSystemSettingsView();

    }

    public void loadReports() {
        Application.getInstance().getBusinessReportView();
    }

    public void loadSettings() {
        Application.getInstance().getUserSettingsMenuView();
    }

    public void loadClose() {
        Application.getInstance().getLoginScreenView();
    }
}
