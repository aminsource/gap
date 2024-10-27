package ir.hoomanamini.dto;

public class UserProfileDTO {
    private Long id;
    private String username;
    private String role;

    private String firstName;

    private String lastName;


    public UserProfileDTO(Long id, String username, String role, String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}
