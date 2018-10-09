package cucumber.serenity;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import model.Student;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.ArrayList;

public class SerenitySteps {

    @Step("Creating a new student {2}")
    public String createStudent(String firstName, String lastName, String email, String programme, ArrayList<String> courses) {
        Student student = new Student(firstName, lastName, email, programme, courses);
        return SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .log()
                .all()
                .when()
                .body(student)
                .post()
                .then()
                .log()
                .all()
                .statusCode(201)
                .extract()
                .response()
                .getHeader("Location")
                .replace(RestAssured.baseURI + "/", "");
    }

    @Step("Checking that student with id {0} exists")
    public ValidatableResponse checkStudent(String studentId) {
        return SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .log()
                .all()
                .when()
                .get("/" + studentId)
                .then();
    }

    @Step("Updating student with id {1}")
    public ValidatableResponse updateStudent(Student student, String studentId) {
        return SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .log()
                .all()
                .when()
                .body(student)
                .put("/" + studentId)
                .then();
    }

    @Step("Verifying that student with id {0} has a name '{1}'")
    public boolean verifyStudentName(String studentId, String name) {
        return SerenityRest.rest()
                .given()
                .when()
                .get("/" + studentId)
                .then()
                .log()
                .all()
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
                .then();
    }
}
