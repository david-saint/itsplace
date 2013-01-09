package net.itsplace.domain;

import java.util.ArrayList;
import java.util.Collection;

public class DataTable<T>{
        private String[] sortCols;
        private String sortDir;
        
        private Integer start;
        private Integer iDisplayLength;
        private long iTotalDisplayRecords;
        private long iTotalRecords;
        private Collection<T> rows;
        
        public DataTable() {
                this.rows = new ArrayList<T>();
                this.start = 0;         
                this.iDisplayLength = 10; 
                this.iTotalRecords = 0;
                this.iTotalDisplayRecords = 0;
                this.sortCols = new String[]{};
                this.sortDir = "";
        }
        /**
    	 * <b>페이징</b> <br />
    	 * <pre>
    	 * <b>History:</b>
    	 *     version 0.1, 2012.8.24 김동훈
    	 * </pre>
    	 * @author 김동훈
    	 * @version 1.0
    	 * @since 2012. 8. 24
    	 * @deprecated 
    	 * @return book/add.jsp 
    	 * @throws AppException 
    	 * @see 
    	 */
        public DataTable(String[] sortCols, String sortDir, Integer start) {
                this();
                this.sortCols = sortCols;
                this.sortDir = sortDir;
                this.start = start;
        }
        /**
    	 * <b>페이징</b> <br />
    	 * <pre>
    	 * <b>History:</b>
    	 *     version 0.1, 2012.8.24 김동훈
    	 * </pre>
    	 * @author 김동훈
    	 * @version 1.0
    	 * @since 2012. 8. 24
    	 * @deprecated 
    	 * @return book/add.jsp 
    	 * @throws AppException 
    	 * @see 
    	 */
        public DataTable(String[] sortCols, String sortDir, Integer start, Integer iDisplayLength) {
                this();
                this.sortCols = sortCols;
                this.sortDir = sortDir;
                this.start = start;
                this.iDisplayLength = iDisplayLength;
        }
        public DataTable(JpaPaging paging){
        	this();
            if(paging.getiDisplayLength() != null && paging.getiDisplayLength()>0){
            	this.sortCols = paging.getColumns();
                this.sortDir = paging.getsSortDiretion();
                this.start = paging.getiDisplayStart();
                this.iDisplayLength = paging.getiDisplayLength();
            }else{
            	this.sortCols = paging.getColumns();
                this.sortDir = paging.getsSortDiretion();
                this.start = paging.getiDisplayStart();
            }
                
        }
        /*public DataTable2(String[] sortCols, String sortDir, Integer start, Long iTotalRecords, Integer iTotalDisplayRecords, Collection<T> rows) {
                this(sortCols, sortDir, start, iTotalDisplayRecords);
                this.iTotalRecords = iTotalRecords;
                this.rows = rows;
        }*/

        public Integer getStart() {
                return start;
        }

        public void setStart(Integer start) {
                this.start = start;
        }

     

        public Collection<T> getRows() {
                return rows;
        }

        public void setRows(Collection<T> rows) {
                this.rows = rows;
        }

        public String[] getSortCols() {
                return sortCols;
        }

        public void setSortCols(String[] sortCols) {
                this.sortCols = sortCols;
        }

        public String getSortDir() {
                return sortDir;
        }

        public void setSortDir(String sortDir) {
                this.sortDir = sortDir;
        }
        
        public Integer getiDisplayLength() {
                return iDisplayLength;
        }

        public void setiDisplayLength(Integer iDisplayLength) {
                this.iDisplayLength = iDisplayLength;
        }

        public Long getiTotalRecords() {
                return iTotalRecords != 0 ? this.iTotalRecords : this.iTotalDisplayRecords;
        }
        public void setiTotalRecords(int iTotalRecords) {
            this.iTotalRecords = iTotalRecords;
        }
        public Long getiTotalDisplayRecords() {
                return iTotalDisplayRecords;
        }
        public void setiTotalDisplayRecords(long iTotalDisplayRecords) {
                this.iTotalDisplayRecords = iTotalDisplayRecords;
        }
     
        // FOR SERVICES
        public String getOrdering(int iSortCol_0) {
                if(iSortCol_0 >= 0 && iSortCol_0 < this.sortCols.length) {
                        if(this.sortDir != null && !("").equals(this.sortDir) && ("desc").equals(this.sortDir)) return "-"+this.sortCols[iSortCol_0];
                        else return this.sortCols[iSortCol_0];
                }
                return "";
        }
        public String getOrderColumn(int iSortCol_0) {
            if(iSortCol_0 >= 0 && iSortCol_0 < this.sortCols.length) {
                    
                  return this.sortCols[iSortCol_0];
            }
            return "";
        }
        public Integer getEnd() {
                return this.getStart() + this.getiDisplayLength();
        }
        
}