package io.muic.ooc.webapp.repositories;

import java.util.List;

import io.muic.ooc.webapp.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
