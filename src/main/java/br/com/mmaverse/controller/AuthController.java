package br.com.mmaverse.controller;

import br.com.mmaverse.config.TokenService;
import br.com.mmaverse.dto.UserRequestDTO;
import br.com.mmaverse.entity.User;
import br.com.mmaverse.mapper.UserMapper;
import br.com.mmaverse.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/mmaverse/auth")
public class AuthController {

    private final UserService userService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserService userService, TokenService tokenService, AuthenticationManager authenticationManager, AuthenticationManager authenticationManager1) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager1;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequestDTO request) {
        User savedUser = userService.save(UserMapper.toUser(request));
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered");
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody UserRequestDTO request) {
        UsernamePasswordAuthenticationToken userAndPassword = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        Authentication authenticate = authenticationManager.authenticate(userAndPassword);
        User user = (User) authenticate.getPrincipal();
        String token = tokenService.generateToken(user);
        return ResponseEntity.ok(Map.of("token", token));
    }
}
