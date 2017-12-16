import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchInventoryController implements ActionListener{
    private SearchInventoryView searchInventoryView;
    private DataAdapter dataAdapter;

    public SearchInventoryController(SearchInventoryView view, DataAdapter data) {
        this.searchInventoryView = view;
        this.dataAdapter = data;

        searchInventoryView.getSearchButton().addActionListener(this);
        searchInventoryView.getCloseButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchInventoryView.getSearchButton()) {
            searchItem();
        } else if (e.getSource() == searchInventoryView.getCloseButton()) {
            loadClose();
        }
    }

    public void searchItem() {
        int productID = 0;
        ProductModel product = new ProductModel();
        try {
            productID = Integer.parseInt(searchInventoryView.getItemField().getText());
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid product ID! Please provide a valid product ID!");
            //return;
        }

        if (dataAdapter.loadProduct(productID) == null) {
            JOptionPane.showMessageDialog(null, "Invalid product ID! Product ID does not exist!");
            //return;
        } else {
            // Set Next view's Fields with selected product
            Application.getInstance().getEditItemInventoryController().loadTable(productID);

            // Open next view and close current View
            Application.getInstance().getEditItemInventoryView().setVisible(true);
            Application.getInstance().getSearchInventoryView().setVisible(false);
            Application.getInstance().getSearchInventoryView().clearTable();

        }

    }

    public void loadClose() {

        Application.getInstance().getSearchInventoryView().setVisible(false);
        Application.getInstance().getManageInventoryController().loadTable();
        Application.getInstance().getManageInventoryView().setVisible(true);
        Application.getInstance().getSearchInventoryView().clearTable();

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

            searchInventoryView.addRow(row);
        }

        searchInventoryView.validate();

    }
}
