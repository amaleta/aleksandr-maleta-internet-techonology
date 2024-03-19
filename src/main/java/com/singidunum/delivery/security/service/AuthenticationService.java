package com.singidunum.delivery.security.service;

import com.singidunum.delivery.security.dto.JwtAuthenticationResponse;
import com.singidunum.delivery.security.dto.JwtResponse;
import com.singidunum.delivery.security.dto.RefreshRequest;
import com.singidunum.delivery.security.dto.SignInRequest;
import com.singidunum.delivery.security.dto.SignUpRequest;
import com.singidunum.delivery.security.model.Role;
import com.singidunum.delivery.security.model.User;
import java.time.Instant;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthenticationResponse signUp(SignUpRequest request) {

        var user = User.builder()
            .username(request.getUsername())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.ROLE_USER)
            .token(UUID.randomUUID().toString())
            .expiryDate(Instant.now().plusSeconds(6000))//100 min
            .build();

        userService.create(user);

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt, user.getToken());
    }

    public JwtResponse updateJwtByRefresh(RefreshRequest request) {
        var user = userService.findByToken(request.getRefreshToken());
        if (userService.isValidRefresh(user)) {
            return new JwtResponse(jwtService.generateToken(user));
        }
        throw new RuntimeException("Invalid refresh token");
    }

    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            request.getUsername(),
            request.getPassword()
        ));

        var user = userService
            .userDetailsService()
            .loadUserByUsername(request.getUsername());

        var jwt = jwtService.generateToken(user);
        var token = userService.getByUsername(request.getUsername()).getToken();
        return new JwtAuthenticationResponse(jwt, token);
    }
}
