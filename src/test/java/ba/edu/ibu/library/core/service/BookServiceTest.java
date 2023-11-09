package ba.edu.ibu.library.core.service;

import ba.edu.ibu.library.core.model.Book;
import ba.edu.ibu.library.core.repository.BookRepository;
import ba.edu.ibu.library.rest.dto.BookRequestDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.swing.text.html.Option;
import java.util.Optional;

@AutoConfigureMockMvc
@SpringBootTest
public class BookServiceTest {

    @MockBean
    BookRepository bookRepository;

    @Autowired
    BookService bookService;

    @Test
    public void shouldReturnBookWhenCreated() {
        Book book = new Book();
        book.setTitle("Treasure Island");
        book.setCategory("Adventure");
        book.setPublicationYear(1881);

        Mockito.when(bookRepository.save(ArgumentMatchers.any(Book.class))).thenReturn(book);

        Book savedBook = bookService.addBook(new BookRequestDTO(book));
        Assertions.assertThat(book.getTitle()).isEqualTo(savedBook.getTitle());
        Assertions.assertThat(book.getCategory()).isNotNull();
        System.out.println(savedBook.getId());
    }

    @Test
    public void shouldReturnBookById() {
        Book book = new Book();
        book.setId("someMongoId");
        book.setTitle("Treasure Island");
        book.setCategory("Adventure");
        book.setPublicationYear(1881);

        Mockito.when(bookRepository.findById("someMongoId")).thenReturn(Optional.of(book));

        Book foundBook = bookService.getBookById("someMongoId");
        Assertions.assertThat(foundBook.getTitle()).isEqualTo("Treasure Island");
    }

}
