package junit;

import cucumber.serenity.SerenitySteps;
import model.StudentClass;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import testbase.TestBase;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;
import static utils.TestUtils.getRandomValue;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentCrudTest extends TestBase {

    private static String studentId;
    @Steps
    SerenitySteps steps;
    private String firstName = "Aaa" + getRandomValue();
    private String firstNameUpdated = firstName + "_upd";
    private String lastName = "Bbb" + getRandomValue();
    private String email = firstName.toLowerCase() + "@" + lastName.toLowerCase() + ".com";
    private String programme = "QA";
    private ArrayList<String> courses = new ArrayList<>(Arrays.asList("Java", "Selenium"));

    @Title("Add a new student and verify that student was added")
    @Test
    public void test001() {
        studentId = steps.createStudent(firstName, lastName, email, programme, courses);
        steps.checkStudent(studentId).statusCode(200);

    }

    @Title("Update student data and verify that student data was updated")
    @Test
    public void test002() {
        StudentClass student = new StudentClass(firstNameUpdated, lastName, email, programme, courses);
        steps.updateStudent(student, studentId).statusCode(200);
        assertTrue(steps.verifyStudentName(studentId, firstNameUpdated));
    }

    @Title("Delete a student and verify that student was deleted")
    @Test
    public void test003() {
        steps.deleteStudent(studentId).statusCode(204);
        steps.checkStudent(studentId).statusCode(404);
    }
}
