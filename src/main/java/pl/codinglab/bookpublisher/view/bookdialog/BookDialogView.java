package pl.codinglab.bookpublisher.view.bookdialog;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.validation.visualization.ControlsFxVisualizer;
import de.saxsys.mvvmfx.utils.validation.visualization.ValidationVisualizer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class BookDialogView implements FxmlView<BookDialogViewModel> {

    @FXML
    private Button saveBookButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField titleInput;
    @FXML
    private TextField authorInput;
    @FXML
    private TextField genreInput;
    @FXML
    private TextField isbnInput;
    @FXML
    private TextField descriptionInput;
    @FXML
    private TextField statusInput;

    @InjectViewModel
    private BookDialogViewModel viewModel;
    private ValidationVisualizer validationVisualizer = new ControlsFxVisualizer();

    public void initialize() {
        titleInput.textProperty().bindBidirectional(viewModel.titleProperty());
        descriptionInput.textProperty().bindBidirectional(viewModel.descriptionProperty());
        isbnInput.textProperty().bindBidirectional(viewModel.isbnProperty());
        authorInput.textProperty().bindBidirectional(viewModel.authorProperty());
        statusInput.textProperty().bindBidirectional(viewModel.statusProperty());
        genreInput.textProperty().bindBidirectional(viewModel.genreProperty());

        validationVisualizer.initVisualization(viewModel.titleValidator(), titleInput, true);
        validationVisualizer.initVisualization(viewModel.authorValidator(), authorInput, true);
        validationVisualizer.initVisualization(viewModel.genreValidator(), genreInput);
        validationVisualizer.initVisualization(viewModel.statusValidator(), statusInput, true);

        saveBookButton.disableProperty().bind(viewModel.saveBookButtonDisabledProperty());
    }

    @FXML
    public void saveBook() {
        viewModel.saveAction();
    }

    public BookDialogViewModel getViewModel() {
        return viewModel;
    }
}