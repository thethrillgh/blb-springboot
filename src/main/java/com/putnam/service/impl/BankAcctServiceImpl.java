package com.putnam.service.impl;

import com.putnam.service.BankAcctService;
import com.putnam.domain.BankAcct;
import com.putnam.repository.BankAcctRepository;
import com.putnam.service.dto.BankAcctDTO;
import com.putnam.service.mapper.BankAcctMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing BankAcct.
 */
@Service
@Transactional
public class BankAcctServiceImpl implements BankAcctService{

    private final Logger log = LoggerFactory.getLogger(BankAcctServiceImpl.class);

    private final BankAcctRepository bankAcctRepository;

    private final BankAcctMapper bankAcctMapper;

    public BankAcctServiceImpl(BankAcctRepository bankAcctRepository, BankAcctMapper bankAcctMapper) {
        this.bankAcctRepository = bankAcctRepository;
        this.bankAcctMapper = bankAcctMapper;
    }

    /**
     * Save a bankAcct.
     *
     * @param bankAcctDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BankAcctDTO save(BankAcctDTO bankAcctDTO) {
        log.debug("Request to save BankAcct : {}", bankAcctDTO);
        BankAcct bankAcct = bankAcctMapper.toEntity(bankAcctDTO);
        bankAcct = bankAcctRepository.save(bankAcct);
        return bankAcctMapper.toDto(bankAcct);
    }

    /**
     *  Get all the bankAccts.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<BankAcctDTO> findAll() {
        log.debug("Request to get all BankAccts");
        return bankAcctRepository.findAll().stream()
            .map(bankAcctMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     *  get all the bankAccts where AssocAccount is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<BankAcctDTO> findAllWhereAssocAccountIsNull() {
        log.debug("Request to get all bankAccts where AssocAccount is null");
        return StreamSupport
            .stream(bankAcctRepository.findAll().spliterator(), false)
            .filter(bankAcct -> bankAcct.getAssocAccount() == null)
            .map(bankAcctMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one bankAcct by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public BankAcctDTO findOne(Long id) {
        log.debug("Request to get BankAcct : {}", id);
        BankAcct bankAcct = bankAcctRepository.findOne(id);
        return bankAcctMapper.toDto(bankAcct);
    }

    /**
     *  Delete the  bankAcct by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BankAcct : {}", id);
        bankAcctRepository.delete(id);
    }
}
