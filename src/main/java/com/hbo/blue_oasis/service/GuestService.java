package com.hbo.blue_oasis.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hbo.blue_oasis.persistence.entity.GuestEntity;
import com.hbo.blue_oasis.persistence.entity.UserEntity;
import com.hbo.blue_oasis.persistence.repository.GuestRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class GuestService {

    private final GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public GuestEntity getGuestById(Long id) {

        Optional<GuestEntity> oGuestEntity = null;
        GuestEntity guestEntity = null;
        try {
            oGuestEntity = guestRepository.findById(id);
            if (oGuestEntity.isPresent()) {
                guestEntity = oGuestEntity.get();
            } else {
                Optional.empty();
            }
        } catch (Exception e) {
            Optional.empty();
        }
        return guestEntity;
    }

    public GuestEntity getGuestByUserId(UserEntity user) {
        List<GuestEntity> guestEntityList = null;
        GuestEntity guestEntity = null;
        try {
            // guestEntityList = guestRepository.findByUserEntity(user);
            guestEntity = guestRepository.findByUser(user.getUsername());

            /*
             * if (guestEntityList.size() > 0) {
             * guestEntity = guestEntityList.getFirst();
             * 
             * } else {
             * throw new NullPointerException("User Not Found !!");
             * }
             */
            if (guestEntity != null) {
                System.out.println("Lo hizo");
            } else {
                throw new NullPointerException("User Not Found !!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return guestEntity;
    }

    public GuestEntity getGuestByUserName(UserEntity user) {
        List<GuestEntity> guestEntityList = null;
        GuestEntity guestEntity = null;
        try {
            // guestEntityList = guestRepository.findByUserEntity(user);
            guestEntity = guestRepository.findByUser(user.getUsername());

            /*
             * if (guestEntityList.size() > 0) {
             * guestEntity = guestEntityList.getFirst();
             * 
             * } else {
             * throw new NullPointerException("User Not Found !!");
             * }
             */

            if (guestEntity != null) {
                System.out.println("Lo hizo");
            } else {
                throw new NullPointerException("User Not Found !!");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return guestEntity;
    }

    public GuestEntity createNewGuest(GuestEntity guestEntity) {
        GuestEntity newGuest = null;
        try {
            newGuest = guestRepository.save(guestEntity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return newGuest;
    }

}
