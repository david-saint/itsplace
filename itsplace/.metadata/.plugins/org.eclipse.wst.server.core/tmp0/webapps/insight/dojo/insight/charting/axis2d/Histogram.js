/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.charting.axis2d.Histogram"]){
dojo._hasResource["insight.charting.axis2d.Histogram"]=true;
dojo.provide("insight.charting.axis2d.Histogram");
dojo.require("dojox.charting.axis2d.Default");
dojo.require("dojox.charting.scaler.linear");
dojo.require("dojo.colors");
dojo.require("dojo.string");
dojo.require("dojox.gfx");
dojo.require("dojox.lang.functional");
dojo.require("dojox.lang.utils");
(function(){
var dc=dojox.charting,g=dojox.gfx,_1=dc.scaler.linear,_2=4;
dojo.declare("insight.charting.axis2d.Histogram",dojox.charting.axis2d.Default,{getOffsets:function(){
var s=this.scaler,_3={l:0,r:0,t:0,b:0};
if(!s){
return _3;
}
var o=this.opt,_4=0,a,b,c,d,gl=dc.scaler.common.getNumericLabel,_5=0,ma=s.major,mi=s.minor,ta=this.chart.theme.axis,_6=o.font||(ta.majorTick&&ta.majorTick.font)||(ta.tick&&ta.tick.font),_7=this.chart.theme.getTick("major",o),_8=this.chart.theme.getTick("minor",o),_9=_6?g.normalizedLength(g.splitFontString(_6).size):0,_a=o.rotation%360,_b=o.leftBottom,_c=Math.abs(Math.cos(_a*Math.PI/180)),_d=Math.abs(Math.sin(_a*Math.PI/180));
if(_a<0){
_a+=360;
}
if(_9){
if(o.maxLabelSize){
_4=o.maxLabelSize;
}else{
if(this.labels){
_4=this._groupLabelWidth(this.labels,_6);
}else{
if(this.ticks&&this.ticks.major&&this.ticks.major[0]&&this.ticks.major[0].label!=(this.ticks.major[0].value||0).toString()){
_4=this._groupLabelWidth(dojo.map(this.ticks.major,function(_e){
return _e.label;
}),_6);
}else{
if(this._cachedLabelWidth){
_4=this._cachedLabelWidth;
}else{
_4=this._groupLabelWidth(["default"],_6);
}
}
}
}
if(this.vertical){
var _f=_b?"l":"r";
switch(_a){
case 0:
case 180:
_3[_f]=_4;
_3.t=_3.b=_9/2;
break;
case 90:
case 270:
_3[_f]=_9;
_3.t=_3.b=_4/2;
break;
default:
if(_a<=centerAnchorLimit||(180<_a&&_a<=(180+centerAnchorLimit))){
_3[_f]=_9*_d/2+_4*_c;
_3[_b?"t":"b"]=_9*_c/2+_4*_d;
_3[_b?"b":"t"]=_9*_c/2;
}else{
if(_a>(360-centerAnchorLimit)||(180>_a&&_a>(180-centerAnchorLimit))){
_3[_f]=_9*_d/2+_4*_c;
_3[_b?"b":"t"]=_9*_c/2+_4*_d;
_3[_b?"t":"b"]=_9*_c/2;
}else{
if(_a<90||(180<_a&&_a<270)){
_3[_f]=_9*_d+_4*_c;
_3[_b?"t":"b"]=_9*_c+_4*_d;
}else{
_3[_f]=_9*_d+_4*_c;
_3[_b?"b":"t"]=_9*_c+_4*_d;
}
}
}
break;
}
_3[_f]+=_2+Math.max(_7.length,_8.length);
}else{
var _f=_b?"b":"t";
switch(_a){
case 0:
case 180:
_3[_f]=_9;
_3.l=_3.r=_4/2;
break;
case 90:
case 270:
_3[_f]=_4;
_3.l=_3.r=_9/2;
break;
default:
if((90-centerAnchorLimit)<=_a&&_a<=90||(270-centerAnchorLimit)<=_a&&_a<=270){
_3[_f]=_9*_d/2+_4*_c;
_3[_b?"r":"l"]=_9*_c/2+_4*_d;
_3[_b?"l":"r"]=_9*_c/2;
}else{
if(90<=_a&&_a<=(90+centerAnchorLimit)||270<=_a&&_a<=(270+centerAnchorLimit)){
_3[_f]=_9*_d/2+_4*_c;
_3[_b?"l":"r"]=_9*_c/2+_4*_d;
_3[_b?"r":"l"]=_9*_c/2;
}else{
if(_a<centerAnchorLimit||(180<_a&&_a<(180-centerAnchorLimit))){
_3[_f]=_9*_d+_4*_c;
_3[_b?"r":"l"]=_9*_c+_4*_d;
}else{
_3[_f]=_9*_d+_4*_c;
_3[_b?"l":"r"]=_9*_c+_4*_d;
}
}
}
break;
}
_3[_f]+=_2+Math.max(_7.length,_8.length);
}
}
if(_4){
this._cachedLabelWidth=_4;
}
return _3;
},render:function(dim,_10){
if(!this.dirty){
return this;
}
var o=this.opt;
var _11,_12,_13,_14,_15,_16,ta=this.chart.theme.axis,_17=o.font||(ta.majorTick&&ta.majorTick.font)||(ta.tick&&ta.tick.font),_18=o.fontColor||(ta.majorTick&&ta.majorTick.fontColor)||(ta.tick&&ta.tick.fontColor)||"black",_19=this.chart.theme.getTick("major",o),_1a=this.chart.theme.getTick("minor",o),_1b=this.chart.theme.getTick("micro",o),_1c=Math.max(_19.length,_1a.length,_1b.length),_1d="stroke" in o?o.stroke:ta.stroke,_1e=_17?g.normalizedLength(g.splitFontString(_17).size):0;
if(this.vertical){
_11={y:dim.height-_10.b};
_12={y:_10.t};
_13={x:0,y:-1};
if(o.leftBottom){
_11.x=_12.x=_10.l;
_14={x:-1,y:0};
_16="end";
}else{
_11.x=_12.x=dim.width-_10.r;
_14={x:1,y:0};
_16="start";
}
_15={x:_14.x*(_1c+_2),y:_1e*0.4};
}else{
_11={x:_10.l};
_12={x:dim.width-_10.r};
_13={x:1,y:0};
_16="middle";
if(o.leftBottom){
_11.y=_12.y=dim.height-_10.b;
_14={x:0,y:1};
_15={y:_1c+_2+_1e};
}else{
_11.y=_12.y=_10.t;
_14={x:0,y:-1};
_15={y:-_1c-_2};
}
_15.x=0;
}
this.cleanGroup();
try{
var s=this.group,c=this.scaler,t=this.ticks,_1f,f=_1.getTransformerFromModel(this.scaler),_20=(dojox.gfx.renderer=="canvas"),_21=_20||this.opt.htmlLabels&&!dojo.isIE&&!dojo.isOpera?"html":"gfx",dx=_14.x*_19.length,dy=_14.y*_19.length;
s.createLine({x1:_11.x,y1:_11.y,x2:_12.x,y2:_12.y}).setStroke(_1d);
dojo.forEach(t.major,function(_22){
var _23=f(_22.value+0.5),_24,x=_11.x+_13.x*_23,y=_11.y+_13.y*_23;
s.createLine({x1:x,y1:y,x2:x+dx,y2:y+dy}).setStroke(_19);
if(_22.label){
_24=dc.axis2d.common.createText[_21](this.chart,s,x+_15.x,y+_15.y,_16,_22.label,_17,_18);
if(_21=="html"){
this.htmlElements.push(_24);
}
}
},this);
dx=_14.x*_1a.length;
dy=_14.y*_1a.length;
_1f=c.minMinorStep<=c.minor.tick*c.bounds.scale;
dojo.forEach(t.minor,function(_25){
var _26=f(_25.value+0.5),_27,x=_11.x+_13.x*_26,y=_11.y+_13.y*_26;
s.createLine({x1:x,y1:y,x2:x+dx,y2:y+dy}).setStroke(_1a);
if(_1f&&_25.label){
_27=dc.axis2d.common.createText[_21](this.chart,s,x+_15.x,y+_15.y,_16,_25.label,_17,_18);
if(_21=="html"){
this.htmlElements.push(_27);
}
}
},this);
dx=_14.x*_1b.length;
dy=_14.y*_1b.length;
dojo.forEach(t.micro,function(_28){
var _29=f(_28.value+0.5),_2a,x=_11.x+_13.x*_29,y=_11.y+_13.y*_29;
s.createLine({x1:x,y1:y,x2:x+dx,y2:y+dy}).setStroke(_1b);
},this);
}
catch(e){
}
this.dirty=false;
return this;
}});
})();
}
