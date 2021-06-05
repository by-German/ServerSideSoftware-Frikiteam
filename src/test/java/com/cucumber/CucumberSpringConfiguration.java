package com.cucumber;

import com.cucumber.RunCucumberTest;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = RunCucumberTest.class)
public class CucumberSpringConfiguration {
}
