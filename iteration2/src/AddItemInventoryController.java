import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddItemInventoryController implements ActionListener {

    private AddItemInventoryView addItemInventoryView;
    private DataAdapter dataAdapter;

    public AddItemInventoryController(AddItemInventoryView view, DataAdapter data ) {
        this.addItemInventoryView = view;
        this.dataAdapter = data;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void addItem() {

    }

    public void loadClose() {

    }
}
