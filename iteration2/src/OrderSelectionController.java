import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderSelectionController implements ActionListener{
    private OrderSelectionView orderSelectionView;
    private DataAdapter dataAdapter;

    private OrderModel order = null;

    public OrderSelectionController(OrderSelectionView view, DataAdapter data ) {
        this.orderSelectionView = view;
        this.dataAdapter = data;

        orderSelectionView.getNewOrderButton().addActionListener(this);
        orderSelectionView.getLoadOrderButton().addActionListener(this);
        orderSelectionView.getCloseButton().addActionListener(this);

        order = new OrderModel();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == orderSelectionView.getNewOrderButton()) {
            createNewOrder();
        } else if (e.getSource() == orderSelectionView.getLoadOrderButton()) {
            loadPreviousOrder();
        } else if (e.getSource() == orderSelectionView.getCloseButton()) {
            loadClose();
        }

    }


    public void loadPreviousOrder() {

        String id = JOptionPane.showInputDialog("Enter Order ID: ");

        System.out.println(id);
        if (dataAdapter.loadOrder(Integer.parseInt(id)) != null) {
            Application.getInstance().getCheckoutView().clearTable();
            Application.getInstance().getCheckoutController().loadTable(Integer.parseInt(id));
            Application.getInstance().getCheckoutController().setCurrentOrderId(Integer.parseInt(id));
            Application.getInstance().getCheckoutView().setVisible(true);
            Application.getInstance().getOrderSelectionView().setVisible(false);


        } else {
            JOptionPane.showMessageDialog(null, "This order does not exist!");
            return;
        }

    }

    public void createNewOrder() {
        Application.getInstance().getCheckoutView().clearTable();
        Application.getInstance().getCheckoutView().setVisible(true);
        int newOrderId = 0;
        for(int i = 1; dataAdapter.loadOrder(i) != null; i++) {
            newOrderId = i;
        }
        Application.getInstance().getCheckoutController().setCurrentOrderId(newOrderId);
        Application.getInstance().getOrderSelectionView().setVisible(false);
    }


    public void loadClose() {
        Application.getInstance().getOrderSelectionView().setVisible(false);
        Application.getInstance().getCashierMenuView().setVisible(true);
    }


}
