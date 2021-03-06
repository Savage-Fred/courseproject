
import java.sql.*;



public class Application {

    private static Application instance;   // Singleton pattern

    public static Application getInstance() {
        if (instance == null) {
            instance = new Application();
        }
        return instance;
    }
    // Main components of this application

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    private DataAdapter dataAdapter;

    private UserModel currentUser;

    // Product Views


    private AddItemInventoryView addItemInventoryView = new AddItemInventoryView();

    private AddNewUserView addNewUserView = new AddNewUserView();

    private BusinessReportView businessReportView = new BusinessReportView();

    private CardPaymentView cardPaymentView = new CardPaymentView();

    private CashPaymentView cashPaymentView = new CashPaymentView();

    private CashierMenuView cashierMenuView = new CashierMenuView();

    private CheckoutView checkoutView = new CheckoutView();

    private EditItemInventoryView editItemInventoryView = new EditItemInventoryView();

    private LoginScreenView loginScreenView = new LoginScreenView();

    private ManageInventoryView manageInventoryView = new ManageInventoryView();

    private ManagerMenuView managerMenuView = new ManagerMenuView();

    private PasswordSettingsView passwordSettingsView = new PasswordSettingsView();

    private PaymentView paymentView = new PaymentView();

    private PhotoSettingsView photoSettingsView = new PhotoSettingsView();

    private ReportMenuView reportMenuView = new ReportMenuView();

    private SearchInventoryView searchInventoryView = new SearchInventoryView();

    private SystemSettingsView systemSettingsView = new SystemSettingsView();

    private UserSettingsMenuView userSettingsMenuView = new UserSettingsMenuView();

    private OrderSelectionView orderSelectionView = new OrderSelectionView();


    // View Controllers

    private AddNewUserController addNewUserController;

    private AddItemInventoryController addItemInventoryController;

    private BusinessReportController businessReportController;

    private CardPaymentController cardPaymentController;

    private CashPaymentController cashPaymentController;

    private CashierMenuController cashierMenuController;

    private CheckoutController checkoutController;

    private EditItemInventoryController editItemInventoryController;

    private LoginScreenController loginScreenController;

    private ManageInventoryController manageInventoryController;

    private ManagerMenuController managerMenuController;

    private PasswordSettingsController passwordSettingsController;

    private PaymentController paymentController;

    private PhotoSettingsController photoSettingsController;

    private ReportMenuController reportMenuController;

    private SearchInventoryController searchInventoryController;

    private SystemSettingsController systemSettingsController;

    private UserSettingsController userSettingsController;

    private OrderSelectionController orderSelectionController;


    // Product View and Controller Get Methods

    public AddItemInventoryView getAddItemInventoryView() {
        return addItemInventoryView;
    }

    public BusinessReportView getBusinessReportView() {
        return businessReportView;
    }

    public CheckoutController getCheckoutController() {
        return checkoutController;
    }

    public AddNewUserView getAddNewUserView() {
        return addNewUserView;
    }

    public CardPaymentView getCardPaymentView() {
        return cardPaymentView;
    }

    public AddNewUserController getAddNewUserController() {
        return addNewUserController;
    }

    public BusinessReportController getBusinessReportController() {
        return businessReportController;
    }

    public CardPaymentController getCardPaymentController() {
        return cardPaymentController;
    }

    public CashierMenuView getCashierMenuView() {
        return cashierMenuView;
    }

    public CashPaymentView getCashPaymentView() {
        return cashPaymentView;
    }

    public CashPaymentController getCashPaymentController() {
        return cashPaymentController;
    }

    public CheckoutView getCheckoutView() {
        return checkoutView;
    }

    public EditItemInventoryController getEditItemInventoryController() {
        return editItemInventoryController;
    }

    public EditItemInventoryView getEditItemInventoryView() {
        return editItemInventoryView;
    }

    public LoginScreenController getLoginScreenController() {
        return loginScreenController;
    }

    public LoginScreenView getLoginScreenView() {
        return loginScreenView;
    }

    public ManageInventoryView getManageInventoryView() {
        return manageInventoryView;
    }

    public ManageInventoryController getManageInventoryController() {
        return manageInventoryController;
    }

    public ManagerMenuView getManagerMenuView() {
        return managerMenuView;
    }

    public PasswordSettingsView getPasswordSettingsView() {
        return passwordSettingsView;
    }

    public PaymentView getPaymentView() {
        return paymentView;
    }

    public PhotoSettingsView getPhotoSettingsView() {
        return photoSettingsView;
    }

