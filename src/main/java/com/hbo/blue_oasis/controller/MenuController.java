package com.hbo.blue_oasis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hbo.blue_oasis.Util.JwtUtils;
import com.hbo.blue_oasis.controller.dto.MenuResponse;
import java.util.ArrayList;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/menu")
@Tag(name = "Menu Operations", description = "Controller for Menu Operations")
public class MenuController {

    @Autowired
    private JwtUtils jwtUtils;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping()
    public ResponseEntity<?> getMenu(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {

        ArrayList<String> authorities = null;
        String username = null;
        String role = null;
        MenuResponse menu = null;
        try {
            if (token != null) {
                token = token.substring(7);
                DecodedJWT decodedJWT = jwtUtils.validateToken(token);
                username = jwtUtils.getUserName(decodedJWT, token);
                System.out.println("User: " + username);
                authorities = jwtUtils.getAuthorities(decodedJWT, token);
                role = jwtUtils.getRole(decodedJWT, token);
                System.out.println("Role: " + role);
                menu = new MenuResponse(username, role, authorities);
            }

            return new ResponseEntity<>(menu, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
