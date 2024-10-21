package ir.hoomanamini;

import java.util.Map;

public record ChatRequest(
        String message,
        Map<String, String> systemMessageParams,
        boolean useDocument
) {}
