package help_desk_backend.controller;

import help_desk_backend.service.AiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/helpdesk")
@CrossOrigin("http://localhost:5173")
public class AiController {

    @Autowired
    private final AiService service;

    public AiController(AiService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> getResponse(@RequestBody String query, @RequestHeader("ConversationId") String conversationId) {
        return ResponseEntity.ok(service.getResponseFromAssistant(query, conversationId));
    }

    @PostMapping(value = "/stream")
    public Flux<String> streamResponse(@RequestBody String query, @RequestHeader("ConversationId") String conversationId) {
        return this.service.streamResponseFromAssistant(query, conversationId);
    }


}
