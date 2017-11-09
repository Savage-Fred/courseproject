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
public class OrderController implements ActionListener {
	////////////////////////////////////
	/////// 	Fields
	////////////////////////////////////
	private OrderView view;
	private OrderModel model;


	////////////////////////////////////
	/////// 	Methods
	////////////////////////////////////

	////////////////////////////////////
	/////// 	Constructors
	////////////////////////////////////

	/**
	 * Default constructor 
	 * 
	 * @param Orderview v
	 * @param Ordermodel m
	 */ 
	public OrderController(OrderView v, OrderModel m) {
		this.view = v;
		this.model = m;

	}

	/**
	 * Add a product to the order 
	 * @param pid id of product to be added 
	 */
	public void addProduct(int pid) {this.model.addProduct(pid);}

	/**
	 * @param product object of product to be added 
	 */
	public void addProduct(ProductModel prod) {this.model.addProduct(prod);}

	/**
	 * @return the total price of the order 
	 */
	public double calculateTotal() {
		double total = 0; 
		double tax = 0;
		ArrayList<ProductModel> prodList = this.model.getProducts();

		for(Iterator<ProductModel> i = prodList.iterator(); i.hasNext();) {
			total = total + i.getPrice();
			tax = tax + (i.getTaxRate() * i.getPrice());
		}
		this.model.setSubTotal(total);
		this.model.setTotalTax(tax;
		this.model.setTotalPrice(total + tax); 
		return total;
	}
	

	////////////////////////////////////
	///////		Setters
	////////////////////////////////////
	public void setOrderID		(int id)	{ this.model.setOrderID(id);}
	public void setDate			(Date d)	{ this.model.setDate(d);}
	public void setTotalTax		(double t) 	{ this.model.setTotalTax(t);}
	public void setSubTotal		(double t) 	{ this.model.setSubTotal(t);}
	public void setTotalPrice 	(double p) 	{ this.model.setTotalPrice(t);}
	

	////////////////////////////////////
	///////		Getters
	////////////////////////////////////
	public int 		getOrderID()	{ return this.model.getOrderID();}
	public double 	getTotalTax() 	{ return this.model.getTotalTax();}
	public double 	getTotalPrice()	{ return this.model.getTotalPrice();}
	public double 	getSubTotal() 	{ return this.model.getSubTotal();}
	public Date 	getDate()		{ return this.model.getDate();}
}