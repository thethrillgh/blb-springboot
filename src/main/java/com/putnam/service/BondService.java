package com.putnam.service;

import com.putnam.service.dto.BondDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Bond.
 */
public interface BondService {

    /**
     * Save a bond.
     *
     * @param bondDTO the entity to save
     * @return the persisted entity
     */
    BondDTO save(BondDTO bondDTO);

    /**
     *  Get all the bonds.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<BondDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" bond.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    BondDTO findOne(Long id);

    /**
     *  Delete the "id" bond.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
