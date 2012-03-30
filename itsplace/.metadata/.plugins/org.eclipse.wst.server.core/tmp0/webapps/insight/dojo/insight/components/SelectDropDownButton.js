/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.components.SelectDropDownButton"]){
dojo._hasResource["insight.components.SelectDropDownButton"]=true;
dojo.provide("insight.components.SelectDropDownButton");
dojo.require("dijit.Menu");
dojo.require("dijit.MenuItem");
dojo.require("dijit.form.DropDownButton");
dojo.declare("insight.components.SelectDropDownButton",[dijit.form.DropDownButton],{_selected:null,_menuItems:[],create:function(_1,_2){
var _3=dojo.byId(_2),_4=new dijit.Menu({},dojo.doc.createElement("div"));
dojo.query("option",_3).forEach(function(_5){
var _6=_5.text,_7=_5.value;
var _8=new dijit.MenuItem({label:_6},dojo.doc.createElement("div"));
this._menuItems.push({menu:_4,menuItem:_8,label:_6,value:_7});
_4.addChild(_8);
},this);
this.dropDown=_4;
this._selected=_3.value;
this.inherited(arguments);
},postCreate:function(){
this.inherited(arguments);
dojo.forEach(this._menuItems,function(_9){
this.connect(_9.menuItem,"onClick",function(){
this.attr("selected",_9);
this.onChange(_9.value);
});
if(this._selected==_9.value){
this.attr("selected",_9);
}
},this);
if(!this._selected){
this.attr("selected",this._menuItems[0].menuItem);
}
},_setSelectedAttr:function(_a){
if(_a){
this._selected=_a.value;
this.attr("label",_a.label);
}
},onChange:function(_b){
}});
}
