/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.charting.plot2d.HeatColumns"]){
dojo._hasResource["insight.charting.plot2d.HeatColumns"]=true;
dojo.provide("insight.charting.plot2d.HeatColumns");
dojo.require("dojox.charting.plot2d.common");
dojo.require("dojox.charting.plot2d.Base");
dojo.require("dojox.gfx.fx");
dojo.require("dojox.lang.utils");
dojo.require("dojox.lang.functional");
dojo.require("dojox.lang.functional.reversed");
(function(){
var df=dojox.lang.functional,du=dojox.lang.utils,dc=dojox.charting.plot2d.common,_1=df.lambda("item.purgeGroup()");
dojo.declare("insight.charting.plot2d.HeatColumns",dojox.charting.plot2d.Base,{defaultParams:{hAxis:"x",vAxis:"y",gap:0,animate:null,minAlpha:0.1},optionalParams:{},constructor:function(_2,_3){
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
var t=this.chart.theme.clone(),f,o=this.opt,_7,_8,ht=this._hScaler.scaler.getTransformerFromModel(this._hScaler),_9=this.events();
f=dc.calculateBarSize(this._hScaler.bounds.scale,this.opt);
_7=f.gap;
_8=f.size;
for(var i=this.series.length-1;i>=0;--i){
var _a=this.series[i],_b=t.next("column",[this.opt,_a]);
if(!this.dirty&&!_a.dirty){
this._reconnectEvents(_a.name);
continue;
}
_a.cleanGroup();
var s=_a.group,_c=new Array(_a.data.length);
for(var j=0;j<_a.data.length;++j){
var _d=_a.data[j];
if(_d!==null){
var _e={x:_5.l+ht(j+0.5)+_7,y:_5.t,width:_8,height:_4.height-_5.t-_5.b};
var _f=new dojo.Color(_b.series.fill),_10=this.opt.minAlpha,_11=_f.a;
_f.a=_d?_d*(_11-_10)+_10:0;
var _12=s.createRect(_e).setFill(_f).setStroke(dojo.Color.transparent);
_a.dyn.fill=new dojo.Color(_b.series.fill);
_a.dyn.stroke=_12.getStroke();
if(_9){
var o={element:"column",index:j,run:_a,shape:_12,x:j+0.5,y:0};
this._connectEvents(o);
_c[j]=o;
}
}
}
this._eventSeries[_a.name]=_c;
_a.dirty=false;
}
this.dirty=false;
return this;
}});
})();
}
