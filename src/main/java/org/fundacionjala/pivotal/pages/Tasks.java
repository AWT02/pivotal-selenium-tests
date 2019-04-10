package org.fundacionjala.pivotal.pages;

import org.fundacionjala.core.ui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Task panel.
 **/
@Component
public class Tasks extends AbstractPage {
    @FindBy(css = "div[data-aid=\"Tasks\"] h4")
    private WebElement status;

    @FindBy(css = "div[data-aid=\"TaskDescription\"] span p")
    private List<WebElement> tasksNames;

    @FindBy(css = "textarea[data-aid='editor']")
    private WebElement taskText;

    @FindBy(css = "button[data-aid='saveTaskButton'] ")
    private WebElement saveTask;

    @FindBys({
            @FindBy(className = "AddSubresourceButton__message___2vsNCBXi"),
            @FindBy(linkText = "Add a task")
    })
    private WebElement addTaskBtn;

    /**
     * This method click on add task button.
     **/
    public void clickOnAddTaskButton() {
        this.action.click(this.addTaskBtn);
    }

    /**
     * This method set text in a task.
     * @param text string
     **/
    public void setTaskText(final String text) {
        this.taskText.sendKeys(Keys.TAB);
        this.taskText.clear();
        this.taskText.sendKeys(text);
    }

    /**
     * This method select a task with text provided.
     * @param text string.
     **/
    public void selectTask(final String text) {
        for (final WebElement element : this.tasksNames) {
            if (element.getText().equals(text)) {
                this.action.click(element);
                return;
            }
        }
    }

    /**
     * This method save the task.
     **/
    public void clickOnSave() {
        this.action.click(this.saveTask);
    }

    /**
     * This method search a task and delete a it.
     * @param text string.
     */
    public void deleteTask(final String text) {
        for (final WebElement element : this.tasksNames) {
            if (element.getText().equals(text)) {
                getParent(getParent(getParent(element)))
                        .findElement(By.xpath("//nav //button"))
                        .click();
                return;
            }
        }
    }

    /**
     * This method check if exist a task with text.
     * @param text string
     * @return boolean
     **/
    public boolean existTask(final String text) {
        for (final WebElement element : this.tasksNames) {
            if (element.getText().equals(text)) {
                return true;
            }
        }
        return false;
    }
}
