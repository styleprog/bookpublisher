package pl.codinglab.bookpublisher.view.editbook;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.stage.Stage;

public class EditBookDialogView implements FxmlView<EditBookDialogViewModel> {

    @InjectViewModel
    private EditBookDialogViewModel viewModel;
    private Stage showDialog;

    public void initialize() {
        viewModel.subscribe(EditBookDialogViewModel.CLOSE_DIALOG_NOTIFICATION, (key, payload) ->
                showDialog.close());
    }

    public void setOwningStage(Stage showDialog) {
        this.showDialog = showDialog;
    }
}