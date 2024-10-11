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
//     Chat with llama3
    @PostMapping("/chat/{chatId}")
    String chat(@PathVariable String chatId, @RequestBody String input) {
        return chatbotService.chat(chatId, input);
    }
    @PostMapping("/chat/response/{chatId}")
    ChatResponse chatResponse(@PathVariable String chatId, @RequestBody String input) {
        return chatbotService.chatResponse(chatId, input);
    }
// chat with open Ai
    @PostMapping("/chat/openai/{chatId}")
    String chatOpenAi(@PathVariable String chatId, @RequestBody String question) {
        return chatbotService.chatOpenAi(question, chatId);
    }

    @PostMapping("/chat/openai-options/{chatId}")
    String chatWithOpenAiOptions(@PathVariable String chatId,
                                 @RequestBody String question,
                                 @RequestParam(defaultValue = "gpt-4o") String model,
                                 @RequestParam(defaultValue = "1.0") Double Temperature) {
        return chatbotService.chatWithOpenAiOptions(question, model, Temperature, chatId);
    }
// FAQ
    @GetMapping("/suniar/faq")
    String faq(@RequestParam String message) throws IOException {
        return chatbotService.faq(message);
    }
    @PostMapping("/chat/doc")
    String chatWithDocument(@RequestBody String input) {
        return chatbotService.chatWithDocument(input);
    }


}
