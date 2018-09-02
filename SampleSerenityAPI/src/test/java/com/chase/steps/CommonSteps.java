package com.chase.steps;

import java.util.Map;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static net.serenitybdd.rest.SerenityRest.then;

public class CommonSteps extends CommonBase {
	
	@Given("^the service name is \"([^\"]*)\"$")
	public void the_service_name_is(String service) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    this.setRequestUri(service);
	    this.setRequestPath(service);
	}

	@Given("^the service contain the following path parametre$")
	public void the_service_contain_the_following_path_parametre(Map<String,String> pathparams) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	    // E,K,V must be a scalar (String, Integer, Date, enum etc)
		
		this.setPathParams(pathparams);
	    
	}

	@When("^user calls the service$")
	public void user_calls_the_service() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		String requestmethod = this.getRequestMethod(servicename);
		switch(requestmethod){
		case "GET":
			service_GET();
			break;
		case "POST":
			service_POST();
			break;

		}
	    
	}

	@Then("^status should be (\\d+)$")
	public void status_should_be(int statuscode) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    then().statusCode(statuscode);
	}

}
