package com.example.springsoapwebservicedemo.endpoint;

import com.example.springsoapwebservicedemo.api.loaneligibility.GetUserRequest;
import com.example.springsoapwebservicedemo.api.loaneligibility.GetUserResponse;
import com.example.springsoapwebservicedemo.api.loaneligibility.User;
import com.example.springsoapwebservicedemo.service.UserService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
public class UserEndpoint {
    private final UserService userService;

    public UserEndpoint(UserService userService) {
        this.userService = userService;
    }

    @PayloadRoot(namespace = "com/example/springsoapwebservicedemo/api/loanEligibility", localPart = "getUserRequest")
    @ResponsePayload
    public GetUserResponse getUserRequest(@RequestPayload GetUserRequest getUserRequest) {
        GetUserResponse response = new GetUserResponse();
        User user = userService.getUser(getUserRequest.getName());
        response.setUser(user);
        return response;
    }
}
