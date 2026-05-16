package com.linkhub.link_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.linkhub.link_service.entity.Link;

public interface LinkeRepository extends JpaRepository<Link, Long> {
	
	List<Link> findByUserId(Long id);
}
