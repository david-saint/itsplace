package com.myplace.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Class DateCommon
 * @program 
 * @description  
 * @author 
 * @update 
 * @package 
 * @see 
*/
public class DateUtil
{	
	//현재 년을 기준으로 n 년전 목록까지(ex n=10 --> 2008~1998 까지) <select>에 들어갈 <option>태그생성
	//첫 인수로 공백,null를 넣으면 금년을 기준으로 목록을 만듬.
	public String getOptionYear(String year,int n) 
	{
		StringBuffer option = new StringBuffer();
		String sel = "";
		String lastyear = "";
		String curyear = "";
		Calendar cal = Calendar.getInstance();
		int thisYear	= cal.get( Calendar.YEAR );
		
		if(year==null){
			year = thisYear+"";
		}
		if(n >0)
		{
			//n 년 전까지 <OPTION>태그 생성
			for(int i=0;i<n;i++)
			{
				lastyear = (thisYear-(n-i))+"";
				if(year.equals(lastyear)) sel=" selected";
				option.append("<option value="+lastyear+sel+">"+lastyear+"</option>");
			}
			if(year.equals(curyear)) 
				sel=" selected";
			else	
				sel="";
			option.append("<option value="+curyear+sel+">"+curyear+"</option>");
		}
	return option.toString();
	}
	//입력된 년을 기준으로 1년전 목록 (파라메터로 NULL이나 공백이 들어가면  현재 년을 기준으로 목록 작성 
	public String getOptionYear(String year) 
	{
		StringBuffer option = new StringBuffer();
		String sel = "";
		String lastyear = "";
		String curyear = "";
		
		Calendar cal = Calendar.getInstance();
		int thisYear	= cal.get( Calendar.YEAR );
		
		if(year==null){
			year = thisYear+"";
		}
		
		//작년 년도
		lastyear = (thisYear-1)+"";
		if(year.equals(lastyear)) sel=" selected";
		option.append("<option value="+lastyear+sel+">"+lastyear+"</option>");
		
		//현재 년도
		sel = "";
		curyear = thisYear+"";
		if(year.equals(curyear)) sel=" selected";
		option.append("<option value="+curyear+sel+">"+curyear+"</option>");			
		
		return option.toString();
	}
	public String getOptionMonth(String month){
		StringBuffer option = new StringBuffer();
		
		Calendar cal = Calendar.getInstance();
		
		int thisMonth	= cal.get( Calendar.MONTH ) + 1;
		String num = "";
		
		if(month ==null){
			if(thisMonth < 10){
				month = "0"+ thisMonth;
			} else {
				month = String.valueOf(thisMonth);
			}
		}
		for(int i=1;i <= 12;i++){
			if(i < 10){
				num = "0" + i;
			} else {
				num = String.valueOf(i);
			}
			
			if(num.equals(month)){
				option.append("<option value='" + num +"' selected>");
				option.append(num + "</option>\n");
			} else {
				option.append("<option value='" + num +"'>");
				option.append(num + "</option>\n");
			}
		}
		return option.toString();	
	}

