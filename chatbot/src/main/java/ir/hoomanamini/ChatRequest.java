package ir.hoomanamini;

import java.util.Map;

public class ChatRequest {
    private String message;
    private Map<String, String> systemMessageParams;
    private boolean useDocument;

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getSystemMessageParams() {
        return systemMessageParams;
    }

    public void setSystemMessageParams(Map<String, String> systemMessageParams) {
        this.systemMessageParams = systemMessageParams;
    }

    public boolean isUseDocument() {
        return useDocument;
    }

    public void setUseDocument(boolean useDocument) {
        this.useDocument = useDocument;
    }
}
