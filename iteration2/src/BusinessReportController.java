import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class BusinessReportController implements ActionListener {

    private BusinessReportView businessReportView;
    private DataAdapter dataAdapter;


    private Vector<ReportModel> reports = new Vector<>();


    public BusinessReportController(BusinessReportView view, DataAdapter data) {
        this.businessReportView = view;
        this.dataAdapter = data;

        businessReportView.getSortByUnitsButton().addActionListener(this);
        businessReportView.getSortByRevenueButton().addActionListener(this);
        businessReportView.getCloseButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == businessReportView.getSortByUnitsButton()) {
            sortByMostUnitsSold();
        } else if (e.getSource() == businessReportView.getSortByRevenueButton()) {
            sortByRevenue();
        } else if (e.getSource() == businessReportView.getCloseButton()) {
            loadClose();
        }

    }

    public void loadOrders(){
        OrderLine orderLine;
        try {

            System.out.println(dataAdapter.loadNumberOfProduct());
            int productId = 1;
            while(dataAdapter.loadProduct(productId) != null) {
                Vector<OrderLine> orders = new Vector<>();
                for (int pioIn = 1; dataAdapter.loadProductforReport(pioIn) != null; pioIn++) {
                    if (dataAdapter.loadProductforReport(productId, pioIn) != null) {
                        orderLine = dataAdapter.loadProductforReport(productId, pioIn);
                        orderLine = setOrderLineCost(orderLine);
                        orders.add(orderLine);
                    }
                }

                ReportModel report = new ReportModel();
                // Calculate Revenue
                double revenue = 0;
                for (int i = 0; i < orders.size(); i++) {
                    revenue += orders.elementAt(i).getCost();
                }
                report.setRevenue(revenue);
                //Calculate Units Sold
                int unitsSold = 0;
                for (int i = 0; i < orders.size(); i++) {
                    unitsSold += orders.elementAt(i).getQuantity();
                }

                report.setUnitsSold(unitsSold);


                System.out.println(productId);
                //Get Item name
                if (orders.firstElement() != null && orders.size() > 0 && dataAdapter.loadProduct(orders.firstElement().getProductID()) != null) {
                    ProductModel product = dataAdapter.loadProduct(orders.firstElement().getProductID());
                    String itemName = product.getProductName();
                    report.setItemName(itemName);
                } else {
                    System.out.println("Class: BusinessReportController"
                            + "\nMethod: loadOrders"
                            + "\nNull Product Return");
                }

                reports.add(report);
                productId++;
            }

            loadTable();

        }
        catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null, "Null pointer Throwen");
                //return;
            }



    }

    public OrderLine setOrderLineCost(OrderLine orderLine) {
        System.out.println(orderLine.getProductID());
        ProductModel product = new ProductModel();
        if (dataAdapter.loadProduct((int)orderLine.getProductID()) != null) {
            product = dataAdapter.loadProduct((int)orderLine.getProductID());
        }
        else {
            System.out.println("Class: BusinessReportController"
                    + "\nMethod: setOrderLineCost"
                    + "\nNull Product Return");
        }

        orderLine.setCost(product.getProductPrice() * orderLine.getQuantity());

        return orderLine;
    }

    public void sortByMostUnitsSold() {
        try {

            for (int i = 0; i < reports.size() - 1; i++) {
                for (int j = 0; j < reports.size() - i - 1; j++) {
                    if (reports.elementAt(j).getUnitsSold() > reports.elementAt(j + 1).getUnitsSold()) {

                        swapReports(j, j + 1);
                    }
                }
            }
            businessReportView.clearTable();
            loadTable();

            JOptionPane.showMessageDialog(null, "Report has been loaded");

        }
        catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Null Pointer Thrown");
            //return;
        }
    }

    public void swapReports(int x, int y) {
        ReportModel temp = new ReportModel();

        temp.setItemName(reports.elementAt(x).getItemName());
        temp.setRevenue(reports.elementAt(x).getRevenue());
        temp.setUnitsSold(reports.elementAt(x).getUnitsSold());

        reports.elementAt(x).setItemName(reports.elementAt(y).getItemName());
        reports.elementAt(x).setRevenue(reports.elementAt(y).getRevenue());
        reports.elementAt(x).setUnitsSold(reports.elementAt(y).getUnitsSold());

        reports.elementAt(y).setItemName(temp.getItemName());
        reports.elementAt(y).setRevenue(temp.getRevenue());
        reports.elementAt(y).setUnitsSold(temp.getUnitsSold());

    }



    public void sortByRevenue() {
        try {

            for (int i = 0; i < reports.size() - 1; i++) {
                for (int j = 0; j < reports.size() - i - 1; j++) {
                    if (reports.elementAt(j).getRevenue() > reports.elementAt(j + 1).getRevenue()) {

                        swapReports(j, j + 1);
                    }
                }
            }
            businessReportView.clearTable();
            loadTable();

            JOptionPane.showMessageDialog(null, "Report has been loaded");

        }
        catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Null Pointer Thrown");
            //return;
        }
    }


    public void loadClose() {
        Application.getInstance().getBusinessReportView().setVisible(false);
        Application.getInstance().getReportMenuView().setVisible(true);
        businessReportView.clearTable();
        reports.clear();
    }



    public void loadTable() {

        businessReportView.clearTable();

        ReportModel report;

        for (int i = 1; i < reports.size(); i++) {
            report = reports.elementAt(i);
            Object[] row = new Object[3];
            row[0] = report.getItemName();
            row[1] = report.getUnitsSold();
            row[2] = report.getRevenue();

            businessReportView.addRow(row);
        }

        businessReportView.validate();

    }
}
