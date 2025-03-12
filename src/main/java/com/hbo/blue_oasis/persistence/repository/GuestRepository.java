package com.hbo.blue_oasis.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hbo.blue_oasis.persistence.entity.GuestEntity;
import com.hbo.blue_oasis.persistence.entity.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface GuestRepository extends JpaRepository<GuestEntity, Long> {

    @SuppressWarnings("null")
    Optional<GuestEntity> findById(Long id);

    List<GuestEntity> findByUserEntity(UserEntity userEntity);

}
