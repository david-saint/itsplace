package net.itsplace.util;

import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class StringUtil{
	
	/*
	 *  Email id 가져오기
	 */
	public static String getEmailId(String email){
		
		if(email==""){
			return "";
		}
		
		StringTokenizer st1 = new StringTokenizer(email,"@");
		
		return st1.nextToken();
	}
	public static String addZero(String val, int size) {
    	String str = val;
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
