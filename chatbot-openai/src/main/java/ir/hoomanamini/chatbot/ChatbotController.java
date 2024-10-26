package ir.hoomanamini.chatbot;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/openai")
@CrossOrigin(origins = "*")
class ChatbotController {
    private final ChatbotService chatbotService;

    ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @PostMapping("/chat/{chatId}")
    String chat(@PathVariable String chatId,
                @RequestBody ChatRequest chatRequest,
                @RequestParam(defaultValue = "gpt-4o") String model,
                @RequestParam(defaultValue = "1.0") Double Temperature) {
        return chatbotService.chat(chatId, chatRequest, model, Temperature);
    }
}
