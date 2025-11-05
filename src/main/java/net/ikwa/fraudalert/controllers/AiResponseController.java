package net.ikwa.fraudalert.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AiResponseController {

    private final ChatClient chatClient;

    // Constructor injection

    public AiResponseController(ChatClient.Builder chatClientBuilder) {
        // Use the builder to create the ChatClient instance
        this.chatClient = chatClientBuilder.build();
    }

    @PostMapping("/chats")
    public ResponseEntity<String> chatUser(@RequestBody String message) {
        try {
            // Send message to AI and get response
            String response = chatClient.prompt(message).call().content();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Log the error (so you know what went wrong)
            e.printStackTrace();

            // Return a friendly error message to the client
            return ResponseEntity.status(500)
                    .body("Sorry, something went wrong: " + e.getMessage());
        }
    }
}
