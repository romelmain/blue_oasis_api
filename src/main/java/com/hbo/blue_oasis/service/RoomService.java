package com.hbo.blue_oasis.service;

import java.util.ArrayList;
import java.util.List;
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

    public Optional<ArrayList<RoomEntity>> getRoomListByIds(List<Long> roomIds) {
        ArrayList<RoomEntity> roomEntityList = null;
        Optional<ArrayList<RoomEntity>> oRoomEntityList;
        try {
            roomEntityList = roomRepository.getRoomListByIds(roomIds);
            if (roomEntityList.size() > 0) {
                oRoomEntityList = Optional.of(roomEntityList);
            } else {
                oRoomEntityList = Optional.empty();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            oRoomEntityList = Optional.empty();
        }
        return oRoomEntityList;
    }

}
