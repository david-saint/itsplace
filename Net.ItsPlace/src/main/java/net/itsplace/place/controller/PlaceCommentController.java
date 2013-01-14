package net.itsplace.place.controller;

import net.itsplace.domain.DataTable;
import net.itsplace.domain.PlaceComment;
import net.itsplace.place.service.PlaceUserService;
import net.itsplace.service.PlaceCommentService;
import net.itsplace.user.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PlaceCommentController {

	private static final Logger logger = LoggerFactory.getLogger(PlaceCommentController.class);
	@Autowired
	private PlaceCommentService placeCommentService;
	
	@Autowired
	
	
	/**
	 * 사용자관리 리스트 관리자는 사용자상세정보, 수정, 삭제 기 <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param iDisplayStart 페이지 번
	 * @param iDisplayLength 페이지 로우수 (한페이지에 보여줄 로우수)
	 * @param iSortCol_0 sort할 컬럼 번호 
	 * @param sSortDir_0 sort할 방향(asc/desc)
	 * @param sSearch 검색
	 * @return DataTables
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value="/place/getPlaceCommentList")
    @ResponseBody
    public DataTable<PlaceComment> getPlaceCommentList(
    								@RequestParam(required=false, defaultValue="1") Integer iDisplayStart,
    								@RequestParam(required=false) Integer iDisplayLength,
    								@RequestParam(required=false) Integer iSortCol_0, 
    								@RequestParam(required=false) String sSortDir_0, 
                                    @RequestParam(required=false, defaultValue="") String sSearch,
                                    @RequestParam(required=true) Integer fid) {

                    logger.info("iDisplayStart:{}", iDisplayStart.toString());
                    logger.info("sSortDir_0:{}", sSortDir_0);
                    logger.info("iSortCol_0:{}", iSortCol_0);
                    logger.info("iDisplayLength:{}", iDisplayLength);
                    logger.info("sSearch:{}", sSearch);
                 
                    String columns[] = new String[]{"cid", "email", "name","PROFILEIMAGEURL", "saveDate"};
                    return  placeCommentService.getPlaceCommentList(columns, iDisplayStart, iDisplayLength, iSortCol_0, sSortDir_0, sSearch, fid);
           
                   
    }       
}
