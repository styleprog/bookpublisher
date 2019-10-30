package pl.codinglab.bookpublisher.model;

import java.util.ArrayList;
import java.util.List;

public class BookTestData {

    private BookTestData() {
    }

    public static List<Book> createTestBooks() {
        List<Book> books = new ArrayList<>();
        Book firstBook = new Book.BookBuilder()
                .title("The Fellowship of the ring")
                .genre("Fantasy")
                .isbn("22323-234234-23423")
                .author("J.R.R. Tolkien")
                .status(Status.SALE.getStatusName())
                .build();
        Book secondBook = new Book.BookBuilder()
                .title("Two Towers")
                .genre("Fantasy")
                .isbn("11122-32344-667567")
                .author("J.R.R. Tolkien")
                .status(Status.SALE.getStatusName())
                .build();
        books.add(firstBook);
        books.add(secondBook);
        return books;
    }
}
