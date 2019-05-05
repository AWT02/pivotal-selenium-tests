package org.fundacionjala.core.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/** Test for {@link ScenarioContext}. **/
public class ScenarioContextTest {

    /** This verify that context have loaded the api url. **/
    @Test
    public void testLoadByDefaultApiUrl() {
        org.apache.log4j.BasicConfigurator.configure();
        final String value = ScenarioContext.getInstance()
                .getContextAsString(ScenarioContext.API_URL_KEY);
        assertNotNull(value);
        assertEquals("https://www.pivotaltracker.com/services/v5", value);
    }
}
