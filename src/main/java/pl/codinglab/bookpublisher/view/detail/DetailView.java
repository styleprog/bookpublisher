package pl.codinglab.bookpublisher.view.detail;

import pl.codinglab.bookpublisher.util.DialogUtil;
import pl.codinglab.bookpublisher.view.editbook.EditBookDialogView;
import pl.codinglab.bookpublisher.view.editbook.EditBookDialogViewModel;
import de.saxsys.mvvmfx.*;
import de.saxsys.mvvmfx.utils.commands.Command;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.inject.Inject;

public class DetailView implements FxmlView<DetailViewModel> {

    @InjectViewModel
    private DetailViewModel viewModel;

    @FXML
    private TextField author;
    @FXML
    private TextField title;
    @FXML
    private TextField description;
    @FXML
    private TextField status;
    @FXML
    private TextField isbn;
    @FXML
    private TextField genre;
    @FXML
    private Button editButton;
    @Inject
    private Stage primaryStage;
    @InjectContext
    private Context context;

    private Command editCommand;

    public void initialize() {
        author.textProperty().bind(viewModel.authorTextProperty());
        title.textProperty().bind(viewModel.titleProperty());
        description.textProperty().bind(viewModel.descriptionProperty());
        status.textProperty().bind(viewModel.statusProperty());
        isbn.textProperty().bind(viewModel.isbnProperty());
        genre.textProperty().bind(viewModel.genreProperty());

        editCommand = viewModel.getEditCommand();
        editButton.disableProperty().bind(editCommand.notExecutableProperty());

        viewModel.subscribe(DetailViewModel.OPEN_EDIT_BOOK_DIALOG, (key, payload) -> {
            ViewTuple<EditBookDialogView, EditBookDialogViewModel> load = FluentViewLoader
                    .fxmlView(EditBookDialogView.class)
                    .context(context)
                    .load();
            Parent view = load.getView();
            Stage showDialog = DialogUtil.showDialog(view, primaryStage);
            load.getCodeBehind().setOwningStage(showDialog);
        });

    }

    @FXML
    public void editAction() {
        editCommand.execute();
    }
}