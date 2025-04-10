package com.hbo.blue_oasis.controller;

import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hbo.blue_oasis.controller.dto.GuestRequest;
import com.hbo.blue_oasis.persistence.entity.GuestEntity;
import com.hbo.blue_oasis.persistence.entity.UserEntity;
import com.hbo.blue_oasis.service.GuestService;
import com.hbo.blue_oasis.service.UserDetailServiceImpl;

@AllArgsConstructor
@RestController
@RequestMapping("/guest")
public class GuestController {

    private final GuestService guestService;
    private final UserDetailServiceImpl userDetailServiceImpl;

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

    @CrossOrigin(origins = { "${BLUE_OASIS_APP}" })
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

    @CrossOrigin(origins = { "${BLUE_OASIS_APP}" })
    @GetMapping("/user/{username}")
    public ResponseEntity<?> getGuestByUserName(@PathVariable String username) {
        GuestEntity guestEntity = null;
        Optional<UserEntity> oUserEntity = null;
        UserEntity user = null;

        ResponseEntity<?> response = null;

        try {

            oUserEntity = userDetailServiceImpl.getUserByName(username);
            if (oUserEntity.isPresent()) {
                user = oUserEntity.get();
                guestEntity = guestService.getGuestByUserName(user);
                if (guestEntity != null) {
                    response = new ResponseEntity<>(guestEntity, HttpStatus.OK);
                } else {
                    response = new ResponseEntity<>("Guest Not Found", HttpStatus.NOT_FOUND);
                }

            } else {
                Optional.empty();
            }

            return response;
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @CrossOrigin(origins = { "${BLUE_OASIS_APP}" })
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody GuestRequest guestRequest) {
        GuestEntity newGuestEntity = null;
        GuestEntity guestEntity = null;
        UserEntity userEntity = null;
        Optional<UserEntity> oUserEntity = null;
        ResponseEntity<?> response = null;
        try {

            oUserEntity = userDetailServiceImpl.getUserByName(guestRequest.username());

            if (oUserEntity.isPresent()) {
                userEntity = oUserEntity.get();
                guestEntity = GuestEntity.builder()
                        .address(guestRequest.address())
                        .name(guestRequest.name())
                        .phone(guestRequest.phone())
                        .lastName(guestRequest.lastname())
                        .createAt(new Date())
                        .updateAt(new Date())
                        .userEntity(userEntity).build();
                newGuestEntity = guestService.createNewGuest(guestEntity);
                response = new ResponseEntity<>(newGuestEntity, HttpStatus.CREATED);
            } else {
                Optional.empty();
                response = new ResponseEntity<>("Problems with the User Data", HttpStatus.BAD_REQUEST);
            }

            return response;

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
