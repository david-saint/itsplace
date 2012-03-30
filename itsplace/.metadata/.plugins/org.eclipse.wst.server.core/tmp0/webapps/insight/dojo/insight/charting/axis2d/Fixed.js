/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.charting.axis2d.Fixed"]){
dojo._hasResource["insight.charting.axis2d.Fixed"]=true;
dojo.provide("insight.charting.axis2d.Fixed");
dojo.require("dojox.charting.axis2d.Invisible");
dojo.require("dojox.charting.scaler.linear");
dojo.require("dojox.charting.axis2d.common");
dojo.require("dojo.colors");
dojo.require("dojo.string");
dojo.require("dojox.gfx");
dojo.require("dojox.lang.functional");
dojo.require("dojox.lang.utils");
(function(){
var dc=dojox.charting,du=dojox.lang.utils,g=dojox.gfx,_1=dc.scaler.linear,_2=4,_3=45;
dojo.declare("insight.charting.axis2d.Fixed",dojox.charting.axis2d.Invisible,{defaultParams:{vertical:false,natural:false,leftBottom:true,includeZero:false,fixed:true,rotation:0,labels:[],htmlLabels:true},optionalParams:{min:0,max:1,from:0,to:1,labelFunc:null,maxLabelSize:0,stroke:{},tick:{},font:"",fontColor:""},constructor:function(_4,_5){
this.opt=dojo.delegate(this.defaultParams,_5);
du.updateWithPattern(this.opt,_5,this.optionalParams);
},getOffsets:function(){
var s=this.scaler,_6={l:0,r:0,t:0,b:0};
if(!s){
return _6;
}
var o=this.opt,_7=0,a,b,c,d,gl=dc.scaler.common.getNumericLabel,_8=0,ma=s.major,mi=s.minor,ta=this.chart.theme.axis,_9=o.font||(ta.tick&&ta.tick.font),_a=this.chart.theme.getTick("major",o),_b=_9?g.normalizedLength(g.splitFontString(_9).size):0,_c=o.rotation%360,_d=o.leftBottom,_e=Math.abs(Math.cos(_c*Math.PI/180)),_f=Math.abs(Math.sin(_c*Math.PI/180));
if(_c<0){
_c+=360;
}
if(_b){
if(o.maxLabelSize){
_7=o.maxLabelSize;
}else{
_7=this._groupLabelWidth(this.labels,_9);
}
if(this.vertical){
var _10=_d?"l":"r";
switch(_c){
case 0:
case 180:
_6[_10]=_7;
_6.t=_6.b=_b/2;
break;
case 90:
case 270:
_6[_10]=_b;
_6.t=_6.b=_7/2;
break;
default:
if(_c<=_3||(180<_c&&_c<=(180+_3))){
_6[_10]=_b*_f/2+_7*_e;
_6[_d?"t":"b"]=_b*_e/2+_7*_f;
_6[_d?"b":"t"]=_b*_e/2;
}else{
if(_c>(360-_3)||(180>_c&&_c>(180-_3))){
_6[_10]=_b*_f/2+_7*_e;
_6[_d?"b":"t"]=_b*_e/2+_7*_f;
_6[_d?"t":"b"]=_b*_e/2;
}else{
if(_c<90||(180<_c&&_c<270)){
_6[_10]=_b*_f+_7*_e;
_6[_d?"t":"b"]=_b*_e+_7*_f;
}else{
_6[_10]=_b*_f+_7*_e;
_6[_d?"b":"t"]=_b*_e+_7*_f;
}
}
}
break;
}
_6[_10]+=_2+_a.length;
}else{
var _10=_d?"b":"t";
switch(_c){
case 0:
case 180:
_6[_10]=_b;
_6.l=_6.r=_7/2;
break;
case 90:
case 270:
_6[_10]=_7;
_6.l=_6.r=_b/2;
break;
default:
if((90-_3)<=_c&&_c<=90||(270-_3)<=_c&&_c<=270){
_6[_10]=_b*_f/2+_7*_e;
_6[_d?"r":"l"]=_b*_e/2+_7*_f;
_6[_d?"l":"r"]=_b*_e/2;
}else{
if(90<=_c&&_c<=(90+_3)||270<=_c&&_c<=(270+_3)){
_6[_10]=_b*_f/2+_7*_e;
_6[_d?"l":"r"]=_b*_e/2+_7*_f;
_6[_d?"r":"l"]=_b*_e/2;
}else{
if(_c<_3||(180<_c&&_c<(180-_3))){
_6[_10]=_b*_f+_7*_e;
_6[_d?"r":"l"]=_b*_e+_7*_f;
}else{
_6[_10]=_b*_f+_7*_e;
_6[_d?"l":"r"]=_b*_e+_7*_f;
}
}
}
break;
}
_6[_10]+=_2+_a.length;
}
}
if(_7){
this._cachedLabelWidth=_7;
}
return _6;
},render:function(dim,_11){
if(!this.dirty){
return this;
}
var o=this.opt,ta=this.chart.theme.axis,_12=o.leftBottom,_13=o.rotation%360,_14,_15,_16,_17,_18,_19,_1a,_1b=o.font||(ta.majorTick&&ta.majorTick.font)||(ta.tick&&ta.tick.font),_1c=o.fontColor||(ta.majorTick&&ta.majorTick.fontColor)||(ta.tick&&ta.tick.fontColor)||"black",_1d=this.chart.theme.getTick("major",o),_1e=_1d.length,_1f="stroke" in o?o.stroke:ta.stroke,_20=_1b?g.normalizedLength(g.splitFontString(_1b).size):0;
if(_13<0){
_13+=360;
}
if(this.vertical){
_14={y:dim.height-_11.b};
_15={y:_11.t};
_16={x:0,y:-1};
_19={x:0,y:0};
_17={x:1,y:0};
_18={x:_2,y:0};
switch(_13){
case 0:
_1a="end";
_19.y=_20*0.4;
break;
case 90:
_1a="middle";
_19.x=-_20;
break;
case 180:
_1a="start";
_19.y=-_20*0.4;
break;
case 270:
_1a="middle";
break;
default:
if(_13<_3){
_1a="end";
_19.y=_20*0.4;
}else{
if(_13<90){
_1a="end";
_19.y=_20*0.4;
}else{
if(_13<(180-_3)){
_1a="start";
}else{
if(_13<(180+_3)){
_1a="start";
_19.y=-_20*0.4;
}else{
if(_13<270){
_1a="start";
_19.x=_12?0:_20*0.4;
}else{
if(_13<(360-_3)){
_1a="end";
_19.x=_12?0:_20*0.4;
}else{
_1a="end";
_19.y=_20*0.4;
}
}
}
}
}
}
}
if(_12){
_14.x=_15.x=_11.l;
_17.x=-1;
_18.x=-_18.x;
}else{
_14.x=_15.x=dim.width-_11.r;
switch(_1a){
case "start":
_1a="end";
break;
case "end":
_1a="start";
break;
case "middle":
_19.x+=_20;
break;
}
}
}else{
_14={x:_11.l};
_15={x:dim.width-_11.r};
_16={x:1,y:0};
_19={x:0,y:0};
_17={x:0,y:1};
_18={x:0,y:_2};
switch(_13){
case 0:
_1a="middle";
_19.y=_20;
break;
case 90:
_1a="start";
_19.x=-_20*0.4;
break;
case 180:
_1a="middle";
break;
case 270:
_1a="end";
_19.x=_20*0.4;
break;
default:
if(_13<(90-_3)){
_1a="start";
_19.y=_12?_20:0;
}else{
if(_13<(90+_3)){
_1a="start";
_19.x=-_20*0.4;
}else{
if(_13<180){
_1a="start";
_19.y=_12?0:-_20;
}else{
if(_13<(270-_3)){
_1a="end";
_19.y=_12?0:-_20;
}else{
if(_13<(270+_3)){
_1a="end";
_19.y=_12?_20*0.4:0;
}else{
_1a="end";
_19.y=_12?_20:0;
}
}
}
}
}
}
if(_12){
_14.y=_15.y=dim.height-_11.b;
}else{
_14.y=_15.y=_11.t;
_17.y=-1;
_18.y=-_18.y;
switch(_1a){
case "start":
_1a="end";
break;
case "end":
_1a="start";
break;
case "middle":
_19.y-=_20;
break;
}
}
}
this.cleanGroup();
try{
var s=this.group,c=this.scaler,t=this.ticks,_21,f=_1.getTransformerFromModel(this.scaler),_22=(dojox.gfx.renderer=="canvas"),_23=_22||!_13&&this.opt.htmlLabels&&!dojo.isIE&&!dojo.isOpera?"html":"gfx",dx=_17.x*_1d.length,dy=_17.y*_1d.length;
s.createLine({x1:_14.x,y1:_14.y,x2:_15.x,y2:_15.y}).setStroke(_1f);
dojo.forEach(this.labels,function(_24){
var _25=f(_24.value),_26,x=_14.x+_16.x*_25,y=_14.y+_16.y*_25;
s.createLine({x1:x,y1:y,x2:x+dx,y2:y+dy}).setStroke(_1d);
if(_24.text){
_26=dc.axis2d.common.createText[_23](this.chart,s,x+dx+_18.x+(_13?0:_19.x),y+dy+_18.y+(_13?0:_19.y),_1a,_24.text,_1b,_1c);
if(_23=="html"){
this.htmlElements.push(_26);
}else{
if(_13){
_26.setTransform([{dx:_19.x,dy:_19.y},g.matrix.rotategAt(_13,x+dx+_18.x,y+dy+_18.y)]);
}
}
}
},this);
}
catch(e){
}
this.dirty=false;
return this;
}});
})();
}
