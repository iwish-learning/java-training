package com.iwish.learning.java.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iwish.learning.java.dao.UserDao;
import com.iwish.learning.java.model.User;

@Component
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public String getName(Long id) {
		Optional<User> user = userDao.findById(id);
		return user.get().getName();
	}

}
