package com.epam.ht3.pages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends AbstractPage
{
	private final String BASE_URL = "https://github.com/";

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

	public MainPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	public void clickOnCreateNewRepositoryButton()
	{
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

	@Override
	public void openPage()
	{
		driver.navigate().to(BASE_URL);
	}
}