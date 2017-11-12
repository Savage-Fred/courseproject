import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BusinessReportController implements ActionListener {

    private BusinessReportView businessReportView;
    private DataAdapter dataAdapter;

    public BusinessReportController(BusinessReportView view, DataAdapter data) {
        this.businessReportView = view;
        this.dataAdapter = data;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void sortByMostUnits() {

    }

    public void sortByHighestDollarValue() {

    }

    public void sortByLargestProfit() {

    }

    public void loadClose() {

    }
}