	public String getOptionDay(String day){
		StringBuffer option = new StringBuffer();
		
		Calendar cal = Calendar.getInstance();
		
		int thisDay	= cal.get( Calendar.DATE );
		String num = "";
		
		if(day ==null){
			if(thisDay < 10){
				day = "0"+ thisDay;
			} else {
				day = String.valueOf(thisDay);
			}
		}
		for(int i=1;i <= 31;i++){
			if(i < 10){
				num = "0" + i;
			} else {
				num = String.valueOf(i);
			}
			if(num.equals(day)){
				option.append("<option value='" + num +"' selected>");
				option.append(num + "</option>\n");
			} else {
				option.append("<option value='" + num +"'>");
				option.append(num + "</option>\n");
			}
		}
		return option.toString();	
	}
	//현재 년도를 반환 (형식 - 2006,2007,2008)
	public String getCurYear()		//현재 년도를 구함
	{
		int cur_year=0;
		try
		{
			Calendar cal = Calendar.getInstance();
			cur_year	= cal.get( Calendar.YEAR );
		}catch(Exception e){e.printStackTrace();}
		return String.valueOf(cur_year);
	}
	//현재 월을 반환(형식 - 1~12)
	public String getCurMonth()
	{
		int cur_month=0;
		try
		{
			Calendar cal = Calendar.getInstance();
			cur_month	= cal.get(Calendar.MONTH)+1;
		}catch(Exception e){e.printStackTrace();}
		return String.valueOf(cur_month);
	}
	//현재 월을 구함 n자리 수로 반환 (형식 n=2 이면 1월~9월 -> 01,02,03,04,05,06,07,08,09)로 표기
	public String getCurMonth(int n)
	{
		String curDate="";
		if(n == 2)
		{
			if(getCurMonth().length() < 2)
			{
				curDate += "0" + getCurMonth();
			} else {
				curDate += getCurMonth();
			}
		}else
			curDate += getCurMonth();
		return curDate;
	}
	//현재 날일을 반환(1~31)
	public String getCurDay()
	{
		int cur_days=0;
		try
		{
			Calendar cal = Calendar.getInstance();
			cur_days = cal.get(Calendar.DAY_OF_MONTH);
		}catch(Exception e){e.printStackTrace();}
		return String.valueOf(cur_days);
	}
	//현재 날일을 구함.n자리 수로 반환 (형식 n=2 1일~9일 -> 01,02,03,04,05,06,07,08,09)로 표기
	public String getCurDay(int n)
	{
		String curDate = "";
		if(n == 2)
		{
			if(getCurDay().length() < 2){
				curDate += "0" + getCurDay();
			} else {
				curDate += getCurDay();
			}
		}else
			curDate+= getCurDay();
		return curDate;
	}
	//현재 년월일 을 붙여서 반환 (형식-8자리 20070309 , 20081112, ....)
	public String getCurDate(){
		
		String curDate = getCurYear();
		
		if(getCurMonth().length() < 2){
			curDate += "0" + getCurMonth();
		} else {
			curDate += getCurMonth();
		}
		
		if(getCurDay().length() < 2){
			curDate += "0" + getCurDay();
		} else {
			curDate += getCurDay();
		}
		
		return curDate;
	}
	//현재 년,월,일을 한번에 출력 (형식- 현재 2007년 12월 5일 )
	public String getDate()
	{
		int cur_year=0;
		int cur_month=0;
		int cur_days=0;
		String cur_date=null;
		try
		{
			Calendar cal=Calendar.getInstance();
			cur_year=cal.get(Calendar.YEAR);
			cur_month=cal.get(Calendar.MONTH);
			cur_days=cal.get(Calendar.DAY_OF_MONTH);
			cur_date="현재 "+cur_year+"년 "+(cur_month+1)+"월 "+cur_days+"일";
		}catch(Exception e){e.printStackTrace();}
		return String.valueOf(cur_date);
	}
	//현재 년,월을  붙임  	ex)200704,200611 ....
	public String getCurYearMonth()
	{
		String curDate = getCurYear();
		
		if(getCurMonth().length() < 2){
			curDate += "0" + getCurMonth();
		} else {
			curDate += getCurMonth();
		}
		return curDate;
	}
	public static String getDateTimeByPattern(String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(
                pattern, Locale.KOREA);
        String dateString = formatter.format(new Date());
        
        return dateString;
    }
    
    
    
    public static String getSysYear(){
    	int cur_year=0;
		try
		{
			Calendar cal = Calendar.getInstance();
			cur_year	= cal.get( Calendar.YEAR );
		}catch(Exception e){e.printStackTrace();}
		return String.valueOf(cur_year);
    }
    public static String getSysDate()
    {
     int year, month, day;
     GregorianCalendar cal = new GregorianCalendar(); 

      year = cal.get(Calendar.YEAR);  
      month = cal.get(Calendar.MONTH)+1;
      day = cal.get(Calendar.DAY_OF_MONTH);
     

      String date = String.valueOf(year)+addZero(month,2)+addZero(day,2);


    return date;
    }
    public static String getSysMonth()
    {
    	int  month;
    	GregorianCalendar cal = new GregorianCalendar(); 
    	
    	  
    	month = cal.get(Calendar.MONTH)+1;
    	
    	return addZero(month,2);
    }
    public static String getSysTime()
    {
     int hour,minute ,second;
     GregorianCalendar cal = new GregorianCalendar(); 

    
      hour = cal.get(Calendar.HOUR_OF_DAY);
      minute = cal.get(Calendar.MINUTE);
      second = cal.get(Calendar.SECOND);

      String time = addZero(hour,2)+addZero(minute,2)+addZero(second,2);
     

    return time;
    }
    
    public static String addZero(int val, int size) {
        String str = String.valueOf(val);       // int 형 값을 문자열로 변환합니다.
        int istrSize = 0;                       // 변환된 문자열의 길이를 담을 변수 입니다.
        StringBuffer sb = new StringBuffer();   // 앞에 붙일 문자열 0 들을 넣을 StringBuffer 입니다.
        istrSize = str.getBytes().length;       // 문자열의 길이를 구합니다.
        if(size < istrSize) {                   // 맟출려는 자리수보다 문자열의 길이가 더 크면 에러를 출력합니다.
          //  System.out.println("맞추고자 하는 길이보다 변환한 문자열의 길이가 더 커요~");
            return "Error";
        }
        // size는 내가 맞추고자할 길이이고 istrSize는 현재 문자열의 길이 이므로
        // 맞추고자할 길이에서 문자열 길이를 뺀 만큼 앞에다 붙일 문자열 "0" 을 만듭니다.
        for(int i=0; i<(size-istrSize); i++) {
            sb.append("0");         // "0" 을 하나 붙입니다.
        }
        str = sb.toString() + str;  // 만들어진 문자열 "0..."과 int형을 변환한 문자열을 합쳐서 리턴합니다.
        return str;
    }
    

}
