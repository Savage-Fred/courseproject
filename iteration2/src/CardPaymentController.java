import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardPaymentController implements ActionListener {

    private CardPaymentView cardPaymentView;
    private DataAdapter dataAdapter;

    public CardPaymentController(CardPaymentView view, DataAdapter data ) {
        this.cardPaymentView = view;
        this.dataAdapter = data;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void loadClose() {

    }
}
