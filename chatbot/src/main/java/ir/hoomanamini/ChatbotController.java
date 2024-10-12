package ir.hoomanamini;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
class ChatbotController {

    private final ChatbotService chatbotService;

    ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @PostMapping("/chat/{chatId}")
    String chat(@PathVariable String chatId,
                @RequestBody ChatRequest chatRequest) {
        return chatbotService.chat(chatId, chatRequest.getMessage(), chatRequest.getSystemMessageParams(), chatRequest.isUseDocument());
    }

    @PostMapping("/chat/response/{chatId}")
    ChatResponse chatResponse(@PathVariable String chatId,
                              @RequestBody ChatRequest chatRequest) {
        return chatbotService.chatResponse(chatId, chatRequest.getMessage(), chatRequest.getSystemMessageParams(), chatRequest.isUseDocument());
    }


}
