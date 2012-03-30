/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.charting.axis2d.Metric"]){
dojo._hasResource["insight.charting.axis2d.Metric"]=true;
dojo.provide("insight.charting.axis2d.Metric");
dojo.require("dojox.charting.axis2d.Default");
dojo.require("insight.charting.scaler.linear");
dojo.require("insight.charting.axis2d.Titled");
dojo.require("dojo.colors");
dojo.require("dojo.string");
dojo.require("dojox.gfx");
dojo.require("dojox.lang.functional");
dojo.require("dojox.lang.utils");
(function(){
var dc=dojox.charting,du=dojox.lang.utils,g=dojox.gfx,_1=insight.charting.scaler.linear,_2=4;
dojo.declare("insight.charting.axis2d.Metric",[dojox.charting.axis2d.Default,insight.charting.axis2d._Titled],{_scaleTo:null,calculate:function(_3,_4,_5,_6){
if(this.initialized()){
return this;
}
var o=this.opt;
if(this._scaleTo){
_4=Math.max.apply(Math,this.chart.series[this.chart.runs[this._scaleTo]].data);
}
this.labels="labels" in o?o.labels:_6;
this.scaler=_1.buildScaler(_3,_4,_5,o);
var _7=this.scaler.bounds;
if("scale" in this){
o.from=_7.lower+this.offset;
o.to=(_7.upper-_7.lower)/this.scale+o.from;
if(!isFinite(o.from)||isNaN(o.from)||!isFinite(o.to)||isNaN(o.to)||o.to-o.from>=_7.upper-_7.lower){
delete o.from;
delete o.to;
delete this.scale;
delete this.offset;
}else{
if(o.from<_7.lower){
o.to+=_7.lower-o.from;
o.from=_7.lower;
}else{
if(o.to>_7.upper){
o.from+=_7.upper-o.to;
o.to=_7.upper;
}
}
this.offset=o.from-_7.lower;
}
this.scaler=_1.buildScaler(_3,_4,_5,o);
_7=this.scaler.bounds;
if(this.scale==1&&this.offset==0){
delete this.scale;
delete this.offset;
}
}
var _8=0,ta=this.chart.theme.axis,_9=o.font||(ta.majorTick&&ta.majorTick.font)||(ta.tick&&ta.tick.font),_a=_9?g.normalizedLength(g.splitFontString(_9).size):0;
if(this.vertical){
if(_a){
_8=_a+_2;
}
}else{
if(_a){
var _b,i;
if(o.labelFunc&&o.maxLabelSize){
_b=o.maxLabelSize;
}else{
if(this.labels){
_b=this._groupLabelWidth(this.labels,_9);
}else{
var _c=Math.ceil(Math.log(Math.max(Math.abs(_7.from),Math.abs(_7.to)))/Math.LN10),t=[];
if(_7.from<0||_7.to<0){
t.push("-");
}
t.push(dojo.string.rep("9",_c));
var _d=Math.floor(Math.log(_7.to-_7.from)/Math.LN10);
if(_d>0){
t.push(".");
for(i=0;i<_d;++i){
t.push("9");
}
}
_b=dojox.gfx._base._getTextBox(t.join(""),{font:_9}).w;
}
}
_8=_b+_2;
}
}
this.scaler.minMinorStep=_8;
this.ticks=_1.buildTicks(this.scaler,o);
return this;
},scaleTo:function(_e){
this._scaleTo=_e;
this.dirty=true;
}});
})();
}
