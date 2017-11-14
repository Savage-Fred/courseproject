import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BusinessReportController implements ActionListener {

    private BusinessReportView businessReportView;
    private DataAdapter dataAdapter;

    public BusinessReportController(BusinessReportView view, DataAdapter data) {
        this.businessReportView = view;
        this.dataAdapter = data;

        businessReportView.getSortByButton().addActionListener(this);
        businessReportView.getCloseButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == businessReportView.getSortByButton()) {
            sortByMostUnits();
        } else if (e.getSource() == businessReportView.getCloseButton()) {
            loadClose();
        }

    }

    public void sortByMostUnits() {
        JOptionPane.showMessageDialog(null, "Available in Future Versions");

    }

    public void sortByHighestDollarValue() {
        JOptionPane.showMessageDialog(null, "Available in Future Versions");

    }

    public void sortByLargestProfit() {
        JOptionPane.showMessageDialog(null, "Available in Future Versions");

    }

    public void loadClose() {
        Application.getInstance().getLoginScreenView().setVisible(true);
    }
}
