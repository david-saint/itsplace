/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.charting.plot2d.HistogramBars"]){
dojo._hasResource["insight.charting.plot2d.HistogramBars"]=true;
dojo.provide("insight.charting.plot2d.HistogramBars");
dojo.require("dojox.charting.plot2d.Bars");
(function(){
var dc=dojox.charting.plot2d.common;
dojo.declare("insight.charting.plot2d.HistogramBars",dojox.charting.plot2d.Bars,{getSeriesStats:function(){
var _1=dc.collectSimpleStats(this.series),t;
_1.hmin-=1;
_1.hmax+=1;
t=_1.hmin,_1.hmin=_1.vmin,_1.vmin=t;
t=_1.hmax,_1.hmax=_1.vmax,_1.vmax=t;
return _1;
}});
})();
}
