package com.putnam.web.rest;

import com.putnam.BuylocalbondsApp;

import com.putnam.domain.Bond;
import com.putnam.repository.BondRepository;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the BondResource REST controller.
 *
 * @see BondResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BuylocalbondsApp.class)
public class BondResourceIntTest {

    private static final String DEFAULT_CUSIP = "AAAAAAAAAA";
    private static final String UPDATED_CUSIP = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_OWNED = false;
    private static final Boolean UPDATED_IS_OWNED = true;

    private static final String DEFAULT_SANDP_RATING = "AAAAAAAAAA";
    private static final String UPDATED_SANDP_RATING = "BBBBBBBBBB";

    private static final String DEFAULT_MOODY_RATING = "AAAAAAAAAA";
    private static final String UPDATED_MOODY_RATING = "BBBBBBBBBB";

    private static final String DEFAULT_FITCH_RATING = "AAAAAAAAAA";
    private static final String UPDATED_FITCH_RATING = "BBBBBBBBBB";

    private static final String DEFAULT_COUPON_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_COUPON_TYPE = "BBBBBBBBBB";

    private static final Double DEFAULT_YIELD_CHANGE = 1D;
    private static final Double UPDATED_YIELD_CHANGE = 2D;

    private static final Double DEFAULT_YIELD_CHANGE_PERCENT = 1D;
    private static final Double UPDATED_YIELD_CHANGE_PERCENT = 2D;

    private static final String DEFAULT_DEBT_OR_ASSET_CLASS = "AAAAAAAAAA";
    private static final String UPDATED_DEBT_OR_ASSET_CLASS = "BBBBBBBBBB";

    private static final String DEFAULT_SECURITY_ID = "AAAAAAAAAA";
    private static final String UPDATED_SECURITY_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ISSUER_ID = "AAAAAAAAAA";
    private static final String UPDATED_ISSUER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ISSUER_DESCRIP = "AAAAAAAAAA";
    private static final String UPDATED_ISSUER_DESCRIP = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_TYPE = "BBBBBBBBBB";

    private static final Double DEFAULT_COUPON_RATE = 1D;
    private static final Double UPDATED_COUPON_RATE = 2D;

    private static final LocalDate DEFAULT_MATURITY_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MATURITY_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_MARKET_PRICE = 1D;
    private static final Double UPDATED_MARKET_PRICE = 2D;

    private static final Double DEFAULT_FACE_VALUE = 1D;
    private static final Double UPDATED_FACE_VALUE = 2D;

    private static final String DEFAULT_BOND_SYMBOL = "AAAAAAAAAA";
    private static final String UPDATED_BOND_SYMBOL = "BBBBBBBBBB";

    private static final Boolean DEFAULT_CALLABLE = false;
    private static final Boolean UPDATED_CALLABLE = true;

    @Autowired
    private BondRepository bondRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBondMockMvc;

    private Bond bond;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        BondResource bondResource = new BondResource(bondRepository);
        this.restBondMockMvc = MockMvcBuilders.standaloneSetup(bondResource)
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
    public static Bond createEntity(EntityManager em) {
        Bond bond = new Bond()
            .cusip(DEFAULT_CUSIP)
            .isOwned(DEFAULT_IS_OWNED)
            .sandpRating(DEFAULT_SANDP_RATING)
            .moodyRating(DEFAULT_MOODY_RATING)
            .fitchRating(DEFAULT_FITCH_RATING)
            .couponType(DEFAULT_COUPON_TYPE)
            .yieldChange(DEFAULT_YIELD_CHANGE)
            .yieldChangePercent(DEFAULT_YIELD_CHANGE_PERCENT)
            .debtOrAssetClass(DEFAULT_DEBT_OR_ASSET_CLASS)
            .securityId(DEFAULT_SECURITY_ID)
            .issuerId(DEFAULT_ISSUER_ID)
            .issuerDescrip(DEFAULT_ISSUER_DESCRIP)
            .productType(DEFAULT_PRODUCT_TYPE)
            .couponRate(DEFAULT_COUPON_RATE)
            .maturityDate(DEFAULT_MATURITY_DATE)
            .marketPrice(DEFAULT_MARKET_PRICE)
            .faceValue(DEFAULT_FACE_VALUE)
            .bondSymbol(DEFAULT_BOND_SYMBOL)
            .callable(DEFAULT_CALLABLE);
        return bond;
    }

