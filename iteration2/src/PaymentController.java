import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentController implements ActionListener {

    private PaymentView paymentView;
    private DataAdapter dataAdapter;

    public PaymentController(PaymentView view, DataAdapter data ) {
        this.paymentView = view;
        this.dataAdapter = data;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void loadCardPayment() {

    }

    public void loadCashPayment() {

    }

    public void loadClose() {

    }
}
