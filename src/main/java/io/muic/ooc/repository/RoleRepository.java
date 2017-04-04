package io.muic.ooc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.muic.ooc.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	Role findById(int id);
}
