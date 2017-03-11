package io.muic.ooc.service;

import io.muic.ooc.model.User;

public interface UserService {
	public User findUserByUsername(String username);
	public void saveUser(User user);

}
