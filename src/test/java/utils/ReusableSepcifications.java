package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.lessThan;

public class ReusableSepcifications {

    public static RequestSpecBuilder requestSpecBuilder;
    public static ResponseSpecBuilder responseSpecBuilder;
    public static RequestSpecification requestSpecification;
    public static ResponseSpecification responseSpecification;

    public static RequestSpecification getGenericRequestSpecification() {
        requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecification = requestSpecBuilder.build();
        return requestSpecification;
    }

    public static ResponseSpecification getGenericResponseSpecification() {
        responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.expectHeader("Content-Type", "application/json;charset=UTF-8");
        responseSpecBuilder.expectHeader("Transfer-Encoding", "chunked");
        responseSpecBuilder.expectContentType(ContentType.JSON);
        responseSpecBuilder.expectResponseTime(lessThan(5L), TimeUnit.SECONDS);
        responseSpecification = responseSpecBuilder.build();
        return responseSpecification;
    }
}
