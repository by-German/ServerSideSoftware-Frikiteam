package com.cucumber.steps;

import com.frikiteam.events.domain.model.Event;
import com.frikiteam.events.domain.service.OrganizerService;
import com.frikiteam.events.resource.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class SearchByEventNameSteps {
    private final String url = "http://localhost:8080/api/";
    private final RestTemplate restTemplate = new RestTemplate();

    private String name;

    @Given("exist a event with name {string} im a list of events")
    public void existEventWith(String name) {
        // Arrange: verify if event exists -> else: to create it
        try {
            restTemplate.getForObject(url + "events/search?name=" + name, Event.class);
        } catch (HttpClientErrorException e) {
            createEvent(name);
        }
    }

    @When("the user search the event with name {string}")
    public void userSearchTheEventWithName(String name) {
        // Action -> endpoint search event by name
        EventResource result = restTemplate.getForObject(url + "events/search?name=" + name, EventResource.class);
        if (result != null)
            this.name = result.getName();
    }

    @Then("the result is the event with name {string}")
    public void resultIsTheEventWithName(String name) {
        // Assert that the event is equals to result
        assert(this.name.equals(name));
    }

    private void createEvent(String name) {
        createPlace();
        createOrganizer();
        SaveEventResource resource = new SaveEventResource();
        resource.setName(name);
        resource.setInformation("best event!");
        restTemplate.postForObject(url + "organizers/1/events/places/1", resource, EventResource.class);
    }

    private void createPlace() {
        try {
            restTemplate.getForObject(url + "countries/1", CountryResource.class);
        } catch (HttpClientErrorException e) {
            SaveCountryResource country = new SaveCountryResource();
            country.setName("country 1");
            restTemplate.postForObject(url + "countries", country, CountryResource.class);
        }

        try {
            restTemplate.getForObject(url + "countries/1/cities/1", CityResource.class);
        } catch (HttpClientErrorException e) {
            SaveCityResource city = new SaveCityResource();
            city.setName("city 1");
            restTemplate.postForObject(url + "countries/1/cities", city, CityResource.class);
        }

        try {
            restTemplate.getForObject(url + "cities/1/districts/1", DistrictResource.class);
        } catch (HttpClientErrorException e) {
            SaveDistrictResource district = new SaveDistrictResource();
            district.setName("district 1");
            restTemplate.postForObject(url + "cities/1/districts", district, DistrictResource.class);
        }

        try {
            restTemplate.getForObject(url + "districts/1/places/1", PlaceResource.class);
        }catch (HttpClientErrorException e) {
            SavePlaceResource place = new SavePlaceResource();
            place.setName("place 1");
            restTemplate.postForObject(url + "districts/1/places", place, PlaceResource.class);
        }

    }

    private void createOrganizer() {
        try {
            restTemplate.getForObject(url + "organizers/1", OrganizerResource.class);
        } catch (Exception e) {
            SaveOrganizerResource organizer = new SaveOrganizerResource();
            organizer.setDescription("I'm organizer");
            restTemplate.postForObject(url + "organizers", organizer, OrganizerResource.class);
        }
    }


}
