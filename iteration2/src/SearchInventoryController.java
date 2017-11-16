import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchInventoryController implements ActionListener{
    private SearchInventoryView searchInventoryView;
    private DataAdapter dataAdapter;

    public SearchInventoryController(SearchInventoryView view, DataAdapter data) {
        this.searchInventoryView = view;
        this.dataAdapter = data;

        searchInventoryView.getSearchButton().addActionListener(this);
        searchInventoryView.getCloseButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchInventoryView.getSearchButton()) {
            searchItem();
        } else if (e.getSource() == searchInventoryView.getCloseButton()) {
            loadClose();
        }
    }

    public void searchItem() {
        // TODO: Search Database for product and pass to next page
        Application.getInstance().getEditItemInventoryView().setVisible(true);
    }

    public void loadClose() {

        Application.getInstance().getSearchInventoryView().setVisible(false);
        //Application.getInstance().getLoginScreenView().setVisible(true);
    }
}
