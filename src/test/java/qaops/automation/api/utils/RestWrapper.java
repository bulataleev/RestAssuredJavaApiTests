package qaops.automation.api.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.specification.RequestSpecification;
import qaops.automation.api.pojos.CreateUserRequest;
import qaops.automation.api.pojos.CreateUserResponse;
import qaops.automation.api.pojos.UserLogin;
import qaops.automation.api.pojos.UserPojoResponse;
import qaops.automation.api.utils.services.OrderService;
import qaops.automation.api.utils.services.UserService;

import java.util.List;

import static io.restassured.RestAssured.given;

public class RestWrapper {
    private static final String BASE_URL = "https://reqres.in/api";
    private Cookies cookies;

    public UserService user;
    public OrderService order;

    private RestWrapper(Cookies cookies){
        this.cookies = cookies;

        user = new UserService(cookies);
        order = new OrderService(cookies);
    }


    public static RestWrapper loginAs(String login, String password) {
        Cookies cookies = given().log().body()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .basePath("/login")
                .body(new UserLogin(login, password))
                .post()
                .getDetailedCookies();

        return new RestWrapper(cookies);
    }


}
