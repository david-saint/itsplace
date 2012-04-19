package net.itsplace.domain;

import java.util.ArrayList;
import java.util.Collection;

public class DataTable<T>{
        private String[] sortCols;
        private String sortDir;
        
        private Integer start;
        private Integer iDisplayLength;
        private Integer iTotalDisplayRecords;
        private Integer iTotalRecords;
        private Collection<T> rows;
        
        public DataTable() {
                this.rows = new ArrayList<T>();
                this.start = 0;         
                this.iDisplayLength = 10; // By default there will be 10 elements in each page.
                this.iTotalRecords = 0;
                this.iTotalDisplayRecords = 0;
                this.sortCols = new String[]{};
                this.sortDir = "";
        }
        
        public DataTable(String[] sortCols, String sortDir, Integer start) {
                this();
                this.sortCols = sortCols;
                this.sortDir = sortDir;
                this.start = start;
        }
        
        public DataTable(String[] sortCols, String sortDir, Integer start, Integer iDisplayLength) {
                this();
                this.sortCols = sortCols;
                this.sortDir = sortDir;
                this.start = start;
                this.iDisplayLength = iDisplayLength;
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

        public Integer getiTotalDisplayRecords() {
                return iTotalDisplayRecords;
        }

        public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
                this.iTotalDisplayRecords = iTotalDisplayRecords;
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

        public Integer getiTotalRecords() {
                return iTotalRecords != 0 ? this.iTotalRecords : this.iTotalDisplayRecords;
        }

        public void setiTotalRecords(int iTotalRecords) {
                this.iTotalRecords = iTotalRecords;
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