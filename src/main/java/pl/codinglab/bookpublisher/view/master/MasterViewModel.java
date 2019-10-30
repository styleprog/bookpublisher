package pl.codinglab.bookpublisher.view.master;

import pl.codinglab.bookpublisher.events.BooksUpdatedEvent;
import pl.codinglab.bookpublisher.model.Book;
import pl.codinglab.bookpublisher.model.BookRepository;
import pl.codinglab.bookpublisher.scopes.MasterDetailScope;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

@Singleton
public class MasterViewModel implements ViewModel {

    private static final Logger LOG = LoggerFactory.getLogger(MasterViewModel.class);

    private final ObservableList<MasterTableViewModel> books = FXCollections.observableArrayList();

    private final ReadOnlyObjectWrapper<Book> selectedBook = new ReadOnlyObjectWrapper<>();

    private final ObjectProperty<MasterTableViewModel> selectedTableRow = new SimpleObjectProperty<>();

    private Consumer<MasterTableViewModel> onSelect;

    @InjectScope
    private MasterDetailScope scope;

    @Inject
    private BookRepository bookRepository;

    public void initialize() {
        updateBooksList();
        scope.selectedElementProperty().bind(selectedBook);
        selectedBook.bind(Bindings.createObjectBinding(() -> {
            if (selectedTableRow.get() == null) {
                return null;
            } else {
                return bookRepository.findById(selectedTableRow.get().getId()).orElse(null);
            }
        }, selectedTableRow));
    }

    public void onBooksUpdateEvent(@Observes BooksUpdatedEvent event) {
        updateBooksList();
    }

    private void updateBooksList() {
        LOG.debug("update book list");

        final String selectedBookId = selectedTableRow.get() == null ? null : selectedTableRow.get().getId();
        Set<Book> allBooks = bookRepository.findAll();
        books.clear();
        allBooks.forEach(book -> books.add(new MasterTableViewModel(book)));
        if (selectedBookId != null) {
            Optional<MasterTableViewModel> selectedRow = books
                    .stream()
                    .filter(row -> row.getId().equals(selectedBookId))
                    .findFirst();

            Optional.of(onSelect).ifPresent(consumer -> consumer.accept(selectedRow.orElse(null)));
        }
    }

    public ObservableList<MasterTableViewModel> getBookList() {
        return books;
    }

    public void setOnSelect(Consumer<MasterTableViewModel> consumer) {
        onSelect = consumer;
    }

    public ObjectProperty<MasterTableViewModel> selectedTableRowProperty() {
        return selectedTableRow;
    }
}