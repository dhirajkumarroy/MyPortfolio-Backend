package Dhiraj.sPortfolio.My_Portfolio.controller;

import Dhiraj.sPortfolio.My_Portfolio.dto.LoginRequest;
import Dhiraj.sPortfolio.My_Portfolio.dto.LoginResponse;
import Dhiraj.sPortfolio.My_Portfolio.entity.User;
import Dhiraj.sPortfolio.My_Portfolio.repository.UserRepository;
import Dhiraj.sPortfolio.My_Portfolio.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authManager,
                          UserRepository userRepo,
                          PasswordEncoder passwordEncoder,
                          JwtUtil jwtUtil) {
        this.authManager = authManager;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Register: keep this protected or remove in production.
     * For portfolio, you can use this to create your admin user initially.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user) {
        if (userRepo.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body(Map.of("error", "Username already taken"));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // ensure role is set: User.Role or string; adapt as your entity uses
        if (user.getRole() == null) {
            // if Role is enum, set appropriately; assuming enum Role.ADMIN exists
            // user.setRole(Role.ADMIN);
        }
        userRepo.save(user);
        return ResponseEntity.ok(Map.of("message", "User registered"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest req) {
        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
            );

            // generate token; include role claim
            var principal = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
            Map<String, Object> claims = new HashMap<>();
            claims.put("roles", principal.getAuthorities());

            String token = jwtUtil.generateToken(principal.getUsername(), claims);
            return ResponseEntity.ok(new LoginResponse(token));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
        }
    }
}
