package net.itsplace.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Service;

import com.ibatis.sqlmap.client.SqlMapClient;

@Service("PagingManager")
public class PagingManager  extends SqlMapClientDaoSupport  {
	private static final Logger logger =  LoggerFactory.getLogger(PagingManager.class);
	
	private List<Integer> pageNo;	// 페이지 그룹의 페이지 번호를 담고 있는 Listg

	private int prev       ;		// 이전 페이지 그룹의 페이지 번호
	private int next       ;		// 다음 페이지 그룹의 페이지 번호
	private int last       ;		// 마지막 페이지 번호
	private int currentPage;		// 현재 페이지 번호
	private int pageSize   ;		// 페이지별 리스트 갯수
	private int totalCount ;		// 리스트 총 갯수
	private int pageGroupSize;   //페이지 그룹의 페이지 갯수
	private String pageHtml;

	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}
	public Integer getFoundRows()
			throws DataAccessException {		
		return (Integer) getSqlMapClientTemplate().queryForObject("common.getFoundRows");
	}
	/**
	 * <pre>
	 * Mysql Limit 조건 생성( 현재페이지, 페이지당 갯수)
	 * </pre>
	 * @param currentPage
	 * @param pageSize
	 * @param totalCount
	 * @param pageGroupSize
	 */
	
	public  Map<String, Object>  createMysqlLimit(int currentPage, int pageSize){
		
		Map<String, Object> param = new HashMap<String, Object>();
		
		int pageSizeTmp = pageSize;
		int currentPageTmp = currentPage;
		
		int startRow = pageSizeTmp * currentPageTmp - (pageSizeTmp -1);
		int endRow =  pageSizeTmp;
		//final int endRow = currentPage * pageSize;
		if(startRow==1){
			startRow=0;
		}
		param.put("startRow",startRow);
		param.put("endRow",endRow);
		logger.info("페이징: startRow:" +startRow);
		logger.info("페이징: endRow:" +endRow);
		return param;
	}
	public  Map<String, Object>  createDataTableLimit(int iDisplayStart, int iDisplayLength){
		
		Map<String, Object> param = new HashMap<String, Object>();
		
		int pageSizeTmp = iDisplayLength;
		int currentPageTmp = iDisplayStart;
		
		int startRow = currentPageTmp;
		int endRow =  pageSizeTmp;
		//final int endRow = currentPage * pageSize;
		
		param.put("startRow",startRow);
		param.put("endRow",endRow);
		logger.info("페이징: startRow:" +startRow);
		logger.info("페이징: endRow:" +endRow);
		return param;
	}
	
	/**
	 * <pre>
	 * 	페이징 만들기( 현재페이지, 페이지당 갯수, 전체ROW수, 페이지 그룹수
	 * </pre>
	 * @param currentPage
	 * @param pageSize
	 * @param totalCount
	 * @param pageGroupSize
	 */
	public String creatPaging(Integer currentPage, Integer pageSize, Integer totalCount,Integer pageGroupSize) {
		
		
			this.currentPage = currentPage;			
		
		
			this.pageSize = pageSize;			
		
		if(pageGroupSize == null){
			this.pageGroupSize=10;
		}else{
			this.pageGroupSize = pageGroupSize;			
		}
		
		this.totalCount =  totalCount;
		
		logger.info("currentPage:"+currentPage);
		logger.info("pageSize:"+pageSize);
		logger.info("pageGroupSize:"+pageGroupSize);
		logger.info("totalCount(전체 개시물수):"+totalCount);
		
		return generatePageHtml();
	}
	
	public String generatePageHtml() {
		
		pageNo = new ArrayList<Integer>();
		
		//this.currentPage       = (Integer) param.get("currentPage")  ;
		//this.pageSize          = (Integer) param.get("pageSize")     ;
		////int pageGroupSize = (Integer) param.get("pageGroupSize");				// 페이지 그룹의 페이지 갯수
		//this.totalCount        = (Integer) param.get("totalCount")   ;

		prev             = 0;
		next             = 0;
		int pageCount    = (totalCount/pageSize)+(totalCount%pageSize==0?0:1);	// 총페이지수
		last             = pageCount;
		int currentGroup = (int) Math.ceil((double)currentPage/pageGroupSize);	// 현재그룹
		int totalGroup   = pageCount/pageSize+1;								// 총그룹
		int startPage    = (currentGroup-1)*pageGroupSize+1; 					// 페이지 그룹의 시작페이지
		int endPage      = startPage+pageGroupSize; 							// 페이지 그룹의 마지막페이지
		
		if(endPage>pageCount){
			endPage = pageCount+1;
		}

		for(int i=startPage;i<endPage;i++){
			pageNo.add(i);
		}

		if(currentGroup>1){
		    prev=(currentGroup-2)*pageGroupSize+pageSize;
		}

		if(currentGroup<totalGroup){
			next=currentGroup*pageGroupSize+1;
		}
		
		return createPageHtml();
	}
	
	

	/********************************
	 * 페이징 소스 작성
	 * <div id="pagingNav">		
			<span class="round pageNum"><span class="img_cm_arrownavi prevPage_off">&lt;</span></span>
		
										<strong class="curNum">1</strong>	
															<a class="pageNum" href="/actions/categoryPlaceSearch?query=한식&amp;pageNo=2&amp;result=10">2</a>		
															<a class="pageNum" href="/actions/categoryPlaceSearch?query=한식&amp;pageNo=3&amp;result=10">3</a>		
															<a class="pageNum" href="/actions/categoryPlaceSearch?query=한식&amp;pageNo=4&amp;result=10">4</a>		
							
			<a class="round pageNum" href="/actions/categoryPlaceSearch?query=한식&amp;pageNo=5&amp;result=10"><span class="img_cm_arrownavi nextPage">&gt;</span></a>	
	</div>
	 *******************************/
	public String createPageHtml() {
		
		//String firstHtml  = "";
		String prevHtml   = "";
		String currHtml   = "";
		String nextHtml   = "";
		//String lastHtml   = "";
		
		//String firstSymbol = "";//<div id="<img src='images/board/frist.gif' alt='첫페이지' align='absmiddle'/>";
		//String prevSymbol  = "&nbsp<img src='images/board/prev.gif' alt='이전' align='absmiddle'/>";
		//String nextSymbol  = "&nbsp<img src='images/board/next.gif' alt='다음' align='absmiddle'/>";
		//String lastSymbol  = "&nbsp<img src='images/board/end.gif' alt='끝페이지' align='absmiddle'/>";
		
		//*** 첫 페이지 ***//
		//firstHtml = "<a class='firstPage'>처음</a>";
		
		//*** 이전 페이지 ***//
		if ( prev >= 0 )
			prevHtml = "<a class='prevPage' page='"+ prev +"'>이전&lt;</a>";
		//else
		//	prevHtml = prevSymbol;
		
		//*** 현재 그룹 페이지 ***//
		for ( int i = 0; pageNo.size() > i; i++ )
		{
			if(currentPage == pageNo.get(i).intValue())
				currHtml += "<a class='pageNumber currentPage'" + " page='"+pageNo.get(i) + "'>"+pageNo.get(i)+"</a>";
			else
				currHtml += "<a class='pageNumber'" + " page='"+pageNo.get(i) + "'>" + pageNo.get(i) + "</a>";
		}
		//*** 다음 페이지 ***//
		if ( next >= 0 )
			nextHtml = "<a class='nextPage' page='"+ next +"'>다음&gt;</a>";
	//	else
		//	nextHtml = nextSymbol;
		
		//*** 마지막 페이지 ***//
		//lastHtml = "<a href='javascript:doList(" + last + ")'>" + lastSymbol + "</a>";

			pageHtml = prevHtml+currHtml+nextHtml;
		logger.info("페이징:" +pageHtml);
		return pageHtml;
	}

	public String getTotalPage(String rows){
		int pageSize = Integer.parseInt(rows);	
		int totalPage = (totalCount/pageSize)+(totalCount%pageSize==0?0:1);
		return String.valueOf(totalPage);
	}
	public String getRecords(){		
		return String.valueOf(this.totalCount);
	}
	public String getPageHtml() {
		return pageHtml;
	}
	public List<Integer> getPageNo() {
		return pageNo;
	}

	public void setPageNo(List<Integer> pageNo) {
		this.pageNo = pageNo;
	}

	public int getPrev() {
		return prev;
	}

	public void setPrev(int prev) {
		this.prev = prev;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	
	
	//*** setter/getter ***//
	
}

