package junit;


import cucumber.serenity.SerenitySteps;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;
import testbase.TestBase;

import java.util.ArrayList;

//@Concurrent(threads = "4x")
@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("testdata/studentinfo.csv")
public class TestDataTest extends TestBase {

    private static String studentId;
    private String firstName;
    private String lastName;
    private String email;
    private String programme;
    private String course01;
    private String course02;
    @Steps
    private SerenitySteps steps;

    @Title("Create multiple data driven students")
    @Test
    public void test000() {
        ArrayList<String> courses = new ArrayList<>();
        courses.add(course01);
        if (!course02.equals("")) {
            courses.add(course02);
        }
        studentId = steps.createStudent(firstName, lastName, email, programme, courses);
        steps.checkStudentExists(studentId).statusCode(200);
    }
}
