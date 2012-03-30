/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.charting.RealTimePerformanceChart"]){
dojo._hasResource["insight.charting.RealTimePerformanceChart"]=true;
dojo.provide("insight.charting.RealTimePerformanceChart");
dojo.require("dojox.charting.DataSeries");
dojo.require("insight.charting.Chart");
dojo.require("insight.charting._TimeRangeChart");
dojo.require("insight.charting.action2d.ClickHighlight");
dojo.require("insight.charting.action2d.PointerHover");
dojo.require("insight.charting.action2d.StrokeHighlight");
dojo.require("insight.charting.action2d.Tooltip");
dojo.require("insight.charting.axis2d.Metric");
dojo.require("dojox.charting.plot2d.Columns");
dojo.require("insight.charting.themes.Spring");
dojo.declare("insight.charting.RealTimePerformanceChart",[insight.charting.Chart,insight.charting._TimeRangeChart],{theme:insight.charting.themes.Spring,type:dojox.charting.plot2d.Columns,_supressTimeRangeActions:true,_selectedWindow:null,postCreate:function(){
this.inherited(arguments);
this.chart.addAxis("y",{type:insight.charting.axis2d.Metric,vertical:true,includeZero:true,fixed:false,minorTicks:false,labelFunc:this._responseTimeLabelFunc});
this.chart.addPlot("default",{type:this.type,gap:1.5});
this.chart.addSeries("duration",new dojox.charting.DataSeries(this.stores.main,{},dojo.hitch(this,function(s,i){
var o={y:s.getValue(i,"maxDuration")};
if(this._selectedWindow&&this._selectedWindow.start==s.getValue(i,"start")&&this._selectedWindow.end==s.getValue(i,"end")){
o.fill=new dojo.Color([113,166,59,0.8]);
this._selectedWindow.index=s.getIdentity(i);
}
return o;
})));
this._registerChartEvents("default");
this.addPlotAction("default",insight.charting.action2d.ClickHighlight,{highlight:new dojo.Color([113,166,59,0.8])});
this.addPlotAction("default",insight.charting.action2d.PointerHover);
this.addPlotAction("default",insight.charting.action2d.StrokeHighlight,{highlight:"#2354A4"});
this.addPlotAction("default",insight.charting.action2d.Tooltip,{text:dojo.hitch(this,function(_1){
var s=this.stores.main,i=s._items[_1.index];
return s.getValue(i,"tooltip");
})});
this.connect(this,"onChartElementClick",function(_2){
var s=this.stores.main,i=s._items[_2.index];
if(_2.element=="column"){
this._selectedWindow={start:s.getValue(i,"start"),end:s.getValue(i,"end"),index:s.getIdentity(i)};
this.onLoadWindow(this._selectedWindow);
}
});
this.connect(this.chart.stack[this.chart.plots["default"]],"render",dojo.hitch(this,function(_3){
if(this._selectedWindow&&this._selectedWindow.index&&this.stores.main._data){
var w=this._selectedWindow,s=this.stores.main,i=s._items[this._selectedWindow.index];
if(s.getValue(i,"start")!=w.start||s.getValue(i,"end")!=w.end){
w.index=null;
return;
}
var a=this.getPlotAction("default","insight.charting.action2d.ClickHighlight"),o=this.chart.stack[this.chart.plots["default"]]._eventSeries["duration"][w.index];
if(a&&o){
a.anim["duration"].action.shape=o.shape;
}
}
}));
},onLoadWindow:dijit._connectOnUseEventHandler});
}
