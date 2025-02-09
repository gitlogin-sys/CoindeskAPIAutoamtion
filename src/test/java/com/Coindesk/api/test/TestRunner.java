package com.Coindesk.api.test;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty:target/cucumber/cucumber.txt",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				//"html:target/cucumber/report",
				"json:target/cucumber/cucumber.json",
				"com.Coindesk.api.utils.MyTestListener"
		}
		,features= {"src/test/resources/features/GetAllBPIs.feature"}
		,glue = {"com.Coindesk.api.stepdefinition"}
		//,dryRun = true
		,monochrome = true
		,snippets = SnippetType.CAMELCASE
		,tags = "@SmokeAPI"
		)
public class TestRunner {

}