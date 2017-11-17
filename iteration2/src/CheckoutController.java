import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CheckoutController implements ActionListener {
    private CheckoutView view;
    private DataAdapter dataAdapter; // to save and load product
    private OrderModel order = null;

    public CheckoutController(CheckoutView view, DataAdapter dataAdapter) {
        this.dataAdapter = dataAdapter;
        this.view = view;

        // TODO: Load Order from Database

        order = new OrderModel();

        view.getPaymentButton().addActionListener(this);
        view.getLoyaltyButton().addActionListener(this);
        view.getManualEntryButton().addActionListener(this);
        view.getCloseButton().addActionListener(this);

    }


    public void actionPerformed(ActionEvent e) {
       if (e.getSource() == view.getPaymentButton()) {
           loadPayment();
       } else if (e.getSource() == view.getLoyaltyButton()) {
           loadLoyaltyProgram();
       }
       else if (e.getSource() == view.getManualEntryButton()) {
           loadManualEntry();
       }
       else if (e.getSource() == view.getCloseButton()) {
           loadClose();
       }
    }

    public void loadPayment() {
        Application.getInstance().getPaymentView().setVisible(true);
    }

    public void loadLoyaltyProgram() {
        JOptionPane.showMessageDialog(null, "Available in Future Versions");

    }

    // TODO: Add Manual Entry Page
    public void loadManualEntry() {
        JOptionPane.showMessageDialog(null, "Available in Future Versions");

    }

    public void loadClose() {
        Application.getInstance().getCheckoutView().setVisible(false);
        //Application.getInstance().getLoginScreenView().setVisible(true);
    }


}