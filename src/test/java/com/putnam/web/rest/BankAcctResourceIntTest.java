package com.putnam.web.rest;

import com.putnam.BuylocalbondsApp;

import com.putnam.domain.BankAcct;
import com.putnam.repository.BankAcctRepository;
import com.putnam.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the BankAcctResource REST controller.
 *
 * @see BankAcctResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BuylocalbondsApp.class)
public class BankAcctResourceIntTest {

    private static final String DEFAULT_ACCT_NUM = "AAAAAAAAAA";
    private static final String UPDATED_ACCT_NUM = "BBBBBBBBBB";

    private static final String DEFAULT_ROUTING_NUM = "AAAAAAAAAA";
    private static final String UPDATED_ROUTING_NUM = "BBBBBBBBBB";

    private static final String DEFAULT_ACCT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_ACCT_TYPE = "BBBBBBBBBB";

    @Autowired
    private BankAcctRepository bankAcctRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBankAcctMockMvc;

    private BankAcct bankAcct;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        BankAcctResource bankAcctResource = new BankAcctResource(bankAcctRepository);
        this.restBankAcctMockMvc = MockMvcBuilders.standaloneSetup(bankAcctResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BankAcct createEntity(EntityManager em) {
        BankAcct bankAcct = new BankAcct()
            .acctNum(DEFAULT_ACCT_NUM)
            .routingNum(DEFAULT_ROUTING_NUM)
            .acctType(DEFAULT_ACCT_TYPE);
        return bankAcct;
    }

    @Before
    public void initTest() {
        bankAcct = createEntity(em);
    }

    @Test
    @Transactional
    public void createBankAcct() throws Exception {
        int databaseSizeBeforeCreate = bankAcctRepository.findAll().size();

        // Create the BankAcct
        restBankAcctMockMvc.perform(post("/api/bank-accts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bankAcct)))
            .andExpect(status().isCreated());

        // Validate the BankAcct in the database
        List<BankAcct> bankAcctList = bankAcctRepository.findAll();
        assertThat(bankAcctList).hasSize(databaseSizeBeforeCreate + 1);
        BankAcct testBankAcct = bankAcctList.get(bankAcctList.size() - 1);
        assertThat(testBankAcct.getAcctNum()).isEqualTo(DEFAULT_ACCT_NUM);
        assertThat(testBankAcct.getRoutingNum()).isEqualTo(DEFAULT_ROUTING_NUM);
        assertThat(testBankAcct.getAcctType()).isEqualTo(DEFAULT_ACCT_TYPE);
    }

    @Test
    @Transactional
    public void createBankAcctWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bankAcctRepository.findAll().size();

        // Create the BankAcct with an existing ID
        bankAcct.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBankAcctMockMvc.perform(post("/api/bank-accts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bankAcct)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<BankAcct> bankAcctList = bankAcctRepository.findAll();
        assertThat(bankAcctList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllBankAccts() throws Exception {
        // Initialize the database
        bankAcctRepository.saveAndFlush(bankAcct);

        // Get all the bankAcctList
        restBankAcctMockMvc.perform(get("/api/bank-accts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bankAcct.getId().intValue())))
            .andExpect(jsonPath("$.[*].acctNum").value(hasItem(DEFAULT_ACCT_NUM.toString())))
            .andExpect(jsonPath("$.[*].routingNum").value(hasItem(DEFAULT_ROUTING_NUM.toString())))
            .andExpect(jsonPath("$.[*].acctType").value(hasItem(DEFAULT_ACCT_TYPE.toString())));
    }

    @Test
    @Transactional
    public void getBankAcct() throws Exception {
        // Initialize the database
        bankAcctRepository.saveAndFlush(bankAcct);

        // Get the bankAcct
        restBankAcctMockMvc.perform(get("/api/bank-accts/{id}", bankAcct.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(bankAcct.getId().intValue()))
            .andExpect(jsonPath("$.acctNum").value(DEFAULT_ACCT_NUM.toString()))
            .andExpect(jsonPath("$.routingNum").value(DEFAULT_ROUTING_NUM.toString()))
            .andExpect(jsonPath("$.acctType").value(DEFAULT_ACCT_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBankAcct() throws Exception {
        // Get the bankAcct
        restBankAcctMockMvc.perform(get("/api/bank-accts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBankAcct() throws Exception {
        // Initialize the database
        bankAcctRepository.saveAndFlush(bankAcct);
        int databaseSizeBeforeUpdate = bankAcctRepository.findAll().size();

        // Update the bankAcct
        BankAcct updatedBankAcct = bankAcctRepository.findOne(bankAcct.getId());
        updatedBankAcct
            .acctNum(UPDATED_ACCT_NUM)
            .routingNum(UPDATED_ROUTING_NUM)
            .acctType(UPDATED_ACCT_TYPE);

        restBankAcctMockMvc.perform(put("/api/bank-accts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBankAcct)))
            .andExpect(status().isOk());

        // Validate the BankAcct in the database
        List<BankAcct> bankAcctList = bankAcctRepository.findAll();
        assertThat(bankAcctList).hasSize(databaseSizeBeforeUpdate);
        BankAcct testBankAcct = bankAcctList.get(bankAcctList.size() - 1);
        assertThat(testBankAcct.getAcctNum()).isEqualTo(UPDATED_ACCT_NUM);
        assertThat(testBankAcct.getRoutingNum()).isEqualTo(UPDATED_ROUTING_NUM);
        assertThat(testBankAcct.getAcctType()).isEqualTo(UPDATED_ACCT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingBankAcct() throws Exception {
        int databaseSizeBeforeUpdate = bankAcctRepository.findAll().size();

        // Create the BankAcct

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBankAcctMockMvc.perform(put("/api/bank-accts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bankAcct)))
            .andExpect(status().isCreated());

        // Validate the BankAcct in the database
        List<BankAcct> bankAcctList = bankAcctRepository.findAll();
        assertThat(bankAcctList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteBankAcct() throws Exception {
        // Initialize the database
        bankAcctRepository.saveAndFlush(bankAcct);
        int databaseSizeBeforeDelete = bankAcctRepository.findAll().size();

        // Get the bankAcct
        restBankAcctMockMvc.perform(delete("/api/bank-accts/{id}", bankAcct.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<BankAcct> bankAcctList = bankAcctRepository.findAll();
        assertThat(bankAcctList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BankAcct.class);
        BankAcct bankAcct1 = new BankAcct();
        bankAcct1.setId(1L);
        BankAcct bankAcct2 = new BankAcct();
        bankAcct2.setId(bankAcct1.getId());
        assertThat(bankAcct1).isEqualTo(bankAcct2);
        bankAcct2.setId(2L);
        assertThat(bankAcct1).isNotEqualTo(bankAcct2);
        bankAcct1.setId(null);
        assertThat(bankAcct1).isNotEqualTo(bankAcct2);
    }
}
