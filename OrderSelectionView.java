import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class OrderSelectionView extends JFrame{
    private JLabel orderSelectionLabel = new JLabel("Select an Order to Checkout");

    private JButton loadOrderButton = new JButton("Load Order");
    private JButton newOrderButton = new JButton("New Order");
    private JButton closeButton = new JButton("Close");

    public OrderSelectionView() {
        this.setTitle("Store Manager");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(500, 300);

        JPanel panelTitle = new JPanel();
        panelTitle.add(orderSelectionLabel);
        this.getContentPane().add(panelTitle);

        JPanel panelButton = new JPanel();
        panelButton.add(newOrderButton);
        panelButton.add(loadOrderButton);
        panelButton.add(closeButton);
        this.getContentPane().add(panelButton);

    }

    public JButton getLoadOrderButton() {
        return loadOrderButton;
    }

    public JButton getNewOrderButton() {
        return newOrderButton;
    }

    public JButton getCloseButton() {
        return closeButton;
    }

}
