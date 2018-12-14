package com.epam.ht3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends AbstractPage {
    private final String BASE_URL = "https://github.com/";
    private String locator;

    @FindBy(xpath = "//summary[@aria-label='Create new…']")
    private WebElement buttonCreateNew;

    @FindBy(xpath = "//a[contains(text(), 'New repository')]")
    private WebElement linkNewRepository;

    @FindBy(xpath = "//summary[@aria-label='View profile and more']")
    private WebElement headerNavLink;

    @FindBy(xpath = "//a[contains( text(), 'Your repositories' )]")
    private WebElement myRepositoriesLink;

    @FindBy(id = "js-flash-container")
    private WebElement repositorySuccessesfullyDeletedMessage;

    @FindBy(id = "dashboard-repos-filter-sidebar")
    private WebElement sidebarSearchRepositoryInput;

    @FindBy(xpath = "//nav[@aria-label='Global']/div/div/div/form/label/input[@type='text']")
    private WebElement headerNavSearchRepositoryInput;

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void clickOnCreateNewRepositoryButton() {
        buttonCreateNew.click();
        linkNewRepository.click();
    }

    public void clickToMyRepositories() {
        headerNavLink.click();
        myRepositoriesLink.click();
    }

    public boolean checkRepositoryDeletion(String repositoryName) {
        String message = "Your repository \"testautomationuser/" + repositoryName + "\" was successfully deleted.";
        return repositorySuccessesfullyDeletedMessage.getText().contains(message);
    }

    public boolean findRepositoryViaSearchingSidebar(String repositoryName) {
        sidebarSearchRepositoryInput.sendKeys(repositoryName);

        locator = "//ul[@class='list-style-none']/li/div/a[@aria-describedby='hovercard-aria-description']/span[@title='" + repositoryName + "']";

        return findRepository(locator, repositoryName);
    }

    public boolean findRepositoryViaHeaderSearchInput(String repositoryName) {
        headerNavSearchRepositoryInput.sendKeys(repositoryName);

        locator = "//ul[@id='jump-to-results']/li/a/div[@aria-label='" + repositoryName + "']";

        return findRepository(locator, repositoryName);
    }

    private boolean findRepository(String locator, String repositoryName) {
        try {
            WebElement foundRepositoryLink = driver.findElement(By.xpath(locator));
            if (foundRepositoryLink.isDisplayed() && foundRepositoryLink.getText().equals(repositoryName)) {
                foundRepositoryLink.click();
            }
            return true;
        } catch (NoSuchElementException nsee) {
            return false;
        }
    }

    @Override
    public void openPage() {
        driver.navigate().to(BASE_URL);
    }
}