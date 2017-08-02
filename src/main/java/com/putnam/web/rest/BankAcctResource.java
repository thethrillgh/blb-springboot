package com.putnam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.putnam.service.BankAcctService;
import com.putnam.web.rest.util.HeaderUtil;
import com.putnam.service.dto.BankAcctDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing BankAcct.
 */
@RestController
@RequestMapping("/api")
public class BankAcctResource {

    private final Logger log = LoggerFactory.getLogger(BankAcctResource.class);

    private static final String ENTITY_NAME = "bankAcct";

    private final BankAcctService bankAcctService;

    public BankAcctResource(BankAcctService bankAcctService) {
        this.bankAcctService = bankAcctService;
    }

    /**
     * POST  /bank-accts : Create a new bankAcct.
     *
     * @param bankAcctDTO the bankAcctDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new bankAcctDTO, or with status 400 (Bad Request) if the bankAcct has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/bank-accts")
    @Timed
    public ResponseEntity<BankAcctDTO> createBankAcct(@RequestBody BankAcctDTO bankAcctDTO) throws URISyntaxException {
        log.debug("REST request to save BankAcct : {}", bankAcctDTO);
        if (bankAcctDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new bankAcct cannot already have an ID")).body(null);
        }
        BankAcctDTO result = bankAcctService.save(bankAcctDTO);
        return ResponseEntity.created(new URI("/api/bank-accts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /bank-accts : Updates an existing bankAcct.
     *
     * @param bankAcctDTO the bankAcctDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated bankAcctDTO,
     * or with status 400 (Bad Request) if the bankAcctDTO is not valid,
     * or with status 500 (Internal Server Error) if the bankAcctDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/bank-accts")
    @Timed
    public ResponseEntity<BankAcctDTO> updateBankAcct(@RequestBody BankAcctDTO bankAcctDTO) throws URISyntaxException {
        log.debug("REST request to update BankAcct : {}", bankAcctDTO);
        if (bankAcctDTO.getId() == null) {
            return createBankAcct(bankAcctDTO);
        }
        BankAcctDTO result = bankAcctService.save(bankAcctDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, bankAcctDTO.getId().toString()))
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
    public List<BankAcctDTO> getAllBankAccts(@RequestParam(required = false) String filter) {
        if ("assocaccount-is-null".equals(filter)) {
            log.debug("REST request to get all BankAccts where assocAccount is null");
            return bankAcctService.findAllWhereAssocAccountIsNull();
        }
        log.debug("REST request to get all BankAccts");
        return bankAcctService.findAll();
    }

    /**
     * GET  /bank-accts/:id : get the "id" bankAcct.
     *
     * @param id the id of the bankAcctDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bankAcctDTO, or with status 404 (Not Found)
     */
    @GetMapping("/bank-accts/{id}")
    @Timed
    public ResponseEntity<BankAcctDTO> getBankAcct(@PathVariable Long id) {
        log.debug("REST request to get BankAcct : {}", id);
        BankAcctDTO bankAcctDTO = bankAcctService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(bankAcctDTO));
    }

    /**
     * DELETE  /bank-accts/:id : delete the "id" bankAcct.
     *
     * @param id the id of the bankAcctDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/bank-accts/{id}")
    @Timed
    public ResponseEntity<Void> deleteBankAcct(@PathVariable Long id) {
        log.debug("REST request to delete BankAcct : {}", id);
        bankAcctService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
