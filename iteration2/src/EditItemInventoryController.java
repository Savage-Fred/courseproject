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

        double productPrice;
        try {
            productPrice = Double.parseDouble(editItemInventoryView.getItemPriceField().getText());
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid product price! Please provide a valid product price!");
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

        dataAdapter.saveProduct(product);
        Application.getInstance().getManageInventoryView().setVisible(true);
    }

    public void loadClose() {
        Application.getInstance().getEditItemInventoryView().setVisible(false);
        //Application.getInstance().getLoginScreenView().setVisible(true);
    }
}
