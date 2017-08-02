package com.putnam.web.rest;

import com.putnam.BuylocalbondsApp;

import com.putnam.domain.UserAccount;
import com.putnam.repository.UserAccountRepository;
import com.putnam.service.UserAccountService;
import com.putnam.service.dto.UserAccountDTO;
import com.putnam.service.mapper.UserAccountMapper;
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
 * Test class for the UserAccountResource REST controller.
 *
 * @see UserAccountResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BuylocalbondsApp.class)
public class UserAccountResourceIntTest {

    private static final Long DEFAULT_ACCT_ID = 1L;
    private static final Long UPDATED_ACCT_ID = 2L;

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUM = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUM = "BBBBBBBBBB";

    private static final String DEFAULT_ACCT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_ACCT_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_ACCT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_ACCT_PASSWORD = "BBBBBBBBBB";

    private static final String DEFAULT_ACCT_SSN = "AAAAAAAAAA";
    private static final String UPDATED_ACCT_SSN = "BBBBBBBBBB";

    private static final String DEFAULT_SSN_LAST_FOUR = "AAAAAAAAAA";
    private static final String UPDATED_SSN_LAST_FOUR = "BBBBBBBBBB";

    private static final String DEFAULT_PASS_SALT = "AAAAAAAAAA";
    private static final String UPDATED_PASS_SALT = "BBBBBBBBBB";

    private static final Double DEFAULT_ACCT_BALANCE = 1D;
    private static final Double UPDATED_ACCT_BALANCE = 2D;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUserAccountMockMvc;

    private UserAccount userAccount;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        UserAccountResource userAccountResource = new UserAccountResource(userAccountService);
        this.restUserAccountMockMvc = MockMvcBuilders.standaloneSetup(userAccountResource)
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
    public static UserAccount createEntity(EntityManager em) {
        UserAccount userAccount = new UserAccount()
            .acctID(DEFAULT_ACCT_ID)
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .phoneNum(DEFAULT_PHONE_NUM)
            .acctEmail(DEFAULT_ACCT_EMAIL)
            .acctPassword(DEFAULT_ACCT_PASSWORD)
            .acctSSN(DEFAULT_ACCT_SSN)
            .ssnLastFour(DEFAULT_SSN_LAST_FOUR)
            .passSalt(DEFAULT_PASS_SALT)
            .acctBalance(DEFAULT_ACCT_BALANCE);
        return userAccount;
    }

