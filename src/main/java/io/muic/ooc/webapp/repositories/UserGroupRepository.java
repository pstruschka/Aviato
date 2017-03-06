package io.muic.ooc.webapp.repositories;


import io.muic.ooc.webapp.entities.UserGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupRepository extends CrudRepository<UserGroup, Long> {

}
