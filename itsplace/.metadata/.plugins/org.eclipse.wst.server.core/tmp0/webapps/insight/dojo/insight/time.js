/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.time"]){
dojo._hasResource["insight.time"]=true;
dojo.provide("insight.time");
dojo.require("dojo.date");
dojo.require("dojo.date.locale");
dojo.require("dojo.string");
insight.time.toDate=function(_1){
return dojo.date.locale.parse(_1,{datePattern:"yyyy-MM-dd'T'HH:mm:ssZ",selector:"date"});
};
insight.time.toString=function(_2){
return dojo.date.locale.format(_2,{datePattern:"yyyy-MM-dd'T'HH:mm:ssZ",selector:"date"});
};
insight.time.normalizeToDate=function(_3){
if(_3 instanceof Date){
return _3;
}else{
if(_3=="now"){
return new Date();
}else{
if(typeof _3=="string"||_3 instanceof String){
return this.toDate(_3);
}
}
}
return null;
};
insight.time.normalizeToString=function(_4){
if(typeof _4=="string"||_4 instanceof String){
return _4;
}else{
if(_4 instanceof Date){
return this.toString(_4);
}else{
return null;
}
}
};
insight.time.difference=function(_5,_6){
return dojo.date.difference(_5,_6,"millisecond");
};
insight.time.add=function(_7,_8){
return dojo.date.add(_7,"millisecond",_8);
};
insight.time.compare=function(_9,_a){
_9=this.normalizeToDate(_9);
_a=this.normalizeToDate(_a);
return dojo.date.compare(_9,_a);
};
insight.time.labels=function(_b,_c,_d,_e){
_b=this.normalizeToDate(_b);
_c=this.normalizeToDate(_c);
var _f={};
if(_d){
_f.start=insight.time.relativeLabel(dojo.date.difference(_c,_b,"minute"));
_f.end=insight.time.relativeLabel(0);
}else{
var _10=(_e||dojo.date.difference(_b,_c,"millisecond"))/(60*1000),_11=dojo.date.locale._getGregorianBundle(dojo.i18n.normalizeLocale()),_12,_13,_14;
if(_10<6*60){
_12="";
_13=_11["timeFormat-medium"];
_14="time";
}else{
if(_10<12*60){
_12=_11["dateFormat-short"];
_13=_11["timeFormat-medium"];
}else{
_12=_11["dateFormat-short"];
_13=_11["timeFormat-short"];
}
}
_f.start=dojo.date.locale.format(_b,{datePattern:_12,timePattern:_13,selector:_14,fullYear:true});
_f.end=dojo.date.locale.format(_c,{datePattern:_12,timePattern:_13,selector:_14,fullYear:true});
}
return _f;
};
insight.time.relativeLabel=function(_15){
var _16=_15<0;
_15=Math.abs(_15);
if(_15==0){
return "Live";
}
var _17=Math.floor(_15/60);
var _18=_15%60;
var _19="";
if(_17>0){
_19=_17+" hour";
if(_17!=1){
_19+="s";
}
_19+=" ";
}
if(_18>0){
_19+=_18+" minute";
if(_18!=1){
_19+="s";
}
}
_19+=_16?" ago":" from now";
return _19;
};
insight.time.shortLabel=function(_1a,_1b){
var _1c="",_1d,_1e,_1f,_20;
if(_1a===0){
return "now";
}
_1d=Math.floor(_1a/insight.time.millis.week);
_1a-=_1d*insight.time.millis.week;
if(_1d>0){
_1c+=_1d+(_1b?"w ":(_1d>1?" weeks ":" week "));
}
_1e=Math.floor(_1a/insight.time.millis.day);
_1a-=_1e*insight.time.millis.day;
if(_1e>0){
_1c+=_1e+(_1b?"d ":(_1e>1?" days ":" day "));
}
_1f=Math.floor(_1a/insight.time.millis.hour);
_1a-=_1f*insight.time.millis.hour;
if(_1f>0){
_1c+=_1f+(_1b?"h ":(_1f>1?" hours ":" hour "));
}
_20=Math.floor(_1a/insight.time.millis.minute);
_1a-=_20*insight.time.millis.minute;
if(_20>0){
_1c+=_20+(_1b?"m ":(_20>1?" minutes ":" minute "));
}
return dojo.string.trim(_1c);
};
insight.time.millis={week:604800000,day:86400000,hour:3600000,minute:60000,second:1000};
dojo.date.locale.parse=function(_21,_22){
var _23=dojo.date.locale._parseInfo(_22),_24=_23.tokens,_25=_23.bundle,re=new RegExp("^"+_23.regexp+"$",_23.strict?"":"i"),_26=re.exec(_21);
if(!_26){
return null;
}
var _27=["abbr","wide","narrow"],_28=[1970,0,1,0,0,0,0],_29="",_2a,_2b=dojo.every(_26,function(v,i){
if(!i){
return true;
}
var _2c=_24[i-1];
var l=_2c.length;
switch(_2c.charAt(0)){
case "y":
if(l!=2&&_22.strict){
_28[0]=v;
}else{
if(v<100){
v=Number(v);
var _2d=""+new Date().getFullYear(),_2e=_2d.substring(0,2)*100,_2f=Math.min(Number(_2d.substring(2,4))+20,99),num=(v<_2f)?_2e+v:_2e-100+v;
_28[0]=num;
}else{
if(_22.strict){
return false;
}
_28[0]=v;
}
}
break;
case "M":
if(l>2){
var _30=_25["months-format-"+_27[l-3]].concat();
if(!_22.strict){
v=v.replace(".","").toLowerCase();
_30=dojo.map(_30,function(s){
return s.replace(".","").toLowerCase();
});
}
v=dojo.indexOf(_30,v);
if(v==-1){
return false;
}
}else{
v--;
}
_28[1]=v;
break;
case "E":
case "e":
var _31=_25["days-format-"+_27[l-3]].concat();
if(!_22.strict){
v=v.toLowerCase();
_31=dojo.map(_31,function(d){
return d.toLowerCase();
});
}
v=dojo.indexOf(_31,v);
if(v==-1){
return false;
}
break;
case "D":
_28[1]=0;
case "d":
_28[2]=v;
break;
case "a":
var am=_22.am||_25["dayPeriods-format-wide-am"],pm=_22.pm||_25["dayPeriods-format-wide-pm"];
if(!_22.strict){
var _32=/\./g;
v=v.replace(_32,"").toLowerCase();
am=am.replace(_32,"").toLowerCase();
pm=pm.replace(_32,"").toLowerCase();
}
if(_22.strict&&v!=am&&v!=pm){
return false;
}
_29=(v==pm)?"p":(v==am)?"a":"";
break;
case "K":
if(v==24){
v=0;
}
case "h":
case "H":
case "k":
if(v>23){
return false;
}
_28[3]=v;
break;
case "m":
_28[4]=v;
break;
case "s":
_28[5]=v;
break;
case "S":
_28[6]=v;
break;
case "Z":
if(v==="Z"){
break;
}
if(v.indexOf("GMT")===0){
v=v.substring(3);
}
_2a=(v.charAt(0)==="-")?-1:1;
_2a*=parseInt(v.substring(1,3),10)*60;
v=(v.charAt(3)===":")?v.substring(4):v.substring(3);
if(v.length===2){
if(_2a<0){
_2a-=parseInt(v,10);
}else{
_2a+=parseInt(v,10);
}
}
break;
}
return true;
});
var _33=+_28[3];
if(_29==="p"&&_33<12){
_28[3]=_33+12;
}else{
if(_29==="a"&&_33==12){
_28[3]=0;
}
}
var _34=new Date(_28[0],_28[1],_28[2],_28[3],_28[4],_28[5],_28[6]);
if(_22.strict){
_34.setFullYear(_28[0]);
}
var _35=_24.join(""),_36=_35.indexOf("d")!=-1,_37=_35.indexOf("M")!=-1;
if(!_2b||(_37&&_34.getMonth()>_28[1])||(_36&&_34.getDate()>_28[2])){
return null;
}
if((_37&&_34.getMonth()<_28[1])||(_36&&_34.getDate()<_28[2])){
_34=dojo.date.add(_34,"hour",1);
}
if(_2a){
_34=dojo.date.add(_34,"minute",_2a+new Date().getTimezoneOffset());
}
return _34;
};
}
