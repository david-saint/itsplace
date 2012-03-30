/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["spring.UrlBuilder"]){
dojo._hasResource["spring.UrlBuilder"]=true;
dojo.provide("spring.UrlBuilder");
dojo.declare("spring.UrlBuilder",null,{template:null,params:null,constructor:function(_1,_2){
this.template=_1;
this.params=_2;
},append:function(_3,_4){
return new spring.UrlBuilder(this.template+_3,dojo.delegate(this.params,_4));
},build:function(_5){
return this._buildUrl(this.template,dojo.delegate(this.params,_5));
},toString:function(){
return this.build();
},_buildUrl:function(_6,_7){
var _8=_6,_9=null,_a={},re=null,_b=null;
if(_7){
for(_9 in _7){
re=new RegExp("\\{"+_9+"\\}");
_b=false;
if(re.test(_8)){
_b=true;
_8=_8.replace(re,encodeURIComponent(_7[_9]),"g");
}
if(!_b){
_a[_9]=_7[_9];
}
}
for(_9 in _a){
if(_a[_9]){
_8+=_8.indexOf("?")===-1?"?":"&";
_8+=encodeURIComponent(_9);
_8+="=";
_8+=encodeURIComponent(_a[_9]);
}
}
}
return _8;
}});
}
