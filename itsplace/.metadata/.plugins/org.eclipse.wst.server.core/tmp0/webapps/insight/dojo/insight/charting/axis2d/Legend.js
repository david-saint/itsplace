/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.charting.axis2d.Legend"]){
dojo._hasResource["insight.charting.axis2d.Legend"]=true;
dojo.provide("insight.charting.axis2d.Legend");
dojo.require("dojox.charting.axis2d.Base");
dojo.require("dojox.charting.axis2d.common");
dojo.require("dojo.colors");
dojo.require("dojo.string");
dojo.require("dojox.gfx");
dojo.require("dojox.lang.functional");
dojo.require("dojox.lang.utils");
(function(){
var dc=dojox.charting,du=dojox.lang.utils,g=dojox.gfx,_1=dc.scaler.linear,_2=4,_3=45;
dojo.declare("insight.charting.axis2d.Legend",dojox.charting.axis2d.Base,{opt:null,dirty:true,offset:0,defaultParams:{vertical:false,leftBottom:true,htmlLabels:true,series:[]},optionalParams:{labels:[],maxLabelSize:0,font:"",fontColor:""},constructor:function(_4,_5){
this.opt=dojo.delegate(this.defaultParams,_5);
du.updateWithPattern(this.opt,_5,this.optionalParams);
},clear:function(){
this.dirty=true;
return this;
},initialized:function(){
return !this.dirty;
},getOffsets:function(){
var _6={l:0,r:0,t:0,b:0},o=this.opt,_7=0,a,b,c,d,gl=dc.scaler.common.getNumericLabel,_8=0,ta=this.chart.theme.axis,_9=o.leftBottom,_a=o.font||(ta.majorTick&&ta.majorTick.font)||(ta.tick&&ta.tick.font),_b=_a?g.normalizedLength(g.splitFontString(_a).size):0;
if(_b){
if(o.maxLabelSize){
_7=o.maxLabelSize;
}else{
if(this.labels){
_7=this._groupLabelWidth(this.labels,_a);
}else{
_7=0;
}
}
if(this.vertical){
_6[_9?"l":"r"]=_7+_2;
}else{
_6[_9?"b":"t"]=_b+_2;
_6.l=_6.r=_7/2;
}
}
if(_7){
this._cachedLabelWidth=_7;
}
return _6;
},render:function(_c,_d){
if(!this.dirty){
return this;
}
var o=this.opt,ta=this.chart.theme.axis,_e=o.leftBottom,_f,_10,_11,_12,_13,_14,_15=o.font||(ta.majorTick&&ta.majorTick.font)||(ta.tick&&ta.tick.font),_16=o.fontColor||(ta.majorTick&&ta.majorTick.fontColor)||(ta.tick&&ta.tick.fontColor)||"black",_17="stroke" in o?o.stroke:ta.stroke,_18=_15?g.normalizedLength(g.splitFontString(_15).size):0;
if(this.vertical){
_f={y:_c.height-_d.b};
_10={y:_d.t};
_11={x:0,y:-1};
_13={x:0,y:0};
_12={x:_2,y:0};
_14="end";
_13.y=_18*0.4;
if(_e){
_f.x=_10.x=_d.l;
_12.x=-_12.x;
}else{
_f.x=_10.x=_c.width-_d.r;
switch(_14){
case "start":
_14="end";
break;
case "end":
_14="start";
break;
case "middle":
_13.x+=_18;
break;
}
}
}else{
_f={x:_d.l};
_10={x:_c.width-_d.r};
_11={x:1,y:0};
_13={x:0,y:0};
_12={x:0,y:_2};
_14="middle";
_13.y=_18;
if(_e){
_f.y=_10.y=_c.height-_d.b;
}else{
_f.y=_10.y=_d.t;
_12.y=-_12.y;
switch(_14){
case "start":
_14="end";
break;
case "end":
_14="start";
break;
case "middle":
_13.y-=_18;
break;
}
}
}
this.cleanGroup();
try{
var s=this.group,_19=(dojox.gfx.renderer=="canvas"),_1a=_19||this.opt.htmlLabels&&!dojo.isIE&&!dojo.isOpera?"html":"gfx";
dojo.forEach(o.series,function(_1b,pos,arr){
var _1c=this.chart.series[this.chart.runs[_1b]],_1d=s.createGroup(),_1e=_1d.createGroup(),_1f=_1c.label,_20,x,y,_21=this._makeIcon(_1e,_1c),_13={x:0,y:0},_22={x:0,y:0};
if(pos===0){
_14="start";
x=_f.x;
y=_f.y;
_13.x+=_21.w+2;
_22.y-=_21.h-2;
}else{
if(pos===arr.length-1){
_14="end";
x=_10.x;
y=_10.y;
_13.x-=_21.w+2;
_22.x-=_21.w;
_22.y-=_21.h-2;
}else{
_14="middle";
x=((_10.x-_f.x)/(arr.length-1)*pos)+_f.x;
y=((_10.y-_f.y)/(arr.length-1)*pos)+_f.y;
_13.x+=(_21.w/2)-2;
_22.x-=(this._groupLabelWidth([_1f],_15)/2)+_21.w;
_22.y-=_21.h-2;
}
}
_1e.setTransform([{dx:x+_12.x+_22.x,dy:y+_12.y+_22.y}]);
_20=dc.axis2d.common.createText[_1a](this.chart,_1d,x+_12.x+_13.x,y+_12.y+_13.y,_14,_1f,_15,_16);
if(_1a=="html"){
this.htmlElements.push(_20);
}
},this);
}
catch(e){
}
this.dirty=false;
return this;
},_makeIcon:function(_23,_24){
var ta=this.chart.theme.axis,_25=this.opt.font||(ta.majorTick&&ta.majorTick.font)||(ta.tick&&ta.tick.font),_26=_25?g.normalizedLength(g.splitFontString(_25).size):0,mb={h:_26,w:_26},dyn=_24.dyn;
if(dyn.fill){
_23.createRect({x:2,y:2,width:mb.w-4,height:mb.h-4}).setFill(dyn.fill);
}else{
if(dyn.stroke||dyn.marker){
var _27={x1:0,y1:mb.h/2,x2:mb.w,y2:mb.h/2};
if(dyn.stroke||dyn.bstroke){
_23.createLine(_27).setStroke(dyn.stroke||dyn.bstroke);
}
if(dyn.marker){
var c={x:mb.w/2,y:mb.h/2};
if(_24.markerStroke){
_23.createPath({path:"M"+c.x+" "+c.y+" "+dyn.marker}).setFill(_24.markerStroke.color).setStroke(_24.markerStroke);
}else{
if(dyn.stroke){
_23.createPath({path:"M"+c.x+" "+c.y+" "+dyn.marker}).setFill(dyn.stroke.color).setStroke(dyn.stroke);
}else{
_23.createPath({path:"M"+c.x+" "+c.y+" "+dyn.marker}).setFill(dyn.color).setStroke(dyn.color);
}
}
}
}else{
}
}
return {h:mb.h,w:mb.w};
},_groupLabelWidth:function(_28,_29){
if(!_28.length){
return 0;
}
if(dojo.isObject(_28[0])){
_28=df.map(_28,function(_2a){
return _2a.text;
});
}
var s=_28.join("<br>");
return dojox.gfx._base._getTextBox(s,{font:_29}).w||0;
}});
})();
}
