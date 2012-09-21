package com.mincoms.date;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class TestDate {

	@Test
	public void test() {
		Calendar calendar = java.util.Calendar.getInstance();
		Calendar calendar2 = java.util.Calendar.getInstance();
		Calendar calendar3 = java.util.Calendar.getInstance();
		Date d = calendar.getTime();
		System.out.println(d.toString());
		calendar.add(calendar.DATE, 7);
		
		System.out.println(calendar.getTime().toString());

		Date today2 = new Date();
		long diff = ( calendar.getTime().getTime()-today2.getTime()) /1000/60/60/24;
		
		Date today = new Date();		
		
		if(calendar.before(calendar2)){
			System.out.println("이후");
		}else{
			System.out.println("이전");
		}
		
		
		Date endDate = new Date("2012-09-21 10:47:25");
		calendar3.setTime(endDate);
		System.out.println(calendar2.compareTo(calendar3));
		
		String temp ="ddddd\"";
		temp = temp.replaceAll("[\"]","aaa");
		
	}

}
