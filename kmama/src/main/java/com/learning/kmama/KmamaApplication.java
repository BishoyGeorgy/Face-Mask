package com.learning.kmama;

import com.learning.kmama.entities.UserEntity;
import com.learning.kmama.hibernate.daosimp.UserDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KmamaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KmamaApplication.class, args);
		UserDAO userDao = new UserDAO();
		UserEntity user = new UserEntity();
		user.setName("Alia");
		user.setGender("F");
		user.setPassword("123");
		userDao.insert(user);
	}

}
