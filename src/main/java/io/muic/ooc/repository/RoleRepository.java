package io.muic.ooc.repository;

import io.muic.ooc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.muic.ooc.model.Role;


import java.util.Set;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer>{
	//Role findByRole(String role);
	Role findById(int id);
}
