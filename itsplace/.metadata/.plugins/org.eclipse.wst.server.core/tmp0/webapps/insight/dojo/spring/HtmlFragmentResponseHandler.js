/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["spring.HtmlFragmentResponseHandler"]){
dojo._hasResource["spring.HtmlFragmentResponseHandler"]=true;
dojo.provide("spring.HtmlFragmentResponseHandler");
dojo.require("dojo.string");
(function(){
dojo.declare("spring.HtmlFragmentResponseHandler",null,{evalScripts:true,replaceWidgets:true,requireFragment:true,mappings:null,constructor:function(_1){
dojo.mixin(this,_1);
this.mappings=this.mappings||{};
},handle:function(_2,_3){
if(_3.xhr.status>=300){
return this.error(_2,_3);
}else{
return this.load(_2,_3);
}
},handler:function(){
return dojo.hitch(this,this.handle);
},load:function(_4,_5){
if(this.requireFragment&&!_4.fragment){
try{
var l=dojo.global.location,_6=l.href,_7=l.hash;
dojo.global.location=_6.substring(0,_6.length-_7.length);
}
finally{
return;
}
}
this._replaceDomNodes(_4);
if(this.evalScripts){
this._evalScripts(_4);
}
return _4;
},error:function(_8,_9){
console.error("HTTP status code: ",_9.xhr.status);
},_replaceDomNodes:function(_a){
dojo.forEach(_a.domNodes,function(_b){
if(_b.id){
var _c=this._lookupTargetId(_b.id);
_b.id=_c;
if(this.replaceWidgets&&dijit&&dijit.byId(_c)){
this._replaceWidget(_c,_b);
}else{
if(dojo.byId(_c)){
this._replaceNode(_c,_b);
}else{
console.error("An existing DOM elment with id '"+_c+"' could not be found for replacement.");
}
}
}
},this);
},_lookupTargetId:function(id){
return this.mappings[id]?this.mappings[id]:id;
},_replaceWidget:function(id,_d){
var _e=dijit.byId(id);
_e.destroyDescendants(false);
dojo.place(_d,_e.attr("domNode"),"replace");
_e.attr("domNode",_d);
},_replaceNode:function(id,_f){
var _10=dojo.byId(id);
dojo.place(_f,_10,"replace");
},_evalScripts:function(_11){
dojo.forEach(_11.scriptNodes,function(_12){
dojo.eval(_12);
},this);
}});
var _13=/(?:<script(.|[\n|\r])*?>)((\n|\r|.)*?)(?:<\/script>)/img,_14=/(?:<script(.|[\n|\r])*?>)((\n|\r|.)*?)(?:<\/script>)/im,_15=[/<!--/mg,/\/\/-->/mg,/<!\[CDATA\[(\/\/>)*/mg,/(<!)*\]\]>/mg];
spring.HtmlFragmentResponseHandler.handle=function(){
if(arguments[1]&&arguments[1].xhr){
return new spring.HtmlFragmentResponseHandler().handle(arguments[0],arguments[1]);
}else{
return new spring.HtmlFragmentResponseHandler(arguments[0]).handler();
}
};
if(!dojo.contentHandlers.html){
dojo.contentHandlers.html=function(xhr){
var _16={raw:xhr.responseText,scriptNodes:[],domNodes:[],fragment:true},_17=dojo.string.trim(_16.raw),_18,_19;
if(_17.substring(0,14).toUpperCase()=="<!DOCTYPE HTML"||_17.substring(0,5).toUpperCase()=="<HTML"){
_16.fragment=false;
}
_18=_16.raw.match(_13);
dojo.forEach(_18,function(_1a){
if(_14.test(_1a)){
var _1b=_1a.match(_14)[2];
dojo.forEach(_15,function(_1c){
_1b=_1b.replace(_1c,"");
},this);
_16.scriptNodes.push(_1b);
}
},this);
_16.raw=_16.raw.replace(_13,"");
_19=dojo.doc.createElement("span");
_19.innerHTML=_16.raw;
_16.domNodes=dojo.query("> *",_19);
return _16;
};
}else{
console.warn("Unable to register spring.HtmlFragmentResponseHandler as 'html' for dojo.contentHandlers");
}
})();
}
