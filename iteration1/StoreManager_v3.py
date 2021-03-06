#!/usr/bin/python


import Tkinter as tk
from Tkinter import *
import tkMessageBox
import pymysql


LARGE_FONT= ("Verdana", 12)

dbServerName    = "127.0.0.1"
dbUser          = "root"
dbPassword      = "passwd"
dbName          = "grocery_store"
charSet         = "utf8mb4"
cursorType      = pymysql.cursors.DictCursor
connection      = pymysql.connect(host=dbServerName, user=dbUser, password=dbPassword, db=dbName, charset=charSet,cursorclass=cursorType)

global order_count
global product_id
order_count = 1


# Main Class for Initilizing and Running Frames (GUI)
class StoreManagement(tk.Tk):

	def __init__(self, *args, **kwargs):
		
		tk.Tk.__init__(self, *args, **kwargs)
		container = tk.Frame(self)

		container.pack(side="top", fill="both", expand = True)

		container.grid_rowconfigure(0, weight=1)
		container.grid_columnconfigure(0, weight=1)
		
		self.frames = {}

		for F in (MainMenuPage, CheckOutPage, AddItemToCartPage, PaymentTypePage, ItemManagementPage, CashPaymentPage, CreditCardPaymentPage, AddItemToInventoryPage, EditItemToInventoryPage, ManageCurrentInventoryPage):

			frame = F(container, self)

			self.frames[F] = frame

			frame.grid(row=3, column=3, sticky="nsew")

		self.show_frame(MainMenuPage)

	def show_frame(self, cont):

		frame = self.frames[cont]
		frame.tkraise()


# Displays Main Menu
# Choices: Checkout Customer, Item Management, Exit         
class MainMenuPage(tk.Frame):

	def __init__(self, parent, controller):
		tk.Frame.__init__(self,parent)
		label = tk.Label(self, text="Main Menu", font=LARGE_FONT)
		label.pack(pady=10,padx=10)

		button = tk.Button(self, text="Check Out", command=lambda: controller.show_frame(CheckOutPage))
		button.pack()

		button2 = tk.Button(self, text="Item Management", command=lambda: controller.show_frame(ItemManagementPage))
		button2.pack()
		
		button3 = tk.Button(self, text="Exit", command=lambda: controller.quit())
		button3.pack()


# Displays Checkout Menu Page 
# Choices: Add Item to Cart, Pay for Current Cart, Return to Main Menu
class CheckOutPage(tk.Frame):

	def __init__(self, parent, controller):
		tk.Frame.__init__(self, parent)
		
		label = tk.Label(self, text="Checkout Customer", font=LARGE_FONT)
		label.grid(row = 0, column = 1)
		
		total = calculateOrder(order_count)
		
		cart_total = StringVar()
		cart_total = "Amount Due: $" + str(total)
		
		label1 = tk.Label(self)#, text = cart_total)
		label1.grid(row = 20, column = 1, sticky = E)
		
		label1.config(text=cart_total)

		
		def updateList(event):
			print("Order Count: ", order_count)
			cart = None
			cart = read_single_order(order_count)
			list1.delete(0, END)
			item = ("Product Name", "Quantity", "Total Dollar Value")
			list1.insert(END, item)
			for row in (cart):
				product_id = (row['product_id'])
				quantity = (row['quantity'])
				product = read_single_product(product_id)
				product_name = product['product_name']
				unit_price = product['unit_price']
			
				price = quantity * unit_price
				price = "$" + str(price)
				items = (product_name, quantity, price)
				list1.insert(END, items)
			
			total = calculateOrder(order_count)				
			print("Order Count: ", order_count)		
			cart_total = ("Amount Due: $" + str(total))
			print("Cart Total: ", cart_total)
			label1.config(text=cart_total)	
		
	
		
		button1 = tk.Button(self, text="Add Item", command=lambda: controller.show_frame(AddItemToCartPage))
		button1.grid(row = 2, column = 0)
		button1.bind("<Button-1>", updateList)

		button2 = tk.Button(self, text="Pay", command=lambda: controller.show_frame(PaymentTypePage))
		button2.grid(row = 2, column = 1)
		
		button3 = tk.Button(self, text="Main Menu", command=lambda: controller.show_frame(MainMenuPage))
		button3.grid(row = 15, column = 2)
		
		button4 = tk.Button(self, text="Refresh")
		button4.grid(row = 2, column = 2)
		button4.bind("<Button-1>", updateList)
		
		# Define ListBox 
				
		list1 = Listbox(self, height = 6, width = 30)
		list1.grid(row = 5, column = 1, rowspan = 3, columnspan = 2)
		
		
		# Search for Order from products_on_order Table 
		
		cart = read_single_order(order_count)
		
		item = ("Product Name", "Quantity", "Total Dollar Value")
		list1.insert(END, item)
		
		# Searches for product information from product table, then inserts info into the list		
		for row in (cart):

			product_id = (row['product_id'])
			quantity = (row['quantity'])
			product = read_single_product(product_id)
			product_name = product['product_name']
			unit_price = product['unit_price']
			
			price = quantity * unit_price
			price = "$" + str(price)
			items = (product_name, quantity, price)
			list1.insert(END, items)
		
		# Attach Scrollbar to the List 
		label4 = tk.Label(self, text = "Current Cart:")
		label4.grid(row = 4, column = 0)
		
		sb1 = Scrollbar(self)
		sb1.grid(row = 5, column = 3, rowspan = 6)
		
		list1.configure(yscrollcommand = sb1.set)
		sb1.configure(command=list1.yview)
		

	
		
		
		
		

