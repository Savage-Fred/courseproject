import javax.swing.*;

public class CardPaymentView extends JFrame {

    private JLabel cardPaymentLabel;
    private JLabel payInstructionLabel;

    private JButton closeButton;

    public CardPaymentView() {
        this.setTitle("Store Manager");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(500, 200);

    }

    public JButton getCloseButton() {
        return closeButton;
    }
}
