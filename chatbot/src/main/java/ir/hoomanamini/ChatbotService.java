package ir.hoomanamini;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

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

    String chat(String chatId, ChatRequest chatRequest) {
        var systemMessageTemplate = """
        You are a {role}. Speak in a {tone} tone.
        {content}
    """;

        var promptBuilder = chatClient.prompt()
                .system(systemSpec -> systemSpec
                        .text(systemMessageTemplate)
                        .param("role", chatRequest.systemMessageParams().getOrDefault("role", "assistant"))
                        .param("tone", chatRequest.systemMessageParams().getOrDefault("tone", "neutral"))
                        .param("content", chatRequest.systemMessageParams().getOrDefault("content", ""))
                )
                .user(chatRequest.message())
                .advisors(a -> a.param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId));

        // Conditionally add the QuestionAnswerAdvisor if useDocument is true
        if (chatRequest.useDocument()) {
            promptBuilder = promptBuilder.advisors(new QuestionAnswerAdvisor(vectorStore));
        }

        return promptBuilder.call().content();
    }

    ChatResponse chatResponse(String chatId, ChatRequest chatRequest) {
        var systemMessageTemplate = """
        You are a {role}. Speak in a {tone} tone.
        {content}
    """;

        var promptBuilder = chatClient.prompt()
                .system(systemSpec -> systemSpec
                        .text(systemMessageTemplate)
                        .param("role", chatRequest.systemMessageParams().getOrDefault("role", "assistant"))
                        .param("tone", chatRequest.systemMessageParams().getOrDefault("tone", "neutral"))
                        .param("content", chatRequest.systemMessageParams().getOrDefault("content", ""))
                )
                .user(chatRequest.message())
                .advisors(a -> a.param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId));

        // Conditionally add the QuestionAnswerAdvisor if useDocument is true
        if (chatRequest.useDocument()) {
            promptBuilder = promptBuilder.advisors(new QuestionAnswerAdvisor(vectorStore));
        }

        return promptBuilder.call().chatResponse();
    }

}
