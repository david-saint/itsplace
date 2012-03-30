/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.time.TimeRange"]){
dojo._hasResource["insight.time.TimeRange"]=true;
dojo.provide("insight.time.TimeRange");
dojo.require("dojo.date");
dojo.require("insight.time");
dojo.declare("insight.time.TimeRange",null,{start:null,end:null,anchored:true,constructor:function(_1){
var _2,_3,_4,_5,_6;
if(dojo.isString(_1)){
if(_1.indexOf("@")===-1){
_3=new Date();
_4=parseInt(_1);
_5=false;
}else{
_3=insight.time.toDate(_1.split("@")[1]);
_4=parseInt(_1.split("@")[0]);
_5=true;
}
}else{
_2=insight.time.normalizeToDate(_1.start);
_3=insight.time.normalizeToDate(_1.end);
_4=_1.duration;
_5=_1.anchored;
}
if(_2&&_3){
this.start=_2;
this.end=_3;
_4=insight.time.difference(_2,_3);
}else{
if(_2&&_4){
this.start=_2;
this.end=insight.time.add(_2,_4);
}else{
if(_3&&_4){
this.end=_3;
this.start=insight.time.add(_3,_4*-1);
}else{
if(_4){
this.end=new Date();
this.start=insight.time.add(this.end,_4*-1);
}
}
}
}
if(_5!==undefined){
this.anchored=_5;
}
},clone:function(_7){
return new insight.time.TimeRange({start:this.start,end:this.end,anchored:this.anchored});
},modulate:function(_8){
var _9=this.getDuration()/_8,_a=this.end.getTime(),_b=_a%_9;
if(_b!=0){
this.end=insight.time.add(this.end,_9-_b);
this.start=insight.time.add(this.start,_9-_b);
}
return this;
},contains:function(_c){
_c=insight.time.normalizeToDate(_c);
return dojo.date.compare(_c,this.start)>=0&&dojo.date.compare(_c,this.end)<0;
},add:function(_d){
this.start=insight.time.add(this.start,_d);
this.end=insight.time.add(this.end,_d);
this.anchored=true;
return this;
},roll:function(_e,_f){
if(!this.anchored||_f){
_e=_e||{};
var _10=_e.date||new Date(),_11=_e.windows||60,_12=_e.bidirectional||false,_13=_e.duration||this.getDuration(),_14=_13/_11,_15=insight.time.difference(this.end,_10);
if(_12||_15>0){
_14=Math.ceil(_15/_14)*_14;
this.end=insight.time.add(this.end,_14);
this.start=insight.time.add(this.start,_14);
}
this.anchored=false;
}
return this;
},shift:function(end){
end=end||new Date();
var _16=insight.time.difference(this.end,end);
this.end=insight.time.add(this.end,_16);
this.start=insight.time.add(this.start,_16);
return this;
},changeDuration:function(_17){
if(!this.anchored){
this.start=insight.time.add(this.end,_17*-1);
}else{
var _18=(this.getDuration()-_17)/2;
this.start=insight.time.add(this.start,_18);
this.end=insight.time.add(this.end,_18*-1);
}
return this;
},getStart:function(){
return insight.time.toString(this.start);
},getStartDate:function(){
return this.start;
},getEnd:function(){
return insight.time.toString(this.end);
},getEndDate:function(){
return this.end;
},getDuration:function(){
return insight.time.difference(this.start,this.end);
},isAnchored:function(){
return this.anchored;
},setAnchored:function(_19){
this.anchored=_19;
return this;
},toString:function(){
if(this.anchored){
return this.getDuration()+"@"+this.getEnd();
}else{
return this.getDuration().toString();
}
}});
}
