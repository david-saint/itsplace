/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.charting.themes.Spring"]){
dojo._hasResource["insight.charting.themes.Spring"]=true;
dojo.provide("insight.charting.themes.Spring");
dojo.require("dojox.charting.Theme");
(function(){
var dc=dojox.charting,t=insight.charting.themes;
t.Spring=new dc.Theme({chart:{stroke:null,fill:"#fff"},plotarea:{stroke:null,fill:"#f0f0f0"},axis:{stroke:{color:"#666",width:1},majorTick:{color:"#666",width:0.5,length:6},majorTickLine:{color:"#fff",width:0.8},minorTick:{color:"#666",width:0.5,length:3},minorTickLine:{color:"#fff",width:0.8},tick:{font:"normal normal normal 12px Tahoma,Arial,sans-serif",fontColor:"#666"}},series:{stroke:{width:1,color:"#666"},fill:new dojo.Color([102,102,102,0.6]),font:"normal normal normal 12px Tahoma,Arial,sans-serif",fontColor:"#333"},marker:{stroke:{width:2,color:new dojo.Color([51,51,51,0])},outline:{color:new dojo.Color([204,204,204,0])},fill:new dojo.Color([102,102,102,0]),font:"normal normal normal 12px Tahoma,Arial,sans-serif",fontColor:"#666"},colors:dc.Theme.defineColors({hue:82,saturation:60,low:40,high:88})});
t.Spring.next=function(_1,_2,_3){
var _4=dc.Theme.prototype.next.apply(this,arguments);
if(_1=="line"||_1=="area"){
_4.marker.stroke.width=1;
_4.marker.outline.color=new dojo.Color([255,255,255,0]);
_4.marker.outline.width=1;
}
return _4;
};
t.SpringLight=t.Spring.clone();
t.SpringLight.series.fill=new dojo.Color([102,102,102,0.2]);
t.SpringTransparent=t.Spring.clone();
t.SpringTransparent.plotarea.fill=new dojo.Color([240,240,240,0]);
})();
}
