import javax.swing.*;

public class ReportMenuView extends JFrame {
    private JLabel reportMenuLabel = new JLabel("Report Menu");

    private JButton businessReportButton = new JButton("Business Report");
    private JButton employeeReportButton = new JButton("Employee Report");
    private JButton tpsReportButton = new JButton("TPS Report");
    private JButton closeButton = new JButton("Close");

    public ReportMenuView() {
        this.setTitle("Store Manager");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(500, 200);

        JPanel panelTitle = new JPanel();
        panelTitle.add(reportMenuLabel);
        this.getContentPane().add(panelTitle);

        JPanel panelButton = new JPanel();
        panelButton.add(businessReportButton);
        panelButton.add(employeeReportButton);
        panelButton.add(tpsReportButton);
        panelButton.add(closeButton);
        this.getContentPane().add(panelButton);

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

