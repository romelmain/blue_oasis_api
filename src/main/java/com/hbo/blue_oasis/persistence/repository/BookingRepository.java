package com.hbo.blue_oasis.persistence.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hbo.blue_oasis.persistence.entity.BookingEntity;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

    @Query(value = "select a.id,a.check_in_date,a.check_out_date,a.create_at,"
            + " a.update_at,a.date,a.guest_id,a.status_id from booking a "
            + " inner join guest b on (a.guest_id = b.id) "
            + " where guest_id = :guestId and status_id = 1", nativeQuery = true)
    public ArrayList<BookingEntity> findBookingByGuest(@Param("guestId") Long guestId);
}
