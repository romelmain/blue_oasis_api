package com.hbo.blue_oasis.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hbo.blue_oasis.persistence.entity.GuestEntity;
import com.hbo.blue_oasis.service.GuestService;

@AllArgsConstructor
@RestController
@RequestMapping("/guest")
public class GuestController {

    private final GuestService guestService;

    @GetMapping()
    public ResponseEntity<?> findAll() {
        try {
            // TODO Implement Your Logic To Get Data From Service Layer Or Directly From
            // Repository Layer
            return new ResponseEntity<>("GetAll Results", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}")
    public ResponseEntity<?> getGuestById(@PathVariable Long id) {
        GuestEntity guestEntity = null;
        ResponseEntity<?> response = null;
        try {
            guestEntity = guestService.getGuestById(id);
            if (guestEntity != null) {
                response = new ResponseEntity<>(guestEntity, HttpStatus.OK);
            } else {
                response = new ResponseEntity<>("Guest Not Found", HttpStatus.NOT_FOUND);
            }
            return response;
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

}
