package qaops.automation.api.utils.services;

import io.restassured.http.Cookies;
import qaops.automation.api.pojos.UserRequest;
import qaops.automation.api.pojos.CreateUserResponse;
import qaops.automation.api.pojos.UserPojoResponse;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserService extends RestService {

    @Override
    protected String getBasePath() {
        return "/users";
    }

    public UserService(Cookies cookies) {
        super(cookies);
    }

    public CreateUserResponse createUser(UserRequest rq){
        return given().spec(REQ_SPEC).body(rq).post().as(CreateUserResponse.class);
    }

    public List<UserPojoResponse> getUsers(){
        return given().log().body()
                .spec(REQ_SPEC)
                .get()
                .jsonPath().getList("data", UserPojoResponse.class);
    }
}
