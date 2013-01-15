package net.itsplace.repository;

import java.util.List;

import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceReview;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface PlaceReviewRepository  extends JpaRepository<PlaceReview, Integer> {

	List<PlaceReview> findByPlace(Place place);
}