    @Before
    public void initTest() {
        bond = createEntity(em);
    }

    @Test
    @Transactional
    public void createBond() throws Exception {
        int databaseSizeBeforeCreate = bondRepository.findAll().size();

        // Create the Bond
        restBondMockMvc.perform(post("/api/bonds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bond)))
            .andExpect(status().isCreated());

        // Validate the Bond in the database
        List<Bond> bondList = bondRepository.findAll();
        assertThat(bondList).hasSize(databaseSizeBeforeCreate + 1);
        Bond testBond = bondList.get(bondList.size() - 1);
        assertThat(testBond.getCusip()).isEqualTo(DEFAULT_CUSIP);
        assertThat(testBond.isIsOwned()).isEqualTo(DEFAULT_IS_OWNED);
        assertThat(testBond.getSandpRating()).isEqualTo(DEFAULT_SANDP_RATING);
        assertThat(testBond.getMoodyRating()).isEqualTo(DEFAULT_MOODY_RATING);
        assertThat(testBond.getFitchRating()).isEqualTo(DEFAULT_FITCH_RATING);
        assertThat(testBond.getCouponType()).isEqualTo(DEFAULT_COUPON_TYPE);
        assertThat(testBond.getYieldChange()).isEqualTo(DEFAULT_YIELD_CHANGE);
        assertThat(testBond.getYieldChangePercent()).isEqualTo(DEFAULT_YIELD_CHANGE_PERCENT);
        assertThat(testBond.getDebtOrAssetClass()).isEqualTo(DEFAULT_DEBT_OR_ASSET_CLASS);
        assertThat(testBond.getSecurityId()).isEqualTo(DEFAULT_SECURITY_ID);
        assertThat(testBond.getIssuerId()).isEqualTo(DEFAULT_ISSUER_ID);
        assertThat(testBond.getIssuerDescrip()).isEqualTo(DEFAULT_ISSUER_DESCRIP);
        assertThat(testBond.getProductType()).isEqualTo(DEFAULT_PRODUCT_TYPE);
        assertThat(testBond.getCouponRate()).isEqualTo(DEFAULT_COUPON_RATE);
        assertThat(testBond.getMaturityDate()).isEqualTo(DEFAULT_MATURITY_DATE);
        assertThat(testBond.getMarketPrice()).isEqualTo(DEFAULT_MARKET_PRICE);
        assertThat(testBond.getFaceValue()).isEqualTo(DEFAULT_FACE_VALUE);
        assertThat(testBond.getBondSymbol()).isEqualTo(DEFAULT_BOND_SYMBOL);
        assertThat(testBond.isCallable()).isEqualTo(DEFAULT_CALLABLE);
    }

    @Test
    @Transactional
    public void createBondWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bondRepository.findAll().size();

        // Create the Bond with an existing ID
        bond.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBondMockMvc.perform(post("/api/bonds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bond)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Bond> bondList = bondRepository.findAll();
        assertThat(bondList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllBonds() throws Exception {
        // Initialize the database
        bondRepository.saveAndFlush(bond);

        // Get all the bondList
        restBondMockMvc.perform(get("/api/bonds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bond.getId().intValue())))
            .andExpect(jsonPath("$.[*].cusip").value(hasItem(DEFAULT_CUSIP.toString())))
            .andExpect(jsonPath("$.[*].isOwned").value(hasItem(DEFAULT_IS_OWNED.booleanValue())))
            .andExpect(jsonPath("$.[*].sandpRating").value(hasItem(DEFAULT_SANDP_RATING.toString())))
            .andExpect(jsonPath("$.[*].moodyRating").value(hasItem(DEFAULT_MOODY_RATING.toString())))
            .andExpect(jsonPath("$.[*].fitchRating").value(hasItem(DEFAULT_FITCH_RATING.toString())))
            .andExpect(jsonPath("$.[*].couponType").value(hasItem(DEFAULT_COUPON_TYPE.toString())))
            .andExpect(jsonPath("$.[*].yieldChange").value(hasItem(DEFAULT_YIELD_CHANGE.doubleValue())))
            .andExpect(jsonPath("$.[*].yieldChangePercent").value(hasItem(DEFAULT_YIELD_CHANGE_PERCENT.doubleValue())))
            .andExpect(jsonPath("$.[*].debtOrAssetClass").value(hasItem(DEFAULT_DEBT_OR_ASSET_CLASS.toString())))
            .andExpect(jsonPath("$.[*].securityId").value(hasItem(DEFAULT_SECURITY_ID.toString())))
            .andExpect(jsonPath("$.[*].issuerId").value(hasItem(DEFAULT_ISSUER_ID.toString())))
            .andExpect(jsonPath("$.[*].issuerDescrip").value(hasItem(DEFAULT_ISSUER_DESCRIP.toString())))
            .andExpect(jsonPath("$.[*].productType").value(hasItem(DEFAULT_PRODUCT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].couponRate").value(hasItem(DEFAULT_COUPON_RATE.doubleValue())))
            .andExpect(jsonPath("$.[*].maturityDate").value(hasItem(DEFAULT_MATURITY_DATE.toString())))
            .andExpect(jsonPath("$.[*].marketPrice").value(hasItem(DEFAULT_MARKET_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].faceValue").value(hasItem(DEFAULT_FACE_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].bondSymbol").value(hasItem(DEFAULT_BOND_SYMBOL.toString())))
            .andExpect(jsonPath("$.[*].callable").value(hasItem(DEFAULT_CALLABLE.booleanValue())));
    }

    @Test
    @Transactional
    public void getBond() throws Exception {
        // Initialize the database
        bondRepository.saveAndFlush(bond);

        // Get the bond
        restBondMockMvc.perform(get("/api/bonds/{id}", bond.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(bond.getId().intValue()))
            .andExpect(jsonPath("$.cusip").value(DEFAULT_CUSIP.toString()))
            .andExpect(jsonPath("$.isOwned").value(DEFAULT_IS_OWNED.booleanValue()))
            .andExpect(jsonPath("$.sandpRating").value(DEFAULT_SANDP_RATING.toString()))
            .andExpect(jsonPath("$.moodyRating").value(DEFAULT_MOODY_RATING.toString()))
            .andExpect(jsonPath("$.fitchRating").value(DEFAULT_FITCH_RATING.toString()))
            .andExpect(jsonPath("$.couponType").value(DEFAULT_COUPON_TYPE.toString()))
            .andExpect(jsonPath("$.yieldChange").value(DEFAULT_YIELD_CHANGE.doubleValue()))
            .andExpect(jsonPath("$.yieldChangePercent").value(DEFAULT_YIELD_CHANGE_PERCENT.doubleValue()))
            .andExpect(jsonPath("$.debtOrAssetClass").value(DEFAULT_DEBT_OR_ASSET_CLASS.toString()))
            .andExpect(jsonPath("$.securityId").value(DEFAULT_SECURITY_ID.toString()))
            .andExpect(jsonPath("$.issuerId").value(DEFAULT_ISSUER_ID.toString()))
            .andExpect(jsonPath("$.issuerDescrip").value(DEFAULT_ISSUER_DESCRIP.toString()))
            .andExpect(jsonPath("$.productType").value(DEFAULT_PRODUCT_TYPE.toString()))
            .andExpect(jsonPath("$.couponRate").value(DEFAULT_COUPON_RATE.doubleValue()))
            .andExpect(jsonPath("$.maturityDate").value(DEFAULT_MATURITY_DATE.toString()))
            .andExpect(jsonPath("$.marketPrice").value(DEFAULT_MARKET_PRICE.doubleValue()))
            .andExpect(jsonPath("$.faceValue").value(DEFAULT_FACE_VALUE.doubleValue()))
            .andExpect(jsonPath("$.bondSymbol").value(DEFAULT_BOND_SYMBOL.toString()))
            .andExpect(jsonPath("$.callable").value(DEFAULT_CALLABLE.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingBond() throws Exception {
        // Get the bond
        restBondMockMvc.perform(get("/api/bonds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBond() throws Exception {
        // Initialize the database
        bondRepository.saveAndFlush(bond);
        int databaseSizeBeforeUpdate = bondRepository.findAll().size();

        // Update the bond
        Bond updatedBond = bondRepository.findOne(bond.getId());
        updatedBond
            .cusip(UPDATED_CUSIP)
            .isOwned(UPDATED_IS_OWNED)
            .sandpRating(UPDATED_SANDP_RATING)
            .moodyRating(UPDATED_MOODY_RATING)
            .fitchRating(UPDATED_FITCH_RATING)
            .couponType(UPDATED_COUPON_TYPE)
            .yieldChange(UPDATED_YIELD_CHANGE)
            .yieldChangePercent(UPDATED_YIELD_CHANGE_PERCENT)
            .debtOrAssetClass(UPDATED_DEBT_OR_ASSET_CLASS)
            .securityId(UPDATED_SECURITY_ID)
            .issuerId(UPDATED_ISSUER_ID)
            .issuerDescrip(UPDATED_ISSUER_DESCRIP)
            .productType(UPDATED_PRODUCT_TYPE)
            .couponRate(UPDATED_COUPON_RATE)
            .maturityDate(UPDATED_MATURITY_DATE)
            .marketPrice(UPDATED_MARKET_PRICE)
            .faceValue(UPDATED_FACE_VALUE)
            .bondSymbol(UPDATED_BOND_SYMBOL)
            .callable(UPDATED_CALLABLE);

        restBondMockMvc.perform(put("/api/bonds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBond)))
            .andExpect(status().isOk());

        // Validate the Bond in the database
        List<Bond> bondList = bondRepository.findAll();
        assertThat(bondList).hasSize(databaseSizeBeforeUpdate);
        Bond testBond = bondList.get(bondList.size() - 1);
        assertThat(testBond.getCusip()).isEqualTo(UPDATED_CUSIP);
        assertThat(testBond.isIsOwned()).isEqualTo(UPDATED_IS_OWNED);
        assertThat(testBond.getSandpRating()).isEqualTo(UPDATED_SANDP_RATING);
        assertThat(testBond.getMoodyRating()).isEqualTo(UPDATED_MOODY_RATING);
        assertThat(testBond.getFitchRating()).isEqualTo(UPDATED_FITCH_RATING);
        assertThat(testBond.getCouponType()).isEqualTo(UPDATED_COUPON_TYPE);
        assertThat(testBond.getYieldChange()).isEqualTo(UPDATED_YIELD_CHANGE);
        assertThat(testBond.getYieldChangePercent()).isEqualTo(UPDATED_YIELD_CHANGE_PERCENT);
        assertThat(testBond.getDebtOrAssetClass()).isEqualTo(UPDATED_DEBT_OR_ASSET_CLASS);
        assertThat(testBond.getSecurityId()).isEqualTo(UPDATED_SECURITY_ID);
        assertThat(testBond.getIssuerId()).isEqualTo(UPDATED_ISSUER_ID);
        assertThat(testBond.getIssuerDescrip()).isEqualTo(UPDATED_ISSUER_DESCRIP);
        assertThat(testBond.getProductType()).isEqualTo(UPDATED_PRODUCT_TYPE);
        assertThat(testBond.getCouponRate()).isEqualTo(UPDATED_COUPON_RATE);
        assertThat(testBond.getMaturityDate()).isEqualTo(UPDATED_MATURITY_DATE);
        assertThat(testBond.getMarketPrice()).isEqualTo(UPDATED_MARKET_PRICE);
        assertThat(testBond.getFaceValue()).isEqualTo(UPDATED_FACE_VALUE);
        assertThat(testBond.getBondSymbol()).isEqualTo(UPDATED_BOND_SYMBOL);
        assertThat(testBond.isCallable()).isEqualTo(UPDATED_CALLABLE);
    }

    @Test
    @Transactional
    public void updateNonExistingBond() throws Exception {
        int databaseSizeBeforeUpdate = bondRepository.findAll().size();

        // Create the Bond

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBondMockMvc.perform(put("/api/bonds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bond)))
            .andExpect(status().isCreated());

        // Validate the Bond in the database
        List<Bond> bondList = bondRepository.findAll();
        assertThat(bondList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteBond() throws Exception {
        // Initialize the database
        bondRepository.saveAndFlush(bond);
        int databaseSizeBeforeDelete = bondRepository.findAll().size();

        // Get the bond
        restBondMockMvc.perform(delete("/api/bonds/{id}", bond.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Bond> bondList = bondRepository.findAll();
        assertThat(bondList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Bond.class);
        Bond bond1 = new Bond();
        bond1.setId(1L);
        Bond bond2 = new Bond();
        bond2.setId(bond1.getId());
        assertThat(bond1).isEqualTo(bond2);
        bond2.setId(2L);
        assertThat(bond1).isNotEqualTo(bond2);
        bond1.setId(null);
        assertThat(bond1).isNotEqualTo(bond2);
    }
}
