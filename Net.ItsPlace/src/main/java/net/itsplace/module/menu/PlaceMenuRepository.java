package net.itsplace.module.menu;

import java.util.List;

import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceComment;
import net.itsplace.domain.PlaceMenu;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceMenuRepository  extends JpaRepository<PlaceMenu, Integer> {

	Page<PlaceMenu> findByPlace(Place place, Pageable pageable);
	List<PlaceMenu> findByPlace(Place place);
}
