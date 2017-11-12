import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageInventoryController implements ActionListener {

    private ManageInventoryView manageInventoryView;
    private DataAdapter dataAdapter;

    public ManageInventoryController(ManageInventoryView view, DataAdapter data ) {
        this.manageInventoryView = view;
        this.dataAdapter = data;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void loadAddItem() {

    }

    public void loadEditItem() {

    }

    public void loadDeleteItem() {

    }

    public void loadClose() {

    }
}

