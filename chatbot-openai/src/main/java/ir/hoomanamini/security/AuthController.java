package ir.hoomanamini.security;

import ir.hoomanamini.dto.UserProfileDTO;
import ir.hoomanamini.model.User;
import ir.hoomanamini.model.UserRole;
import ir.hoomanamini.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          UserDetailsServiceImpl userDetailsService,
                          JwtUtil jwtUtil,
                          UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Register endpoint with default role as USER
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        user.setRole(UserRole.USER);  // Enforce "USER" role on registration
        user.setPassword(passwordEncoder.encode(user.getPassword()));  // Hash password
        userRepository.save(user);
        return "User registered successfully with USER role";
    }

    // Login endpoint
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        return jwtUtil.generateToken(userDetails.getUsername());  // Return JWT token
    }

    // Profile endpoint to get authenticated user details
    @GetMapping("/profile")
    public UserProfileDTO getUserProfile(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new UserProfileDTO(user.getId(), user.getUsername(), user.getRole().name());  // DTO for profile
    }

    // Admin-only endpoint to assign a role to a user
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/assign-role")
    public String assignRole(@RequestParam String username, @RequestParam UserRole role) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setRole(role);
        userRepository.save(user);
        return "Role assigned successfully";
    }
}
