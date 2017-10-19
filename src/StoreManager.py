
import tkinter as tk
from tkinter import *
import pymysql.cursors
import pymysql
#import MySQLdb

LARGE_FONT= ("Verdana", 12)
dbServerName    = "127.0.0.1"
dbUser          = "will"
dbPassword      = "will"
dbName          = "grocery_store"
charSet         = "utf8mb4"
cursorType      = pymysql.cursors.DictCursor
connection      = pymysql.connect(host=dbServerName, user=dbUser, password=dbPassword, db=dbName, charset=charSet,cursorclass=cursorType)

# Main Class for Initilizing and Running Frames (GUI)
class StoreManagement(tk.Tk):

    def __init__(self, *args, **kwargs):
        
        tk.Tk.__init__(self, *args, **kwargs)
        container = tk.Frame(self)

        container.pack(side="top", fill="both", expand = True)

        container.grid_rowconfigure(0, weight=1)
        container.grid_columnconfigure(0, weight=1)
        
        self.frames = {}

        for F in (MainMenuPage, CheckOutPage, AddItemToCartPage, PaymentTypePage, ItemManagementPage,CashPaymentPage, CreditCardPaymentPage, AddItemToInventoryPage, ManageCurrentInventoryPage):

            frame = F(container, self)

            self.frames[F] = frame

            frame.grid(row=0, column=0, sticky="nsew")

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
        label.pack(pady=10,padx=10)

        button1 = tk.Button(self, text="Add Item", command=lambda: controller.show_frame(AddItemToCartPage))
        button1.pack()

        button2 = tk.Button(self, text="Pay", command=lambda: controller.show_frame(PaymentTypePage))
        button2.pack()
        
        button3 = tk.Button(self, text="Main Menu", command=lambda: controller.show_frame(MainMenuPage))
        button3.pack()
        

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
        
        productid_text = StringVar()
        entry1 = tk.Entry(self, bd = 2, textvariable = productid_text)
        entry1.grid(row = 1, column = 1, sticky = E)
        
        label2 = tk.Label(self, text = "Quantity: ")
        label2.grid(row = 2, column = 0, sticky = E)
        
        quantity_text = StringVar()
        entry2 = tk.Entry(self, bd = 2, textvariable = quantity_text)
        entry2.grid(row = 2, column = 1, sticky = E)
        
        
        # Define ListBox 
        
        label3 = tk.Label(self, text = "Current Cart")
        
        list1 = Listbox(self, height = 6, width = 35)
        list1.grid(row = 4, column = 1, rowspan = 6, columnspan = 2)
        
        cart = {}
        
        for order_id, prodcut_id, quantity in (CartOrders):
            if (order_id == 1):
                cart[prodcut_id] = quantity
        
        # Add function to search product Id
        # Display current total
        
        for items in cart.items():
            list1.insert(END, items)
        
        # Attach Scrollbar to the List 
        
        sb1 = Scrollbar(self)
        sb1.grid(row = 3, column = 3, rowspan = 6)
        
        list1.configure(yscrollcommand = sb1.set)
        sb1.configure(command=list1.yview)
        
        def clearEntries(event):
            entry1.delete(0, END)
            entry2.delete(0, END)

        button1 = tk.Button(self, text="Add Item", command=lambda: controller.show_frame(AddItemToCartPage))
        button1.grid(row = 10, column = 0, sticky = E)
        button1.bind("<Button-1>", clearEntries)

        button2 = tk.Button(self, text="Back to Cart", command=lambda: controller.show_frame(CheckOutPage))
        button2.grid(row = 10, column = 1, sticky = E)
        button2.bind("<Button-1>", clearEntries)

        
                
# Display Payment Type Page
#   Choices: Credit Card or Cash    
class PaymentTypePage(tk.Frame):

    def __init__(self, parent, controller):
        tk.Frame.__init__(self, parent)
        label = tk.Label(self, text="Select Payment Type", font=LARGE_FONT)
        label.grid(row = 2, column = 3)

        button1 = tk.Button(self, text="Credit Card", command=lambda: controller.show_frame(CreditCardPaymentPage))
        button1.grid(row = 3, column = 3)

        button2 = tk.Button(self, text="Cash", command=lambda: controller.show_frame(CashPaymentPage))
        button2.grid(row = 4, column = 3)
        
        button3 = tk.Button(self, text="Back to Cart", command=lambda: controller.show_frame(CheckOutPage))
        button3.grid(row = 5, column = 3)
        
