package com.hbo.blue_oasis.controller;

import lombok.AllArgsConstructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hbo.blue_oasis.controller.dto.BookingRequest;
import com.hbo.blue_oasis.controller.dto.Rooms;
import com.hbo.blue_oasis.persistence.entity.BookingEntity;
import com.hbo.blue_oasis.persistence.entity.BookingStatusEntity;
import com.hbo.blue_oasis.persistence.entity.GuestEntity;
import com.hbo.blue_oasis.persistence.entity.RoomEntity;
import com.hbo.blue_oasis.service.BookingService;
import com.hbo.blue_oasis.service.RoomService;

@AllArgsConstructor
@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;
    private final RoomService roomService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody BookingRequest bookingRequest) {
        Optional<BookingEntity> oBookingEntity = null;
        Optional<ArrayList<RoomEntity>> oRoomEntityList = null;
        BookingEntity bookingEntity = null;
        ResponseEntity<?> response = null;
        ArrayList<RoomEntity> roomList = new ArrayList<RoomEntity>();
        List<Long> roomIds = null;
        GuestEntity guestEntity = null;

        try {
            BookingStatusEntity bookingStatusEntity = BookingStatusEntity.builder().id(Long.valueOf(1)).build();
            guestEntity = GuestEntity.builder().id(bookingRequest.guestId()).build();

            roomIds = bookingRequest.roomList().stream().map(Rooms::roomId).collect(Collectors.toList());
            oRoomEntityList = roomService.getRoomListByIds(roomIds);

            if (oRoomEntityList.isPresent()) {
                roomList = oRoomEntityList.get();

                System.out.println("Hay Lista");

                if (bookingRequest.bookingId() != null) {
                    bookingEntity = bookingService.findBookingById(bookingRequest.bookingId());
                    for (RoomEntity room : roomList) {
                        bookingEntity.getRooms().add(room);
                    }

                } else {
                    bookingEntity = BookingEntity.builder()
                            .createAt(bookingRequest.createAt())
                            .updateAt(bookingRequest.updateAt())
                            .date(bookingRequest.date())
                            .checkInDate(bookingRequest.checkInDate())
                            .checkOutDate(bookingRequest.checkOutDate())
                            .rooms(roomList)
                            .status(bookingStatusEntity)
                            .guest(guestEntity)
                            .build();
                }

                oBookingEntity = bookingService.createNewBooking(bookingEntity);

                if (oBookingEntity.isPresent()) {
                    response = new ResponseEntity<>(oBookingEntity.get(), HttpStatus.CREATED);

                } else {
                    response = new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
                }

            } else {
                response = new ResponseEntity<>("Problems with the rooms", HttpStatus.BAD_REQUEST);
                Optional.empty();
            }

            return response;
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/guest/{id}")
    public ResponseEntity<?> getBookingByGuest(@PathVariable Long id) {
        BookingEntity bookingEntity = null;
        ArrayList<BookingEntity> bookingEntitieList = null;
        ResponseEntity<?> response = null;
        try {
            bookingEntitieList = bookingService.findBookingByGuest(id);
            int count = bookingEntitieList.size();
            if (count == 1) {
                bookingEntity = bookingEntitieList.getFirst();
                response = new ResponseEntity<>(bookingEntity, HttpStatus.OK);

            } else if (count > 1) {
                throw new NullPointerException("Problem: there are more one Booking for this guest !!");
            } else if (count == 0) {
                response = new ResponseEntity<>("Booking Not Found", HttpStatus.NOT_FOUND);
            }

            return response;
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
