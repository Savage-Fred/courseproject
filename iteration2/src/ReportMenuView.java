import javax.swing.*;

public class ReportMenuView extends JFrame {
    private JLabel reportMenuLabel;

    private JButton businessReportButton;
    private JButton employeeReportButton;
    private JButton tpsReportButton;
    private JButton closeButton;

    public ReportMenuView() {

    }

    public JButton getBusinessReportButton() {
        return businessReportButton;
    }

    public JButton getEmployeeReportButton() {
        return employeeReportButton;
    }

    public JButton getTpsReportButton() {
        return tpsReportButton;
    }

    public JButton getCloseButton() {
        return closeButton;
    }
}