# Display Credit Card Payment Page
# Choices: Return to Main Menu
class CreditCardPaymentPage(tk.Frame):

    def __init__(self, parent, controller):
        tk.Frame.__init__(self, parent)
        label = tk.Label(self, text="Credit Card Payment", font=LARGE_FONT)
        label.grid(row = 0, column = 0, sticky = E)
        
        total = 12.50
        label3 = tk.Label(self, text = "Amount Due: %s"%total)
        label3.grid(row = 1, column = 0, sticky = E)
                
        label2 = tk.Label(self, text="Credit Card Number")
        label2.grid(row = 2, column = 0, sticky = E)
        
        def clearEntries(event):
            entry1.delete(0, END)
                    
        entry1 = tk.Entry(self, bd = 2)
        entry1.grid(row = 2, column = 1, sticky = E)

        button1 = tk.Button(self, text="Main Menu", command=lambda: controller.show_frame(MainMenuPage))
        button1.grid(row = 3, stick = E)
        button1.bind("<Button-1>", clearEntries)
        

# Display Cash Payment Page
# Choices: Return to Main Menu
class CashPaymentPage(tk.Frame):
    
    def __init__(self, parent, controller):
        tk.Frame.__init__(self, parent)
        label = tk.Label(self, text="Cash Payment", font=LARGE_FONT)
        label.grid(row = 0, column = 0, sticky = E)
        
        total = 0
        
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
        
        def calculate(event):
                cashRecieved = float(entry1.get())
                change = cashRecieved - total
                label4 = tk.Label(self, text = "$ %s" % change)
                label4.grid(row = 3, column = 1, sticky = E)
        
        def clearEntries(event):
            entry1.delete(0, END)   
                
        # Button that Calculates Change
        button2 = tk.Button(self, text="Calculate")
        button2.bind("<Button-1>", calculate)
        button2.grid(row = 4, column = 1, sticky = E)
        
        # Button that directs user back to Main Menu
        button1 = tk.Button(self, text="Main Menu", command=lambda: controller.show_frame(MainMenuPage))
        button1.grid(row = 4, column = 2, sticky = E)
        button1.bind("<Button-1>", clearEntries)
        
    

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

        label1 = tk.Label(self, text = "Product ID: ")
        label1.grid(row = 2, column = 0)
        
        # Testing Return Key Output
        def getProductID(event):
            product_id = entry1.get()
            print(product_id)
        
        # User Entry field for Product ID
        entry1 = tk.Entry(self, bd = 2)
        entry1.bind(("<Return>"), getProductID)
        entry1.grid(row = 2, column = 1)
        product_id = entry1.get()
        
        # Testing Return Key Output
        def getProductName(event):
            product_name = entry2.get()
            print(product_name)
        
        label2 = tk.Label(self, text = "Product Name: ")
        label2.grid(row = 3, column = 0)
        
        # User Entry field for Product Name
        entry2 = tk.Entry(self, bd = 2)
        entry2.grid(row = 3, column = 1)
        entry2.bind(("<Return>"), getProductName)
        
        # Testing Return Key Output
        def getProductDescription(event):
            product_description = entry4.get()
            print(product_description)
        
        label4 = tk.Label(self, text = "Product Description: ")
        label4.grid(row = 4, column = 0)
        
        # User Entry field for Product Description
        entry4 = tk.Entry(self, bd = 2)
        entry4.grid(row = 4, column = 1)
        entry4.bind(("<Return>"), getProductDescription)
        
        # Testing Return Key Output
        def getProductExpDate(event):
            product_expdate = entry5.get()
            print(product_expdate)
        
        label5 = tk.Label(self, text = "Product Experation Date: ")
        label5.grid(row = 5, column = 0)
        
        # User Entry field for Product Exp. Date
        entry5 = tk.Entry(self, bd = 2)
        entry5.grid(row = 5, column = 1)
        entry5.bind(("<Return>"), getProductExpDate)
        
        # Testing Return Key Output
        def getProductQuantity(event):
            product_quantity = entry6.get()
            print(product_quantity)
        
        label6 = tk.Label(self, text = "Product Quantity: ")
        label6.grid(row = 6, column = 0)
        
        # User Entry field for Product Quantity
        entry6 = tk.Entry(self, bd = 2)
        entry6.grid(row = 6, column = 1)
        entry6.bind(("<Return>"), getProductQuantity)
        
        # Testing Return Key Output
        def getProductTaxRate(event):
            product_taxrate = entry7.get()
            print(product_taxrate)
        
        label7 = tk.Label(self, text = "Product Tax Rate: ")
        label7.grid(row = 7, column = 0)
        
        # User Entry field for Product Tax Rate
        entry7 = tk.Entry(self, bd = 2)
        entry7.grid(row = 7, column = 1)
        entry7.bind(("<Return>"), getProductTaxRate)
        
        # Testing Return Key Output
        def getProductPrice(event):
            product_price = entry3.get()
            print(product_price)
        
        label3 = tk.Label(self, text = "Product Price: ")
        label3.grid(row = 8, column = 0)
        
        # User Entry field for Product Price
        entry3 = tk.Entry(self, bd = 2)
        entry3.grid(row = 8, column = 1)
        entry3.bind(("<Return>"), getProductPrice)
        
        def clearEntries(event):
            entry1.delete(0, END)
            entry2.delete(0, END)
            entry3.delete(0, END)
            entry4.delete(0, END)
            entry5.delete(0, END)
            entry6.delete(0, END)
            entry7.delete(0, END)

        
        def addToInventory(event):
            product = Inventory.insert(Products.newProduct(product_id, product_name, product_price, product_description, product_expdate, product_quantity, product_taxrate))
            
            

        button1 = tk.Button(self, text="Add Item", command=lambda: controller.show_frame(AddItemToInventoryPage))
        button1.grid(row = 9, column = 0)
        button1.bind("<Button-1>", addToInventory)
        button1.bind("<Button-1>", clearEntries)
        

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
        
        for x in Inventory:
            list1.insert(END, x)
        
        # Insert Sample Data 
        # list1.insert(1, "Apple")
        # list1.insert(2, "Orange")
        
        # Attach Scrollbar to the List 
        
        sb1 = Scrollbar(self)
        sb1.grid(row = 3, column = 3, rowspan = 6)
        
        list1.configure(yscrollcommand = sb1.set)
        sb1.configure(command=list1.yview)

        button1 = tk.Button(self, text="Add Item", command=lambda: controller.show_frame(AddItemToInventoryPage))
        button1.grid(row = 12, column = 1)

        button2 = tk.Button(self, text="Item Management Menu", command=lambda: controller.show_frame(ItemManagementPage))
        button2.grid(row = 12, column = 2)
        
        
