package junit;

import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class FirstSerenityTest {

    @BeforeClass
    public static void initialization() {
        RestAssured.baseURI="http://localhost:8080/student";
    }

    @Test
    public void firstSerenityTest() {
        RestAssured.given()
                .when()
                .get("1")
                .then()
                .log()
                .all()
                .statusCode(200);
    }
}
