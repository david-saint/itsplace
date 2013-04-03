package net.itsplace.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceComment;
import net.itsplace.domain.PlaceReview;
import net.itsplace.domain.QPlaceComment;
import net.itsplace.domain.QPlaceReview;
import net.itsplace.domain.dto.PlaceUserMedia;
import net.itsplace.repository.PlaceCommentRepository;
import net.itsplace.repository.PlaceRepository;
import net.itsplace.repository.PlaceReviewRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.Comparator;

import javax.servlet.ServletContext;

import com.mysema.query.types.Predicate;

public interface PlaceUserMediaService {
	
	public List<PlaceUserMedia> findByPlace(Place place);
}

@Service("PlaceUserMediaService")
class PlaceUserMediaServiceImpl implements PlaceUserMediaService {
	private static final Logger logger = LoggerFactory.getLogger(PlaceUserMediaService.class);
	

	@Autowired
	WebApplicationContext webApplicationContext;
	
	@Autowired
	PlaceCommentRepository commentRepo;
	@Autowired
	PlaceReviewRepository reviewRepo;
	
	@Override
	public List<PlaceUserMedia> findByPlace(Place place) {
		List<PlaceUserMedia> userMedias = new ArrayList();
		String imageHost = (String) webApplicationContext.getServletContext().getAttribute("ImageHost");
		logger.info("imagehost:"+imageHost);
		QPlaceComment placeComment = QPlaceComment.placeComment;
		Predicate predicate = placeComment.isDelete.eq(false).and(placeComment.place.eq(place));
    	Iterable<PlaceComment> list = commentRepo.findAll(predicate);
    	
    	PlaceUserMedia placeUserMedia = null;
    	for (PlaceComment c : list) {
			placeUserMedia = new PlaceUserMedia();
			placeUserMedia.setMkey(c.getCid());
			placeUserMedia.setTitle("");
			placeUserMedia.setMtype("comment");
			placeUserMedia.setContent(c.getComment());
			placeUserMedia.setUrl( c.getFilePath());
			placeUserMedia.setCreateDate(c.getSaveDate());
			userMedias.add(placeUserMedia);
		}
    	
    	
    	QPlaceReview placeReview = QPlaceReview.placeReview;
		predicate = placeReview.isDelete.eq(false).and(placeReview.place.eq(place));
    	Iterable<PlaceReview> list2 = reviewRepo.findAll(predicate);
    	
    	for (PlaceReview r : list2) {
    		placeUserMedia = new PlaceUserMedia();
			placeUserMedia.setMkey(r.getRid());
			placeUserMedia.setTitle(r.getTitle());
			placeUserMedia.setMtype("review");
			placeUserMedia.setContent(r.getContent());
			placeUserMedia.setUrl(r.getFilePath());
			placeUserMedia.setSiteUrl(r.getSiteURL());
			placeUserMedia.setCreateDate(r.getSaveDate());
			userMedias.add(placeUserMedia);
		}
    	Comparator compate = new Comparator<PlaceUserMedia>() {

			@Override
			public int compare(PlaceUserMedia o1, PlaceUserMedia o2) {
				//o1.getCreateDate()
				return 0;
			}
		};
    	//Collections.sort(userMedias, )
		return userMedias;
	}
}
