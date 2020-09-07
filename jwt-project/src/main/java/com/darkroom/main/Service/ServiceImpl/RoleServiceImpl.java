package com.darkroom.main.Service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.darkroom.main.Service.RoleService;
import com.darkroom.main.model.Role;
import com.darkroom.main.repository.RoleRepository;


@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Override
	public Role findByRole(String role) {
		return roleRepository.findByRole(role);
	}
}
