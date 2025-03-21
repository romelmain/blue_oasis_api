package com.hbo.blue_oasis.controller.dto;

import java.util.Date;
import java.util.List;

public record BookingRequest(
        Long bookingId,
        Date createAt, Date updateAt,
        Date date,
        Date checkInDate,
        Date checkOutDate,
        Long guestId,
        List<Rooms> roomList) {

}
