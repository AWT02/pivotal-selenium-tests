package org.fundacionjala.pivotal.pages.project;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import org.fundacionjala.core.ui.AbstractPage;
import org.fundacionjala.core.ui.forms.FormsElements;
import org.fundacionjala.core.util.Environment;
import org.fundacionjala.pivotal.pages.ISteps;
import org.fundacionjala.pivotal.pages.common.DropdownMenuSearch;

/** This class represents the story page. */
@Component
public class Story extends AbstractPage {
    private static final Logger LOGGER =
            Logger.getLogger(Story.class.getName());

    @FindBy(xpath = "//section[@class='story_or_epic_header']/child::button")
    private WebElement collapseStory;

    @FindBy(css = "button[title='Delete this story']")
    private WebElement deleteStoryButton;

    @FindBy(css = ".autosaves.button.std.save")
    private WebElement saveButton;

    @FindBy(css = ".autosaves.cancel.clear")
    private WebElement cancelButton;

    @FindBy(css = ".autosaves.button.std.close")
    private WebElement closeButton;

    @FindBy(css = "textarea[name='story[name]']")
    private WebElement storyName;

    @FindBy(css = ".autosaves.id.text_value")
    private WebElement idStoryField;

    @FindBy(css = "div[class='dropdown story_type']")
    private WebElement storyTypeDropdown;

    @FindBy(xpath = "//div[@class='dropdown story_type']/a[contains(@class,'item_feature')]/span")
    private WebElement storyTypeText;

    @FindBy(xpath = "//div[contains(@class,'dropdown story_estimate')]")
    private WebElement estimatedPointsDropdown;

    @FindBy(xpath = "//div[contains(@class,'dropdown story_estimate')]/a[contains(@class,'selection')]/span")
    private WebElement estimatedPointsText;

    @FindBy(xpath = "//div[@class='dropdown story_requested_by_id']")
    private WebElement requesterDropdown;

    @FindBy(css = ".name.hbsAvatarName")
    private WebElement requesterName;

    @FindBy(xpath = "//a[contains(@class,'selectable_owner_row_element add_owner')]")
    private WebElement addStoryOwner;

    @FindBy(xpath = "//span[@class='wrapper hbsAvatarName']/span")
    private WebElement ownerName;

    @FindBy(xpath = "//div[@class='story_owners']/a/span[@class='none']")
    private WebElement defaultOwner;

    /** 1 follower, 2 followers. */
    @FindBy(css = ".count.not_read_only")
    private WebElement followerCount;

    @FindBy(css = "div[data-aid='renderedDescription']")
    private WebElement openDescription;

    @FindBy(css = "textarea[data-aid='textarea']")
    private WebElement descriptionText;

    @FindBy(css = "div[data-aid='renderedDescription']")
    private WebElement descriptionTextLabel;

    @FindBy(css = ".tracker_markup")
    private WebElement storyNameCreated;

    @FindBy(css = "button[type='button'][title='Clone this story']")
    private WebElement cloneStory;

    @FindBy(css = "label[data-destination-state='start']")
    private WebElement startStory;

    @FindBy(xpath = "//div[@data-aid='StoryState__dropdown']/child::button")
    private WebElement stateButton;

    @FindBy(xpath = "//div[@data-aid='StoryState__dropdown']/button/span")
    private WebElement stateButtonText;

    /**
     * Basic method with the minimum requerid for create a story.
     *
     * @param strStoryName of the story that you want to create
     */
    public void setStoryNameText(final String strStoryName) {
        this.action.setValue(this.storyName, strStoryName);
    }

    /**
     * This method gets the story name.
     *
     * @return string.
     */
    public String getStoryNameText() {
        return this.action.getValue(this.storyName);
    }

    /**
     * Basic method with the minimum requerid for create a story.
     */
    public void clickSaveButton() {
        this.action.click(this.saveButton);
    }

    /**
     * Basic method with the minimum requerid for create a story.
     *
     * @param name of the story
     */
    public void createStory(final String name) {
        setStoryNameText(name);
        clickSaveButton();
    }


    /** Click delete button. */
    public void clickDeleteButton() {
        this.action.click(this.deleteStoryButton);
    }

    /**
     * This method get the state.
     *
     * @return String state.
     */
    public String getStateText() {
        return this.action.getValue(this.stateButtonText);
    }

