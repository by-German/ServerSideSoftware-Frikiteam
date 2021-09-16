package com.cucumber.steps;

import com.frikiteam.events.domain.model.EventInformation;
import com.frikiteam.events.resource.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class GetDescriptionOfAnEventSteps {
    private final String url = "http://localhost:8080/api/";
    private final RestTemplate restTemplate = new RestTemplate();

    EventResource event;
    SaveEventResource resource = new SaveEventResource();
    EventInformation eventInformation = new EventInformation();

    @Given("the user wants to view the description of the event")
    public void eventExist() {
        createPlace();
        createOrganizer();

        resource.setName("The great show");
        resource.setInformation("Only geeks");
        resource.setPrice(25.0);
        resource.setQuantity(3500);
        resource.setPlaceId(1L);

        event = restTemplate.postForObject(url + "organizers/1/events", resource, EventResource.class);

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

    @When("choose one of the events")
    public void chooseAnEvent() {
        assert (event != null);
    }

    @Then("basic information about the event is appreciated")
    public void getDescription() {
        System.out.println(event.getInformation());
    }

}
