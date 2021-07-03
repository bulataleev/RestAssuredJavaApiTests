package qaops.automation.api.restTests;
import io.restassured.http.ContentType;
import org.junit.Test;
import qaops.automation.api.pojos.UserPojoResponse;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;


public class getUsersTest {

    @Test
    public void getUsersandSaveToClass(){
        List<UserPojoResponse> users = given()
                .baseUri("https://reqres.in/api")
                .basePath("/users")
                .contentType(ContentType.JSON)
                .when().get()
                .then().statusCode(200).extract().jsonPath().getList("data", UserPojoResponse.class);
        assertThat(users).extracting(UserPojoResponse::getEmail).contains("janet.weaver@reqres.in");
        System.out.println(users.get(0).getFirstName());
    }

    @Test
    public void getUsersEmails(){
        List<String> emails = given()
                .baseUri("https://reqres.in/api")
                .basePath("/users")
                .contentType(ContentType.JSON)
                .when().get()
                .then().statusCode(200).extract().jsonPath().getList("data.email");
        System.out.println(emails);
    }
    @Test
    public void getUserWithSpecificEmail(){
        given()
                .baseUri("https://reqres.in/api")
                .basePath("/users")
                .contentType(ContentType.JSON)
                .when().get()
                .then().statusCode(200).body("data[0].email",equalTo("george.bluth@reqres.in"));
    }
    @Test
    public void getUserByLambda(){
        given()
                .baseUri("https://reqres.in/api")
                .basePath("/users")
                .contentType(ContentType.JSON)
                .when().get()
                .then().statusCode(200).body("data.find{it.email=='george.bluth@reqres.in'}.first_name",
                equalTo("George"));
    }
    @Test
    public void getUsersandSAveToSTring(){
        String rs = given()
                .baseUri("https://reqres.in/api")
                .basePath("/users")
                .contentType(ContentType.JSON)
                .when().get()
                .then().statusCode(200).extract().asString();
        System.out.println(":::"+rs);
    }
}
