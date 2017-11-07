/**
 * Java Class mockup 
 */
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.Iterator;


///////////////////////////////////////
///////// 		USER 	 	///////////
///////////////////////////////////////

/**
 * Data for user 
 * 
 * @author William McCarty
 * @version 1.0
 */
public class UserModel {
	////////////////////////////////////
	//////// 	Fields
	////////////////////////////////////
	private String name;
	private int userID;
	private Date startDate; //java.util.*
	private char[] password;
	private boolean isManager;
	private BufferedImage profilePicture;


	////////////////////////////////////
	//////	 Methods
	////////////////////////////////////

	////////////////////////////////////
	////// Getters and Setters
	////////////////////////////////////
	/**
	 * @return name of the user
	 */
	public String getName() {return this.name;}
	
	/**
	 * @return userID of this user
	 */
	public int getUserID() {return this.userID;}
	
	/**
	 * @return start date of employee
	 */
	public Date getStartDate() {return this.startdate;}
	
	/**
	 * @return profile picture 
	 */
	public BufferedImage getProfilePicture() {return this.profilePic;}

	/**
	 * @return true if user is manager
	 */
	public boolean isManager() {return this.isManager;}

	/**
	 * @param username
	 */
	public void setName(String uname) {this.name = uname;}
	
	/**
	 * @param user id 
	 */
	public void setUserID(int uid) {this.userID = uid;}
	
	/**
	 * @param startdate
	 */
	public void setStartDate(Date dateIn) {this.startDate = dateIn;}
	
	/**
	 * @param true if manager
	 */
	public void setIsManager(boolean x) {this.isManager = x;}

	/**
	 * @param 	path to image - String
	 * @throws 	FileNotFoundException if image not found
	 */
	public void setProfilePicture(String imagePath) throws FileNotFoundException {
		BufferedImage img = null;
		try {
    		img = ImageIO.read(new File(imagePath));
    		this.profilePicture = img;
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + e.getMessage());
		}
	}


}

/**
 * View for the User class
 * 
 * @author William McCarty
 * @version 1.0
 */
public class UserView extends JFrame {

	/**
	 * Display the profile of the user 
	 * @param uid user id 
	 */
	void displayProfile(int uid) {/** Do stuff here */}
}

/**
 * Controller for the USer 
 * 
 * @author William McCarty
 * @version 1.0
 */

public class UserController {
	////////////////////////////////////
	////// 	Fields 
	////////////////////////////////////
	private UserView view;
	private UserModel model;

	/**
	 * Class to get user input 
	 */ 
	class UserControllerListener implements ActionListener {

	}
}

////////////////////////////////////
///////// 		ORDER 	 	
////////////////////////////////////
/**
 * Order model contains information about an order 
 * 
 * @author William McCarty
 * @version 1.0
 */
public class OrderModel {
	////////////////////////////////////
	//////	Fields 
	////////////////////////////////////
	private int orderID;
	private double totalPrice;
	private double totalTax;
	private double subTotal;
	private Date orderDate;
	private ArrayList<ProductModel> products;



	////////////////////////////////////
	//////	 Methods
	////////////////////////////////////
	/**
	 * Constructor 
	 * 
	 * @param orderID
	 * @param totalPrice
	 * @param totalTax
	 * @param subTotal
	 * @param orderDate
	 * @param products
	 */
	public OrderModel(	int orderID, 
						double totalPrice, 
						double totalTax, 
						double subTotal, 
						Date orderDate, 
						ArrayList<ProductModel> products)
	{
		this.orderID = orderID;
		this.totalPrice = totalPrice;
		this.totalTax = totalTax;
		this.subTotal = subTotal;
		this.orderDate = orderDate;
		this.products = products;
	}

	/**
	 * Default constructor
	 */ 
	public OrderModel() {
		this.orderID 	= 	0;
		this.totalPrice = 	0;
		this.totalTax 	= 	0;
		this.subTotal 	= 	0;
		this.orderDate 	= 	null;
		this.products 	= 	new ArrayList<ProductModel>;
	}

	/**
	 * Clear the current order 
	 */ 
	public void clearOrder() {
		this.totalPrice = 0;
		this.totalTax 	= 0;
		this.subTotal 	= 0;
		this.products.clear();
	}
	/**
	 * @return the total price of the order 
	 */
	public double calculateTotal() {
		double total = 0; 
		double tax = 0;

		for(Iterator<ProductModel> i = this.products.iterator(); i.hasNext();) {
			total = total + i.getPrice();
			tax = tax + (i.getTaxRate() * i.getPrice());
		}
		this.subTotal = total;
		this.totalTax = tax;
		this.totalPrice = total + tax; 
		return totalPrice;
	}

	/**
	 * Calculate the subtotal of the order 
	 * @return subtotal of the order
	 */
	public double calculateSubtotal() {
		double total = 0; 
		for(Iterator<ProductModel> i = this.products.iterator(); i.hasNext();) {
			total = total + i.getPrice();
		}
		this.subTotal = total;
		return total;
	}

