package ba.edu.ibu.library.core.model;

import org.assertj.core.api.AssertionsForInterfaceTypes;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.Date;

public class BookTest {

    @Test
    void shouldCreateNewBook() {
        Book book = new Book(
                "someId",
                "9882323",
                "Test Book",
                "Fiction",
                2023,
                200,
                "en",
                new Date()
        );

        Assertions.assertEquals("Test Book", book.getTitle());
        Assertions.assertEquals("Fiction", book.getCategory());
    }

    @Test
    void shouldCompareTwoBooks() {
        Book book1 = new Book(
                "someId",
                "9882323",
                "Test Book",
                "Fiction",
                2023,
                200,
                "en",
                new Date()
        );

        Book book2 = new Book(
                "someId",
                "9882323",
                "Test Book",
                "Fiction",
                2023,
                200,
                "en",
                new Date()
        );

        AssertionsForInterfaceTypes
                .assertThat(book1)
                .usingRecursiveComparison()
                .isEqualTo(book2);
    }
}