# Displays Add product to Cart page
# Chocies: Add Another Item, Return to Cart 
# Functions: 
#   1) Adds an Item to Customer's Cart 
#   2) Display's Customer's Current Cart 
	 

class AddItemToCartPage(tk.Frame):
	
	def __init__(self, parent, controller):
		tk.Frame.__init__(self, parent)
		
		label = tk.Label(self, text="Add Item to Cart", font=LARGE_FONT)
		label.grid(row = 0, column = 0, sticky = E)
		
		label1 = tk.Label(self, text = "Product ID: ")
		label1.grid(row = 1, column = 0, sticky = E)
		
		entry1 = tk.Entry(self, bd = 2)
		entry1.grid(row = 1, column = 1, sticky = E)
		
		label2 = tk.Label(self, text = "Quantity: ")
		label2.grid(row = 2, column = 0, sticky = E)
		
		entry2 = tk.Entry(self, bd = 2)
		entry2.grid(row = 2, column = 1, sticky = E)
		
		
		# Define ListBox 
		
		label3 = tk.Label(self, text = "Current Cart")
		
		list1 = Listbox(self, height = 6, width = 35)
		list1.grid(row = 4, column = 1, rowspan = 6, columnspan = 2)
		
		list1.delete(0,END)
		
		
		item = ("Product ID", "Product Name", "Product Quantity", "Product Price")
		list1.insert(END, item)
	
		for product in return_products():
			product_id = product["product_id"]
			product_name = product["product_name"]
			product_price = product["unit_price"]	
			product_quantity = product["quantity"]		
			item = (product_id, product_name, product_quantity, product_price)
			list1.insert(END, item)
		
		# Attach Scrollbar to the List 
		label4 = tk.Label(self, text = "Store Inventory")
		label4.grid(row = 3, column = 1)
		
		sb1 = Scrollbar(self)
		sb1.grid(row = 4, column = 3, rowspan = 6)
		
		list1.configure(yscrollcommand = sb1.set)
		sb1.configure(command=list1.yview)
	
		def clearEntries(event):
			entry1.delete(0, END)
			entry2.delete(0, END)
		
			
		def updateList(event):
			item = ("Product ID", "Product Name", "Product Quantity", "Product Price")
			list1.delete(0, END)
			list1.insert(END, item)
		
			for product in return_products():
				product_id = product["product_id"]
				product_name = product["product_name"]
				product_price = product["unit_price"]	
				product_quantity = product["quantity"]		
				item = (product_id, product_name, product_quantity, product_price)
				list1.insert(END, item)
			controller.show_frame(AddItemToCartPage)
			clearEntries(self)
					
		
		def addItem(event):
			print("Product Added to Cart ")

			product_id = int(entry1.get())
			quantity = int(entry2.get())
			order_id = order_count
			oid = (order_id, product_id, quantity)
			add_order(oid)
			for i in xrange(quantity):
				decrement_product_quantity(str(product_id))
			updateList(self)
						
			
		def checkQuantity(event):
			try:
				quantity = int(entry2.get())
				product = read_single_product(int(entry1.get()))
				quantity_rem = int(product["quantity"])
				print("Quantity Rem: ", quantity_rem)
				if (quantity > quantity_rem):
					raise ValueError
				if (quantity_rem < 1):
					raise ValueError
				else:
					addItem(self)	
				
			except ValueError:
				tkMessageBox.showwarning("Invalid Product Quantity","Please enter a valid number for the Product Quantity")
		
		def checkProductID(event):
			try:
				product_id = int(entry1.get())
				product = read_single_product(product_id)
				if (product == None):
					raise ValueError
				else:
					checkQuantity(self)
				
			except ValueError:
				tkMessageBox.showwarning("Invalid Product ID","Please enter a valid number for the Product ID")
				

		button1 = tk.Button(self, text="Add Item")
		button1.grid(row = 12, column = 0, sticky = E)
		button1.bind("<Button-1>", checkProductID)
		

		button2 = tk.Button(self, text="Back to Cart", command=lambda: controller.show_frame(CheckOutPage))
		button2.grid(row = 12, column = 1, sticky = E)
		button2.bind("<Button-1>", updateList)


		
				
