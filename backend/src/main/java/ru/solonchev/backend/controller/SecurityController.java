package ru.solonchev.backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.solonchev.backend.dto.user.response.LoginResponse;
import ru.solonchev.backend.jwt.JwtCore;
import ru.solonchev.backend.domain.User;
import ru.solonchev.backend.dto.user.request.SigninRequest;
import ru.solonchev.backend.dto.user.request.SignupRequest;
import ru.solonchev.backend.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class SecurityController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtCore jwtCore;

    @PostMapping("/signin")
    ResponseEntity<?> signIn(@RequestBody SigninRequest signinRequest) {
        Authentication authentication = null;
        final String email = signinRequest.getUsername();
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getUsername(), signinRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok(
                LoginResponse.builder()
                        .token(jwtCore.generateToken(authentication))
                        .userId(userService.findUserByEmail(email).getId())
                        .build()
        );
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignupRequest signupRequest) {
        if (userService.existUserByEmail(signupRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Choose another email");
        }
        String hashedPassword = passwordEncoder.encode(signupRequest.getPassword());
        User user = new User();
        user.setFirstName(signupRequest.getFirstName());
        user.setLastName(signupRequest.getLastName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(hashedPassword);
        userService.addUser(user);
        return ResponseEntity.ok("Success, Baby");
    }
}
