package com.parabank.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.base.baseClass;

public class HomePage extends baseClass {

	By homePageLogo=By.xpath("//img[@title='ParaBank']");
	By links=By.xpath("//ul[@class='leftmenu']/li/a");
	By username=By.xpath("//input[@name='username']");
	By password=By.xpath("//input[@name='password']");
	By loginButton=By.xpath("//input[@value='Log In']");
	By mailButton=By.xpath("//a[@href='contact.htm']");
	By name=By.id("name");
	By email=By.id("email");
	By phone=By.id("phone");
	By message=By.id("message");
	By sendToCustomerCare=By.xpath("//input[@value='Send to Customer Care']");
	By successMessage=By.xpath("//*[text()='A Customer Care Representative will be contacting you.']");

	public boolean verifyHomePage() {
		return getDriver().findElement(homePageLogo).isDisplayed();
	}

	public List<String> getLeftMenuLinks() {
		List<WebElement> list=getDriver().findElements(links);
		List<String> listToReturn=new ArrayList<String>();
		for(WebElement m:list) {
			listToReturn.add(m.getText());
		}
		return listToReturn;
	}

	public void enterCredentials(String username, String password) {
		getDriver().findElement(this.username).sendKeys(username);
		getDriver().findElement(this.password).sendKeys(password);
	}

	public void clickButton(String button) {
		switch(button) {
		case "Login":
			getDriver().findElement(loginButton).click();
			break;
		case "Mail":
			getDriver().findElement(mailButton).click();
			break;
		case "Send to Customer Care":
			getDriver().findElement(sendToCustomerCare).click();
		}
	}

	public void enterName(String name) {
		getDriver().findElement(this.name).sendKeys(name);
		
	}

	public void enterEmail(String email) {
		getDriver().findElement(this.email).sendKeys(email);
		
	}

	public void enterPhone(String phone) {
		getDriver().findElement(this.phone).sendKeys(phone);
		
	}

	public void enterMessage(String message) {
		getDriver().findElement(this.message).sendKeys(message);
		
	}

	public boolean verifySuccessMessage() {
		return getDriver().findElement(successMessage).isDisplayed();
	}

	
	
	
	
}
