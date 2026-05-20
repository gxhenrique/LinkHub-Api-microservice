package com.linkhub.user_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.linkhub.user_service.dto.LinkDTO;
import com.linkhub.user_service.dto.UserDTO;
import com.linkhub.user_service.dto.UserWithLinksDTO;
import com.linkhub.user_service.entity.User;
import com.linkhub.user_service.exception.ResourceAlreadyExists;
import com.linkhub.user_service.exception.ResourceNotFound;
import com.linkhub.user_service.feign.LinkServiceFeign;
import com.linkhub.user_service.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private LinkServiceFeign linkServiceFeign;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public List<User> findAllUsers(){
		
		List<User> list = repository.findAll();
		return list;
	}
	
	public User findById(Long id) {
		
		return repository.findById(id).orElseThrow(() -> new ResourceNotFound("Usuario não  encotrado com  id: " + id));
		
	}
	
	public User create(UserDTO dto) {
		
		if(repository.existsByEmail(dto.email())) {
			throw new ResourceAlreadyExists("Email já cadastrado");
		}
		
		User user = new User();
		user.setName(dto.name());
		user.setEmail(dto.email());
		user.setPassword(passwordEncoder.encode(dto.password()));
		
		return repository.save(user);
	}
	
	public void delete(Long id) {
		
		User entity = findById(id);
		repository.delete(entity);
	//	try {
	//		User entity = findById(id);
	//		repository.delete(entity);
	//	} catch (ResourceNotFound e) {
	//		throw new ResourceNotFound(e.getMessage());
	//	}
		
	}
	
	public User update(Long id, UserDTO dto) {
		
		User entity = repository.findById(id).orElseThrow(() -> new ResourceNotFound("Usuario não  encotrado com  id: " + id ));
		updateUser(entity, dto);
		
		return repository.save(entity);
		
		
	}
	
	private void updateUser(User entity, UserDTO dto) {
		entity.setEmail(dto.email());
		entity.setName(dto.name());
		entity.setPassword(passwordEncoder.encode(dto.password()));
		
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
		
		User user = repository.findById(id).orElseThrow(() -> new ResourceNotFound("User not found with id: " + id));
		
		List<LinkDTO> links = linkServiceFeign.findByUserId(id);
		
		return new UserWithLinksDTO(
				user.getId(),
				user.getName(),
				user.getEmail(),
				links
				
				
				);
		
	}


	
	
}