	/**
	 * Calculate the totalTax 
	 * @return total tax of the order
	 */
	public double calculateTotalTax() {
		double tax = 0;

		for(Iterator<ProductModel> i = this.products.iterator(); i.hasNext();) {
			tax = tax + (i.getTaxRate() * i.getPrice());
		}
		this.totalTax = tax;
		return tax;
	}

	/**
	 * Add a product to the order 
	 * @param product of the product to be added 
	 */
	public void addProduct(ProductModel prod){
		this.products.add(prod);
	}

	/**
	 * @param productid of the product to be added
	 */
	public void addProduct(int pid) {
		ProductModel newProduct = new ProductModel();
		// Lookup the product in the database
		this.products.add(newProduct);
	}

	////////////////////////////////////
	////// 	 GETTERS AND SETTERS 
	////////////////////////////////////
	/**
	 * @return orderID 
	 */	
	public int getOrderID(){return this.orderID;}
	
	/**
	 * @param oid set the order id to this
	 */
	public void setOrderID(int oid) {this.orderID = oid;}
	
	/**
	 * @param datein 
	 */
	public void setOrderDate(Date d) {this.orderDate = d;}

	/**
	 * @return date
	 */
	public Date getOrderDate() {return this.orderDate;}

	/**
	 * @param p the total price 
	 */
	public void setTotalPrice(double p) {this.totalPrice = p;}

	/**
	 * @return totalPrice
	 */
	public double getTotalPrice() {
		if (this.totalPrice == 0) {
			calculateTotal(); 
		}
		return this.totalPrice;
	}

	/**
	 * @return subtotal of the order
	 */ 
	public double getSubTotal() {
		if (this.subTotal == 0) {
			calculateSubtotal()
		}
		return this.subTotal();
	}

	/**
	 * @param totaltax
	 */
	public void setTotalTax(double t) {this.totalTax = t;}

	/**
	 * @return totalTax
	 */
	public double getTotalTax() {
		if (this.totalTax ==0) {
			calculateTotalTax();
		}
		return this.totalTax;
	}


	/**
	 * @param subtotal in
	 */ 
	public void setSubTotal(double t) {this.subtotal = t;}

	/**
	 * @return arraylist of products in order
	 */
	public static ArrayList<ProductModel> getProducts() {return this.products;}

	/**
	 * @param productslist in 
	 */
	public void setProducts(ArrayList<ProductModel> prodin) {this.products = prodin;}
}

/**
 * OrderView displays the order 
 *
 * @author William McCarty
 * @version 1.0
 */
public class OrderView extends JFrame {

	////////////////////////////////////
	/////// 	Methods
	////////////////////////////////////
	/**
	 * Display the order on the screen
	 */
	public void displayOrder(){/** Do stuff here */}
	
	/**
	 * Print the receipt 
	 */
	public void printReceipt(){/** Do stuff here */}
}

/**
 * 
 * 
 * @author William McCarty
 * @version 1.0
 */
public class OrderController {
	////////////////////////////////////
	/////// 	Fields
	////////////////////////////////////
	private OrderView view;
	private OrderModel model;


	////////////////////////////////////
	/////// 	Methods
	////////////////////////////////////
	/**
	 * Add a product to the order 
	 * @param pid id of product to be added 
	 */
	public void addProduct(int pid) {this.model.addProduct(pid);}

	/**
	 * @param product object of product to be added 
	 */
	public void addProduct(ProductModel prod) {this.model.addProduct(prod);}
	

	////////////////////////////////////
	///////		GETTERS AND SETTERS
	////////////////////////////////////
	/**
	 * @return the order ID
	 */
	public int getOrderID(){ return this.model.getOrderID();}
	
	/**
	 * @param id order id 
	 */
	public void setOrderID(int id){ this.model.setOrderID(id);}
	
	/**
	 * @return the total price 
	 */
	public double getTotalPrice(){ this.model.calculateTotal(); return this.model.getTotalPrice();}
	
	/**
	 * @return total tax of the order
	 */
	public double getTotalTax() { return this.model.getTotalTax();}

	/**
	 * @param t total tax 
	 */
	public void setTotalTax(double t) {this.model.setTotalTax(t);}

	/**
	 * @return subtotal of the order
	 */
	public double getSubTotal() {return this.model.getSubTotal();}

	/**
	 * @param t subtotal in
	 */
	public void setSubTotal(double t) {this.model.setSubTotal(t);}
	
	/**
	 * @return the date of the order
	 */
	public Date getDate(){ return this.model.getDate();}
	
	/**
	 * @param d date 
	 */
	public void setDate(Date d){this.model.setDate(d);}
}


///////////////////////////////////////
///////// 		PRODUCT 	///////////
///////////////////////////////////////

/**
 * 
 * 
 * @author William McCarty
 * @version 1.0
 */
