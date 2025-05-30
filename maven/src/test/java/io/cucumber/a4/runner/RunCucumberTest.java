package io.cucumber.a4.runner;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("io/cucumber/a4")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber-reports/cucumber-report.html, json:target/cucumber-reports/json/Cucumber.json, junit:target/cucumber-reports/xml/Cucumber.xml")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "io.cucumber.a4.stepdefinitions,io.cucumber.a4.util")
public class RunCucumberTest {
}
