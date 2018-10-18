package com.cucumber.steps;

import com.cucumber.serenity.SerenitySteps;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class StudentSteps {

    private String id;

    @Steps
    private SerenitySteps serenitySteps;

    @When("^Student ([^\"]*) ([^\"]*) ([^\"]*) ([^\"]*) ([^\"]*) ([^\"]*) is created$")
    public void createStudent(String firstName, String lastName, String email, String programme, String course01, String course02) {
        id = serenitySteps.createStudent(firstName, lastName, email, programme, course01, course02);
    }

    @Then("^Student is deleted$")
    public void deleteStudent() {
        serenitySteps.deleteStudent(id);
    }

    @Then("^Student can be found by id$")
    public void checkStudentExists() {
        serenitySteps.checkStudentExists(id);
    }

    @Then("^Student can not be found by id$")
    public void checkStudent() {
        serenitySteps.checkStudentDoesNotExist(id);
    }
}