    /**
     * This method set the story type.
     * {feature, bug, chore, release}
     *
     * @param type story type.
     */
    public void setStoryType(final String type) {
        this.action.click(this.storyTypeDropdown);
        final DropdownMenuSearch dropdown = new DropdownMenuSearch();
        dropdown.selectItem(type);
    }

    /**
     * This method get the story type value.
     *
     * @return story type.
     */
    public String getStoryTypeText() {
        return this.action.getValue(this.storyTypeText);
    }

    /**
     * This method return true if the story is of feature type.
     *
     * @return boolean
     */
    private boolean isFeature() {
        return getStoryTypeText().equalsIgnoreCase("feature");
    }

    /**
     * This method set the estimated points.
     * {unestimated, 0 points, 1 point, 2 points, 3 points}
     *
     * @param points story points.
     */
    public void setEstimatedPoints(final String points) {
        if (isFeature()) {
            this.action.click(this.estimatedPointsDropdown);
            final DropdownMenuSearch dropdown = new DropdownMenuSearch();
            dropdown.selectItem(points);
        }
    }

    /**
     * This method get the estimated points for story.
     *
     * @return estimated points.
     */
    public String getEstimatedPoints() {
        return this.action.getValue(this.estimatedPointsText);
    }

    /**
     * This method select the requester.
     *
     * @param requesterKey requester key (owner1, member1)
     */
    public void setRequesr(final String requesterKey) {
        final String requester = Environment.getInstance()
                .getAccountName(requesterKey);
        this.action.click(this.requesterDropdown);
        final DropdownMenuSearch dropdown = new DropdownMenuSearch();
        dropdown.selectItem(requester);
    }

    /**
     * This method get the requester selected in the story.
     *
     * @return requester name.
     */
    public String getRequester() {
        return this.action.getValue(this.requesterName);
    }

    /**
     * This method set the owner.
     *
     * @param ownerNameKey owner name. e.g. owner1, member1.
     */
    public void setOwner(final String ownerNameKey) {
        final String ownerNameValue = Environment.getInstance()
                .getAccountName(ownerNameKey);
        final String xpath = "//div/article[@class='content']//descendant::a"
                .concat("/child::span[text() = '").concat(ownerNameValue)
                .concat("']/parent::a");
        this.action.click(this.addStoryOwner);
        this.action.click(By.xpath(xpath));
    }

    /**
     * This method get the owner name.
     *
     * @return owner.
     */
    public String getOwner() {
        try {
            return this.action.getValue(this.ownerName);
        } catch (final Exception e) {
            LOGGER.warn("Explicit owner not found. Trying to get default "
                    .concat("owner text. Exception: ").concat(e.getMessage()));
            return this.action.getValue(this.defaultOwner);
        }
    }

    /**
     * This method set the description of story.
     *
     * @param newDescription string description.
     */
    public void setDescription(final String newDescription) {
        this.action.click(this.openDescription);
        this.action.setValue(this.descriptionText, newDescription);
    }

    /**
     * This method get the story description.
     *
     * @return description.
     */
    public String getDescription() {
        return this.action.getValue(this.descriptionTextLabel);
    }

    /**
     * This method create a story with data provided in attributes map.
     *
     * @param attributes map.
     */
    public void createStory(final Map<String, String> attributes) {
        final String name = attributes.get(FormsElements.NAME.key());
        final Map<String, ISteps> strategy = new HashMap<>();
        strategy.put(FormsElements.NAME.key(), () ->
                setStoryNameText(name));
        strategy.put(FormsElements.STORY_TYPE.key(), () ->
                setStoryType(attributes.get(FormsElements.STORY_TYPE.key())));
        strategy.put(FormsElements.ESTIMATED_POINTS.key(), () ->
                setEstimatedPoints(attributes.get(
                        FormsElements.ESTIMATED_POINTS.key())));
        strategy.put(FormsElements.REQUESTER.key(), () ->
                setRequesr(attributes.get(FormsElements.REQUESTER.key())));
        strategy.put(FormsElements.OWNER.key(), () ->
                setOwner(attributes.get(FormsElements.OWNER.key())));
        strategy.put(FormsElements.DESCRIPTION.key(), () ->
                setDescription(attributes.get(FormsElements.DESCRIPTION.key())));
        attributes.keySet()
                .forEach(key -> strategy.get(key).perform());
        clickSaveButton();
        final String xpath = "//span[@class='story_name']/span[text()='%s']";
        this.action.waitPresenceOfElement(
                By.xpath(String.format(xpath, name)));
    }
}
