package net.itsplace.place.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSourceAware;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.itsplace.domain.Stamp;
import net.itsplace.place.dao.PlaceStampDao;
import net.itsplace.util.Encrypt;
import net.itsplace.util.StringUtil;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;


@Service("placeStampService")
public class PlaceStampServiceImpl implements PlaceStampService {
	private static final Logger logger = LoggerFactory.getLogger(PlaceStampServiceImpl.class);
	
	@Autowired
	private PlaceStampDao placeStampDao;

	@Override
	public void saveStamp(Stamp stamp) {
		placeStampDao.saveStamp(stamp);		
	}

	@Override
	public void burnStamp(Stamp stamp) {
		placeStampDao.burnStamp(stamp);
	}
	
	

	
	
}
