package com.cucumber.steps;

import com.frikiteam.events.domain.model.Event;
import com.frikiteam.events.resource.EventResource;
import com.frikiteam.events.resource.SaveEventResource;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.web.client.RestTemplate;

public class SearchByEventNameSteps {
    private final String url = "http://localhost:8080/api/";
    private final RestTemplate restTemplate = new RestTemplate();

    private Event result;
    private String name;

    @Given("exist a event with name {string} im a list of events")
    public void asd(String name) {
        // Arrange
        // verify if event exists -> else: to create it
//        Event exist = restTemplate.getForObject(url + "events/?name=" + name, Event.class);
//        if (exist == null) {
            SaveEventResource resource = new SaveEventResource();
            resource.setName(name);
            resource.setInformation("informacion del evento numero 5");
            resource.setPrice(10.0);
            resource.setQuantity(100);
            restTemplate.postForObject(url + "organizers/1/events/places/1", resource, EventResource.class);
//        }


//        String getOfferId = "offers/1";
//        Offer offer = restTemplate.getForObject(url + getOfferId, Offer.class);
//        if (offer != null) {
//            System.out.println(offer.getId());
//            System.out.println(offer.getName());
//            System.out.println(offer.getDiscountAmount());
//        }

    }

    @When("the user search the event with name {string}")
    public void sdf(String name) {
        // Action -> endpoint search event by name
//        this.name = name;
//        result = restTemplate.getForObject("events/?name=jose", Event.class);
//        EventInformation ef = restTemplate.getForObject("events/1/information", EventInformation.class);
    }

    @Then("the event with the searched name is displayed")
    public void dfg() {
//        Assert that the event is equals to result
//        assert (result != null);
//        assert (result.getName() == name);
    }
}
