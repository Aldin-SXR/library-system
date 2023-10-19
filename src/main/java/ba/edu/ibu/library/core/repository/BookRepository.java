package ba.edu.ibu.library.core.repository;

import ba.edu.ibu.library.core.model.Book;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class BookRepository {
    private List<Book> books;

    public BookRepository() {
        this.books = Arrays.asList(

        );
    }

    public List<Book> findAll() {
        return books;
    }

    public Book findById(int id) {
        return books.stream().filter(book -> book.getId() == id).findFirst().orElse(null);
    }
}
