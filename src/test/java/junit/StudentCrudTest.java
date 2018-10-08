package junit;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import model.StudentClass;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Title;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import testbase.TestBase;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
import static utils.TestUtils.getRandomValue;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentCrudTest extends TestBase {

    private static String studentId;
    private static String studentFirstName = "Aaa" + getRandomValue();
    private static String studentFirstNameUpdated = studentFirstName + "_upd";
    private static String studentSecondName = "Bbb" + getRandomValue();
    private static String studentEmail = studentFirstName.toLowerCase() + "@" + studentSecondName.toLowerCase() + ".com";
    private static String studentProgramme = "QA";
    private static String course01 = "Java";
    private static String course02 = "Selenium";

    @Title("Add a new student")
    @Test
    public void test001() {

        ArrayList<String> courses = new ArrayList<>();
        courses.add(course01);
        courses.add(course02);
        StudentClass student = new StudentClass();
        student.setFirstName(studentFirstName);
        student.setLastName(studentSecondName);
        student.setEmail(studentEmail);
        student.setProgramme(studentProgramme);
        student.setCourses(courses);

        studentId = SerenityRest.rest()
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

    @Title("Verify that student was added")
    @Test
    public void test002() {
        String studentUrl = RestAssured.baseURI + "/" + studentId;
        SerenityRest.rest()
                .given()
                .when()
                .get(studentUrl)
                .then()
                .log()
                .all()
                .statusCode(200);
    }

    @Title("Update student data")
    @Test
    public void test003() {
        ArrayList<String> courses = new ArrayList<>();
        courses.add(course01);
        courses.add(course02);
        StudentClass student = new StudentClass();
        student.setFirstName(studentFirstNameUpdated);
        student.setLastName(studentSecondName);
        student.setEmail(studentEmail);
        student.setProgramme(studentProgramme);
        student.setCourses(courses);
        SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .log()
                .all()
                .when()
                .body(student)
                .put("/" + studentId)
                .then()
                .log()
                .all()
                .statusCode(200);
    }

    @Title("Verify that student data was updated")
    @Test
    public void test004() {
        String studentUrl = RestAssured.baseURI + "/" + studentId;
        assertTrue(SerenityRest.rest()
                .given()
                .when()
                .get(studentUrl)
                .then()
                .log()
                .all()
                .extract()
                .body()
                .path("firstName")
                .toString()
                .equals(studentFirstNameUpdated));
    }

    @Pending
    @Title("Delete a student")
    @Test
    public void test005() {

    }

    @Pending
    @Title("Verify that student was deleted")
    @Test
    public void test006() {

    }
}
