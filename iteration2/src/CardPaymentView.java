import javax.swing.*;

public class CardPaymentView extends JFrame {

    private JLabel cardPaymentLabel = new JLabel("Card Payment");
    private JLabel payInstructionLabel = new JLabel("Swipe card to print out receipt");

    private JButton closeButton = new JButton("Close");

    public CardPaymentView() {
        this.setTitle("Store Manager");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(500, 200);

        JPanel panelTitle = new JPanel();
        panelTitle.add(cardPaymentLabel);
        this.getContentPane().add(panelTitle);

        JPanel panelPrompt = new JPanel();
        panelPrompt.add(payInstructionLabel);
        this.getContentPane().add(panelPrompt);

        JPanel panelButton = new JPanel();
        panelButton.add(closeButton);
        this.getContentPane().add(panelButton);
    }

    public JButton getCloseButton() {
        return closeButton;
    }
}
