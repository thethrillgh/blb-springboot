package com.putnam.service;

import com.putnam.service.dto.BankAcctDTO;
import java.util.List;

/**
 * Service Interface for managing BankAcct.
 */
public interface BankAcctService {

    /**
     * Save a bankAcct.
     *
     * @param bankAcctDTO the entity to save
     * @return the persisted entity
     */
    BankAcctDTO save(BankAcctDTO bankAcctDTO);

    /**
     *  Get all the bankAccts.
     *
     *  @return the list of entities
     */
    List<BankAcctDTO> findAll();
    /**
     *  Get all the BankAcctDTO where AssocAccount is null.
     *
     *  @return the list of entities
     */
    List<BankAcctDTO> findAllWhereAssocAccountIsNull();

    /**
     *  Get the "id" bankAcct.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    BankAcctDTO findOne(Long id);

    /**
     *  Delete the "id" bankAcct.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
