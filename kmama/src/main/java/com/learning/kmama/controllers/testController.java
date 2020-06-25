package com.learning.kmama.controllers;

import com.learning.kmama.entities.UserEntity;
import com.learning.kmama.hibernate.daosimp.AbstractDao;
import com.learning.kmama.hibernate.daosimp.UserDAO;

public class testController {

    public static void main(String... arg) {

        AbstractDao<UserEntity> userDao = new UserDAO();
        UserEntity user = new UserEntity();
        user.setName("Alia2");
        user.setGender("F");
        user.setPassword("1223");
        user.setEmail("Email2");
        user.setImage(new byte[8]);
        userDao.insert(user);
    }
}
