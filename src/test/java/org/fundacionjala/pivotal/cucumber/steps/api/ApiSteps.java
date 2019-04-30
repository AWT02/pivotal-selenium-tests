package org.fundacionjala.pivotal.cucumber.steps.api;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import io.restassured.response.Response;
import org.fundacionjala.core.api.RequestManager;
import org.fundacionjala.util.ScenarioContext;
import org.fundacionjala.util.StringUtil;

import java.util.Map;

/**
 * Common steps.
 */
public class ApiSteps {

    private Response resp;

    /**
     * This is a generic API POST method.
     *
     * @param endpoint is the strings that is needed to complete the url for the
     *                 endpoint.
     * @param json     is the attributes read on the feature
     *                 file.
     */
    @Given("sends a POST request {string}")
    public void sendsAPOSTRequest(final String endpoint,
                                  final Map<String, String> param) {
        final String builtEndpoint = StringUtil.getExplicitEndpoint(endpoint);
        this.resp = RequestManager.postRequest(builtEndpoint, param);
    }

    @Given("sends a POST request {string} with json")
    public void sendsAPOSTRequest(final String endpoint,
                                  final String json) {
        final String builtEndpoint = StringUtil.getExplicitEndpoint(endpoint);
        this.resp = RequestManager.postRequest(builtEndpoint, json);
    }

    /**
     * This is a generic API PUT method.
     *
     * @param endpoint is the strings that is needed to complete the url for the
     *                 endpoint.
     * @param param    is the attributes read on the feature
     *                 file.
     */
    @And("sends a PUT request {string}")
    public void sendsAPUTRequest(final String endpoint,
                                 final Map<String, String> param) {
        final String builtEndpoint = StringUtil.getExplicitEndpoint(endpoint);
        this.resp = RequestManager.putRequest(builtEndpoint, param);
    }

    /**
     * This step gets an specified project.
     *
     * @param endpoint end point bareURl
     */
    @And("sends a GET request {string}")
    public void sendsAGETRequest(final String endpoint) {
        final String url = StringUtil.getExplicitEndpoint(endpoint);
        this.resp = RequestManager.getRequest(url);
    }

    /**
     * This step deletes an specified project.
     *
     * @param arg0 end point bareURl
     */
    @And("sends a DELETE request {string}")
    public void sendADELETERequest(final String arg0) {
        final String url = StringUtil.getExplicitEndpoint(arg0);
        this.resp = RequestManager.deleteRequest(url);
    }

    /**
     * saves the response.
     *
     * @param keyContext for save the response
     */
    @And("stores response as {string}")
    public void storesResponseAs(final String keyContext) {
        ScenarioContext.getInstance().setContext(keyContext, this.resp);
    }
}
