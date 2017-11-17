import java.util.*;



/*
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
    private double 	productQuantity;

    ////////////////////////////////////
    ////// 	Constructors
    ////////////////////////////////////

    /*
     * Default Constructor
     */
    public ProductModel() {
        this.productID 				= 0;
        this.productPrice 			= 0;
        this.productTaxRate 		= 0;
        this.productName 			= null;
        this.productDescription 	= null;
        this.productExpirationDate 	= null;
    }

    /*
     * Consturctor for productmodel
     *
     * @param productName
     * @param productDescription
     * @param productPrice
     * @para productTaxRate
     * @param productExpirationDate
     */
    public ProductModel(int pid, String name, String desc, double price, double tax, Date expdate) {
        this.productID 				= pid;
        this.productName 			= name;
        this.productPrice 			= price;
        this.productTaxRate 		= tax;
        this.productDescription 	= desc;
        this.productExpirationDate 	= expdate;
    }


    ////////////////////////////////////
    /////// 	METHODS
    ////////////////////////////////////



    ////////////////////////////////////
    /////// 	Setters
    ////////////////////////////////////
    public void setProductID (int pid) 				{ this.productID 				= pid;}
    public void setProductName(String name) 		{ this.productName 				= name;}
    public void setProductPrice(double price) 		{ this.productPrice 			= price;}
    public void setProductQuantity(double quant) 	{ this.productQuantity 			= quant;}
    public void setProductTaxRate(double tax) 		{ this.productTaxRate 			= tax;}
    public void setProductDescription(String desc) 	{ this.productDescription 		= desc;}
    public void setProductExpirationDate(Date d) 	{ this.productExpirationDate 	= d;}

    ////////////////////////////////////
    /////// 	Getters
    ///////////////////////////////////
    public int 		getProductID() 				{ return this.productID;}
    public double 	getProductPrice() 			{ return this.productPrice;}
    public double 	getProductTaxRate() 		{ return this.productTaxRate;}
    public double 	getProductQuantity() 		{ return this.productQuantity;}
    public String 	getProductName() 			{ return this.productName;}
    public String 	getProductDescription() 	{ return this.productDescription;}
    public Date 	getProductExpirationDate() 	{ return this.productExpirationDate;}
}