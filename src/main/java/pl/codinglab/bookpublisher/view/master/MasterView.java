package pl.codinglab.bookpublisher.view.master;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class MasterView implements FxmlView<MasterViewModel> {

    @FXML
    private TableView<MasterTableViewModel> booksTable;

    @InjectViewModel
    private MasterViewModel viewModel;

    public void initialize() {
        booksTable.setItems(viewModel.getBookList());
        viewModel.selectedTableRowProperty().bind(booksTable.getSelectionModel().selectedItemProperty());
        viewModel.setOnSelect(vm -> booksTable.getSelectionModel().select(vm));
    }
}