package qaops.automation.api.restTests;

import io.restassured.http.ContentType;
import org.junit.Test;
import qaops.automation.api.pojos.CreateUserRequest;
import qaops.automation.api.pojos.CreateUserResponse;

import static io.restassured.RestAssured.given;

public class createUserTest {

    @Test
    public void createUser(){
        CreateUserRequest rq = new CreateUserRequest();
        rq.setName("sample");
        rq.setJob("automation");

        CreateUserResponse rs = given()
                .baseUri("https://reqres.in/api")
                .basePath("/users")
                .contentType(ContentType.JSON)
                .body(rq)
                .when().post()
                .then().extract().as(CreateUserResponse.class);
        System.out.println(rs);
    }
}
