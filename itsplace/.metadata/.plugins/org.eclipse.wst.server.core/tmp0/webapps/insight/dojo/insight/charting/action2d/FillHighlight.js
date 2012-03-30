/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.charting.action2d.FillHighlight"]){
dojo._hasResource["insight.charting.action2d.FillHighlight"]=true;
dojo.provide("insight.charting.action2d.FillHighlight");
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
dojo.declare("insight.charting.action2d.FillHighlight",dojox.charting.action2d.Base,{defaultParams:{duration:400,easing:dojo.fx.easing.backOut},optionalParams:{highlight:"red"},constructor:function(_6,_7,_8){
var a=_8&&_8.highlight;
this.colorFun=a?(dojo.isFunction(a)?a:cc(a)):hl;
this.connect();
},process:function(o){
if(!o.shape||!(o.type in this.overOutEvents||o.originalEvent in this.overOutEvents)){
return;
}
dojo.forEach(o.plot.series,function(_9){
var i=o.plot._eventSeries[_9.name]&&o.plot._eventSeries[_9.name][o.index];
if(i){
this._process(dojo.delegate(o,{run:i.run,shape:i.shape}));
}
},this);
},_process:function(o){
var _a=o.run.name,_b=o.index,_c,_d,_e;
if(_a in this.anim){
_c=this.anim[_a][_b];
}else{
this.anim[_a]={};
}
if(_c){
_c.action.stop(true);
}else{
var _f=o.shape.getFill();
if(!_f||!(_f instanceof dojo.Color)){
return;
}
this.anim[_a][_b]=_c={start:_f,end:this.colorFun(_f)};
}
var _10=_c.start,end=_c.end;
if(o.type=="onmouseout"||o.originalEvent=="onmouseout"){
var t=_10;
_10=end;
end=t;
}
_c.action=dojox.gfx.fx.animateFill({shape:o.shape,duration:this.duration,easing:this.easing,color:{start:_10,end:end}});
if(o.type=="onmouseout"||o.originalEvent=="onmouseout"){
var _11=dojo.connect(_c.action,"onEnd",this,function(){
dojo.disconnect(_11);
if(this.anim[_a]){
delete this.anim[_a][_b];
}
});
}
_c.action.play();
}});
})();
}
