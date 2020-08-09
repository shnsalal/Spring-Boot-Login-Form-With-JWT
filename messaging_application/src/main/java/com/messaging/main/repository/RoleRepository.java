package com.messaging.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.messaging.main.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	public Role findById(int id);
}
