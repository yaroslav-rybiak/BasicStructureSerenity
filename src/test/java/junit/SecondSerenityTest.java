package junit;

import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class SecondSerenityTest {

    @BeforeClass
    public static void initialization() {
        RestAssured.baseURI = "http://localhost:8080/student";
    }

    @Test
    public void verify_404Error() {
        SerenityRest
                .given()
                .when()
                .get(Integer.toString(0))
                .then()
                .log()
                .all()
                .statusCode(404);
    }
}
