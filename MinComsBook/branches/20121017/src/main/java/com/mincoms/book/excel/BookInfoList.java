package com.mincoms.book.excel;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.mincoms.book.domain.BookInfo;


public class BookInfoList extends AbstractExcelView{
	 @SuppressWarnings("unchecked")
	 @Override
	 protected void buildExcelDocument(Map<String, Object> model,
		   HSSFWorkbook workbook, HttpServletRequest req, HttpServletResponse res)
		   throws Exception {
		  // TODO Auto-generated method stub
		  System.out.println("---- ExcelDownloadView.buildExcelDocument() ----");
		  
		  String userAgent = req.getHeader("User-Agent");
		  String fileName = "test.xls";
		  
		  if(userAgent.indexOf("MSIE") > -1){
		   fileName = URLEncoder.encode(fileName, "utf-8");
		  }else{
		   fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
		  }
		  
		  res.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		  res.setHeader("Content-Transfer-Encoding", "binary");
		    
		  HSSFSheet sheet = createFirstSheet(workbook);
		  createColumnLabel(sheet);
		  
		  List<BookInfo> bookInfos = (List<BookInfo>) model.get("bookInfos");
		  for(int i=0; i <= bookInfos.size()-1; i++){
			  createPageRow(sheet, bookInfos, i);
		  }
	 }
	  
	 private HSSFSheet createFirstSheet(HSSFWorkbook workbook){
		  HSSFSheet sheet = workbook.createSheet();
		  workbook.setSheetName(0, "테스트");
		  sheet.setColumnWidth(1, 256*30);
		  return sheet;
	 }
	 
	 private void createColumnLabel(HSSFSheet sheet){
		  HSSFRow firstRow = sheet.createRow(0);
		  
		  HSSFCell cell = firstRow.createCell(0);
		  cell.setCellValue("isbn");
		  
		  cell = firstRow.createCell(1);
		  cell.setCellValue("제목");
	 }
	 
	 private void createPageRow(HSSFSheet sheet, List<BookInfo> bookInfos, int rowNum){
		  HSSFRow row = sheet.createRow(rowNum + 1);
		  
		  HSSFCell cell = row.createCell(0);
		  cell.setCellValue(bookInfos.get(rowNum).getIsbn());
		  
		  cell = row.createCell(1);
		  cell.setCellValue(bookInfos.get(rowNum).getTitle()); 
	 }
}