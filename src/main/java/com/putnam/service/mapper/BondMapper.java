package com.putnam.service.mapper;

import com.putnam.domain.*;
import com.putnam.service.dto.BondDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Bond and its DTO BondDTO.
 */
@Mapper(componentModel = "spring", uses = {OrderMapper.class, })
public interface BondMapper extends EntityMapper <BondDTO, Bond> {

    @Mapping(source = "order.id", target = "orderId")
    BondDTO toDto(Bond bond); 

    @Mapping(source = "orderId", target = "order")
    Bond toEntity(BondDTO bondDTO); 
    default Bond fromId(Long id) {
        if (id == null) {
            return null;
        }
        Bond bond = new Bond();
        bond.setId(id);
        return bond;
    }
}
