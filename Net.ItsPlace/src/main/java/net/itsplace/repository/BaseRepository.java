package net.itsplace.repository;

import net.itsplace.domain.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface BaseRepository extends JpaRepository<Bascd, Integer>, QueryDslPredicateExecutor<Bascd> {

}
