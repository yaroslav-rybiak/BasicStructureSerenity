package junit;

import io.restassured.http.ContentType;
import model.StudentClass;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import testbase.TestBase;

import java.util.ArrayList;

import static utils.TestUtils.getRandomValue;

@RunWith(SerenityRunner.class)
public class StudentCrudTest extends TestBase {

    @Title("Create a new student")
    @Test
    public void createStudent() {

        ArrayList<String> courses = new ArrayList<>();
        courses.add("Java");
        courses.add("Selenium");

        StudentClass student = new StudentClass();
        student.setFirstName("Aaa" + getRandomValue());
        student.setLastName("Bbb" + getRandomValue());
        student.setEmail("aaa" + getRandomValue() + "@gmail.com");
        student.setProgramme("QA");
        student.setCourses(courses);
        SerenityRest.given()
                .contentType(ContentType.JSON)
                .log()
                .all()
                .when()
                .body(student)
                .post()
                .then()
                .log()
                .all()
                .statusCode(201);
    }
}
