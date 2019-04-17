package org.fundacionjala.core.ui.forms;

import org.fundacionjala.core.ui.HtmlForms;
import org.fundacionjala.pivotal.pages.Project;

import java.util.Map;

public class EditProjectForm implements HtmlForms {
    protected Project project;

    @Override
    public void fillForm(final Map<String, String> formAttributes) {
        project.setEditProjectTitle(formAttributes.get("title"));
        project.setEditProjectDescription(formAttributes.get("description"));
        project.setEditProjectAccount(formAttributes.get("account"));
        project.setEditProjectTaskEnable(formAttributes.get("taskEnable"));
        project.setEditProjectPrivacy(formAttributes.get("privacy"));
    }

    @Override
    public void validateAttribute(final Map<String, String> formAttributes) {

    }
}
