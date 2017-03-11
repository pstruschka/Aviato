package io.muic.ooc.service;

import io.muic.ooc.model.User;

public interface UserService {
	public User findUserByUsername(String email);
	public void saveUser(User user);
}
