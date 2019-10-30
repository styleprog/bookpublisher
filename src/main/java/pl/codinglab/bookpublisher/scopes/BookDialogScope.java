package pl.codinglab.bookpublisher.scopes;

import pl.codinglab.bookpublisher.model.Book;
import de.saxsys.mvvmfx.Scope;
import javafx.beans.property.*;

public class BookDialogScope implements Scope {

    public static final String RESET_DIALOG_PAGE = "book_reset_dialog_page";
    public static final String OK_BEFORE_COMMIT = "book_ok_before_commit";
    public static final String COMMIT = "book_commit";
    public static final String RESET_FORMS = "book_reset";

    private final ObjectProperty<Book> bookToEdit = new SimpleObjectProperty<>(this, "bookToEdit");

    private final BooleanProperty bookFormValid = new SimpleBooleanProperty();
    private final StringProperty dialogTitle = new SimpleStringProperty();

    public BooleanProperty bookFormValidProperty() {
        return this.bookFormValid;
    }

    public boolean isBookFormValid() {
        return this.bookFormValidProperty().get();
    }

    public ObjectProperty<Book> bookToEditProperty() {
        return this.bookToEdit;
    }

    public Book getBookToEdit() {
        return this.bookToEditProperty().get();
    }

    public void setBookToEdit(final Book bookToEdit) {
        this.bookToEditProperty().set(bookToEdit);
    }

    public final StringProperty dialogTitleProperty() {
        return this.dialogTitle;
    }

    public final java.lang.String getDialogTitle() {
        return this.dialogTitleProperty().get();
    }

    public final void setDialogTitle(final java.lang.String dialogTitle) {
        this.dialogTitleProperty().set(dialogTitle);
    }
}
