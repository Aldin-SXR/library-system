package ba.edu.ibu.library.core.service;

import ba.edu.ibu.library.core.api.mailsender.MailSender;
import ba.edu.ibu.library.core.model.User;
import ba.edu.ibu.library.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

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
    // @Autowired
    // private MailSender mailSender;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String sendEmailToAllUsers(String message) {
        List<User> users = userRepository.findAll();
        // Method 1: Using a specific implementation name
        return mailgunSender.send(users, message);
        // return sendgridSender.send(users, message);

        // Method 2: The appropriate implementation is decided based on configuration
        // return mailSender.send(users, message);
    }
}
