/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.time.TimeRangeDropDownButton"]){
dojo._hasResource["insight.time.TimeRangeDropDownButton"]=true;
dojo.provide("insight.time.TimeRangeDropDownButton");
dojo.require("dijit.Menu");
dojo.require("dijit.MenuItem");
dojo.require("dijit.form.DropDownButton");
dojo.require("insight.time");
dojo.declare("insight.time.TimeRangeDropDownButton",dijit.form.DropDownButton,{times:[insight.time.millis.minute*15,insight.time.millis.minute*30,insight.time.millis.hour,insight.time.millis.hour*4,insight.time.millis.hour*8,insight.time.millis.hour*12,insight.time.millis.day,insight.time.millis.day*3,insight.time.millis.week],playPauseMenuItem:null,postCreate:function(){
this.inherited(arguments);
var _1=new dijit.Menu({},dojo.doc.createElement("div"));
this.playPauseMenuItem=this._createMenuItem(0);
_1.addChild(this.playPauseMenuItem);
dojo.forEach(this.times,function(_2){
_1.addChild(this._createMenuItem(_2));
},this);
this.dropDown=_1;
},_createMenuItem:function(_3){
var _4=new dijit.MenuItem({label:insight.time.shortLabel(_3)});
this.connect(_4,"onClick",function(){
this.attr("selected",_3);
});
return _4;
},setLabelTimeRange:function(_5){
var _6=insight.time.shortLabel(_5.getDuration(),true)+" @ ";
if(!_5.isAnchored()){
_6+="now";
this.playPauseMenuItem.set("label","pause");
}else{
_6+=insight.time.labels(_5.getEndDate(),new Date()).start;
this.playPauseMenuItem.set("label","now");
}
this.set("label",_6);
this.set("title",_6);
},_setSelectedAttr:function(_7){
if(_7!=null){
this.onChange(_7);
}
},onChange:dijit._connectOnUseEventHandler});
}
