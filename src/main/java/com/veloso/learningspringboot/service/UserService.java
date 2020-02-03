package com.veloso.learningspringboot.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.veloso.learningspringboot.dao.UserDao;
import com.veloso.learningspringboot.model.User;
import com.veloso.learningspringboot.model.User.Gender;

@Service
public class UserService {

	private UserDao userDao;

	@Autowired
	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}

	public List<User> getAllUsers(Optional<String> gender) {
		List<User> users = userDao.selectAllUsers();
		if (!gender.isPresent()) {
			return users;
		}
		try {
			Gender theGender = User.Gender.valueOf(gender.get().toUpperCase());
			return users.stream().filter(user -> user.getGender().equals(theGender)).collect(Collectors.toList());
		} catch (Exception e) {
			throw new IllegalStateException("Invalid gender");
		}

	}

	public Optional<User> getUser(UUID userUid) {
		return userDao.selectUserByUserUid(userUid);
	}

	public int updateUser(User user) {
		Optional<User> optionalUser = getUser(user.getUserUid());
		if (optionalUser.isPresent()) {
			return userDao.updateUser(user);
		}
		return -1;
	}

	public int removeUser(UUID userUid) {
		Optional<User> optionalUser = getUser(userUid);
		if (optionalUser.isPresent()) {
			return userDao.deleteUserByUserUid(userUid);
		}
		return -1;
	}

	public int insertUser(User user) {
		UUID userUid = UUID.randomUUID();
		return userDao.insertUser(userUid, User.newUser(userUid, user));
	}

}
