package com.putnam.service.impl;

import com.putnam.service.BondService;
import com.putnam.domain.Bond;
import com.putnam.repository.BondRepository;
import com.putnam.service.dto.BondDTO;
import com.putnam.service.mapper.BondMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Bond.
 */
@Service
@Transactional
public class BondServiceImpl implements BondService{

    private final Logger log = LoggerFactory.getLogger(BondServiceImpl.class);

    private final BondRepository bondRepository;

    private final BondMapper bondMapper;

    public BondServiceImpl(BondRepository bondRepository, BondMapper bondMapper) {
        this.bondRepository = bondRepository;
        this.bondMapper = bondMapper;
    }

    /**
     * Save a bond.
     *
     * @param bondDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BondDTO save(BondDTO bondDTO) {
        log.debug("Request to save Bond : {}", bondDTO);
        Bond bond = bondMapper.toEntity(bondDTO);
        bond = bondRepository.save(bond);
        return bondMapper.toDto(bond);
    }

    /**
     *  Get all the bonds.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BondDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Bonds");
        return bondRepository.findAll(pageable)
            .map(bondMapper::toDto);
    }

    /**
     *  Get one bond by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public BondDTO findOne(Long id) {
        log.debug("Request to get Bond : {}", id);
        Bond bond = bondRepository.findOne(id);
        return bondMapper.toDto(bond);
    }

    /**
     *  Delete the  bond by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Bond : {}", id);
        bondRepository.delete(id);
    }
}
