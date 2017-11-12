import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportMenuController implements ActionListener {

    private ReportMenuView reportMenuView;
    private DataAdapter dataAdapter;

    public ReportMenuController(ReportMenuView view, DataAdapter data) {
        this.reportMenuView = view;
        this.dataAdapter = data;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void loadBusinessReport() {

    }

    public void loadEmployeeReport() {

    }

    public void loadTPSReport() {

    }

    public void loadClose() {

    }
}
