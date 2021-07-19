package qaops.automation.api.steps;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import qaops.automation.api.pojos.CreateUserRequest;
import qaops.automation.api.pojos.CreateUserResponse;
import qaops.automation.api.pojos.UserPojoResponse;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UsersSteps {
    private static final RequestSpecification REQ_SPEC =
            new RequestSpecBuilder()
                    .setBaseUri("https://reqres.in/api")
                    .setBasePath("/users")
                    .setContentType(ContentType.JSON)
                    .build().log().everything(true);

    private CreateUserResponse user;

    public CreateUserResponse createUser(CreateUserRequest rq) {
        user = given().spec(REQ_SPEC).body(rq).post().as(CreateUserResponse.class);
        return user;
    }

    public UserPojoResponse getUser() {
        return given().spec(REQ_SPEC).get("/" + user.getId()).as(UserPojoResponse.class);
    }

    public static List<UserPojoResponse> getUsers() {
        return given().spec(REQ_SPEC)
                .get()
                .jsonPath().getList("data", UserPojoResponse.class);
    }

    public static UserPojoResponse getUser(int id) {
        return given().spec(REQ_SPEC).get("/" + id).as(UserPojoResponse.class);
    }
}
