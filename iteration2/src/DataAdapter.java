import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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
            String query = "SELECT * FROM products_in_order WHERE product_id = " + id;

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
                statement.setString(1, product.getProductName());
                statement.setDouble(2, product.getProductPrice());
                statement.setDouble(3, product.getProductQuantity());
                statement.setInt(4, product.getProductID());
            }
            else { // this product does not exist, use insert into
                statement = connection.prepareStatement("INSERT INTO products VALUES (?, ?, ?, ?)");
                statement.setString(2, product.getProductName());
                statement.setDouble(3, product.getProductPrice());
                statement.setDouble(4, product.getProductQuantity());
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
                order.setOrderID(resultSet.getInt("OrderID"));
                order.setCustomer(resultSet.getString("Customer"));
                order.setTotalPrice(resultSet.getDouble("TotalCost"));
                order.setOrderDate(resultSet.getDate("OrderDate"));
                resultSet.close();
                statement.close();
            }

            // loading the order lines for this order
            resultSet = statement.executeQuery("SELECT * FROM products_in_order WHERE order_id = " + id);

            while (resultSet.next()) {
                OrderLine line = new OrderLine();
                line.setOrderID(resultSet.getInt(1));
                line.setProductID(resultSet.getInt(2));
                line.setQuantity(resultSet.getDouble(3));
                line.setCost(resultSet.getDouble(4));
                order.addLine(line);
            }

            return order;

        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
            return null;
        }
    }

    public boolean saveOrder(OrderModel order) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO orders VALUES (?, ?, ?, ?, ?)");
            statement.setInt(1, order.getOrderID());
            statement.setDate(2, order.getOrderDate());
            statement.setString(3, order.getCustomer());
            statement.setDouble(4, order.getTotalPrice());
            statement.setDouble(5, order.getTotalTax());

            statement.execute();    // commit to the database;
            statement.close();

            statement = connection.prepareStatement("INSERT INTO products_in_order VALUES (?, ?, ?, ?)");
            for (OrderLine line: order.getLines()) { // store for each order line!
                statement.setInt(1, line.getOrderID());
                statement.setInt(2, line.getProductID());
                statement.setDouble(3, line.getQuantity());
                statement.setDouble(4, line.getCost());

                statement.execute();    // commit to the database;
            }
            statement.close();
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
                statement.setString(1, userInput.getDisplayName());
                statement.setString(2, userInput.getPassword());
                statement.setInt(3, userInput.getIsManager());
                statement.setString(4, userInput.getProfilePicture());
                statement.setInt(5, userInput.getIsSignedIn());

                System.out.println("Made it here");

            } else {
                statement = connection.prepareStatement("INSERT INTO orders VALUES (?, ?, ?, ?, ?, ? , ?, ?)");
                statement.setInt(1, userInput.getUserID());
                statement.setString(2, userInput.getDisplayName());
                statement.setString(3, userInput.getName());
                statement.setDate(4, ((java.sql.Date) userInput.getStartDate()));
                statement.setString(5, userInput.getPassword());
                statement.setInt(6, userInput.getIsManager());
                statement.setString(7, userInput.getProfilePicture());
                statement.setInt(8,userInput.getIsSignedIn());
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
