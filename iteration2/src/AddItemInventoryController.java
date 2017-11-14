import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddItemInventoryController implements ActionListener {

    private AddItemInventoryView addItemInventoryView;
    private DataAdapter dataAdapter;

    public AddItemInventoryController(AddItemInventoryView view, DataAdapter data ) {
        this.addItemInventoryView = view;
        this.dataAdapter = data;

        addItemInventoryView.getAddItemButton().addActionListener(this);
        addItemInventoryView.getCloseButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addItemInventoryView.getAddItemButton()) {
            addItem();
        } else if (e.getSource() == addItemInventoryView.getCloseButton()) {
            loadClose();
        }

    }

    public void addItem() {
        Application.getInstance().getManageInventoryView().setVisible(true);
    }

    public void loadClose() {
        Application.getInstance().getLoginScreenView().setVisible(true);
    }
}
