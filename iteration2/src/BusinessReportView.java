import javax.swing.*;

public class BusinessReportView extends JFrame {
    private JLabel businessReportLabel;

    private JTable currentInventory;

    private JButton sortByButton;
    private JButton closeButton;

    public BusinessReportView() {
        this.setTitle("Store Manager");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(500, 200);

    }

    public JButton getSortByButton() {
        return sortByButton;
    }

    public JButton getCloseButton() {
        return closeButton;
    }
}
