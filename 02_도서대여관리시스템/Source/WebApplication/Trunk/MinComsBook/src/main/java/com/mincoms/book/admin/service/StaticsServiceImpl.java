package com.mincoms.book.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.mincoms.book.admin.dao.StaticsDao;
import com.mincoms.book.admin.repository.RestrictionRepository;
import com.mincoms.book.admin.repository.RestrictionSpecs;
import com.mincoms.book.domain.BookInfo;
import com.mincoms.book.domain.BookRental;
import com.mincoms.book.domain.BookRestriction;
import com.mincoms.book.domain.DataTable;
import com.mincoms.book.domain.Paging;
import com.mincoms.book.domain.dto.DtoBookInfo;
import com.mincoms.book.domain.dto.DtoRentalStatics;
import com.mincoms.book.repository.RentalRepository;
import com.mincoms.book.service.RentalService;
import com.mincoms.book.service.UserService;

@Service
public class StaticsServiceImpl  implements StaticsService {
	private static final Logger logger = LoggerFactory.getLogger(StaticsServiceImpl.class);
	
	@Autowired
	StaticsDao staticsDao;
	@Autowired
	UserService userService;
	@Autowired
	MessageSource messageSource;
	
	@Override
	public DataTable findRentalStatics(Paging paging) {
		DataTable<DtoRentalStatics> table = new DataTable<DtoRentalStatics>(paging);
		
		List<DtoRentalStatics> results = new ArrayList();
		results = staticsDao.getRentalStatics( paging.getParameter());
					
		table.setRows(results); 
		table.setiTotalDisplayRecords(staticsDao.getRentalStaticsCount(paging.getParameter()));
		
		return table;
	}	
}
