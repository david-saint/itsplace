/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.charting.scaler.linear"]){
dojo._hasResource["insight.charting.scaler.linear"]=true;
dojo.provide("insight.charting.scaler.linear");
dojo.require("dojox.charting.scaler.common");
(function(){
var _1=3,_2=dojox.charting.scaler.common,_3=_2.findString,_4=_2.getNumericLabel;
var _5=[{coefficient:1.5,major:0.5,minor:0.1},{coefficient:2,major:0.5,minor:0.1},{coefficient:2.5,major:0.5,minor:0.1},{coefficient:3,major:1,minor:0.5},{coefficient:4,major:1,minor:0.5},{coefficient:5,major:1,minor:0.5},{coefficient:6,major:1.5,minor:0.5},{coefficient:7.5,major:2.5,minor:0.5},{coefficient:8,major:2,minor:0.5},{coefficient:9,major:3,minor:0.5},{coefficient:10,major:2.5,minor:0.5}];
var _6=function(_7){
_7*=1.05;
var _8,_9=0,_a;
do{
_8=Math.pow(10,_9);
_9++;
}while(_7>10*_8);
for(var i in _5){
_a=_5[i];
if(_7<_a.coefficient*_8){
return {max:_8*_a.coefficient,major:_8*_a.major,minor:_8*_a.minor};
}
}
};
var _b=function(_c,_d,_e,_f,_10,_11,_12){
_e=dojo.delegate(_e);
if(!_f){
if(_e.fixUpper=="major"){
_e.fixUpper="minor";
}
if(_e.fixLower=="major"){
_e.fixLower="minor";
}
}
if(!_10){
if(_e.fixUpper=="minor"){
_e.fixUpper="micro";
}
if(_e.fixLower=="minor"){
_e.fixLower="micro";
}
}
if(!_11){
if(_e.fixUpper=="micro"){
_e.fixUpper="none";
}
if(_e.fixLower=="micro"){
_e.fixLower="none";
}
}
var _13=_3(_e.fixLower,["major"])?Math.floor(_e.min/_f)*_f:_3(_e.fixLower,["minor"])?Math.floor(_e.min/_10)*_10:_3(_e.fixLower,["micro"])?Math.floor(_e.min/_11)*_11:_e.min,_14=_3(_e.fixUpper,["major"])?Math.ceil(_e.max/_f)*_f:_3(_e.fixUpper,["minor"])?Math.ceil(_e.max/_10)*_10:_3(_e.fixUpper,["micro"])?Math.ceil(_e.max/_11)*_11:_e.max;
if(_e.useMin){
_c=_13;
}
if(_e.useMax){
_d=_14;
}
var _15=(!_f||_e.useMin&&_3(_e.fixLower,["major"]))?_c:Math.ceil(_c/_f)*_f,_16=(!_10||_e.useMin&&_3(_e.fixLower,["major","minor"]))?_c:Math.ceil(_c/_10)*_10,_17=(!_11||_e.useMin&&_3(_e.fixLower,["major","minor","micro"]))?_c:Math.ceil(_c/_11)*_11,_18=!_f?0:(_e.useMax&&_3(_e.fixUpper,["major"])?Math.round((_d-_15)/_f):Math.floor((_d-_15)/_f))+1,_19=!_10?0:(_e.useMax&&_3(_e.fixUpper,["major","minor"])?Math.round((_d-_16)/_10):Math.floor((_d-_16)/_10))+1,_1a=!_11?0:(_e.useMax&&_3(_e.fixUpper,["major","minor","micro"])?Math.round((_d-_17)/_11):Math.floor((_d-_17)/_11))+1,_1b=_10?Math.round(_f/_10):0,_1c=_11?Math.round(_10/_11):0,_1d=_f?Math.floor(Math.log(_f)/Math.LN10):0,_1e=_10?Math.floor(Math.log(_10)/Math.LN10):0,_1f=_12/(_d-_c);
if(!isFinite(_1f)){
_1f=1;
}
return {bounds:{lower:_13,upper:_14,from:_c,to:_d,scale:_1f,span:_12},major:{tick:_f,start:_15,count:_18,prec:_1d},minor:{tick:_10,start:_16,count:_19,prec:_1e},micro:{tick:_11,start:_17,count:_1a,prec:0},minorPerMajor:_1b,microPerMinor:_1c,scaler:insight.charting.scaler.linear};
};
dojo.mixin(insight.charting.scaler.linear,{buildScaler:function(min,max,_20,_21){
var h={fixUpper:"none",fixLower:"none",natural:false};
if(_21){
if("fixUpper" in _21){
h.fixUpper=String(_21.fixUpper);
}
if("fixLower" in _21){
h.fixLower=String(_21.fixLower);
}
if("natural" in _21){
h.natural=Boolean(_21.natural);
}
}
if("min" in _21){
min=_21.min;
}
if("max" in _21){
max=_21.max;
}
if(_21.includeZero){
if(min>0){
min=0;
}
if(max<0){
max=0;
}
}
h.min=min;
h.useMin=true;
h.max=max;
h.useMax=true;
if("from" in _21){
min=_21.from;
h.useMin=false;
}
if("to" in _21){
max=_21.to;
h.useMax=false;
}
if(max<=min){
return _b(min,max,h,0,0,0,_20);
}
var _22=_6(max),_23=_21&&("majorTickStep" in _21)?_21.majorTickStep:_22.major,_24=_21&&("minorTickStep" in _21)?_21.minorTickStep:_22.minor,_25=0,_26;
h.max=max=_22.max;
if(_21&&("microTickStep" in _21)){
_25=_21.microTickStep;
_26=_b(min,max,h,_23,_24,_25,_20);
}else{
do{
_25=_24/10;
if(!h.natural||_25>0.9){
_26=_b(min,max,h,_23,_24,_25,_20);
if(_26.bounds.scale*_26.micro.tick>_1){
break;
}
}
_25=_24/5;
if(!h.natural||_25>0.9){
_26=_b(min,max,h,_23,_24,_25,_20);
if(_26.bounds.scale*_26.micro.tick>_1){
break;
}
}
_25=_24/2;
if(!h.natural||_25>0.9){
_26=_b(min,max,h,_23,_24,_25,_20);
if(_26.bounds.scale*_26.micro.tick>_1){
break;
}
}
_25=0;
}while(false);
}
return _25?_26:_b(min,max,h,_23,_24,0,_20);
},buildTicks:function(_27,_28){
var _29,_2a,_2b,_2c=_27.major.start,_2d=_27.minor.start,_2e=_27.micro.start;
if(_28.microTicks&&_27.micro.tick){
_29=_27.micro.tick,_2a=_2e;
}else{
if(_28.minorTicks&&_27.minor.tick){
_29=_27.minor.tick,_2a=_2d;
}else{
if(_27.major.tick){
_29=_27.major.tick,_2a=_2c;
}else{
return null;
}
}
}
var _2f=1/_27.bounds.scale;
if(_27.bounds.to<=_27.bounds.from||isNaN(_2f)||!isFinite(_2f)||_29<=0||isNaN(_29)||!isFinite(_29)){
return null;
}
var _30=[],_31=[],_32=[];
while(_2a<=_27.bounds.to+_2f){
if(Math.abs(_2c-_2a)<_29/2){
_2b={value:_2c};
if(_28.majorLabels){
_2b.label=_4(_2c,_27.major.prec,_28);
}
_30.push(_2b);
_2c+=_27.major.tick;
_2d+=_27.minor.tick;
_2e+=_27.micro.tick;
}else{
if(Math.abs(_2d-_2a)<_29/2){
if(_28.minorTicks){
_2b={value:_2d};
if(_28.minorLabels&&(_27.minMinorStep<=_27.minor.tick*_27.bounds.scale)){
_2b.label=_4(_2d,_27.minor.prec,_28);
}
_31.push(_2b);
}
_2d+=_27.minor.tick;
_2e+=_27.micro.tick;
}else{
if(_28.microTicks){
_32.push({value:_2e});
}
_2e+=_27.micro.tick;
}
}
_2a+=_29;
}
return {major:_30,minor:_31,micro:_32};
},getTransformerFromModel:function(_33){
var _34=_33.bounds.from,_35=_33.bounds.scale;
return function(x){
return (x-_34)*_35;
};
},getTransformerFromPlot:function(_36){
var _37=_36.bounds.from,_38=_36.bounds.scale;
return function(x){
return x/_38+_37;
};
}});
})();
}
