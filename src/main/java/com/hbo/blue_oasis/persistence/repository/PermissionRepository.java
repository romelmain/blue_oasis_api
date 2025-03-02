package com.hbo.blue_oasis.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hbo.blue_oasis.persistence.entity.PermissionEntity;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {
    @Query(value = "select name,path from permissions where name in (:permissions) and menu = true")
    PermissionEntity getPermissionsPath(@Param("permissions") String permissions);
}
