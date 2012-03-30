/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.charting.plot2d.HorizontalBands"]){
dojo._hasResource["insight.charting.plot2d.HorizontalBands"]=true;
dojo.provide("insight.charting.plot2d.HorizontalBands");
dojo.require("dojox.charting.Element");
dojo.require("dojox.charting.plot2d.common");
dojo.require("dojox.lang.functional");
dojo.require("dojox.lang.utils");
(function(){
var du=dojox.lang.utils,dc=dojox.charting.plot2d.common;
dojo.declare("insight.charting.plot2d.HorizontalBands",dojox.charting.Element,{defaultParams:{bands:[],hAxis:"x",vAxis:"y"},optionalParams:{},constructor:function(_1,_2){
this.opt=dojo.delegate(this.defaultParams,_2);
du.updateWithPattern(this.opt,_2,this.optionalParams);
this.hAxis=this.opt.hAxis;
this.vAxis=this.opt.vAxis;
this.dirty=true;
},clear:function(){
this._hAxis=null;
this._vAxis=null;
this.dirty=true;
return this;
},setAxis:function(_3){
if(_3){
this[_3.vertical?"_vAxis":"_hAxis"]=_3;
}
return this;
},addSeries:function(_4){
return this;
},getSeriesStats:function(){
return dojo.delegate(dc.defaultStats);
},initializeScalers:function(){
return this;
},isDirty:function(){
return this.dirty||this._hAxis&&this._hAxis.dirty||this._vAxis&&this._vAxis.dirty;
},performZoom:function(_5,_6){
return this;
},getRequiredColors:function(){
return 0;
},render:function(_7,_8){
this.dirty=this.isDirty();
if(!this.dirty){
return this;
}
this.cleanGroup();
var s=this.group,ta=this.chart.theme.axis;
try{
var _9=this._vAxis.getScaler(),vt=_9.scaler.getTransformerFromModel(_9);
dojo.forEach(this.opt.bands,function(_a){
var _b=vt(_a.from||this._vAxis.opt.min),to=vt(_a.to||this._vAxis.opt.max);
s.createRect({x:_8.l,y:_7.height-_8.b-to,width:_7.width-_8.r,height:to-_b}).setFill(_a.color);
},this);
}
catch(e){
}
this.dirty=false;
return this;
}});
})();
}
