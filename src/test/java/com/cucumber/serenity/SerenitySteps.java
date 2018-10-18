package com.cucumber.serenity;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import model.Student;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import utils.ReusableSepcifications;

import java.util.ArrayList;

public class SerenitySteps {

    @Step("Creating a new student {2}")
    public String createStudent(String firstName, String lastName, String email, String programme, String course01, String course02) {
        ArrayList<String> courses = new ArrayList<>();
        courses.add(course01);
        courses.add(course02);
        Student student = new Student(firstName, lastName, email, programme, courses);
        return SerenityRest.rest()
                .given()
                .spec(ReusableSepcifications.getGenericRequestSpecification())
                .when()
                .body(student)
                .post()
                .then()
                .statusCode(201)
                .spec(ReusableSepcifications.getGenericResponseSpecification())
                .extract()
                .response()
                .getHeader("Location")
                .replace(RestAssured.baseURI + "/", "");
    }

    @Step("Checking that student with id {0} exists")
    public ValidatableResponse checkStudentExists(String studentId) {
        return SerenityRest.rest()
                .given()
                .when()
                .get("/" + studentId)
                .then()
                .statusCode(200)
                .spec(ReusableSepcifications.getGenericResponseSpecification());
    }

    @Step("Checking that student with id {0} does not exist")
    public ValidatableResponse checkStudentDoesNotExist(String studentId) {
        return SerenityRest.rest()
                .given()
                .when()
                .get("/" + studentId)
                .then()
                .statusCode(404);
    }

    @Step("Updating student with id {1}")
    public ValidatableResponse updateStudent(Student student, String studentId) {
        return SerenityRest.rest()
                .given()
                .spec(ReusableSepcifications.getGenericRequestSpecification())
                .log()
                .all()
                .when()
                .body(student)
                .put("/" + studentId)
                .then()
                .statusCode(200)
                .spec(ReusableSepcifications.getGenericResponseSpecification());
    }

    @Step("Verifying that student with id {0} has a name '{1}'")
    public boolean verifyStudentName(String studentId, String name) {
        return SerenityRest.rest()
                .given()
                .when()
                .get("/" + studentId)
                .then()
                .extract()
                .body()
                .path("firstName")
                .toString()
                .equals(name);
    }

    @Step("Deleting a student with id {0}")
    public ValidatableResponse deleteStudent(String studentId) {
        return SerenityRest.rest()
                .given()
                .when()
                .delete("/" + studentId)
                .then()
                .statusCode(204);
    }
}
