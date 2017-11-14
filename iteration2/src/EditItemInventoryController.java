import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditItemInventoryController implements ActionListener {

    private EditItemInventoryView editItemInventoryView;
    private DataAdapter dataAdapter;

    public EditItemInventoryController(EditItemInventoryView view, DataAdapter data ) {
        this.editItemInventoryView = view;
        this.dataAdapter = data;

        editItemInventoryView.getEditItemButton().addActionListener(this);
        editItemInventoryView.getCloseButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == editItemInventoryView.getEditItemButton()) {
            editItem();
        } else if (e.getSource() == editItemInventoryView.getCloseButton()) {
            loadClose();
        }

    }

    public void editItem() {
        Application.getInstance().getManageInventoryView().setVisible(true);
    }

    public void loadClose() {
        Application.getInstance().getLoginScreenView().setVisible(true);
    }
}
