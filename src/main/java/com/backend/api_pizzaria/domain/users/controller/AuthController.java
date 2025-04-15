package com.backend.api_pizzaria.domain.users.controller;

import com.backend.api_pizzaria.infra.security.TokenService;
import com.backend.api_pizzaria.domain.users.IUserRepository;
import com.backend.api_pizzaria.domain.users.User;
import com.backend.api_pizzaria.domain.users.controller.dtos.AuthResponseDTO;
import com.backend.api_pizzaria.domain.users.controller.dtos.AuthenticationDTO;
import com.backend.api_pizzaria.domain.users.controller.dtos.RegisterDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var userPassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(userPassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new AuthResponseDTO(token));
    }

    @PostMapping("/register")
    public  ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data){
        if (this.userRepository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.name(), data.email(), encryptedPassword);

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
