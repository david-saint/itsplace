/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.charting.ResponseTimeHistogram"]){
dojo._hasResource["insight.charting.ResponseTimeHistogram"]=true;
dojo.provide("insight.charting.ResponseTimeHistogram");
dojo.require("dojo.number");
dojo.require("insight.charting.plot2d.HistogramBars");
dojo.require("insight.charting.Chart");
dojo.require("insight.charting.action2d.PointerHover");
dojo.require("insight.charting.action2d.Tooltip");
dojo.require("insight.charting.axis2d.Histogram");
dojo.require("insight.charting.axis2d.Metric");
dojo.declare("insight.charting.ResponseTimeHistogram",insight.charting.Chart,{timeRange:null,postCreate:function(){
this.inherited(arguments);
this.chart.addAxis("x",{type:insight.charting.axis2d.Metric,includeZero:true,fixed:false,natural:true,minorTicks:false,title:"Invocations"});
this.chart.addAxis("y",{type:insight.charting.axis2d.Histogram,vertical:true,fixed:false,majorTickStep:this.dataPoints-1,minorTicks:false,labelFunc:dojo.hitch(this,this.labelFunc)});
this.chart.addPlot("default",{type:insight.charting.plot2d.HistogramBars,gap:0.5});
this.chart.addSeries("histogram",new dojox.charting.DataSeries(this.stores.main,{},function(s,i){
var o={y:s.getValue(i,"count")},_1=s._data.boundries,_2=s.getValue(i,"start");
if(_1){
if(_2<=_1.satisfied){
o.fill="#46A218";
o.stroke={color:"#FFF",width:1};
}else{
if(_2<=_1.tolerated){
o.fill="#A2D018";
o.stroke={color:"#FFF",width:1};
}else{
o.fill="#FE1818";
o.stroke={color:"#FFF",width:1};
}
}
}
return o;
}),{plot:"default"});
this._registerChartEvents("default");
this.addPlotAction("default",insight.charting.action2d.PointerHover);
this.addPlotAction("default",insight.charting.action2d.StrokeHighlight,{highlight:"#2354A4"});
this.addPlotAction("default",insight.charting.action2d.Tooltip,{text:dojo.hitch(this,function(_3){
var s=this.stores.main,i=s._items[_3.index];
return s.getValue(i,"startLabel")+" - "+s.getValue(i,"endLabel")+"<br />"+dojo.number.format(s.getValue(i,"count"))+" invocations";
})});
},_urlParams:function(){
var _4=this.inherited(arguments);
if(this.timeRange&&!(_4.start&&_4.end)){
_4.start=this.timeRange.getStart();
_4.end=this.timeRange.getEnd();
}
return _4;
},updateTimeRange:function(_5){
if(_5.timeRange){
this.timeRange=_5.timeRange;
this.refresh();
}
},labelFunc:function(_6){
if(!(this.stores.main&&this.stores.main._data&&this.stores.main._items)){
return _6;
}
var _7=parseInt(_6);
if(_7===0){
return this.stores.main._data.startLabel;
}else{
if(_7===this.stores.main._items.length-1){
return this.stores.main._data.endLabel;
}else{
return "";
}
}
}});
}
