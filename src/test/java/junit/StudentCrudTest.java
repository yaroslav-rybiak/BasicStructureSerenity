package junit;

import cucumber.serenity.SerenitySteps;
import model.Student;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.ajbrown.namemachine.Name;
import org.ajbrown.namemachine.NameGenerator;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import testbase.TestBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

//@RunWith(SerenityRunner.class)
@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("testdata/studentinfo.csv")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentCrudTest extends TestBase {

    private static NameGenerator generator = new NameGenerator();
    private static List<Name> names = generator.generateNames( 100);

    private static String studentId;
    @Steps
    private SerenitySteps steps;
    //    private String firstName = names.get(0).getFirstName();
//    private String firstNameUpdated = names.get(1).getFirstName();
//    private String lastName = names.get(0).getLastName();
//    private String lastNameUpdated = names.get(1).getLastName();
//    private String email = firstName.toLowerCase() +"." + lastName.toLowerCase() + "@" + "nonexisting.mail";
//    private String programme = "QA";
//    private ArrayList<String> courses = new ArrayList<>(Arrays.asList("Java", "Selenium"));
    private String firstName;// = names.get(0).getFirstName();
    private String lastName;// = names.get(0).getLastName();
    private String email;// = firstName.toLowerCase() +"." + lastName.toLowerCase() + "@" + "nonexisting.mail";
    private String programme;// = "QA";
    private String course01;// = new ArrayList<>(Arrays.asList("Java", "Selenium"));
    private String course02;// = new ArrayList<>(Arrays.asList("Java", "Selenium"));

    @Title("Create multiple data driven students")
    @Test
    public void test000() {
        ArrayList<String> courses = new ArrayList<String>();
        courses.add(course01);
        if(!course02.equals("")) {
            courses.add(course02);
        }
        studentId = steps.createStudent(firstName, lastName, email, programme, courses);
        steps.checkStudentExists(studentId).statusCode(200);
    }
//
//    @Ignore
//    @Title("Add a new student and verify that student was created")
//    @Test
//    public void test001() {
//        studentId = steps.createStudent(firstName, lastName, email, programme, courses);
//        steps.checkStudentExists(studentId).statusCode(200);
//
//    }
//
//    @Ignore
//    @Title("Correct student data and verify that student data was updated")
//    @Test
//    public void test002() {
//        Student student = new Student(firstNameUpdated, lastNameUpdated, email, programme, courses);
//        steps.updateStudent(student, studentId).statusCode(200);
//        assertTrue(steps.verifyStudentName(studentId, firstNameUpdated));
//    }
//
//    @Ignore
//    @Title("Delete a student and verify that student no longer exists")
//    @Test
//    public void test003() {
//        steps.deleteStudent(studentId).statusCode(204);
//        steps.checkStudentDoesNotExist(studentId).statusCode(404);
//    }
}
