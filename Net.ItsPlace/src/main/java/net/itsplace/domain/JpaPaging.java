package net.itsplace.domain;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;


public class JpaPaging {
	private static final Logger logger = LoggerFactory.getLogger(JpaPaging.class);
	
	private String[] columns; 
	private Integer iDisplayStart;
	private Integer iDisplayLength; 
	private Integer iSortColumn;
	private String sSortDiretion;
	private String sSearch;
	private Integer currentPage;
	private Map<String, Object> parameter;
	
	public static String DESC = "desc";
	public static String ASC= "asc";
	public JpaPaging(){
		this.currentPage = 0;
	}
	/**
	 * <b>페이징 생성자 </b> <br />	
	 * @author 김동훈
	 * @version 2.0
	 * @since 2012. 8. 24
	 * @param colums 컬럼스트링
	 * @param iDisplayStart 페이지 
	 * @param iDisplayLength 페이지당 로우수
	 * @param iSortColumns sort column 인덱스
	 * @param sSortDiretion sort 방향(DESC, ASC
	 * @param sSearch 검색어
	 * @return JsonResponse
	 * @Exception Exception 
	 * @throws 
	 * @see ssss
	 */
	public JpaPaging(String[] columns,Integer iDisplayStart, Integer iDisplayLength,Integer iSortColumn, String sSortDiretion,String sSearch){
		 this.iSortColumn = iSortColumn;
         this.sSortDiretion = sSortDiretion;
         this.iDisplayStart = iDisplayStart;
         this.iDisplayLength = iDisplayLength;
         this.columns = columns;
         this.sSearch = sSearch;
	}
	public String[] getColumns() {
		return columns;
	}
	public void setColumns(String[] columns) {
		this.columns = columns;
	}
	public Integer getCurrentPage() {
			currentPage = (int) Math.ceil((double)iDisplayStart/iDisplayLength);
		return currentPage;
	}
	public Integer getiDisplayStart() {
		return iDisplayStart;
	}
	public void setiDisplayStart(Integer iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}
	public Integer getiDisplayLength() {
		return iDisplayLength;
	}
	public void setiDisplayLength(Integer iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}
	public Integer getiSortColumn() {
		return iSortColumn;
	}
	public void setiSortColums(Integer iSortColumn) {
		this.iSortColumn = iSortColumn;
	}
	public String getsSortDiretion() {
		return sSortDiretion;
	}
	public void setsSortDiretion(String sSortDiretion) {
		this.sSortDiretion = sSortDiretion;
	}
	public String getsSearch() {
		return sSearch;
	}
	public void setsSearch(String sSearch) {
		this.sSearch = sSearch;
	}
	/**
	 * DataTable용 JPA 페이징 객체 리턴
	 * @return Pageable
	 */
	public Pageable getPageable(){
		Sort sort = null;
		String sortColumn = columns[getiSortColumn()];
		
		if(iSortColumn >=0){
			if( sSortDiretion.equals("desc") ){
				sort = new Sort(Sort.Direction.DESC, sortColumn);
				
			}else{
				sort = new Sort(Sort.Direction.ASC, sortColumn);
			}
			logger.info("소팅 커럼:"+sortColumn+"소팅 커럼 Index:"+getiSortColumn());	
			return new PageRequest(getCurrentPage(),getiDisplayLength(), sort);
		}else{
			return new PageRequest(getCurrentPage(),getiDisplayLength());
		}
		
	}

	public Pageable getPageable(String sortColumn,Direction sortDirection,int currentPage, int displayLength){

		return new PageRequest(currentPage, displayLength ,  new Sort(sortDirection, sortColumn));
	}
	public Pageable getPageable(int currentPage, int displayLength){

		return new PageRequest(currentPage, displayLength);
	}
	
	public Map<String, Object> getParameter() {
		return parameter;
	}
	public void setParameter(Map<String, Object> parameter) {
		this.parameter = parameter;
		
		parameter.put("sortColumn", getOrderColumn(this.iSortColumn));
		parameter.put("search", this.sSearch);
		parameter.put("sortDirection", this.sSortDiretion);
	    parameter.put("startRow", this.sSortDiretion);
		parameter.put("sortDirection", this.sSortDiretion);
		parameter.put("startRow", this.iDisplayStart);
		parameter.put("endRow", (getCurrentPage()+1) * this.iDisplayLength );
			
	}
	public String getOrderColumn(int iSortCol_0) {
          if(iSortCol_0 >= 0 && iSortCol_0 < this.columns.length) {
                  
                return this.columns[iSortCol_0];
          }
          return "";
    }

	
	public String toString(){
		return "Paging[iDisplayStart="+iDisplayStart				
				+",iDisplayLength="+iDisplayLength
				+",iSortColumns="+iSortColumn
				+",sSortDiretion="+sSortDiretion
				+",sSearch="+sSearch
				+",currentpage="+getCurrentPage()
				+"]";
	}
	
}
