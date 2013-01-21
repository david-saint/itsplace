package net.itsplace.exception;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import net.itsplace.domain.DataTable;
import net.itsplace.domain.JpaPaging;



public interface ExceptionService {

	public DataTable<AppException> getExceptionList(JpaPaging page, AppException exception); 
		
	
}

@Service
 class ExceptionServiceImpl implements ExceptionService {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionServiceImpl.class);
	@Autowired
	private ExceptionRepository exceptionRepo;
	@Override
	public DataTable<AppException> getExceptionList(JpaPaging page, AppException exception) {
		
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
