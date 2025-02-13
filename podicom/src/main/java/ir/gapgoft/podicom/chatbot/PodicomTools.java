package ir.gapgoft.podicom.chatbot;

import ir.gapgoft.podicom.chatbot.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

@Component
public class PodicomTools {

  private static final Logger logger = LoggerFactory.getLogger(PodicomTools.class);

  private final WebClient webClient;

  public PodicomTools(WebClient.Builder webClientBuilder) {
    this.webClient = webClientBuilder.baseUrl("https://api.padikam.com").build();
  }

  @Tool(description = "Welcome users to the podicom")
  void welcome() {
    logger.info("Welcoming users to the podicom");
  }

  @Tool(description = "Retrieve all transactions from Padicom API")
  public void getAllTransactions() {
    logger.info("Fetching all transactions from Padikam API");
//
// List<Trandasction
//    return webClient.get()
//            .uri("/transactions")
//            .retrieve()
//            .bodyToFlux(Transaction.class)
//            .collectList()
//            .block(); // Blocking call (for simplicity, but you can make it reactive)
  }

//  @Tool(description = "Retrieve transactions for a specific user from Padikam API")
//  public List<Transaction> getTransactionsByUser(String username) {
//    logger.info("Fetching transactions for user: {}", username);
//
//    return webClient.get()
//            .uri(uriBuilder -> uriBuilder.path("/transactions/user/{username}")
//                    .build(username))
//            .retrieve()
//            .bodyToFlux(Transaction.class)
//            .collectList()
//            .block();
//  }

//  @Tool(description = "Retrieve transactions within a specific date range from Padikam API")
//  public List<Transaction> getTransactionsByDateRange(String startDate, String endDate) {
//    logger.info("Fetching transactions between {} and {}", startDate, endDate);
//
//    return webClient.get()
//            .uri(uriBuilder -> uriBuilder.path("/transactions")
//                    .queryParam("startDate", startDate)
//                    .queryParam("endDate", endDate)
//                    .build())
//            .retrieve()
//            .bodyToFlux(Transaction.class)
//            .collectList()
//            .block();
//  }

//  @Tool(description = "Retrieve a transaction by its ID from Padikam API")
//  public Transaction getTransactionById(String transactionId) {
//    logger.info("Fetching transaction with ID: {}", transactionId);
//
//    return webClient.get()
//            .uri("/transactions/{id}", transactionId)
//            .retrieve()
//            .bodyToMono(Transaction.class)
//            .block();
//  }

//  @Tool(description = "Retrieve failed transactions from Padikam API")
//  public List<Transaction> getFailedTransactions() {
//    logger.info("Fetching failed transactions");
//
//    return webClient.get()
//            .uri("/transactions/failed")
//            .retrieve()
//            .bodyToFlux(Transaction.class)
//            .collectList()
//            .block();
//  }
}
