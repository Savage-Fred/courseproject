import javax.swing.*;

public class BusinessReportView extends JFrame {
    private JLabel businessReportLabel = new JLabel("Business Reports");

    // TODO: Add method to add current inventory to JTable
    private JTable currentInventory;

    private JButton sortByButton = new JButton("Sort By");
    private JButton closeButton = new JButton("Close");

    public BusinessReportView() {
        this.setTitle("Store Manager");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(500, 200);

        JPanel panelButton = new JPanel();
        panelButton.add(sortByButton);
        panelButton.add(closeButton);
        this.getContentPane().add(panelButton);

    }

    public JButton getSortByButton() {
        return sortByButton;
    }

    public JButton getCloseButton() {
        return closeButton;
    }
}
