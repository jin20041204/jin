package com.jin.controller;

import com.jin.dto.ChatRequest;
import com.jin.dto.ChatResponse;
import com.jin.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ResponseEntity<?> chat(@RequestBody ChatRequest req) {
        if (req.getMessage() == null || req.getMessage().isBlank()) {
            return ResponseEntity.badRequest().body("message is required");
        }
        ChatResponse resp = chatService.handleChat(req);
        return ResponseEntity.ok(resp);
    }
}
