package ba.edu.ibu.library.core.service;

import ba.edu.ibu.library.core.exceptions.repository.ResourceNotFoundException;
import ba.edu.ibu.library.core.model.Book;
import ba.edu.ibu.library.core.repository.BookRepository;
import ba.edu.ibu.library.rest.dto.BookRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(String id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            throw new ResourceNotFoundException("The book with the given ID does not exist.");
        }
        return book.get();
    }

    public Book addBook(BookRequestDTO payload) {
        return bookRepository.save(payload.toEntity());
    }

    public Book updateBook(String id, BookRequestDTO payload) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            throw new ResourceNotFoundException("The book with the given ID does not exist.");
        }
        Book updatedBook = payload.toEntity();
        updatedBook.setId(book.get().getId());
        updatedBook = bookRepository.save(updatedBook);
        return updatedBook;
    }

    public void deleteBook(String id) {
        Optional<Book> book = bookRepository.findById(id);
        book.ifPresent(bookRepository::delete);
    }
}
