package com.hbo.blue_oasis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hbo.blue_oasis.controller.dto.GuestRequest;
import com.hbo.blue_oasis.service.GuestProducerService;

@RestController
@RequestMapping("/producer")
public class GuestProducerController {

    @Autowired
    private GuestProducerService guestProducerService;

    @PostMapping
    public ResponseEntity<?> sendMessage(@RequestBody GuestRequest message) {
        guestProducerService.sendMessage(message);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
