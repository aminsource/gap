package ir.gapgoft.podicom.chatbot;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PodicomController {
  private final ChatClient chatClient;
  private final PodicomTools tools;

  PodicomController(ChatClient.Builder chatClientBuilder, PodicomTools tools) {
    this.chatClient = chatClientBuilder.build();
    this.tools = tools;
  }


  @GetMapping("/")
  public String sayHello() {
    return "Hello, Podicom!";
  }

  @GetMapping("/chat/podicom")
  String chatMethodNoArgs(String message) {
    return chatClient.prompt()
            .user(message)
            .tools(tools)
            .call()
            .content();
  }

}

