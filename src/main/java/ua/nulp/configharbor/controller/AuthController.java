package ua.nulp.configharbor.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ua.nulp.configharbor.model.dto.AuthCredentialsRequest;
import ua.nulp.configharbor.model.dto.AuthResponse;
import ua.nulp.configharbor.model.users.User;
import ua.nulp.configharbor.service.UserService;
import ua.nulp.configharbor.util.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("login")
    public ResponseEntity<?> login (@RequestBody AuthCredentialsRequest request) throws Exception {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            request.getUserEmail(),
                            request.getUserPassword()
                    ));
            String email = authentication.getName();
            User user = userService.getUserByEmail(email);
            user.setUserPassword("");
            String token = jwtUtil.createToken(user);

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION,
                            token
                    ).body(
                            user
                    );

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> signUp(@RequestBody User user) throws Exception {
        if(userService.getUserByEmail(user.getUserEmail()) != null) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Email already taken");
        }

        user.setUserPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user);
        String token = jwtUtil.createToken(user);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setUserEmail(user.getUserEmail());
        authResponse.setUserToken(token);
        authResponse.setMessage("Register Success");

        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getUser(@AuthenticationPrincipal User user) throws Exception{
        return ResponseEntity.ok()
                .header(
                        HttpHeaders.AUTHORIZATION,
                        ""
                ).body(
                        user
                );
    }
}
