package com.learning.kmama.hibernate.daosimp;

import com.learning.kmama.entities.UserEntity;
import com.learning.kmama.hibernate.util.SessionManager;
import com.learning.kmama.hibernate.util.TransactionUtil;
import org.hibernate.Session;

import java.util.List;

public class UserDAO extends AbstractDao <UserEntity>{
    private UserEntity user;

    public UserDAO() {
        super(UserEntity.class);
    }

}
