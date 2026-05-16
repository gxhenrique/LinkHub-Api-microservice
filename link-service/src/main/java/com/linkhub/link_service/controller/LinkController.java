package com.linkhub.link_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linkhub.link_service.dto.LinkDTO;
import com.linkhub.link_service.dto.LinkResponseDTO;
import com.linkhub.link_service.entity.Link;
import com.linkhub.link_service.service.LinkService;

@RestController
@RequestMapping(value = "/links")
public class LinkController {
	
	@Autowired
	private LinkService service;
	
	@PostMapping
	public ResponseEntity<Link> created(@RequestBody Link dto){
		Link obj = service.created(dto);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<LinkResponseDTO>> findAll(){
		
		List<LinkResponseDTO> list = service.findAll();
	
		return ResponseEntity.ok().body(list);
		
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<LinkResponseDTO>> findByLinksUserId(@PathVariable Long userId){
		List<LinkResponseDTO> list = service.findByUserId(userId);
		return ResponseEntity.ok().body(list);
	}
	
	
	

}
