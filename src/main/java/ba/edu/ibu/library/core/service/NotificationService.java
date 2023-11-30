package ba.edu.ibu.library.core.service;

import ba.edu.ibu.library.rest.websockets.MainSocketHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class NotificationService {
    private final MainSocketHandler mainSocketHandler;

    public NotificationService(MainSocketHandler mainSocketHandler) {
        this.mainSocketHandler = mainSocketHandler;
    }

    public void broadcastMessage(String message) throws IOException {
        mainSocketHandler.broadcastMessage(message);
    }

    public void sendMessage(String userId, String message) {
        mainSocketHandler.sendMessage(userId, message);
    }
}