package pl.codinglab.bookpublisher.model;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BookRepository {

    Set<Book> findAll();

    Optional<Book> findById(String id);

    Optional<Book> findByIsbn(String isbn);

    void save(Book book);

    void saveAll(List<Book> books);

    void delete(Book book);
}