# Display Payment Type Page
#   Choices: Credit Card or Cash    
class PaymentTypePage(tk.Frame):

	def __init__(self, parent, controller):
		tk.Frame.__init__(self, parent)
		label = tk.Label(self, text="Select Payment Type", font=LARGE_FONT)
		label.grid(row = 2, column = 4, sticky = N)

		button1 = tk.Button(self, text="Credit Card", command=lambda: controller.show_frame(CreditCardPaymentPage))
		button1.grid(row = 3, column = 5)

		button2 = tk.Button(self, text="Cash", command=lambda: controller.show_frame(CashPaymentPage))
		button2.grid(row = 4, column = 5)
		
		button3 = tk.Button(self, text="Back to Cart", command=lambda: controller.show_frame(CheckOutPage))
		button3.grid(row = 5, column = 5)
		
# Display Credit Card Payment Page
# Choices: Return to Main Menu
class CreditCardPaymentPage(tk.Frame):

	def __init__(self, parent, controller):
		tk.Frame.__init__(self, parent)
		label = tk.Label(self, text="Credit Card Payment", font=LARGE_FONT)
		label.grid(row = 0, column = 0, sticky = E)
		
		global order_count 
		total = calculateOrder(order_count)
		print("Total:", total)
		label3 = tk.Label(self, text = "Amount Due: %s"%total)
		label3.grid(row = 1, column = 0, sticky = E)
				
		label2 = tk.Label(self, text="Credit Card Number")
		label2.grid(row = 2, column = 0, sticky = E)
		
		def clearEntries(event):
			global order_count
			order_count = 1 + order_count
			controller.show_frame(MainMenuPage)
			print(order_count)
			total = calculateOrder(order_count)
			label3.config(text = "Amount Due: %s"%total)
			entry1.delete(0, END)
			
								
		entry1 = tk.Entry(self, bd = 2)
		entry1.grid(row = 2, column = 1, sticky = E)
		
		def processPayment(event):
			try:
				cardnumber = int(entry1.get())
				if (len(str(cardnumber)) < 12):
					raise ValueError
				else:
					label4 = tk.Label(self, text = "Thank you for your purchase")
					label4.grid(row = 4, column = 0)
					print("Made it ")
					button1 = tk.Button(self, text="Main Menu")
					button1.grid(row = 5, column = 1)
					button1.bind("<Button-1>", clearEntries)
					
					#button1.destroy()
					
			except ValueError:
				tkMessageBox.showwarning("Invalid Card Number", "Please enter a valid credit card number")
				
		def refreshPrice(event):
			total = float(calculateOrder(order_count))
			label3.config(text = ("Amount Due: $%s" % total))
			controller.show_frame(CreditCardPaymentPage)
				
		# Button that Calculates Change
		button4 = tk.Button(self, text="Refresh")
		button4.bind("<Button-1>", refreshPrice)
		button4.grid(row = 1, column = 1, sticky = E)
		
		button2 = tk.Button(self, text="Pay")
		button2.bind("<Button-1>", processPayment)
		button2.grid(row =3, column = 1)
		
		

