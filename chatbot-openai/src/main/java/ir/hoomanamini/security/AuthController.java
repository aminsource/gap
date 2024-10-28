package ir.hoomanamini.security;

import ir.hoomanamini.dto.ApiResponse;
import ir.hoomanamini.dto.UserProfileDTO;
import ir.hoomanamini.model.User;
import ir.hoomanamini.model.UserRole;
import ir.hoomanamini.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

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
    public ResponseEntity<ApiResponse<String>> register(@RequestBody @Valid User user) {
        user.setRole(UserRole.USER);  // Enforce "USER" role on registration
        user.setPassword(passwordEncoder.encode(user.getPassword()));  // Hash password
        userRepository.save(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(null, "کاربر با موفقیت ایجاد شد"));
    }

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody User user) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        String token = jwtUtil.generateToken(userDetails.getUsername());  // Generate JWT token
        return ResponseEntity
                .ok(ApiResponse.success(token, "ورود با موفقیت انجام شد"));
    }

    // Profile endpoint to get authenticated user details
    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<UserProfileDTO>> getUserProfile(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserProfileDTO userProfile = new UserProfileDTO(
                user.getId(), user.getUsername(), user.getRole().name(), user.getFirstName(), user.getLastName());

        return ResponseEntity
                .ok(ApiResponse.success(userProfile, "پروفایل کاربر با موفقیت دریافت شد"));
    }

    // Admin-only endpoint to assign a role to a user
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/assign-role")
    public ResponseEntity<ApiResponse<String>> assignRole(@RequestParam String username, @RequestParam UserRole role) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setRole(role);
        userRepository.save(user);
        return ResponseEntity
                .ok(ApiResponse.success(null, "نقش با موفقیت اختصاص یافت"));
    }
}
