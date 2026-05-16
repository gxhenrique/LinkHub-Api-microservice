package com.linkhub.user_service.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.linkhub.user_service.dto.LinkDTO;

@FeignClient(name = "link-service")
public interface LinkServiceFeign {
	
	@GetMapping(value = "/links/user/{userId}")
	List<LinkDTO> findByUserId(@PathVariable("userId") Long userId);

}
