package itsplace.library.util;

public class Strings {
	public static Boolean isEmpty(String str){		
		if(str == null || str.trim().length()==0 || str.trim() == "" ){
			return true;
		}else{
			return false;
		}
		
	}
}
