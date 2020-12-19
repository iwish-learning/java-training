package com.iwish.learning.java.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iwish.learning.java.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Long> {

}
