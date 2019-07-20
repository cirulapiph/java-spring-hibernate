package id.go.bps.jpa.mapping.controller;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.go.bps.jpa.mapping.dto.role.CreateRoleDTO;
import id.go.bps.jpa.mapping.dto.role.ListRoleDTO;
import id.go.bps.jpa.mapping.dto.user.CreateUserDTO;
import id.go.bps.jpa.mapping.dto.user.ListUserDTO;
import id.go.bps.jpa.mapping.exception.CustomErrorException;
import id.go.bps.jpa.mapping.exception.EntityNotFoundException;
import id.go.bps.jpa.mapping.model.Role;
import id.go.bps.jpa.mapping.model.User;
import id.go.bps.jpa.mapping.repository.UserRepository;
import id.go.bps.jpa.mapping.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// list
	@GetMapping("/users")
	public ResponseEntity<List<ListUserDTO>> getAllUsers() {
		List<User> listUser = (List<User>) userRepository.findAll();
		Type targetType = new TypeToken<List<ListUserDTO>>() {}.getType();
		return ResponseEntity.ok(modelMapper.map(listUser, targetType));
	}
	
	// add
	@PostMapping("/users")
	public ResponseEntity<CreateUserDTO> addNewUser(@Valid @RequestBody CreateUserDTO newUsers) throws EntityNotFoundException{
		userService.saveAll(newUsers,1,null);
		return ResponseEntity.status(HttpStatus.CREATED).body(newUsers);
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<CreateUserDTO> updateUser(@Valid @RequestBody CreateUserDTO updatedUsers,
			@PathVariable Long id) throws EntityNotFoundException{
		userRepository.findById(id)
		.orElseThrow
		(()->new EntityNotFoundException(User.class, "id",id.toString()));
		
		userService.saveAll(updatedUsers, 2, id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedUsers);
		
	}
}