# Display Cash Payment Page
# Choices: Return to Main Menu
class CashPaymentPage(tk.Frame):
	
	def __init__(self, parent, controller):
		tk.Frame.__init__(self, parent)
		label = tk.Label(self, text="Cash Payment", font=LARGE_FONT)
		label.grid(row = 0, column = 0, sticky = E)
		
		total = float(calculateOrder(order_count))
		
		# Display Current Cart Total
		label3 = tk.Label(self, text = "Cart Total: $%s" % total )
		label3.grid(row = 1, column = 0, sticky = E)
	
		
		# Prompts User to enter Amount Recieved 
		label2 = tk.Label(self, text="Cash Amount:")
		label2.grid(row = 2, column = 0, sticky = E)
		
		
		# User inputs Cash amount recieved 
		entry1 = tk.Entry(self, bd = 2)
		entry1.grid(row = 2, column = 1, sticky = E)
		
	
		label4 = tk.Label(self, text = "Change Due: ")
		label4.grid(row = 3, column = 0, sticky = E)
		
		def clearEntries(event):
			global order_count
			order_count = 1 + order_count
			controller.show_frame(MainMenuPage)
			print(order_count)
			label5.config(text = "")
			entry1.delete(0, END) 
				  
		
		def calculate(event):
			try:
				cashRecieved = float(entry1.get())
				total = float(calculateOrder(order_count))
				if (cashRecieved < total):
					raise ValueError
				else:
					change = cashRecieved - total
					label5.config(text = str("$ %s" % change))
					label5.grid(row = 4, column = 2, sticky = E)
					
					# Button that directs user back to Main Menu
					button1 = tk.Button(self, text="Main Menu")
					button1.grid(row = 5, column = 2, sticky = E)
					button1.bind("<Button-1>", clearEntries)
					
				


		
			except ValueError:
				tkMessageBox.showwarning("Invalid Input", "Please enter a valid number") 
			 
				
		def refreshPrice(event):
			total = float(calculateOrder(order_count))
			label3.config(text = "Cart Total: $%s" % total)
			controller.show_frame(CashPaymentPage)
	
		label5 = tk.Label(self, text = "")
		
		# Button that Calculates Change
		button2 = tk.Button(self, text="Calculate")
		button2.bind("<Button-1>", calculate)
		button2.grid(row = 4, column = 1, sticky = E) 
		
		# Button that Calculates Change
		button4 = tk.Button(self, text="Refresh")
		button4.bind("<Button-1>", refreshPrice)
		button4.grid(row = 4, column = 0, sticky = E)
						
		
		
	

# Display Item Management Page 
# Choices: Add Product to Inventory, Manage Current Products and Return to Main Menu  
class ItemManagementPage(tk.Frame):

	def __init__(self, parent, controller):
		tk.Frame.__init__(self, parent)
		label = tk.Label(self, text="Item Management Mode", font=LARGE_FONT)
		label.pack(pady=10,padx=10)

		button1 = tk.Button(self, text="Add Product to Inventory", command=lambda: controller.show_frame(AddItemToInventoryPage))
		button1.pack()

		button2 = tk.Button(self, text="Manage Current Inventory", command=lambda: controller.show_frame(ManageCurrentInventoryPage))
		button2.pack()
		
		button3 = tk.Button(self, text="Main Menu", command=lambda: controller.show_frame(MainMenuPage))
		button3.pack()
		
# Displays Add product to Store Inventory
# Chocies: Add Another Item, Return to Item Management Menu

