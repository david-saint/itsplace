/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.charting.Chart"]){
dojo._hasResource["insight.charting.Chart"]=true;
dojo.provide("insight.charting.Chart");
dojo.require("dijit._Widget");
dojo.require("dojox.charting.Chart2D");
dojo.require("insight.charting._Extensions");
dojo.require("dojox.charting.plot2d.Grid");
dojo.require("insight.charting.themes.Spring");
dojo.require("dojox.data.QueryReadStore");
dojo.require("dojox.lang.functional");
dojo.require("insight.runtime");
dojo.declare("insight.charting.ChartStore",dojox.data.QueryReadStore,{_data:null,_loading:false,_deferedRequests:null,_xhrArgs:null,constructor:function(_1){
this.inherited(arguments);
this._xhrArgs={};
this._xhrArgs.timeout=_1.timeout||insight.runtime.getXhrTimeout("Chart");
this._xhrArgs.error=function(_2,_3){
dojo.publish("error/xhr",[_2,_3,_3.args.uid]);
};
this.url=this.urlTemplate.build(this.urlParams);
delete this.urlParams;
},getValue:function(){
var _4=this.inherited(arguments);
return _4!=null?_4:null;
},_fetchItems:function(_5,_6,_7){
_5.uid=this._generateUid();
if(this._loading){
this._deferedRequests.push({request:_5,fetchHandler:_6,errorHandler:_7});
}else{
if(this._data){
this._xhrFetchHandler(this._data,_5,_6,_7);
}else{
this._loading=true;
this._deferedRequests=[];
this._fetchItemsInternal(_5,_6,this._wrapErrorHandler(_7));
}
}
},_fetchItemsInternal:function(_8,_9,_a){
var _b=_8.serverQuery||_8.query||{};
if(!this.doClientPaging){
_b.start=_8.start||0;
if(_8.count){
_b.count=_8.count;
}
}
if(!this.doClientSorting&&_8.sort){
var _c=[];
dojo.forEach(_8.sort,function(_d){
if(_d&&_d.attribute){
_c.push((_d.descending?"-":"")+_d.attribute);
}
});
_b.sort=_c.join(",");
}
if(this.doClientPaging&&this._lastServerQuery!==null&&dojo.toJson(_b)==dojo.toJson(this._lastServerQuery)){
this._numRows=(this._numRows===-1)?this._items.length:this._numRows;
_9(this._items,_8,this._numRows);
}else{
var _e=this.requestMethod.toLowerCase()=="post"?dojo.xhrPost:dojo.xhrGet;
var _f=_e(dojo.delegate({url:this.url,handleAs:"json-comment-optional",content:_b,uid:_8.uid},this._xhrArgs));
_f.addCallback(dojo.hitch(this,function(_10){
this._xhrFetchHandler(_10,_8,_9,_a);
}));
_f.addErrback(function(_11){
_a(_11,_8);
});
this.lastRequestHash=new Date().getTime()+"-"+String(Math.random()).substring(2);
this._lastServerQuery=dojo.mixin({},_b);
}
},_xhrFetchHandler:function(_12,_13,_14,_15){
var _16=this.getInherited(arguments);
this._data=_12;
this._loading=false;
_16.call(this,_12,_13,_14,this._wrapErrorHandler(_15));
dojo.forEach(this._deferedRequests,function(o){
_16.call(this,_12,o.request,o.fetchHandler,this._wrapErrorHandler(o.errorHandler));
},this);
this._deferedRequests=null;
},_wrapErrorHandler:function(_17){
if(_17){
var _18=this;
return function(){
_18._storeError.apply(this,arguments);
_17.apply(this,arguments);
};
}else{
return this._xhrError;
}
},_storeError:function(_19,_1a){
dojo.publish("error/store",["Unable to return requested data",_1a.store.chart+"-"+_1a.store.name,_1a.uid]);
},_generateUid:function(){
return new Date().getTime()+":"+Math.floor(Math.random()*1000);
},setUrl:function(_1b,_1c){
this.urlTemplate=_1b||this.urlTemplate;
this.url=this.urlTemplate.build(_1c);
this._reset();
},_reset:function(){
this._data=null;
this._items=[];
this._itemsByIdentity=null;
this._numRows=-1;
}});
dojo.declare("insight.charting.Chart",dijit._Widget,{theme:insight.charting.themes.Spring,chart:null,stores:null,dataPoints:30,actions:null,gridHLines:true,gridVLines:true,margins:{l:10,t:10,r:10,b:10},postCreate:function(){
this.inherited(arguments);
this._responseTimeLabelFunc=dojo.hitch(this,this._responseTimeLabelFunc);
this._throughputLabelFunc=dojo.hitch(this,this._throughputLabelFunc);
this._errorRateLabelFunc=dojo.hitch(this,this._errorRateLabelFunc);
this._healthLabelFunc=dojo.hitch(this,this._healthLabelFunc);
this.stores={};
this.urlParams={};
this.actions={};
if(this.url){
this.addStore("main",this.url);
delete this.url;
}
this.chart=new dojox.charting.Chart2D(this.domNode,{margins:this.margins});
this.chart.parent=this;
this.chart.setTheme(this.theme);
this.chart.addPlot("grid",{type:dojox.charting.plot2d.Grid,hMajorLines:this.gridHLines,vMajorLines:this.gridVLines,vMinorLines:this.gridVLines});
this.subscribe("window/resize",this.resizeToFit);
this.connect(this,"onChartElementMouseOver",function(_1d){
Insight.pause(true);
});
this.connect(this,"onChartElementMouseOut",function(_1e){
Insight.play(true);
});
},startup:function(){
this.inherited(arguments);
this.chart.movePlotToBack("grid");
this.chart.render();
this.dims=this._dimensions(this.domNode);
},destroy:function(_1f){
dojox.lang.functional.forIn(this.actions,function(a){
a.destroy();
},this);
this.chart.destroy();
delete this.chart;
delete this.actions;
delete this.stores;
this.inherited(arguments);
},resizeToFit:function(){
if(!this.dims){
return;
}
var _20=this._dimensions(this.domNode);
if(!(_20.w==this.dims.w&&_20.h==this.dims.h)){
this.chart.resize(_20.w,_20.h);
}
this.dims=_20;
},_dimensions:function(_21){
return {w:dojo.contentBox(_21.parentNode).w,h:parseInt(dojo.style(this.domNode,"height"))};
},addPlotAction:function(_22,_23,_24){
var a=new _23(this.chart,_22,_24);
this.actions[a.declaredClass+"."+_22]=a;
},getPlotAction:function(_25,_26){
return this.actions[_26+"."+_25];
},addStore:function(_27,url){
this.stores[_27]=new insight.charting.ChartStore({urlTemplate:url,urlParams:this._urlParams(_27),name:_27,chart:this.id});
},_store:function(_28){
return dojo.isString(_28)?this.stores[_28]:_28;
},refresh:function(){
if(this.chart._delayedRenderHandle){
if(!this._delayedRenderHandle){
this._delayedRenderHandle=setTimeout(dojo.hitch(this,this.refresh),100);
}
return;
}
if(this._delayedRenderHandle){
clearTimeout(this._delayedRenderHandle);
this._delayedRenderHandle=null;
}
this.chart.delayedRender();
this.chart._makeDirty();
dojox.lang.functional.forIn(this.stores,function(_29,_2a){
var _2b=false;
_29.setUrl(null,this._urlParams(_2a));
_29.fetch({scope:this,onComplete:function(){
if(_2b){
console.error("runaway onComplete cycle aborted");
return;
}
_2b=true;
dojo.forEach(this.chart.series,function(_2c){
if(_2c.source&&_2c.source.fetch&&(_2c.store==_2a||(!_2c.store&&_2a=="main"))){
_2c.source.fetch();
}
},this);
this.chart.render();
}});
},this);
},_urlParams:function(_2d){
return dojo.mixin({dataPoints:this.dataPoints},this.urlParams.all,this.urlParams[_2d]);
},urlParam:function(_2e,_2f,_30){
if(!_30){
_30="all";
}
if(!this.urlParams[_30]){
this.urlParams[_30]={};
}
this.urlParams[_30][_2e]=_2f;
return this;
},_registerChartEvents:function(_31){
this.chart.connectToPlot(_31,this,function(_32){
if(_32.type=="onmouseover"){
this.onChartElementMouseOver(_32);
}else{
if(_32.type=="onmouseout"){
this.onChartElementMouseOut(_32);
}else{
if(_32.type=="onclick"){
this.onChartElementClick(_32);
}else{
if(_32.type=="onplotreset"){
this.onChartPlotReset(_32);
}
}
}
}
});
},onChartElementMouseOver:dijit._connectOnUseEventHandler,onChartElementMouseOut:dijit._connectOnUseEventHandler,onChartElementClick:dijit._connectOnUseEventHandler,onChartPlotReset:dijit._connectOnUseEventHandler,_responseTimeLabelFunc:function(_33,_34,_35,_36){
if(!_33){
return "unknown";
}
_36=this.chart.getAxis(_36||"y");
if(_36.scaler.bounds.upper<=1000){
return Math.floor(_34)+" ms";
}else{
var _35=(_36.scaler.major.tick/100)%10==0?0:1;
return dojo.number.format(_34/1000,{places:_35})+" s";
}
},_throughputLabelFunc:function(_37,_38,_39,_3a){
_3a=this.chart.getAxis(_3a||"y");
var _39=_3a.scaler.bounds.upper<=10?1:0;
return dojo.number.format(_38,{places:_39})+" tpm";
},_errorRateLabelFunc:function(_3b,_3c,_3d,_3e){
return dojo.number.format(_3c,{type:"percent",places:2});
},_healthLabelFunc:function(_3f,_40,_41,_42){
var _43;
if(isNaN(_40)||_40===null){
return "no sample";
}else{
if(_40>=0.94){
_43="excellent";
}else{
if(_40>=0.85){
_43="good";
}else{
if(_40>=0.7){
_43="fair";
}else{
if(_40>=0.5){
_43="poor";
}else{
_43="unacceptable";
}
}
}
}
}
return _43;
}});
}
