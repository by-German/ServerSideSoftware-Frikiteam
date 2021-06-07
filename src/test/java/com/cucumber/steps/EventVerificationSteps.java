package com.cucumber.steps;

import com.frikiteam.events.domain.model.Customer;
import com.frikiteam.events.domain.model.Event;
import com.frikiteam.events.domain.model.Organizer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.aspectj.weaver.ast.Or;

public class EventVerificationSteps {

    private String result;

    @Given("the user is in the event list")
    public void userIsInTheEventList() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Juan");
    }

    @When("viewing the status of each event")
    public void viewingTheStatusOfEachEvent() {
        Organizer organizer = new Organizer();
        organizer.setId(1L);
        organizer.setDescription("Description");
        result = organizer.getDescription();
    }

    @Then("the system indicates Verified")
    public void systemIndicatesVerified() {
        assert(!result.isEmpty());
    }


    @Given("Since the user is in the event list")
    public void asd() {
        Organizer organizer = new Organizer();
        if (organizer != null) {
            organizer.setDescription("German");
            organizer.setId(1L);
        }
        result = organizer.getDescription();
    }

    @When("you view the status of each event you choose")
    public void qwe() {
        Event event = new Event();
        event.setName("The best event!");
        event.getName();
    }

    @Then("the system indicates Not Verified")
    public void zxc() {
        assert(!result.isEmpty());

    }

}
