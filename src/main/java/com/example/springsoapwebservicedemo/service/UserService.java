package com.example.springsoapwebservicedemo.service;

import com.example.springsoapwebservicedemo.jaxb.userinfo.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private static final Map<String,User> users = new HashMap<>();


    @PostConstruct
    public void initialize() {
        User user1 = new User();
        user1.setName("Jon");
        user1.setEmpId(1111);
        user1.setSalary(10000);
        User user2 = new User();
        user2.setName("Sam");
        user2.setEmpId(2222);
        user2.setSalary(20000);
        User user3 = new User();
        user3.setName("Dave");
        user3.setEmpId(3333);
        user3.setSalary(30000);

        users.put(user1.getName(),user1);
        users.put(user2.getName(),user2);
        users.put(user3.getName(),user3);
    }

    public User getUser(String name) {
        return users.get(name);
    }
}
