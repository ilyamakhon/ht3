package com.epam.ht3.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RepositoryPage extends AbstractPage {

    @FindBy(linkText = "Settings")
    private WebElement repositorySettingsLink;

    @FindBy(xpath = "//details[@class='details-reset details-overlay details-overlay-dark']/summary[contains( text(), 'Delete this repository' )]")
    private WebElement deleteRepositoryButton;

    @FindBy(xpath = "//details-dialog[@aria-label='Delete repository']/div[@class='Box-body overflow-auto']/form/p/input[@name='verify']")
    private WebElement verifyRepositoryDeletion;

    @FindBy(xpath = "//details-dialog[@aria-label='Delete repository']/div[@class='Box-body overflow-auto']/form/button[@type='submit']")
    private WebElement finalRepositoryDeletion;

    public RepositoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage() {

    }

    public void deleteRepository(String repositoryName) {
        repositorySettingsLink.click();
        deleteRepositoryButton.click();
        verifyRepositoryDeletion.sendKeys(repositoryName);
        finalRepositoryDeletion.click();
    }

}
