package com.hbo.blue_oasis.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hbo.blue_oasis.persistence.entity.BookingEntity;
import com.hbo.blue_oasis.persistence.repository.BookingRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @SuppressWarnings("unused")
    public Optional<BookingEntity> createNewBooking(BookingEntity bookingEntity) {
        Optional<BookingEntity> oBookingEntity = null;
        BookingEntity newBookingEntity = null;
        try {
            newBookingEntity = bookingRepository.save(bookingEntity);
            if (newBookingEntity != null) {
                System.out.println("AAAAAAAAAAAAAAAAAAAAAAA");
                oBookingEntity = Optional.of(newBookingEntity);
            } else {
                System.out.println("BBBBBBBBBBBBBBBBBBBB");
                oBookingEntity = Optional.empty();
            }

        } catch (Exception e) {
            System.out.println("CCCCCCCCCCCCCCCCCCCC");
            System.out.println(e.getMessage());
            System.out.println(e.getCause());

            oBookingEntity = Optional.empty();
        }

        return oBookingEntity;

    }

}
