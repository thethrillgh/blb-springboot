package com.putnam.service.mapper;

import com.putnam.domain.*;
import com.putnam.service.dto.UserAddressDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity UserAddress and its DTO UserAddressDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserAddressMapper extends EntityMapper <UserAddressDTO, UserAddress> {
    
    @Mapping(target = "assocAcct", ignore = true)
    UserAddress toEntity(UserAddressDTO userAddressDTO); 
    default UserAddress fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserAddress userAddress = new UserAddress();
        userAddress.setId(id);
        return userAddress;
    }
}
