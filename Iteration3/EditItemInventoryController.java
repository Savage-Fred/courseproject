import javax.swing.*;
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

        int productID;
        try {
            productID = Integer.parseInt(editItemInventoryView.getItemIDField().getText());
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid product ID! Please provide a valid product ID!");
            return;
        }

        /*


        if (dataAdapter.loadProduct(productID) != null) {
            JOptionPane.showMessageDialog(null, "Product ID Taken! Please provide a valid product ID!");
            return;
        }

        */

        double productPrice;
        try {
            productPrice = Double.parseDouble(editItemInventoryView.getItemPriceField().getText());
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid product price! Please provide a valid product price!");
            return;
        }

        if (productPrice < 0) {
            JOptionPane.showMessageDialog(null, "Invalid product price! Please provide a product price greater than 0!");
            return;
        }

        double productQuantity;
        try {
            productQuantity = Double.parseDouble(editItemInventoryView.getItemQuantityField().getText());
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid product quantity! Please provide a valid product quantity!");
            return;
        }
        if (productQuantity < 0) {
            JOptionPane.showMessageDialog(null, "Invalid product Quantity! Please provide a product quantity greater than 0!");
            return;
        }

        String productName = editItemInventoryView.getItemNameField().getText().trim();

        if (productName.length() == 0) {
            JOptionPane.showMessageDialog(null, "Invalid product name! Please provide a non-empty product name!");
            return;
        }

        // Done all validations! Make an object for this product!

        ProductModel product = new ProductModel();
        product.setProductID(productID);
        product.setProductName(productName);
        product.setProductPrice(productPrice);
        product.setProductQuantity(productQuantity);

        // Store the product to the database

        if (dataAdapter.saveProduct(product)) {
            JOptionPane.showMessageDialog(null, "The product has been updated!");
        }
        Application.getInstance().getManageInventoryView().setVisible(true);
    }

    public void loadTable(int productId) {
        ProductModel product = new ProductModel();
        product = dataAdapter.loadProduct(productId);
        Object[] row = new Object[5];
        row[0] = product.getProductID();
        row[1] = product.getProductName();
        row[2] = product.getProductPrice();
        row[3] = product.getProductQuantity();

        editItemInventoryView.addRow(row);
        editItemInventoryView.validate();

    }

    public void loadClose() {
        Application.getInstance().getEditItemInventoryView().setVisible(false);
        Application.getInstance().getEditItemInventoryView().clearTable();
        Application.getInstance().getManageInventoryController().loadTable();
        Application.getInstance().getManageInventoryView().setVisible(true);

    }
}
