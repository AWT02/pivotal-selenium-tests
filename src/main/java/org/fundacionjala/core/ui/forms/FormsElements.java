package org.fundacionjala.core.ui.forms;

/**
 * Enum for the org.fundacionjala.core.ui.forms elements.
 */
public enum FormsElements {
    TITLE("title"),
    DESCRIPTION("description"),
    ACCOUNT("account"),
    TASKENABLE("taskenable"),
    PRIVACY("privacy");

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
}