class Customer():
    current_order = {}
    current_order_total = DoubleVar
    
    #def calculateOrder():
    #   for x in elements:
    
class Products():
    product_id = IntVar
    name = StringVar
    price = DoubleVar
    description = StringVar
    exp_date = StringVar
    quantity = IntVar
    tax_rate = DoubleVar
    
    def newProduct(product_id, name, price, description, exp_date, quantity, tax_rate):
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
    order_id = IntVar
    order_date = StringVar
    order_time = StringVar
    total_price = DoubleVar


#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#
#############################################################################
########################     DATABASE STUFF BELOW    ########################
#############################################################################
#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#


#Function to connect to the mysql database 
def connect_to_db():
    connection = pymysql.connect(host='localhost',
                             user='will',
                             password='will',
                             db='grocery_store',
                             charset='utf8mb4',
                             cursorclass=pymysql.cursors.DictCursor)
    return connection

############################################################################
######################  Initialize the Database     ########################
############################################################################
def create_tables():
    connection = connect_to_db()
    try:
        with connection.cursor() as cursor:
            #cursor.execute("DROP TABLE IF EXISTS `products`;")
            #cursor.execute("DROP TABLE IF EXISTS `orders`;")
            #cursor.execute("DROP TABLE IF EXISTS `products_in_order`;")
            #connection.commit()
            ###### Build the strings 
            create_tbl = "CREATE TABLE IF NOT EXISTS "
            prt1 = "products("
            prt2 = "product_id INT AUTO_INCREMENT PRIMARY KEY, "
            prt3 = "product_name VARCHAR(30) NOT NULL, "
            prt4 = "description VARCHAR(30), "
            prt5 = "expiration_date DATETIME, "
            prt6 = "quantity INT, "
            prt7 = "unit_price DECIMAL(13,4), "
            prt8 = "tax_rate DECIMAL(5,5));"
            createProductsTbl = create_tbl + prt1 + prt2 + prt3 + prt4 + prt5 + prt6 + prt7 + prt8
            print(createProductsTbl)

            ort1 = "orders("
            ort2 = "order_id INT AUTO_INCREMENT PRIMARY KEY, "
            ort3 = "order_date DATETIME, "
            ort4 = "order_time TIME, "
            ort5 = "total_price DECIMAL(13,4));"
            createOrdersTbl = create_tbl + ort1 + ort2 + ort3 + ort4 + ort5
            print()
            print(createOrdersTbl)

            potbl1 = "products_in_order("
            potbl2 = "pio_id INT AUTO_INCREMENT PRIMARY KEY, "
            potbl3 = "FOREIGN KEY order_id(order_id) REFERENCES orders(order_id), "
            potbl4 = "FOREIGN KEY product_id(product_id) REFERENCES products(product_id), "
            potbl5 = "quantity INT);"
            createPinOTbl = create_tbl + potbl1 + potbl2 + potbl3 + potbl4 + potbl5

            print()
            print(createPinOTbl)
            print('\n' * 10)
            # Create a new record
            print()
            cursor.execute(createOrdersTbl)
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
    #connect_to_db()
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
    #connection = connect_to_db()
    sql = "UPDATE 'products' SET 'price' = (%s) WHERE 'product_id' = (%s)"
    try:
        with connection.cursor() as cursor:
            cursor.execute(sql, (str(price_in), str(pid)))
            connection.commit()
    finally:
        cursor.close()
        connection.close()

