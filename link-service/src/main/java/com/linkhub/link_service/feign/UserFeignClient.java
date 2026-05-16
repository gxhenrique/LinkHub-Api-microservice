package com.linkhub.link_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.linkhub.link_service.dto.UserDTO;

@FeignClient(name = "user-service")
public interface UserFeignClient {
	
	@GetMapping(value = "/users/{id}")
	UserDTO findById(@PathVariable("id") Long id);
}
