package net.itsplace.web;

import static org.junit.Assert.*;

import org.junit.Test;

public class Test1 {

	@Test
	public void test() {
		int abc = 0;
		if(isNull(abc)){
			System.out.println("s");
		}else{
			System.out.println("false");
		}
		
	

	}
	 public  boolean isNull(Object obj){
		 boolean result = false;
	 
		 if(obj.getClass().getName() == "java.lang.Integer"){
			 if(obj == null){
				 result = true;
			 }else if((Integer)obj == 0){
				 result = true;
			 }
		 }
		 if(obj.getClass().getName() == "java.lang.String"){
			 if(obj == null){
				 result = true;
			 }else if((String)obj == ""){
				 result = true;
			 }
		 }
		 return result;
	 }

}
