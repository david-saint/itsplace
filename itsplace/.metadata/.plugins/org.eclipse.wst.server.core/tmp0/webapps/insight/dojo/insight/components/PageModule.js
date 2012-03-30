/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.components.PageModule"]){
dojo._hasResource["insight.components.PageModule"]=true;
dojo.provide("insight.components.PageModule");
dojo.require("dijit._Widget");
dojo.require("dojox.layout.ResizeHandle");
dojo.require("spring.HtmlFragmentResponseHandler");
dojo.require("insight.runtime");
dojo.declare("insight.components.PageModule",dijit._Widget,{url:null,_urlParams:null,refreshOn:null,maxHeight:null,timeout:insight.runtime.getXhrTimeout("PageModule"),resize:null,constructor:function(){
this._urlParams={};
},postCreate:function(){
this.inherited(arguments);
this.startup();
if(dojo.isArray(this.refreshOn)){
dojo.forEach(this.refreshOn,function(_1){
this.subscribe(_1,function(){
if(!this._hidden){
this.load();
}
});
},this);
}else{
if(this.refreshOn){
this.subscribe(this.refreshOn,function(){
if(!this._hidden){
this.load();
}
});
}
}
},startup:function(){
this.inherited(arguments);
this.load();
},hide:function(_2){
this._hidden=true;
dojo.style(this.domNode,"display","none");
if(!_2){
this.destroyDescendants();
}
},show:function(){
this._hidden=false;
dojo.style(this.domNode,"display","block");
},hidden:function(){
return !!this._hidden;
},delayedLoad:function(_3){
if(this._delayedLoad){
return;
}
this._delayedLoad=dojo.global.setTimeout(dojo.hitch(this,this.load),_3);
},load:function(_4,_5){
if(this._delayedLoad){
dojo.global.clearTimeout(this._delayedLoad);
this._delayedLoad=null;
}
_4=_4||{};
_5=_5||"get";
if(this.url){
dojo.xhr(_5,{handleAs:"html",load:dojo.hitch(this,this._load),error:dojo.hitch(this,this._error),url:this.url.build(dojo.delegate(this._urlParams,_4)),timeout:this.timeout});
}
},_load:function(_6,_7){
if(!_6.fragment){
try{
var l=dojo.global.location,_8=l.href,_9=l.hash;
dojo.global.location=_8.substring(0,_8.length-_9.length);
}
finally{
return;
}
}
var _a=_6.domNodes[0];
if(!_a){
console.error("a DOM node is required in the response");
}
this.destroyDescendants();
this._hidden=false;
_a.id=this.domNode.id;
dojo.place(_a,this.domNode,"replace");
this.domNode=_a;
this.containerNode=this.domNode;
dojo.forEach(_6.scriptNodes,function(_b){
try{
dojo.eval("(function(){var self=dijit.byId('"+this.id+"');"+_b+"})();");
}
catch(e){
console.error("error evaling script",e);
}
},this);
if(this.resize){
var _c,_d=this.id+"#ResizeHandle",_e=dojo.query("> .page-module-body",this.domNode)[0],_f=dojo.contentBox(_e),_10=dojo.create("div",null,this.domNode,"last"),_11=parseInt(Insight.getLocal(_d));
_c=new dojox.layout.ResizeHandle({resizeAxis:"y",targetContainer:_e,maxHeight:_f.h,maxWidth:_f.w,minWidth:_f.w,constrainMax:true,activeResize:!dojo.isIE},_10);
if(_11<_f.h){
dojo.style(_e,{height:_11});
}else{
if(isNaN(_11)&&this.maxHeight&&this.maxHeight<_f.h){
dojo.style(_e,{height:this.maxHeight});
}
}
this.connect(_c,"onResize",function(){
var _12=dojo.contentBox(_e);
Insight.setLocal(_d,(_12.h*1.1<_f.h)?_12.h:null);
});
this.subscribe("window/resize",function(){
var _13=dojo.contentBox(this.domNode).w-2;
_c.minSize.w=_c.minWidth=_c.maxSize.w=_c.maxWidth=_13;
dojo.style(_e,{width:_13});
});
}else{
if(this.maxHeight){
var _14=dojo.query(".page-module-body",this.domNode)[0];
if(parseInt(dojo.style(_14,"height"))>this.maxHeight){
dojo.style(_14,"display","block");
dojo.style(_14,"position","relative");
dojo.style(_14,"height",this.maxHeight+"px");
dojo.style(_14,"overflowX","hidden");
dojo.style(_14,"overflowY","auto");
}
}
}
this.onReplace();
},_error:function(_15,_16){
dojo.publish("error/xhr",arguments);
},updateUrlTemplate:function(_17){
this.url=_17;
},updateUrlParams:function(_18){
dojo.mixin(this._urlParams,_18);
},_setDomNodeAttr:function(_19){
this.domNode=_19;
this.onReplace();
},_urlArgs:function(){
},connectForm:function(_1a){
_1a=dojo.byId(_1a);
_1a.onsubmit=dojo.hitch(this,function(){
if(!_1a.action){
_1a.action=this.url.build(this._urlParams);
}
dojo.xhr(_1a.method||"post",{handleAs:"html",form:_1a,load:dojo.hitch(this,this._load),error:dojo.hitch(this,this._error),timeout:this.timeout});
this.onSubmit(_1a);
return false;
});
},onSubmit:function(){
},onReplace:function(){
}});
}
