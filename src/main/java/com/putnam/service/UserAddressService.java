package com.putnam.service;

import com.putnam.service.dto.UserAddressDTO;
import java.util.List;

/**
 * Service Interface for managing UserAddress.
 */
public interface UserAddressService {

    /**
     * Save a userAddress.
     *
     * @param userAddressDTO the entity to save
     * @return the persisted entity
     */
    UserAddressDTO save(UserAddressDTO userAddressDTO);

    /**
     *  Get all the userAddresses.
     *
     *  @return the list of entities
     */
    List<UserAddressDTO> findAll();
    /**
     *  Get all the UserAddressDTO where AssocAcct is null.
     *
     *  @return the list of entities
     */
    List<UserAddressDTO> findAllWhereAssocAcctIsNull();

    /**
     *  Get the "id" userAddress.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    UserAddressDTO findOne(Long id);

    /**
     *  Delete the "id" userAddress.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
