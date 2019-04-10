package org.fundacionjala.core.api.services;

import org.fundacionjala.core.api.RequestManager;
import org.fundacionjala.core.api.util.Properties;

import java.util.HashMap;
import java.util.Map;

/** This class have story api rest services. **/
public final class StoryService {
    private static final String BASE_URL = Properties.getValue("url.api")
            .concat("/projects/%d/stories");

    /** Private constructor. */
    private StoryService() {
    }

    /**
     * Creates a story with the supplied name in the specified project.
     * @param projectId The project in which the Story will be created.
     * @param name      The name of the story.
     * @return The ID of the new Story.
     */
    public static int createStory(final int projectId, final String name) {
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", name);
        return RequestManager.postRequest(String.format(BASE_URL,
                projectId), parameters)
                .body().jsonPath().get("id");
    }

    /**
     * Modify a story with the supplied name in the specified project.
     * @param projectId The project in which the Story will be created.
     * @param name      The name of the story.
     * @return The ID of the new Story.
     */
    public static int modifyStory(final int projectId, final String name) {
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", name);
        return RequestManager.putRequest(String.format(BASE_URL,
                projectId), parameters)
                .body().jsonPath().get("id");
    }

    /**
     * Deletes the specified story in the specified project.
     * @param projectId the ID of the project containing the story.
     * @param storyId   the ID of the story to delete.
     */
    public static void deleteStory(final int projectId, final int storyId) {
        RequestManager.deleteRequest(String.format(BASE_URL.concat("/%d"),
                projectId,
                storyId));
    }
}
