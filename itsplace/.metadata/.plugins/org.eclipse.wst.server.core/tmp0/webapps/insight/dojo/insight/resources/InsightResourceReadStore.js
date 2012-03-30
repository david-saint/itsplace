/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.resources.InsightResourceReadStore"]){
dojo._hasResource["insight.resources.InsightResourceReadStore"]=true;
dojo.provide("insight.resources.InsightResourceReadStore");
dojo.require("dojo.data.util.sorter");
dojo.require("dojox.lang.functional");
dojo.require("insight.resources.ResourceKey");
dojo.require("insight.runtime");
dojo.declare("insight.resources.InsightResourceReadStore",null,{url:null,_items:null,timeout:insight.runtime.getXhrTimeout("InsightResourceReadStore"),_resourceMappings:{"Server":null,"EndPoint":null,"Application":"Application.EndPoint","Application.Server":"Application.Server.EndPoint","Application.EndPoint":null,"Application.Server.EndPoint":null},constructor:function(_1){
dojo.mixin(this,_1);
this._items={};
if(_1.root){
delete this.root;
this._items[_1.root.resourceKey]={resourceKey:_1.root.resourceKey,resourceLabel:_1.root.resourceLabel,children:true,store:this};
}
},getItems:function(){
var _2=[],i;
for(i in this._items){
if(this._items.hasOwnProperty(i)){
_2.push(this._items[i]);
}
}
return _2;
},refresh:function(){
dojox.lang.functional.forIn(this._items,function(_3,_4){
if(dojo.isArray(_3.children)){
this.loadItem({item:_3});
}
},this);
},getFeatures:function(){
return {"dojo.data.api.Read":true,"dojo.data.api.Identity":true,"dojo.data.api.Notification":true};
},getValue:function(_5,_6,_7){
if(!this.hasAttribute(_5,_6)){
return _7;
}
var _8=_5[_6];
if(dojo.isArray(_8)){
_8=_8[0];
}
if(!_8){
_8=_7;
}
return _8;
},getValues:function(_9,_a){
var _b=_9[_a];
if(dojo.isArray(_b)){
return _b;
}else{
if(dojo.isObject(_b)&&_b!==null){
return [_b];
}else{
return [];
}
}
},getAttributes:function(_c){
var _d=[];
for(name in _c){
if(this.hasAttribute(_c,name)){
_d.push(name);
}
}
return _d;
},hasAttribute:function(_e,_f){
if(!this.isItem(_e)){
throw "An item is required";
}
return _e.hasOwnProperty(_f)&&_f!="store"&&_f!="parent";
},containsValue:function(_10,_11,_12){
var _13=false;
dojo.forEach(this.getValues(_10,_11),function(_14){
if(_14===_12){
_13=true;
}
},this);
return _13;
},isItem:function(_15){
return _15&&_15.store===this;
},isItemLoaded:function(_16){
if(!this.isItem(_16)){
return false;
}
if(!_16.hasOwnProperty("children")){
return true;
}
return dojo.isArray(_16.children);
},loadItem:function(_17){
var _18=new insight.resources.ResourceKey(this.getIdentity(_17.item));
var _19=_18.getAttribute("type");
var _1a=_18.getAttribute("name");
if(_1a&&this._resourceMappings[_19]){
_18.setAttribute(_19,_1a);
_18.setAttribute("type",this._resourceMappings[_19]);
_18.setAttribute("name",null);
}
_17.query=_18.toString();
this.fetch(_17);
},fetch:function(_1b){
var _1c=_1b||{};
if(!_1c.query){
return _1c;
}
if(_1c.item&&_1c.item.doNotLoad){
return _1c;
}
var xhr=dojo.xhrGet({handleAs:"json",url:this.url.build({resourceKey:_1c.query}),content:_1c.queryOptions,request:_1c,load:dojo.hitch(this,"_fetchLoad"),error:dojo.hitch(this,"_fetchError"),timeout:this.timeout});
_1c.abort=dojo.hitch(xhr,"cancel");
return _1c;
},_fetchLoad:function(_1d,_1e){
var _1f=_1e.args.request,_20=false,_21=_1f.scope||dojo.global,_22=[];
if(!_1f.store){
_1f.store=this;
}
if(_1f.onBegin){
_1f.onBegin.call(_21,_22.length,_1f);
}
if(_1f.sort&&dojo.isArray(_1d.resources)){
_1d.resources.sort(dojo.data.util.sorter.createSortFunction(_1f.sort,this));
}
var _23=dojo.hitch(this,function(_24){
var id=_24[this.getIdentityAttributes()[0]];
var _25=this._items[id];
var _26=new insight.resources.ResourceKey(id);
_24[this.getIdentityAttributes(_24)[0]]=_26.toString();
if(this._resourceMappings[_26.getAttribute("type")]&&!_24.doNotLoad){
_24.children=true;
}
_24.store=this;
if(_25){
var _27=dojo.mixin({},_25);
_25.invocationCount=_24.invocationCount;
_25.health=_24.health;
_25.healthLamp=_24.healthLamp;
this.onSet(_25);
}else{
if(_1f.item){
if(!dojo.isArray(_1f.item.children)){
_1f.item.children=[];
}
_1f.item.children.push(_24);
_24.parent=_1f.item;
}
this.onNew(_24,{item:_24.parent});
this._items[id]=_24;
_22.push(_24);
}
if(_1f.onItem&&!_20){
_1f.onItem.call(_21,_24.parent,_1f);
}
});
if(_1d.resource){
_23(_1d.resource);
}else{
if(_1d.resources&&_1d.resources.length>0){
for(var i in _1d.resources){
_23(_1d.resources[i]);
}
}else{
_23({resourceKey:_1f.item.resourceKey+",children=notfound",resourceLabel:"no matching resources",health:{rating:"NOSAMPLE"},doNotLoad:true});
}
}
if(_1f.onComplete&&!_20){
var _28=null;
if(!_1f.onItem){
_28=_22;
}
_1f.onComplete.call(_21,_28,_1f);
}
},_fetchError:function(_29,_2a){
dojo.publish("error/xhr",arguments);
},close:function(_2b){
console.error("Unimplemented API: dojo.data.api.Read.close",arguments);
throw new Error("Unimplemented API: dojo.data.api.Read.close");
},getLabel:function(_2c){
return this.getValue(_2c,this.getLabelAttributes(_2c)[0]);
},getLabelAttributes:function(_2d){
return ["resourceLabel"];
},getIdentity:function(_2e){
if(!this.isItem(_2e)){
return null;
}
var _2f=this.getValue(_2e,this.getIdentityAttributes(_2e)[0]);
return _2f;
},getIdentityAttributes:function(_30){
return ["resourceKey"];
},fetchItemByIdentity:function(_31){
if(this._items[_31.identity]&&_31.onItem){
_31.onItem.call(_31.scope?_31.scope:dojo.global,this._items[_31.identity]);
return;
}
_31.query=_31.identity;
this.fetch(_31);
},onSet:function(_32,_33,_34,_35){
},onNew:function(_36,_37){
},onDelete:function(_38){
}});
}