class AddItemToInventoryPage(tk.Frame):

	def __init__(self, parent, controller):
		tk.Frame.__init__(self, parent)
		label = tk.Label(self, text="Add Item to Inventory", font=LARGE_FONT)
		label.grid(row = 1, column = 1)

		
		label2 = tk.Label(self, text = "Product Name: ")
		label2.grid(row = 3, column = 0)
		
		# User Entry field for Product Name
		entry2 = tk.Entry(self, bd = 2)
		entry2.grid(row = 3, column = 1)
		
		
		label6 = tk.Label(self, text = "Product Quantity: ")
		label6.grid(row = 6, column = 0)
		
		# User Entry field for Product Quantity
		entry6 = tk.Entry(self, bd = 2)
		entry6.grid(row = 6, column = 1)
		
		
		label3 = tk.Label(self, text = "Product Price: ")
		label3.grid(row = 8, column = 0)
		
		# User Entry field for Product Price
		entry3 = tk.Entry(self, bd = 2)
		entry3.grid(row = 8, column = 1)
		
		
		def clearEntries(event):		
			entry2.delete(0, END)
			entry3.delete(0, END)		
			entry6.delete(0, END)
			controller.show_frame(AddItemToInventoryPage)

		
		def addToInventory(event):
			product_name = str(entry2.get())
			product_quantity = str(entry6.get())
			product_price = str(entry3.get())
			product = (product_name, product_quantity, product_price)
			add_product_simple(product)
			clearEntries(self)
			
		# Testing Return Key Output
		def getProductName(event):
			try:
				product_name = str(entry2.get())
				addToInventory(self)
			except ValueError:
				tkMessageBox.showwarning("Invalid Product Name", "Please enter a valid string for Product Name")
			
		# Testing Return Key Output
		def getProductQuantity(event):
			try:
				product_quantity = int(entry6.get())
				getProductName(self)
			except ValueError:
				tkMessageBox.showwarning("Invalid Product Quantity", "Please enter a valid number for Product Quantity")
		
		# Testing Return Key Output
		def getProductPrice(event):
			try:
				product_price = float(entry3.get())
				getProductQuantity(self)
			except ValueError:
				tkMessageBox.showwarning("Invalid Product Price", "Please enter a valid number for Product Price")
			

		button1 = tk.Button(self, text="Add Item")	
		button1.grid(row = 9, column = 0)
		button1.bind("<Button-1>", getProductPrice)
		

		button2 = tk.Button(self, text="Item Management Menu", command=lambda: controller.show_frame(ItemManagementPage))
		button2.grid(row = 9, column = 1)
		button2.bind("<Button-1>", clearEntries)

# Displays Add product to Store Inventory
# Chocies: Add Another Item, Return to Item Management Menu

class EditItemToInventoryPage(tk.Frame):

	def __init__(self, parent, controller):
		tk.Frame.__init__(self, parent)
		label = tk.Label(self, text="Edit Item in Inventory", font=LARGE_FONT)
		label.grid(row = 1, column = 1)
		
		label6 = tk.Label(self, text = "Product Quantity: ")
		label6.grid(row = 6, column = 0)
		
		# User Entry field for Product Quantity
		entry6 = tk.Entry(self, bd = 2)
		entry6.grid(row = 6, column = 1)
		
		
		label3 = tk.Label(self, text = "Product Price: ")
		label3.grid(row = 8, column = 0)
		
		# User Entry field for Product Price
		entry3 = tk.Entry(self, bd = 2)
		entry3.grid(row = 8, column = 1)
		
		def clearEntries(event):		
			entry3.delete(0, END)		
			entry6.delete(0, END)
		
		def addToInventory(event):
			new_product_price = float(entry3.get())
			new_product_quantity = int(entry6.get())
			old_product = read_single_product(product_id)
			old_poduct_price = old_product["unit_price"]
			old_product_quantity = old_product["quantity"]
			
			if (old_poduct_price != new_product_price):
				update_product_price(product_id, new_product_price)
				
			if(old_product_quantity != new_product_quantity):
				update_product_quanity(product_id, new_product_quantity)
			controller.show_frame(ManageCurrentInventoryPage)
			clearEntries(self)
			
		# Testing Return Key Output
		def getProductPrice(event):
			try:
				product_price = float(entry3.get())
				addToInventory(self)
			except ValueError:
				tkMessageBox.showwarning("Invalid Product Price", "Please enter a valid number for Product Price")
				
		# Testing Return Key Output
		def getProductQuantity(event):
			try:
				product_quantity = int(entry6.get())
				getProductPrice(self)
			except ValueError:
				tkMessageBox.showwarning("Invalid Product Quantity", "Please enter a valid number for Product Quantity")			

		button1 = tk.Button(self, text="Save Edit") #, command=lambda: controller.show_frame(ManageCurrentInventoryPage))		
		button1.grid(row = 9, column = 0)
		button1.bind("<Button-1>", getProductQuantity)
		

		button2 = tk.Button(self, text="Item Management Menu", command=lambda: controller.show_frame(ItemManagementPage))
		button2.grid(row = 9, column = 1)
		button2.bind("<Button-1>", clearEntries)		

# Displays Manage Current Store Inventory page
# Chocies: Modify Item, Return Item Management Menu  

