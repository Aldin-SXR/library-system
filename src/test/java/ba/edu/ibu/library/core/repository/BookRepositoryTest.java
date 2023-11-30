package ba.edu.ibu.library.core.repository;

import ba.edu.ibu.library.core.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void shouldReturnAllBooks() {
        List<Book> books = bookRepository.findAll();

        Assertions.assertEquals(1, books.size());
        Assertions.assertEquals("Alice in Wonderland", books.get(0).getTitle());
    }

    @Test
    public void shouldFindBookByTitle() {
        Optional<Book> book = bookRepository.findByTitle("Alice in Wonderland");
        Assertions.assertNotNull(book.orElse(null));
        Assertions.assertEquals("Alice in Wonderland", book.get().getTitle());
    }
}
