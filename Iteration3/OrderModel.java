
import java.util.List;
import java.util.ArrayList;


/**
 * Order model contains information about an order 
 *
 * @author William McCarty
 * @version 1.0
 */
@SuppressWarnings("JavadocReference")
public class OrderModel {
    ////////////////////////////////////
    //////	Fields
    ////////////////////////////////////
    private int 	orderID;
    private double 	totalPrice;
    private double 	totalTax;
    private double 	subTotal;
    private java.sql.Date  orderDate;
    private int  customerId;
    private ArrayList<ProductModel> products;
    private ArrayList<OrderLine> lines;



    ////////////////////////////////////
    //////	 Methods
    ////////////////////////////////////
    /*
     * Constructor
     *
     * @param orderID
     * @param totalPrice
     * @param totalTax
     * @param subTotal
     * @param orderDate
     * @param products
     */
    public OrderModel(int orderID, double totalPrice, double totalTax, double subTotal, java.sql.Date orderDate) {
        this.orderID 	= orderID;
        this.totalPrice = totalPrice;
        this.totalTax 	= totalTax;
        this.subTotal 	= subTotal;
        this.orderDate 	= orderDate;

        products = new ArrayList<>();
        lines = new ArrayList<>();
    }

    /*
     * Default constructor
     */
    public OrderModel() {
        lines = new ArrayList<>();
    }

    /*
     * Clear the current order
     */
    public void clearOrder() {
        this.totalPrice = 0;
        this.totalTax 	= 0;
        this.subTotal 	= 0;
        this.products.clear();
    }
    /*
     * @return the total price of the order
     */
    public double calculateTotal() {
        double total = 0;
        double tax = 0;
        /*
        for(Iterator<ProductModel> i = this.products.iterator(); i.hasNext();) {
            total = total + i.getPrice();
            tax = tax + (i.getTaxRate() * i.getPrice());
        }
        */

        for (ProductModel product : products) {
            total = total + product.getProductPrice();
            tax = tax + (product.getProductTaxRate() * product.getProductPrice());
        }

        this.subTotal = total;
        this.totalTax = tax;
        this.totalPrice = total + tax;
        return totalPrice;
    }

    /*
     * Calculate the subtotal of the order
     * @return subtotal of the order
     */
    public double calculateSubtotal() {
        double total = 0;
        /*
        for(Iterator<ProductModel> i = this.products.iterator(); i.hasNext();) {
            total = total + i.getPrice();
        }
        */

        for (ProductModel product : products) {
            total = total + product.getProductPrice();
        }

        this.subTotal = total;
        return total;
    }

    /*
     * Calculate the totalTax
     * @return total tax of the order
     */
    public double calculateTotalTax() {
        double tax = 0;
        /*
        for(Iterator<ProductModel> i = this.products.iterator(); i.hasNext();) {
            tax = tax + (i.getTaxRate() * i.getPrice());
        }
        */

        for (ProductModel product : products) {
            tax = tax + (product.getProductTaxRate() * product.getProductPrice());
        }

        this.totalTax = tax;
        return tax;
    }

    /*
     * Add a product to the order
     * @param product of the product to be added
     */
    public void addProduct(ProductModel prod){
        this.products.add(prod);
    }

    /*
     * @param productid of the product to be added
     */
    public void addProduct(int pid) {
        ProductModel newProduct = new ProductModel();
        // Lookup the product in the database
        this.products.add(newProduct);
    }

    public void addLine(OrderLine line) {
        lines.add(line);
    }

    public void removeLine(OrderLine line) {
        lines.remove(line);
    }

    ////////////////////////////////////
    ////// 	 Setters
    ////////////////////////////////////
    public void setOrderDate	(java.sql.Date d) 	{ this.orderDate 	= d;}
    public void setOrderID		(int oid) 	{ this.orderID 		= oid;}
    public void setTotalTax		(double t) 	{ this.totalTax 	= t;}
    public void setSubTotal		(double t) 	{ this.subTotal 	= t;}
    public void setTotalPrice	(double p) 	{ this.totalPrice 	= p;}
    public void setCustomer 	(int cust) {this.customerId 	= cust;}
    public void setProducts		(ArrayList<ProductModel> prodin) { this.products = prodin;}

    ////////////////////////////////////
    ////// 	 Getters
    ////////////////////////////////////
    public int 		getOrderID()	{ return this.orderID;}
    public java.sql.Date 	getOrderDate() 	{ return this.orderDate;}
    public double 	getTotalPrice() { return this.totalPrice;}
    public double 	getSubTotal() 	{ return this.subTotal;}
    public double 	getTotalTax() 	{ return this.totalTax;}
    public int 	getCustomer() 	{ return this.customerId;}
    public List<OrderLine> getLines() { return lines;}
    public ArrayList<ProductModel> getProducts() { return this.products;}

}