package io.muic.ooc.webapp.service;

import io.muic.ooc.webapp.entities.User;


public interface UserService {
    void save(User user);

    User findByUsername(String username);

    boolean validate(String username, String password);
}
