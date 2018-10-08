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

import static utils.TestUtils.getRandomValue;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentCrudTest extends TestBase {

    private static String studentId;

    @Title("Add a new student")
    @Test
    public void test001() {
        ArrayList<String> courses = new ArrayList<>();
        courses.add("Java");
        courses.add("Selenium");
        StudentClass student = new StudentClass();
        student.setFirstName("Aaa" + getRandomValue());
        student.setLastName("Bbb" + getRandomValue());
        student.setEmail(student.getFirstName() + "@gmail.com");
        student.setProgramme("QA");
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

    @Pending
    @Title("Update student data")
    @Test
    public void test003() {

    }

    @Pending
    @Title("Verify that student data was updated")
    @Test
    public void test004() {

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
