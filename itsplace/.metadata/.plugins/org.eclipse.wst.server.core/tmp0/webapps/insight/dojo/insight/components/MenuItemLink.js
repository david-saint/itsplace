/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.components.MenuItemLink"]){
dojo._hasResource["insight.components.MenuItemLink"]=true;
dojo.provide("insight.components.MenuItemLink");
dojo.require("dijit.MenuItem");
dojo.declare("insight.components.MenuItemLink",dijit.MenuItem,{href:null,onClick:function(){
window.location=this.href.toString();
}});
}
