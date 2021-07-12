package qaops.automation.api.restTests;

import org.junit.Test;
import qaops.automation.api.pojos.CreateUserRequest;
import qaops.automation.api.pojos.CreateUserResponse;
import qaops.automation.api.steps.UsersSteps;
import qaops.automation.api.utils.UserGenerator;

import static org.assertj.core.api.Assertions.assertThat;

public class createUserTest {

    @Test
    public void createUser(){
//        CreateUserRequest rq = new CreateUserRequest();
//        rq.setName("sample");
//        rq.setJob("automation"); -- old way
        CreateUserRequest rq = CreateUserRequest
                .builder()
                .name("Bulat")
                .job("automation")
                .build(); //by using builder pattern
        UsersSteps userApi = new UsersSteps();
        CreateUserResponse rs = userApi.createUser(rq);
//        CreateUserResponse rs = given()
//                .baseUri("https://reqres.in/api")
//                .basePath("/users")
//                .contentType(ContentType.JSON)
//                .body(rq)
//                .when().post()
//                .then().extract().as(CreateUserResponse.class); --old way

        assertThat(rs)
                .isNotNull()
                .extracting(CreateUserResponse::getName)
                .isEqualTo(rq.getName());

        System.out.println("request::  "+rq);
        System.out.println("response:: "+rs);
    }
    @Test
    public void getCreatedUser() {
//        CreateUserRequest rq = CreateUserRequest
//                .builder()
//                .name("Bulat2")
//                .job("automation2")
//                .build(); //by using builder pattern
        CreateUserRequest rq = UserGenerator.getSimpleUser();

        UsersSteps userApi = new UsersSteps();
        CreateUserResponse rs = userApi.createUser(rq);

        System.out.println(rs.getId());
        System.out.println(userApi.getUser(rs.getId()));
    }

    @Test
    public void createUserwithGenerator() {
        CreateUserRequest rq = UserGenerator.getSimpleUser();

        UsersSteps userApi = new UsersSteps();
        CreateUserResponse rs = userApi.createUser(rq);

        assertThat(rs)
                .isNotNull()
                .extracting(CreateUserResponse::getName)
                .isEqualTo(rq.getName());
    }

}
