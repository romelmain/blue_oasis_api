package com.hbo.blue_oasis.controller.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "username", "message", "jwt", "status", })
public record AuthResponse(String username, String message, String jwt, boolean status) {

}
