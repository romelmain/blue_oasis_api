package com.hbo.blue_oasis.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hbo.blue_oasis.persistence.entity.RoomEntity;
import com.hbo.blue_oasis.persistence.repository.RoomRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Optional<ArrayList<RoomEntity>> getRoomList() {
        ArrayList<RoomEntity> roomList = null;
        Optional<ArrayList<RoomEntity>> oRoomList = null;

        try {
            roomList = roomRepository.getRoomList();
            if (roomList.size() > 0) {
                oRoomList = Optional.of(roomList);
            } else {
                oRoomList = Optional.empty();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            oRoomList = Optional.empty();
        }
        return oRoomList;
    }

    public Optional<RoomEntity> getRoomById(Long id) {
        Optional<RoomEntity> oRoom = null;

        try {
            oRoom = roomRepository.findById(id);
            if (!oRoom.isPresent()) {
                oRoom = Optional.empty();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            oRoom = Optional.empty();
        }
        return oRoom;
    }

}
