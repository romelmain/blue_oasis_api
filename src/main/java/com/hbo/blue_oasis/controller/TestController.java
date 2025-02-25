package com.hbo.blue_oasis.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/method")
public class TestController {

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/get")
    public String callGet() {
        return "Method Called With GET";
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/post")
    public String callPost() {
        return "Method Called With POST";
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/put")
    public String callPut() {
        return "Method Called With PUT";
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/delete")
    public String callDelete() {
        return "Method Called With DELETE";
    }
}