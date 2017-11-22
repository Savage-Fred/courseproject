import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BusinessReportView extends JFrame {
    private JLabel businessReportLabel = new JLabel("Business Reports");

    private DefaultTableModel items = new DefaultTableModel();

    private JTable currentInventory = new JTable(items);

    private JButton sortByUnitsButton = new JButton("Sort By Units Sold");
    private JButton sortByRevenueButton = new JButton("Sort By Revenue");

    private JButton closeButton = new JButton("Close");

    public BusinessReportView() {
        this.setTitle("Store Manager");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(500, 200);

        items.addColumn("Item Name");
        items.addColumn("Units Sold");
        items.addColumn("Revenue");

        JPanel panelOrder = new JPanel();
        panelOrder.setPreferredSize(new Dimension(400, 450));
        panelOrder.setLayout(new BoxLayout(panelOrder, BoxLayout.PAGE_AXIS));
        currentInventory.setBounds(0, 0, 400, 350);
        panelOrder.add(currentInventory.getTableHeader());
        panelOrder.add(currentInventory);
        currentInventory.setFillsViewportHeight(true);
        this.getContentPane().add(panelOrder);

        JPanel panelButton = new JPanel();
        panelButton.add(sortByUnitsButton);
        panelButton.add(sortByRevenueButton);
        panelButton.add(closeButton);
        this.getContentPane().add(panelButton);

    }

    public JButton getSortByUnitsButton() {
        return sortByUnitsButton;
    }

    public JButton getSortByRevenueButton() {
        return sortByRevenueButton;
    }

    public JButton getCloseButton() {
        return closeButton;
    }

    public void addRow(Object[] row) {
        items.addRow(row);              // add a row to list of item!
        items.fireTableDataChanged();
    }

    public void clearTable(){
        items.setRowCount(0);
    }

}
