package ba.edu.ibu.library.rest.dto;

import ba.edu.ibu.library.core.model.Book;

import java.util.Date;

public class BookRequestDTO {
    private String isbn;
    private String title;
    private String category;
    private int publicationYear;
    private int numberOfPages;
    private String language;

    public BookRequestDTO() { }

    public BookRequestDTO(Book book) {
        this.isbn = book.getIsbn();
        this.title = book.getTitle();
        this.category = book.getCategory();
        this.publicationYear = book.getPublicationYear();
        this.numberOfPages = book.getNumberOfPages();
        this.language = book.getLanguage();
    }

    public Book toEntity() {
        Book book = new Book();
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setCategory(category);
        book.setPublicationYear(publicationYear);
        book.setNumberOfPages(numberOfPages);
        book.setLanguage(language);
        book.setCreationDate(new Date());
        return book;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
