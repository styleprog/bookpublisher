package pl.codinglab.bookpublisher.view.detail;

import pl.codinglab.bookpublisher.model.Book;
import pl.codinglab.bookpublisher.scopes.BookDialogScope;
import pl.codinglab.bookpublisher.scopes.MasterDetailScope;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ScopeProvider;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.commands.Action;
import de.saxsys.mvvmfx.utils.commands.Command;
import de.saxsys.mvvmfx.utils.commands.DelegateCommand;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;

import static eu.lestard.advanced_bindings.api.ObjectBindings.map;

@ScopeProvider(scopes = BookDialogScope.class)
public class DetailViewModel implements ViewModel {

    public static final String OPEN_EDIT_BOOK_DIALOG = "open_edit_book";

    @InjectScope
    private MasterDetailScope scope;
    @InjectScope
    private BookDialogScope bookScope;

    private DelegateCommand editCommand;

    private final ReadOnlyStringWrapper title = new ReadOnlyStringWrapper();
    private final ReadOnlyStringWrapper author = new ReadOnlyStringWrapper();
    private final ReadOnlyStringWrapper genre = new ReadOnlyStringWrapper();
    private final ReadOnlyStringWrapper description = new ReadOnlyStringWrapper();
    private final ReadOnlyStringWrapper isbn = new ReadOnlyStringWrapper();
    private final ReadOnlyStringWrapper status = new ReadOnlyStringWrapper();

    public void initialize() {
        ReadOnlyObjectProperty<Book> bookProperty = getSelectedBookPropertyFromScope();
        createBindingsForTextFields(bookProperty);
    }

    private void createBindingsForTextFields(ReadOnlyObjectProperty<Book> bookProperty) {
        title.bind(emptyStringOnNull(map(bookProperty, Book::getTitle)));
        author.bind(emptyStringOnNull(map(bookProperty, Book::getAuthor)));
        genre.bind(emptyStringOnNull(map(bookProperty, Book::getGenre)));
        description.bind(emptyStringOnNull(map(bookProperty, Book::getDescription)));
        isbn.bind(emptyStringOnNull(map(bookProperty, Book::getIsbn)));
        status.bind(emptyStringOnNull(map(bookProperty, Book::getStatus)));

        editCommand = new DelegateCommand(() -> new Action() {
            @Override
            protected void action() throws Exception {
                Book selectedBook = getSelectedBookFromScope();
                if (selectedBook != null) {
                    bookScope.setBookToEdit(selectedBook);
                    publish(OPEN_EDIT_BOOK_DIALOG);
                }
            }
        }, getSelectedBookPropertyFromScope().isNotNull());
    }

    private StringBinding emptyStringOnNull(ObservableValue<String> source) {
        return Bindings.createStringBinding(() -> {
            if (source.getValue() == null) {
                return "";
            } else {
                return source.getValue();
            }
        }, source);
    }

    private Book getSelectedBookFromScope() {
        return getSelectedBookPropertyFromScope().get();
    }

    private ObjectProperty<Book> getSelectedBookPropertyFromScope() {
        return scope.selectedElementProperty();
    }

    public ReadOnlyStringProperty authorTextProperty() {
        return author.getReadOnlyProperty();
    }

    public ReadOnlyStringProperty titleProperty() {
        return title.getReadOnlyProperty();
    }

    public ReadOnlyStringProperty genreProperty() {
        return genre.getReadOnlyProperty();
    }

    public ReadOnlyStringProperty descriptionProperty() {
        return description.getReadOnlyProperty();
    }

    public ReadOnlyStringProperty isbnProperty() {
        return isbn.getReadOnlyProperty();
    }

    public ReadOnlyStringProperty statusProperty() {
        return status.getReadOnlyProperty();
    }

    public Command getEditCommand() {
        return editCommand;
    }
}