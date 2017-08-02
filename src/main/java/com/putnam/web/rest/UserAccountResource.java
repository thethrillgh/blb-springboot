package com.putnam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.putnam.service.UserAccountService;
import com.putnam.web.rest.util.HeaderUtil;
import com.putnam.service.dto.UserAccountDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing UserAccount.
 */
@RestController
@RequestMapping("/api")
public class UserAccountResource {

    private final Logger log = LoggerFactory.getLogger(UserAccountResource.class);

    private static final String ENTITY_NAME = "userAccount";

    private final UserAccountService userAccountService;

    public UserAccountResource(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    /**
     * POST  /user-accounts : Create a new userAccount.
     *
     * @param userAccountDTO the userAccountDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userAccountDTO, or with status 400 (Bad Request) if the userAccount has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user-accounts")
    @Timed
    public ResponseEntity<UserAccountDTO> createUserAccount(@RequestBody UserAccountDTO userAccountDTO) throws URISyntaxException {
        log.debug("REST request to save UserAccount : {}", userAccountDTO);
        if (userAccountDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new userAccount cannot already have an ID")).body(null);
        }
        UserAccountDTO result = userAccountService.save(userAccountDTO);
        return ResponseEntity.created(new URI("/api/user-accounts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /user-accounts : Updates an existing userAccount.
     *
     * @param userAccountDTO the userAccountDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userAccountDTO,
     * or with status 400 (Bad Request) if the userAccountDTO is not valid,
     * or with status 500 (Internal Server Error) if the userAccountDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/user-accounts")
    @Timed
    public ResponseEntity<UserAccountDTO> updateUserAccount(@RequestBody UserAccountDTO userAccountDTO) throws URISyntaxException {
        log.debug("REST request to update UserAccount : {}", userAccountDTO);
        if (userAccountDTO.getId() == null) {
            return createUserAccount(userAccountDTO);
        }
        UserAccountDTO result = userAccountService.save(userAccountDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userAccountDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /user-accounts : get all the userAccounts.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of userAccounts in body
     */
    @GetMapping("/user-accounts")
    @Timed
    public List<UserAccountDTO> getAllUserAccounts() {
        log.debug("REST request to get all UserAccounts");
        return userAccountService.findAll();
    }

    /**
     * GET  /user-accounts/:id : get the "id" userAccount.
     *
     * @param id the id of the userAccountDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userAccountDTO, or with status 404 (Not Found)
     */
    @GetMapping("/user-accounts/{id}")
    @Timed
    public ResponseEntity<UserAccountDTO> getUserAccount(@PathVariable Long id) {
        log.debug("REST request to get UserAccount : {}", id);
        UserAccountDTO userAccountDTO = userAccountService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(userAccountDTO));
    }

    /**
     * DELETE  /user-accounts/:id : delete the "id" userAccount.
     *
     * @param id the id of the userAccountDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/user-accounts/{id}")
    @Timed
    public ResponseEntity<Void> deleteUserAccount(@PathVariable Long id) {
        log.debug("REST request to delete UserAccount : {}", id);
        userAccountService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
