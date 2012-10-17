package com.mincoms.util;

import org.junit.Test;
import java.lang.reflect.Field;
import com.mincoms.book.domain.BookInfo;

//import com.mincoms.security.Authority;

public class TestEnum {
	
	
	@Test 
	public void test1(){
		/*System.out.println("ordinal():"+Authority.ADMIN.ordinal());
		System.out.println("ordinal():"+Authority.TEAMLEADER.ordinal());
		System.out.println("ordinal():"+Authority.WORKER.ordinal());
		System.out.println("ordinal():"+Authority.WORKER.name());*/
		
		
		Field[] fs = BookInfo.class.getDeclaredFields();
		for(Field f: fs){
			System.out.println(f.getName());
		}
	}
}
