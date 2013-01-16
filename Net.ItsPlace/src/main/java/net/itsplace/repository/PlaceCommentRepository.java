package net.itsplace.repository;

import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceComment;
import net.itsplace.domain.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceCommentRepository  extends JpaRepository<PlaceComment, Integer> {

	Page<PlaceComment> findByPlace(Place place, Pageable pagealbe);
}
