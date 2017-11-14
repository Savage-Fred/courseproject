import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageInventoryController implements ActionListener {

    private ManageInventoryView manageInventoryView;
    private DataAdapter dataAdapter;

    public ManageInventoryController(ManageInventoryView view, DataAdapter data ) {
        this.manageInventoryView = view;
        this.dataAdapter = data;

        manageInventoryView.getAddItemButton().addActionListener(this);
        manageInventoryView.getCloseButton().addActionListener(this);
        manageInventoryView.getEditItemButton().addActionListener(this);
        manageInventoryView.getDeleteItemButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == manageInventoryView.getAddItemButton()) {
            loadAddItem();
        } else if (e.getSource() == manageInventoryView.getEditItemButton()) {
            loadEditItem();
        } else if (e.getSource() == manageInventoryView.getDeleteItemButton()) {
            loadDeleteItem();
        } else if (e.getSource() == manageInventoryView.getCloseButton()) {
            loadClose();
        }

    }

    public void loadAddItem() {
        Application.getInstance().getAddItemInventoryView().setVisible(true);
    }

    public void loadEditItem() {
        Application.getInstance().getEditItemInventoryView().setVisible(true);
    }

    public void loadDeleteItem() {

        JOptionPane.showMessageDialog(null, "Available in Future Versions");

    }

    public void loadClose() {
        Application.getInstance().getLoginScreenView().setVisible(true);
    }
}

