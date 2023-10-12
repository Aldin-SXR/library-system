package ba.edu.ibu.library.core.api.mailsender;

import ba.edu.ibu.library.core.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MailSender {

    public String send(List<User> users, String message);
}
