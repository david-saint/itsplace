/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.charting.plot2d.BackgroundColumns"]){
dojo._hasResource["insight.charting.plot2d.BackgroundColumns"]=true;
dojo.provide("insight.charting.plot2d.BackgroundColumns");
dojo.require("dojox.charting.plot2d.common");
dojo.require("dojox.charting.plot2d.Base");
dojo.require("dojox.gfx.fx");
dojo.require("dojox.lang.utils");
dojo.require("dojox.lang.functional");
dojo.require("dojox.lang.functional.reversed");
(function(){
var df=dojox.lang.functional,du=dojox.lang.utils,dc=dojox.charting.plot2d.common,_1=df.lambda("item.purgeGroup()");
dojo.declare("insight.charting.plot2d.BackgroundColumns",dojox.charting.plot2d.Base,{defaultParams:{hAxis:"x",vAxis:"y",gap:0,animate:null},optionalParams:{},constructor:function(_2,_3){
this.opt=dojo.clone(this.defaultParams);
du.updateWithObject(this.opt,_3);
du.updateWithPattern(this.opt,_3,this.optionalParams);
this.series=[];
this.hAxis=this.opt.hAxis;
this.vAxis=this.opt.vAxis;
this.animate=this.opt.animate;
},getSeriesStats:function(){
return dc.collectSimpleStats(this.series);
},render:function(_4,_5){
if(this.zoom&&!this.isDataDirty()){
return this.performZoom(_4,_5);
}
this.resetEvents();
this.dirty=this.isDirty();
if(this.dirty){
dojo.forEach(this.series,_1);
this._eventSeries={};
this.cleanGroup();
var s=this.group;
df.forEachRev(this.series,function(_6){
_6.cleanGroup(s);
});
}
var t=this.chart.theme,f,_7,_8,ht=this._hScaler.scaler.getTransformerFromModel(this._hScaler),_9=this.events();
f=dc.calculateBarSize(this._hScaler.bounds.scale,this.opt);
_7=f.gap;
_8=f.size;
for(var i=this.series.length-1;i>=0;--i){
var _a=this.series[i];
if(!this.dirty&&!_a.dirty){
this._reconnectEvents(_a.name);
continue;
}
_a.cleanGroup();
var s=_a.group,_b=new Array(_a.data.length);
for(var j=0;j<_a.data.length;++j){
var _c=_a.data[j];
if(_c!==null){
var _d={x:_5.l+ht(j+0.5)+_7,y:_5.t,width:_8,height:_4.height-_5.t-_5.b};
var _e=new dojo.Color(t.plotarea.fill);
_e.a=0;
var _f=s.createRect(_d).setFill(_e).setStroke(_e);
_a.dyn.fill=_f.getFill();
_a.dyn.stroke=_f.getStroke();
if(_9){
var o={element:"column",index:j,run:_a,shape:_f,x:j+0.5,y:0};
this._connectEvents(o);
_b[j]=o;
}
}
}
this._eventSeries[_a.name]=_b;
_a.dirty=false;
}
this.dirty=false;
return this;
}});
})();
}
