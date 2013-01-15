package net.itsplace.repository;

import java.util.List;

import net.itsplace.domain.JpaPaging;
import net.itsplace.domain.Place;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

@Component
public class PlaceRepo  {

	@Autowired
	PlaceRepository repo;
	
	/**
	 * 최근 등록된 Place
	 * @param limit 객수
	 * @return
	 */
	

}
