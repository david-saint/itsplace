package net.itsplace.repository;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceComment;
import net.itsplace.domain.PlaceMedia;
import net.itsplace.domain.QPlaceComment;
import net.itsplace.domain.QPlaceMedia;
import net.itsplace.domain.QPlaceReview;
import net.itsplace.domain.dto.PlaceUserMedia;
import net.itsplace.init.TestApplicationContext;

import org.apache.velocity.runtime.directive.Foreach;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mysema.query.types.Predicate;

public class PlaceCommentRepositoryTest extends TestApplicationContext {
	  
	private static final Logger logger  = LoggerFactory.getLogger(PlaceCommentRepositoryTest.class); 

	@Autowired
	PlaceCommentRepository commentRepo;
	
	@Autowired
	PlaceReviewRepository reviewRepo;
	
	@Ignore
	public void testFindByPlace() {
		Place place = new Place();
		place.setFid(46);
		
		
		List<PlaceUserMedia> userMedias = new ArrayList();
		
		QPlaceComment placeComment = QPlaceComment.placeComment;
		Predicate predicate = placeComment.isDelete.eq(false).and(placeComment.place.eq(place));
    	Iterable<PlaceComment> list = commentRepo.findAll(predicate);
    	
    	PlaceUserMedia placeUserMedia;
    	for (PlaceComment c : list) {
			new PlaceUserMedia();
			placeUserMedia.setMkey(c.getCid());
			placeUserMedia.setTitle("");
			placeUserMedia.setMtype("comment");
			placeUserMedia.setContent(c.getComment());
			placeUserMedia.setUrl("");
			placeUserMedia.setCreateDate(c.getSaveDate());
			userMedias.add(placeUserMedia);
		}
    	
    	
    	
    	
    	QPlaceReview placeReview = QPlaceReview.placeReview;
		predicate = placeReview.isDelete.eq(false).and(placeComment.place.eq(place));
    	Iterable<PlaceReview> list2 = reviewRepo.findAll(predicate);
    	
		commentRepo.findAll(pageable)
		
		
	}
	

}
