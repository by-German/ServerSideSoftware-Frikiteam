package com.cucumber.steps;

import com.frikiteam.events.resource.OfferResource;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.web.client.RestTemplate;

public class SearchByEventNameSteps {
    private RestTemplate restTemplate = new RestTemplate();

    @Given("exist a event with name {string} im a list of events")
    public void asd(String name) {

    }

    @When("the user search the event with name {string}")
    public void sdf(String name) {
        String url = "http://localhost:8080/api/offers/1";
//        String url = "https://api-frikiteam.herokuapp.com/api/offers/1";
        OfferResource offer = restTemplate.getForObject(url, OfferResource.class);
        System.out.println(offer.getName());
    }

    @Then("the event with the searched name is displayed")
    public void dfg() {

    }
}
