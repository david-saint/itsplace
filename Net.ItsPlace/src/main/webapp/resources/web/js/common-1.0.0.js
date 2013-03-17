/*
 * 공통 스크립트
 * ver1.0 김동훈 2011-08-21
 * param
 * return
 * 
 */


$(document).ready(function() {
});
var util = {
	empty : function(data) {
	    if(data=="" || data.length<=0 ){
	    	return true;
	    }else{
	    	return false;
	    }
	  
	},
	log : function(message) {
		if(window.console){
			console.log(message);
		}
	    
	},		
};	