class ManageCurrentInventoryPage(tk.Frame):

	def __init__(self, parent, controller):
		tk.Frame.__init__(self, parent)
		label = tk.Label(self, text="Manage Store Inventory", font=LARGE_FONT)
		label.grid(row=1, column=1)
		
		# Define ListBox 
		
		list1 = Listbox(self, height = 6, width = 35)
		list1.grid(row = 3, column = 1, rowspan = 6, columnspan = 2)
		
		item = ("Product ID", "Product Name", "Product Quantity", "Product Price")
		list1.insert(END, item)
		
		for product in return_products():
			product_id = product["product_id"]
			product_name = product["product_name"]
			product_price = product["unit_price"]	
			product_quantity = product["quantity"]		
			item = (product_id, product_name, product_quantity, product_price)
			list1.insert(END, item)
			
		
		
		# Attach Scrollbar to the List 
		
		sb1 = Scrollbar(self)
		sb1.grid(row = 3, column = 3, rowspan = 6, columnspan = 6)
		
		list1.configure(yscrollcommand = sb1.set)
		sb1.configure(command=list1.yview)
		
		def updateList(event):
			list1.delete(0, END)
			for product in return_products():
				product_id = product["product_id"]
				product_name = product["product_name"]
				product_price = product["unit_price"]	
				product_quantity = product["quantity"]		
				item = (product_id, product_name, product_quantity, product_price)
				list1.insert(END, item)
			entry1.delete(0, END)
		
		def checkProductID(event):
			try:
				product_id = int(entry1.get())
				product = read_single_product(product_id)
				if (product == None):
					controller.show_frame(ManageCurrentInventoryPage)
					raise ValueError
				else:
					#global product_id
					product_id = int(entry1.get())
					controller.show_frame(EditItemToInventoryPage)
					updateList(self)
								
			except ValueError:
				tkMessageBox.showwarning("Invalid Product ID","Please enter a valid number for the Product ID")
				
			
		
		label2 = tk.Label(self, text="Product ID:", font=LARGE_FONT)
		label2.grid(row=12, column=1)
		
		entry1 = tk.Entry(self, bd = 2)
		entry1.grid(row=12, column=2)

		button1 = tk.Button(self, text="Edit Item")
		button1.grid(row = 14, column = 1)
		button1.bind("<Button-1>", checkProductID)

		button2 = tk.Button(self, text="Item Management Menu", command=lambda: controller.show_frame(ItemManagementPage))
		button2.grid(row = 14, column = 2)
		
		button4 = tk.Button(self, text="Refresh")
		button4.grid(row = 14, column = 3)
		button4.bind("<Button-1>", updateList)
		
		
class Customer():
	
	def __init__(self):
		current_order = {}
		current_order_total = DoubleVar
	
	
class Products():
	
	def __init__(self,product_id, name, price, description, exp_date, quantity, tax_rate):
		self.product_id = product_id
		self.name = name
		self.price = price 
		self.description = description 
		self.exp_date = exp_date
		self.quantity = quantity
		self.tax_rate = tax_rate
	

class Store():
	current_inventory = {}
	
class Order():
	def __init__(self, order_id, order_date, order_time, total_price):
		self.order_id = order_id
		self.order_date = order_date
		self.order_time = order_time
		self.total_price = total_price
		
		
def calculateOrder(order_id):
	cart = read_single_order(order_id)
	total = 0
	for item in cart:
		product_id = item["product_id"]
		quantity = item["quantity"]
		product = read_single_product(product_id)
		unit_price = product["unit_price"]
		price = unit_price * quantity
		total = total + price
	return total
		
	
		
	


#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#
#############################################################################
########################     DATABASE STUFF BELOW    ########################
#############################################################################
#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#

#Function to connect to the mysql database 
def connect_to_db():
	connection = pymysql.connect(host= dbServerName,
							 user= dbUser,
							 password=dbPassword,
							 db= dbName,
							 charset=charSet,
							 cursorclass=pymysql.cursors.DictCursor)
	return connection

