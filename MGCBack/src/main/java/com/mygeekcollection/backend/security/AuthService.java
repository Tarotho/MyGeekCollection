package com.mygeekcollection.backend.security;

import com.mygeekcollection.backend.entity.Role;
import com.mygeekcollection.backend.entity.User;
import com.mygeekcollection.backend.repository.UserRepository;
import com.mygeekcollection.backend.security.entity.AuthResponse;
import com.mygeekcollection.backend.security.entity.LoginRequest;
import com.mygeekcollection.backend.security.entity.RegisterRequest;
import com.mygeekcollection.backend.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsernameOrEmail(), request.getPassword()));
        User user = userRepository.findByUsernameOrEmail(request.getUsernameOrEmail(), request.getUsernameOrEmail()).orElseThrow();
        String token = jwtService.getToken(user);

        return AuthResponse.builder()
                .token(token)
                .build();

    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        User userResponse = userRepository.save(user);
        return AuthResponse.builder()
                .token(jwtService.getToken(userResponse))
                .build();
    }
}
