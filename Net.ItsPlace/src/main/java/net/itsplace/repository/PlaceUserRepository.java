package net.itsplace.repository;

import java.util.List;

import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceReview;
import net.itsplace.domain.PlaceUser;
import net.itsplace.user.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceUserRepository extends JpaRepository<PlaceUser, Integer> {

	List<PlaceUser> findByPlace(Place place);
	Page<PlaceUser> findByPlace(Place place, Pageable pageable);
	List<PlaceUser> findByUser(User user);
	
}
