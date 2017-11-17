import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class BusinessReportController implements ActionListener {

    private BusinessReportView businessReportView;
    private DataAdapter dataAdapter;


    private Vector<ReportModel> reports;


    public BusinessReportController(BusinessReportView view, DataAdapter data) {
        this.businessReportView = view;
        this.dataAdapter = data;

        businessReportView.getSortByButton().addActionListener(this);
        businessReportView.getCloseButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == businessReportView.getSortByButton()) {
            sortByMostUnitsSold();
        } else if (e.getSource() == businessReportView.getCloseButton()) {
            loadClose();
        }

    }

    public void loadOrders(){
        OrderLine orderLine;
        try {

            for (int productIdIn = 1; dataAdapter.loadProduct(productIdIn) != null; productIdIn++) {
                Vector<OrderLine> orders = new Vector<>();
                for (int pioIn = 1; dataAdapter.loadProductforReport(pioIn) != null; pioIn++) {
                    if (dataAdapter.loadProductforReport(productIdIn, pioIn) != null) {

                        orderLine = dataAdapter.loadProductforReport(productIdIn, pioIn);
                        orderLine = setOrderLineCost(orderLine);
                        orders.add(orderLine);

                    } else {
                        System.out.println("Class: BusinessReportController"
                                + "\nMethod: loadOrders"
                                + "\nNull Order Return");
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

                //Get Item name
                if (dataAdapter.loadProduct(orders.elementAt(0).getProductID()).getProductName() != null) {

                    String itemName = dataAdapter.loadProduct(orders.elementAt(0).getProductID()).getProductName();
                    report.setItemName(itemName);
                } else {
                    System.out.println("Class: BusinessReportController"
                            + "\nMethod: loadOrders"
                            + "\nNull Product Return");
                }

                reports.add(report);

            }
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
                loadTable();

                JOptionPane.showMessageDialog(null, "Report has been loaded");
            }
        }
        catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Null Pointer Thrown");
            //return;
        }
    }

    public void swapReports(int x, int y) {
        ReportModel temp = reports.elementAt(x);

        reports.elementAt(x).setItemName(reports.elementAt(y).getItemName());
        reports.elementAt(x).setRevenue(reports.elementAt(y).getRevenue());
        reports.elementAt(x).setUnitsSold(reports.elementAt(y).getUnitsSold());

        reports.elementAt(y).setItemName(temp.getItemName());
        reports.elementAt(y).setRevenue(temp.getRevenue());
        reports.elementAt(y).setUnitsSold(temp.getUnitsSold());

    }





    public void sortByHighestDollarValue() {
        JOptionPane.showMessageDialog(null, "Available in Future Versions");

    }

    public void sortByLargestProfit() {
        JOptionPane.showMessageDialog(null, "Available in Future Versions");

    }

    public void loadClose() {
        Application.getInstance().getBusinessReportView().setVisible(false);
        Application.getInstance().getReportMenuView().setVisible(true);
    }



    public void loadTable() {


        ReportModel report;

        for (int i = 1; i < reports.size(); i++) {
            report = reports.elementAt(i);
            Object[] row = new Object[5];
            row[0] = report.getItemName();
            row[1] = report.getUnitsSold();
            row[2] = report.getRevenue();

            businessReportView.addRow(row);
        }

        businessReportView.validate();

    }
}
