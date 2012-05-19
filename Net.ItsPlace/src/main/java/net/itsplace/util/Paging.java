package net.itsplace.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;


public class Paging  extends SqlMapClientDaoSupport  {
	private static final Logger logger = LoggerFactory.getLogger(Paging.class);
	
	private List<Integer> pageNo;	// 페이지 그룹의 페이지 번호를 담고 있는 List

	private int prev;		// 이전 페이지 그룹의 페이지 번호
	private int next;		// 다음 페이지 그룹의 페이지 번호
	private int last;		// 마지막 페이지 번호
	private int currentPage;		// 현재 페이지 번호
	private int pageSize;		// 페이지별 리스트 갯수
	private int totalCount;		// 리스트 총 갯수
	private	int pageGroupSize;
	
	private int pageCount;
	private int currentCroup;
	private int totalGroup;
	private int startPage;
	private int endPage;
	
	private String returnHtml;
	private String table;
	private String feild;
	private String where;
	
	private HashMap<String, Object> param;
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}
	
	/**
	 * 페이징 기본정보
	 * @param _currentpage
	 * @param _pagesize
	 * @param _totalCount
	 * @param _pageGroupSize
	 */
	public Paging(int _currentpage, int _pagesize, int _pageGroupSize, String _table, String _where)
	{
		this.currentPage 	= _currentpage;
		this.pageSize 		= _pagesize;
		this.table			= _table;
		this.where			= _where;
		this.pageGroupSize 	= _pageGroupSize;
		this.prev 			= 0;
		this.next 			= 0;
		
		param = new HashMap<String, Object>();
		param.put("table", table);
		param.put("where", where);
		this.totalCount 	= (Integer) getSqlMapClientTemplate().queryForObject("util.getCount", param);		
		
		init();
	}
	
	/**
	 * 페이징 기본 설정
	 * @return
	 */
	public void init() 
	{
		pageCount = (totalCount / pageSize) + (totalCount % pageSize == 0 ? 0 : 1);
		currentCroup = (int)Math.ceil((double) currentPage / pageGroupSize);
		totalGroup = pageCount / pageSize + 1;
		startPage = (currentCroup - 1) * pageGroupSize + 1;
		endPage = startPage + pageGroupSize;
		
		if(endPage > pageCount) endPage = pageCount + 1;
		
		pageNo = new ArrayList<Integer>();
		for(int i=startPage; i<endPage; i++)
		{
			pageNo.add(i);
		}
		
		if(currentCroup > 1) prev = (currentCroup - 2) * pageGroupSize + pageSize;
		if(currentCroup < totalGroup) next = currentCroup * pageGroupSize + 1;
	}
	
	/********************************
	 * 페이징 소스 작성
	 *******************************/
	public String getPageSet() {
		
		String firstHtml  = "";
		String prevHtml   = "";
		String currHtml   = "";
		String nextHtml   = "";
		String lastHtml   = "";
		
		String firstSymbol = "<img src='img/board/frist.gif' alt='첫페이지' align='absmiddle'/>";
		String prevSymbol = "<img src=\"http://static.naver.com/common/paginate/btn_page_prev.gif\" width=\"56\" height=\"27\" alt='이전'/>";
		String nextSymbol = "<img src=\"http://static.naver.com/common/paginate/btn_page_next.gif\" width=\"57\" height=\"27\" alt='다음'/>";
		String lastSymbol  = "<img src='img/board/end.gif' alt='끝페이지' align='absmiddle'/>";
		
		//*** 이전 페이지 ***//
		if ( prev > 0 )
			prevHtml = "<a href='#' onclick=\"doList(" + prev + ")\" class='pre'>"  + prevSymbol + "</a>\n";
		else
			prevHtml = "<span class='pre'>" + prevSymbol + "</span>";
		
		//*** 현재 그룹 페이지 ***//
		for ( int i = 0; pageNo.size() > i; i++ )
		{
			if(currentPage == pageNo.get(i).intValue())
				currHtml +="<strong><span class='page_num'>"+pageNo.get(i)+"</span></strong>\n";
			else
				currHtml += "<a href='#' onclick=\"doList(" + pageNo.get(i) + ")\"><span class='page_num'>" + pageNo.get(i) + "</span></a>\n";
		}
		//*** 다음 페이지 ***//
		if ( next > totalCount )
			nextHtml = "<a href='#' onclick=\"doList(" + next + ")\" class='next'>"  + nextSymbol + "</a>\n";
		else
			nextHtml = "<span class='next'>" +nextSymbol + "</span>";
		
		returnHtml = "<div class='paginate'>" + prevHtml+currHtml+nextHtml + "</div>";
		
		return returnHtml;
	}
	
	//*** setter/getter ***//
	public int getPrev(){
		return prev;
	}
	public int getNext(){
		return next;
	}
	public List<Integer> getPageNo() {
		return pageNo;
	}
	public int getLast() {
		return last;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public String getReturnHtml() {
		return returnHtml;
	}
	public void setReturnHtml(String returnHtml) {
		this.returnHtml = returnHtml;
	}
	public int getPageGroupSize() {
		return pageGroupSize;
	}
	public void setPageGroupSize(int pageGroupSize) {
		this.pageGroupSize = pageGroupSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getCurrentCroup() {
		return currentCroup;
	}
	public void setCurrentCroup(int currentCroup) {
		this.currentCroup = currentCroup;
	}
	public int getTotalGroup() {
		return totalGroup;
	}
	public void setTotalGroup(int totalGroup) {
		this.totalGroup = totalGroup;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public void setPageNo(List<Integer> pageNo) {
		this.pageNo = pageNo;
	}
	public void setPrev(int prev) {
		this.prev = prev;
	}
	public void setNext(int next) {
		this.next = next;
	}
	public void setLast(int last) {
		this.last = last;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getFeild() {
		return feild;
	}
	public void setFeild(String feild) {
		this.feild = feild;
	}
	public String getWhere() {
		return where;
	}
	public void setWhere(String where) {
		this.where = where;
	}

	public HashMap<String, Object> getParam() {
		return param;
	}

	public void setParam(HashMap<String, Object> param) {
		this.param = param;
	}
	
}

