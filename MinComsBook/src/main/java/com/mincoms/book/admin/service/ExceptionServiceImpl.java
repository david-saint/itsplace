package com.mincoms.book.admin.service;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.mincoms.book.admin.repository.ExceptionRepository;
import com.mincoms.book.admin.repository.ExceptionSpecs;
import com.mincoms.book.domain.AppException;
import com.mincoms.book.domain.BookInfo;
import com.mincoms.book.domain.DataTable;
import com.mincoms.book.domain.Paging;

@Service
public class ExceptionServiceImpl implements ExceptionService {

	@Autowired
	private ExceptionRepository exceptionRepo;
	@Override
	public DataTable<AppException> getExceptionList(Paging page, AppException exception) {
		
		
        DataTable<AppException> table = new DataTable<AppException>(page);
     
       
  		
  		Specifications<AppException> spec = Specifications.where(ExceptionSpecs.isCompletd(exception.getIsCompleted()));
  		
  		spec = spec.and(ExceptionSpecs.isDeleted(exception.getIsDeleted()));
  		
  		if(!page.getsSearch().equals("")){
  			spec = spec.and(ExceptionSpecs.searchMessage(page.getsSearch()));
  			
  			String pattern = "^[0-9]*$";
  			if( Pattern.matches(pattern, page.getsSearch())){
  				spec = spec.or(ExceptionSpecs.searchId(Integer.parseInt(page.getsSearch())));
  			}
  			
  		}
  		
  		Page<AppException> exceptions = exceptionRepo.findAll(spec, page.getPageable());
  		table.setRows(exceptions.getContent()); 		 
		table.setiTotalDisplayRecords(exceptions.getTotalElements());
	
		  return table;
	}
}
