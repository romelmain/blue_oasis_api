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
                        PermissionEntity roomsPermission = PermissionEntity.builder()
                                        .name("ROOMS")
                                        .menu(true)
                                        .path("/prueba")
                                        .build();

                        PermissionEntity bookingPermission = PermissionEntity.builder()
                                        .name("BOOKING")
                                        .menu(false)
                                        .build();

                        PermissionEntity roomDetailsPermission = PermissionEntity.builder()
                                        .name("ROOM DETAILS")
                                        .menu(false)
                                        .build();

                        PermissionEntity paymentPermission = PermissionEntity.builder()
                                        .name("PAYMENT")
                                        .menu(false)
                                        .build();

                        PermissionEntity bookingDetailsPermission = PermissionEntity.builder()
                                        .name("BOOKING DETAILS")
                                        .menu(true)
                                        .path("/prueba")
                                        .build();

                        PermissionEntity bookingsPermission = PermissionEntity.builder()
                                        .name("BOOKINGS")
                                        .menu(true)
                                        .path("/prueba")
                                        .build();

                        PermissionEntity checkInPermission = PermissionEntity.builder()
                                        .name("CHECK IN")
                                        .menu(false)
                                        .build();

                        PermissionEntity checkOutPermission = PermissionEntity.builder()
                                        .name("CHECKOUT")
                                        .menu(false)
                                        .build();

                        /* Create Roles */
                        RoleEntity roleAdmin = RoleEntity.builder()
                                        .roleEnum(RoleEnum.ADMIN)
                                        .permissionList(Set.of(roomsPermission, roomDetailsPermission,
                                                        bookingPermission, bookingDetailsPermission, bookingsPermission,
                                                        paymentPermission, checkInPermission, checkOutPermission))
                                        .build();
                        RoleEntity roleGuest = RoleEntity.builder()
                                        .roleEnum(RoleEnum.GUEST)
                                        .permissionList(Set.of(roomsPermission, roomDetailsPermission,
                                                        bookingPermission, bookingDetailsPermission, paymentPermission))
                                        .build();
                        RoleEntity roleFrontDesk = RoleEntity.builder()
                                        .roleEnum(RoleEnum.FRONTDESK)
                                        .permissionList(Set.of(roomsPermission, roomDetailsPermission,
                                                        bookingDetailsPermission, bookingsPermission,
                                                        checkInPermission, checkOutPermission))
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
