package pl.codinglab.bookpublisher.model;

import pl.codinglab.bookpublisher.events.BooksUpdatedEvent;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.*;

@Singleton
public class InmemoryBookRepository implements BookRepository {

    private final Set<Book> books = new HashSet<>();

    @Inject
    private Event<BooksUpdatedEvent> booksUpdatedEvent;

    @Override
    public Set<Book> findAll() {
        return Collections.unmodifiableSet(books);
    }

    @Override
    public Optional<Book> findById(String id) {
        return books
                .stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return books
                .stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst();
    }

    @Override
    public void save(Book book) {
        books.add(book);
        fireUpdateEvent();
    }

    @Override
    public void saveAll(List<Book> books) {
        this.books.addAll(books);
    }

    @Override
    public void delete(Book book) {
        books.remove(book);
        fireUpdateEvent();
    }

    private void fireUpdateEvent() {
        if (booksUpdatedEvent != null) {
            booksUpdatedEvent.fire(new BooksUpdatedEvent());
        }
    }
}
