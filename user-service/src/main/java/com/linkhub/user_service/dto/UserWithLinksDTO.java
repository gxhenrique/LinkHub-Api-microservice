package com.linkhub.user_service.dto;

import java.util.List;

public record UserWithLinksDTO(Long id, String name, String email, List<LinkDTO> links) {

}
