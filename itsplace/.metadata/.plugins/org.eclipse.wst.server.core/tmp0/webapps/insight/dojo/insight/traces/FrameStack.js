/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.traces.FrameStack"]){
dojo._hasResource["insight.traces.FrameStack"]=true;
dojo.provide("insight.traces.FrameStack");
dojo.require("dojo.DeferredList");
dojo.require("dojo.cookie");
dojo.require("dojo.data.ItemFileReadStore");
dojo.require("dojo.fx");
dojo.require("dojo.number");
dojo.require("dijit.Tree");
dojo.require("dijit.layout.ContentPane");
dojo.require("dijit.tree.TreeStoreModel");
dojo.require("insight.runtime");
dojo.declare("insight.traces.FrameTreeNode",dijit._TreeNode,{templateString:dojo.cache("insight.traces","templates/FrameTreeNode.html","<div class=\"dijitTreeNode\" waiRole=\"presentation\"\n\t><div dojoAttachPoint=\"rowNode\" class=\"dijitTreeRow\" waiRole=\"presentation\" dojoAttachEvent=\"onmouseenter:_onMouseEnter, onmouseleave:_onMouseLeave, onclick:_onClick, ondblclick:_onDblClick\"\n\t\t><div class=\"insightGanttContainer\" dojoAttachPoint=\"ganttNode\"\n\t\t\t><div dojoAttachPoint=\"durationNode\" class=\"insightGanttDuration\"></div\n\t\t>&nbsp;</div\n\t\t><div class=\"operationExpando\"></div\n\t\t><img src=\"${_blankGif}\" alt=\"\" dojoAttachPoint=\"expandoNode\" class=\"dijitTreeExpando\" waiRole=\"presentation\"\n\t\t><span dojoAttachPoint=\"expandoNodeText\" class=\"dijitExpandoText\" waiRole=\"presentation\"\n\t\t></span\n\t\t><span dojoAttachPoint=\"contentNode\"\n\t\t\tclass=\"dijitTreeContent\" waiRole=\"presentation\">\n\t\t\t<img src=\"${_blankGif}\" alt=\"\" dojoAttachPoint=\"iconNode\" class=\"dijitIcon dijitTreeIcon\" waiRole=\"presentation\"\n\t\t\t/><span dojoAttachPoint=\"labelNode\" class=\"dijitTreeLabel\" wairole=\"treeitem\" tabindex=\"-1\" waiState=\"selected-false\" dojoAttachEvent=\"onfocus:_onLabelFocus\"></span\n\t\t></span\n\t></div>\n\t<div dojoAttachPoint=\"containerNode\" class=\"dijitTreeContainer\" waiRole=\"presentation\" style=\"display: none;\"></div>\n</div>\n"),ganttNode:null,attributeMap:dojo.delegate(dijit._Widget.prototype.attributeMap,{label:{node:"labelNode",type:"innerText"},tooltip:{node:"rowNode",type:"attribute",attribute:"title"},duration:{node:"durationNode",type:"innerText"}}),postCreate:function(){
this.inherited(arguments);
this._addGanttBar(this.params.traceStartNanos,this.params.traceEndNanos,this.params.frameStartNanos,this.params.frameEndNanos,true);
dojo.forEach(dojo.fromJson(this.params.selfFragments),function(_1){
this._addGanttBar(this.params.traceStartNanos,this.params.traceEndNanos,_1.startNanos,_1.endNanos);
},this);
if(this.params.ganttTooltip){
dojo.attr(this.ganttNode,"title",this.params.ganttTooltip);
}
},_addGanttBar:function(_2,_3,_4,_5,_6){
var _7=dojo.doc.createElement("div");
dojo.addClass(_7,_6?"insightGanttBarMaster":"insightGanttBar");
dojo.style(_7,{position:"absolute",top:"2px",bottom:"2px",left:(_4-_2)/(_3-_2)*100+"%",right:(_3-_5)/(_3-_2)*100+"%"});
dojo.place(_7,this.ganttNode,"last");
},_onMouseEnter:function(_8){
dojo.addClass(this.rowNode.parentNode,"dijitTreeHighlight");
this.inherited(arguments);
},_onMouseLeave:function(_9){
dojo.removeClass(this.rowNode.parentNode,"dijitTreeHighlight");
this.inherited(arguments);
},destroyRecursive:function(){
if(this.operation){
this.operation.destroyRecursive();
this.operation=null;
}
this.inherited(arguments);
}});
dojo.declare("insight.traces.FrameStack",null,{autoOpenThreshold:0.1,traceId:null,tree:null,timeout:insight.runtime.getXhrTimeout("FrameStack"),constructor:function(_a,_b){
this.autoOpenThreshold=_a.autoOpenThreshold||this.autoOpenThreshold;
this.traceId=_a.traceId;
if(!this.traceId){
throw ("traceId is required to create FrameStack");
}
if(dojo.isIE<9){
this.autoOpenThreshold*=2;
}
dojo.xhrGet({url:_a.traceUri.build(),handleAs:"json",timeout:this.timeout,load:dojo.hitch(this,function(_c,_d){
this._build(_b,_c,_a.operationUri);
}),error:function(){
dojo.publish("error/xhr",arguments);
}});
},_build:function(_e,_f,_10){
var _11=this._buildTreeStore(_f.trace,_10);
this.tree=new dijit.Tree({onClick:dojo.hitch(this,this.toggleOperation),model:_11,persist:false,_createTreeNode:function(_12){
_12.duration=dojo.number.format(this.model.store.getValue(_12.item,"duration"))+" ms";
if(this.model.store.hasAttribute(_12.item,"durationSelf")){
_12.ganttTooltip=dojo.number.format(this.model.store.getValue(_12.item,"durationSelf"))+" ms (self time)";
_12.selfFragments=this.model.store.getValue(_12.item,"selfFragments");
}
_12.traceStartNanos=this.model.store.getValue(_12.item,"traceStartNanos");
_12.traceEndNanos=this.model.store.getValue(_12.item,"traceEndNanos");
_12.frameStartNanos=this.model.store.getValue(_12.item,"frameStartNanos");
_12.frameEndNanos=this.model.store.getValue(_12.item,"frameEndNanos");
return new insight.traces.FrameTreeNode(_12);
}},_e);
this.tree.startup();
this._expandTree(this.tree);
},_buildTreeStore:function(_13,_14){
var _15={identifier:"id",label:"label",traceId:_13.id,range:_13.range,items:[]};
this._processFrame(_15,_13.frameStack,null,_14);
return new dijit.tree.TreeStoreModel({store:new dojo.data.ItemFileReadStore({data:_15}),query:{type:"root"}});
},_processFrame:function(_16,_17,_18,_19){
var _1a={id:_17.id,traceId:_16.traceId,frameId:_17.id,url:_19.build({traceId:_16.traceId,frameId:_17.id}),label:_17.operation.label,duration:_17.range.duration,durationSelf:_17.range.selfDuration,traceStartNanos:_16.range.startNanos,traceEndNanos:_16.range.endNanos,frameStartNanos:_17.range.startNanos,frameEndNanos:_17.range.endNanos,selfFragments:dojo.toJson(_17.range.selfFragments)};
if(_18){
if(!_18.children){
_18.children=[];
}
_18.children.push(_1a);
_1a.parentId=_18.frameId;
}else{
_1a.type="root";
_16.items.push(_1a);
}
dojo.forEach(_17.children,function(_1b){
this._processFrame(_16,_1b,_1a,_19);
},this);
},_expandTree:function(_1c){
var _1d=this._openedOperations();
this._expandTreeNode(_1c,_1c.rootNode,_1d);
if(dojo.indexOf(_1d,_1c.rootNode.item.frameId)!=-1){
this._toggleOperation(_1c.rootNode.item,_1c.rootNode);
}
},_expandTreeNode:function(_1e,_1f,_20){
_1e._expandNode(_1f);
dojo.forEach(_1f.getChildren(),function(_21){
var _22=_21.item.duration/_1f.item.duration;
if(_22>this.autoOpenThreshold||this._forceExpand(_21.item,_20)){
this._expandTreeNode(_1e,_21,_20);
}
if(dojo.indexOf(_20,_21.item.frameId)!=-1){
this._toggleOperation(_21.item,_21);
}
},this);
_1f.setSelected(false);
},_forceExpand:function(_23,_24){
if(_23.forceExpand===false){
return false;
}
if(dojo.indexOf(_24,_23.frameId)!=-1){
return true;
}
if(_23.children){
for(var i=0;i<_23.children.length;i++){
if(this._forceExpand(_23.children[i],_24)){
return true;
}
}
}
_23.forceExpand=false;
return false;
},toggleOperation:function(_25,_26){
var _27=this._toggleOperation(_25,_26);
if(dojo.cookie.isSupported()){
this._updateCookie(_25.frameId,!!_27);
}
},_openedOperations:function(){
var _28=dojo.cookie(this.tree.id+"-operations");
if(!_28){
return [];
}
var _29=_28.split(":")[0];
if(_29!=this.traceId){
return [];
}
return _28.split(":")[1].split(",");
},_updateCookie:function(_2a,_2b){
var _2c=this._openedOperations();
if(_2b){
_2c.push(_2a);
}else{
_2c=dojo.filter(_2c,function(i){
return i!=_2a;
},this);
}
dojo.cookie(this.tree.id+"-operations",this.traceId+":"+_2c.join(","));
},_toggleOperation:function(_2d,_2e){
var _2f=dojo.query("> .dijitTreeRow",_2e.domNode)[0];
if(_2e.operation){
_2e.operation.destroyRecursive();
dojo.removeClass(_2f,"dijitTreeNodeOpen");
_2e.operation=null;
}else{
var _30=function(_31){
_31.cancelBubble=true;
};
_2e.operation=new dijit.layout.ContentPane({href:_2d.url,id:_2e.id+"_operation","class":"operation",onClick:_30,onDblClick:_30});
dojo.addClass(_2e.operation.domNode,"operation");
dojo.addClass(_2f,"dijitTreeNodeOpen");
_2e.operation.placeAt(dojo.query("> .dijitTreeRow > .dijitTreeContent",_2e.domNode)[0],"last");
_2e.operation.startup();
}
return _2e.operation;
}});
}
