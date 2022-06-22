package com.parabank.stepdefs;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.parabank.pages.HomePage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomePageStepdefs {
	
	HomePage homePage=new HomePage();
	List<String> links=new ArrayList<String>();

	@Given("^when I launch Parabank page$")
	public void when_i_launch_parabank_page() {
		Assert.assertTrue(homePage.verifyHomePage());
	}

	@When("^I verify links on left menu$")
	public void i_verify_links_on_left_menu() {
		links.add("About Us");
		links.add("Services");
		links.add("Products");
		links.add("Locations");
		links.add("Admin Page");
		List<String> LinksAvailable=homePage.getLeftMenuLinks();
		Assert.assertTrue(links.containsAll(LinksAvailable) && LinksAvailable.size()==links.size());
	}

	@When("^I enter username and password as \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_enter_username_and_password_as_something_and_something(String username, String password) {
		homePage.enterCredentials(username,password);
	}

	@When("^I click on \"([^\"]*)\" button$")
	public void i_click_on_something_button(String button) {
		homePage.clickButton(button);
	}

	@When("^I enter name as \"([^\"]*)\"$")
	public void i_enter_name_as_something(String name) {
		homePage.enterName(name);
	}

	@Then("^I verify success message$")
	public void i_verify_success_message(){
		Assert.assertTrue(homePage.verifySuccessMessage());
	}

	@And("^I enter Email as \"([^\"]*)\"$")
	public void i_enter_email_as_something(String email) {
		homePage.enterEmail(email);
	}

	@And("^I enter Phone as \"([^\"]*)\"$")
	public void i_enter_phone_as_something(String phone) {
		homePage.enterPhone(phone);
	}

	@And("^I enter message as \"([^\"]*)\"$")
	public void i_enter_message_as_something(String message) {
		homePage.enterMessage(message);
	}
}
