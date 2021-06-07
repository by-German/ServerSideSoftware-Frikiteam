package com.cucumber.steps;

import com.frikiteam.events.domain.model.*;
import com.frikiteam.events.resource.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.web.client.RestTemplate;

public class RateAnEventStep {
    private final String url = "http://localhost:8080/api/";
    private final RestTemplate restTemplate = new RestTemplate();
    EventResource event;
    OfferResource offer;
    TicketResource ticket;
    CustomerResource customer;
    EventQualificationResource eventQualificationResource;

    @Given("the geek user wants to rate and event and it has ended")
    public void anEventExist(){
        String name = "Geeks for ever";

        SaveEventResource resource = new SaveEventResource();
        resource.setName(name);
        resource.setInformation("The mots popular event of the year");
        resource.setPrice(25.0);
        resource.setQuantity(3500);
        event = restTemplate.postForObject(url + "organizers/1/events/places/1", resource, EventResource.class);
    }

    @When("fill in the qualification data requested")
    public void fillDataRequested() {
        /*SaveEventQualificationResource qResource = new SaveEventQualificationResource();

        try {
            restTemplate.getForObject(url + "customers/1", CustomerResource.class);
        } catch (Exception e) {
            SaveCustomerResource saveCustomer = new SaveCustomerResource();
            customer = restTemplate.postForObject(url + "organizers", saveCustomer, CustomerResource.class);
        }

        SaveOfferResource saveOfferResource = new SaveOfferResource();
        saveOfferResource.setName("Summer Time");
        offer = restTemplate.postForObject(url + "offers", saveOfferResource, OfferResource.class);

        SaveTicketResource saveTicket = new SaveTicketResource();
        saveTicket.setCustomerId(customer.getId());
        saveTicket.setOfferId(offer.getId());
        saveTicket.setEventId(event.getId());
        //ticket = restTemplate.postForObject(url);
        ticket.setId(1l);


        qResource.setStarsQuantity(7);
        qResource.setEventId(event.getId());
        qResource.setCustomerId(customer.getId());
        qResource.setTicketId(ticket.getId());

        eventQualificationResource = restTemplate.postForObject(url + "event_qualifications", qResource, EventQualificationResource.class);

         */
    }

    @Then("the system records the rating provided by the user")
    public void ratingRecorded() {
        /* assert (eventQualificationResource != null); */
    }
}
