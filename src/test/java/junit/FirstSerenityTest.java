package junit;

import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import testbase.TestBase;

@RunWith(SerenityRunner.class)
public class FirstSerenityTest extends TestBase {

    @Test
    public void tryFirstStudents() {
        for (int i = 1; i <= 3; i++) {
            SerenityRest
                    .given()
                    .when()
                    .get(Integer.toString(i))
                    .then()
                    .log()
                    .all()
                    .statusCode(200);
        }
    }

    @Test
    public void tryLastStudents() {
        for (int i = 100; i >= 97; i--) {
            SerenityRest
                    .given()
                    .when()
                    .get(Integer.toString(i))
                    .then()
                    .log()
                    .all()
                    .statusCode(200);
        }
    }
}
