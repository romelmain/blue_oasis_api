package com.hbo.blue_oasis.service;

import org.springframework.stereotype.Service;

import com.hbo.blue_oasis.persistence.entity.PermissionEntity;
import com.hbo.blue_oasis.persistence.repository.PermissionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PermissionService {

    private final PermissionRepository permissionRepository;

    /*
     * public Optional<PermissionEntity> getPermissionsPath(){
     * 
     * }
     */

    /*
     * public Optional<Cart> getCartByUserId(Integer userId) {
     * Cart cart = new Cart();
     * Optional<Cart> oCart;
     * try {
     * cart = cartRepository.validateCart(userId);
     * if (cart != null) {
     * oCart = Optional.of(cart);
     * } else {
     * oCart = Optional.empty();
     * }
     * } catch (Exception e) {
     * oCart = Optional.empty();
     * }
     * return oCart;
     * }
     */

}
