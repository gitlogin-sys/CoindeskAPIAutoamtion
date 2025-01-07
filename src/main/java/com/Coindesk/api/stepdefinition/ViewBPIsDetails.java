package com.Coindesk.api.stepdefinition;

import static org.junit.Assert.*;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.Coindesk.api.globalConstants.HttpStatus;
import com.Coindesk.api.model.CurrencyID;
import com.Coindesk.api.utils.ResponseHandler;
import com.Coindesk.api.utils.TestContext;

import io.cucumber.java.en.*;
import io.restassured.module.jsv.JsonSchemaValidator;

public class ViewBPIsDetails {
	
	private TestContext context;
	private static final Logger LOG = LogManager.getLogger(ViewBPIsDetails.class);
	
	public ViewBPIsDetails(TestContext context) {
		this.context = context;
	}
	
	
	
	
	@Given("user has access to endpoint {string}")
	public void userHasAccessToEndpoint(String endpoint) {		
		context.session.put("endpoint", endpoint);
	}

@When("user makes a request to view BPI IDs")
public void user_makes_a_request_to_view_bpi_i_ds() {
    
	context.response = context.requestSetup().when().get(context.session.get("endpoint").toString());
	assertNotNull(context.response.getBody().jsonPath());	
}
@Then("user should get the successful response code")
public void user_should_get_the_successful_response_code() {
	assertEquals(Long.valueOf(HttpStatus.OK.getCode()), Long.valueOf(context.response.getStatusCode()));
}
@Then("user should validate all the BPIs")
public void user_should_validate_all_the_bp_is() {
	 //Verify currencies
    String[] expectedCurrencies = {"USD", "GBP", "EUR"};
	for (String currency : expectedCurrencies) {
		LOG.info("currency : "   +currency );
		assertNotNull("currency not found!", currency);
        assert context.response.jsonPath().getMap("bpi").containsKey(currency) : "Currency " + currency + " is missing!";
    }
}

@Then("user should validate GBP description")
public void user_should_validate_gbp_description() {
	// Verify GBP description
    String gbpDescription  = context.response.getBody().jsonPath().getString("bpi.GBP.description");
    assert gbpDescription.equals("British Pound Sterling") : "GBP description mismatch!"; 
	
	
}
}