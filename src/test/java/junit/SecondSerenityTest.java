package junit;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import org.junit.Test;
import org.junit.runner.RunWith;
import testbase.TestBase;

@RunWith(SerenityRunner.class)
public class SecondSerenityTest extends TestBase {

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
