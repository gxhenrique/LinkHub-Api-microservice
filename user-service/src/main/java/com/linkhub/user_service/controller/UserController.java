package com.linkhub.user_service.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linkhub.user_service.dto.UserWithLinksDTO;
import com.linkhub.user_service.entity.User;
import com.linkhub.user_service.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	 
	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> list = service.findAllUsers();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/user/{userId}")
	public ResponseEntity<UserWithLinksDTO> findUserWithId(@PathVariable Long userId){
		UserWithLinksDTO obj = service.findUserWithLinksDTO(userId);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<User> insert(User user){
		User obj = service.create(user);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@PutMapping(value = "/id")
	public ResponseEntity<User> update(@PathVariable Long id, User user ){
		User obj =  service.update(id, user);
		return ResponseEntity.ok().body(obj);		
	}
	
	@PatchMapping(value = "/id")
	public ResponseEntity<User> patch(@PathVariable Long id, User user){
		User obj = service.patch(id, user);
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping(value = "/id")
	public ResponseEntity<User> delete(@PathVariable Long id){
		service.delete(id);
		
		return ResponseEntity.noContent().build();
		
	}
	
}
