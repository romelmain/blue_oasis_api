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

    @CrossOrigin(origins = "http://localhost:4200")
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

    /*
     * @GetMapping("/{id}")
     * public ResponseEntity<?> find(@PathVariable Integer id) {
     * try {
     * // TODO Implement Your Logic To Get Data From Service Layer Or Directly From
     * // Repository Layer
     * return new ResponseEntity<>("GetOne Result", HttpStatus.OK);
     * } catch (Exception e) {
     * return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
     * }
     * }
     * 
     * @PostMapping()
     * public ResponseEntity<?> create(@RequestBody Dto dto) {
     * try {
     * // TODO Implement Your Logic To Save Data And Return Result Through
     * // ResponseEntity
     * return new ResponseEntity<>("Create Result", HttpStatus.OK);
     * } catch (Exception e) {
     * return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
     * }
     * }
     * 
     * @PutMapping()
     * public ResponseEntity<?> update(@RequestBody Dto dto) {
     * try {
     * // TODO Implement Your Logic To Update Data And Return Result Through
     * // ResponseEntity
     * return new ResponseEntity<>("Update Result", HttpStatus.OK);
     * } catch (Exception e) {
     * return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
     * }
     * }
     * 
     * @DeleteMapping("/{id}")
     * public ResponseEntity<?> delete(@PathVariable Integer id) {
     * try {
     * // TODO Implement Your Logic To Destroy Data And Return Result Through
     * // ResponseEntity
     * return new ResponseEntity<>("Destroy Result", HttpStatus.OK);
     * } catch (Exception e) {
     * return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
     * }
     * }
     */
}
