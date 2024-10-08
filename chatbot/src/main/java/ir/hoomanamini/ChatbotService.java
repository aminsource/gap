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

import java.io.IOException;
import java.nio.charset.Charset;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;

@Service
class ChatbotService {

    private final ChatClient chatClient;
    private final VectorStore vectorStore;
    @Value("classpath:/docs/suniar-faq.txt")
    private Resource suniarFaq;
    @Value("classpath:/prompts/faq.st")
    private Resource faqPrompt;
    ChatbotService(ChatClient.Builder chatClientBuilder, ChatMemory chatMemory, VectorStore vectorStore) {
        this.vectorStore = vectorStore;
        this.chatClient = chatClientBuilder
                .defaultAdvisors(new MessageChatMemoryAdvisor(chatMemory))
                .build();
    }

    String chat(String chatId, String message) {
        return chatClient
                .prompt()
                .user(message)
                .advisors(a -> a.param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId))
                .call()
                .content();
    }
    ChatResponse chatResponse(String chatId, String message) {
        return chatClient
                .prompt()
                .user(message)
                .advisors(a -> a.param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId))
                .call()
                .chatResponse();
    }
    String faq(String message) throws IOException {
        String suniarFaqText = suniarFaq.getContentAsString(Charset.defaultCharset());
        return chatClient.prompt()
                .user(u -> {
                    u.text(faqPrompt);
                    u.param("question",message);
                    u.param("context", suniarFaqText);
                })
                .call()
                .content();

    }
    String chatWithDocument(String message) {
        return chatClient.prompt()
                .advisors(new QuestionAnswerAdvisor(vectorStore))
                .user(message)
                .call()
                .content();
    }

}
