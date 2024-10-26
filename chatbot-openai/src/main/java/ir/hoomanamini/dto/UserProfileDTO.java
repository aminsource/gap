package ir.hoomanamini.dto;

public class UserProfileDTO {
    private Long id;
    private String username;
    private String role;

    public UserProfileDTO(Long id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    // Getters only, as this DTO is read-only
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}
