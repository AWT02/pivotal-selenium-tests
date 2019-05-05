package org.fundacionjala.pivotal.cucumber.hooks;

import cucumber.api.java.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import org.fundacionjala.core.util.Assert;

/**
 * Assertion Hooks.
 */
public class AssertionHooks {

    @Autowired
    private Assert assertion;

    /**
     * Based on tag annotation enable soft assert.
     */
    @Before("@SoftAssert")
    public void initialize() {
        assertion.setAssertion(new SoftAssert());
    }

    /**
     * Based on tag annotation enable soft assert.
     */
    @Before
    public void initializeHardAssert() {
        assertion.setAssertion(new Assertion());
    }

}
