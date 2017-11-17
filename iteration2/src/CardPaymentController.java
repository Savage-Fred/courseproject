import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardPaymentController implements ActionListener {

    private CardPaymentView cardPaymentView;
    private DataAdapter dataAdapter;

    public CardPaymentController(CardPaymentView view, DataAdapter data ) {
        this.cardPaymentView = view;
        this.dataAdapter = data;

        cardPaymentView.getCloseButton().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cardPaymentView.getCloseButton()) {
            loadClose();
        }
    }

    public void loadClose() {
        Application.getInstance().getCardPaymentView().setVisible(false);
        //Application.getInstance().getLoginScreenView().setVisible(true);
    }
}
