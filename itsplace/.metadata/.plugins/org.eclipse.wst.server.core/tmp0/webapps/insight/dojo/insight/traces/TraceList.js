/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.traces.TraceList"]){
dojo._hasResource["insight.traces.TraceList"]=true;
dojo.provide("insight.traces.TraceList");
dojo.require("insight.components.SortableTable");
dojo.declare("insight.traces.TraceList",insight.components.SortableTable,{start:null,end:null,_activeRow:null,postCreate:function(){
this.inherited(arguments);
this.start=this.url.params.start;
this.end=this.url.params.end;
},_enhanceTable:function(){
this._addRowInteractivity();
this._selectRow(dojo.query(".trace_list_item",this.domNode)[0]);
this.inherited(arguments);
},loadWindow:function(_1){
dojo.mixin(this._urlParams,_1);
this.load();
},_addRowInteractivity:function(){
var _2=dojo.query("table",this.domNode)[0];
dijit.setWaiRole(_2,"navigation");
if(!this._keyHandlerMap){
this.connect(_2,"onkeypress",this._onKeyPress);
}
dojo.query(".trace_list_item",_2).forEach(function(_3){
dijit.setWaiRole(_3,"link");
this.connect(_3,"onclick",function(){
this._onClick(_3);
});
this.connect(_3,"onmouseenter",function(){
this._onMouseEnter(_3);
});
this.connect(_3,"onmouseleave",function(){
this._onMouseLeave(_3);
});
dojo.query("a",_3).forEach(function(_4){
_4.parentNode.innerHTML=_4.innerHTML;
},this);
},this);
},_selectRow:function(_5){
if(_5&&dojo.hasClass(_5,"trace_list_item")){
if(this._activeRow){
dojo.removeClass(this._activeRow,"selected");
this._activeRow.setAttribute("tabIndex","-1");
dijit.setWaiState(this._activeRow,"selected",false);
}
this._activeRow=_5;
dojo.addClass(this._activeRow,"selected");
this._scrollToSelectedRow();
this._activeRow.setAttribute("tabIndex","0");
dijit.setWaiState(this._activeRow,"selected",true);
_5.focus();
var _6=this._activeRow.id.substr(this._activeRow.id.lastIndexOf("_")+1);
this.loadTrace(_6);
}
},_scrollToSelectedRow:function(){
var _7=dojo.query("table",this.domNode)[0];
var _8=this._activeRow;
var _9=_8.offsetTop-_7.scrollTop;
var _a=(_8.offsetTop+_8.clientHeight)-_7.scrollTop;
if(!(_9>0&&_9<_7.clientHeight&&_a>0&&_a<_7.clientHeight)){
var _b=_8.offsetTop-(_7.clientHeight/2)+(_8.clientHeight/2);
if(_b>_7.scrollHeight-_7.clientHeight){
_b=_7.scrollHeight-_7.clientHeight;
}else{
if(_b<_7.clientHeight/2){
_b=0;
}
}
_7.scrollTop=_b;
}
},loadTrace:function(_c){
},_onMouseEnter:function(_d){
if(_d){
if(dojo.hasClass(_d,"trace_list_item")){
dojo.addClass(_d,"hover");
}
var _e=dojo.attr(_d,"trace-date");
if(_e){
dojo.publish("highlightDate",[_e]);
}
}
},_onMouseLeave:function(_f){
if(_f){
if(dojo.hasClass(_f,"trace_list_item")){
dojo.removeClass(_f,"hover");
}
var _10=dojo.attr(_f,"trace-date");
if(_10){
dojo.publish("blurDate",[_10]);
}
}
},_onClick:function(row){
if(row&&dojo.hasClass(row,"trace_list_item")){
this._selectRow(row);
}
},_onKeyPress:function(e){
if(e.altKey){
return;
}
var dk=dojo.keys;
var key=e.charOrCode;
var map=this._keyHandlerMap;
if(!map){
map={};
map[dojo._isBodyLtr()?dk.LEFT_ARROW:dk.RIGHT_ARROW]="_onUpArrow";
map[dojo._isBodyLtr()?dk.RIGHT_ARROW:dk.LEFT_ARROW]="_onDownArrow";
map[dk.UP_ARROW]="_onUpArrow";
map[dk.DOWN_ARROW]="_onDownArrow";
map[dk.HOME]="_onHomeKey";
map[dk.END]="_onEndKey";
this._keyHandlerMap=map;
}
if(this._keyHandlerMap[key]){
this[this._keyHandlerMap[key]]({node:e.target});
dojo.stopEvent(e);
}
},_onDownArrow:function(_11){
if(this._activeRow){
var _12=this._nextElementSibling(this._activeRow);
if(_12){
this._selectRow(_12);
}
}
},_onUpArrow:function(_13){
if(this._activeRow){
var _14=this._previousElementSibling(this._activeRow);
if(_14){
this._selectRow(_14);
}
}
},_onHomeKey:function(){
if(this._activeRow){
dojo.query(".trace_list_item",this._activeRow.parentNode).slice(0,1).forEach(function(row){
if(row.id!=this._activeRow.id){
this._selectRow(row);
}
},this);
}
},_onEndKey:function(_15){
if(this._activeRow){
dojo.query(".trace_list_item",this._activeRow.parentNode).slice(-1).forEach(function(row){
if(row.id!=this._activeRow.id){
this._selectRow(row);
}
},this);
}
},_previousElementSibling:function(_16){
do{
_16=_16.previousSibling;
}while(_16&&_16.nodeType!=1);
return _16;
},_nextElementSibling:function(_17){
do{
_17=_17.nextSibling;
}while(_17&&_17.nodeType!=1);
return _17;
}});
}
