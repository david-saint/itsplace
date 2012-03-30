/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.charting.axis2d.TimeRange"]){
dojo._hasResource["insight.charting.axis2d.TimeRange"]=true;
dojo.provide("insight.charting.axis2d.TimeRange");
dojo.require("dojo.date");
dojo.require("dojo.date.locale");
dojo.require("dojo.i18n");
dojo.require("dojox.charting.axis2d.Default");
dojo.require("insight.time");
(function(){
var dc=dojox.charting,df=dojox.lang.functional,du=dojox.lang.utils,g=dojox.gfx,_1=dc.scaler.linear,_2=4;
dojo.declare("insight.charting.axis2d.TimeRange",dojox.charting.axis2d.Default,{constructor:function(_3,_4){
_4.timeRange=_4.timeRange||null;
_4.relative=_4.relative||false;
_4.includeZero=_4.includeZero||true;
_4.minorLabels=_4.minorLabels||false;
_4.minorTickStep=_4.minorTickStep||1;
this.inherited(arguments);
},setTimeRange:function(_5,_6){
this.opt.timeRange=_5;
this.dirty=true;
if(!_6){
this.render(this.chart.dim,this.chart.offsets);
}
},setRelative:function(_7){
this.opt.relative=_7;
this.dirty=true;
this.render(this.chart.dim,this.chart.offsets);
},render:function(_8,_9){
if(!this.dirty){
return this;
}
var o=this.opt;
var _a,_b,_c,_d,_e,ta=this.chart.theme.axis,_f=o.font||(ta.majorTick&&ta.majorTick.font)||(ta.tick&&ta.tick.font),_10=o.fontColor||(ta.majorTick&&ta.majorTick.fontColor)||(ta.tick&&ta.tick.fontColor)||"black",_11=this.chart.theme.getTick("major",o),_12=this.chart.theme.getTick("minor",o),_13=this.chart.theme.getTick("micro",o),_14=Math.max(_11.length,_12.length,_13.length),_15="stroke" in o?o.stroke:ta.stroke,_16=_f?g.normalizedLength(g.splitFontString(_f).size):0;
if(this.vertical){
_a={y:_8.height-_9.b};
_b={y:_9.t};
_c={x:0,y:-1};
if(o.leftBottom){
_a.x=_b.x=_9.l;
_d={x:-1,y:0};
}else{
_a.x=_b.x=_8.width-_9.r;
_d={x:1,y:0};
}
_e={x:_d.x*(_14+_2),y:_16*0.4};
}else{
_a={x:_9.l};
_b={x:_8.width-_9.r};
_c={x:1,y:0};
if(o.leftBottom){
_a.y=_b.y=_8.height-_9.b;
_d={x:0,y:1};
_e={y:_14+_2+_16};
}else{
_a.y=_b.y=_9.t;
_d={x:0,y:-1};
_e={y:-_14-_2};
}
_e.x=0;
}
this.cleanGroup();
try{
var s=this.group,c=this.scaler,t=this.ticks,f=_1.getTransformerFromModel(this.scaler),_17=(dojox.gfx.renderer=="canvas"),_18=_17||this.opt.htmlLabels&&!dojo.isIE&&!dojo.isOpera?"html":"gfx",dx=_d.x*_11.length,dy=_d.y*_11.length,_19,_1a=insight.time.labels(this.opt.timeRange.getStartDate(),this.opt.timeRange.getEndDate(),this.opt.relative);
s.createLine({x1:_a.x,y1:_a.y,x2:_b.x,y2:_b.y}).setStroke(_15);
s.createLine({x1:_a.x,y1:_a.y,x2:_a.x+dx,y2:_a.y+dy}).setStroke(_11);
_19=dc.axis2d.common.createText[_18](this.chart,s,_a.x+_e.x,_a.y+_e.y,this.vertical?"middle":"start",_1a.start,_f,_10);
if(_18=="html"){
this.htmlElements.push(_19);
}
s.createLine({x1:_b.x,y1:_b.y,x2:_b.x+dx,y2:_b.y+dy}).setStroke(_11);
_19=dc.axis2d.common.createText[_18](this.chart,s,_b.x+_e.x,_b.y+_e.y,this.vertical?"middle":"end",_1a.end,_f,_10);
if(_18=="html"){
this.htmlElements.push(_19);
}
dx=_d.x*_12.length;
dy=_d.y*_12.length;
function _1b(_1c){
var _1d=f(_1c.value),_19,x=_a.x+_c.x*_1d,y=_a.y+_c.y*_1d;
s.createLine({x1:x,y1:y,x2:x+dx,y2:y+dy}).setStroke(_12);
};
dojo.forEach(t.major,_1b,this);
dojo.forEach(t.minor,_1b,this);
}
catch(e){
}
this.dirty=false;
return this;
}});
})();
}
