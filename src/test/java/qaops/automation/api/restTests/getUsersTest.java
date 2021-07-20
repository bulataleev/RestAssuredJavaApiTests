package qaops.automation.api.restTests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import qaops.automation.api.pojos.UserPojoResponse;
import qaops.automation.api.steps.UsersSteps;
import qaops.automation.api.utils.RestWrapper;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;


@DisplayName("Тесты работы с API клиента")
public class getUsersTest {
    private static RestWrapper api;

    @BeforeAll
    public static void prepareClient() {
        api = RestWrapper.loginAs("eve.holt@reqres.in", "citylicka");
    }

    @Test
    @DisplayName("Human-readable test name")
    //@Description("sdas")
    public void getUsersWithWrapper() {
        assertThat(api.user.getUsers()).extracting(UserPojoResponse::getEmail).contains("george.bluth@reqres.in");
    }


    //---------experiments
    private static final RequestSpecification REQ_SPEC =
            new RequestSpecBuilder()
                    .setBaseUri("https://reqres.in/api")
                    .setBasePath("/users")
                    .addCookie("bearer", "123")
                    .setContentType(ContentType.JSON)
                    .build().log().everything(true);

    @Test
    public void getUsers() { // static getUsers method is used from UsersSteps class
        List<UserPojoResponse> users = UsersSteps.getUsers();
        assertThat(users).extracting(UserPojoResponse::getEmail).contains("janet.weaver@reqres.in");
        System.out.println(users.get(0).getFirstName());
    }

    @Test
    public void getUsersEmails() {
        List<String> emails = given()
                .spec(REQ_SPEC)
                .when().get()
                .then().statusCode(200).extract().jsonPath().getList("data.email");
        System.out.println(emails.size());
    }

    @Test
    public void getUserWithSpecificEmail() {
        given()
                .spec(REQ_SPEC)
                .when().get()
                .then().statusCode(200).body("data[0].email", equalTo("george.bluth@reqres.in"));
    }

    @Test
    public void getUserByLambda() {
        given()
                .spec(REQ_SPEC)
                .when().get()
                .then().statusCode(200).body("data.find{it.email=='george.bluth@reqres.in'}.first_name",
                equalTo("George"));
    }

    @Test
    public void getUsersandSAveToSTring() {
        String rs = given()
                .spec(REQ_SPEC)
                .when().get()
                .then().statusCode(200).extract().asString();
        System.out.println(":::" + rs);
    }
}
