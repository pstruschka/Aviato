package io.muic.ooc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.muic.ooc.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	 User findByUsername(String username);
	 List<User> findUsersByUsernameContains(String keyword);
}
