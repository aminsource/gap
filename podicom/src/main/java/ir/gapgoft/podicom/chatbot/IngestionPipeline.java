package ir.gapgoft.podicom.chatbot;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Component
public class IngestionPipeline {
    private static final Logger logger = LoggerFactory.getLogger(IngestionPipeline.class);

    private final VectorStore vectorStore;

    @Value("classpath:documents/story1.md")
    Resource textFile1;

    public IngestionPipeline(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @PostConstruct
    void run() {
        List<Document> documents = new ArrayList<>();

        logger.info("Loading .md files as Documents");
        var textReader1 = new TextReader(textFile1);
        textReader1.getCustomMetadata().put("location", "North Pole");
        textReader1.setCharset(Charset.defaultCharset());
        documents.addAll(textReader1.get());

        logger.info("Creating and storing Embeddings from Documents");
        vectorStore.add(new TokenTextSplitter().split(documents));


    }
}
