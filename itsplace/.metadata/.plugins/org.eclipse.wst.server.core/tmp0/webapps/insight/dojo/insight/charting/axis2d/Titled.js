/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.charting.axis2d.Titled"]){
dojo._hasResource["insight.charting.axis2d.Titled"]=true;
dojo.provide("insight.charting.axis2d.Titled");
dojo.require("dojox.charting.axis2d.Default");
dojo.require("dojox.gfx");
dojo.require("dojox.gfx.matrix");
(function(){
var dc=dojox.charting,g=dojox.gfx,m=dojox.gfx.matrix,_1=4;
dojo.declare("insight.charting.axis2d._Titled",null,{getOffsets:function(){
var _2=this.inherited(arguments),o=this.opt;
if(o.title){
var ta=this.chart.theme.axis,_3=o.font||(ta.majorTick&&ta.majorTick.font)||(ta.tick&&ta.tick.font),_4=_3?g.normalizedLength(g.splitFontString(_3).size):0,_5=o.leftBottom;
if(this.vertical){
_2[_5?"l":"r"]+=_4+_1;
}else{
_2[_5?"b":"t"]+=_4+_1;
}
}
return _2;
},render:function(_6,_7){
if(!this.dirty){
return this;
}
this.inherited(arguments);
try{
var o=this.opt,_8=o.title,ta=this.chart.theme.axis,_9=o.font||(ta.majorTick&&ta.majorTick.font)||(ta.tick&&ta.tick.font),_a=o.fontColor||(ta.majorTick&&ta.majorTick.fontColor)||(ta.tick&&ta.tick.fontColor)||"black",_b=_9?g.normalizedLength(g.splitFontString(_9).size):0,cm=this.chart.margins,_c=o.leftBottom;
if(_8){
var x,y,_d;
if(this.vertical){
_d=_c?270:90;
x=_c?_b+cm.l:_6.width-_b-cm.r;
y=(_7.t+_6.height-_7.b)/2;
}else{
_d=0;
x=(_7.l+_6.width-_7.r)/2;
y=_c?_6.height-cm.b:cm.t;
}
var _e=this.group.createText({x:0,y:0,text:_8,align:"middle"});
_e.setFont(_9).setFill(_a);
if(_d){
_e.setTransform([m.translate(x,y),m.rotateg(_d)]);
}else{
_e.setTransform(m.translate(x,y));
}
}
}
catch(e){
}
return this;
}});
dojo.declare("insight.charting.axis2d.Titled",[dojox.charting.axis2d.Default,insight.charting.axis2d._Titled],{});
})();
}
