package pl.codinglab.bookpublisher.view.toolbar;

import pl.codinglab.bookpublisher.util.DialogUtil;
import pl.codinglab.bookpublisher.view.addbook.AddBookDialogView;
import pl.codinglab.bookpublisher.view.addbook.AddBookDialogViewModel;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.stage.Stage;

import javax.inject.Inject;

public class ToolbarView implements FxmlView<ToolbarViewModel> {

    @InjectViewModel
    private ToolbarViewModel viewModel;

    @Inject
    private Stage primaryStage;

    @FXML
    public void addNewBook() {
        ViewTuple<AddBookDialogView, AddBookDialogViewModel> load = FluentViewLoader
                .fxmlView(AddBookDialogView.class)
                .providedScopes(viewModel.getScopesForAddDialog())
                .load();

        Parent view = load.getView();
        Stage dialog = DialogUtil.showDialog(view, primaryStage);
        load.getCodeBehind().setDisplayingStage(dialog);
    }
}