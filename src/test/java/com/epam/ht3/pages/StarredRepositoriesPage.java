package com.epam.ht3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class StarredRepositoriesPage extends AbstractPage {

    private final String BASE_URL = "https://github.com/testautomationuser?tab=stars";

    public StarredRepositoriesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage() {
        driver.navigate().to(BASE_URL);
    }

    public boolean findRepository(String repositoryName) {
        String locator = "//h3/a[@href='/testautomationuser/" + repositoryName + "']";

        try {
            driver.findElement(By.xpath(locator)).isDisplayed();
            return true;
        } catch (NoSuchElementException nsee) {
            return false;
        }
    }

    public boolean unstarRepository(String repositoryName) {
        WebElement unstarButton = driver.findElement(By.xpath("//form[@class='starred']/button[@title='Unstar testautomationuser/" + repositoryName + "']"));

        unstarButton.click();
        return false;
    }
}
