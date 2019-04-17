package org.fundacionjala.core.ui;

import java.util.Map;

/**
 * manage behavior of Html elements.
 */
public interface HtmlForms {
    /**
     * Strategy to fill a form.
     *
     * @param formAttributes list of attributes
     */
    void fillForm(Map<String, String> formAttributes);

    /**
     * Strategy to validate matching attribute on form.
     *
     * @param formAttributes list of attributes
     */
    void validateAttribute(Map<String, String> formAttributes);
}
