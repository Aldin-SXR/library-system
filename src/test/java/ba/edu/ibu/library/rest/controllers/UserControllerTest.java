package ba.edu.ibu.library.rest.controllers;

import ba.edu.ibu.library.core.service.AuthService;
import ba.edu.ibu.library.rest.dto.LoginDTO;
import ba.edu.ibu.library.rest.dto.LoginRequestDTO;
import com.jayway.jsonpath.JsonPath;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {
    static String jwtToken;
    @Autowired
    MockMvc mockMvc;

    @MockBean
    AuthService authService;

    @BeforeEach
    public void init() throws Exception {
        if (jwtToken == null) {
            LoginRequestDTO login = new LoginRequestDTO("test@email.com", "testpass");
            LoginDTO jwt = new LoginDTO("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBbGRpbi1TWFIiLCJpYXQiOjE2OTk1MjA1MTIsImV4cCI6MTY5OTUyMTk1Mn0.v-j332W9Do7YFltar_3NS19xjNTzscs0e4qUXg4yQWQ");

            Mockito.when(authService.signIn(ArgumentMatchers.any(LoginRequestDTO.class))).thenReturn(jwt);

            MvcResult result = mockMvc.perform(
                            MockMvcRequestBuilders
                                    .post("/api/auth/login")
                                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

            System.out.println("###### " + result.getResponse().getContentAsString());

            String response = result.getResponse().getContentAsString();
            jwtToken = JsonPath.read(response, "$.jwt");
        }
    }

    @Test
    void shouldGetAllUsers() throws Exception {

        System.out.println("BEFORE HAS BEEN EXECUTED <--> ".concat(jwtToken));
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/users/")
                .header("Authorization", "Bearer " + jwtToken)).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        System.out.println(result);
    }
}
