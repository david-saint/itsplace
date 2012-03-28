package com.myplace.bbs;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myplace.util.Encrypt;

@Service("BbsService")
public class BbsServiceImpl implements BbsService{
	protected final Log logger = LogFactory.getLog(getClass());

}
