package com.putnam.cucumber.stepdefs;

import com.putnam.BuylocalbondsApp;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.boot.test.context.SpringBootTest;

@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = BuylocalbondsApp.class)
public abstract class StepDefs {

    protected ResultActions actions;

}
