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

        if (dataAdapter.loadProduct(productID) != null) {
            JOptionPane.showMessageDialog(null, "Product ID is already in use! Please provide a product ID that has not been used!");
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

        if (dataAdapter.saveProduct(product)) {
            JOptionPane.showMessageDialog(null, "The product has been added to your Inventory!");

        }

        loadTable();

        addItemInventoryView.setItemIDField(new JTextField(""));
        addItemInventoryView.setItemNameField(new JTextField(""));
        addItemInventoryView.setItemPriceField(new JTextField(""));
        addItemInventoryView.setItemQuantityField(new JTextField(""));

        addItemInventoryView.validate();
        //Application.getInstance().getManageInventoryView().setVisible(true);
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

            addItemInventoryView.addRow(row);
        }

        addItemInventoryView.invalidate();

    }

    public void loadClose() {
        Application.getInstance().getManageInventoryController().loadTable();
        Application.getInstance().getManageInventoryView().setVisible(true);
        Application.getInstance().getAddItemInventoryView().setVisible(false);
        Application.getInstance().getAddItemInventoryView().clearTable();


    }
}
