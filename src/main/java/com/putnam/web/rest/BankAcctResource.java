package com.putnam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.putnam.domain.BankAcct;

import com.putnam.repository.BankAcctRepository;
import com.putnam.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing BankAcct.
 */
@RestController
@RequestMapping("/api")
public class BankAcctResource {

    private final Logger log = LoggerFactory.getLogger(BankAcctResource.class);

    private static final String ENTITY_NAME = "bankAcct";

    private final BankAcctRepository bankAcctRepository;

    public BankAcctResource(BankAcctRepository bankAcctRepository) {
        this.bankAcctRepository = bankAcctRepository;
    }

    /**
     * POST  /bank-accts : Create a new bankAcct.
     *
     * @param bankAcct the bankAcct to create
     * @return the ResponseEntity with status 201 (Created) and with body the new bankAcct, or with status 400 (Bad Request) if the bankAcct has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/bank-accts")
    @Timed
    public ResponseEntity<BankAcct> createBankAcct(@RequestBody BankAcct bankAcct) throws URISyntaxException {
        log.debug("REST request to save BankAcct : {}", bankAcct);
        if (bankAcct.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new bankAcct cannot already have an ID")).body(null);
        }
        BankAcct result = bankAcctRepository.save(bankAcct);
        return ResponseEntity.created(new URI("/api/bank-accts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /bank-accts : Updates an existing bankAcct.
     *
     * @param bankAcct the bankAcct to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated bankAcct,
     * or with status 400 (Bad Request) if the bankAcct is not valid,
     * or with status 500 (Internal Server Error) if the bankAcct couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/bank-accts")
    @Timed
    public ResponseEntity<BankAcct> updateBankAcct(@RequestBody BankAcct bankAcct) throws URISyntaxException {
        log.debug("REST request to update BankAcct : {}", bankAcct);
        if (bankAcct.getId() == null) {
            return createBankAcct(bankAcct);
        }
        BankAcct result = bankAcctRepository.save(bankAcct);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, bankAcct.getId().toString()))
            .body(result);
    }

    /**
     * GET  /bank-accts : get all the bankAccts.
     *
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of bankAccts in body
     */
    @GetMapping("/bank-accts")
    @Timed
    public List<BankAcct> getAllBankAccts(@RequestParam(required = false) String filter) {
        if ("assocaccount-is-null".equals(filter)) {
            log.debug("REST request to get all BankAccts where assocAccount is null");
            return StreamSupport
                .stream(bankAcctRepository.findAll().spliterator(), false)
                .filter(bankAcct -> bankAcct.getAssocAccount() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all BankAccts");
        return bankAcctRepository.findAll();
    }

    /**
     * GET  /bank-accts/:id : get the "id" bankAcct.
     *
     * @param id the id of the bankAcct to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bankAcct, or with status 404 (Not Found)
     */
    @GetMapping("/bank-accts/{id}")
    @Timed
    public ResponseEntity<BankAcct> getBankAcct(@PathVariable Long id) {
        log.debug("REST request to get BankAcct : {}", id);
        BankAcct bankAcct = bankAcctRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(bankAcct));
    }

    /**
     * DELETE  /bank-accts/:id : delete the "id" bankAcct.
     *
     * @param id the id of the bankAcct to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/bank-accts/{id}")
    @Timed
    public ResponseEntity<Void> deleteBankAcct(@PathVariable Long id) {
        log.debug("REST request to delete BankAcct : {}", id);
        bankAcctRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
