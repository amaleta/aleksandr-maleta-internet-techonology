package com.singidunum.delivery.controller;

import com.singidunum.delivery.security.dto.JwtAuthenticationResponse;
import com.singidunum.delivery.security.dto.JwtResponse;
import com.singidunum.delivery.security.dto.RefreshRequest;
import com.singidunum.delivery.security.dto.SignInRequest;
import com.singidunum.delivery.security.dto.SignUpRequest;
import com.singidunum.delivery.security.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sing-up")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
        return authenticationService.signUp(request);
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/login")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        return authenticationService.signIn(request);
    }

    @Operation(summary = "Refresh токена доступа")
    @PostMapping("/refresh")
    public JwtResponse refresh(@RequestBody @Valid RefreshRequest request) {
        return authenticationService.updateJwtByRefresh(request);
    }
}

