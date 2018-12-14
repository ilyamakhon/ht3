package com.epam.ht3;

import com.epam.ht3.dataprovider.DataProviderClass;
import com.epam.ht3.steps.Steps;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GitHubAutomationTest {
    private final String USERNAME = "testautomationuser";
    private final String PASSWORD = "Time4Death!";

    private final int REPOSITORY_NAME_POSTFIX_LENGTH = 6;
    private Steps steps;
    private String REPOSITORY_NAME;
    private boolean isRepositoryFound;

    @BeforeMethod(description = "Init browser")
    public void setUp() {
        steps = new Steps();
        steps.openBrowser();
    }

    @Test()
    public void oneCanCreateProject() {
        steps.loginGithub(USERNAME, PASSWORD);
        String repositoryName = steps.generateRandomRepositoryNameWithCharLength(REPOSITORY_NAME_POSTFIX_LENGTH);
        steps.createNewRepository(repositoryName, "auto-generated test repo");
        Assert.assertEquals(steps.getCurrentRepositoryName(), repositoryName);

        REPOSITORY_NAME = steps.getCurrentRepositoryName();
    }

    @Test(description = "Login to Github")
    public void oneCanLoginGithub() {
        steps.loginGithub(USERNAME, PASSWORD);
        Assert.assertEquals(USERNAME, steps.getLoggedInUserName());
    }

    @Test(dataProvider = "dataProvider", dataProviderClass = DataProviderClass.class)
    public void tst_repositoryDeletion(String searchingType) {
        //Find repository
        steps.loginGithub(USERNAME, PASSWORD);

        isRepositoryFound = steps.findRepository(this.REPOSITORY_NAME, searchingType);
        Assert.assertTrue(isRepositoryFound, "Repository " + this.REPOSITORY_NAME + " not found");

        //Delete repository
        boolean isRepositoryDeleted = steps.deleteRepository(this.REPOSITORY_NAME);
        Assert.assertTrue(isRepositoryDeleted, "Repository " + this.REPOSITORY_NAME + " wasn't deleted");
    }

    @Test(dataProvider = "dataProvider", dataProviderClass = DataProviderClass.class)
    public void tst_repositorySearch_Via(String searchingType) {
        steps.loginGithub(USERNAME, PASSWORD);

        isRepositoryFound = steps.findRepository(this.REPOSITORY_NAME, searchingType);
        Assert.assertTrue(isRepositoryFound, "Repository " + this.REPOSITORY_NAME + " not found");
    }

    @AfterMethod(description = "Stop Browser")
    public void stopBrowser() {
        steps.closeBrowser();
    }

}
