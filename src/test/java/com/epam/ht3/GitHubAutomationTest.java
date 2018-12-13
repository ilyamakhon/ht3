package com.epam.ht3;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.ht3.steps.Steps;

public class GitHubAutomationTest
{
	private Steps steps;
	private final String USERNAME = "testautomationuser";
	private final String PASSWORD = "Time4Death!";
	private final int REPOSITORY_NAME_POSTFIX_LENGTH = 6;

	private String REPOSITORY_NAME;

	@BeforeMethod(description = "Init browser")
	public void setUp()
	{
		steps = new Steps();
		steps.openBrowser();
	}

	@Test(priority = 2)
	public void oneCanCreateProject()
	{
		steps.loginGithub(USERNAME, PASSWORD);
		String repositoryName = steps.generateRandomRepositoryNameWithCharLength(REPOSITORY_NAME_POSTFIX_LENGTH);
		steps.createNewRepository(repositoryName, "auto-generated test repo");
		Assert.assertEquals(steps.getCurrentRepositoryName(), repositoryName);

		REPOSITORY_NAME = steps.getCurrentRepositoryName();
	}

	@Test(description = "Login to Github", priority = 1)
	public void oneCanLoginGithub()
	{
		steps.loginGithub(USERNAME, PASSWORD);
		Assert.assertEquals(USERNAME, steps.getLoggedInUserName());
	}

	@Test(priority = 3)
	public void deleteRepository() {
		//Find repository
		steps.loginGithub(USERNAME, PASSWORD);
		boolean repositoryFound = steps.findRepository(this.REPOSITORY_NAME);
		Assert.assertTrue(repositoryFound, "Repository " + this.REPOSITORY_NAME + " not found");

		//Delete repository
		boolean isRepositoryDeleted = steps.deleteRepository(this.REPOSITORY_NAME);
		Assert.assertTrue(isRepositoryDeleted, "Repository " + this.REPOSITORY_NAME + " wasn't deleted");
	}

	@AfterMethod(description = "Stop Browser")
	public void stopBrowser()
	{
		steps.closeBrowser();
	}

}
