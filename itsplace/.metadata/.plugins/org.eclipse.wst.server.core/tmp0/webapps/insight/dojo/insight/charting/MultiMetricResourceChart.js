/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.charting.MultiMetricResourceChart"]){
dojo._hasResource["insight.charting.MultiMetricResourceChart"]=true;
dojo.provide("insight.charting.MultiMetricResourceChart");
dojo.require("dojox.charting.DataSeries");
dojo.require("insight.charting.Chart");
dojo.require("insight.charting._TimeRangeChart");
dojo.require("dojox.charting.axis2d.Invisible");
dojo.require("insight.charting.axis2d.Metric");
dojo.require("insight.charting.themes.Spring");
dojo.declare("insight.charting.MultiMetricResourceChart",[insight.charting.Chart,insight.charting._TimeRangeChart],{theme:insight.charting.themes.SpringLight,_metrics:null,_metricLabels:null,postCreate:function(){
this.inherited(arguments);
this._metrics=[];
this._metricLabels={};
},addResourceMetricPlot:function(_1,_2,_3,_4,_5,_6){
var _7=this._names(_2),_8=this._metrics.length;
_1=this._store(_1);
_4=dojo.delegate({type:insight.charting.axis2d.Metric,metric:_2},_4);
_5=dojo.delegate({vAxis:_7.axis,metric:_2,label:_3},_5);
_6=dojo.delegate({plot:_7.plot,metric:_2,label:_3,store:_1.name},_6);
if(_8>1||_4.hidden){
_4.type=dojox.charting.axis2d.Invisible;
}else{
if(_8==1){
_4.leftBottom=false;
}
}
_4.vertical=true;
this.chart.addAxis(_7.axis,_4);
this.chart.addPlot(_7.plot,_5);
this.chart.addSeries(_7.series,new dojox.charting.DataSeries(_1,{},_2),_6);
this._registerChartEvents(_7.plot);
this.chart.movePlotToFront(_7.plot);
this._metrics.push(_2);
this._metricLabels[_2]=_3;
},addResourceMetricPlotAction:function(_9,_a,_b){
this.addPlotAction(this._names(_9).plot,_a,_b);
},_names:function(_c){
return {axis:_c+"YAxis",plot:_c+"Plot",series:_c+"Series"};
}});
}
