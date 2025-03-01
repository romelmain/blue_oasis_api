package com.hbo.blue_oasis.controller.dto;

import java.util.ArrayList;

public record MenuResponse(String userName, String role, ArrayList<String> authorities) {

}
