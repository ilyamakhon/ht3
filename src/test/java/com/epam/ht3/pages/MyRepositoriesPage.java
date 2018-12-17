package com.epam.ht3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyRepositoriesPage extends AbstractPage {

    private final String BASE_URL = "https://github.com/testautomationuser?tab=repositories";

    @FindBy(xpath = "//nav[@aria-label='User profile']/a[contains( text(), 'Stars' )]")
    private WebElement starsLink;

    public MyRepositoriesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage() {
        driver.navigate().to(BASE_URL);
    }

    public boolean findRepository(String repositoryName) {
        String locator = "//div[@id='user-repositories-list']/ul/li/div/div/h3/a[@href='/testautomationuser/" + repositoryName + "']";

        try {
            WebElement foundRepositoryLink = driver.findElement(By.xpath(locator));
            if (foundRepositoryLink.isDisplayed() && foundRepositoryLink.getText().equals(repositoryName)) {
                foundRepositoryLink.click();
            }
            return true;
        } catch (NoSuchElementException nsee) {
            WebElement nextPageLink = driver.findElement(By.xpath("//div[@class='pagination']/a[contains( text(), 'Next' )]"));
            nextPageLink.click();
        }
        return false;
    }

    public void starRepository(String repositoryName) {
        WebElement starButton = driver.findElement(By.xpath("//form[@class='unstarred']/button[@title='Star testautomationuser/" + repositoryName + "']"));

        starButton.click();
        starsLink.click();
    }
}
