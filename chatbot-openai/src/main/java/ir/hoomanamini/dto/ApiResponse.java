package ir.hoomanamini.dto;

public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T result;

    public ApiResponse(boolean success, String message, T result) {
        this.success = success;
        this.message = message;
        this.result = result;
    }

    public static <T> ApiResponse<T> success(T result, String message) {
        return new ApiResponse<>(true, message, result);
    }

    // Overloaded failure method to accept result
    public static <T> ApiResponse<T> failure(String message, T result) {
        return new ApiResponse<>(false, message, result);
    }

    public static <T> ApiResponse<T> failure(String message) {
        return new ApiResponse<>(false, message, null);
    }

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
