/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.resources.ResourceTree"]){
dojo._hasResource["insight.resources.ResourceTree"]=true;
dojo.provide("insight.resources.ResourceTree");
dojo.require("dijit.Tree");
dojo.declare("insight.resources.ResourceTreeNode",dijit._TreeNode,{attributeMap:dojo.delegate(dijit._Widget.prototype.attributeMap,{label:{node:"labelNode",type:"innerHTML"},tooltip:{node:"rowNode",type:"attribute",attribute:"title"}}),_updateItemClasses:function(_1){
this.inherited(arguments);
if(_1.health&&_1.health.rating=="NOSAMPLE"){
this.set("style",{display:"none"});
}else{
this.set("style",{display:""});
}
},_onMouseEnter:function(_2){
dojo.addClass(this.rowNode,"dijitTreeHighlight");
this.inherited(arguments);
},_onMouseLeave:function(_3){
dojo.removeClass(this.rowNode,"dijitTreeHighlight");
this.inherited(arguments);
}});
dojo.declare("insight.resources.ResourceTree",dijit.Tree,{persist:false,healthLampUrl:null,startup:function(){
this.inherited(arguments);
dojo.forEach(this.attr("rootNode").getChildren(),function(_4){
this._expandNode(_4);
},this);
this.rootNode.startup();
},getLabel:function(_5){
var _6="";
if(_5.health){
_6+="<img src='"+this.healthLampUrl.build({lamp:_5.healthLamp})+"' /> ";
}
_6+=this.model.getLabel(_5);
return _6;
},getTooltip:function(_7){
return this.model.getLabel(_7);
},findTreeNode:function(_8){
return this.getNodesByItem(_8.toString())[0];
},highlightResource:function(_9){
var _a=this.findTreeNode(_9);
if(_a){
this._selectNode(_a);
}else{
this.set("path",this._resourcePath(_9));
}
},_resourcePath:function(_b){
_b=new insight.resources.ResourceKey(_b);
var _c=[];
function _d(_e){
if(_e){
_c.push(_e.toString());
_d(_e.makeParentResourceKey());
if(_e.getName()){
if(_e.getType()=="Application"){
_c.push("insight:type=Application");
}else{
if(_e.getType()=="Server"){
_c.push("insight:type=Server");
}
}
}
}
};
_d(_b);
return _c.reverse();
},_createTreeNode:function(_f){
return new insight.resources.ResourceTreeNode(_f);
},_initState:function(){
if(this.persist){
var _10=dojo.cookie(this.cookieName);
this._openedItemIds={};
if(_10){
dojo.forEach(_10.split("|"),function(_11){
this._openedItemIds[_11]=true;
},this);
}
}
},_saveState:function(){
if(!this.persist){
return;
}
var ary=[];
for(var id in this._openedItemIds){
ary.push(id);
}
dojo.cookie(this.cookieName,ary.join("|"),{expires:1});
},_onDownArrow:function(_12){
var _13=this._getNextNode(_12.node);
if(_13&&_13.isTreeNode){
if(dojo.style(_13,"display")=="none"){
this._onDownArrow(dojo.delegate(_12,{node:_13}));
return;
}
this.focusNode(_13);
}
},_onUpArrow:function(_14){
var _15=_14.node;
var _16=_15.getPreviousSibling();
if(_16){
_15=_16;
while(_15.isExpandable&&_15.isExpanded&&_15.hasChildren()){
var _17=_15.getChildren();
_15=_17[_17.length-1];
}
}else{
var _18=_15.getParent();
if(!(!this.showRoot&&_18===this.rootNode)){
_15=_18;
}
}
if(_15&&_15.isTreeNode){
if(dojo.style(_15,"display")=="none"){
this._onUpArrow(dojo.delegate(_14,{node:_15}));
return;
}
this.focusNode(_15);
}
}});
}
