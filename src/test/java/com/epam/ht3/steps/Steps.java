package com.epam.ht3.steps;

import com.epam.ht3.driver.DriverSingleton;
import com.epam.ht3.pages.CreateNewRepositoryPage;
import com.epam.ht3.pages.LoginPage;
import com.epam.ht3.pages.MainPage;
import com.epam.ht3.pages.MyRepositoriesPage;
import com.epam.ht3.pages.RepositoryPage;
import com.epam.ht3.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class Steps {
    private final Logger logger = LogManager.getRootLogger();
    private WebDriver driver;

    public void openBrowser() {
        driver = DriverSingleton.getDriver();
    }

    public void closeBrowser() {
        DriverSingleton.closeDriver();
    }

    public void loginGithub(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(username, password);
    }

    public String getLoggedInUserName() {
        LoginPage loginPage = new LoginPage(driver);
        return loginPage.getLoggedInUserName().trim().toLowerCase();
    }

    public void createNewRepository(String repositoryName, String repositoryDescription) {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnCreateNewRepositoryButton();
        CreateNewRepositoryPage createNewRepositoryPage = new CreateNewRepositoryPage(driver);
        createNewRepositoryPage.createNewRepository(repositoryName, repositoryDescription);
    }

    public boolean currentRepositoryIsEmpty() {
        CreateNewRepositoryPage createNewRepositoryPage = new CreateNewRepositoryPage(driver);
        return createNewRepositoryPage.isCurrentRepositoryEmpty();
    }

    public String getCurrentRepositoryName() {
        CreateNewRepositoryPage createNewRepositoryPage = new CreateNewRepositoryPage(driver);
        return createNewRepositoryPage.getCurrentRepositoryName();
    }

    public String generateRandomRepositoryNameWithCharLength(int howManyChars) {
        String repositoryNamePrefix = "testRepo_";
        return repositoryNamePrefix.concat(Utils.getRandomString(howManyChars));
    }

    public boolean findRepository(String repositoryName, String searchingType) {
        return findRepositoryBySearchingType(repositoryName, searchingType);
    }

    public boolean deleteRepository(String repositoryName) {
        RepositoryPage repositoryPage = new RepositoryPage(driver);
        repositoryPage.deleteRepository(repositoryName);

        MainPage mainPage = new MainPage(driver);
        return mainPage.checkRepositoryDeletion(repositoryName);
    }

    public boolean findRepositoryBySearchingType(String repositoryName, String searchingType) {
        MyRepositoriesPage myRepositoriesPage = new MyRepositoriesPage(driver);
        MainPage mainPage = new MainPage(driver);

        switch (searchingType) {
            case "repositoriesPage":
                mainPage.clickToMyRepositories();
                do {
                    if (myRepositoriesPage.findRepository(repositoryName)) {
                        return true;
                    }
                } while (!myRepositoriesPage.findRepository(repositoryName));

            case "searchingSidebar":
                return mainPage.findRepositoryViaSearchingSidebar(repositoryName);

            case "headerNavSearchInput":
                return mainPage.findRepositoryViaHeaderSearchInput(repositoryName);

        }
        return false;
    }

}
