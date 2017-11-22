import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CashPaymentController implements ActionListener {

    private CashPaymentView cashPaymentView;
    private DataAdapter dataAdapter;

    public int currentOrderId;

    public double total;


    OrderModel order = null;


    public CashPaymentController(CashPaymentView view, DataAdapter data) {
        this.cashPaymentView = view;
        this.dataAdapter = data;

        cashPaymentView.getPrintReceiptButton().addActionListener(this);
        cashPaymentView.getCloseButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cashPaymentView.getPrintReceiptButton()) {
            printReceipt();
        } else if (e.getSource() == cashPaymentView.getCloseButton()) {
            loadClose();
        }

    }


    public void calculateTotal() {
        order = new OrderModel();
        if (dataAdapter.loadOrder(currentOrderId) != null) {
            order = dataAdapter.loadOrder(currentOrderId);

            this.cashPaymentView.getTotalLabel().setText("Total: " + order.getTotalPrice());
            this.total = order.getTotalPrice();
            cashPaymentView.invalidate();
        }


    }


    public void printReceipt() {
        double change;
        double cashGiven = Double.parseDouble(cashPaymentView.getCashGivenField().getText());


        if (cashGiven < total){
            JOptionPane.showMessageDialog(null, "Not enough cash! Please provide more money in order to purchase items!");
            return;
        }

        change = cashGiven - total;

        cashPaymentView.setChangeField(change);

        cashPaymentView.getPrintReceiptButton().setVisible(false);


    }

    public void loadClose() {
        Application.getInstance().getCashPaymentView().setVisible(false);
        Application.getInstance().getCashierMenuView().setVisible(true);

    }

    public void setCurrentOrderId(int currentOrderId) {
        this.currentOrderId = currentOrderId;
    }
}
