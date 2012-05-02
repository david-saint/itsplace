package net.itsplace.stamp;

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
import net.itsplace.util.Encrypt;
import net.itsplace.util.StringUtil;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;


@Service("StampService")
public class StampServiceImpl implements StampService {
	private static final Logger logger = LoggerFactory.getLogger(StampServiceImpl.class);
	
	@Autowired
	private StampDao stampDao;

	@Override
	public void saveStamp(Stamp stamp) {
		stampDao.saveStamp(stamp);		
	}

	@Override
	public void burnStamp(Stamp stamp) {
		stampDao.burnStamp(stamp);
	}
	
	

	
	
}
