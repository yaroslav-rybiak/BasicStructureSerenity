package com.cucumber.steps;

import cucumber.api.java.en.Then;
import io.restassured.RestAssured;

public class ParcelSteps {

    @Then("^Parcel ([^\"]*) exists$")
    public void parcelExists(String parcelId) {
        RestAssured.baseURI = "https://api-shipx-pl.easypack24.net/v1/tracking/";
        RestAssured.get(parcelId);
    }
}
