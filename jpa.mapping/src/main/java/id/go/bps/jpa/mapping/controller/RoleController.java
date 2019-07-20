package id.go.bps.jpa.mapping.controller;

import java.lang.reflect.Type;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.go.bps.jpa.mapping.dto.role.CreateRoleDTO;
import id.go.bps.jpa.mapping.dto.role.ListRoleDTO;
import id.go.bps.jpa.mapping.exception.CustomErrorException;
import id.go.bps.jpa.mapping.model.Role;
import id.go.bps.jpa.mapping.repository.RoleRepository;

@RestController
@RequestMapping("/api/v1")
public class RoleController {
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	// list
	@GetMapping("/roles")
	public ResponseEntity<List<ListRoleDTO>> getAllRoles() {
		List<Role> listRole = (List<Role>) roleRepository.findAll();
		Type targetType = new TypeToken<List<ListRoleDTO>>() {}.getType();
		return ResponseEntity.ok(modelMapper.map(listRole, targetType));
	}
	
	//list by ID
	@GetMapping("/roles/{id}")
	public ResponseEntity<ListRoleDTO> getAllRolesById(@PathVariable Long id) {
		Role role = roleRepository.findById(id)
				.orElseThrow(()-> new CustomErrorException("Roles not found"));
		
		return ResponseEntity.ok(modelMapper.map(role, ListRoleDTO.class));
	}
	
	// add
	@PostMapping("/roles")
	public ResponseEntity<CreateRoleDTO> addNewRole(@Valid @RequestBody CreateRoleDTO newRoles){
		roleRepository.save(modelMapper.map(newRoles, Role.class));
		return ResponseEntity.status(HttpStatus.CREATED).body(newRoles);
	}
	
	//edit
	@PutMapping("/roles/{id}")
	public ResponseEntity<CreateRoleDTO> updateRole(@Valid @RequestBody CreateRoleDTO updatedRoles,
			@PathVariable Long id){
		roleRepository.findById(id).orElseThrow(()->new CustomErrorException("Roles not found"));
		Role role = modelMapper.map(updatedRoles, Role.class);
		role.setId(id);
		roleRepository.save(role);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedRoles);
		
	}
}
