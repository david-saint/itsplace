/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.traces.FilterMenu"]){
dojo._hasResource["insight.traces.FilterMenu"]=true;
dojo.provide("insight.traces.FilterMenu");
dojo.require("dijit.Menu");
dojo.extend(dijit._MenuBase,{executeOnClick:true,onItemClick:function(_1,_2){
if(typeof this.isShowingNow=="undefined"){
this._markActive();
}
this.focusChild(_1);
if(_1.disabled){
return false;
}
if(_1.popup){
this._openPopup();
}else{
if(this.executeOnClick){
this.onExecute();
}
_1.onClick(_2);
}
}});
}
