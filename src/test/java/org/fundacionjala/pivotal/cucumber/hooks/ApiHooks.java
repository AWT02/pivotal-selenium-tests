package org.fundacionjala.pivotal.cucumber.hooks;

import cucumber.api.java.After;
import org.apache.log4j.Logger;

import org.fundacionjala.core.api.RequestManager;
import org.fundacionjala.core.util.StringUtil;

/**
 * CommonHooks steps.
 */
public class ApiHooks {

    private static final Logger LOGGER = Logger.getLogger(ApiHooks.class.getName());

    /** After hook that delete project created in the background steps. **/
    @After
    public void after() {
        try {
            final String url = StringUtil.getExplicitEndpoint("/projects/{project_response.id}");
            RequestManager.deleteRequest(url);
        } catch (final Exception e) {
            LOGGER.warn("After hook for story steps doesn't run");
        }
    }

}
