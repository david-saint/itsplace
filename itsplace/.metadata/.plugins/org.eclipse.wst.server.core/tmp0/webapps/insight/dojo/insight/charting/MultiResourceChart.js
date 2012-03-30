/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.charting.MultiResourceChart"]){
dojo._hasResource["insight.charting.MultiResourceChart"]=true;
dojo.provide("insight.charting.MultiResourceChart");
dojo.require("dojo.number");
dojo.require("dojox.charting.DataSeries");
dojo.require("insight.charting.MultiMetricResourceChart");
dojo.require("insight.charting.action2d.StrokeHighlight");
dojo.require("dojox.charting.axis2d.Invisible");
dojo.require("insight.charting.axis2d.Metric");
dojo.require("dojox.charting.plot2d.Areas");
dojo.require("insight.charting.themes.Spring");
dojo.require("insight.time");
dojo.require("dojox.lang.functional");
dojo.declare("insight.charting.MultiResourceChart",insight.charting.MultiMetricResourceChart,{theme:insight.charting.themes.SpringLight,type:dojox.charting.plot2d.Areas,resourceDisplayLimit:dojo.isIE?5:10,tension:dojo.isIE?null:"S",scaleOnRestack:true,postCreate:function(){
this.inherited(arguments);
if(dojo.isString(this.yAxisArgs.labelFunc)){
this.yAxisArgs.labelFunc=this[this.yAxisArgs.labelFunc];
}
this.chart.addAxis("y",this.yAxisArgs);
this.chart.addPlot("resource",{type:this.type,markers:true,tension:this.tension});
this._registerChartEvents("resource");
this.addPlotAction("resource",insight.charting.action2d.StrokeHighlight,{highlight:"#2354A4"});
this._initialLoad();
},_initialLoad:function(){
this._series=[];
dojox.lang.functional.forIn(this.stores,function(_1,_2){
_1.setUrl(null,this._urlParams(_2));
_1.fetch({scope:this,onComplete:function(){
var t=setTimeout(dojo.hitch(this,function(){
clearTimeout(t);
t=null;
if(_1._data.weights){
var _3=_1._data.weights.sort(function(a,b){
return a.value-b.value;
}).reverse(),_4=dojo.map(_3,function(_5){
return _5.name;
},this);
dojo.forEach(_4.slice(0,this.resourceDisplayLimit),function(_6){
this._addSeries(_6,_1._data.resources[_6],_1);
},this);
}
this.chart.render();
}),50);
}});
},this);
},_addSeries:function(_7,_8,_9){
_9=this._store(_9);
if(!(_9._data&&_9._data.colors&&_9._data.colors[_7])){
return;
}
var _a=new dojo.Color(_9._data.colors[_7]),_b=new dojo.Color(_a),_c=new dojo.Color(_a),_d=new dojo.Color(_a);
_b.a=0.6;
_c.a=0.2;
_d.a=0;
this.chart.addSeries(_7,new dojox.charting.DataSeries(_9,{},_7),{plot:"resource",resourceKey:_7,label:_8||_9._data.resources[_7],stroke:{color:_b},outline:{color:new dojo.Color([255,255,255,0]),width:0},fill:_c,markerStroke:{color:_d},markerOutline:{color:_d},markerFill:_d,store:_9.name});
this._series.push(_7);
},reset:function(){
dojo.forEach(this._series,function(_e){
this.chart.removeSeries(_e);
},this);
this._initialLoad();
},highlightResource:function(_f,_10){
if(_f==this._highlightedResource){
return;
}
var _11={key:_f};
if(this._highlightedResource){
this.blurResource(this._highlightedResource);
}
var _12=this._findResourceSeries(_f,_10);
if(_12){
_11.strokeAlpha=_12.stroke.color.a;
_11.strokeWidth=_12.stroke.width;
_11.outlineAlpha=_12.outline.color.a;
_11.outlineWidth=_12.outline.width;
_12.stroke.color.a=1;
_12.stroke.width=4;
_12.outline.color.a=0.5;
_12.outline.width=5;
_12.dirty=true;
this.chart.moveSeriesToFront(_f).moveSeriesToFront("foregroundColumnsSeries").render();
}
this._highlightedResource=_11;
},blurResource:function(_13,_14){
if(!this._highlightedResource||_13!=this._highlightedResource.key){
return;
}
var _15=this._highlightedResource;
var _16=this._findResourceSeries(_13,_14);
if(_16){
_16.stroke.color.a=_15.strokeAlpha;
_16.stroke.width=_15.strokeWidth;
_16.outline.color.a=_15.outlineAlpha;
_16.outline.width=_15.outlineWidth;
_16.dirty=true;
this.chart.render();
}
this._highlightedResource=null;
},restackResource:function(_17,_18){
var _19=this._findResourceSeries(_17,_18);
if(_19){
if(this.scaleOnRestack){
this.chart.getAxis("y").scaleTo(_17);
}
this.chart.moveSeriesToFront(_17).moveSeriesToFront("foregroundColumnsSeries").render();
}
},_findResourceSeries:function(_1a,_1b){
var _1c=this.chart.series[this.chart.runs[_1a]];
if(!_1c&&_1b){
this._addSeries(_1a,null,_1b);
_1c=this._findResourceSeries(_1a);
}
return _1c;
}});
}
