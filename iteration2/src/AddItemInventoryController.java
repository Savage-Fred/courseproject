import javax.swing.*;
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
        int productID;
        try {
            productID = Integer.parseInt(addItemInventoryView.getItemIDField().getText());
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid product ID! Please provide a valid product ID!");
            return;
        }

        double productPrice;
        try {
            productPrice = Double.parseDouble(addItemInventoryView.getItemPriceField().getText());
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid product price! Please provide a valid product price!");
            return;
        }

        double productQuantity;
        try {
            productQuantity = Double.parseDouble(addItemInventoryView.getItemQuantityField().getText());
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid product quantity! Please provide a valid product quantity!");
            return;
        }

        String productName = addItemInventoryView.getItemNameField().getText().trim();

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
        //Application.getInstance().getManageInventoryView().setVisible(true);
    }

    public void loadClose() {
        Application.getInstance().getLoginScreenView().setVisible(true);
        Application.getInstance().getAddItemInventoryView().setVisible(false);

    }
}
