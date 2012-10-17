package com.mincoms.book.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mincoms.book.domain.News;



public interface NewsRepository extends JpaRepository<News, Integer> {
	News findByNewsTitle(String NewsTitle);
}
