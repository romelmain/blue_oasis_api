package com.hbo.blue_oasis.persistence.repository;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.hbo.blue_oasis.persistence.entity.RoomEntity;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {

    @Query(value = "select * from room", nativeQuery = true)
    ArrayList<RoomEntity> getRoomList();

}
