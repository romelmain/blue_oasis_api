package com.hbo.blue_oasis.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hbo.blue_oasis.persistence.entity.PermissionEntity;
import com.hbo.blue_oasis.persistence.repository.PermissionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PermissionService {

    private final PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public Optional<ArrayList<PermissionEntity>> getPermissionsPath(List<String> authorities) {
        ArrayList<PermissionEntity> permissionEntityList = null;
        Optional<ArrayList<PermissionEntity>> oPermissionList;
        try {
            permissionEntityList = permissionRepository.getPermissionsPath(authorities);
            if (permissionEntityList.size() > 0) {
                System.out.println("AAAAAAAAAAAAAAAAAAAAAA");
                oPermissionList = Optional.of(permissionEntityList);
            } else {
                System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBB");
                oPermissionList = Optional.empty();
            }
        } catch (Exception e) {
            System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCC");
            System.out.println(e.getMessage());
            oPermissionList = Optional.empty();
        }
        return oPermissionList;
    }

}
