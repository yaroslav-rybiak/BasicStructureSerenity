package cucumber.serenity;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import model.StudentClass;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.ArrayList;

public class SerenitySteps {

    @Step
    public String createStudent(String firstName, String lastName, String email, String programme, ArrayList<String> courses) {
        StudentClass student = new StudentClass(firstName, lastName, email, programme, courses);
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

    @Step
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

    @Step
    public ValidatableResponse updateStudent(StudentClass student, String studentId) {
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

    @Step
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

    @Step
    public ValidatableResponse deleteStudent(String studentId) {
        return SerenityRest.rest()
                .given()
                .when()
                .delete("/" + studentId)
                .then();
    }
}
