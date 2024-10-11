package ir.hoomanamini;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/openai")
class ChatbotController {
    private final ChatbotService chatbotService;

    ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }


    // chat with open Ai
    @PostMapping("/chat/{chatId}")
    String chatOpenAi(@PathVariable String chatId, @RequestBody String question) {
        return chatbotService.chatOpenAi(question, chatId);
    }

    @PostMapping("/chat/options/{chatId}")
    String chatWithOpenAiOptions(@PathVariable String chatId,
                                 @RequestBody String question,
                                 @RequestParam(defaultValue = "gpt-4o") String model,
                                 @RequestParam(defaultValue = "1.0") Double Temperature) {
        return chatbotService.chatWithOpenAiOptions(question, model, Temperature, chatId);
    }

}
