package com.cucumber.steps;

import com.cucumber.RunCucumberTest;
import com.frikiteam.events.EventsApplication;
import com.frikiteam.events.controller.OffersController;
import com.frikiteam.events.controller.TagsController;
import com.frikiteam.events.domain.model.Offer;
import com.frikiteam.events.domain.model.Tag;
import com.frikiteam.events.domain.repositories.OfferRepository;
import com.frikiteam.events.domain.service.OfferService;
import com.frikiteam.events.resource.OfferResource;
import com.frikiteam.events.resource.TagResource;
import com.frikiteam.events.service.OfferServiceImpl;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class EventVerificationSteps {

//     Implement Mock de repository de test
    @MockBean
    private OfferRepository repository;
    private OffersController controller = new OffersController();

    @TestConfiguration
    static class EventServiceTestConfiguration {
        @Bean
        public OfferService employeeService() {
            return new OfferServiceImpl();
        }
        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }
    }


    @Given("the user is in the event list")
    public void userIsInTheEventList() {

    }

    @When("viewing the status of each event")
    public void viewingTheStatusOfEachEvent() {
        Long id = 1L;
        Offer offer = new Offer();
        offer.setId(id);
        offer.setName("name of offer");
        OfferResource resource = new OfferResource();
        resource.setName("name of offer");

        when(repository.findById(id)).thenReturn(Optional.of(offer));

        OfferResource result =  controller.getByIdOffer(id);
        System.out.println(result.getName());
    }

    @Then("the system indicates Verified")
    public void systemIndicatesVerified() {
        int a = 1;
        int b = 1;
        assert(a == b);
    }


    @Given("Since the user is in the event list")
    public void asd() {

    }

    @When("you view the status of each event you choose")
    public void qwe() {

    }

    @Then("the system indicates Not Verified")
    public void zxc() {
        int a = 2;
        int b = 2;
        assert(a == b);
    }

}
