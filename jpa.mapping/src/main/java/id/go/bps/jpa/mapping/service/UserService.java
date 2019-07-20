package id.go.bps.jpa.mapping.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import id.go.bps.jpa.mapping.dto.user.CreateUserDTO;
import id.go.bps.jpa.mapping.dto.user.RoleUserDTO;
import id.go.bps.jpa.mapping.exception.EntityNotFoundException;
import id.go.bps.jpa.mapping.model.Role;
import id.go.bps.jpa.mapping.model.User;
import id.go.bps.jpa.mapping.repository.RoleRepository;
import id.go.bps.jpa.mapping.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Transactional(propagation = Propagation.MANDATORY)
	public User saveUser(CreateUserDTO createUserDTO, 
			@Nullable Long userId){
		User user = new User();
		if(userId != null) {
			user.setId(userId);
		}
		user.setUserName(createUserDTO.getUserName());
		user.setFirstName(createUserDTO.getFirstName());
		user.setLastName(createUserDTO.getLastName());
		user.setEmail(createUserDTO.getEmail());
		user.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
		
		return userRepository.save(user);
	}
	
	@Transactional(propagation = Propagation.MANDATORY)
	public void saveNewUserRole(Long userId, Long roleId) throws EntityNotFoundException {
		Role role = roleRepository.findById(roleId)
				.orElseThrow(()->new EntityNotFoundException(Role.class, "id",roleId.toString()));
		
		userRepository.findById(userId)
		.map(user -> {
			if(user.getRoles()==null) {
				List<Role> roles = new ArrayList<Role>();
				roles.add(role);
				user.setRoles(roles);
			}else {
				user.getRoles().add(role);
			}
			return userRepository.save(user).getRoles();
		}).orElseThrow(()->new EntityNotFoundException(User.class, "id",userId.toString()));
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = EntityNotFoundException.class)
	public void saveAll(CreateUserDTO createUserDTO, int action, @Nullable Long userId) throws EntityNotFoundException {
		User user;
		Long newUserId = null;
		// add
		if(action==1) {
			user = saveUser(createUserDTO, null);
			newUserId=user.getId();
		}
		// edit
		else {
			user = saveUser(createUserDTO, userId);
			newUserId=userId;
		}
		
		for (RoleUserDTO roleId : createUserDTO.getRoleId()) {
			saveNewUserRole(newUserId, roleId.getId());	
		}
	}
}
