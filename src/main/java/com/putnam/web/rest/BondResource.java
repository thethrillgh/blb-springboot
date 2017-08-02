package com.putnam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.putnam.service.BondService;
import com.putnam.web.rest.util.HeaderUtil;
import com.putnam.web.rest.util.PaginationUtil;
import com.putnam.service.dto.BondDTO;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Bond.
 */
@RestController
@RequestMapping("/api")
public class BondResource {

    private final Logger log = LoggerFactory.getLogger(BondResource.class);

    private static final String ENTITY_NAME = "bond";

    private final BondService bondService;

    public BondResource(BondService bondService) {
        this.bondService = bondService;
    }

    /**
     * POST  /bonds : Create a new bond.
     *
     * @param bondDTO the bondDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new bondDTO, or with status 400 (Bad Request) if the bond has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/bonds")
    @Timed
    public ResponseEntity<BondDTO> createBond(@RequestBody BondDTO bondDTO) throws URISyntaxException {
        log.debug("REST request to save Bond : {}", bondDTO);
        if (bondDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new bond cannot already have an ID")).body(null);
        }
        BondDTO result = bondService.save(bondDTO);
        return ResponseEntity.created(new URI("/api/bonds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /bonds : Updates an existing bond.
     *
     * @param bondDTO the bondDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated bondDTO,
     * or with status 400 (Bad Request) if the bondDTO is not valid,
     * or with status 500 (Internal Server Error) if the bondDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/bonds")
    @Timed
    public ResponseEntity<BondDTO> updateBond(@RequestBody BondDTO bondDTO) throws URISyntaxException {
        log.debug("REST request to update Bond : {}", bondDTO);
        if (bondDTO.getId() == null) {
            return createBond(bondDTO);
        }
        BondDTO result = bondService.save(bondDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, bondDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /bonds : get all the bonds.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of bonds in body
     */
    @GetMapping("/bonds")
    @Timed
    public ResponseEntity<List<BondDTO>> getAllBonds(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Bonds");
        Page<BondDTO> page = bondService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/bonds");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /bonds/:id : get the "id" bond.
     *
     * @param id the id of the bondDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bondDTO, or with status 404 (Not Found)
     */
    @GetMapping("/bonds/{id}")
    @Timed
    public ResponseEntity<BondDTO> getBond(@PathVariable Long id) {
        log.debug("REST request to get Bond : {}", id);
        BondDTO bondDTO = bondService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(bondDTO));
    }

    /**
     * DELETE  /bonds/:id : delete the "id" bond.
     *
     * @param id the id of the bondDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/bonds/{id}")
    @Timed
    public ResponseEntity<Void> deleteBond(@PathVariable Long id) {
        log.debug("REST request to delete Bond : {}", id);
        bondService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
