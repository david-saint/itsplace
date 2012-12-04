package itsplace.library.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	/**
	 * <b>오늘날짜와 비교(년-월-일)로 비교함</b> <br />
	 * 오늘보다 크면 1 작으면 -1 같으면 0
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 9. 6
	 * @param Date 비교할 날짜
	 * @return int 
	 * @throws Exception 
	 * @see 
	 */
	public  static int DateCompareToday(Date d){
		int result = 0 ;
		Calendar today = java.util.Calendar.getInstance();
		Calendar testDate = java.util.Calendar.getInstance();
		testDate.setTime(d);
		
		today.set(Calendar.HOUR_OF_DAY, 0 );
		today.set(Calendar.MINUTE, 0 );
		today.set(Calendar.SECOND, 0 );
		today.set(Calendar.MILLISECOND, 0 );
		
		testDate.set(Calendar.HOUR_OF_DAY, 0 );
		testDate.set(Calendar.MINUTE, 0 );
		testDate.set(Calendar.SECOND, 0 );
		testDate.set(Calendar.MILLISECOND, 0 );
		
		if(today.after(testDate)){
			result = 1;
		}else if(today.before(testDate)){
			result = -1;			
		}else{
			result = 0;
		}
		
		return result;
	}
}
