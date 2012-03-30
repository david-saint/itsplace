/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.charting._TimeRangeChart"]){
dojo._hasResource["insight.charting._TimeRangeChart"]=true;
dojo.provide("insight.charting._TimeRangeChart");
dojo.require("insight.charting.action2d.FillHighlight");
dojo.require("insight.charting.action2d.PointerHover");
dojo.require("insight.charting.action2d.Tooltip");
dojo.require("insight.charting.axis2d.TimeRange");
dojo.require("insight.charting.plot2d.BackgroundColumns");
dojo.require("insight.time");
dojo.require("insight.time.TimeRange");
dojo.declare("insight.charting._TimeRangeChart",null,{timeRange:null,clickable:false,hideXAxis:false,postCreate:function(){
this.timeRange.modulate(this.dataPoints);
this.inherited(arguments);
var _1=this.hideXAxis?dojox.charting.axis2d.Invisible:insight.charting.axis2d.TimeRange;
this.chart.addAxis("x",{type:_1,timeRange:this.timeRange,relative:Insight.playing(),from:1,minorTickStep:1});
this.subscribe("Insight.play",function(){
this.updateTimeRange({relative:true});
});
this.subscribe("Insight.pause",function(){
this.updateTimeRange({relative:false});
});
if(!this._supressTimeRangeActions){
this.chart.addPlot("backgroundColumnsPlot",{type:insight.charting.plot2d.BackgroundColumns,vAxis:null});
this.chart.addSeries("backgroundColumnsSeries",new dojox.charting.DataSeries(this.stores.main,{},function(s,i){
return new insight.time.TimeRange({start:s.getValue(i,"start"),end:s.getValue(i,"end")});
}),{plot:"backgroundColumnsPlot"});
this.addPlotAction("backgroundColumnsPlot",insight.charting.action2d.FillHighlight,{highlight:new dojo.Color([220,238,241,0.5])});
this.chart.addPlot("foregroundColumnsPlot",{type:insight.charting.plot2d.BackgroundColumns,vAxis:null});
this.chart.addSeries("foregroundColumnsSeries",new dojox.charting.DataSeries(this.stores.main,{},function(s,i){
return new insight.time.TimeRange({start:s.getValue(i,"start"),end:s.getValue(i,"end")});
}),{plot:"foregroundColumnsPlot"});
this._registerChartEvents("foregroundColumnsPlot");
if(this.clickable){
this.addPlotAction("foregroundColumnsPlot",insight.charting.action2d.PointerHover);
}
this.addPlotAction("foregroundColumnsPlot",insight.charting.action2d.Tooltip,{text:dojo.hitch(this,this._tooltipMessage)});
this.subscribe("highlightDate",this.highlightDate);
this.subscribe("blurDate",this.blurDate);
this.connect(this.chart.stack[this.chart.plots["foregroundColumnsPlot"]],"render",dojo.hitch(this,function(_2){
if(this._highlightDate){
this.highlightDate(this._highlightDate);
}
}));
}
},startup:function(){
if(!this._supressTimeRangeActions){
this.chart.movePlotToBack("backgroundColumnsPlot");
this.chart.movePlotToFront("foregroundColumnsPlot");
}
var _3=this.chart.stack[this.chart.plots["grid"]];
if(_3.opt.hMajorLines||_3.opt.hMinorLines){
var _4=[];
dojox.lang.functional.forIn(this.chart.axes,function(_5){
if(_5.vertical){
_4.push(_5);
}
});
if(_4[0]){
_3.vAxis=_4[0].name;
}
}
this.inherited(arguments);
},_urlParams:function(){
var _6=this.inherited(arguments);
if(this.timeRange&&!(_6.start&&_6.end)){
_6.start=this.timeRange.getStart();
_6.end=this.timeRange.getEnd();
}
return _6;
},updateTimeRange:function(_7){
var _8=this.chart.getAxis("x"),_9=_8.declaredClass=="insight.charting.axis2d.TimeRange";
if(_7.timeRange){
this.timeRange=_7.timeRange.clone().modulate(this.dataPoints);
if(_9){
_8.setTimeRange(this.timeRange,true);
}
this.refresh();
}
if(typeof _7.relative=="boolean"&&_9){
_8.setRelative(_7.relative);
}
},_tooltipMessage:function(o){
var t=o.run&&o.run.data&&o.run.data[o.index],_a=o.run&&o.run.source&&o.run.source.store,_b="",_c,_d,_e,_f,_10,_11,_12;
_a.fetchItemByIdentity({onItem:function(i){
_c=i;
},identity:o.index});
_d=insight.time.labels(_a.getValue(_c,"start"),_a.getValue(_c,"end"),false,this.timeRange.getDuration());
_b+="<h2>"+_d.start+" - "+_d.end+"</h2>";
_b+="<table class='dl'>";
var _13=dojox.lang.functional.keys(o.chart.runs);
dojo.forEach(o.chart.getPlotOrder(),function(_14){
var _15=dojo.filter(_13,function(run){
return _14===o.chart.series[o.chart.runs[run]].plot;
},this);
var _16=dojo.map(_15,function(_17){
var _18=o.chart.getAxis(o.chart.stack[o.chart.plots[_14]].vAxis),_e=o.chart.series[o.chart.runs[_17]],_12=_e.data[o.index];
if(!_18||_12==null){
return;
}
return {label:_e.label,value:_12,formattedValue:(_18.opt.labelFunc?_18.opt.labelFunc.call(_18.opt,_12.toString(),_12,_18.scaler.major.prec):_12.toString())};
},this);
_16=dojo.filter(_16,function(_19){
return !!_19&&!!_19.formattedValue;
},this);
if(_16.length===0){
return;
}
_16.sort(function(a,b){
return b.value-a.value;
});
dojo.forEach(_16,function(_1a){
_b+="<tr><td>"+_1a.label+"</td><td>"+_1a.formattedValue+"</td></tr>";
},this);
},this);
_b+="</table>";
return _b;
},highlightDate:function(_1b){
_1b=insight.time.normalizeToDate(_1b);
if(this._highlightDate){
this.blurDate(this._highlightDate);
}
this._highlightDate=_1b;
var i=this._findDateIndex(_1b,"backgroundColumnsSeries");
if(i!=-1){
this.chart.fireEvent("backgroundColumnsSeries","onmouseover",i);
}
},blurDate:function(_1c){
_1c=insight.time.normalizeToDate(_1c);
this._highlightDate=null;
var i=this._findDateIndex(_1c,"backgroundColumnsSeries");
if(i!=-1){
this.chart.fireEvent("backgroundColumnsSeries","onmouseout",i);
}
},_findDateIndex:function(_1d,_1e){
var _1f=this.chart.runs[_1e],_20=this.chart.series[_1f].plot,_21=this.chart.series[_1f];
for(var i=0;i<_21.data.length;i++){
if(_21.data[i].contains(_1d)){
return i;
}
}
return -1;
}});
}
