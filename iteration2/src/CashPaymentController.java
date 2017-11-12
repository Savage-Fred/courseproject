import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CashPaymentController implements ActionListener {

    private CashPaymentView cashPaymentView;
    private DataAdapter dataAdapter;

    public CashPaymentController(CashPaymentView view, DataAdapter data) {
        this.cashPaymentView = view;
        this.dataAdapter = data;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void calculateChange() {

    }

    public void printReciept() {

    }

    public void loadClose() {

    }
}
