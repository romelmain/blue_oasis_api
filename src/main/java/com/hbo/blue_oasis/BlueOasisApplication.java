package com.hbo.blue_oasis;

import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hbo.blue_oasis.persistence.entity.PermissionEntity;
import com.hbo.blue_oasis.persistence.entity.RoleEntity;
import com.hbo.blue_oasis.persistence.entity.RoleEnum;
import com.hbo.blue_oasis.persistence.entity.UserEntity;
import com.hbo.blue_oasis.persistence.repository.UserRepository;

@SpringBootApplication
public class BlueOasisApplication {
        public static void main(String[] args) {
                SpringApplication.run(BlueOasisApplication.class, args);
        }

        @Bean
        CommandLineRunner init(UserRepository userRepository) {
                return args -> {
                        /* Crate Permissions */
                        PermissionEntity createPermission = PermissionEntity.builder()
                                        .name("CREATE")
                                        .build();

                        PermissionEntity readPermission = PermissionEntity.builder()
                                        .name("READ")
                                        .build();

                        PermissionEntity updatePermission = PermissionEntity.builder()
                                        .name("UPDATE")
                                        .build();

                        PermissionEntity deletePermission = PermissionEntity.builder()
                                        .name("DELETE")
                                        .build();

                        /* Create Roles */
                        RoleEntity roleAdmin = RoleEntity.builder()
                                        .roleEnum(RoleEnum.ADMIN)
                                        .permissionList(Set.of(createPermission, readPermission, updatePermission,
                                                        deletePermission))
                                        .build();
                        RoleEntity roleGuest = RoleEntity.builder()
                                        .roleEnum(RoleEnum.GUEST)
                                        .permissionList(Set.of(createPermission, readPermission))
                                        .build();
                        RoleEntity roleFrontDesk = RoleEntity.builder()
                                        .roleEnum(RoleEnum.FRONTDESK)
                                        .permissionList(Set.of(createPermission, readPermission, updatePermission,
                                                        deletePermission))
                                        .build();

                        /* Create Users */
                        UserEntity userRomel = UserEntity.builder()
                                        .username("Romel")
                                        .password("$2a$10$FI3pi7Bw0Wb/sSdc5J3K/u/zmYLvVcsKEOrt2D3LF99S0JEoPeYIW")
                                        .isEnabled(true)
                                        .accountNoExpired(true)
                                        .accountNoLocked(true)
                                        .credentialNoExpired(true)
                                        .roles(Set.of(roleAdmin))
                                        .build();

                        UserEntity userJuan = UserEntity.builder()
                                        .username("Juan")
                                        .password("$2a$10$FI3pi7Bw0Wb/sSdc5J3K/u/zmYLvVcsKEOrt2D3LF99S0JEoPeYIW")
                                        .isEnabled(true)
                                        .accountNoExpired(true)
                                        .accountNoLocked(true)
                                        .credentialNoExpired(true)
                                        .roles(Set.of(roleGuest))
                                        .build();

                        UserEntity userAndrea = UserEntity.builder()
                                        .username("Andrea")
                                        .password("$2a$10$FI3pi7Bw0Wb/sSdc5J3K/u/zmYLvVcsKEOrt2D3LF99S0JEoPeYIW")
                                        .isEnabled(true)
                                        .accountNoExpired(true)
                                        .accountNoLocked(true)
                                        .credentialNoExpired(true)
                                        .roles(Set.of(roleFrontDesk))
                                        .build();

                        UserEntity userAnyi = UserEntity.builder()
                                        .username("Anyi")
                                        .password("$2a$10$FI3pi7Bw0Wb/sSdc5J3K/u/zmYLvVcsKEOrt2D3LF99S0JEoPeYIW")
                                        .isEnabled(true)
                                        .accountNoExpired(true)
                                        .accountNoLocked(true)
                                        .credentialNoExpired(true)
                                        .roles(Set.of(roleGuest))
                                        .build();

                        userRepository.saveAll(List.of(userRomel, userJuan, userAndrea, userAnyi));
                };
        }
}
