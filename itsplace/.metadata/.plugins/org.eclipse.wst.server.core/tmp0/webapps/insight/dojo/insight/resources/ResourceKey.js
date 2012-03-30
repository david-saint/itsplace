/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.resources.ResourceKey"]){
dojo._hasResource["insight.resources.ResourceKey"]=true;
dojo.provide("insight.resources.ResourceKey");
dojo.declare("insight.resources.ResourceKey",null,{_namespace:null,_attributes:null,constructor:function(_1){
this._attributes={};
if(dojo.isString(_1)){
this._parse(_1);
}else{
if(_1&&_1.declaredClass=="insight.resources.ResourceKey"){
this._namespace=_1._namespace;
this._attributes=dojo.mixin({},_1._attributes);
}
}
},_parse:function(_2){
_2=this._parseNamespace(_2);
this._parseAttributes(_2);
},_parseNamespace:function(_3){
var i=_3.indexOf(":");
this._namespace=_3.substring(0,i);
return _3.substring(i+1);
},_parseAttributes:function(_4){
var _5,_6,i;
while(_4.length>0){
i=_4.indexOf("=");
_5=_4.substring(0,i);
_4=_4.substring(i+1);
if(_4.charAt(0)==="\""){
i=_4.indexOf(",",_4.indexOf("\"",1));
}else{
i=_4.indexOf(",");
}
if(i===-1){
_6=_4;
_4="";
}else{
_6=_4.substring(0,i);
_4=_4.substring(i+1);
}
this._attributes[_5]=_6;
}
},getName:function(){
return this.getAttribute("name");
},setName:function(_7){
this.setAttribute("name",_7);
},getType:function(){
return this.getAttribute("type");
},setType:function(_8){
return this.setAttribute("type",_8);
},getAttributeNames:function(){
var _9=[];
for(var _a in this._attributes){
if(this._attributes.hasOwnProperty(_a)){
_9.push(_a);
}
}
return _9.sort();
},getAttribute:function(_b){
return this._attributes[_b];
},setAttribute:function(_c,_d){
if(_d){
this._attributes[_c]=_d;
}else{
delete this._attributes[_c];
}
},getNamespace:function(){
return this._namespace;
},setNamespace:function(_e){
this._namespace=_e;
},makeParentResourceKey:function(){
var _f=this.getType(),_10,_11;
if(!_f||_f.lastIndexOf(".")==-1){
return null;
}
_10=_f.substring(0,_f.lastIndexOf("."));
_11=new insight.resources.ResourceKey();
_11.setNamespace(this.getNamespace());
dojo.forEach(this.getAttributeNames(),function(_12){
if(_12=="type"){
_11.setAttribute("type",_10);
}else{
if(_12==_10){
_11.setAttribute("name",this.getAttribute(_10));
}else{
if(_12!="name"){
_11.setAttribute(_12,this.getAttribute(_12));
}
}
}
},this);
return _11;
},makeComponentKey:function(_13){
if(this.getType()==_13){
return this.clone();
}else{
if(!this.getAttribute(_13)){
return null;
}
}
var _14=new insight.resources.ResourceKey();
_14.setNamespace(this.getNamespace());
_14.setType(_13);
_14.setName(this.getType()==_13?this.getName():this.getAttribute(_13));
dojo.forEach(_13.split("."),function(_15){
if(_13!=_15){
_14.setAttribute(_15,this.getAttribute(_15));
}
},this);
return _14;
},makeComponentlessKey:function(_16){
var _17=this.getType().split("."),_18=new insight.resources.ResourceKey();
type="";
_18.setNamespace(this.getNamespace());
if(this.getType()==_16){
return null;
}else{
if(dojo.indexOf(_17,_16)==-1){
return this.clone();
}
}
_18.setName(this.getName());
dojo.forEach(_17,function(_19){
if(_16!=_19){
_18.setAttribute(_19,this.getAttribute(_19));
if(type.length>0){
type+=".";
}
type+=_19;
}
},this);
_18.setType(type);
if(type.indexOf(".")==-1){
_18.setName(this.getAttribute(type));
_18.setAttribute(type,null);
}
return _18;
},makeQueryKey:function(_1a){
var key=this.clone();
key.setAttribute(key.getType(),key.getName());
if(_1a){
key.setType(_1a);
}
key.setName(null);
return key;
},clone:function(){
return new insight.resources.ResourceKey(this.toString());
},merge:function(key){
key=dojo.isString(key)?new insight.resources.ResourceKey(key):key;
if(this.getNamespace()!=key.getNamespace()){
return;
}
var _1b=key.getType()=="Server"?this.clone():key,_1c=key.getType()=="Server"?key:this.clone(),_1d=new insight.resources.ResourceKey();
_1d.setNamespace(this.getNamespace());
if(!_1b.getName()&&!_1c.getName()){
return _1d;
}else{
if(!_1b.getName()){
return _1c;
}else{
if(!_1c.getName()){
return _1b;
}
}
}
if(_1b.getType()=="Application.EndPoint"){
_1d.setType("Application.Server.EndPoint");
_1d.setName(_1b.getName());
_1d.setAttribute("Application",_1b.getAttribute("Application"));
_1d.setAttribute("Server",_1c.getName());
}else{
_1d.setType("Application.Server");
_1d.setName(this.quote(this.unquote(_1b.getName())+"-"+this.unquote(_1c.getName())));
_1d.setAttribute("Application",_1b.getName());
_1d.setAttribute("Server",_1c.getName());
}
return _1d;
},unquote:function(_1e){
return _1e.substring(1,_1e.length-1);
},quote:function(_1f){
return "\""+_1f+"\"";
},toString:function(){
var _20="",_21,_22;
if(this._namespace){
_20+=this._namespace;
_20+=":";
}
_22=this.getAttributeNames();
for(_21 in _22){
_21=_22[_21];
if(_20.indexOf(":")+1!==_20.length){
_20+=",";
}
_20+=_21;
_20+="=";
_20+=this._attributes[_21];
}
return _20;
}});
}
