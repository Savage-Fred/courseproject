import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CashPaymentController implements ActionListener {

    private CashPaymentView cashPaymentView;
    private DataAdapter dataAdapter;

    public CashPaymentController(CashPaymentView view, DataAdapter data) {
        this.cashPaymentView = view;
        this.dataAdapter = data;

        cashPaymentView.getPrintReceiptButton().addActionListener(this);
        cashPaymentView.getCloseButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cashPaymentView.getPrintReceiptButton()) {
            printReciept();
        } else if (e.getSource() == cashPaymentView.getCloseButton()) {
            loadClose();
        }

    }


    public void calculateChange() {
        // TODO: Check for valid user inputs
        // TODO: Calculate Change
    }

    public void printReciept() {
        // TODO: Add Message Dialog Box with printed receipt
        // TODO: Save Order to Database

        Application.getInstance().getCashierMenuView().setVisible(true);

    }

    public void loadClose() {
        Application.getInstance().getCashPaymentView().setVisible(false);
        Application.getInstance().getCashierMenuView().setVisible(true);

    }
}
