package pl.codinglab.bookpublisher.view.bookdialog;

import pl.codinglab.bookpublisher.model.Book;
import pl.codinglab.bookpublisher.scopes.BookDialogScope;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.mapping.ModelWrapper;
import de.saxsys.mvvmfx.utils.validation.*;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableBooleanValue;

public class BookDialogViewModel implements ViewModel {

    private final ModelWrapper<Book> bookWrapper = new ModelWrapper<>();
    private final CompositeValidator formValidator = new CompositeValidator();
    private final ReadOnlyBooleanWrapper valid = new ReadOnlyBooleanWrapper();

    private Validator titleValidator;
    private Validator authorValidator;
    private Validator genreValidator;
    private Validator statusValidator;

    @InjectScope
    private BookDialogScope dialogScope;

    public BookDialogViewModel() {
        titleValidator = new FunctionBasedValidator<>(
                titleProperty(),
                title -> {
                    if (title == null || title.isEmpty()) {
                        return ValidationMessage.error("Title may not be empty");
                    } else if (title.trim().isEmpty()) {
                        return ValidationMessage.error("Title may not only contain whitespaces");
                    }
                    return null;
                });

        authorValidator = new FunctionBasedValidator<>(
                authorProperty(),
                author -> {
                    if (author == null || author.isEmpty()) {
                        return ValidationMessage.error("Author name may not be empty");
                    } else if (author.trim().isEmpty()) {
                        return ValidationMessage.error("Author name may not only contain whitespaces");
                    }
                    return null;
                });

        genreValidator = new FunctionBasedValidator<>(
                genreProperty(),
                genre -> {
                    if (genre == null || genre.isEmpty()) {
                        return ValidationMessage.error("Genre may not be empty");
                    } else if (genre.trim().isEmpty()) {
                        return ValidationMessage.error("Genre may not only contain whitespaces");
                    }
                    return null;
                });

        statusValidator = new FunctionBasedValidator<>(
                statusProperty(),
                status -> {
                    if (status == null || status.isEmpty()) {
                        return ValidationMessage.error("Status may not be empty");
                    } else if (status.trim().isEmpty()) {
                        return ValidationMessage.error("Status may not only contain whitespaces");
                    }
                    return null;
                });

        formValidator.addValidators(
                titleValidator,
                authorValidator,
                genreValidator,
                statusValidator);
    }

    public void initialize() {
        valid.bind(dialogScope.bookFormValidProperty());
        dialogScope.subscribe(BookDialogScope.RESET_FORMS, (key, payload) -> resetForm());
        dialogScope.subscribe(BookDialogScope.COMMIT, (key, payload) -> commitChanges());

        ObjectProperty<Book> bookToEditProperty = dialogScope.bookToEditProperty();
        if (bookToEditProperty.get() != null) {
            initWithBook(bookToEditProperty.get());
        }

        bookToEditProperty.addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                initWithBook(newValue);
            }
        });
        dialogScope.bookFormValidProperty().bind(formValidator.getValidationStatus().validProperty());
    }

    private void resetForm() {
        bookWrapper.reset();
    }

    private void initWithBook(Book book) {
        this.bookWrapper.set(book);
        this.bookWrapper.reload();
    }

    private void commitChanges() {
        if (bookWrapper.get() == null) {
            bookWrapper.set(new Book.BookBuilder().build());
        }
        bookWrapper.commit();
    }

    public void saveAction() {
        dialogScope.publish(BookDialogScope.OK_BEFORE_COMMIT);
    }


    public StringProperty titleProperty() {
        return bookWrapper.field("title", Book::getTitle, Book::setTitle);
    }

    public StringProperty descriptionProperty() {
        return bookWrapper.field("description", Book::getDescription, Book::setDescription);
    }

    public StringProperty isbnProperty() {
        return bookWrapper.field("isbn", Book::getIsbn, Book::setIsbn);
    }

    public StringProperty authorProperty() {
        return bookWrapper.field("author", Book::getAuthor, Book::setAuthor);
    }

    public StringProperty statusProperty() {
        return bookWrapper.field("status", Book::getStatus, Book::setStatus);
    }

    public StringProperty genreProperty() {
        return bookWrapper.field("genre", Book::getGenre, Book::setGenre);
    }

    public ValidationStatus titleValidator() {
        return titleValidator.getValidationStatus();
    }

    public ValidationStatus authorValidator() {
        return authorValidator.getValidationStatus();
    }

    public ValidationStatus genreValidator() {
        return genreValidator.getValidationStatus();
    }

    public ValidationStatus statusValidator() {
        return statusValidator.getValidationStatus();
    }

    public ObservableBooleanValue saveBookButtonDisabledProperty() {
        return valid.not();
    }
}