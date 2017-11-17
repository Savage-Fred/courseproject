import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportMenuController implements ActionListener {

    private ReportMenuView reportMenuView;
    private DataAdapter dataAdapter;

    public ReportMenuController(ReportMenuView view, DataAdapter data) {
        this.reportMenuView = view;
        this.dataAdapter = data;

        reportMenuView.getBusinessReportButton().addActionListener(this);
        reportMenuView.getEmployeeReportButton().addActionListener(this);
        reportMenuView.getTpsReportButton().addActionListener(this);
        reportMenuView.getCloseButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reportMenuView.getBusinessReportButton()) {
            loadBusinessReport();
        } else if (e.getSource() == reportMenuView.getEmployeeReportButton()) {
            loadEmployeeReport();
        } else if (e.getSource() == reportMenuView.getTpsReportButton()) {
            loadTPSReport();
        } else if (e.getSource() == reportMenuView.getCloseButton()) {
            loadClose();
        }
    }

    public void loadBusinessReport() {

        Application.getInstance().getBusinessReportController().loadOrders();
        Application.getInstance().getBusinessReportView().setVisible(true);
        Application.getInstance().getReportMenuView().setVisible(false);
    }

    public void loadEmployeeReport() {
        JOptionPane.showMessageDialog(null, "Available in Future Versions");

    }

    public void loadTPSReport() {
        JOptionPane.showMessageDialog(null, "Available once Cover Sheet is Updated");

    }

    public void loadClose() {
        Application.getInstance().getReportMenuView().setVisible(false);
        Application.getInstance().getManagerMenuView().setVisible(true);
    }
}
