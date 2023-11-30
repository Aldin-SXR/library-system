package ba.edu.ibu.library.rest.controllers;

import ba.edu.ibu.library.core.model.Book;
import ba.edu.ibu.library.core.service.BookService;
import ba.edu.ibu.library.core.service.JwtService;
import ba.edu.ibu.library.core.service.UserService;
import ba.edu.ibu.library.rest.configuration.SecurityConfiguration;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@AutoConfigureMockMvc
@WebMvcTest(BookController.class)
@Import(SecurityConfiguration.class)
public class BookControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookService bookService;

    @MockBean
    JwtService jwtService;

    @MockBean
    UserService userService;

    @MockBean
    AuthenticationProvider authenticationProvider;

    @Test
    void shouldReturnAllBooks() throws Exception {

        Book book = new Book();
        book.setTitle("Treasure Island");
        book.setCategory("Adventure");
        book.setPublicationYear(1881);

        Mockito.when(bookService.getBooks()).thenReturn(List.of(book));

        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/books/")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        System.out.println(response);
        Assertions.assertEquals(1, (Integer) JsonPath.read(response, "$.length()"));
        Assertions.assertEquals("Treasure Island", JsonPath.read(response, "$.[0].title"));

    }
}
