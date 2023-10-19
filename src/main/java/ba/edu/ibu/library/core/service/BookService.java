package ba.edu.ibu.library.core.service;

import ba.edu.ibu.library.core.model.Book;
import ba.edu.ibu.library.core.repository.BookRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    /**
     * Dependency injection.
     * We do not need to use "bookRepository = new BookRepository()" anywhere in the code.
     */
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(int id) {
        return bookRepository.findById(id);
    }
}
