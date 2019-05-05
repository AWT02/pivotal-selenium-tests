package org.fundacionjala.pivotal.cucumber.hooks;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import org.apache.log4j.Logger;

/**
 * CommonHooks steps.
 */
public class CommonHooks {

    private static final Logger LOGGER = Logger.getLogger(CommonHooks.class.getName());

    /**
     * Before steps for every feature.
     *
     * @param scenario Scenario
     **/
    @Before
    public void before(final Scenario scenario) {
        LOGGER.info(String.format("@Before %s  Status - %s", scenario.getName(),
                scenario.getStatus()));
    }
}