    public ReportMenuView getReportMenuView() {
        return reportMenuView;
    }

    public SearchInventoryView getSearchInventoryView() {
        return searchInventoryView;
    }

    public PasswordSettingsController getPasswordSettingsController() {
        return passwordSettingsController;
    }

    public SystemSettingsView getSystemSettingsView() {
        return systemSettingsView;
    }

    public PaymentController getPaymentController() {
        return paymentController;
    }

    public PhotoSettingsController getPhotoSettingsController() {
        return photoSettingsController;
    }

    public ReportMenuController getReportMenuController() {
        return reportMenuController;
    }

    public SearchInventoryController getSearchInventoryController() {
        return searchInventoryController;
    }

    public SystemSettingsController getSystemSettingsController() {
        return systemSettingsController;
    }

    public UserSettingsController getUserSettingsController() {
        return userSettingsController;
    }

    public UserSettingsMenuView getUserSettingsMenuView() {
        return userSettingsMenuView;
    }

    public CashierMenuController getCashierMenuController() {
        return cashierMenuController;
    }

    public OrderSelectionController getOrderSelectionController() {
        return orderSelectionController;
    }

    public OrderSelectionView getOrderSelectionView() {
        return orderSelectionView;
    }

    public DataAdapter getDataAdapter() {

        return dataAdapter;
    }

    public AddItemInventoryController getAddItemInventoryController() {
        return addItemInventoryController;
    }

    public ManagerMenuController getManagerMenuController() {
        return managerMenuController;
    }

    public UserModel getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserModel currentUser) {
        this.currentUser = currentUser;
    }

    private void initializeDatabase(Statement stmt) throws SQLException {
        // create the tables and insert sample data here!

        //stmt.execute("create table product (product_id int PRIMARY KEY, ProductName char(30), Price double, Quantity double)");
        //stmt.execute("create table orders (ProductID int PRIMARY KEY, ProductName char(30), Price double, Quantity double)");


    }

    private Application() {
        // create SQLite database connection here!

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection( "jdbc:sqlite:grocery_store.db");
            Statement stmt = connection.createStatement();
            System.out.println("Connection to SQLite has been established.");
            if (!stmt.executeQuery("select * from products").next()) // product table do not exist
                initializeDatabase(stmt);


        }
        catch (ClassNotFoundException ex) {
            System.out.println("SQLite is not installed. System exits with error!");
            System.out.println("Cause: " + ex.getCause() + "\nException: " + ex.getException() + "\n");
            System.exit(1);
        }

        catch (SQLException ex) {
            System.out.println("SQLite database is not ready. System exits with error!" + ex.getMessage());

            System.exit(2);
        }

        // Create data adapter here!
        dataAdapter = new DataAdapter(connection);

        orderSelectionController = new OrderSelectionController(orderSelectionView, dataAdapter);

        addItemInventoryController = new AddItemInventoryController(addItemInventoryView, dataAdapter);

        checkoutController = new CheckoutController(checkoutView, dataAdapter);

        addNewUserController = new AddNewUserController(addNewUserView, dataAdapter);

        businessReportController = new BusinessReportController(businessReportView, dataAdapter);

        cardPaymentController = new CardPaymentController(cardPaymentView, dataAdapter);

        cashPaymentController = new CashPaymentController(cashPaymentView, dataAdapter);

        //checkoutController = new CheckoutController(checkoutView, dataAdapter);

        editItemInventoryController = new EditItemInventoryController(editItemInventoryView, dataAdapter);

        loginScreenController = new LoginScreenController(loginScreenView, dataAdapter);

        manageInventoryController = new ManageInventoryController(manageInventoryView, dataAdapter);

        managerMenuController = new ManagerMenuController(managerMenuView, dataAdapter);

        passwordSettingsController = new PasswordSettingsController(passwordSettingsView, dataAdapter);

        paymentController = new PaymentController(paymentView, dataAdapter);

        photoSettingsController = new PhotoSettingsController(photoSettingsView, dataAdapter);

        reportMenuController = new ReportMenuController(reportMenuView, dataAdapter);

        searchInventoryController = new SearchInventoryController(searchInventoryView, dataAdapter);

        systemSettingsController = new SystemSettingsController(systemSettingsView, dataAdapter);

        userSettingsController = new UserSettingsController(userSettingsMenuView, dataAdapter);

        cashierMenuController = new CashierMenuController(cashierMenuView, dataAdapter);


    }


    public static void main(String[] args) {

        Application.getInstance().getLoginScreenView().setVisible(true);
    }
}
