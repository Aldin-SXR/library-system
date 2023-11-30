package ba.edu.ibu.library.core.service;

import ba.edu.ibu.library.core.api.mailsender.MailSender;
import ba.edu.ibu.library.core.exceptions.repository.ResourceNotFoundException;
import ba.edu.ibu.library.core.model.Book;
import ba.edu.ibu.library.core.model.User;
import ba.edu.ibu.library.core.repository.BookRepository;
import ba.edu.ibu.library.core.repository.UserRepository;
import ba.edu.ibu.library.rest.dto.UserDTO;
import ba.edu.ibu.library.rest.dto.UserRequestDTO;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.NotActiveException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    /**
     * Method 1: Using @Autowired with implementation names
     */
    @Autowired
    private MailSender mailgunSender;

    @Autowired
    private MailSender sendgridSender;

    /**
     * Method 2: Using @ConditionalOnProperty and application.yml
     */
//    @Autowired
//    private MailSender mailSender;

    public UserService(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public List<UserDTO> getUsers() {
        List<User> users = userRepository.findAll();
        // List<User> users = userRepository.findAllCustom();

        return users
                .stream()
                .map(UserDTO::new)
                .collect(toList());
    }

    public UserDTO getUserById(String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("The user with the given ID does not exist.");
        }
        return new UserDTO(user.get());
    }

    public UserDTO addUser(UserRequestDTO payload) {
        User user = userRepository.save(payload.toEntity());
        return new UserDTO(user);
    }

    public UserDTO updateUser(String id, UserRequestDTO payload) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("The user with the given ID does not exist.");
        }
        User updatedUser = payload.toEntity();
        updatedUser.setId(user.get().getId());
        updatedUser = userRepository.save(updatedUser);
        return new UserDTO(updatedUser);
    }

    public void deleteUser(String id) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(userRepository::delete);
    }

    public UserDTO filterByEmail(String email) {
        Optional<User> user = userRepository.findFirstByEmailLike(email);
        // Optional<User> user = userRepository.findByEmailCustom(email);
        return user.map(UserDTO::new).orElse(null);
    }

    public List<Book> getBooksByUser(String userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("The user with the given ID does not exist.");
        }
        List<String> borrowedBookIds = user.get().getBorrowedBooks();
        List<Book> books = new ArrayList<>();
        for (String id: borrowedBookIds) {
            Optional<Book> book = bookRepository.findById(id);
            book.ifPresent(books::add);
        }
        return books;
    }

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByUsernameOrEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }
}
