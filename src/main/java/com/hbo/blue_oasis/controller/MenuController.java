package com.hbo.blue_oasis.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hbo.blue_oasis.Util.JwtUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import com.auth0.jwt.interfaces.Claim;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/menu")
@Tag(name = "Menu Operations", description = "Controller for Menu Operations")
public class MenuController {

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping()
    public ResponseEntity<?> getMenu(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        Claim authoritiesClaim = null;
        Claim usernameClaim = null;
        String[] authorities = null;
        String username = null;
        List<String> authoritiesList = null;
        try {
            if (token != null) {
                System.out.println(token);
                token = token.substring(7);
                DecodedJWT decodedJWT = jwtUtils.validateToken(token);
                authoritiesClaim = jwtUtils.getSpecificClaim(decodedJWT, "authorities");
                usernameClaim = jwtUtils.getSpecificClaim(decodedJWT, "sub");
                StringBuilder authoritiesStr = new StringBuilder(authoritiesClaim.toString());
                authoritiesStr.deleteCharAt(0);
                authoritiesStr.deleteCharAt((authoritiesStr.length() - 1));
                System.out.println(authoritiesStr);
                authorities = authoritiesStr.toString().split(",");
                authoritiesList = new ArrayList<String>(Arrays.asList(authorities));
                String usernamestr = usernameClaim.toString();
                username = usernamestr.replace("\"", "");
                System.out.println(username);

            }

            return new ResponseEntity<>(authoritiesList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
