package com.linkhub.link_service.dto;

import com.linkhub.link_service.entity.Link;

public record LinkResponseDTO(Long id, String name, String url, Long userId) {
	
	public static LinkResponseDTO fromEntity(Link link) {
		return new LinkResponseDTO(
				link.getId(),
				link.getName(),
				link.getUrl(),
				link.getUserId()
				
				);
	}
}
