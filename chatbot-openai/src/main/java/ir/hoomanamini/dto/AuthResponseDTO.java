package ir.hoomanamini.dto;

public class AuthResponseDTO {
    private String token;
    private Long id;
    private String username;
    private String role;
    private String firstName;
    private String lastName;

    public AuthResponseDTO(String token, Long id, String username, String role, String firstName, String lastName) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getToken() {
        return token;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
