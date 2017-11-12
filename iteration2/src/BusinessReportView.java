import javax.swing.*;

public class BusinessReportView {
    private JLabel businessReportLabel;

    private JTable currentInventory;

    private JButton sortByButton;
    private JButton closeButton;

    public BusinessReportView() {

    }

    public JButton getSortByButton() {
        return sortByButton;
    }

    public JButton getCloseButton() {
        return closeButton;
    }
}
