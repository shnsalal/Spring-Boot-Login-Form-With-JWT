package com.darkroom.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.darkroom.main.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	public Role findByRole(String role);
}
