/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.components.ErrorButton"]){
dojo._hasResource["insight.components.ErrorButton"]=true;
dojo.provide("insight.components.ErrorButton");
dojo.require("dijit.form.Button");
dojo.declare("insight.components.ErrorButton",dijit.form.Button,{hidden:true,parentNode:null,startup:function(){
this.inherited(arguments);
this.parentNode=this.domNode.parentNode;
this.attr("hidden",this.hidden);
},_setHiddenAttr:function(_1){
this.hidden=_1;
if(!this.parentNode){
return;
}
dojo.style(this.parentNode,{display:_1?"none":null});
}});
}