############################################################################
######################  Initialize the Database     ########################
############################################################################
def create_tables():
	connection = connect_to_db()
	try:
		with connection.cursor() as cursor:
			cursor.execute("DROP TABLE IF EXISTS `products`;")
			cursor.execute("DROP TABLE IF EXISTS `orders`;")
			cursor.execute("DROP TABLE IF EXISTS `products_in_order`;")
			connection.commit()
			
			###### Build the strings 
			create_tbl = "CREATE TABLE IF NOT EXISTS "
			prt1 = "products("
			prt2 = "product_id INT AUTO_INCREMENT PRIMARY KEY, "
			prt3 = "product_name VARCHAR(30) NOT NULL, "
			prt4 = "description VARCHAR(30), "
			prt5 = "expiration_date DATETIME, "
			prt6 = "quantity INT NOT NULL, "
			prt7 = "unit_price DECIMAL(13,4), "
			prt8 = "tax_rate DECIMAL(5,5));"
			createProductsTbl = create_tbl + prt1 + prt2 + prt3 + prt4 + prt5 + prt6 + prt7 + prt8

			ort1 = "orders("
			ort2 = "order_id INT AUTO_INCREMENT PRIMARY KEY, "
			ort3 = "order_date DATETIME, "
			ort4 = "order_time TIME, "
			ort5 = "total_price DECIMAL(13,4));"
			createOrdersTbl = create_tbl + ort1 + ort2 + ort3 + ort4 + ort5		

			potbl1 = "products_in_order("
			potbl2 = "pio_id INT AUTO_INCREMENT PRIMARY KEY, "
			potbl3 = "order_id INT NOT NULL, "
			potbl4 = "product_id INT NOT NULL, "
			potbl5 = "quantity INT);"
			createPinOTbl = create_tbl + potbl1 + potbl2 + potbl3 + potbl4 + potbl5			
			
			# Create a new record

			cursor.execute(createProductsTbl)
			connection.commit()
			
			cursor.execute(createOrdersTbl)
			connection.commit()
			
			cursor.execute(createPinOTbl)
			connection.commit()
		
	except Exception as e:
		print("Exeception occured:{}".format(e))
	finally: 
		connection.close()


def initialize_db():
	connect_to_db()
	# String to insert produts into table
	connection      = pymysql.connect(host=dbServerName, user=dbUser, password=dbPassword, db=dbName, charset=charSet,cursorclass=cursorType)
	productsInsert = "INSERT INTO products (product_name, description, expiration_date, quantity, unit_price, tax_rate) VALUES "
	prd1 = "('Apple',   'Red Delicious',        '2017-10-13', 50, 1.75,     0.06000),"
	prd2 = "('Banana',  'Contains 10 Bananas',  '2017-10-15', 30, 2.00,     0.06000),"
	prd3 = "('Eggs',    'One Dozen Eggs',       '2017-10-17', 40, 2.75,     0.08000),"
	prd4 = "('Milk',    'Whole Milk',           '2017-10-05', 70, 2.50,     0.08000),"
	prd5 = "('Bread',   'Whole Grain',          '2017-06-10', 45, 3.00,     0.06000),"
	prd6 = "('Cheese',  'Shredded Cheddar',     '2017-11-27', 60, 4.00,     0.08000),"
	prd7 = "('Steak',   'Two Pack T-Bones',     '2017-10-04', 35, 10.00,    0.05000);"
	productsInsertStatement = productsInsert + prd1 + prd2 + prd3 + prd4 + prd5 + prd6 + prd7

	# String to insert orders into databse
	ordersinsert = "INSERT INTO orders (order_date, order_time, total_price) VALUES "
	ord1 = "('2017-9-20', '08:00:00',   13.25),"
	ord2 = "('2017-9-20', '10:00:00',   31.75),"
	ord3 = "('2017-9-21', '15:00:00',   14.50),"
	ord4 = "('2017-9-22', '11:00:00',   21.50),"
	ord5 = "('2017-9-22', '14:30:00',   21.00);"
	orderInsertStatement = ordersinsert + ord1 + ord2 + ord3 + ord4 + ord5

	# String to insert product in orders 
	prdInOdr = "INSERT INTO products_in_order (order_id, product_id, quantity) VALUES "
	prdsInOdrStr = "(1, 1, 3),(1, 3, 2),(1, 4, 1),(2, 3, 1),(2, 4, 2),(2, 6, 1),(2, 7, 2),(3, 2, 2),(3, 4, 1),(3, 6, 2),(4, 1, 2),(4, 4, 2),(4, 5, 1),(4, 7, 1),(5, 2, 1),(5, 4, 2),(5, 6, 1);"
	productsInOrderInsertStatement = prdInOdr + prdsInOdrStr

	try: 
		with connection.cursor() as cursor:
			cursor.execute(productsInsertStatement)
			cursor.execute(orderInsertStatement)
			cursor.execute(productsInOrderInsertStatement)
		connection.commit()
	finally: 
		cursor.close()
		connection.close()


