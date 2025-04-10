package com.hbo.blue_oasis.controller;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hbo.blue_oasis.persistence.entity.RoomEntity;
import com.hbo.blue_oasis.service.RoomService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    @CrossOrigin(origins = { "${BLUE_OASIS_APP}" })
    @GetMapping()
    public ResponseEntity<?> getRoomList() {
        ArrayList<RoomEntity> roomList = null;
        Optional<ArrayList<RoomEntity>> oRoomList = null;
        try {
            oRoomList = roomService.getRoomList();
            if (oRoomList.isPresent()) {
                System.out.println("Exito !!!");
            } else {
                System.out.println("Fallo");
            }
            return new ResponseEntity<>(oRoomList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = { "${BLUE_OASIS_APP}" })
    @GetMapping("/{id}")
    public ResponseEntity<?> getRoomById(@PathVariable Long id) {
        RoomEntity roomEntity = null;
        Optional<RoomEntity> oRoom = null;
        ResponseEntity<?> response = null;
        try {
            oRoom = roomService.getRoomById(id);
            if (oRoom.isPresent()) {
                roomEntity = oRoom.get();
                response = new ResponseEntity<>(roomEntity, HttpStatus.OK);
            } else {
                response = new ResponseEntity<>("Room Not Found", HttpStatus.NOT_FOUND);
                oRoom.empty();
            }
            return response;
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
