import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

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
        Application.getInstance().getManageInventoryView().setVisible(false);
        //Application.getInstance().getLoginScreenView().setVisible(true);
    }

    public void loadTable() {
        Vector<ProductModel> inventory = dataAdapter.loadInventory();

        for (int i = 0; i < inventory.size(); i++) {
            Object[] row = new Object[5];
            row[0] = inventory.get(i).getProductID();
            row[1] = inventory.get(i).getProductName();
            row[2] = inventory.get(i).getProductPrice();
            row[3] = inventory.get(i).getProductQuantity();

            manageInventoryView.addRow(row);
        }

        manageInventoryView.validate();

    }
}

