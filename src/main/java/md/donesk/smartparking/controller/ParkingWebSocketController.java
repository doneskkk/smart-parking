package md.donesk.smartparking.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class ParkingWebSocketController extends TextWebSocketHandler {

    private LocalDateTime startTime;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        startTime = LocalDateTime.now();

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            try {
                Duration duration = Duration.between(startTime, LocalDateTime.now());
                long minutesParked = duration.toMinutes();

                session.sendMessage(new TextMessage("Parking time: " + minutesParked + " minutes"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, 0, 1, TimeUnit.MINUTES);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String clientMessage = message.getPayload();
        session.sendMessage(new TextMessage("You have just sent: " + clientMessage));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        session.close();
        System.out.println("Connection closed");
    }
}
