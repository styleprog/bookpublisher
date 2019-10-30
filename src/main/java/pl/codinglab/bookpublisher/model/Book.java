package pl.codinglab.bookpublisher.model;

import java.util.UUID;

public class Book {

    private String id;
    private String title;
    private String description;
    private String isbn;
    private String status;
    private String genre;
    private String author;

    public Book(BookBuilder b) {
        this.id = UUID.randomUUID().toString();
        this.title = b.title;
        this.description = b.description;
        this.isbn = b.isbn;
        this.status = b.status;
        this.genre = b.genre;
        this.author = b.author;
    }

    public static class BookBuilder {
        private String title;
        private String description;
        private String isbn;
        private String status;
        private String genre;
        private String author;

        public BookBuilder title(String title) {
            this.title = title;
            return this;
        }

        public BookBuilder description(String description) {
            this.description = description;
            return this;
        }

        public BookBuilder isbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public BookBuilder status(String status) {
            this.status = status;
            return this;
        }

        public BookBuilder genre(String genre) {
            this.genre = genre;
            return this;
        }

        public BookBuilder author(String author) {
            this.author = author;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }

    public String getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