    @Before
    public void initTest() {
        userAccount = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserAccount() throws Exception {
        int databaseSizeBeforeCreate = userAccountRepository.findAll().size();

        // Create the UserAccount
        UserAccountDTO userAccountDTO = userAccountMapper.toDto(userAccount);
        restUserAccountMockMvc.perform(post("/api/user-accounts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userAccountDTO)))
            .andExpect(status().isCreated());

        // Validate the UserAccount in the database
        List<UserAccount> userAccountList = userAccountRepository.findAll();
        assertThat(userAccountList).hasSize(databaseSizeBeforeCreate + 1);
        UserAccount testUserAccount = userAccountList.get(userAccountList.size() - 1);
        assertThat(testUserAccount.getAcctID()).isEqualTo(DEFAULT_ACCT_ID);
        assertThat(testUserAccount.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testUserAccount.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testUserAccount.getPhoneNum()).isEqualTo(DEFAULT_PHONE_NUM);
        assertThat(testUserAccount.getAcctEmail()).isEqualTo(DEFAULT_ACCT_EMAIL);
        assertThat(testUserAccount.getAcctPassword()).isEqualTo(DEFAULT_ACCT_PASSWORD);
        assertThat(testUserAccount.getAcctSSN()).isEqualTo(DEFAULT_ACCT_SSN);
        assertThat(testUserAccount.getSsnLastFour()).isEqualTo(DEFAULT_SSN_LAST_FOUR);
        assertThat(testUserAccount.getPassSalt()).isEqualTo(DEFAULT_PASS_SALT);
        assertThat(testUserAccount.getAcctBalance()).isEqualTo(DEFAULT_ACCT_BALANCE);
    }

    @Test
    @Transactional
    public void createUserAccountWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userAccountRepository.findAll().size();

        // Create the UserAccount with an existing ID
        userAccount.setId(1L);
        UserAccountDTO userAccountDTO = userAccountMapper.toDto(userAccount);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserAccountMockMvc.perform(post("/api/user-accounts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userAccountDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<UserAccount> userAccountList = userAccountRepository.findAll();
        assertThat(userAccountList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllUserAccounts() throws Exception {
        // Initialize the database
        userAccountRepository.saveAndFlush(userAccount);

        // Get all the userAccountList
        restUserAccountMockMvc.perform(get("/api/user-accounts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userAccount.getId().intValue())))
            .andExpect(jsonPath("$.[*].acctID").value(hasItem(DEFAULT_ACCT_ID.intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME.toString())))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].phoneNum").value(hasItem(DEFAULT_PHONE_NUM.toString())))
            .andExpect(jsonPath("$.[*].acctEmail").value(hasItem(DEFAULT_ACCT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].acctPassword").value(hasItem(DEFAULT_ACCT_PASSWORD.toString())))
            .andExpect(jsonPath("$.[*].acctSSN").value(hasItem(DEFAULT_ACCT_SSN.toString())))
            .andExpect(jsonPath("$.[*].ssnLastFour").value(hasItem(DEFAULT_SSN_LAST_FOUR.toString())))
            .andExpect(jsonPath("$.[*].passSalt").value(hasItem(DEFAULT_PASS_SALT.toString())))
            .andExpect(jsonPath("$.[*].acctBalance").value(hasItem(DEFAULT_ACCT_BALANCE.doubleValue())));
    }

    @Test
    @Transactional
    public void getUserAccount() throws Exception {
        // Initialize the database
        userAccountRepository.saveAndFlush(userAccount);

        // Get the userAccount
        restUserAccountMockMvc.perform(get("/api/user-accounts/{id}", userAccount.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userAccount.getId().intValue()))
            .andExpect(jsonPath("$.acctID").value(DEFAULT_ACCT_ID.intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME.toString()))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME.toString()))
            .andExpect(jsonPath("$.phoneNum").value(DEFAULT_PHONE_NUM.toString()))
            .andExpect(jsonPath("$.acctEmail").value(DEFAULT_ACCT_EMAIL.toString()))
            .andExpect(jsonPath("$.acctPassword").value(DEFAULT_ACCT_PASSWORD.toString()))
            .andExpect(jsonPath("$.acctSSN").value(DEFAULT_ACCT_SSN.toString()))
            .andExpect(jsonPath("$.ssnLastFour").value(DEFAULT_SSN_LAST_FOUR.toString()))
            .andExpect(jsonPath("$.passSalt").value(DEFAULT_PASS_SALT.toString()))
            .andExpect(jsonPath("$.acctBalance").value(DEFAULT_ACCT_BALANCE.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingUserAccount() throws Exception {
        // Get the userAccount
        restUserAccountMockMvc.perform(get("/api/user-accounts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserAccount() throws Exception {
        // Initialize the database
        userAccountRepository.saveAndFlush(userAccount);
        int databaseSizeBeforeUpdate = userAccountRepository.findAll().size();

        // Update the userAccount
        UserAccount updatedUserAccount = userAccountRepository.findOne(userAccount.getId());
        updatedUserAccount
            .acctID(UPDATED_ACCT_ID)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .phoneNum(UPDATED_PHONE_NUM)
            .acctEmail(UPDATED_ACCT_EMAIL)
            .acctPassword(UPDATED_ACCT_PASSWORD)
            .acctSSN(UPDATED_ACCT_SSN)
            .ssnLastFour(UPDATED_SSN_LAST_FOUR)
            .passSalt(UPDATED_PASS_SALT)
            .acctBalance(UPDATED_ACCT_BALANCE);
        UserAccountDTO userAccountDTO = userAccountMapper.toDto(updatedUserAccount);

        restUserAccountMockMvc.perform(put("/api/user-accounts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userAccountDTO)))
            .andExpect(status().isOk());

        // Validate the UserAccount in the database
        List<UserAccount> userAccountList = userAccountRepository.findAll();
        assertThat(userAccountList).hasSize(databaseSizeBeforeUpdate);
        UserAccount testUserAccount = userAccountList.get(userAccountList.size() - 1);
        assertThat(testUserAccount.getAcctID()).isEqualTo(UPDATED_ACCT_ID);
        assertThat(testUserAccount.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testUserAccount.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testUserAccount.getPhoneNum()).isEqualTo(UPDATED_PHONE_NUM);
        assertThat(testUserAccount.getAcctEmail()).isEqualTo(UPDATED_ACCT_EMAIL);
        assertThat(testUserAccount.getAcctPassword()).isEqualTo(UPDATED_ACCT_PASSWORD);
        assertThat(testUserAccount.getAcctSSN()).isEqualTo(UPDATED_ACCT_SSN);
        assertThat(testUserAccount.getSsnLastFour()).isEqualTo(UPDATED_SSN_LAST_FOUR);
        assertThat(testUserAccount.getPassSalt()).isEqualTo(UPDATED_PASS_SALT);
        assertThat(testUserAccount.getAcctBalance()).isEqualTo(UPDATED_ACCT_BALANCE);
    }

    @Test
    @Transactional
    public void updateNonExistingUserAccount() throws Exception {
        int databaseSizeBeforeUpdate = userAccountRepository.findAll().size();

        // Create the UserAccount
        UserAccountDTO userAccountDTO = userAccountMapper.toDto(userAccount);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUserAccountMockMvc.perform(put("/api/user-accounts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userAccountDTO)))
            .andExpect(status().isCreated());

        // Validate the UserAccount in the database
        List<UserAccount> userAccountList = userAccountRepository.findAll();
        assertThat(userAccountList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteUserAccount() throws Exception {
        // Initialize the database
        userAccountRepository.saveAndFlush(userAccount);
        int databaseSizeBeforeDelete = userAccountRepository.findAll().size();

        // Get the userAccount
        restUserAccountMockMvc.perform(delete("/api/user-accounts/{id}", userAccount.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UserAccount> userAccountList = userAccountRepository.findAll();
        assertThat(userAccountList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserAccount.class);
        UserAccount userAccount1 = new UserAccount();
        userAccount1.setId(1L);
        UserAccount userAccount2 = new UserAccount();
        userAccount2.setId(userAccount1.getId());
        assertThat(userAccount1).isEqualTo(userAccount2);
        userAccount2.setId(2L);
        assertThat(userAccount1).isNotEqualTo(userAccount2);
        userAccount1.setId(null);
        assertThat(userAccount1).isNotEqualTo(userAccount2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserAccountDTO.class);
        UserAccountDTO userAccountDTO1 = new UserAccountDTO();
        userAccountDTO1.setId(1L);
        UserAccountDTO userAccountDTO2 = new UserAccountDTO();
        assertThat(userAccountDTO1).isNotEqualTo(userAccountDTO2);
        userAccountDTO2.setId(userAccountDTO1.getId());
        assertThat(userAccountDTO1).isEqualTo(userAccountDTO2);
        userAccountDTO2.setId(2L);
        assertThat(userAccountDTO1).isNotEqualTo(userAccountDTO2);
        userAccountDTO1.setId(null);
        assertThat(userAccountDTO1).isNotEqualTo(userAccountDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(userAccountMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(userAccountMapper.fromId(null)).isNull();
    }
}
