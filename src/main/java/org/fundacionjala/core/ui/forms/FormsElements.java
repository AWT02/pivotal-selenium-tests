package org.fundacionjala.core.ui.forms;

/**
 * Enum for the org.fundacionjala.core.ui.forms elements.
 */
public enum FormsElements {
    NAME("name"),
    DESCRIPTION("description"),
    ACCOUNT("account"),
    TASKENABLE("taskenable"),
    PRIVACY("privacy"),
    STORY_TYPE("storyType"),
    ESTIMATED_POINTS("estimatedPoints"),
    REQUESTER("requester"),
    OWNER("owners"),
    BLOCKERS("blockers"),
    WS_NAME("workspace.name");

    private final String element;

    /**
     * Constructor to set element.
     *
     * @param elementName Form element
     */
    FormsElements(final String elementName) {
        this.element = elementName;
    }

    @Override
    public String toString() {
        return this.element;
    }

    /**
     * This method get the name.
     *
     * @return String name.
     */
    public String key() {
        return this.element;
    }
}
