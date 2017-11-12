import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditItemInventoryController implements ActionListener {

    private EditItemInventoryView editItemInventoryView;
    private DataAdapter dataAdapter;

    public EditItemInventoryController(EditItemInventoryView view, DataAdapter data ) {
        this.editItemInventoryView = view;
        this.dataAdapter = data;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void editItem() {

    }

    public void loadClose() {

    }
}
