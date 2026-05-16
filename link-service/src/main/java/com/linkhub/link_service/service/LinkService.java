package com.linkhub.link_service.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkhub.link_service.dto.LinkDTO;
import com.linkhub.link_service.dto.LinkResponseDTO;
import com.linkhub.link_service.dto.UserDTO;
import com.linkhub.link_service.entity.Link;
import com.linkhub.link_service.feign.UserFeignClient;
import com.linkhub.link_service.repository.LinkeRepository;

@Service
public class LinkService {
	
	@Autowired
	private LinkeRepository repository;
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	public Link created(Link dto) {
		
		
		
		UserDTO user = userFeignClient.findById(dto.getUserId());
		
		Link link = new Link();
		link.setName(dto.getName());
		link.setUrl(dto.getUrl());
		link.setUserId(user.id());
		
		
		return repository.save(link);
	
	}
	
	public List<LinkResponseDTO> findAll(){
		
		List<Link> list = repository.findAll();
		
		List<LinkResponseDTO> linksDTO = list.stream()
			    .map(Link -> LinkResponseDTO.fromEntity(Link))
			    .toList();
		
		return linksDTO;
		
	}
	
	public List<LinkResponseDTO> findByUserId(Long id){
		List<Link> list = repository.findByUserId(id);
		
		List<LinkResponseDTO> links = list.stream().map(Link -> LinkResponseDTO.fromEntity(Link)).toList();
		
		return links;

	}
	

	
}
