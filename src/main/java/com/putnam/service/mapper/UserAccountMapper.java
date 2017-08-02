package com.putnam.service.mapper;

import com.putnam.domain.*;
import com.putnam.service.dto.UserAccountDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity UserAccount and its DTO UserAccountDTO.
 */
@Mapper(componentModel = "spring", uses = {UserAddressMapper.class, BankAcctMapper.class, })
public interface UserAccountMapper extends EntityMapper <UserAccountDTO, UserAccount> {

    @Mapping(source = "userAddress.id", target = "userAddressId")

    @Mapping(source = "assocBankAccount.id", target = "assocBankAccountId")
    UserAccountDTO toDto(UserAccount userAccount); 

    @Mapping(source = "userAddressId", target = "userAddress")

    @Mapping(source = "assocBankAccountId", target = "assocBankAccount")
    @Mapping(target = "orders", ignore = true)
    UserAccount toEntity(UserAccountDTO userAccountDTO); 
    default UserAccount fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserAccount userAccount = new UserAccount();
        userAccount.setId(id);
        return userAccount;
    }
}
