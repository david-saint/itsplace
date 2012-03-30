/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.charting.action2d.ClickHighlight"]){
dojo._hasResource["insight.charting.action2d.ClickHighlight"]=true;
dojo.provide("insight.charting.action2d.ClickHighlight");
dojo.require("dojox.charting.action2d.Base");
dojo.require("dojox.color");
(function(){
var _1=100,_2=75,_3=50,c=dojox.color,cc=function(_4){
return function(){
return _4;
};
},hl=function(_5){
var a=new c.Color(_5),x=a.toHsl();
if(x.s==0){
x.l=x.l<50?100:0;
}else{
x.s=_1;
if(x.l<_3){
x.l=_2;
}else{
if(x.l>_2){
x.l=_3;
}else{
x.l=x.l-_3>_2-x.l?_3:_2;
}
}
}
return c.fromHsl(x);
};
dojo.declare("insight.charting.action2d.ClickHighlight",dojox.charting.action2d.Base,{defaultParams:{duration:400,easing:dojo.fx.easing.backOut},optionalParams:{highlight:"red"},constructor:function(_6,_7,_8){
var a=_8&&_8.highlight;
this.colorFun=a?(dojo.isFunction(a)?a:cc(a)):hl;
this.connect();
},process:function(o){
if(!o.shape||o.type!="onclick"){
return;
}
if(!this.anim[o.run.name]){
this.anim[o.run.name]={};
}else{
if(this.anim[o.run.name].action){
if(o.shape==this.anim[o.run.name].action.shape){
return;
}
this.doProcess(this.anim[o.run.name].action.shape,this.anim[o.run.name],this.anim[o.run.name]);
this.anim[o.run.name]={};
}
}
this.doProcess(o.shape,o.run.name);
},doProcess:function(_9,_a,_b){
var _c,_d,_e;
if(_b){
_e=true;
_b.action.stop(true);
}else{
var _f=_9.getFill();
if(!_f||!(_f instanceof dojo.Color)){
return;
}
this.anim[_a]=_b={start:_f,end:this.colorFun(_f)};
}
var _10=_b.start,end=_b.end;
if(_e){
var t=_10;
_10=end;
end=t;
}
_b.action=dojox.gfx.fx.animateFill({shape:_9,duration:this.duration,easing:this.easing,color:{start:_10,end:end}});
_b.action.play();
}});
})();
}
