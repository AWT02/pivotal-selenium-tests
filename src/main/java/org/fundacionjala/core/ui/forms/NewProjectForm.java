package org.fundacionjala.core.ui.forms;

import org.fundacionjala.core.ui.HtmlForms;
import org.fundacionjala.pivotal.pages.Project;

import java.util.Map;


/**
 * Fill new project form.
 */
public class NewProjectForm implements HtmlForms {
    protected Project project;

    @Override
    public void fillForm(final Map<String, String> formAttributes) {
        project.setProjectNameTextField(formAttributes.get("name"));

        project.openSelectAccountCombobox();
        project.selectAccount(formAttributes.get("account"));

        project.selectProjectPrivacy(formAttributes.get("privacy"));
    }

    @Override
    public void validateAttribute(final Map<String, String> formAttributes) {

    }
}
