package com.cucumber;

import com.cucumber.RunCucumberTest;
import com.frikiteam.events.EventsApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = RunCucumberTest.class, webEnvironment =  SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = EventsApplication.class, loader = SpringBootContextLoader.class)
public class CucumberSpringConfiguration {
}
