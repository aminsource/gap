package com.thomasvitale.ai.spring;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Chat examples using the high-level ChatClient API.
 */
@RestController
class ChatbotController {

    private final ChatClient openAichatClient;

    ChatbotController(OpenAiChatModel openAiChatModel) {
        this.openAichatClient = ChatClient.builder(openAiChatModel).build();
    }


    @GetMapping("/chat/openai")
    String chatOpenAi(@RequestParam(defaultValue = "What did Gandalf say to the Balrog?") String question) {
        return openAichatClient.prompt()
                .user(question)
                .call()
                .content();
    }


    @GetMapping("/chat/openai-options")
    String chatWithOpenAiOptions(@RequestParam(defaultValue = "What did Gandalf say to the Balrog?") String question) {
        return openAichatClient.prompt()
                .user(question)
                .options(OpenAiChatOptions.builder()
                        .withModel("gpt-4o-mini")
                        .withTemperature(1.0)
                        .build())
                .call()
                .content();
    }

}
