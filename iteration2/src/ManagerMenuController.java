import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerMenuController implements ActionListener {

    private ManagerMenuView managerMenuView;
    private DataAdapter dataAdapter;

    public ManagerMenuController(ManagerMenuView view, DataAdapter data ) {
        this.managerMenuView = view;
        this.dataAdapter = data;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void loadManageInventory() {

    }

    public void loadSystemSettings() {

    }

    public void loadReports() {

    }

    public void loadSettings() {

    }

    public void loadClose() {

    }
}
