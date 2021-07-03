package com.cucumber.steps;

import com.frikiteam.events.domain.model.Event;
import com.frikiteam.events.domain.model.EventInformation;
import com.frikiteam.events.resource.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java8.Da;
import org.springframework.data.domain.Page;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

public class DateOfAnEventSteps {
    private final String url = "http://localhost:8080/api/";
    private final RestTemplate restTemplate = new RestTemplate();
    EventInformationResource event = new EventInformationResource();

    @Given("the user1 is in the event list")
    public void userIsInTheEventList() {
        try {

            restTemplate.getForObject(url + "events/1/information", Event.class);
            //restTemplate.getForObject(url + "/api/events/{eventId}/information1", Event.class);
        } catch (HttpClientErrorException e) {
            createEvent();
            createEventInformation();
        }

    }

    @When("viewing one event")
    public void viewingOneEvent() {
        EventInformationResource result = restTemplate.getForObject(url + "events/1/information" , EventInformationResource.class);
        event = result;
        assert(true);
    }

    @Then("the system indicates show the date")
    public void systemShowDate() {
        System.out.println(event.getDescription());
    }


    private void createEvent() {
        createPlace();
        createOrganizer();
        SaveEventResource resource = new SaveEventResource();
        resource.setName("bou");
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

    private void createEventInformation(){
        try {
            restTemplate.getForObject(url + "events/1/information/1", EventInformationResource.class);
        } catch (HttpClientErrorException e){
            SaveEventInformationResource event_i = new SaveEventInformationResource();
//            event_i.setEventDescription("new topic");
//            event_i.setEventImage("new image");
//            event_i.setEventLink("https/new.com");
//            event_i.setStartDate(new Date(2021-05-19));
            restTemplate.postForObject(url + "events/1/information", event_i, EventInformationResource.class);
        }
    }

}
