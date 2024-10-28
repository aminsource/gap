package ir.hoomanamini.chatbot;

import ir.hoomanamini.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/openai")
@CrossOrigin(origins = "*")
class ChatbotController {

    private final ChatbotService chatbotService;

    public ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @PostMapping("/chat/{chatId}")
    public ResponseEntity<ApiResponse<String>> chat(
            @PathVariable String chatId,
            @RequestBody ChatRequest chatRequest,
            @RequestParam(defaultValue = "gpt-4o") String model,
            @RequestParam(defaultValue = "1.0") Double temperature) {

        String response = chatbotService.chat(chatId, chatRequest, model, temperature);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(response, "پاسخ گفتگو با موفقیت ایجاد شد"));
    }
}
