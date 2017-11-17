import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
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

        Application.getInstance().getAddItemInventoryController().loadTable();
        Application.getInstance().getAddItemInventoryView().setVisible(true);
        Application.getInstance().getManageInventoryView().setVisible(false);
    }

    public void loadEditItem() {

        Application.getInstance().getSearchInventoryController().loadTable();
        Application.getInstance().getSearchInventoryView().setVisible(true);
        Application.getInstance().getManageInventoryView().setVisible(false);

    }

    public void loadDeleteItem() {

        JOptionPane.showMessageDialog(null, "Available in Future Versions");

    }

    public void loadClose() {
        Application.getInstance().getManageInventoryView().setVisible(false);
        //Application.getInstance().getLoginScreenView().setVisible(true);
    }

    public void loadTable() {
        ProductModel product = new ProductModel();

        for (int i = 1; dataAdapter.loadProduct(i) != null; i++) {
            product = dataAdapter.loadProduct(i);
            Object[] row = new Object[5];
            row[0] = product.getProductID();
            row[1] = product.getProductName();
            row[2] = product.getProductPrice();
            row[3] = product.getProductQuantity();

            manageInventoryView.addRow(row);
        }

        manageInventoryView.validate();

    }
}

