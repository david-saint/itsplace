package com.rangs.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rangs.domain.News;
import com.rangs.domain.User;

public interface NewsRepository extends JpaRepository<News, Integer> {
	
	
	
	News findByNewsTitle(String NewsTitle);
}
