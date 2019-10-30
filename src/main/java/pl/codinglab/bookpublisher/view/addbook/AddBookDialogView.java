package pl.codinglab.bookpublisher.view.addbook;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.stage.Stage;

public class AddBookDialogView implements FxmlView<AddBookDialogViewModel> {

    @InjectViewModel
    private AddBookDialogViewModel viewModel;

    private Stage showDialog;

    public void initialize() {
        viewModel.subscribe(AddBookDialogViewModel.CLOSE_DIALOG_NOTIFICATION, (key, payload) -> showDialog.close());
    }

    public void setDisplayingStage(Stage showDialog) {
        this.showDialog = showDialog;
    }
}
