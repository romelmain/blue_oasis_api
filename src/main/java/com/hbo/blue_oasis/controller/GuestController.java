package com.hbo.blue_oasis.controller;

import lombok.AllArgsConstructor;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hbo.blue_oasis.controller.dto.GuestRequest;
import com.hbo.blue_oasis.persistence.entity.GuestEntity;
import com.hbo.blue_oasis.persistence.entity.UserEntity;
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

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getGuestByUserId(@PathVariable Long id) {
        GuestEntity guestEntity = null;
        UserEntity user = new UserEntity();
        user.setId(id);
        ResponseEntity<?> response = null;
        try {
            guestEntity = guestService.getGuestByUserId(user);
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

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody GuestRequest guestRequest) {
        GuestEntity newGuestEntity = null;
        GuestEntity guestEntity = null;
        UserEntity userEntity = null;

        try {
            userEntity = UserEntity.builder().id(guestRequest.userId()).build();
            guestEntity = GuestEntity.builder()
                    .address(guestRequest.address())
                    .name(guestRequest.name())
                    .phone(guestRequest.phone())
                    .lastName(guestRequest.lastname())
                    .createAt(new Date())
                    .updateAt(new Date())
                    .userEntity(userEntity).build();
            newGuestEntity = guestService.createNewGuest(guestEntity);
            return new ResponseEntity<>(newGuestEntity, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
