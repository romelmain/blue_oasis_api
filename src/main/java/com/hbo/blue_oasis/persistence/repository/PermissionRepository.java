package com.hbo.blue_oasis.persistence.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hbo.blue_oasis.persistence.entity.PermissionEntity;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {
    @Query(value = "select a.id,a.menu,a.name,a.path from permissions a where " +
            " name IN (:authorities) and menu = true", nativeQuery = true)
    ArrayList<PermissionEntity> getPermissionsPath(@Param("authorities") List<String> authorities);

}
