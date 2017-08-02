package com.putnam.service.mapper;

import com.putnam.domain.*;
import com.putnam.service.dto.BankAcctDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity BankAcct and its DTO BankAcctDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BankAcctMapper extends EntityMapper <BankAcctDTO, BankAcct> {
    
    @Mapping(target = "assocAccount", ignore = true)
    BankAcct toEntity(BankAcctDTO bankAcctDTO); 
    default BankAcct fromId(Long id) {
        if (id == null) {
            return null;
        }
        BankAcct bankAcct = new BankAcct();
        bankAcct.setId(id);
        return bankAcct;
    }
}
