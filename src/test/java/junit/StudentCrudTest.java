package junit;

import cucumber.serenity.SerenitySteps;
import model.StudentClass;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.ajbrown.namemachine.Name;
import org.ajbrown.namemachine.NameGenerator;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import testbase.TestBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentCrudTest extends TestBase {

    private static NameGenerator generator = new NameGenerator();
    private static List<Name> names = generator.generateNames( 1);

    private static String studentId;
    @Steps
    private SerenitySteps steps;
    private String firstName = names.get(0).getFirstName();
    private String firstNameUpdated = firstName + "_upd";
    private String lastName = names.get(0).getLastName();
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
