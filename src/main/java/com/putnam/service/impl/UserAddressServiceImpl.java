package com.putnam.service.impl;

import com.putnam.service.UserAddressService;
import com.putnam.domain.UserAddress;
import com.putnam.repository.UserAddressRepository;
import com.putnam.service.dto.UserAddressDTO;
import com.putnam.service.mapper.UserAddressMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing UserAddress.
 */
@Service
@Transactional
public class UserAddressServiceImpl implements UserAddressService{

    private final Logger log = LoggerFactory.getLogger(UserAddressServiceImpl.class);

    private final UserAddressRepository userAddressRepository;

    private final UserAddressMapper userAddressMapper;

    public UserAddressServiceImpl(UserAddressRepository userAddressRepository, UserAddressMapper userAddressMapper) {
        this.userAddressRepository = userAddressRepository;
        this.userAddressMapper = userAddressMapper;
    }

    /**
     * Save a userAddress.
     *
     * @param userAddressDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public UserAddressDTO save(UserAddressDTO userAddressDTO) {
        log.debug("Request to save UserAddress : {}", userAddressDTO);
        UserAddress userAddress = userAddressMapper.toEntity(userAddressDTO);
        userAddress = userAddressRepository.save(userAddress);
        return userAddressMapper.toDto(userAddress);
    }

    /**
     *  Get all the userAddresses.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserAddressDTO> findAll() {
        log.debug("Request to get all UserAddresses");
        return userAddressRepository.findAll().stream()
            .map(userAddressMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     *  get all the userAddresses where AssocAcct is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<UserAddressDTO> findAllWhereAssocAcctIsNull() {
        log.debug("Request to get all userAddresses where AssocAcct is null");
        return StreamSupport
            .stream(userAddressRepository.findAll().spliterator(), false)
            .filter(userAddress -> userAddress.getAssocAcct() == null)
            .map(userAddressMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one userAddress by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public UserAddressDTO findOne(Long id) {
        log.debug("Request to get UserAddress : {}", id);
        UserAddress userAddress = userAddressRepository.findOne(id);
        return userAddressMapper.toDto(userAddress);
    }

    /**
     *  Delete the  userAddress by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserAddress : {}", id);
        userAddressRepository.delete(id);
    }
}
