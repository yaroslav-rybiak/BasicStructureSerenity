package com.cucumber.steps;

import cucumber.api.java.en.Then;
import net.serenitybdd.rest.SerenityRest;

public class InpostSteps {

    private String URL = "https://inpost.pl/sledzenie-przesylek";

    @Then("^Parcel ([^\"]*) exists$")
    public void parcelExists(String parcelId) {
        SerenityRest.given().log().all()
                .param("number", parcelId)
                .get(URL);
    }
}
