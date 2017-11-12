import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchInventoryController implements ActionListener{
    private SearchInventoryView searchInventoryView;
    private DataAdapter dataAdapter;

    public SearchInventoryController(SearchInventoryView view, DataAdapter data) {
        this.searchInventoryView = view;
        this.dataAdapter = data;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void searchItem() {

    }

    public void loadClose() {

    }
}
