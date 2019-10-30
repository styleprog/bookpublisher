package pl.codinglab.bookpublisher.view.addbook;

import pl.codinglab.bookpublisher.model.Book;
import pl.codinglab.bookpublisher.model.BookRepository;
import pl.codinglab.bookpublisher.scopes.BookDialogScope;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ViewModel;

import javax.inject.Inject;
import java.util.ResourceBundle;

public class AddBookDialogViewModel implements ViewModel {

    static final String CLOSE_DIALOG_NOTIFICATION = "closeDialog";
    private static final String TITLE_LABEL_KEY = "dialog.addbook.title";

    @InjectScope
    private BookDialogScope dialogScope;
    @Inject
    private ResourceBundle defaultResourceBundle;
    @Inject
    private BookRepository repository;

    public void initialize() {
        dialogScope.subscribe(BookDialogScope.OK_BEFORE_COMMIT, (key, payload) -> addBookAction());

        dialogScope.dialogTitleProperty().set(defaultResourceBundle.getString(TITLE_LABEL_KEY));
        dialogScope.publish(BookDialogScope.RESET_FORMS);
        Book book = new Book.BookBuilder().build();
        dialogScope.setBookToEdit(book);
    }

    private void addBookAction() {
        if (dialogScope.isBookFormValid()) {
            dialogScope.publish(BookDialogScope.COMMIT);
            Book book = dialogScope.getBookToEdit();
            repository.save(book);
            dialogScope.publish(BookDialogScope.RESET_DIALOG_PAGE);
            dialogScope.setBookToEdit(null);
            publish(CLOSE_DIALOG_NOTIFICATION);
        }
    }
}
