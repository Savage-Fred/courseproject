import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CashierMenuController implements ActionListener {

    private CashierMenuView cashierMenuView;
    private DataAdapter dataAdapter;

    public CashierMenuController(CashierMenuView view, DataAdapter data) {
        this.cashierMenuView = view;
        this.dataAdapter = data;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void loadCheckout() {

    }

    public void loadSettings() {

    }

    public void loadClose() {

    }
}
