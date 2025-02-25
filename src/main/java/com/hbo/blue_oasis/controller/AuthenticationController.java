package com.hbo.blue_oasis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hbo.blue_oasis.controller.dto.AuthCreateUserRequest;
import com.hbo.blue_oasis.controller.dto.AuthLoginRequest;
import com.hbo.blue_oasis.controller.dto.AuthResponse;
import com.hbo.blue_oasis.service.UserDetailServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Controller for Authentication")
public class AuthenticationController {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @PostMapping("sign-up")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid AuthCreateUserRequest authCreateUser) {
        return new ResponseEntity<>(this.userDetailService.createUser(authCreateUser), HttpStatus.CREATED);
    }

    @PostMapping("/log-in")
    @Operation(summary = "Login User", description = "Authenticate a user and return the authentication token along with user details.", tags = {
            "Authentication" }, requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Authentication request with username and password", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthLoginRequest.class))), responses = {
                    @ApiResponse(responseCode = "200", description = "Successful authentication", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class)))
            })
    public ResponseEntity<AuthResponse> login(@RequestBody AuthLoginRequest userRequest) {
        return new ResponseEntity<>(this.userDetailService.loginUser(userRequest), HttpStatus.OK);
    }

}