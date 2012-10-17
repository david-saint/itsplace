package com.mincoms.book.admin.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.mincoms.book.admin.repository.ExceptionSpecs;
import com.mincoms.book.admin.repository.RestrictionRepository;
import com.mincoms.book.admin.repository.RestrictionSpecs;
import com.mincoms.book.domain.AppException;
import com.mincoms.book.domain.BaseCode;
import com.mincoms.book.domain.BookRestriction;
import com.mincoms.book.domain.DataTable;
import com.mincoms.book.domain.JsonResponse;
import com.mincoms.book.domain.Paging;
import com.mincoms.book.domain.UserInfo;
import com.mincoms.book.domain.dto.DtoBookRestriction;
import com.mincoms.book.domain.vo.VoBookRestriction;
import com.mincoms.book.security.SignedUser;
import com.mincoms.book.service.UserService;
import com.mincoms.book.util.DateUtil;

@Service
public class RestrictionServiceImpl implements RestrictionService {
	private static final Logger logger = LoggerFactory.getLogger(RestrictionServiceImpl.class);
	
	@Autowired
	RestrictionRepository restrictionRepo;
	@Autowired
	UserService userService;
	@Autowired
	MessageSource messageSource;
	
	@Override
	public void save(DtoBookRestriction dtoBookRestriction) {
		
		BookRestriction bookRestriction = new BookRestriction();
		for(int i=0; i<dtoBookRestriction.getRestrictUsers().size();i++){
			bookRestriction.setBasecode(new BaseCode( Integer.parseInt(dtoBookRestriction.getRestrictReason())));
			String userName = dtoBookRestriction.getRestrictUsers().get(i);
			UserInfo userInfo = userService.findByUserName(userName);
			if(userInfo != null){
				bookRestriction.setUserInfo(userInfo);
				bookRestriction.setRegDate(new Date());
				if(restrictionRepo.findByUserInfoAndSolveDateIsNull(userInfo) == null){
					restrictionRepo.save(bookRestriction);
				}
			}
			
		}
		
	}
	@Override
	public DataTable<VoBookRestriction> getRestrictionUserList(Paging page, Boolean isSolved) {
		
        DataTable<VoBookRestriction> table = new DataTable<VoBookRestriction>(page);
  		Specifications<BookRestriction> spec = Specifications.where(RestrictionSpecs.isSolved(isSolved) );
  		
  		//spec = spec.and(ExceptionSpecs.isDeleted(exception.getIsDeleted()));
  		
  		if(!page.getsSearch().equals("")){
  		//	spec = spec.and(ExceptionSpecs.searchMessage(page.getsSearch()));
  			String pattern = "^[0-9]*$";
  			if( Pattern.matches(pattern, page.getsSearch())){
  			//	spec = spec.or(ExceptionSpecs.searchId(Integer.parseInt(page.getsSearch())));
  			}
  		}
  		
  		Page<BookRestriction> bookRestrictions = restrictionRepo.findAll(spec, page.getPageable());
  		List<VoBookRestriction> voBookRegistrictions  = new ArrayList();
  		VoBookRestriction vo = null;
  		for(BookRestriction br: bookRestrictions.getContent()){
  			vo = new VoBookRestriction();
  			vo.setId(br.getId());
  			vo.setUserId(br.getUserInfo().getUserId());
  			vo.setReason(br.getBasecode().getCodeDesc());
  			vo.setRegDate(br.getRegDate());
  			vo.setSolveDate(br.getSolveDate());
  			vo.setDeptName(br.getUserInfo().getDeptInfo().getDeptName());
  			vo.setUserRname(br.getUserInfo().getUserRname());
  			vo.setSolveReason(br.getSolveReason());
  			voBookRegistrictions.add(vo);
  		}
  		table.setRows(voBookRegistrictions); 		 
		table.setiTotalDisplayRecords(bookRestrictions.getTotalElements());
	
		  return table;
	}
	@Override
	public BookRestriction findByBookRestriction(int id) {
		return restrictionRepo.findOne(id);
	}
	@Override
	public JsonResponse save(BookRestriction BookRestriction) {
		BookRestriction bookRestriction =restrictionRepo.save(BookRestriction);
		JsonResponse json = new JsonResponse();
		json.setResult(messageSource.getMessage("solveUser", new Object [] {bookRestriction.getUserInfo().getUserRname()}, Locale.getDefault()));
		json.setSuccess();
		return json;
	}
	@Override
	public BookRestriction findByUserInfoAndSolveDateIsNull(UserInfo userInfo) {
		return restrictionRepo.findByUserInfoAndSolveDateIsNull(userInfo);
	}
	@Override
	public JsonResponse isRestriction(UserInfo userInfo) {
		JsonResponse json = new JsonResponse();
		BookRestriction brs = this.findByUserInfoAndSolveDateIsNull(userInfo);		
		if(brs != null){
			json.setResult(messageSource.getMessage("registration.user", new Object [] {brs.getBasecode().getCodeDesc()}, Locale.getDefault()));
			json.setSuccess();			
		}else{
			json.setFail();
		}
		return json;
	}
	@Override
	public List<BookRestriction> findByUserInfo(int userId) {
		UserInfo userInfo = userService.findByUserId(userId);
		return restrictionRepo.findByUserInfo(userInfo);
	}
	
	//@Scheduled(cron="*/5 * * * * ?")//5초
	
	
}
