package ir.hoomanamini;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;

@Service
class ChatbotService {

    private final ChatClient chatClient;
    private final VectorStore vectorStore;
    ChatbotService(ChatClient.Builder chatClientBuilder, ChatMemory chatMemory, VectorStore vectorStore) {
        this.vectorStore = vectorStore;
        this.chatClient = chatClientBuilder
                .defaultAdvisors(new MessageChatMemoryAdvisor(chatMemory))
                .build();
    }

    String chat(String chatId, String message, Map<String, String> systemMessageParams, boolean useDocument) {
        String systemMessage = String.format(
                "You are a %s. Speak in a %s tone.\n%s",
                systemMessageParams.getOrDefault("role", "assistant"),
                systemMessageParams.getOrDefault("tone", "neutral"),
                systemMessageParams.getOrDefault("content", "")
        );

        var promptBuilder = chatClient.prompt()
                .system(systemMessage)
                .user(message)
                .advisors(a -> a.param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId));

        // If document-based chat is enabled, add QuestionAnswerAdvisor
        if (useDocument) {
            promptBuilder.advisors(new QuestionAnswerAdvisor(vectorStore));
        }

        return promptBuilder.call().content();
    }


    ChatResponse chatResponse(String chatId, String message, Map<String, String> systemMessageParams, boolean useDocument) {
        String systemMessage = String.format(
                "You are a %s. Speak in a %s tone.\n%s",
                systemMessageParams.getOrDefault("role", "assistant"),
                systemMessageParams.getOrDefault("tone", "neutral"),
                systemMessageParams.getOrDefault("content", "")
        );

        var promptBuilder = chatClient.prompt()
                .system(systemMessage)
                .user(message)
                .advisors(a -> a.param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId));

        // If document-based chat is enabled, add QuestionAnswerAdvisor
        if (useDocument) {
            promptBuilder.advisors(new QuestionAnswerAdvisor(vectorStore));
        }

        return promptBuilder.call().chatResponse();
    }
}
