package org.fundacionjala.core.api.services;

import org.fundacionjala.core.api.RequestManager;
import org.fundacionjala.core.api.util.Properties;

import java.util.HashMap;
import java.util.Map;

/** This class have project api rest services. */
public final class ProjectService {
    private static final String BASE_URL = Properties.getValue("url.api")
            .concat("/projects/");

    /** Private constructor. */
    private ProjectService() {
    }

    /**
     * Create a project through the REST API.
     * @param name The name of the project.
     * @return The ID of the created project.
     */
    public static int createProject(final String name) {
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", name);
        return RequestManager.postRequest(BASE_URL, parameters).body()
                .jsonPath().get("id");
    }

    /**
     * Deletes the specified project.
     * @param projectId The ID of the project to delete.
     */
    public static void deleteProject(final int projectId) {
        final StringBuilder endpoint = new StringBuilder(BASE_URL);
        endpoint.append(projectId);
        RequestManager.deleteRequest(endpoint.toString());
    }

}
