package pl.codinglab.bookpublisher.view.master;

import pl.codinglab.bookpublisher.model.Book;

public class MasterTableViewModel {

    private Book book;

    public MasterTableViewModel(Book book) {
        this.book = book;
    }

    public String getIsbn() {
        return book.getIsbn();
    }

    public String getTitle() {
        return book.getTitle();
    }

    public String getDescription() {
        return book.getDescription();
    }

    public String getGenre() {
        return book.getGenre();
    }

    public String getAuthor() {
        return book.getAuthor();
    }

    public String getId() {
        return book.getId();
    }

    public String getStatus() {
        return book.getStatus();
    }


}