#Update the product quantiy with a new quanity. 
def update_product_quanity(pid, quanity_in):
    #connection = connect_to_db()
    sql = "UPDATE 'products' SET 'quanity' = (%s) WHERE 'product_id' = (%s)"
    try:
        with connection.cursor() as cursor:
            cursor.execute(sql, (str(quanity_in), str(pid)))
            connection.commit()
    finally:
        cursor.close()
        connection.close()


#Function to decrease product quanity in the database by 1
def decrement_product_quantity(pid):
    #connection = connect_to_db()
    pidstring = str(pid)
    sql = "UPDATE 'products' SET 'quantity' = 'quantity' - 1 WHERE 'product_id' = (%s)"
    try: 
        with connection.cursor() as cursor:
            cursor.execute(sql, (pidstring))
            connection.commit()
    finally:
        cursor.close()
        connection.close()

#Add a product to the database
def add_product(product):
    #connection = connect_to_db()

    try: 
        with connection.cursor() as cursor:
            sql = "INSERT INTO 'products' ('product_name', 'description', 'expiration_date','quantity', 'unit_price', 'tax_rate' VALUES (%s, %s, %s, %s,%s,%s)"
            cursor.execute(sql, (product.name, product.description, product.exp_date, str(product.quantity), str(product.price), str(product.tax_rate)))
            connection.commit()
    finally:
        cursor.close()
        connection.close()

#Read a single product and return it 
def read_single_product(pid):
    #connection = connect_to_db()
    try: 
        with connection.cursor() as cursor:
            sql = "SELECT '*' FROM 'products' WHERE 'product_id'=%s"
            cursor.execute(sql,(str(pid),))
            result = cursor.fetchone()
    finally:
        connection.close()
    return result


############################################################################
######################      ORDER TABLE FUNCTIONS      #####################
############################################################################

#Read a single order and return it 
def read_single_order(oid):
    #connection = connect_to_db()
    try:
        with connection.cursor() as cursor: 
            sql = "SELECT '*' FROM 'Orders' WHERE 'order_id'=%s"
            cursor.execute(sql,(str(oid),))
            result = cursor.fetchone() 
    finally:
        connection.close()
    return result;





############################################################################
##########################  SAMPLE DATA IN PYTHON
############################################################################
# Sample Inventory
# Product ID, Product Name, Description, Experation Date, Quantity, Unit Price, Tax Rate

Inventory = (
    (1, 'Apple',    'Red Delicious',        '2017-10-13', 50, 1.75,     0.06000),
    (2, 'Banana',   'Contains 10 Bananas',  '2017-10-15', 30, 2.00,     0.06000),
    (3, 'Eggs',     'One Dozen Eggs',       '2017-10-17', 40, 2.75,     0.08000),
    (4, 'Milk',     'Whole Milk',           '2017-10-05', 70, 2.50,     0.08000),
    (5, 'Bread',    'Whole Grain',          '2017-06-10', 45, 3.00,     0.06000),
    (6, 'Cheese',   'Shredded Cheddar',     '2017-11-27', 60, 4.00,     0.08000),
    (7, 'Steak',    'Two Pack T-Bones',     '2017-10-04', 35, 10.00,    0.05000)
    )
    
# Sample Orders 
# Order Date, Order Time, Total Price
Orders = (
    ('2017-9-20', '08:00:00',   13.25),
    ('2017-9-20', '10:00:00',   31.75),
    ('2017-9-21', '15:00:00',   14.50),
    ('2017-9-22', '11:00:00',   21.50),
    ('2017-9-22', '14:30:00',   21.00)
)

# Sample Products and Orders 
# Order ID, Product ID, Quantity

CartOrders = (
    (1, 1, 3),
    (1, 3, 2),
    (1, 4, 1),
    (2, 3, 1),
    (2, 4, 2),
    (2, 6, 1),
    (2, 7, 2),
    (3, 2, 2),
    (3, 4, 1),
    (3, 6, 2),
    (4, 1, 2),
    (4, 4, 2),
    (4, 5, 1),
    (4, 7, 1),
    (5, 2, 1),
    (5, 4, 2),
    (5, 6, 1)
)
    
    
#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#
#############################################################################
############################    DRIVER    ###################################
#############################################################################
#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#


app = StoreManagement()
create_tables()
#initialize_db()
app.mainloop()