public class ProductModel {
	////////////////////////////////////
	////// 	Fields
	////////////////////////////////////
	private int 	productID;
	private String 	productName;
	private String	productDescription;
	private double 	productPrice;
	private double	productTaxRate;
	private Date 	productExpirationDate;

	////////////////////////////////////
	////// 	Constructors 
	////////////////////////////////////
	/**
	 * Default Constructor 
	 */
	public ProductModel() {
		this.productID 				= 0;
		this.productName 			= null;
		this.productDescription 	= null;
		this.productPrice 			= 0;
		this.productTaxRate 		= 0;
		this.productExpirationDate 	= null;
	}

	/**
	 * Consturctor for productmodel 
	 *
	 * @param productID
	 * @param productName
	 * @param productDescription
	 * @param productPrice
	 * @param productTaxRate
	 * @param productExpirationDate
	 */
	public ProductModel(int 	pid,
						String 	name,
						String 	desc,
						double 	price,
						double 	tax,
						Date 	expdate)
	{
		this.productID 				= pid;
		this.productName 			= name;
		this.productDescription 	= desc;
		this.productPrice 			= price;
		this.productTaxRat 			= tax;
		this.productExpirationDate 	= expdate;
	}


	////////////////////////////////////
	/////// 	METHODS 
	////////////////////////////////////



	////////////////////////////////////
	/////// 	Setters
	////////////////////////////////////
	/**
	 * @param pid
	 */
	public void setProductID(int pid) { this.productID = pid;}

	/**
	 * @param product name
	 */
	public void setProductName(String name) { this.productName = name;}

	/**
	 * @param description
	 */
	public void setProductDescription(String desc) { this.productDescription = desc;}

	/**
	 * @param price
	 */
	public void setProductPrice(double price) { this.productPrice = price;}

	/**
	 * @param taxrate
	 */
	public void setProductTaxRate(double tax) {this.productTaxRate = tax;}

	/**
	 * @param exp date
	 */
	public void setProductExpirationDate(Date d) { this.productExpirationDate = d; }


	////////////////////////////////////
	/////// 	Getters
	////////////////////////////////////
	/**
	 * @return product ID
	 */ 
	public int getProductID() { return this.productID; }

	/**
	 * @return name
	 */ 
	public String getProductName() { return this.productName; }

	/**
	 * @return description
	 */ 
	public String getProductDescription() { return this.productDescription; }

	/**
	 * @return price
	 */ 
	public double getProductPrice() { return this.productPrice; }

	/**
	 * @return tax rate
	 */ 
	public double getProductTaxRate() { return this.productTaxRate; }

	/**
	 * @return expiration date
	 */ 
	public Date getProductExpirationDate() { return this.productExpirationDate; }
}

/**
 * ProductView displays the Product
 *
 * @author William McCarty
 * @version 1.0
 */
public class ProductView extends JFrame {
	/**
	 * Display the product information 
	 */ 
	public void displayProduct(){/** Do stuff here */}
}





/**
 * 
 * 
 * @author William McCarty
 * @version 1.0
 */
public class ProductController {
	////////////////////////////////////
	/////// 	Fields
	////////////////////////////////////
	private ProductView pview;
	private ProductModel pmodel;

	/**
	 * @param pid
	 */
	public void setProductID(int pid) { this.pmodel.setProductID(pid);}

	/**
	 * @param product name
	 */
	public void setProductName(String name) { this.pmodel.setProductName(name);}

	/**
	 * @param description
	 */
	public void setProductDescription(String desc) { this.pmodel.setProductDescription(desc);}

	/**
	 * @param price
	 */
	public void setProductPrice(double price) { this.pmodel.setProductPrice(price);}

	/**
	 * Set tax rate. Check if tax rate is valid 
	 * @param taxrate
	 */
	public void setProductTaxRate(double tax) {
		if (tax > 1.00 || tax < 0) {
			System.out.println("invalid tax rate.");
		} else {
			this.pmodel.setProductTaxRate(tax);
		}
	}

	/**
	 * @param exp date
	 */
	public void setProductExpirationDate(Date d) { this.pmodel.setProductExpirationDate(d); }


	////////////////////////////////////
	/////// Getters
	////////////////////////////////////
	/**
	 * @return product ID
	 */ 
	public int getProductID() { return this.pmodel.getProductID(); }

	/**
	 * @return name
	 */ 
	public String getProductName() { return this.pmodel.getProductName(); }

	/**
	 * @return description
	 */ 
	public String getProductDescription() { return this.pmodel.getProductDescription(); }

	/**
	 * @return price
	 */ 
	public double getProductPrice() { return this.pmodel.getProductPrice(); }

	/**
	 * @return tax rate
	 */ 
	public double getProductTaxRate() { return this.pmodel.getProductTaxRate(); }

	/**
	 * @return expiration date
	 */ 
	public Date getProductExpirationDate() { return this.pmodel.getProductExpirationDate(); }
}





























