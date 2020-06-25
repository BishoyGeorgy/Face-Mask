package com.learning.kmama.controllers;

import com.learning.kmama.entities.UserEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Title: ApiController.java
 * </p>
 *
 * <p>
 * Description:
 * </p>
 *
 * <p>
 * Copyright: Copyright(c) , 2020
 * </p>
 *
 * @author <a href="bishoy.georgy@incorta.com">Bishoy Georgy</a>
 * @version 1.0
 * @date 11/06/2020
 */

@RestController
@RequestMapping("/api")
public class ApiController {

    private static List<UserEntity> users = new ArrayList<>();

    static {
        for (int i = 0; i < 10; i++) {
            //users.add(new UserEntity("First Name " + i, "last Name " + i));
        }
    }

//    @GetMapping("/users")
//    public List<User> getAllUsers() {
//        return users;
//    }
}
