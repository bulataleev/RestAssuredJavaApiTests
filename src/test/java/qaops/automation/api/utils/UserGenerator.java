package qaops.automation.api.utils;

import qaops.automation.api.pojos.UserRequest;

public class UserGenerator {

    public static UserRequest getSimpleUser(){
        return UserRequest.builder()
                .name("simple2")
                .job("automation2")
                .build();
    }
}
