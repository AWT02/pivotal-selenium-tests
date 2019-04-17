package org.fundacionjala.util;

import io.restassured.response.Response;
import org.fundacionjala.core.api.RequestManager;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;


/** Test for {@link StringUtil}. **/
public class StringUtilTest {

    /** This method load basic data for testcases. **/
    @Before
    public void setup() {
        ScenarioContext.getInstance().setContext("url.api",
                "https://www.pivotaltracker.com/services/v5");
        ScenarioContext.getInstance().setContext("project_id", "23");
        ScenarioContext.getInstance().setContext("workspace_id", "52");
        final Map<String, String> param = new HashMap();
        param.put("name", "My project");
        final Response projectResponse = RequestManager.postRequest("/projects", param);
        final String id = projectResponse.body().jsonPath().get("id").toString();
        ScenarioContext.getInstance().setContext("project", projectResponse);

        param.put("name", "My story");
        param.put("project_id", id);
        final Response storyResponse = RequestManager.postRequest(String.format("/projects/%s/stories", id), param);
        ScenarioContext.getInstance().setContext("story", storyResponse);


        param.put("name", "My workspace");
        param.put("project_ids", String.format("[%s]", id));
        final Response workspaceResponse = RequestManager.postRequest("/my/workspaces", param);
        ScenarioContext.getInstance().setContext("workspace", workspaceResponse);
    }

    /** this test verify get url for simple url. **/
    @Test
    public void testGetExplicitEndpointForSimpleUrl() {
        final String projectUrl = StringUtil.getExplicitEndpoint("/projects/");
        assertEquals("https://www.pivotaltracker.com/services/v5/projects/",
                projectUrl);
        final String workspaceUrl = StringUtil
                .getExplicitEndpoint("/my/workspaces/");
        assertEquals("https://www.pivotaltracker.com/services/v5/my/"
                .concat("workspaces/"), workspaceUrl);
    }

    /** this test verify get url for 1 simple parameter url. **/
    @Test
    public void testGetExplicitEndpointForUrlWith1SimpleParameter() {
        final String projectUrl = StringUtil
                .getExplicitEndpoint("/projects/{project_id}");
        assertEquals("https://www.pivotaltracker.com/services/v5/projects/23",
                projectUrl);
        final String workspaceUrl = StringUtil
                .getExplicitEndpoint("/my/workspaces/{workspace_id}");
        assertEquals("https://www.pivotaltracker.com/services/v5/my"
                .concat("/workspaces/52"), workspaceUrl);
    }

    /** this test verify get url for 1 simple parameter url. **/
    @Test
    public void testGetExplicitEndpointForUrlWith1CompositeParameter() {
        final String projectUrl = StringUtil
                .getExplicitEndpoint("/projects/{project.id}");
        assertEquals("https://www.pivotaltracker.com/services/v5/projects/524",
                projectUrl);
    }

    /** this test verify get url for 1 simple parameter url. **/
    @Test
    public void testGetExplicitEndpointForUrlWith2Parameters() {
        final String projectUrl = StringUtil.getExplicitEndpoint(
                "/projects/{project_id}/stories/{story.id}");
        assertEquals("https://www.pivotaltracker.com/services/v5/projects"
                .concat("/23/stories/12"), projectUrl);
    }
}