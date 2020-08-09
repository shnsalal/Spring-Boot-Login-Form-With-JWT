package com.messaging.main.Service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.messaging.main.Service.RoleService;
import com.messaging.main.model.Role;
import com.messaging.main.repository.RoleRepository;


@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Override
	public Role findRoleById(int id) {
		return roleRepository.findById(id);
	}
}
