package io.muic.ooc.webapp.service;

import io.muic.ooc.webapp.entities.User;
import io.muic.ooc.webapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user){
        user.setHashedPassword(bCryptPasswordEncoder.encode(user.getHashedPassword()));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean validate(String username, String password){
        User uname = this.findByUsername(username);
        if (uname != null){
            if (bCryptPasswordEncoder.matches(password,uname.getHashedPassword())){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}
