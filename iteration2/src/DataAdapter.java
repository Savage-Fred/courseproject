import java.sql.*;


public class DataAdapter {
    private Connection connection;

    public DataAdapter(Connection connection) {
        this.connection = connection;
    }

    /*
    *  @param integer product_id
    *  @returns returns ProductModel
    *
    *  Connects to products table
    *
    */
    public ProductModel loadProduct(int id) {
        try {
            String query = "SELECT * FROM products WHERE product_id = " + id;

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                ProductModel product = new ProductModel();
                product.setProductID(resultSet.getInt(1));
                product.setProductName(resultSet.getString(2));
                product.setProductPrice(resultSet.getDouble(6));
                product.setProductQuantity(resultSet.getDouble(5));
                resultSet.close();
                statement.close();

                return product;
            }

        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
        }
        return null;
    }

    /*
     *  @param integer pio_id
     *  @returns returns Orderline
     *
     *  Connects to products_id_order table
     *  Used in the Business Report Controller
     */

    public OrderLine loadProductforReport(int id) {
        try {
            String query = "SELECT * FROM products_in_order WHERE pio_id = " + id;

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                OrderLine orderLine = new OrderLine();
                orderLine.setOrderID(resultSet.getInt(2)); //order_id
                orderLine.setProductID(resultSet.getInt(3)); //product_id
                orderLine.setQuantity(resultSet.getInt(4)); // quantity
                resultSet.close();
                statement.close();

                return orderLine;
            }

        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
        }
        return null;
    }

    /*
     *  @param integer pio_id
     *  @returns returns Orderline
     *
     *  Connects to products_id_order table
     *  Used in the Business Report Controller
     */

    public OrderLine loadProductforReport(int productIdIn, int pioIdIn) {
        try {
            String query = "SELECT * FROM products_in_order WHERE pio_id = " + pioIdIn + " AND product_id = " + productIdIn + "";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                OrderLine orderLine = new OrderLine();
                orderLine.setOrderID(resultSet.getInt(2)); //order_id
                orderLine.setProductID(resultSet.getInt(3)); //product_id
                orderLine.setQuantity(resultSet.getInt(4)); // quantity
                resultSet.close();
                statement.close();

                return orderLine;
            }

        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
        }
        return null;
    }






    public boolean saveProduct(ProductModel product) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM products WHERE product_id = ?");
            statement.setInt(1, product.getProductID());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) { // this product exists, update its fields
                statement = connection.prepareStatement("UPDATE products SET product_name = ?, unit_price = ?, quantity = ? WHERE product_id = ?");
                statement.setString(2, product.getProductName());
                statement.setDouble(3, product.getProductPrice());
                statement.setDouble(4, product.getProductQuantity());
                statement.setInt(1, product.getProductID());
            }
            else { // this product does not exist, use insert into
                statement = connection.prepareStatement("INSERT INTO products (product_id, product_name, quantity, unit_price) VALUES (?, ?, ?, ?)");
                statement.setString(2, product.getProductName());
                statement.setDouble(4, product.getProductPrice());
                statement.setDouble(3, product.getProductQuantity());
                statement.setInt(1, product.getProductID());
            }
            statement.execute();
            resultSet.close();
            statement.close();
            return true;        // save successfully

        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
            return false; // cannot save!
        }
    }

    public OrderModel loadOrder(int id) {
        try {
            OrderModel order = null;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM orders WHERE order_id = " + id);

            if (resultSet.next()) {
                order = new OrderModel();
                order.setOrderID(resultSet.getInt("order_id"));
                order.setCustomer(resultSet.getInt("customer_id"));
                order.setTotalPrice(resultSet.getDouble("total_price"));
                //order.setOrderDate(resultSet.getDate("order_date"));
                resultSet.close();
                statement.close();
            }

            // loading the order lines for this order
            resultSet = statement.executeQuery("SELECT * FROM products_in_order WHERE order_id = " + id);

            while (resultSet.next()) {
                OrderLine line = new OrderLine();
                line.setOrderID(resultSet.getInt(2));
                line.setProductID(resultSet.getInt(3));
                line.setQuantity(resultSet.getDouble(4));
                assert order != null;
                order.addLine(line);
            }

            return order;

        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
            return null;
        }
    }

    public int loadProductInOrder() {
        try {
            int latestSessionID = -1;
            Statement statement = connection.createStatement();

            // loading the order lines for this order
            ResultSet resultSet = statement.executeQuery("SELECT pio_id FROM products_in_order ORDER BY pio_id DESC " );

            if (resultSet.next()) {
                latestSessionID = resultSet.getInt("pio_id");
                System.out.println("Latest session_id : " + latestSessionID);

            }


            return (latestSessionID);

        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
            return -1;
        }
    }

    public int loadNumberOfProduct() {
        try {
            int latestSessionID = -1;
            Statement statement = connection.createStatement();

            // loading the order lines for this order
            ResultSet resultSet = statement.executeQuery("SELECT product_id FROM products ORDER BY product_id DESC " );

            if (resultSet.next()) {
                latestSessionID = resultSet.getInt("product_id");
                System.out.println("Latest session_id : " + latestSessionID);

            }


            return (latestSessionID);

        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
            return -1;
        }
    }

    public int loadNumberOfOrders() {
        try {
            int latestSessionID = -1;
            Statement statement = connection.createStatement();

            // loading the order lines for this order
            ResultSet resultSet = statement.executeQuery("SELECT order_id FROM orders ORDER BY order_id DESC " );

            if (resultSet.next()) {
                latestSessionID = resultSet.getInt("order_id");
                System.out.println("Latest session_id : " + latestSessionID);

            }


            return (latestSessionID);

        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
            return -1;
        }
    }


        public boolean saveOrder(OrderModel order) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE order_id = ?");

            statement.setInt(1, order.getOrderID());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) //Order exist, update its fields
            {
                statement = connection.prepareStatement("UPDATE orders SET total_price = ? WHERE order_id = ?");
                statement.setInt(2, order.getOrderID());
                statement.setDouble(1, order.getTotalPrice());

                statement.execute();
                statement.close();

                int newPIO = loadProductInOrder();

                statement = connection.prepareStatement("SELECT * FROM products_in_order WHERE order_id = ?");

                statement.setInt(1, order.getOrderID());
                ResultSet resultSet2 = statement.executeQuery();
                int size = 0;
                while (resultSet2.next())
                {
                    size++;
                }


                statement.close();

                statement = connection.prepareStatement("INSERT INTO products_in_order VALUES (?, ?, ?, ?)");
                int i = 1;
                for(OrderLine line: order.getLines()) {

                    if (i > size) {
                        statement.setInt(1, (newPIO+1));
                        statement.setInt(2, order.getOrderID());
                        statement.setInt(3, line.getProductID());
                        statement.setDouble(4, line.getQuantity());
                        statement.execute();    // commit to the database;
                    }
                    i++;

                }
                statement.close();


            } else {
                statement = connection.prepareStatement("INSERT INTO orders VALUES (?, ?, ?, ?, ?, ? , ? )");

                statement.setInt(1, order.getOrderID());
                statement.setDate(2, order.getOrderDate());
                statement.setInt(3, order.getCustomer());
                statement.setDouble(4, order.getTotalPrice());
                statement.setDouble(5, order.getTotalTax());

                statement.execute();    // commit to the database;
                statement.close();

                statement = connection.prepareStatement("INSERT INTO products_in_order VALUES (?, ?, ?, ?)");
                int newPIO = loadProductInOrder() + 1;


                for (OrderLine line : order.getLines()) { // store for each order line!

                    System.out.println(newPIO);


                    statement.setInt(1, newPIO);
                    statement.setInt(2, line.getOrderID());
                    statement.setInt(3, line.getProductID());
                    statement.setDouble(4, line.getQuantity());

                    newPIO++;

                    statement.execute();    // commit to the database;
                }
                statement.close();
            }
            return true; // save successfully!
        }
        catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
            return false;
        }
    }

    public UserModel loadUser(String username, String password) {
        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            //PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");

            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                UserModel user = new UserModel();
                user.setUserID(resultSet.getInt("user_id"));
                user.setName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setDisplayName(resultSet.getString("fullname"));
                user.setIsManager(resultSet.getInt("is_manager"));
                user.setIsSignedIn(resultSet.getInt("is_signed_in"));
                user.setProfilePicture(resultSet.getString("user_pic_path"));

                //System.out.println(resultSet.getInt("is_manager")
              //      +  "\n" + resultSet.getInt("is_signed_in") );


                resultSet.close();
                statement.close();

                return user;
            }

        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
        }
        return null;
    }

    public boolean saveUser(UserModel userInput) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE user_id = ?");
            statement.setInt(1, userInput.getUserID());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) //User exist, update its fields
            {
                statement = connection.prepareStatement("UPDATE users SET username = ?, password = ?, is_manager = ?, user_pic_path = ?, is_signed_in = ? WHERE user_id = ?");
                statement.setString(1, userInput.getName());
                statement.setString(2, userInput.getPassword());
                statement.setInt(3, userInput.getIsManager());
                statement.setString(4, userInput.getProfilePicture());
                statement.setInt(5, userInput.getIsSignedIn());
                statement.setInt(6, userInput.getUserID());

                statement.execute();


            } else {
                statement = connection.prepareStatement("INSERT INTO users VALUES (?, ?, ?, ?, ?, ? , ?, ?)");
                statement.setInt(1, userInput.getUserID());
                statement.setString(2, userInput.getDisplayName());
                statement.setString(3, userInput.getName());
                statement.setDate(4, ((java.sql.Date) userInput.getStartDate()));
                statement.setString(5, userInput.getPassword());
                statement.setInt(6, userInput.getIsManager());
                statement.setString(7, userInput.getProfilePicture());
                statement.setInt(8,userInput.getIsSignedIn());
                statement.execute();
            }


            resultSet.close();
            statement.close();
            return true;        // save successfully

        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
            return false; // cannot save!

        }
    }

    public boolean loginUser(UserModel user) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE user_id = ?");
            statement.setInt(1, user.getUserID());

            user.setIsSignedIn(1);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) { // update user isSignedIn field
                statement = connection.prepareStatement("UPDATE users SET is_signed_in = ? WHERE user_id = ?");
                statement.setInt(1, user.getIsSignedIn());
                statement.setInt(2, user.getUserID());

                statement.execute();    // commit to the database;
                resultSet.close();
                statement.close();
            }

            return true;        // save successfully

        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
            return false; // cannot save!
        }


    }

    public boolean logoutUser() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE is_signed_in = 1");

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) { // update user isSignedIn field
                statement = connection.prepareStatement("UPDATE users SET is_signed_in = 0 WHERE is_signed_in = 1");

                statement.execute();    // commit to the database;
                resultSet.close();
                statement.close();
            }

            return true;        // save successfully

        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
            return false; // cannot save!
        }


    }




    public UserModel getCurrentUser() {
        UserModel user = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE is_signed_in = 1");
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new UserModel();
                user.setUserID(resultSet.getInt("user_id"));
                user.setName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setDisplayName(resultSet.getString("fullname"));
                user.setIsManager(resultSet.getInt("is_manager"));
                user.setIsSignedIn(resultSet.getInt("is_signed_in"));
                user.setProfilePicture(resultSet.getString("user_pic_path"));


                resultSet.close();
                statement.close();

                return user;
            }

        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
        }
        return null;




    }



}