############################################################################
###################     PRODUCT TABLE FUNCTIONS     ########################
############################################################################

#Update Product Price 
def update_product_price(pid, price_in):
	connection = connect_to_db()
	sql = ("UPDATE products SET unit_price = (" + str(price_in) + ") WHERE product_id = (" + str(pid) + ")")
	try:
		with connection.cursor() as cursor:
			cursor.execute(sql)
			connection.commit()
	finally:
		cursor.close()
		connection.close()

#Update the product quantiy with a new quanity. 
def update_product_quanity(pid, quanity_in):
	connection = connect_to_db()
	sql = ("UPDATE products SET quantity = (" + str(quanity_in) + ") WHERE product_id = (" + str(pid) + ")")
	try:
		with connection.cursor() as cursor:
			cursor.execute(sql)
			connection.commit()
	finally:
		cursor.close()
		connection.close()
		
#Update the product quantiy with a new quanity. 
def return_products():
	connection = connect_to_db()
	sql = "SELECT * from products"
	try:
		with connection.cursor() as cursor:
			cursor.execute(sql)
			connection.commit()
			result = cursor.fetchall()
	finally:
		cursor.close()
		connection.close()
		return result
		


#Function to decrease product quanity in the database by 1
def decrement_product_quantity(pid):
	connection = connect_to_db()
	pidstring = str(pid)
	sql = ("UPDATE products SET quantity = quantity - 1 WHERE product_id = (%s)")
	try: 
		with connection.cursor() as cursor:
			cursor.execute(sql,pidstring)
			connection.commit()
	finally:
		cursor.close()
		connection.close()

#Add a product to the database
def add_product(product):
	connection = connect_to_db()

	try: 
		with connection.cursor() as cursor:
			sql = ("INSERT INTO products (product_name, description, expiration_date, quantity, unit_price, tax_rate VALUES (%s, %s, %s, %s,%s,%s)")
			cursor.execute(sql, (product.name, product.description, product.exp_date, str(product.quantity), str(product.price), str(product.tax_rate)))
			connection.commit()
	finally:
		cursor.close()
		connection.close()
		
#Add a product to the database
def add_product_simple(product):
	connection = connect_to_db()
	try: 
		with connection.cursor() as cursor:
			sql = ("INSERT INTO products (product_name, quantity, unit_price) VALUES (%s, %s, %s)")
			cursor.execute(sql, product)
			connection.commit()
	finally:
		cursor.close()
		connection.close()

#Read a single product and return it 
def read_single_product(pid):
	connection = connect_to_db()
	try: 
		with connection.cursor() as cursor:
			sql = "SELECT * FROM products WHERE product_id=%s"
			cursor.execute(sql,(str(pid),))
			result = cursor.fetchone()
	finally:
		connection.close()
	return result;


############################################################################
######################      ORDER TABLE FUNCTIONS      #####################
############################################################################

#Read a single order and return it 
def read_single_order_info(oid):
	connection = connect_to_db()
	try:
		with connection.cursor() as cursor: 
			sql = "SELECT '*' FROM 'orders' WHERE 'order_id'=%s"
			cursor.execute(sql,(str(oid),))
			result = cursor.fetchone() 
	finally:
		connection.close()
	return result;


############################################################################
################     Products_in_Order TABLE FUNCTIONS      ################
############################################################################

#Read a single order and return it 
def read_single_order(oid):
	connection = connect_to_db()
	try:
		with connection.cursor() as cursor: 
			#sql = "SELECT '*' FROM 'products_in_order' WHERE 'order_id'=%s"
			cursor.execute("SELECT * FROM products_in_order WHERE order_id = %s", str(oid))
			result = cursor.fetchall() 
	finally:
		connection.close()
	return result;
	

#Add a order to the database
def add_order(oid):
	connection = connect_to_db()

	try: 
		with connection.cursor() as cursor:
			sql = "INSERT INTO products_in_order (order_id, product_id, quantity) VALUES (%s, %s, %s)"
			cursor.execute(sql, oid)
			connection.commit()
	finally:
		cursor.close()
		connection.close()



#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#
#############################################################################
############################    DRIVER    ###################################
#############################################################################
#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#


global app
create_tables()
initialize_db()
app = StoreManagement()
app.mainloop()