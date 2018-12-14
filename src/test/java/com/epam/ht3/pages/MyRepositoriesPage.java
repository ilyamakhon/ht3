package com.epam.ht3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyRepositoriesPage extends AbstractPage {

    private final String BASE_URL = "https://github.com/testautomationuser?tab=repositories";

    private WebElement repository;

    @FindBy(xpath = "//div[@class='pagination']/a[contains( text(), 'Next' )]")
    private WebElement nextPageLink;

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
            driver.findElement(By.xpath(locator)).isDisplayed();
            return true;
        } catch (NoSuchElementException nsee) {
            nextPageLink.click();
        }
        return false;
    }

//    public void goToRepositoryPage(String repositoryName) {
//        String locator = "//div[@id='user-repositories-list']/ul/li/div/div/h3/a[@href='/testautomationuser/" + repositoryName + "']";
//
//        driver.findElement(By.xpath(locator)).click();
//    }

}
