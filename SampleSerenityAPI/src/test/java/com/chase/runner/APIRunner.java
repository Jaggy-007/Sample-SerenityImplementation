package com.chase.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)

@CucumberOptions(features="src/test/resource/Feature file/cityweather.feature",glue="com.chase.steps")
public class APIRunner {

}
