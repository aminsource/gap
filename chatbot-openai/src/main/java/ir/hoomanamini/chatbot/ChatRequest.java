package ir.hoomanamini.chatbot;

import java.util.Map;

public record ChatRequest(
        String message,
        Map<String, String> systemMessageParams,
        boolean useDocument
) {}
