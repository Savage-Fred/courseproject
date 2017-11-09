import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

package grocerystore;


/**
 * 
 * 
 * @author William McCarty
 * @version 1.0
 */
public class ProductController  implements ActionListener{
	
	////////////////////////////////////
	/////// 	Fields
	////////////////////////////////////
	private ProductView 	pview;
	private ProductModel 	pmodel;
	private DataAdapter 	dataAdapter; // to save and load product information


	////////////////////////////////////
	/////// 	Constructors 
	////////////////////////////////////
	/**
	 * Default Constructor 
	 * 
	 * @param view the productview
	 * @param model the productmodel
	 * @param adapter the dataadapter
	 */
	public ProductController(ProductView view, ProductModel model, DataAdapter adapter) {
		this.pview 	= view;
		this.pmodel = model;
		this.dataadapter = adapter;

		pview.getBtnLoad().addActionListener(this);
		pview.getBtnSave().addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pview.getBtnLoad())
            loadProduct();
        else
        if (e.getSource() == pview.getBtnSave())
            saveProduct();
    }


	private void saveProduct() {
        int productID;
        try {
            productID = Integer.parseInt(pview.getTxtProductID().getText());
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid product ID! Please provide a valid product ID!");
            return;
        }

        double productPrice;
        try {
            productPrice = Double.parseDouble(pview.getTxtProductPrice().getText());
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid product price! Please provide a valid product price!");
            return;
        }

        double productQuantity;
        try {
            productQuantity = Double.parseDouble(pview.getTxtProductQuantity().getText());
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid product quantity! Please provide a valid product quantity!");
            return;
        }

        String productName = pview.getTxtProductName().getText().trim();

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
    }

    private void loadProduct() {
        int productID = 0;
        try {
            productID = Integer.parseInt(pview.getTxtProductID().getText());
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid product ID! Please provide a valid product ID!");
            return;
        }

        ProductModel product = dataAdapter.loadProduct(productID);

        if (product == null) {
            JOptionPane.showMessageDialog(null, "This product ID does not exist in the database!");
            return;
        }

        pview.getTxtProductName().setText(product.getProductName());
        pview.getTxtProductPrice().setText(String.valueOf(product.getProductPrice()));
        pview.getTxtProductQuantity().setText(String.valueOf(product.getProductQuantity()));
    }






	////////////////////////////////////
	/////// 	Setters
	////////////////////////////////////	
	public void setProductID 			(int pid) 		{ this.pmodel.setProductID(pid);}
	public void setProductName			(String name) 	{ this.pmodel.setProductName(name);}
	public void setProductPrice			(double price) 	{ this.pmodel.setProductPrice(price);}
	public void setProductDescription	(String desc) 	{ this.pmodel.setProductDescription(desc);}
	public void setProductExpirationDate(Date d) 		{ this.pmodel.setProductExpirationDate(d);}
	public void setProductTaxRate		(double tax) {
		if (tax > 1.00 || tax < 0) {
			System.out.println("invalid tax rate.");
		} else {
			this.pmodel.setProductTaxRate(tax);
		}
	}



	////////////////////////////////////
	/////// 	Getters
	////////////////////////////////////
	public int 		getProductID()		 		{ return this.pmodel.getProductID();}
	public String 	getProductName()			{ return this.pmodel.getProductName();}
	public double	getProductPrice()			{ return this.pmodel.getProductPrice();}
	public double 	getProductTaxRate()			{ return this.pmodel.getProductTaxRate();}
	public String 	getProductDescription()		{ return this.pmodel.getProductDescription();}
	public Date 	getProductExpirationDate()	{ return this.pmodel.getProductExpirationDate();}
}