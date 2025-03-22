package com.hbo.blue_oasis.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.hbo.blue_oasis.persistence.entity.GuestEntity;
import java.util.Optional;

@Repository
public interface GuestRepository extends JpaRepository<GuestEntity, Long> {

    @SuppressWarnings("null")
    Optional<GuestEntity> findById(Long id);

    // List<GuestEntity> findByUserEntity(UserEntity userEntity);
    @Query(value = "select a.id,a.create_at,a.update_at,a.name,a.last_name,a.address,a.phone,b.username,a.user_id "
            + " from guest a "
            +
            " inner join users b on (a.user_id = b.id) " +
            " where b.username = :username", nativeQuery = true)
    GuestEntity findByUser(@Param("username") String username);
}
