package io.muic.ooc.service;

import io.muic.ooc.configuration.SecurityConfiguration;
import io.muic.ooc.repository.RoleRepository;
import io.muic.ooc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import io.muic.ooc.model.Role;
import io.muic.ooc.model.User;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;
    //@Autowired
    //private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private SecurityConfiguration securityConfiguration;
	
	@Override
	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(securityConfiguration.passwordEncoder().encode(user.getPassword()));
		Role userRole = roleRepository.findById(user.getRole().getId());
        user.setRole(userRole);
		user.setActive(1);
		userRepository.save(user);
	}

}
