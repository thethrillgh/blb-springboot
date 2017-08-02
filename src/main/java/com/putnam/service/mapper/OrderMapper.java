package com.putnam.service.mapper;

import com.putnam.domain.*;
import com.putnam.service.dto.OrderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Order and its DTO OrderDTO.
 */
@Mapper(componentModel = "spring", uses = {UserAccountMapper.class, })
public interface OrderMapper extends EntityMapper <OrderDTO, Order> {

    @Mapping(source = "assocAcct.id", target = "assocAcctId")
    OrderDTO toDto(Order order); 
    @Mapping(target = "assocBonds", ignore = true)

    @Mapping(source = "assocAcctId", target = "assocAcct")
    Order toEntity(OrderDTO orderDTO); 
    default Order fromId(Long id) {
        if (id == null) {
            return null;
        }
        Order order = new Order();
        order.setId(id);
        return order;
    }
}
