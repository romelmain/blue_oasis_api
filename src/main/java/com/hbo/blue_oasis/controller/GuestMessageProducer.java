package com.hbo.blue_oasis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hbo.blue_oasis.controller.dto.GuestRequest;
import com.hbo.blue_oasis.service.GuestMessageSender;

@RestController
@RequestMapping("/guest-producer")
public class GuestMessageProducer {

    @Autowired
    private GuestMessageSender guestMessageSender;

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody GuestRequest guestRequest) {
        try {
            guestMessageSender.sendMessage(guestRequest);
            return new ResponseEntity<>("Message sent to queue", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}