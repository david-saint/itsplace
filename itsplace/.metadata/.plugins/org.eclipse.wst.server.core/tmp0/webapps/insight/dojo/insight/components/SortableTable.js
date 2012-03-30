/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.components.SortableTable"]){
dojo._hasResource["insight.components.SortableTable"]=true;
dojo.provide("insight.components.SortableTable");
dojo.require("dojo.cookie");
dojo.require("dojo.string");
dojo.require("insight.components.PageModule");
dojo.declare("insight.components.SortableTable",insight.components.PageModule,{sortSettings:{field:null,desc:null},defaultSortSettings:{field:null,desc:null},persist:true,startup:function(){
if(!this.sortSettings.field){
this._defaultSortSettings();
}
this.connect(this,"onReplace",this._enhanceTable);
this.inherited(arguments);
},sort:function(_1,_2){
this.sortSettings.field=_1;
this.sortSettings.desc=_2;
if(this.persist&&dojo.cookie.isSupported()){
dojo.cookie(this.id+"_sortSettings",dojo.toJson(this.sortSettings));
}
this._urlParams.sortField=this.sortSettings.field;
this._urlParams.sortDesc=this.sortSettings.desc;
this.load();
},_enhanceTable:function(){
dojo.query(".sort-field",this.domNode).forEach(this._enhanceSortableHeaders,this);
dojo.query("tbody td.collapse",this.domNode).forEach(this._enhanceCollapsibleCells,this);
},_enhanceSortableHeaders:function(th){
var _3="sort-field-";
var _4=null;
dojo.forEach(th["className"].split(" "),function(_5){
if(_5.indexOf(_3)==0){
_4=_5.substr(_3.length);
}
});
if(_4==this.sortSettings.field){
var _6=dojo.doc.createElement("div");
_6.innerHTML=th.innerHTML;
dojo.addClass(th,"sorted");
dojo.addClass(th,this.sortSettings.desc?"sorted-asc":"sorted-desc");
dojo.place(_6,th,"only");
}
this.connect(th,"onclick",function(){
if(this.sortSettings.field==_4){
this.sort(_4,!this.sortSettings.desc);
}else{
this.sort(_4,dojo.hasClass(th,"numeric")||dojo.hasClass(th,"sort-numeric"));
}
});
},_enhanceCollapsibleCells:function(td){
var _7=dojo.doc.createElement("SPAN"),_8=dojo.doc.createElement("SPAN"),_9=dojo.doc.createElement("SPAN");
dojo.addClass(_7,"collapse-container");
dojo.addClass(_8,"collapse-display");
dojo.addClass(_9,"collapse-spacing");
dojo.place(_8,_7,"last");
dojo.place(_9,_7,"last");
dojo.forEach(td.childNodes,function(_a){
dojo.place(_a,_8,"last");
},this);
_9.innerHTML=this._createSpacer(_8);
dojo.place(_7,td,"only");
},_createSpacer:function(_b,_c){
_c=_c||"";
dojo.forEach(_b.childNodes,function(_d){
_c+=this._createSpacer(_d,_c);
},this);
if(_b.nodeType==3){
_c+=dojo.string.pad("-",dojo.string.trim(_b.nodeValue).length*1.25,"- ");
}
return _c;
},_defaultSortSettings:function(){
var _e;
if(this.persist&&dojo.cookie.isSupported()){
_e=dojo.fromJson(dojo.cookie(this.id+"_sortSettings"));
}
if(!_e){
_e=this.defaultSortSettings;
}
this.sortSettings=_e;
this._urlParams.sortField=this.sortSettings.field;
this._urlParams.sortDesc=this.sortSettings.desc;
}});
}
