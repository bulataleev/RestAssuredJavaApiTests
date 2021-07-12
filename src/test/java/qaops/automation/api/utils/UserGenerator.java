package qaops.automation.api.utils;

import qaops.automation.api.pojos.CreateUserRequest;

public class UserGenerator {

    public static CreateUserRequest getSimpleUser(){
        return CreateUserRequest.builder()
                .name("simple1")
                .job("automation1")
                .build();
    }
}
