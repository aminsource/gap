package ir.hoomanamini;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
class ChatbotController {

    private final ChatbotService chatbotService;

    ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @PostMapping("/chat/{chatId}")
    String chat(@PathVariable String chatId, @RequestBody String input) {
        return chatbotService.chat(chatId, input);
    }
    @PostMapping("/chat/response/{chatId}")
    ChatResponse chatResponse(@PathVariable String chatId, @RequestBody String input) {
        return chatbotService.chatResponse(chatId, input);
    }
    @GetMapping("/suniar/faq")
    String faq(@RequestParam String message) throws IOException {
        return chatbotService.faq(message);
    }


}
