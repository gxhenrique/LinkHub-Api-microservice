package com.linkhub.user_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkhub.user_service.dto.LinkDTO;
import com.linkhub.user_service.dto.UserWithLinksDTO;
import com.linkhub.user_service.entity.User;
import com.linkhub.user_service.feign.LinkServiceFeign;
import com.linkhub.user_service.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private LinkServiceFeign linkServiceFeign;
	
	public List<User> findAllUsers(){
		
		List<User> list = repository.findAll();
		return list;
	}
	
	public User findById(Long id) {
		
		return repository.findById(id).orElseThrow(() -> new RuntimeException("User nao encontrado"));
		
	}
	
	public User create(User user) {
		return repository.save(user);
	}
	
	public void delete(Long id) {
		User entity = findById(id);
		repository.delete(entity);
	}
	
	public User update(Long id, User user) {
		
		User entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Usuario não  encotrado com  id: " + id ));
		updateUser(entity, user);
		
		return entity;
		
		
	}
	
	private void updateUser(User entity, User user) {
		entity.setEmail(user.getEmail());
		entity.setName(user.getName());
		entity.setPassword(user.getPassword());
		
	}

	public User patch(Long id, User user) {
		User entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Usuario não  encotrado com  id: " + id ));
		updatePatch(entity, user);
		return entity;
	}

	private void updatePatch(User entity, User user) {
		if(user != null) {
			entity.setEmail(user.getEmail());
		}
		if(user != null) {
			entity.setName(user.getName());
		}
		
		if(user != null) {
			entity.setPassword(user.getPassword());
		}
		
		
		
	}
	
	public UserWithLinksDTO findUserWithLinksDTO(Long id) {
		User user = repository.findById(id).get();
		
		List<LinkDTO> links = linkServiceFeign.findByUserId(id);
		
		return new UserWithLinksDTO(
				user.getId(),
				user.getName(),
				user.getEmail(),
				links
				
				
				);
		
	}


	
	
}
