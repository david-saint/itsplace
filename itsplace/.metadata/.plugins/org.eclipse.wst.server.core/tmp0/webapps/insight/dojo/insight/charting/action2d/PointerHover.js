/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.charting.action2d.PointerHover"]){
dojo._hasResource["insight.charting.action2d.PointerHover"]=true;
dojo.provide("insight.charting.action2d.PointerHover");
dojo.require("dojox.charting.action2d.Base");
dojo.require("dojox.color");
(function(){
dojo.declare("insight.charting.action2d.PointerHover",dojox.charting.action2d.Base,{defaultParams:{cursor:"pointer"},constructor:function(_1,_2,_3){
_3=_3||{};
this.cursor=_3.cursor||this.defaultParams.cursor;
this.connect();
},process:function(o){
if(!o.eventMask){
return;
}
var rn=o.eventMask.getNode();
if(!rn.getAttribute("cursor")){
rn.setAttribute("cursor",this.cursor);
}
}});
})();
}
