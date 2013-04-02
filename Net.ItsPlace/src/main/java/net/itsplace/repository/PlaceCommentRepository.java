package net.itsplace.repository;

import java.util.List;

import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceComment;
import net.itsplace.domain.PlaceMedia;
import net.itsplace.domain.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface PlaceCommentRepository  extends JpaRepository<PlaceComment, Integer>, QueryDslPredicateExecutor<PlaceComment>{

	Page<PlaceComment> findByPlace(Place place, Pageable pagealbe);
	List<PlaceComment> findByPlace(Place place);
}
