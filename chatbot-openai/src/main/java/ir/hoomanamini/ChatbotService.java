package ir.hoomanamini;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;

@Service
public class ChatbotService {
    private final ChatClient openAichatClient;

    ChatbotService(OpenAiChatModel openAiChatModel) {
        this.openAichatClient = ChatClient.builder(openAiChatModel).build();
    }
    String chatOpenAi(String question, String chatId) {
        return openAichatClient.prompt()
                .user(question)
                .advisors(a -> a.param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId))
                .call()
                .content();
    }

    String chatWithOpenAiOptions(String question, String model, Double Temperature, String chatId) {
        return openAichatClient.prompt()
                .user(question)
                .advisors(a -> a.param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId))
                .options(OpenAiChatOptions.builder()
                        .withModel(model)
                        .withTemperature(Temperature)
                        .build())
                .call()
                .content();
    }


}
