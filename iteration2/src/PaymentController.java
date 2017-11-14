import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentController implements ActionListener {

    private PaymentView paymentView;
    private DataAdapter dataAdapter;

    public PaymentController(PaymentView view, DataAdapter data ) {
        this.paymentView = view;
        this.dataAdapter = data;

        paymentView.getCashPaymentButton().addActionListener(this);
        paymentView.getCardPaymentButton().addActionListener(this);
        paymentView.getCloseButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == paymentView.getCardPaymentButton()) {
            loadCardPayment();
        } else if (e.getSource() == paymentView.getCashPaymentButton()) {
            loadCashPayment();
        }
        else if (e.getSource() == paymentView.getCloseButton())
        {
            loadClose();
        }

    }

    public void loadCardPayment() {
        Application.getInstance().getCardPaymentView().setVisible(true);
    }

    public void loadCashPayment() {
        Application.getInstance().getCashPaymentView().setVisible(true);

    }

    public void loadClose() {
        Application.getInstance().getCheckoutView().setVisible(true);
    }
}
