package com.cucumber;

import com.testbase.TestBase;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features",
        format = {"pretty", "html:target/cucumber", "json:target/cucumber.json"})
public class AppRunner extends TestBase {

}
