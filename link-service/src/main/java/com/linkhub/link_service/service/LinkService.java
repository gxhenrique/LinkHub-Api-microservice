package com.linkhub.link_service.service;



import java.security.PublicKey;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkhub.link_service.dto.LinkDTO;
import com.linkhub.link_service.dto.LinkResponseDTO;
import com.linkhub.link_service.dto.LinkUpdateDTO;
import com.linkhub.link_service.dto.UserDTO;
import com.linkhub.link_service.entity.Link;
import com.linkhub.link_service.exception.ResourceNotFound;
import com.linkhub.link_service.feign.UserFeignClient;
import com.linkhub.link_service.repository.LinkeRepository;

@Service
public class LinkService {
	
	@Autowired
	private LinkeRepository repository;
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	public Link created(LinkDTO dto) {
		
		UserDTO user = userFeignClient.findById(dto.userId());
		
		Link link = new Link();
		link.setName(dto.name());
		link.setUrl(dto.url());
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
	
	public Link findById(Long id) {
		Link link = repository.findById(id).orElseThrow(() -> new ResourceNotFound("Link não encontrado com o id " + id));
		
		return link;
	}
	
	public void delete(Long id) {
		Link link = repository.findById(id).orElseThrow(() -> new ResourceNotFound("Link não encontrado com o id " + id));
		repository.delete(link);
	}
	
	public LinkResponseDTO update(Long id, LinkUpdateDTO dto) {
		Link link = findById(id);
		
		link.setName(dto.name());
		link.setUrl(dto.url());
		
		repository.save(link);
		
		return LinkResponseDTO.fromEntity(link);
	}
	
	public LinkResponseDTO patch(Long id, LinkUpdateDTO dto) {
		Link link = findById(id);
		
		if(dto.name() != null) {
			link.setName(dto.name());
		}
		
		if(dto.url() != null) {
			link.setUrl(dto.url());
		}
		
		
		repository.save(link);
		
		return LinkResponseDTO.fromEntity(link);
	}
	

	
}
