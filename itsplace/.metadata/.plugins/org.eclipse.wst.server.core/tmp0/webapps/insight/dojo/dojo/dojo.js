/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/

/*
	This is an optimized version of Dojo, built for deployment and not for
	development. To get sources and documentation, please visit:

		http://dojotoolkit.org
*/

(function(){
var _1=null;
if((_1||(typeof djConfig!="undefined"&&djConfig.scopeMap))&&(typeof window!="undefined")){
var _2="",_3="",_4="",_5={},_6={};
_1=_1||djConfig.scopeMap;
for(var i=0;i<_1.length;i++){
var _7=_1[i];
_2+="var "+_7[0]+" = {}; "+_7[1]+" = "+_7[0]+";"+_7[1]+"._scopeName = '"+_7[1]+"';";
_3+=(i==0?"":",")+_7[0];
_4+=(i==0?"":",")+_7[1];
_5[_7[0]]=_7[1];
_6[_7[1]]=_7[0];
}
eval(_2+"dojo._scopeArgs = ["+_4+"];");
dojo._scopePrefixArgs=_3;
dojo._scopePrefix="(function("+_3+"){";
dojo._scopeSuffix="})("+_4+")";
dojo._scopeMap=_5;
dojo._scopeMapRev=_6;
}
(function(){
if(typeof this["loadFirebugConsole"]=="function"){
this["loadFirebugConsole"]();
}else{
this.console=this.console||{};
var cn=["assert","count","debug","dir","dirxml","error","group","groupEnd","info","profile","profileEnd","time","timeEnd","trace","warn","log"];
var i=0,tn;
while((tn=cn[i++])){
if(!console[tn]){
(function(){
var _8=tn+"";
console[_8]=("log" in console)?function(){
var a=Array.apply({},arguments);
a.unshift(_8+":");
console["log"](a.join(" "));
}:function(){
};
console[_8]._fake=true;
})();
}
}
}
if(typeof dojo=="undefined"){
dojo={_scopeName:"dojo",_scopePrefix:"",_scopePrefixArgs:"",_scopeSuffix:"",_scopeMap:{},_scopeMapRev:{}};
}
var d=dojo;
if(typeof dijit=="undefined"){
dijit={_scopeName:"dijit"};
}
if(typeof dojox=="undefined"){
dojox={_scopeName:"dojox"};
}
if(!d._scopeArgs){
d._scopeArgs=[dojo,dijit,dojox];
}
d.global=this;
d.config={isDebug:false,debugAtAllCosts:false};
if(typeof djConfig!="undefined"){
for(var _9 in djConfig){
d.config[_9]=djConfig[_9];
}
}
dojo.locale=d.config.locale;
var _a="$Rev: 22487 $".match(/\d+/);
dojo.version={major:0,minor:0,patch:0,flag:"dev",revision:_a?+_a[0]:NaN,toString:function(){
with(d.version){
return major+"."+minor+"."+patch+flag+" ("+revision+")";
}
}};
if(typeof OpenAjax!="undefined"){
OpenAjax.hub.registerLibrary(dojo._scopeName,"http://dojotoolkit.org",d.version.toString());
}
var _b,_c,_d={};
for(var i in {toString:1}){
_b=[];
break;
}
dojo._extraNames=_b=_b||["hasOwnProperty","valueOf","isPrototypeOf","propertyIsEnumerable","toLocaleString","toString","constructor"];
_c=_b.length;
dojo._mixin=function(_e,_f){
var _10,s,i;
for(_10 in _f){
s=_f[_10];
if(!(_10 in _e)||(_e[_10]!==s&&(!(_10 in _d)||_d[_10]!==s))){
_e[_10]=s;
}
}
if(_c&&_f){
for(i=0;i<_c;++i){
_10=_b[i];
s=_f[_10];
if(!(_10 in _e)||(_e[_10]!==s&&(!(_10 in _d)||_d[_10]!==s))){
_e[_10]=s;
}
}
}
return _e;
};
dojo.mixin=function(obj,_11){
if(!obj){
obj={};
}
for(var i=1,l=arguments.length;i<l;i++){
d._mixin(obj,arguments[i]);
}
return obj;
};
dojo._getProp=function(_12,_13,_14){
var obj=_14||d.global;
for(var i=0,p;obj&&(p=_12[i]);i++){
if(i==0&&d._scopeMap[p]){
p=d._scopeMap[p];
}
obj=(p in obj?obj[p]:(_13?obj[p]={}:undefined));
}
return obj;
};
dojo.setObject=function(_15,_16,_17){
var _18=_15.split("."),p=_18.pop(),obj=d._getProp(_18,true,_17);
return obj&&p?(obj[p]=_16):undefined;
};
dojo.getObject=function(_19,_1a,_1b){
return d._getProp(_19.split("."),_1a,_1b);
};
dojo.exists=function(_1c,obj){
return !!d.getObject(_1c,false,obj);
};
dojo["eval"]=function(_1d){
return d.global.eval?d.global.eval(_1d):eval(_1d);
};
d.deprecated=d.experimental=function(){
};
})();
(function(){
var d=dojo;
d.mixin(d,{_loadedModules:{},_inFlightCount:0,_hasResource:{},_modulePrefixes:{dojo:{name:"dojo",value:"."},doh:{name:"doh",value:"../util/doh"},tests:{name:"tests",value:"tests"}},_moduleHasPrefix:function(_1e){
var mp=d._modulePrefixes;
return !!(mp[_1e]&&mp[_1e].value);
},_getModulePrefix:function(_1f){
var mp=d._modulePrefixes;
if(d._moduleHasPrefix(_1f)){
return mp[_1f].value;
}
return _1f;
},_loadedUrls:[],_postLoad:false,_loaders:[],_unloaders:[],_loadNotifying:false});
dojo._loadPath=function(_20,_21,cb){
var uri=((_20.charAt(0)=="/"||_20.match(/^\w+:/))?"":d.baseUrl)+_20;
try{
return !_21?d._loadUri(uri,cb):d._loadUriAndCheck(uri,_21,cb);
}
catch(e){
console.error(e);
return false;
}
};
dojo._loadUri=function(uri,cb){
if(d._loadedUrls[uri]){
return true;
}
d._inFlightCount++;
var _22=d._getText(uri,true);
if(_22){
d._loadedUrls[uri]=true;
d._loadedUrls.push(uri);
if(cb){
_22="("+_22+")";
}else{
_22=d._scopePrefix+_22+d._scopeSuffix;
}
if(!d.isIE){
_22+="\r\n//@ sourceURL="+uri;
}
var _23=d["eval"](_22);
if(cb){
cb(_23);
}
}
if(--d._inFlightCount==0&&d._postLoad&&d._loaders.length){
setTimeout(function(){
if(d._inFlightCount==0){
d._callLoaded();
}
},0);
}
return !!_22;
};
dojo._loadUriAndCheck=function(uri,_24,cb){
var ok=false;
try{
ok=d._loadUri(uri,cb);
}
catch(e){
console.error("failed loading "+uri+" with error: "+e);
}
return !!(ok&&d._loadedModules[_24]);
};
dojo.loaded=function(){
d._loadNotifying=true;
d._postLoad=true;
var mll=d._loaders;
d._loaders=[];
for(var x=0;x<mll.length;x++){
mll[x]();
}
d._loadNotifying=false;
if(d._postLoad&&d._inFlightCount==0&&mll.length){
d._callLoaded();
}
};
dojo.unloaded=function(){
var mll=d._unloaders;
while(mll.length){
(mll.pop())();
}
};
d._onto=function(arr,obj,fn){
if(!fn){
arr.push(obj);
}else{
if(fn){
var _25=(typeof fn=="string")?obj[fn]:fn;
arr.push(function(){
_25.call(obj);
});
}
}
};
dojo.ready=dojo.addOnLoad=function(obj,_26){
d._onto(d._loaders,obj,_26);
if(d._postLoad&&d._inFlightCount==0&&!d._loadNotifying){
d._callLoaded();
}
};
var dca=d.config.addOnLoad;
if(dca){
d.addOnLoad[(dca instanceof Array?"apply":"call")](d,dca);
}
dojo._modulesLoaded=function(){
if(d._postLoad){
return;
}
if(d._inFlightCount>0){
console.warn("files still in flight!");
return;
}
d._callLoaded();
};
dojo._callLoaded=function(){
if(typeof setTimeout=="object"||(d.config.useXDomain&&d.isOpera)){
setTimeout(d.isAIR?function(){
d.loaded();
}:d._scopeName+".loaded();",0);
}else{
d.loaded();
}
};
dojo._getModuleSymbols=function(_27){
var _28=_27.split(".");
for(var i=_28.length;i>0;i--){
var _29=_28.slice(0,i).join(".");
if(i==1&&!d._moduleHasPrefix(_29)){
_28[0]="../"+_28[0];
}else{
var _2a=d._getModulePrefix(_29);
if(_2a!=_29){
_28.splice(0,i,_2a);
break;
}
}
}
return _28;
};
dojo._global_omit_module_check=false;
dojo.loadInit=function(_2b){
_2b();
};
dojo._loadModule=dojo.require=function(_2c,_2d){
_2d=d._global_omit_module_check||_2d;
var _2e=d._loadedModules[_2c];
if(_2e){
return _2e;
}
var _2f=d._getModuleSymbols(_2c).join("/")+".js";
var _30=!_2d?_2c:null;
var ok=d._loadPath(_2f,_30);
if(!ok&&!_2d){
throw new Error("Could not load '"+_2c+"'; last tried '"+_2f+"'");
}
if(!_2d&&!d._isXDomain){
_2e=d._loadedModules[_2c];
if(!_2e){
throw new Error("symbol '"+_2c+"' is not defined after loading '"+_2f+"'");
}
}
return _2e;
};
dojo.provide=function(_31){
_31=_31+"";
return (d._loadedModules[_31]=d.getObject(_31,true));
};
dojo.platformRequire=function(_32){
var _33=_32.common||[];
var _34=_33.concat(_32[d._name]||_32["default"]||[]);
for(var x=0;x<_34.length;x++){
var _35=_34[x];
if(_35.constructor==Array){
d._loadModule.apply(d,_35);
}else{
d._loadModule(_35);
}
}
};
dojo.requireIf=function(_36,_37){
if(_36===true){
var _38=[];
for(var i=1;i<arguments.length;i++){
_38.push(arguments[i]);
}
d.require.apply(d,_38);
}
};
dojo.requireAfterIf=d.requireIf;
dojo.registerModulePath=function(_39,_3a){
d._modulePrefixes[_39]={name:_39,value:_3a};
};
dojo.requireLocalization=function(_3b,_3c,_3d,_3e){
d.require("dojo.i18n");
d.i18n._requireLocalization.apply(d.hostenv,arguments);
};
var ore=new RegExp("^(([^:/?#]+):)?(//([^/?#]*))?([^?#]*)(\\?([^#]*))?(#(.*))?$"),ire=new RegExp("^((([^\\[:]+):)?([^@]+)@)?(\\[([^\\]]+)\\]|([^\\[:]*))(:([0-9]+))?$");
dojo._Url=function(){
var n=null,_3f=arguments,uri=[_3f[0]];
for(var i=1;i<_3f.length;i++){
if(!_3f[i]){
continue;
}
var _40=new d._Url(_3f[i]+""),_41=new d._Url(uri[0]+"");
if(_40.path==""&&!_40.scheme&&!_40.authority&&!_40.query){
if(_40.fragment!=n){
_41.fragment=_40.fragment;
}
_40=_41;
}else{
if(!_40.scheme){
_40.scheme=_41.scheme;
if(!_40.authority){
_40.authority=_41.authority;
if(_40.path.charAt(0)!="/"){
var _42=_41.path.substring(0,_41.path.lastIndexOf("/")+1)+_40.path;
var _43=_42.split("/");
for(var j=0;j<_43.length;j++){
if(_43[j]=="."){
if(j==_43.length-1){
_43[j]="";
}else{
_43.splice(j,1);
j--;
}
}else{
if(j>0&&!(j==1&&_43[0]=="")&&_43[j]==".."&&_43[j-1]!=".."){
if(j==(_43.length-1)){
_43.splice(j,1);
_43[j-1]="";
}else{
_43.splice(j-1,2);
j-=2;
}
}
}
}
_40.path=_43.join("/");
}
}
}
}
uri=[];
if(_40.scheme){
uri.push(_40.scheme,":");
}
if(_40.authority){
uri.push("//",_40.authority);
}
uri.push(_40.path);
if(_40.query){
uri.push("?",_40.query);
}
if(_40.fragment){
uri.push("#",_40.fragment);
}
}
this.uri=uri.join("");
var r=this.uri.match(ore);
this.scheme=r[2]||(r[1]?"":n);
this.authority=r[4]||(r[3]?"":n);
this.path=r[5];
this.query=r[7]||(r[6]?"":n);
this.fragment=r[9]||(r[8]?"":n);
if(this.authority!=n){
r=this.authority.match(ire);
this.user=r[3]||n;
this.password=r[4]||n;
this.host=r[6]||r[7];
this.port=r[9]||n;
}
};
dojo._Url.prototype.toString=function(){
return this.uri;
};
dojo.moduleUrl=function(_44,url){
var loc=d._getModuleSymbols(_44).join("/");
if(!loc){
return null;
}
if(loc.lastIndexOf("/")!=loc.length-1){
loc+="/";
}
var _45=loc.indexOf(":");
if(loc.charAt(0)!="/"&&(_45==-1||_45>loc.indexOf("/"))){
loc=d.baseUrl+loc;
}
return new d._Url(loc,url);
};
})();
if(typeof window!="undefined"){
dojo.isBrowser=true;
dojo._name="browser";
(function(){
var d=dojo;
if(document&&document.getElementsByTagName){
var _46=document.getElementsByTagName("script");
var _47=/dojo(\.xd)?\.js(\W|$)/i;
for(var i=0;i<_46.length;i++){
var src=_46[i].getAttribute("src");
if(!src){
continue;
}
var m=src.match(_47);
if(m){
if(!d.config.baseUrl){
d.config.baseUrl=src.substring(0,m.index);
}
var cfg=_46[i].getAttribute("djConfig");
if(cfg){
var _48=eval("({ "+cfg+" })");
for(var x in _48){
dojo.config[x]=_48[x];
}
}
break;
}
}
}
d.baseUrl=d.config.baseUrl;
var n=navigator;
var dua=n.userAgent,dav=n.appVersion,tv=parseFloat(dav);
if(dua.indexOf("Opera")>=0){
d.isOpera=tv;
}
if(dua.indexOf("AdobeAIR")>=0){
d.isAIR=1;
}
d.isKhtml=(dav.indexOf("Konqueror")>=0)?tv:0;
d.isWebKit=parseFloat(dua.split("WebKit/")[1])||undefined;
d.isChrome=parseFloat(dua.split("Chrome/")[1])||undefined;
d.isMac=dav.indexOf("Macintosh")>=0;
var _49=Math.max(dav.indexOf("WebKit"),dav.indexOf("Safari"),0);
if(_49&&!dojo.isChrome){
d.isSafari=parseFloat(dav.split("Version/")[1]);
if(!d.isSafari||parseFloat(dav.substr(_49+7))<=419.3){
d.isSafari=2;
}
}
if(dua.indexOf("Gecko")>=0&&!d.isKhtml&&!d.isWebKit){
d.isMozilla=d.isMoz=tv;
}
if(d.isMoz){
d.isFF=parseFloat(dua.split("Firefox/")[1]||dua.split("Minefield/")[1])||undefined;
}
if(document.all&&!d.isOpera){
d.isIE=parseFloat(dav.split("MSIE ")[1])||undefined;
var _4a=document.documentMode;
if(_4a&&_4a!=5&&Math.floor(d.isIE)!=_4a){
d.isIE=_4a;
}
}
if(dojo.isIE&&window.location.protocol==="file:"){
dojo.config.ieForceActiveXXhr=true;
}
d.isQuirks=document.compatMode=="BackCompat";
d.locale=dojo.config.locale||(d.isIE?n.userLanguage:n.language).toLowerCase();
d._XMLHTTP_PROGIDS=["Msxml2.XMLHTTP","Microsoft.XMLHTTP","Msxml2.XMLHTTP.4.0"];
d._xhrObj=function(){
var _4b,_4c;
if(!dojo.isIE||!dojo.config.ieForceActiveXXhr){
try{
_4b=new XMLHttpRequest();
}
catch(e){
}
}
if(!_4b){
for(var i=0;i<3;++i){
var _4d=d._XMLHTTP_PROGIDS[i];
try{
_4b=new ActiveXObject(_4d);
}
catch(e){
_4c=e;
}
if(_4b){
d._XMLHTTP_PROGIDS=[_4d];
break;
}
}
}
if(!_4b){
throw new Error("XMLHTTP not available: "+_4c);
}
return _4b;
};
d._isDocumentOk=function(_4e){
var _4f=_4e.status||0,lp=location.protocol;
return (_4f>=200&&_4f<300)||_4f==304||_4f==1223||(!_4f&&(lp=="file:"||lp=="chrome:"||lp=="chrome-extension:"||lp=="app:"));
};
var _50=window.location+"";
var _51=document.getElementsByTagName("base");
var _52=(_51&&_51.length>0);
d._getText=function(uri,_53){
var _54=d._xhrObj();
if(!_52&&dojo._Url){
uri=(new dojo._Url(_50,uri)).toString();
}
if(d.config.cacheBust){
uri+="";
uri+=(uri.indexOf("?")==-1?"?":"&")+String(d.config.cacheBust).replace(/\W+/g,"");
}
_54.open("GET",uri,false);
try{
_54.send(null);
if(!d._isDocumentOk(_54)){
var err=Error("Unable to load "+uri+" status:"+_54.status);
err.status=_54.status;
err.responseText=_54.responseText;
throw err;
}
}
catch(e){
if(_53){
return null;
}
throw e;
}
return _54.responseText;
};
var _55=window;
var _56=function(_57,fp){
var _58=_55.attachEvent||_55.addEventListener;
_57=_55.attachEvent?_57:_57.substring(2);
_58(_57,function(){
fp.apply(_55,arguments);
},false);
};
d._windowUnloaders=[];
d.windowUnloaded=function(){
var mll=d._windowUnloaders;
while(mll.length){
(mll.pop())();
}
d=null;
};
var _59=0;
d.addOnWindowUnload=function(obj,_5a){
d._onto(d._windowUnloaders,obj,_5a);
if(!_59){
_59=1;
_56("onunload",d.windowUnloaded);
}
};
var _5b=0;
d.addOnUnload=function(obj,_5c){
d._onto(d._unloaders,obj,_5c);
if(!_5b){
_5b=1;
_56("onbeforeunload",dojo.unloaded);
}
};
})();
dojo._initFired=false;
dojo._loadInit=function(e){
if(dojo._scrollIntervalId){
clearInterval(dojo._scrollIntervalId);
dojo._scrollIntervalId=0;
}
if(!dojo._initFired){
dojo._initFired=true;
if(!dojo.config.afterOnLoad&&window.detachEvent){
window.detachEvent("onload",dojo._loadInit);
}
if(dojo._inFlightCount==0){
dojo._modulesLoaded();
}
}
};
if(!dojo.config.afterOnLoad){
if(document.addEventListener){
document.addEventListener("DOMContentLoaded",dojo._loadInit,false);
window.addEventListener("load",dojo._loadInit,false);
}else{
if(window.attachEvent){
window.attachEvent("onload",dojo._loadInit);
if(!dojo.config.skipIeDomLoaded&&self===self.top){
dojo._scrollIntervalId=setInterval(function(){
try{
if(document.body){
document.documentElement.doScroll("left");
dojo._loadInit();
}
}
catch(e){
}
},30);
}
}
}
}
if(dojo.isIE){
try{
(function(){
document.namespaces.add("v","urn:schemas-microsoft-com:vml");
var _5d=["*","group","roundrect","oval","shape","rect","imagedata","path","textpath","text"],i=0,l=1,s=document.createStyleSheet();
if(dojo.isIE>=8){
i=1;
l=_5d.length;
}
for(;i<l;++i){
s.addRule("v\\:"+_5d[i],"behavior:url(#default#VML); display:inline-block");
}
})();
}
catch(e){
}
}
}
(function(){
var mp=dojo.config["modulePaths"];
if(mp){
for(var _5e in mp){
dojo.registerModulePath(_5e,mp[_5e]);
}
}
})();
if(dojo.config.isDebug){
dojo.require("dojo._firebug.firebug");
}
if(dojo.config.debugAtAllCosts){
dojo.config.useXDomain=true;
dojo.require("dojo._base._loader.loader_xd");
dojo.require("dojo._base._loader.loader_debug");
}
if(!dojo._hasResource["dojo._base.lang"]){
dojo._hasResource["dojo._base.lang"]=true;
dojo.provide("dojo._base.lang");
(function(){
var d=dojo,_5f=Object.prototype.toString;
dojo.isString=function(it){
return (typeof it=="string"||it instanceof String);
};
dojo.isArray=function(it){
return it&&(it instanceof Array||typeof it=="array");
};
dojo.isFunction=function(it){
return _5f.call(it)==="[object Function]";
};
dojo.isObject=function(it){
return it!==undefined&&(it===null||typeof it=="object"||d.isArray(it)||d.isFunction(it));
};
dojo.isArrayLike=function(it){
return it&&it!==undefined&&!d.isString(it)&&!d.isFunction(it)&&!(it.tagName&&it.tagName.toLowerCase()=="form")&&(d.isArray(it)||isFinite(it.length));
};
dojo.isAlien=function(it){
return it&&!d.isFunction(it)&&/\{\s*\[native code\]\s*\}/.test(String(it));
};
dojo.extend=function(_60,_61){
for(var i=1,l=arguments.length;i<l;i++){
d._mixin(_60.prototype,arguments[i]);
}
return _60;
};
dojo._hitchArgs=function(_62,_63){
var pre=d._toArray(arguments,2);
var _64=d.isString(_63);
return function(){
var _65=d._toArray(arguments);
var f=_64?(_62||d.global)[_63]:_63;
return f&&f.apply(_62||this,pre.concat(_65));
};
};
dojo.hitch=function(_66,_67){
if(arguments.length>2){
return d._hitchArgs.apply(d,arguments);
}
if(!_67){
_67=_66;
_66=null;
}
if(d.isString(_67)){
_66=_66||d.global;
if(!_66[_67]){
throw (["dojo.hitch: scope[\"",_67,"\"] is null (scope=\"",_66,"\")"].join(""));
}
return function(){
return _66[_67].apply(_66,arguments||[]);
};
}
return !_66?_67:function(){
return _67.apply(_66,arguments||[]);
};
};
dojo.delegate=dojo._delegate=(function(){
function TMP(){
};
return function(obj,_68){
TMP.prototype=obj;
var tmp=new TMP();
TMP.prototype=null;
if(_68){
d._mixin(tmp,_68);
}
return tmp;
};
})();
var _69=function(obj,_6a,_6b){
return (_6b||[]).concat(Array.prototype.slice.call(obj,_6a||0));
};
var _6c=function(obj,_6d,_6e){
var arr=_6e||[];
for(var x=_6d||0;x<obj.length;x++){
arr.push(obj[x]);
}
return arr;
};
dojo._toArray=d.isIE?function(obj){
return ((obj.item)?_6c:_69).apply(this,arguments);
}:_69;
dojo.partial=function(_6f){
var arr=[null];
return d.hitch.apply(d,arr.concat(d._toArray(arguments)));
};
var _70=d._extraNames,_71=_70.length,_72={};
dojo.clone=function(o){
if(!o||typeof o!="object"||d.isFunction(o)){
return o;
}
if(o.nodeType&&"cloneNode" in o){
return o.cloneNode(true);
}
if(o instanceof Date){
return new Date(o.getTime());
}
var r,i,l,s,_73;
if(d.isArray(o)){
r=[];
for(i=0,l=o.length;i<l;++i){
if(i in o){
r.push(d.clone(o[i]));
}
}
}else{
r=o.constructor?new o.constructor():{};
}
for(_73 in o){
s=o[_73];
if(!(_73 in r)||(r[_73]!==s&&(!(_73 in _72)||_72[_73]!==s))){
r[_73]=d.clone(s);
}
}
if(_71){
for(i=0;i<_71;++i){
_73=_70[i];
s=o[_73];
if(!(_73 in r)||(r[_73]!==s&&(!(_73 in _72)||_72[_73]!==s))){
r[_73]=s;
}
}
}
return r;
};
dojo.trim=String.prototype.trim?function(str){
return str.trim();
}:function(str){
return str.replace(/^\s\s*/,"").replace(/\s\s*$/,"");
};
var _74=/\{([^\}]+)\}/g;
dojo.replace=function(_75,map,_76){
return _75.replace(_76||_74,d.isFunction(map)?map:function(_77,k){
return d.getObject(k,false,map);
});
};
})();
}
if(!dojo._hasResource["dojo._base.array"]){
dojo._hasResource["dojo._base.array"]=true;
dojo.provide("dojo._base.array");
(function(){
var _78=function(arr,obj,cb){
return [(typeof arr=="string")?arr.split(""):arr,obj||dojo.global,(typeof cb=="string")?new Function("item","index","array",cb):cb];
};
var _79=function(_7a,arr,_7b,_7c){
var _7d=_78(arr,_7c,_7b);
arr=_7d[0];
for(var i=0,l=arr.length;i<l;++i){
var _7e=!!_7d[2].call(_7d[1],arr[i],i,arr);
if(_7a^_7e){
return _7e;
}
}
return _7a;
};
dojo.mixin(dojo,{indexOf:function(_7f,_80,_81,_82){
var _83=1,end=_7f.length||0,i=0;
if(_82){
i=end-1;
_83=end=-1;
}
if(_81!=undefined){
i=_81;
}
if((_82&&i>end)||i<end){
for(;i!=end;i+=_83){
if(_7f[i]==_80){
return i;
}
}
}
return -1;
},lastIndexOf:function(_84,_85,_86){
return dojo.indexOf(_84,_85,_86,true);
},forEach:function(arr,_87,_88){
if(!arr||!arr.length){
return;
}
var _89=_78(arr,_88,_87);
arr=_89[0];
for(var i=0,l=arr.length;i<l;++i){
_89[2].call(_89[1],arr[i],i,arr);
}
},every:function(arr,_8a,_8b){
return _79(true,arr,_8a,_8b);
},some:function(arr,_8c,_8d){
return _79(false,arr,_8c,_8d);
},map:function(arr,_8e,_8f){
var _90=_78(arr,_8f,_8e);
arr=_90[0];
var _91=(arguments[3]?(new arguments[3]()):[]);
for(var i=0,l=arr.length;i<l;++i){
_91.push(_90[2].call(_90[1],arr[i],i,arr));
}
return _91;
},filter:function(arr,_92,_93){
var _94=_78(arr,_93,_92);
arr=_94[0];
var _95=[];
for(var i=0,l=arr.length;i<l;++i){
if(_94[2].call(_94[1],arr[i],i,arr)){
_95.push(arr[i]);
}
}
return _95;
}});
})();
}
if(!dojo._hasResource["dojo._base.declare"]){
dojo._hasResource["dojo._base.declare"]=true;
dojo.provide("dojo._base.declare");
(function(){
var d=dojo,mix=d._mixin,op=Object.prototype,_96=op.toString,_97=new Function,_98=0,_99="constructor";
function err(msg){
throw new Error("declare: "+msg);
};
function _9a(_9b){
var _9c=[],_9d=[{cls:0,refs:[]}],_9e={},_9f=1,l=_9b.length,i=0,j,lin,_a0,top,_a1,rec,_a2,_a3;
for(;i<l;++i){
_a0=_9b[i];
if(!_a0){
err("mixin #"+i+" is unknown. Did you use dojo.require to pull it in?");
}else{
if(_96.call(_a0)!="[object Function]"){
err("mixin #"+i+" is not a callable constructor.");
}
}
lin=_a0._meta?_a0._meta.bases:[_a0];
top=0;
for(j=lin.length-1;j>=0;--j){
_a1=lin[j].prototype;
if(!_a1.hasOwnProperty("declaredClass")){
_a1.declaredClass="uniqName_"+(_98++);
}
_a2=_a1.declaredClass;
if(!_9e.hasOwnProperty(_a2)){
_9e[_a2]={count:0,refs:[],cls:lin[j]};
++_9f;
}
rec=_9e[_a2];
if(top&&top!==rec){
rec.refs.push(top);
++top.count;
}
top=rec;
}
++top.count;
_9d[0].refs.push(top);
}
while(_9d.length){
top=_9d.pop();
_9c.push(top.cls);
--_9f;
while(_a3=top.refs,_a3.length==1){
top=_a3[0];
if(!top||--top.count){
top=0;
break;
}
_9c.push(top.cls);
--_9f;
}
if(top){
for(i=0,l=_a3.length;i<l;++i){
top=_a3[i];
if(!--top.count){
_9d.push(top);
}
}
}
}
if(_9f){
err("can't build consistent linearization");
}
_a0=_9b[0];
_9c[0]=_a0?_a0._meta&&_a0===_9c[_9c.length-_a0._meta.bases.length]?_a0._meta.bases.length:1:0;
return _9c;
};
function _a4(_a5,a,f){
var _a6,_a7,_a8,_a9,_aa,_ab,_ac,opf,pos,_ad=this._inherited=this._inherited||{};
if(typeof _a5=="string"){
_a6=_a5;
_a5=a;
a=f;
}
f=0;
_a9=_a5.callee;
_a6=_a6||_a9.nom;
if(!_a6){
err("can't deduce a name to call inherited()");
}
_aa=this.constructor._meta;
_a8=_aa.bases;
pos=_ad.p;
if(_a6!=_99){
if(_ad.c!==_a9){
pos=0;
_ab=_a8[0];
_aa=_ab._meta;
if(_aa.hidden[_a6]!==_a9){
_a7=_aa.chains;
if(_a7&&typeof _a7[_a6]=="string"){
err("calling chained method with inherited: "+_a6);
}
do{
_aa=_ab._meta;
_ac=_ab.prototype;
if(_aa&&(_ac[_a6]===_a9&&_ac.hasOwnProperty(_a6)||_aa.hidden[_a6]===_a9)){
break;
}
}while(_ab=_a8[++pos]);
pos=_ab?pos:-1;
}
}
_ab=_a8[++pos];
if(_ab){
_ac=_ab.prototype;
if(_ab._meta&&_ac.hasOwnProperty(_a6)){
f=_ac[_a6];
}else{
opf=op[_a6];
do{
_ac=_ab.prototype;
f=_ac[_a6];
if(f&&(_ab._meta?_ac.hasOwnProperty(_a6):f!==opf)){
break;
}
}while(_ab=_a8[++pos]);
}
}
f=_ab&&f||op[_a6];
}else{
if(_ad.c!==_a9){
pos=0;
_aa=_a8[0]._meta;
if(_aa&&_aa.ctor!==_a9){
_a7=_aa.chains;
if(!_a7||_a7.constructor!=="manual"){
err("calling chained constructor with inherited");
}
while(_ab=_a8[++pos]){
_aa=_ab._meta;
if(_aa&&_aa.ctor===_a9){
break;
}
}
pos=_ab?pos:-1;
}
}
while(_ab=_a8[++pos]){
_aa=_ab._meta;
f=_aa?_aa.ctor:_ab;
if(f){
break;
}
}
f=_ab&&f;
}
_ad.c=f;
_ad.p=pos;
if(f){
return a===true?f:f.apply(this,a||_a5);
}
};
function _ae(_af,_b0){
if(typeof _af=="string"){
return this.inherited(_af,_b0,true);
}
return this.inherited(_af,true);
};
function _b1(cls){
var _b2=this.constructor._meta.bases;
for(var i=0,l=_b2.length;i<l;++i){
if(_b2[i]===cls){
return true;
}
}
return this instanceof cls;
};
function _b3(_b4,_b5){
var _b6,i=0,l=d._extraNames.length;
for(_b6 in _b5){
if(_b6!=_99&&_b5.hasOwnProperty(_b6)){
_b4[_b6]=_b5[_b6];
}
}
for(;i<l;++i){
_b6=d._extraNames[i];
if(_b6!=_99&&_b5.hasOwnProperty(_b6)){
_b4[_b6]=_b5[_b6];
}
}
};
function _b7(_b8,_b9){
var _ba,t,i=0,l=d._extraNames.length;
for(_ba in _b9){
t=_b9[_ba];
if((t!==op[_ba]||!(_ba in op))&&_ba!=_99){
if(_96.call(t)=="[object Function]"){
t.nom=_ba;
}
_b8[_ba]=t;
}
}
for(;i<l;++i){
_ba=d._extraNames[i];
t=_b9[_ba];
if((t!==op[_ba]||!(_ba in op))&&_ba!=_99){
if(_96.call(t)=="[object Function]"){
t.nom=_ba;
}
_b8[_ba]=t;
}
}
return _b8;
};
function _bb(_bc){
_b7(this.prototype,_bc);
return this;
};
function _bd(_be,_bf){
return function(){
var a=arguments,_c0=a,a0=a[0],f,i,m,l=_be.length,_c1;
if(!(this instanceof a.callee)){
return _c2(a);
}
if(_bf&&(a0&&a0.preamble||this.preamble)){
_c1=new Array(_be.length);
_c1[0]=a;
for(i=0;;){
a0=a[0];
if(a0){
f=a0.preamble;
if(f){
a=f.apply(this,a)||a;
}
}
f=_be[i].prototype;
f=f.hasOwnProperty("preamble")&&f.preamble;
if(f){
a=f.apply(this,a)||a;
}
if(++i==l){
break;
}
_c1[i]=a;
}
}
for(i=l-1;i>=0;--i){
f=_be[i];
m=f._meta;
f=m?m.ctor:f;
if(f){
f.apply(this,_c1?_c1[i]:a);
}
}
f=this.postscript;
if(f){
f.apply(this,_c0);
}
};
};
function _c3(_c4,_c5){
return function(){
var a=arguments,t=a,a0=a[0],f;
if(!(this instanceof a.callee)){
return _c2(a);
}
if(_c5){
if(a0){
f=a0.preamble;
if(f){
t=f.apply(this,t)||t;
}
}
f=this.preamble;
if(f){
f.apply(this,t);
}
}
if(_c4){
_c4.apply(this,a);
}
f=this.postscript;
if(f){
f.apply(this,a);
}
};
};
function _c6(_c7){
return function(){
var a=arguments,i=0,f,m;
if(!(this instanceof a.callee)){
return _c2(a);
}
for(;f=_c7[i];++i){
m=f._meta;
f=m?m.ctor:f;
if(f){
f.apply(this,a);
break;
}
}
f=this.postscript;
if(f){
f.apply(this,a);
}
};
};
function _c8(_c9,_ca,_cb){
return function(){
var b,m,f,i=0,_cc=1;
if(_cb){
i=_ca.length-1;
_cc=-1;
}
for(;b=_ca[i];i+=_cc){
m=b._meta;
f=(m?m.hidden:b.prototype)[_c9];
if(f){
f.apply(this,arguments);
}
}
};
};
function _cd(_ce){
_97.prototype=_ce.prototype;
var t=new _97;
_97.prototype=null;
return t;
};
function _c2(_cf){
var _d0=_cf.callee,t=_cd(_d0);
_d0.apply(t,_cf);
return t;
};
d.declare=function(_d1,_d2,_d3){
if(typeof _d1!="string"){
_d3=_d2;
_d2=_d1;
_d1="";
}
_d3=_d3||{};
var _d4,i,t,_d5,_d6,_d7,_d8,_d9=1,_da=_d2;
if(_96.call(_d2)=="[object Array]"){
_d7=_9a(_d2);
t=_d7[0];
_d9=_d7.length-t;
_d2=_d7[_d9];
}else{
_d7=[0];
if(_d2){
if(_96.call(_d2)=="[object Function]"){
t=_d2._meta;
_d7=_d7.concat(t?t.bases:_d2);
}else{
err("base class is not a callable constructor.");
}
}else{
if(_d2!==null){
err("unknown base class. Did you use dojo.require to pull it in?");
}
}
}
if(_d2){
for(i=_d9-1;;--i){
_d4=_cd(_d2);
if(!i){
break;
}
t=_d7[i];
(t._meta?_b3:mix)(_d4,t.prototype);
_d5=new Function;
_d5.superclass=_d2;
_d5.prototype=_d4;
_d2=_d4.constructor=_d5;
}
}else{
_d4={};
}
_b7(_d4,_d3);
t=_d3.constructor;
if(t!==op.constructor){
t.nom=_99;
_d4.constructor=t;
}
for(i=_d9-1;i;--i){
t=_d7[i]._meta;
if(t&&t.chains){
_d8=mix(_d8||{},t.chains);
}
}
if(_d4["-chains-"]){
_d8=mix(_d8||{},_d4["-chains-"]);
}
t=!_d8||!_d8.hasOwnProperty(_99);
_d7[0]=_d5=(_d8&&_d8.constructor==="manual")?_c6(_d7):(_d7.length==1?_c3(_d3.constructor,t):_bd(_d7,t));
_d5._meta={bases:_d7,hidden:_d3,chains:_d8,parents:_da,ctor:_d3.constructor};
_d5.superclass=_d2&&_d2.prototype;
_d5.extend=_bb;
_d5.prototype=_d4;
_d4.constructor=_d5;
_d4.getInherited=_ae;
_d4.inherited=_a4;
_d4.isInstanceOf=_b1;
if(_d1){
_d4.declaredClass=_d1;
d.setObject(_d1,_d5);
}
if(_d8){
for(_d6 in _d8){
if(_d4[_d6]&&typeof _d8[_d6]=="string"&&_d6!=_99){
t=_d4[_d6]=_c8(_d6,_d7,_d8[_d6]==="after");
t.nom=_d6;
}
}
}
return _d5;
};
d.safeMixin=_b7;
})();
}
if(!dojo._hasResource["dojo._base.connect"]){
dojo._hasResource["dojo._base.connect"]=true;
dojo.provide("dojo._base.connect");
dojo._listener={getDispatcher:function(){
return function(){
var ap=Array.prototype,c=arguments.callee,ls=c._listeners,t=c.target;
var r=t&&t.apply(this,arguments);
var i,lls;
lls=[].concat(ls);
for(i in lls){
if(!(i in ap)){
lls[i].apply(this,arguments);
}
}
return r;
};
},add:function(_db,_dc,_dd){
_db=_db||dojo.global;
var f=_db[_dc];
if(!f||!f._listeners){
var d=dojo._listener.getDispatcher();
d.target=f;
d._listeners=[];
f=_db[_dc]=d;
}
return f._listeners.push(_dd);
},remove:function(_de,_df,_e0){
var f=(_de||dojo.global)[_df];
if(f&&f._listeners&&_e0--){
delete f._listeners[_e0];
}
}};
dojo.connect=function(obj,_e1,_e2,_e3,_e4){
var a=arguments,_e5=[],i=0;
_e5.push(dojo.isString(a[0])?null:a[i++],a[i++]);
var a1=a[i+1];
_e5.push(dojo.isString(a1)||dojo.isFunction(a1)?a[i++]:null,a[i++]);
for(var l=a.length;i<l;i++){
_e5.push(a[i]);
}
return dojo._connect.apply(this,_e5);
};
dojo._connect=function(obj,_e6,_e7,_e8){
var l=dojo._listener,h=l.add(obj,_e6,dojo.hitch(_e7,_e8));
return [obj,_e6,h,l];
};
dojo.disconnect=function(_e9){
if(_e9&&_e9[0]!==undefined){
dojo._disconnect.apply(this,_e9);
delete _e9[0];
}
};
dojo._disconnect=function(obj,_ea,_eb,_ec){
_ec.remove(obj,_ea,_eb);
};
dojo._topics={};
dojo.subscribe=function(_ed,_ee,_ef){
return [_ed,dojo._listener.add(dojo._topics,_ed,dojo.hitch(_ee,_ef))];
};
dojo.unsubscribe=function(_f0){
if(_f0){
dojo._listener.remove(dojo._topics,_f0[0],_f0[1]);
}
};
dojo.publish=function(_f1,_f2){
var f=dojo._topics[_f1];
if(f){
f.apply(this,_f2||[]);
}
};
dojo.connectPublisher=function(_f3,obj,_f4){
var pf=function(){
dojo.publish(_f3,arguments);
};
return _f4?dojo.connect(obj,_f4,pf):dojo.connect(obj,pf);
};
}
if(!dojo._hasResource["dojo._base.Deferred"]){
dojo._hasResource["dojo._base.Deferred"]=true;
dojo.provide("dojo._base.Deferred");
(function(){
var _f5=function(){
};
var _f6=Object.freeze||function(){
};
dojo.Deferred=function(_f7){
var _f8,_f9,_fa,_fb,_fc;
var _fd=this.promise={};
function _fe(_ff){
if(_f9){
return;
throw new Error("This deferred has already been resolved");
}
_f8=_ff;
_f9=true;
_100();
};
function _100(){
var _101;
while(!_101&&_fc){
var _102=_fc;
_fc=_fc.next;
if(_101=(_102.progress==_f5)){
_f9=false;
}
var func=(_fa?_102.error:_102.resolved);
if(func){
try{
var _103=func(_f8);
if(_103&&typeof _103.then==="function"){
_103.then(dojo.hitch(_102.deferred,"resolve"),dojo.hitch(_102.deferred,"reject"));
continue;
}
var _104=_101&&_103===undefined;
_102.deferred[_104&&_fa?"reject":"resolve"](_104?_f8:_103);
}
catch(e){
_102.deferred.reject(e);
}
}else{
if(_fa){
_102.deferred.reject(_f8);
}else{
_102.deferred.resolve(_f8);
}
}
}
};
this.resolve=this.callback=function(_105){
this.fired=0;
this.results=[_105,null];
_fe(_105);
};
this.reject=this.errback=function(_106){
_fa=true;
this.fired=1;
_fe(_106);
this.results=[null,_106];
if(!_106||_106.log!==false){
(dojo.config.deferredOnError||function(x){
console.error(x);
})(_106);
}
};
this.progress=function(_107){
var _108=_fc;
while(_108){
var _109=_108.progress;
_109&&_109(_107);
_108=_108.next;
}
};
this.addCallbacks=function(_10a,_10b){
this.then(_10a,_10b,_f5);
return this;
};
this.then=_fd.then=function(_10c,_10d,_10e){
var _10f=_10e==_f5?this:new dojo.Deferred(_fd.cancel);
var _110={resolved:_10c,error:_10d,progress:_10e,deferred:_10f};
if(_fc){
_fb=_fb.next=_110;
}else{
_fc=_fb=_110;
}
if(_f9){
_100();
}
return _10f.promise;
};
var _111=this;
this.cancel=_fd.cancel=function(){
if(!_f9){
var _112=_f7&&_f7(_111);
if(!_f9){
if(!(_112 instanceof Error)){
_112=new Error(_112);
}
_112.log=false;
_111.reject(_112);
}
}
};
_f6(_fd);
};
dojo.extend(dojo.Deferred,{addCallback:function(_113){
return this.addCallbacks(dojo.hitch.apply(dojo,arguments));
},addErrback:function(_114){
return this.addCallbacks(null,dojo.hitch.apply(dojo,arguments));
},addBoth:function(_115){
var _116=dojo.hitch.apply(dojo,arguments);
return this.addCallbacks(_116,_116);
},fired:-1});
})();
dojo.when=function(_117,_118,_119,_11a){
if(_117&&typeof _117.then==="function"){
return _117.then(_118,_119,_11a);
}
return _118(_117);
};
}
if(!dojo._hasResource["dojo._base.json"]){
dojo._hasResource["dojo._base.json"]=true;
dojo.provide("dojo._base.json");
dojo.fromJson=function(json){
return eval("("+json+")");
};
dojo._escapeString=function(str){
return ("\""+str.replace(/(["\\])/g,"\\$1")+"\"").replace(/[\f]/g,"\\f").replace(/[\b]/g,"\\b").replace(/[\n]/g,"\\n").replace(/[\t]/g,"\\t").replace(/[\r]/g,"\\r");
};
dojo.toJsonIndentStr="\t";
dojo.toJson=function(it,_11b,_11c){
if(it===undefined){
return "undefined";
}
var _11d=typeof it;
if(_11d=="number"||_11d=="boolean"){
return it+"";
}
if(it===null){
return "null";
}
if(dojo.isString(it)){
return dojo._escapeString(it);
}
var _11e=arguments.callee;
var _11f;
_11c=_11c||"";
var _120=_11b?_11c+dojo.toJsonIndentStr:"";
var tf=it.__json__||it.json;
if(dojo.isFunction(tf)){
_11f=tf.call(it);
if(it!==_11f){
return _11e(_11f,_11b,_120);
}
}
if(it.nodeType&&it.cloneNode){
throw new Error("Can't serialize DOM nodes");
}
var sep=_11b?" ":"";
var _121=_11b?"\n":"";
if(dojo.isArray(it)){
var res=dojo.map(it,function(obj){
var val=_11e(obj,_11b,_120);
if(typeof val!="string"){
val="undefined";
}
return _121+_120+val;
});
return "["+res.join(","+sep)+_121+_11c+"]";
}
if(_11d=="function"){
return null;
}
var _122=[],key;
for(key in it){
var _123,val;
if(typeof key=="number"){
_123="\""+key+"\"";
}else{
if(typeof key=="string"){
_123=dojo._escapeString(key);
}else{
continue;
}
}
val=_11e(it[key],_11b,_120);
if(typeof val!="string"){
continue;
}
_122.push(_121+_120+_123+":"+sep+val);
}
return "{"+_122.join(","+sep)+_121+_11c+"}";
};
}
if(!dojo._hasResource["dojo._base.Color"]){
dojo._hasResource["dojo._base.Color"]=true;
dojo.provide("dojo._base.Color");
(function(){
var d=dojo;
dojo.Color=function(_124){
if(_124){
this.setColor(_124);
}
};
dojo.Color.named={black:[0,0,0],silver:[192,192,192],gray:[128,128,128],white:[255,255,255],maroon:[128,0,0],red:[255,0,0],purple:[128,0,128],fuchsia:[255,0,255],green:[0,128,0],lime:[0,255,0],olive:[128,128,0],yellow:[255,255,0],navy:[0,0,128],blue:[0,0,255],teal:[0,128,128],aqua:[0,255,255],transparent:d.config.transparentColor||[255,255,255]};
dojo.extend(dojo.Color,{r:255,g:255,b:255,a:1,_set:function(r,g,b,a){
var t=this;
t.r=r;
t.g=g;
t.b=b;
t.a=a;
},setColor:function(_125){
if(d.isString(_125)){
d.colorFromString(_125,this);
}else{
if(d.isArray(_125)){
d.colorFromArray(_125,this);
}else{
this._set(_125.r,_125.g,_125.b,_125.a);
if(!(_125 instanceof d.Color)){
this.sanitize();
}
}
}
return this;
},sanitize:function(){
return this;
},toRgb:function(){
var t=this;
return [t.r,t.g,t.b];
},toRgba:function(){
var t=this;
return [t.r,t.g,t.b,t.a];
},toHex:function(){
var arr=d.map(["r","g","b"],function(x){
var s=this[x].toString(16);
return s.length<2?"0"+s:s;
},this);
return "#"+arr.join("");
},toCss:function(_126){
var t=this,rgb=t.r+", "+t.g+", "+t.b;
return (_126?"rgba("+rgb+", "+t.a:"rgb("+rgb)+")";
},toString:function(){
return this.toCss(true);
}});
dojo.blendColors=function(_127,end,_128,obj){
var t=obj||new d.Color();
d.forEach(["r","g","b","a"],function(x){
t[x]=_127[x]+(end[x]-_127[x])*_128;
if(x!="a"){
t[x]=Math.round(t[x]);
}
});
return t.sanitize();
};
dojo.colorFromRgb=function(_129,obj){
var m=_129.toLowerCase().match(/^rgba?\(([\s\.,0-9]+)\)/);
return m&&dojo.colorFromArray(m[1].split(/\s*,\s*/),obj);
};
dojo.colorFromHex=function(_12a,obj){
var t=obj||new d.Color(),bits=(_12a.length==4)?4:8,mask=(1<<bits)-1;
_12a=Number("0x"+_12a.substr(1));
if(isNaN(_12a)){
return null;
}
d.forEach(["b","g","r"],function(x){
var c=_12a&mask;
_12a>>=bits;
t[x]=bits==4?17*c:c;
});
t.a=1;
return t;
};
dojo.colorFromArray=function(a,obj){
var t=obj||new d.Color();
t._set(Number(a[0]),Number(a[1]),Number(a[2]),Number(a[3]));
if(isNaN(t.a)){
t.a=1;
}
return t.sanitize();
};
dojo.colorFromString=function(str,obj){
var a=d.Color.named[str];
return a&&d.colorFromArray(a,obj)||d.colorFromRgb(str,obj)||d.colorFromHex(str,obj);
};
})();
}
if(!dojo._hasResource["dojo._base"]){
dojo._hasResource["dojo._base"]=true;
dojo.provide("dojo._base");
}
if(!dojo._hasResource["dojo._base.window"]){
dojo._hasResource["dojo._base.window"]=true;
dojo.provide("dojo._base.window");
dojo.doc=window["document"]||null;
dojo.body=function(){
return dojo.doc.body||dojo.doc.getElementsByTagName("body")[0];
};
dojo.setContext=function(_12b,_12c){
dojo.global=_12b;
dojo.doc=_12c;
};
dojo.withGlobal=function(_12d,_12e,_12f,_130){
var _131=dojo.global;
try{
dojo.global=_12d;
return dojo.withDoc.call(null,_12d.document,_12e,_12f,_130);
}
finally{
dojo.global=_131;
}
};
dojo.withDoc=function(_132,_133,_134,_135){
var _136=dojo.doc,_137=dojo._bodyLtr,oldQ=dojo.isQuirks;
try{
dojo.doc=_132;
delete dojo._bodyLtr;
dojo.isQuirks=dojo.doc.compatMode=="BackCompat";
if(_134&&typeof _133=="string"){
_133=_134[_133];
}
return _133.apply(_134,_135||[]);
}
finally{
dojo.doc=_136;
delete dojo._bodyLtr;
if(_137!==undefined){
dojo._bodyLtr=_137;
}
dojo.isQuirks=oldQ;
}
};
}
if(!dojo._hasResource["dojo._base.event"]){
dojo._hasResource["dojo._base.event"]=true;
dojo.provide("dojo._base.event");
(function(){
var del=(dojo._event_listener={add:function(node,name,fp){
if(!node){
return;
}
name=del._normalizeEventName(name);
fp=del._fixCallback(name,fp);
var _138=name;
if(!dojo.isIE&&(name=="mouseenter"||name=="mouseleave")){
var ofp=fp;
name=(name=="mouseenter")?"mouseover":"mouseout";
fp=function(e){
if(!dojo.isDescendant(e.relatedTarget,node)){
return ofp.call(this,e);
}
};
}
node.addEventListener(name,fp,false);
return fp;
},remove:function(node,_139,_13a){
if(node){
_139=del._normalizeEventName(_139);
if(!dojo.isIE&&(_139=="mouseenter"||_139=="mouseleave")){
_139=(_139=="mouseenter")?"mouseover":"mouseout";
}
node.removeEventListener(_139,_13a,false);
}
},_normalizeEventName:function(name){
return name.slice(0,2)=="on"?name.slice(2):name;
},_fixCallback:function(name,fp){
return name!="keypress"?fp:function(e){
return fp.call(this,del._fixEvent(e,this));
};
},_fixEvent:function(evt,_13b){
switch(evt.type){
case "keypress":
del._setKeyChar(evt);
break;
}
return evt;
},_setKeyChar:function(evt){
evt.keyChar=evt.charCode?String.fromCharCode(evt.charCode):"";
evt.charOrCode=evt.keyChar||evt.keyCode;
},_punctMap:{106:42,111:47,186:59,187:43,188:44,189:45,190:46,191:47,192:96,219:91,220:92,221:93,222:39}});
dojo.fixEvent=function(evt,_13c){
return del._fixEvent(evt,_13c);
};
dojo.stopEvent=function(evt){
evt.preventDefault();
evt.stopPropagation();
};
var _13d=dojo._listener;
dojo._connect=function(obj,_13e,_13f,_140,_141){
var _142=obj&&(obj.nodeType||obj.attachEvent||obj.addEventListener);
var lid=_142?(_141?2:1):0,l=[dojo._listener,del,_13d][lid];
var h=l.add(obj,_13e,dojo.hitch(_13f,_140));
return [obj,_13e,h,lid];
};
dojo._disconnect=function(obj,_143,_144,_145){
([dojo._listener,del,_13d][_145]).remove(obj,_143,_144);
};
dojo.keys={BACKSPACE:8,TAB:9,CLEAR:12,ENTER:13,SHIFT:16,CTRL:17,ALT:18,META:dojo.isSafari?91:224,PAUSE:19,CAPS_LOCK:20,ESCAPE:27,SPACE:32,PAGE_UP:33,PAGE_DOWN:34,END:35,HOME:36,LEFT_ARROW:37,UP_ARROW:38,RIGHT_ARROW:39,DOWN_ARROW:40,INSERT:45,DELETE:46,HELP:47,LEFT_WINDOW:91,RIGHT_WINDOW:92,SELECT:93,NUMPAD_0:96,NUMPAD_1:97,NUMPAD_2:98,NUMPAD_3:99,NUMPAD_4:100,NUMPAD_5:101,NUMPAD_6:102,NUMPAD_7:103,NUMPAD_8:104,NUMPAD_9:105,NUMPAD_MULTIPLY:106,NUMPAD_PLUS:107,NUMPAD_ENTER:108,NUMPAD_MINUS:109,NUMPAD_PERIOD:110,NUMPAD_DIVIDE:111,F1:112,F2:113,F3:114,F4:115,F5:116,F6:117,F7:118,F8:119,F9:120,F10:121,F11:122,F12:123,F13:124,F14:125,F15:126,NUM_LOCK:144,SCROLL_LOCK:145,copyKey:dojo.isMac&&!dojo.isAIR?(dojo.isSafari?91:224):17};
var _146=dojo.isMac?"metaKey":"ctrlKey";
dojo.isCopyKey=function(e){
return e[_146];
};
if(dojo.isIE){
dojo.mouseButtons={LEFT:1,MIDDLE:4,RIGHT:2,isButton:function(e,_147){
return e.button&_147;
},isLeft:function(e){
return e.button&1;
},isMiddle:function(e){
return e.button&4;
},isRight:function(e){
return e.button&2;
}};
}else{
dojo.mouseButtons={LEFT:0,MIDDLE:1,RIGHT:2,isButton:function(e,_148){
return e.button==_148;
},isLeft:function(e){
return e.button==0;
},isMiddle:function(e){
return e.button==1;
},isRight:function(e){
return e.button==2;
}};
}
if(dojo.isIE){
var _149=function(e,code){
try{
return (e.keyCode=code);
}
catch(e){
return 0;
}
};
var iel=dojo._listener;
var _14a=(dojo._ieListenersName="_"+dojo._scopeName+"_listeners");
if(!dojo.config._allow_leaks){
_13d=iel=dojo._ie_listener={handlers:[],add:function(_14b,_14c,_14d){
_14b=_14b||dojo.global;
var f=_14b[_14c];
if(!f||!f[_14a]){
var d=dojo._getIeDispatcher();
d.target=f&&(ieh.push(f)-1);
d[_14a]=[];
f=_14b[_14c]=d;
}
return f[_14a].push(ieh.push(_14d)-1);
},remove:function(_14e,_14f,_150){
var f=(_14e||dojo.global)[_14f],l=f&&f[_14a];
if(f&&l&&_150--){
delete ieh[l[_150]];
delete l[_150];
}
}};
var ieh=iel.handlers;
}
dojo.mixin(del,{add:function(node,_151,fp){
if(!node){
return;
}
_151=del._normalizeEventName(_151);
if(_151=="onkeypress"){
var kd=node.onkeydown;
if(!kd||!kd[_14a]||!kd._stealthKeydownHandle){
var h=del.add(node,"onkeydown",del._stealthKeyDown);
kd=node.onkeydown;
kd._stealthKeydownHandle=h;
kd._stealthKeydownRefs=1;
}else{
kd._stealthKeydownRefs++;
}
}
return iel.add(node,_151,del._fixCallback(fp));
},remove:function(node,_152,_153){
_152=del._normalizeEventName(_152);
iel.remove(node,_152,_153);
if(_152=="onkeypress"){
var kd=node.onkeydown;
if(--kd._stealthKeydownRefs<=0){
iel.remove(node,"onkeydown",kd._stealthKeydownHandle);
delete kd._stealthKeydownHandle;
}
}
},_normalizeEventName:function(_154){
return _154.slice(0,2)!="on"?"on"+_154:_154;
},_nop:function(){
},_fixEvent:function(evt,_155){
if(!evt){
var w=_155&&(_155.ownerDocument||_155.document||_155).parentWindow||window;
evt=w.event;
}
if(!evt){
return (evt);
}
evt.target=evt.srcElement;
evt.currentTarget=(_155||evt.srcElement);
evt.layerX=evt.offsetX;
evt.layerY=evt.offsetY;
var se=evt.srcElement,doc=(se&&se.ownerDocument)||document;
var _156=((dojo.isIE<6)||(doc["compatMode"]=="BackCompat"))?doc.body:doc.documentElement;
var _157=dojo._getIeDocumentElementOffset();
evt.pageX=evt.clientX+dojo._fixIeBiDiScrollLeft(_156.scrollLeft||0)-_157.x;
evt.pageY=evt.clientY+(_156.scrollTop||0)-_157.y;
if(evt.type=="mouseover"){
evt.relatedTarget=evt.fromElement;
}
if(evt.type=="mouseout"){
evt.relatedTarget=evt.toElement;
}
evt.stopPropagation=del._stopPropagation;
evt.preventDefault=del._preventDefault;
return del._fixKeys(evt);
},_fixKeys:function(evt){
switch(evt.type){
case "keypress":
var c=("charCode" in evt?evt.charCode:evt.keyCode);
if(c==10){
c=0;
evt.keyCode=13;
}else{
if(c==13||c==27){
c=0;
}else{
if(c==3){
c=99;
}
}
}
evt.charCode=c;
del._setKeyChar(evt);
break;
}
return evt;
},_stealthKeyDown:function(evt){
var kp=evt.currentTarget.onkeypress;
if(!kp||!kp[_14a]){
return;
}
var k=evt.keyCode;
var _158=k!=13&&k!=32&&k!=27&&(k<48||k>90)&&(k<96||k>111)&&(k<186||k>192)&&(k<219||k>222);
if(_158||evt.ctrlKey){
var c=_158?0:k;
if(evt.ctrlKey){
if(k==3||k==13){
return;
}else{
if(c>95&&c<106){
c-=48;
}else{
if((!evt.shiftKey)&&(c>=65&&c<=90)){
c+=32;
}else{
c=del._punctMap[c]||c;
}
}
}
}
var faux=del._synthesizeEvent(evt,{type:"keypress",faux:true,charCode:c});
kp.call(evt.currentTarget,faux);
evt.cancelBubble=faux.cancelBubble;
evt.returnValue=faux.returnValue;
_149(evt,faux.keyCode);
}
},_stopPropagation:function(){
this.cancelBubble=true;
},_preventDefault:function(){
this.bubbledKeyCode=this.keyCode;
if(this.ctrlKey){
_149(this,0);
}
this.returnValue=false;
}});
dojo.stopEvent=function(evt){
evt=evt||window.event;
del._stopPropagation.call(evt);
del._preventDefault.call(evt);
};
}
del._synthesizeEvent=function(evt,_159){
var faux=dojo.mixin({},evt,_159);
del._setKeyChar(faux);
faux.preventDefault=function(){
evt.preventDefault();
};
faux.stopPropagation=function(){
evt.stopPropagation();
};
return faux;
};
if(dojo.isOpera){
dojo.mixin(del,{_fixEvent:function(evt,_15a){
switch(evt.type){
case "keypress":
var c=evt.which;
if(c==3){
c=99;
}
c=c<41&&!evt.shiftKey?0:c;
if(evt.ctrlKey&&!evt.shiftKey&&c>=65&&c<=90){
c+=32;
}
return del._synthesizeEvent(evt,{charCode:c});
}
return evt;
}});
}
if(dojo.isWebKit){
del._add=del.add;
del._remove=del.remove;
dojo.mixin(del,{add:function(node,_15b,fp){
if(!node){
return;
}
var _15c=del._add(node,_15b,fp);
if(del._normalizeEventName(_15b)=="keypress"){
_15c._stealthKeyDownHandle=del._add(node,"keydown",function(evt){
var k=evt.keyCode;
var _15d=k!=13&&k!=32&&(k<48||k>90)&&(k<96||k>111)&&(k<186||k>192)&&(k<219||k>222);
if(_15d||evt.ctrlKey){
var c=_15d?0:k;
if(evt.ctrlKey){
if(k==3||k==13){
return;
}else{
if(c>95&&c<106){
c-=48;
}else{
if(!evt.shiftKey&&c>=65&&c<=90){
c+=32;
}else{
c=del._punctMap[c]||c;
}
}
}
}
var faux=del._synthesizeEvent(evt,{type:"keypress",faux:true,charCode:c});
fp.call(evt.currentTarget,faux);
}
});
}
return _15c;
},remove:function(node,_15e,_15f){
if(node){
if(_15f._stealthKeyDownHandle){
del._remove(node,"keydown",_15f._stealthKeyDownHandle);
}
del._remove(node,_15e,_15f);
}
},_fixEvent:function(evt,_160){
switch(evt.type){
case "keypress":
if(evt.faux){
return evt;
}
var c=evt.charCode;
c=c>=32?c:0;
return del._synthesizeEvent(evt,{charCode:c,faux:true});
}
return evt;
}});
}
})();
if(dojo.isIE){
dojo._ieDispatcher=function(args,_161){
var ap=Array.prototype,h=dojo._ie_listener.handlers,c=args.callee,ls=c[dojo._ieListenersName],t=h[c.target];
var r=t&&t.apply(_161,args);
var lls=[].concat(ls);
for(var i in lls){
var f=h[lls[i]];
if(!(i in ap)&&f){
f.apply(_161,args);
}
}
return r;
};
dojo._getIeDispatcher=function(){
return new Function(dojo._scopeName+"._ieDispatcher(arguments, this)");
};
dojo._event_listener._fixCallback=function(fp){
var f=dojo._event_listener._fixEvent;
return function(e){
return fp.call(this,f(e,this));
};
};
}
}
if(!dojo._hasResource["dojo._base.html"]){
dojo._hasResource["dojo._base.html"]=true;
dojo.provide("dojo._base.html");
try{
document.execCommand("BackgroundImageCache",false,true);
}
catch(e){
}
if(dojo.isIE||dojo.isOpera){
dojo.byId=function(id,doc){
if(typeof id!="string"){
return id;
}
var _162=doc||dojo.doc,te=_162.getElementById(id);
if(te&&(te.attributes.id.value==id||te.id==id)){
return te;
}else{
var eles=_162.all[id];
if(!eles||eles.nodeName){
eles=[eles];
}
var i=0;
while((te=eles[i++])){
if((te.attributes&&te.attributes.id&&te.attributes.id.value==id)||te.id==id){
return te;
}
}
}
};
}else{
dojo.byId=function(id,doc){
return (typeof id=="string")?(doc||dojo.doc).getElementById(id):id;
};
}
(function(){
var d=dojo;
var byId=d.byId;
var _163=null,_164;
d.addOnWindowUnload(function(){
_163=null;
});
dojo._destroyElement=dojo.destroy=function(node){
node=byId(node);
try{
var doc=node.ownerDocument;
if(!_163||_164!=doc){
_163=doc.createElement("div");
_164=doc;
}
_163.appendChild(node.parentNode?node.parentNode.removeChild(node):node);
_163.innerHTML="";
}
catch(e){
}
};
dojo.isDescendant=function(node,_165){
try{
node=byId(node);
_165=byId(_165);
while(node){
if(node==_165){
return true;
}
node=node.parentNode;
}
}
catch(e){
}
return false;
};
dojo.setSelectable=function(node,_166){
node=byId(node);
if(d.isMozilla){
node.style.MozUserSelect=_166?"":"none";
}else{
if(d.isKhtml||d.isWebKit){
node.style.KhtmlUserSelect=_166?"auto":"none";
}else{
if(d.isIE){
var v=(node.unselectable=_166?"":"on");
d.query("*",node).forEach("item.unselectable = '"+v+"'");
}
}
}
};
var _167=function(node,ref){
var _168=ref.parentNode;
if(_168){
_168.insertBefore(node,ref);
}
};
var _169=function(node,ref){
var _16a=ref.parentNode;
if(_16a){
if(_16a.lastChild==ref){
_16a.appendChild(node);
}else{
_16a.insertBefore(node,ref.nextSibling);
}
}
};
dojo.place=function(node,_16b,_16c){
_16b=byId(_16b);
if(typeof node=="string"){
node=node.charAt(0)=="<"?d._toDom(node,_16b.ownerDocument):byId(node);
}
if(typeof _16c=="number"){
var cn=_16b.childNodes;
if(!cn.length||cn.length<=_16c){
_16b.appendChild(node);
}else{
_167(node,cn[_16c<0?0:_16c]);
}
}else{
switch(_16c){
case "before":
_167(node,_16b);
break;
case "after":
_169(node,_16b);
break;
case "replace":
_16b.parentNode.replaceChild(node,_16b);
break;
case "only":
d.empty(_16b);
_16b.appendChild(node);
break;
case "first":
if(_16b.firstChild){
_167(node,_16b.firstChild);
break;
}
default:
_16b.appendChild(node);
}
}
return node;
};
dojo.boxModel="content-box";
if(d.isIE){
d.boxModel=document.compatMode=="BackCompat"?"border-box":"content-box";
}
var gcs;
if(d.isWebKit){
gcs=function(node){
var s;
if(node.nodeType==1){
var dv=node.ownerDocument.defaultView;
s=dv.getComputedStyle(node,null);
if(!s&&node.style){
node.style.display="";
s=dv.getComputedStyle(node,null);
}
}
return s||{};
};
}else{
if(d.isIE){
gcs=function(node){
return node.nodeType==1?node.currentStyle:{};
};
}else{
gcs=function(node){
return node.nodeType==1?node.ownerDocument.defaultView.getComputedStyle(node,null):{};
};
}
}
dojo.getComputedStyle=gcs;
if(!d.isIE){
d._toPixelValue=function(_16d,_16e){
return parseFloat(_16e)||0;
};
}else{
d._toPixelValue=function(_16f,_170){
if(!_170){
return 0;
}
if(_170=="medium"){
return 4;
}
if(_170.slice&&_170.slice(-2)=="px"){
return parseFloat(_170);
}
with(_16f){
var _171=style.left;
var _172=runtimeStyle.left;
runtimeStyle.left=currentStyle.left;
try{
style.left=_170;
_170=style.pixelLeft;
}
catch(e){
_170=0;
}
style.left=_171;
runtimeStyle.left=_172;
}
return _170;
};
}
var px=d._toPixelValue;
var astr="DXImageTransform.Microsoft.Alpha";
var af=function(n,f){
try{
return n.filters.item(astr);
}
catch(e){
return f?{}:null;
}
};
dojo._getOpacity=d.isIE?function(node){
try{
return af(node).Opacity/100;
}
catch(e){
return 1;
}
}:function(node){
return gcs(node).opacity;
};
dojo._setOpacity=d.isIE?function(node,_173){
var ov=_173*100,_174=_173==1;
node.style.zoom=_174?"":1;
if(!af(node)){
if(_174){
return _173;
}
node.style.filter+=" progid:"+astr+"(Opacity="+ov+")";
}else{
af(node,1).Opacity=ov;
}
af(node,1).Enabled=!_174;
if(node.nodeName.toLowerCase()=="tr"){
d.query("> td",node).forEach(function(i){
d._setOpacity(i,_173);
});
}
return _173;
}:function(node,_175){
return node.style.opacity=_175;
};
var _176={left:true,top:true};
var _177=/margin|padding|width|height|max|min|offset/;
var _178=function(node,type,_179){
type=type.toLowerCase();
if(d.isIE){
if(_179=="auto"){
if(type=="height"){
return node.offsetHeight;
}
if(type=="width"){
return node.offsetWidth;
}
}
if(type=="fontweight"){
switch(_179){
case 700:
return "bold";
case 400:
default:
return "normal";
}
}
}
if(!(type in _176)){
_176[type]=_177.test(type);
}
return _176[type]?px(node,_179):_179;
};
var _17a=d.isIE?"styleFloat":"cssFloat",_17b={"cssFloat":_17a,"styleFloat":_17a,"float":_17a};
dojo.style=function(node,_17c,_17d){
var n=byId(node),args=arguments.length,op=(_17c=="opacity");
_17c=_17b[_17c]||_17c;
if(args==3){
return op?d._setOpacity(n,_17d):n.style[_17c]=_17d;
}
if(args==2&&op){
return d._getOpacity(n);
}
var s=gcs(n);
if(args==2&&typeof _17c!="string"){
for(var x in _17c){
d.style(node,x,_17c[x]);
}
return s;
}
return (args==1)?s:_178(n,_17c,s[_17c]||n.style[_17c]);
};
dojo._getPadExtents=function(n,_17e){
var s=_17e||gcs(n),l=px(n,s.paddingLeft),t=px(n,s.paddingTop);
return {l:l,t:t,w:l+px(n,s.paddingRight),h:t+px(n,s.paddingBottom)};
};
dojo._getBorderExtents=function(n,_17f){
var ne="none",s=_17f||gcs(n),bl=(s.borderLeftStyle!=ne?px(n,s.borderLeftWidth):0),bt=(s.borderTopStyle!=ne?px(n,s.borderTopWidth):0);
return {l:bl,t:bt,w:bl+(s.borderRightStyle!=ne?px(n,s.borderRightWidth):0),h:bt+(s.borderBottomStyle!=ne?px(n,s.borderBottomWidth):0)};
};
dojo._getPadBorderExtents=function(n,_180){
var s=_180||gcs(n),p=d._getPadExtents(n,s),b=d._getBorderExtents(n,s);
return {l:p.l+b.l,t:p.t+b.t,w:p.w+b.w,h:p.h+b.h};
};
dojo._getMarginExtents=function(n,_181){
var s=_181||gcs(n),l=px(n,s.marginLeft),t=px(n,s.marginTop),r=px(n,s.marginRight),b=px(n,s.marginBottom);
if(d.isWebKit&&(s.position!="absolute")){
r=l;
}
return {l:l,t:t,w:l+r,h:t+b};
};
dojo._getMarginBox=function(node,_182){
var s=_182||gcs(node),me=d._getMarginExtents(node,s);
var l=node.offsetLeft-me.l,t=node.offsetTop-me.t,p=node.parentNode;
if(d.isMoz){
var sl=parseFloat(s.left),st=parseFloat(s.top);
if(!isNaN(sl)&&!isNaN(st)){
l=sl,t=st;
}else{
if(p&&p.style){
var pcs=gcs(p);
if(pcs.overflow!="visible"){
var be=d._getBorderExtents(p,pcs);
l+=be.l,t+=be.t;
}
}
}
}else{
if(d.isOpera||(d.isIE>7&&!d.isQuirks)){
if(p){
be=d._getBorderExtents(p);
l-=be.l;
t-=be.t;
}
}
}
return {l:l,t:t,w:node.offsetWidth+me.w,h:node.offsetHeight+me.h};
};
dojo._getContentBox=function(node,_183){
var s=_183||gcs(node),pe=d._getPadExtents(node,s),be=d._getBorderExtents(node,s),w=node.clientWidth,h;
if(!w){
w=node.offsetWidth,h=node.offsetHeight;
}else{
h=node.clientHeight,be.w=be.h=0;
}
if(d.isOpera){
pe.l+=be.l;
pe.t+=be.t;
}
return {l:pe.l,t:pe.t,w:w-pe.w-be.w,h:h-pe.h-be.h};
};
dojo._getBorderBox=function(node,_184){
var s=_184||gcs(node),pe=d._getPadExtents(node,s),cb=d._getContentBox(node,s);
return {l:cb.l-pe.l,t:cb.t-pe.t,w:cb.w+pe.w,h:cb.h+pe.h};
};
dojo._setBox=function(node,l,t,w,h,u){
u=u||"px";
var s=node.style;
if(!isNaN(l)){
s.left=l+u;
}
if(!isNaN(t)){
s.top=t+u;
}
if(w>=0){
s.width=w+u;
}
if(h>=0){
s.height=h+u;
}
};
dojo._isButtonTag=function(node){
return node.tagName=="BUTTON"||node.tagName=="INPUT"&&(node.getAttribute("type")||"").toUpperCase()=="BUTTON";
};
dojo._usesBorderBox=function(node){
var n=node.tagName;
return d.boxModel=="border-box"||n=="TABLE"||d._isButtonTag(node);
};
dojo._setContentSize=function(node,_185,_186,_187){
if(d._usesBorderBox(node)){
var pb=d._getPadBorderExtents(node,_187);
if(_185>=0){
_185+=pb.w;
}
if(_186>=0){
_186+=pb.h;
}
}
d._setBox(node,NaN,NaN,_185,_186);
};
dojo._setMarginBox=function(node,_188,_189,_18a,_18b,_18c){
var s=_18c||gcs(node),bb=d._usesBorderBox(node),pb=bb?_18d:d._getPadBorderExtents(node,s);
if(d.isWebKit){
if(d._isButtonTag(node)){
var ns=node.style;
if(_18a>=0&&!ns.width){
ns.width="4px";
}
if(_18b>=0&&!ns.height){
ns.height="4px";
}
}
}
var mb=d._getMarginExtents(node,s);
if(_18a>=0){
_18a=Math.max(_18a-pb.w-mb.w,0);
}
if(_18b>=0){
_18b=Math.max(_18b-pb.h-mb.h,0);
}
d._setBox(node,_188,_189,_18a,_18b);
};
var _18d={l:0,t:0,w:0,h:0};
dojo.marginBox=function(node,box){
var n=byId(node),s=gcs(n),b=box;
return !b?d._getMarginBox(n,s):d._setMarginBox(n,b.l,b.t,b.w,b.h,s);
};
dojo.contentBox=function(node,box){
var n=byId(node),s=gcs(n),b=box;
return !b?d._getContentBox(n,s):d._setContentSize(n,b.w,b.h,s);
};
var _18e=function(node,prop){
if(!(node=(node||0).parentNode)){
return 0;
}
var val,_18f=0,_190=d.body();
while(node&&node.style){
if(gcs(node).position=="fixed"){
return 0;
}
val=node[prop];
if(val){
_18f+=val-0;
if(node==_190){
break;
}
}
node=node.parentNode;
}
return _18f;
};
dojo._docScroll=function(){
var n=d.global;
return "pageXOffset" in n?{x:n.pageXOffset,y:n.pageYOffset}:(n=d.doc.documentElement,n.clientHeight?{x:d._fixIeBiDiScrollLeft(n.scrollLeft),y:n.scrollTop}:(n=d.body(),{x:n.scrollLeft||0,y:n.scrollTop||0}));
};
dojo._isBodyLtr=function(){
return "_bodyLtr" in d?d._bodyLtr:d._bodyLtr=(d.body().dir||d.doc.documentElement.dir||"ltr").toLowerCase()=="ltr";
};
dojo._getIeDocumentElementOffset=function(){
var de=d.doc.documentElement;
if(d.isIE<8){
var r=de.getBoundingClientRect();
var l=r.left,t=r.top;
if(d.isIE<7){
l+=de.clientLeft;
t+=de.clientTop;
}
return {x:l<0?0:l,y:t<0?0:t};
}else{
return {x:0,y:0};
}
};
dojo._fixIeBiDiScrollLeft=function(_191){
var dd=d.doc;
if(d.isIE<8&&!d._isBodyLtr()){
var de=d.isQuirks?dd.body:dd.documentElement;
return _191+de.clientWidth-de.scrollWidth;
}
return _191;
};
dojo._abs=dojo.position=function(node,_192){
var db=d.body(),dh=db.parentNode,ret;
node=byId(node);
if(node["getBoundingClientRect"]){
ret=node.getBoundingClientRect();
ret={x:ret.left,y:ret.top,w:ret.right-ret.left,h:ret.bottom-ret.top};
if(d.isIE){
var _193=d._getIeDocumentElementOffset();
ret.x-=_193.x+(d.isQuirks?db.clientLeft+db.offsetLeft:0);
ret.y-=_193.y+(d.isQuirks?db.clientTop+db.offsetTop:0);
}else{
if(d.isFF==3){
var cs=gcs(dh);
ret.x-=px(dh,cs.marginLeft)+px(dh,cs.borderLeftWidth);
ret.y-=px(dh,cs.marginTop)+px(dh,cs.borderTopWidth);
}
}
}else{
ret={x:0,y:0,w:node.offsetWidth,h:node.offsetHeight};
if(node["offsetParent"]){
ret.x-=_18e(node,"scrollLeft");
ret.y-=_18e(node,"scrollTop");
var _194=node;
do{
var n=_194.offsetLeft,t=_194.offsetTop;
ret.x+=isNaN(n)?0:n;
ret.y+=isNaN(t)?0:t;
cs=gcs(_194);
if(_194!=node){
if(d.isMoz){
ret.x+=2*px(_194,cs.borderLeftWidth);
ret.y+=2*px(_194,cs.borderTopWidth);
}else{
ret.x+=px(_194,cs.borderLeftWidth);
ret.y+=px(_194,cs.borderTopWidth);
}
}
if(d.isMoz&&cs.position=="static"){
var _195=_194.parentNode;
while(_195!=_194.offsetParent){
var pcs=gcs(_195);
if(pcs.position=="static"){
ret.x+=px(_194,pcs.borderLeftWidth);
ret.y+=px(_194,pcs.borderTopWidth);
}
_195=_195.parentNode;
}
}
_194=_194.offsetParent;
}while((_194!=dh)&&_194);
}else{
if(node.x&&node.y){
ret.x+=isNaN(node.x)?0:node.x;
ret.y+=isNaN(node.y)?0:node.y;
}
}
}
if(_192){
var _196=d._docScroll();
ret.x+=_196.x;
ret.y+=_196.y;
}
return ret;
};
dojo.coords=function(node,_197){
var n=byId(node),s=gcs(n),mb=d._getMarginBox(n,s);
var abs=d.position(n,_197);
mb.x=abs.x;
mb.y=abs.y;
return mb;
};
var _198={"class":"className","for":"htmlFor",tabindex:"tabIndex",readonly:"readOnly",colspan:"colSpan",frameborder:"frameBorder",rowspan:"rowSpan",valuetype:"valueType"},_199={classname:"class",htmlfor:"for",tabindex:"tabIndex",readonly:"readOnly"},_19a={innerHTML:1,className:1,htmlFor:d.isIE,value:1};
var _19b=function(name){
return _199[name.toLowerCase()]||name;
};
var _19c=function(node,name){
var attr=node.getAttributeNode&&node.getAttributeNode(name);
return attr&&attr.specified;
};
dojo.hasAttr=function(node,name){
var lc=name.toLowerCase();
return _19a[_198[lc]||name]||_19c(byId(node),_199[lc]||name);
};
var _19d={},_19e=0,_19f=dojo._scopeName+"attrid",_1a0={col:1,colgroup:1,table:1,tbody:1,tfoot:1,thead:1,tr:1,title:1};
dojo.attr=function(node,name,_1a1){
node=byId(node);
var args=arguments.length,prop;
if(args==2&&typeof name!="string"){
for(var x in name){
d.attr(node,x,name[x]);
}
return node;
}
var lc=name.toLowerCase(),_1a2=_198[lc]||name,_1a3=_19a[_1a2],_1a4=_199[lc]||name;
if(args==3){
do{
if(_1a2=="style"&&typeof _1a1!="string"){
d.style(node,_1a1);
break;
}
if(_1a2=="innerHTML"){
if(d.isIE&&node.tagName.toLowerCase() in _1a0){
d.empty(node);
node.appendChild(d._toDom(_1a1,node.ownerDocument));
}else{
node[_1a2]=_1a1;
}
break;
}
if(d.isFunction(_1a1)){
var _1a5=d.attr(node,_19f);
if(!_1a5){
_1a5=_19e++;
d.attr(node,_19f,_1a5);
}
if(!_19d[_1a5]){
_19d[_1a5]={};
}
var h=_19d[_1a5][_1a2];
if(h){
d.disconnect(h);
}else{
try{
delete node[_1a2];
}
catch(e){
}
}
_19d[_1a5][_1a2]=d.connect(node,_1a2,_1a1);
break;
}
if(_1a3||typeof _1a1=="boolean"){
node[_1a2]=_1a1;
break;
}
node.setAttribute(_1a4,_1a1);
}while(false);
return node;
}
_1a1=node[_1a2];
if(_1a3&&typeof _1a1!="undefined"){
return _1a1;
}
if(_1a2!="href"&&(typeof _1a1=="boolean"||d.isFunction(_1a1))){
return _1a1;
}
return _19c(node,_1a4)?node.getAttribute(_1a4):null;
};
dojo.removeAttr=function(node,name){
byId(node).removeAttribute(_19b(name));
};
dojo.getNodeProp=function(node,name){
node=byId(node);
var lc=name.toLowerCase(),_1a6=_198[lc]||name;
if((_1a6 in node)&&_1a6!="href"){
return node[_1a6];
}
var _1a7=_199[lc]||name;
return _19c(node,_1a7)?node.getAttribute(_1a7):null;
};
dojo.create=function(tag,_1a8,_1a9,pos){
var doc=d.doc;
if(_1a9){
_1a9=byId(_1a9);
doc=_1a9.ownerDocument;
}
if(typeof tag=="string"){
tag=doc.createElement(tag);
}
if(_1a8){
d.attr(tag,_1a8);
}
if(_1a9){
d.place(tag,_1a9,pos);
}
return tag;
};
d.empty=d.isIE?function(node){
node=byId(node);
for(var c;c=node.lastChild;){
d.destroy(c);
}
}:function(node){
byId(node).innerHTML="";
};
var _1aa={option:["select"],tbody:["table"],thead:["table"],tfoot:["table"],tr:["table","tbody"],td:["table","tbody","tr"],th:["table","thead","tr"],legend:["fieldset"],caption:["table"],colgroup:["table"],col:["table","colgroup"],li:["ul"]},_1ab=/<\s*([\w\:]+)/,_1ac={},_1ad=0,_1ae="__"+d._scopeName+"ToDomId";
for(var _1af in _1aa){
var tw=_1aa[_1af];
tw.pre=_1af=="option"?"<select multiple=\"multiple\">":"<"+tw.join("><")+">";
tw.post="</"+tw.reverse().join("></")+">";
}
d._toDom=function(frag,doc){
doc=doc||d.doc;
var _1b0=doc[_1ae];
if(!_1b0){
doc[_1ae]=_1b0=++_1ad+"";
_1ac[_1b0]=doc.createElement("div");
}
frag+="";
var _1b1=frag.match(_1ab),tag=_1b1?_1b1[1].toLowerCase():"",_1b2=_1ac[_1b0],wrap,i,fc,df;
if(_1b1&&_1aa[tag]){
wrap=_1aa[tag];
_1b2.innerHTML=wrap.pre+frag+wrap.post;
for(i=wrap.length;i;--i){
_1b2=_1b2.firstChild;
}
}else{
_1b2.innerHTML=frag;
}
if(_1b2.childNodes.length==1){
return _1b2.removeChild(_1b2.firstChild);
}
df=doc.createDocumentFragment();
while(fc=_1b2.firstChild){
df.appendChild(fc);
}
return df;
};
var _1b3="className";
dojo.hasClass=function(node,_1b4){
return ((" "+byId(node)[_1b3]+" ").indexOf(" "+_1b4+" ")>=0);
};
var _1b5=/\s+/,a1=[""],_1b6=function(s){
if(typeof s=="string"||s instanceof String){
if(s.indexOf(" ")<0){
a1[0]=s;
return a1;
}else{
return s.split(_1b5);
}
}
return s||"";
};
dojo.addClass=function(node,_1b7){
node=byId(node);
_1b7=_1b6(_1b7);
var cls=node[_1b3],_1b8;
cls=cls?" "+cls+" ":" ";
_1b8=cls.length;
for(var i=0,len=_1b7.length,c;i<len;++i){
c=_1b7[i];
if(c&&cls.indexOf(" "+c+" ")<0){
cls+=c+" ";
}
}
if(_1b8<cls.length){
node[_1b3]=cls.substr(1,cls.length-2);
}
};
dojo.removeClass=function(node,_1b9){
node=byId(node);
var cls;
if(_1b9!==undefined){
_1b9=_1b6(_1b9);
cls=" "+node[_1b3]+" ";
for(var i=0,len=_1b9.length;i<len;++i){
cls=cls.replace(" "+_1b9[i]+" "," ");
}
cls=d.trim(cls);
}else{
cls="";
}
if(node[_1b3]!=cls){
node[_1b3]=cls;
}
};
dojo.toggleClass=function(node,_1ba,_1bb){
if(_1bb===undefined){
_1bb=!d.hasClass(node,_1ba);
}
d[_1bb?"addClass":"removeClass"](node,_1ba);
};
})();
}
if(!dojo._hasResource["dojo._base.NodeList"]){
dojo._hasResource["dojo._base.NodeList"]=true;
dojo.provide("dojo._base.NodeList");
(function(){
var d=dojo;
var ap=Array.prototype,aps=ap.slice,apc=ap.concat;
var tnl=function(a,_1bc,_1bd){
if(!a.sort){
a=aps.call(a,0);
}
var ctor=_1bd||this._NodeListCtor||d._NodeListCtor;
a.constructor=ctor;
dojo._mixin(a,ctor.prototype);
a._NodeListCtor=ctor;
return _1bc?a._stash(_1bc):a;
};
var _1be=function(f,a,o){
a=[0].concat(aps.call(a,0));
o=o||d.global;
return function(node){
a[0]=node;
return f.apply(o,a);
};
};
var _1bf=function(f,o){
return function(){
this.forEach(_1be(f,arguments,o));
return this;
};
};
var _1c0=function(f,o){
return function(){
return this.map(_1be(f,arguments,o));
};
};
var _1c1=function(f,o){
return function(){
return this.filter(_1be(f,arguments,o));
};
};
var _1c2=function(f,g,o){
return function(){
var a=arguments,body=_1be(f,a,o);
if(g.call(o||d.global,a)){
return this.map(body);
}
this.forEach(body);
return this;
};
};
var _1c3=function(a){
return a.length==1&&(typeof a[0]=="string");
};
var _1c4=function(node){
var p=node.parentNode;
if(p){
p.removeChild(node);
}
};
dojo.NodeList=function(){
return tnl(Array.apply(null,arguments));
};
d._NodeListCtor=d.NodeList;
var nl=d.NodeList,nlp=nl.prototype;
nl._wrap=nlp._wrap=tnl;
nl._adaptAsMap=_1c0;
nl._adaptAsForEach=_1bf;
nl._adaptAsFilter=_1c1;
nl._adaptWithCondition=_1c2;
d.forEach(["slice","splice"],function(name){
var f=ap[name];
nlp[name]=function(){
return this._wrap(f.apply(this,arguments),name=="slice"?this:null);
};
});
d.forEach(["indexOf","lastIndexOf","every","some"],function(name){
var f=d[name];
nlp[name]=function(){
return f.apply(d,[this].concat(aps.call(arguments,0)));
};
});
d.forEach(["attr","style"],function(name){
nlp[name]=_1c2(d[name],_1c3);
});
d.forEach(["connect","addClass","removeClass","toggleClass","empty","removeAttr"],function(name){
nlp[name]=_1bf(d[name]);
});
dojo.extend(dojo.NodeList,{_normalize:function(_1c5,_1c6){
var _1c7=_1c5.parse===true?true:false;
if(typeof _1c5.template=="string"){
var _1c8=_1c5.templateFunc||(dojo.string&&dojo.string.substitute);
_1c5=_1c8?_1c8(_1c5.template,_1c5):_1c5;
}
var type=(typeof _1c5);
if(type=="string"||type=="number"){
_1c5=dojo._toDom(_1c5,(_1c6&&_1c6.ownerDocument));
if(_1c5.nodeType==11){
_1c5=dojo._toArray(_1c5.childNodes);
}else{
_1c5=[_1c5];
}
}else{
if(!dojo.isArrayLike(_1c5)){
_1c5=[_1c5];
}else{
if(!dojo.isArray(_1c5)){
_1c5=dojo._toArray(_1c5);
}
}
}
if(_1c7){
_1c5._runParse=true;
}
return _1c5;
},_cloneNode:function(node){
return node.cloneNode(true);
},_place:function(ary,_1c9,_1ca,_1cb){
if(_1c9.nodeType!=1&&_1ca=="only"){
return;
}
var _1cc=_1c9,_1cd;
var _1ce=ary.length;
for(var i=_1ce-1;i>=0;i--){
var node=(_1cb?this._cloneNode(ary[i]):ary[i]);
if(ary._runParse&&dojo.parser&&dojo.parser.parse){
if(!_1cd){
_1cd=_1cc.ownerDocument.createElement("div");
}
_1cd.appendChild(node);
dojo.parser.parse(_1cd);
node=_1cd.firstChild;
while(_1cd.firstChild){
_1cd.removeChild(_1cd.firstChild);
}
}
if(i==_1ce-1){
dojo.place(node,_1cc,_1ca);
}else{
_1cc.parentNode.insertBefore(node,_1cc);
}
_1cc=node;
}
},_stash:function(_1cf){
this._parent=_1cf;
return this;
},end:function(){
if(this._parent){
return this._parent;
}else{
return new this._NodeListCtor();
}
},concat:function(item){
var t=d.isArray(this)?this:aps.call(this,0),m=d.map(arguments,function(a){
return a&&!d.isArray(a)&&(typeof NodeList!="undefined"&&a.constructor===NodeList||a.constructor===this._NodeListCtor)?aps.call(a,0):a;
});
return this._wrap(apc.apply(t,m),this);
},map:function(func,obj){
return this._wrap(d.map(this,func,obj),this);
},forEach:function(_1d0,_1d1){
d.forEach(this,_1d0,_1d1);
return this;
},coords:_1c0(d.coords),position:_1c0(d.position),place:function(_1d2,_1d3){
var item=d.query(_1d2)[0];
return this.forEach(function(node){
d.place(node,item,_1d3);
});
},orphan:function(_1d4){
return (_1d4?d._filterQueryResult(this,_1d4):this).forEach(_1c4);
},adopt:function(_1d5,_1d6){
return d.query(_1d5).place(this[0],_1d6)._stash(this);
},query:function(_1d7){
if(!_1d7){
return this;
}
var ret=this.map(function(node){
return d.query(_1d7,node).filter(function(_1d8){
return _1d8!==undefined;
});
});
return this._wrap(apc.apply([],ret),this);
},filter:function(_1d9){
var a=arguments,_1da=this,_1db=0;
if(typeof _1d9=="string"){
_1da=d._filterQueryResult(this,a[0]);
if(a.length==1){
return _1da._stash(this);
}
_1db=1;
}
return this._wrap(d.filter(_1da,a[_1db],a[_1db+1]),this);
},addContent:function(_1dc,_1dd){
_1dc=this._normalize(_1dc,this[0]);
for(var i=0,node;node=this[i];i++){
this._place(_1dc,node,_1dd,i>0);
}
return this;
},instantiate:function(_1de,_1df){
var c=d.isFunction(_1de)?_1de:d.getObject(_1de);
_1df=_1df||{};
return this.forEach(function(node){
new c(_1df,node);
});
},at:function(){
var t=new this._NodeListCtor();
d.forEach(arguments,function(i){
if(i<0){
i=this.length+i;
}
if(this[i]){
t.push(this[i]);
}
},this);
return t._stash(this);
}});
nl.events=["blur","focus","change","click","error","keydown","keypress","keyup","load","mousedown","mouseenter","mouseleave","mousemove","mouseout","mouseover","mouseup","submit"];
d.forEach(nl.events,function(evt){
var _1e0="on"+evt;
nlp[_1e0]=function(a,b){
return this.connect(_1e0,a,b);
};
});
})();
}
if(!dojo._hasResource["dojo._base.query"]){
dojo._hasResource["dojo._base.query"]=true;
if(typeof dojo!="undefined"){
dojo.provide("dojo._base.query");
}
(function(d){
var trim=d.trim;
var each=d.forEach;
var qlc=d._NodeListCtor=d.NodeList;
var _1e1=function(){
return d.doc;
};
var _1e2=((d.isWebKit||d.isMozilla)&&((_1e1().compatMode)=="BackCompat"));
var _1e3=!!_1e1().firstChild["children"]?"children":"childNodes";
var _1e4=">~+";
var _1e5=false;
var _1e6=function(){
return true;
};
var _1e7=function(_1e8){
if(_1e4.indexOf(_1e8.slice(-1))>=0){
_1e8+=" * ";
}else{
_1e8+=" ";
}
var ts=function(s,e){
return trim(_1e8.slice(s,e));
};
var _1e9=[];
var _1ea=-1,_1eb=-1,_1ec=-1,_1ed=-1,_1ee=-1,inId=-1,_1ef=-1,lc="",cc="",_1f0;
var x=0,ql=_1e8.length,_1f1=null,_1f2=null;
var _1f3=function(){
if(_1ef>=0){
var tv=(_1ef==x)?null:ts(_1ef,x);
_1f1[(_1e4.indexOf(tv)<0)?"tag":"oper"]=tv;
_1ef=-1;
}
};
var _1f4=function(){
if(inId>=0){
_1f1.id=ts(inId,x).replace(/\\/g,"");
inId=-1;
}
};
var _1f5=function(){
if(_1ee>=0){
_1f1.classes.push(ts(_1ee+1,x).replace(/\\/g,""));
_1ee=-1;
}
};
var _1f6=function(){
_1f4();
_1f3();
_1f5();
};
var _1f7=function(){
_1f6();
if(_1ed>=0){
_1f1.pseudos.push({name:ts(_1ed+1,x)});
}
_1f1.loops=(_1f1.pseudos.length||_1f1.attrs.length||_1f1.classes.length);
_1f1.oquery=_1f1.query=ts(_1f0,x);
_1f1.otag=_1f1.tag=(_1f1["oper"])?null:(_1f1.tag||"*");
if(_1f1.tag){
_1f1.tag=_1f1.tag.toUpperCase();
}
if(_1e9.length&&(_1e9[_1e9.length-1].oper)){
_1f1.infixOper=_1e9.pop();
_1f1.query=_1f1.infixOper.query+" "+_1f1.query;
}
_1e9.push(_1f1);
_1f1=null;
};
for(;lc=cc,cc=_1e8.charAt(x),x<ql;x++){
if(lc=="\\"){
continue;
}
if(!_1f1){
_1f0=x;
_1f1={query:null,pseudos:[],attrs:[],classes:[],tag:null,oper:null,id:null,getTag:function(){
return (_1e5)?this.otag:this.tag;
}};
_1ef=x;
}
if(_1ea>=0){
if(cc=="]"){
if(!_1f2.attr){
_1f2.attr=ts(_1ea+1,x);
}else{
_1f2.matchFor=ts((_1ec||_1ea+1),x);
}
var cmf=_1f2.matchFor;
if(cmf){
if((cmf.charAt(0)=="\"")||(cmf.charAt(0)=="'")){
_1f2.matchFor=cmf.slice(1,-1);
}
}
_1f1.attrs.push(_1f2);
_1f2=null;
_1ea=_1ec=-1;
}else{
if(cc=="="){
var _1f8=("|~^$*".indexOf(lc)>=0)?lc:"";
_1f2.type=_1f8+cc;
_1f2.attr=ts(_1ea+1,x-_1f8.length);
_1ec=x+1;
}
}
}else{
if(_1eb>=0){
if(cc==")"){
if(_1ed>=0){
_1f2.value=ts(_1eb+1,x);
}
_1ed=_1eb=-1;
}
}else{
if(cc=="#"){
_1f6();
inId=x+1;
}else{
if(cc=="."){
_1f6();
_1ee=x;
}else{
if(cc==":"){
_1f6();
_1ed=x;
}else{
if(cc=="["){
_1f6();
_1ea=x;
_1f2={};
}else{
if(cc=="("){
if(_1ed>=0){
_1f2={name:ts(_1ed+1,x),value:null};
_1f1.pseudos.push(_1f2);
}
_1eb=x;
}else{
if((cc==" ")&&(lc!=cc)){
_1f7();
}
}
}
}
}
}
}
}
}
return _1e9;
};
var _1f9=function(_1fa,_1fb){
if(!_1fa){
return _1fb;
}
if(!_1fb){
return _1fa;
}
return function(){
return _1fa.apply(window,arguments)&&_1fb.apply(window,arguments);
};
};
var _1fc=function(i,arr){
var r=arr||[];
if(i){
r.push(i);
}
return r;
};
var _1fd=function(n){
return (1==n.nodeType);
};
var _1fe="";
var _1ff=function(elem,attr){
if(!elem){
return _1fe;
}
if(attr=="class"){
return elem.className||_1fe;
}
if(attr=="for"){
return elem.htmlFor||_1fe;
}
if(attr=="style"){
return elem.style.cssText||_1fe;
}
return (_1e5?elem.getAttribute(attr):elem.getAttribute(attr,2))||_1fe;
};
var _200={"*=":function(attr,_201){
return function(elem){
return (_1ff(elem,attr).indexOf(_201)>=0);
};
},"^=":function(attr,_202){
return function(elem){
return (_1ff(elem,attr).indexOf(_202)==0);
};
},"$=":function(attr,_203){
var tval=" "+_203;
return function(elem){
var ea=" "+_1ff(elem,attr);
return (ea.lastIndexOf(_203)==(ea.length-_203.length));
};
},"~=":function(attr,_204){
var tval=" "+_204+" ";
return function(elem){
var ea=" "+_1ff(elem,attr)+" ";
return (ea.indexOf(tval)>=0);
};
},"|=":function(attr,_205){
var _206=" "+_205+"-";
return function(elem){
var ea=" "+_1ff(elem,attr);
return ((ea==_205)||(ea.indexOf(_206)==0));
};
},"=":function(attr,_207){
return function(elem){
return (_1ff(elem,attr)==_207);
};
}};
var _208=(typeof _1e1().firstChild.nextElementSibling=="undefined");
var _209=!_208?"nextElementSibling":"nextSibling";
var _20a=!_208?"previousElementSibling":"previousSibling";
var _20b=(_208?_1fd:_1e6);
var _20c=function(node){
while(node=node[_20a]){
if(_20b(node)){
return false;
}
}
return true;
};
var _20d=function(node){
while(node=node[_209]){
if(_20b(node)){
return false;
}
}
return true;
};
var _20e=function(node){
var root=node.parentNode;
var i=0,tret=root[_1e3],ci=(node["_i"]||-1),cl=(root["_l"]||-1);
if(!tret){
return -1;
}
var l=tret.length;
if(cl==l&&ci>=0&&cl>=0){
return ci;
}
root["_l"]=l;
ci=-1;
for(var te=root["firstElementChild"]||root["firstChild"];te;te=te[_209]){
if(_20b(te)){
te["_i"]=++i;
if(node===te){
ci=i;
}
}
}
return ci;
};
var _20f=function(elem){
return !((_20e(elem))%2);
};
var _210=function(elem){
return ((_20e(elem))%2);
};
var _211={"checked":function(name,_212){
return function(elem){
return !!("checked" in elem?elem.checked:elem.selected);
};
},"first-child":function(){
return _20c;
},"last-child":function(){
return _20d;
},"only-child":function(name,_213){
return function(node){
if(!_20c(node)){
return false;
}
if(!_20d(node)){
return false;
}
return true;
};
},"empty":function(name,_214){
return function(elem){
var cn=elem.childNodes;
var cnl=elem.childNodes.length;
for(var x=cnl-1;x>=0;x--){
var nt=cn[x].nodeType;
if((nt===1)||(nt==3)){
return false;
}
}
return true;
};
},"contains":function(name,_215){
var cz=_215.charAt(0);
if(cz=="\""||cz=="'"){
_215=_215.slice(1,-1);
}
return function(elem){
return (elem.innerHTML.indexOf(_215)>=0);
};
},"not":function(name,_216){
var p=_1e7(_216)[0];
var _217={el:1};
if(p.tag!="*"){
_217.tag=1;
}
if(!p.classes.length){
_217.classes=1;
}
var ntf=_218(p,_217);
return function(elem){
return (!ntf(elem));
};
},"nth-child":function(name,_219){
var pi=parseInt;
if(_219=="odd"){
return _210;
}else{
if(_219=="even"){
return _20f;
}
}
if(_219.indexOf("n")!=-1){
var _21a=_219.split("n",2);
var pred=_21a[0]?((_21a[0]=="-")?-1:pi(_21a[0])):1;
var idx=_21a[1]?pi(_21a[1]):0;
var lb=0,ub=-1;
if(pred>0){
if(idx<0){
idx=(idx%pred)&&(pred+(idx%pred));
}else{
if(idx>0){
if(idx>=pred){
lb=idx-idx%pred;
}
idx=idx%pred;
}
}
}else{
if(pred<0){
pred*=-1;
if(idx>0){
ub=idx;
idx=idx%pred;
}
}
}
if(pred>0){
return function(elem){
var i=_20e(elem);
return (i>=lb)&&(ub<0||i<=ub)&&((i%pred)==idx);
};
}else{
_219=idx;
}
}
var _21b=pi(_219);
return function(elem){
return (_20e(elem)==_21b);
};
}};
var _21c=(d.isIE)?function(cond){
var clc=cond.toLowerCase();
if(clc=="class"){
cond="className";
}
return function(elem){
return (_1e5?elem.getAttribute(cond):elem[cond]||elem[clc]);
};
}:function(cond){
return function(elem){
return (elem&&elem.getAttribute&&elem.hasAttribute(cond));
};
};
var _218=function(_21d,_21e){
if(!_21d){
return _1e6;
}
_21e=_21e||{};
var ff=null;
if(!("el" in _21e)){
ff=_1f9(ff,_1fd);
}
if(!("tag" in _21e)){
if(_21d.tag!="*"){
ff=_1f9(ff,function(elem){
return (elem&&(elem.tagName==_21d.getTag()));
});
}
}
if(!("classes" in _21e)){
each(_21d.classes,function(_21f,idx,arr){
var re=new RegExp("(?:^|\\s)"+_21f+"(?:\\s|$)");
ff=_1f9(ff,function(elem){
return re.test(elem.className);
});
ff.count=idx;
});
}
if(!("pseudos" in _21e)){
each(_21d.pseudos,function(_220){
var pn=_220.name;
if(_211[pn]){
ff=_1f9(ff,_211[pn](pn,_220.value));
}
});
}
if(!("attrs" in _21e)){
each(_21d.attrs,function(attr){
var _221;
var a=attr.attr;
if(attr.type&&_200[attr.type]){
_221=_200[attr.type](a,attr.matchFor);
}else{
if(a.length){
_221=_21c(a);
}
}
if(_221){
ff=_1f9(ff,_221);
}
});
}
if(!("id" in _21e)){
if(_21d.id){
ff=_1f9(ff,function(elem){
return (!!elem&&(elem.id==_21d.id));
});
}
}
if(!ff){
if(!("default" in _21e)){
ff=_1e6;
}
}
return ff;
};
var _222=function(_223){
return function(node,ret,bag){
while(node=node[_209]){
if(_208&&(!_1fd(node))){
continue;
}
if((!bag||_224(node,bag))&&_223(node)){
ret.push(node);
}
break;
}
return ret;
};
};
var _225=function(_226){
return function(root,ret,bag){
var te=root[_209];
while(te){
if(_20b(te)){
if(bag&&!_224(te,bag)){
break;
}
if(_226(te)){
ret.push(te);
}
}
te=te[_209];
}
return ret;
};
};
var _227=function(_228){
_228=_228||_1e6;
return function(root,ret,bag){
var te,x=0,tret=root[_1e3];
while(te=tret[x++]){
if(_20b(te)&&(!bag||_224(te,bag))&&(_228(te,x))){
ret.push(te);
}
}
return ret;
};
};
var _229=function(node,root){
var pn=node.parentNode;
while(pn){
if(pn==root){
break;
}
pn=pn.parentNode;
}
return !!pn;
};
var _22a={};
var _22b=function(_22c){
var _22d=_22a[_22c.query];
if(_22d){
return _22d;
}
var io=_22c.infixOper;
var oper=(io?io.oper:"");
var _22e=_218(_22c,{el:1});
var qt=_22c.tag;
var _22f=("*"==qt);
var ecs=_1e1()["getElementsByClassName"];
if(!oper){
if(_22c.id){
_22e=(!_22c.loops&&_22f)?_1e6:_218(_22c,{el:1,id:1});
_22d=function(root,arr){
var te=d.byId(_22c.id,(root.ownerDocument||root));
if(!te||!_22e(te)){
return;
}
if(9==root.nodeType){
return _1fc(te,arr);
}else{
if(_229(te,root)){
return _1fc(te,arr);
}
}
};
}else{
if(ecs&&/\{\s*\[native code\]\s*\}/.test(String(ecs))&&_22c.classes.length&&!_1e2){
_22e=_218(_22c,{el:1,classes:1,id:1});
var _230=_22c.classes.join(" ");
_22d=function(root,arr,bag){
var ret=_1fc(0,arr),te,x=0;
var tret=root.getElementsByClassName(_230);
while((te=tret[x++])){
if(_22e(te,root)&&_224(te,bag)){
ret.push(te);
}
}
return ret;
};
}else{
if(!_22f&&!_22c.loops){
_22d=function(root,arr,bag){
var ret=_1fc(0,arr),te,x=0;
var tret=root.getElementsByTagName(_22c.getTag());
while((te=tret[x++])){
if(_224(te,bag)){
ret.push(te);
}
}
return ret;
};
}else{
_22e=_218(_22c,{el:1,tag:1,id:1});
_22d=function(root,arr,bag){
var ret=_1fc(0,arr),te,x=0;
var tret=root.getElementsByTagName(_22c.getTag());
while((te=tret[x++])){
if(_22e(te,root)&&_224(te,bag)){
ret.push(te);
}
}
return ret;
};
}
}
}
}else{
var _231={el:1};
if(_22f){
_231.tag=1;
}
_22e=_218(_22c,_231);
if("+"==oper){
_22d=_222(_22e);
}else{
if("~"==oper){
_22d=_225(_22e);
}else{
if(">"==oper){
_22d=_227(_22e);
}
}
}
}
return _22a[_22c.query]=_22d;
};
var _232=function(root,_233){
var _234=_1fc(root),qp,x,te,qpl=_233.length,bag,ret;
for(var i=0;i<qpl;i++){
ret=[];
qp=_233[i];
x=_234.length-1;
if(x>0){
bag={};
ret.nozip=true;
}
var gef=_22b(qp);
for(var j=0;(te=_234[j]);j++){
gef(te,ret,bag);
}
if(!ret.length){
break;
}
_234=ret;
}
return ret;
};
var _235={},_236={};
var _237=function(_238){
var _239=_1e7(trim(_238));
if(_239.length==1){
var tef=_22b(_239[0]);
return function(root){
var r=tef(root,new qlc());
if(r){
r.nozip=true;
}
return r;
};
}
return function(root){
return _232(root,_239);
};
};
var nua=navigator.userAgent;
var wk="WebKit/";
var _23a=(d.isWebKit&&(nua.indexOf(wk)>0)&&(parseFloat(nua.split(wk)[1])>528));
var _23b=d.isIE?"commentStrip":"nozip";
var qsa="querySelectorAll";
var _23c=(!!_1e1()[qsa]&&(!d.isSafari||(d.isSafari>3.1)||_23a));
var _23d=/n\+\d|([^ ])?([>~+])([^ =])?/g;
var _23e=function(_23f,pre,ch,post){
return ch?(pre?pre+" ":"")+ch+(post?" "+post:""):_23f;
};
var _240=function(_241,_242){
_241=_241.replace(_23d,_23e);
if(_23c){
var _243=_236[_241];
if(_243&&!_242){
return _243;
}
}
var _244=_235[_241];
if(_244){
return _244;
}
var qcz=_241.charAt(0);
var _245=(-1==_241.indexOf(" "));
if((_241.indexOf("#")>=0)&&(_245)){
_242=true;
}
var _246=(_23c&&(!_242)&&(_1e4.indexOf(qcz)==-1)&&(!d.isIE||(_241.indexOf(":")==-1))&&(!(_1e2&&(_241.indexOf(".")>=0)))&&(_241.indexOf(":contains")==-1)&&(_241.indexOf(":checked")==-1)&&(_241.indexOf("|=")==-1));
if(_246){
var tq=(_1e4.indexOf(_241.charAt(_241.length-1))>=0)?(_241+" *"):_241;
return _236[_241]=function(root){
try{
if(!((9==root.nodeType)||_245)){
throw "";
}
var r=root[qsa](tq);
r[_23b]=true;
return r;
}
catch(e){
return _240(_241,true)(root);
}
};
}else{
var _247=_241.split(/\s*,\s*/);
return _235[_241]=((_247.length<2)?_237(_241):function(root){
var _248=0,ret=[],tp;
while((tp=_247[_248++])){
ret=ret.concat(_237(tp)(root));
}
return ret;
});
}
};
var _249=0;
var _24a=d.isIE?function(node){
if(_1e5){
return (node.getAttribute("_uid")||node.setAttribute("_uid",++_249)||_249);
}else{
return node.uniqueID;
}
}:function(node){
return (node._uid||(node._uid=++_249));
};
var _224=function(node,bag){
if(!bag){
return 1;
}
var id=_24a(node);
if(!bag[id]){
return bag[id]=1;
}
return 0;
};
var _24b="_zipIdx";
var _24c=function(arr){
if(arr&&arr.nozip){
return (qlc._wrap)?qlc._wrap(arr):arr;
}
var ret=new qlc();
if(!arr||!arr.length){
return ret;
}
if(arr[0]){
ret.push(arr[0]);
}
if(arr.length<2){
return ret;
}
_249++;
if(d.isIE&&_1e5){
var _24d=_249+"";
arr[0].setAttribute(_24b,_24d);
for(var x=1,te;te=arr[x];x++){
if(arr[x].getAttribute(_24b)!=_24d){
ret.push(te);
}
te.setAttribute(_24b,_24d);
}
}else{
if(d.isIE&&arr.commentStrip){
try{
for(var x=1,te;te=arr[x];x++){
if(_1fd(te)){
ret.push(te);
}
}
}
catch(e){
}
}else{
if(arr[0]){
arr[0][_24b]=_249;
}
for(var x=1,te;te=arr[x];x++){
if(arr[x][_24b]!=_249){
ret.push(te);
}
te[_24b]=_249;
}
}
}
return ret;
};
d.query=function(_24e,root){
qlc=d._NodeListCtor;
if(!_24e){
return new qlc();
}
if(_24e.constructor==qlc){
return _24e;
}
if(typeof _24e!="string"){
return new qlc(_24e);
}
if(typeof root=="string"){
root=d.byId(root);
if(!root){
return new qlc();
}
}
root=root||_1e1();
var od=root.ownerDocument||root.documentElement;
_1e5=(root.contentType&&root.contentType=="application/xml")||(d.isOpera&&(root.doctype||od.toString()=="[object XMLDocument]"))||(!!od)&&(d.isIE?od.xml:(root.xmlVersion||od.xmlVersion));
var r=_240(_24e)(root);
if(r&&r.nozip&&!qlc._wrap){
return r;
}
return _24c(r);
};
d.query.pseudos=_211;
d._filterQueryResult=function(_24f,_250){
var _251=new d._NodeListCtor();
var _252=_218(_1e7(_250)[0]);
for(var x=0,te;te=_24f[x];x++){
if(_252(te)){
_251.push(te);
}
}
return _251;
};
})(this["queryPortability"]||this["acme"]||dojo);
}
if(!dojo._hasResource["dojo._base.xhr"]){
dojo._hasResource["dojo._base.xhr"]=true;
dojo.provide("dojo._base.xhr");
(function(){
var _253=dojo,cfg=_253.config;
function _254(obj,name,_255){
if(_255===null){
return;
}
var val=obj[name];
if(typeof val=="string"){
obj[name]=[val,_255];
}else{
if(_253.isArray(val)){
val.push(_255);
}else{
obj[name]=_255;
}
}
};
dojo.fieldToObject=function(_256){
var ret=null;
var item=_253.byId(_256);
if(item){
var _257=item.name;
var type=(item.type||"").toLowerCase();
if(_257&&type&&!item.disabled){
if(type=="radio"||type=="checkbox"){
if(item.checked){
ret=item.value;
}
}else{
if(item.multiple){
ret=[];
_253.query("option",item).forEach(function(opt){
if(opt.selected){
ret.push(opt.value);
}
});
}else{
ret=item.value;
}
}
}
}
return ret;
};
dojo.formToObject=function(_258){
var ret={};
var _259="file|submit|image|reset|button|";
_253.forEach(dojo.byId(_258).elements,function(item){
var _25a=item.name;
var type=(item.type||"").toLowerCase();
if(_25a&&type&&_259.indexOf(type)==-1&&!item.disabled){
_254(ret,_25a,_253.fieldToObject(item));
if(type=="image"){
ret[_25a+".x"]=ret[_25a+".y"]=ret[_25a].x=ret[_25a].y=0;
}
}
});
return ret;
};
dojo.objectToQuery=function(map){
var enc=encodeURIComponent;
var _25b=[];
var _25c={};
for(var name in map){
var _25d=map[name];
if(_25d!=_25c[name]){
var _25e=enc(name)+"=";
if(_253.isArray(_25d)){
for(var i=0;i<_25d.length;i++){
_25b.push(_25e+enc(_25d[i]));
}
}else{
_25b.push(_25e+enc(_25d));
}
}
}
return _25b.join("&");
};
dojo.formToQuery=function(_25f){
return _253.objectToQuery(_253.formToObject(_25f));
};
dojo.formToJson=function(_260,_261){
return _253.toJson(_253.formToObject(_260),_261);
};
dojo.queryToObject=function(str){
var ret={};
var qp=str.split("&");
var dec=decodeURIComponent;
_253.forEach(qp,function(item){
if(item.length){
var _262=item.split("=");
var name=dec(_262.shift());
var val=dec(_262.join("="));
if(typeof ret[name]=="string"){
ret[name]=[ret[name]];
}
if(_253.isArray(ret[name])){
ret[name].push(val);
}else{
ret[name]=val;
}
}
});
return ret;
};
dojo._blockAsync=false;
var _263=_253._contentHandlers=dojo.contentHandlers={text:function(xhr){
return xhr.responseText;
},json:function(xhr){
return _253.fromJson(xhr.responseText||null);
},"json-comment-filtered":function(xhr){
if(!dojo.config.useCommentedJson){
console.warn("Consider using the standard mimetype:application/json."+" json-commenting can introduce security issues. To"+" decrease the chances of hijacking, use the standard the 'json' handler and"+" prefix your json with: {}&&\n"+"Use djConfig.useCommentedJson=true to turn off this message.");
}
var _264=xhr.responseText;
var _265=_264.indexOf("/*");
var _266=_264.lastIndexOf("*/");
if(_265==-1||_266==-1){
throw new Error("JSON was not comment filtered");
}
return _253.fromJson(_264.substring(_265+2,_266));
},javascript:function(xhr){
return _253.eval(xhr.responseText);
},xml:function(xhr){
var _267=xhr.responseXML;
if(_253.isIE&&(!_267||!_267.documentElement)){
var ms=function(n){
return "MSXML"+n+".DOMDocument";
};
var dp=["Microsoft.XMLDOM",ms(6),ms(4),ms(3),ms(2)];
_253.some(dp,function(p){
try{
var dom=new ActiveXObject(p);
dom.async=false;
dom.loadXML(xhr.responseText);
_267=dom;
}
catch(e){
return false;
}
return true;
});
}
return _267;
},"json-comment-optional":function(xhr){
if(xhr.responseText&&/^[^{\[]*\/\*/.test(xhr.responseText)){
return _263["json-comment-filtered"](xhr);
}else{
return _263["json"](xhr);
}
}};
dojo._ioSetArgs=function(args,_268,_269,_26a){
var _26b={args:args,url:args.url};
var _26c=null;
if(args.form){
var form=_253.byId(args.form);
var _26d=form.getAttributeNode("action");
_26b.url=_26b.url||(_26d?_26d.value:null);
_26c=_253.formToObject(form);
}
var _26e=[{}];
if(_26c){
_26e.push(_26c);
}
if(args.content){
_26e.push(args.content);
}
if(args.preventCache){
_26e.push({"dojo.preventCache":new Date().valueOf()});
}
_26b.query=_253.objectToQuery(_253.mixin.apply(null,_26e));
_26b.handleAs=args.handleAs||"text";
var d=new _253.Deferred(_268);
d.addCallbacks(_269,function(_26f){
return _26a(_26f,d);
});
var ld=args.load;
if(ld&&_253.isFunction(ld)){
d.addCallback(function(_270){
return ld.call(args,_270,_26b);
});
}
var err=args.error;
if(err&&_253.isFunction(err)){
d.addErrback(function(_271){
return err.call(args,_271,_26b);
});
}
var _272=args.handle;
if(_272&&_253.isFunction(_272)){
d.addBoth(function(_273){
return _272.call(args,_273,_26b);
});
}
if(cfg.ioPublish&&_253.publish&&_26b.args.ioPublish!==false){
d.addCallbacks(function(res){
_253.publish("/dojo/io/load",[d,res]);
return res;
},function(res){
_253.publish("/dojo/io/error",[d,res]);
return res;
});
d.addBoth(function(res){
_253.publish("/dojo/io/done",[d,res]);
return res;
});
}
d.ioArgs=_26b;
return d;
};
var _274=function(dfd){
dfd.canceled=true;
var xhr=dfd.ioArgs.xhr;
var _275=typeof xhr.abort;
if(_275=="function"||_275=="object"||_275=="unknown"){
xhr.abort();
}
var err=dfd.ioArgs.error;
if(!err){
err=new Error("xhr cancelled");
err.dojoType="cancel";
}
return err;
};
var _276=function(dfd){
var ret=_263[dfd.ioArgs.handleAs](dfd.ioArgs.xhr);
return ret===undefined?null:ret;
};
var _277=function(_278,dfd){
if(!dfd.ioArgs.args.failOk){
console.error(_278);
}
return _278;
};
var _279=null;
var _27a=[];
var _27b=0;
var _27c=function(dfd){
if(_27b<=0){
_27b=0;
if(cfg.ioPublish&&_253.publish&&(!dfd||dfd&&dfd.ioArgs.args.ioPublish!==false)){
_253.publish("/dojo/io/stop");
}
}
};
var _27d=function(){
var now=(new Date()).getTime();
if(!_253._blockAsync){
for(var i=0,tif;i<_27a.length&&(tif=_27a[i]);i++){
var dfd=tif.dfd;
var func=function(){
if(!dfd||dfd.canceled||!tif.validCheck(dfd)){
_27a.splice(i--,1);
_27b-=1;
}else{
if(tif.ioCheck(dfd)){
_27a.splice(i--,1);
tif.resHandle(dfd);
_27b-=1;
}else{
if(dfd.startTime){
if(dfd.startTime+(dfd.ioArgs.args.timeout||0)<now){
_27a.splice(i--,1);
var err=new Error("timeout exceeded");
err.dojoType="timeout";
dfd.errback(err);
dfd.cancel();
_27b-=1;
}
}
}
}
};
if(dojo.config.debugAtAllCosts){
func.call(this);
}else{
try{
func.call(this);
}
catch(e){
dfd.errback(e);
}
}
}
}
_27c(dfd);
if(!_27a.length){
clearInterval(_279);
_279=null;
return;
}
};
dojo._ioCancelAll=function(){
try{
_253.forEach(_27a,function(i){
try{
i.dfd.cancel();
}
catch(e){
}
});
}
catch(e){
}
};
if(_253.isIE){
_253.addOnWindowUnload(_253._ioCancelAll);
}
_253._ioNotifyStart=function(dfd){
if(cfg.ioPublish&&_253.publish&&dfd.ioArgs.args.ioPublish!==false){
if(!_27b){
_253.publish("/dojo/io/start");
}
_27b+=1;
_253.publish("/dojo/io/send",[dfd]);
}
};
_253._ioWatch=function(dfd,_27e,_27f,_280){
var args=dfd.ioArgs.args;
if(args.timeout){
dfd.startTime=(new Date()).getTime();
}
_27a.push({dfd:dfd,validCheck:_27e,ioCheck:_27f,resHandle:_280});
if(!_279){
_279=setInterval(_27d,50);
}
if(args.sync){
_27d();
}
};
var _281="application/x-www-form-urlencoded";
var _282=function(dfd){
return dfd.ioArgs.xhr.readyState;
};
var _283=function(dfd){
return 4==dfd.ioArgs.xhr.readyState;
};
var _284=function(dfd){
var xhr=dfd.ioArgs.xhr;
if(_253._isDocumentOk(xhr)){
dfd.callback(dfd);
}else{
var err=new Error("Unable to load "+dfd.ioArgs.url+" status:"+xhr.status);
err.status=xhr.status;
err.responseText=xhr.responseText;
dfd.errback(err);
}
};
dojo._ioAddQueryToUrl=function(_285){
if(_285.query.length){
_285.url+=(_285.url.indexOf("?")==-1?"?":"&")+_285.query;
_285.query=null;
}
};
dojo.xhr=function(_286,args,_287){
var dfd=_253._ioSetArgs(args,_274,_276,_277);
var _288=dfd.ioArgs;
var xhr=_288.xhr=_253._xhrObj(_288.args);
if(!xhr){
dfd.cancel();
return dfd;
}
if("postData" in args){
_288.query=args.postData;
}else{
if("putData" in args){
_288.query=args.putData;
}else{
if("rawBody" in args){
_288.query=args.rawBody;
}else{
if((arguments.length>2&&!_287)||"POST|PUT".indexOf(_286.toUpperCase())==-1){
_253._ioAddQueryToUrl(_288);
}
}
}
}
xhr.open(_286,_288.url,args.sync!==true,args.user||undefined,args.password||undefined);
if(args.headers){
for(var hdr in args.headers){
if(hdr.toLowerCase()==="content-type"&&!args.contentType){
args.contentType=args.headers[hdr];
}else{
if(args.headers[hdr]){
xhr.setRequestHeader(hdr,args.headers[hdr]);
}
}
}
}
xhr.setRequestHeader("Content-Type",args.contentType||_281);
if(!args.headers||!("X-Requested-With" in args.headers)){
xhr.setRequestHeader("X-Requested-With","XMLHttpRequest");
}
_253._ioNotifyStart(dfd);
if(dojo.config.debugAtAllCosts){
xhr.send(_288.query);
}else{
try{
xhr.send(_288.query);
}
catch(e){
_288.error=e;
dfd.cancel();
}
}
_253._ioWatch(dfd,_282,_283,_284);
xhr=null;
return dfd;
};
dojo.xhrGet=function(args){
return _253.xhr("GET",args);
};
dojo.rawXhrPost=dojo.xhrPost=function(args){
return _253.xhr("POST",args,true);
};
dojo.rawXhrPut=dojo.xhrPut=function(args){
return _253.xhr("PUT",args,true);
};
dojo.xhrDelete=function(args){
return _253.xhr("DELETE",args);
};
})();
}
if(!dojo._hasResource["dojo._base.fx"]){
dojo._hasResource["dojo._base.fx"]=true;
dojo.provide("dojo._base.fx");
(function(){
var d=dojo;
var _289=d._mixin;
dojo._Line=function(_28a,end){
this.start=_28a;
this.end=end;
};
dojo._Line.prototype.getValue=function(n){
return ((this.end-this.start)*n)+this.start;
};
dojo.Animation=function(args){
_289(this,args);
if(d.isArray(this.curve)){
this.curve=new d._Line(this.curve[0],this.curve[1]);
}
};
d._Animation=d.Animation;
d.extend(dojo.Animation,{duration:350,repeat:0,rate:20,_percent:0,_startRepeatCount:0,_getStep:function(){
var _28b=this._percent,_28c=this.easing;
return _28c?_28c(_28b):_28b;
},_fire:function(evt,args){
var a=args||[];
if(this[evt]){
if(d.config.debugAtAllCosts){
this[evt].apply(this,a);
}else{
try{
this[evt].apply(this,a);
}
catch(e){
console.error("exception in animation handler for:",evt);
console.error(e);
}
}
}
return this;
},play:function(_28d,_28e){
var _28f=this;
if(_28f._delayTimer){
_28f._clearTimer();
}
if(_28e){
_28f._stopTimer();
_28f._active=_28f._paused=false;
_28f._percent=0;
}else{
if(_28f._active&&!_28f._paused){
return _28f;
}
}
_28f._fire("beforeBegin",[_28f.node]);
var de=_28d||_28f.delay,_290=dojo.hitch(_28f,"_play",_28e);
if(de>0){
_28f._delayTimer=setTimeout(_290,de);
return _28f;
}
_290();
return _28f;
},_play:function(_291){
var _292=this;
if(_292._delayTimer){
_292._clearTimer();
}
_292._startTime=new Date().valueOf();
if(_292._paused){
_292._startTime-=_292.duration*_292._percent;
}
_292._active=true;
_292._paused=false;
var _293=_292.curve.getValue(_292._getStep());
if(!_292._percent){
if(!_292._startRepeatCount){
_292._startRepeatCount=_292.repeat;
}
_292._fire("onBegin",[_293]);
}
_292._fire("onPlay",[_293]);
_292._cycle();
return _292;
},pause:function(){
var _294=this;
if(_294._delayTimer){
_294._clearTimer();
}
_294._stopTimer();
if(!_294._active){
return _294;
}
_294._paused=true;
_294._fire("onPause",[_294.curve.getValue(_294._getStep())]);
return _294;
},gotoPercent:function(_295,_296){
var _297=this;
_297._stopTimer();
_297._active=_297._paused=true;
_297._percent=_295;
if(_296){
_297.play();
}
return _297;
},stop:function(_298){
var _299=this;
if(_299._delayTimer){
_299._clearTimer();
}
if(!_299._timer){
return _299;
}
_299._stopTimer();
if(_298){
_299._percent=1;
}
_299._fire("onStop",[_299.curve.getValue(_299._getStep())]);
_299._active=_299._paused=false;
return _299;
},status:function(){
if(this._active){
return this._paused?"paused":"playing";
}
return "stopped";
},_cycle:function(){
var _29a=this;
if(_29a._active){
var curr=new Date().valueOf();
var step=(curr-_29a._startTime)/(_29a.duration);
if(step>=1){
step=1;
}
_29a._percent=step;
if(_29a.easing){
step=_29a.easing(step);
}
_29a._fire("onAnimate",[_29a.curve.getValue(step)]);
if(_29a._percent<1){
_29a._startTimer();
}else{
_29a._active=false;
if(_29a.repeat>0){
_29a.repeat--;
_29a.play(null,true);
}else{
if(_29a.repeat==-1){
_29a.play(null,true);
}else{
if(_29a._startRepeatCount){
_29a.repeat=_29a._startRepeatCount;
_29a._startRepeatCount=0;
}
}
}
_29a._percent=0;
_29a._fire("onEnd",[_29a.node]);
!_29a.repeat&&_29a._stopTimer();
}
}
return _29a;
},_clearTimer:function(){
clearTimeout(this._delayTimer);
delete this._delayTimer;
}});
var ctr=0,_29b=null,_29c={run:function(){
}};
d.extend(d.Animation,{_startTimer:function(){
if(!this._timer){
this._timer=d.connect(_29c,"run",this,"_cycle");
ctr++;
}
if(!_29b){
_29b=setInterval(d.hitch(_29c,"run"),this.rate);
}
},_stopTimer:function(){
if(this._timer){
d.disconnect(this._timer);
this._timer=null;
ctr--;
}
if(ctr<=0){
clearInterval(_29b);
_29b=null;
ctr=0;
}
}});
var _29d=d.isIE?function(node){
var ns=node.style;
if(!ns.width.length&&d.style(node,"width")=="auto"){
ns.width="auto";
}
}:function(){
};
dojo._fade=function(args){
args.node=d.byId(args.node);
var _29e=_289({properties:{}},args),_29f=(_29e.properties.opacity={});
_29f.start=!("start" in _29e)?function(){
return +d.style(_29e.node,"opacity")||0;
}:_29e.start;
_29f.end=_29e.end;
var anim=d.animateProperty(_29e);
d.connect(anim,"beforeBegin",d.partial(_29d,_29e.node));
return anim;
};
dojo.fadeIn=function(args){
return d._fade(_289({end:1},args));
};
dojo.fadeOut=function(args){
return d._fade(_289({end:0},args));
};
dojo._defaultEasing=function(n){
return 0.5+((Math.sin((n+1.5)*Math.PI))/2);
};
var _2a0=function(_2a1){
this._properties=_2a1;
for(var p in _2a1){
var prop=_2a1[p];
if(prop.start instanceof d.Color){
prop.tempColor=new d.Color();
}
}
};
_2a0.prototype.getValue=function(r){
var ret={};
for(var p in this._properties){
var prop=this._properties[p],_2a2=prop.start;
if(_2a2 instanceof d.Color){
ret[p]=d.blendColors(_2a2,prop.end,r,prop.tempColor).toCss();
}else{
if(!d.isArray(_2a2)){
ret[p]=((prop.end-_2a2)*r)+_2a2+(p!="opacity"?prop.units||"px":0);
}
}
}
return ret;
};
dojo.animateProperty=function(args){
var n=args.node=d.byId(args.node);
if(!args.easing){
args.easing=d._defaultEasing;
}
var anim=new d.Animation(args);
d.connect(anim,"beforeBegin",anim,function(){
var pm={};
for(var p in this.properties){
if(p=="width"||p=="height"){
this.node.display="block";
}
var prop=this.properties[p];
if(d.isFunction(prop)){
prop=prop(n);
}
prop=pm[p]=_289({},(d.isObject(prop)?prop:{end:prop}));
if(d.isFunction(prop.start)){
prop.start=prop.start(n);
}
if(d.isFunction(prop.end)){
prop.end=prop.end(n);
}
var _2a3=(p.toLowerCase().indexOf("color")>=0);
function _2a4(node,p){
var v={height:node.offsetHeight,width:node.offsetWidth}[p];
if(v!==undefined){
return v;
}
v=d.style(node,p);
return (p=="opacity")?+v:(_2a3?v:parseFloat(v));
};
if(!("end" in prop)){
prop.end=_2a4(n,p);
}else{
if(!("start" in prop)){
prop.start=_2a4(n,p);
}
}
if(_2a3){
prop.start=new d.Color(prop.start);
prop.end=new d.Color(prop.end);
}else{
prop.start=(p=="opacity")?+prop.start:parseFloat(prop.start);
}
}
this.curve=new _2a0(pm);
});
d.connect(anim,"onAnimate",d.hitch(d,"style",anim.node));
return anim;
};
dojo.anim=function(node,_2a5,_2a6,_2a7,_2a8,_2a9){
return d.animateProperty({node:node,duration:_2a6||d.Animation.prototype.duration,properties:_2a5,easing:_2a7,onEnd:_2a8}).play(_2a9||0);
};
})();
}
if(!dojo._hasResource["dojo._base.browser"]){
dojo._hasResource["dojo._base.browser"]=true;
dojo.provide("dojo._base.browser");
dojo.forEach(dojo.config.require,function(i){
dojo["require"](i);
});
}
if(!dojo._hasResource["dojo.window"]){
dojo._hasResource["dojo.window"]=true;
dojo.provide("dojo.window");
dojo.window.getBox=function(){
var _2aa=(dojo.doc.compatMode=="BackCompat")?dojo.body():dojo.doc.documentElement;
var _2ab=dojo._docScroll();
return {w:_2aa.clientWidth,h:_2aa.clientHeight,l:_2ab.x,t:_2ab.y};
};
dojo.window.get=function(doc){
if(dojo.isIE&&window!==document.parentWindow){
doc.parentWindow.execScript("document._parentWindow = window;","Javascript");
var win=doc._parentWindow;
doc._parentWindow=null;
return win;
}
return doc.parentWindow||doc.defaultView;
};
dojo.window.scrollIntoView=function(node,pos){
try{
node=dojo.byId(node);
var doc=node.ownerDocument||dojo.doc,body=doc.body||dojo.body(),html=doc.documentElement||body.parentNode,isIE=dojo.isIE,isWK=dojo.isWebKit;
if((!(dojo.isMoz||isIE||isWK||dojo.isOpera)||node==body||node==html)&&(typeof node.scrollIntoView!="undefined")){
node.scrollIntoView(false);
return;
}
var _2ac=doc.compatMode=="BackCompat",_2ad=_2ac?body:html,_2ae=isWK?body:_2ad,_2af=_2ad.clientWidth,_2b0=_2ad.clientHeight,rtl=!dojo._isBodyLtr(),_2b1=pos||dojo.position(node),el=node.parentNode,_2b2=function(el){
return ((isIE<=6||(isIE&&_2ac))?false:(dojo.style(el,"position").toLowerCase()=="fixed"));
};
if(_2b2(node)){
return;
}
while(el){
if(el==body){
el=_2ae;
}
var _2b3=dojo.position(el),_2b4=_2b2(el);
if(el==_2ae){
_2b3.w=_2af;
_2b3.h=_2b0;
if(_2ae==html&&isIE&&rtl){
_2b3.x+=_2ae.offsetWidth-_2b3.w;
}
if(_2b3.x<0||!isIE){
_2b3.x=0;
}
if(_2b3.y<0||!isIE){
_2b3.y=0;
}
}else{
var pb=dojo._getPadBorderExtents(el);
_2b3.w-=pb.w;
_2b3.h-=pb.h;
_2b3.x+=pb.l;
_2b3.y+=pb.t;
}
if(el!=_2ae){
var _2b5=el.clientWidth,_2b6=_2b3.w-_2b5;
if(_2b5>0&&_2b6>0){
_2b3.w=_2b5;
if(isIE&&rtl){
_2b3.x+=_2b6;
}
}
_2b5=el.clientHeight;
_2b6=_2b3.h-_2b5;
if(_2b5>0&&_2b6>0){
_2b3.h=_2b5;
}
}
if(_2b4){
if(_2b3.y<0){
_2b3.h+=_2b3.y;
_2b3.y=0;
}
if(_2b3.x<0){
_2b3.w+=_2b3.x;
_2b3.x=0;
}
if(_2b3.y+_2b3.h>_2b0){
_2b3.h=_2b0-_2b3.y;
}
if(_2b3.x+_2b3.w>_2af){
_2b3.w=_2af-_2b3.x;
}
}
var l=_2b1.x-_2b3.x,t=_2b1.y-Math.max(_2b3.y,0),r=l+_2b1.w-_2b3.w,bot=t+_2b1.h-_2b3.h;
if(r*l>0){
var s=Math[l<0?"max":"min"](l,r);
_2b1.x+=el.scrollLeft;
el.scrollLeft+=(isIE>=8&&!_2ac&&rtl)?-s:s;
_2b1.x-=el.scrollLeft;
}
if(bot*t>0){
_2b1.y+=el.scrollTop;
el.scrollTop+=Math[t<0?"max":"min"](t,bot);
_2b1.y-=el.scrollTop;
}
el=(el!=_2ae)&&!_2b4&&el.parentNode;
}
}
catch(error){
console.error("scrollIntoView: "+error);
node.scrollIntoView(false);
}
};
}
if(!dojo._hasResource["dijit._base.manager"]){
dojo._hasResource["dijit._base.manager"]=true;
dojo.provide("dijit._base.manager");
dojo.declare("dijit.WidgetSet",null,{constructor:function(){
this._hash={};
this.length=0;
},add:function(_2b7){
if(this._hash[_2b7.id]){
throw new Error("Tried to register widget with id=="+_2b7.id+" but that id is already registered");
}
this._hash[_2b7.id]=_2b7;
this.length++;
},remove:function(id){
if(this._hash[id]){
delete this._hash[id];
this.length--;
}
},forEach:function(func,_2b8){
_2b8=_2b8||dojo.global;
var i=0,id;
for(id in this._hash){
func.call(_2b8,this._hash[id],i++,this._hash);
}
return this;
},filter:function(_2b9,_2ba){
_2ba=_2ba||dojo.global;
var res=new dijit.WidgetSet(),i=0,id;
for(id in this._hash){
var w=this._hash[id];
if(_2b9.call(_2ba,w,i++,this._hash)){
res.add(w);
}
}
return res;
},byId:function(id){
return this._hash[id];
},byClass:function(cls){
var res=new dijit.WidgetSet(),id,_2bb;
for(id in this._hash){
_2bb=this._hash[id];
if(_2bb.declaredClass==cls){
res.add(_2bb);
}
}
return res;
},toArray:function(){
var ar=[];
for(var id in this._hash){
ar.push(this._hash[id]);
}
return ar;
},map:function(func,_2bc){
return dojo.map(this.toArray(),func,_2bc);
},every:function(func,_2bd){
_2bd=_2bd||dojo.global;
var x=0,i;
for(i in this._hash){
if(!func.call(_2bd,this._hash[i],x++,this._hash)){
return false;
}
}
return true;
},some:function(func,_2be){
_2be=_2be||dojo.global;
var x=0,i;
for(i in this._hash){
if(func.call(_2be,this._hash[i],x++,this._hash)){
return true;
}
}
return false;
}});
(function(){
dijit.registry=new dijit.WidgetSet();
var hash=dijit.registry._hash,attr=dojo.attr,_2bf=dojo.hasAttr,_2c0=dojo.style;
dijit.byId=function(id){
return typeof id=="string"?hash[id]:id;
};
var _2c1={};
dijit.getUniqueId=function(_2c2){
var id;
do{
id=_2c2+"_"+(_2c2 in _2c1?++_2c1[_2c2]:_2c1[_2c2]=0);
}while(hash[id]);
return dijit._scopeName=="dijit"?id:dijit._scopeName+"_"+id;
};
dijit.findWidgets=function(root){
var _2c3=[];
function _2c4(root){
for(var node=root.firstChild;node;node=node.nextSibling){
if(node.nodeType==1){
var _2c5=node.getAttribute("widgetId");
if(_2c5){
_2c3.push(hash[_2c5]);
}else{
_2c4(node);
}
}
}
};
_2c4(root);
return _2c3;
};
dijit._destroyAll=function(){
dijit._curFocus=null;
dijit._prevFocus=null;
dijit._activeStack=[];
dojo.forEach(dijit.findWidgets(dojo.body()),function(_2c6){
if(!_2c6._destroyed){
if(_2c6.destroyRecursive){
_2c6.destroyRecursive();
}else{
if(_2c6.destroy){
_2c6.destroy();
}
}
}
});
};
if(dojo.isIE){
dojo.addOnWindowUnload(function(){
dijit._destroyAll();
});
}
dijit.byNode=function(node){
return hash[node.getAttribute("widgetId")];
};
dijit.getEnclosingWidget=function(node){
while(node){
var id=node.getAttribute&&node.getAttribute("widgetId");
if(id){
return hash[id];
}
node=node.parentNode;
}
return null;
};
var _2c7=(dijit._isElementShown=function(elem){
var s=_2c0(elem);
return (s.visibility!="hidden")&&(s.visibility!="collapsed")&&(s.display!="none")&&(attr(elem,"type")!="hidden");
});
dijit.hasDefaultTabStop=function(elem){
switch(elem.nodeName.toLowerCase()){
case "a":
return _2bf(elem,"href");
case "area":
case "button":
case "input":
case "object":
case "select":
case "textarea":
return true;
case "iframe":
if(dojo.isMoz){
try{
return elem.contentDocument.designMode=="on";
}
catch(err){
return false;
}
}else{
if(dojo.isWebKit){
var doc=elem.contentDocument,body=doc&&doc.body;
return body&&body.contentEditable=="true";
}else{
try{
doc=elem.contentWindow.document;
body=doc&&doc.body;
return body&&body.firstChild&&body.firstChild.contentEditable=="true";
}
catch(e){
return false;
}
}
}
default:
return elem.contentEditable=="true";
}
};
var _2c8=(dijit.isTabNavigable=function(elem){
if(attr(elem,"disabled")){
return false;
}else{
if(_2bf(elem,"tabIndex")){
return attr(elem,"tabIndex")>=0;
}else{
return dijit.hasDefaultTabStop(elem);
}
}
});
dijit._getTabNavigable=function(root){
var _2c9,last,_2ca,_2cb,_2cc,_2cd;
var _2ce=function(_2cf){
dojo.query("> *",_2cf).forEach(function(_2d0){
if((dojo.isIE&&_2d0.scopeName!=="HTML")||!_2c7(_2d0)){
return;
}
if(_2c8(_2d0)){
var _2d1=attr(_2d0,"tabIndex");
if(!_2bf(_2d0,"tabIndex")||_2d1==0){
if(!_2c9){
_2c9=_2d0;
}
last=_2d0;
}else{
if(_2d1>0){
if(!_2ca||_2d1<_2cb){
_2cb=_2d1;
_2ca=_2d0;
}
if(!_2cc||_2d1>=_2cd){
_2cd=_2d1;
_2cc=_2d0;
}
}
}
}
if(_2d0.nodeName.toUpperCase()!="SELECT"){
_2ce(_2d0);
}
});
};
if(_2c7(root)){
_2ce(root);
}
return {first:_2c9,last:last,lowest:_2ca,highest:_2cc};
};
dijit.getFirstInTabbingOrder=function(root){
var _2d2=dijit._getTabNavigable(dojo.byId(root));
return _2d2.lowest?_2d2.lowest:_2d2.first;
};
dijit.getLastInTabbingOrder=function(root){
var _2d3=dijit._getTabNavigable(dojo.byId(root));
return _2d3.last?_2d3.last:_2d3.highest;
};
dijit.defaultDuration=dojo.config["defaultDuration"]||200;
})();
}
if(!dojo._hasResource["dijit._base.focus"]){
dojo._hasResource["dijit._base.focus"]=true;
dojo.provide("dijit._base.focus");
dojo.mixin(dijit,{_curFocus:null,_prevFocus:null,isCollapsed:function(){
return dijit.getBookmark().isCollapsed;
},getBookmark:function(){
var bm,rg,tg,sel=dojo.doc.selection,cf=dijit._curFocus;
if(dojo.global.getSelection){
sel=dojo.global.getSelection();
if(sel){
if(sel.isCollapsed){
tg=cf?cf.tagName:"";
if(tg){
tg=tg.toLowerCase();
if(tg=="textarea"||(tg=="input"&&(!cf.type||cf.type.toLowerCase()=="text"))){
sel={start:cf.selectionStart,end:cf.selectionEnd,node:cf,pRange:true};
return {isCollapsed:(sel.end<=sel.start),mark:sel};
}
}
bm={isCollapsed:true};
}else{
rg=sel.getRangeAt(0);
bm={isCollapsed:false,mark:rg.cloneRange()};
}
}
}else{
if(sel){
tg=cf?cf.tagName:"";
tg=tg.toLowerCase();
if(cf&&tg&&(tg=="button"||tg=="textarea"||tg=="input")){
if(sel.type&&sel.type.toLowerCase()=="none"){
return {isCollapsed:true,mark:null};
}else{
rg=sel.createRange();
return {isCollapsed:rg.text&&rg.text.length?false:true,mark:{range:rg,pRange:true}};
}
}
bm={};
try{
rg=sel.createRange();
bm.isCollapsed=!(sel.type=="Text"?rg.htmlText.length:rg.length);
}
catch(e){
bm.isCollapsed=true;
return bm;
}
if(sel.type.toUpperCase()=="CONTROL"){
if(rg.length){
bm.mark=[];
var i=0,len=rg.length;
while(i<len){
bm.mark.push(rg.item(i++));
}
}else{
bm.isCollapsed=true;
bm.mark=null;
}
}else{
bm.mark=rg.getBookmark();
}
}else{
console.warn("No idea how to store the current selection for this browser!");
}
}
return bm;
},moveToBookmark:function(_2d4){
var _2d5=dojo.doc,mark=_2d4.mark;
if(mark){
if(dojo.global.getSelection){
var sel=dojo.global.getSelection();
if(sel&&sel.removeAllRanges){
if(mark.pRange){
var r=mark;
var n=r.node;
n.selectionStart=r.start;
n.selectionEnd=r.end;
}else{
sel.removeAllRanges();
sel.addRange(mark);
}
}else{
console.warn("No idea how to restore selection for this browser!");
}
}else{
if(_2d5.selection&&mark){
var rg;
if(mark.pRange){
rg=mark.range;
}else{
if(dojo.isArray(mark)){
rg=_2d5.body.createControlRange();
dojo.forEach(mark,function(n){
rg.addElement(n);
});
}else{
rg=_2d5.body.createTextRange();
rg.moveToBookmark(mark);
}
}
rg.select();
}
}
}
},getFocus:function(menu,_2d6){
var node=!dijit._curFocus||(menu&&dojo.isDescendant(dijit._curFocus,menu.domNode))?dijit._prevFocus:dijit._curFocus;
return {node:node,bookmark:(node==dijit._curFocus)&&dojo.withGlobal(_2d6||dojo.global,dijit.getBookmark),openedForWindow:_2d6};
},focus:function(_2d7){
if(!_2d7){
return;
}
var node="node" in _2d7?_2d7.node:_2d7,_2d8=_2d7.bookmark,_2d9=_2d7.openedForWindow,_2da=_2d8?_2d8.isCollapsed:false;
if(node){
var _2db=(node.tagName.toLowerCase()=="iframe")?node.contentWindow:node;
if(_2db&&_2db.focus){
try{
_2db.focus();
}
catch(e){
}
}
dijit._onFocusNode(node);
}
if(_2d8&&dojo.withGlobal(_2d9||dojo.global,dijit.isCollapsed)&&!_2da){
if(_2d9){
_2d9.focus();
}
try{
dojo.withGlobal(_2d9||dojo.global,dijit.moveToBookmark,null,[_2d8]);
}
catch(e2){
}
}
},_activeStack:[],registerIframe:function(_2dc){
return dijit.registerWin(_2dc.contentWindow,_2dc);
},unregisterIframe:function(_2dd){
dijit.unregisterWin(_2dd);
},registerWin:function(_2de,_2df){
var _2e0=function(evt){
dijit._justMouseDowned=true;
setTimeout(function(){
dijit._justMouseDowned=false;
},0);
if(dojo.isIE&&evt&&evt.srcElement&&evt.srcElement.parentNode==null){
return;
}
dijit._onTouchNode(_2df||evt.target||evt.srcElement,"mouse");
};
var doc=dojo.isIE?_2de.document.documentElement:_2de.document;
if(doc){
if(dojo.isIE){
doc.attachEvent("onmousedown",_2e0);
var _2e1=function(evt){
if(evt.srcElement.tagName.toLowerCase()!="#document"&&dijit.isTabNavigable(evt.srcElement)){
dijit._onFocusNode(_2df||evt.srcElement);
}else{
dijit._onTouchNode(_2df||evt.srcElement);
}
};
doc.attachEvent("onactivate",_2e1);
var _2e2=function(evt){
dijit._onBlurNode(_2df||evt.srcElement);
};
doc.attachEvent("ondeactivate",_2e2);
return function(){
doc.detachEvent("onmousedown",_2e0);
doc.detachEvent("onactivate",_2e1);
doc.detachEvent("ondeactivate",_2e2);
doc=null;
};
}else{
doc.addEventListener("mousedown",_2e0,true);
var _2e3=function(evt){
dijit._onFocusNode(_2df||evt.target);
};
doc.addEventListener("focus",_2e3,true);
var _2e4=function(evt){
dijit._onBlurNode(_2df||evt.target);
};
doc.addEventListener("blur",_2e4,true);
return function(){
doc.removeEventListener("mousedown",_2e0,true);
doc.removeEventListener("focus",_2e3,true);
doc.removeEventListener("blur",_2e4,true);
doc=null;
};
}
}
},unregisterWin:function(_2e5){
_2e5&&_2e5();
},_onBlurNode:function(node){
dijit._prevFocus=dijit._curFocus;
dijit._curFocus=null;
if(dijit._justMouseDowned){
return;
}
if(dijit._clearActiveWidgetsTimer){
clearTimeout(dijit._clearActiveWidgetsTimer);
}
dijit._clearActiveWidgetsTimer=setTimeout(function(){
delete dijit._clearActiveWidgetsTimer;
dijit._setStack([]);
dijit._prevFocus=null;
},100);
},_onTouchNode:function(node,by){
if(dijit._clearActiveWidgetsTimer){
clearTimeout(dijit._clearActiveWidgetsTimer);
delete dijit._clearActiveWidgetsTimer;
}
var _2e6=[];
try{
while(node){
var _2e7=dojo.attr(node,"dijitPopupParent");
if(_2e7){
node=dijit.byId(_2e7).domNode;
}else{
if(node.tagName&&node.tagName.toLowerCase()=="body"){
if(node===dojo.body()){
break;
}
node=dojo.window.get(node.ownerDocument).frameElement;
}else{
var id=node.getAttribute&&node.getAttribute("widgetId"),_2e8=id&&dijit.byId(id);
if(_2e8&&!(by=="mouse"&&_2e8.get("disabled"))){
_2e6.unshift(id);
}
node=node.parentNode;
}
}
}
}
catch(e){
}
dijit._setStack(_2e6,by);
},_onFocusNode:function(node){
if(!node){
return;
}
if(node.nodeType==9){
return;
}
dijit._onTouchNode(node);
if(node==dijit._curFocus){
return;
}
if(dijit._curFocus){
dijit._prevFocus=dijit._curFocus;
}
dijit._curFocus=node;
dojo.publish("focusNode",[node]);
},_setStack:function(_2e9,by){
var _2ea=dijit._activeStack;
dijit._activeStack=_2e9;
for(var _2eb=0;_2eb<Math.min(_2ea.length,_2e9.length);_2eb++){
if(_2ea[_2eb]!=_2e9[_2eb]){
break;
}
}
var _2ec;
for(var i=_2ea.length-1;i>=_2eb;i--){
_2ec=dijit.byId(_2ea[i]);
if(_2ec){
_2ec._focused=false;
_2ec._hasBeenBlurred=true;
if(_2ec._onBlur){
_2ec._onBlur(by);
}
dojo.publish("widgetBlur",[_2ec,by]);
}
}
for(i=_2eb;i<_2e9.length;i++){
_2ec=dijit.byId(_2e9[i]);
if(_2ec){
_2ec._focused=true;
if(_2ec._onFocus){
_2ec._onFocus(by);
}
dojo.publish("widgetFocus",[_2ec,by]);
}
}
}});
dojo.addOnLoad(function(){
var _2ed=dijit.registerWin(window);
if(dojo.isIE){
dojo.addOnWindowUnload(function(){
dijit.unregisterWin(_2ed);
_2ed=null;
});
}
});
}
if(!dojo._hasResource["dojo.AdapterRegistry"]){
dojo._hasResource["dojo.AdapterRegistry"]=true;
dojo.provide("dojo.AdapterRegistry");
dojo.AdapterRegistry=function(_2ee){
this.pairs=[];
this.returnWrappers=_2ee||false;
};
dojo.extend(dojo.AdapterRegistry,{register:function(name,_2ef,wrap,_2f0,_2f1){
this.pairs[((_2f1)?"unshift":"push")]([name,_2ef,wrap,_2f0]);
},match:function(){
for(var i=0;i<this.pairs.length;i++){
var pair=this.pairs[i];
if(pair[1].apply(this,arguments)){
if((pair[3])||(this.returnWrappers)){
return pair[2];
}else{
return pair[2].apply(this,arguments);
}
}
}
throw new Error("No match found");
},unregister:function(name){
for(var i=0;i<this.pairs.length;i++){
var pair=this.pairs[i];
if(pair[0]==name){
this.pairs.splice(i,1);
return true;
}
}
return false;
}});
}
if(!dojo._hasResource["dijit._base.place"]){
dojo._hasResource["dijit._base.place"]=true;
dojo.provide("dijit._base.place");
dijit.getViewport=function(){
return dojo.window.getBox();
};
dijit.placeOnScreen=function(node,pos,_2f2,_2f3){
var _2f4=dojo.map(_2f2,function(_2f5){
var c={corner:_2f5,pos:{x:pos.x,y:pos.y}};
if(_2f3){
c.pos.x+=_2f5.charAt(1)=="L"?_2f3.x:-_2f3.x;
c.pos.y+=_2f5.charAt(0)=="T"?_2f3.y:-_2f3.y;
}
return c;
});
return dijit._place(node,_2f4);
};
dijit._place=function(node,_2f6,_2f7){
var view=dojo.window.getBox();
if(!node.parentNode||String(node.parentNode.tagName).toLowerCase()!="body"){
dojo.body().appendChild(node);
}
var best=null;
dojo.some(_2f6,function(_2f8){
var _2f9=_2f8.corner;
var pos=_2f8.pos;
if(_2f7){
_2f7(node,_2f8.aroundCorner,_2f9);
}
var _2fa=node.style;
var _2fb=_2fa.display;
var _2fc=_2fa.visibility;
_2fa.visibility="hidden";
_2fa.display="";
var mb=dojo.marginBox(node);
_2fa.display=_2fb;
_2fa.visibility=_2fc;
var _2fd=Math.max(view.l,_2f9.charAt(1)=="L"?pos.x:(pos.x-mb.w)),_2fe=Math.max(view.t,_2f9.charAt(0)=="T"?pos.y:(pos.y-mb.h)),endX=Math.min(view.l+view.w,_2f9.charAt(1)=="L"?(_2fd+mb.w):pos.x),endY=Math.min(view.t+view.h,_2f9.charAt(0)=="T"?(_2fe+mb.h):pos.y),_2ff=endX-_2fd,_300=endY-_2fe,_301=(mb.w-_2ff)+(mb.h-_300);
if(best==null||_301<best.overflow){
best={corner:_2f9,aroundCorner:_2f8.aroundCorner,x:_2fd,y:_2fe,w:_2ff,h:_300,overflow:_301};
}
return !_301;
});
node.style.left=best.x+"px";
node.style.top=best.y+"px";
if(best.overflow&&_2f7){
_2f7(node,best.aroundCorner,best.corner);
}
return best;
};
dijit.placeOnScreenAroundNode=function(node,_302,_303,_304){
_302=dojo.byId(_302);
var _305=_302.style.display;
_302.style.display="";
var _306=dojo.position(_302,true);
_302.style.display=_305;
return dijit._placeOnScreenAroundRect(node,_306.x,_306.y,_306.w,_306.h,_303,_304);
};
dijit.placeOnScreenAroundRectangle=function(node,_307,_308,_309){
return dijit._placeOnScreenAroundRect(node,_307.x,_307.y,_307.width,_307.height,_308,_309);
};
dijit._placeOnScreenAroundRect=function(node,x,y,_30a,_30b,_30c,_30d){
var _30e=[];
for(var _30f in _30c){
_30e.push({aroundCorner:_30f,corner:_30c[_30f],pos:{x:x+(_30f.charAt(1)=="L"?0:_30a),y:y+(_30f.charAt(0)=="T"?0:_30b)}});
}
return dijit._place(node,_30e,_30d);
};
dijit.placementRegistry=new dojo.AdapterRegistry();
dijit.placementRegistry.register("node",function(n,x){
return typeof x=="object"&&typeof x.offsetWidth!="undefined"&&typeof x.offsetHeight!="undefined";
},dijit.placeOnScreenAroundNode);
dijit.placementRegistry.register("rect",function(n,x){
return typeof x=="object"&&"x" in x&&"y" in x&&"width" in x&&"height" in x;
},dijit.placeOnScreenAroundRectangle);
dijit.placeOnScreenAroundElement=function(node,_310,_311,_312){
return dijit.placementRegistry.match.apply(dijit.placementRegistry,arguments);
};
dijit.getPopupAroundAlignment=function(_313,_314){
var _315={};
dojo.forEach(_313,function(pos){
switch(pos){
case "after":
_315[_314?"BR":"BL"]=_314?"BL":"BR";
break;
case "before":
_315[_314?"BL":"BR"]=_314?"BR":"BL";
break;
case "below":
_315[_314?"BL":"BR"]=_314?"TL":"TR";
_315[_314?"BR":"BL"]=_314?"TR":"TL";
break;
case "above":
default:
_315[_314?"TL":"TR"]=_314?"BL":"BR";
_315[_314?"TR":"TL"]=_314?"BR":"BL";
break;
}
});
return _315;
};
}
if(!dojo._hasResource["dijit._base.window"]){
dojo._hasResource["dijit._base.window"]=true;
dojo.provide("dijit._base.window");
dijit.getDocumentWindow=function(doc){
return dojo.window.get(doc);
};
}
if(!dojo._hasResource["dijit._base.popup"]){
dojo._hasResource["dijit._base.popup"]=true;
dojo.provide("dijit._base.popup");
dijit.popup={_stack:[],_beginZIndex:1000,_idGen:1,moveOffScreen:function(node){
var _316=node.parentNode;
if(!_316||!dojo.hasClass(_316,"dijitPopup")){
_316=dojo.create("div",{"class":"dijitPopup",style:{visibility:"hidden",top:"-9999px"}},dojo.body());
dijit.setWaiRole(_316,"presentation");
_316.appendChild(node);
}
var s=node.style;
s.display="";
s.visibility="";
s.position="";
s.top="0px";
dojo.style(_316,{visibility:"hidden",top:"-9999px"});
},getTopPopup:function(){
var _317=this._stack;
for(var pi=_317.length-1;pi>0&&_317[pi].parent===_317[pi-1].widget;pi--){
}
return _317[pi];
},open:function(args){
var _318=this._stack,_319=args.popup,_31a=args.orient||((args.parent?args.parent.isLeftToRight():dojo._isBodyLtr())?{"BL":"TL","BR":"TR","TL":"BL","TR":"BR"}:{"BR":"TR","BL":"TL","TR":"BR","TL":"BL"}),_31b=args.around,id=(args.around&&args.around.id)?(args.around.id+"_dropdown"):("popup_"+this._idGen++);
var _31c=_319.domNode.parentNode;
if(!_31c||!dojo.hasClass(_31c,"dijitPopup")){
this.moveOffScreen(_319.domNode);
_31c=_319.domNode.parentNode;
}
dojo.attr(_31c,{id:id,style:{zIndex:this._beginZIndex+_318.length},"class":"dijitPopup "+(_319.baseClass||_319["class"]||"").split(" ")[0]+"Popup",dijitPopupParent:args.parent?args.parent.id:""});
if(dojo.isIE||dojo.isMoz){
var _31d=_31c.childNodes[1];
if(!_31d){
_31d=new dijit.BackgroundIframe(_31c);
}
}
var best=_31b?dijit.placeOnScreenAroundElement(_31c,_31b,_31a,_319.orient?dojo.hitch(_319,"orient"):null):dijit.placeOnScreen(_31c,args,_31a=="R"?["TR","BR","TL","BL"]:["TL","BL","TR","BR"],args.padding);
_31c.style.visibility="visible";
_319.domNode.style.visibility="visible";
var _31e=[];
_31e.push(dojo.connect(_31c,"onkeypress",this,function(evt){
if(evt.charOrCode==dojo.keys.ESCAPE&&args.onCancel){
dojo.stopEvent(evt);
args.onCancel();
}else{
if(evt.charOrCode===dojo.keys.TAB){
dojo.stopEvent(evt);
var _31f=this.getTopPopup();
if(_31f&&_31f.onCancel){
_31f.onCancel();
}
}
}
}));
if(_319.onCancel){
_31e.push(dojo.connect(_319,"onCancel",args.onCancel));
}
_31e.push(dojo.connect(_319,_319.onExecute?"onExecute":"onChange",this,function(){
var _320=this.getTopPopup();
if(_320&&_320.onExecute){
_320.onExecute();
}
}));
_318.push({wrapper:_31c,iframe:_31d,widget:_319,parent:args.parent,onExecute:args.onExecute,onCancel:args.onCancel,onClose:args.onClose,handlers:_31e});
if(_319.onOpen){
_319.onOpen(best);
}
return best;
},close:function(_321){
var _322=this._stack;
while(dojo.some(_322,function(elem){
return elem.widget==_321;
})){
var top=_322.pop(),_323=top.wrapper,_324=top.iframe,_325=top.widget,_326=top.onClose;
if(_325.onClose){
_325.onClose();
}
dojo.forEach(top.handlers,dojo.disconnect);
if(_325&&_325.domNode){
this.moveOffScreen(_325.domNode);
}else{
dojo.destroy(_323);
}
if(_326){
_326();
}
}
}};
dijit._frames=new function(){
var _327=[];
this.pop=function(){
var _328;
if(_327.length){
_328=_327.pop();
_328.style.display="";
}else{
if(dojo.isIE){
var burl=dojo.config["dojoBlankHtmlUrl"]||(dojo.moduleUrl("dojo","resources/blank.html")+"")||"javascript:\"\"";
var html="<iframe src='"+burl+"'"+" style='position: absolute; left: 0px; top: 0px;"+"z-index: -1; filter:Alpha(Opacity=\"0\");'>";
_328=dojo.doc.createElement(html);
}else{
_328=dojo.create("iframe");
_328.src="javascript:\"\"";
_328.className="dijitBackgroundIframe";
dojo.style(_328,"opacity",0.1);
}
_328.tabIndex=-1;
dijit.setWaiRole(_328,"presentation");
}
return _328;
};
this.push=function(_329){
_329.style.display="none";
_327.push(_329);
};
}();
dijit.BackgroundIframe=function(node){
if(!node.id){
throw new Error("no id");
}
if(dojo.isIE||dojo.isMoz){
var _32a=dijit._frames.pop();
node.appendChild(_32a);
if(dojo.isIE<7){
this.resize(node);
this._conn=dojo.connect(node,"onresize",this,function(){
this.resize(node);
});
}else{
dojo.style(_32a,{width:"100%",height:"100%"});
}
this.iframe=_32a;
}
};
dojo.extend(dijit.BackgroundIframe,{resize:function(node){
if(this.iframe&&dojo.isIE<7){
dojo.style(this.iframe,{width:node.offsetWidth+"px",height:node.offsetHeight+"px"});
}
},destroy:function(){
if(this._conn){
dojo.disconnect(this._conn);
this._conn=null;
}
if(this.iframe){
dijit._frames.push(this.iframe);
delete this.iframe;
}
}});
}
if(!dojo._hasResource["dijit._base.scroll"]){
dojo._hasResource["dijit._base.scroll"]=true;
dojo.provide("dijit._base.scroll");
dijit.scrollIntoView=function(node,pos){
dojo.window.scrollIntoView(node,pos);
};
}
if(!dojo._hasResource["dojo.uacss"]){
dojo._hasResource["dojo.uacss"]=true;
dojo.provide("dojo.uacss");
(function(){
var d=dojo,html=d.doc.documentElement,ie=d.isIE,_32b=d.isOpera,maj=Math.floor,ff=d.isFF,_32c=d.boxModel.replace(/-/,""),_32d={dj_ie:ie,dj_ie6:maj(ie)==6,dj_ie7:maj(ie)==7,dj_ie8:maj(ie)==8,dj_quirks:d.isQuirks,dj_iequirks:ie&&d.isQuirks,dj_opera:_32b,dj_khtml:d.isKhtml,dj_webkit:d.isWebKit,dj_safari:d.isSafari,dj_chrome:d.isChrome,dj_gecko:d.isMozilla,dj_ff3:maj(ff)==3};
_32d["dj_"+_32c]=true;
var _32e="";
for(var clz in _32d){
if(_32d[clz]){
_32e+=clz+" ";
}
}
html.className=d.trim(html.className+" "+_32e);
dojo._loaders.unshift(function(){
if(!dojo._isBodyLtr()){
var _32f="dj_rtl dijitRtl "+_32e.replace(/ /g,"-rtl ");
html.className=d.trim(html.className+" "+_32f);
}
});
})();
}
if(!dojo._hasResource["dijit._base.sniff"]){
dojo._hasResource["dijit._base.sniff"]=true;
dojo.provide("dijit._base.sniff");
}
if(!dojo._hasResource["dijit._base.typematic"]){
dojo._hasResource["dijit._base.typematic"]=true;
dojo.provide("dijit._base.typematic");
dijit.typematic={_fireEventAndReload:function(){
this._timer=null;
this._callback(++this._count,this._node,this._evt);
this._currentTimeout=Math.max(this._currentTimeout<0?this._initialDelay:(this._subsequentDelay>1?this._subsequentDelay:Math.round(this._currentTimeout*this._subsequentDelay)),this._minDelay);
this._timer=setTimeout(dojo.hitch(this,"_fireEventAndReload"),this._currentTimeout);
},trigger:function(evt,_330,node,_331,obj,_332,_333,_334){
if(obj!=this._obj){
this.stop();
this._initialDelay=_333||500;
this._subsequentDelay=_332||0.9;
this._minDelay=_334||10;
this._obj=obj;
this._evt=evt;
this._node=node;
this._currentTimeout=-1;
this._count=-1;
this._callback=dojo.hitch(_330,_331);
this._fireEventAndReload();
this._evt=dojo.mixin({faux:true},evt);
}
},stop:function(){
if(this._timer){
clearTimeout(this._timer);
this._timer=null;
}
if(this._obj){
this._callback(-1,this._node,this._evt);
this._obj=null;
}
},addKeyListener:function(node,_335,_336,_337,_338,_339,_33a){
if(_335.keyCode){
_335.charOrCode=_335.keyCode;
dojo.deprecated("keyCode attribute parameter for dijit.typematic.addKeyListener is deprecated. Use charOrCode instead.","","2.0");
}else{
if(_335.charCode){
_335.charOrCode=String.fromCharCode(_335.charCode);
dojo.deprecated("charCode attribute parameter for dijit.typematic.addKeyListener is deprecated. Use charOrCode instead.","","2.0");
}
}
return [dojo.connect(node,"onkeypress",this,function(evt){
if(evt.charOrCode==_335.charOrCode&&(_335.ctrlKey===undefined||_335.ctrlKey==evt.ctrlKey)&&(_335.altKey===undefined||_335.altKey==evt.altKey)&&(_335.metaKey===undefined||_335.metaKey==(evt.metaKey||false))&&(_335.shiftKey===undefined||_335.shiftKey==evt.shiftKey)){
dojo.stopEvent(evt);
dijit.typematic.trigger(evt,_336,node,_337,_335,_338,_339,_33a);
}else{
if(dijit.typematic._obj==_335){
dijit.typematic.stop();
}
}
}),dojo.connect(node,"onkeyup",this,function(evt){
if(dijit.typematic._obj==_335){
dijit.typematic.stop();
}
})];
},addMouseListener:function(node,_33b,_33c,_33d,_33e,_33f){
var dc=dojo.connect;
return [dc(node,"mousedown",this,function(evt){
dojo.stopEvent(evt);
dijit.typematic.trigger(evt,_33b,node,_33c,node,_33d,_33e,_33f);
}),dc(node,"mouseup",this,function(evt){
dojo.stopEvent(evt);
dijit.typematic.stop();
}),dc(node,"mouseout",this,function(evt){
dojo.stopEvent(evt);
dijit.typematic.stop();
}),dc(node,"mousemove",this,function(evt){
evt.preventDefault();
}),dc(node,"dblclick",this,function(evt){
dojo.stopEvent(evt);
if(dojo.isIE){
dijit.typematic.trigger(evt,_33b,node,_33c,node,_33d,_33e,_33f);
setTimeout(dojo.hitch(this,dijit.typematic.stop),50);
}
})];
},addListener:function(_340,_341,_342,_343,_344,_345,_346,_347){
return this.addKeyListener(_341,_342,_343,_344,_345,_346,_347).concat(this.addMouseListener(_340,_343,_344,_345,_346,_347));
}};
}
if(!dojo._hasResource["dijit._base.wai"]){
dojo._hasResource["dijit._base.wai"]=true;
dojo.provide("dijit._base.wai");
dijit.wai={onload:function(){
var div=dojo.create("div",{id:"a11yTestNode",style:{cssText:"border: 1px solid;"+"border-color:red green;"+"position: absolute;"+"height: 5px;"+"top: -999px;"+"background-image: url(\""+(dojo.config.blankGif||dojo.moduleUrl("dojo","resources/blank.gif"))+"\");"}},dojo.body());
var cs=dojo.getComputedStyle(div);
if(cs){
var _348=cs.backgroundImage;
var _349=(cs.borderTopColor==cs.borderRightColor)||(_348!=null&&(_348=="none"||_348=="url(invalid-url:)"));
dojo[_349?"addClass":"removeClass"](dojo.body(),"dijit_a11y");
if(dojo.isIE){
div.outerHTML="";
}else{
dojo.body().removeChild(div);
}
}
}};
if(dojo.isIE||dojo.isMoz){
dojo._loaders.unshift(dijit.wai.onload);
}
dojo.mixin(dijit,{_XhtmlRoles:/banner|contentinfo|definition|main|navigation|search|note|secondary|seealso/,hasWaiRole:function(elem,role){
var _34a=this.getWaiRole(elem);
return role?(_34a.indexOf(role)>-1):(_34a.length>0);
},getWaiRole:function(elem){
return dojo.trim((dojo.attr(elem,"role")||"").replace(this._XhtmlRoles,"").replace("wairole:",""));
},setWaiRole:function(elem,role){
var _34b=dojo.attr(elem,"role")||"";
if(!this._XhtmlRoles.test(_34b)){
dojo.attr(elem,"role",role);
}else{
if((" "+_34b+" ").indexOf(" "+role+" ")<0){
var _34c=dojo.trim(_34b.replace(this._XhtmlRoles,""));
var _34d=dojo.trim(_34b.replace(_34c,""));
dojo.attr(elem,"role",_34d+(_34d?" ":"")+role);
}
}
},removeWaiRole:function(elem,role){
var _34e=dojo.attr(elem,"role");
if(!_34e){
return;
}
if(role){
var t=dojo.trim((" "+_34e+" ").replace(" "+role+" "," "));
dojo.attr(elem,"role",t);
}else{
elem.removeAttribute("role");
}
},hasWaiState:function(elem,_34f){
return elem.hasAttribute?elem.hasAttribute("aria-"+_34f):!!elem.getAttribute("aria-"+_34f);
},getWaiState:function(elem,_350){
return elem.getAttribute("aria-"+_350)||"";
},setWaiState:function(elem,_351,_352){
elem.setAttribute("aria-"+_351,_352);
},removeWaiState:function(elem,_353){
elem.removeAttribute("aria-"+_353);
}});
}
if(!dojo._hasResource["dijit._base"]){
dojo._hasResource["dijit._base"]=true;
dojo.provide("dijit._base");
}
if(!dojo._hasResource["dijit._Widget"]){
dojo._hasResource["dijit._Widget"]=true;
dojo.provide("dijit._Widget");
dojo.require("dijit._base");
dojo.connect(dojo,"_connect",function(_354,_355){
if(_354&&dojo.isFunction(_354._onConnect)){
_354._onConnect(_355);
}
});
dijit._connectOnUseEventHandler=function(_356){
};
dijit._lastKeyDownNode=null;
if(dojo.isIE){
(function(){
var _357=function(evt){
dijit._lastKeyDownNode=evt.srcElement;
};
dojo.doc.attachEvent("onkeydown",_357);
dojo.addOnWindowUnload(function(){
dojo.doc.detachEvent("onkeydown",_357);
});
})();
}else{
dojo.doc.addEventListener("keydown",function(evt){
dijit._lastKeyDownNode=evt.target;
},true);
}
(function(){
var _358={},_359=function(_35a){
var dc=_35a.declaredClass;
if(!_358[dc]){
var r=[],_35b,_35c=_35a.constructor.prototype;
for(var _35d in _35c){
if(dojo.isFunction(_35c[_35d])&&(_35b=_35d.match(/^_set([a-zA-Z]*)Attr$/))&&_35b[1]){
r.push(_35b[1].charAt(0).toLowerCase()+_35b[1].substr(1));
}
}
_358[dc]=r;
}
return _358[dc]||[];
};
dojo.declare("dijit._Widget",null,{id:"",lang:"",dir:"","class":"",style:"",title:"",tooltip:"",baseClass:"",srcNodeRef:null,domNode:null,containerNode:null,attributeMap:{id:"",dir:"",lang:"","class":"",style:"",title:""},_deferredConnects:{onClick:"",onDblClick:"",onKeyDown:"",onKeyPress:"",onKeyUp:"",onMouseMove:"",onMouseDown:"",onMouseOut:"",onMouseOver:"",onMouseLeave:"",onMouseEnter:"",onMouseUp:""},onClick:dijit._connectOnUseEventHandler,onDblClick:dijit._connectOnUseEventHandler,onKeyDown:dijit._connectOnUseEventHandler,onKeyPress:dijit._connectOnUseEventHandler,onKeyUp:dijit._connectOnUseEventHandler,onMouseDown:dijit._connectOnUseEventHandler,onMouseMove:dijit._connectOnUseEventHandler,onMouseOut:dijit._connectOnUseEventHandler,onMouseOver:dijit._connectOnUseEventHandler,onMouseLeave:dijit._connectOnUseEventHandler,onMouseEnter:dijit._connectOnUseEventHandler,onMouseUp:dijit._connectOnUseEventHandler,_blankGif:(dojo.config.blankGif||dojo.moduleUrl("dojo","resources/blank.gif")).toString(),postscript:function(_35e,_35f){
this.create(_35e,_35f);
},create:function(_360,_361){
this.srcNodeRef=dojo.byId(_361);
this._connects=[];
this._subscribes=[];
this._deferredConnects=dojo.clone(this._deferredConnects);
for(var attr in this.attributeMap){
delete this._deferredConnects[attr];
}
for(attr in this._deferredConnects){
if(this[attr]!==dijit._connectOnUseEventHandler){
delete this._deferredConnects[attr];
}
}
if(this.srcNodeRef&&(typeof this.srcNodeRef.id=="string")){
this.id=this.srcNodeRef.id;
}
if(_360){
this.params=_360;
dojo.mixin(this,_360);
}
this.postMixInProperties();
if(!this.id){
this.id=dijit.getUniqueId(this.declaredClass.replace(/\./g,"_"));
}
dijit.registry.add(this);
this.buildRendering();
if(this.domNode){
this._applyAttributes();
var _362=this.srcNodeRef;
if(_362&&_362.parentNode){
_362.parentNode.replaceChild(this.domNode,_362);
}
for(attr in this.params){
this._onConnect(attr);
}
}
if(this.domNode){
this.domNode.setAttribute("widgetId",this.id);
}
this.postCreate();
if(this.srcNodeRef&&!this.srcNodeRef.parentNode){
delete this.srcNodeRef;
}
this._created=true;
},_applyAttributes:function(){
var _363=function(attr,_364){
if((_364.params&&attr in _364.params)||_364[attr]){
_364.set(attr,_364[attr]);
}
};
for(var attr in this.attributeMap){
_363(attr,this);
}
dojo.forEach(_359(this),function(a){
if(!(a in this.attributeMap)){
_363(a,this);
}
},this);
},postMixInProperties:function(){
},buildRendering:function(){
this.domNode=this.srcNodeRef||dojo.create("div");
},postCreate:function(){
if(this.baseClass){
var _365=this.baseClass.split(" ");
if(!this.isLeftToRight()){
_365=_365.concat(dojo.map(_365,function(name){
return name+"Rtl";
}));
}
dojo.addClass(this.domNode,_365);
}
},startup:function(){
this._started=true;
},destroyRecursive:function(_366){
this._beingDestroyed=true;
this.destroyDescendants(_366);
this.destroy(_366);
},destroy:function(_367){
this._beingDestroyed=true;
this.uninitialize();
var d=dojo,dfe=d.forEach,dun=d.unsubscribe;
dfe(this._connects,function(_368){
dfe(_368,d.disconnect);
});
dfe(this._subscribes,function(_369){
dun(_369);
});
dfe(this._supportingWidgets||[],function(w){
if(w.destroyRecursive){
w.destroyRecursive();
}else{
if(w.destroy){
w.destroy();
}
}
});
this.destroyRendering(_367);
dijit.registry.remove(this.id);
this._destroyed=true;
},destroyRendering:function(_36a){
if(this.bgIframe){
this.bgIframe.destroy(_36a);
delete this.bgIframe;
}
if(this.domNode){
if(_36a){
dojo.removeAttr(this.domNode,"widgetId");
}else{
dojo.destroy(this.domNode);
}
delete this.domNode;
}
if(this.srcNodeRef){
if(!_36a){
dojo.destroy(this.srcNodeRef);
}
delete this.srcNodeRef;
}
},destroyDescendants:function(_36b){
dojo.forEach(this.getChildren(),function(_36c){
if(_36c.destroyRecursive){
_36c.destroyRecursive(_36b);
}
});
},uninitialize:function(){
return false;
},onFocus:function(){
},onBlur:function(){
},_onFocus:function(e){
this.onFocus();
},_onBlur:function(){
this.onBlur();
},_onConnect:function(_36d){
if(_36d in this._deferredConnects){
var _36e=this[this._deferredConnects[_36d]||"domNode"];
this.connect(_36e,_36d.toLowerCase(),_36d);
delete this._deferredConnects[_36d];
}
},_setClassAttr:function(_36f){
var _370=this[this.attributeMap["class"]||"domNode"];
dojo.removeClass(_370,this["class"]);
this["class"]=_36f;
dojo.addClass(_370,_36f);
},_setStyleAttr:function(_371){
var _372=this[this.attributeMap.style||"domNode"];
if(dojo.isObject(_371)){
dojo.style(_372,_371);
}else{
if(_372.style.cssText){
_372.style.cssText+="; "+_371;
}else{
_372.style.cssText=_371;
}
}
this.style=_371;
},setAttribute:function(attr,_373){
dojo.deprecated(this.declaredClass+"::setAttribute(attr, value) is deprecated. Use set() instead.","","2.0");
this.set(attr,_373);
},_attrToDom:function(attr,_374){
var _375=this.attributeMap[attr];
dojo.forEach(dojo.isArray(_375)?_375:[_375],function(_376){
var _377=this[_376.node||_376||"domNode"];
var type=_376.type||"attribute";
switch(type){
case "attribute":
if(dojo.isFunction(_374)){
_374=dojo.hitch(this,_374);
}
var _378=_376.attribute?_376.attribute:(/^on[A-Z][a-zA-Z]*$/.test(attr)?attr.toLowerCase():attr);
dojo.attr(_377,_378,_374);
break;
case "innerText":
_377.innerHTML="";
_377.appendChild(dojo.doc.createTextNode(_374));
break;
case "innerHTML":
_377.innerHTML=_374;
break;
case "class":
dojo.removeClass(_377,this[attr]);
dojo.addClass(_377,_374);
break;
}
},this);
this[attr]=_374;
},attr:function(name,_379){
if(dojo.config.isDebug){
var _37a=arguments.callee._ach||(arguments.callee._ach={}),_37b=(arguments.callee.caller||"unknown caller").toString();
if(!_37a[_37b]){
dojo.deprecated(this.declaredClass+"::attr() is deprecated. Use get() or set() instead, called from "+_37b,"","2.0");
_37a[_37b]=true;
}
}
var args=arguments.length;
if(args>=2||typeof name==="object"){
return this.set.apply(this,arguments);
}else{
return this.get(name);
}
},get:function(name){
var _37c=this._getAttrNames(name);
return this[_37c.g]?this[_37c.g]():this[name];
},set:function(name,_37d){
if(typeof name==="object"){
for(var x in name){
this.set(x,name[x]);
}
return this;
}
var _37e=this._getAttrNames(name);
if(this[_37e.s]){
var _37f=this[_37e.s].apply(this,Array.prototype.slice.call(arguments,1));
}else{
if(name in this.attributeMap){
this._attrToDom(name,_37d);
}
var _380=this[name];
this[name]=_37d;
}
return _37f||this;
},_attrPairNames:{},_getAttrNames:function(name){
var apn=this._attrPairNames;
if(apn[name]){
return apn[name];
}
var uc=name.charAt(0).toUpperCase()+name.substr(1);
return (apn[name]={n:name+"Node",s:"_set"+uc+"Attr",g:"_get"+uc+"Attr"});
},toString:function(){
return "[Widget "+this.declaredClass+", "+(this.id||"NO ID")+"]";
},getDescendants:function(){
return this.containerNode?dojo.query("[widgetId]",this.containerNode).map(dijit.byNode):[];
},getChildren:function(){
return this.containerNode?dijit.findWidgets(this.containerNode):[];
},nodesWithKeyClick:["input","button"],connect:function(obj,_381,_382){
var d=dojo,dc=d._connect,_383=[];
if(_381=="ondijitclick"){
if(dojo.indexOf(this.nodesWithKeyClick,obj.nodeName.toLowerCase())==-1){
var m=d.hitch(this,_382);
_383.push(dc(obj,"onkeydown",this,function(e){
if((e.keyCode==d.keys.ENTER||e.keyCode==d.keys.SPACE)&&!e.ctrlKey&&!e.shiftKey&&!e.altKey&&!e.metaKey){
dijit._lastKeyDownNode=e.target;
e.preventDefault();
}
}),dc(obj,"onkeyup",this,function(e){
if((e.keyCode==d.keys.ENTER||e.keyCode==d.keys.SPACE)&&e.target===dijit._lastKeyDownNode&&!e.ctrlKey&&!e.shiftKey&&!e.altKey&&!e.metaKey){
dijit._lastKeyDownNode=null;
return m(e);
}
}));
}
_381="onclick";
}
_383.push(dc(obj,_381,this,_382));
this._connects.push(_383);
return _383;
},disconnect:function(_384){
for(var i=0;i<this._connects.length;i++){
if(this._connects[i]==_384){
dojo.forEach(_384,dojo.disconnect);
this._connects.splice(i,1);
return;
}
}
},subscribe:function(_385,_386){
var d=dojo,_387=d.subscribe(_385,this,_386);
this._subscribes.push(_387);
return _387;
},unsubscribe:function(_388){
for(var i=0;i<this._subscribes.length;i++){
if(this._subscribes[i]==_388){
dojo.unsubscribe(_388);
this._subscribes.splice(i,1);
return;
}
}
},isLeftToRight:function(){
return this.dir?(this.dir=="ltr"):dojo._isBodyLtr();
},isFocusable:function(){
return this.focus&&(dojo.style(this.domNode,"display")!="none");
},placeAt:function(_389,_38a){
if(_389.declaredClass&&_389.addChild){
_389.addChild(this,_38a);
}else{
dojo.place(this.domNode,_389,_38a);
}
return this;
},_onShow:function(){
this.onShow();
},onShow:function(){
},onHide:function(){
},onClose:function(){
return true;
}});
})();
}
if(!dojo._hasResource["dojo.string"]){
dojo._hasResource["dojo.string"]=true;
dojo.provide("dojo.string");
dojo.string.rep=function(str,num){
if(num<=0||!str){
return "";
}
var buf=[];
for(;;){
if(num&1){
buf.push(str);
}
if(!(num>>=1)){
break;
}
str+=str;
}
return buf.join("");
};
dojo.string.pad=function(text,size,ch,end){
if(!ch){
ch="0";
}
var out=String(text),pad=dojo.string.rep(ch,Math.ceil((size-out.length)/ch.length));
return end?out+pad:pad+out;
};
dojo.string.substitute=function(_38b,map,_38c,_38d){
_38d=_38d||dojo.global;
_38c=_38c?dojo.hitch(_38d,_38c):function(v){
return v;
};
return _38b.replace(/\$\{([^\s\:\}]+)(?:\:([^\s\:\}]+))?\}/g,function(_38e,key,_38f){
var _390=dojo.getObject(key,false,map);
if(_38f){
_390=dojo.getObject(_38f,false,_38d).call(_38d,_390,key);
}
return _38c(_390,key).toString();
});
};
dojo.string.trim=String.prototype.trim?dojo.trim:function(str){
str=str.replace(/^\s+/,"");
for(var i=str.length-1;i>=0;i--){
if(/\S/.test(str.charAt(i))){
str=str.substring(0,i+1);
break;
}
}
return str;
};
}
if(!dojo._hasResource["dojo.date.stamp"]){
dojo._hasResource["dojo.date.stamp"]=true;
dojo.provide("dojo.date.stamp");
dojo.date.stamp.fromISOString=function(_391,_392){
if(!dojo.date.stamp._isoRegExp){
dojo.date.stamp._isoRegExp=/^(?:(\d{4})(?:-(\d{2})(?:-(\d{2}))?)?)?(?:T(\d{2}):(\d{2})(?::(\d{2})(.\d+)?)?((?:[+-](\d{2}):(\d{2}))|Z)?)?$/;
}
var _393=dojo.date.stamp._isoRegExp.exec(_391),_394=null;
if(_393){
_393.shift();
if(_393[1]){
_393[1]--;
}
if(_393[6]){
_393[6]*=1000;
}
if(_392){
_392=new Date(_392);
dojo.forEach(dojo.map(["FullYear","Month","Date","Hours","Minutes","Seconds","Milliseconds"],function(prop){
return _392["get"+prop]();
}),function(_395,_396){
_393[_396]=_393[_396]||_395;
});
}
_394=new Date(_393[0]||1970,_393[1]||0,_393[2]||1,_393[3]||0,_393[4]||0,_393[5]||0,_393[6]||0);
if(_393[0]<100){
_394.setFullYear(_393[0]||1970);
}
var _397=0,_398=_393[7]&&_393[7].charAt(0);
if(_398!="Z"){
_397=((_393[8]||0)*60)+(Number(_393[9])||0);
if(_398!="-"){
_397*=-1;
}
}
if(_398){
_397-=_394.getTimezoneOffset();
}
if(_397){
_394.setTime(_394.getTime()+_397*60000);
}
}
return _394;
};
dojo.date.stamp.toISOString=function(_399,_39a){
var _39b=function(n){
return (n<10)?"0"+n:n;
};
_39a=_39a||{};
var _39c=[],_39d=_39a.zulu?"getUTC":"get",date="";
if(_39a.selector!="time"){
var year=_399[_39d+"FullYear"]();
date=["0000".substr((year+"").length)+year,_39b(_399[_39d+"Month"]()+1),_39b(_399[_39d+"Date"]())].join("-");
}
_39c.push(date);
if(_39a.selector!="date"){
var time=[_39b(_399[_39d+"Hours"]()),_39b(_399[_39d+"Minutes"]()),_39b(_399[_39d+"Seconds"]())].join(":");
var _39e=_399[_39d+"Milliseconds"]();
if(_39a.milliseconds){
time+="."+(_39e<100?"0":"")+_39b(_39e);
}
if(_39a.zulu){
time+="Z";
}else{
if(_39a.selector!="time"){
var _39f=_399.getTimezoneOffset();
var _3a0=Math.abs(_39f);
time+=(_39f>0?"-":"+")+_39b(Math.floor(_3a0/60))+":"+_39b(_3a0%60);
}
}
_39c.push(time);
}
return _39c.join("T");
};
}
if(!dojo._hasResource["dojo.parser"]){
dojo._hasResource["dojo.parser"]=true;
dojo.provide("dojo.parser");
new Date("X");
dojo.parser=new function(){
var d=dojo;
this._attrName=d._scopeName+"Type";
this._query="["+this._attrName+"]";
function _3a1(_3a2){
if(d.isString(_3a2)){
return "string";
}
if(typeof _3a2=="number"){
return "number";
}
if(typeof _3a2=="boolean"){
return "boolean";
}
if(d.isFunction(_3a2)){
return "function";
}
if(d.isArray(_3a2)){
return "array";
}
if(_3a2 instanceof Date){
return "date";
}
if(_3a2 instanceof d._Url){
return "url";
}
return "object";
};
function _3a3(_3a4,type){
switch(type){
case "string":
return _3a4;
case "number":
return _3a4.length?Number(_3a4):NaN;
case "boolean":
return typeof _3a4=="boolean"?_3a4:!(_3a4.toLowerCase()=="false");
case "function":
if(d.isFunction(_3a4)){
_3a4=_3a4.toString();
_3a4=d.trim(_3a4.substring(_3a4.indexOf("{")+1,_3a4.length-1));
}
try{
if(_3a4===""||_3a4.search(/[^\w\.]+/i)!=-1){
return new Function(_3a4);
}else{
return d.getObject(_3a4,false)||new Function(_3a4);
}
}
catch(e){
return new Function();
}
case "array":
return _3a4?_3a4.split(/\s*,\s*/):[];
case "date":
switch(_3a4){
case "":
return new Date("");
case "now":
return new Date();
default:
return d.date.stamp.fromISOString(_3a4);
}
case "url":
return d.baseUrl+_3a4;
default:
return d.fromJson(_3a4);
}
};
var _3a5={};
dojo.connect(dojo,"extend",function(){
_3a5={};
});
function _3a6(_3a7){
if(!_3a5[_3a7]){
var cls=d.getObject(_3a7);
if(!cls){
return null;
}
var _3a8=cls.prototype;
var _3a9={},_3aa={};
for(var name in _3a8){
if(name.charAt(0)=="_"){
continue;
}
if(name in _3aa){
continue;
}
var _3ab=_3a8[name];
_3a9[name]=_3a1(_3ab);
}
_3a5[_3a7]={cls:cls,params:_3a9};
}
return _3a5[_3a7];
};
this._functionFromScript=function(_3ac){
var _3ad="";
var _3ae="";
var _3af=_3ac.getAttribute("args");
if(_3af){
d.forEach(_3af.split(/\s*,\s*/),function(part,idx){
_3ad+="var "+part+" = arguments["+idx+"]; ";
});
}
var _3b0=_3ac.getAttribute("with");
if(_3b0&&_3b0.length){
d.forEach(_3b0.split(/\s*,\s*/),function(part){
_3ad+="with("+part+"){";
_3ae+="}";
});
}
return new Function(_3ad+_3ac.innerHTML+_3ae);
};
this.instantiate=function(_3b1,_3b2,args){
var _3b3=[],dp=dojo.parser;
_3b2=_3b2||{};
args=args||{};
d.forEach(_3b1,function(obj){
if(!obj){
return;
}
var node,type,_3b4,_3b5,_3b6;
if(obj.node){
node=obj.node;
type=obj.type;
_3b4=obj.clsInfo||(type&&_3a6(type));
_3b5=_3b4&&_3b4.cls;
_3b6=obj.scripts;
}else{
node=obj;
type=dp._attrName in _3b2?_3b2[dp._attrName]:node.getAttribute(dp._attrName);
_3b4=type&&_3a6(type);
_3b5=_3b4&&_3b4.cls;
_3b6=(_3b5&&(_3b5._noScript||_3b5.prototype._noScript)?[]:d.query("> script[type^='dojo/']",node));
}
if(!_3b4){
throw new Error("Could not load class '"+type);
}
var _3b7={},_3b8=node.attributes;
if(args.defaults){
dojo.mixin(_3b7,args.defaults);
}
if(obj.inherited){
dojo.mixin(_3b7,obj.inherited);
}
for(var name in _3b4.params){
var item=name in _3b2?{value:_3b2[name],specified:true}:_3b8.getNamedItem(name);
if(!item||(!item.specified&&(!dojo.isIE||name.toLowerCase()!="value"))){
continue;
}
var _3b9=item.value;
switch(name){
case "class":
_3b9="className" in _3b2?_3b2.className:node.className;
break;
case "style":
_3b9="style" in _3b2?_3b2.style:(node.style&&node.style.cssText);
}
var _3ba=_3b4.params[name];
if(typeof _3b9=="string"){
_3b7[name]=_3a3(_3b9,_3ba);
}else{
_3b7[name]=_3b9;
}
}
var _3bb=[],_3bc=[];
d.forEach(_3b6,function(_3bd){
node.removeChild(_3bd);
var _3be=_3bd.getAttribute("event"),type=_3bd.getAttribute("type"),nf=d.parser._functionFromScript(_3bd);
if(_3be){
if(type=="dojo/connect"){
_3bb.push({event:_3be,func:nf});
}else{
_3b7[_3be]=nf;
}
}else{
_3bc.push(nf);
}
});
var _3bf=_3b5.markupFactory||_3b5.prototype&&_3b5.prototype.markupFactory;
var _3c0=_3bf?_3bf(_3b7,node,_3b5):new _3b5(_3b7,node);
_3b3.push(_3c0);
var _3c1=node.getAttribute("jsId");
if(_3c1){
d.setObject(_3c1,_3c0);
}
d.forEach(_3bb,function(_3c2){
d.connect(_3c0,_3c2.event,null,_3c2.func);
});
d.forEach(_3bc,function(func){
func.call(_3c0);
});
});
if(!_3b2._started){
d.forEach(_3b3,function(_3c3){
if(!args.noStart&&_3c3&&_3c3.startup&&!_3c3._started&&(!_3c3.getParent||!_3c3.getParent())){
_3c3.startup();
}
});
}
return _3b3;
};
this.parse=function(_3c4,args){
var root;
if(!args&&_3c4&&_3c4.rootNode){
args=_3c4;
root=args.rootNode;
}else{
root=_3c4;
}
var _3c5=this._attrName;
function scan(_3c6,list){
var _3c7=dojo.clone(_3c6.inherited);
dojo.forEach(["dir","lang"],function(name){
var val=_3c6.node.getAttribute(name);
if(val){
_3c7[name]=val;
}
});
var _3c8=_3c6.scripts;
var _3c9=!_3c6.clsInfo||!_3c6.clsInfo.cls.prototype.stopParser;
for(var _3ca=_3c6.node.firstChild;_3ca;_3ca=_3ca.nextSibling){
if(_3ca.nodeType==1){
var type=_3c9&&_3ca.getAttribute(_3c5);
if(type){
var _3cb={"type":type,clsInfo:_3a6(type),node:_3ca,scripts:[],inherited:_3c7};
list.push(_3cb);
scan(_3cb,list);
}else{
if(_3c8&&_3ca.nodeName.toLowerCase()=="script"){
type=_3ca.getAttribute("type");
if(type&&/^dojo\//i.test(type)){
_3c8.push(_3ca);
}
}else{
if(_3c9){
scan({node:_3ca,inherited:_3c7},list);
}
}
}
}
}
};
var list=[];
scan({node:root?dojo.byId(root):dojo.body(),inherited:(args&&args.inherited)||{dir:dojo._isBodyLtr()?"ltr":"rtl"}},list);
return this.instantiate(list,null,args);
};
}();
(function(){
var _3cc=function(){
if(dojo.config.parseOnLoad){
dojo.parser.parse();
}
};
if(dojo.exists("dijit.wai.onload")&&(dijit.wai.onload===dojo._loaders[0])){
dojo._loaders.splice(1,0,_3cc);
}else{
dojo._loaders.unshift(_3cc);
}
})();
}
if(!dojo._hasResource["dojo.cache"]){
dojo._hasResource["dojo.cache"]=true;
dojo.provide("dojo.cache");
(function(){
var _3cd={};
dojo.cache=function(_3ce,url,_3cf){
if(typeof _3ce=="string"){
var _3d0=dojo.moduleUrl(_3ce,url);
}else{
_3d0=_3ce;
_3cf=url;
}
var key=_3d0.toString();
var val=_3cf;
if(_3cf!=undefined&&!dojo.isString(_3cf)){
val=("value" in _3cf?_3cf.value:undefined);
}
var _3d1=_3cf&&_3cf.sanitize?true:false;
if(typeof val=="string"){
val=_3cd[key]=_3d1?dojo.cache._sanitize(val):val;
}else{
if(val===null){
delete _3cd[key];
}else{
if(!(key in _3cd)){
val=dojo._getText(key);
_3cd[key]=_3d1?dojo.cache._sanitize(val):val;
}
val=_3cd[key];
}
}
return val;
};
dojo.cache._sanitize=function(val){
if(val){
val=val.replace(/^\s*<\?xml(\s)+version=[\'\"](\d)*.(\d)*[\'\"](\s)*\?>/im,"");
var _3d2=val.match(/<body[^>]*>\s*([\s\S]+)\s*<\/body>/im);
if(_3d2){
val=_3d2[1];
}
}else{
val="";
}
return val;
};
})();
}
if(!dojo._hasResource["dijit._Templated"]){
dojo._hasResource["dijit._Templated"]=true;
dojo.provide("dijit._Templated");
dojo.declare("dijit._Templated",null,{templateString:null,templatePath:null,widgetsInTemplate:false,_skipNodeCache:false,_earlyTemplatedStartup:false,constructor:function(){
this._attachPoints=[];
},_stringRepl:function(tmpl){
var _3d3=this.declaredClass,_3d4=this;
return dojo.string.substitute(tmpl,this,function(_3d5,key){
if(key.charAt(0)=="!"){
_3d5=dojo.getObject(key.substr(1),false,_3d4);
}
if(typeof _3d5=="undefined"){
throw new Error(_3d3+" template:"+key);
}
if(_3d5==null){
return "";
}
return key.charAt(0)=="!"?_3d5:_3d5.toString().replace(/"/g,"&quot;");
},this);
},buildRendering:function(){
var _3d6=dijit._Templated.getCachedTemplate(this.templatePath,this.templateString,this._skipNodeCache);
var node;
if(dojo.isString(_3d6)){
node=dojo._toDom(this._stringRepl(_3d6));
if(node.nodeType!=1){
throw new Error("Invalid template: "+_3d6);
}
}else{
node=_3d6.cloneNode(true);
}
this.domNode=node;
this._attachTemplateNodes(node);
if(this.widgetsInTemplate){
var _3d7=dojo.parser,qry,attr;
if(_3d7._query!="[dojoType]"){
qry=_3d7._query;
attr=_3d7._attrName;
_3d7._query="[dojoType]";
_3d7._attrName="dojoType";
}
var cw=(this._startupWidgets=dojo.parser.parse(node,{noStart:!this._earlyTemplatedStartup,inherited:{dir:this.dir,lang:this.lang}}));
if(qry){
_3d7._query=qry;
_3d7._attrName=attr;
}
this._supportingWidgets=dijit.findWidgets(node);
this._attachTemplateNodes(cw,function(n,p){
return n[p];
});
}
this._fillContent(this.srcNodeRef);
},_fillContent:function(_3d8){
var dest=this.containerNode;
if(_3d8&&dest){
while(_3d8.hasChildNodes()){
dest.appendChild(_3d8.firstChild);
}
}
},_attachTemplateNodes:function(_3d9,_3da){
_3da=_3da||function(n,p){
return n.getAttribute(p);
};
var _3db=dojo.isArray(_3d9)?_3d9:(_3d9.all||_3d9.getElementsByTagName("*"));
var x=dojo.isArray(_3d9)?0:-1;
for(;x<_3db.length;x++){
var _3dc=(x==-1)?_3d9:_3db[x];
if(this.widgetsInTemplate&&_3da(_3dc,"dojoType")){
continue;
}
var _3dd=_3da(_3dc,"dojoAttachPoint");
if(_3dd){
var _3de,_3df=_3dd.split(/\s*,\s*/);
while((_3de=_3df.shift())){
if(dojo.isArray(this[_3de])){
this[_3de].push(_3dc);
}else{
this[_3de]=_3dc;
}
this._attachPoints.push(_3de);
}
}
var _3e0=_3da(_3dc,"dojoAttachEvent");
if(_3e0){
var _3e1,_3e2=_3e0.split(/\s*,\s*/);
var trim=dojo.trim;
while((_3e1=_3e2.shift())){
if(_3e1){
var _3e3=null;
if(_3e1.indexOf(":")!=-1){
var _3e4=_3e1.split(":");
_3e1=trim(_3e4[0]);
_3e3=trim(_3e4[1]);
}else{
_3e1=trim(_3e1);
}
if(!_3e3){
_3e3=_3e1;
}
this.connect(_3dc,_3e1,_3e3);
}
}
}
var role=_3da(_3dc,"waiRole");
if(role){
dijit.setWaiRole(_3dc,role);
}
var _3e5=_3da(_3dc,"waiState");
if(_3e5){
dojo.forEach(_3e5.split(/\s*,\s*/),function(_3e6){
if(_3e6.indexOf("-")!=-1){
var pair=_3e6.split("-");
dijit.setWaiState(_3dc,pair[0],pair[1]);
}
});
}
}
},startup:function(){
dojo.forEach(this._startupWidgets,function(w){
if(w&&!w._started&&w.startup){
w.startup();
}
});
this.inherited(arguments);
},destroyRendering:function(){
dojo.forEach(this._attachPoints,function(_3e7){
delete this[_3e7];
},this);
this._attachPoints=[];
this.inherited(arguments);
}});
dijit._Templated._templateCache={};
dijit._Templated.getCachedTemplate=function(_3e8,_3e9,_3ea){
var _3eb=dijit._Templated._templateCache;
var key=_3e9||_3e8;
var _3ec=_3eb[key];
if(_3ec){
try{
if(!_3ec.ownerDocument||_3ec.ownerDocument==dojo.doc){
return _3ec;
}
}
catch(e){
}
dojo.destroy(_3ec);
}
if(!_3e9){
_3e9=dojo.cache(_3e8,{sanitize:true});
}
_3e9=dojo.string.trim(_3e9);
if(_3ea||_3e9.match(/\$\{([^\}]+)\}/g)){
return (_3eb[key]=_3e9);
}else{
var node=dojo._toDom(_3e9);
if(node.nodeType!=1){
throw new Error("Invalid template: "+_3e9);
}
return (_3eb[key]=node);
}
};
if(dojo.isIE){
dojo.addOnWindowUnload(function(){
var _3ed=dijit._Templated._templateCache;
for(var key in _3ed){
var _3ee=_3ed[key];
if(typeof _3ee=="object"){
dojo.destroy(_3ee);
}
delete _3ed[key];
}
});
}
dojo.extend(dijit._Widget,{dojoAttachEvent:"",dojoAttachPoint:"",waiRole:"",waiState:""});
}
if(!dojo._hasResource["dijit._CssStateMixin"]){
dojo._hasResource["dijit._CssStateMixin"]=true;
dojo.provide("dijit._CssStateMixin");
dojo.declare("dijit._CssStateMixin",[],{cssStateNodes:{},postCreate:function(){
this.inherited(arguments);
dojo.forEach(["onmouseenter","onmouseleave","onmousedown"],function(e){
this.connect(this.domNode,e,"_cssMouseEvent");
},this);
this.connect(this,"set",function(name,_3ef){
if(arguments.length>=2&&{disabled:true,readOnly:true,checked:true,selected:true}[name]){
this._setStateClass();
}
});
dojo.forEach(["_onFocus","_onBlur"],function(ap){
this.connect(this,ap,"_setStateClass");
},this);
for(var ap in this.cssStateNodes){
this._trackMouseState(this[ap],this.cssStateNodes[ap]);
}
this._setStateClass();
},_cssMouseEvent:function(_3f0){
if(!this.disabled){
switch(_3f0.type){
case "mouseenter":
case "mouseover":
this._hovering=true;
this._active=this._mouseDown;
break;
case "mouseleave":
case "mouseout":
this._hovering=false;
this._active=false;
break;
case "mousedown":
this._active=true;
this._mouseDown=true;
var _3f1=this.connect(dojo.body(),"onmouseup",function(){
this._active=false;
this._mouseDown=false;
this._setStateClass();
this.disconnect(_3f1);
});
break;
}
this._setStateClass();
}
},_setStateClass:function(){
var _3f2=this.baseClass.split(" ");
function _3f3(_3f4){
_3f2=_3f2.concat(dojo.map(_3f2,function(c){
return c+_3f4;
}),"dijit"+_3f4);
};
if(!this.isLeftToRight()){
_3f3("Rtl");
}
if(this.checked){
_3f3("Checked");
}
if(this.state){
_3f3(this.state);
}
if(this.selected){
_3f3("Selected");
}
if(this.disabled){
_3f3("Disabled");
}else{
if(this.readOnly){
_3f3("ReadOnly");
}else{
if(this._active){
_3f3("Active");
}else{
if(this._hovering){
_3f3("Hover");
}
}
}
}
if(this._focused){
_3f3("Focused");
}
var tn=this.stateNode||this.domNode,_3f5={};
dojo.forEach(tn.className.split(" "),function(c){
_3f5[c]=true;
});
if("_stateClasses" in this){
dojo.forEach(this._stateClasses,function(c){
delete _3f5[c];
});
}
dojo.forEach(_3f2,function(c){
_3f5[c]=true;
});
var _3f6=[];
for(var c in _3f5){
_3f6.push(c);
}
tn.className=_3f6.join(" ");
this._stateClasses=_3f2;
},_trackMouseState:function(node,_3f7){
var _3f8=false,_3f9=false,_3fa=false;
var self=this,cn=dojo.hitch(this,"connect",node);
function _3fb(){
var _3fc=("disabled" in self&&self.disabled)||("readonly" in self&&self.readonly);
dojo.toggleClass(node,_3f7+"Hover",_3f8&&!_3f9&&!_3fc);
dojo.toggleClass(node,_3f7+"Active",_3f9&&!_3fc);
dojo.toggleClass(node,_3f7+"Focused",_3fa&&!_3fc);
};
cn("onmouseenter",function(){
_3f8=true;
_3fb();
});
cn("onmouseleave",function(){
_3f8=false;
_3f9=false;
_3fb();
});
cn("onmousedown",function(){
_3f9=true;
_3fb();
});
cn("onmouseup",function(){
_3f9=false;
_3fb();
});
cn("onfocus",function(){
_3fa=true;
_3fb();
});
cn("onblur",function(){
_3fa=false;
_3fb();
});
this.connect(this,"set",function(name,_3fd){
if(name=="disabled"||name=="readOnly"){
_3fb();
}
});
}});
}
if(!dojo._hasResource["dijit.form._FormWidget"]){
dojo._hasResource["dijit.form._FormWidget"]=true;
dojo.provide("dijit.form._FormWidget");
dojo.declare("dijit.form._FormWidget",[dijit._Widget,dijit._Templated,dijit._CssStateMixin],{name:"",alt:"",value:"",type:"text",tabIndex:"0",disabled:false,intermediateChanges:false,scrollOnFocus:true,attributeMap:dojo.delegate(dijit._Widget.prototype.attributeMap,{value:"focusNode",id:"focusNode",tabIndex:"focusNode",alt:"focusNode",title:"focusNode"}),postMixInProperties:function(){
this.nameAttrSetting=this.name?("name=\""+this.name.replace(/'/g,"&quot;")+"\""):"";
this.inherited(arguments);
},postCreate:function(){
this.inherited(arguments);
this.connect(this.domNode,"onmousedown","_onMouseDown");
},_setDisabledAttr:function(_3fe){
this.disabled=_3fe;
dojo.attr(this.focusNode,"disabled",_3fe);
if(this.valueNode){
dojo.attr(this.valueNode,"disabled",_3fe);
}
dijit.setWaiState(this.focusNode,"disabled",_3fe);
if(_3fe){
this._hovering=false;
this._active=false;
var _3ff="tabIndex" in this.attributeMap?this.attributeMap.tabIndex:"focusNode";
dojo.forEach(dojo.isArray(_3ff)?_3ff:[_3ff],function(_400){
var node=this[_400];
if(dojo.isWebKit||dijit.hasDefaultTabStop(node)){
node.setAttribute("tabIndex","-1");
}else{
node.removeAttribute("tabIndex");
}
},this);
}else{
this.focusNode.setAttribute("tabIndex",this.tabIndex);
}
},setDisabled:function(_401){
dojo.deprecated("setDisabled("+_401+") is deprecated. Use set('disabled',"+_401+") instead.","","2.0");
this.set("disabled",_401);
},_onFocus:function(e){
if(this.scrollOnFocus){
dojo.window.scrollIntoView(this.domNode);
}
this.inherited(arguments);
},isFocusable:function(){
return !this.disabled&&!this.readOnly&&this.focusNode&&(dojo.style(this.domNode,"display")!="none");
},focus:function(){
dijit.focus(this.focusNode);
},compare:function(val1,val2){
if(typeof val1=="number"&&typeof val2=="number"){
return (isNaN(val1)&&isNaN(val2))?0:val1-val2;
}else{
if(val1>val2){
return 1;
}else{
if(val1<val2){
return -1;
}else{
return 0;
}
}
}
},onChange:function(_402){
},_onChangeActive:false,_handleOnChange:function(_403,_404){
this._lastValue=_403;
if(this._lastValueReported==undefined&&(_404===null||!this._onChangeActive)){
this._resetValue=this._lastValueReported=_403;
}
if((this.intermediateChanges||_404||_404===undefined)&&((typeof _403!=typeof this._lastValueReported)||this.compare(_403,this._lastValueReported)!=0)){
this._lastValueReported=_403;
if(this._onChangeActive){
if(this._onChangeHandle){
clearTimeout(this._onChangeHandle);
}
this._onChangeHandle=setTimeout(dojo.hitch(this,function(){
this._onChangeHandle=null;
this.onChange(_403);
}),0);
}
}
},create:function(){
this.inherited(arguments);
this._onChangeActive=true;
},destroy:function(){
if(this._onChangeHandle){
clearTimeout(this._onChangeHandle);
this.onChange(this._lastValueReported);
}
this.inherited(arguments);
},setValue:function(_405){
dojo.deprecated("dijit.form._FormWidget:setValue("+_405+") is deprecated.  Use set('value',"+_405+") instead.","","2.0");
this.set("value",_405);
},getValue:function(){
dojo.deprecated(this.declaredClass+"::getValue() is deprecated. Use get('value') instead.","","2.0");
return this.get("value");
},_onMouseDown:function(e){
if(!e.ctrlKey&&this.isFocusable()){
var _406=this.connect(dojo.body(),"onmouseup",function(){
if(this.isFocusable()){
this.focus();
}
this.disconnect(_406);
});
}
}});
dojo.declare("dijit.form._FormValueWidget",dijit.form._FormWidget,{readOnly:false,attributeMap:dojo.delegate(dijit.form._FormWidget.prototype.attributeMap,{value:"",readOnly:"focusNode"}),_setReadOnlyAttr:function(_407){
this.readOnly=_407;
dojo.attr(this.focusNode,"readOnly",_407);
dijit.setWaiState(this.focusNode,"readonly",_407);
},postCreate:function(){
this.inherited(arguments);
if(dojo.isIE){
this.connect(this.focusNode||this.domNode,"onkeydown",this._onKeyDown);
}
if(this._resetValue===undefined){
this._resetValue=this.value;
}
},_setValueAttr:function(_408,_409){
this.value=_408;
this._handleOnChange(_408,_409);
},_getValueAttr:function(){
return this._lastValue;
},undo:function(){
this._setValueAttr(this._lastValueReported,false);
},reset:function(){
this._hasBeenBlurred=false;
this._setValueAttr(this._resetValue,true);
},_onKeyDown:function(e){
if(e.keyCode==dojo.keys.ESCAPE&&!(e.ctrlKey||e.altKey||e.metaKey)){
var te;
if(dojo.isIE){
e.preventDefault();
te=document.createEventObject();
te.keyCode=dojo.keys.ESCAPE;
te.shiftKey=e.shiftKey;
e.srcElement.fireEvent("onkeypress",te);
}
}
},_layoutHackIE7:function(){
if(dojo.isIE==7){
var _40a=this.domNode;
var _40b=_40a.parentNode;
var _40c=_40a.firstChild||_40a;
var _40d=_40c.style.filter;
var _40e=this;
while(_40b&&_40b.clientHeight==0){
(function ping(){
var _40f=_40e.connect(_40b,"onscroll",function(e){
_40e.disconnect(_40f);
_40c.style.filter=(new Date()).getMilliseconds();
setTimeout(function(){
_40c.style.filter=_40d;
},0);
});
})();
_40b=_40b.parentNode;
}
}
}});
}
if(!dojo._hasResource["dijit._Container"]){
dojo._hasResource["dijit._Container"]=true;
dojo.provide("dijit._Container");
dojo.declare("dijit._Container",null,{isContainer:true,buildRendering:function(){
this.inherited(arguments);
if(!this.containerNode){
this.containerNode=this.domNode;
}
},addChild:function(_410,_411){
var _412=this.containerNode;
if(_411&&typeof _411=="number"){
var _413=this.getChildren();
if(_413&&_413.length>=_411){
_412=_413[_411-1].domNode;
_411="after";
}
}
dojo.place(_410.domNode,_412,_411);
if(this._started&&!_410._started){
_410.startup();
}
},removeChild:function(_414){
if(typeof _414=="number"&&_414>0){
_414=this.getChildren()[_414];
}
if(_414){
var node=_414.domNode;
if(node&&node.parentNode){
node.parentNode.removeChild(node);
}
}
},hasChildren:function(){
return this.getChildren().length>0;
},destroyDescendants:function(_415){
dojo.forEach(this.getChildren(),function(_416){
_416.destroyRecursive(_415);
});
},_getSiblingOfChild:function(_417,dir){
var node=_417.domNode,_418=(dir>0?"nextSibling":"previousSibling");
do{
node=node[_418];
}while(node&&(node.nodeType!=1||!dijit.byNode(node)));
return node&&dijit.byNode(node);
},getIndexOfChild:function(_419){
return dojo.indexOf(this.getChildren(),_419);
},startup:function(){
if(this._started){
return;
}
dojo.forEach(this.getChildren(),function(_41a){
_41a.startup();
});
this.inherited(arguments);
}});
}
if(!dojo._hasResource["dijit._HasDropDown"]){
dojo._hasResource["dijit._HasDropDown"]=true;
dojo.provide("dijit._HasDropDown");
dojo.declare("dijit._HasDropDown",null,{_buttonNode:null,_arrowWrapperNode:null,_popupStateNode:null,_aroundNode:null,dropDown:null,autoWidth:true,forceWidth:false,maxHeight:0,dropDownPosition:["below","above"],_stopClickEvents:true,_onDropDownMouseDown:function(e){
if(this.disabled||this.readOnly){
return;
}
this._docHandler=this.connect(dojo.doc,"onmouseup","_onDropDownMouseUp");
this.toggleDropDown();
},_onDropDownMouseUp:function(e){
if(e&&this._docHandler){
this.disconnect(this._docHandler);
}
var _41b=this.dropDown,_41c=false;
if(e&&this._opened){
var c=dojo.position(this._buttonNode,true);
if(!(e.pageX>=c.x&&e.pageX<=c.x+c.w)||!(e.pageY>=c.y&&e.pageY<=c.y+c.h)){
var t=e.target;
while(t&&!_41c){
if(dojo.hasClass(t,"dijitPopup")){
_41c=true;
}else{
t=t.parentNode;
}
}
if(_41c){
t=e.target;
if(_41b.onItemClick){
var _41d;
while(t&&!(_41d=dijit.byNode(t))){
t=t.parentNode;
}
if(_41d&&_41d.onClick&&_41d.getParent){
_41d.getParent().onItemClick(_41d,e);
}
}
return;
}
}
}
if(this._opened&&_41b.focus){
window.setTimeout(dojo.hitch(_41b,"focus"),1);
}
},_onDropDownClick:function(e){
if(this._stopClickEvents){
dojo.stopEvent(e);
}
},_setupDropdown:function(){
this._buttonNode=this._buttonNode||this.focusNode||this.domNode;
this._popupStateNode=this._popupStateNode||this.focusNode||this._buttonNode;
this._aroundNode=this._aroundNode||this.domNode;
this.connect(this._buttonNode,"onmousedown","_onDropDownMouseDown");
this.connect(this._buttonNode,"onclick","_onDropDownClick");
this.connect(this._buttonNode,"onkeydown","_onDropDownKeydown");
this.connect(this._buttonNode,"onkeyup","_onKey");
if(this._setStateClass){
this.connect(this,"openDropDown","_setStateClass");
this.connect(this,"closeDropDown","_setStateClass");
}
var _41e={"after":this.isLeftToRight()?"Right":"Left","before":this.isLeftToRight()?"Left":"Right","above":"Up","below":"Down","left":"Left","right":"Right"}[this.dropDownPosition[0]]||this.dropDownPosition[0]||"Down";
dojo.addClass(this._arrowWrapperNode||this._buttonNode,"dijit"+_41e+"ArrowButton");
},postCreate:function(){
this._setupDropdown();
this.inherited(arguments);
},destroyDescendants:function(){
if(this.dropDown){
if(!this.dropDown._destroyed){
this.dropDown.destroyRecursive();
}
delete this.dropDown;
}
this.inherited(arguments);
},_onDropDownKeydown:function(e){
if(e.keyCode==dojo.keys.DOWN_ARROW||e.keyCode==dojo.keys.ENTER||e.keyCode==dojo.keys.SPACE){
e.preventDefault();
}
},_onKey:function(e){
if(this.disabled||this.readOnly){
return;
}
var d=this.dropDown;
if(d&&this._opened&&d.handleKey){
if(d.handleKey(e)===false){
return;
}
}
if(d&&this._opened&&e.keyCode==dojo.keys.ESCAPE){
this.toggleDropDown();
}else{
if(d&&!this._opened&&(e.keyCode==dojo.keys.DOWN_ARROW||e.keyCode==dojo.keys.ENTER||e.keyCode==dojo.keys.SPACE)){
this.toggleDropDown();
if(d.focus){
setTimeout(dojo.hitch(d,"focus"),1);
}
}
}
},_onBlur:function(){
this.closeDropDown();
this.inherited(arguments);
},isLoaded:function(){
return true;
},loadDropDown:function(_41f){
_41f();
},toggleDropDown:function(){
if(this.disabled||this.readOnly){
return;
}
this.focus();
var _420=this.dropDown;
if(!_420){
return;
}
if(!this._opened){
if(!this.isLoaded()){
this.loadDropDown(dojo.hitch(this,"openDropDown"));
return;
}else{
this.openDropDown();
}
}else{
this.closeDropDown();
}
},openDropDown:function(){
var _421=this.dropDown;
var _422=_421.domNode;
var self=this;
if(!this._preparedNode){
dijit.popup.moveOffScreen(_422);
this._preparedNode=true;
if(_422.style.width){
this._explicitDDWidth=true;
}
if(_422.style.height){
this._explicitDDHeight=true;
}
}
if(this.maxHeight||this.forceWidth||this.autoWidth){
var _423={display:"",visibility:"hidden"};
if(!this._explicitDDWidth){
_423.width="";
}
if(!this._explicitDDHeight){
_423.height="";
}
dojo.style(_422,_423);
var mb=dojo.marginBox(_422);
var _424=(this.maxHeight&&mb.h>this.maxHeight);
dojo.style(_422,{overflowX:"hidden",overflowY:_424?"auto":"hidden"});
if(_424){
mb.h=this.maxHeight;
if("w" in mb){
mb.w+=16;
}
}else{
delete mb.h;
}
delete mb.t;
delete mb.l;
if(this.forceWidth){
mb.w=this.domNode.offsetWidth;
}else{
if(this.autoWidth){
mb.w=Math.max(mb.w,this.domNode.offsetWidth);
}else{
delete mb.w;
}
}
if(dojo.isFunction(_421.resize)){
_421.resize(mb);
}else{
dojo.marginBox(_422,mb);
}
}
var _425=dijit.popup.open({parent:this,popup:_421,around:this._aroundNode,orient:dijit.getPopupAroundAlignment((this.dropDownPosition&&this.dropDownPosition.length)?this.dropDownPosition:["below"],this.isLeftToRight()),onExecute:function(){
self.closeDropDown(true);
},onCancel:function(){
self.closeDropDown(true);
},onClose:function(){
dojo.attr(self._popupStateNode,"popupActive",false);
dojo.removeClass(self._popupStateNode,"dijitHasDropDownOpen");
self._opened=false;
self.state="";
}});
dojo.attr(this._popupStateNode,"popupActive","true");
dojo.addClass(self._popupStateNode,"dijitHasDropDownOpen");
this._opened=true;
this.state="Opened";
return _425;
},closeDropDown:function(_426){
if(this._opened){
if(_426){
this.focus();
}
dijit.popup.close(this.dropDown);
this._opened=false;
this.state="";
}
}});
}
if(!dojo._hasResource["dijit.form.Button"]){
dojo._hasResource["dijit.form.Button"]=true;
dojo.provide("dijit.form.Button");
dojo.declare("dijit.form.Button",dijit.form._FormWidget,{label:"",showLabel:true,iconClass:"",type:"button",baseClass:"dijitButton",templateString:dojo.cache("dijit.form","templates/Button.html","<span class=\"dijit dijitReset dijitInline\"\n\t><span class=\"dijitReset dijitInline dijitButtonNode\"\n\t\tdojoAttachEvent=\"ondijitclick:_onButtonClick\"\n\t\t><span class=\"dijitReset dijitStretch dijitButtonContents\"\n\t\t\tdojoAttachPoint=\"titleNode,focusNode\"\n\t\t\twaiRole=\"button\" waiState=\"labelledby-${id}_label\"\n\t\t\t><span class=\"dijitReset dijitInline dijitIcon\" dojoAttachPoint=\"iconNode\"></span\n\t\t\t><span class=\"dijitReset dijitToggleButtonIconChar\">&#x25CF;</span\n\t\t\t><span class=\"dijitReset dijitInline dijitButtonText\"\n\t\t\t\tid=\"${id}_label\"\n\t\t\t\tdojoAttachPoint=\"containerNode\"\n\t\t\t></span\n\t\t></span\n\t></span\n\t><input ${!nameAttrSetting} type=\"${type}\" value=\"${value}\" class=\"dijitOffScreen\"\n\t\tdojoAttachPoint=\"valueNode\"\n/></span>\n"),attributeMap:dojo.delegate(dijit.form._FormWidget.prototype.attributeMap,{value:"valueNode",iconClass:{node:"iconNode",type:"class"}}),_onClick:function(e){
if(this.disabled){
return false;
}
this._clicked();
return this.onClick(e);
},_onButtonClick:function(e){
if(this._onClick(e)===false){
e.preventDefault();
}else{
if(this.type=="submit"&&!(this.valueNode||this.focusNode).form){
for(var node=this.domNode;node.parentNode;node=node.parentNode){
var _427=dijit.byNode(node);
if(_427&&typeof _427._onSubmit=="function"){
_427._onSubmit(e);
break;
}
}
}else{
if(this.valueNode){
this.valueNode.click();
e.preventDefault();
}
}
}
},_fillContent:function(_428){
if(_428&&(!this.params||!("label" in this.params))){
this.set("label",_428.innerHTML);
}
},postCreate:function(){
dojo.setSelectable(this.focusNode,false);
this.inherited(arguments);
},_setShowLabelAttr:function(val){
if(this.containerNode){
dojo.toggleClass(this.containerNode,"dijitDisplayNone",!val);
}
this.showLabel=val;
},onClick:function(e){
return true;
},_clicked:function(e){
},setLabel:function(_429){
dojo.deprecated("dijit.form.Button.setLabel() is deprecated.  Use set('label', ...) instead.","","2.0");
this.set("label",_429);
},_setLabelAttr:function(_42a){
this.containerNode.innerHTML=this.label=_42a;
if(this.showLabel==false&&!this.params.title){
this.titleNode.title=dojo.trim(this.containerNode.innerText||this.containerNode.textContent||"");
}
}});
dojo.declare("dijit.form.DropDownButton",[dijit.form.Button,dijit._Container,dijit._HasDropDown],{baseClass:"dijitDropDownButton",templateString:dojo.cache("dijit.form","templates/DropDownButton.html","<span class=\"dijit dijitReset dijitInline\"\n\t><span class='dijitReset dijitInline dijitButtonNode'\n\t\tdojoAttachEvent=\"ondijitclick:_onButtonClick\" dojoAttachPoint=\"_buttonNode\"\n\t\t><span class=\"dijitReset dijitStretch dijitButtonContents\"\n\t\t\tdojoAttachPoint=\"focusNode,titleNode,_arrowWrapperNode\"\n\t\t\twaiRole=\"button\" waiState=\"haspopup-true,labelledby-${id}_label\"\n\t\t\t><span class=\"dijitReset dijitInline dijitIcon\"\n\t\t\t\tdojoAttachPoint=\"iconNode\"\n\t\t\t></span\n\t\t\t><span class=\"dijitReset dijitInline dijitButtonText\"\n\t\t\t\tdojoAttachPoint=\"containerNode,_popupStateNode\"\n\t\t\t\tid=\"${id}_label\"\n\t\t\t></span\n\t\t\t><span class=\"dijitReset dijitInline dijitArrowButtonInner\"></span\n\t\t\t><span class=\"dijitReset dijitInline dijitArrowButtonChar\">&#9660;</span\n\t\t></span\n\t></span\n\t><input ${!nameAttrSetting} type=\"${type}\" value=\"${value}\" class=\"dijitOffScreen\"\n\t\tdojoAttachPoint=\"valueNode\"\n/></span>\n"),_fillContent:function(){
if(this.srcNodeRef){
var _42b=dojo.query("*",this.srcNodeRef);
dijit.form.DropDownButton.superclass._fillContent.call(this,_42b[0]);
this.dropDownContainer=this.srcNodeRef;
}
},startup:function(){
if(this._started){
return;
}
if(!this.dropDown){
var _42c=dojo.query("[widgetId]",this.dropDownContainer)[0];
this.dropDown=dijit.byNode(_42c);
delete this.dropDownContainer;
}
dijit.popup.moveOffScreen(this.dropDown.domNode);
this.inherited(arguments);
},isLoaded:function(){
var _42d=this.dropDown;
return (!_42d.href||_42d.isLoaded);
},loadDropDown:function(){
var _42e=this.dropDown;
if(!_42e){
return;
}
if(!this.isLoaded()){
var _42f=dojo.connect(_42e,"onLoad",this,function(){
dojo.disconnect(_42f);
this.openDropDown();
});
_42e.refresh();
}else{
this.openDropDown();
}
},isFocusable:function(){
return this.inherited(arguments)&&!this._mouseDown;
}});
dojo.declare("dijit.form.ComboButton",dijit.form.DropDownButton,{templateString:dojo.cache("dijit.form","templates/ComboButton.html","<table class=\"dijit dijitReset dijitInline dijitLeft\"\n\tcellspacing='0' cellpadding='0' waiRole=\"presentation\"\n\t><tbody waiRole=\"presentation\"><tr waiRole=\"presentation\"\n\t\t><td class=\"dijitReset dijitStretch dijitButtonNode\" dojoAttachPoint=\"buttonNode\" dojoAttachEvent=\"ondijitclick:_onButtonClick,onkeypress:_onButtonKeyPress\"\n\t\t><div id=\"${id}_button\" class=\"dijitReset dijitButtonContents\"\n\t\t\tdojoAttachPoint=\"titleNode\"\n\t\t\twaiRole=\"button\" waiState=\"labelledby-${id}_label\"\n\t\t\t><div class=\"dijitReset dijitInline dijitIcon\" dojoAttachPoint=\"iconNode\" waiRole=\"presentation\"></div\n\t\t\t><div class=\"dijitReset dijitInline dijitButtonText\" id=\"${id}_label\" dojoAttachPoint=\"containerNode\" waiRole=\"presentation\"></div\n\t\t></div\n\t\t></td\n\t\t><td id=\"${id}_arrow\" class='dijitReset dijitRight dijitButtonNode dijitArrowButton'\n\t\t\tdojoAttachPoint=\"_popupStateNode,focusNode,_buttonNode\"\n\t\t\tdojoAttachEvent=\"onkeypress:_onArrowKeyPress\"\n\t\t\ttitle=\"${optionsTitle}\"\n\t\t\twaiRole=\"button\" waiState=\"haspopup-true\"\n\t\t\t><div class=\"dijitReset dijitArrowButtonInner\" waiRole=\"presentation\"></div\n\t\t\t><div class=\"dijitReset dijitArrowButtonChar\" waiRole=\"presentation\">&#9660;</div\n\t\t></td\n\t\t><td style=\"display:none !important;\"\n\t\t\t><input ${!nameAttrSetting} type=\"${type}\" value=\"${value}\" dojoAttachPoint=\"valueNode\"\n\t\t/></td></tr></tbody\n></table>\n"),attributeMap:dojo.mixin(dojo.clone(dijit.form.Button.prototype.attributeMap),{id:"",tabIndex:["focusNode","titleNode"],title:"titleNode"}),optionsTitle:"",baseClass:"dijitComboButton",cssStateNodes:{"buttonNode":"dijitButtonNode","titleNode":"dijitButtonContents","_popupStateNode":"dijitDownArrowButton"},_focusedNode:null,_onButtonKeyPress:function(evt){
if(evt.charOrCode==dojo.keys[this.isLeftToRight()?"RIGHT_ARROW":"LEFT_ARROW"]){
dijit.focus(this._popupStateNode);
dojo.stopEvent(evt);
}
},_onArrowKeyPress:function(evt){
if(evt.charOrCode==dojo.keys[this.isLeftToRight()?"LEFT_ARROW":"RIGHT_ARROW"]){
dijit.focus(this.titleNode);
dojo.stopEvent(evt);
}
},focus:function(_430){
dijit.focus(_430=="start"?this.titleNode:this._popupStateNode);
}});
dojo.declare("dijit.form.ToggleButton",dijit.form.Button,{baseClass:"dijitToggleButton",checked:false,attributeMap:dojo.mixin(dojo.clone(dijit.form.Button.prototype.attributeMap),{checked:"focusNode"}),_clicked:function(evt){
this.set("checked",!this.checked);
},_setCheckedAttr:function(_431,_432){
this.checked=_431;
dojo.attr(this.focusNode||this.domNode,"checked",_431);
dijit.setWaiState(this.focusNode||this.domNode,"pressed",_431);
this._handleOnChange(_431,_432);
},setChecked:function(_433){
dojo.deprecated("setChecked("+_433+") is deprecated. Use set('checked',"+_433+") instead.","","2.0");
this.set("checked",_433);
},reset:function(){
this._hasBeenBlurred=false;
this.set("checked",this.params.checked||false);
}});
}
if(!dojo._hasResource["dijit.form.DropDownButton"]){
dojo._hasResource["dijit.form.DropDownButton"]=true;
dojo.provide("dijit.form.DropDownButton");
}
if(!dojo._hasResource["dijit.form.ToggleButton"]){
dojo._hasResource["dijit.form.ToggleButton"]=true;
dojo.provide("dijit.form.ToggleButton");
}
if(!dojo._hasResource["dijit._KeyNavContainer"]){
dojo._hasResource["dijit._KeyNavContainer"]=true;
dojo.provide("dijit._KeyNavContainer");
dojo.declare("dijit._KeyNavContainer",dijit._Container,{tabIndex:"0",_keyNavCodes:{},connectKeyNavHandlers:function(_434,_435){
var _436=(this._keyNavCodes={});
var prev=dojo.hitch(this,this.focusPrev);
var next=dojo.hitch(this,this.focusNext);
dojo.forEach(_434,function(code){
_436[code]=prev;
});
dojo.forEach(_435,function(code){
_436[code]=next;
});
this.connect(this.domNode,"onkeypress","_onContainerKeypress");
this.connect(this.domNode,"onfocus","_onContainerFocus");
},startupKeyNavChildren:function(){
dojo.forEach(this.getChildren(),dojo.hitch(this,"_startupChild"));
},addChild:function(_437,_438){
dijit._KeyNavContainer.superclass.addChild.apply(this,arguments);
this._startupChild(_437);
},focus:function(){
this.focusFirstChild();
},focusFirstChild:function(){
var _439=this._getFirstFocusableChild();
if(_439){
this.focusChild(_439);
}
},focusNext:function(){
var _43a=this._getNextFocusableChild(this.focusedChild,1);
this.focusChild(_43a);
},focusPrev:function(){
var _43b=this._getNextFocusableChild(this.focusedChild,-1);
this.focusChild(_43b,true);
},focusChild:function(_43c,last){
if(this.focusedChild&&_43c!==this.focusedChild){
this._onChildBlur(this.focusedChild);
}
_43c.focus(last?"end":"start");
this.focusedChild=_43c;
},_startupChild:function(_43d){
_43d.set("tabIndex","-1");
this.connect(_43d,"_onFocus",function(){
_43d.set("tabIndex",this.tabIndex);
});
this.connect(_43d,"_onBlur",function(){
_43d.set("tabIndex","-1");
});
},_onContainerFocus:function(evt){
if(evt.target!==this.domNode){
return;
}
this.focusFirstChild();
dojo.attr(this.domNode,"tabIndex","-1");
},_onBlur:function(evt){
if(this.tabIndex){
dojo.attr(this.domNode,"tabIndex",this.tabIndex);
}
this.inherited(arguments);
},_onContainerKeypress:function(evt){
if(evt.ctrlKey||evt.altKey){
return;
}
var func=this._keyNavCodes[evt.charOrCode];
if(func){
func();
dojo.stopEvent(evt);
}
},_onChildBlur:function(_43e){
},_getFirstFocusableChild:function(){
return this._getNextFocusableChild(null,1);
},_getNextFocusableChild:function(_43f,dir){
if(_43f){
_43f=this._getSiblingOfChild(_43f,dir);
}
var _440=this.getChildren();
for(var i=0;i<_440.length;i++){
if(!_43f){
_43f=_440[(dir>0)?0:(_440.length-1)];
}
if(_43f.isFocusable()){
return _43f;
}
_43f=this._getSiblingOfChild(_43f,dir);
}
return null;
}});
}
if(!dojo._hasResource["dijit._Contained"]){
dojo._hasResource["dijit._Contained"]=true;
dojo.provide("dijit._Contained");
dojo.declare("dijit._Contained",null,{getParent:function(){
var _441=dijit.getEnclosingWidget(this.domNode.parentNode);
return _441&&_441.isContainer?_441:null;
},_getSibling:function(_442){
var node=this.domNode;
do{
node=node[_442+"Sibling"];
}while(node&&node.nodeType!=1);
return node&&dijit.byNode(node);
},getPreviousSibling:function(){
return this._getSibling("previous");
},getNextSibling:function(){
return this._getSibling("next");
},getIndexInParent:function(){
var p=this.getParent();
if(!p||!p.getIndexOfChild){
return -1;
}
return p.getIndexOfChild(this);
}});
}
if(!dojo._hasResource["dijit.MenuItem"]){
dojo._hasResource["dijit.MenuItem"]=true;
dojo.provide("dijit.MenuItem");
dojo.declare("dijit.MenuItem",[dijit._Widget,dijit._Templated,dijit._Contained,dijit._CssStateMixin],{templateString:dojo.cache("dijit","templates/MenuItem.html","<tr class=\"dijitReset dijitMenuItem\" dojoAttachPoint=\"focusNode\" waiRole=\"menuitem\" tabIndex=\"-1\"\n\t\tdojoAttachEvent=\"onmouseenter:_onHover,onmouseleave:_onUnhover,ondijitclick:_onClick\">\n\t<td class=\"dijitReset dijitMenuItemIconCell\" waiRole=\"presentation\">\n\t\t<img src=\"${_blankGif}\" alt=\"\" class=\"dijitIcon dijitMenuItemIcon\" dojoAttachPoint=\"iconNode\"/>\n\t</td>\n\t<td class=\"dijitReset dijitMenuItemLabel\" colspan=\"2\" dojoAttachPoint=\"containerNode\"></td>\n\t<td class=\"dijitReset dijitMenuItemAccelKey\" style=\"display: none\" dojoAttachPoint=\"accelKeyNode\"></td>\n\t<td class=\"dijitReset dijitMenuArrowCell\" waiRole=\"presentation\">\n\t\t<div dojoAttachPoint=\"arrowWrapper\" style=\"visibility: hidden\">\n\t\t\t<img src=\"${_blankGif}\" alt=\"\" class=\"dijitMenuExpand\"/>\n\t\t\t<span class=\"dijitMenuExpandA11y\">+</span>\n\t\t</div>\n\t</td>\n</tr>\n"),attributeMap:dojo.delegate(dijit._Widget.prototype.attributeMap,{label:{node:"containerNode",type:"innerHTML"},iconClass:{node:"iconNode",type:"class"}}),baseClass:"dijitMenuItem",label:"",iconClass:"",accelKey:"",disabled:false,_fillContent:function(_443){
if(_443&&!("label" in this.params)){
this.set("label",_443.innerHTML);
}
},postCreate:function(){
this.inherited(arguments);
dojo.setSelectable(this.domNode,false);
var _444=this.id+"_text";
dojo.attr(this.containerNode,"id",_444);
if(this.accelKeyNode){
dojo.attr(this.accelKeyNode,"id",this.id+"_accel");
_444+=" "+this.id+"_accel";
}
dijit.setWaiState(this.domNode,"labelledby",_444);
},_onHover:function(){
this.getParent().onItemHover(this);
},_onUnhover:function(){
this.getParent().onItemUnhover(this);
this._hovering=false;
this._setStateClass();
},_onClick:function(evt){
this.getParent().onItemClick(this,evt);
dojo.stopEvent(evt);
},onClick:function(evt){
},focus:function(){
try{
if(dojo.isIE==8){
this.containerNode.focus();
}
dijit.focus(this.focusNode);
}
catch(e){
}
},_onFocus:function(){
this._setSelected(true);
this.getParent()._onItemFocus(this);
this.inherited(arguments);
},_setSelected:function(_445){
dojo.toggleClass(this.domNode,"dijitMenuItemSelected",_445);
},setLabel:function(_446){
dojo.deprecated("dijit.MenuItem.setLabel() is deprecated.  Use set('label', ...) instead.","","2.0");
this.set("label",_446);
},setDisabled:function(_447){
dojo.deprecated("dijit.Menu.setDisabled() is deprecated.  Use set('disabled', bool) instead.","","2.0");
this.set("disabled",_447);
},_setDisabledAttr:function(_448){
this.disabled=_448;
dijit.setWaiState(this.focusNode,"disabled",_448?"true":"false");
},_setAccelKeyAttr:function(_449){
this.accelKey=_449;
this.accelKeyNode.style.display=_449?"":"none";
this.accelKeyNode.innerHTML=_449;
dojo.attr(this.containerNode,"colSpan",_449?"1":"2");
}});
}
if(!dojo._hasResource["dijit.PopupMenuItem"]){
dojo._hasResource["dijit.PopupMenuItem"]=true;
dojo.provide("dijit.PopupMenuItem");
dojo.declare("dijit.PopupMenuItem",dijit.MenuItem,{_fillContent:function(){
if(this.srcNodeRef){
var _44a=dojo.query("*",this.srcNodeRef);
dijit.PopupMenuItem.superclass._fillContent.call(this,_44a[0]);
this.dropDownContainer=this.srcNodeRef;
}
},startup:function(){
if(this._started){
return;
}
this.inherited(arguments);
if(!this.popup){
var node=dojo.query("[widgetId]",this.dropDownContainer)[0];
this.popup=dijit.byNode(node);
}
dojo.body().appendChild(this.popup.domNode);
this.popup.startup();
this.popup.domNode.style.display="none";
if(this.arrowWrapper){
dojo.style(this.arrowWrapper,"visibility","");
}
dijit.setWaiState(this.focusNode,"haspopup","true");
},destroyDescendants:function(){
if(this.popup){
if(!this.popup._destroyed){
this.popup.destroyRecursive();
}
delete this.popup;
}
this.inherited(arguments);
}});
}
if(!dojo._hasResource["dijit.CheckedMenuItem"]){
dojo._hasResource["dijit.CheckedMenuItem"]=true;
dojo.provide("dijit.CheckedMenuItem");
dojo.declare("dijit.CheckedMenuItem",dijit.MenuItem,{templateString:dojo.cache("dijit","templates/CheckedMenuItem.html","<tr class=\"dijitReset dijitMenuItem\" dojoAttachPoint=\"focusNode\" waiRole=\"menuitemcheckbox\" tabIndex=\"-1\"\n\t\tdojoAttachEvent=\"onmouseenter:_onHover,onmouseleave:_onUnhover,ondijitclick:_onClick\">\n\t<td class=\"dijitReset dijitMenuItemIconCell\" waiRole=\"presentation\">\n\t\t<img src=\"${_blankGif}\" alt=\"\" class=\"dijitMenuItemIcon dijitCheckedMenuItemIcon\" dojoAttachPoint=\"iconNode\"/>\n\t\t<span class=\"dijitCheckedMenuItemIconChar\">&#10003;</span>\n\t</td>\n\t<td class=\"dijitReset dijitMenuItemLabel\" colspan=\"2\" dojoAttachPoint=\"containerNode,labelNode\"></td>\n\t<td class=\"dijitReset dijitMenuItemAccelKey\" style=\"display: none\" dojoAttachPoint=\"accelKeyNode\"></td>\n\t<td class=\"dijitReset dijitMenuArrowCell\" waiRole=\"presentation\">&nbsp;</td>\n</tr>\n"),checked:false,_setCheckedAttr:function(_44b){
dojo.toggleClass(this.domNode,"dijitCheckedMenuItemChecked",_44b);
dijit.setWaiState(this.domNode,"checked",_44b);
this.checked=_44b;
},onChange:function(_44c){
},_onClick:function(e){
if(!this.disabled){
this.set("checked",!this.checked);
this.onChange(this.checked);
}
this.inherited(arguments);
}});
}
if(!dojo._hasResource["dijit.MenuSeparator"]){
dojo._hasResource["dijit.MenuSeparator"]=true;
dojo.provide("dijit.MenuSeparator");
dojo.declare("dijit.MenuSeparator",[dijit._Widget,dijit._Templated,dijit._Contained],{templateString:dojo.cache("dijit","templates/MenuSeparator.html","<tr class=\"dijitMenuSeparator\">\n\t<td class=\"dijitMenuSeparatorIconCell\">\n\t\t<div class=\"dijitMenuSeparatorTop\"></div>\n\t\t<div class=\"dijitMenuSeparatorBottom\"></div>\n\t</td>\n\t<td colspan=\"3\" class=\"dijitMenuSeparatorLabelCell\">\n\t\t<div class=\"dijitMenuSeparatorTop dijitMenuSeparatorLabel\"></div>\n\t\t<div class=\"dijitMenuSeparatorBottom\"></div>\n\t</td>\n</tr>\n"),postCreate:function(){
dojo.setSelectable(this.domNode,false);
},isFocusable:function(){
return false;
}});
}
if(!dojo._hasResource["dijit.Menu"]){
dojo._hasResource["dijit.Menu"]=true;
dojo.provide("dijit.Menu");
dojo.declare("dijit._MenuBase",[dijit._Widget,dijit._Templated,dijit._KeyNavContainer],{parentMenu:null,popupDelay:500,startup:function(){
if(this._started){
return;
}
dojo.forEach(this.getChildren(),function(_44d){
_44d.startup();
});
this.startupKeyNavChildren();
this.inherited(arguments);
},onExecute:function(){
},onCancel:function(_44e){
},_moveToPopup:function(evt){
if(this.focusedChild&&this.focusedChild.popup&&!this.focusedChild.disabled){
this.focusedChild._onClick(evt);
}else{
var _44f=this._getTopMenu();
if(_44f&&_44f._isMenuBar){
_44f.focusNext();
}
}
},_onPopupHover:function(evt){
if(this.currentPopup&&this.currentPopup._pendingClose_timer){
var _450=this.currentPopup.parentMenu;
if(_450.focusedChild){
_450.focusedChild._setSelected(false);
}
_450.focusedChild=this.currentPopup.from_item;
_450.focusedChild._setSelected(true);
this._stopPendingCloseTimer(this.currentPopup);
}
},onItemHover:function(item){
if(this.isActive){
this.focusChild(item);
if(this.focusedChild.popup&&!this.focusedChild.disabled&&!this.hover_timer){
this.hover_timer=setTimeout(dojo.hitch(this,"_openPopup"),this.popupDelay);
}
}
if(this.focusedChild){
this.focusChild(item);
}
this._hoveredChild=item;
},_onChildBlur:function(item){
this._stopPopupTimer();
item._setSelected(false);
var _451=item.popup;
if(_451){
this._stopPendingCloseTimer(_451);
_451._pendingClose_timer=setTimeout(function(){
_451._pendingClose_timer=null;
if(_451.parentMenu){
_451.parentMenu.currentPopup=null;
}
dijit.popup.close(_451);
},this.popupDelay);
}
},onItemUnhover:function(item){
if(this.isActive){
this._stopPopupTimer();
}
if(this._hoveredChild==item){
this._hoveredChild=null;
}
},_stopPopupTimer:function(){
if(this.hover_timer){
clearTimeout(this.hover_timer);
this.hover_timer=null;
}
},_stopPendingCloseTimer:function(_452){
if(_452._pendingClose_timer){
clearTimeout(_452._pendingClose_timer);
_452._pendingClose_timer=null;
}
},_stopFocusTimer:function(){
if(this._focus_timer){
clearTimeout(this._focus_timer);
this._focus_timer=null;
}
},_getTopMenu:function(){
for(var top=this;top.parentMenu;top=top.parentMenu){
}
return top;
},onItemClick:function(item,evt){
if(typeof this.isShowingNow=="undefined"){
this._markActive();
}
this.focusChild(item);
if(item.disabled){
return false;
}
if(item.popup){
this._openPopup();
}else{
this.onExecute();
item.onClick(evt);
}
},_openPopup:function(){
this._stopPopupTimer();
var _453=this.focusedChild;
if(!_453){
return;
}
var _454=_453.popup;
if(_454.isShowingNow){
return;
}
if(this.currentPopup){
this._stopPendingCloseTimer(this.currentPopup);
dijit.popup.close(this.currentPopup);
}
_454.parentMenu=this;
_454.from_item=_453;
var self=this;
dijit.popup.open({parent:this,popup:_454,around:_453.domNode,orient:this._orient||(this.isLeftToRight()?{"TR":"TL","TL":"TR","BR":"BL","BL":"BR"}:{"TL":"TR","TR":"TL","BL":"BR","BR":"BL"}),onCancel:function(){
self.focusChild(_453);
self._cleanUp();
_453._setSelected(true);
self.focusedChild=_453;
},onExecute:dojo.hitch(this,"_cleanUp")});
this.currentPopup=_454;
_454.connect(_454.domNode,"onmouseenter",dojo.hitch(self,"_onPopupHover"));
if(_454.focus){
_454._focus_timer=setTimeout(dojo.hitch(_454,function(){
this._focus_timer=null;
this.focus();
}),0);
}
},_markActive:function(){
this.isActive=true;
dojo.addClass(this.domNode,"dijitMenuActive");
dojo.removeClass(this.domNode,"dijitMenuPassive");
},onOpen:function(e){
this.isShowingNow=true;
this._markActive();
},_markInactive:function(){
this.isActive=false;
dojo.removeClass(this.domNode,"dijitMenuActive");
dojo.addClass(this.domNode,"dijitMenuPassive");
},onClose:function(){
this._stopFocusTimer();
this._markInactive();
this.isShowingNow=false;
this.parentMenu=null;
},_closeChild:function(){
this._stopPopupTimer();
if(this.focusedChild){
this.focusedChild._setSelected(false);
this.focusedChild._onUnhover();
this.focusedChild=null;
}
if(this.currentPopup){
dijit.popup.close(this.currentPopup);
this.currentPopup=null;
}
},_onItemFocus:function(item){
if(this._hoveredChild&&this._hoveredChild!=item){
this._hoveredChild._onUnhover();
}
},_onBlur:function(){
this._cleanUp();
this.inherited(arguments);
},_cleanUp:function(){
this._closeChild();
if(typeof this.isShowingNow=="undefined"){
this._markInactive();
}
}});
dojo.declare("dijit.Menu",dijit._MenuBase,{constructor:function(){
this._bindings=[];
},templateString:dojo.cache("dijit","templates/Menu.html","<table class=\"dijit dijitMenu dijitMenuPassive dijitReset dijitMenuTable\" waiRole=\"menu\" tabIndex=\"${tabIndex}\" dojoAttachEvent=\"onkeypress:_onKeyPress\" cellspacing=0>\n\t<tbody class=\"dijitReset\" dojoAttachPoint=\"containerNode\"></tbody>\n</table>\n"),baseClass:"dijitMenu",targetNodeIds:[],contextMenuForWindow:false,leftClickToOpen:false,refocus:true,postCreate:function(){
if(this.contextMenuForWindow){
this.bindDomNode(dojo.body());
}else{
dojo.forEach(this.targetNodeIds,this.bindDomNode,this);
}
var k=dojo.keys,l=this.isLeftToRight();
this._openSubMenuKey=l?k.RIGHT_ARROW:k.LEFT_ARROW;
this._closeSubMenuKey=l?k.LEFT_ARROW:k.RIGHT_ARROW;
this.connectKeyNavHandlers([k.UP_ARROW],[k.DOWN_ARROW]);
},_onKeyPress:function(evt){
if(evt.ctrlKey||evt.altKey){
return;
}
switch(evt.charOrCode){
case this._openSubMenuKey:
this._moveToPopup(evt);
dojo.stopEvent(evt);
break;
case this._closeSubMenuKey:
if(this.parentMenu){
if(this.parentMenu._isMenuBar){
this.parentMenu.focusPrev();
}else{
this.onCancel(false);
}
}else{
dojo.stopEvent(evt);
}
break;
}
},_iframeContentWindow:function(_455){
var win=dojo.window.get(this._iframeContentDocument(_455))||this._iframeContentDocument(_455)["__parent__"]||(_455.name&&dojo.doc.frames[_455.name])||null;
return win;
},_iframeContentDocument:function(_456){
var doc=_456.contentDocument||(_456.contentWindow&&_456.contentWindow.document)||(_456.name&&dojo.doc.frames[_456.name]&&dojo.doc.frames[_456.name].document)||null;
return doc;
},bindDomNode:function(node){
node=dojo.byId(node);
var cn;
if(node.tagName.toLowerCase()=="iframe"){
var _457=node,win=this._iframeContentWindow(_457);
cn=dojo.withGlobal(win,dojo.body);
}else{
cn=(node==dojo.body()?dojo.doc.documentElement:node);
}
var _458={node:node,iframe:_457};
dojo.attr(node,"_dijitMenu"+this.id,this._bindings.push(_458));
var _459=dojo.hitch(this,function(cn){
return [dojo.connect(cn,this.leftClickToOpen?"onclick":"oncontextmenu",this,function(evt){
dojo.stopEvent(evt);
this._scheduleOpen(evt.target,_457,{x:evt.pageX,y:evt.pageY});
}),dojo.connect(cn,"onkeydown",this,function(evt){
if(evt.shiftKey&&evt.keyCode==dojo.keys.F10){
dojo.stopEvent(evt);
this._scheduleOpen(evt.target,_457);
}
})];
});
_458.connects=cn?_459(cn):[];
if(_457){
_458.onloadHandler=dojo.hitch(this,function(){
var win=this._iframeContentWindow(_457);
cn=dojo.withGlobal(win,dojo.body);
_458.connects=_459(cn);
});
if(_457.addEventListener){
_457.addEventListener("load",_458.onloadHandler,false);
}else{
_457.attachEvent("onload",_458.onloadHandler);
}
}
},unBindDomNode:function(_45a){
var node;
try{
node=dojo.byId(_45a);
}
catch(e){
return;
}
var _45b="_dijitMenu"+this.id;
if(node&&dojo.hasAttr(node,_45b)){
var bid=dojo.attr(node,_45b)-1,b=this._bindings[bid];
dojo.forEach(b.connects,dojo.disconnect);
var _45c=b.iframe;
if(_45c){
if(_45c.removeEventListener){
_45c.removeEventListener("load",b.onloadHandler,false);
}else{
_45c.detachEvent("onload",b.onloadHandler);
}
}
dojo.removeAttr(node,_45b);
delete this._bindings[bid];
}
},_scheduleOpen:function(_45d,_45e,_45f){
if(!this._openTimer){
this._openTimer=setTimeout(dojo.hitch(this,function(){
delete this._openTimer;
this._openMyself({target:_45d,iframe:_45e,coords:_45f});
}),1);
}
},_openMyself:function(args){
var _460=args.target,_461=args.iframe,_462=args.coords;
if(_462){
if(_461){
var od=_460.ownerDocument,ifc=dojo.position(_461,true),win=this._iframeContentWindow(_461),_463=dojo.withGlobal(win,"_docScroll",dojo);
var cs=dojo.getComputedStyle(_461),tp=dojo._toPixelValue,left=(dojo.isIE&&dojo.isQuirks?0:tp(_461,cs.paddingLeft))+(dojo.isIE&&dojo.isQuirks?tp(_461,cs.borderLeftWidth):0),top=(dojo.isIE&&dojo.isQuirks?0:tp(_461,cs.paddingTop))+(dojo.isIE&&dojo.isQuirks?tp(_461,cs.borderTopWidth):0);
_462.x+=ifc.x+left-_463.x;
_462.y+=ifc.y+top-_463.y;
}
}else{
_462=dojo.position(_460,true);
_462.x+=10;
_462.y+=10;
}
var self=this;
var _464=dijit.getFocus(this);
function _465(){
if(self.refocus){
dijit.focus(_464);
}
dijit.popup.close(self);
};
dijit.popup.open({popup:this,x:_462.x,y:_462.y,onExecute:_465,onCancel:_465,orient:this.isLeftToRight()?"L":"R"});
this.focus();
this._onBlur=function(){
this.inherited("_onBlur",arguments);
dijit.popup.close(this);
};
},uninitialize:function(){
dojo.forEach(this._bindings,function(b){
if(b){
this.unBindDomNode(b.node);
}
},this);
this.inherited(arguments);
}});
}
if(!dojo._hasResource["dojo.fx.Toggler"]){
dojo._hasResource["dojo.fx.Toggler"]=true;
dojo.provide("dojo.fx.Toggler");
dojo.declare("dojo.fx.Toggler",null,{node:null,showFunc:dojo.fadeIn,hideFunc:dojo.fadeOut,showDuration:200,hideDuration:200,constructor:function(args){
var _466=this;
dojo.mixin(_466,args);
_466.node=args.node;
_466._showArgs=dojo.mixin({},args);
_466._showArgs.node=_466.node;
_466._showArgs.duration=_466.showDuration;
_466.showAnim=_466.showFunc(_466._showArgs);
_466._hideArgs=dojo.mixin({},args);
_466._hideArgs.node=_466.node;
_466._hideArgs.duration=_466.hideDuration;
_466.hideAnim=_466.hideFunc(_466._hideArgs);
dojo.connect(_466.showAnim,"beforeBegin",dojo.hitch(_466.hideAnim,"stop",true));
dojo.connect(_466.hideAnim,"beforeBegin",dojo.hitch(_466.showAnim,"stop",true));
},show:function(_467){
return this.showAnim.play(_467||0);
},hide:function(_468){
return this.hideAnim.play(_468||0);
}});
}
if(!dojo._hasResource["dojo.fx"]){
dojo._hasResource["dojo.fx"]=true;
dojo.provide("dojo.fx");
(function(){
var d=dojo,_469={_fire:function(evt,args){
if(this[evt]){
this[evt].apply(this,args||[]);
}
return this;
}};
var _46a=function(_46b){
this._index=-1;
this._animations=_46b||[];
this._current=this._onAnimateCtx=this._onEndCtx=null;
this.duration=0;
d.forEach(this._animations,function(a){
this.duration+=a.duration;
if(a.delay){
this.duration+=a.delay;
}
},this);
};
d.extend(_46a,{_onAnimate:function(){
this._fire("onAnimate",arguments);
},_onEnd:function(){
d.disconnect(this._onAnimateCtx);
d.disconnect(this._onEndCtx);
this._onAnimateCtx=this._onEndCtx=null;
if(this._index+1==this._animations.length){
this._fire("onEnd");
}else{
this._current=this._animations[++this._index];
this._onAnimateCtx=d.connect(this._current,"onAnimate",this,"_onAnimate");
this._onEndCtx=d.connect(this._current,"onEnd",this,"_onEnd");
this._current.play(0,true);
}
},play:function(_46c,_46d){
if(!this._current){
this._current=this._animations[this._index=0];
}
if(!_46d&&this._current.status()=="playing"){
return this;
}
var _46e=d.connect(this._current,"beforeBegin",this,function(){
this._fire("beforeBegin");
}),_46f=d.connect(this._current,"onBegin",this,function(arg){
this._fire("onBegin",arguments);
}),_470=d.connect(this._current,"onPlay",this,function(arg){
this._fire("onPlay",arguments);
d.disconnect(_46e);
d.disconnect(_46f);
d.disconnect(_470);
});
if(this._onAnimateCtx){
d.disconnect(this._onAnimateCtx);
}
this._onAnimateCtx=d.connect(this._current,"onAnimate",this,"_onAnimate");
if(this._onEndCtx){
d.disconnect(this._onEndCtx);
}
this._onEndCtx=d.connect(this._current,"onEnd",this,"_onEnd");
this._current.play.apply(this._current,arguments);
return this;
},pause:function(){
if(this._current){
var e=d.connect(this._current,"onPause",this,function(arg){
this._fire("onPause",arguments);
d.disconnect(e);
});
this._current.pause();
}
return this;
},gotoPercent:function(_471,_472){
this.pause();
var _473=this.duration*_471;
this._current=null;
d.some(this._animations,function(a){
if(a.duration<=_473){
this._current=a;
return true;
}
_473-=a.duration;
return false;
});
if(this._current){
this._current.gotoPercent(_473/this._current.duration,_472);
}
return this;
},stop:function(_474){
if(this._current){
if(_474){
for(;this._index+1<this._animations.length;++this._index){
this._animations[this._index].stop(true);
}
this._current=this._animations[this._index];
}
var e=d.connect(this._current,"onStop",this,function(arg){
this._fire("onStop",arguments);
d.disconnect(e);
});
this._current.stop();
}
return this;
},status:function(){
return this._current?this._current.status():"stopped";
},destroy:function(){
if(this._onAnimateCtx){
d.disconnect(this._onAnimateCtx);
}
if(this._onEndCtx){
d.disconnect(this._onEndCtx);
}
}});
d.extend(_46a,_469);
dojo.fx.chain=function(_475){
return new _46a(_475);
};
var _476=function(_477){
this._animations=_477||[];
this._connects=[];
this._finished=0;
this.duration=0;
d.forEach(_477,function(a){
var _478=a.duration;
if(a.delay){
_478+=a.delay;
}
if(this.duration<_478){
this.duration=_478;
}
this._connects.push(d.connect(a,"onEnd",this,"_onEnd"));
},this);
this._pseudoAnimation=new d.Animation({curve:[0,1],duration:this.duration});
var self=this;
d.forEach(["beforeBegin","onBegin","onPlay","onAnimate","onPause","onStop","onEnd"],function(evt){
self._connects.push(d.connect(self._pseudoAnimation,evt,function(){
self._fire(evt,arguments);
}));
});
};
d.extend(_476,{_doAction:function(_479,args){
d.forEach(this._animations,function(a){
a[_479].apply(a,args);
});
return this;
},_onEnd:function(){
if(++this._finished>this._animations.length){
this._fire("onEnd");
}
},_call:function(_47a,args){
var t=this._pseudoAnimation;
t[_47a].apply(t,args);
},play:function(_47b,_47c){
this._finished=0;
this._doAction("play",arguments);
this._call("play",arguments);
return this;
},pause:function(){
this._doAction("pause",arguments);
this._call("pause",arguments);
return this;
},gotoPercent:function(_47d,_47e){
var ms=this.duration*_47d;
d.forEach(this._animations,function(a){
a.gotoPercent(a.duration<ms?1:(ms/a.duration),_47e);
});
this._call("gotoPercent",arguments);
return this;
},stop:function(_47f){
this._doAction("stop",arguments);
this._call("stop",arguments);
return this;
},status:function(){
return this._pseudoAnimation.status();
},destroy:function(){
d.forEach(this._connects,dojo.disconnect);
}});
d.extend(_476,_469);
dojo.fx.combine=function(_480){
return new _476(_480);
};
dojo.fx.wipeIn=function(args){
var node=args.node=d.byId(args.node),s=node.style,o;
var anim=d.animateProperty(d.mixin({properties:{height:{start:function(){
o=s.overflow;
s.overflow="hidden";
if(s.visibility=="hidden"||s.display=="none"){
s.height="1px";
s.display="";
s.visibility="";
return 1;
}else{
var _481=d.style(node,"height");
return Math.max(_481,1);
}
},end:function(){
return node.scrollHeight;
}}}},args));
d.connect(anim,"onEnd",function(){
s.height="auto";
s.overflow=o;
});
return anim;
};
dojo.fx.wipeOut=function(args){
var node=args.node=d.byId(args.node),s=node.style,o;
var anim=d.animateProperty(d.mixin({properties:{height:{end:1}}},args));
d.connect(anim,"beforeBegin",function(){
o=s.overflow;
s.overflow="hidden";
s.display="";
});
d.connect(anim,"onEnd",function(){
s.overflow=o;
s.height="auto";
s.display="none";
});
return anim;
};
dojo.fx.slideTo=function(args){
var node=args.node=d.byId(args.node),top=null,left=null;
var init=(function(n){
return function(){
var cs=d.getComputedStyle(n);
var pos=cs.position;
top=(pos=="absolute"?n.offsetTop:parseInt(cs.top)||0);
left=(pos=="absolute"?n.offsetLeft:parseInt(cs.left)||0);
if(pos!="absolute"&&pos!="relative"){
var ret=d.position(n,true);
top=ret.y;
left=ret.x;
n.style.position="absolute";
n.style.top=top+"px";
n.style.left=left+"px";
}
};
})(node);
init();
var anim=d.animateProperty(d.mixin({properties:{top:args.top||0,left:args.left||0}},args));
d.connect(anim,"beforeBegin",anim,init);
return anim;
};
})();
}
if(!dojo._hasResource["dijit.layout._LayoutWidget"]){
dojo._hasResource["dijit.layout._LayoutWidget"]=true;
dojo.provide("dijit.layout._LayoutWidget");
dojo.declare("dijit.layout._LayoutWidget",[dijit._Widget,dijit._Container,dijit._Contained],{baseClass:"dijitLayoutContainer",isLayoutContainer:true,postCreate:function(){
dojo.addClass(this.domNode,"dijitContainer");
this.inherited(arguments);
},startup:function(){
if(this._started){
return;
}
this.inherited(arguments);
var _482=this.getParent&&this.getParent();
if(!(_482&&_482.isLayoutContainer)){
this.resize();
this.connect(dojo.isIE?this.domNode:dojo.global,"onresize",function(){
this.resize();
});
}
},resize:function(_483,_484){
var node=this.domNode;
if(_483){
dojo.marginBox(node,_483);
if(_483.t){
node.style.top=_483.t+"px";
}
if(_483.l){
node.style.left=_483.l+"px";
}
}
var mb=_484||{};
dojo.mixin(mb,_483||{});
if(!("h" in mb)||!("w" in mb)){
mb=dojo.mixin(dojo.marginBox(node),mb);
}
var cs=dojo.getComputedStyle(node);
var me=dojo._getMarginExtents(node,cs);
var be=dojo._getBorderExtents(node,cs);
var bb=(this._borderBox={w:mb.w-(me.w+be.w),h:mb.h-(me.h+be.h)});
var pe=dojo._getPadExtents(node,cs);
this._contentBox={l:dojo._toPixelValue(node,cs.paddingLeft),t:dojo._toPixelValue(node,cs.paddingTop),w:bb.w-pe.w,h:bb.h-pe.h};
this.layout();
},layout:function(){
},_setupChild:function(_485){
dojo.addClass(_485.domNode,this.baseClass+"-child");
if(_485.baseClass){
dojo.addClass(_485.domNode,this.baseClass+"-"+_485.baseClass);
}
},addChild:function(_486,_487){
this.inherited(arguments);
if(this._started){
this._setupChild(_486);
}
},removeChild:function(_488){
dojo.removeClass(_488.domNode,this.baseClass+"-child");
if(_488.baseClass){
dojo.removeClass(_488.domNode,this.baseClass+"-"+_488.baseClass);
}
this.inherited(arguments);
}});
dijit.layout.marginBox2contentBox=function(node,mb){
var cs=dojo.getComputedStyle(node);
var me=dojo._getMarginExtents(node,cs);
var pb=dojo._getPadBorderExtents(node,cs);
return {l:dojo._toPixelValue(node,cs.paddingLeft),t:dojo._toPixelValue(node,cs.paddingTop),w:mb.w-(me.w+pb.w),h:mb.h-(me.h+pb.h)};
};
(function(){
var _489=function(word){
return word.substring(0,1).toUpperCase()+word.substring(1);
};
var size=function(_48a,dim){
_48a.resize?_48a.resize(dim):dojo.marginBox(_48a.domNode,dim);
dojo.mixin(_48a,dojo.marginBox(_48a.domNode));
dojo.mixin(_48a,dim);
};
dijit.layout.layoutChildren=function(_48b,dim,_48c){
dim=dojo.mixin({},dim);
dojo.addClass(_48b,"dijitLayoutContainer");
_48c=dojo.filter(_48c,function(item){
return item.layoutAlign!="client";
}).concat(dojo.filter(_48c,function(item){
return item.layoutAlign=="client";
}));
dojo.forEach(_48c,function(_48d){
var elm=_48d.domNode,pos=_48d.layoutAlign;
var _48e=elm.style;
_48e.left=dim.l+"px";
_48e.top=dim.t+"px";
_48e.bottom=_48e.right="auto";
dojo.addClass(elm,"dijitAlign"+_489(pos));
if(pos=="top"||pos=="bottom"){
size(_48d,{w:dim.w});
dim.h-=_48d.h;
if(pos=="top"){
dim.t+=_48d.h;
}else{
_48e.top=dim.t+dim.h+"px";
}
}else{
if(pos=="left"||pos=="right"){
size(_48d,{h:dim.h});
dim.w-=_48d.w;
if(pos=="left"){
dim.l+=_48d.w;
}else{
_48e.left=dim.l+dim.w+"px";
}
}else{
if(pos=="client"){
size(_48d,dim);
}
}
}
});
};
})();
}
if(!dojo._hasResource["dojo.html"]){
dojo._hasResource["dojo.html"]=true;
dojo.provide("dojo.html");
(function(){
var _48f=0,d=dojo;
dojo.html._secureForInnerHtml=function(cont){
return cont.replace(/(?:\s*<!DOCTYPE\s[^>]+>|<title[^>]*>[\s\S]*?<\/title>)/ig,"");
};
dojo.html._emptyNode=dojo.empty;
dojo.html._setNodeContent=function(node,cont){
d.empty(node);
if(cont){
if(typeof cont=="string"){
cont=d._toDom(cont,node.ownerDocument);
}
if(!cont.nodeType&&d.isArrayLike(cont)){
for(var _490=cont.length,i=0;i<cont.length;i=_490==cont.length?i+1:0){
d.place(cont[i],node,"last");
}
}else{
d.place(cont,node,"last");
}
}
return node;
};
dojo.declare("dojo.html._ContentSetter",null,{node:"",content:"",id:"",cleanContent:false,extractContent:false,parseContent:false,constructor:function(_491,node){
dojo.mixin(this,_491||{});
node=this.node=dojo.byId(this.node||node);
if(!this.id){
this.id=["Setter",(node)?node.id||node.tagName:"",_48f++].join("_");
}
},set:function(cont,_492){
if(undefined!==cont){
this.content=cont;
}
if(_492){
this._mixin(_492);
}
this.onBegin();
this.setContent();
this.onEnd();
return this.node;
},setContent:function(){
var node=this.node;
if(!node){
throw new Error(this.declaredClass+": setContent given no node");
}
try{
node=dojo.html._setNodeContent(node,this.content);
}
catch(e){
var _493=this.onContentError(e);
try{
node.innerHTML=_493;
}
catch(e){
console.error("Fatal "+this.declaredClass+".setContent could not change content due to "+e.message,e);
}
}
this.node=node;
},empty:function(){
if(this.parseResults&&this.parseResults.length){
dojo.forEach(this.parseResults,function(w){
if(w.destroy){
w.destroy();
}
});
delete this.parseResults;
}
dojo.html._emptyNode(this.node);
},onBegin:function(){
var cont=this.content;
if(dojo.isString(cont)){
if(this.cleanContent){
cont=dojo.html._secureForInnerHtml(cont);
}
if(this.extractContent){
var _494=cont.match(/<body[^>]*>\s*([\s\S]+)\s*<\/body>/im);
if(_494){
cont=_494[1];
}
}
}
this.empty();
this.content=cont;
return this.node;
},onEnd:function(){
if(this.parseContent){
this._parse();
}
return this.node;
},tearDown:function(){
delete this.parseResults;
delete this.node;
delete this.content;
},onContentError:function(err){
return "Error occured setting content: "+err;
},_mixin:function(_495){
var _496={},key;
for(key in _495){
if(key in _496){
continue;
}
this[key]=_495[key];
}
},_parse:function(){
var _497=this.node;
try{
this.parseResults=dojo.parser.parse({rootNode:_497,dir:this.dir,lang:this.lang});
}
catch(e){
this._onError("Content",e,"Error parsing in _ContentSetter#"+this.id);
}
},_onError:function(type,err,_498){
var _499=this["on"+type+"Error"].call(this,err);
if(_498){
console.error(_498,err);
}else{
if(_499){
dojo.html._setNodeContent(this.node,_499,true);
}
}
}});
dojo.html.set=function(node,cont,_49a){
if(undefined==cont){
console.warn("dojo.html.set: no cont argument provided, using empty string");
cont="";
}
if(!_49a){
return dojo.html._setNodeContent(node,cont,true);
}else{
var op=new dojo.html._ContentSetter(dojo.mixin(_49a,{content:cont,node:node}));
return op.set();
}
};
})();
}
if(!dojo._hasResource["dojo.i18n"]){
dojo._hasResource["dojo.i18n"]=true;
dojo.provide("dojo.i18n");
dojo.i18n.getLocalization=function(_49b,_49c,_49d){
_49d=dojo.i18n.normalizeLocale(_49d);
var _49e=_49d.split("-");
var _49f=[_49b,"nls",_49c].join(".");
var _4a0=dojo._loadedModules[_49f];
if(_4a0){
var _4a1;
for(var i=_49e.length;i>0;i--){
var loc=_49e.slice(0,i).join("_");
if(_4a0[loc]){
_4a1=_4a0[loc];
break;
}
}
if(!_4a1){
_4a1=_4a0.ROOT;
}
if(_4a1){
var _4a2=function(){
};
_4a2.prototype=_4a1;
return new _4a2();
}
}
throw new Error("Bundle not found: "+_49c+" in "+_49b+" , locale="+_49d);
};
dojo.i18n.normalizeLocale=function(_4a3){
var _4a4=_4a3?_4a3.toLowerCase():dojo.locale;
if(_4a4=="root"){
_4a4="ROOT";
}
return _4a4;
};
dojo.i18n._requireLocalization=function(_4a5,_4a6,_4a7,_4a8){
var _4a9=dojo.i18n.normalizeLocale(_4a7);
var _4aa=[_4a5,"nls",_4a6].join(".");
var _4ab="";
if(_4a8){
var _4ac=_4a8.split(",");
for(var i=0;i<_4ac.length;i++){
if(_4a9["indexOf"](_4ac[i])==0){
if(_4ac[i].length>_4ab.length){
_4ab=_4ac[i];
}
}
}
if(!_4ab){
_4ab="ROOT";
}
}
var _4ad=_4a8?_4ab:_4a9;
var _4ae=dojo._loadedModules[_4aa];
var _4af=null;
if(_4ae){
if(dojo.config.localizationComplete&&_4ae._built){
return;
}
var _4b0=_4ad.replace(/-/g,"_");
var _4b1=_4aa+"."+_4b0;
_4af=dojo._loadedModules[_4b1];
}
if(!_4af){
_4ae=dojo["provide"](_4aa);
var syms=dojo._getModuleSymbols(_4a5);
var _4b2=syms.concat("nls").join("/");
var _4b3;
dojo.i18n._searchLocalePath(_4ad,_4a8,function(loc){
var _4b4=loc.replace(/-/g,"_");
var _4b5=_4aa+"."+_4b4;
var _4b6=false;
if(!dojo._loadedModules[_4b5]){
dojo["provide"](_4b5);
var _4b7=[_4b2];
if(loc!="ROOT"){
_4b7.push(loc);
}
_4b7.push(_4a6);
var _4b8=_4b7.join("/")+".js";
_4b6=dojo._loadPath(_4b8,null,function(hash){
var _4b9=function(){
};
_4b9.prototype=_4b3;
_4ae[_4b4]=new _4b9();
for(var j in hash){
_4ae[_4b4][j]=hash[j];
}
});
}else{
_4b6=true;
}
if(_4b6&&_4ae[_4b4]){
_4b3=_4ae[_4b4];
}else{
_4ae[_4b4]=_4b3;
}
if(_4a8){
return true;
}
});
}
if(_4a8&&_4a9!=_4ab){
_4ae[_4a9.replace(/-/g,"_")]=_4ae[_4ab.replace(/-/g,"_")];
}
};
(function(){
var _4ba=dojo.config.extraLocale;
if(_4ba){
if(!_4ba instanceof Array){
_4ba=[_4ba];
}
var req=dojo.i18n._requireLocalization;
dojo.i18n._requireLocalization=function(m,b,_4bb,_4bc){
req(m,b,_4bb,_4bc);
if(_4bb){
return;
}
for(var i=0;i<_4ba.length;i++){
req(m,b,_4ba[i],_4bc);
}
};
}
})();
dojo.i18n._searchLocalePath=function(_4bd,down,_4be){
_4bd=dojo.i18n.normalizeLocale(_4bd);
var _4bf=_4bd.split("-");
var _4c0=[];
for(var i=_4bf.length;i>0;i--){
_4c0.push(_4bf.slice(0,i).join("-"));
}
_4c0.push(false);
if(down){
_4c0.reverse();
}
for(var j=_4c0.length-1;j>=0;j--){
var loc=_4c0[j]||"ROOT";
var stop=_4be(loc);
if(stop){
break;
}
}
};
dojo.i18n._preloadLocalizations=function(_4c1,_4c2){
function _4c3(_4c4){
_4c4=dojo.i18n.normalizeLocale(_4c4);
dojo.i18n._searchLocalePath(_4c4,true,function(loc){
for(var i=0;i<_4c2.length;i++){
if(_4c2[i]==loc){
dojo["require"](_4c1+"_"+loc);
return true;
}
}
return false;
});
};
_4c3();
var _4c5=dojo.config.extraLocale||[];
for(var i=0;i<_4c5.length;i++){
_4c3(_4c5[i]);
}
};
}
if(!dojo._hasResource["dijit.layout.ContentPane"]){
dojo._hasResource["dijit.layout.ContentPane"]=true;
dojo.provide("dijit.layout.ContentPane");
dojo.declare("dijit.layout.ContentPane",dijit._Widget,{href:"",extractContent:false,parseOnLoad:true,preventCache:false,preload:false,refreshOnShow:false,loadingMessage:"<span class='dijitContentPaneLoading'>${loadingState}</span>",errorMessage:"<span class='dijitContentPaneError'>${errorState}</span>",isLoaded:false,baseClass:"dijitContentPane",doLayout:true,ioArgs:{},isContainer:true,isLayoutContainer:true,onLoadDeferred:null,attributeMap:dojo.delegate(dijit._Widget.prototype.attributeMap,{title:[]}),postMixInProperties:function(){
this.inherited(arguments);
var _4c6=dojo.i18n.getLocalization("dijit","loading",this.lang);
this.loadingMessage=dojo.string.substitute(this.loadingMessage,_4c6);
this.errorMessage=dojo.string.substitute(this.errorMessage,_4c6);
if(!this.href&&this.srcNodeRef&&this.srcNodeRef.innerHTML){
this.isLoaded=true;
}
},buildRendering:function(){
this.inherited(arguments);
if(!this.containerNode){
this.containerNode=this.domNode;
}
},postCreate:function(){
this.domNode.title="";
if(!dojo.attr(this.domNode,"role")){
dijit.setWaiRole(this.domNode,"group");
}
dojo.addClass(this.domNode,this.baseClass);
},startup:function(){
if(this._started){
return;
}
var _4c7=dijit._Contained.prototype.getParent.call(this);
this._childOfLayoutWidget=_4c7&&_4c7.isLayoutContainer;
this._needLayout=!this._childOfLayoutWidget;
if(this.isLoaded){
dojo.forEach(this.getChildren(),function(_4c8){
_4c8.startup();
});
}
if(this._isShown()||this.preload){
this._onShow();
}
this.inherited(arguments);
},_checkIfSingleChild:function(){
var _4c9=dojo.query("> *",this.containerNode).filter(function(node){
return node.tagName!=="SCRIPT";
}),_4ca=_4c9.filter(function(node){
return dojo.hasAttr(node,"dojoType")||dojo.hasAttr(node,"widgetId");
}),_4cb=dojo.filter(_4ca.map(dijit.byNode),function(_4cc){
return _4cc&&_4cc.domNode&&_4cc.resize;
});
if(_4c9.length==_4ca.length&&_4cb.length==1){
this._singleChild=_4cb[0];
}else{
delete this._singleChild;
}
dojo.toggleClass(this.containerNode,this.baseClass+"SingleChild",!!this._singleChild);
},setHref:function(href){
dojo.deprecated("dijit.layout.ContentPane.setHref() is deprecated. Use set('href', ...) instead.","","2.0");
return this.set("href",href);
},_setHrefAttr:function(href){
this.cancel();
this.onLoadDeferred=new dojo.Deferred(dojo.hitch(this,"cancel"));
this.href=href;
if(this._created&&(this.preload||this._isShown())){
this._load();
}else{
this._hrefChanged=true;
}
return this.onLoadDeferred;
},setContent:function(data){
dojo.deprecated("dijit.layout.ContentPane.setContent() is deprecated.  Use set('content', ...) instead.","","2.0");
this.set("content",data);
},_setContentAttr:function(data){
this.href="";
this.cancel();
this.onLoadDeferred=new dojo.Deferred(dojo.hitch(this,"cancel"));
this._setContent(data||"");
this._isDownloaded=false;
return this.onLoadDeferred;
},_getContentAttr:function(){
return this.containerNode.innerHTML;
},cancel:function(){
if(this._xhrDfd&&(this._xhrDfd.fired==-1)){
this._xhrDfd.cancel();
}
delete this._xhrDfd;
this.onLoadDeferred=null;
},uninitialize:function(){
if(this._beingDestroyed){
this.cancel();
}
this.inherited(arguments);
},destroyRecursive:function(_4cd){
if(this._beingDestroyed){
return;
}
this.inherited(arguments);
},resize:function(_4ce,_4cf){
if(!this._wasShown){
this._onShow();
}
this._resizeCalled=true;
if(_4ce){
dojo.marginBox(this.domNode,_4ce);
}
var cn=this.containerNode;
if(cn===this.domNode){
var mb=_4cf||{};
dojo.mixin(mb,_4ce||{});
if(!("h" in mb)||!("w" in mb)){
mb=dojo.mixin(dojo.marginBox(cn),mb);
}
this._contentBox=dijit.layout.marginBox2contentBox(cn,mb);
}else{
this._contentBox=dojo.contentBox(cn);
}
this._layoutChildren();
},_isShown:function(){
if(this._childOfLayoutWidget){
if(this._resizeCalled&&"open" in this){
return this.open;
}
return this._resizeCalled;
}else{
if("open" in this){
return this.open;
}else{
var node=this.domNode;
return (node.style.display!="none")&&(node.style.visibility!="hidden")&&!dojo.hasClass(node,"dijitHidden");
}
}
},_onShow:function(){
if(this.href){
if(!this._xhrDfd&&(!this.isLoaded||this._hrefChanged||this.refreshOnShow)){
this.refresh();
}
}else{
if(!this._childOfLayoutWidget&&this._needLayout){
this._layoutChildren();
}
}
this.inherited(arguments);
this._wasShown=true;
},refresh:function(){
this.cancel();
this.onLoadDeferred=new dojo.Deferred(dojo.hitch(this,"cancel"));
this._load();
return this.onLoadDeferred;
},_load:function(){
this._setContent(this.onDownloadStart(),true);
var self=this;
var _4d0={preventCache:(this.preventCache||this.refreshOnShow),url:this.href,handleAs:"text"};
if(dojo.isObject(this.ioArgs)){
dojo.mixin(_4d0,this.ioArgs);
}
var hand=(this._xhrDfd=(this.ioMethod||dojo.xhrGet)(_4d0));
hand.addCallback(function(html){
try{
self._isDownloaded=true;
self._setContent(html,false);
self.onDownloadEnd();
}
catch(err){
self._onError("Content",err);
}
delete self._xhrDfd;
return html;
});
hand.addErrback(function(err){
if(!hand.canceled){
self._onError("Download",err);
}
delete self._xhrDfd;
return err;
});
delete this._hrefChanged;
},_onLoadHandler:function(data){
this.isLoaded=true;
try{
this.onLoadDeferred.callback(data);
this.onLoad(data);
}
catch(e){
console.error("Error "+this.widgetId+" running custom onLoad code: "+e.message);
}
},_onUnloadHandler:function(){
this.isLoaded=false;
try{
this.onUnload();
}
catch(e){
console.error("Error "+this.widgetId+" running custom onUnload code: "+e.message);
}
},destroyDescendants:function(){
if(this.isLoaded){
this._onUnloadHandler();
}
var _4d1=this._contentSetter;
dojo.forEach(this.getChildren(),function(_4d2){
if(_4d2.destroyRecursive){
_4d2.destroyRecursive();
}
});
if(_4d1){
dojo.forEach(_4d1.parseResults,function(_4d3){
if(_4d3.destroyRecursive&&_4d3.domNode&&_4d3.domNode.parentNode==dojo.body()){
_4d3.destroyRecursive();
}
});
delete _4d1.parseResults;
}
dojo.html._emptyNode(this.containerNode);
delete this._singleChild;
},_setContent:function(cont,_4d4){
this.destroyDescendants();
var _4d5=this._contentSetter;
if(!(_4d5&&_4d5 instanceof dojo.html._ContentSetter)){
_4d5=this._contentSetter=new dojo.html._ContentSetter({node:this.containerNode,_onError:dojo.hitch(this,this._onError),onContentError:dojo.hitch(this,function(e){
var _4d6=this.onContentError(e);
try{
this.containerNode.innerHTML=_4d6;
}
catch(e){
console.error("Fatal "+this.id+" could not change content due to "+e.message,e);
}
})});
}
var _4d7=dojo.mixin({cleanContent:this.cleanContent,extractContent:this.extractContent,parseContent:this.parseOnLoad,dir:this.dir,lang:this.lang},this._contentSetterParams||{});
dojo.mixin(_4d5,_4d7);
_4d5.set((dojo.isObject(cont)&&cont.domNode)?cont.domNode:cont);
delete this._contentSetterParams;
if(!_4d4){
dojo.forEach(this.getChildren(),function(_4d8){
if(!this.parseOnLoad||_4d8.getParent){
_4d8.startup();
}
},this);
this._scheduleLayout();
this._onLoadHandler(cont);
}
},_onError:function(type,err,_4d9){
this.onLoadDeferred.errback(err);
var _4da=this["on"+type+"Error"].call(this,err);
if(_4d9){
console.error(_4d9,err);
}else{
if(_4da){
this._setContent(_4da,true);
}
}
},_scheduleLayout:function(){
if(this._isShown()){
this._layoutChildren();
}else{
this._needLayout=true;
}
},_layoutChildren:function(){
if(this.doLayout){
this._checkIfSingleChild();
}
if(this._singleChild&&this._singleChild.resize){
var cb=this._contentBox||dojo.contentBox(this.containerNode);
this._singleChild.resize({w:cb.w,h:cb.h});
}else{
dojo.forEach(this.getChildren(),function(_4db){
if(_4db.resize){
_4db.resize();
}
});
}
delete this._needLayout;
},onLoad:function(data){
},onUnload:function(){
},onDownloadStart:function(){
return this.loadingMessage;
},onContentError:function(_4dc){
},onDownloadError:function(_4dd){
return this.errorMessage;
},onDownloadEnd:function(){
}});
}
if(!dojo._hasResource["dijit.TitlePane"]){
dojo._hasResource["dijit.TitlePane"]=true;
dojo.provide("dijit.TitlePane");
dojo.declare("dijit.TitlePane",[dijit.layout.ContentPane,dijit._Templated,dijit._CssStateMixin],{title:"",open:true,toggleable:true,tabIndex:"0",duration:dijit.defaultDuration,baseClass:"dijitTitlePane",templateString:dojo.cache("dijit","templates/TitlePane.html","<div>\n\t<div dojoAttachEvent=\"onclick:_onTitleClick, onkeypress:_onTitleKey\"\n\t\t\tclass=\"dijitTitlePaneTitle\" dojoAttachPoint=\"titleBarNode\">\n\t\t<div class=\"dijitTitlePaneTitleFocus\" dojoAttachPoint=\"focusNode\">\n\t\t\t<img src=\"${_blankGif}\" alt=\"\" dojoAttachPoint=\"arrowNode\" class=\"dijitArrowNode\" waiRole=\"presentation\"\n\t\t\t/><span dojoAttachPoint=\"arrowNodeInner\" class=\"dijitArrowNodeInner\"></span\n\t\t\t><span dojoAttachPoint=\"titleNode\" class=\"dijitTitlePaneTextNode\"></span>\n\t\t</div>\n\t</div>\n\t<div class=\"dijitTitlePaneContentOuter\" dojoAttachPoint=\"hideNode\" waiRole=\"presentation\">\n\t\t<div class=\"dijitReset\" dojoAttachPoint=\"wipeNode\" waiRole=\"presentation\">\n\t\t\t<div class=\"dijitTitlePaneContentInner\" dojoAttachPoint=\"containerNode\" waiRole=\"region\" tabindex=\"-1\" id=\"${id}_pane\">\n\t\t\t\t<!-- nested divs because wipeIn()/wipeOut() doesn't work right on node w/padding etc.  Put padding on inner div. -->\n\t\t\t</div>\n\t\t</div>\n\t</div>\n</div>\n"),attributeMap:dojo.delegate(dijit.layout.ContentPane.prototype.attributeMap,{title:{node:"titleNode",type:"innerHTML"},tooltip:{node:"focusNode",type:"attribute",attribute:"title"},id:""}),postCreate:function(){
if(!this.open){
this.hideNode.style.display=this.wipeNode.style.display="none";
}
if(this.toggleable){
this._trackMouseState(this.titleBarNode,"dijitTitlePaneTitle");
}
this._setCss();
dojo.setSelectable(this.titleNode,false);
var _4de=this.hideNode,_4df=this.wipeNode;
this._wipeIn=dojo.fx.wipeIn({node:this.wipeNode,duration:this.duration,beforeBegin:function(){
_4de.style.display="";
}});
this._wipeOut=dojo.fx.wipeOut({node:this.wipeNode,duration:this.duration,onEnd:function(){
_4de.style.display="none";
}});
this.inherited(arguments);
},_setOpenAttr:function(open){
if(this.open!==open){
this.toggle();
}
dijit.setWaiState(this.containerNode,"hidden",this.open?"false":"true");
dijit.setWaiState(this.focusNode,"pressed",this.open?"true":"false");
},_setToggleableAttr:function(_4e0){
this.toggleable=_4e0;
dijit.setWaiRole(this.focusNode,_4e0?"button":"heading");
if(_4e0){
dijit.setWaiState(this.focusNode,"controls",this.id+"_pane");
dojo.attr(this.focusNode,"tabIndex",this.tabIndex);
}else{
dojo.removeAttr(this.focusNode,"tabIndex");
}
this._setCss();
},_setContentAttr:function(_4e1){
if(!this.open||!this._wipeOut||this._wipeOut.status()=="playing"){
this.inherited(arguments);
}else{
if(this._wipeIn&&this._wipeIn.status()=="playing"){
this._wipeIn.stop();
}
dojo.marginBox(this.wipeNode,{h:dojo.marginBox(this.wipeNode).h});
this.inherited(arguments);
if(this._wipeIn){
this._wipeIn.play();
}else{
this.hideNode.style.display="";
}
}
},toggle:function(){
dojo.forEach([this._wipeIn,this._wipeOut],function(_4e2){
if(_4e2&&_4e2.status()=="playing"){
_4e2.stop();
}
});
var anim=this[this.open?"_wipeOut":"_wipeIn"];
if(anim){
anim.play();
}else{
this.hideNode.style.display=this.open?"":"none";
}
this.open=!this.open;
if(this.open){
this._onShow();
}else{
this.onHide();
}
this._setCss();
},_setCss:function(){
var node=this.titleBarNode||this.focusNode;
if(this._titleBarClass){
dojo.removeClass(node,this._titleBarClass);
}
this._titleBarClass="dijit"+(this.toggleable?"":"Fixed")+(this.open?"Open":"Closed");
dojo.addClass(node,this._titleBarClass);
this.arrowNodeInner.innerHTML=this.open?"-":"+";
},_onTitleKey:function(e){
if(e.charOrCode==dojo.keys.ENTER||e.charOrCode==" "){
if(this.toggleable){
this.toggle();
}
dojo.stopEvent(e);
}else{
if(e.charOrCode==dojo.keys.DOWN_ARROW&&this.open){
this.containerNode.focus();
e.preventDefault();
}
}
},_onTitleClick:function(){
if(this.toggleable){
this.toggle();
}
},setTitle:function(_4e3){
dojo.deprecated("dijit.TitlePane.setTitle() is deprecated.  Use set('title', ...) instead.","","2.0");
this.set("title",_4e3);
}});
}
if(!dojo._hasResource["dijit.tree.TreeStoreModel"]){
dojo._hasResource["dijit.tree.TreeStoreModel"]=true;
dojo.provide("dijit.tree.TreeStoreModel");
dojo.declare("dijit.tree.TreeStoreModel",null,{store:null,childrenAttrs:["children"],newItemIdAttr:"id",labelAttr:"",root:null,query:null,deferItemLoadingUntilExpand:false,constructor:function(args){
dojo.mixin(this,args);
this.connects=[];
var _4e4=this.store;
if(!_4e4.getFeatures()["dojo.data.api.Identity"]){
throw new Error("dijit.Tree: store must support dojo.data.Identity");
}
if(_4e4.getFeatures()["dojo.data.api.Notification"]){
this.connects=this.connects.concat([dojo.connect(_4e4,"onNew",this,"onNewItem"),dojo.connect(_4e4,"onDelete",this,"onDeleteItem"),dojo.connect(_4e4,"onSet",this,"onSetItem")]);
}
},destroy:function(){
dojo.forEach(this.connects,dojo.disconnect);
},getRoot:function(_4e5,_4e6){
if(this.root){
_4e5(this.root);
}else{
this.store.fetch({query:this.query,onComplete:dojo.hitch(this,function(_4e7){
if(_4e7.length!=1){
throw new Error(this.declaredClass+": query "+dojo.toJson(this.query)+" returned "+_4e7.length+" items, but must return exactly one item");
}
this.root=_4e7[0];
_4e5(this.root);
}),onError:_4e6});
}
},mayHaveChildren:function(item){
return dojo.some(this.childrenAttrs,function(attr){
return this.store.hasAttribute(item,attr);
},this);
},getChildren:function(_4e8,_4e9,_4ea){
var _4eb=this.store;
if(!_4eb.isItemLoaded(_4e8)){
var _4ec=dojo.hitch(this,arguments.callee);
_4eb.loadItem({item:_4e8,onItem:function(_4ed){
_4ec(_4ed,_4e9,_4ea);
},onError:_4ea});
return;
}
var _4ee=[];
for(var i=0;i<this.childrenAttrs.length;i++){
var vals=_4eb.getValues(_4e8,this.childrenAttrs[i]);
_4ee=_4ee.concat(vals);
}
var _4ef=0;
if(!this.deferItemLoadingUntilExpand){
dojo.forEach(_4ee,function(item){
if(!_4eb.isItemLoaded(item)){
_4ef++;
}
});
}
if(_4ef==0){
_4e9(_4ee);
}else{
dojo.forEach(_4ee,function(item,idx){
if(!_4eb.isItemLoaded(item)){
_4eb.loadItem({item:item,onItem:function(item){
_4ee[idx]=item;
if(--_4ef==0){
_4e9(_4ee);
}
},onError:_4ea});
}
});
}
},isItem:function(_4f0){
return this.store.isItem(_4f0);
},fetchItemByIdentity:function(_4f1){
this.store.fetchItemByIdentity(_4f1);
},getIdentity:function(item){
return this.store.getIdentity(item);
},getLabel:function(item){
if(this.labelAttr){
return this.store.getValue(item,this.labelAttr);
}else{
return this.store.getLabel(item);
}
},newItem:function(args,_4f2,_4f3){
var _4f4={parent:_4f2,attribute:this.childrenAttrs[0],insertIndex:_4f3};
if(this.newItemIdAttr&&args[this.newItemIdAttr]){
this.fetchItemByIdentity({identity:args[this.newItemIdAttr],scope:this,onItem:function(item){
if(item){
this.pasteItem(item,null,_4f2,true,_4f3);
}else{
this.store.newItem(args,_4f4);
}
}});
}else{
this.store.newItem(args,_4f4);
}
},pasteItem:function(_4f5,_4f6,_4f7,_4f8,_4f9){
var _4fa=this.store,_4fb=this.childrenAttrs[0];
if(_4f6){
dojo.forEach(this.childrenAttrs,function(attr){
if(_4fa.containsValue(_4f6,attr,_4f5)){
if(!_4f8){
var _4fc=dojo.filter(_4fa.getValues(_4f6,attr),function(x){
return x!=_4f5;
});
_4fa.setValues(_4f6,attr,_4fc);
}
_4fb=attr;
}
});
}
if(_4f7){
if(typeof _4f9=="number"){
var _4fd=_4fa.getValues(_4f7,_4fb).slice();
_4fd.splice(_4f9,0,_4f5);
_4fa.setValues(_4f7,_4fb,_4fd);
}else{
_4fa.setValues(_4f7,_4fb,_4fa.getValues(_4f7,_4fb).concat(_4f5));
}
}
},onChange:function(item){
},onChildrenChange:function(_4fe,_4ff){
},onDelete:function(_500,_501){
},onNewItem:function(item,_502){
if(!_502){
return;
}
this.getChildren(_502.item,dojo.hitch(this,function(_503){
this.onChildrenChange(_502.item,_503);
}));
},onDeleteItem:function(item){
this.onDelete(item);
},onSetItem:function(item,_504,_505,_506){
if(dojo.indexOf(this.childrenAttrs,_504)!=-1){
this.getChildren(item,dojo.hitch(this,function(_507){
this.onChildrenChange(item,_507);
}));
}else{
this.onChange(item);
}
}});
}
if(!dojo._hasResource["dojox.charting.scaler.common"]){
dojo._hasResource["dojox.charting.scaler.common"]=true;
dojo.provide("dojox.charting.scaler.common");
(function(){
var eq=function(a,b){
return Math.abs(a-b)<=0.000001*(Math.abs(a)+Math.abs(b));
};
dojo.mixin(dojox.charting.scaler.common,{findString:function(val,text){
val=val.toLowerCase();
for(var i=0;i<text.length;++i){
if(val==text[i]){
return true;
}
}
return false;
},getNumericLabel:function(_508,_509,_50a){
var def=_50a.fixed?_508.toFixed(_509<0?-_509:0):_508.toString();
if(_50a.labelFunc){
var r=_50a.labelFunc(def,_508,_509);
if(r){
return r;
}
}
if(_50a.labels){
var l=_50a.labels,lo=0,hi=l.length;
while(lo<hi){
var mid=Math.floor((lo+hi)/2),val=l[mid].value;
if(val<_508){
lo=mid+1;
}else{
hi=mid;
}
}
if(lo<l.length&&eq(l[lo].value,_508)){
return l[lo].text;
}
--lo;
if(lo>=0&&lo<l.length&&eq(l[lo].value,_508)){
return l[lo].text;
}
lo+=2;
if(lo<l.length&&eq(l[lo].value,_508)){
return l[lo].text;
}
}
return def;
}});
})();
}
if(!dojo._hasResource["dojox.charting.scaler.linear"]){
dojo._hasResource["dojox.charting.scaler.linear"]=true;
dojo.provide("dojox.charting.scaler.linear");
(function(){
var _50b=3,dc=dojox.charting,dcs=dc.scaler,dcsc=dcs.common,_50c=dcsc.findString,_50d=dcsc.getNumericLabel;
var _50e=function(min,max,_50f,_510,_511,_512,span){
_50f=dojo.delegate(_50f);
if(!_510){
if(_50f.fixUpper=="major"){
_50f.fixUpper="minor";
}
if(_50f.fixLower=="major"){
_50f.fixLower="minor";
}
}
if(!_511){
if(_50f.fixUpper=="minor"){
_50f.fixUpper="micro";
}
if(_50f.fixLower=="minor"){
_50f.fixLower="micro";
}
}
if(!_512){
if(_50f.fixUpper=="micro"){
_50f.fixUpper="none";
}
if(_50f.fixLower=="micro"){
_50f.fixLower="none";
}
}
var _513=_50c(_50f.fixLower,["major"])?Math.floor(_50f.min/_510)*_510:_50c(_50f.fixLower,["minor"])?Math.floor(_50f.min/_511)*_511:_50c(_50f.fixLower,["micro"])?Math.floor(_50f.min/_512)*_512:_50f.min,_514=_50c(_50f.fixUpper,["major"])?Math.ceil(_50f.max/_510)*_510:_50c(_50f.fixUpper,["minor"])?Math.ceil(_50f.max/_511)*_511:_50c(_50f.fixUpper,["micro"])?Math.ceil(_50f.max/_512)*_512:_50f.max;
if(_50f.useMin){
min=_513;
}
if(_50f.useMax){
max=_514;
}
var _515=(!_510||_50f.useMin&&_50c(_50f.fixLower,["major"]))?min:Math.ceil(min/_510)*_510,_516=(!_511||_50f.useMin&&_50c(_50f.fixLower,["major","minor"]))?min:Math.ceil(min/_511)*_511,_517=(!_512||_50f.useMin&&_50c(_50f.fixLower,["major","minor","micro"]))?min:Math.ceil(min/_512)*_512,_518=!_510?0:(_50f.useMax&&_50c(_50f.fixUpper,["major"])?Math.round((max-_515)/_510):Math.floor((max-_515)/_510))+1,_519=!_511?0:(_50f.useMax&&_50c(_50f.fixUpper,["major","minor"])?Math.round((max-_516)/_511):Math.floor((max-_516)/_511))+1,_51a=!_512?0:(_50f.useMax&&_50c(_50f.fixUpper,["major","minor","micro"])?Math.round((max-_517)/_512):Math.floor((max-_517)/_512))+1,_51b=_511?Math.round(_510/_511):0,_51c=_512?Math.round(_511/_512):0,_51d=_510?Math.floor(Math.log(_510)/Math.LN10):0,_51e=_511?Math.floor(Math.log(_511)/Math.LN10):0,_51f=span/(max-min);
if(!isFinite(_51f)){
_51f=1;
}
return {bounds:{lower:_513,upper:_514,from:min,to:max,scale:_51f,span:span},major:{tick:_510,start:_515,count:_518,prec:_51d},minor:{tick:_511,start:_516,count:_519,prec:_51e},micro:{tick:_512,start:_517,count:_51a,prec:0},minorPerMajor:_51b,microPerMinor:_51c,scaler:dcs.linear};
};
dojo.mixin(dojox.charting.scaler.linear,{buildScaler:function(min,max,span,_520){
var h={fixUpper:"none",fixLower:"none",natural:false};
if(_520){
if("fixUpper" in _520){
h.fixUpper=String(_520.fixUpper);
}
if("fixLower" in _520){
h.fixLower=String(_520.fixLower);
}
if("natural" in _520){
h.natural=Boolean(_520.natural);
}
}
if("min" in _520){
min=_520.min;
}
if("max" in _520){
max=_520.max;
}
if(_520.includeZero){
if(min>0){
min=0;
}
if(max<0){
max=0;
}
}
h.min=min;
h.useMin=true;
h.max=max;
h.useMax=true;
if("from" in _520){
min=_520.from;
h.useMin=false;
}
if("to" in _520){
max=_520.to;
h.useMax=false;
}
if(max<=min){
return _50e(min,max,h,0,0,0,span);
}
var mag=Math.floor(Math.log(max-min)/Math.LN10),_521=_520&&("majorTickStep" in _520)?_520.majorTickStep:Math.pow(10,mag),_522=0,_523=0,_524;
if(_520&&("minorTickStep" in _520)){
_522=_520.minorTickStep;
}else{
do{
_522=_521/10;
if(!h.natural||_522>0.9){
_524=_50e(min,max,h,_521,_522,0,span);
if(_524.bounds.scale*_524.minor.tick>_50b){
break;
}
}
_522=_521/5;
if(!h.natural||_522>0.9){
_524=_50e(min,max,h,_521,_522,0,span);
if(_524.bounds.scale*_524.minor.tick>_50b){
break;
}
}
_522=_521/2;
if(!h.natural||_522>0.9){
_524=_50e(min,max,h,_521,_522,0,span);
if(_524.bounds.scale*_524.minor.tick>_50b){
break;
}
}
return _50e(min,max,h,_521,0,0,span);
}while(false);
}
if(_520&&("microTickStep" in _520)){
_523=_520.microTickStep;
_524=_50e(min,max,h,_521,_522,_523,span);
}else{
do{
_523=_522/10;
if(!h.natural||_523>0.9){
_524=_50e(min,max,h,_521,_522,_523,span);
if(_524.bounds.scale*_524.micro.tick>_50b){
break;
}
}
_523=_522/5;
if(!h.natural||_523>0.9){
_524=_50e(min,max,h,_521,_522,_523,span);
if(_524.bounds.scale*_524.micro.tick>_50b){
break;
}
}
_523=_522/2;
if(!h.natural||_523>0.9){
_524=_50e(min,max,h,_521,_522,_523,span);
if(_524.bounds.scale*_524.micro.tick>_50b){
break;
}
}
_523=0;
}while(false);
}
return _523?_524:_50e(min,max,h,_521,_522,0,span);
},buildTicks:function(_525,_526){
var step,next,tick,_527=_525.major.start,_528=_525.minor.start,_529=_525.micro.start;
if(_526.microTicks&&_525.micro.tick){
step=_525.micro.tick,next=_529;
}else{
if(_526.minorTicks&&_525.minor.tick){
step=_525.minor.tick,next=_528;
}else{
if(_525.major.tick){
step=_525.major.tick,next=_527;
}else{
return null;
}
}
}
var _52a=1/_525.bounds.scale;
if(_525.bounds.to<=_525.bounds.from||isNaN(_52a)||!isFinite(_52a)||step<=0||isNaN(step)||!isFinite(step)){
return null;
}
var _52b=[],_52c=[],_52d=[];
while(next<=_525.bounds.to+_52a){
if(Math.abs(_527-next)<step/2){
tick={value:_527};
if(_526.majorLabels){
tick.label=_50d(_527,_525.major.prec,_526);
}
_52b.push(tick);
_527+=_525.major.tick;
_528+=_525.minor.tick;
_529+=_525.micro.tick;
}else{
if(Math.abs(_528-next)<step/2){
if(_526.minorTicks){
tick={value:_528};
if(_526.minorLabels&&(_525.minMinorStep<=_525.minor.tick*_525.bounds.scale)){
tick.label=_50d(_528,_525.minor.prec,_526);
}
_52c.push(tick);
}
_528+=_525.minor.tick;
_529+=_525.micro.tick;
}else{
if(_526.microTicks){
_52d.push({value:_529});
}
_529+=_525.micro.tick;
}
}
next+=step;
}
return {major:_52b,minor:_52c,micro:_52d};
},getTransformerFromModel:function(_52e){
var _52f=_52e.bounds.from,_530=_52e.bounds.scale;
return function(x){
return (x-_52f)*_530;
};
},getTransformerFromPlot:function(_531){
var _532=_531.bounds.from,_533=_531.bounds.scale;
return function(x){
return x/_533+_532;
};
}});
})();
}
if(!dojo._hasResource["dojox.gfx.matrix"]){
dojo._hasResource["dojox.gfx.matrix"]=true;
dojo.provide("dojox.gfx.matrix");
(function(){
var m=dojox.gfx.matrix;
var _534={};
m._degToRad=function(_535){
return _534[_535]||(_534[_535]=(Math.PI*_535/180));
};
m._radToDeg=function(_536){
return _536/Math.PI*180;
};
m.Matrix2D=function(arg){
if(arg){
if(typeof arg=="number"){
this.xx=this.yy=arg;
}else{
if(arg instanceof Array){
if(arg.length>0){
var _537=m.normalize(arg[0]);
for(var i=1;i<arg.length;++i){
var l=_537,r=dojox.gfx.matrix.normalize(arg[i]);
_537=new m.Matrix2D();
_537.xx=l.xx*r.xx+l.xy*r.yx;
_537.xy=l.xx*r.xy+l.xy*r.yy;
_537.yx=l.yx*r.xx+l.yy*r.yx;
_537.yy=l.yx*r.xy+l.yy*r.yy;
_537.dx=l.xx*r.dx+l.xy*r.dy+l.dx;
_537.dy=l.yx*r.dx+l.yy*r.dy+l.dy;
}
dojo.mixin(this,_537);
}
}else{
dojo.mixin(this,arg);
}
}
}
};
dojo.extend(m.Matrix2D,{xx:1,xy:0,yx:0,yy:1,dx:0,dy:0});
dojo.mixin(m,{identity:new m.Matrix2D(),flipX:new m.Matrix2D({xx:-1}),flipY:new m.Matrix2D({yy:-1}),flipXY:new m.Matrix2D({xx:-1,yy:-1}),translate:function(a,b){
if(arguments.length>1){
return new m.Matrix2D({dx:a,dy:b});
}
return new m.Matrix2D({dx:a.x,dy:a.y});
},scale:function(a,b){
if(arguments.length>1){
return new m.Matrix2D({xx:a,yy:b});
}
if(typeof a=="number"){
return new m.Matrix2D({xx:a,yy:a});
}
return new m.Matrix2D({xx:a.x,yy:a.y});
},rotate:function(_538){
var c=Math.cos(_538);
var s=Math.sin(_538);
return new m.Matrix2D({xx:c,xy:-s,yx:s,yy:c});
},rotateg:function(_539){
return m.rotate(m._degToRad(_539));
},skewX:function(_53a){
return new m.Matrix2D({xy:Math.tan(_53a)});
},skewXg:function(_53b){
return m.skewX(m._degToRad(_53b));
},skewY:function(_53c){
return new m.Matrix2D({yx:Math.tan(_53c)});
},skewYg:function(_53d){
return m.skewY(m._degToRad(_53d));
},reflect:function(a,b){
if(arguments.length==1){
b=a.y;
a=a.x;
}
var a2=a*a,b2=b*b,n2=a2+b2,xy=2*a*b/n2;
return new m.Matrix2D({xx:2*a2/n2-1,xy:xy,yx:xy,yy:2*b2/n2-1});
},project:function(a,b){
if(arguments.length==1){
b=a.y;
a=a.x;
}
var a2=a*a,b2=b*b,n2=a2+b2,xy=a*b/n2;
return new m.Matrix2D({xx:a2/n2,xy:xy,yx:xy,yy:b2/n2});
},normalize:function(_53e){
return (_53e instanceof m.Matrix2D)?_53e:new m.Matrix2D(_53e);
},clone:function(_53f){
var obj=new m.Matrix2D();
for(var i in _53f){
if(typeof (_53f[i])=="number"&&typeof (obj[i])=="number"&&obj[i]!=_53f[i]){
obj[i]=_53f[i];
}
}
return obj;
},invert:function(_540){
var M=m.normalize(_540),D=M.xx*M.yy-M.xy*M.yx,M=new m.Matrix2D({xx:M.yy/D,xy:-M.xy/D,yx:-M.yx/D,yy:M.xx/D,dx:(M.xy*M.dy-M.yy*M.dx)/D,dy:(M.yx*M.dx-M.xx*M.dy)/D});
return M;
},_multiplyPoint:function(_541,x,y){
return {x:_541.xx*x+_541.xy*y+_541.dx,y:_541.yx*x+_541.yy*y+_541.dy};
},multiplyPoint:function(_542,a,b){
var M=m.normalize(_542);
if(typeof a=="number"&&typeof b=="number"){
return m._multiplyPoint(M,a,b);
}
return m._multiplyPoint(M,a.x,a.y);
},multiply:function(_543){
var M=m.normalize(_543);
for(var i=1;i<arguments.length;++i){
var l=M,r=m.normalize(arguments[i]);
M=new m.Matrix2D();
M.xx=l.xx*r.xx+l.xy*r.yx;
M.xy=l.xx*r.xy+l.xy*r.yy;
M.yx=l.yx*r.xx+l.yy*r.yx;
M.yy=l.yx*r.xy+l.yy*r.yy;
M.dx=l.xx*r.dx+l.xy*r.dy+l.dx;
M.dy=l.yx*r.dx+l.yy*r.dy+l.dy;
}
return M;
},_sandwich:function(_544,x,y){
return m.multiply(m.translate(x,y),_544,m.translate(-x,-y));
},scaleAt:function(a,b,c,d){
switch(arguments.length){
case 4:
return m._sandwich(m.scale(a,b),c,d);
case 3:
if(typeof c=="number"){
return m._sandwich(m.scale(a),b,c);
}
return m._sandwich(m.scale(a,b),c.x,c.y);
}
return m._sandwich(m.scale(a),b.x,b.y);
},rotateAt:function(_545,a,b){
if(arguments.length>2){
return m._sandwich(m.rotate(_545),a,b);
}
return m._sandwich(m.rotate(_545),a.x,a.y);
},rotategAt:function(_546,a,b){
if(arguments.length>2){
return m._sandwich(m.rotateg(_546),a,b);
}
return m._sandwich(m.rotateg(_546),a.x,a.y);
},skewXAt:function(_547,a,b){
if(arguments.length>2){
return m._sandwich(m.skewX(_547),a,b);
}
return m._sandwich(m.skewX(_547),a.x,a.y);
},skewXgAt:function(_548,a,b){
if(arguments.length>2){
return m._sandwich(m.skewXg(_548),a,b);
}
return m._sandwich(m.skewXg(_548),a.x,a.y);
},skewYAt:function(_549,a,b){
if(arguments.length>2){
return m._sandwich(m.skewY(_549),a,b);
}
return m._sandwich(m.skewY(_549),a.x,a.y);
},skewYgAt:function(_54a,a,b){
if(arguments.length>2){
return m._sandwich(m.skewYg(_54a),a,b);
}
return m._sandwich(m.skewYg(_54a),a.x,a.y);
}});
})();
dojox.gfx.Matrix2D=dojox.gfx.matrix.Matrix2D;
}
if(!dojo._hasResource["dojox.gfx._base"]){
dojo._hasResource["dojox.gfx._base"]=true;
dojo.provide("dojox.gfx._base");
(function(){
var g=dojox.gfx,b=g._base;
g._hasClass=function(node,_54b){
var cls=node.getAttribute("className");
return cls&&(" "+cls+" ").indexOf(" "+_54b+" ")>=0;
};
g._addClass=function(node,_54c){
var cls=node.getAttribute("className")||"";
if(!cls||(" "+cls+" ").indexOf(" "+_54c+" ")<0){
node.setAttribute("className",cls+(cls?" ":"")+_54c);
}
};
g._removeClass=function(node,_54d){
var cls=node.getAttribute("className");
if(cls){
node.setAttribute("className",cls.replace(new RegExp("(^|\\s+)"+_54d+"(\\s+|$)"),"$1$2"));
}
};
b._getFontMeasurements=function(){
var _54e={"1em":0,"1ex":0,"100%":0,"12pt":0,"16px":0,"xx-small":0,"x-small":0,"small":0,"medium":0,"large":0,"x-large":0,"xx-large":0};
if(dojo.isIE){
dojo.doc.documentElement.style.fontSize="100%";
}
var div=dojo.doc.createElement("div");
var s=div.style;
s.position="absolute";
s.left="-100px";
s.top="0px";
s.width="30px";
s.height="1000em";
s.borderWidth="0px";
s.margin="0px";
s.padding="0px";
s.outline="none";
s.lineHeight="1";
s.overflow="hidden";
dojo.body().appendChild(div);
for(var p in _54e){
div.style.fontSize=p;
_54e[p]=Math.round(div.offsetHeight*12/16)*16/12/1000;
}
dojo.body().removeChild(div);
div=null;
return _54e;
};
var _54f=null;
b._getCachedFontMeasurements=function(_550){
if(_550||!_54f){
_54f=b._getFontMeasurements();
}
return _54f;
};
var _551=null,_552={};
b._getTextBox=function(text,_553,_554){
var m,s,al=arguments.length;
if(!_551){
m=_551=dojo.doc.createElement("div");
s=m.style;
s.position="absolute";
s.left="-10000px";
s.top="0";
dojo.body().appendChild(m);
}else{
m=_551;
s=m.style;
}
m.className="";
s.borderWidth="0";
s.margin="0";
s.padding="0";
s.outline="0";
if(al>1&&_553){
for(var i in _553){
if(i in _552){
continue;
}
s[i]=_553[i];
}
}
if(al>2&&_554){
m.className=_554;
}
m.innerHTML=text;
if(m["getBoundingClientRect"]){
var bcr=m.getBoundingClientRect();
return {l:bcr.left,t:bcr.top,w:bcr.width||(bcr.right-bcr.left),h:bcr.height||(bcr.bottom-bcr.top)};
}else{
return dojo.marginBox(m);
}
};
var _555=0;
b._getUniqueId=function(){
var id;
do{
id=dojo._scopeName+"Unique"+(++_555);
}while(dojo.byId(id));
return id;
};
})();
dojo.mixin(dojox.gfx,{defaultPath:{type:"path",path:""},defaultPolyline:{type:"polyline",points:[]},defaultRect:{type:"rect",x:0,y:0,width:100,height:100,r:0},defaultEllipse:{type:"ellipse",cx:0,cy:0,rx:200,ry:100},defaultCircle:{type:"circle",cx:0,cy:0,r:100},defaultLine:{type:"line",x1:0,y1:0,x2:100,y2:100},defaultImage:{type:"image",x:0,y:0,width:0,height:0,src:""},defaultText:{type:"text",x:0,y:0,text:"",align:"start",decoration:"none",rotated:false,kerning:true},defaultTextPath:{type:"textpath",text:"",align:"start",decoration:"none",rotated:false,kerning:true},defaultStroke:{type:"stroke",color:"black",style:"solid",width:1,cap:"butt",join:4},defaultLinearGradient:{type:"linear",x1:0,y1:0,x2:100,y2:100,colors:[{offset:0,color:"black"},{offset:1,color:"white"}]},defaultRadialGradient:{type:"radial",cx:0,cy:0,r:100,colors:[{offset:0,color:"black"},{offset:1,color:"white"}]},defaultPattern:{type:"pattern",x:0,y:0,width:0,height:0,src:""},defaultFont:{type:"font",style:"normal",variant:"normal",weight:"normal",size:"10pt",family:"serif"},getDefault:(function(){
var _556={};
return function(type){
var t=_556[type];
if(t){
return new t();
}
t=_556[type]=new Function;
t.prototype=dojox.gfx["default"+type];
return new t();
};
})(),normalizeColor:function(_557){
return (_557 instanceof dojo.Color)?_557:new dojo.Color(_557);
},normalizeParameters:function(_558,_559){
if(_559){
var _55a={};
for(var x in _558){
if(x in _559&&!(x in _55a)){
_558[x]=_559[x];
}
}
}
return _558;
},makeParameters:function(_55b,_55c){
if(!_55c){
return dojo.delegate(_55b);
}
var _55d={};
for(var i in _55b){
if(!(i in _55d)){
_55d[i]=dojo.clone((i in _55c)?_55c[i]:_55b[i]);
}
}
return _55d;
},formatNumber:function(x,_55e){
var val=x.toString();
if(val.indexOf("e")>=0){
val=x.toFixed(4);
}else{
var _55f=val.indexOf(".");
if(_55f>=0&&val.length-_55f>5){
val=x.toFixed(4);
}
}
if(x<0){
return val;
}
return _55e?" "+val:val;
},makeFontString:function(font){
return font.style+" "+font.variant+" "+font.weight+" "+font.size+" "+font.family;
},splitFontString:function(str){
var font=dojox.gfx.getDefault("Font");
var t=str.split(/\s+/);
do{
if(t.length<5){
break;
}
font.style=t[0];
font.variant=t[1];
font.weight=t[2];
var i=t[3].indexOf("/");
font.size=i<0?t[3]:t[3].substring(0,i);
var j=4;
if(i<0){
if(t[4]=="/"){
j=6;
}else{
if(t[4].charAt(0)=="/"){
j=5;
}
}
}
if(j<t.length){
font.family=t.slice(j).join(" ");
}
}while(false);
return font;
},cm_in_pt:72/2.54,mm_in_pt:7.2/2.54,px_in_pt:function(){
return dojox.gfx._base._getCachedFontMeasurements()["12pt"]/12;
},pt2px:function(len){
return len*dojox.gfx.px_in_pt();
},px2pt:function(len){
return len/dojox.gfx.px_in_pt();
},normalizedLength:function(len){
if(len.length==0){
return 0;
}
if(len.length>2){
var _560=dojox.gfx.px_in_pt();
var val=parseFloat(len);
switch(len.slice(-2)){
case "px":
return val;
case "pt":
return val*_560;
case "in":
return val*72*_560;
case "pc":
return val*12*_560;
case "mm":
return val*dojox.gfx.mm_in_pt*_560;
case "cm":
return val*dojox.gfx.cm_in_pt*_560;
}
}
return parseFloat(len);
},pathVmlRegExp:/([A-Za-z]+)|(\d+(\.\d+)?)|(\.\d+)|(-\d+(\.\d+)?)|(-\.\d+)/g,pathSvgRegExp:/([A-Za-z])|(\d+(\.\d+)?)|(\.\d+)|(-\d+(\.\d+)?)|(-\.\d+)/g,equalSources:function(a,b){
return a&&b&&a==b;
}});
}
if(!dojo._hasResource["dojox.gfx"]){
dojo._hasResource["dojox.gfx"]=true;
dojo.provide("dojox.gfx");
dojo.loadInit(function(){
var gfx=dojo.getObject("dojox.gfx",true),sl,flag,_561;
if(!gfx.renderer){
if(dojo.config.forceGfxRenderer){
dojox.gfx.renderer=dojo.config.forceGfxRenderer;
return;
}
var _562=(typeof dojo.config.gfxRenderer=="string"?dojo.config.gfxRenderer:"svg,vml,silverlight,canvas").split(",");
var ua=navigator.userAgent,_563=0,_564=0;
if(dojo.isSafari>=3){
if(ua.indexOf("iPhone")>=0||ua.indexOf("iPod")>=0){
_561=ua.match(/Version\/(\d(\.\d)?(\.\d)?)\sMobile\/([^\s]*)\s?/);
if(_561){
_563=parseInt(_561[4].substr(0,3),16);
}
}
}
if(dojo.isWebKit){
if(!_563){
_561=ua.match(/Android\s+(\d+\.\d+)/);
if(_561){
_564=parseFloat(_561[1]);
}
}
}
for(var i=0;i<_562.length;++i){
switch(_562[i]){
case "svg":
if(!dojo.isIE&&(!_563||_563>=1521)&&!_564&&!dojo.isAIR){
dojox.gfx.renderer="svg";
}
break;
case "vml":
if(dojo.isIE){
dojox.gfx.renderer="vml";
}
break;
case "silverlight":
try{
if(dojo.isIE){
sl=new ActiveXObject("AgControl.AgControl");
if(sl&&sl.IsVersionSupported("1.0")){
flag=true;
}
}else{
if(navigator.plugins["Silverlight Plug-In"]){
flag=true;
}
}
}
catch(e){
flag=false;
}
finally{
sl=null;
}
if(flag){
dojox.gfx.renderer="silverlight";
}
break;
case "canvas":
if(!dojo.isIE){
dojox.gfx.renderer="canvas";
}
break;
}
if(dojox.gfx.renderer){
break;
}
}
if(dojo.config.isDebug){
}
}
});
dojo.requireIf(dojox.gfx.renderer=="svg","dojox.gfx.svg");
dojo.requireIf(dojox.gfx.renderer=="vml","dojox.gfx.vml");
dojo.requireIf(dojox.gfx.renderer=="silverlight","dojox.gfx.silverlight");
dojo.requireIf(dojox.gfx.renderer=="canvas","dojox.gfx.canvas");
}
if(!dojo._hasResource["dojox.charting.axis2d.common"]){
dojo._hasResource["dojox.charting.axis2d.common"]=true;
dojo.provide("dojox.charting.axis2d.common");
(function(){
var g=dojox.gfx;
var _565=function(s){
s.marginLeft="0px";
s.marginTop="0px";
s.marginRight="0px";
s.marginBottom="0px";
s.paddingLeft="0px";
s.paddingTop="0px";
s.paddingRight="0px";
s.paddingBottom="0px";
s.borderLeftWidth="0px";
s.borderTopWidth="0px";
s.borderRightWidth="0px";
s.borderBottomWidth="0px";
};
var _566=function(n){
if(n["getBoundingClientRect"]){
var bcr=n.getBoundingClientRect();
return bcr.width||(bcr.right-bcr.left);
}else{
return dojo.marginBox(n).w;
}
};
dojo.mixin(dojox.charting.axis2d.common,{createText:{gfx:function(_567,_568,x,y,_569,text,font,_56a){
return _568.createText({x:x,y:y,text:text,align:_569}).setFont(font).setFill(_56a);
},html:function(_56b,_56c,x,y,_56d,text,font,_56e,_56f){
var p=dojo.doc.createElement("div"),s=p.style,_570;
_565(s);
s.font=font;
p.innerHTML=String(text).replace(/\s/g,"&nbsp;");
s.color=_56e;
s.position="absolute";
s.left="-10000px";
dojo.body().appendChild(p);
var size=g.normalizedLength(g.splitFontString(font).size);
if(!_56f){
_570=_566(p);
}
dojo.body().removeChild(p);
s.position="relative";
if(_56f){
s.width=_56f+"px";
switch(_56d){
case "middle":
s.textAlign="center";
s.left=(x-_56f/2)+"px";
break;
case "end":
s.textAlign="right";
s.left=(x-_56f)+"px";
break;
default:
s.left=x+"px";
s.textAlign="left";
break;
}
}else{
switch(_56d){
case "middle":
s.left=Math.floor(x-_570/2)+"px";
break;
case "end":
s.left=Math.floor(x-_570)+"px";
break;
default:
s.left=Math.floor(x)+"px";
break;
}
}
s.top=Math.floor(y-size)+"px";
s.whiteSpace="nowrap";
var wrap=dojo.doc.createElement("div"),w=wrap.style;
_565(w);
w.width="0px";
w.height="0px";
wrap.appendChild(p);
_56b.node.insertBefore(wrap,_56b.node.firstChild);
return wrap;
}}});
})();
}
if(!dojo._hasResource["dojox.charting.Element"]){
dojo._hasResource["dojox.charting.Element"]=true;
dojo.provide("dojox.charting.Element");
dojo.declare("dojox.charting.Element",null,{chart:null,group:null,htmlElements:null,dirty:true,constructor:function(_571){
this.chart=_571;
this.group=null;
this.htmlElements=[];
this.dirty=true;
},createGroup:function(_572){
if(!_572){
_572=this.chart.surface;
}
if(!this.group){
this.group=_572.createGroup();
}
return this;
},purgeGroup:function(){
this.destroyHtmlElements();
if(this.group){
this.group.clear();
this.group.removeShape();
this.group=null;
}
this.dirty=true;
return this;
},cleanGroup:function(_573){
this.destroyHtmlElements();
if(!_573){
_573=this.chart.surface;
}
if(this.group){
this.group.clear();
}else{
this.group=_573.createGroup();
}
this.dirty=true;
return this;
},destroyHtmlElements:function(){
if(this.htmlElements.length){
dojo.forEach(this.htmlElements,dojo.destroy);
this.htmlElements=[];
}
},destroy:function(){
this.purgeGroup();
},_plotFill:function(fill,dim,_574){
if(!fill||!fill.type||!fill.space){
return fill;
}
var _575=fill.space;
switch(fill.type){
case "linear":
if(_575==="plot"||_575==="shapeX"||_575==="shapeY"){
fill=dojox.gfx.makeParameters(dojox.gfx.defaultLinearGradient,fill);
fill.space=_575;
if(_575==="plot"||_575==="shapeX"){
var span=dim.height-_574.t-_574.b;
fill.y1=_574.t+span*fill.y1/100;
fill.y2=_574.t+span*fill.y2/100;
}
if(_575==="plot"||_575==="shapeY"){
var span=dim.width-_574.l-_574.r;
fill.x1=_574.l+span*fill.x1/100;
fill.x2=_574.l+span*fill.x2/100;
}
}
break;
case "radial":
if(_575==="plot"){
fill=dojox.gfx.makeParameters(dojox.gfx.defaultRadialGradient,fill);
fill.space=_575;
var _576=dim.width-_574.l-_574.r,_577=dim.height-_574.t-_574.b;
fill.cx=_574.l+_576*fill.cx/100;
fill.cy=_574.t+_577*fill.cy/100;
fill.r=fill.r*Math.sqrt(_576*_576+_577*_577)/200;
}
break;
case "pattern":
if(_575==="plot"||_575==="shapeX"||_575==="shapeY"){
fill=dojox.gfx.makeParameters(dojox.gfx.defaultPattern,fill);
fill.space=_575;
if(_575==="plot"||_575==="shapeX"){
var span=dim.height-_574.t-_574.b;
fill.y=_574.t+span*fill.y/100;
fill.height=span*fill.height/100;
}
if(_575==="plot"||_575==="shapeY"){
var span=dim.width-_574.l-_574.r;
fill.x=_574.l+span*fill.x/100;
fill.width=span*fill.width/100;
}
}
break;
}
return fill;
},_shapeFill:function(fill,bbox){
if(!fill||!fill.space){
return fill;
}
var _578=fill.space;
switch(fill.type){
case "linear":
if(_578==="shape"||_578==="shapeX"||_578==="shapeY"){
fill=dojox.gfx.makeParameters(dojox.gfx.defaultLinearGradient,fill);
fill.space=_578;
if(_578==="shape"||_578==="shapeX"){
var span=bbox.width;
fill.x1=bbox.x+span*fill.x1/100;
fill.x2=bbox.x+span*fill.x2/100;
}
if(_578==="shape"||_578==="shapeY"){
var span=bbox.height;
fill.y1=bbox.y+span*fill.y1/100;
fill.y2=bbox.y+span*fill.y2/100;
}
}
break;
case "radial":
if(_578==="shape"){
fill=dojox.gfx.makeParameters(dojox.gfx.defaultRadialGradient,fill);
fill.space=_578;
fill.cx=bbox.x+bbox.width/2;
fill.cy=bbox.y+bbox.height/2;
fill.r=fill.r*bbox.width/200;
}
break;
case "pattern":
if(_578==="shape"||_578==="shapeX"||_578==="shapeY"){
fill=dojox.gfx.makeParameters(dojox.gfx.defaultPattern,fill);
fill.space=_578;
if(_578==="shape"||_578==="shapeX"){
var span=bbox.width;
fill.x=bbox.x+span*fill.x/100;
fill.width=span*fill.width/100;
}
if(_578==="shape"||_578==="shapeY"){
var span=bbox.height;
fill.y=bbox.y+span*fill.y/100;
fill.height=span*fill.height/100;
}
}
break;
}
return fill;
},_pseudoRadialFill:function(fill,_579,_57a,_57b,end){
if(!fill||fill.type!=="radial"||fill.space!=="shape"){
return fill;
}
var _57c=fill.space;
fill=dojox.gfx.makeParameters(dojox.gfx.defaultRadialGradient,fill);
fill.space=_57c;
if(arguments.length<4){
fill.cx=_579.x;
fill.cy=_579.y;
fill.r=fill.r*_57a/100;
return fill;
}
var _57d=arguments.length<5?_57b:(end+_57b)/2;
return {type:"linear",x1:_579.x,y1:_579.y,x2:_579.x+fill.r*_57a*Math.cos(_57d)/100,y2:_579.y+fill.r*_57a*Math.sin(_57d)/100,colors:fill.colors};
return fill;
}});
}
if(!dojo._hasResource["dojox.charting.axis2d.Base"]){
dojo._hasResource["dojox.charting.axis2d.Base"]=true;
dojo.provide("dojox.charting.axis2d.Base");
dojo.declare("dojox.charting.axis2d.Base",dojox.charting.Element,{constructor:function(_57e,_57f){
this.vertical=_57f&&_57f.vertical;
},clear:function(){
return this;
},initialized:function(){
return false;
},calculate:function(min,max,span){
return this;
},getScaler:function(){
return null;
},getTicks:function(){
return null;
},getOffsets:function(){
return {l:0,r:0,t:0,b:0};
},render:function(dim,_580){
this.dirty=false;
return this;
}});
}
if(!dojo._hasResource["dojox.lang.functional.lambda"]){
dojo._hasResource["dojox.lang.functional.lambda"]=true;
dojo.provide("dojox.lang.functional.lambda");
(function(){
var df=dojox.lang.functional,_581={};
var _582="ab".split(/a*/).length>1?String.prototype.split:function(sep){
var r=this.split.call(this,sep),m=sep.exec(this);
if(m&&m.index==0){
r.unshift("");
}
return r;
};
var _583=function(s){
var args=[],_584=_582.call(s,/\s*->\s*/m);
if(_584.length>1){
while(_584.length){
s=_584.pop();
args=_584.pop().split(/\s*,\s*|\s+/m);
if(_584.length){
_584.push("(function("+args+"){return ("+s+")})");
}
}
}else{
if(s.match(/\b_\b/)){
args=["_"];
}else{
var l=s.match(/^\s*(?:[+*\/%&|\^\.=<>]|!=)/m),r=s.match(/[+\-*\/%&|\^\.=<>!]\s*$/m);
if(l||r){
if(l){
args.push("$1");
s="$1"+s;
}
if(r){
args.push("$2");
s=s+"$2";
}
}else{
var vars=s.replace(/(?:\b[A-Z]|\.[a-zA-Z_$])[a-zA-Z_$\d]*|[a-zA-Z_$][a-zA-Z_$\d]*:|this|true|false|null|undefined|typeof|instanceof|in|delete|new|void|arguments|decodeURI|decodeURIComponent|encodeURI|encodeURIComponent|escape|eval|isFinite|isNaN|parseFloat|parseInt|unescape|dojo|dijit|dojox|window|document|'(?:[^'\\]|\\.)*'|"(?:[^"\\]|\\.)*"/g,"").match(/([a-z_$][a-z_$\d]*)/gi)||[],t={};
dojo.forEach(vars,function(v){
if(!(v in t)){
args.push(v);
t[v]=1;
}
});
}
}
}
return {args:args,body:s};
};
var _585=function(a){
return a.length?function(){
var i=a.length-1,x=df.lambda(a[i]).apply(this,arguments);
for(--i;i>=0;--i){
x=df.lambda(a[i]).call(this,x);
}
return x;
}:function(x){
return x;
};
};
dojo.mixin(df,{rawLambda:function(s){
return _583(s);
},buildLambda:function(s){
s=_583(s);
return "function("+s.args.join(",")+"){return ("+s.body+");}";
},lambda:function(s){
if(typeof s=="function"){
return s;
}
if(s instanceof Array){
return _585(s);
}
if(s in _581){
return _581[s];
}
s=_583(s);
return _581[s]=new Function(s.args,"return ("+s.body+");");
},clearLambdaCache:function(){
_581={};
}});
})();
}
if(!dojo._hasResource["dojox.lang.functional.array"]){
dojo._hasResource["dojox.lang.functional.array"]=true;
dojo.provide("dojox.lang.functional.array");
(function(){
var d=dojo,df=dojox.lang.functional,_586={};
d.mixin(df,{filter:function(a,f,o){
if(typeof a=="string"){
a=a.split("");
}
o=o||d.global;
f=df.lambda(f);
var t=[],v,i,n;
if(d.isArray(a)){
for(i=0,n=a.length;i<n;++i){
v=a[i];
if(f.call(o,v,i,a)){
t.push(v);
}
}
}else{
if(typeof a.hasNext=="function"&&typeof a.next=="function"){
for(i=0;a.hasNext();){
v=a.next();
if(f.call(o,v,i++,a)){
t.push(v);
}
}
}else{
for(i in a){
if(!(i in _586)){
v=a[i];
if(f.call(o,v,i,a)){
t.push(v);
}
}
}
}
}
return t;
},forEach:function(a,f,o){
if(typeof a=="string"){
a=a.split("");
}
o=o||d.global;
f=df.lambda(f);
var i,n;
if(d.isArray(a)){
for(i=0,n=a.length;i<n;f.call(o,a[i],i,a),++i){
}
}else{
if(typeof a.hasNext=="function"&&typeof a.next=="function"){
for(i=0;a.hasNext();f.call(o,a.next(),i++,a)){
}
}else{
for(i in a){
if(!(i in _586)){
f.call(o,a[i],i,a);
}
}
}
}
return o;
},map:function(a,f,o){
if(typeof a=="string"){
a=a.split("");
}
o=o||d.global;
f=df.lambda(f);
var t,n,i;
if(d.isArray(a)){
t=new Array(n=a.length);
for(i=0;i<n;t[i]=f.call(o,a[i],i,a),++i){
}
}else{
if(typeof a.hasNext=="function"&&typeof a.next=="function"){
t=[];
for(i=0;a.hasNext();t.push(f.call(o,a.next(),i++,a))){
}
}else{
t=[];
for(i in a){
if(!(i in _586)){
t.push(f.call(o,a[i],i,a));
}
}
}
}
return t;
},every:function(a,f,o){
if(typeof a=="string"){
a=a.split("");
}
o=o||d.global;
f=df.lambda(f);
var i,n;
if(d.isArray(a)){
for(i=0,n=a.length;i<n;++i){
if(!f.call(o,a[i],i,a)){
return false;
}
}
}else{
if(typeof a.hasNext=="function"&&typeof a.next=="function"){
for(i=0;a.hasNext();){
if(!f.call(o,a.next(),i++,a)){
return false;
}
}
}else{
for(i in a){
if(!(i in _586)){
if(!f.call(o,a[i],i,a)){
return false;
}
}
}
}
}
return true;
},some:function(a,f,o){
if(typeof a=="string"){
a=a.split("");
}
o=o||d.global;
f=df.lambda(f);
var i,n;
if(d.isArray(a)){
for(i=0,n=a.length;i<n;++i){
if(f.call(o,a[i],i,a)){
return true;
}
}
}else{
if(typeof a.hasNext=="function"&&typeof a.next=="function"){
for(i=0;a.hasNext();){
if(f.call(o,a.next(),i++,a)){
return true;
}
}
}else{
for(i in a){
if(!(i in _586)){
if(f.call(o,a[i],i,a)){
return true;
}
}
}
}
}
return false;
}});
})();
}
if(!dojo._hasResource["dojox.lang.functional.object"]){
dojo._hasResource["dojox.lang.functional.object"]=true;
dojo.provide("dojox.lang.functional.object");
(function(){
var d=dojo,df=dojox.lang.functional,_587={};
d.mixin(df,{keys:function(obj){
var t=[];
for(var i in obj){
if(!(i in _587)){
t.push(i);
}
}
return t;
},values:function(obj){
var t=[];
for(var i in obj){
if(!(i in _587)){
t.push(obj[i]);
}
}
return t;
},filterIn:function(obj,f,o){
o=o||d.global;
f=df.lambda(f);
var t={},v,i;
for(i in obj){
if(!(i in _587)){
v=obj[i];
if(f.call(o,v,i,obj)){
t[i]=v;
}
}
}
return t;
},forIn:function(obj,f,o){
o=o||d.global;
f=df.lambda(f);
for(var i in obj){
if(!(i in _587)){
f.call(o,obj[i],i,obj);
}
}
return o;
},mapIn:function(obj,f,o){
o=o||d.global;
f=df.lambda(f);
var t={},i;
for(i in obj){
if(!(i in _587)){
t[i]=f.call(o,obj[i],i,obj);
}
}
return t;
}});
})();
}
if(!dojo._hasResource["dojox.lang.functional"]){
dojo._hasResource["dojox.lang.functional"]=true;
dojo.provide("dojox.lang.functional");
}
if(!dojo._hasResource["dojox.lang.utils"]){
dojo._hasResource["dojox.lang.utils"]=true;
dojo.provide("dojox.lang.utils");
(function(){
var _588={},du=dojox.lang.utils,opts=Object.prototype.toString;
var _589=function(o){
if(o){
switch(opts.call(o)){
case "[object Array]":
return o.slice(0);
case "[object Object]":
return dojo.delegate(o);
}
}
return o;
};
dojo.mixin(du,{coerceType:function(_58a,_58b){
switch(typeof _58a){
case "number":
return Number(eval("("+_58b+")"));
case "string":
return String(_58b);
case "boolean":
return Boolean(eval("("+_58b+")"));
}
return eval("("+_58b+")");
},updateWithObject:function(_58c,_58d,conv){
if(!_58d){
return _58c;
}
for(var x in _58c){
if(x in _58d&&!(x in _588)){
var t=_58c[x];
if(t&&typeof t=="object"){
du.updateWithObject(t,_58d[x],conv);
}else{
_58c[x]=conv?du.coerceType(t,_58d[x]):_589(_58d[x]);
}
}
}
return _58c;
},updateWithPattern:function(_58e,_58f,_590,conv){
if(!_58f||!_590){
return _58e;
}
for(var x in _590){
if(x in _58f&&!(x in _588)){
_58e[x]=conv?du.coerceType(_590[x],_58f[x]):_589(_58f[x]);
}
}
return _58e;
},merge:function(_591,_592){
if(_592){
var _593=opts.call(_591),_594=opts.call(_592),t,i,l,m;
switch(_594){
case "[object Array]":
if(_594==_593){
t=new Array(Math.max(_591.length,_592.length));
for(i=0,l=t.length;i<l;++i){
t[i]=du.merge(_591[i],_592[i]);
}
return t;
}
return _592.slice(0);
case "[object Object]":
if(_594==_593&&_591){
t=dojo.delegate(_591);
for(i in _592){
if(i in _591){
l=_591[i];
m=_592[i];
if(m!==l){
t[i]=du.merge(l,m);
}
}else{
t[i]=dojo.clone(_592[i]);
}
}
return t;
}
return dojo.clone(_592);
}
}
return _592;
}});
})();
}
if(!dojo._hasResource["dojox.charting.axis2d.Invisible"]){
dojo._hasResource["dojox.charting.axis2d.Invisible"]=true;
dojo.provide("dojox.charting.axis2d.Invisible");
(function(){
var dc=dojox.charting,df=dojox.lang.functional,du=dojox.lang.utils,g=dojox.gfx,lin=dc.scaler.linear,_595=du.merge,_596=4,_597=45;
dojo.declare("dojox.charting.axis2d.Invisible",dojox.charting.axis2d.Base,{defaultParams:{vertical:false,fixUpper:"none",fixLower:"none",natural:false,leftBottom:true,includeZero:false,fixed:true,majorLabels:true,minorTicks:true,minorLabels:true,microTicks:false,rotation:0},optionalParams:{min:0,max:1,from:0,to:1,majorTickStep:4,minorTickStep:2,microTickStep:1,labels:[],labelFunc:null,maxLabelSize:0},constructor:function(_598,_599){
this.opt=dojo.delegate(this.defaultParams,_599);
du.updateWithPattern(this.opt,_599,this.optionalParams);
},dependOnData:function(){
return !("min" in this.opt)||!("max" in this.opt);
},clear:function(){
delete this.scaler;
delete this.ticks;
this.dirty=true;
return this;
},initialized:function(){
return "scaler" in this&&!(this.dirty&&this.dependOnData());
},setWindow:function(_59a,_59b){
this.scale=_59a;
this.offset=_59b;
return this.clear();
},getWindowScale:function(){
return "scale" in this?this.scale:1;
},getWindowOffset:function(){
return "offset" in this?this.offset:0;
},_groupLabelWidth:function(_59c,font){
if(!_59c.length){
return 0;
}
if(dojo.isObject(_59c[0])){
_59c=df.map(_59c,function(_59d){
return _59d.text;
});
}
var s=_59c.join("<br>");
return dojox.gfx._base._getTextBox(s,{font:font}).w||0;
},calculate:function(min,max,span,_59e){
if(this.initialized()){
return this;
}
var o=this.opt;
this.labels="labels" in o?o.labels:_59e;
this.scaler=lin.buildScaler(min,max,span,o);
var tsb=this.scaler.bounds;
if("scale" in this){
o.from=tsb.lower+this.offset;
o.to=(tsb.upper-tsb.lower)/this.scale+o.from;
if(!isFinite(o.from)||isNaN(o.from)||!isFinite(o.to)||isNaN(o.to)||o.to-o.from>=tsb.upper-tsb.lower){
delete o.from;
delete o.to;
delete this.scale;
delete this.offset;
}else{
if(o.from<tsb.lower){
o.to+=tsb.lower-o.from;
o.from=tsb.lower;
}else{
if(o.to>tsb.upper){
o.from+=tsb.upper-o.to;
o.to=tsb.upper;
}
}
this.offset=o.from-tsb.lower;
}
this.scaler=lin.buildScaler(min,max,span,o);
tsb=this.scaler.bounds;
if(this.scale==1&&this.offset==0){
delete this.scale;
delete this.offset;
}
}
var ta=this.chart.theme.axis,_59f=0,_5a0=o.rotation%360,_5a1=o.font||(ta.majorTick&&ta.majorTick.font)||(ta.tick&&ta.tick.font),size=_5a1?g.normalizedLength(g.splitFontString(_5a1).size):0,cosr=Math.abs(Math.cos(_5a0*Math.PI/180)),sinr=Math.abs(Math.sin(_5a0*Math.PI/180));
if(_5a0<0){
_5a0+=360;
}
if(size){
if(this.vertical?_5a0!=0&&_5a0!=180:_5a0!=90&&_5a0!=270){
if(o.maxLabelSize){
_59f=o.maxLabelSize;
}else{
if(this.labels){
_59f=this._groupLabelWidth(this.labels,_5a1);
}else{
var _5a2=Math.ceil(Math.log(Math.max(Math.abs(tsb.from),Math.abs(tsb.to)))/Math.LN10),t=[];
if(tsb.from<0||tsb.to<0){
t.push("-");
}
t.push(dojo.string.rep("9",_5a2));
var _5a3=Math.floor(Math.log(tsb.to-tsb.from)/Math.LN10);
if(_5a3>0){
t.push(".");
t.push(dojo.string.rep("9",_5a3));
}
_59f=dojox.gfx._base._getTextBox(t.join(""),{font:_5a1}).w;
}
}
}else{
_59f=size;
}
switch(_5a0){
case 0:
case 90:
case 180:
case 270:
break;
default:
var gap1=Math.sqrt(_59f*_59f+size*size),gap2=this.vertical?size*cosr+_59f*sinr:_59f*cosr+size*sinr;
_59f=Math.min(gap1,gap2);
break;
}
}
this.scaler.minMinorStep=_59f+_596;
this.ticks=lin.buildTicks(this.scaler,o);
return this;
},getScaler:function(){
return this.scaler;
},getTicks:function(){
return this.ticks;
}});
})();
}
if(!dojo._hasResource["dojo.colors"]){
dojo._hasResource["dojo.colors"]=true;
dojo.provide("dojo.colors");
(function(){
var _5a4=function(m1,m2,h){
if(h<0){
++h;
}
if(h>1){
--h;
}
var h6=6*h;
if(h6<1){
return m1+(m2-m1)*h6;
}
if(2*h<1){
return m2;
}
if(3*h<2){
return m1+(m2-m1)*(2/3-h)*6;
}
return m1;
};
dojo.colorFromRgb=function(_5a5,obj){
var m=_5a5.toLowerCase().match(/^(rgba?|hsla?)\(([\s\.\-,%0-9]+)\)/);
if(m){
var c=m[2].split(/\s*,\s*/),l=c.length,t=m[1],a;
if((t=="rgb"&&l==3)||(t=="rgba"&&l==4)){
var r=c[0];
if(r.charAt(r.length-1)=="%"){
a=dojo.map(c,function(x){
return parseFloat(x)*2.56;
});
if(l==4){
a[3]=c[3];
}
return dojo.colorFromArray(a,obj);
}
return dojo.colorFromArray(c,obj);
}
if((t=="hsl"&&l==3)||(t=="hsla"&&l==4)){
var H=((parseFloat(c[0])%360)+360)%360/360,S=parseFloat(c[1])/100,L=parseFloat(c[2])/100,m2=L<=0.5?L*(S+1):L+S-L*S,m1=2*L-m2;
a=[_5a4(m1,m2,H+1/3)*256,_5a4(m1,m2,H)*256,_5a4(m1,m2,H-1/3)*256,1];
if(l==4){
a[3]=c[3];
}
return dojo.colorFromArray(a,obj);
}
}
return null;
};
var _5a6=function(c,low,high){
c=Number(c);
return isNaN(c)?high:c<low?low:c>high?high:c;
};
dojo.Color.prototype.sanitize=function(){
var t=this;
t.r=Math.round(_5a6(t.r,0,255));
t.g=Math.round(_5a6(t.g,0,255));
t.b=Math.round(_5a6(t.b,0,255));
t.a=_5a6(t.a,0,1);
return this;
};
})();
dojo.colors.makeGrey=function(g,a){
return dojo.colorFromArray([g,g,g,a]);
};
dojo.mixin(dojo.Color.named,{aliceblue:[240,248,255],antiquewhite:[250,235,215],aquamarine:[127,255,212],azure:[240,255,255],beige:[245,245,220],bisque:[255,228,196],blanchedalmond:[255,235,205],blueviolet:[138,43,226],brown:[165,42,42],burlywood:[222,184,135],cadetblue:[95,158,160],chartreuse:[127,255,0],chocolate:[210,105,30],coral:[255,127,80],cornflowerblue:[100,149,237],cornsilk:[255,248,220],crimson:[220,20,60],cyan:[0,255,255],darkblue:[0,0,139],darkcyan:[0,139,139],darkgoldenrod:[184,134,11],darkgray:[169,169,169],darkgreen:[0,100,0],darkgrey:[169,169,169],darkkhaki:[189,183,107],darkmagenta:[139,0,139],darkolivegreen:[85,107,47],darkorange:[255,140,0],darkorchid:[153,50,204],darkred:[139,0,0],darksalmon:[233,150,122],darkseagreen:[143,188,143],darkslateblue:[72,61,139],darkslategray:[47,79,79],darkslategrey:[47,79,79],darkturquoise:[0,206,209],darkviolet:[148,0,211],deeppink:[255,20,147],deepskyblue:[0,191,255],dimgray:[105,105,105],dimgrey:[105,105,105],dodgerblue:[30,144,255],firebrick:[178,34,34],floralwhite:[255,250,240],forestgreen:[34,139,34],gainsboro:[220,220,220],ghostwhite:[248,248,255],gold:[255,215,0],goldenrod:[218,165,32],greenyellow:[173,255,47],grey:[128,128,128],honeydew:[240,255,240],hotpink:[255,105,180],indianred:[205,92,92],indigo:[75,0,130],ivory:[255,255,240],khaki:[240,230,140],lavender:[230,230,250],lavenderblush:[255,240,245],lawngreen:[124,252,0],lemonchiffon:[255,250,205],lightblue:[173,216,230],lightcoral:[240,128,128],lightcyan:[224,255,255],lightgoldenrodyellow:[250,250,210],lightgray:[211,211,211],lightgreen:[144,238,144],lightgrey:[211,211,211],lightpink:[255,182,193],lightsalmon:[255,160,122],lightseagreen:[32,178,170],lightskyblue:[135,206,250],lightslategray:[119,136,153],lightslategrey:[119,136,153],lightsteelblue:[176,196,222],lightyellow:[255,255,224],limegreen:[50,205,50],linen:[250,240,230],magenta:[255,0,255],mediumaquamarine:[102,205,170],mediumblue:[0,0,205],mediumorchid:[186,85,211],mediumpurple:[147,112,219],mediumseagreen:[60,179,113],mediumslateblue:[123,104,238],mediumspringgreen:[0,250,154],mediumturquoise:[72,209,204],mediumvioletred:[199,21,133],midnightblue:[25,25,112],mintcream:[245,255,250],mistyrose:[255,228,225],moccasin:[255,228,181],navajowhite:[255,222,173],oldlace:[253,245,230],olivedrab:[107,142,35],orange:[255,165,0],orangered:[255,69,0],orchid:[218,112,214],palegoldenrod:[238,232,170],palegreen:[152,251,152],paleturquoise:[175,238,238],palevioletred:[219,112,147],papayawhip:[255,239,213],peachpuff:[255,218,185],peru:[205,133,63],pink:[255,192,203],plum:[221,160,221],powderblue:[176,224,230],rosybrown:[188,143,143],royalblue:[65,105,225],saddlebrown:[139,69,19],salmon:[250,128,114],sandybrown:[244,164,96],seagreen:[46,139,87],seashell:[255,245,238],sienna:[160,82,45],skyblue:[135,206,235],slateblue:[106,90,205],slategray:[112,128,144],slategrey:[112,128,144],snow:[255,250,250],springgreen:[0,255,127],steelblue:[70,130,180],tan:[210,180,140],thistle:[216,191,216],tomato:[255,99,71],transparent:[0,0,0,0],turquoise:[64,224,208],violet:[238,130,238],wheat:[245,222,179],whitesmoke:[245,245,245],yellowgreen:[154,205,50]});
}
if(!dojo._hasResource["dojox.charting.plot2d.common"]){
dojo._hasResource["dojox.charting.plot2d.common"]=true;
dojo.provide("dojox.charting.plot2d.common");
(function(){
var df=dojox.lang.functional,dc=dojox.charting.plot2d.common;
dojo.mixin(dojox.charting.plot2d.common,{makeStroke:function(_5a7){
if(!_5a7){
return _5a7;
}
if(typeof _5a7=="string"||_5a7 instanceof dojo.Color){
_5a7={color:_5a7};
}
return dojox.gfx.makeParameters(dojox.gfx.defaultStroke,_5a7);
},augmentColor:function(_5a8,_5a9){
var t=new dojo.Color(_5a8),c=new dojo.Color(_5a9);
c.a=t.a;
return c;
},augmentStroke:function(_5aa,_5ab){
var s=dc.makeStroke(_5aa);
if(s){
s.color=dc.augmentColor(s.color,_5ab);
}
return s;
},augmentFill:function(fill,_5ac){
var fc,c=new dojo.Color(_5ac);
if(typeof fill=="string"||fill instanceof dojo.Color){
return dc.augmentColor(fill,_5ac);
}
return fill;
},defaultStats:{hmin:Number.POSITIVE_INFINITY,hmax:Number.NEGATIVE_INFINITY,vmin:Number.POSITIVE_INFINITY,vmax:Number.NEGATIVE_INFINITY},collectSimpleStats:function(_5ad){
var _5ae=dojo.delegate(dc.defaultStats);
for(var i=0;i<_5ad.length;++i){
var run=_5ad[i];
for(var j=0;j<run.data.length;j++){
if(run.data[j]!==null){
if(typeof run.data[j]=="number"){
var _5af=_5ae.vmin,_5b0=_5ae.vmax;
if(!("ymin" in run)||!("ymax" in run)){
dojo.forEach(run.data,function(val,i){
if(val!==null){
var x=i+1,y=val;
if(isNaN(y)){
y=0;
}
_5ae.hmin=Math.min(_5ae.hmin,x);
_5ae.hmax=Math.max(_5ae.hmax,x);
_5ae.vmin=Math.min(_5ae.vmin,y);
_5ae.vmax=Math.max(_5ae.vmax,y);
}
});
}
if("ymin" in run){
_5ae.vmin=Math.min(_5af,run.ymin);
}
if("ymax" in run){
_5ae.vmax=Math.max(_5b0,run.ymax);
}
}else{
var _5b1=_5ae.hmin,_5b2=_5ae.hmax,_5af=_5ae.vmin,_5b0=_5ae.vmax;
if(!("xmin" in run)||!("xmax" in run)||!("ymin" in run)||!("ymax" in run)){
dojo.forEach(run.data,function(val,i){
if(val!==null){
var x="x" in val?val.x:i+1,y=val.y;
if(isNaN(x)){
x=0;
}
if(isNaN(y)){
y=0;
}
_5ae.hmin=Math.min(_5ae.hmin,x);
_5ae.hmax=Math.max(_5ae.hmax,x);
_5ae.vmin=Math.min(_5ae.vmin,y);
_5ae.vmax=Math.max(_5ae.vmax,y);
}
});
}
if("xmin" in run){
_5ae.hmin=Math.min(_5b1,run.xmin);
}
if("xmax" in run){
_5ae.hmax=Math.max(_5b2,run.xmax);
}
if("ymin" in run){
_5ae.vmin=Math.min(_5af,run.ymin);
}
if("ymax" in run){
_5ae.vmax=Math.max(_5b0,run.ymax);
}
}
break;
}
}
}
return _5ae;
},calculateBarSize:function(_5b3,opt,_5b4){
if(!_5b4){
_5b4=1;
}
var gap=opt.gap,size=(_5b3-2*gap)/_5b4;
if("minBarSize" in opt){
size=Math.max(size,opt.minBarSize);
}
if("maxBarSize" in opt){
size=Math.min(size,opt.maxBarSize);
}
size=Math.max(size,1);
gap=(_5b3-size*_5b4)/2;
return {size:size,gap:gap};
},collectStackedStats:function(_5b5){
var _5b6=dojo.clone(dc.defaultStats);
if(_5b5.length){
_5b6.hmin=Math.min(_5b6.hmin,1);
_5b6.hmax=df.foldl(_5b5,"seed, run -> Math.max(seed, run.data.length)",_5b6.hmax);
for(var i=0;i<_5b6.hmax;++i){
var v=_5b5[0].data[i];
if(isNaN(v)){
v=0;
}
_5b6.vmin=Math.min(_5b6.vmin,v);
for(var j=1;j<_5b5.length;++j){
var t=_5b5[j].data[i];
if(isNaN(t)){
t=0;
}
v+=t;
}
_5b6.vmax=Math.max(_5b6.vmax,v);
}
}
return _5b6;
},curve:function(a,_5b7){
var arr=a.slice(0);
if(_5b7=="x"){
arr[arr.length]=arr[0];
}
var p=dojo.map(arr,function(item,i){
if(i==0){
return "M"+item.x+","+item.y;
}
if(!isNaN(_5b7)){
var dx=item.x-arr[i-1].x,dy=arr[i-1].y;
return "C"+(item.x-(_5b7-1)*(dx/_5b7))+","+dy+" "+(item.x-(dx/_5b7))+","+item.y+" "+item.x+","+item.y;
}else{
if(_5b7=="X"||_5b7=="x"||_5b7=="S"){
var p0,p1=arr[i-1],p2=arr[i],p3;
var bz1x,bz1y,bz2x,bz2y;
var f=1/6;
if(i==1){
if(_5b7=="x"){
p0=arr[arr.length-2];
}else{
p0=p1;
}
f=1/3;
}else{
p0=arr[i-2];
}
if(i==(arr.length-1)){
if(_5b7=="x"){
p3=arr[1];
}else{
p3=p2;
}
f=1/3;
}else{
p3=arr[i+1];
}
var p1p2=Math.sqrt((p2.x-p1.x)*(p2.x-p1.x)+(p2.y-p1.y)*(p2.y-p1.y));
var p0p2=Math.sqrt((p2.x-p0.x)*(p2.x-p0.x)+(p2.y-p0.y)*(p2.y-p0.y));
var p1p3=Math.sqrt((p3.x-p1.x)*(p3.x-p1.x)+(p3.y-p1.y)*(p3.y-p1.y));
var _5b8=p0p2*f;
var _5b9=p1p3*f;
if(_5b8>p1p2/2&&_5b9>p1p2/2){
_5b8=p1p2/2;
_5b9=p1p2/2;
}else{
if(_5b8>p1p2/2){
_5b8=p1p2/2;
_5b9=p1p2/2*p1p3/p0p2;
}else{
if(_5b9>p1p2/2){
_5b9=p1p2/2;
_5b8=p1p2/2*p0p2/p1p3;
}
}
}
if(_5b7=="S"){
if(p0==p1){
_5b8=0;
}
if(p2==p3){
_5b9=0;
}
}
bz1x=p1.x+_5b8*(p2.x-p0.x)/p0p2;
bz1y=p1.y+_5b8*(p2.y-p0.y)/p0p2;
bz2x=p2.x-_5b9*(p3.x-p1.x)/p1p3;
bz2y=p2.y-_5b9*(p3.y-p1.y)/p1p3;
}
}
return "C"+(bz1x+","+bz1y+" "+bz2x+","+bz2y+" "+p2.x+","+p2.y);
});
return p.join(" ");
}});
})();
}
if(!dojo._hasResource["dojox.charting.scaler.primitive"]){
dojo._hasResource["dojox.charting.scaler.primitive"]=true;
dojo.provide("dojox.charting.scaler.primitive");
dojox.charting.scaler.primitive={buildScaler:function(min,max,span,_5ba){
if(min==max){
min-=0.5;
max+=0.5;
}
return {bounds:{lower:min,upper:max,from:min,to:max,scale:span/(max-min),span:span},scaler:dojox.charting.scaler.primitive};
},buildTicks:function(_5bb,_5bc){
return {major:[],minor:[],micro:[]};
},getTransformerFromModel:function(_5bd){
var _5be=_5bd.bounds.from,_5bf=_5bd.bounds.scale;
return function(x){
return (x-_5be)*_5bf;
};
},getTransformerFromPlot:function(_5c0){
var _5c1=_5c0.bounds.from,_5c2=_5c0.bounds.scale;
return function(x){
return x/_5c2+_5c1;
};
}};
}
if(!dojo._hasResource["dojox.charting.plot2d._PlotEvents"]){
dojo._hasResource["dojox.charting.plot2d._PlotEvents"]=true;
dojo.provide("dojox.charting.plot2d._PlotEvents");
dojo.declare("dojox.charting.plot2d._PlotEvents",null,{constructor:function(){
this._shapeEvents=[];
this._eventSeries={};
},destroy:function(){
this.resetEvents();
this.inherited(arguments);
},plotEvent:function(o){
},raiseEvent:function(o){
this.plotEvent(o);
var t=dojo.delegate(o);
t.originalEvent=o.type;
t.originalPlot=o.plot;
t.type="onindirect";
dojo.forEach(this.chart.stack,function(plot){
if(plot!==this&&plot.plotEvent){
t.plot=plot;
plot.plotEvent(t);
}
},this);
},connect:function(_5c3,_5c4){
this.dirty=true;
return dojo.connect(this,"plotEvent",_5c3,_5c4);
},events:function(){
var ls=this.plotEvent._listeners;
if(!ls||!ls.length){
return false;
}
for(var i in ls){
if(!(i in Array.prototype)){
return true;
}
}
return false;
},resetEvents:function(){
if(this._shapeEvents.length){
dojo.forEach(this._shapeEvents,function(item){
item.shape.disconnect(item.handle);
});
this._shapeEvents=[];
}
this.raiseEvent({type:"onplotreset",plot:this});
},_connectSingleEvent:function(o,_5c5){
this._shapeEvents.push({shape:o.eventMask,handle:o.eventMask.connect(_5c5,this,function(e){
o.type=_5c5;
o.event=e;
this.raiseEvent(o);
o.event=null;
})});
},_connectEvents:function(o){
o.chart=this.chart;
o.plot=this;
o.hAxis=this.hAxis||null;
o.vAxis=this.vAxis||null;
o.eventMask=o.eventMask||o.shape;
this._connectSingleEvent(o,"onmouseover");
this._connectSingleEvent(o,"onmouseout");
this._connectSingleEvent(o,"onclick");
},_reconnectEvents:function(_5c6){
var a=this._eventSeries[_5c6];
if(a){
dojo.forEach(a,this._connectEvents,this);
}
},fireEvent:function(_5c7,_5c8,_5c9,_5ca){
var s=this._eventSeries[_5c7];
if(s&&s.length&&_5c9<s.length){
var o=s[_5c9];
o.type=_5c8;
o.event=_5ca||null;
this.raiseEvent(o);
o.event=null;
}
}});
}
if(!dojo._hasResource["dojox.charting.plot2d.Base"]){
dojo._hasResource["dojox.charting.plot2d.Base"]=true;
dojo.provide("dojox.charting.plot2d.Base");
dojo.declare("dojox.charting.plot2d.Base",[dojox.charting.Element,dojox.charting.plot2d._PlotEvents],{constructor:function(_5cb,_5cc){
this.zoom=null,this.zoomQueue=[];
this.lastWindow={vscale:1,hscale:1,xoffset:0,yoffset:0};
},clear:function(){
this.series=[];
this._hAxis=null;
this._vAxis=null;
this.dirty=true;
return this;
},setAxis:function(axis){
if(axis){
this[axis.vertical?"_vAxis":"_hAxis"]=axis;
}
return this;
},addSeries:function(run){
this.series.push(run);
return this;
},getSeriesStats:function(){
return dojox.charting.plot2d.common.collectSimpleStats(this.series);
},calculateAxes:function(dim){
this.initializeScalers(dim,this.getSeriesStats());
return this;
},isDirty:function(){
return this.dirty||this._hAxis&&this._hAxis.dirty||this._vAxis&&this._vAxis.dirty;
},isDataDirty:function(){
return dojo.some(this.series,function(item){
return item.dirty;
});
},performZoom:function(dim,_5cd){
var vs=this._vAxis.scale||1,hs=this._hAxis.scale||1,_5ce=dim.height-_5cd.b,_5cf=this._hScaler.bounds,_5d0=(_5cf.from-_5cf.lower)*_5cf.scale,_5d1=this._vScaler.bounds,_5d2=(_5d1.from-_5d1.lower)*_5d1.scale;
rVScale=vs/this.lastWindow.vscale,rHScale=hs/this.lastWindow.hscale,rXOffset=(this.lastWindow.xoffset-_5d0)/((this.lastWindow.hscale==1)?hs:this.lastWindow.hscale),rYOffset=(_5d2-this.lastWindow.yoffset)/((this.lastWindow.vscale==1)?vs:this.lastWindow.vscale),shape=this.group,anim=dojox.gfx.fx.animateTransform(dojo.delegate({shape:shape,duration:1200,transform:[{name:"translate",start:[0,0],end:[_5cd.l*(1-rHScale),_5ce*(1-rVScale)]},{name:"scale",start:[1,1],end:[rHScale,rVScale]},{name:"original"},{name:"translate",start:[0,0],end:[rXOffset,rYOffset]}]},this.zoom));
dojo.mixin(this.lastWindow,{vscale:vs,hscale:hs,xoffset:_5d0,yoffset:_5d2});
this.zoomQueue.push(anim);
dojo.connect(anim,"onEnd",this,function(){
this.zoom=null;
this.zoomQueue.shift();
if(this.zoomQueue.length>0){
this.zoomQueue[0].play();
}
});
if(this.zoomQueue.length==1){
this.zoomQueue[0].play();
}
return this;
},render:function(dim,_5d3){
return this;
},getRequiredColors:function(){
return this.series.length;
},initializeScalers:function(dim,_5d4){
if(this._hAxis){
if(!this._hAxis.initialized()){
this._hAxis.calculate(_5d4.hmin,_5d4.hmax,dim.width);
}
this._hScaler=this._hAxis.getScaler();
}else{
this._hScaler=dojox.charting.scaler.primitive.buildScaler(_5d4.hmin,_5d4.hmax,dim.width);
}
if(this._vAxis){
if(!this._vAxis.initialized()){
this._vAxis.calculate(_5d4.vmin,_5d4.vmax,dim.height);
}
this._vScaler=this._vAxis.getScaler();
}else{
this._vScaler=dojox.charting.scaler.primitive.buildScaler(_5d4.vmin,_5d4.vmax,dim.height);
}
return this;
}});
}
if(!dojo._hasResource["dojox.lang.functional.reversed"]){
dojo._hasResource["dojox.lang.functional.reversed"]=true;
dojo.provide("dojox.lang.functional.reversed");
(function(){
var d=dojo,df=dojox.lang.functional;
d.mixin(df,{filterRev:function(a,f,o){
if(typeof a=="string"){
a=a.split("");
}
o=o||d.global;
f=df.lambda(f);
var t=[],v,i=a.length-1;
for(;i>=0;--i){
v=a[i];
if(f.call(o,v,i,a)){
t.push(v);
}
}
return t;
},forEachRev:function(a,f,o){
if(typeof a=="string"){
a=a.split("");
}
o=o||d.global;
f=df.lambda(f);
for(var i=a.length-1;i>=0;f.call(o,a[i],i,a),--i){
}
},mapRev:function(a,f,o){
if(typeof a=="string"){
a=a.split("");
}
o=o||d.global;
f=df.lambda(f);
var n=a.length,t=new Array(n),i=n-1,j=0;
for(;i>=0;t[j++]=f.call(o,a[i],i,a),--i){
}
return t;
},everyRev:function(a,f,o){
if(typeof a=="string"){
a=a.split("");
}
o=o||d.global;
f=df.lambda(f);
for(var i=a.length-1;i>=0;--i){
if(!f.call(o,a[i],i,a)){
return false;
}
}
return true;
},someRev:function(a,f,o){
if(typeof a=="string"){
a=a.split("");
}
o=o||d.global;
f=df.lambda(f);
for(var i=a.length-1;i>=0;--i){
if(f.call(o,a[i],i,a)){
return true;
}
}
return false;
}});
})();
}
if(!dojo._hasResource["dojox.gfx.fx"]){
dojo._hasResource["dojox.gfx.fx"]=true;
dojo.provide("dojox.gfx.fx");
(function(){
var d=dojo,g=dojox.gfx,m=g.matrix;
var _5d5=function(_5d6,end){
this.start=_5d6,this.end=end;
};
d.extend(_5d5,{getValue:function(r){
return (this.end-this.start)*r+this.start;
}});
var _5d7=function(_5d8,end,_5d9){
this.start=_5d8,this.end=end;
this.units=_5d9;
};
d.extend(_5d7,{getValue:function(r){
return (this.end-this.start)*r+this.start+this.units;
}});
var _5da=function(_5db,end){
this.start=_5db,this.end=end;
this.temp=new dojo.Color();
};
d.extend(_5da,{getValue:function(r){
return d.blendColors(this.start,this.end,r,this.temp);
}});
var _5dc=function(_5dd){
this.values=_5dd;
this.length=_5dd.length;
};
d.extend(_5dc,{getValue:function(r){
return this.values[Math.min(Math.floor(r*this.length),this.length-1)];
}});
var _5de=function(_5df,def){
this.values=_5df;
this.def=def?def:{};
};
d.extend(_5de,{getValue:function(r){
var ret=dojo.clone(this.def);
for(var i in this.values){
ret[i]=this.values[i].getValue(r);
}
return ret;
}});
var _5e0=function(_5e1,_5e2){
this.stack=_5e1;
this.original=_5e2;
};
d.extend(_5e0,{getValue:function(r){
var ret=[];
dojo.forEach(this.stack,function(t){
if(t instanceof m.Matrix2D){
ret.push(t);
return;
}
if(t.name=="original"&&this.original){
ret.push(this.original);
return;
}
if(!(t.name in m)){
return;
}
var f=m[t.name];
if(typeof f!="function"){
ret.push(f);
return;
}
var val=dojo.map(t.start,function(v,i){
return (t.end[i]-v)*r+v;
}),_5e3=f.apply(m,val);
if(_5e3 instanceof m.Matrix2D){
ret.push(_5e3);
}
},this);
return ret;
}});
var _5e4=new d.Color(0,0,0,0);
var _5e5=function(prop,obj,name,def){
if(prop.values){
return new _5dc(prop.values);
}
var _5e6,_5e7,end;
if(prop.start){
_5e7=g.normalizeColor(prop.start);
}else{
_5e7=_5e6=obj?(name?obj[name]:obj):def;
}
if(prop.end){
end=g.normalizeColor(prop.end);
}else{
if(!_5e6){
_5e6=obj?(name?obj[name]:obj):def;
}
end=_5e6;
}
return new _5da(_5e7,end);
};
var _5e8=function(prop,obj,name,def){
if(prop.values){
return new _5dc(prop.values);
}
var _5e9,_5ea,end;
if(prop.start){
_5ea=prop.start;
}else{
_5ea=_5e9=obj?obj[name]:def;
}
if(prop.end){
end=prop.end;
}else{
if(typeof _5e9!="number"){
_5e9=obj?obj[name]:def;
}
end=_5e9;
}
return new _5d5(_5ea,end);
};
g.fx.animateStroke=function(args){
if(!args.easing){
args.easing=d._defaultEasing;
}
var anim=new d.Animation(args),_5eb=args.shape,_5ec;
d.connect(anim,"beforeBegin",anim,function(){
_5ec=_5eb.getStroke();
var prop=args.color,_5ed={},_5ee,_5ef,end;
if(prop){
_5ed.color=_5e5(prop,_5ec,"color",_5e4);
}
prop=args.style;
if(prop&&prop.values){
_5ed.style=new _5dc(prop.values);
}
prop=args.width;
if(prop){
_5ed.width=_5e8(prop,_5ec,"width",1);
}
prop=args.cap;
if(prop&&prop.values){
_5ed.cap=new _5dc(prop.values);
}
prop=args.join;
if(prop){
if(prop.values){
_5ed.join=new _5dc(prop.values);
}else{
_5ef=prop.start?prop.start:(_5ec&&_5ec.join||0);
end=prop.end?prop.end:(_5ec&&_5ec.join||0);
if(typeof _5ef=="number"&&typeof end=="number"){
_5ed.join=new _5d5(_5ef,end);
}
}
}
this.curve=new _5de(_5ed,_5ec);
});
d.connect(anim,"onAnimate",_5eb,"setStroke");
return anim;
};
g.fx.animateFill=function(args){
if(!args.easing){
args.easing=d._defaultEasing;
}
var anim=new d.Animation(args),_5f0=args.shape,fill;
d.connect(anim,"beforeBegin",anim,function(){
fill=_5f0.getFill();
var prop=args.color,_5f1={};
if(prop){
this.curve=_5e5(prop,fill,"",_5e4);
}
});
d.connect(anim,"onAnimate",_5f0,"setFill");
return anim;
};
g.fx.animateFont=function(args){
if(!args.easing){
args.easing=d._defaultEasing;
}
var anim=new d.Animation(args),_5f2=args.shape,font;
d.connect(anim,"beforeBegin",anim,function(){
font=_5f2.getFont();
var prop=args.style,_5f3={},_5f4,_5f5,end;
if(prop&&prop.values){
_5f3.style=new _5dc(prop.values);
}
prop=args.variant;
if(prop&&prop.values){
_5f3.variant=new _5dc(prop.values);
}
prop=args.weight;
if(prop&&prop.values){
_5f3.weight=new _5dc(prop.values);
}
prop=args.family;
if(prop&&prop.values){
_5f3.family=new _5dc(prop.values);
}
prop=args.size;
if(prop&&prop.units){
_5f5=parseFloat(prop.start?prop.start:(_5f2.font&&_5f2.font.size||"0"));
end=parseFloat(prop.end?prop.end:(_5f2.font&&_5f2.font.size||"0"));
_5f3.size=new _5d7(_5f5,end,prop.units);
}
this.curve=new _5de(_5f3,font);
});
d.connect(anim,"onAnimate",_5f2,"setFont");
return anim;
};
g.fx.animateTransform=function(args){
if(!args.easing){
args.easing=d._defaultEasing;
}
var anim=new d.Animation(args),_5f6=args.shape,_5f7;
d.connect(anim,"beforeBegin",anim,function(){
_5f7=_5f6.getTransform();
this.curve=new _5e0(args.transform,_5f7);
});
d.connect(anim,"onAnimate",_5f6,"setTransform");
return anim;
};
})();
}
if(!dojo._hasResource["dojox.charting.plot2d.Default"]){
dojo._hasResource["dojox.charting.plot2d.Default"]=true;
dojo.provide("dojox.charting.plot2d.Default");
(function(){
var df=dojox.lang.functional,du=dojox.lang.utils,dc=dojox.charting.plot2d.common,_5f8=df.lambda("item.purgeGroup()");
var _5f9=1200;
dojo.declare("dojox.charting.plot2d.Default",dojox.charting.plot2d.Base,{defaultParams:{hAxis:"x",vAxis:"y",lines:true,areas:false,markers:false,tension:"",animate:false},optionalParams:{stroke:{},outline:{},shadow:{},fill:{},font:"",fontColor:"",markerStroke:{},markerOutline:{},markerShadow:{},markerFill:{},markerFont:"",markerFontColor:""},constructor:function(_5fa,_5fb){
this.opt=dojo.clone(this.defaultParams);
du.updateWithObject(this.opt,_5fb);
this.series=[];
this.hAxis=this.opt.hAxis;
this.vAxis=this.opt.vAxis;
this.animate=this.opt.animate;
},render:function(dim,_5fc){
if(this.zoom&&!this.isDataDirty()){
return this.performZoom(dim,_5fc);
}
this.resetEvents();
this.dirty=this.isDirty();
if(this.dirty){
dojo.forEach(this.series,_5f8);
this._eventSeries={};
this.cleanGroup();
this.group.setTransform(null);
var s=this.group;
df.forEachRev(this.series,function(item){
item.cleanGroup(s);
});
}
var t=this.chart.theme,_5fd,_5fe,_5ff,_600=this.events();
for(var i=this.series.length-1;i>=0;--i){
var run=this.series[i];
if(!this.dirty&&!run.dirty){
t.skip();
this._reconnectEvents(run.name);
continue;
}
run.cleanGroup();
if(!run.data.length){
run.dirty=false;
t.skip();
continue;
}
var _601=t.next(this.opt.areas?"area":"line",[this.opt,run],true),s=run.group,_602=[],_603=[],rseg=null,_604,ht=this._hScaler.scaler.getTransformerFromModel(this._hScaler),vt=this._vScaler.scaler.getTransformerFromModel(this._vScaler),_605=this._eventSeries[run.name]=new Array(run.data.length);
for(var j=0;j<run.data.length;j++){
if(run.data[j]!=null){
if(!rseg){
rseg=[];
_603.push(j);
_602.push(rseg);
}
rseg.push(run.data[j]);
}else{
rseg=null;
}
}
for(var seg=0;seg<_602.length;seg++){
if(typeof _602[seg][0]=="number"){
_604=dojo.map(_602[seg],function(v,i){
return {x:ht(i+_603[seg]+1)+_5fc.l,y:dim.height-_5fc.b-vt(v)};
},this);
}else{
_604=dojo.map(_602[seg],function(v,i){
return {x:ht(v.x)+_5fc.l,y:dim.height-_5fc.b-vt(v.y)};
},this);
}
var _606=this.opt.tension?dc.curve(_604,this.opt.tension):"";
if(this.opt.areas&&_604.length>1){
var fill=_601.series.fill;
var _607=dojo.clone(_604);
if(this.opt.tension){
var _608="L"+_607[_607.length-1].x+","+(dim.height-_5fc.b)+" L"+_607[0].x+","+(dim.height-_5fc.b)+" L"+_607[0].x+","+_607[0].y;
run.dyn.fill=s.createPath(_606+" "+_608).setFill(fill).getFill();
}else{
_607.push({x:_604[_604.length-1].x,y:dim.height-_5fc.b});
_607.push({x:_604[0].x,y:dim.height-_5fc.b});
_607.push(_604[0]);
run.dyn.fill=s.createPolyline(_607).setFill(fill).getFill();
}
}
if(this.opt.lines||this.opt.markers){
_5fd=_601.series.stroke;
if(_601.series.outline){
_5fe=run.dyn.outline=dc.makeStroke(_601.series.outline);
_5fe.width=2*_5fe.width+_5fd.width;
}
}
if(this.opt.markers){
run.dyn.marker=_601.symbol;
}
var _609=null,_60a=null,_60b=null;
if(_5fd&&_601.series.shadow&&_604.length>1){
var _60c=_601.series.shadow,_60d=dojo.map(_604,function(c){
return {x:c.x+_60c.dx,y:c.y+_60c.dy};
});
if(this.opt.lines){
if(this.opt.tension){
run.dyn.shadow=s.createPath(dc.curve(_60d,this.opt.tension)).setStroke(_60c).getStroke();
}else{
run.dyn.shadow=s.createPolyline(_60d).setStroke(_60c).getStroke();
}
}
if(this.opt.markers&&_601.marker.shadow){
_60c=_601.marker.shadow;
_60b=dojo.map(_60d,function(c){
return s.createPath("M"+c.x+" "+c.y+" "+_601.symbol).setStroke(_60c).setFill(_60c.color);
},this);
}
}
if(this.opt.lines&&_604.length>1){
if(_5fe){
if(this.opt.tension){
run.dyn.outline=s.createPath(_606).setStroke(_5fe).getStroke();
}else{
run.dyn.outline=s.createPolyline(_604).setStroke(_5fe).getStroke();
}
}
if(this.opt.tension){
run.dyn.stroke=s.createPath(_606).setStroke(_5fd).getStroke();
}else{
run.dyn.stroke=s.createPolyline(_604).setStroke(_5fd).getStroke();
}
}
if(this.opt.markers){
_609=new Array(_604.length);
_60a=new Array(_604.length);
_5fe=null;
if(_601.marker.outline){
_5fe=dc.makeStroke(_601.marker.outline);
_5fe.width=2*_5fe.width+(_601.marker.stroke?_601.marker.stroke.width:0);
}
dojo.forEach(_604,function(c,i){
var path="M"+c.x+" "+c.y+" "+_601.symbol;
if(_5fe){
_60a[i]=s.createPath(path).setStroke(_5fe);
}
_609[i]=s.createPath(path).setStroke(_601.marker.stroke).setFill(_601.marker.fill);
},this);
if(_600){
dojo.forEach(_609,function(s,i){
var o={element:"marker",index:i+_603[seg],run:run,shape:s,outline:_60a[i]||null,shadow:_60b&&_60b[i]||null,cx:_604[i].x,cy:_604[i].y};
if(typeof _602[seg][0]=="number"){
o.x=i+_603[seg]+1;
o.y=_602[seg][i];
}else{
o.x=_602[seg][i].x;
o.y=_602[seg][i].y;
}
this._connectEvents(o);
_605[i+_603[seg]]=o;
},this);
}else{
delete this._eventSeries[run.name];
}
}
}
run.dirty=false;
}
if(this.animate){
var _60e=this.group;
dojox.gfx.fx.animateTransform(dojo.delegate({shape:_60e,duration:_5f9,transform:[{name:"translate",start:[0,dim.height-_5fc.b],end:[0,0]},{name:"scale",start:[1,0],end:[1,1]},{name:"original"}]},this.animate)).play();
}
this.dirty=false;
return this;
}});
})();
}
if(!dojo._hasResource["dojox.charting.plot2d.Areas"]){
dojo._hasResource["dojox.charting.plot2d.Areas"]=true;
dojo.provide("dojox.charting.plot2d.Areas");
dojo.declare("dojox.charting.plot2d.Areas",dojox.charting.plot2d.Default,{constructor:function(){
this.opt.lines=true;
this.opt.areas=true;
}});
}
if(!dojo._hasResource["dojox.charting.plot2d.Lines"]){
dojo._hasResource["dojox.charting.plot2d.Lines"]=true;
dojo.provide("dojox.charting.plot2d.Lines");
dojo.declare("dojox.charting.plot2d.Lines",dojox.charting.plot2d.Default,{constructor:function(){
this.opt.lines=true;
}});
}
if(!dojo._hasResource["dojo.fx.easing"]){
dojo._hasResource["dojo.fx.easing"]=true;
dojo.provide("dojo.fx.easing");
dojo.fx.easing={linear:function(n){
return n;
},quadIn:function(n){
return Math.pow(n,2);
},quadOut:function(n){
return n*(n-2)*-1;
},quadInOut:function(n){
n=n*2;
if(n<1){
return Math.pow(n,2)/2;
}
return -1*((--n)*(n-2)-1)/2;
},cubicIn:function(n){
return Math.pow(n,3);
},cubicOut:function(n){
return Math.pow(n-1,3)+1;
},cubicInOut:function(n){
n=n*2;
if(n<1){
return Math.pow(n,3)/2;
}
n-=2;
return (Math.pow(n,3)+2)/2;
},quartIn:function(n){
return Math.pow(n,4);
},quartOut:function(n){
return -1*(Math.pow(n-1,4)-1);
},quartInOut:function(n){
n=n*2;
if(n<1){
return Math.pow(n,4)/2;
}
n-=2;
return -1/2*(Math.pow(n,4)-2);
},quintIn:function(n){
return Math.pow(n,5);
},quintOut:function(n){
return Math.pow(n-1,5)+1;
},quintInOut:function(n){
n=n*2;
if(n<1){
return Math.pow(n,5)/2;
}
n-=2;
return (Math.pow(n,5)+2)/2;
},sineIn:function(n){
return -1*Math.cos(n*(Math.PI/2))+1;
},sineOut:function(n){
return Math.sin(n*(Math.PI/2));
},sineInOut:function(n){
return -1*(Math.cos(Math.PI*n)-1)/2;
},expoIn:function(n){
return (n==0)?0:Math.pow(2,10*(n-1));
},expoOut:function(n){
return (n==1)?1:(-1*Math.pow(2,-10*n)+1);
},expoInOut:function(n){
if(n==0){
return 0;
}
if(n==1){
return 1;
}
n=n*2;
if(n<1){
return Math.pow(2,10*(n-1))/2;
}
--n;
return (-1*Math.pow(2,-10*n)+2)/2;
},circIn:function(n){
return -1*(Math.sqrt(1-Math.pow(n,2))-1);
},circOut:function(n){
n=n-1;
return Math.sqrt(1-Math.pow(n,2));
},circInOut:function(n){
n=n*2;
if(n<1){
return -1/2*(Math.sqrt(1-Math.pow(n,2))-1);
}
n-=2;
return 1/2*(Math.sqrt(1-Math.pow(n,2))+1);
},backIn:function(n){
var s=1.70158;
return Math.pow(n,2)*((s+1)*n-s);
},backOut:function(n){
n=n-1;
var s=1.70158;
return Math.pow(n,2)*((s+1)*n+s)+1;
},backInOut:function(n){
var s=1.70158*1.525;
n=n*2;
if(n<1){
return (Math.pow(n,2)*((s+1)*n-s))/2;
}
n-=2;
return (Math.pow(n,2)*((s+1)*n+s)+2)/2;
},elasticIn:function(n){
if(n==0||n==1){
return n;
}
var p=0.3;
var s=p/4;
n=n-1;
return -1*Math.pow(2,10*n)*Math.sin((n-s)*(2*Math.PI)/p);
},elasticOut:function(n){
if(n==0||n==1){
return n;
}
var p=0.3;
var s=p/4;
return Math.pow(2,-10*n)*Math.sin((n-s)*(2*Math.PI)/p)+1;
},elasticInOut:function(n){
if(n==0){
return 0;
}
n=n*2;
if(n==2){
return 1;
}
var p=0.3*1.5;
var s=p/4;
if(n<1){
n-=1;
return -0.5*(Math.pow(2,10*n)*Math.sin((n-s)*(2*Math.PI)/p));
}
n-=1;
return 0.5*(Math.pow(2,-10*n)*Math.sin((n-s)*(2*Math.PI)/p))+1;
},bounceIn:function(n){
return (1-dojo.fx.easing.bounceOut(1-n));
},bounceOut:function(n){
var s=7.5625;
var p=2.75;
var l;
if(n<(1/p)){
l=s*Math.pow(n,2);
}else{
if(n<(2/p)){
n-=(1.5/p);
l=s*Math.pow(n,2)+0.75;
}else{
if(n<(2.5/p)){
n-=(2.25/p);
l=s*Math.pow(n,2)+0.9375;
}else{
n-=(2.625/p);
l=s*Math.pow(n,2)+0.984375;
}
}
}
return l;
},bounceInOut:function(n){
if(n<0.5){
return dojo.fx.easing.bounceIn(n*2)/2;
}
return (dojo.fx.easing.bounceOut(n*2-1)/2)+0.5;
}};
}
if(!dojo._hasResource["dojox.charting.action2d.Base"]){
dojo._hasResource["dojox.charting.action2d.Base"]=true;
dojo.provide("dojox.charting.action2d.Base");
(function(){
var _60f=400,_610=dojo.fx.easing.backOut,df=dojox.lang.functional;
dojo.declare("dojox.charting.action2d.Base",null,{overOutEvents:{onmouseover:1,onmouseout:1},constructor:function(_611,plot,_612){
this.chart=_611;
this.plot=plot||"default";
this.anim={};
if(!_612){
_612={};
}
this.duration=_612.duration?_612.duration:_60f;
this.easing=_612.easing?_612.easing:_610;
},connect:function(){
this.handle=this.chart.connectToPlot(this.plot,this,"process");
},disconnect:function(){
if(this.handle){
dojo.disconnect(this.handle);
this.handle=null;
}
},reset:function(){
},destroy:function(){
this.disconnect();
df.forIn(this.anim,function(o){
df.forIn(o,function(anim){
anim.action.stop(true);
});
});
this.anim={};
}});
})();
}
if(!dojo._hasResource["dojox.color._base"]){
dojo._hasResource["dojox.color._base"]=true;
dojo.provide("dojox.color._base");
dojox.color.Color=dojo.Color;
dojox.color.blend=dojo.blendColors;
dojox.color.fromRgb=dojo.colorFromRgb;
dojox.color.fromHex=dojo.colorFromHex;
dojox.color.fromArray=dojo.colorFromArray;
dojox.color.fromString=dojo.colorFromString;
dojox.color.greyscale=dojo.colors.makeGrey;
dojo.mixin(dojox.color,{fromCmy:function(cyan,_613,_614){
if(dojo.isArray(cyan)){
_613=cyan[1],_614=cyan[2],cyan=cyan[0];
}else{
if(dojo.isObject(cyan)){
_613=cyan.m,_614=cyan.y,cyan=cyan.c;
}
}
cyan/=100,_613/=100,_614/=100;
var r=1-cyan,g=1-_613,b=1-_614;
return new dojox.color.Color({r:Math.round(r*255),g:Math.round(g*255),b:Math.round(b*255)});
},fromCmyk:function(cyan,_615,_616,_617){
if(dojo.isArray(cyan)){
_615=cyan[1],_616=cyan[2],_617=cyan[3],cyan=cyan[0];
}else{
if(dojo.isObject(cyan)){
_615=cyan.m,_616=cyan.y,_617=cyan.b,cyan=cyan.c;
}
}
cyan/=100,_615/=100,_616/=100,_617/=100;
var r,g,b;
r=1-Math.min(1,cyan*(1-_617)+_617);
g=1-Math.min(1,_615*(1-_617)+_617);
b=1-Math.min(1,_616*(1-_617)+_617);
return new dojox.color.Color({r:Math.round(r*255),g:Math.round(g*255),b:Math.round(b*255)});
},fromHsl:function(hue,_618,_619){
if(dojo.isArray(hue)){
_618=hue[1],_619=hue[2],hue=hue[0];
}else{
if(dojo.isObject(hue)){
_618=hue.s,_619=hue.l,hue=hue.h;
}
}
_618/=100;
_619/=100;
while(hue<0){
hue+=360;
}
while(hue>=360){
hue-=360;
}
var r,g,b;
if(hue<120){
r=(120-hue)/60,g=hue/60,b=0;
}else{
if(hue<240){
r=0,g=(240-hue)/60,b=(hue-120)/60;
}else{
r=(hue-240)/60,g=0,b=(360-hue)/60;
}
}
r=2*_618*Math.min(r,1)+(1-_618);
g=2*_618*Math.min(g,1)+(1-_618);
b=2*_618*Math.min(b,1)+(1-_618);
if(_619<0.5){
r*=_619,g*=_619,b*=_619;
}else{
r=(1-_619)*r+2*_619-1;
g=(1-_619)*g+2*_619-1;
b=(1-_619)*b+2*_619-1;
}
return new dojox.color.Color({r:Math.round(r*255),g:Math.round(g*255),b:Math.round(b*255)});
},fromHsv:function(hue,_61a,_61b){
if(dojo.isArray(hue)){
_61a=hue[1],_61b=hue[2],hue=hue[0];
}else{
if(dojo.isObject(hue)){
_61a=hue.s,_61b=hue.v,hue=hue.h;
}
}
if(hue==360){
hue=0;
}
_61a/=100;
_61b/=100;
var r,g,b;
if(_61a==0){
r=_61b,b=_61b,g=_61b;
}else{
var _61c=hue/60,i=Math.floor(_61c),f=_61c-i;
var p=_61b*(1-_61a);
var q=_61b*(1-(_61a*f));
var t=_61b*(1-(_61a*(1-f)));
switch(i){
case 0:
r=_61b,g=t,b=p;
break;
case 1:
r=q,g=_61b,b=p;
break;
case 2:
r=p,g=_61b,b=t;
break;
case 3:
r=p,g=q,b=_61b;
break;
case 4:
r=t,g=p,b=_61b;
break;
case 5:
r=_61b,g=p,b=q;
break;
}
}
return new dojox.color.Color({r:Math.round(r*255),g:Math.round(g*255),b:Math.round(b*255)});
}});
dojo.extend(dojox.color.Color,{toCmy:function(){
var cyan=1-(this.r/255),_61d=1-(this.g/255),_61e=1-(this.b/255);
return {c:Math.round(cyan*100),m:Math.round(_61d*100),y:Math.round(_61e*100)};
},toCmyk:function(){
var cyan,_61f,_620,_621;
var r=this.r/255,g=this.g/255,b=this.b/255;
_621=Math.min(1-r,1-g,1-b);
cyan=(1-r-_621)/(1-_621);
_61f=(1-g-_621)/(1-_621);
_620=(1-b-_621)/(1-_621);
return {c:Math.round(cyan*100),m:Math.round(_61f*100),y:Math.round(_620*100),b:Math.round(_621*100)};
},toHsl:function(){
var r=this.r/255,g=this.g/255,b=this.b/255;
var min=Math.min(r,b,g),max=Math.max(r,g,b);
var _622=max-min;
var h=0,s=0,l=(min+max)/2;
if(l>0&&l<1){
s=_622/((l<0.5)?(2*l):(2-2*l));
}
if(_622>0){
if(max==r&&max!=g){
h+=(g-b)/_622;
}
if(max==g&&max!=b){
h+=(2+(b-r)/_622);
}
if(max==b&&max!=r){
h+=(4+(r-g)/_622);
}
h*=60;
}
return {h:h,s:Math.round(s*100),l:Math.round(l*100)};
},toHsv:function(){
var r=this.r/255,g=this.g/255,b=this.b/255;
var min=Math.min(r,b,g),max=Math.max(r,g,b);
var _623=max-min;
var h=null,s=(max==0)?0:(_623/max);
if(s==0){
h=0;
}else{
if(r==max){
h=60*(g-b)/_623;
}else{
if(g==max){
h=120+60*(b-r)/_623;
}else{
h=240+60*(r-g)/_623;
}
}
if(h<0){
h+=360;
}
}
return {h:h,s:Math.round(s*100),v:Math.round(max*100)};
}});
}
if(!dojo._hasResource["dojox.color"]){
dojo._hasResource["dojox.color"]=true;
dojo.provide("dojox.color");
}
if(!dojo._hasResource["insight.charting.action2d.StrokeHighlight"]){
dojo._hasResource["insight.charting.action2d.StrokeHighlight"]=true;
dojo.provide("insight.charting.action2d.StrokeHighlight");
(function(){
var _624=100,_625=75,_626=50,c=dojox.color,cc=function(_627){
return function(){
return _627;
};
},hl=function(_628){
var a=new c.Color(_628),x=a.toHsl();
if(x.s==0){
x.l=x.l<50?100:0;
}else{
x.s=_624;
if(x.l<_626){
x.l=_625;
}else{
if(x.l>_625){
x.l=_626;
}else{
x.l=x.l-_626>_625-x.l?_626:_625;
}
}
}
return c.fromHsl(x);
};
dojo.declare("insight.charting.action2d.StrokeHighlight",dojox.charting.action2d.Base,{defaultParams:{duration:400,easing:dojo.fx.easing.backOut},optionalParams:{highlight:"red"},constructor:function(_629,plot,_62a){
var a=_62a&&_62a.highlight;
this.colorFun=a?(dojo.isFunction(a)?a:cc(a)):hl;
this.connect();
},process:function(o){
if(!o.shape||!(o.type in this.overOutEvents||o.originalEvent in this.overOutEvents)){
return;
}
dojo.forEach(o.plot.series,function(_62b){
var i=o.plot._eventSeries[_62b.name]&&o.plot._eventSeries[_62b.name][o.index];
if(i){
this._process(dojo.delegate(o,{run:i.run,shape:i.shape}));
}
},this);
},_process:function(o){
var _62c=o.run.name,_62d=o.index,anim,_62e,_62f;
if(_62c in this.anim){
anim=this.anim[_62c][_62d];
}else{
this.anim[_62c]={};
}
if(anim){
anim.action.stop(true);
}else{
var _630=o.shape.getStroke().color;
if(!_630||!(_630 instanceof dojo.Color)){
return;
}
this.anim[_62c][_62d]=anim={start:_630,end:this.colorFun(_630)};
}
var _631=anim.start,end=anim.end;
if(o.type=="onmouseout"||o.originalEvent=="onmouseout"){
var t=_631;
_631=end;
end=t;
}
anim.action=dojox.gfx.fx.animateStroke({shape:o.shape,duration:this.duration,easing:this.easing,color:{start:_631,end:end}});
if(o.type=="onmouseout"||o.originalEvent=="onmouseout"){
var _632=dojo.connect(anim.action,"onEnd",this,function(){
dojo.disconnect(_632);
if(this.anim[_62c]){
delete this.anim[_62c][_62d];
}
});
}
anim.action.play();
}});
})();
}
if(!dojo._hasResource["insight.charting.axis2d.Fixed"]){
dojo._hasResource["insight.charting.axis2d.Fixed"]=true;
dojo.provide("insight.charting.axis2d.Fixed");
(function(){
var dc=dojox.charting,du=dojox.lang.utils,g=dojox.gfx,lin=dc.scaler.linear,_633=4,_634=45;
dojo.declare("insight.charting.axis2d.Fixed",dojox.charting.axis2d.Invisible,{defaultParams:{vertical:false,natural:false,leftBottom:true,includeZero:false,fixed:true,rotation:0,labels:[],htmlLabels:true},optionalParams:{min:0,max:1,from:0,to:1,labelFunc:null,maxLabelSize:0,stroke:{},tick:{},font:"",fontColor:""},constructor:function(_635,_636){
this.opt=dojo.delegate(this.defaultParams,_636);
du.updateWithPattern(this.opt,_636,this.optionalParams);
},getOffsets:function(){
var s=this.scaler,_637={l:0,r:0,t:0,b:0};
if(!s){
return _637;
}
var o=this.opt,_638=0,a,b,c,d,gl=dc.scaler.common.getNumericLabel,_639=0,ma=s.major,mi=s.minor,ta=this.chart.theme.axis,_63a=o.font||(ta.tick&&ta.tick.font),_63b=this.chart.theme.getTick("major",o),size=_63a?g.normalizedLength(g.splitFontString(_63a).size):0,_63c=o.rotation%360,_63d=o.leftBottom,cosr=Math.abs(Math.cos(_63c*Math.PI/180)),sinr=Math.abs(Math.sin(_63c*Math.PI/180));
if(_63c<0){
_63c+=360;
}
if(size){
if(o.maxLabelSize){
_638=o.maxLabelSize;
}else{
_638=this._groupLabelWidth(this.labels,_63a);
}
if(this.vertical){
var side=_63d?"l":"r";
switch(_63c){
case 0:
case 180:
_637[side]=_638;
_637.t=_637.b=size/2;
break;
case 90:
case 270:
_637[side]=size;
_637.t=_637.b=_638/2;
break;
default:
if(_63c<=_634||(180<_63c&&_63c<=(180+_634))){
_637[side]=size*sinr/2+_638*cosr;
_637[_63d?"t":"b"]=size*cosr/2+_638*sinr;
_637[_63d?"b":"t"]=size*cosr/2;
}else{
if(_63c>(360-_634)||(180>_63c&&_63c>(180-_634))){
_637[side]=size*sinr/2+_638*cosr;
_637[_63d?"b":"t"]=size*cosr/2+_638*sinr;
_637[_63d?"t":"b"]=size*cosr/2;
}else{
if(_63c<90||(180<_63c&&_63c<270)){
_637[side]=size*sinr+_638*cosr;
_637[_63d?"t":"b"]=size*cosr+_638*sinr;
}else{
_637[side]=size*sinr+_638*cosr;
_637[_63d?"b":"t"]=size*cosr+_638*sinr;
}
}
}
break;
}
_637[side]+=_633+_63b.length;
}else{
var side=_63d?"b":"t";
switch(_63c){
case 0:
case 180:
_637[side]=size;
_637.l=_637.r=_638/2;
break;
case 90:
case 270:
_637[side]=_638;
_637.l=_637.r=size/2;
break;
default:
if((90-_634)<=_63c&&_63c<=90||(270-_634)<=_63c&&_63c<=270){
_637[side]=size*sinr/2+_638*cosr;
_637[_63d?"r":"l"]=size*cosr/2+_638*sinr;
_637[_63d?"l":"r"]=size*cosr/2;
}else{
if(90<=_63c&&_63c<=(90+_634)||270<=_63c&&_63c<=(270+_634)){
_637[side]=size*sinr/2+_638*cosr;
_637[_63d?"l":"r"]=size*cosr/2+_638*sinr;
_637[_63d?"r":"l"]=size*cosr/2;
}else{
if(_63c<_634||(180<_63c&&_63c<(180-_634))){
_637[side]=size*sinr+_638*cosr;
_637[_63d?"r":"l"]=size*cosr+_638*sinr;
}else{
_637[side]=size*sinr+_638*cosr;
_637[_63d?"l":"r"]=size*cosr+_638*sinr;
}
}
}
break;
}
_637[side]+=_633+_63b.length;
}
}
if(_638){
this._cachedLabelWidth=_638;
}
return _637;
},render:function(dim,_63e){
if(!this.dirty){
return this;
}
var o=this.opt,ta=this.chart.theme.axis,_63f=o.leftBottom,_640=o.rotation%360,_641,stop,_642,_643,_644,_645,_646,_647=o.font||(ta.majorTick&&ta.majorTick.font)||(ta.tick&&ta.tick.font),_648=o.fontColor||(ta.majorTick&&ta.majorTick.fontColor)||(ta.tick&&ta.tick.fontColor)||"black",_649=this.chart.theme.getTick("major",o),_64a=_649.length,_64b="stroke" in o?o.stroke:ta.stroke,size=_647?g.normalizedLength(g.splitFontString(_647).size):0;
if(_640<0){
_640+=360;
}
if(this.vertical){
_641={y:dim.height-_63e.b};
stop={y:_63e.t};
_642={x:0,y:-1};
_645={x:0,y:0};
_643={x:1,y:0};
_644={x:_633,y:0};
switch(_640){
case 0:
_646="end";
_645.y=size*0.4;
break;
case 90:
_646="middle";
_645.x=-size;
break;
case 180:
_646="start";
_645.y=-size*0.4;
break;
case 270:
_646="middle";
break;
default:
if(_640<_634){
_646="end";
_645.y=size*0.4;
}else{
if(_640<90){
_646="end";
_645.y=size*0.4;
}else{
if(_640<(180-_634)){
_646="start";
}else{
if(_640<(180+_634)){
_646="start";
_645.y=-size*0.4;
}else{
if(_640<270){
_646="start";
_645.x=_63f?0:size*0.4;
}else{
if(_640<(360-_634)){
_646="end";
_645.x=_63f?0:size*0.4;
}else{
_646="end";
_645.y=size*0.4;
}
}
}
}
}
}
}
if(_63f){
_641.x=stop.x=_63e.l;
_643.x=-1;
_644.x=-_644.x;
}else{
_641.x=stop.x=dim.width-_63e.r;
switch(_646){
case "start":
_646="end";
break;
case "end":
_646="start";
break;
case "middle":
_645.x+=size;
break;
}
}
}else{
_641={x:_63e.l};
stop={x:dim.width-_63e.r};
_642={x:1,y:0};
_645={x:0,y:0};
_643={x:0,y:1};
_644={x:0,y:_633};
switch(_640){
case 0:
_646="middle";
_645.y=size;
break;
case 90:
_646="start";
_645.x=-size*0.4;
break;
case 180:
_646="middle";
break;
case 270:
_646="end";
_645.x=size*0.4;
break;
default:
if(_640<(90-_634)){
_646="start";
_645.y=_63f?size:0;
}else{
if(_640<(90+_634)){
_646="start";
_645.x=-size*0.4;
}else{
if(_640<180){
_646="start";
_645.y=_63f?0:-size;
}else{
if(_640<(270-_634)){
_646="end";
_645.y=_63f?0:-size;
}else{
if(_640<(270+_634)){
_646="end";
_645.y=_63f?size*0.4:0;
}else{
_646="end";
_645.y=_63f?size:0;
}
}
}
}
}
}
if(_63f){
_641.y=stop.y=dim.height-_63e.b;
}else{
_641.y=stop.y=_63e.t;
_643.y=-1;
_644.y=-_644.y;
switch(_646){
case "start":
_646="end";
break;
case "end":
_646="start";
break;
case "middle":
_645.y-=size;
break;
}
}
}
this.cleanGroup();
try{
var s=this.group,c=this.scaler,t=this.ticks,_64c,f=lin.getTransformerFromModel(this.scaler),_64d=(dojox.gfx.renderer=="canvas"),_64e=_64d||!_640&&this.opt.htmlLabels&&!dojo.isIE&&!dojo.isOpera?"html":"gfx",dx=_643.x*_649.length,dy=_643.y*_649.length;
s.createLine({x1:_641.x,y1:_641.y,x2:stop.x,y2:stop.y}).setStroke(_64b);
dojo.forEach(this.labels,function(_64f){
var _650=f(_64f.value),elem,x=_641.x+_642.x*_650,y=_641.y+_642.y*_650;
s.createLine({x1:x,y1:y,x2:x+dx,y2:y+dy}).setStroke(_649);
if(_64f.text){
elem=dc.axis2d.common.createText[_64e](this.chart,s,x+dx+_644.x+(_640?0:_645.x),y+dy+_644.y+(_640?0:_645.y),_646,_64f.text,_647,_648);
if(_64e=="html"){
this.htmlElements.push(elem);
}else{
if(_640){
elem.setTransform([{dx:_645.x,dy:_645.y},g.matrix.rotategAt(_640,x+dx+_644.x,y+dy+_644.y)]);
}
}
}
},this);
}
catch(e){
}
this.dirty=false;
return this;
}});
})();
}
if(!dojo._hasResource["insight.charting.axis2d.Legend"]){
dojo._hasResource["insight.charting.axis2d.Legend"]=true;
dojo.provide("insight.charting.axis2d.Legend");
(function(){
var dc=dojox.charting,du=dojox.lang.utils,g=dojox.gfx,lin=dc.scaler.linear,_651=4,_652=45;
dojo.declare("insight.charting.axis2d.Legend",dojox.charting.axis2d.Base,{opt:null,dirty:true,offset:0,defaultParams:{vertical:false,leftBottom:true,htmlLabels:true,series:[]},optionalParams:{labels:[],maxLabelSize:0,font:"",fontColor:""},constructor:function(_653,_654){
this.opt=dojo.delegate(this.defaultParams,_654);
du.updateWithPattern(this.opt,_654,this.optionalParams);
},clear:function(){
this.dirty=true;
return this;
},initialized:function(){
return !this.dirty;
},getOffsets:function(){
var _655={l:0,r:0,t:0,b:0},o=this.opt,_656=0,a,b,c,d,gl=dc.scaler.common.getNumericLabel,_657=0,ta=this.chart.theme.axis,_658=o.leftBottom,_659=o.font||(ta.majorTick&&ta.majorTick.font)||(ta.tick&&ta.tick.font),size=_659?g.normalizedLength(g.splitFontString(_659).size):0;
if(size){
if(o.maxLabelSize){
_656=o.maxLabelSize;
}else{
if(this.labels){
_656=this._groupLabelWidth(this.labels,_659);
}else{
_656=0;
}
}
if(this.vertical){
_655[_658?"l":"r"]=_656+_651;
}else{
_655[_658?"b":"t"]=size+_651;
_655.l=_655.r=_656/2;
}
}
if(_656){
this._cachedLabelWidth=_656;
}
return _655;
},render:function(dim,_65a){
if(!this.dirty){
return this;
}
var o=this.opt,ta=this.chart.theme.axis,_65b=o.leftBottom,_65c,stop,_65d,_65e,_65f,_660,_661=o.font||(ta.majorTick&&ta.majorTick.font)||(ta.tick&&ta.tick.font),_662=o.fontColor||(ta.majorTick&&ta.majorTick.fontColor)||(ta.tick&&ta.tick.fontColor)||"black",_663="stroke" in o?o.stroke:ta.stroke,size=_661?g.normalizedLength(g.splitFontString(_661).size):0;
if(this.vertical){
_65c={y:dim.height-_65a.b};
stop={y:_65a.t};
_65d={x:0,y:-1};
_65f={x:0,y:0};
_65e={x:_651,y:0};
_660="end";
_65f.y=size*0.4;
if(_65b){
_65c.x=stop.x=_65a.l;
_65e.x=-_65e.x;
}else{
_65c.x=stop.x=dim.width-_65a.r;
switch(_660){
case "start":
_660="end";
break;
case "end":
_660="start";
break;
case "middle":
_65f.x+=size;
break;
}
}
}else{
_65c={x:_65a.l};
stop={x:dim.width-_65a.r};
_65d={x:1,y:0};
_65f={x:0,y:0};
_65e={x:0,y:_651};
_660="middle";
_65f.y=size;
if(_65b){
_65c.y=stop.y=dim.height-_65a.b;
}else{
_65c.y=stop.y=_65a.t;
_65e.y=-_65e.y;
switch(_660){
case "start":
_660="end";
break;
case "end":
_660="start";
break;
case "middle":
_65f.y-=size;
break;
}
}
}
this.cleanGroup();
try{
var s=this.group,_664=(dojox.gfx.renderer=="canvas"),_665=_664||this.opt.htmlLabels&&!dojo.isIE&&!dojo.isOpera?"html":"gfx";
dojo.forEach(o.series,function(_666,pos,arr){
var _667=this.chart.series[this.chart.runs[_666]],_668=s.createGroup(),_669=_668.createGroup(),_66a=_667.label,elem,x,y,_66b=this._makeIcon(_669,_667),_65f={x:0,y:0},_66c={x:0,y:0};
if(pos===0){
_660="start";
x=_65c.x;
y=_65c.y;
_65f.x+=_66b.w+2;
_66c.y-=_66b.h-2;
}else{
if(pos===arr.length-1){
_660="end";
x=stop.x;
y=stop.y;
_65f.x-=_66b.w+2;
_66c.x-=_66b.w;
_66c.y-=_66b.h-2;
}else{
_660="middle";
x=((stop.x-_65c.x)/(arr.length-1)*pos)+_65c.x;
y=((stop.y-_65c.y)/(arr.length-1)*pos)+_65c.y;
_65f.x+=(_66b.w/2)-2;
_66c.x-=(this._groupLabelWidth([_66a],_661)/2)+_66b.w;
_66c.y-=_66b.h-2;
}
}
_669.setTransform([{dx:x+_65e.x+_66c.x,dy:y+_65e.y+_66c.y}]);
elem=dc.axis2d.common.createText[_665](this.chart,_668,x+_65e.x+_65f.x,y+_65e.y+_65f.y,_660,_66a,_661,_662);
if(_665=="html"){
this.htmlElements.push(elem);
}
},this);
}
catch(e){
}
this.dirty=false;
return this;
},_makeIcon:function(_66d,_66e){
var ta=this.chart.theme.axis,_66f=this.opt.font||(ta.majorTick&&ta.majorTick.font)||(ta.tick&&ta.tick.font),size=_66f?g.normalizedLength(g.splitFontString(_66f).size):0,mb={h:size,w:size},dyn=_66e.dyn;
if(dyn.fill){
_66d.createRect({x:2,y:2,width:mb.w-4,height:mb.h-4}).setFill(dyn.fill);
}else{
if(dyn.stroke||dyn.marker){
var line={x1:0,y1:mb.h/2,x2:mb.w,y2:mb.h/2};
if(dyn.stroke||dyn.bstroke){
_66d.createLine(line).setStroke(dyn.stroke||dyn.bstroke);
}
if(dyn.marker){
var c={x:mb.w/2,y:mb.h/2};
if(_66e.markerStroke){
_66d.createPath({path:"M"+c.x+" "+c.y+" "+dyn.marker}).setFill(_66e.markerStroke.color).setStroke(_66e.markerStroke);
}else{
if(dyn.stroke){
_66d.createPath({path:"M"+c.x+" "+c.y+" "+dyn.marker}).setFill(dyn.stroke.color).setStroke(dyn.stroke);
}else{
_66d.createPath({path:"M"+c.x+" "+c.y+" "+dyn.marker}).setFill(dyn.color).setStroke(dyn.color);
}
}
}
}else{
}
}
return {h:mb.h,w:mb.w};
},_groupLabelWidth:function(_670,font){
if(!_670.length){
return 0;
}
if(dojo.isObject(_670[0])){
_670=df.map(_670,function(_671){
return _671.text;
});
}
var s=_670.join("<br>");
return dojox.gfx._base._getTextBox(s,{font:font}).w||0;
}});
})();
}
if(!dojo._hasResource["dojox.charting.DataSeries"]){
dojo._hasResource["dojox.charting.DataSeries"]=true;
dojo.provide("dojox.charting.DataSeries");
dojo.declare("dojox.charting.DataSeries",null,{constructor:function(_672,_673,_674){
this.store=_672;
this.kwArgs=_673;
if(_674){
if(dojo.isFunction(_674)){
this.value=_674;
}else{
if(dojo.isObject(_674)){
this.value=dojo.hitch(this,"_dictValue",dojox.lang.functional.keys(_674),_674);
}else{
this.value=dojo.hitch(this,"_fieldValue",_674);
}
}
}else{
this.value=dojo.hitch(this,"_defaultValue");
}
this.data=[];
this._events=[];
if(this.store.getFeatures()["dojo.data.api.Notification"]){
this._events.push(dojo.connect(this.store,"onNew",this,"_onStoreNew"),dojo.connect(this.store,"onDelete",this,"_onStoreDelete"),dojo.connect(this.store,"onSet",this,"_onStoreSet"));
}
this.fetch();
},destroy:function(){
dojo.forEach(this._events,dojo.disconnect);
},setSeriesObject:function(_675){
this.series=_675;
},_dictValue:function(keys,dict,_676,item){
var o={};
dojo.forEach(keys,function(key){
o[key]=_676.getValue(item,dict[key]);
});
return o;
},_fieldValue:function(_677,_678,item){
return _678.getValue(item,_677);
},_defaultValue:function(_679,item){
return _679.getValue(item,"value");
},fetch:function(){
if(!this._inFlight){
this._inFlight=true;
var _67a=dojo.delegate(this.kwArgs);
_67a.onComplete=dojo.hitch(this,"_onFetchComplete");
_67a.onError=dojo.hitch(this,"onFetchError");
this.store.fetch(_67a);
}
},_onFetchComplete:function(_67b,_67c){
this.items=_67b;
this._buildItemMap();
this.data=dojo.map(this.items,function(item){
return this.value(this.store,item);
},this);
this._pushDataChanges();
this._inFlight=false;
},onFetchError:function(_67d,_67e){
this._inFlight=false;
},_buildItemMap:function(){
if(this.store.getFeatures()["dojo.data.api.Identity"]){
var _67f={};
dojo.forEach(this.items,function(item,_680){
_67f[this.store.getIdentity(item)]=_680;
},this);
this.itemMap=_67f;
}
},_pushDataChanges:function(){
if(this.series){
this.series.chart.updateSeries(this.series.name,this);
this.series.chart.delayedRender();
}
},_onStoreNew:function(){
this.fetch();
},_onStoreDelete:function(item){
if(this.items){
var flag=dojo.some(this.items,function(it,_681){
if(it===item){
this.items.splice(_681,1);
this._buildItemMap();
this.data.splice(_681,1);
return true;
}
return false;
},this);
if(flag){
this._pushDataChanges();
}
}
},_onStoreSet:function(item){
if(this.itemMap){
var id=this.store.getIdentity(item),_682=this.itemMap[id];
if(typeof _682=="number"){
this.data[_682]=this.value(this.store,this.items[_682]);
this._pushDataChanges();
}
}else{
if(this.items){
var flag=dojo.some(this.items,function(it,_683){
if(it===item){
this.data[_683]=this.value(this.store,it);
return true;
}
return false;
},this);
if(flag){
this._pushDataChanges();
}
}
}
}});
}
if(!dojo._hasResource["dojox.lang.functional.fold"]){
dojo._hasResource["dojox.lang.functional.fold"]=true;
dojo.provide("dojox.lang.functional.fold");
(function(){
var d=dojo,df=dojox.lang.functional,_684={};
d.mixin(df,{foldl:function(a,f,z,o){
if(typeof a=="string"){
a=a.split("");
}
o=o||d.global;
f=df.lambda(f);
var i,n;
if(d.isArray(a)){
for(i=0,n=a.length;i<n;z=f.call(o,z,a[i],i,a),++i){
}
}else{
if(typeof a.hasNext=="function"&&typeof a.next=="function"){
for(i=0;a.hasNext();z=f.call(o,z,a.next(),i++,a)){
}
}else{
for(i in a){
if(!(i in _684)){
z=f.call(o,z,a[i],i,a);
}
}
}
}
return z;
},foldl1:function(a,f,o){
if(typeof a=="string"){
a=a.split("");
}
o=o||d.global;
f=df.lambda(f);
var z,i,n;
if(d.isArray(a)){
z=a[0];
for(i=1,n=a.length;i<n;z=f.call(o,z,a[i],i,a),++i){
}
}else{
if(typeof a.hasNext=="function"&&typeof a.next=="function"){
if(a.hasNext()){
z=a.next();
for(i=1;a.hasNext();z=f.call(o,z,a.next(),i++,a)){
}
}
}else{
var _685=true;
for(i in a){
if(!(i in _684)){
if(_685){
z=a[i];
_685=false;
}else{
z=f.call(o,z,a[i],i,a);
}
}
}
}
}
return z;
},foldr:function(a,f,z,o){
if(typeof a=="string"){
a=a.split("");
}
o=o||d.global;
f=df.lambda(f);
for(var i=a.length;i>0;--i,z=f.call(o,z,a[i],i,a)){
}
return z;
},foldr1:function(a,f,o){
if(typeof a=="string"){
a=a.split("");
}
o=o||d.global;
f=df.lambda(f);
var n=a.length,z=a[n-1],i=n-1;
for(;i>0;--i,z=f.call(o,z,a[i],i,a)){
}
return z;
},reduce:function(a,f,z){
return arguments.length<3?df.foldl1(a,f):df.foldl(a,f,z);
},reduceRight:function(a,f,z){
return arguments.length<3?df.foldr1(a,f):df.foldr(a,f,z);
},unfold:function(pr,f,g,z,o){
o=o||d.global;
f=df.lambda(f);
g=df.lambda(g);
pr=df.lambda(pr);
var t=[];
for(;!pr.call(o,z);t.push(f.call(o,z)),z=g.call(o,z)){
}
return t;
}});
})();
}
if(!dojo._hasResource["dojox.color.Palette"]){
dojo._hasResource["dojox.color.Palette"]=true;
dojo.provide("dojox.color.Palette");
(function(){
var dxc=dojox.color;
dxc.Palette=function(base){
this.colors=[];
if(base instanceof dojox.color.Palette){
this.colors=base.colors.slice(0);
}else{
if(base instanceof dojox.color.Color){
this.colors=[null,null,base,null,null];
}else{
if(dojo.isArray(base)){
this.colors=dojo.map(base.slice(0),function(item){
if(dojo.isString(item)){
return new dojox.color.Color(item);
}
return item;
});
}else{
if(dojo.isString(base)){
this.colors=[null,null,new dojox.color.Color(base),null,null];
}
}
}
}
};
function _686(p,_687,val){
var ret=new dojox.color.Palette();
ret.colors=[];
dojo.forEach(p.colors,function(item){
var r=(_687=="dr")?item.r+val:item.r,g=(_687=="dg")?item.g+val:item.g,b=(_687=="db")?item.b+val:item.b,a=(_687=="da")?item.a+val:item.a;
ret.colors.push(new dojox.color.Color({r:Math.min(255,Math.max(0,r)),g:Math.min(255,Math.max(0,g)),b:Math.min(255,Math.max(0,b)),a:Math.min(1,Math.max(0,a))}));
});
return ret;
};
function tCMY(p,_688,val){
var ret=new dojox.color.Palette();
ret.colors=[];
dojo.forEach(p.colors,function(item){
var o=item.toCmy(),c=(_688=="dc")?o.c+val:o.c,m=(_688=="dm")?o.m+val:o.m,y=(_688=="dy")?o.y+val:o.y;
ret.colors.push(dojox.color.fromCmy(Math.min(100,Math.max(0,c)),Math.min(100,Math.max(0,m)),Math.min(100,Math.max(0,y))));
});
return ret;
};
function _689(p,_68a,val){
var ret=new dojox.color.Palette();
ret.colors=[];
dojo.forEach(p.colors,function(item){
var o=item.toCmyk(),c=(_68a=="dc")?o.c+val:o.c,m=(_68a=="dm")?o.m+val:o.m,y=(_68a=="dy")?o.y+val:o.y,k=(_68a=="dk")?o.b+val:o.b;
ret.colors.push(dojox.color.fromCmyk(Math.min(100,Math.max(0,c)),Math.min(100,Math.max(0,m)),Math.min(100,Math.max(0,y)),Math.min(100,Math.max(0,k))));
});
return ret;
};
function tHSL(p,_68b,val){
var ret=new dojox.color.Palette();
ret.colors=[];
dojo.forEach(p.colors,function(item){
var o=item.toHsl(),h=(_68b=="dh")?o.h+val:o.h,s=(_68b=="ds")?o.s+val:o.s,l=(_68b=="dl")?o.l+val:o.l;
ret.colors.push(dojox.color.fromHsl(h%360,Math.min(100,Math.max(0,s)),Math.min(100,Math.max(0,l))));
});
return ret;
};
function tHSV(p,_68c,val){
var ret=new dojox.color.Palette();
ret.colors=[];
dojo.forEach(p.colors,function(item){
var o=item.toHsv(),h=(_68c=="dh")?o.h+val:o.h,s=(_68c=="ds")?o.s+val:o.s,v=(_68c=="dv")?o.v+val:o.v;
ret.colors.push(dojox.color.fromHsv(h%360,Math.min(100,Math.max(0,s)),Math.min(100,Math.max(0,v))));
});
return ret;
};
function _68d(val,low,high){
return high-((high-val)*((high-low)/high));
};
dojo.extend(dxc.Palette,{transform:function(_68e){
var fn=_686;
if(_68e.use){
var use=_68e.use.toLowerCase();
if(use.indexOf("hs")==0){
if(use.charAt(2)=="l"){
fn=tHSL;
}else{
fn=tHSV;
}
}else{
if(use.indexOf("cmy")==0){
if(use.charAt(3)=="k"){
fn=_689;
}else{
fn=tCMY;
}
}
}
}else{
if("dc" in _68e||"dm" in _68e||"dy" in _68e){
if("dk" in _68e){
fn=_689;
}else{
fn=tCMY;
}
}else{
if("dh" in _68e||"ds" in _68e){
if("dv" in _68e){
fn=tHSV;
}else{
fn=tHSL;
}
}
}
}
var _68f=this;
for(var p in _68e){
if(p=="use"){
continue;
}
_68f=fn(_68f,p,_68e[p]);
}
return _68f;
},clone:function(){
return new dxc.Palette(this);
}});
dojo.mixin(dxc.Palette,{generators:{analogous:function(args){
var high=args.high||60,low=args.low||18,base=dojo.isString(args.base)?new dojox.color.Color(args.base):args.base,hsv=base.toHsv();
var h=[(hsv.h+low+360)%360,(hsv.h+Math.round(low/2)+360)%360,hsv.h,(hsv.h-Math.round(high/2)+360)%360,(hsv.h-high+360)%360];
var s1=Math.max(10,(hsv.s<=95)?hsv.s+5:(100-(hsv.s-95))),s2=(hsv.s>1)?hsv.s-1:21-hsv.s,v1=(hsv.v>=92)?hsv.v-9:Math.max(hsv.v+9,20),v2=(hsv.v<=90)?Math.max(hsv.v+5,20):(95+Math.ceil((hsv.v-90)/2)),s=[s1,s2,hsv.s,s1,s1],v=[v1,v2,hsv.v,v1,v2];
return new dxc.Palette(dojo.map(h,function(hue,i){
return dojox.color.fromHsv(hue,s[i],v[i]);
}));
},monochromatic:function(args){
var base=dojo.isString(args.base)?new dojox.color.Color(args.base):args.base,hsv=base.toHsv();
var s1=(hsv.s-30>9)?hsv.s-30:hsv.s+30,s2=hsv.s,v1=_68d(hsv.v,20,100),v2=(hsv.v-20>20)?hsv.v-20:hsv.v+60,v3=(hsv.v-50>20)?hsv.v-50:hsv.v+30;
return new dxc.Palette([dojox.color.fromHsv(hsv.h,s1,v1),dojox.color.fromHsv(hsv.h,s2,v3),base,dojox.color.fromHsv(hsv.h,s1,v3),dojox.color.fromHsv(hsv.h,s2,v2)]);
},triadic:function(args){
var base=dojo.isString(args.base)?new dojox.color.Color(args.base):args.base,hsv=base.toHsv();
var h1=(hsv.h+57+360)%360,h2=(hsv.h-157+360)%360,s1=(hsv.s>20)?hsv.s-10:hsv.s+10,s2=(hsv.s>90)?hsv.s-10:hsv.s+10,s3=(hsv.s>95)?hsv.s-5:hsv.s+5,v1=(hsv.v-20>20)?hsv.v-20:hsv.v+20,v2=(hsv.v-30>20)?hsv.v-30:hsv.v+30,v3=(hsv.v-30>70)?hsv.v-30:hsv.v+30;
return new dxc.Palette([dojox.color.fromHsv(h1,s1,hsv.v),dojox.color.fromHsv(hsv.h,s2,v2),base,dojox.color.fromHsv(h2,s2,v1),dojox.color.fromHsv(h2,s3,v3)]);
},complementary:function(args){
var base=dojo.isString(args.base)?new dojox.color.Color(args.base):args.base,hsv=base.toHsv();
var h1=((hsv.h*2)+137<360)?(hsv.h*2)+137:Math.floor(hsv.h/2)-137,s1=Math.max(hsv.s-10,0),s2=_68d(hsv.s,10,100),s3=Math.min(100,hsv.s+20),v1=Math.min(100,hsv.v+30),v2=(hsv.v>20)?hsv.v-30:hsv.v+30;
return new dxc.Palette([dojox.color.fromHsv(hsv.h,s1,v1),dojox.color.fromHsv(hsv.h,s2,v2),base,dojox.color.fromHsv(h1,s3,v2),dojox.color.fromHsv(h1,hsv.s,hsv.v)]);
},splitComplementary:function(args){
var base=dojo.isString(args.base)?new dojox.color.Color(args.base):args.base,_690=args.da||30,hsv=base.toHsv();
var _691=((hsv.h*2)+137<360)?(hsv.h*2)+137:Math.floor(hsv.h/2)-137,h1=(_691-_690+360)%360,h2=(_691+_690)%360,s1=Math.max(hsv.s-10,0),s2=_68d(hsv.s,10,100),s3=Math.min(100,hsv.s+20),v1=Math.min(100,hsv.v+30),v2=(hsv.v>20)?hsv.v-30:hsv.v+30;
return new dxc.Palette([dojox.color.fromHsv(h1,s1,v1),dojox.color.fromHsv(h1,s2,v2),base,dojox.color.fromHsv(h2,s3,v2),dojox.color.fromHsv(h2,hsv.s,hsv.v)]);
},compound:function(args){
var base=dojo.isString(args.base)?new dojox.color.Color(args.base):args.base,hsv=base.toHsv();
var h1=((hsv.h*2)+18<360)?(hsv.h*2)+18:Math.floor(hsv.h/2)-18,h2=((hsv.h*2)+120<360)?(hsv.h*2)+120:Math.floor(hsv.h/2)-120,h3=((hsv.h*2)+99<360)?(hsv.h*2)+99:Math.floor(hsv.h/2)-99,s1=(hsv.s-40>10)?hsv.s-40:hsv.s+40,s2=(hsv.s-10>80)?hsv.s-10:hsv.s+10,s3=(hsv.s-25>10)?hsv.s-25:hsv.s+25,v1=(hsv.v-40>10)?hsv.v-40:hsv.v+40,v2=(hsv.v-20>80)?hsv.v-20:hsv.v+20,v3=Math.max(hsv.v,20);
return new dxc.Palette([dojox.color.fromHsv(h1,s1,v1),dojox.color.fromHsv(h1,s2,v2),base,dojox.color.fromHsv(h2,s3,v3),dojox.color.fromHsv(h3,s2,v2)]);
},shades:function(args){
var base=dojo.isString(args.base)?new dojox.color.Color(args.base):args.base,hsv=base.toHsv();
var s=(hsv.s==100&&hsv.v==0)?0:hsv.s,v1=(hsv.v-50>20)?hsv.v-50:hsv.v+30,v2=(hsv.v-25>=20)?hsv.v-25:hsv.v+55,v3=(hsv.v-75>=20)?hsv.v-75:hsv.v+5,v4=Math.max(hsv.v-10,20);
return new dxc.Palette([new dojox.color.fromHsv(hsv.h,s,v1),new dojox.color.fromHsv(hsv.h,s,v2),base,new dojox.color.fromHsv(hsv.h,s,v3),new dojox.color.fromHsv(hsv.h,s,v4)]);
}},generate:function(base,type){
if(dojo.isFunction(type)){
return type({base:base});
}else{
if(dxc.Palette.generators[type]){
return dxc.Palette.generators[type]({base:base});
}
}
throw new Error("dojox.color.Palette.generate: the specified generator ('"+type+"') does not exist.");
}});
})();
}
if(!dojo._hasResource["dojox.gfx.gradutils"]){
dojo._hasResource["dojox.gfx.gradutils"]=true;
dojo.provide("dojox.gfx.gradutils");
(function(){
var d=dojo,m=dojox.gfx.matrix,C=d.Color;
function _692(o,c){
if(o<=0){
return c[0].color;
}
var len=c.length;
if(o>=1){
return c[len-1].color;
}
for(var i=0;i<len;++i){
var stop=c[i];
if(stop.offset>=o){
if(i){
var prev=c[i-1];
return d.blendColors(new C(prev.color),new C(stop.color),(o-prev.offset)/(stop.offset-prev.offset));
}
return stop.color;
}
}
return c[len-1].color;
};
dojox.gfx.gradutils.getColor=function(fill,pt){
var o;
if(fill){
switch(fill.type){
case "linear":
var _693=Math.atan2(fill.y2-fill.y1,fill.x2-fill.x1),_694=m.rotate(-_693),_695=m.project(fill.x2-fill.x1,fill.y2-fill.y1),p=m.multiplyPoint(_695,pt),pf1=m.multiplyPoint(_695,fill.x1,fill.y1),pf2=m.multiplyPoint(_695,fill.x2,fill.y2),_696=m.multiplyPoint(_694,pf2.x-pf1.x,pf2.y-pf1.y).x,o=m.multiplyPoint(_694,p.x-pf1.x,p.y-pf1.y).x/_696;
break;
case "radial":
var dx=pt.x-fill.cx,dy=pt.y-fill.cy,o=Math.sqrt(dx*dx+dy*dy)/fill.r;
break;
}
return _692(o,fill.colors);
}
return new C(fill||[0,0,0,0]);
};
dojox.gfx.gradutils.reverse=function(fill){
if(fill){
switch(fill.type){
case "linear":
case "radial":
fill=dojo.delegate(fill);
if(fill.colors){
var c=fill.colors,l=c.length,i=0,stop,n=fill.colors=new Array(c.length);
for(;i<l;++i){
stop=c[i];
n[i]={offset:1-stop.offset,color:stop.color};
}
n.sort(function(a,b){
return a.offset-b.offset;
});
}
break;
}
}
return fill;
};
})();
}
if(!dojo._hasResource["dojox.charting.Theme"]){
dojo._hasResource["dojox.charting.Theme"]=true;
dojo.provide("dojox.charting.Theme");
dojo.declare("dojox.charting.Theme",null,{shapeSpaces:{shape:1,shapeX:1,shapeY:1},constructor:function(_697){
_697=_697||{};
var def=dojox.charting.Theme.defaultTheme;
dojo.forEach(["chart","plotarea","axis","series","marker"],function(name){
this[name]=dojo.delegate(def[name],_697[name]);
},this);
if(_697.seriesThemes&&_697.seriesThemes.length){
this.colors=null;
this.seriesThemes=_697.seriesThemes.slice(0);
}else{
this.seriesThemes=null;
this.colors=(_697.colors||dojox.charting.Theme.defaultColors).slice(0);
}
this.markerThemes=null;
if(_697.markerThemes&&_697.markerThemes.length){
this.markerThemes=_697.markerThemes.slice(0);
}
this.markers=dojo.delegate(dojox.charting.Theme.defaultMarkers,_697.markers);
this.noGradConv=_697.noGradConv;
this.noRadialConv=_697.noRadialConv;
if(_697.reverseFills){
this.reverseFills();
}
this._current=0;
this._buildMarkerArray();
},clone:function(){
var _698=new dojox.charting.Theme({chart:this.chart,plotarea:this.plotarea,axis:this.axis,series:this.series,marker:this.marker,colors:this.colors,markers:this.markers,seriesThemes:this.seriesThemes,markerThemes:this.markerThemes,noGradConv:this.noGradConv,noRadialConv:this.noRadialConv});
dojo.forEach(["clone","clear","next","skip","addMixin","post","getTick"],function(name){
if(this.hasOwnProperty(name)){
_698[name]=this[name];
}
},this);
return _698;
},clear:function(){
this._current=0;
},next:function(_699,_69a,_69b){
var _69c=dojox.lang.utils.merge,_69d,_69e;
if(this.colors){
_69d=dojo.delegate(this.series);
_69e=dojo.delegate(this.marker);
var _69f=new dojo.Color(this.colors[this._current%this.colors.length]),old;
if(_69d.stroke&&_69d.stroke.color){
_69d.stroke=dojo.delegate(_69d.stroke);
old=new dojo.Color(_69d.stroke.color);
_69d.stroke.color=new dojo.Color(_69f);
_69d.stroke.color.a=old.a;
}else{
_69d.stroke={color:_69f};
}
if(_69e.stroke&&_69e.stroke.color){
_69e.stroke=dojo.delegate(_69e.stroke);
old=new dojo.Color(_69e.stroke.color);
_69e.stroke.color=new dojo.Color(_69f);
_69e.stroke.color.a=old.a;
}else{
_69e.stroke={color:_69f};
}
if(!_69d.fill||_69d.fill.type){
_69d.fill=_69f;
}else{
old=new dojo.Color(_69d.fill);
_69d.fill=new dojo.Color(_69f);
_69d.fill.a=old.a;
}
if(!_69e.fill||_69e.fill.type){
_69e.fill=_69f;
}else{
old=new dojo.Color(_69e.fill);
_69e.fill=new dojo.Color(_69f);
_69e.fill.a=old.a;
}
}else{
_69d=this.seriesThemes?_69c(this.series,this.seriesThemes[this._current%this.seriesThemes.length]):this.series;
_69e=this.markerThemes?_69c(this.marker,this.markerThemes[this._current%this.markerThemes.length]):_69d;
}
var _6a0=_69e&&_69e.symbol||this._markers[this._current%this._markers.length];
var _6a1={series:_69d,marker:_69e,symbol:_6a0};
++this._current;
if(_69a){
_6a1=this.addMixin(_6a1,_699,_69a);
}
if(_69b){
_6a1=this.post(_6a1,_699);
}
return _6a1;
},skip:function(){
++this._current;
},addMixin:function(_6a2,_6a3,_6a4,_6a5){
if(dojo.isArray(_6a4)){
dojo.forEach(_6a4,function(m){
_6a2=this.addMixin(_6a2,_6a3,m);
},this);
}else{
var t={};
if("color" in _6a4){
if(_6a3=="line"||_6a3=="area"){
dojo.setObject("series.stroke.color",_6a4.color,t);
dojo.setObject("marker.stroke.color",_6a4.color,t);
}else{
dojo.setObject("series.fill",_6a4.color,t);
}
}
dojo.forEach(["stroke","outline","shadow","fill","font","fontColor"],function(name){
var _6a6="marker"+name.charAt(0).toUpperCase()+name.substr(1),b=_6a6 in _6a4;
if(name in _6a4){
dojo.setObject("series."+name,_6a4[name],t);
if(!b){
dojo.setObject("marker."+name,_6a4[name],t);
}
}
if(b){
dojo.setObject("marker."+name,_6a4[_6a6],t);
}
});
if("marker" in _6a4){
t.symbol=_6a4.marker;
}
_6a2=dojox.lang.utils.merge(_6a2,t);
}
if(_6a5){
_6a2=this.post(_6a2,_6a3);
}
return _6a2;
},post:function(_6a7,_6a8){
var fill=_6a7.series.fill,t;
if(!this.noGradConv&&this.shapeSpaces[fill.space]&&fill.type=="linear"){
if(_6a8=="bar"){
t={x1:fill.y1,y1:fill.x1,x2:fill.y2,y2:fill.x2};
}else{
if(!this.noRadialConv&&fill.space=="shape"&&(_6a8=="slice"||_6a8=="circle")){
t={type:"radial",cx:0,cy:0,r:100};
}
}
if(t){
return dojox.lang.utils.merge(_6a7,{series:{fill:t}});
}
}
return _6a7;
},getTick:function(name,_6a9){
var tick=this.axis.tick,_6aa=name+"Tick";
merge=dojox.lang.utils.merge;
if(tick){
if(this.axis[_6aa]){
tick=merge(tick,this.axis[_6aa]);
}
}else{
tick=this.axis[_6aa];
}
if(_6a9){
if(tick){
if(_6a9[_6aa]){
tick=merge(tick,_6a9[_6aa]);
}
}else{
tick=_6a9[_6aa];
}
}
return tick;
},inspectObjects:function(f){
dojo.forEach(["chart","plotarea","axis","series","marker"],function(name){
f(this[name]);
},this);
if(this.seriesThemes){
dojo.forEach(this.seriesThemes,f);
}
if(this.markerThemes){
dojo.forEach(this.markerThemes,f);
}
},reverseFills:function(){
this.inspectObjects(function(o){
if(o&&o.fill){
o.fill=dojox.gfx.gradutils.reverse(o.fill);
}
});
},addMarker:function(name,_6ab){
this.markers[name]=_6ab;
this._buildMarkerArray();
},setMarkers:function(obj){
this.markers=obj;
this._buildMarkerArray();
},_buildMarkerArray:function(){
this._markers=[];
for(var p in this.markers){
this._markers.push(this.markers[p]);
}
}});
dojo.mixin(dojox.charting.Theme,{defaultMarkers:{CIRCLE:"m-3,0 c0,-4 6,-4 6,0 m-6,0 c0,4 6,4 6,0",SQUARE:"m-3,-3 l0,6 6,0 0,-6 z",DIAMOND:"m0,-3 l3,3 -3,3 -3,-3 z",CROSS:"m0,-3 l0,6 m-3,-3 l6,0",X:"m-3,-3 l6,6 m0,-6 l-6,6",TRIANGLE:"m-3,3 l3,-6 3,6 z",TRIANGLE_INVERTED:"m-3,-3 l3,6 3,-6 z"},defaultColors:["#54544c","#858e94","#6e767a","#948585","#474747"],defaultTheme:{chart:{stroke:null,fill:"white",pageStyle:null},plotarea:{stroke:null,fill:"white"},axis:{stroke:{color:"#333",width:1},tick:{color:"#666",position:"center",font:"normal normal normal 7pt Tahoma",fontColor:"#333"},majorTick:{width:1,length:6},minorTick:{width:0.8,length:3},microTick:{width:0.5,length:1}},series:{stroke:{width:1.5,color:"#333"},outline:{width:0.1,color:"#ccc"},shadow:null,fill:"#ccc",font:"normal normal normal 8pt Tahoma",fontColor:"#000"},marker:{stroke:{width:1.5,color:"#333"},outline:{width:0.1,color:"#ccc"},shadow:null,fill:"#ccc",font:"normal normal normal 8pt Tahoma",fontColor:"#000"}},defineColors:function(_6ac){
_6ac=_6ac||{};
var c=[],n=_6ac.num||5;
if(_6ac.colors){
var l=_6ac.colors.length;
for(var i=0;i<n;i++){
c.push(_6ac.colors[i%l]);
}
return c;
}
if(_6ac.hue){
var s=_6ac.saturation||100;
var st=_6ac.low||30;
var end=_6ac.high||90;
var l=(end+st)/2;
return dojox.color.Palette.generate(dojox.color.fromHsv(_6ac.hue,s,l),"monochromatic").colors;
}
if(_6ac.generator){
return dojox.color.Palette.generate(_6ac.base,_6ac.generator).colors;
}
return c;
},generateGradient:function(_6ad,_6ae,_6af){
var fill=dojo.delegate(_6ad);
fill.colors=[{offset:0,color:_6ae},{offset:1,color:_6af}];
return fill;
},generateHslColor:function(_6b0,_6b1){
_6b0=new dojox.color.Color(_6b0);
var hsl=_6b0.toHsl(),_6b2=dojox.color.fromHsl(hsl.h,hsl.s,_6b1);
_6b2.a=_6b0.a;
return _6b2;
},generateHslGradient:function(_6b3,_6b4,_6b5,_6b6){
_6b3=new dojox.color.Color(_6b3);
var hsl=_6b3.toHsl(),_6b7=dojox.color.fromHsl(hsl.h,hsl.s,_6b5),_6b8=dojox.color.fromHsl(hsl.h,hsl.s,_6b6);
_6b7.a=_6b8.a=_6b3.a;
return dojox.charting.Theme.generateGradient(_6b4,_6b7,_6b8);
}});
}
if(!dojo._hasResource["dojox.charting.Series"]){
dojo._hasResource["dojox.charting.Series"]=true;
dojo.provide("dojox.charting.Series");
dojo.declare("dojox.charting.Series",dojox.charting.Element,{constructor:function(_6b9,data,_6ba){
dojo.mixin(this,_6ba);
if(typeof this.plot!="string"){
this.plot="default";
}
this.update(data);
},clear:function(){
this.dyn={};
},update:function(data){
if(dojo.isArray(data)){
this.data=data;
}else{
this.source=data;
this.data=this.source.data;
if(this.source.setSeriesObject){
this.source.setSeriesObject(this);
}
}
this.dirty=true;
this.clear();
}});
}
if(!dojo._hasResource["dojox.charting.axis2d.Default"]){
dojo._hasResource["dojox.charting.axis2d.Default"]=true;
dojo.provide("dojox.charting.axis2d.Default");
(function(){
var dc=dojox.charting,du=dojox.lang.utils,g=dojox.gfx,lin=dc.scaler.linear,_6bb=4,_6bc=45;
dojo.declare("dojox.charting.axis2d.Default",dojox.charting.axis2d.Invisible,{defaultParams:{vertical:false,fixUpper:"none",fixLower:"none",natural:false,leftBottom:true,includeZero:false,fixed:true,majorLabels:true,minorTicks:true,minorLabels:true,microTicks:false,rotation:0,htmlLabels:true},optionalParams:{min:0,max:1,from:0,to:1,majorTickStep:4,minorTickStep:2,microTickStep:1,labels:[],labelFunc:null,maxLabelSize:0,stroke:{},majorTick:{},minorTick:{},microTick:{},tick:{},font:"",fontColor:""},constructor:function(_6bd,_6be){
this.opt=dojo.delegate(this.defaultParams,_6be);
du.updateWithPattern(this.opt,_6be,this.optionalParams);
},getOffsets:function(){
var s=this.scaler,_6bf={l:0,r:0,t:0,b:0};
if(!s){
return _6bf;
}
var o=this.opt,_6c0=0,a,b,c,d,gl=dc.scaler.common.getNumericLabel,_6c1=0,ma=s.major,mi=s.minor,ta=this.chart.theme.axis,_6c2=o.font||(ta.majorTick&&ta.majorTick.font)||(ta.tick&&ta.tick.font),_6c3=this.chart.theme.getTick("major",o),_6c4=this.chart.theme.getTick("minor",o),size=_6c2?g.normalizedLength(g.splitFontString(_6c2).size):0,_6c5=o.rotation%360,_6c6=o.leftBottom,cosr=Math.abs(Math.cos(_6c5*Math.PI/180)),sinr=Math.abs(Math.sin(_6c5*Math.PI/180));
if(_6c5<0){
_6c5+=360;
}
if(size){
if(o.maxLabelSize){
_6c0=o.maxLabelSize;
}else{
if(this.labels){
_6c0=this._groupLabelWidth(this.labels,_6c2);
}else{
_6c0=this._groupLabelWidth([gl(ma.start,ma.prec,o),gl(ma.start+ma.count*ma.tick,ma.prec,o),gl(mi.start,mi.prec,o),gl(mi.start+mi.count*mi.tick,mi.prec,o)],_6c2);
}
}
if(this.vertical){
var side=_6c6?"l":"r";
switch(_6c5){
case 0:
case 180:
_6bf[side]=_6c0;
_6bf.t=_6bf.b=size/2;
break;
case 90:
case 270:
_6bf[side]=size;
_6bf.t=_6bf.b=_6c0/2;
break;
default:
if(_6c5<=_6bc||(180<_6c5&&_6c5<=(180+_6bc))){
_6bf[side]=size*sinr/2+_6c0*cosr;
_6bf[_6c6?"t":"b"]=size*cosr/2+_6c0*sinr;
_6bf[_6c6?"b":"t"]=size*cosr/2;
}else{
if(_6c5>(360-_6bc)||(180>_6c5&&_6c5>(180-_6bc))){
_6bf[side]=size*sinr/2+_6c0*cosr;
_6bf[_6c6?"b":"t"]=size*cosr/2+_6c0*sinr;
_6bf[_6c6?"t":"b"]=size*cosr/2;
}else{
if(_6c5<90||(180<_6c5&&_6c5<270)){
_6bf[side]=size*sinr+_6c0*cosr;
_6bf[_6c6?"t":"b"]=size*cosr+_6c0*sinr;
}else{
_6bf[side]=size*sinr+_6c0*cosr;
_6bf[_6c6?"b":"t"]=size*cosr+_6c0*sinr;
}
}
}
break;
}
_6bf[side]+=_6bb+Math.max(_6c3.length,_6c4.length);
}else{
var side=_6c6?"b":"t";
switch(_6c5){
case 0:
case 180:
_6bf[side]=size;
_6bf.l=_6bf.r=_6c0/2;
break;
case 90:
case 270:
_6bf[side]=_6c0;
_6bf.l=_6bf.r=size/2;
break;
default:
if((90-_6bc)<=_6c5&&_6c5<=90||(270-_6bc)<=_6c5&&_6c5<=270){
_6bf[side]=size*sinr/2+_6c0*cosr;
_6bf[_6c6?"r":"l"]=size*cosr/2+_6c0*sinr;
_6bf[_6c6?"l":"r"]=size*cosr/2;
}else{
if(90<=_6c5&&_6c5<=(90+_6bc)||270<=_6c5&&_6c5<=(270+_6bc)){
_6bf[side]=size*sinr/2+_6c0*cosr;
_6bf[_6c6?"l":"r"]=size*cosr/2+_6c0*sinr;
_6bf[_6c6?"r":"l"]=size*cosr/2;
}else{
if(_6c5<_6bc||(180<_6c5&&_6c5<(180-_6bc))){
_6bf[side]=size*sinr+_6c0*cosr;
_6bf[_6c6?"r":"l"]=size*cosr+_6c0*sinr;
}else{
_6bf[side]=size*sinr+_6c0*cosr;
_6bf[_6c6?"l":"r"]=size*cosr+_6c0*sinr;
}
}
}
break;
}
_6bf[side]+=_6bb+Math.max(_6c3.length,_6c4.length);
}
}
if(_6c0){
this._cachedLabelWidth=_6c0;
}
return _6bf;
},render:function(dim,_6c7){
if(!this.dirty){
return this;
}
var o=this.opt,ta=this.chart.theme.axis,_6c8=o.leftBottom,_6c9=o.rotation%360,_6ca,stop,_6cb,_6cc,_6cd,_6ce,_6cf,_6d0=o.font||(ta.majorTick&&ta.majorTick.font)||(ta.tick&&ta.tick.font),_6d1=o.fontColor||(ta.majorTick&&ta.majorTick.fontColor)||(ta.tick&&ta.tick.fontColor)||"black",_6d2=this.chart.theme.getTick("major",o),_6d3=this.chart.theme.getTick("minor",o),_6d4=this.chart.theme.getTick("micro",o),_6d5=Math.max(_6d2.length,_6d3.length,_6d4.length),_6d6="stroke" in o?o.stroke:ta.stroke,size=_6d0?g.normalizedLength(g.splitFontString(_6d0).size):0;
if(_6c9<0){
_6c9+=360;
}
if(this.vertical){
_6ca={y:dim.height-_6c7.b};
stop={y:_6c7.t};
_6cb={x:0,y:-1};
_6ce={x:0,y:0};
_6cc={x:1,y:0};
_6cd={x:_6bb,y:0};
switch(_6c9){
case 0:
_6cf="end";
_6ce.y=size*0.4;
break;
case 90:
_6cf="middle";
_6ce.x=-size;
break;
case 180:
_6cf="start";
_6ce.y=-size*0.4;
break;
case 270:
_6cf="middle";
break;
default:
if(_6c9<_6bc){
_6cf="end";
_6ce.y=size*0.4;
}else{
if(_6c9<90){
_6cf="end";
_6ce.y=size*0.4;
}else{
if(_6c9<(180-_6bc)){
_6cf="start";
}else{
if(_6c9<(180+_6bc)){
_6cf="start";
_6ce.y=-size*0.4;
}else{
if(_6c9<270){
_6cf="start";
_6ce.x=_6c8?0:size*0.4;
}else{
if(_6c9<(360-_6bc)){
_6cf="end";
_6ce.x=_6c8?0:size*0.4;
}else{
_6cf="end";
_6ce.y=size*0.4;
}
}
}
}
}
}
}
if(_6c8){
_6ca.x=stop.x=_6c7.l;
_6cc.x=-1;
_6cd.x=-_6cd.x;
}else{
_6ca.x=stop.x=dim.width-_6c7.r;
switch(_6cf){
case "start":
_6cf="end";
break;
case "end":
_6cf="start";
break;
case "middle":
_6ce.x+=size;
break;
}
}
}else{
_6ca={x:_6c7.l};
stop={x:dim.width-_6c7.r};
_6cb={x:1,y:0};
_6ce={x:0,y:0};
_6cc={x:0,y:1};
_6cd={x:0,y:_6bb};
switch(_6c9){
case 0:
_6cf="middle";
_6ce.y=size;
break;
case 90:
_6cf="start";
_6ce.x=-size*0.4;
break;
case 180:
_6cf="middle";
break;
case 270:
_6cf="end";
_6ce.x=size*0.4;
break;
default:
if(_6c9<(90-_6bc)){
_6cf="start";
_6ce.y=_6c8?size:0;
}else{
if(_6c9<(90+_6bc)){
_6cf="start";
_6ce.x=-size*0.4;
}else{
if(_6c9<180){
_6cf="start";
_6ce.y=_6c8?0:-size;
}else{
if(_6c9<(270-_6bc)){
_6cf="end";
_6ce.y=_6c8?0:-size;
}else{
if(_6c9<(270+_6bc)){
_6cf="end";
_6ce.y=_6c8?size*0.4:0;
}else{
_6cf="end";
_6ce.y=_6c8?size:0;
}
}
}
}
}
}
if(_6c8){
_6ca.y=stop.y=dim.height-_6c7.b;
}else{
_6ca.y=stop.y=_6c7.t;
_6cc.y=-1;
_6cd.y=-_6cd.y;
switch(_6cf){
case "start":
_6cf="end";
break;
case "end":
_6cf="start";
break;
case "middle":
_6ce.y-=size;
break;
}
}
}
this.cleanGroup();
try{
var s=this.group,c=this.scaler,t=this.ticks,_6d7,f=lin.getTransformerFromModel(this.scaler),_6d8=(dojox.gfx.renderer=="canvas"),_6d9=_6d8||!_6c9&&this.opt.htmlLabels&&!dojo.isIE&&!dojo.isOpera?"html":"gfx",dx=_6cc.x*_6d2.length,dy=_6cc.y*_6d2.length;
s.createLine({x1:_6ca.x,y1:_6ca.y,x2:stop.x,y2:stop.y}).setStroke(_6d6);
dojo.forEach(t.major,function(tick){
var _6da=f(tick.value),elem,x=_6ca.x+_6cb.x*_6da,y=_6ca.y+_6cb.y*_6da;
s.createLine({x1:x,y1:y,x2:x+dx,y2:y+dy}).setStroke(_6d2);
if(tick.label){
elem=dc.axis2d.common.createText[_6d9](this.chart,s,x+dx+_6cd.x+(_6c9?0:_6ce.x),y+dy+_6cd.y+(_6c9?0:_6ce.y),_6cf,tick.label,_6d0,_6d1);
if(_6d9=="html"){
this.htmlElements.push(elem);
}else{
if(_6c9){
elem.setTransform([{dx:_6ce.x,dy:_6ce.y},g.matrix.rotategAt(_6c9,x+dx+_6cd.x,y+dy+_6cd.y)]);
}
}
}
},this);
dx=_6cc.x*_6d3.length;
dy=_6cc.y*_6d3.length;
_6d7=c.minMinorStep<=c.minor.tick*c.bounds.scale;
dojo.forEach(t.minor,function(tick){
var _6db=f(tick.value),elem,x=_6ca.x+_6cb.x*_6db,y=_6ca.y+_6cb.y*_6db;
s.createLine({x1:x,y1:y,x2:x+dx,y2:y+dy}).setStroke(_6d3);
if(_6d7&&tick.label){
elem=dc.axis2d.common.createText[_6d9](this.chart,s,x+dx+_6cd.x+(_6c9?0:_6ce.x),y+dy+_6cd.y+(_6c9?0:_6ce.y),_6cf,tick.label,_6d0,_6d1);
if(_6d9=="html"){
this.htmlElements.push(elem);
}else{
if(_6c9){
elem.setTransform([{dx:_6ce.x,dy:_6ce.y},g.matrix.rotategAt(_6c9,x+dx+_6cd.x,y+dy+_6cd.y)]);
}
}
}
},this);
dx=_6cc.x*_6d4.length;
dy=_6cc.y*_6d4.length;
dojo.forEach(t.micro,function(tick){
var _6dc=f(tick.value),elem,x=_6ca.x+_6cb.x*_6dc,y=_6ca.y+_6cb.y*_6dc;
s.createLine({x1:x,y1:y,x2:x+dx,y2:y+dy}).setStroke(_6d4);
},this);
}
catch(e){
}
this.dirty=false;
return this;
}});
})();
}
if(!dojo._hasResource["dojox.charting.plot2d.Markers"]){
dojo._hasResource["dojox.charting.plot2d.Markers"]=true;
dojo.provide("dojox.charting.plot2d.Markers");
dojo.declare("dojox.charting.plot2d.Markers",dojox.charting.plot2d.Default,{constructor:function(){
this.opt.markers=true;
}});
}
if(!dojo._hasResource["dojox.charting.plot2d.MarkersOnly"]){
dojo._hasResource["dojox.charting.plot2d.MarkersOnly"]=true;
dojo.provide("dojox.charting.plot2d.MarkersOnly");
dojo.declare("dojox.charting.plot2d.MarkersOnly",dojox.charting.plot2d.Default,{constructor:function(){
this.opt.lines=false;
this.opt.markers=true;
}});
}
if(!dojo._hasResource["dojox.charting.plot2d.Scatter"]){
dojo._hasResource["dojox.charting.plot2d.Scatter"]=true;
dojo.provide("dojox.charting.plot2d.Scatter");
(function(){
var df=dojox.lang.functional,du=dojox.lang.utils,dc=dojox.charting.plot2d.common,_6dd=df.lambda("item.purgeGroup()");
dojo.declare("dojox.charting.plot2d.Scatter",dojox.charting.plot2d.Base,{defaultParams:{hAxis:"x",vAxis:"y",shadows:null,animate:null},optionalParams:{markerStroke:{},markerOutline:{},markerShadow:{},markerFill:{},markerFont:"",markerFontColor:""},constructor:function(_6de,_6df){
this.opt=dojo.clone(this.defaultParams);
du.updateWithObject(this.opt,_6df);
this.series=[];
this.hAxis=this.opt.hAxis;
this.vAxis=this.opt.vAxis;
this.animate=this.opt.animate;
},render:function(dim,_6e0){
if(this.zoom&&!this.isDataDirty()){
return this.performZoom(dim,_6e0);
}
this.resetEvents();
this.dirty=this.isDirty();
if(this.dirty){
dojo.forEach(this.series,_6dd);
this._eventSeries={};
this.cleanGroup();
var s=this.group;
df.forEachRev(this.series,function(item){
item.cleanGroup(s);
});
}
var t=this.chart.theme,_6e1=this.events();
for(var i=this.series.length-1;i>=0;--i){
var run=this.series[i];
if(!this.dirty&&!run.dirty){
t.skip();
this._reconnectEvents(run.name);
continue;
}
run.cleanGroup();
if(!run.data.length){
run.dirty=false;
t.skip();
continue;
}
var _6e2=t.next("marker",[this.opt,run]),s=run.group,_6e3,ht=this._hScaler.scaler.getTransformerFromModel(this._hScaler),vt=this._vScaler.scaler.getTransformerFromModel(this._vScaler);
if(typeof run.data[0]=="number"){
_6e3=dojo.map(run.data,function(v,i){
return {x:ht(i+1)+_6e0.l,y:dim.height-_6e0.b-vt(v)};
},this);
}else{
_6e3=dojo.map(run.data,function(v,i){
return {x:ht(v.x)+_6e0.l,y:dim.height-_6e0.b-vt(v.y)};
},this);
}
var _6e4=new Array(_6e3.length),_6e5=new Array(_6e3.length),_6e6=new Array(_6e3.length);
dojo.forEach(_6e3,function(c,i){
var _6e7=typeof run.data[i]=="number"?t.post(_6e2,"marker"):t.addMixin(_6e2,"marker",run.data[i],true),path="M"+c.x+" "+c.y+" "+_6e7.symbol;
if(_6e7.marker.shadow){
_6e4[i]=s.createPath("M"+(c.x+_6e7.marker.shadow.dx)+" "+(c.y+_6e7.marker.shadow.dy)+" "+_6e7.symbol).setStroke(_6e7.marker.shadow).setFill(_6e7.marker.shadow.color);
if(this.animate){
this._animateScatter(_6e4[i],dim.height-_6e0.b);
}
}
if(_6e7.marker.outline){
var _6e8=dc.makeStroke(_6e7.marker.outline);
_6e8.width=2*_6e8.width+_6e7.marker.stroke.width;
_6e6[i]=s.createPath(path).setStroke(_6e8);
if(this.animate){
this._animateScatter(_6e6[i],dim.height-_6e0.b);
}
}
var _6e9=dc.makeStroke(_6e7.marker.stroke),fill=this._plotFill(_6e7.marker.fill,dim,_6e0);
if(fill&&(fill.type==="linear"||fill.type=="radial")){
var _6ea=dojox.gfx.gradutils.getColor(fill,{x:c.x,y:c.y});
if(_6e9){
_6e9.color=_6ea;
}
_6e5[i]=s.createPath(path).setStroke(_6e9).setFill(_6ea);
}else{
_6e5[i]=s.createPath(path).setStroke(_6e9).setFill(fill);
}
if(this.animate){
this._animateScatter(_6e5[i],dim.height-_6e0.b);
}
},this);
if(_6e5.length){
run.dyn.stroke=_6e5[_6e5.length-1].getStroke();
run.dyn.fill=_6e5[_6e5.length-1].getFill();
}
if(_6e1){
var _6eb=new Array(_6e5.length);
dojo.forEach(_6e5,function(s,i){
var o={element:"marker",index:i,run:run,shape:s,outline:_6e6&&_6e6[i]||null,shadow:_6e4&&_6e4[i]||null,cx:_6e3[i].x,cy:_6e3[i].y};
if(typeof run.data[0]=="number"){
o.x=i+1;
o.y=run.data[i];
}else{
o.x=run.data[i].x;
o.y=run.data[i].y;
}
this._connectEvents(o);
_6eb[i]=o;
},this);
this._eventSeries[run.name]=_6eb;
}else{
delete this._eventSeries[run.name];
}
run.dirty=false;
}
this.dirty=false;
return this;
},_animateScatter:function(_6ec,_6ed){
dojox.gfx.fx.animateTransform(dojo.delegate({shape:_6ec,duration:1200,transform:[{name:"translate",start:[0,_6ed],end:[0,0]},{name:"scale",start:[0,0],end:[1,1]},{name:"original"}]},this.animate)).play();
}});
})();
}
if(!dojo._hasResource["dojox.lang.functional.sequence"]){
dojo._hasResource["dojox.lang.functional.sequence"]=true;
dojo.provide("dojox.lang.functional.sequence");
(function(){
var d=dojo,df=dojox.lang.functional;
d.mixin(df,{repeat:function(n,f,z,o){
o=o||d.global;
f=df.lambda(f);
var t=new Array(n),i=1;
t[0]=z;
for(;i<n;t[i]=z=f.call(o,z),++i){
}
return t;
},until:function(pr,f,z,o){
o=o||d.global;
f=df.lambda(f);
pr=df.lambda(pr);
var t=[];
for(;!pr.call(o,z);t.push(z),z=f.call(o,z)){
}
return t;
}});
})();
}
if(!dojo._hasResource["dojox.charting.plot2d.Stacked"]){
dojo._hasResource["dojox.charting.plot2d.Stacked"]=true;
dojo.provide("dojox.charting.plot2d.Stacked");
(function(){
var df=dojox.lang.functional,dc=dojox.charting.plot2d.common,_6ee=df.lambda("item.purgeGroup()");
dojo.declare("dojox.charting.plot2d.Stacked",dojox.charting.plot2d.Default,{getSeriesStats:function(){
var _6ef=dc.collectStackedStats(this.series);
this._maxRunLength=_6ef.hmax;
return _6ef;
},render:function(dim,_6f0){
if(this._maxRunLength<=0){
return this;
}
var acc=df.repeat(this._maxRunLength,"-> 0",0);
for(var i=0;i<this.series.length;++i){
var run=this.series[i];
for(var j=0;j<run.data.length;++j){
var v=run.data[j];
if(v!==null){
if(isNaN(v)){
v=0;
}
acc[j]+=v;
}
}
}
if(this.zoom&&!this.isDataDirty()){
return this.performZoom(dim,_6f0);
}
this.resetEvents();
this.dirty=this.isDirty();
if(this.dirty){
dojo.forEach(this.series,_6ee);
this._eventSeries={};
this.cleanGroup();
var s=this.group;
df.forEachRev(this.series,function(item){
item.cleanGroup(s);
});
}
var t=this.chart.theme,_6f1=this.events(),ht=this._hScaler.scaler.getTransformerFromModel(this._hScaler),vt=this._vScaler.scaler.getTransformerFromModel(this._vScaler);
for(var i=this.series.length-1;i>=0;--i){
var run=this.series[i];
if(!this.dirty&&!run.dirty){
t.skip();
this._reconnectEvents(run.name);
continue;
}
run.cleanGroup();
var _6f2=t.next(this.opt.areas?"area":"line",[this.opt,run],true),s=run.group,_6f3,_6f4=dojo.map(acc,function(v,i){
return {x:ht(i+1)+_6f0.l,y:dim.height-_6f0.b-vt(v)};
},this);
var _6f5=this.opt.tension?dc.curve(_6f4,this.opt.tension):"";
if(this.opt.areas){
var _6f6=dojo.clone(_6f4);
if(this.opt.tension){
var p=dc.curve(_6f6,this.opt.tension);
p+=" L"+_6f4[_6f4.length-1].x+","+(dim.height-_6f0.b)+" L"+_6f4[0].x+","+(dim.height-_6f0.b)+" L"+_6f4[0].x+","+_6f4[0].y;
run.dyn.fill=s.createPath(p).setFill(_6f2.series.fill).getFill();
}else{
_6f6.push({x:_6f4[_6f4.length-1].x,y:dim.height-_6f0.b});
_6f6.push({x:_6f4[0].x,y:dim.height-_6f0.b});
_6f6.push(_6f4[0]);
run.dyn.fill=s.createPolyline(_6f6).setFill(_6f2.series.fill).getFill();
}
}
if(this.opt.lines||this.opt.markers){
if(_6f2.series.outline){
_6f3=dc.makeStroke(_6f2.series.outline);
_6f3.width=2*_6f3.width+_6f2.series.stroke.width;
}
}
if(this.opt.markers){
run.dyn.marker=_6f2.symbol;
}
var _6f7,_6f8,_6f9;
if(_6f2.series.shadow&&_6f2.series.stroke){
var _6fa=_6f2.series.shadow,_6fb=dojo.map(_6f4,function(c){
return {x:c.x+_6fa.dx,y:c.y+_6fa.dy};
});
if(this.opt.lines){
if(this.opt.tension){
run.dyn.shadow=s.createPath(dc.curve(_6fb,this.opt.tension)).setStroke(_6fa).getStroke();
}else{
run.dyn.shadow=s.createPolyline(_6fb).setStroke(_6fa).getStroke();
}
}
if(this.opt.markers){
_6fa=_6f2.marker.shadow;
_6f9=dojo.map(_6fb,function(c){
return s.createPath("M"+c.x+" "+c.y+" "+_6f2.symbol).setStroke(_6fa).setFill(_6fa.color);
},this);
}
}
if(this.opt.lines){
if(_6f3){
if(this.opt.tension){
run.dyn.outline=s.createPath(_6f5).setStroke(_6f3).getStroke();
}else{
run.dyn.outline=s.createPolyline(_6f4).setStroke(_6f3).getStroke();
}
}
if(this.opt.tension){
run.dyn.stroke=s.createPath(_6f5).setStroke(_6f2.series.stroke).getStroke();
}else{
run.dyn.stroke=s.createPolyline(_6f4).setStroke(_6f2.series.stroke).getStroke();
}
}
if(this.opt.markers){
_6f7=new Array(_6f4.length);
_6f8=new Array(_6f4.length);
_6f3=null;
if(_6f2.marker.outline){
_6f3=dc.makeStroke(_6f2.marker.outline);
_6f3.width=2*_6f3.width+(_6f2.marker.stroke?_6f2.marker.stroke.width:0);
}
dojo.forEach(_6f4,function(c,i){
var path="M"+c.x+" "+c.y+" "+_6f2.symbol;
if(_6f3){
_6f8[i]=s.createPath(path).setStroke(_6f3);
}
_6f7[i]=s.createPath(path).setStroke(_6f2.marker.stroke).setFill(_6f2.marker.fill);
},this);
if(_6f1){
var _6fc=new Array(_6f7.length);
dojo.forEach(_6f7,function(s,i){
var o={element:"marker",index:i,run:run,shape:s,outline:_6f8[i]||null,shadow:_6f9&&_6f9[i]||null,cx:_6f4[i].x,cy:_6f4[i].y,x:i+1,y:run.data[i]};
this._connectEvents(o);
_6fc[i]=o;
},this);
this._eventSeries[run.name]=_6fc;
}else{
delete this._eventSeries[run.name];
}
}
run.dirty=false;
for(var j=0;j<run.data.length;++j){
var v=run.data[j];
if(v!==null){
if(isNaN(v)){
v=0;
}
acc[j]-=v;
}
}
}
this.dirty=false;
return this;
}});
})();
}
if(!dojo._hasResource["dojox.charting.plot2d.StackedLines"]){
dojo._hasResource["dojox.charting.plot2d.StackedLines"]=true;
dojo.provide("dojox.charting.plot2d.StackedLines");
dojo.declare("dojox.charting.plot2d.StackedLines",dojox.charting.plot2d.Stacked,{constructor:function(){
this.opt.lines=true;
}});
}
if(!dojo._hasResource["dojox.charting.plot2d.StackedAreas"]){
dojo._hasResource["dojox.charting.plot2d.StackedAreas"]=true;
dojo.provide("dojox.charting.plot2d.StackedAreas");
dojo.declare("dojox.charting.plot2d.StackedAreas",dojox.charting.plot2d.Stacked,{constructor:function(){
this.opt.lines=true;
this.opt.areas=true;
}});
}
if(!dojo._hasResource["dojox.charting.plot2d.Columns"]){
dojo._hasResource["dojox.charting.plot2d.Columns"]=true;
dojo.provide("dojox.charting.plot2d.Columns");
(function(){
var df=dojox.lang.functional,du=dojox.lang.utils,dc=dojox.charting.plot2d.common,_6fd=df.lambda("item.purgeGroup()");
dojo.declare("dojox.charting.plot2d.Columns",dojox.charting.plot2d.Base,{defaultParams:{hAxis:"x",vAxis:"y",gap:0,animate:null},optionalParams:{minBarSize:1,maxBarSize:1,stroke:{},outline:{},shadow:{},fill:{},font:"",fontColor:""},constructor:function(_6fe,_6ff){
this.opt=dojo.clone(this.defaultParams);
du.updateWithObject(this.opt,_6ff);
du.updateWithPattern(this.opt,_6ff,this.optionalParams);
this.series=[];
this.hAxis=this.opt.hAxis;
this.vAxis=this.opt.vAxis;
this.animate=this.opt.animate;
},getSeriesStats:function(){
var _700=dc.collectSimpleStats(this.series);
_700.hmin-=0.5;
_700.hmax+=0.5;
return _700;
},render:function(dim,_701){
if(this.zoom&&!this.isDataDirty()){
return this.performZoom(dim,_701);
}
this.resetEvents();
this.dirty=this.isDirty();
if(this.dirty){
dojo.forEach(this.series,_6fd);
this._eventSeries={};
this.cleanGroup();
var s=this.group;
df.forEachRev(this.series,function(item){
item.cleanGroup(s);
});
}
var t=this.chart.theme,f,gap,_702,ht=this._hScaler.scaler.getTransformerFromModel(this._hScaler),vt=this._vScaler.scaler.getTransformerFromModel(this._vScaler),_703=Math.max(0,this._vScaler.bounds.lower),_704=vt(_703),_705=this.events();
f=dc.calculateBarSize(this._hScaler.bounds.scale,this.opt);
gap=f.gap;
_702=f.size;
for(var i=this.series.length-1;i>=0;--i){
var run=this.series[i];
if(!this.dirty&&!run.dirty){
t.skip();
this._reconnectEvents(run.name);
continue;
}
run.cleanGroup();
var _706=t.next("column",[this.opt,run]),s=run.group,_707=new Array(run.data.length);
for(var j=0;j<run.data.length;++j){
var _708=run.data[j];
if(_708!==null){
var v=typeof _708=="number"?_708:_708.y,vv=vt(v),_709=vv-_704,h=Math.abs(_709),_70a=typeof _708!="number"?t.addMixin(_706,"column",_708,true):t.post(_706,"column");
if(_702>=1&&h>=1){
var rect={x:_701.l+ht(j+0.5)+gap,y:dim.height-_701.b-(v>_703?vv:_704),width:_702,height:h};
var _70b=this._plotFill(_70a.series.fill,dim,_701);
_70b=this._shapeFill(_70b,rect);
var _70c=s.createRect(rect).setFill(_70b).setStroke(_70a.series.stroke);
run.dyn.fill=_70c.getFill();
run.dyn.stroke=_70c.getStroke();
if(_705){
var o={element:"column",index:j,run:run,shape:_70c,x:j+0.5,y:v};
this._connectEvents(o);
_707[j]=o;
}
if(this.animate){
this._animateColumn(_70c,dim.height-_701.b-_704,h);
}
}
}
}
this._eventSeries[run.name]=_707;
run.dirty=false;
}
this.dirty=false;
return this;
},_animateColumn:function(_70d,_70e,_70f){
dojox.gfx.fx.animateTransform(dojo.delegate({shape:_70d,duration:1200,transform:[{name:"translate",start:[0,_70e-(_70e/_70f)],end:[0,0]},{name:"scale",start:[1,1/_70f],end:[1,1]},{name:"original"}]},this.animate)).play();
}});
})();
}
if(!dojo._hasResource["dojox.charting.plot2d.StackedColumns"]){
dojo._hasResource["dojox.charting.plot2d.StackedColumns"]=true;
dojo.provide("dojox.charting.plot2d.StackedColumns");
(function(){
var df=dojox.lang.functional,dc=dojox.charting.plot2d.common,_710=df.lambda("item.purgeGroup()");
dojo.declare("dojox.charting.plot2d.StackedColumns",dojox.charting.plot2d.Columns,{getSeriesStats:function(){
var _711=dc.collectStackedStats(this.series);
this._maxRunLength=_711.hmax;
_711.hmin-=0.5;
_711.hmax+=0.5;
return _711;
},render:function(dim,_712){
if(this._maxRunLength<=0){
return this;
}
var acc=df.repeat(this._maxRunLength,"-> 0",0);
for(var i=0;i<this.series.length;++i){
var run=this.series[i];
for(var j=0;j<run.data.length;++j){
var _713=run.data[j];
if(_713!==null){
var v=typeof _713=="number"?_713:_713.y;
if(isNaN(v)){
v=0;
}
acc[j]+=v;
}
}
}
if(this.zoom&&!this.isDataDirty()){
return this.performZoom(dim,_712);
}
this.resetEvents();
this.dirty=this.isDirty();
if(this.dirty){
dojo.forEach(this.series,_710);
this._eventSeries={};
this.cleanGroup();
var s=this.group;
df.forEachRev(this.series,function(item){
item.cleanGroup(s);
});
}
var t=this.chart.theme,f,gap,_714,ht=this._hScaler.scaler.getTransformerFromModel(this._hScaler),vt=this._vScaler.scaler.getTransformerFromModel(this._vScaler),_715=this.events();
f=dc.calculateBarSize(this._hScaler.bounds.scale,this.opt);
gap=f.gap;
_714=f.size;
for(var i=this.series.length-1;i>=0;--i){
var run=this.series[i];
if(!this.dirty&&!run.dirty){
t.skip();
this._reconnectEvents(run.name);
continue;
}
run.cleanGroup();
var _716=t.next("column",[this.opt,run]),s=run.group,_717=new Array(acc.length);
for(var j=0;j<acc.length;++j){
var _713=run.data[j];
if(_713!==null){
var v=acc[j],_718=vt(v),_719=typeof _713!="number"?t.addMixin(_716,"column",_713,true):t.post(_716,"column");
if(_714>=1&&_718>=1){
var rect={x:_712.l+ht(j+0.5)+gap,y:dim.height-_712.b-vt(v),width:_714,height:_718};
var _71a=this._plotFill(_719.series.fill,dim,_712);
_71a=this._shapeFill(_71a,rect);
var _71b=s.createRect(rect).setFill(_71a).setStroke(_719.series.stroke);
run.dyn.fill=_71b.getFill();
run.dyn.stroke=_71b.getStroke();
if(_715){
var o={element:"column",index:j,run:run,shape:_71b,x:j+0.5,y:v};
this._connectEvents(o);
_717[j]=o;
}
if(this.animate){
this._animateColumn(_71b,dim.height-_712.b,_718);
}
}
}
}
this._eventSeries[run.name]=_717;
run.dirty=false;
for(var j=0;j<run.data.length;++j){
var _713=run.data[j];
if(_713!==null){
var v=typeof _713=="number"?_713:_713.y;
if(isNaN(v)){
v=0;
}
acc[j]-=v;
}
}
}
this.dirty=false;
return this;
}});
})();
}
if(!dojo._hasResource["dojox.charting.plot2d.ClusteredColumns"]){
dojo._hasResource["dojox.charting.plot2d.ClusteredColumns"]=true;
dojo.provide("dojox.charting.plot2d.ClusteredColumns");
(function(){
var df=dojox.lang.functional,dc=dojox.charting.plot2d.common,_71c=df.lambda("item.purgeGroup()");
dojo.declare("dojox.charting.plot2d.ClusteredColumns",dojox.charting.plot2d.Columns,{render:function(dim,_71d){
if(this.zoom&&!this.isDataDirty()){
return this.performZoom(dim,_71d);
}
this.resetEvents();
this.dirty=this.isDirty();
if(this.dirty){
dojo.forEach(this.series,_71c);
this._eventSeries={};
this.cleanGroup();
var s=this.group;
df.forEachRev(this.series,function(item){
item.cleanGroup(s);
});
}
var t=this.chart.theme,f,gap,_71e,_71f,ht=this._hScaler.scaler.getTransformerFromModel(this._hScaler),vt=this._vScaler.scaler.getTransformerFromModel(this._vScaler),_720=Math.max(0,this._vScaler.bounds.lower),_721=vt(_720),_722=this.events();
f=dc.calculateBarSize(this._hScaler.bounds.scale,this.opt,this.series.length);
gap=f.gap;
_71e=_71f=f.size;
for(var i=0;i<this.series.length;++i){
var run=this.series[i],_723=_71f*i;
if(!this.dirty&&!run.dirty){
t.skip();
this._reconnectEvents(run.name);
continue;
}
run.cleanGroup();
var _724=t.next("column",[this.opt,run]),s=run.group,_725=new Array(run.data.length);
for(var j=0;j<run.data.length;++j){
var _726=run.data[j];
if(_726!==null){
var v=typeof _726=="number"?_726:_726.y,vv=vt(v),_727=vv-_721,h=Math.abs(_727),_728=typeof _726!="number"?t.addMixin(_724,"column",_726,true):t.post(_724,"column");
if(_71e>=1&&h>=1){
var rect={x:_71d.l+ht(j+0.5)+gap+_723,y:dim.height-_71d.b-(v>_720?vv:_721),width:_71e,height:h};
var _729=this._plotFill(_728.series.fill,dim,_71d);
_729=this._shapeFill(_729,rect);
var _72a=s.createRect(rect).setFill(_729).setStroke(_728.series.stroke);
run.dyn.fill=_72a.getFill();
run.dyn.stroke=_72a.getStroke();
if(_722){
var o={element:"column",index:j,run:run,shape:_72a,x:j+0.5,y:v};
this._connectEvents(o);
_725[j]=o;
}
if(this.animate){
this._animateColumn(_72a,dim.height-_71d.b-_721,h);
}
}
}
}
this._eventSeries[run.name]=_725;
run.dirty=false;
}
this.dirty=false;
return this;
}});
})();
}
if(!dojo._hasResource["dojox.charting.plot2d.Bars"]){
dojo._hasResource["dojox.charting.plot2d.Bars"]=true;
dojo.provide("dojox.charting.plot2d.Bars");
(function(){
var df=dojox.lang.functional,du=dojox.lang.utils,dc=dojox.charting.plot2d.common,_72b=df.lambda("item.purgeGroup()");
dojo.declare("dojox.charting.plot2d.Bars",dojox.charting.plot2d.Base,{defaultParams:{hAxis:"x",vAxis:"y",gap:0,animate:null},optionalParams:{minBarSize:1,maxBarSize:1,stroke:{},outline:{},shadow:{},fill:{},font:"",fontColor:""},constructor:function(_72c,_72d){
this.opt=dojo.clone(this.defaultParams);
du.updateWithObject(this.opt,_72d);
du.updateWithPattern(this.opt,_72d,this.optionalParams);
this.series=[];
this.hAxis=this.opt.hAxis;
this.vAxis=this.opt.vAxis;
this.animate=this.opt.animate;
},getSeriesStats:function(){
var _72e=dc.collectSimpleStats(this.series),t;
_72e.hmin-=0.5;
_72e.hmax+=0.5;
t=_72e.hmin,_72e.hmin=_72e.vmin,_72e.vmin=t;
t=_72e.hmax,_72e.hmax=_72e.vmax,_72e.vmax=t;
return _72e;
},render:function(dim,_72f){
if(this.zoom&&!this.isDataDirty()){
return this.performZoom(dim,_72f);
}
this.dirty=this.isDirty();
this.resetEvents();
if(this.dirty){
dojo.forEach(this.series,_72b);
this._eventSeries={};
this.cleanGroup();
var s=this.group;
df.forEachRev(this.series,function(item){
item.cleanGroup(s);
});
}
var t=this.chart.theme,f,gap,_730,ht=this._hScaler.scaler.getTransformerFromModel(this._hScaler),vt=this._vScaler.scaler.getTransformerFromModel(this._vScaler),_731=Math.max(0,this._hScaler.bounds.lower),_732=ht(_731),_733=this.events();
f=dc.calculateBarSize(this._vScaler.bounds.scale,this.opt);
gap=f.gap;
_730=f.size;
for(var i=this.series.length-1;i>=0;--i){
var run=this.series[i];
if(!this.dirty&&!run.dirty){
t.skip();
this._reconnectEvents(run.name);
continue;
}
run.cleanGroup();
var _734=t.next("bar",[this.opt,run]),s=run.group,_735=new Array(run.data.length);
for(var j=0;j<run.data.length;++j){
var _736=run.data[j];
if(_736!==null){
var v=typeof _736=="number"?_736:_736.y,hv=ht(v),_737=hv-_732,w=Math.abs(_737),_738=typeof _736!="number"?t.addMixin(_734,"bar",_736,true):t.post(_734,"bar");
if(w>=1&&_730>=1){
var rect={x:_72f.l+(v<_731?hv:_732),y:dim.height-_72f.b-vt(j+1.5)+gap,width:w,height:_730};
var _739=this._plotFill(_738.series.fill,dim,_72f);
_739=this._shapeFill(_739,rect);
var _73a=s.createRect(rect).setFill(_739).setStroke(_738.series.stroke);
run.dyn.fill=_73a.getFill();
run.dyn.stroke=_73a.getStroke();
if(_733){
var o={element:"bar",index:j,run:run,shape:_73a,x:v,y:j+1.5};
this._connectEvents(o);
_735[j]=o;
}
if(this.animate){
this._animateBar(_73a,_72f.l+_732,-w);
}
}
}
}
this._eventSeries[run.name]=_735;
run.dirty=false;
}
this.dirty=false;
return this;
},_animateBar:function(_73b,_73c,_73d){
dojox.gfx.fx.animateTransform(dojo.delegate({shape:_73b,duration:1200,transform:[{name:"translate",start:[_73c-(_73c/_73d),0],end:[0,0]},{name:"scale",start:[1/_73d,1],end:[1,1]},{name:"original"}]},this.animate)).play();
}});
})();
}
if(!dojo._hasResource["dojox.charting.plot2d.StackedBars"]){
dojo._hasResource["dojox.charting.plot2d.StackedBars"]=true;
dojo.provide("dojox.charting.plot2d.StackedBars");
(function(){
var df=dojox.lang.functional,dc=dojox.charting.plot2d.common,_73e=df.lambda("item.purgeGroup()");
dojo.declare("dojox.charting.plot2d.StackedBars",dojox.charting.plot2d.Bars,{getSeriesStats:function(){
var _73f=dc.collectStackedStats(this.series),t;
this._maxRunLength=_73f.hmax;
_73f.hmin-=0.5;
_73f.hmax+=0.5;
t=_73f.hmin,_73f.hmin=_73f.vmin,_73f.vmin=t;
t=_73f.hmax,_73f.hmax=_73f.vmax,_73f.vmax=t;
return _73f;
},render:function(dim,_740){
if(this._maxRunLength<=0){
return this;
}
var acc=df.repeat(this._maxRunLength,"-> 0",0);
for(var i=0;i<this.series.length;++i){
var run=this.series[i];
for(var j=0;j<run.data.length;++j){
var _741=run.data[j];
if(_741!==null){
var v=typeof _741=="number"?_741:_741.y;
if(isNaN(v)){
v=0;
}
acc[j]+=v;
}
}
}
if(this.zoom&&!this.isDataDirty()){
return this.performZoom(dim,_740);
}
this.resetEvents();
this.dirty=this.isDirty();
if(this.dirty){
dojo.forEach(this.series,_73e);
this._eventSeries={};
this.cleanGroup();
var s=this.group;
df.forEachRev(this.series,function(item){
item.cleanGroup(s);
});
}
var t=this.chart.theme,f,gap,_742,ht=this._hScaler.scaler.getTransformerFromModel(this._hScaler),vt=this._vScaler.scaler.getTransformerFromModel(this._vScaler),_743=this.events();
f=dc.calculateBarSize(this._vScaler.bounds.scale,this.opt);
gap=f.gap;
_742=f.size;
for(var i=this.series.length-1;i>=0;--i){
var run=this.series[i];
if(!this.dirty&&!run.dirty){
t.skip();
this._reconnectEvents(run.name);
continue;
}
run.cleanGroup();
var _744=t.next("bar",[this.opt,run]),s=run.group,_745=new Array(acc.length);
for(var j=0;j<acc.length;++j){
var _741=run.data[j];
if(_741!==null){
var v=acc[j],_746=ht(v),_747=typeof _741!="number"?t.addMixin(_744,"bar",_741,true):t.post(_744,"bar");
if(_746>=1&&_742>=1){
var rect={x:_740.l,y:dim.height-_740.b-vt(j+1.5)+gap,width:_746,height:_742};
var _748=this._plotFill(_747.series.fill,dim,_740);
_748=this._shapeFill(_748,rect);
var _749=s.createRect(rect).setFill(_748).setStroke(_747.series.stroke);
run.dyn.fill=_749.getFill();
run.dyn.stroke=_749.getStroke();
if(_743){
var o={element:"bar",index:j,run:run,shape:_749,x:v,y:j+1.5};
this._connectEvents(o);
_745[j]=o;
}
if(this.animate){
this._animateBar(_749,_740.l,-_746);
}
}
}
}
this._eventSeries[run.name]=_745;
run.dirty=false;
for(var j=0;j<run.data.length;++j){
var _741=run.data[j];
if(_741!==null){
var v=typeof _741=="number"?_741:_741.y;
if(isNaN(v)){
v=0;
}
acc[j]-=v;
}
}
}
this.dirty=false;
return this;
}});
})();
}
if(!dojo._hasResource["dojox.charting.plot2d.ClusteredBars"]){
dojo._hasResource["dojox.charting.plot2d.ClusteredBars"]=true;
dojo.provide("dojox.charting.plot2d.ClusteredBars");
(function(){
var df=dojox.lang.functional,dc=dojox.charting.plot2d.common,_74a=df.lambda("item.purgeGroup()");
dojo.declare("dojox.charting.plot2d.ClusteredBars",dojox.charting.plot2d.Bars,{render:function(dim,_74b){
if(this.zoom&&!this.isDataDirty()){
return this.performZoom(dim,_74b);
}
this.resetEvents();
this.dirty=this.isDirty();
if(this.dirty){
dojo.forEach(this.series,_74a);
this._eventSeries={};
this.cleanGroup();
var s=this.group;
df.forEachRev(this.series,function(item){
item.cleanGroup(s);
});
}
var t=this.chart.theme,f,gap,_74c,_74d,ht=this._hScaler.scaler.getTransformerFromModel(this._hScaler),vt=this._vScaler.scaler.getTransformerFromModel(this._vScaler),_74e=Math.max(0,this._hScaler.bounds.lower),_74f=ht(_74e),_750=this.events();
f=dc.calculateBarSize(this._vScaler.bounds.scale,this.opt,this.series.length);
gap=f.gap;
_74c=_74d=f.size;
for(var i=this.series.length-1;i>=0;--i){
var run=this.series[i],_751=_74d*(this.series.length-i-1);
if(!this.dirty&&!run.dirty){
t.skip();
this._reconnectEvents(run.name);
continue;
}
run.cleanGroup();
var _752=t.next("bar",[this.opt,run]),s=run.group,_753=new Array(run.data.length);
for(var j=0;j<run.data.length;++j){
var _754=run.data[j];
if(_754!==null){
var v=typeof _754=="number"?_754:_754.y,hv=ht(v),_755=hv-_74f,w=Math.abs(_755),_756=typeof _754!="number"?t.addMixin(_752,"bar",_754,true):t.post(_752,"bar");
if(w>=1&&_74c>=1){
var rect={x:_74b.l+(v<_74e?hv:_74f),y:dim.height-_74b.b-vt(j+1.5)+gap+_751,width:w,height:_74c};
var _757=this._plotFill(_756.series.fill,dim,_74b);
_757=this._shapeFill(_757,rect);
var _758=s.createRect(rect).setFill(_757).setStroke(_756.series.stroke);
run.dyn.fill=_758.getFill();
run.dyn.stroke=_758.getStroke();
if(_750){
var o={element:"bar",index:j,run:run,shape:_758,x:v,y:j+1.5};
this._connectEvents(o);
_753[j]=o;
}
if(this.animate){
this._animateBar(_758,_74b.l+_74f,-_755);
}
}
}
}
this._eventSeries[run.name]=_753;
run.dirty=false;
}
this.dirty=false;
return this;
}});
})();
}
if(!dojo._hasResource["dojox.charting.plot2d.Grid"]){
dojo._hasResource["dojox.charting.plot2d.Grid"]=true;
dojo.provide("dojox.charting.plot2d.Grid");
(function(){
var du=dojox.lang.utils,dc=dojox.charting.plot2d.common;
dojo.declare("dojox.charting.plot2d.Grid",dojox.charting.Element,{defaultParams:{hAxis:"x",vAxis:"y",hMajorLines:true,hMinorLines:false,vMajorLines:true,vMinorLines:false,hStripes:"none",vStripes:"none",animate:null},optionalParams:{},constructor:function(_759,_75a){
this.opt=dojo.clone(this.defaultParams);
du.updateWithObject(this.opt,_75a);
this.hAxis=this.opt.hAxis;
this.vAxis=this.opt.vAxis;
this.dirty=true;
this.animate=this.opt.animate;
this.zoom=null,this.zoomQueue=[];
this.lastWindow={vscale:1,hscale:1,xoffset:0,yoffset:0};
},clear:function(){
this._hAxis=null;
this._vAxis=null;
this.dirty=true;
return this;
},setAxis:function(axis){
if(axis){
this[axis.vertical?"_vAxis":"_hAxis"]=axis;
}
return this;
},addSeries:function(run){
return this;
},getSeriesStats:function(){
return dojo.delegate(dc.defaultStats);
},initializeScalers:function(){
return this;
},isDirty:function(){
return this.dirty||this._hAxis&&this._hAxis.dirty||this._vAxis&&this._vAxis.dirty;
},performZoom:function(dim,_75b){
var vs=this._vAxis.scale||1,hs=this._hAxis.scale||1,_75c=dim.height-_75b.b,_75d=this._hAxis.getScaler().bounds,_75e=(_75d.from-_75d.lower)*_75d.scale,_75f=this._vAxis.getScaler().bounds,_760=(_75f.from-_75f.lower)*_75f.scale;
rVScale=vs/this.lastWindow.vscale,rHScale=hs/this.lastWindow.hscale,rXOffset=(this.lastWindow.xoffset-_75e)/((this.lastWindow.hscale==1)?hs:this.lastWindow.hscale),rYOffset=(_760-this.lastWindow.yoffset)/((this.lastWindow.vscale==1)?vs:this.lastWindow.vscale),shape=this.group,anim=dojox.gfx.fx.animateTransform(dojo.delegate({shape:shape,duration:1200,transform:[{name:"translate",start:[0,0],end:[_75b.l*(1-rHScale),_75c*(1-rVScale)]},{name:"scale",start:[1,1],end:[rHScale,rVScale]},{name:"original"},{name:"translate",start:[0,0],end:[rXOffset,rYOffset]}]},this.zoom));
dojo.mixin(this.lastWindow,{vscale:vs,hscale:hs,xoffset:_75e,yoffset:_760});
this.zoomQueue.push(anim);
dojo.connect(anim,"onEnd",this,function(){
this.zoom=null;
this.zoomQueue.shift();
if(this.zoomQueue.length>0){
this.zoomQueue[0].play();
}
});
if(this.zoomQueue.length==1){
this.zoomQueue[0].play();
}
return this;
},getRequiredColors:function(){
return 0;
},render:function(dim,_761){
if(this.zoom){
return this.performZoom(dim,_761);
}
this.dirty=this.isDirty();
if(!this.dirty){
return this;
}
this.cleanGroup();
var s=this.group,ta=this.chart.theme.axis;
try{
var _762=this._vAxis.getScaler(),vt=_762.scaler.getTransformerFromModel(_762),_763=this._vAxis.getTicks();
if(this.opt.hMinorLines){
dojo.forEach(_763.minor,function(tick){
var y=dim.height-_761.b-vt(tick.value);
var _764=s.createLine({x1:_761.l,y1:y,x2:dim.width-_761.r,y2:y}).setStroke(ta.minorTick);
if(this.animate){
this._animateGrid(_764,"h",_761.l,_761.r+_761.l-dim.width);
}
},this);
}
if(this.opt.hMajorLines){
dojo.forEach(_763.major,function(tick){
var y=dim.height-_761.b-vt(tick.value);
var _765=s.createLine({x1:_761.l,y1:y,x2:dim.width-_761.r,y2:y}).setStroke(ta.majorTick);
if(this.animate){
this._animateGrid(_765,"h",_761.l,_761.r+_761.l-dim.width);
}
},this);
}
}
catch(e){
}
try{
var _766=this._hAxis.getScaler(),ht=_766.scaler.getTransformerFromModel(_766),_763=this._hAxis.getTicks();
if(_763&&this.opt.vMinorLines){
dojo.forEach(_763.minor,function(tick){
var x=_761.l+ht(tick.value);
var _767=s.createLine({x1:x,y1:_761.t,x2:x,y2:dim.height-_761.b}).setStroke(ta.minorTick);
if(this.animate){
this._animateGrid(_767,"v",dim.height-_761.b,dim.height-_761.b-_761.t);
}
},this);
}
if(_763&&this.opt.vMajorLines){
dojo.forEach(_763.major,function(tick){
var x=_761.l+ht(tick.value);
var _768=s.createLine({x1:x,y1:_761.t,x2:x,y2:dim.height-_761.b}).setStroke(ta.majorTick);
if(this.animate){
this._animateGrid(_768,"v",dim.height-_761.b,dim.height-_761.b-_761.t);
}
},this);
}
}
catch(e){
}
this.dirty=false;
return this;
},_animateGrid:function(_769,type,_76a,size){
var _76b=type=="h"?[_76a,0]:[0,_76a];
var _76c=type=="h"?[1/size,1]:[1,1/size];
dojox.gfx.fx.animateTransform(dojo.delegate({shape:_769,duration:1200,transform:[{name:"translate",start:_76b,end:[0,0]},{name:"scale",start:_76c,end:[1,1]},{name:"original"}]},this.animate)).play();
}});
})();
}
if(!dojo._hasResource["dojo.regexp"]){
dojo._hasResource["dojo.regexp"]=true;
dojo.provide("dojo.regexp");
dojo.regexp.escapeString=function(str,_76d){
return str.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g,function(ch){
if(_76d&&_76d.indexOf(ch)!=-1){
return ch;
}
return "\\"+ch;
});
};
dojo.regexp.buildGroupRE=function(arr,re,_76e){
if(!(arr instanceof Array)){
return re(arr);
}
var b=[];
for(var i=0;i<arr.length;i++){
b.push(re(arr[i]));
}
return dojo.regexp.group(b.join("|"),_76e);
};
dojo.regexp.group=function(_76f,_770){
return "("+(_770?"?:":"")+_76f+")";
};
}
if(!dojo._hasResource["dojo.number"]){
dojo._hasResource["dojo.number"]=true;
dojo.provide("dojo.number");
dojo.number.format=function(_771,_772){
_772=dojo.mixin({},_772||{});
var _773=dojo.i18n.normalizeLocale(_772.locale),_774=dojo.i18n.getLocalization("dojo.cldr","number",_773);
_772.customs=_774;
var _775=_772.pattern||_774[(_772.type||"decimal")+"Format"];
if(isNaN(_771)||Math.abs(_771)==Infinity){
return null;
}
return dojo.number._applyPattern(_771,_775,_772);
};
dojo.number._numberPatternRE=/[#0,]*[#0](?:\.0*#*)?/;
dojo.number._applyPattern=function(_776,_777,_778){
_778=_778||{};
var _779=_778.customs.group,_77a=_778.customs.decimal,_77b=_777.split(";"),_77c=_77b[0];
_777=_77b[(_776<0)?1:0]||("-"+_77c);
if(_777.indexOf("%")!=-1){
_776*=100;
}else{
if(_777.indexOf("‰")!=-1){
_776*=1000;
}else{
if(_777.indexOf("¤")!=-1){
_779=_778.customs.currencyGroup||_779;
_77a=_778.customs.currencyDecimal||_77a;
_777=_777.replace(/\u00a4{1,3}/,function(_77d){
var prop=["symbol","currency","displayName"][_77d.length-1];
return _778[prop]||_778.currency||"";
});
}else{
if(_777.indexOf("E")!=-1){
throw new Error("exponential notation not supported");
}
}
}
}
var _77e=dojo.number._numberPatternRE;
var _77f=_77c.match(_77e);
if(!_77f){
throw new Error("unable to find a number expression in pattern: "+_777);
}
if(_778.fractional===false){
_778.places=0;
}
return _777.replace(_77e,dojo.number._formatAbsolute(_776,_77f[0],{decimal:_77a,group:_779,places:_778.places,round:_778.round}));
};
dojo.number.round=function(_780,_781,_782){
var _783=10/(_782||10);
return (_783*+_780).toFixed(_781)/_783;
};
if((0.9).toFixed()==0){
(function(){
var _784=dojo.number.round;
dojo.number.round=function(v,p,m){
var d=Math.pow(10,-p||0),a=Math.abs(v);
if(!v||a>=d||a*Math.pow(10,p+1)<5){
d=0;
}
return _784(v,p,m)+(v>0?d:-d);
};
})();
}
dojo.number._formatAbsolute=function(_785,_786,_787){
_787=_787||{};
if(_787.places===true){
_787.places=0;
}
if(_787.places===Infinity){
_787.places=6;
}
var _788=_786.split("."),_789=typeof _787.places=="string"&&_787.places.indexOf(","),_78a=_787.places;
if(_789){
_78a=_787.places.substring(_789+1);
}else{
if(!(_78a>=0)){
_78a=(_788[1]||[]).length;
}
}
if(!(_787.round<0)){
_785=dojo.number.round(_785,_78a,_787.round);
}
var _78b=String(Math.abs(_785)).split("."),_78c=_78b[1]||"";
if(_788[1]||_787.places){
if(_789){
_787.places=_787.places.substring(0,_789);
}
var pad=_787.places!==undefined?_787.places:(_788[1]&&_788[1].lastIndexOf("0")+1);
if(pad>_78c.length){
_78b[1]=dojo.string.pad(_78c,pad,"0",true);
}
if(_78a<_78c.length){
_78b[1]=_78c.substr(0,_78a);
}
}else{
if(_78b[1]){
_78b.pop();
}
}
var _78d=_788[0].replace(",","");
pad=_78d.indexOf("0");
if(pad!=-1){
pad=_78d.length-pad;
if(pad>_78b[0].length){
_78b[0]=dojo.string.pad(_78b[0],pad);
}
if(_78d.indexOf("#")==-1){
_78b[0]=_78b[0].substr(_78b[0].length-pad);
}
}
var _78e=_788[0].lastIndexOf(","),_78f,_790;
if(_78e!=-1){
_78f=_788[0].length-_78e-1;
var _791=_788[0].substr(0,_78e);
_78e=_791.lastIndexOf(",");
if(_78e!=-1){
_790=_791.length-_78e-1;
}
}
var _792=[];
for(var _793=_78b[0];_793;){
var off=_793.length-_78f;
_792.push((off>0)?_793.substr(off):_793);
_793=(off>0)?_793.slice(0,off):"";
if(_790){
_78f=_790;
delete _790;
}
}
_78b[0]=_792.reverse().join(_787.group||",");
return _78b.join(_787.decimal||".");
};
dojo.number.regexp=function(_794){
return dojo.number._parseInfo(_794).regexp;
};
dojo.number._parseInfo=function(_795){
_795=_795||{};
var _796=dojo.i18n.normalizeLocale(_795.locale),_797=dojo.i18n.getLocalization("dojo.cldr","number",_796),_798=_795.pattern||_797[(_795.type||"decimal")+"Format"],_799=_797.group,_79a=_797.decimal,_79b=1;
if(_798.indexOf("%")!=-1){
_79b/=100;
}else{
if(_798.indexOf("‰")!=-1){
_79b/=1000;
}else{
var _79c=_798.indexOf("¤")!=-1;
if(_79c){
_799=_797.currencyGroup||_799;
_79a=_797.currencyDecimal||_79a;
}
}
}
var _79d=_798.split(";");
if(_79d.length==1){
_79d.push("-"+_79d[0]);
}
var re=dojo.regexp.buildGroupRE(_79d,function(_79e){
_79e="(?:"+dojo.regexp.escapeString(_79e,".")+")";
return _79e.replace(dojo.number._numberPatternRE,function(_79f){
var _7a0={signed:false,separator:_795.strict?_799:[_799,""],fractional:_795.fractional,decimal:_79a,exponent:false},_7a1=_79f.split("."),_7a2=_795.places;
if(_7a1.length==1&&_79b!=1){
_7a1[1]="###";
}
if(_7a1.length==1||_7a2===0){
_7a0.fractional=false;
}else{
if(_7a2===undefined){
_7a2=_795.pattern?_7a1[1].lastIndexOf("0")+1:Infinity;
}
if(_7a2&&_795.fractional==undefined){
_7a0.fractional=true;
}
if(!_795.places&&(_7a2<_7a1[1].length)){
_7a2+=","+_7a1[1].length;
}
_7a0.places=_7a2;
}
var _7a3=_7a1[0].split(",");
if(_7a3.length>1){
_7a0.groupSize=_7a3.pop().length;
if(_7a3.length>1){
_7a0.groupSize2=_7a3.pop().length;
}
}
return "("+dojo.number._realNumberRegexp(_7a0)+")";
});
},true);
if(_79c){
re=re.replace(/([\s\xa0]*)(\u00a4{1,3})([\s\xa0]*)/g,function(_7a4,_7a5,_7a6,_7a7){
var prop=["symbol","currency","displayName"][_7a6.length-1],_7a8=dojo.regexp.escapeString(_795[prop]||_795.currency||"");
_7a5=_7a5?"[\\s\\xa0]":"";
_7a7=_7a7?"[\\s\\xa0]":"";
if(!_795.strict){
if(_7a5){
_7a5+="*";
}
if(_7a7){
_7a7+="*";
}
return "(?:"+_7a5+_7a8+_7a7+")?";
}
return _7a5+_7a8+_7a7;
});
}
return {regexp:re.replace(/[\xa0 ]/g,"[\\s\\xa0]"),group:_799,decimal:_79a,factor:_79b};
};
dojo.number.parse=function(_7a9,_7aa){
var info=dojo.number._parseInfo(_7aa),_7ab=(new RegExp("^"+info.regexp+"$")).exec(_7a9);
if(!_7ab){
return NaN;
}
var _7ac=_7ab[1];
if(!_7ab[1]){
if(!_7ab[2]){
return NaN;
}
_7ac=_7ab[2];
info.factor*=-1;
}
_7ac=_7ac.replace(new RegExp("["+info.group+"\\s\\xa0"+"]","g"),"").replace(info.decimal,".");
return _7ac*info.factor;
};
dojo.number._realNumberRegexp=function(_7ad){
_7ad=_7ad||{};
if(!("places" in _7ad)){
_7ad.places=Infinity;
}
if(typeof _7ad.decimal!="string"){
_7ad.decimal=".";
}
if(!("fractional" in _7ad)||/^0/.test(_7ad.places)){
_7ad.fractional=[true,false];
}
if(!("exponent" in _7ad)){
_7ad.exponent=[true,false];
}
if(!("eSigned" in _7ad)){
_7ad.eSigned=[true,false];
}
var _7ae=dojo.number._integerRegexp(_7ad),_7af=dojo.regexp.buildGroupRE(_7ad.fractional,function(q){
var re="";
if(q&&(_7ad.places!==0)){
re="\\"+_7ad.decimal;
if(_7ad.places==Infinity){
re="(?:"+re+"\\d+)?";
}else{
re+="\\d{"+_7ad.places+"}";
}
}
return re;
},true);
var _7b0=dojo.regexp.buildGroupRE(_7ad.exponent,function(q){
if(q){
return "([eE]"+dojo.number._integerRegexp({signed:_7ad.eSigned})+")";
}
return "";
});
var _7b1=_7ae+_7af;
if(_7af){
_7b1="(?:(?:"+_7b1+")|(?:"+_7af+"))";
}
return _7b1+_7b0;
};
dojo.number._integerRegexp=function(_7b2){
_7b2=_7b2||{};
if(!("signed" in _7b2)){
_7b2.signed=[true,false];
}
if(!("separator" in _7b2)){
_7b2.separator="";
}else{
if(!("groupSize" in _7b2)){
_7b2.groupSize=3;
}
}
var _7b3=dojo.regexp.buildGroupRE(_7b2.signed,function(q){
return q?"[-+]":"";
},true);
var _7b4=dojo.regexp.buildGroupRE(_7b2.separator,function(sep){
if(!sep){
return "(?:\\d+)";
}
sep=dojo.regexp.escapeString(sep);
if(sep==" "){
sep="\\s";
}else{
if(sep==" "){
sep="\\s\\xa0";
}
}
var grp=_7b2.groupSize,grp2=_7b2.groupSize2;
if(grp2){
var _7b5="(?:0|[1-9]\\d{0,"+(grp2-1)+"}(?:["+sep+"]\\d{"+grp2+"})*["+sep+"]\\d{"+grp+"})";
return ((grp-grp2)>0)?"(?:"+_7b5+"|(?:0|[1-9]\\d{0,"+(grp-1)+"}))":_7b5;
}
return "(?:0|[1-9]\\d{0,"+(grp-1)+"}(?:["+sep+"]\\d{"+grp+"})*)";
},true);
return _7b3+_7b4;
};
}
if(!dojo._hasResource["dojox.charting.plot2d.Pie"]){
dojo._hasResource["dojox.charting.plot2d.Pie"]=true;
dojo.provide("dojox.charting.plot2d.Pie");
(function(){
var df=dojox.lang.functional,du=dojox.lang.utils,dc=dojox.charting.plot2d.common,da=dojox.charting.axis2d.common,g=dojox.gfx,m=g.matrix,_7b6=0.2;
dojo.declare("dojox.charting.plot2d.Pie",[dojox.charting.Element,dojox.charting.plot2d._PlotEvents],{defaultParams:{labels:true,ticks:false,fixed:true,precision:1,labelOffset:20,labelStyle:"default",htmlLabels:true,radGrad:"native",fanSize:5,startAngle:0},optionalParams:{radius:0,stroke:{},outline:{},shadow:{},fill:{},font:"",fontColor:""},constructor:function(_7b7,_7b8){
this.opt=dojo.clone(this.defaultParams);
du.updateWithObject(this.opt,_7b8);
du.updateWithPattern(this.opt,_7b8,this.optionalParams);
this.run=null;
this.dyn=[];
},clear:function(){
this.dirty=true;
this.dyn=[];
this.run=null;
return this;
},setAxis:function(axis){
return this;
},addSeries:function(run){
this.run=run;
return this;
},getSeriesStats:function(){
return dojo.delegate(dc.defaultStats);
},initializeScalers:function(){
return this;
},getRequiredColors:function(){
return this.run?this.run.data.length:0;
},render:function(dim,_7b9){
if(!this.dirty){
return this;
}
this.resetEvents();
this.dirty=false;
this._eventSeries={};
this.cleanGroup();
var s=this.group,t=this.chart.theme;
if(!this.run||!this.run.data.length){
return this;
}
var rx=(dim.width-_7b9.l-_7b9.r)/2,ry=(dim.height-_7b9.t-_7b9.b)/2,r=Math.min(rx,ry),_7ba="font" in this.opt?this.opt.font:t.axis.font,size=_7ba?g.normalizedLength(g.splitFontString(_7ba).size):0,_7bb="fontColor" in this.opt?this.opt.fontColor:t.axis.fontColor,_7bc=m._degToRad(this.opt.startAngle),_7bd=_7bc,step,_7be,_7bf,_7c0,_7c1,_7c2,run=this.run.data,_7c3=this.events();
if(typeof run[0]=="number"){
_7be=df.map(run,"x ? Math.max(x, 0) : 0");
if(df.every(_7be,"<= 0")){
return this;
}
_7bf=df.map(_7be,"/this",df.foldl(_7be,"+",0));
if(this.opt.labels){
_7c0=dojo.map(_7bf,function(x){
return x>0?this._getLabel(x*100)+"%":"";
},this);
}
}else{
_7be=df.map(run,"x ? Math.max(x.y, 0) : 0");
if(df.every(_7be,"<= 0")){
return this;
}
_7bf=df.map(_7be,"/this",df.foldl(_7be,"+",0));
if(this.opt.labels){
_7c0=dojo.map(_7bf,function(x,i){
if(x<=0){
return "";
}
var v=run[i];
return "text" in v?v.text:this._getLabel(x*100)+"%";
},this);
}
}
var _7c4=df.map(run,function(v,i){
if(v===null||typeof v=="number"){
return t.next("slice",[this.opt,this.run],true);
}
return t.next("slice",[this.opt,this.run,v],true);
},this);
if(this.opt.labels){
_7c1=df.foldl1(df.map(_7c0,function(_7c5,i){
var font=_7c4[i].series.font;
return dojox.gfx._base._getTextBox(_7c5,{font:font}).w;
},this),"Math.max(a, b)")/2;
if(this.opt.labelOffset<0){
r=Math.min(rx-2*_7c1,ry-size)+this.opt.labelOffset;
}
_7c2=r-this.opt.labelOffset;
}
if("radius" in this.opt){
r=this.opt.radius;
_7c2=r-this.opt.labelOffset;
}
var _7c6={cx:_7b9.l+rx,cy:_7b9.t+ry,r:r};
this.dyn=[];
var _7c7=new Array(_7bf.length);
dojo.some(_7bf,function(_7c8,i){
if(_7c8<=0){
return false;
}
var v=run[i],_7c9=_7c4[i],_7ca;
if(_7c8>=1){
_7ca=this._plotFill(_7c9.series.fill,dim,_7b9);
_7ca=this._shapeFill(_7ca,{x:_7c6.cx-_7c6.r,y:_7c6.cy-_7c6.r,width:2*_7c6.r,height:2*_7c6.r});
_7ca=this._pseudoRadialFill(_7ca,{x:_7c6.cx,y:_7c6.cy},_7c6.r);
var _7cb=s.createCircle(_7c6).setFill(_7ca).setStroke(_7c9.series.stroke);
this.dyn.push({fill:_7ca,stroke:_7c9.series.stroke});
if(_7c3){
var o={element:"slice",index:i,run:this.run,shape:_7cb,x:i,y:typeof v=="number"?v:v.y,cx:_7c6.cx,cy:_7c6.cy,cr:r};
this._connectEvents(o);
_7c7[i]=o;
}
return true;
}
var end=_7bd+_7c8*2*Math.PI;
if(i+1==_7bf.length){
end=_7bc+2*Math.PI;
}
var step=end-_7bd,x1=_7c6.cx+r*Math.cos(_7bd),y1=_7c6.cy+r*Math.sin(_7bd),x2=_7c6.cx+r*Math.cos(end),y2=_7c6.cy+r*Math.sin(end);
var _7cc=m._degToRad(this.opt.fanSize);
if(_7c9.series.fill&&_7c9.series.fill.type==="radial"&&this.opt.radGrad==="fan"&&step>_7cc){
var _7cd=s.createGroup(),_7ce=Math.ceil(step/_7cc),_7cf=step/_7ce;
_7ca=this._shapeFill(_7c9.series.fill,{x:_7c6.cx-_7c6.r,y:_7c6.cy-_7c6.r,width:2*_7c6.r,height:2*_7c6.r});
for(var j=0;j<_7ce;++j){
var _7d0=j==0?x1:_7c6.cx+r*Math.cos(_7bd+(j-_7b6)*_7cf),_7d1=j==0?y1:_7c6.cy+r*Math.sin(_7bd+(j-_7b6)*_7cf),_7d2=j==_7ce-1?x2:_7c6.cx+r*Math.cos(_7bd+(j+1+_7b6)*_7cf),_7d3=j==_7ce-1?y2:_7c6.cy+r*Math.sin(_7bd+(j+1+_7b6)*_7cf),fan=_7cd.createPath({}).moveTo(_7c6.cx,_7c6.cy).lineTo(_7d0,_7d1).arcTo(r,r,0,_7cf>Math.PI,true,_7d2,_7d3).lineTo(_7c6.cx,_7c6.cy).closePath().setFill(this._pseudoRadialFill(_7ca,{x:_7c6.cx,y:_7c6.cy},r,_7bd+(j+0.5)*_7cf,_7bd+(j+0.5)*_7cf));
}
_7cd.createPath({}).moveTo(_7c6.cx,_7c6.cy).lineTo(x1,y1).arcTo(r,r,0,step>Math.PI,true,x2,y2).lineTo(_7c6.cx,_7c6.cy).closePath().setStroke(_7c9.series.stroke);
_7cb=_7cd;
}else{
_7cb=s.createPath({}).moveTo(_7c6.cx,_7c6.cy).lineTo(x1,y1).arcTo(r,r,0,step>Math.PI,true,x2,y2).lineTo(_7c6.cx,_7c6.cy).closePath().setStroke(_7c9.series.stroke);
var _7ca=_7c9.series.fill;
if(_7ca&&_7ca.type==="radial"){
_7ca=this._shapeFill(_7ca,{x:_7c6.cx-_7c6.r,y:_7c6.cy-_7c6.r,width:2*_7c6.r,height:2*_7c6.r});
if(this.opt.radGrad==="linear"){
_7ca=this._pseudoRadialFill(_7ca,{x:_7c6.cx,y:_7c6.cy},r,_7bd,end);
}
}else{
if(_7ca&&_7ca.type==="linear"){
_7ca=this._plotFill(_7ca,dim,_7b9);
_7ca=this._shapeFill(_7ca,_7cb.getBoundingBox());
}
}
_7cb.setFill(_7ca);
}
this.dyn.push({fill:_7ca,stroke:_7c9.series.stroke});
if(_7c3){
var o={element:"slice",index:i,run:this.run,shape:_7cb,x:i,y:typeof v=="number"?v:v.y,cx:_7c6.cx,cy:_7c6.cy,cr:r};
this._connectEvents(o);
_7c7[i]=o;
}
_7bd=end;
return false;
},this);
if(this.opt.labels){
_7bd=_7bc;
dojo.some(_7bf,function(_7d4,i){
if(_7d4<=0){
return false;
}
var _7d5=_7c4[i];
if(_7d4>=1){
var v=run[i],elem=da.createText[this.opt.htmlLabels&&dojox.gfx.renderer!="vml"?"html":"gfx"](this.chart,s,_7c6.cx,_7c6.cy+size/2,"middle",_7c0[i],_7d5.series.font,_7d5.series.fontColor);
if(this.opt.htmlLabels){
this.htmlElements.push(elem);
}
return true;
}
var end=_7bd+_7d4*2*Math.PI,v=run[i];
if(i+1==_7bf.length){
end=_7bc+2*Math.PI;
}
var _7d6=(_7bd+end)/2,x=_7c6.cx+_7c2*Math.cos(_7d6),y=_7c6.cy+_7c2*Math.sin(_7d6)+size/2;
var elem=da.createText[this.opt.htmlLabels&&dojox.gfx.renderer!="vml"?"html":"gfx"](this.chart,s,x,y,"middle",_7c0[i],_7d5.series.font,_7d5.series.fontColor);
if(this.opt.htmlLabels){
this.htmlElements.push(elem);
}
_7bd=end;
return false;
},this);
}
var esi=0;
this._eventSeries[this.run.name]=df.map(run,function(v){
return v<=0?null:_7c7[esi++];
});
return this;
},_getLabel:function(_7d7){
return this.opt.fixed?dojo.number.format(_7d7,{places:this.opt.precision}):_7d7.toString();
}});
})();
}
if(!dojo._hasResource["dojox.charting.plot2d.Bubble"]){
dojo._hasResource["dojox.charting.plot2d.Bubble"]=true;
dojo.provide("dojox.charting.plot2d.Bubble");
(function(){
var df=dojox.lang.functional,du=dojox.lang.utils,dc=dojox.charting.plot2d.common,_7d8=df.lambda("item.purgeGroup()");
dojo.declare("dojox.charting.plot2d.Bubble",dojox.charting.plot2d.Base,{defaultParams:{hAxis:"x",vAxis:"y",animate:null},optionalParams:{stroke:{},outline:{},shadow:{},fill:{},font:"",fontColor:""},constructor:function(_7d9,_7da){
this.opt=dojo.clone(this.defaultParams);
du.updateWithObject(this.opt,_7da);
this.series=[];
this.hAxis=this.opt.hAxis;
this.vAxis=this.opt.vAxis;
this.animate=this.opt.animate;
},render:function(dim,_7db){
if(this.zoom&&!this.isDataDirty()){
return this.performZoom(dim,_7db);
}
this.resetEvents();
this.dirty=this.isDirty();
if(this.dirty){
dojo.forEach(this.series,_7d8);
this._eventSeries={};
this.cleanGroup();
var s=this.group;
df.forEachRev(this.series,function(item){
item.cleanGroup(s);
});
}
var t=this.chart.theme,ht=this._hScaler.scaler.getTransformerFromModel(this._hScaler),vt=this._vScaler.scaler.getTransformerFromModel(this._vScaler),_7dc=this.events();
for(var i=this.series.length-1;i>=0;--i){
var run=this.series[i];
if(!this.dirty&&!run.dirty){
t.skip();
this._reconnectEvents(run.name);
continue;
}
run.cleanGroup();
if(!run.data.length){
run.dirty=false;
t.skip();
continue;
}
if(typeof run.data[0]=="number"){
console.warn("dojox.charting.plot2d.Bubble: the data in the following series cannot be rendered as a bubble chart; ",run);
continue;
}
var _7dd=t.next("circle",[this.opt,run]),s=run.group,_7de=dojo.map(run.data,function(v,i){
return v?{x:ht(v.x)+_7db.l,y:dim.height-_7db.b-vt(v.y),radius:this._vScaler.bounds.scale*(v.size/2)}:null;
},this);
var _7df=null,_7e0=null,_7e1=null;
if(_7dd.series.shadow){
_7e1=dojo.map(_7de,function(item){
if(item!==null){
var _7e2=t.addMixin(_7dd,"circle",item,true),_7e3=_7e2.series.shadow;
var _7e4=s.createCircle({cx:item.x+_7e3.dx,cy:item.y+_7e3.dy,r:item.radius}).setStroke(_7e3).setFill(_7e3.color);
if(this.animate){
this._animateBubble(_7e4,dim.height-_7db.b,item.radius);
}
return _7e4;
}
return null;
},this);
if(_7e1.length){
run.dyn.shadow=_7e1[_7e1.length-1].getStroke();
}
}
if(_7dd.series.outline){
_7e0=dojo.map(_7de,function(item){
if(item!==null){
var _7e5=t.addMixin(_7dd,"circle",item,true),_7e6=dc.makeStroke(_7e5.series.outline);
_7e6.width=2*_7e6.width+_7dd.series.stroke.width;
var _7e7=s.createCircle({cx:item.x,cy:item.y,r:item.radius}).setStroke(_7e6);
if(this.animate){
this._animateBubble(_7e7,dim.height-_7db.b,item.radius);
}
return _7e7;
}
return null;
},this);
if(_7e0.length){
run.dyn.outline=_7e0[_7e0.length-1].getStroke();
}
}
_7df=dojo.map(_7de,function(item){
if(item!==null){
var _7e8=t.addMixin(_7dd,"circle",item,true),rect={x:item.x-item.radius,y:item.y-item.radius,width:2*item.radius,height:2*item.radius};
var _7e9=this._plotFill(_7e8.series.fill,dim,_7db);
_7e9=this._shapeFill(_7e9,rect);
var _7ea=s.createCircle({cx:item.x,cy:item.y,r:item.radius}).setFill(_7e9).setStroke(_7e8.series.stroke);
if(this.animate){
this._animateBubble(_7ea,dim.height-_7db.b,item.radius);
}
return _7ea;
}
return null;
},this);
if(_7df.length){
run.dyn.fill=_7df[_7df.length-1].getFill();
run.dyn.stroke=_7df[_7df.length-1].getStroke();
}
if(_7dc){
var _7eb=new Array(_7df.length);
dojo.forEach(_7df,function(s,i){
if(s!==null){
var o={element:"circle",index:i,run:run,shape:s,outline:_7e0&&_7e0[i]||null,shadow:_7e1&&_7e1[i]||null,x:run.data[i].x,y:run.data[i].y,r:run.data[i].size/2,cx:_7de[i].x,cy:_7de[i].y,cr:_7de[i].radius};
this._connectEvents(o);
_7eb[i]=o;
}
},this);
this._eventSeries[run.name]=_7eb;
}else{
delete this._eventSeries[run.name];
}
run.dirty=false;
}
this.dirty=false;
return this;
},_animateBubble:function(_7ec,_7ed,size){
dojox.gfx.fx.animateTransform(dojo.delegate({shape:_7ec,duration:1200,transform:[{name:"translate",start:[0,_7ed],end:[0,0]},{name:"scale",start:[0,1/size],end:[1,1]},{name:"original"}]},this.animate)).play();
}});
})();
}
if(!dojo._hasResource["dojox.charting.plot2d.Candlesticks"]){
dojo._hasResource["dojox.charting.plot2d.Candlesticks"]=true;
dojo.provide("dojox.charting.plot2d.Candlesticks");
(function(){
var df=dojox.lang.functional,du=dojox.lang.utils,dc=dojox.charting.plot2d.common,_7ee=df.lambda("item.purgeGroup()");
dojo.declare("dojox.charting.plot2d.Candlesticks",dojox.charting.plot2d.Base,{defaultParams:{hAxis:"x",vAxis:"y",gap:2,animate:null},optionalParams:{minBarSize:1,maxBarSize:1,stroke:{},outline:{},shadow:{},fill:{},font:"",fontColor:""},constructor:function(_7ef,_7f0){
this.opt=dojo.clone(this.defaultParams);
du.updateWithObject(this.opt,_7f0);
du.updateWithPattern(this.opt,_7f0,this.optionalParams);
this.series=[];
this.hAxis=this.opt.hAxis;
this.vAxis=this.opt.vAxis;
this.animate=this.opt.animate;
},collectStats:function(_7f1){
var _7f2=dojo.delegate(dc.defaultStats);
for(var i=0;i<_7f1.length;i++){
var run=_7f1[i];
if(!run.data.length){
continue;
}
var _7f3=_7f2.vmin,_7f4=_7f2.vmax;
if(!("ymin" in run)||!("ymax" in run)){
dojo.forEach(run.data,function(val,idx){
if(val!==null){
var x=val.x||idx+1;
_7f2.hmin=Math.min(_7f2.hmin,x);
_7f2.hmax=Math.max(_7f2.hmax,x);
_7f2.vmin=Math.min(_7f2.vmin,val.open,val.close,val.high,val.low);
_7f2.vmax=Math.max(_7f2.vmax,val.open,val.close,val.high,val.low);
}
});
}
if("ymin" in run){
_7f2.vmin=Math.min(_7f3,run.ymin);
}
if("ymax" in run){
_7f2.vmax=Math.max(_7f4,run.ymax);
}
}
return _7f2;
},getSeriesStats:function(){
var _7f5=this.collectStats(this.series);
_7f5.hmin-=0.5;
_7f5.hmax+=0.5;
return _7f5;
},render:function(dim,_7f6){
if(this.zoom&&!this.isDataDirty()){
return this.performZoom(dim,_7f6);
}
this.resetEvents();
this.dirty=this.isDirty();
if(this.dirty){
dojo.forEach(this.series,_7ee);
this._eventSeries={};
this.cleanGroup();
var s=this.group;
df.forEachRev(this.series,function(item){
item.cleanGroup(s);
});
}
var t=this.chart.theme,f,gap,_7f7,ht=this._hScaler.scaler.getTransformerFromModel(this._hScaler),vt=this._vScaler.scaler.getTransformerFromModel(this._vScaler),_7f8=Math.max(0,this._vScaler.bounds.lower),_7f9=vt(_7f8),_7fa=this.events();
f=dc.calculateBarSize(this._hScaler.bounds.scale,this.opt);
gap=f.gap;
_7f7=f.size;
for(var i=this.series.length-1;i>=0;--i){
var run=this.series[i];
if(!this.dirty&&!run.dirty){
t.skip();
this._reconnectEvents(run.name);
continue;
}
run.cleanGroup();
var _7fb=t.next("candlestick",[this.opt,run]),s=run.group,_7fc=new Array(run.data.length);
for(var j=0;j<run.data.length;++j){
var v=run.data[j];
if(v!==null){
var _7fd=t.addMixin(_7fb,"candlestick",v,true);
var x=ht(v.x||(j+0.5))+_7f6.l+gap,y=dim.height-_7f6.b,open=vt(v.open),_7fe=vt(v.close),high=vt(v.high),low=vt(v.low);
if("mid" in v){
var mid=vt(v.mid);
}
if(low>high){
var tmp=high;
high=low;
low=tmp;
}
if(_7f7>=1){
var _7ff=open>_7fe;
var line={x1:_7f7/2,x2:_7f7/2,y1:y-high,y2:y-low},rect={x:0,y:y-Math.max(open,_7fe),width:_7f7,height:Math.max(_7ff?open-_7fe:_7fe-open,1)};
shape=s.createGroup();
shape.setTransform({dx:x,dy:0});
var _800=shape.createGroup();
_800.createLine(line).setStroke(_7fd.series.stroke);
_800.createRect(rect).setStroke(_7fd.series.stroke).setFill(_7ff?_7fd.series.fill:"white");
if("mid" in v){
_800.createLine({x1:(_7fd.series.stroke.width||1),x2:_7f7-(_7fd.series.stroke.width||1),y1:y-mid,y2:y-mid}).setStroke(_7ff?"white":_7fd.series.stroke);
}
run.dyn.fill=_7fd.series.fill;
run.dyn.stroke=_7fd.series.stroke;
if(_7fa){
var o={element:"candlestick",index:j,run:run,shape:_800,x:x,y:y-Math.max(open,_7fe),cx:_7f7/2,cy:(y-Math.max(open,_7fe))+(Math.max(_7ff?open-_7fe:_7fe-open,1)/2),width:_7f7,height:Math.max(_7ff?open-_7fe:_7fe-open,1),data:v};
this._connectEvents(o);
_7fc[j]=o;
}
}
if(this.animate){
this._animateCandlesticks(shape,y-low,high-low);
}
}
}
this._eventSeries[run.name]=_7fc;
run.dirty=false;
}
this.dirty=false;
return this;
},_animateCandlesticks:function(_801,_802,_803){
dojox.gfx.fx.animateTransform(dojo.delegate({shape:_801,duration:1200,transform:[{name:"translate",start:[0,_802-(_802/_803)],end:[0,0]},{name:"scale",start:[1,1/_803],end:[1,1]},{name:"original"}]},this.animate)).play();
}});
})();
}
if(!dojo._hasResource["dojox.charting.plot2d.OHLC"]){
dojo._hasResource["dojox.charting.plot2d.OHLC"]=true;
dojo.provide("dojox.charting.plot2d.OHLC");
(function(){
var df=dojox.lang.functional,du=dojox.lang.utils,dc=dojox.charting.plot2d.common,_804=df.lambda("item.purgeGroup()");
dojo.declare("dojox.charting.plot2d.OHLC",dojox.charting.plot2d.Base,{defaultParams:{hAxis:"x",vAxis:"y",gap:2,animate:null},optionalParams:{minBarSize:1,maxBarSize:1,stroke:{},outline:{},shadow:{},fill:{},font:"",fontColor:""},constructor:function(_805,_806){
this.opt=dojo.clone(this.defaultParams);
du.updateWithObject(this.opt,_806);
du.updateWithPattern(this.opt,_806,this.optionalParams);
this.series=[];
this.hAxis=this.opt.hAxis;
this.vAxis=this.opt.vAxis;
this.animate=this.opt.animate;
},collectStats:function(_807){
var _808=dojo.delegate(dc.defaultStats);
for(var i=0;i<_807.length;i++){
var run=_807[i];
if(!run.data.length){
continue;
}
var _809=_808.vmin,_80a=_808.vmax;
if(!("ymin" in run)||!("ymax" in run)){
dojo.forEach(run.data,function(val,idx){
if(val!==null){
var x=val.x||idx+1;
_808.hmin=Math.min(_808.hmin,x);
_808.hmax=Math.max(_808.hmax,x);
_808.vmin=Math.min(_808.vmin,val.open,val.close,val.high,val.low);
_808.vmax=Math.max(_808.vmax,val.open,val.close,val.high,val.low);
}
});
}
if("ymin" in run){
_808.vmin=Math.min(_809,run.ymin);
}
if("ymax" in run){
_808.vmax=Math.max(_80a,run.ymax);
}
}
return _808;
},getSeriesStats:function(){
var _80b=this.collectStats(this.series);
_80b.hmin-=0.5;
_80b.hmax+=0.5;
return _80b;
},render:function(dim,_80c){
if(this.zoom&&!this.isDataDirty()){
return this.performZoom(dim,_80c);
}
this.resetEvents();
this.dirty=this.isDirty();
if(this.dirty){
dojo.forEach(this.series,_804);
this._eventSeries={};
this.cleanGroup();
var s=this.group;
df.forEachRev(this.series,function(item){
item.cleanGroup(s);
});
}
var t=this.chart.theme,f,gap,_80d,ht=this._hScaler.scaler.getTransformerFromModel(this._hScaler),vt=this._vScaler.scaler.getTransformerFromModel(this._vScaler),_80e=Math.max(0,this._vScaler.bounds.lower),_80f=vt(_80e),_810=this.events();
f=dc.calculateBarSize(this._hScaler.bounds.scale,this.opt);
gap=f.gap;
_80d=f.size;
for(var i=this.series.length-1;i>=0;--i){
var run=this.series[i];
if(!this.dirty&&!run.dirty){
t.skip();
this._reconnectEvents(run.name);
continue;
}
run.cleanGroup();
var _811=t.next("candlestick",[this.opt,run]),s=run.group,_812=new Array(run.data.length);
for(var j=0;j<run.data.length;++j){
var v=run.data[j];
if(v!==null){
var _813=t.addMixin(_811,"candlestick",v,true);
var x=ht(v.x||(j+0.5))+_80c.l+gap,y=dim.height-_80c.b,open=vt(v.open),_814=vt(v.close),high=vt(v.high),low=vt(v.low);
if(low>high){
var tmp=high;
high=low;
low=tmp;
}
if(_80d>=1){
var hl={x1:_80d/2,x2:_80d/2,y1:y-high,y2:y-low},op={x1:0,x2:((_80d/2)+((_813.series.stroke.width||1)/2)),y1:y-open,y2:y-open},cl={x1:((_80d/2)-((_813.series.stroke.width||1)/2)),x2:_80d,y1:y-_814,y2:y-_814};
shape=s.createGroup();
shape.setTransform({dx:x,dy:0});
var _815=shape.createGroup();
_815.createLine(hl).setStroke(_813.series.stroke);
_815.createLine(op).setStroke(_813.series.stroke);
_815.createLine(cl).setStroke(_813.series.stroke);
run.dyn.stroke=_813.series.stroke;
if(_810){
var o={element:"candlestick",index:j,run:run,shape:_815,x:x,y:y-Math.max(open,_814),cx:_80d/2,cy:(y-Math.max(open,_814))+(Math.max(open>_814?open-_814:_814-open,1)/2),width:_80d,height:Math.max(open>_814?open-_814:_814-open,1),data:v};
this._connectEvents(o);
_812[j]=o;
}
}
if(this.animate){
this._animateOHLC(shape,y-low,high-low);
}
}
}
this._eventSeries[run.name]=_812;
run.dirty=false;
}
this.dirty=false;
return this;
},_animateOHLC:function(_816,_817,_818){
dojox.gfx.fx.animateTransform(dojo.delegate({shape:_816,duration:1200,transform:[{name:"translate",start:[0,_817-(_817/_818)],end:[0,0]},{name:"scale",start:[1,1/_818],end:[1,1]},{name:"original"}]},this.animate)).play();
}});
})();
}
if(!dojo._hasResource["dojox.charting.Chart2D"]){
dojo._hasResource["dojox.charting.Chart2D"]=true;
dojo.provide("dojox.charting.Chart2D");
(function(){
var df=dojox.lang.functional,dc=dojox.charting,_819=df.lambda("item.clear()"),_81a=df.lambda("item.purgeGroup()"),_81b=df.lambda("item.destroy()"),_81c=df.lambda("item.dirty = false"),_81d=df.lambda("item.dirty = true"),_81e=df.lambda("item.name");
dojo.declare("dojox.charting.Chart2D",null,{constructor:function(node,_81f){
if(!_81f){
_81f={};
}
this.margins=_81f.margins?_81f.margins:{l:10,t:10,r:10,b:10};
this.stroke=_81f.stroke;
this.fill=_81f.fill;
this.delayInMs=_81f.delayInMs||200;
this.theme=null;
this.axes={};
this.stack=[];
this.plots={};
this.series=[];
this.runs={};
this.dirty=true;
this.coords=null;
this.node=dojo.byId(node);
var box=dojo.marginBox(node);
this.surface=dojox.gfx.createSurface(this.node,box.w||400,box.h||300);
},destroy:function(){
dojo.forEach(this.series,_81b);
dojo.forEach(this.stack,_81b);
df.forIn(this.axes,_81b);
this.surface.destroy();
},getCoords:function(){
if(!this.coords){
this.coords=dojo.coords(this.node,true);
}
return this.coords;
},setTheme:function(_820){
this.theme=_820.clone();
this.dirty=true;
return this;
},addAxis:function(name,_821){
var axis;
if(!_821||!("type" in _821)){
axis=new dc.axis2d.Default(this,_821);
}else{
axis=typeof _821.type=="string"?new dc.axis2d[_821.type](this,_821):new _821.type(this,_821);
}
axis.name=name;
axis.dirty=true;
if(name in this.axes){
this.axes[name].destroy();
}
this.axes[name]=axis;
this.dirty=true;
return this;
},getAxis:function(name){
return this.axes[name];
},removeAxis:function(name){
if(name in this.axes){
this.axes[name].destroy();
delete this.axes[name];
this.dirty=true;
}
return this;
},addPlot:function(name,_822){
var plot;
if(!_822||!("type" in _822)){
plot=new dc.plot2d.Default(this,_822);
}else{
plot=typeof _822.type=="string"?new dc.plot2d[_822.type](this,_822):new _822.type(this,_822);
}
plot.name=name;
plot.dirty=true;
if(name in this.plots){
this.stack[this.plots[name]].destroy();
this.stack[this.plots[name]]=plot;
}else{
this.plots[name]=this.stack.length;
this.stack.push(plot);
}
this.dirty=true;
return this;
},removePlot:function(name){
if(name in this.plots){
var _823=this.plots[name];
delete this.plots[name];
this.stack[_823].destroy();
this.stack.splice(_823,1);
df.forIn(this.plots,function(idx,name,_824){
if(idx>_823){
_824[name]=idx-1;
}
});
this.dirty=true;
}
return this;
},getPlotOrder:function(){
return df.map(this.stack,_81e);
},setPlotOrder:function(_825){
var _826={},_827=df.filter(_825,function(name){
if(!(name in this.plots)||(name in _826)){
return false;
}
_826[name]=1;
return true;
},this);
if(_827.length<this.stack.length){
df.forEach(this.stack,function(plot){
var name=plot.name;
if(!(name in _826)){
_827.push(name);
}
});
}
var _828=df.map(_827,function(name){
return this.stack[this.plots[name]];
},this);
df.forEach(_828,function(plot,i){
this.plots[plot.name]=i;
},this);
this.stack=_828;
this.dirty=true;
return this;
},movePlotToFront:function(name){
if(name in this.plots){
var _829=this.plots[name];
if(_829){
var _82a=this.getPlotOrder();
_82a.splice(_829,1);
_82a.unshift(name);
return this.setPlotOrder(_82a);
}
}
return this;
},movePlotToBack:function(name){
if(name in this.plots){
var _82b=this.plots[name];
if(_82b<this.stack.length-1){
var _82c=this.getPlotOrder();
_82c.splice(_82b,1);
_82c.push(name);
return this.setPlotOrder(_82c);
}
}
return this;
},addSeries:function(name,data,_82d){
var run=new dc.Series(this,data,_82d);
run.name=name;
if(name in this.runs){
this.series[this.runs[name]].destroy();
this.series[this.runs[name]]=run;
}else{
this.runs[name]=this.series.length;
this.series.push(run);
}
this.dirty=true;
if(!("ymin" in run)&&"min" in run){
run.ymin=run.min;
}
if(!("ymax" in run)&&"max" in run){
run.ymax=run.max;
}
return this;
},removeSeries:function(name){
if(name in this.runs){
var _82e=this.runs[name],_82f=this.series[_82e].plot;
delete this.runs[name];
this.series[_82e].destroy();
this.series.splice(_82e,1);
df.forIn(this.runs,function(idx,name,runs){
if(idx>_82e){
runs[name]=idx-1;
}
});
this.dirty=true;
}
return this;
},updateSeries:function(name,data){
if(name in this.runs){
var run=this.series[this.runs[name]];
run.update(data);
this._invalidateDependentPlots(run.plot,false);
this._invalidateDependentPlots(run.plot,true);
}
return this;
},getSeriesOrder:function(_830){
return df.map(df.filter(this.series,function(run){
return run.plot==_830;
}),_81e);
},setSeriesOrder:function(_831){
var _832,_833={},_834=df.filter(_831,function(name){
if(!(name in this.runs)||(name in _833)){
return false;
}
var run=this.series[this.runs[name]];
if(_832){
if(run.plot!=_832){
return false;
}
}else{
_832=run.plot;
}
_833[name]=1;
return true;
},this);
df.forEach(this.series,function(run){
var name=run.name;
if(!(name in _833)&&run.plot==_832){
_834.push(name);
}
});
var _835=df.map(_834,function(name){
return this.series[this.runs[name]];
},this);
this.series=_835.concat(df.filter(this.series,function(run){
return run.plot!=_832;
}));
df.forEach(this.series,function(run,i){
this.runs[run.name]=i;
},this);
this.dirty=true;
return this;
},moveSeriesToFront:function(name){
if(name in this.runs){
var _836=this.runs[name],_837=this.getSeriesOrder(this.series[_836].plot);
if(name!=_837[0]){
_837.splice(_836,1);
_837.unshift(name);
return this.setSeriesOrder(_837);
}
}
return this;
},moveSeriesToBack:function(name){
if(name in this.runs){
var _838=this.runs[name],_839=this.getSeriesOrder(this.series[_838].plot);
if(name!=_839[_839.length-1]){
_839.splice(_838,1);
_839.push(name);
return this.setSeriesOrder(_839);
}
}
return this;
},resize:function(_83a,_83b){
var box;
switch(arguments.length){
case 0:
box=dojo.marginBox(this.node);
break;
case 1:
box=_83a;
break;
default:
box={w:_83a,h:_83b};
break;
}
dojo.marginBox(this.node,box);
this.surface.setDimensions(box.w,box.h);
this.dirty=true;
this.coords=null;
return this.render();
},getGeometry:function(){
var ret={};
df.forIn(this.axes,function(axis){
if(axis.initialized()){
ret[axis.name]={name:axis.name,vertical:axis.vertical,scaler:axis.scaler,ticks:axis.ticks};
}
});
return ret;
},setAxisWindow:function(name,_83c,_83d,zoom){
var axis=this.axes[name];
if(axis){
axis.setWindow(_83c,_83d);
dojo.forEach(this.stack,function(plot){
if(plot.hAxis==name||plot.vAxis==name){
plot.zoom=zoom;
}
});
}
return this;
},setWindow:function(sx,sy,dx,dy,zoom){
if(!("plotArea" in this)){
this.calculateGeometry();
}
df.forIn(this.axes,function(axis){
var _83e,_83f,_840=axis.getScaler().bounds,s=_840.span/(_840.upper-_840.lower);
if(axis.vertical){
_83e=sy;
_83f=dy/s/_83e;
}else{
_83e=sx;
_83f=dx/s/_83e;
}
axis.setWindow(_83e,_83f);
});
dojo.forEach(this.stack,function(plot){
plot.zoom=zoom;
});
return this;
},zoomIn:function(name,_841){
var axis=this.axes[name];
if(axis){
var _842,_843,_844=axis.getScaler().bounds;
var _845=Math.min(_841[0],_841[1]);
var _846=Math.max(_841[0],_841[1]);
_845=_841[0]<_844.lower?_844.lower:_845;
_846=_841[1]>_844.upper?_844.upper:_846;
_842=(_844.upper-_844.lower)/(_846-_845);
_843=_845-_844.lower;
this.setAxisWindow(name,_842,_843);
this.render();
}
},calculateGeometry:function(){
if(this.dirty){
return this.fullGeometry();
}
var _847=dojo.filter(this.stack,function(plot){
return plot.dirty||(plot.hAxis&&this.axes[plot.hAxis].dirty)||(plot.vAxis&&this.axes[plot.vAxis].dirty);
},this);
_848(_847,this.plotArea);
return this;
},fullGeometry:function(){
this._makeDirty();
dojo.forEach(this.stack,_819);
if(!this.theme){
this.setTheme(new dojox.charting.Theme(dojox.charting._def));
}
dojo.forEach(this.series,function(run){
if(!(run.plot in this.plots)){
var plot=new dc.plot2d.Default(this,{});
plot.name=run.plot;
this.plots[run.plot]=this.stack.length;
this.stack.push(plot);
}
this.stack[this.plots[run.plot]].addSeries(run);
},this);
dojo.forEach(this.stack,function(plot){
if(plot.hAxis){
plot.setAxis(this.axes[plot.hAxis]);
}
if(plot.vAxis){
plot.setAxis(this.axes[plot.vAxis]);
}
},this);
var dim=this.dim=this.surface.getDimensions();
dim.width=dojox.gfx.normalizedLength(dim.width);
dim.height=dojox.gfx.normalizedLength(dim.height);
df.forIn(this.axes,_819);
_848(this.stack,dim);
var _849=this.offsets={l:0,r:0,t:0,b:0};
df.forIn(this.axes,function(axis){
df.forIn(axis.getOffsets(),function(o,i){
_849[i]+=o;
});
});
df.forIn(this.margins,function(o,i){
_849[i]+=o;
});
this.plotArea={width:dim.width-_849.l-_849.r,height:dim.height-_849.t-_849.b};
df.forIn(this.axes,_819);
_848(this.stack,this.plotArea);
return this;
},render:function(){
if(this.theme){
this.theme.clear();
}
if(this.dirty){
return this.fullRender();
}
this.calculateGeometry();
df.forEachRev(this.stack,function(plot){
plot.render(this.dim,this.offsets);
},this);
df.forIn(this.axes,function(axis){
axis.render(this.dim,this.offsets);
},this);
this._makeClean();
if(this.surface.render){
this.surface.render();
}
return this;
},fullRender:function(){
this.fullGeometry();
var _84a=this.offsets,dim=this.dim;
dojo.forEach(this.series,_81a);
df.forIn(this.axes,_81a);
dojo.forEach(this.stack,_81a);
this.surface.clear();
var t=this.theme,fill=t.plotarea&&t.plotarea.fill,_84b=t.plotarea&&t.plotarea.stroke;
if(fill){
this.surface.createRect({x:_84a.l-1,y:_84a.t-1,width:dim.width-_84a.l-_84a.r+2,height:dim.height-_84a.t-_84a.b+2}).setFill(fill);
}
if(_84b){
this.surface.createRect({x:_84a.l,y:_84a.t,width:dim.width-_84a.l-_84a.r+1,height:dim.height-_84a.t-_84a.b+1}).setStroke(_84b);
}
df.foldr(this.stack,function(z,plot){
return plot.render(dim,_84a),0;
},0);
fill=this.fill!==undefined?this.fill:(t.chart&&t.chart.fill);
_84b=this.stroke!==undefined?this.stroke:(t.chart&&t.chart.stroke);
if(fill=="inherit"){
var node=this.node,fill=new dojo.Color(dojo.style(node,"backgroundColor"));
while(fill.a==0&&node!=document.documentElement){
fill=new dojo.Color(dojo.style(node,"backgroundColor"));
node=node.parentNode;
}
}
if(fill){
if(_84a.l){
this.surface.createRect({width:_84a.l,height:dim.height+1}).setFill(fill);
}
if(_84a.r){
this.surface.createRect({x:dim.width-_84a.r,width:_84a.r+1,height:dim.height+2}).setFill(fill);
}
if(_84a.t){
this.surface.createRect({width:dim.width+1,height:_84a.t}).setFill(fill);
}
if(_84a.b){
this.surface.createRect({y:dim.height-_84a.b,width:dim.width+1,height:_84a.b+2}).setFill(fill);
}
}
if(_84b){
this.surface.createRect({width:dim.width-1,height:dim.height-1}).setStroke(_84b);
}
df.forIn(this.axes,function(axis){
axis.render(dim,_84a);
});
this._makeClean();
if(this.surface.render){
this.surface.render();
}
return this;
},delayedRender:function(){
if(!this._delayedRenderHandle){
this._delayedRenderHandle=setTimeout(dojo.hitch(this,function(){
clearTimeout(this._delayedRenderHandle);
this._delayedRenderHandle=null;
this.render();
}),this.delayInMs);
}
return this;
},connectToPlot:function(name,_84c,_84d){
return name in this.plots?this.stack[this.plots[name]].connect(_84c,_84d):null;
},fireEvent:function(_84e,_84f,_850){
if(_84e in this.runs){
var _851=this.series[this.runs[_84e]].plot;
if(_851 in this.plots){
var plot=this.stack[this.plots[_851]];
if(plot){
plot.fireEvent(_84e,_84f,_850);
}
}
}
return this;
},_makeClean:function(){
dojo.forEach(this.axes,_81c);
dojo.forEach(this.stack,_81c);
dojo.forEach(this.series,_81c);
this.dirty=false;
},_makeDirty:function(){
dojo.forEach(this.axes,_81d);
dojo.forEach(this.stack,_81d);
dojo.forEach(this.series,_81d);
this.dirty=true;
},_invalidateDependentPlots:function(_852,_853){
if(_852 in this.plots){
var plot=this.stack[this.plots[_852]],axis,_854=_853?"vAxis":"hAxis";
if(plot[_854]){
axis=this.axes[plot[_854]];
if(axis&&axis.dependOnData()){
axis.dirty=true;
dojo.forEach(this.stack,function(p){
if(p[_854]&&p[_854]==plot[_854]){
p.dirty=true;
}
});
}
}else{
plot.dirty=true;
}
}
}});
function _855(_856){
return {min:_856.hmin,max:_856.hmax};
};
function _857(_858){
return {min:_858.vmin,max:_858.vmax};
};
function _859(_85a,h){
_85a.hmin=h.min;
_85a.hmax=h.max;
};
function _85b(_85c,v){
_85c.vmin=v.min;
_85c.vmax=v.max;
};
function _85d(_85e,_85f){
if(_85e&&_85f){
_85e.min=Math.min(_85e.min,_85f.min);
_85e.max=Math.max(_85e.max,_85f.max);
}
return _85e||_85f;
};
function _848(_860,_861){
var _862={},axes={};
dojo.forEach(_860,function(plot){
var _863=_862[plot.name]=plot.getSeriesStats();
if(plot.hAxis){
axes[plot.hAxis]=_85d(axes[plot.hAxis],_855(_863));
}
if(plot.vAxis){
axes[plot.vAxis]=_85d(axes[plot.vAxis],_857(_863));
}
});
dojo.forEach(_860,function(plot){
var _864=_862[plot.name];
if(plot.hAxis){
_859(_864,axes[plot.hAxis]);
}
if(plot.vAxis){
_85b(_864,axes[plot.vAxis]);
}
plot.initializeScalers(_861,_864);
});
};
})();
}
if(!dojo._hasResource["insight.charting._Extensions"]){
dojo._hasResource["insight.charting._Extensions"]=true;
dojo.provide("insight.charting._Extensions");
(function(){
var df=dojox.lang.functional,dc=dojox.charting,_865=df.lambda("item.clear()");
dojo.extend(dojox.charting.Chart2D,{delayedRender:function(_866){
if(!this._delayedRenderHandle){
this._delayedRenderHandle=setTimeout(dojo.hitch(this,this.render),this.delayInMs);
}else{
if(this._delayedRenderHandle&&_866){
this.render();
}
}
return this;
},render:function(){
if(this._delayedRenderHandle){
clearTimeout(this._delayedRenderHandle);
this._delayedRenderHandle=null;
}
if(this.theme){
this.theme.clear();
}
if(this.dirty){
return this.fullRender();
}
this.calculateGeometry();
df.forEachRev(this.stack,function(plot){
plot.render(this.dim,this.offsets);
},this);
df.forIn(this.axes,function(axis){
axis.render(this.dim,this.offsets);
},this);
this._makeClean();
if(this.surface.render){
this.surface.render();
}
return this;
},fullGeometry:function(){
this._makeDirty();
dojo.forEach(this.stack,_865);
if(!this.theme){
this.setTheme(new dojox.charting.Theme(dojox.charting._def));
}
dojo.forEach(this.series,function(run){
if(!(run.plot in this.plots)){
var plot=new dc.plot2d.Default(this,{});
plot.name=run.plot;
this.plots[run.plot]=this.stack.length;
this.stack.push(plot);
}
this.stack[this.plots[run.plot]].addSeries(run);
},this);
dojo.forEach(this.stack,function(plot){
if(plot.hAxis){
plot.setAxis(this.axes[plot.hAxis]);
}
if(plot.vAxis){
plot.setAxis(this.axes[plot.vAxis]);
}
},this);
var dim=this.dim=this.surface.getDimensions();
dim.width=dojox.gfx.normalizedLength(dim.width);
dim.height=dojox.gfx.normalizedLength(dim.height);
df.forIn(this.axes,_865);
_868(this.stack,dim);
var _867=this.offsets={l:0,r:0,t:0,b:0};
df.forIn(this.axes,function(axis){
df.forIn(axis.getOffsets(),function(o,i){
_867[i]=Math.max(_867[i],o);
});
});
df.forIn(this.margins,function(o,i){
_867[i]+=o;
});
this.plotArea={width:dim.width-_867.l-_867.r,height:dim.height-_867.t-_867.b};
df.forIn(this.axes,_865);
_868(this.stack,this.plotArea);
return this;
}});
function _869(_86a){
return {min:_86a.hmin,max:_86a.hmax};
};
function _86b(_86c){
return {min:_86c.vmin,max:_86c.vmax};
};
function _86d(_86e,h){
_86e.hmin=h.min;
_86e.hmax=h.max;
};
function _86f(_870,v){
_870.vmin=v.min;
_870.vmax=v.max;
};
function _871(_872,_873){
if(_872&&_873){
_872.min=Math.min(_872.min,_873.min);
_872.max=Math.max(_872.max,_873.max);
}
return _872||_873;
};
function _868(_874,_875){
var _876={},axes={};
dojo.forEach(_874,function(plot){
var _877=_876[plot.name]=plot.getSeriesStats();
if(plot.hAxis){
axes[plot.hAxis]=_871(axes[plot.hAxis],_869(_877));
}
if(plot.vAxis){
axes[plot.vAxis]=_871(axes[plot.vAxis],_86b(_877));
}
});
dojo.forEach(_874,function(plot){
var _878=_876[plot.name];
if(plot.hAxis){
_86d(_878,axes[plot.hAxis]);
}
if(plot.vAxis){
_86f(_878,axes[plot.vAxis]);
}
plot.initializeScalers(_875,_878);
});
};
})();
(function(){
var dc=dojox.charting,du=dojox.lang.utils,g=dojox.gfx,lin=dc.scaler.linear,_879=4,_87a=45;
dojo.extend(dojox.charting.axis2d.Default,{getOffsets:function(){
var s=this.scaler,_87b={l:0,r:0,t:0,b:0};
if(!s){
return _87b;
}
var o=this.opt,_87c=0,a,b,c,d,gl=dc.scaler.common.getNumericLabel,_87d=0,ma=s.major,mi=s.minor,ta=this.chart.theme.axis,_87e=o.font||(ta.majorTick&&ta.majorTick.font)||(ta.tick&&ta.tick.font),_87f=this.chart.theme.getTick("major",o),_880=this.chart.theme.getTick("minor",o),size=_87e?g.normalizedLength(g.splitFontString(_87e).size):0,_881=o.rotation%360,_882=o.leftBottom,cosr=Math.abs(Math.cos(_881*Math.PI/180)),sinr=Math.abs(Math.sin(_881*Math.PI/180));
if(_881<0){
_881+=360;
}
if(size){
if(o.maxLabelSize){
_87c=o.maxLabelSize;
}else{
if(this.labels){
_87c=this._groupLabelWidth(this.labels,_87e);
}else{
_87c=this._groupLabelWidth([o.majorTicks!==false?gl(ma.start,ma.prec,o):"",o.majorTicks!==false?gl(ma.start+ma.count*ma.tick,ma.prec,o):"",o.minorTicks!==false?gl(mi.start,mi.prec,o):"",o.minorTicks!==false?gl(mi.start+mi.count*mi.tick,mi.prec,o):""],_87e);
}
}
if(this.vertical){
var side=_882?"l":"r";
switch(_881){
case 0:
case 180:
_87b[side]=_87c;
_87b.t=_87b.b=size/2;
break;
case 90:
case 270:
_87b[side]=size;
_87b.t=_87b.b=_87c/2;
break;
default:
if(_881<=_87a||(180<_881&&_881<=(180+_87a))){
_87b[side]=size*sinr/2+_87c*cosr;
_87b[_882?"t":"b"]=size*cosr/2+_87c*sinr;
_87b[_882?"b":"t"]=size*cosr/2;
}else{
if(_881>(360-_87a)||(180>_881&&_881>(180-_87a))){
_87b[side]=size*sinr/2+_87c*cosr;
_87b[_882?"b":"t"]=size*cosr/2+_87c*sinr;
_87b[_882?"t":"b"]=size*cosr/2;
}else{
if(_881<90||(180<_881&&_881<270)){
_87b[side]=size*sinr+_87c*cosr;
_87b[_882?"t":"b"]=size*cosr+_87c*sinr;
}else{
_87b[side]=size*sinr+_87c*cosr;
_87b[_882?"b":"t"]=size*cosr+_87c*sinr;
}
}
}
break;
}
_87b[side]+=_879+Math.max(_87f.length,_880.length);
}else{
var side=_882?"b":"t";
switch(_881){
case 0:
case 180:
_87b[side]=size;
_87b.l=_87b.r=_87c/2;
break;
case 90:
case 270:
_87b[side]=_87c;
_87b.l=_87b.r=size/2;
break;
default:
if((90-_87a)<=_881&&_881<=90||(270-_87a)<=_881&&_881<=270){
_87b[side]=size*sinr/2+_87c*cosr;
_87b[_882?"r":"l"]=size*cosr/2+_87c*sinr;
_87b[_882?"l":"r"]=size*cosr/2;
}else{
if(90<=_881&&_881<=(90+_87a)||270<=_881&&_881<=(270+_87a)){
_87b[side]=size*sinr/2+_87c*cosr;
_87b[_882?"l":"r"]=size*cosr/2+_87c*sinr;
_87b[_882?"r":"l"]=size*cosr/2;
}else{
if(_881<_87a||(180<_881&&_881<(180-_87a))){
_87b[side]=size*sinr+_87c*cosr;
_87b[_882?"r":"l"]=size*cosr+_87c*sinr;
}else{
_87b[side]=size*sinr+_87c*cosr;
_87b[_882?"l":"r"]=size*cosr+_87c*sinr;
}
}
}
break;
}
_87b[side]+=_879+Math.max(_87f.length,_880.length);
}
}
if(_87c){
this._cachedLabelWidth=_87c;
}
return _87b;
}});
})();
(function(){
var df=dojox.lang.functional,du=dojox.lang.utils,dc=dojox.charting.plot2d.common,_883=df.lambda("item.purgeGroup()");
dojo.extend(dojox.charting.plot2d.Default,{render:function(dim,_884){
if(this.zoom&&!this.isDataDirty()){
return this.performZoom(dim,_884);
}
this.resetEvents();
this.dirty=this.isDirty();
if(this.dirty){
dojo.forEach(this.series,_883);
this._eventSeries={};
this.cleanGroup();
this.group.setTransform(null);
var s=this.group;
df.forEachRev(this.series,function(item){
item.cleanGroup(s);
});
}
var t=this.chart.theme,_885,_886,_887,_888,_889=this.events();
for(var i=this.series.length-1;i>=0;--i){
var run=this.series[i];
if(!this.dirty&&!run.dirty){
t.skip();
this._reconnectEvents(run.name);
continue;
}
run.cleanGroup();
if(!run.data.length){
run.dirty=false;
t.skip();
continue;
}
var _88a=t.next(this.opt.areas?"area":"line",[this.opt,run],true),s=run.group,_88b=[],_88c=[],rseg=null,_88d,_88e,_88f=null,_890=null,ht=this._hScaler.scaler.getTransformerFromModel(this._hScaler),vt=this._vScaler.scaler.getTransformerFromModel(this._vScaler),_891=this._eventSeries[run.name]=new Array(run.data.length);
for(var j=0;j<run.data.length;j++){
if(run.data[j]!=null){
if(!rseg){
rseg=[];
_88c.push(j);
_88b.push(rseg);
}
rseg.push(run.data[j]);
}else{
rseg=null;
}
}
if(_88b[0]&&typeof _88b[0][0]=="number"){
_88e=function(v,i){
return {x:ht(i+_88c[seg]+1)+_884.l,y:dim.height-_884.b-vt(v)};
};
}else{
_88e=function(v,i){
return {x:ht(v.x)+_884.l,y:dim.height-_884.b-vt(v.y)};
};
}
for(var seg=0;seg<_88b.length;seg++){
_88d=dojo.map(_88b[seg],_88e,this);
if(seg!=0){
_890=[_88f[_88f.length-1],_88d[0]];
}
_88f=_88d;
var _892=this.opt.tension?dc.curve(_88d,this.opt.tension):"";
if(this.opt.areas&&_88d.length>1){
var fill=_88a.series.fill;
var _893=dojo.clone(_88d);
if(this.opt.tension){
var _894="L"+_893[_893.length-1].x+","+(dim.height-_884.b)+" L"+_893[0].x+","+(dim.height-_884.b)+" L"+_893[0].x+","+_893[0].y;
run.dyn.fill=s.createPath(_892+" "+_894).setFill(fill).getFill();
}else{
_893.push({x:_88d[_88d.length-1].x,y:dim.height-_884.b});
_893.push({x:_88d[0].x,y:dim.height-_884.b});
_893.push(_88d[0]);
run.dyn.fill=s.createPolyline(_893).setFill(fill).getFill();
}
}
if(this.opt.lines||this.opt.markers){
_885=_88a.series.stroke;
_887=dojo.delegate({style:"dash"},_885);
if(_88a.series.outline){
_886=run.dyn.outline=dc.makeStroke(_88a.series.outline);
_886.width=2*_886.width+_885.width;
}
}
if(this.opt.markers){
run.dyn.marker=_88a.symbol;
}
var _895=null,_896=null,_897=null;
if(_885&&_88a.series.shadow&&_88d.length>1){
var _898=_88a.series.shadow,_899=dojo.map(_88d,function(c){
return {x:c.x+_898.dx,y:c.y+_898.dy};
});
if(this.opt.lines){
if(this.opt.tension){
run.dyn.shadow=s.createPath(dc.curve(_899,this.opt.tension)).setStroke(_898).getStroke();
}else{
run.dyn.shadow=s.createPolyline(_899).setStroke(_898).getStroke();
}
}
if(this.opt.markers&&_88a.marker.shadow){
_898=_88a.marker.shadow;
_897=dojo.map(_899,function(c){
return s.createPath("M"+c.x+" "+c.y+" "+_88a.symbol).setStroke(_898).setFill(_898.color);
},this);
}
}
if(this.opt.lines&&_88d.length>1){
if(_886){
if(this.opt.tension){
run.dyn.outline=s.createPath(_892).setStroke(_886).getStroke();
}else{
run.dyn.outline=s.createPolyline(_88d).setStroke(_886).getStroke();
}
}
if(this.opt.tension){
run.dyn.stroke=s.createPath(_892).setStroke(_885).getStroke();
}else{
run.dyn.stroke=s.createPolyline(_88d).setStroke(_885).getStroke();
}
}
if(this.opt.lines&&_890){
run.dyn.bstroke=s.createPolyline(_890).setStroke(_887).getStroke();
}
if(this.opt.lines&&this.opt.markers&&_88b.length===1&&_88d.length==1){
var path="M"+_88d[0].x+" "+_88d[0].y+" "+_88a.symbol;
if(_88a.marker.outline){
_886=dc.makeStroke(_88a.marker.outline);
_886.width=2*_886.width+(_88a.marker.stroke?_88a.marker.stroke.width:0);
s.createPath(path).setStroke(_886);
}
s.createPath(path).setStroke(_885).setFill(_885.color);
}
if(this.opt.markers){
_895=new Array(_88d.length);
_896=new Array(_88d.length);
_886=null;
if(_88a.marker.outline){
_886=dc.makeStroke(_88a.marker.outline);
_886.width=2*_886.width+(_88a.marker.stroke?_88a.marker.stroke.width:0);
}
dojo.forEach(_88d,function(c,i){
var path="M"+c.x+" "+c.y+" "+_88a.symbol;
if(_886){
_896[i]=s.createPath(path).setStroke(_886);
}
_895[i]=s.createPath(path).setStroke(_88a.marker.stroke).setFill(_88a.marker.fill);
},this);
if(_889){
dojo.forEach(_895,function(s,i){
var o={element:"marker",index:i+_88c[seg],run:run,shape:s,outline:_896[i]||null,shadow:_897&&_897[i]||null,cx:_88d[i].x,cy:_88d[i].y};
if(typeof _88b[seg][0]=="number"){
o.x=i+_88c[seg]+1;
o.y=_88b[seg][i];
}else{
o.x=_88b[seg][i].x;
o.y=_88b[seg][i].y;
}
this._connectEvents(o);
_891[i+_88c[seg]]=o;
},this);
}else{
delete this._eventSeries[run.name];
}
if(!run.dyn.stroke){
run.dyn.stroke=_885;
}
}
}
run.dirty=false;
}
if(this.animate){
var _89a=this.group;
dojox.gfx.fx.animateTransform(dojo.delegate({shape:_89a,duration:DEFAULT_ANIMATION_LENGTH,transform:[{name:"translate",start:[0,dim.height-_884.b],end:[0,0]},{name:"scale",start:[1,0],end:[1,1]},{name:"original"}]},this.animate)).play();
}
this.dirty=false;
return this;
}});
dojo.extend(dojox.charting.plot2d.Bars,{minSize:5,render:function(dim,_89b){
if(this.zoom&&!this.isDataDirty()){
return this.performZoom(dim,_89b);
}
this.dirty=this.isDirty();
this.resetEvents();
if(this.dirty){
dojo.forEach(this.series,_883);
this._eventSeries={};
this.cleanGroup();
var s=this.group;
df.forEachRev(this.series,function(item){
item.cleanGroup(s);
});
}
var t=this.chart.theme,f,gap,_89c,ht=this._hScaler.scaler.getTransformerFromModel(this._hScaler),vt=this._vScaler.scaler.getTransformerFromModel(this._vScaler),_89d=Math.max(0,this._hScaler.bounds.lower),_89e=ht(_89d),_89f=this.events();
f=dc.calculateBarSize(this._vScaler.bounds.scale,this.opt);
gap=f.gap;
_89c=f.size;
for(var i=this.series.length-1;i>=0;--i){
var run=this.series[i];
if(!this.dirty&&!run.dirty){
t.skip();
this._reconnectEvents(run.name);
continue;
}
run.cleanGroup();
var _8a0=t.next("bar",[this.opt,run]),s=run.group,_8a1=new Array(run.data.length);
for(var j=0;j<run.data.length;++j){
var _8a2=run.data[j];
if(_8a2!==null){
var v=typeof _8a2=="number"?_8a2:_8a2.y,hv=ht(v),_8a3=hv-_89e,w=Math.abs(_8a3),_8a4=typeof _8a2!="number"?t.addMixin(_8a0,"bar",_8a2,true):t.post(_8a0,"bar");
if(w>0&&_89c>0){
if(w<this.minSize){
hv+=(this.minSize-w)*_8a3/Math.abs(_8a3);
_8a3=hv-_89e;
w=Math.abs(_8a3);
}
var rect={x:_89b.l+(v<_89d?hv:_89e),y:dim.height-_89b.b-vt(j+1.5)+gap,width:w,height:_89c};
var _8a5=this._plotFill(_8a4.series.fill,dim,_89b);
_8a5=this._shapeFill(_8a5,rect);
var _8a6=s.createRect(rect).setFill(_8a5).setStroke(_8a4.series.stroke);
this.overrideShape(_8a6,{index:j,value:v});
var _8a7={x:rect.x,y:rect.y,height:rect.height,width:dim.width-_89b.l-_89b.r};
var mask=s.createRect(_8a7).setFill(new dojo.Color([0,0,0,0])).setStroke(new dojo.Color([0,0,0,0]));
run.dyn.fill=_8a6.getFill();
run.dyn.stroke=_8a6.getStroke();
if(_89f){
var o={element:"bar",index:j,run:run,shape:_8a6,eventMask:mask,x:v,y:j+1.5};
this._connectEvents(o);
_8a1[j]=o;
}
if(this.animate){
this._animateBar(_8a6,_89b.l+_89e,-w);
}
}
}
}
this._eventSeries[run.name]=_8a1;
run.dirty=false;
}
this.dirty=false;
return this;
},overrideShape:function(_8a8,_8a9){
}});
dojo.extend(dojox.charting.plot2d.Columns,{minSize:5,render:function(dim,_8aa){
if(this.zoom&&!this.isDataDirty()){
return this.performZoom(dim,_8aa);
}
this.resetEvents();
this.dirty=this.isDirty();
if(this.dirty){
dojo.forEach(this.series,_883);
this._eventSeries={};
this.cleanGroup();
var s=this.group;
df.forEachRev(this.series,function(item){
item.cleanGroup(s);
});
}
var t=this.chart.theme,f,gap,_8ab,ht=this._hScaler.scaler.getTransformerFromModel(this._hScaler),vt=this._vScaler.scaler.getTransformerFromModel(this._vScaler),_8ac=Math.max(0,this._vScaler.bounds.lower),_8ad=vt(_8ac),_8ae=this.events();
f=dc.calculateBarSize(this._hScaler.bounds.scale,this.opt);
gap=f.gap;
_8ab=f.size;
for(var i=this.series.length-1;i>=0;--i){
var run=this.series[i];
if(!this.dirty&&!run.dirty){
t.skip();
this._reconnectEvents(run.name);
continue;
}
run.cleanGroup();
var _8af=t.next("column",[this.opt,run]),s=run.group,_8b0=new Array(run.data.length);
for(var j=0;j<run.data.length;++j){
var _8b1=run.data[j];
if(_8b1!==null){
var v=typeof _8b1=="number"?_8b1:_8b1.y,vv=vt(v),_8b2=vv-_8ad,h=Math.abs(_8b2),_8b3=typeof _8b1!="number"?t.addMixin(_8af,"column",_8b1,true):t.post(_8af,"column");
if(_8ab>0&&h>0){
if(h<this.minSize){
vv+=(this.minSize-h)*_8b2/Math.abs(_8b2);
_8b2=vv-_8ad;
h=Math.abs(_8b2);
}
var rect={x:_8aa.l+ht(j+0.5)+gap,y:dim.height-_8aa.b-(v>_8ac?vv:_8ad),width:_8ab,height:h};
var _8b4=this._plotFill(_8b3.series.fill,dim,_8aa);
_8b4=this._shapeFill(_8b4,rect);
var _8b5=s.createRect(rect).setFill(_8b4).setStroke(_8b3.series.stroke);
this.overrideShape(_8b5,{index:j,value:v});
var _8b6={x:rect.x,width:rect.width,y:_8aa.t,height:dim.height-_8aa.t-_8aa.b};
var mask=s.createRect(_8b6).setFill(new dojo.Color([0,0,0,0])).setStroke(new dojo.Color([0,0,0,0]));
run.dyn.fill=_8b5.getFill();
run.dyn.stroke=_8b5.getStroke();
if(_8ae){
var o={element:"column",index:j,run:run,shape:_8b5,eventMask:mask,x:j+0.5,y:v};
this._connectEvents(o);
_8b0[j]=o;
}
if(this.animate){
this._animateColumn(_8b5,dim.height-_8aa.b-_8ad,h);
}
}
}
}
this._eventSeries[run.name]=_8b0;
run.dirty=false;
}
this.dirty=false;
return this;
},overrideShape:function(_8b7,_8b8){
}});
dojo.extend(dojox.charting.plot2d.Grid,{render:function(dim,_8b9){
if(this.zoom){
return this.performZoom(dim,_8b9);
}
this.dirty=this.isDirty();
if(!this.dirty){
return this;
}
this.cleanGroup();
var s=this.group,ta=this.chart.theme.axis;
try{
var _8ba=this._vAxis.getScaler(),vt=_8ba.scaler.getTransformerFromModel(_8ba),_8bb=this._vAxis.getTicks();
if(this.opt.hMinorLines){
dojo.forEach(_8bb.minor,function(tick){
var y=dim.height-_8b9.b-vt(tick.value);
var _8bc=s.createLine({x1:_8b9.l,y1:y,x2:dim.width-_8b9.r,y2:y}).setStroke(ta.minorTickLine||ta.minorTick||ta.line);
if(this.animate){
this._animateGrid(_8bc,"h",_8b9.l,_8b9.r+_8b9.l-dim.width);
}
},this);
}
if(this.opt.hMajorLines){
dojo.forEach(_8bb.major,function(tick){
var y=dim.height-_8b9.b-vt(tick.value);
var _8bd=s.createLine({x1:_8b9.l,y1:y,x2:dim.width-_8b9.r,y2:y}).setStroke(ta.majorTickLine||ta.majorTick||ta.line);
if(this.animate){
this._animateGrid(_8bd,"h",_8b9.l,_8b9.r+_8b9.l-dim.width);
}
},this);
}
}
catch(e){
}
try{
var _8be=this._hAxis.getScaler(),ht=_8be.scaler.getTransformerFromModel(_8be),_8bb=this._hAxis.getTicks();
if(_8bb&&this.opt.vMinorLines){
dojo.forEach(_8bb.minor,function(tick){
var x=_8b9.l+ht(tick.value);
var _8bf=s.createLine({x1:x,y1:_8b9.t,x2:x,y2:dim.height-_8b9.b}).setStroke(ta.minorTickLine||ta.minorTick||ta.line);
if(this.animate){
this._animateGrid(_8bf,"v",dim.height-_8b9.b,dim.height-_8b9.b-_8b9.t);
}
},this);
}
if(_8bb&&this.opt.vMajorLines){
dojo.forEach(_8bb.major,function(tick){
var x=_8b9.l+ht(tick.value);
var _8c0=s.createLine({x1:x,y1:_8b9.t,x2:x,y2:dim.height-_8b9.b}).setStroke(ta.majorTickLine||ta.majorTick||ta.line);
if(this.animate){
this._animateGrid(_8c0,"v",dim.height-_8b9.b,dim.height-_8b9.b-_8b9.t);
}
},this);
}
}
catch(e){
}
this.dirty=false;
return this;
}});
})();
}
if(!dojo._hasResource["insight.charting.themes.Spring"]){
dojo._hasResource["insight.charting.themes.Spring"]=true;
dojo.provide("insight.charting.themes.Spring");
(function(){
var dc=dojox.charting,t=insight.charting.themes;
t.Spring=new dc.Theme({chart:{stroke:null,fill:"#fff"},plotarea:{stroke:null,fill:"#f0f0f0"},axis:{stroke:{color:"#666",width:1},majorTick:{color:"#666",width:0.5,length:6},majorTickLine:{color:"#fff",width:0.8},minorTick:{color:"#666",width:0.5,length:3},minorTickLine:{color:"#fff",width:0.8},tick:{font:"normal normal normal 12px Tahoma,Arial,sans-serif",fontColor:"#666"}},series:{stroke:{width:1,color:"#666"},fill:new dojo.Color([102,102,102,0.6]),font:"normal normal normal 12px Tahoma,Arial,sans-serif",fontColor:"#333"},marker:{stroke:{width:2,color:new dojo.Color([51,51,51,0])},outline:{color:new dojo.Color([204,204,204,0])},fill:new dojo.Color([102,102,102,0]),font:"normal normal normal 12px Tahoma,Arial,sans-serif",fontColor:"#666"},colors:dc.Theme.defineColors({hue:82,saturation:60,low:40,high:88})});
t.Spring.next=function(_8c1,_8c2,_8c3){
var _8c4=dc.Theme.prototype.next.apply(this,arguments);
if(_8c1=="line"||_8c1=="area"){
_8c4.marker.stroke.width=1;
_8c4.marker.outline.color=new dojo.Color([255,255,255,0]);
_8c4.marker.outline.width=1;
}
return _8c4;
};
t.SpringLight=t.Spring.clone();
t.SpringLight.series.fill=new dojo.Color([102,102,102,0.2]);
t.SpringTransparent=t.Spring.clone();
t.SpringTransparent.plotarea.fill=new dojo.Color([240,240,240,0]);
})();
}
if(!dojo._hasResource["dojo.data.util.sorter"]){
dojo._hasResource["dojo.data.util.sorter"]=true;
dojo.provide("dojo.data.util.sorter");
dojo.data.util.sorter.basicComparator=function(a,b){
var r=-1;
if(a===null){
a=undefined;
}
if(b===null){
b=undefined;
}
if(a==b){
r=0;
}else{
if(a>b||a==null){
r=1;
}
}
return r;
};
dojo.data.util.sorter.createSortFunction=function(_8c5,_8c6){
var _8c7=[];
function _8c8(attr,dir,comp,s){
return function(_8c9,_8ca){
var a=s.getValue(_8c9,attr);
var b=s.getValue(_8ca,attr);
return dir*comp(a,b);
};
};
var _8cb;
var map=_8c6.comparatorMap;
var bc=dojo.data.util.sorter.basicComparator;
for(var i=0;i<_8c5.length;i++){
_8cb=_8c5[i];
var attr=_8cb.attribute;
if(attr){
var dir=(_8cb.descending)?-1:1;
var comp=bc;
if(map){
if(typeof attr!=="string"&&("toString" in attr)){
attr=attr.toString();
}
comp=map[attr]||bc;
}
_8c7.push(_8c8(attr,dir,comp,_8c6));
}
}
return function(rowA,rowB){
var i=0;
while(i<_8c7.length){
var ret=_8c7[i++](rowA,rowB);
if(ret!==0){
return ret;
}
}
return 0;
};
};
}
if(!dojo._hasResource["dojox.data.QueryReadStore"]){
dojo._hasResource["dojox.data.QueryReadStore"]=true;
dojo.provide("dojox.data.QueryReadStore");
dojo.declare("dojox.data.QueryReadStore",null,{url:"",requestMethod:"get",_className:"dojox.data.QueryReadStore",_items:[],_lastServerQuery:null,_numRows:-1,lastRequestHash:null,doClientPaging:false,doClientSorting:false,_itemsByIdentity:null,_identifier:null,_features:{"dojo.data.api.Read":true,"dojo.data.api.Identity":true},_labelAttr:"label",constructor:function(_8cc){
dojo.mixin(this,_8cc);
},getValue:function(item,_8cd,_8ce){
this._assertIsItem(item);
if(!dojo.isString(_8cd)){
throw new Error(this._className+".getValue(): Invalid attribute, string expected!");
}
if(!this.hasAttribute(item,_8cd)){
if(_8ce){
return _8ce;
}
}
return item.i[_8cd];
},getValues:function(item,_8cf){
this._assertIsItem(item);
var ret=[];
if(this.hasAttribute(item,_8cf)){
ret.push(item.i[_8cf]);
}
return ret;
},getAttributes:function(item){
this._assertIsItem(item);
var ret=[];
for(var i in item.i){
ret.push(i);
}
return ret;
},hasAttribute:function(item,_8d0){
return this.isItem(item)&&typeof item.i[_8d0]!="undefined";
},containsValue:function(item,_8d1,_8d2){
var _8d3=this.getValues(item,_8d1);
var len=_8d3.length;
for(var i=0;i<len;i++){
if(_8d3[i]==_8d2){
return true;
}
}
return false;
},isItem:function(_8d4){
if(_8d4){
return typeof _8d4.r!="undefined"&&_8d4.r==this;
}
return false;
},isItemLoaded:function(_8d5){
return this.isItem(_8d5);
},loadItem:function(args){
if(this.isItemLoaded(args.item)){
return;
}
},fetch:function(_8d6){
_8d6=_8d6||{};
if(!_8d6.store){
_8d6.store=this;
}
var self=this;
var _8d7=function(_8d8,_8d9){
if(_8d9.onError){
var _8da=_8d9.scope||dojo.global;
_8d9.onError.call(_8da,_8d8,_8d9);
}
};
var _8db=function(_8dc,_8dd,_8de){
var _8df=_8dd.abort||null;
var _8e0=false;
var _8e1=_8dd.start?_8dd.start:0;
if(self.doClientPaging==false){
_8e1=0;
}
var _8e2=_8dd.count?(_8e1+_8dd.count):_8dc.length;
_8dd.abort=function(){
_8e0=true;
if(_8df){
_8df.call(_8dd);
}
};
var _8e3=_8dd.scope||dojo.global;
if(!_8dd.store){
_8dd.store=self;
}
if(_8dd.onBegin){
_8dd.onBegin.call(_8e3,_8de,_8dd);
}
if(_8dd.sort&&self.doClientSorting){
_8dc.sort(dojo.data.util.sorter.createSortFunction(_8dd.sort,self));
}
if(_8dd.onItem){
for(var i=_8e1;(i<_8dc.length)&&(i<_8e2);++i){
var item=_8dc[i];
if(!_8e0){
_8dd.onItem.call(_8e3,item,_8dd);
}
}
}
if(_8dd.onComplete&&!_8e0){
var _8e4=null;
if(!_8dd.onItem){
_8e4=_8dc.slice(_8e1,_8e2);
}
_8dd.onComplete.call(_8e3,_8e4,_8dd);
}
};
this._fetchItems(_8d6,_8db,_8d7);
return _8d6;
},getFeatures:function(){
return this._features;
},close:function(_8e5){
},getLabel:function(item){
if(this._labelAttr&&this.isItem(item)){
return this.getValue(item,this._labelAttr);
}
return undefined;
},getLabelAttributes:function(item){
if(this._labelAttr){
return [this._labelAttr];
}
return null;
},_xhrFetchHandler:function(data,_8e6,_8e7,_8e8){
data=this._filterResponse(data);
if(data.label){
this._labelAttr=data.label;
}
var _8e9=data.numRows||-1;
this._items=[];
dojo.forEach(data.items,function(e){
this._items.push({i:e,r:this});
},this);
var _8ea=data.identifier;
this._itemsByIdentity={};
if(_8ea){
this._identifier=_8ea;
var i;
for(i=0;i<this._items.length;++i){
var item=this._items[i].i;
var _8eb=item[_8ea];
if(!this._itemsByIdentity[_8eb]){
this._itemsByIdentity[_8eb]=item;
}else{
throw new Error(this._className+":  The json data as specified by: ["+this.url+"] is malformed.  Items within the list have identifier: ["+_8ea+"].  Value collided: ["+_8eb+"]");
}
}
}else{
this._identifier=Number;
for(i=0;i<this._items.length;++i){
this._items[i].n=i;
}
}
_8e9=this._numRows=(_8e9===-1)?this._items.length:_8e9;
_8e7(this._items,_8e6,_8e9);
this._numRows=_8e9;
},_fetchItems:function(_8ec,_8ed,_8ee){
var _8ef=_8ec.serverQuery||_8ec.query||{};
if(!this.doClientPaging){
_8ef.start=_8ec.start||0;
if(_8ec.count){
_8ef.count=_8ec.count;
}
}
if(!this.doClientSorting&&_8ec.sort){
var _8f0=[];
dojo.forEach(_8ec.sort,function(sort){
if(sort&&sort.attribute){
_8f0.push((sort.descending?"-":"")+sort.attribute);
}
});
_8ef.sort=_8f0.join(",");
}
if(this.doClientPaging&&this._lastServerQuery!==null&&dojo.toJson(_8ef)==dojo.toJson(this._lastServerQuery)){
this._numRows=(this._numRows===-1)?this._items.length:this._numRows;
_8ed(this._items,_8ec,this._numRows);
}else{
var _8f1=this.requestMethod.toLowerCase()=="post"?dojo.xhrPost:dojo.xhrGet;
var _8f2=_8f1({url:this.url,handleAs:"json-comment-optional",content:_8ef});
_8f2.addCallback(dojo.hitch(this,function(data){
this._xhrFetchHandler(data,_8ec,_8ed,_8ee);
}));
_8f2.addErrback(function(_8f3){
_8ee(_8f3,_8ec);
});
this.lastRequestHash=new Date().getTime()+"-"+String(Math.random()).substring(2);
this._lastServerQuery=dojo.mixin({},_8ef);
}
},_filterResponse:function(data){
return data;
},_assertIsItem:function(item){
if(!this.isItem(item)){
throw new Error(this._className+": Invalid item argument.");
}
},_assertIsAttribute:function(_8f4){
if(typeof _8f4!=="string"){
throw new Error(this._className+": Invalid attribute argument ('"+_8f4+"').");
}
},fetchItemByIdentity:function(_8f5){
if(this._itemsByIdentity){
var item=this._itemsByIdentity[_8f5.identity];
if(!(item===undefined)){
if(_8f5.onItem){
var _8f6=_8f5.scope?_8f5.scope:dojo.global;
_8f5.onItem.call(_8f6,{i:item,r:this});
}
return;
}
}
var _8f7=function(_8f8,_8f9){
var _8fa=_8f5.scope?_8f5.scope:dojo.global;
if(_8f5.onError){
_8f5.onError.call(_8fa,_8f8);
}
};
var _8fb=function(_8fc,_8fd){
var _8fe=_8f5.scope?_8f5.scope:dojo.global;
try{
var item=null;
if(_8fc&&_8fc.length==1){
item=_8fc[0];
}
if(_8f5.onItem){
_8f5.onItem.call(_8fe,item);
}
}
catch(error){
if(_8f5.onError){
_8f5.onError.call(_8fe,error);
}
}
};
var _8ff={serverQuery:{id:_8f5.identity}};
this._fetchItems(_8ff,_8fb,_8f7);
},getIdentity:function(item){
var _900=null;
if(this._identifier===Number){
_900=item.n;
}else{
_900=item.i[this._identifier];
}
return _900;
},getIdentityAttributes:function(item){
return [this._identifier];
}});
}
if(!dojo._hasResource["insight.runtime"]){
dojo._hasResource["insight.runtime"]=true;
dojo.provide("insight.runtime");
insight.runtime.getXhrTimeout=function(name){
return 8000;
};
}
if(!dojo._hasResource["insight.charting.Chart"]){
dojo._hasResource["insight.charting.Chart"]=true;
dojo.provide("insight.charting.Chart");
dojo.declare("insight.charting.ChartStore",dojox.data.QueryReadStore,{_data:null,_loading:false,_deferedRequests:null,_xhrArgs:null,constructor:function(_901){
this.inherited(arguments);
this._xhrArgs={};
this._xhrArgs.timeout=_901.timeout||insight.runtime.getXhrTimeout("Chart");
this._xhrArgs.error=function(_902,_903){
dojo.publish("error/xhr",[_902,_903,_903.args.uid]);
};
this.url=this.urlTemplate.build(this.urlParams);
delete this.urlParams;
},getValue:function(){
var _904=this.inherited(arguments);
return _904!=null?_904:null;
},_fetchItems:function(_905,_906,_907){
_905.uid=this._generateUid();
if(this._loading){
this._deferedRequests.push({request:_905,fetchHandler:_906,errorHandler:_907});
}else{
if(this._data){
this._xhrFetchHandler(this._data,_905,_906,_907);
}else{
this._loading=true;
this._deferedRequests=[];
this._fetchItemsInternal(_905,_906,this._wrapErrorHandler(_907));
}
}
},_fetchItemsInternal:function(_908,_909,_90a){
var _90b=_908.serverQuery||_908.query||{};
if(!this.doClientPaging){
_90b.start=_908.start||0;
if(_908.count){
_90b.count=_908.count;
}
}
if(!this.doClientSorting&&_908.sort){
var _90c=[];
dojo.forEach(_908.sort,function(sort){
if(sort&&sort.attribute){
_90c.push((sort.descending?"-":"")+sort.attribute);
}
});
_90b.sort=_90c.join(",");
}
if(this.doClientPaging&&this._lastServerQuery!==null&&dojo.toJson(_90b)==dojo.toJson(this._lastServerQuery)){
this._numRows=(this._numRows===-1)?this._items.length:this._numRows;
_909(this._items,_908,this._numRows);
}else{
var _90d=this.requestMethod.toLowerCase()=="post"?dojo.xhrPost:dojo.xhrGet;
var _90e=_90d(dojo.delegate({url:this.url,handleAs:"json-comment-optional",content:_90b,uid:_908.uid},this._xhrArgs));
_90e.addCallback(dojo.hitch(this,function(data){
this._xhrFetchHandler(data,_908,_909,_90a);
}));
_90e.addErrback(function(_90f){
_90a(_90f,_908);
});
this.lastRequestHash=new Date().getTime()+"-"+String(Math.random()).substring(2);
this._lastServerQuery=dojo.mixin({},_90b);
}
},_xhrFetchHandler:function(data,_910,_911,_912){
var _913=this.getInherited(arguments);
this._data=data;
this._loading=false;
_913.call(this,data,_910,_911,this._wrapErrorHandler(_912));
dojo.forEach(this._deferedRequests,function(o){
_913.call(this,data,o.request,o.fetchHandler,this._wrapErrorHandler(o.errorHandler));
},this);
this._deferedRequests=null;
},_wrapErrorHandler:function(_914){
if(_914){
var _915=this;
return function(){
_915._storeError.apply(this,arguments);
_914.apply(this,arguments);
};
}else{
return this._xhrError;
}
},_storeError:function(_916,_917){
dojo.publish("error/store",["Unable to return requested data",_917.store.chart+"-"+_917.store.name,_917.uid]);
},_generateUid:function(){
return new Date().getTime()+":"+Math.floor(Math.random()*1000);
},setUrl:function(_918,_919){
this.urlTemplate=_918||this.urlTemplate;
this.url=this.urlTemplate.build(_919);
this._reset();
},_reset:function(){
this._data=null;
this._items=[];
this._itemsByIdentity=null;
this._numRows=-1;
}});
dojo.declare("insight.charting.Chart",dijit._Widget,{theme:insight.charting.themes.Spring,chart:null,stores:null,dataPoints:30,actions:null,gridHLines:true,gridVLines:true,margins:{l:10,t:10,r:10,b:10},postCreate:function(){
this.inherited(arguments);
this._responseTimeLabelFunc=dojo.hitch(this,this._responseTimeLabelFunc);
this._throughputLabelFunc=dojo.hitch(this,this._throughputLabelFunc);
this._errorRateLabelFunc=dojo.hitch(this,this._errorRateLabelFunc);
this._healthLabelFunc=dojo.hitch(this,this._healthLabelFunc);
this.stores={};
this.urlParams={};
this.actions={};
if(this.url){
this.addStore("main",this.url);
delete this.url;
}
this.chart=new dojox.charting.Chart2D(this.domNode,{margins:this.margins});
this.chart.parent=this;
this.chart.setTheme(this.theme);
this.chart.addPlot("grid",{type:dojox.charting.plot2d.Grid,hMajorLines:this.gridHLines,vMajorLines:this.gridVLines,vMinorLines:this.gridVLines});
this.subscribe("window/resize",this.resizeToFit);
this.connect(this,"onChartElementMouseOver",function(_91a){
Insight.pause(true);
});
this.connect(this,"onChartElementMouseOut",function(_91b){
Insight.play(true);
});
},startup:function(){
this.inherited(arguments);
this.chart.movePlotToBack("grid");
this.chart.render();
this.dims=this._dimensions(this.domNode);
},destroy:function(_91c){
dojox.lang.functional.forIn(this.actions,function(a){
a.destroy();
},this);
this.chart.destroy();
delete this.chart;
delete this.actions;
delete this.stores;
this.inherited(arguments);
},resizeToFit:function(){
if(!this.dims){
return;
}
var dims=this._dimensions(this.domNode);
if(!(dims.w==this.dims.w&&dims.h==this.dims.h)){
this.chart.resize(dims.w,dims.h);
}
this.dims=dims;
},_dimensions:function(_91d){
return {w:dojo.contentBox(_91d.parentNode).w,h:parseInt(dojo.style(this.domNode,"height"))};
},addPlotAction:function(plot,_91e,args){
var a=new _91e(this.chart,plot,args);
this.actions[a.declaredClass+"."+plot]=a;
},getPlotAction:function(plot,_91f){
return this.actions[_91f+"."+plot];
},addStore:function(name,url){
this.stores[name]=new insight.charting.ChartStore({urlTemplate:url,urlParams:this._urlParams(name),name:name,chart:this.id});
},_store:function(_920){
return dojo.isString(_920)?this.stores[_920]:_920;
},refresh:function(){
if(this.chart._delayedRenderHandle){
if(!this._delayedRenderHandle){
this._delayedRenderHandle=setTimeout(dojo.hitch(this,this.refresh),100);
}
return;
}
if(this._delayedRenderHandle){
clearTimeout(this._delayedRenderHandle);
this._delayedRenderHandle=null;
}
this.chart.delayedRender();
this.chart._makeDirty();
dojox.lang.functional.forIn(this.stores,function(_921,name){
var _922=false;
_921.setUrl(null,this._urlParams(name));
_921.fetch({scope:this,onComplete:function(){
if(_922){
console.error("runaway onComplete cycle aborted");
return;
}
_922=true;
dojo.forEach(this.chart.series,function(_923){
if(_923.source&&_923.source.fetch&&(_923.store==name||(!_923.store&&name=="main"))){
_923.source.fetch();
}
},this);
this.chart.render();
}});
},this);
},_urlParams:function(_924){
return dojo.mixin({dataPoints:this.dataPoints},this.urlParams.all,this.urlParams[_924]);
},urlParam:function(name,_925,_926){
if(!_926){
_926="all";
}
if(!this.urlParams[_926]){
this.urlParams[_926]={};
}
this.urlParams[_926][name]=_925;
return this;
},_registerChartEvents:function(_927){
this.chart.connectToPlot(_927,this,function(_928){
if(_928.type=="onmouseover"){
this.onChartElementMouseOver(_928);
}else{
if(_928.type=="onmouseout"){
this.onChartElementMouseOut(_928);
}else{
if(_928.type=="onclick"){
this.onChartElementClick(_928);
}else{
if(_928.type=="onplotreset"){
this.onChartPlotReset(_928);
}
}
}
}
});
},onChartElementMouseOver:dijit._connectOnUseEventHandler,onChartElementMouseOut:dijit._connectOnUseEventHandler,onChartElementClick:dijit._connectOnUseEventHandler,onChartPlotReset:dijit._connectOnUseEventHandler,_responseTimeLabelFunc:function(text,_929,_92a,axis){
if(!text){
return "unknown";
}
axis=this.chart.getAxis(axis||"y");
if(axis.scaler.bounds.upper<=1000){
return Math.floor(_929)+" ms";
}else{
var _92a=(axis.scaler.major.tick/100)%10==0?0:1;
return dojo.number.format(_929/1000,{places:_92a})+" s";
}
},_throughputLabelFunc:function(text,_92b,_92c,axis){
axis=this.chart.getAxis(axis||"y");
var _92c=axis.scaler.bounds.upper<=10?1:0;
return dojo.number.format(_92b,{places:_92c})+" tpm";
},_errorRateLabelFunc:function(text,_92d,_92e,axis){
return dojo.number.format(_92d,{type:"percent",places:2});
},_healthLabelFunc:function(text,_92f,_930,axis){
var _931;
if(isNaN(_92f)||_92f===null){
return "no sample";
}else{
if(_92f>=0.94){
_931="excellent";
}else{
if(_92f>=0.85){
_931="good";
}else{
if(_92f>=0.7){
_931="fair";
}else{
if(_92f>=0.5){
_931="poor";
}else{
_931="unacceptable";
}
}
}
}
}
return _931;
}});
}
if(!dojo._hasResource["insight.charting.action2d.FillHighlight"]){
dojo._hasResource["insight.charting.action2d.FillHighlight"]=true;
dojo.provide("insight.charting.action2d.FillHighlight");
(function(){
var _932=100,_933=75,_934=50,c=dojox.color,cc=function(_935){
return function(){
return _935;
};
},hl=function(_936){
var a=new c.Color(_936),x=a.toHsl();
if(x.s==0){
x.l=x.l<50?100:0;
}else{
x.s=_932;
if(x.l<_934){
x.l=_933;
}else{
if(x.l>_933){
x.l=_934;
}else{
x.l=x.l-_934>_933-x.l?_934:_933;
}
}
}
return c.fromHsl(x);
};
dojo.declare("insight.charting.action2d.FillHighlight",dojox.charting.action2d.Base,{defaultParams:{duration:400,easing:dojo.fx.easing.backOut},optionalParams:{highlight:"red"},constructor:function(_937,plot,_938){
var a=_938&&_938.highlight;
this.colorFun=a?(dojo.isFunction(a)?a:cc(a)):hl;
this.connect();
},process:function(o){
if(!o.shape||!(o.type in this.overOutEvents||o.originalEvent in this.overOutEvents)){
return;
}
dojo.forEach(o.plot.series,function(_939){
var i=o.plot._eventSeries[_939.name]&&o.plot._eventSeries[_939.name][o.index];
if(i){
this._process(dojo.delegate(o,{run:i.run,shape:i.shape}));
}
},this);
},_process:function(o){
var _93a=o.run.name,_93b=o.index,anim,_93c,_93d;
if(_93a in this.anim){
anim=this.anim[_93a][_93b];
}else{
this.anim[_93a]={};
}
if(anim){
anim.action.stop(true);
}else{
var _93e=o.shape.getFill();
if(!_93e||!(_93e instanceof dojo.Color)){
return;
}
this.anim[_93a][_93b]=anim={start:_93e,end:this.colorFun(_93e)};
}
var _93f=anim.start,end=anim.end;
if(o.type=="onmouseout"||o.originalEvent=="onmouseout"){
var t=_93f;
_93f=end;
end=t;
}
anim.action=dojox.gfx.fx.animateFill({shape:o.shape,duration:this.duration,easing:this.easing,color:{start:_93f,end:end}});
if(o.type=="onmouseout"||o.originalEvent=="onmouseout"){
var _940=dojo.connect(anim.action,"onEnd",this,function(){
dojo.disconnect(_940);
if(this.anim[_93a]){
delete this.anim[_93a][_93b];
}
});
}
anim.action.play();
}});
})();
}
if(!dojo._hasResource["insight.charting.action2d.PointerHover"]){
dojo._hasResource["insight.charting.action2d.PointerHover"]=true;
dojo.provide("insight.charting.action2d.PointerHover");
(function(){
dojo.declare("insight.charting.action2d.PointerHover",dojox.charting.action2d.Base,{defaultParams:{cursor:"pointer"},constructor:function(_941,plot,_942){
_942=_942||{};
this.cursor=_942.cursor||this.defaultParams.cursor;
this.connect();
},process:function(o){
if(!o.eventMask){
return;
}
var rn=o.eventMask.getNode();
if(!rn.getAttribute("cursor")){
rn.setAttribute("cursor",this.cursor);
}
}});
})();
}
if(!dojo._hasResource["dijit.Tooltip"]){
dojo._hasResource["dijit.Tooltip"]=true;
dojo.provide("dijit.Tooltip");
dojo.declare("dijit._MasterTooltip",[dijit._Widget,dijit._Templated],{duration:dijit.defaultDuration,templateString:dojo.cache("dijit","templates/Tooltip.html","<div class=\"dijitTooltip dijitTooltipLeft\" id=\"dojoTooltip\">\n\t<div class=\"dijitTooltipContainer dijitTooltipContents\" dojoAttachPoint=\"containerNode\" waiRole='alert'></div>\n\t<div class=\"dijitTooltipConnector\"></div>\n</div>\n"),postCreate:function(){
dojo.body().appendChild(this.domNode);
this.bgIframe=new dijit.BackgroundIframe(this.domNode);
this.fadeIn=dojo.fadeIn({node:this.domNode,duration:this.duration,onEnd:dojo.hitch(this,"_onShow")});
this.fadeOut=dojo.fadeOut({node:this.domNode,duration:this.duration,onEnd:dojo.hitch(this,"_onHide")});
},show:function(_943,_944,_945,rtl){
if(this.aroundNode&&this.aroundNode===_944){
return;
}
if(this.fadeOut.status()=="playing"){
this._onDeck=arguments;
return;
}
this.containerNode.innerHTML=_943;
var pos=dijit.placeOnScreenAroundElement(this.domNode,_944,dijit.getPopupAroundAlignment((_945&&_945.length)?_945:dijit.Tooltip.defaultPosition,!rtl),dojo.hitch(this,"orient"));
dojo.style(this.domNode,"opacity",0);
this.fadeIn.play();
this.isShowingNow=true;
this.aroundNode=_944;
},orient:function(node,_946,_947){
node.className="dijitTooltip "+{"BL-TL":"dijitTooltipBelow dijitTooltipABLeft","TL-BL":"dijitTooltipAbove dijitTooltipABLeft","BR-TR":"dijitTooltipBelow dijitTooltipABRight","TR-BR":"dijitTooltipAbove dijitTooltipABRight","BR-BL":"dijitTooltipRight","BL-BR":"dijitTooltipLeft"}[_946+"-"+_947];
},_onShow:function(){
if(dojo.isIE){
this.domNode.style.filter="";
}
},hide:function(_948){
if(this._onDeck&&this._onDeck[1]==_948){
this._onDeck=null;
}else{
if(this.aroundNode===_948){
this.fadeIn.stop();
this.isShowingNow=false;
this.aroundNode=null;
this.fadeOut.play();
}else{
}
}
},_onHide:function(){
this.domNode.style.cssText="";
this.containerNode.innerHTML="";
if(this._onDeck){
this.show.apply(this,this._onDeck);
this._onDeck=null;
}
}});
dijit.showTooltip=function(_949,_94a,_94b,rtl){
if(!dijit._masterTT){
dijit._masterTT=new dijit._MasterTooltip();
}
return dijit._masterTT.show(_949,_94a,_94b,rtl);
};
dijit.hideTooltip=function(_94c){
if(!dijit._masterTT){
dijit._masterTT=new dijit._MasterTooltip();
}
return dijit._masterTT.hide(_94c);
};
dojo.declare("dijit.Tooltip",dijit._Widget,{label:"",showDelay:400,connectId:[],position:[],constructor:function(){
this._nodeConnectionsById={};
},_setConnectIdAttr:function(_94d){
for(var _94e in this._nodeConnectionsById){
this.removeTarget(_94e);
}
dojo.forEach(dojo.isArrayLike(_94d)?_94d:[_94d],this.addTarget,this);
},_getConnectIdAttr:function(){
var ary=[];
for(var id in this._nodeConnectionsById){
ary.push(id);
}
return ary;
},addTarget:function(id){
var node=dojo.byId(id);
if(!node){
return;
}
if(node.id in this._nodeConnectionsById){
return;
}
this._nodeConnectionsById[node.id]=[this.connect(node,"onmouseenter","_onTargetMouseEnter"),this.connect(node,"onmouseleave","_onTargetMouseLeave"),this.connect(node,"onfocus","_onTargetFocus"),this.connect(node,"onblur","_onTargetBlur")];
},removeTarget:function(node){
var id=node.id||node;
if(id in this._nodeConnectionsById){
dojo.forEach(this._nodeConnectionsById[id],this.disconnect,this);
delete this._nodeConnectionsById[id];
}
},postCreate:function(){
dojo.addClass(this.domNode,"dijitTooltipData");
},startup:function(){
this.inherited(arguments);
var ids=this.connectId;
dojo.forEach(dojo.isArrayLike(ids)?ids:[ids],this.addTarget,this);
},_onTargetMouseEnter:function(e){
this._onHover(e);
},_onTargetMouseLeave:function(e){
this._onUnHover(e);
},_onTargetFocus:function(e){
this._focus=true;
this._onHover(e);
},_onTargetBlur:function(e){
this._focus=false;
this._onUnHover(e);
},_onHover:function(e){
if(!this._showTimer){
var _94f=e.target;
this._showTimer=setTimeout(dojo.hitch(this,function(){
this.open(_94f);
}),this.showDelay);
}
},_onUnHover:function(e){
if(this._focus){
return;
}
if(this._showTimer){
clearTimeout(this._showTimer);
delete this._showTimer;
}
this.close();
},open:function(_950){
if(this._showTimer){
clearTimeout(this._showTimer);
delete this._showTimer;
}
dijit.showTooltip(this.label||this.domNode.innerHTML,_950,this.position,!this.isLeftToRight());
this._connectNode=_950;
this.onShow(_950,this.position);
},close:function(){
if(this._connectNode){
dijit.hideTooltip(this._connectNode);
delete this._connectNode;
this.onHide();
}
if(this._showTimer){
clearTimeout(this._showTimer);
delete this._showTimer;
}
},onShow:function(_951,_952){
},onHide:function(){
},uninitialize:function(){
this.close();
this.inherited(arguments);
}});
dijit.Tooltip.defaultPosition=["after","before"];
}
if(!dojo._hasResource["dojox.lang.functional.scan"]){
dojo._hasResource["dojox.lang.functional.scan"]=true;
dojo.provide("dojox.lang.functional.scan");
(function(){
var d=dojo,df=dojox.lang.functional,_953={};
d.mixin(df,{scanl:function(a,f,z,o){
if(typeof a=="string"){
a=a.split("");
}
o=o||d.global;
f=df.lambda(f);
var t,n,i;
if(d.isArray(a)){
t=new Array((n=a.length)+1);
t[0]=z;
for(i=0;i<n;z=f.call(o,z,a[i],i,a),t[++i]=z){
}
}else{
if(typeof a.hasNext=="function"&&typeof a.next=="function"){
t=[z];
for(i=0;a.hasNext();t.push(z=f.call(o,z,a.next(),i++,a))){
}
}else{
t=[z];
for(i in a){
if(!(i in _953)){
t.push(z=f.call(o,z,a[i],i,a));
}
}
}
}
return t;
},scanl1:function(a,f,o){
if(typeof a=="string"){
a=a.split("");
}
o=o||d.global;
f=df.lambda(f);
var t,n,z,_954=true;
if(d.isArray(a)){
t=new Array(n=a.length);
t[0]=z=a[0];
for(var i=1;i<n;t[i]=z=f.call(o,z,a[i],i,a),++i){
}
}else{
if(typeof a.hasNext=="function"&&typeof a.next=="function"){
if(a.hasNext()){
t=[z=a.next()];
for(var i=1;a.hasNext();t.push(z=f.call(o,z,a.next(),i++,a))){
}
}
}else{
for(var i in a){
if(!(i in _953)){
if(_954){
t=[z=a[i]];
_954=false;
}else{
t.push(z=f.call(o,z,a[i],i,a));
}
}
}
}
}
return t;
},scanr:function(a,f,z,o){
if(typeof a=="string"){
a=a.split("");
}
o=o||d.global;
f=df.lambda(f);
var n=a.length,t=new Array(n+1),i=n;
t[n]=z;
for(;i>0;--i,z=f.call(o,z,a[i],i,a),t[i]=z){
}
return t;
},scanr1:function(a,f,o){
if(typeof a=="string"){
a=a.split("");
}
o=o||d.global;
f=df.lambda(f);
var n=a.length,t=new Array(n),z=a[n-1],i=n-1;
t[i]=z;
for(;i>0;--i,z=f.call(o,z,a[i],i,a),t[i]=z){
}
return t;
}});
})();
}
if(!dojo._hasResource["insight.charting.action2d.Tooltip"]){
dojo._hasResource["insight.charting.action2d.Tooltip"]=true;
dojo.provide("insight.charting.action2d.Tooltip");
(function(){
var _955=function(o){
var t=o.run&&o.run.data&&o.run.data[o.index];
if(t&&typeof t!="number"&&(t.tooltip||t.text)){
return t.tooltip||t.text;
}
if(o.element=="candlestick"){
return "<table cellpadding=\"1\" cellspacing=\"0\" border=\"0\" style=\"font-size:0.9em;\">"+"<tr><td>Open:</td><td align=\"right\"><strong>"+o.data.open+"</strong></td></tr>"+"<tr><td>High:</td><td align=\"right\"><strong>"+o.data.high+"</strong></td></tr>"+"<tr><td>Low:</td><td align=\"right\"><strong>"+o.data.low+"</strong></td></tr>"+"<tr><td>Close:</td><td align=\"right\"><strong>"+o.data.close+"</strong></td></tr>"+(o.data.mid!==undefined?"<tr><td>Mid:</td><td align=\"right\"><strong>"+o.data.mid+"</strong></td></tr>":"")+"</table>";
}
return o.element=="bar"?o.x:o.y;
};
var df=dojox.lang.functional,pi4=Math.PI/4,pi2=Math.PI/2;
dojo.declare("insight.charting.action2d.Tooltip",dojox.charting.action2d.Base,{defaultParams:{text:_955},optionalParams:{},constructor:function(_956,plot,_957){
this.text=_957&&_957.text?_957.text:_955;
this.connect();
},process:function(o){
if(o.type==="onplotreset"||o.type==="onmouseout"){
dijit.hideTooltip(this.aroundRect);
this.aroundRect=null;
return;
}
if(!o.shape||o.type!=="onmouseover"){
return;
}
var _958={type:"rect"},_959=["after","before"];
switch(o.element){
case "marker":
_958.x=o.cx;
_958.y=o.cy;
_958.width=_958.height=1;
break;
case "circle":
_958.x=o.cx-o.cr;
_958.y=o.cy-o.cr;
_958.width=_958.height=2*o.cr;
break;
case "column":
_959=["below","above"];
_958=dojo.clone(o.shape.getShape());
break;
case "bar":
_959=["before","after"];
_958=dojo.clone(o.shape.getShape());
break;
case "candlestick":
_958.x=o.x;
_958.y=o.y;
_958.width=o.width;
_958.height=o.height;
break;
default:
if(!this.angles){
if(typeof o.run.data[0]=="number"){
this.angles=df.map(df.scanl(o.run.data,"+",0),"* 2 * Math.PI / this",df.foldl(o.run.data,"+",0));
}else{
this.angles=df.map(df.scanl(o.run.data,"a + b.y",0),"* 2 * Math.PI / this",df.foldl(o.run.data,"a + b.y",0));
}
}
var _95a=(this.angles[o.index]+this.angles[o.index+1])/2;
_958.x=o.cx+o.cr*Math.cos(_95a);
_958.y=o.cy+o.cr*Math.sin(_95a);
_958.width=_958.height=1;
if(_95a<pi4){
}else{
if(_95a<pi2+pi4){
_959=["below","above"];
}else{
if(_95a<Math.PI+pi4){
_959=["before","after"];
}else{
if(_95a<2*Math.PI-pi4){
_959=["above","below"];
}
}
}
}
break;
}
var lt=dojo.coords(this.chart.node,true);
_958.x+=lt.x;
_958.y+=lt.y;
_958.x=Math.round(_958.x);
_958.y=Math.round(_958.y);
_958.width=Math.ceil(_958.width);
_958.height=Math.ceil(_958.height);
this.aroundRect=_958;
dijit.showTooltip(this.text(o),this.aroundRect,_959);
}});
})();
}
if(!dojo._hasResource["dojo.date"]){
dojo._hasResource["dojo.date"]=true;
dojo.provide("dojo.date");
dojo.date.getDaysInMonth=function(_95b){
var _95c=_95b.getMonth();
var days=[31,28,31,30,31,30,31,31,30,31,30,31];
if(_95c==1&&dojo.date.isLeapYear(_95b)){
return 29;
}
return days[_95c];
};
dojo.date.isLeapYear=function(_95d){
var year=_95d.getFullYear();
return !(year%400)||(!(year%4)&&!!(year%100));
};
dojo.date.getTimezoneName=function(_95e){
var str=_95e.toString();
var tz="";
var _95f;
var pos=str.indexOf("(");
if(pos>-1){
tz=str.substring(++pos,str.indexOf(")"));
}else{
var pat=/([A-Z\/]+) \d{4}$/;
if((_95f=str.match(pat))){
tz=_95f[1];
}else{
str=_95e.toLocaleString();
pat=/ ([A-Z\/]+)$/;
if((_95f=str.match(pat))){
tz=_95f[1];
}
}
}
return (tz=="AM"||tz=="PM")?"":tz;
};
dojo.date.compare=function(_960,_961,_962){
_960=new Date(+_960);
_961=new Date(+(_961||new Date()));
if(_962=="date"){
_960.setHours(0,0,0,0);
_961.setHours(0,0,0,0);
}else{
if(_962=="time"){
_960.setFullYear(0,0,0);
_961.setFullYear(0,0,0);
}
}
if(_960>_961){
return 1;
}
if(_960<_961){
return -1;
}
return 0;
};
dojo.date.add=function(date,_963,_964){
var sum=new Date(+date);
var _965=false;
var _966="Date";
switch(_963){
case "day":
break;
case "weekday":
var days,_967;
var mod=_964%5;
if(!mod){
days=(_964>0)?5:-5;
_967=(_964>0)?((_964-5)/5):((_964+5)/5);
}else{
days=mod;
_967=parseInt(_964/5);
}
var strt=date.getDay();
var adj=0;
if(strt==6&&_964>0){
adj=1;
}else{
if(strt==0&&_964<0){
adj=-1;
}
}
var trgt=strt+days;
if(trgt==0||trgt==6){
adj=(_964>0)?2:-2;
}
_964=(7*_967)+days+adj;
break;
case "year":
_966="FullYear";
_965=true;
break;
case "week":
_964*=7;
break;
case "quarter":
_964*=3;
case "month":
_965=true;
_966="Month";
break;
default:
_966="UTC"+_963.charAt(0).toUpperCase()+_963.substring(1)+"s";
}
if(_966){
sum["set"+_966](sum["get"+_966]()+_964);
}
if(_965&&(sum.getDate()<date.getDate())){
sum.setDate(0);
}
return sum;
};
dojo.date.difference=function(_968,_969,_96a){
_969=_969||new Date();
_96a=_96a||"day";
var _96b=_969.getFullYear()-_968.getFullYear();
var _96c=1;
switch(_96a){
case "quarter":
var m1=_968.getMonth();
var m2=_969.getMonth();
var q1=Math.floor(m1/3)+1;
var q2=Math.floor(m2/3)+1;
q2+=(_96b*4);
_96c=q2-q1;
break;
case "weekday":
var days=Math.round(dojo.date.difference(_968,_969,"day"));
var _96d=parseInt(dojo.date.difference(_968,_969,"week"));
var mod=days%7;
if(mod==0){
days=_96d*5;
}else{
var adj=0;
var aDay=_968.getDay();
var bDay=_969.getDay();
_96d=parseInt(days/7);
mod=days%7;
var _96e=new Date(_968);
_96e.setDate(_96e.getDate()+(_96d*7));
var _96f=_96e.getDay();
if(days>0){
switch(true){
case aDay==6:
adj=-1;
break;
case aDay==0:
adj=0;
break;
case bDay==6:
adj=-1;
break;
case bDay==0:
adj=-2;
break;
case (_96f+mod)>5:
adj=-2;
}
}else{
if(days<0){
switch(true){
case aDay==6:
adj=0;
break;
case aDay==0:
adj=1;
break;
case bDay==6:
adj=2;
break;
case bDay==0:
adj=1;
break;
case (_96f+mod)<0:
adj=2;
}
}
}
days+=adj;
days-=(_96d*2);
}
_96c=days;
break;
case "year":
_96c=_96b;
break;
case "month":
_96c=(_969.getMonth()-_968.getMonth())+(_96b*12);
break;
case "week":
_96c=parseInt(dojo.date.difference(_968,_969,"day")/7);
break;
case "day":
_96c/=24;
case "hour":
_96c/=60;
case "minute":
_96c/=60;
case "second":
_96c/=1000;
case "millisecond":
_96c*=_969.getTime()-_968.getTime();
}
return Math.round(_96c);
};
}
if(!dojo._hasResource["dojo.cldr.supplemental"]){
dojo._hasResource["dojo.cldr.supplemental"]=true;
dojo.provide("dojo.cldr.supplemental");
dojo.cldr.supplemental.getFirstDayOfWeek=function(_970){
var _971={mv:5,af:6,bh:6,dj:6,dz:6,eg:6,er:6,et:6,iq:6,ir:6,jo:6,ke:6,kw:6,ly:6,ma:6,om:6,qa:6,sa:6,sd:6,so:6,tn:6,ye:6,ar:0,as:0,az:0,bw:0,ca:0,cn:0,fo:0,ge:0,gl:0,gu:0,hk:0,ie:0,il:0,"in":0,is:0,jm:0,jp:0,kg:0,kr:0,la:0,mh:0,mn:0,mo:0,mp:0,mt:0,nz:0,ph:0,pk:0,sg:0,sy:0,th:0,tt:0,tw:0,um:0,us:0,uz:0,vi:0,zw:0};
var _972=dojo.cldr.supplemental._region(_970);
var dow=_971[_972];
return (dow===undefined)?1:dow;
};
dojo.cldr.supplemental._region=function(_973){
_973=dojo.i18n.normalizeLocale(_973);
var tags=_973.split("-");
var _974=tags[1];
if(!_974){
_974={de:"de",en:"us",es:"es",fi:"fi",fr:"fr",he:"il",hu:"hu",it:"it",ja:"jp",ko:"kr",nl:"nl",pt:"br",sv:"se",zh:"cn"}[tags[0]];
}else{
if(_974.length==4){
_974=tags[2];
}
}
return _974;
};
dojo.cldr.supplemental.getWeekend=function(_975){
var _976={"in":0,af:4,dz:4,ir:4,om:4,sa:4,ye:4,ae:5,bh:5,eg:5,il:5,iq:5,jo:5,kw:5,ly:5,ma:5,qa:5,sd:5,sy:5,tn:5};
var _977={af:5,dz:5,ir:5,om:5,sa:5,ye:5,ae:6,bh:5,eg:6,il:6,iq:6,jo:6,kw:6,ly:6,ma:6,qa:6,sd:6,sy:6,tn:6};
var _978=dojo.cldr.supplemental._region(_975);
var _979=_976[_978];
var end=_977[_978];
if(_979===undefined){
_979=6;
}
if(end===undefined){
end=0;
}
return {start:_979,end:end};
};
}
if(!dojo._hasResource["dojo.date.locale"]){
dojo._hasResource["dojo.date.locale"]=true;
dojo.provide("dojo.date.locale");
(function(){
function _97a(_97b,_97c,_97d,_97e){
return _97e.replace(/([a-z])\1*/ig,function(_97f){
var s,pad,c=_97f.charAt(0),l=_97f.length,_980=["abbr","wide","narrow"];
switch(c){
case "G":
s=_97c[(l<4)?"eraAbbr":"eraNames"][_97b.getFullYear()<0?0:1];
break;
case "y":
s=_97b.getFullYear();
switch(l){
case 1:
break;
case 2:
if(!_97d.fullYear){
s=String(s);
s=s.substr(s.length-2);
break;
}
default:
pad=true;
}
break;
case "Q":
case "q":
s=Math.ceil((_97b.getMonth()+1)/3);
pad=true;
break;
case "M":
var m=_97b.getMonth();
if(l<3){
s=m+1;
pad=true;
}else{
var _981=["months","format",_980[l-3]].join("-");
s=_97c[_981][m];
}
break;
case "w":
var _982=0;
s=dojo.date.locale._getWeekOfYear(_97b,_982);
pad=true;
break;
case "d":
s=_97b.getDate();
pad=true;
break;
case "D":
s=dojo.date.locale._getDayOfYear(_97b);
pad=true;
break;
case "E":
var d=_97b.getDay();
if(l<3){
s=d+1;
pad=true;
}else{
var _983=["days","format",_980[l-3]].join("-");
s=_97c[_983][d];
}
break;
case "a":
var _984=(_97b.getHours()<12)?"am":"pm";
s=_97c["dayPeriods-format-wide-"+_984];
break;
case "h":
case "H":
case "K":
case "k":
var h=_97b.getHours();
switch(c){
case "h":
s=(h%12)||12;
break;
case "H":
s=h;
break;
case "K":
s=(h%12);
break;
case "k":
s=h||24;
break;
}
pad=true;
break;
case "m":
s=_97b.getMinutes();
pad=true;
break;
case "s":
s=_97b.getSeconds();
pad=true;
break;
case "S":
s=Math.round(_97b.getMilliseconds()*Math.pow(10,l-3));
pad=true;
break;
case "v":
case "z":
s=dojo.date.locale._getZone(_97b,true,_97d);
if(s){
break;
}
l=4;
case "Z":
var _985=dojo.date.locale._getZone(_97b,false,_97d);
var tz=[(_985<=0?"+":"-"),dojo.string.pad(Math.floor(Math.abs(_985)/60),2),dojo.string.pad(Math.abs(_985)%60,2)];
if(l==4){
tz.splice(0,0,"GMT");
tz.splice(3,0,":");
}
s=tz.join("");
break;
default:
throw new Error("dojo.date.locale.format: invalid pattern char: "+_97e);
}
if(pad){
s=dojo.string.pad(s,l);
}
return s;
});
};
dojo.date.locale._getZone=function(_986,_987,_988){
if(_987){
return dojo.date.getTimezoneName(_986);
}else{
return _986.getTimezoneOffset();
}
};
dojo.date.locale.format=function(_989,_98a){
_98a=_98a||{};
var _98b=dojo.i18n.normalizeLocale(_98a.locale),_98c=_98a.formatLength||"short",_98d=dojo.date.locale._getGregorianBundle(_98b),str=[],_98e=dojo.hitch(this,_97a,_989,_98d,_98a);
if(_98a.selector=="year"){
return _98f(_98d["dateFormatItem-yyyy"]||"yyyy",_98e);
}
var _990;
if(_98a.selector!="date"){
_990=_98a.timePattern||_98d["timeFormat-"+_98c];
if(_990){
str.push(_98f(_990,_98e));
}
}
if(_98a.selector!="time"){
_990=_98a.datePattern||_98d["dateFormat-"+_98c];
if(_990){
str.push(_98f(_990,_98e));
}
}
return str.length==1?str[0]:_98d["dateTimeFormat-"+_98c].replace(/\{(\d+)\}/g,function(_991,key){
return str[key];
});
};
dojo.date.locale.regexp=function(_992){
return dojo.date.locale._parseInfo(_992).regexp;
};
dojo.date.locale._parseInfo=function(_993){
_993=_993||{};
var _994=dojo.i18n.normalizeLocale(_993.locale),_995=dojo.date.locale._getGregorianBundle(_994),_996=_993.formatLength||"short",_997=_993.datePattern||_995["dateFormat-"+_996],_998=_993.timePattern||_995["timeFormat-"+_996],_999;
if(_993.selector=="date"){
_999=_997;
}else{
if(_993.selector=="time"){
_999=_998;
}else{
_999=_995["dateTimeFormat-"+_996].replace(/\{(\d+)\}/g,function(_99a,key){
return [_998,_997][key];
});
}
}
var _99b=[],re=_98f(_999,dojo.hitch(this,_99c,_99b,_995,_993));
return {regexp:re,tokens:_99b,bundle:_995};
};
dojo.date.locale.parse=function(_99d,_99e){
var info=dojo.date.locale._parseInfo(_99e),_99f=info.tokens,_9a0=info.bundle,re=new RegExp("^"+info.regexp+"$",info.strict?"":"i"),_9a1=re.exec(_99d);
if(!_9a1){
return null;
}
var _9a2=["abbr","wide","narrow"],_9a3=[1970,0,1,0,0,0,0],amPm="",_9a4=dojo.every(_9a1,function(v,i){
if(!i){
return true;
}
var _9a5=_99f[i-1];
var l=_9a5.length;
switch(_9a5.charAt(0)){
case "y":
if(l!=2&&_99e.strict){
_9a3[0]=v;
}else{
if(v<100){
v=Number(v);
var year=""+new Date().getFullYear(),_9a6=year.substring(0,2)*100,_9a7=Math.min(Number(year.substring(2,4))+20,99),num=(v<_9a7)?_9a6+v:_9a6-100+v;
_9a3[0]=num;
}else{
if(_99e.strict){
return false;
}
_9a3[0]=v;
}
}
break;
case "M":
if(l>2){
var _9a8=_9a0["months-format-"+_9a2[l-3]].concat();
if(!_99e.strict){
v=v.replace(".","").toLowerCase();
_9a8=dojo.map(_9a8,function(s){
return s.replace(".","").toLowerCase();
});
}
v=dojo.indexOf(_9a8,v);
if(v==-1){
return false;
}
}else{
v--;
}
_9a3[1]=v;
break;
case "E":
case "e":
var days=_9a0["days-format-"+_9a2[l-3]].concat();
if(!_99e.strict){
v=v.toLowerCase();
days=dojo.map(days,function(d){
return d.toLowerCase();
});
}
v=dojo.indexOf(days,v);
if(v==-1){
return false;
}
break;
case "D":
_9a3[1]=0;
case "d":
_9a3[2]=v;
break;
case "a":
var am=_99e.am||_9a0["dayPeriods-format-wide-am"],pm=_99e.pm||_9a0["dayPeriods-format-wide-pm"];
if(!_99e.strict){
var _9a9=/\./g;
v=v.replace(_9a9,"").toLowerCase();
am=am.replace(_9a9,"").toLowerCase();
pm=pm.replace(_9a9,"").toLowerCase();
}
if(_99e.strict&&v!=am&&v!=pm){
return false;
}
amPm=(v==pm)?"p":(v==am)?"a":"";
break;
case "K":
if(v==24){
v=0;
}
case "h":
case "H":
case "k":
if(v>23){
return false;
}
_9a3[3]=v;
break;
case "m":
_9a3[4]=v;
break;
case "s":
_9a3[5]=v;
break;
case "S":
_9a3[6]=v;
}
return true;
});
var _9aa=+_9a3[3];
if(amPm==="p"&&_9aa<12){
_9a3[3]=_9aa+12;
}else{
if(amPm==="a"&&_9aa==12){
_9a3[3]=0;
}
}
var _9ab=new Date(_9a3[0],_9a3[1],_9a3[2],_9a3[3],_9a3[4],_9a3[5],_9a3[6]);
if(_99e.strict){
_9ab.setFullYear(_9a3[0]);
}
var _9ac=_99f.join(""),_9ad=_9ac.indexOf("d")!=-1,_9ae=_9ac.indexOf("M")!=-1;
if(!_9a4||(_9ae&&_9ab.getMonth()>_9a3[1])||(_9ad&&_9ab.getDate()>_9a3[2])){
return null;
}
if((_9ae&&_9ab.getMonth()<_9a3[1])||(_9ad&&_9ab.getDate()<_9a3[2])){
_9ab=dojo.date.add(_9ab,"hour",1);
}
return _9ab;
};
function _98f(_9af,_9b0,_9b1,_9b2){
var _9b3=function(x){
return x;
};
_9b0=_9b0||_9b3;
_9b1=_9b1||_9b3;
_9b2=_9b2||_9b3;
var _9b4=_9af.match(/(''|[^'])+/g),_9b5=_9af.charAt(0)=="'";
dojo.forEach(_9b4,function(_9b6,i){
if(!_9b6){
_9b4[i]="";
}else{
_9b4[i]=(_9b5?_9b1:_9b0)(_9b6.replace(/''/g,"'"));
_9b5=!_9b5;
}
});
return _9b2(_9b4.join(""));
};
function _99c(_9b7,_9b8,_9b9,_9ba){
_9ba=dojo.regexp.escapeString(_9ba);
if(!_9b9.strict){
_9ba=_9ba.replace(" a"," ?a");
}
return _9ba.replace(/([a-z])\1*/ig,function(_9bb){
var s,c=_9bb.charAt(0),l=_9bb.length,p2="",p3="";
if(_9b9.strict){
if(l>1){
p2="0"+"{"+(l-1)+"}";
}
if(l>2){
p3="0"+"{"+(l-2)+"}";
}
}else{
p2="0?";
p3="0{0,2}";
}
switch(c){
case "y":
s="\\d{2,4}";
break;
case "M":
s=(l>2)?"\\S+?":p2+"[1-9]|1[0-2]";
break;
case "D":
s=p2+"[1-9]|"+p3+"[1-9][0-9]|[12][0-9][0-9]|3[0-5][0-9]|36[0-6]";
break;
case "d":
s="3[01]|[12]\\d|"+p2+"[1-9]";
break;
case "w":
s=p2+"[1-9]|[1-4][0-9]|5[0-3]";
break;
case "E":
s="\\S+";
break;
case "h":
s=p2+"[1-9]|1[0-2]";
break;
case "k":
s=p2+"\\d|1[01]";
break;
case "H":
s=p2+"\\d|1\\d|2[0-3]";
break;
case "K":
s=p2+"[1-9]|1\\d|2[0-4]";
break;
case "m":
case "s":
s="[0-5]\\d";
break;
case "S":
s="\\d{"+l+"}";
break;
case "a":
var am=_9b9.am||_9b8["dayPeriods-format-wide-am"],pm=_9b9.pm||_9b8["dayPeriods-format-wide-pm"];
if(_9b9.strict){
s=am+"|"+pm;
}else{
s=am+"|"+pm;
if(am!=am.toLowerCase()){
s+="|"+am.toLowerCase();
}
if(pm!=pm.toLowerCase()){
s+="|"+pm.toLowerCase();
}
if(s.indexOf(".")!=-1){
s+="|"+s.replace(/\./g,"");
}
}
s=s.replace(/\./g,"\\.");
break;
default:
s=".*";
}
if(_9b7){
_9b7.push(_9bb);
}
return "("+s+")";
}).replace(/[\xa0 ]/g,"[\\s\\xa0]");
};
})();
(function(){
var _9bc=[];
dojo.date.locale.addCustomFormats=function(_9bd,_9be){
_9bc.push({pkg:_9bd,name:_9be});
};
dojo.date.locale._getGregorianBundle=function(_9bf){
var _9c0={};
dojo.forEach(_9bc,function(desc){
var _9c1=dojo.i18n.getLocalization(desc.pkg,desc.name,_9bf);
_9c0=dojo.mixin(_9c0,_9c1);
},this);
return _9c0;
};
})();
dojo.date.locale.addCustomFormats("dojo.cldr","gregorian");
dojo.date.locale.getNames=function(item,type,_9c2,_9c3){
var _9c4,_9c5=dojo.date.locale._getGregorianBundle(_9c3),_9c6=[item,_9c2,type];
if(_9c2=="standAlone"){
var key=_9c6.join("-");
_9c4=_9c5[key];
if(_9c4[0]==1){
_9c4=undefined;
}
}
_9c6[1]="format";
return (_9c4||_9c5[_9c6.join("-")]).concat();
};
dojo.date.locale.isWeekend=function(_9c7,_9c8){
var _9c9=dojo.cldr.supplemental.getWeekend(_9c8),day=(_9c7||new Date()).getDay();
if(_9c9.end<_9c9.start){
_9c9.end+=7;
if(day<_9c9.start){
day+=7;
}
}
return day>=_9c9.start&&day<=_9c9.end;
};
dojo.date.locale._getDayOfYear=function(_9ca){
return dojo.date.difference(new Date(_9ca.getFullYear(),0,1,_9ca.getHours()),_9ca)+1;
};
dojo.date.locale._getWeekOfYear=function(_9cb,_9cc){
if(arguments.length==1){
_9cc=0;
}
var _9cd=new Date(_9cb.getFullYear(),0,1).getDay(),adj=(_9cd-_9cc+7)%7,week=Math.floor((dojo.date.locale._getDayOfYear(_9cb)+adj-1)/7);
if(_9cd==_9cc){
week++;
}
return week;
};
}
if(!dojo._hasResource["insight.time"]){
dojo._hasResource["insight.time"]=true;
dojo.provide("insight.time");
insight.time.toDate=function(_9ce){
return dojo.date.locale.parse(_9ce,{datePattern:"yyyy-MM-dd'T'HH:mm:ssZ",selector:"date"});
};
insight.time.toString=function(date){
return dojo.date.locale.format(date,{datePattern:"yyyy-MM-dd'T'HH:mm:ssZ",selector:"date"});
};
insight.time.normalizeToDate=function(date){
if(date instanceof Date){
return date;
}else{
if(date=="now"){
return new Date();
}else{
if(typeof date=="string"||date instanceof String){
return this.toDate(date);
}
}
}
return null;
};
insight.time.normalizeToString=function(date){
if(typeof date=="string"||date instanceof String){
return date;
}else{
if(date instanceof Date){
return this.toString(date);
}else{
return null;
}
}
};
insight.time.difference=function(_9cf,_9d0){
return dojo.date.difference(_9cf,_9d0,"millisecond");
};
insight.time.add=function(date,_9d1){
return dojo.date.add(date,"millisecond",_9d1);
};
insight.time.compare=function(_9d2,_9d3){
_9d2=this.normalizeToDate(_9d2);
_9d3=this.normalizeToDate(_9d3);
return dojo.date.compare(_9d2,_9d3);
};
insight.time.labels=function(_9d4,end,_9d5,_9d6){
_9d4=this.normalizeToDate(_9d4);
end=this.normalizeToDate(end);
var _9d7={};
if(_9d5){
_9d7.start=insight.time.relativeLabel(dojo.date.difference(end,_9d4,"minute"));
_9d7.end=insight.time.relativeLabel(0);
}else{
var _9d8=(_9d6||dojo.date.difference(_9d4,end,"millisecond"))/(60*1000),_9d9=dojo.date.locale._getGregorianBundle(dojo.i18n.normalizeLocale()),_9da,_9db,_9dc;
if(_9d8<6*60){
_9da="";
_9db=_9d9["timeFormat-medium"];
_9dc="time";
}else{
if(_9d8<12*60){
_9da=_9d9["dateFormat-short"];
_9db=_9d9["timeFormat-medium"];
}else{
_9da=_9d9["dateFormat-short"];
_9db=_9d9["timeFormat-short"];
}
}
_9d7.start=dojo.date.locale.format(_9d4,{datePattern:_9da,timePattern:_9db,selector:_9dc,fullYear:true});
_9d7.end=dojo.date.locale.format(end,{datePattern:_9da,timePattern:_9db,selector:_9dc,fullYear:true});
}
return _9d7;
};
insight.time.relativeLabel=function(_9dd){
var past=_9dd<0;
_9dd=Math.abs(_9dd);
if(_9dd==0){
return "Live";
}
var _9de=Math.floor(_9dd/60);
var _9df=_9dd%60;
var _9e0="";
if(_9de>0){
_9e0=_9de+" hour";
if(_9de!=1){
_9e0+="s";
}
_9e0+=" ";
}
if(_9df>0){
_9e0+=_9df+" minute";
if(_9df!=1){
_9e0+="s";
}
}
_9e0+=past?" ago":" from now";
return _9e0;
};
insight.time.shortLabel=function(_9e1,abbr){
var _9e2="",_9e3,days,_9e4,_9e5;
if(_9e1===0){
return "now";
}
_9e3=Math.floor(_9e1/insight.time.millis.week);
_9e1-=_9e3*insight.time.millis.week;
if(_9e3>0){
_9e2+=_9e3+(abbr?"w ":(_9e3>1?" weeks ":" week "));
}
days=Math.floor(_9e1/insight.time.millis.day);
_9e1-=days*insight.time.millis.day;
if(days>0){
_9e2+=days+(abbr?"d ":(days>1?" days ":" day "));
}
_9e4=Math.floor(_9e1/insight.time.millis.hour);
_9e1-=_9e4*insight.time.millis.hour;
if(_9e4>0){
_9e2+=_9e4+(abbr?"h ":(_9e4>1?" hours ":" hour "));
}
_9e5=Math.floor(_9e1/insight.time.millis.minute);
_9e1-=_9e5*insight.time.millis.minute;
if(_9e5>0){
_9e2+=_9e5+(abbr?"m ":(_9e5>1?" minutes ":" minute "));
}
return dojo.string.trim(_9e2);
};
insight.time.millis={week:604800000,day:86400000,hour:3600000,minute:60000,second:1000};
dojo.date.locale.parse=function(_9e6,_9e7){
var info=dojo.date.locale._parseInfo(_9e7),_9e8=info.tokens,_9e9=info.bundle,re=new RegExp("^"+info.regexp+"$",info.strict?"":"i"),_9ea=re.exec(_9e6);
if(!_9ea){
return null;
}
var _9eb=["abbr","wide","narrow"],_9ec=[1970,0,1,0,0,0,0],amPm="",_9ed,_9ee=dojo.every(_9ea,function(v,i){
if(!i){
return true;
}
var _9ef=_9e8[i-1];
var l=_9ef.length;
switch(_9ef.charAt(0)){
case "y":
if(l!=2&&_9e7.strict){
_9ec[0]=v;
}else{
if(v<100){
v=Number(v);
var year=""+new Date().getFullYear(),_9f0=year.substring(0,2)*100,_9f1=Math.min(Number(year.substring(2,4))+20,99),num=(v<_9f1)?_9f0+v:_9f0-100+v;
_9ec[0]=num;
}else{
if(_9e7.strict){
return false;
}
_9ec[0]=v;
}
}
break;
case "M":
if(l>2){
var _9f2=_9e9["months-format-"+_9eb[l-3]].concat();
if(!_9e7.strict){
v=v.replace(".","").toLowerCase();
_9f2=dojo.map(_9f2,function(s){
return s.replace(".","").toLowerCase();
});
}
v=dojo.indexOf(_9f2,v);
if(v==-1){
return false;
}
}else{
v--;
}
_9ec[1]=v;
break;
case "E":
case "e":
var days=_9e9["days-format-"+_9eb[l-3]].concat();
if(!_9e7.strict){
v=v.toLowerCase();
days=dojo.map(days,function(d){
return d.toLowerCase();
});
}
v=dojo.indexOf(days,v);
if(v==-1){
return false;
}
break;
case "D":
_9ec[1]=0;
case "d":
_9ec[2]=v;
break;
case "a":
var am=_9e7.am||_9e9["dayPeriods-format-wide-am"],pm=_9e7.pm||_9e9["dayPeriods-format-wide-pm"];
if(!_9e7.strict){
var _9f3=/\./g;
v=v.replace(_9f3,"").toLowerCase();
am=am.replace(_9f3,"").toLowerCase();
pm=pm.replace(_9f3,"").toLowerCase();
}
if(_9e7.strict&&v!=am&&v!=pm){
return false;
}
amPm=(v==pm)?"p":(v==am)?"a":"";
break;
case "K":
if(v==24){
v=0;
}
case "h":
case "H":
case "k":
if(v>23){
return false;
}
_9ec[3]=v;
break;
case "m":
_9ec[4]=v;
break;
case "s":
_9ec[5]=v;
break;
case "S":
_9ec[6]=v;
break;
case "Z":
if(v==="Z"){
break;
}
if(v.indexOf("GMT")===0){
v=v.substring(3);
}
_9ed=(v.charAt(0)==="-")?-1:1;
_9ed*=parseInt(v.substring(1,3),10)*60;
v=(v.charAt(3)===":")?v.substring(4):v.substring(3);
if(v.length===2){
if(_9ed<0){
_9ed-=parseInt(v,10);
}else{
_9ed+=parseInt(v,10);
}
}
break;
}
return true;
});
var _9f4=+_9ec[3];
if(amPm==="p"&&_9f4<12){
_9ec[3]=_9f4+12;
}else{
if(amPm==="a"&&_9f4==12){
_9ec[3]=0;
}
}
var _9f5=new Date(_9ec[0],_9ec[1],_9ec[2],_9ec[3],_9ec[4],_9ec[5],_9ec[6]);
if(_9e7.strict){
_9f5.setFullYear(_9ec[0]);
}
var _9f6=_9e8.join(""),_9f7=_9f6.indexOf("d")!=-1,_9f8=_9f6.indexOf("M")!=-1;
if(!_9ee||(_9f8&&_9f5.getMonth()>_9ec[1])||(_9f7&&_9f5.getDate()>_9ec[2])){
return null;
}
if((_9f8&&_9f5.getMonth()<_9ec[1])||(_9f7&&_9f5.getDate()<_9ec[2])){
_9f5=dojo.date.add(_9f5,"hour",1);
}
if(_9ed){
_9f5=dojo.date.add(_9f5,"minute",_9ed+new Date().getTimezoneOffset());
}
return _9f5;
};
}
if(!dojo._hasResource["insight.charting.axis2d.TimeRange"]){
dojo._hasResource["insight.charting.axis2d.TimeRange"]=true;
dojo.provide("insight.charting.axis2d.TimeRange");
(function(){
var dc=dojox.charting,df=dojox.lang.functional,du=dojox.lang.utils,g=dojox.gfx,lin=dc.scaler.linear,_9f9=4;
dojo.declare("insight.charting.axis2d.TimeRange",dojox.charting.axis2d.Default,{constructor:function(_9fa,_9fb){
_9fb.timeRange=_9fb.timeRange||null;
_9fb.relative=_9fb.relative||false;
_9fb.includeZero=_9fb.includeZero||true;
_9fb.minorLabels=_9fb.minorLabels||false;
_9fb.minorTickStep=_9fb.minorTickStep||1;
this.inherited(arguments);
},setTimeRange:function(_9fc,_9fd){
this.opt.timeRange=_9fc;
this.dirty=true;
if(!_9fd){
this.render(this.chart.dim,this.chart.offsets);
}
},setRelative:function(_9fe){
this.opt.relative=_9fe;
this.dirty=true;
this.render(this.chart.dim,this.chart.offsets);
},render:function(dim,_9ff){
if(!this.dirty){
return this;
}
var o=this.opt;
var _a00,stop,_a01,_a02,_a03,ta=this.chart.theme.axis,_a04=o.font||(ta.majorTick&&ta.majorTick.font)||(ta.tick&&ta.tick.font),_a05=o.fontColor||(ta.majorTick&&ta.majorTick.fontColor)||(ta.tick&&ta.tick.fontColor)||"black",_a06=this.chart.theme.getTick("major",o),_a07=this.chart.theme.getTick("minor",o),_a08=this.chart.theme.getTick("micro",o),_a09=Math.max(_a06.length,_a07.length,_a08.length),_a0a="stroke" in o?o.stroke:ta.stroke,size=_a04?g.normalizedLength(g.splitFontString(_a04).size):0;
if(this.vertical){
_a00={y:dim.height-_9ff.b};
stop={y:_9ff.t};
_a01={x:0,y:-1};
if(o.leftBottom){
_a00.x=stop.x=_9ff.l;
_a02={x:-1,y:0};
}else{
_a00.x=stop.x=dim.width-_9ff.r;
_a02={x:1,y:0};
}
_a03={x:_a02.x*(_a09+_9f9),y:size*0.4};
}else{
_a00={x:_9ff.l};
stop={x:dim.width-_9ff.r};
_a01={x:1,y:0};
if(o.leftBottom){
_a00.y=stop.y=dim.height-_9ff.b;
_a02={x:0,y:1};
_a03={y:_a09+_9f9+size};
}else{
_a00.y=stop.y=_9ff.t;
_a02={x:0,y:-1};
_a03={y:-_a09-_9f9};
}
_a03.x=0;
}
this.cleanGroup();
try{
var s=this.group,c=this.scaler,t=this.ticks,f=lin.getTransformerFromModel(this.scaler),_a0b=(dojox.gfx.renderer=="canvas"),_a0c=_a0b||this.opt.htmlLabels&&!dojo.isIE&&!dojo.isOpera?"html":"gfx",dx=_a02.x*_a06.length,dy=_a02.y*_a06.length,elem,_a0d=insight.time.labels(this.opt.timeRange.getStartDate(),this.opt.timeRange.getEndDate(),this.opt.relative);
s.createLine({x1:_a00.x,y1:_a00.y,x2:stop.x,y2:stop.y}).setStroke(_a0a);
s.createLine({x1:_a00.x,y1:_a00.y,x2:_a00.x+dx,y2:_a00.y+dy}).setStroke(_a06);
elem=dc.axis2d.common.createText[_a0c](this.chart,s,_a00.x+_a03.x,_a00.y+_a03.y,this.vertical?"middle":"start",_a0d.start,_a04,_a05);
if(_a0c=="html"){
this.htmlElements.push(elem);
}
s.createLine({x1:stop.x,y1:stop.y,x2:stop.x+dx,y2:stop.y+dy}).setStroke(_a06);
elem=dc.axis2d.common.createText[_a0c](this.chart,s,stop.x+_a03.x,stop.y+_a03.y,this.vertical?"middle":"end",_a0d.end,_a04,_a05);
if(_a0c=="html"){
this.htmlElements.push(elem);
}
dx=_a02.x*_a07.length;
dy=_a02.y*_a07.length;
function _a0e(tick){
var _a0f=f(tick.value),elem,x=_a00.x+_a01.x*_a0f,y=_a00.y+_a01.y*_a0f;
s.createLine({x1:x,y1:y,x2:x+dx,y2:y+dy}).setStroke(_a07);
};
dojo.forEach(t.major,_a0e,this);
dojo.forEach(t.minor,_a0e,this);
}
catch(e){
}
this.dirty=false;
return this;
}});
})();
}
if(!dojo._hasResource["insight.charting.plot2d.BackgroundColumns"]){
dojo._hasResource["insight.charting.plot2d.BackgroundColumns"]=true;
dojo.provide("insight.charting.plot2d.BackgroundColumns");
(function(){
var df=dojox.lang.functional,du=dojox.lang.utils,dc=dojox.charting.plot2d.common,_a10=df.lambda("item.purgeGroup()");
dojo.declare("insight.charting.plot2d.BackgroundColumns",dojox.charting.plot2d.Base,{defaultParams:{hAxis:"x",vAxis:"y",gap:0,animate:null},optionalParams:{},constructor:function(_a11,_a12){
this.opt=dojo.clone(this.defaultParams);
du.updateWithObject(this.opt,_a12);
du.updateWithPattern(this.opt,_a12,this.optionalParams);
this.series=[];
this.hAxis=this.opt.hAxis;
this.vAxis=this.opt.vAxis;
this.animate=this.opt.animate;
},getSeriesStats:function(){
return dc.collectSimpleStats(this.series);
},render:function(dim,_a13){
if(this.zoom&&!this.isDataDirty()){
return this.performZoom(dim,_a13);
}
this.resetEvents();
this.dirty=this.isDirty();
if(this.dirty){
dojo.forEach(this.series,_a10);
this._eventSeries={};
this.cleanGroup();
var s=this.group;
df.forEachRev(this.series,function(item){
item.cleanGroup(s);
});
}
var t=this.chart.theme,f,gap,_a14,ht=this._hScaler.scaler.getTransformerFromModel(this._hScaler),_a15=this.events();
f=dc.calculateBarSize(this._hScaler.bounds.scale,this.opt);
gap=f.gap;
_a14=f.size;
for(var i=this.series.length-1;i>=0;--i){
var run=this.series[i];
if(!this.dirty&&!run.dirty){
this._reconnectEvents(run.name);
continue;
}
run.cleanGroup();
var s=run.group,_a16=new Array(run.data.length);
for(var j=0;j<run.data.length;++j){
var _a17=run.data[j];
if(_a17!==null){
var rect={x:_a13.l+ht(j+0.5)+gap,y:_a13.t,width:_a14,height:dim.height-_a13.t-_a13.b};
var _a18=new dojo.Color(t.plotarea.fill);
_a18.a=0;
var _a19=s.createRect(rect).setFill(_a18).setStroke(_a18);
run.dyn.fill=_a19.getFill();
run.dyn.stroke=_a19.getStroke();
if(_a15){
var o={element:"column",index:j,run:run,shape:_a19,x:j+0.5,y:0};
this._connectEvents(o);
_a16[j]=o;
}
}
}
this._eventSeries[run.name]=_a16;
run.dirty=false;
}
this.dirty=false;
return this;
}});
})();
}
if(!dojo._hasResource["insight.time.TimeRange"]){
dojo._hasResource["insight.time.TimeRange"]=true;
dojo.provide("insight.time.TimeRange");
dojo.declare("insight.time.TimeRange",null,{start:null,end:null,anchored:true,constructor:function(_a1a){
var _a1b,end,_a1c,_a1d,_a1e;
if(dojo.isString(_a1a)){
if(_a1a.indexOf("@")===-1){
end=new Date();
_a1c=parseInt(_a1a);
_a1d=false;
}else{
end=insight.time.toDate(_a1a.split("@")[1]);
_a1c=parseInt(_a1a.split("@")[0]);
_a1d=true;
}
}else{
_a1b=insight.time.normalizeToDate(_a1a.start);
end=insight.time.normalizeToDate(_a1a.end);
_a1c=_a1a.duration;
_a1d=_a1a.anchored;
}
if(_a1b&&end){
this.start=_a1b;
this.end=end;
_a1c=insight.time.difference(_a1b,end);
}else{
if(_a1b&&_a1c){
this.start=_a1b;
this.end=insight.time.add(_a1b,_a1c);
}else{
if(end&&_a1c){
this.end=end;
this.start=insight.time.add(end,_a1c*-1);
}else{
if(_a1c){
this.end=new Date();
this.start=insight.time.add(this.end,_a1c*-1);
}
}
}
}
if(_a1d!==undefined){
this.anchored=_a1d;
}
},clone:function(_a1f){
return new insight.time.TimeRange({start:this.start,end:this.end,anchored:this.anchored});
},modulate:function(_a20){
var _a21=this.getDuration()/_a20,_a22=this.end.getTime(),_a23=_a22%_a21;
if(_a23!=0){
this.end=insight.time.add(this.end,_a21-_a23);
this.start=insight.time.add(this.start,_a21-_a23);
}
return this;
},contains:function(date){
date=insight.time.normalizeToDate(date);
return dojo.date.compare(date,this.start)>=0&&dojo.date.compare(date,this.end)<0;
},add:function(_a24){
this.start=insight.time.add(this.start,_a24);
this.end=insight.time.add(this.end,_a24);
this.anchored=true;
return this;
},roll:function(_a25,_a26){
if(!this.anchored||_a26){
_a25=_a25||{};
var date=_a25.date||new Date(),_a27=_a25.windows||60,_a28=_a25.bidirectional||false,_a29=_a25.duration||this.getDuration(),_a2a=_a29/_a27,diff=insight.time.difference(this.end,date);
if(_a28||diff>0){
_a2a=Math.ceil(diff/_a2a)*_a2a;
this.end=insight.time.add(this.end,_a2a);
this.start=insight.time.add(this.start,_a2a);
}
this.anchored=false;
}
return this;
},shift:function(end){
end=end||new Date();
var _a2b=insight.time.difference(this.end,end);
this.end=insight.time.add(this.end,_a2b);
this.start=insight.time.add(this.start,_a2b);
return this;
},changeDuration:function(_a2c){
if(!this.anchored){
this.start=insight.time.add(this.end,_a2c*-1);
}else{
var _a2d=(this.getDuration()-_a2c)/2;
this.start=insight.time.add(this.start,_a2d);
this.end=insight.time.add(this.end,_a2d*-1);
}
return this;
},getStart:function(){
return insight.time.toString(this.start);
},getStartDate:function(){
return this.start;
},getEnd:function(){
return insight.time.toString(this.end);
},getEndDate:function(){
return this.end;
},getDuration:function(){
return insight.time.difference(this.start,this.end);
},isAnchored:function(){
return this.anchored;
},setAnchored:function(_a2e){
this.anchored=_a2e;
return this;
},toString:function(){
if(this.anchored){
return this.getDuration()+"@"+this.getEnd();
}else{
return this.getDuration().toString();
}
}});
}
if(!dojo._hasResource["insight.charting._TimeRangeChart"]){
dojo._hasResource["insight.charting._TimeRangeChart"]=true;
dojo.provide("insight.charting._TimeRangeChart");
dojo.declare("insight.charting._TimeRangeChart",null,{timeRange:null,clickable:false,hideXAxis:false,postCreate:function(){
this.timeRange.modulate(this.dataPoints);
this.inherited(arguments);
var _a2f=this.hideXAxis?dojox.charting.axis2d.Invisible:insight.charting.axis2d.TimeRange;
this.chart.addAxis("x",{type:_a2f,timeRange:this.timeRange,relative:Insight.playing(),from:1,minorTickStep:1});
this.subscribe("Insight.play",function(){
this.updateTimeRange({relative:true});
});
this.subscribe("Insight.pause",function(){
this.updateTimeRange({relative:false});
});
if(!this._supressTimeRangeActions){
this.chart.addPlot("backgroundColumnsPlot",{type:insight.charting.plot2d.BackgroundColumns,vAxis:null});
this.chart.addSeries("backgroundColumnsSeries",new dojox.charting.DataSeries(this.stores.main,{},function(s,i){
return new insight.time.TimeRange({start:s.getValue(i,"start"),end:s.getValue(i,"end")});
}),{plot:"backgroundColumnsPlot"});
this.addPlotAction("backgroundColumnsPlot",insight.charting.action2d.FillHighlight,{highlight:new dojo.Color([220,238,241,0.5])});
this.chart.addPlot("foregroundColumnsPlot",{type:insight.charting.plot2d.BackgroundColumns,vAxis:null});
this.chart.addSeries("foregroundColumnsSeries",new dojox.charting.DataSeries(this.stores.main,{},function(s,i){
return new insight.time.TimeRange({start:s.getValue(i,"start"),end:s.getValue(i,"end")});
}),{plot:"foregroundColumnsPlot"});
this._registerChartEvents("foregroundColumnsPlot");
if(this.clickable){
this.addPlotAction("foregroundColumnsPlot",insight.charting.action2d.PointerHover);
}
this.addPlotAction("foregroundColumnsPlot",insight.charting.action2d.Tooltip,{text:dojo.hitch(this,this._tooltipMessage)});
this.subscribe("highlightDate",this.highlightDate);
this.subscribe("blurDate",this.blurDate);
this.connect(this.chart.stack[this.chart.plots["foregroundColumnsPlot"]],"render",dojo.hitch(this,function(_a30){
if(this._highlightDate){
this.highlightDate(this._highlightDate);
}
}));
}
},startup:function(){
if(!this._supressTimeRangeActions){
this.chart.movePlotToBack("backgroundColumnsPlot");
this.chart.movePlotToFront("foregroundColumnsPlot");
}
var grid=this.chart.stack[this.chart.plots["grid"]];
if(grid.opt.hMajorLines||grid.opt.hMinorLines){
var _a31=[];
dojox.lang.functional.forIn(this.chart.axes,function(axis){
if(axis.vertical){
_a31.push(axis);
}
});
if(_a31[0]){
grid.vAxis=_a31[0].name;
}
}
this.inherited(arguments);
},_urlParams:function(){
var _a32=this.inherited(arguments);
if(this.timeRange&&!(_a32.start&&_a32.end)){
_a32.start=this.timeRange.getStart();
_a32.end=this.timeRange.getEnd();
}
return _a32;
},updateTimeRange:function(args){
var axis=this.chart.getAxis("x"),_a33=axis.declaredClass=="insight.charting.axis2d.TimeRange";
if(args.timeRange){
this.timeRange=args.timeRange.clone().modulate(this.dataPoints);
if(_a33){
axis.setTimeRange(this.timeRange,true);
}
this.refresh();
}
if(typeof args.relative=="boolean"&&_a33){
axis.setRelative(args.relative);
}
},_tooltipMessage:function(o){
var t=o.run&&o.run.data&&o.run.data[o.index],_a34=o.run&&o.run.source&&o.run.source.store,_a35="",item,_a36,run,_a37,plot,axis,_a38;
_a34.fetchItemByIdentity({onItem:function(i){
item=i;
},identity:o.index});
_a36=insight.time.labels(_a34.getValue(item,"start"),_a34.getValue(item,"end"),false,this.timeRange.getDuration());
_a35+="<h2>"+_a36.start+" - "+_a36.end+"</h2>";
_a35+="<table class='dl'>";
var _a39=dojox.lang.functional.keys(o.chart.runs);
dojo.forEach(o.chart.getPlotOrder(),function(_a3a){
var _a3b=dojo.filter(_a39,function(run){
return _a3a===o.chart.series[o.chart.runs[run]].plot;
},this);
var _a3c=dojo.map(_a3b,function(_a3d){
var axis=o.chart.getAxis(o.chart.stack[o.chart.plots[_a3a]].vAxis),run=o.chart.series[o.chart.runs[_a3d]],_a38=run.data[o.index];
if(!axis||_a38==null){
return;
}
return {label:run.label,value:_a38,formattedValue:(axis.opt.labelFunc?axis.opt.labelFunc.call(axis.opt,_a38.toString(),_a38,axis.scaler.major.prec):_a38.toString())};
},this);
_a3c=dojo.filter(_a3c,function(item){
return !!item&&!!item.formattedValue;
},this);
if(_a3c.length===0){
return;
}
_a3c.sort(function(a,b){
return b.value-a.value;
});
dojo.forEach(_a3c,function(item){
_a35+="<tr><td>"+item.label+"</td><td>"+item.formattedValue+"</td></tr>";
},this);
},this);
_a35+="</table>";
return _a35;
},highlightDate:function(date){
date=insight.time.normalizeToDate(date);
if(this._highlightDate){
this.blurDate(this._highlightDate);
}
this._highlightDate=date;
var i=this._findDateIndex(date,"backgroundColumnsSeries");
if(i!=-1){
this.chart.fireEvent("backgroundColumnsSeries","onmouseover",i);
}
},blurDate:function(date){
date=insight.time.normalizeToDate(date);
this._highlightDate=null;
var i=this._findDateIndex(date,"backgroundColumnsSeries");
if(i!=-1){
this.chart.fireEvent("backgroundColumnsSeries","onmouseout",i);
}
},_findDateIndex:function(date,_a3e){
var _a3f=this.chart.runs[_a3e],_a40=this.chart.series[_a3f].plot,_a41=this.chart.series[_a3f];
for(var i=0;i<_a41.data.length;i++){
if(_a41.data[i].contains(date)){
return i;
}
}
return -1;
}});
}
if(!dojo._hasResource["insight.charting.scaler.linear"]){
dojo._hasResource["insight.charting.scaler.linear"]=true;
dojo.provide("insight.charting.scaler.linear");
(function(){
var _a42=3,dcsc=dojox.charting.scaler.common,_a43=dcsc.findString,_a44=dcsc.getNumericLabel;
var _a45=[{coefficient:1.5,major:0.5,minor:0.1},{coefficient:2,major:0.5,minor:0.1},{coefficient:2.5,major:0.5,minor:0.1},{coefficient:3,major:1,minor:0.5},{coefficient:4,major:1,minor:0.5},{coefficient:5,major:1,minor:0.5},{coefficient:6,major:1.5,minor:0.5},{coefficient:7.5,major:2.5,minor:0.5},{coefficient:8,major:2,minor:0.5},{coefficient:9,major:3,minor:0.5},{coefficient:10,major:2.5,minor:0.5}];
var _a46=function(max){
max*=1.05;
var base,_a47=0,_a48;
do{
base=Math.pow(10,_a47);
_a47++;
}while(max>10*base);
for(var i in _a45){
_a48=_a45[i];
if(max<_a48.coefficient*base){
return {max:base*_a48.coefficient,major:base*_a48.major,minor:base*_a48.minor};
}
}
};
var _a49=function(min,max,_a4a,_a4b,_a4c,_a4d,span){
_a4a=dojo.delegate(_a4a);
if(!_a4b){
if(_a4a.fixUpper=="major"){
_a4a.fixUpper="minor";
}
if(_a4a.fixLower=="major"){
_a4a.fixLower="minor";
}
}
if(!_a4c){
if(_a4a.fixUpper=="minor"){
_a4a.fixUpper="micro";
}
if(_a4a.fixLower=="minor"){
_a4a.fixLower="micro";
}
}
if(!_a4d){
if(_a4a.fixUpper=="micro"){
_a4a.fixUpper="none";
}
if(_a4a.fixLower=="micro"){
_a4a.fixLower="none";
}
}
var _a4e=_a43(_a4a.fixLower,["major"])?Math.floor(_a4a.min/_a4b)*_a4b:_a43(_a4a.fixLower,["minor"])?Math.floor(_a4a.min/_a4c)*_a4c:_a43(_a4a.fixLower,["micro"])?Math.floor(_a4a.min/_a4d)*_a4d:_a4a.min,_a4f=_a43(_a4a.fixUpper,["major"])?Math.ceil(_a4a.max/_a4b)*_a4b:_a43(_a4a.fixUpper,["minor"])?Math.ceil(_a4a.max/_a4c)*_a4c:_a43(_a4a.fixUpper,["micro"])?Math.ceil(_a4a.max/_a4d)*_a4d:_a4a.max;
if(_a4a.useMin){
min=_a4e;
}
if(_a4a.useMax){
max=_a4f;
}
var _a50=(!_a4b||_a4a.useMin&&_a43(_a4a.fixLower,["major"]))?min:Math.ceil(min/_a4b)*_a4b,_a51=(!_a4c||_a4a.useMin&&_a43(_a4a.fixLower,["major","minor"]))?min:Math.ceil(min/_a4c)*_a4c,_a52=(!_a4d||_a4a.useMin&&_a43(_a4a.fixLower,["major","minor","micro"]))?min:Math.ceil(min/_a4d)*_a4d,_a53=!_a4b?0:(_a4a.useMax&&_a43(_a4a.fixUpper,["major"])?Math.round((max-_a50)/_a4b):Math.floor((max-_a50)/_a4b))+1,_a54=!_a4c?0:(_a4a.useMax&&_a43(_a4a.fixUpper,["major","minor"])?Math.round((max-_a51)/_a4c):Math.floor((max-_a51)/_a4c))+1,_a55=!_a4d?0:(_a4a.useMax&&_a43(_a4a.fixUpper,["major","minor","micro"])?Math.round((max-_a52)/_a4d):Math.floor((max-_a52)/_a4d))+1,_a56=_a4c?Math.round(_a4b/_a4c):0,_a57=_a4d?Math.round(_a4c/_a4d):0,_a58=_a4b?Math.floor(Math.log(_a4b)/Math.LN10):0,_a59=_a4c?Math.floor(Math.log(_a4c)/Math.LN10):0,_a5a=span/(max-min);
if(!isFinite(_a5a)){
_a5a=1;
}
return {bounds:{lower:_a4e,upper:_a4f,from:min,to:max,scale:_a5a,span:span},major:{tick:_a4b,start:_a50,count:_a53,prec:_a58},minor:{tick:_a4c,start:_a51,count:_a54,prec:_a59},micro:{tick:_a4d,start:_a52,count:_a55,prec:0},minorPerMajor:_a56,microPerMinor:_a57,scaler:insight.charting.scaler.linear};
};
dojo.mixin(insight.charting.scaler.linear,{buildScaler:function(min,max,span,_a5b){
var h={fixUpper:"none",fixLower:"none",natural:false};
if(_a5b){
if("fixUpper" in _a5b){
h.fixUpper=String(_a5b.fixUpper);
}
if("fixLower" in _a5b){
h.fixLower=String(_a5b.fixLower);
}
if("natural" in _a5b){
h.natural=Boolean(_a5b.natural);
}
}
if("min" in _a5b){
min=_a5b.min;
}
if("max" in _a5b){
max=_a5b.max;
}
if(_a5b.includeZero){
if(min>0){
min=0;
}
if(max<0){
max=0;
}
}
h.min=min;
h.useMin=true;
h.max=max;
h.useMax=true;
if("from" in _a5b){
min=_a5b.from;
h.useMin=false;
}
if("to" in _a5b){
max=_a5b.to;
h.useMax=false;
}
if(max<=min){
return _a49(min,max,h,0,0,0,span);
}
var _a5c=_a46(max),_a5d=_a5b&&("majorTickStep" in _a5b)?_a5b.majorTickStep:_a5c.major,_a5e=_a5b&&("minorTickStep" in _a5b)?_a5b.minorTickStep:_a5c.minor,_a5f=0,_a60;
h.max=max=_a5c.max;
if(_a5b&&("microTickStep" in _a5b)){
_a5f=_a5b.microTickStep;
_a60=_a49(min,max,h,_a5d,_a5e,_a5f,span);
}else{
do{
_a5f=_a5e/10;
if(!h.natural||_a5f>0.9){
_a60=_a49(min,max,h,_a5d,_a5e,_a5f,span);
if(_a60.bounds.scale*_a60.micro.tick>_a42){
break;
}
}
_a5f=_a5e/5;
if(!h.natural||_a5f>0.9){
_a60=_a49(min,max,h,_a5d,_a5e,_a5f,span);
if(_a60.bounds.scale*_a60.micro.tick>_a42){
break;
}
}
_a5f=_a5e/2;
if(!h.natural||_a5f>0.9){
_a60=_a49(min,max,h,_a5d,_a5e,_a5f,span);
if(_a60.bounds.scale*_a60.micro.tick>_a42){
break;
}
}
_a5f=0;
}while(false);
}
return _a5f?_a60:_a49(min,max,h,_a5d,_a5e,0,span);
},buildTicks:function(_a61,_a62){
var step,next,tick,_a63=_a61.major.start,_a64=_a61.minor.start,_a65=_a61.micro.start;
if(_a62.microTicks&&_a61.micro.tick){
step=_a61.micro.tick,next=_a65;
}else{
if(_a62.minorTicks&&_a61.minor.tick){
step=_a61.minor.tick,next=_a64;
}else{
if(_a61.major.tick){
step=_a61.major.tick,next=_a63;
}else{
return null;
}
}
}
var _a66=1/_a61.bounds.scale;
if(_a61.bounds.to<=_a61.bounds.from||isNaN(_a66)||!isFinite(_a66)||step<=0||isNaN(step)||!isFinite(step)){
return null;
}
var _a67=[],_a68=[],_a69=[];
while(next<=_a61.bounds.to+_a66){
if(Math.abs(_a63-next)<step/2){
tick={value:_a63};
if(_a62.majorLabels){
tick.label=_a44(_a63,_a61.major.prec,_a62);
}
_a67.push(tick);
_a63+=_a61.major.tick;
_a64+=_a61.minor.tick;
_a65+=_a61.micro.tick;
}else{
if(Math.abs(_a64-next)<step/2){
if(_a62.minorTicks){
tick={value:_a64};
if(_a62.minorLabels&&(_a61.minMinorStep<=_a61.minor.tick*_a61.bounds.scale)){
tick.label=_a44(_a64,_a61.minor.prec,_a62);
}
_a68.push(tick);
}
_a64+=_a61.minor.tick;
_a65+=_a61.micro.tick;
}else{
if(_a62.microTicks){
_a69.push({value:_a65});
}
_a65+=_a61.micro.tick;
}
}
next+=step;
}
return {major:_a67,minor:_a68,micro:_a69};
},getTransformerFromModel:function(_a6a){
var _a6b=_a6a.bounds.from,_a6c=_a6a.bounds.scale;
return function(x){
return (x-_a6b)*_a6c;
};
},getTransformerFromPlot:function(_a6d){
var _a6e=_a6d.bounds.from,_a6f=_a6d.bounds.scale;
return function(x){
return x/_a6f+_a6e;
};
}});
})();
}
if(!dojo._hasResource["insight.charting.axis2d.Titled"]){
dojo._hasResource["insight.charting.axis2d.Titled"]=true;
dojo.provide("insight.charting.axis2d.Titled");
(function(){
var dc=dojox.charting,g=dojox.gfx,m=dojox.gfx.matrix,_a70=4;
dojo.declare("insight.charting.axis2d._Titled",null,{getOffsets:function(){
var _a71=this.inherited(arguments),o=this.opt;
if(o.title){
var ta=this.chart.theme.axis,_a72=o.font||(ta.majorTick&&ta.majorTick.font)||(ta.tick&&ta.tick.font),size=_a72?g.normalizedLength(g.splitFontString(_a72).size):0,_a73=o.leftBottom;
if(this.vertical){
_a71[_a73?"l":"r"]+=size+_a70;
}else{
_a71[_a73?"b":"t"]+=size+_a70;
}
}
return _a71;
},render:function(dim,_a74){
if(!this.dirty){
return this;
}
this.inherited(arguments);
try{
var o=this.opt,_a75=o.title,ta=this.chart.theme.axis,_a76=o.font||(ta.majorTick&&ta.majorTick.font)||(ta.tick&&ta.tick.font),_a77=o.fontColor||(ta.majorTick&&ta.majorTick.fontColor)||(ta.tick&&ta.tick.fontColor)||"black",size=_a76?g.normalizedLength(g.splitFontString(_a76).size):0,cm=this.chart.margins,_a78=o.leftBottom;
if(_a75){
var x,y,_a79;
if(this.vertical){
_a79=_a78?270:90;
x=_a78?size+cm.l:dim.width-size-cm.r;
y=(_a74.t+dim.height-_a74.b)/2;
}else{
_a79=0;
x=(_a74.l+dim.width-_a74.r)/2;
y=_a78?dim.height-cm.b:cm.t;
}
var elem=this.group.createText({x:0,y:0,text:_a75,align:"middle"});
elem.setFont(_a76).setFill(_a77);
if(_a79){
elem.setTransform([m.translate(x,y),m.rotateg(_a79)]);
}else{
elem.setTransform(m.translate(x,y));
}
}
}
catch(e){
}
return this;
}});
dojo.declare("insight.charting.axis2d.Titled",[dojox.charting.axis2d.Default,insight.charting.axis2d._Titled],{});
})();
}
if(!dojo._hasResource["insight.charting.axis2d.Metric"]){
dojo._hasResource["insight.charting.axis2d.Metric"]=true;
dojo.provide("insight.charting.axis2d.Metric");
(function(){
var dc=dojox.charting,du=dojox.lang.utils,g=dojox.gfx,lin=insight.charting.scaler.linear,_a7a=4;
dojo.declare("insight.charting.axis2d.Metric",[dojox.charting.axis2d.Default,insight.charting.axis2d._Titled],{_scaleTo:null,calculate:function(min,max,span,_a7b){
if(this.initialized()){
return this;
}
var o=this.opt;
if(this._scaleTo){
max=Math.max.apply(Math,this.chart.series[this.chart.runs[this._scaleTo]].data);
}
this.labels="labels" in o?o.labels:_a7b;
this.scaler=lin.buildScaler(min,max,span,o);
var tsb=this.scaler.bounds;
if("scale" in this){
o.from=tsb.lower+this.offset;
o.to=(tsb.upper-tsb.lower)/this.scale+o.from;
if(!isFinite(o.from)||isNaN(o.from)||!isFinite(o.to)||isNaN(o.to)||o.to-o.from>=tsb.upper-tsb.lower){
delete o.from;
delete o.to;
delete this.scale;
delete this.offset;
}else{
if(o.from<tsb.lower){
o.to+=tsb.lower-o.from;
o.from=tsb.lower;
}else{
if(o.to>tsb.upper){
o.from+=tsb.upper-o.to;
o.to=tsb.upper;
}
}
this.offset=o.from-tsb.lower;
}
this.scaler=lin.buildScaler(min,max,span,o);
tsb=this.scaler.bounds;
if(this.scale==1&&this.offset==0){
delete this.scale;
delete this.offset;
}
}
var _a7c=0,ta=this.chart.theme.axis,_a7d=o.font||(ta.majorTick&&ta.majorTick.font)||(ta.tick&&ta.tick.font),size=_a7d?g.normalizedLength(g.splitFontString(_a7d).size):0;
if(this.vertical){
if(size){
_a7c=size+_a7a;
}
}else{
if(size){
var _a7e,i;
if(o.labelFunc&&o.maxLabelSize){
_a7e=o.maxLabelSize;
}else{
if(this.labels){
_a7e=this._groupLabelWidth(this.labels,_a7d);
}else{
var _a7f=Math.ceil(Math.log(Math.max(Math.abs(tsb.from),Math.abs(tsb.to)))/Math.LN10),t=[];
if(tsb.from<0||tsb.to<0){
t.push("-");
}
t.push(dojo.string.rep("9",_a7f));
var _a80=Math.floor(Math.log(tsb.to-tsb.from)/Math.LN10);
if(_a80>0){
t.push(".");
for(i=0;i<_a80;++i){
t.push("9");
}
}
_a7e=dojox.gfx._base._getTextBox(t.join(""),{font:_a7d}).w;
}
}
_a7c=_a7e+_a7a;
}
}
this.scaler.minMinorStep=_a7c;
this.ticks=lin.buildTicks(this.scaler,o);
return this;
},scaleTo:function(_a81){
this._scaleTo=_a81;
this.dirty=true;
}});
})();
}
if(!dojo._hasResource["insight.charting.MultiMetricResourceChart"]){
dojo._hasResource["insight.charting.MultiMetricResourceChart"]=true;
dojo.provide("insight.charting.MultiMetricResourceChart");
dojo.declare("insight.charting.MultiMetricResourceChart",[insight.charting.Chart,insight.charting._TimeRangeChart],{theme:insight.charting.themes.SpringLight,_metrics:null,_metricLabels:null,postCreate:function(){
this.inherited(arguments);
this._metrics=[];
this._metricLabels={};
},addResourceMetricPlot:function(_a82,_a83,_a84,_a85,_a86,_a87){
var _a88=this._names(_a83),_a89=this._metrics.length;
_a82=this._store(_a82);
_a85=dojo.delegate({type:insight.charting.axis2d.Metric,metric:_a83},_a85);
_a86=dojo.delegate({vAxis:_a88.axis,metric:_a83,label:_a84},_a86);
_a87=dojo.delegate({plot:_a88.plot,metric:_a83,label:_a84,store:_a82.name},_a87);
if(_a89>1||_a85.hidden){
_a85.type=dojox.charting.axis2d.Invisible;
}else{
if(_a89==1){
_a85.leftBottom=false;
}
}
_a85.vertical=true;
this.chart.addAxis(_a88.axis,_a85);
this.chart.addPlot(_a88.plot,_a86);
this.chart.addSeries(_a88.series,new dojox.charting.DataSeries(_a82,{},_a83),_a87);
this._registerChartEvents(_a88.plot);
this.chart.movePlotToFront(_a88.plot);
this._metrics.push(_a83);
this._metricLabels[_a83]=_a84;
},addResourceMetricPlotAction:function(_a8a,_a8b,args){
this.addPlotAction(this._names(_a8a).plot,_a8b,args);
},_names:function(_a8c){
return {axis:_a8c+"YAxis",plot:_a8c+"Plot",series:_a8c+"Series"};
}});
}
if(!dojo._hasResource["insight.charting.MultiResourceChart"]){
dojo._hasResource["insight.charting.MultiResourceChart"]=true;
dojo.provide("insight.charting.MultiResourceChart");
dojo.declare("insight.charting.MultiResourceChart",insight.charting.MultiMetricResourceChart,{theme:insight.charting.themes.SpringLight,type:dojox.charting.plot2d.Areas,resourceDisplayLimit:dojo.isIE?5:10,tension:dojo.isIE?null:"S",scaleOnRestack:true,postCreate:function(){
this.inherited(arguments);
if(dojo.isString(this.yAxisArgs.labelFunc)){
this.yAxisArgs.labelFunc=this[this.yAxisArgs.labelFunc];
}
this.chart.addAxis("y",this.yAxisArgs);
this.chart.addPlot("resource",{type:this.type,markers:true,tension:this.tension});
this._registerChartEvents("resource");
this.addPlotAction("resource",insight.charting.action2d.StrokeHighlight,{highlight:"#2354A4"});
this._initialLoad();
},_initialLoad:function(){
this._series=[];
dojox.lang.functional.forIn(this.stores,function(_a8d,name){
_a8d.setUrl(null,this._urlParams(name));
_a8d.fetch({scope:this,onComplete:function(){
var t=setTimeout(dojo.hitch(this,function(){
clearTimeout(t);
t=null;
if(_a8d._data.weights){
var _a8e=_a8d._data.weights.sort(function(a,b){
return a.value-b.value;
}).reverse(),_a8f=dojo.map(_a8e,function(item){
return item.name;
},this);
dojo.forEach(_a8f.slice(0,this.resourceDisplayLimit),function(_a90){
this._addSeries(_a90,_a8d._data.resources[_a90],_a8d);
},this);
}
this.chart.render();
}),50);
}});
},this);
},_addSeries:function(_a91,_a92,_a93){
_a93=this._store(_a93);
if(!(_a93._data&&_a93._data.colors&&_a93._data.colors[_a91])){
return;
}
var _a94=new dojo.Color(_a93._data.colors[_a91]),_a95=new dojo.Color(_a94),fill=new dojo.Color(_a94),_a96=new dojo.Color(_a94);
_a95.a=0.6;
fill.a=0.2;
_a96.a=0;
this.chart.addSeries(_a91,new dojox.charting.DataSeries(_a93,{},_a91),{plot:"resource",resourceKey:_a91,label:_a92||_a93._data.resources[_a91],stroke:{color:_a95},outline:{color:new dojo.Color([255,255,255,0]),width:0},fill:fill,markerStroke:{color:_a96},markerOutline:{color:_a96},markerFill:_a96,store:_a93.name});
this._series.push(_a91);
},reset:function(){
dojo.forEach(this._series,function(_a97){
this.chart.removeSeries(_a97);
},this);
this._initialLoad();
},highlightResource:function(_a98,_a99){
if(_a98==this._highlightedResource){
return;
}
var _a9a={key:_a98};
if(this._highlightedResource){
this.blurResource(this._highlightedResource);
}
var _a9b=this._findResourceSeries(_a98,_a99);
if(_a9b){
_a9a.strokeAlpha=_a9b.stroke.color.a;
_a9a.strokeWidth=_a9b.stroke.width;
_a9a.outlineAlpha=_a9b.outline.color.a;
_a9a.outlineWidth=_a9b.outline.width;
_a9b.stroke.color.a=1;
_a9b.stroke.width=4;
_a9b.outline.color.a=0.5;
_a9b.outline.width=5;
_a9b.dirty=true;
this.chart.moveSeriesToFront(_a98).moveSeriesToFront("foregroundColumnsSeries").render();
}
this._highlightedResource=_a9a;
},blurResource:function(_a9c,_a9d){
if(!this._highlightedResource||_a9c!=this._highlightedResource.key){
return;
}
var _a9e=this._highlightedResource;
var _a9f=this._findResourceSeries(_a9c,_a9d);
if(_a9f){
_a9f.stroke.color.a=_a9e.strokeAlpha;
_a9f.stroke.width=_a9e.strokeWidth;
_a9f.outline.color.a=_a9e.outlineAlpha;
_a9f.outline.width=_a9e.outlineWidth;
_a9f.dirty=true;
this.chart.render();
}
this._highlightedResource=null;
},restackResource:function(_aa0,_aa1){
var _aa2=this._findResourceSeries(_aa0,_aa1);
if(_aa2){
if(this.scaleOnRestack){
this.chart.getAxis("y").scaleTo(_aa0);
}
this.chart.moveSeriesToFront(_aa0).moveSeriesToFront("foregroundColumnsSeries").render();
}
},_findResourceSeries:function(_aa3,_aa4){
var _aa5=this.chart.series[this.chart.runs[_aa3]];
if(!_aa5&&_aa4){
this._addSeries(_aa3,null,_aa4);
_aa5=this._findResourceSeries(_aa3);
}
return _aa5;
}});
}
if(!dojo._hasResource["insight.charting.plot2d.HeatColumns"]){
dojo._hasResource["insight.charting.plot2d.HeatColumns"]=true;
dojo.provide("insight.charting.plot2d.HeatColumns");
(function(){
var df=dojox.lang.functional,du=dojox.lang.utils,dc=dojox.charting.plot2d.common,_aa6=df.lambda("item.purgeGroup()");
dojo.declare("insight.charting.plot2d.HeatColumns",dojox.charting.plot2d.Base,{defaultParams:{hAxis:"x",vAxis:"y",gap:0,animate:null,minAlpha:0.1},optionalParams:{},constructor:function(_aa7,_aa8){
this.opt=dojo.clone(this.defaultParams);
du.updateWithObject(this.opt,_aa8);
du.updateWithPattern(this.opt,_aa8,this.optionalParams);
this.series=[];
this.hAxis=this.opt.hAxis;
this.vAxis=this.opt.vAxis;
this.animate=this.opt.animate;
},getSeriesStats:function(){
return dc.collectSimpleStats(this.series);
},render:function(dim,_aa9){
if(this.zoom&&!this.isDataDirty()){
return this.performZoom(dim,_aa9);
}
this.resetEvents();
this.dirty=this.isDirty();
if(this.dirty){
dojo.forEach(this.series,_aa6);
this._eventSeries={};
this.cleanGroup();
var s=this.group;
df.forEachRev(this.series,function(item){
item.cleanGroup(s);
});
}
var t=this.chart.theme.clone(),f,o=this.opt,gap,_aaa,ht=this._hScaler.scaler.getTransformerFromModel(this._hScaler),_aab=this.events();
f=dc.calculateBarSize(this._hScaler.bounds.scale,this.opt);
gap=f.gap;
_aaa=f.size;
for(var i=this.series.length-1;i>=0;--i){
var run=this.series[i],_aac=t.next("column",[this.opt,run]);
if(!this.dirty&&!run.dirty){
this._reconnectEvents(run.name);
continue;
}
run.cleanGroup();
var s=run.group,_aad=new Array(run.data.length);
for(var j=0;j<run.data.length;++j){
var _aae=run.data[j];
if(_aae!==null){
var rect={x:_aa9.l+ht(j+0.5)+gap,y:_aa9.t,width:_aaa,height:dim.height-_aa9.t-_aa9.b};
var _aaf=new dojo.Color(_aac.series.fill),_ab0=this.opt.minAlpha,_ab1=_aaf.a;
_aaf.a=_aae?_aae*(_ab1-_ab0)+_ab0:0;
var _ab2=s.createRect(rect).setFill(_aaf).setStroke(dojo.Color.transparent);
run.dyn.fill=new dojo.Color(_aac.series.fill);
run.dyn.stroke=_ab2.getStroke();
if(_aab){
var o={element:"column",index:j,run:run,shape:_ab2,x:j+0.5,y:0};
this._connectEvents(o);
_aad[j]=o;
}
}
}
this._eventSeries[run.name]=_aad;
run.dirty=false;
}
this.dirty=false;
return this;
}});
})();
}
if(!dojo._hasResource["insight.charting.plot2d.HorizontalBands"]){
dojo._hasResource["insight.charting.plot2d.HorizontalBands"]=true;
dojo.provide("insight.charting.plot2d.HorizontalBands");
(function(){
var du=dojox.lang.utils,dc=dojox.charting.plot2d.common;
dojo.declare("insight.charting.plot2d.HorizontalBands",dojox.charting.Element,{defaultParams:{bands:[],hAxis:"x",vAxis:"y"},optionalParams:{},constructor:function(_ab3,_ab4){
this.opt=dojo.delegate(this.defaultParams,_ab4);
du.updateWithPattern(this.opt,_ab4,this.optionalParams);
this.hAxis=this.opt.hAxis;
this.vAxis=this.opt.vAxis;
this.dirty=true;
},clear:function(){
this._hAxis=null;
this._vAxis=null;
this.dirty=true;
return this;
},setAxis:function(axis){
if(axis){
this[axis.vertical?"_vAxis":"_hAxis"]=axis;
}
return this;
},addSeries:function(run){
return this;
},getSeriesStats:function(){
return dojo.delegate(dc.defaultStats);
},initializeScalers:function(){
return this;
},isDirty:function(){
return this.dirty||this._hAxis&&this._hAxis.dirty||this._vAxis&&this._vAxis.dirty;
},performZoom:function(dim,_ab5){
return this;
},getRequiredColors:function(){
return 0;
},render:function(dim,_ab6){
this.dirty=this.isDirty();
if(!this.dirty){
return this;
}
this.cleanGroup();
var s=this.group,ta=this.chart.theme.axis;
try{
var _ab7=this._vAxis.getScaler(),vt=_ab7.scaler.getTransformerFromModel(_ab7);
dojo.forEach(this.opt.bands,function(band){
var from=vt(band.from||this._vAxis.opt.min),to=vt(band.to||this._vAxis.opt.max);
s.createRect({x:_ab6.l,y:dim.height-_ab6.b-to,width:dim.width-_ab6.r,height:to-from}).setFill(band.color);
},this);
}
catch(e){
}
this.dirty=false;
return this;
}});
})();
}
if(!dojo._hasResource["insight.charting.action2d.ClickHighlight"]){
dojo._hasResource["insight.charting.action2d.ClickHighlight"]=true;
dojo.provide("insight.charting.action2d.ClickHighlight");
(function(){
var _ab8=100,_ab9=75,_aba=50,c=dojox.color,cc=function(_abb){
return function(){
return _abb;
};
},hl=function(_abc){
var a=new c.Color(_abc),x=a.toHsl();
if(x.s==0){
x.l=x.l<50?100:0;
}else{
x.s=_ab8;
if(x.l<_aba){
x.l=_ab9;
}else{
if(x.l>_ab9){
x.l=_aba;
}else{
x.l=x.l-_aba>_ab9-x.l?_aba:_ab9;
}
}
}
return c.fromHsl(x);
};
dojo.declare("insight.charting.action2d.ClickHighlight",dojox.charting.action2d.Base,{defaultParams:{duration:400,easing:dojo.fx.easing.backOut},optionalParams:{highlight:"red"},constructor:function(_abd,plot,_abe){
var a=_abe&&_abe.highlight;
this.colorFun=a?(dojo.isFunction(a)?a:cc(a)):hl;
this.connect();
},process:function(o){
if(!o.shape||o.type!="onclick"){
return;
}
if(!this.anim[o.run.name]){
this.anim[o.run.name]={};
}else{
if(this.anim[o.run.name].action){
if(o.shape==this.anim[o.run.name].action.shape){
return;
}
this.doProcess(this.anim[o.run.name].action.shape,this.anim[o.run.name],this.anim[o.run.name]);
this.anim[o.run.name]={};
}
}
this.doProcess(o.shape,o.run.name);
},doProcess:function(_abf,_ac0,anim){
var _ac1,_ac2,_ac3;
if(anim){
_ac3=true;
anim.action.stop(true);
}else{
var _ac4=_abf.getFill();
if(!_ac4||!(_ac4 instanceof dojo.Color)){
return;
}
this.anim[_ac0]=anim={start:_ac4,end:this.colorFun(_ac4)};
}
var _ac5=anim.start,end=anim.end;
if(_ac3){
var t=_ac5;
_ac5=end;
end=t;
}
anim.action=dojox.gfx.fx.animateFill({shape:_abf,duration:this.duration,easing:this.easing,color:{start:_ac5,end:end}});
anim.action.play();
}});
})();
}
if(!dojo._hasResource["insight.charting.RealTimePerformanceChart"]){
dojo._hasResource["insight.charting.RealTimePerformanceChart"]=true;
dojo.provide("insight.charting.RealTimePerformanceChart");
dojo.declare("insight.charting.RealTimePerformanceChart",[insight.charting.Chart,insight.charting._TimeRangeChart],{theme:insight.charting.themes.Spring,type:dojox.charting.plot2d.Columns,_supressTimeRangeActions:true,_selectedWindow:null,postCreate:function(){
this.inherited(arguments);
this.chart.addAxis("y",{type:insight.charting.axis2d.Metric,vertical:true,includeZero:true,fixed:false,minorTicks:false,labelFunc:this._responseTimeLabelFunc});
this.chart.addPlot("default",{type:this.type,gap:1.5});
this.chart.addSeries("duration",new dojox.charting.DataSeries(this.stores.main,{},dojo.hitch(this,function(s,i){
var o={y:s.getValue(i,"maxDuration")};
if(this._selectedWindow&&this._selectedWindow.start==s.getValue(i,"start")&&this._selectedWindow.end==s.getValue(i,"end")){
o.fill=new dojo.Color([113,166,59,0.8]);
this._selectedWindow.index=s.getIdentity(i);
}
return o;
})));
this._registerChartEvents("default");
this.addPlotAction("default",insight.charting.action2d.ClickHighlight,{highlight:new dojo.Color([113,166,59,0.8])});
this.addPlotAction("default",insight.charting.action2d.PointerHover);
this.addPlotAction("default",insight.charting.action2d.StrokeHighlight,{highlight:"#2354A4"});
this.addPlotAction("default",insight.charting.action2d.Tooltip,{text:dojo.hitch(this,function(_ac6){
var s=this.stores.main,i=s._items[_ac6.index];
return s.getValue(i,"tooltip");
})});
this.connect(this,"onChartElementClick",function(_ac7){
var s=this.stores.main,i=s._items[_ac7.index];
if(_ac7.element=="column"){
this._selectedWindow={start:s.getValue(i,"start"),end:s.getValue(i,"end"),index:s.getIdentity(i)};
this.onLoadWindow(this._selectedWindow);
}
});
this.connect(this.chart.stack[this.chart.plots["default"]],"render",dojo.hitch(this,function(_ac8){
if(this._selectedWindow&&this._selectedWindow.index&&this.stores.main._data){
var w=this._selectedWindow,s=this.stores.main,i=s._items[this._selectedWindow.index];
if(s.getValue(i,"start")!=w.start||s.getValue(i,"end")!=w.end){
w.index=null;
return;
}
var a=this.getPlotAction("default","insight.charting.action2d.ClickHighlight"),o=this.chart.stack[this.chart.plots["default"]]._eventSeries["duration"][w.index];
if(a&&o){
a.anim["duration"].action.shape=o.shape;
}
}
}));
},onLoadWindow:dijit._connectOnUseEventHandler});
}
if(!dojo._hasResource["insight.charting.plot2d.HistogramBars"]){
dojo._hasResource["insight.charting.plot2d.HistogramBars"]=true;
dojo.provide("insight.charting.plot2d.HistogramBars");
(function(){
var dc=dojox.charting.plot2d.common;
dojo.declare("insight.charting.plot2d.HistogramBars",dojox.charting.plot2d.Bars,{getSeriesStats:function(){
var _ac9=dc.collectSimpleStats(this.series),t;
_ac9.hmin-=1;
_ac9.hmax+=1;
t=_ac9.hmin,_ac9.hmin=_ac9.vmin,_ac9.vmin=t;
t=_ac9.hmax,_ac9.hmax=_ac9.vmax,_ac9.vmax=t;
return _ac9;
}});
})();
}
if(!dojo._hasResource["insight.charting.axis2d.Histogram"]){
dojo._hasResource["insight.charting.axis2d.Histogram"]=true;
dojo.provide("insight.charting.axis2d.Histogram");
(function(){
var dc=dojox.charting,g=dojox.gfx,lin=dc.scaler.linear,_aca=4;
dojo.declare("insight.charting.axis2d.Histogram",dojox.charting.axis2d.Default,{getOffsets:function(){
var s=this.scaler,_acb={l:0,r:0,t:0,b:0};
if(!s){
return _acb;
}
var o=this.opt,_acc=0,a,b,c,d,gl=dc.scaler.common.getNumericLabel,_acd=0,ma=s.major,mi=s.minor,ta=this.chart.theme.axis,_ace=o.font||(ta.majorTick&&ta.majorTick.font)||(ta.tick&&ta.tick.font),_acf=this.chart.theme.getTick("major",o),_ad0=this.chart.theme.getTick("minor",o),size=_ace?g.normalizedLength(g.splitFontString(_ace).size):0,_ad1=o.rotation%360,_ad2=o.leftBottom,cosr=Math.abs(Math.cos(_ad1*Math.PI/180)),sinr=Math.abs(Math.sin(_ad1*Math.PI/180));
if(_ad1<0){
_ad1+=360;
}
if(size){
if(o.maxLabelSize){
_acc=o.maxLabelSize;
}else{
if(this.labels){
_acc=this._groupLabelWidth(this.labels,_ace);
}else{
if(this.ticks&&this.ticks.major&&this.ticks.major[0]&&this.ticks.major[0].label!=(this.ticks.major[0].value||0).toString()){
_acc=this._groupLabelWidth(dojo.map(this.ticks.major,function(tick){
return tick.label;
}),_ace);
}else{
if(this._cachedLabelWidth){
_acc=this._cachedLabelWidth;
}else{
_acc=this._groupLabelWidth(["default"],_ace);
}
}
}
}
if(this.vertical){
var side=_ad2?"l":"r";
switch(_ad1){
case 0:
case 180:
_acb[side]=_acc;
_acb.t=_acb.b=size/2;
break;
case 90:
case 270:
_acb[side]=size;
_acb.t=_acb.b=_acc/2;
break;
default:
if(_ad1<=centerAnchorLimit||(180<_ad1&&_ad1<=(180+centerAnchorLimit))){
_acb[side]=size*sinr/2+_acc*cosr;
_acb[_ad2?"t":"b"]=size*cosr/2+_acc*sinr;
_acb[_ad2?"b":"t"]=size*cosr/2;
}else{
if(_ad1>(360-centerAnchorLimit)||(180>_ad1&&_ad1>(180-centerAnchorLimit))){
_acb[side]=size*sinr/2+_acc*cosr;
_acb[_ad2?"b":"t"]=size*cosr/2+_acc*sinr;
_acb[_ad2?"t":"b"]=size*cosr/2;
}else{
if(_ad1<90||(180<_ad1&&_ad1<270)){
_acb[side]=size*sinr+_acc*cosr;
_acb[_ad2?"t":"b"]=size*cosr+_acc*sinr;
}else{
_acb[side]=size*sinr+_acc*cosr;
_acb[_ad2?"b":"t"]=size*cosr+_acc*sinr;
}
}
}
break;
}
_acb[side]+=_aca+Math.max(_acf.length,_ad0.length);
}else{
var side=_ad2?"b":"t";
switch(_ad1){
case 0:
case 180:
_acb[side]=size;
_acb.l=_acb.r=_acc/2;
break;
case 90:
case 270:
_acb[side]=_acc;
_acb.l=_acb.r=size/2;
break;
default:
if((90-centerAnchorLimit)<=_ad1&&_ad1<=90||(270-centerAnchorLimit)<=_ad1&&_ad1<=270){
_acb[side]=size*sinr/2+_acc*cosr;
_acb[_ad2?"r":"l"]=size*cosr/2+_acc*sinr;
_acb[_ad2?"l":"r"]=size*cosr/2;
}else{
if(90<=_ad1&&_ad1<=(90+centerAnchorLimit)||270<=_ad1&&_ad1<=(270+centerAnchorLimit)){
_acb[side]=size*sinr/2+_acc*cosr;
_acb[_ad2?"l":"r"]=size*cosr/2+_acc*sinr;
_acb[_ad2?"r":"l"]=size*cosr/2;
}else{
if(_ad1<centerAnchorLimit||(180<_ad1&&_ad1<(180-centerAnchorLimit))){
_acb[side]=size*sinr+_acc*cosr;
_acb[_ad2?"r":"l"]=size*cosr+_acc*sinr;
}else{
_acb[side]=size*sinr+_acc*cosr;
_acb[_ad2?"l":"r"]=size*cosr+_acc*sinr;
}
}
}
break;
}
_acb[side]+=_aca+Math.max(_acf.length,_ad0.length);
}
}
if(_acc){
this._cachedLabelWidth=_acc;
}
return _acb;
},render:function(dim,_ad3){
if(!this.dirty){
return this;
}
var o=this.opt;
var _ad4,stop,_ad5,_ad6,_ad7,_ad8,ta=this.chart.theme.axis,_ad9=o.font||(ta.majorTick&&ta.majorTick.font)||(ta.tick&&ta.tick.font),_ada=o.fontColor||(ta.majorTick&&ta.majorTick.fontColor)||(ta.tick&&ta.tick.fontColor)||"black",_adb=this.chart.theme.getTick("major",o),_adc=this.chart.theme.getTick("minor",o),_add=this.chart.theme.getTick("micro",o),_ade=Math.max(_adb.length,_adc.length,_add.length),_adf="stroke" in o?o.stroke:ta.stroke,size=_ad9?g.normalizedLength(g.splitFontString(_ad9).size):0;
if(this.vertical){
_ad4={y:dim.height-_ad3.b};
stop={y:_ad3.t};
_ad5={x:0,y:-1};
if(o.leftBottom){
_ad4.x=stop.x=_ad3.l;
_ad6={x:-1,y:0};
_ad8="end";
}else{
_ad4.x=stop.x=dim.width-_ad3.r;
_ad6={x:1,y:0};
_ad8="start";
}
_ad7={x:_ad6.x*(_ade+_aca),y:size*0.4};
}else{
_ad4={x:_ad3.l};
stop={x:dim.width-_ad3.r};
_ad5={x:1,y:0};
_ad8="middle";
if(o.leftBottom){
_ad4.y=stop.y=dim.height-_ad3.b;
_ad6={x:0,y:1};
_ad7={y:_ade+_aca+size};
}else{
_ad4.y=stop.y=_ad3.t;
_ad6={x:0,y:-1};
_ad7={y:-_ade-_aca};
}
_ad7.x=0;
}
this.cleanGroup();
try{
var s=this.group,c=this.scaler,t=this.ticks,_ae0,f=lin.getTransformerFromModel(this.scaler),_ae1=(dojox.gfx.renderer=="canvas"),_ae2=_ae1||this.opt.htmlLabels&&!dojo.isIE&&!dojo.isOpera?"html":"gfx",dx=_ad6.x*_adb.length,dy=_ad6.y*_adb.length;
s.createLine({x1:_ad4.x,y1:_ad4.y,x2:stop.x,y2:stop.y}).setStroke(_adf);
dojo.forEach(t.major,function(tick){
var _ae3=f(tick.value+0.5),elem,x=_ad4.x+_ad5.x*_ae3,y=_ad4.y+_ad5.y*_ae3;
s.createLine({x1:x,y1:y,x2:x+dx,y2:y+dy}).setStroke(_adb);
if(tick.label){
elem=dc.axis2d.common.createText[_ae2](this.chart,s,x+_ad7.x,y+_ad7.y,_ad8,tick.label,_ad9,_ada);
if(_ae2=="html"){
this.htmlElements.push(elem);
}
}
},this);
dx=_ad6.x*_adc.length;
dy=_ad6.y*_adc.length;
_ae0=c.minMinorStep<=c.minor.tick*c.bounds.scale;
dojo.forEach(t.minor,function(tick){
var _ae4=f(tick.value+0.5),elem,x=_ad4.x+_ad5.x*_ae4,y=_ad4.y+_ad5.y*_ae4;
s.createLine({x1:x,y1:y,x2:x+dx,y2:y+dy}).setStroke(_adc);
if(_ae0&&tick.label){
elem=dc.axis2d.common.createText[_ae2](this.chart,s,x+_ad7.x,y+_ad7.y,_ad8,tick.label,_ad9,_ada);
if(_ae2=="html"){
this.htmlElements.push(elem);
}
}
},this);
dx=_ad6.x*_add.length;
dy=_ad6.y*_add.length;
dojo.forEach(t.micro,function(tick){
var _ae5=f(tick.value+0.5),elem,x=_ad4.x+_ad5.x*_ae5,y=_ad4.y+_ad5.y*_ae5;
s.createLine({x1:x,y1:y,x2:x+dx,y2:y+dy}).setStroke(_add);
},this);
}
catch(e){
}
this.dirty=false;
return this;
}});
})();
}
if(!dojo._hasResource["insight.charting.ResponseTimeHistogram"]){
dojo._hasResource["insight.charting.ResponseTimeHistogram"]=true;
dojo.provide("insight.charting.ResponseTimeHistogram");
dojo.declare("insight.charting.ResponseTimeHistogram",insight.charting.Chart,{timeRange:null,postCreate:function(){
this.inherited(arguments);
this.chart.addAxis("x",{type:insight.charting.axis2d.Metric,includeZero:true,fixed:false,natural:true,minorTicks:false,title:"Invocations"});
this.chart.addAxis("y",{type:insight.charting.axis2d.Histogram,vertical:true,fixed:false,majorTickStep:this.dataPoints-1,minorTicks:false,labelFunc:dojo.hitch(this,this.labelFunc)});
this.chart.addPlot("default",{type:insight.charting.plot2d.HistogramBars,gap:0.5});
this.chart.addSeries("histogram",new dojox.charting.DataSeries(this.stores.main,{},function(s,i){
var o={y:s.getValue(i,"count")},_ae6=s._data.boundries,_ae7=s.getValue(i,"start");
if(_ae6){
if(_ae7<=_ae6.satisfied){
o.fill="#46A218";
o.stroke={color:"#FFF",width:1};
}else{
if(_ae7<=_ae6.tolerated){
o.fill="#A2D018";
o.stroke={color:"#FFF",width:1};
}else{
o.fill="#FE1818";
o.stroke={color:"#FFF",width:1};
}
}
}
return o;
}),{plot:"default"});
this._registerChartEvents("default");
this.addPlotAction("default",insight.charting.action2d.PointerHover);
this.addPlotAction("default",insight.charting.action2d.StrokeHighlight,{highlight:"#2354A4"});
this.addPlotAction("default",insight.charting.action2d.Tooltip,{text:dojo.hitch(this,function(_ae8){
var s=this.stores.main,i=s._items[_ae8.index];
return s.getValue(i,"startLabel")+" - "+s.getValue(i,"endLabel")+"<br />"+dojo.number.format(s.getValue(i,"count"))+" invocations";
})});
},_urlParams:function(){
var _ae9=this.inherited(arguments);
if(this.timeRange&&!(_ae9.start&&_ae9.end)){
_ae9.start=this.timeRange.getStart();
_ae9.end=this.timeRange.getEnd();
}
return _ae9;
},updateTimeRange:function(args){
if(args.timeRange){
this.timeRange=args.timeRange;
this.refresh();
}
},labelFunc:function(text){
if(!(this.stores.main&&this.stores.main._data&&this.stores.main._items)){
return text;
}
var _aea=parseInt(text);
if(_aea===0){
return this.stores.main._data.startLabel;
}else{
if(_aea===this.stores.main._items.length-1){
return this.stores.main._data.endLabel;
}else{
return "";
}
}
}});
}
if(!dojo._hasResource["insight.charting._HealthBands"]){
dojo._hasResource["insight.charting._HealthBands"]=true;
dojo.provide("insight.charting._HealthBands");
insight.charting._HealthBands.healthBands=[{from:null,to:0.5,color:[255,52,0,0.1],value:0.25,text:"Unacceptable"},{from:0.5,to:0.7,color:[255,135,0,0.1],value:0.6,text:"Poor"},{from:0.7,to:0.85,color:[234,211,0,0.1],value:0.775,text:"Fair"},{from:0.85,to:0.94,color:[89,210,0,0.1],value:0.895,text:"Good"},{from:0.94,to:null,color:[3,144,0,0.1],value:1.01,text:"Excellent"}];
}
if(!dojo._hasResource["insight.components.ErrorButton"]){
dojo._hasResource["insight.components.ErrorButton"]=true;
dojo.provide("insight.components.ErrorButton");
dojo.declare("insight.components.ErrorButton",dijit.form.Button,{hidden:true,parentNode:null,startup:function(){
this.inherited(arguments);
this.parentNode=this.domNode.parentNode;
this.attr("hidden",this.hidden);
},_setHiddenAttr:function(_aeb){
this.hidden=_aeb;
if(!this.parentNode){
return;
}
dojo.style(this.parentNode,{display:_aeb?"none":null});
}});
}
if(!dojo._hasResource["dojox.layout.ResizeHandle"]){
dojo._hasResource["dojox.layout.ResizeHandle"]=true;
dojo.provide("dojox.layout.ResizeHandle");
dojo.experimental("dojox.layout.ResizeHandle");
dojo.declare("dojox.layout.ResizeHandle",[dijit._Widget,dijit._Templated],{targetId:"",targetContainer:null,resizeAxis:"xy",activeResize:false,activeResizeClass:"dojoxResizeHandleClone",animateSizing:true,animateMethod:"chain",animateDuration:225,minHeight:100,minWidth:100,constrainMax:false,maxHeight:0,maxWidth:0,fixedAspect:false,intermediateChanges:false,startTopic:"/dojo/resize/start",endTopic:"/dojo/resize/stop",templateString:"<div dojoAttachPoint=\"resizeHandle\" class=\"dojoxResizeHandle\"><div></div></div>",postCreate:function(){
this.connect(this.resizeHandle,"onmousedown","_beginSizing");
if(!this.activeResize){
this._resizeHelper=dijit.byId("dojoxGlobalResizeHelper");
if(!this._resizeHelper){
this._resizeHelper=new dojox.layout._ResizeHelper({id:"dojoxGlobalResizeHelper"}).placeAt(dojo.body());
dojo.addClass(this._resizeHelper.domNode,this.activeResizeClass);
}
}else{
this.animateSizing=false;
}
if(!this.minSize){
this.minSize={w:this.minWidth,h:this.minHeight};
}
if(this.constrainMax){
this.maxSize={w:this.maxWidth,h:this.maxHeight};
}
this._resizeX=this._resizeY=false;
var _aec=dojo.partial(dojo.addClass,this.resizeHandle);
switch(this.resizeAxis.toLowerCase()){
case "xy":
this._resizeX=this._resizeY=true;
_aec("dojoxResizeNW");
break;
case "x":
this._resizeX=true;
_aec("dojoxResizeW");
break;
case "y":
this._resizeY=true;
_aec("dojoxResizeN");
break;
}
},_beginSizing:function(e){
if(this._isSizing){
return false;
}
dojo.publish(this.startTopic,[this]);
this.targetWidget=dijit.byId(this.targetId);
this.targetDomNode=this.targetWidget?this.targetWidget.domNode:dojo.byId(this.targetId);
if(this.targetContainer){
this.targetDomNode=this.targetContainer;
}
if(!this.targetDomNode){
return false;
}
if(!this.activeResize){
var c=dojo.position(this.targetDomNode,true);
this._resizeHelper.resize({l:c.x,t:c.y,w:c.w,h:c.h});
this._resizeHelper.show();
}
this._isSizing=true;
this.startPoint={x:e.clientX,y:e.clientY};
var mb=this.targetWidget?dojo.marginBox(this.targetDomNode):dojo.contentBox(this.targetDomNode);
this.startSize={w:mb.w,h:mb.h};
if(this.fixedAspect){
var max,val;
if(mb.w>mb.h){
max="w";
val=mb.w/mb.h;
}else{
max="h";
val=mb.h/mb.w;
}
this._aspect={prop:max};
this._aspect[max]=val;
}
this._pconnects=[];
this._pconnects.push(dojo.connect(dojo.doc,"onmousemove",this,"_updateSizing"));
this._pconnects.push(dojo.connect(dojo.doc,"onmouseup",this,"_endSizing"));
dojo.stopEvent(e);
},_updateSizing:function(e){
if(this.activeResize){
this._changeSizing(e);
}else{
var tmp=this._getNewCoords(e);
if(tmp===false){
return;
}
this._resizeHelper.resize(tmp);
}
e.preventDefault();
},_getNewCoords:function(e){
try{
if(!e.clientX||!e.clientY){
return false;
}
}
catch(e){
return false;
}
this._activeResizeLastEvent=e;
var dx=(this.isLeftToRight()?this.startPoint.x-e.clientX:e.clientX-this.startPoint.x),dy=this.startPoint.y-e.clientY,newW=this.startSize.w-(this._resizeX?dx:0),newH=this.startSize.h-(this._resizeY?dy:0);
return this._checkConstraints(newW,newH);
},_checkConstraints:function(newW,newH){
if(this.minSize){
var tm=this.minSize;
if(newW<tm.w){
newW=tm.w;
}
if(newH<tm.h){
newH=tm.h;
}
}
if(this.constrainMax&&this.maxSize){
var ms=this.maxSize;
if(newW>ms.w){
newW=ms.w;
}
if(newH>ms.h){
newH=ms.h;
}
}
if(this.fixedAspect){
var ta=this._aspect[this._aspect.prop];
if(newW<newH){
newH=newW*ta;
}else{
if(newH<newW){
newW=newH*ta;
}
}
}
return {w:newW,h:newH};
},_changeSizing:function(e){
var tmp=this._getNewCoords(e);
if(tmp===false){
return;
}
if(this.targetWidget&&dojo.isFunction(this.targetWidget.resize)){
this.targetWidget.resize(tmp);
}else{
if(this.animateSizing){
var anim=dojo.fx[this.animateMethod]([dojo.animateProperty({node:this.targetDomNode,properties:{width:{start:this.startSize.w,end:tmp.w}},duration:this.animateDuration}),dojo.animateProperty({node:this.targetDomNode,properties:{height:{start:this.startSize.h,end:tmp.h}},duration:this.animateDuration})]);
anim.play();
}else{
dojo.style(this.targetDomNode,{width:tmp.w+"px",height:tmp.h+"px"});
}
}
if(this.intermediateChanges){
this.onResize(e);
}
},_endSizing:function(e){
dojo.forEach(this._pconnects,dojo.disconnect);
var pub=dojo.partial(dojo.publish,this.endTopic,[this]);
if(!this.activeResize){
this._resizeHelper.hide();
this._changeSizing(e);
setTimeout(pub,this.animateDuration+15);
}else{
pub();
}
this._isSizing=false;
this.onResize(e);
},onResize:function(e){
}});
dojo.declare("dojox.layout._ResizeHelper",dijit._Widget,{show:function(){
dojo.fadeIn({node:this.domNode,duration:120,beforeBegin:function(n){
dojo.style(n,"display","");
}}).play();
},hide:function(){
dojo.fadeOut({node:this.domNode,duration:250,onEnd:function(n){
dojo.style(n,"display","none");
}}).play();
},resize:function(dim){
dojo.marginBox(this.domNode,dim);
}});
}
if(!dojo._hasResource["spring.HtmlFragmentResponseHandler"]){
dojo._hasResource["spring.HtmlFragmentResponseHandler"]=true;
dojo.provide("spring.HtmlFragmentResponseHandler");
(function(){
dojo.declare("spring.HtmlFragmentResponseHandler",null,{evalScripts:true,replaceWidgets:true,requireFragment:true,mappings:null,constructor:function(_aed){
dojo.mixin(this,_aed);
this.mappings=this.mappings||{};
},handle:function(_aee,_aef){
if(_aef.xhr.status>=300){
return this.error(_aee,_aef);
}else{
return this.load(_aee,_aef);
}
},handler:function(){
return dojo.hitch(this,this.handle);
},load:function(_af0,_af1){
if(this.requireFragment&&!_af0.fragment){
try{
var l=dojo.global.location,href=l.href,hash=l.hash;
dojo.global.location=href.substring(0,href.length-hash.length);
}
finally{
return;
}
}
this._replaceDomNodes(_af0);
if(this.evalScripts){
this._evalScripts(_af0);
}
return _af0;
},error:function(_af2,_af3){
console.error("HTTP status code: ",_af3.xhr.status);
},_replaceDomNodes:function(_af4){
dojo.forEach(_af4.domNodes,function(_af5){
if(_af5.id){
var _af6=this._lookupTargetId(_af5.id);
_af5.id=_af6;
if(this.replaceWidgets&&dijit&&dijit.byId(_af6)){
this._replaceWidget(_af6,_af5);
}else{
if(dojo.byId(_af6)){
this._replaceNode(_af6,_af5);
}else{
console.error("An existing DOM elment with id '"+_af6+"' could not be found for replacement.");
}
}
}
},this);
},_lookupTargetId:function(id){
return this.mappings[id]?this.mappings[id]:id;
},_replaceWidget:function(id,node){
var _af7=dijit.byId(id);
_af7.destroyDescendants(false);
dojo.place(node,_af7.attr("domNode"),"replace");
_af7.attr("domNode",node);
},_replaceNode:function(id,node){
var _af8=dojo.byId(id);
dojo.place(node,_af8,"replace");
},_evalScripts:function(_af9){
dojo.forEach(_af9.scriptNodes,function(_afa){
dojo.eval(_afa);
},this);
}});
var _afb=/(?:<script(.|[\n|\r])*?>)((\n|\r|.)*?)(?:<\/script>)/img,_afc=/(?:<script(.|[\n|\r])*?>)((\n|\r|.)*?)(?:<\/script>)/im,_afd=[/<!--/mg,/\/\/-->/mg,/<!\[CDATA\[(\/\/>)*/mg,/(<!)*\]\]>/mg];
spring.HtmlFragmentResponseHandler.handle=function(){
if(arguments[1]&&arguments[1].xhr){
return new spring.HtmlFragmentResponseHandler().handle(arguments[0],arguments[1]);
}else{
return new spring.HtmlFragmentResponseHandler(arguments[0]).handler();
}
};
if(!dojo.contentHandlers.html){
dojo.contentHandlers.html=function(xhr){
var _afe={raw:xhr.responseText,scriptNodes:[],domNodes:[],fragment:true},_aff=dojo.string.trim(_afe.raw),_b00,_b01;
if(_aff.substring(0,14).toUpperCase()=="<!DOCTYPE HTML"||_aff.substring(0,5).toUpperCase()=="<HTML"){
_afe.fragment=false;
}
_b00=_afe.raw.match(_afb);
dojo.forEach(_b00,function(_b02){
if(_afc.test(_b02)){
var _b03=_b02.match(_afc)[2];
dojo.forEach(_afd,function(_b04){
_b03=_b03.replace(_b04,"");
},this);
_afe.scriptNodes.push(_b03);
}
},this);
_afe.raw=_afe.raw.replace(_afb,"");
_b01=dojo.doc.createElement("span");
_b01.innerHTML=_afe.raw;
_afe.domNodes=dojo.query("> *",_b01);
return _afe;
};
}else{
console.warn("Unable to register spring.HtmlFragmentResponseHandler as 'html' for dojo.contentHandlers");
}
})();
}
if(!dojo._hasResource["insight.components.PageModule"]){
dojo._hasResource["insight.components.PageModule"]=true;
dojo.provide("insight.components.PageModule");
dojo.declare("insight.components.PageModule",dijit._Widget,{url:null,_urlParams:null,refreshOn:null,maxHeight:null,timeout:insight.runtime.getXhrTimeout("PageModule"),resize:null,constructor:function(){
this._urlParams={};
},postCreate:function(){
this.inherited(arguments);
this.startup();
if(dojo.isArray(this.refreshOn)){
dojo.forEach(this.refreshOn,function(_b05){
this.subscribe(_b05,function(){
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
},hide:function(_b06){
this._hidden=true;
dojo.style(this.domNode,"display","none");
if(!_b06){
this.destroyDescendants();
}
},show:function(){
this._hidden=false;
dojo.style(this.domNode,"display","block");
},hidden:function(){
return !!this._hidden;
},delayedLoad:function(_b07){
if(this._delayedLoad){
return;
}
this._delayedLoad=dojo.global.setTimeout(dojo.hitch(this,this.load),_b07);
},load:function(_b08,_b09){
if(this._delayedLoad){
dojo.global.clearTimeout(this._delayedLoad);
this._delayedLoad=null;
}
_b08=_b08||{};
_b09=_b09||"get";
if(this.url){
dojo.xhr(_b09,{handleAs:"html",load:dojo.hitch(this,this._load),error:dojo.hitch(this,this._error),url:this.url.build(dojo.delegate(this._urlParams,_b08)),timeout:this.timeout});
}
},_load:function(_b0a,_b0b){
if(!_b0a.fragment){
try{
var l=dojo.global.location,href=l.href,hash=l.hash;
dojo.global.location=href.substring(0,href.length-hash.length);
}
finally{
return;
}
}
var node=_b0a.domNodes[0];
if(!node){
console.error("a DOM node is required in the response");
}
this.destroyDescendants();
this._hidden=false;
node.id=this.domNode.id;
dojo.place(node,this.domNode,"replace");
this.domNode=node;
this.containerNode=this.domNode;
dojo.forEach(_b0a.scriptNodes,function(_b0c){
try{
dojo.eval("(function(){var self=dijit.byId('"+this.id+"');"+_b0c+"})();");
}
catch(e){
console.error("error evaling script",e);
}
},this);
if(this.resize){
var _b0d,_b0e=this.id+"#ResizeHandle",_b0f=dojo.query("> .page-module-body",this.domNode)[0],box=dojo.contentBox(_b0f),_b10=dojo.create("div",null,this.domNode,"last"),_b11=parseInt(Insight.getLocal(_b0e));
_b0d=new dojox.layout.ResizeHandle({resizeAxis:"y",targetContainer:_b0f,maxHeight:box.h,maxWidth:box.w,minWidth:box.w,constrainMax:true,activeResize:!dojo.isIE},_b10);
if(_b11<box.h){
dojo.style(_b0f,{height:_b11});
}else{
if(isNaN(_b11)&&this.maxHeight&&this.maxHeight<box.h){
dojo.style(_b0f,{height:this.maxHeight});
}
}
this.connect(_b0d,"onResize",function(){
var _b12=dojo.contentBox(_b0f);
Insight.setLocal(_b0e,(_b12.h*1.1<box.h)?_b12.h:null);
});
this.subscribe("window/resize",function(){
var _b13=dojo.contentBox(this.domNode).w-2;
_b0d.minSize.w=_b0d.minWidth=_b0d.maxSize.w=_b0d.maxWidth=_b13;
dojo.style(_b0f,{width:_b13});
});
}else{
if(this.maxHeight){
var _b14=dojo.query(".page-module-body",this.domNode)[0];
if(parseInt(dojo.style(_b14,"height"))>this.maxHeight){
dojo.style(_b14,"display","block");
dojo.style(_b14,"position","relative");
dojo.style(_b14,"height",this.maxHeight+"px");
dojo.style(_b14,"overflowX","hidden");
dojo.style(_b14,"overflowY","auto");
}
}
}
this.onReplace();
},_error:function(_b15,_b16){
dojo.publish("error/xhr",arguments);
},updateUrlTemplate:function(_b17){
this.url=_b17;
},updateUrlParams:function(_b18){
dojo.mixin(this._urlParams,_b18);
},_setDomNodeAttr:function(_b19){
this.domNode=_b19;
this.onReplace();
},_urlArgs:function(){
},connectForm:function(form){
form=dojo.byId(form);
form.onsubmit=dojo.hitch(this,function(){
if(!form.action){
form.action=this.url.build(this._urlParams);
}
dojo.xhr(form.method||"post",{handleAs:"html",form:form,load:dojo.hitch(this,this._load),error:dojo.hitch(this,this._error),timeout:this.timeout});
this.onSubmit(form);
return false;
});
},onSubmit:function(){
},onReplace:function(){
}});
}
if(!dojo._hasResource["insight.components.ErrorDialog"]){
dojo._hasResource["insight.components.ErrorDialog"]=true;
dojo.provide("insight.components.ErrorDialog");
dojo.declare("insight.components.ErrorDialog",insight.components.PageModule,{errorListDomNode:null,pauseThreshold:4,_pausedDueToErrors:false,_errorLog:null,postCreate:function(){
this.inherited(arguments);
this.hide(true);
this.subscribe("insight/error",this.addError);
this.errorListDomNode=dojo.query(".page-module-body .errorList table",this.domNode)[0];
this.errorList=new dijit.TitlePane({title:"Error Details",open:true},dojo.query(".page-module-body .errorList",this.domNode)[0]);
this.errorList.startup();
dojo.style(this.errorList.containerNode,{maxHeight:"150px",overflowY:"auto"});
},hide:function(){
this.inherited(arguments);
this._errorLog=[];
this._emptyList=true;
if(this._pausedDueToErrors){
Insight.play();
this._pausedDueToErrors=false;
dojo.publish("insight/error/play");
}
if(this._pausedNode){
this._pausedNode.parentNode.removeChild(this._pausedNode);
this._pausedNode=null;
}
dojo.publish("insight/error/close");
},addError:function(_b1a,code,uid,_b1b){
var mode=this._emptyList?"only":"first",now=dojo.date.locale.format(new Date(),{formatLength:"medium"});
this._emptyList=false;
dojo.create("tr",{innerHTML:"<td class='nowrap'>"+now+"</td><td class='nowrap'>"+code+"</td><td><span class='collapse-container'><span class='collapse-display'>"+_b1a+"</span><span class='collapse-spacing'>"+dojo.string.pad("-",_b1a.length*1.25,"- ")+"</span></span></td>"},this.errorListDomNode,mode);
if(!uid||this._errorLog.indexOf(uid)==-1&&!_b1b){
this._errorLog.push(uid);
}
if(this._errorLog.length>=this.pauseThreshold&&Insight.playing()&&!this._pausedNode){
Insight.pause();
this._pausedDueToErrors=true;
this._pausedNode=dojo.create("p",{className:"warning",innerHTML:"Data playback paused due to excessive errors.  Close error dialog to resume playback."},this.errorList.domNode,"before");
dojo.publish("insight/error/pause");
}
}});
}
if(!dojo._hasResource["insight.components.MenuItemLink"]){
dojo._hasResource["insight.components.MenuItemLink"]=true;
dojo.provide("insight.components.MenuItemLink");
dojo.declare("insight.components.MenuItemLink",dijit.MenuItem,{href:null,onClick:function(){
window.location=this.href.toString();
}});
}
if(!dojo._hasResource["insight.components.SelectDropDownButton"]){
dojo._hasResource["insight.components.SelectDropDownButton"]=true;
dojo.provide("insight.components.SelectDropDownButton");
dojo.declare("insight.components.SelectDropDownButton",[dijit.form.DropDownButton],{_selected:null,_menuItems:[],create:function(_b1c,_b1d){
var _b1e=dojo.byId(_b1d),menu=new dijit.Menu({},dojo.doc.createElement("div"));
dojo.query("option",_b1e).forEach(function(_b1f){
var _b20=_b1f.text,_b21=_b1f.value;
var item=new dijit.MenuItem({label:_b20},dojo.doc.createElement("div"));
this._menuItems.push({menu:menu,menuItem:item,label:_b20,value:_b21});
menu.addChild(item);
},this);
this.dropDown=menu;
this._selected=_b1e.value;
this.inherited(arguments);
},postCreate:function(){
this.inherited(arguments);
dojo.forEach(this._menuItems,function(item){
this.connect(item.menuItem,"onClick",function(){
this.attr("selected",item);
this.onChange(item.value);
});
if(this._selected==item.value){
this.attr("selected",item);
}
},this);
if(!this._selected){
this.attr("selected",this._menuItems[0].menuItem);
}
},_setSelectedAttr:function(item){
if(item){
this._selected=item.value;
this.attr("label",item.label);
}
},onChange:function(_b22){
}});
}
if(!dojo._hasResource["dojo.cookie"]){
dojo._hasResource["dojo.cookie"]=true;
dojo.provide("dojo.cookie");
dojo.cookie=function(name,_b23,_b24){
var c=document.cookie;
if(arguments.length==1){
var _b25=c.match(new RegExp("(?:^|; )"+dojo.regexp.escapeString(name)+"=([^;]*)"));
return _b25?decodeURIComponent(_b25[1]):undefined;
}else{
_b24=_b24||{};
var exp=_b24.expires;
if(typeof exp=="number"){
var d=new Date();
d.setTime(d.getTime()+exp*24*60*60*1000);
exp=_b24.expires=d;
}
if(exp&&exp.toUTCString){
_b24.expires=exp.toUTCString();
}
_b23=encodeURIComponent(_b23);
var _b26=name+"="+_b23,_b27;
for(_b27 in _b24){
_b26+="; "+_b27;
var _b28=_b24[_b27];
if(_b28!==true){
_b26+="="+_b28;
}
}
document.cookie=_b26;
}
};
dojo.cookie.isSupported=function(){
if(!("cookieEnabled" in navigator)){
this("__djCookieTest__","CookiesAllowed");
navigator.cookieEnabled=this("__djCookieTest__")=="CookiesAllowed";
if(navigator.cookieEnabled){
this("__djCookieTest__","",{expires:-1});
}
}
return navigator.cookieEnabled;
};
}
if(!dojo._hasResource["insight.components.SortableTable"]){
dojo._hasResource["insight.components.SortableTable"]=true;
dojo.provide("insight.components.SortableTable");
dojo.declare("insight.components.SortableTable",insight.components.PageModule,{sortSettings:{field:null,desc:null},defaultSortSettings:{field:null,desc:null},persist:true,startup:function(){
if(!this.sortSettings.field){
this._defaultSortSettings();
}
this.connect(this,"onReplace",this._enhanceTable);
this.inherited(arguments);
},sort:function(_b29,desc){
this.sortSettings.field=_b29;
this.sortSettings.desc=desc;
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
var _b2a="sort-field-";
var _b2b=null;
dojo.forEach(th["className"].split(" "),function(name){
if(name.indexOf(_b2a)==0){
_b2b=name.substr(_b2a.length);
}
});
if(_b2b==this.sortSettings.field){
var _b2c=dojo.doc.createElement("div");
_b2c.innerHTML=th.innerHTML;
dojo.addClass(th,"sorted");
dojo.addClass(th,this.sortSettings.desc?"sorted-asc":"sorted-desc");
dojo.place(_b2c,th,"only");
}
this.connect(th,"onclick",function(){
if(this.sortSettings.field==_b2b){
this.sort(_b2b,!this.sortSettings.desc);
}else{
this.sort(_b2b,dojo.hasClass(th,"numeric")||dojo.hasClass(th,"sort-numeric"));
}
});
},_enhanceCollapsibleCells:function(td){
var _b2d=dojo.doc.createElement("SPAN"),_b2e=dojo.doc.createElement("SPAN"),_b2f=dojo.doc.createElement("SPAN");
dojo.addClass(_b2d,"collapse-container");
dojo.addClass(_b2e,"collapse-display");
dojo.addClass(_b2f,"collapse-spacing");
dojo.place(_b2e,_b2d,"last");
dojo.place(_b2f,_b2d,"last");
dojo.forEach(td.childNodes,function(node){
dojo.place(node,_b2e,"last");
},this);
_b2f.innerHTML=this._createSpacer(_b2e);
dojo.place(_b2d,td,"only");
},_createSpacer:function(node,_b30){
_b30=_b30||"";
dojo.forEach(node.childNodes,function(_b31){
_b30+=this._createSpacer(_b31,_b30);
},this);
if(node.nodeType==3){
_b30+=dojo.string.pad("-",dojo.string.trim(node.nodeValue).length*1.25,"- ");
}
return _b30;
},_defaultSortSettings:function(){
var _b32;
if(this.persist&&dojo.cookie.isSupported()){
_b32=dojo.fromJson(dojo.cookie(this.id+"_sortSettings"));
}
if(!_b32){
_b32=this.defaultSortSettings;
}
this.sortSettings=_b32;
this._urlParams.sortField=this.sortSettings.field;
this._urlParams.sortDesc=this.sortSettings.desc;
}});
}
if(!dojo._hasResource["insight.components.ToggleMenuItem"]){
dojo._hasResource["insight.components.ToggleMenuItem"]=true;
dojo.provide("insight.components.ToggleMenuItem");
dojo.declare("insight.components.ToggleMenuItem",dijit.MenuItem,{templateString:dojo.cache("insight.components","templates/ToggleMenuItem.html","<tr class=\"dijitReset dijitMenuItem\" dojoAttachPoint=\"focusNode\" waiRole=\"menuitemcheckbox\" tabIndex=\"-1\"\n\t\tdojoAttachEvent=\"onmouseenter:_onHover,onmouseleave:_onUnhover,ondijitclick:_onClick\">\n\t<td class=\"dijitReset dijitMenuItemIconCell\" waiRole=\"presentation\">\n\t\t<img src=\"${_blankGif}\" alt=\"\" class=\"dijitMenuItemIcon dijitCheckedMenuItemIcon\" dojoAttachPoint=\"iconNode\"/>\n\t\t<span class=\"dijitCheckedMenuItemIconChar\">&#10003;</span>\n\t</td>\n\t<td class=\"dijitReset dijitMenuItemLabel\" colspan=\"2\" dojoAttachPoint=\"containerNode,labelNode\"></td>\n\t<td class=\"dijitReset dijitMenuItemAccelKey\" style=\"display: none\" dojoAttachPoint=\"accelKeyNode\"></td>\n\t<td class=\"dijitReset dijitMenuArrowCell\" waiRole=\"presentation\">&nbsp;</td>\n</tr>\n"),value:"",toggleStates:["","+","-"],toggleCssClass:{"":"","+":"Plus","-":"Minus"},postCreate:function(){
this.inherited(arguments);
var i=dojo.indexOf(this.toggleStates,this.value);
this.set("value",this.toggleStates[i>=0?i:0]);
},_setValueAttr:function(_b33){
dojo.forEach(this.toggleStates,function(_b34){
var _b35=this.toggleCssClass[_b34];
if(_b34===_b33){
dojo.addClass(this.domNode,"insightToggleMenuItemChecked"+_b35);
}else{
dojo.removeClass(this.domNode,"insightToggleMenuItemChecked"+_b35);
}
},this);
this.value=_b33;
},onChange:function(_b36){
},_onClick:function(e){
if(!this.disabled){
var _b37=(dojo.indexOf(this.toggleStates,this.value)+1)%this.toggleStates.length;
this.set("value",this.toggleStates[_b37]);
this.onChange(this.value);
}
this.inherited(arguments);
}});
}
if(!dojo._hasResource["dojo.hash"]){
dojo._hasResource["dojo.hash"]=true;
dojo.provide("dojo.hash");
(function(){
dojo.hash=function(hash,_b38){
if(!arguments.length){
return _b39();
}
if(hash.charAt(0)=="#"){
hash=hash.substring(1);
}
if(_b38){
_b3a(hash);
}else{
location.href="#"+hash;
}
return hash;
};
var _b3b=null,_b3c=null,_b3d=dojo.config.hashPollFrequency||100;
function _b3e(str,_b3f){
var i=str.indexOf(_b3f);
return (i>=0)?str.substring(i+1):"";
};
function _b39(){
return _b3e(location.href,"#");
};
function _b40(){
dojo.publish("/dojo/hashchange",[_b39()]);
};
function _b41(){
if(_b39()===_b3b){
return;
}
_b3b=_b39();
_b40();
};
function _b3a(hash){
if(_b3c){
if(_b3c.isTransitioning()){
setTimeout(dojo.hitch(null,_b3a,hash),_b3d);
return;
}
var href=_b3c.iframe.location.href;
var _b42=href.indexOf("?");
_b3c.iframe.location.replace(href.substring(0,_b42)+"?"+hash);
return;
}
location.replace("#"+hash);
_b41();
};
function _b43(){
var ifr=document.createElement("iframe"),_b44="dojo-hash-iframe",_b45=dojo.config.dojoBlankHtmlUrl||dojo.moduleUrl("dojo","resources/blank.html");
ifr.id=_b44;
ifr.src=_b45+"?"+_b39();
ifr.style.display="none";
document.body.appendChild(ifr);
this.iframe=dojo.global[_b44];
var _b46,_b47,_b48,_b49,_b4a,_b4b=this.iframe.location;
function _b4c(){
_b3b=_b39();
_b46=_b4a?_b3b:_b3e(_b4b.href,"?");
_b47=false;
_b48=null;
};
this.isTransitioning=function(){
return _b47;
};
this.pollLocation=function(){
if(!_b4a){
try{
var _b4d=_b3e(_b4b.href,"?");
if(document.title!=_b49){
_b49=this.iframe.document.title=document.title;
}
}
catch(e){
_b4a=true;
console.error("dojo.hash: Error adding history entry. Server unreachable.");
}
}
var hash=_b39();
if(_b47&&_b3b===hash){
if(_b4a||_b4d===_b48){
_b4c();
_b40();
}else{
setTimeout(dojo.hitch(this,this.pollLocation),0);
return;
}
}else{
if(_b3b===hash&&(_b4a||_b46===_b4d)){
}else{
if(_b3b!==hash){
_b3b=hash;
_b47=true;
_b48=hash;
ifr.src=_b45+"?"+_b48;
_b4a=false;
setTimeout(dojo.hitch(this,this.pollLocation),0);
return;
}else{
if(!_b4a){
location.href="#"+_b4b.search.substring(1);
_b4c();
_b40();
}
}
}
}
setTimeout(dojo.hitch(this,this.pollLocation),_b3d);
};
_b4c();
setTimeout(dojo.hitch(this,this.pollLocation),_b3d);
};
dojo.addOnLoad(function(){
if("onhashchange" in dojo.global&&(!dojo.isIE||(dojo.isIE>=8&&document.compatMode!="BackCompat"))){
dojo.connect(dojo.global,"onhashchange",_b40);
}else{
if(document.addEventListener){
_b3b=_b39();
setInterval(_b41,_b3d);
}else{
if(document.attachEvent){
_b3c=new _b43();
}
}
}
});
})();
}
if(!dojo._hasResource["insight.resources.ResourceKey"]){
dojo._hasResource["insight.resources.ResourceKey"]=true;
dojo.provide("insight.resources.ResourceKey");
dojo.declare("insight.resources.ResourceKey",null,{_namespace:null,_attributes:null,constructor:function(_b4e){
this._attributes={};
if(dojo.isString(_b4e)){
this._parse(_b4e);
}else{
if(_b4e&&_b4e.declaredClass=="insight.resources.ResourceKey"){
this._namespace=_b4e._namespace;
this._attributes=dojo.mixin({},_b4e._attributes);
}
}
},_parse:function(_b4f){
_b4f=this._parseNamespace(_b4f);
this._parseAttributes(_b4f);
},_parseNamespace:function(_b50){
var i=_b50.indexOf(":");
this._namespace=_b50.substring(0,i);
return _b50.substring(i+1);
},_parseAttributes:function(_b51){
var name,_b52,i;
while(_b51.length>0){
i=_b51.indexOf("=");
name=_b51.substring(0,i);
_b51=_b51.substring(i+1);
if(_b51.charAt(0)==="\""){
i=_b51.indexOf(",",_b51.indexOf("\"",1));
}else{
i=_b51.indexOf(",");
}
if(i===-1){
_b52=_b51;
_b51="";
}else{
_b52=_b51.substring(0,i);
_b51=_b51.substring(i+1);
}
this._attributes[name]=_b52;
}
},getName:function(){
return this.getAttribute("name");
},setName:function(name){
this.setAttribute("name",name);
},getType:function(){
return this.getAttribute("type");
},setType:function(type){
return this.setAttribute("type",type);
},getAttributeNames:function(){
var _b53=[];
for(var name in this._attributes){
if(this._attributes.hasOwnProperty(name)){
_b53.push(name);
}
}
return _b53.sort();
},getAttribute:function(name){
return this._attributes[name];
},setAttribute:function(name,_b54){
if(_b54){
this._attributes[name]=_b54;
}else{
delete this._attributes[name];
}
},getNamespace:function(){
return this._namespace;
},setNamespace:function(_b55){
this._namespace=_b55;
},makeParentResourceKey:function(){
var type=this.getType(),_b56,_b57;
if(!type||type.lastIndexOf(".")==-1){
return null;
}
_b56=type.substring(0,type.lastIndexOf("."));
_b57=new insight.resources.ResourceKey();
_b57.setNamespace(this.getNamespace());
dojo.forEach(this.getAttributeNames(),function(_b58){
if(_b58=="type"){
_b57.setAttribute("type",_b56);
}else{
if(_b58==_b56){
_b57.setAttribute("name",this.getAttribute(_b56));
}else{
if(_b58!="name"){
_b57.setAttribute(_b58,this.getAttribute(_b58));
}
}
}
},this);
return _b57;
},makeComponentKey:function(_b59){
if(this.getType()==_b59){
return this.clone();
}else{
if(!this.getAttribute(_b59)){
return null;
}
}
var _b5a=new insight.resources.ResourceKey();
_b5a.setNamespace(this.getNamespace());
_b5a.setType(_b59);
_b5a.setName(this.getType()==_b59?this.getName():this.getAttribute(_b59));
dojo.forEach(_b59.split("."),function(_b5b){
if(_b59!=_b5b){
_b5a.setAttribute(_b5b,this.getAttribute(_b5b));
}
},this);
return _b5a;
},makeComponentlessKey:function(_b5c){
var _b5d=this.getType().split("."),_b5e=new insight.resources.ResourceKey();
type="";
_b5e.setNamespace(this.getNamespace());
if(this.getType()==_b5c){
return null;
}else{
if(dojo.indexOf(_b5d,_b5c)==-1){
return this.clone();
}
}
_b5e.setName(this.getName());
dojo.forEach(_b5d,function(_b5f){
if(_b5c!=_b5f){
_b5e.setAttribute(_b5f,this.getAttribute(_b5f));
if(type.length>0){
type+=".";
}
type+=_b5f;
}
},this);
_b5e.setType(type);
if(type.indexOf(".")==-1){
_b5e.setName(this.getAttribute(type));
_b5e.setAttribute(type,null);
}
return _b5e;
},makeQueryKey:function(type){
var key=this.clone();
key.setAttribute(key.getType(),key.getName());
if(type){
key.setType(type);
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
var _b60=key.getType()=="Server"?this.clone():key,_b61=key.getType()=="Server"?key:this.clone(),_b62=new insight.resources.ResourceKey();
_b62.setNamespace(this.getNamespace());
if(!_b60.getName()&&!_b61.getName()){
return _b62;
}else{
if(!_b60.getName()){
return _b61;
}else{
if(!_b61.getName()){
return _b60;
}
}
}
if(_b60.getType()=="Application.EndPoint"){
_b62.setType("Application.Server.EndPoint");
_b62.setName(_b60.getName());
_b62.setAttribute("Application",_b60.getAttribute("Application"));
_b62.setAttribute("Server",_b61.getName());
}else{
_b62.setType("Application.Server");
_b62.setName(this.quote(this.unquote(_b60.getName())+"-"+this.unquote(_b61.getName())));
_b62.setAttribute("Application",_b60.getName());
_b62.setAttribute("Server",_b61.getName());
}
return _b62;
},unquote:function(_b63){
return _b63.substring(1,_b63.length-1);
},quote:function(_b64){
return "\""+_b64+"\"";
},toString:function(){
var _b65="",_b66,_b67;
if(this._namespace){
_b65+=this._namespace;
_b65+=":";
}
_b67=this.getAttributeNames();
for(_b66 in _b67){
_b66=_b67[_b66];
if(_b65.indexOf(":")+1!==_b65.length){
_b65+=",";
}
_b65+=_b66;
_b65+="=";
_b65+=this._attributes[_b66];
}
return _b65;
}});
}
if(!dojo._hasResource["insight.traces.TraceList"]){
dojo._hasResource["insight.traces.TraceList"]=true;
dojo.provide("insight.traces.TraceList");
dojo.declare("insight.traces.TraceList",insight.components.SortableTable,{start:null,end:null,_activeRow:null,postCreate:function(){
this.inherited(arguments);
this.start=this.url.params.start;
this.end=this.url.params.end;
},_enhanceTable:function(){
this._addRowInteractivity();
this._selectRow(dojo.query(".trace_list_item",this.domNode)[0]);
this.inherited(arguments);
},loadWindow:function(_b68){
dojo.mixin(this._urlParams,_b68);
this.load();
},_addRowInteractivity:function(){
var _b69=dojo.query("table",this.domNode)[0];
dijit.setWaiRole(_b69,"navigation");
if(!this._keyHandlerMap){
this.connect(_b69,"onkeypress",this._onKeyPress);
}
dojo.query(".trace_list_item",_b69).forEach(function(row){
dijit.setWaiRole(row,"link");
this.connect(row,"onclick",function(){
this._onClick(row);
});
this.connect(row,"onmouseenter",function(){
this._onMouseEnter(row);
});
this.connect(row,"onmouseleave",function(){
this._onMouseLeave(row);
});
dojo.query("a",row).forEach(function(link){
link.parentNode.innerHTML=link.innerHTML;
},this);
},this);
},_selectRow:function(row){
if(row&&dojo.hasClass(row,"trace_list_item")){
if(this._activeRow){
dojo.removeClass(this._activeRow,"selected");
this._activeRow.setAttribute("tabIndex","-1");
dijit.setWaiState(this._activeRow,"selected",false);
}
this._activeRow=row;
dojo.addClass(this._activeRow,"selected");
this._scrollToSelectedRow();
this._activeRow.setAttribute("tabIndex","0");
dijit.setWaiState(this._activeRow,"selected",true);
row.focus();
var _b6a=this._activeRow.id.substr(this._activeRow.id.lastIndexOf("_")+1);
this.loadTrace(_b6a);
}
},_scrollToSelectedRow:function(){
var _b6b=dojo.query("table",this.domNode)[0];
var row=this._activeRow;
var _b6c=row.offsetTop-_b6b.scrollTop;
var _b6d=(row.offsetTop+row.clientHeight)-_b6b.scrollTop;
if(!(_b6c>0&&_b6c<_b6b.clientHeight&&_b6d>0&&_b6d<_b6b.clientHeight)){
var _b6e=row.offsetTop-(_b6b.clientHeight/2)+(row.clientHeight/2);
if(_b6e>_b6b.scrollHeight-_b6b.clientHeight){
_b6e=_b6b.scrollHeight-_b6b.clientHeight;
}else{
if(_b6e<_b6b.clientHeight/2){
_b6e=0;
}
}
_b6b.scrollTop=_b6e;
}
},loadTrace:function(_b6f){
},_onMouseEnter:function(row){
if(row){
if(dojo.hasClass(row,"trace_list_item")){
dojo.addClass(row,"hover");
}
var date=dojo.attr(row,"trace-date");
if(date){
dojo.publish("highlightDate",[date]);
}
}
},_onMouseLeave:function(row){
if(row){
if(dojo.hasClass(row,"trace_list_item")){
dojo.removeClass(row,"hover");
}
var date=dojo.attr(row,"trace-date");
if(date){
dojo.publish("blurDate",[date]);
}
}
},_onClick:function(row){
if(row&&dojo.hasClass(row,"trace_list_item")){
this._selectRow(row);
}
},_onKeyPress:function(e){
if(e.altKey){
return;
}
var dk=dojo.keys;
var key=e.charOrCode;
var map=this._keyHandlerMap;
if(!map){
map={};
map[dojo._isBodyLtr()?dk.LEFT_ARROW:dk.RIGHT_ARROW]="_onUpArrow";
map[dojo._isBodyLtr()?dk.RIGHT_ARROW:dk.LEFT_ARROW]="_onDownArrow";
map[dk.UP_ARROW]="_onUpArrow";
map[dk.DOWN_ARROW]="_onDownArrow";
map[dk.HOME]="_onHomeKey";
map[dk.END]="_onEndKey";
this._keyHandlerMap=map;
}
if(this._keyHandlerMap[key]){
this[this._keyHandlerMap[key]]({node:e.target});
dojo.stopEvent(e);
}
},_onDownArrow:function(_b70){
if(this._activeRow){
var _b71=this._nextElementSibling(this._activeRow);
if(_b71){
this._selectRow(_b71);
}
}
},_onUpArrow:function(_b72){
if(this._activeRow){
var _b73=this._previousElementSibling(this._activeRow);
if(_b73){
this._selectRow(_b73);
}
}
},_onHomeKey:function(){
if(this._activeRow){
dojo.query(".trace_list_item",this._activeRow.parentNode).slice(0,1).forEach(function(row){
if(row.id!=this._activeRow.id){
this._selectRow(row);
}
},this);
}
},_onEndKey:function(_b74){
if(this._activeRow){
dojo.query(".trace_list_item",this._activeRow.parentNode).slice(-1).forEach(function(row){
if(row.id!=this._activeRow.id){
this._selectRow(row);
}
},this);
}
},_previousElementSibling:function(node){
do{
node=node.previousSibling;
}while(node&&node.nodeType!=1);
return node;
},_nextElementSibling:function(node){
do{
node=node.nextSibling;
}while(node&&node.nodeType!=1);
return node;
}});
}
if(!dojo._hasResource["spring.UrlBuilder"]){
dojo._hasResource["spring.UrlBuilder"]=true;
dojo.provide("spring.UrlBuilder");
dojo.declare("spring.UrlBuilder",null,{template:null,params:null,constructor:function(_b75,_b76){
this.template=_b75;
this.params=_b76;
},append:function(_b77,_b78){
return new spring.UrlBuilder(this.template+_b77,dojo.delegate(this.params,_b78));
},build:function(_b79){
return this._buildUrl(this.template,dojo.delegate(this.params,_b79));
},toString:function(){
return this.build();
},_buildUrl:function(_b7a,_b7b){
var url=_b7a,name=null,_b7c={},re=null,_b7d=null;
if(_b7b){
for(name in _b7b){
re=new RegExp("\\{"+name+"\\}");
_b7d=false;
if(re.test(url)){
_b7d=true;
url=url.replace(re,encodeURIComponent(_b7b[name]),"g");
}
if(!_b7d){
_b7c[name]=_b7b[name];
}
}
for(name in _b7c){
if(_b7c[name]){
url+=url.indexOf("?")===-1?"?":"&";
url+=encodeURIComponent(name);
url+="=";
url+=encodeURIComponent(_b7c[name]);
}
}
}
return url;
}});
}
if(!dojo._hasResource["insight.Insight"]){
dojo._hasResource["insight.Insight"]=true;
dojo.provide("insight.Insight");
dojo.declare("insight.Insight",null,{_basePath:null,_timeRange:null,_switchUri:null,_switchAppUri:null,_browserWarnType:null,_browserSupported:true,_hashChangeSubscription:null,refreshInterval:dojo.isIE?15000:5000,_pulseCount:0,_active:null,constructor:function(_b7e){
this._active={};
this._basePath=this._parseBasePath(_b7e);
this._initTimeRange();
this._initTimeZoneOffset();
this._hashChangeSubscription=dojo.subscribe("/dojo/hashchange",this,this.onHashChange);
this._errorXhrSubscription=dojo.subscribe("error/xhr",this,this.xhrError);
this._errorStoreSubscription=dojo.subscribe("error/store",this,this.storeError);
if(!this.fromQueryString(dojo.hash()).range){
this.updateHash("range",this._timeRange.toString());
}
dojo.connect(dojo.global,"onresize",this,function(e){
this._onresizeEvent=e;
if(!this._onresizeTimeout){
this._onresizeTimeout=setTimeout(dojo.hitch(this,function(){
var e=this._onresizeEvent;
this._onresizeTimeout=this._onresizeEvent=null;
dojo.publish("window/resize",[e]);
}),100);
}
});
},isBrowserSupported:function(){
return this._browserSupported;
},scheduleLogout:function(url,_b7f){
this._logoutUrl=url;
this._logoutDelay=_b7f||1800000;
if(this._logoutTimer){
this.resetLogout();
}else{
dojo.connect(this,"loadTrace",this,this.resetLogout);
dojo.connect(this,"loadResource",this,this.resetLogout);
dojo.connect(this,"loadTaggedWindow",this,this.resetLogout);
dojo.connect(this,"loadWindow",this,this.resetLogout);
dojo.subscribe("Insight.timeRangeChange",this,this.resetLogout);
this._logoutTimer=setTimeout(dojo.hitch(this,function(){
this.logout();
}),this._logoutDelay);
}
},resetLogout:function(){
if(this._logoutTimer){
clearTimeout(this._logoutTimer);
this._logoutTimer=setTimeout(dojo.hitch(this,function(){
this.logout();
}),this._logoutDelay);
}
},logout:function(url){
url=url||this._logoutUrl;
if(url){
dojo.global.location=url;
}
},hashMode:function(mode){
if(mode){
this._hashMode=mode;
}
return this._hashMode;
},currentResource:function(){
return this.fromQueryString(dojo.hash()).resource;
},currentTrace:function(){
return this.fromQueryString(dojo.hash()).trace;
},getResourceTreeFilter:function(){
return dojo.fromJson(this.getLocal("resourceTreeFilter"));
},loadResource:function(_b80){
if(_b80=="insight:"){
_b80="insight:type=Application";
}
if(_b80!=this.currentResource()&&this.hashMode()=="resource"){
this.updateHash("resource",_b80);
}else{
Insight._loadResource(_b80);
}
},_loadResource:function(_b81){
this.delayNextRefresh();
this._active.resource=_b81;
var _b82=new insight.resources.ResourceKey(_b81);
Insight.hideResourceModules(_b82);
Insight.loadResourceDetail("resource",_b82);
this.highlightResourceInTree(_b82);
},highlightResourceInTree:function(_b83){
if(!_b83){
_b83=Insight.currentResource();
}
dojo.publish("Insight.highlightTreeResource",[_b83]);
},hideResourceModules:function(_b84){
if(!_b84.getName()){
this._hideModule("resource");
}else{
this._hideModule("resources");
}
this._hideModule("resources_alt");
this._hideModule("traces-window");
this._hideModule("trace");
},_hideModule:function(id){
var _b85=dijit.byId(id);
if(_b85){
_b85.hide();
_b85.destroyDescendants();
}
},loadResourceDetail:function(id,_b86){
this.destroyWidget(id);
var _b87=this.getTimeRange();
return new insight.components.PageModule({url:Insight.buildUri("/services/resources/{resource}/{start}/{end}",{resource:_b86.toString(),start:_b87.getStart(),end:_b87.getEnd()})},id);
},loadResourceList:function(id,_b88,type){
var _b89=this.getTimeRange();
this.destroyWidget(id);
var list=new insight.components.SortableTable({url:Insight.buildUri("/services/resources",{context:_b88.toString(),resourceType:type,start:_b89.getStart(),end:_b89.getEnd()}),defaultSortSettings:{field:"responseTimeScore",desc:true},resize:true,maxHeight:id=="resources_alt"?300:null},id);
list.subscribe("Insight.timeRangeMajorPulse",function(_b8a){
this.updateUrlParams({start:_b8a.getStart(),end:_b8a.getEnd()});
if(!this.hidden()){
this.load();
}
});
return list;
},loadTraces:function(_b8b){
this.destroyWidget("traces");
return new insight.components.PageModule({url:Insight.buildUri("/services/traces",{application:_b8b})},"traces");
},loadTaggedWindow:function(_b8c,end,_b8d,tag){
var _b8e={resource:_b8d,tag:tag},_b8f=dijit.byId("traces-window"),_b90="/services/resources/{resource}/traces/{start}/{end}/tags/{tag}";
if(_b8c&&end){
_b8e.start=insight.time.toString(insight.time.normalizeToDate(_b8c));
_b8e.end=insight.time.toString(insight.time.normalizeToDate(end));
}else{
_b8e.start=Insight.getTimeRange().getStart();
_b8e.end=Insight.getTimeRange().getEnd();
}
this._hideModule("trace");
if(_b8f){
_b8f.updateUrlTemplate(Insight.buildUri(_b90));
_b8f.updateUrlParams(_b8e);
_b8f.load();
}else{
_b8f=new insight.traces.TraceList({url:Insight.buildUri(_b90,_b8e),maxHeight:150,defaultSortSettings:{field:"range.start",desc:true},resize:true},"traces-window");
_b8f.connect(_b8f,"loadTrace",dojo.hitch(Insight,Insight.loadTrace));
}
},loadWindow:function(_b91,end,_b92,min,max){
var _b93={},_b94,_b95=150,_b96=dijit.byId("traces-window");
if(_b91&&end){
_b93.start=insight.time.toString(insight.time.normalizeToDate(_b91));
_b93.end=insight.time.toString(insight.time.normalizeToDate(end));
}else{
_b93.start=Insight.getTimeRange().getStart();
_b93.end=Insight.getTimeRange().getEnd();
}
if(_b92){
_b93.resource=_b92;
if(min||max){
_b93.min=min;
_b93.max=max;
_b94="/services/resources/{resource}/traces/{start}/{end}/{min}/{max}";
}else{
if(_b92.indexOf("insight:")==0){
_b94="/services/resources/{resource}/traces/{start}/{end}";
}
}
}
if(!_b94){
if(_b92){
_b93.application=_b92;
}
_b94="/services/traces/windows/{start}/{end}";
_b95=185;
}
this._hideModule("trace");
if(_b96){
_b96.updateUrlTemplate(Insight.buildUri(_b94));
_b96.updateUrlParams(_b93);
_b96.load();
}else{
_b96=new insight.traces.TraceList({url:Insight.buildUri(_b94,_b93),maxHeight:_b95,defaultSortSettings:{field:(min&&max)?"range.start":"range.duration",desc:true},resize:true},"traces-window");
_b96.connect(_b96,"loadTrace",dojo.hitch(Insight,Insight.loadTrace));
}
},loadTrace:function(_b97){
Insight._loadTrace(_b97);
},_loadTrace:function(_b98){
this._active.trace=_b98;
this.destroyWidget("trace");
return new insight.components.PageModule({url:Insight.buildUri("/services/traces/{traceId}",{traceId:_b98})},"trace");
},setSwitchApplicationUri:function(uri){
if(dojo.isString(uri)){
uri=this.buildUri(uri);
}
this._switchAppUri=uri;
},setSwitchUri:function(uri){
if(dojo.isString(uri)){
uri=this.buildUri(uri);
}
this._switchUri=uri;
},switchToApplication:function(_b99){
var url;
_b99=this._normalizeApplication(_b99);
if(_b99==null&&this._switchUri){
url=this._switchUri.build();
}else{
if(_b99!=null&&this._switchAppUri){
url=this._switchAppUri.build({application:_b99});
}
}
if(url){
dojo.global.location=url;
}
},_normalizeApplication:function(_b9a){
if(_b9a=="*"){
return null;
}else{
return _b9a;
}
},getOperationFilter:function(){
return this.getCookie("Insight.operationFilter");
},_getOperationFilterArray:function(){
var _b9b=(this.getOperationFilter()||"").split("|");
return dojo.filter(_b9b,function(rule){
return !!rule;
},this);
},_getOperationFilterRegExp:function(rule){
return new RegExp("^[-+]"+dojo.regexp.escapeString(rule)+"$");
},clearOperationFilter:function(){
this.setCookie("Insight.operationFilter",null);
if(dijit.byId("trace")){
dijit.byId("trace").load();
}
},hasOperationFilter:function(rule){
var _b9c=this._getOperationFilterRegExp(rule),_b9d=dojo.filter(this._getOperationFilterArray(),function(r){
return r.match(_b9c);
},this);
return _b9d[0]&&_b9d[0].charAt(0);
},addOperationFilter:function(_b9e,_b9f){
var _ba0=this._getOperationFilterArray(),_ba1=this._getOperationFilterRegExp(_b9e),_ba2=[_b9f+_b9e];
dojo.forEach(_ba0,function(rule){
if(!rule.match(_ba1)){
_ba2.push(rule);
}
},this);
this.setCookie("Insight.operationFilter",_ba2.join("|"));
},removeOperationFilter:function(_ba3){
var _ba4=(this.getOperationFilter()||"").split("|"),_ba5=this._getOperationFilterRegExp(_ba3),_ba6=[];
dojo.forEach(_ba4,function(rule){
if(!rule.match(_ba5)){
_ba6.push(rule);
}
},this);
this.setCookie("Insight.operationFilter",_ba6.join("|"));
},getTimeRange:function(){
return this._timeRange.clone();
},setTimeRange:function(_ba7){
dojo.publish("Insight.timeRangeChange",[_ba7]);
if(!_ba7.isAnchored()){
this.play();
}else{
this.pause();
}
},incrementTimeRange:function(){
if(!this._timeRange.isAnchored()){
return;
}
var _ba8=this._timeRange.getDuration(),_ba9=_ba8/4,now=new Date().getTime();
if(now<this._timeRange.getEndDate().getTime()+_ba9+(_ba8*0.1)){
this._timeRange.shift().setAnchored(false);
}else{
this._timeRange.add(_ba9);
}
this.setTimeRange(this._timeRange);
},decrementTimeRange:function(){
var _baa=this._timeRange.getDuration()*-1/4;
this._timeRange.add(_baa);
this.setTimeRange(this._timeRange);
},shiftTimeRangeToNow:function(){
this.setTimeRange(this._timeRange.shift().setAnchored(false));
},_setTimeRange:function(_bab){
this._timeRange=_bab;
this.setLocal("Insight.timeRange",this._timeRange);
},_initTimeRange:function(){
var _bac=new insight.time.TimeRange(this._defaultTimeRange());
this._setTimeRange(_bac);
dojo.subscribe("Insight.timeRangeChange",this,function(_bad){
this._setTimeRange(_bad.clone());
this._pulseCount=-1;
dojo.publish("Insight.timeRangePulse",[_bad.clone()]);
});
dojo.subscribe("Insight.timeRangePulse",this,function(_bae){
this._pulseCount=(this._pulseCount+1)%6;
if(this._pulseCount===0){
dojo.publish("Insight.timeRangeMajorPulse",[_bae.clone()]);
}
});
if(!_bac.isAnchored()){
dojo.publish("Insight.play",[this._timeRange.clone()]);
this._scheduleRefresh();
}
dojo.subscribe("Insight.timeRangeChange",this,function(_baf){
_baf=_baf.toString();
if(this._active.range!==_baf){
this.updateHash("range",_baf);
}
});
},_defaultTimeRange:function(){
return this.fromQueryString(dojo.hash()).range||this.getLocal("Insight.timeRange")||"900000";
},play:function(_bb0){
if(_bb0&&this._pausedForUser){
this._timeRange.setAnchored(false);
dojo.publish("Insight.play",[this._timeRange.clone()]);
this._pausedForUser=false;
this._scheduleRefresh();
}else{
if(!_bb0&&!this.playing()){
this._timeRange.setAnchored(false);
dojo.publish("Insight.play",[this._timeRange.clone()]);
this.updateHash("range",this._timeRange.toString());
this._scheduleRefresh();
this.refresh();
}
}
},_scheduleRefresh:function(){
clearTimeout(this._playing);
this._playing=setTimeout(dojo.hitch(this,this.refresh),this.refreshInterval);
},pause:function(_bb1){
if(this.playing()){
clearTimeout(this._playing);
this._playing=null;
this._timeRange.setAnchored(true);
dojo.publish("Insight.pause",[this._timeRange.clone()]);
if(_bb1){
this._pausedForUser=true;
}else{
this.updateHash("range",this._timeRange.toString());
}
}
},refresh:function(){
if(this.playing()){
clearTimeout(this._playing);
}
if(!this._timeRange.isAnchored()){
this._timeRange.shift();
}
this._setTimeRange(this._timeRange);
dojo.publish("Insight.timeRangePulse",[this._timeRange.clone()]);
if(this.playing()){
this._scheduleRefresh();
}
},delayNextRefresh:function(){
if(this.playing()){
this._scheduleRefresh();
}
},playing:function(){
return this._playing!=null;
},xhrError:function(_bb2,_bb3,uid){
if(_bb2=="Error: timeout exceeded"){
dojo.publish("insight/error",["Server took too long to respond: "+_bb3.url,"[TIMEOUT:"+_bb3.args.timeout+"]",uid]);
}else{
if(_bb3.xhr.status===0){
dojo.publish("insight/error",["Unable to connect to server, the server or network may be down: "+_bb3.url,"[NETWORK]",uid]);
}else{
dojo.publish("insight/error",["Server encountered an error: "+_bb3.url,"[HTTP:"+_bb3.xhr.status+"]",uid]);
}
}
},storeError:function(_bb4,_bb5,uid){
dojo.publish("insight/error",[_bb4,"[STORE:"+_bb5+"]",uid,true]);
},getLocal:function(name){
var _bb6=this.getSession(name);
if(_bb6===null){
_bb6=this.getCookie(name);
}
return _bb6;
},getSession:function(name){
if(dojo.global.sessionStorage){
return dojo.global.sessionStorage.getItem(name);
}else{
return null;
}
},getCookie:function(name){
if(dojo.cookie.isSupported()){
return dojo.cookie(name);
}else{
return null;
}
},setLocal:function(name,_bb7,_bb8){
this.setSession(name,_bb7);
if(_bb8||this.getSession(name)===null){
this.setCookie(name,_bb7);
}
},setSession:function(name,_bb9){
if(dojo.global.sessionStorage){
if(_bb9===null){
dojo.global.sessionStorage.removeItem(name);
}else{
dojo.global.sessionStorage.setItem(name,_bb9);
}
}
},setCookie:function(name,_bba,_bbb){
if(dojo.cookie.isSupported()){
if(!_bba){
_bbb=-1;
}
var _bbc={path:this._basePath.template};
if(_bbb!==undefined){
_bbc.expires=_bbb;
}
dojo.cookie(name,_bba,_bbc);
}
},buildUri:function(uri,_bbd){
return this._basePath.append(uri,_bbd);
},fromQueryString:function(str){
var obj={};
if(!str){
return obj;
}
dojo.forEach(str.split("&"),function(item){
var pos=item.indexOf("=");
if(pos!=-1){
obj[decodeURIComponent(item.substring(0,pos))]=decodeURIComponent(item.substring(pos+1));
}
},this);
return obj;
},toQueryString:function(obj){
var str="";
if(!obj){
return str;
}
for(var i in obj){
if(obj.hasOwnProperty(i)){
if(str!=""){
str+="&";
}
str+=encodeURIComponent(i);
str+="=";
str+=encodeURIComponent(obj[i]);
}
}
return str;
},updateHash:function(name,_bbe){
var obj=this.fromQueryString(dojo.hash());
if(_bbe===null){
delete obj[name];
}else{
obj[name]=_bbe;
}
dojo.hash(this.toQueryString(obj));
},onHashChange:function(){
var obj=this.fromQueryString(dojo.hash()),mode=this.hashMode();
if(mode=="trace"&&obj.trace&&obj.trace!==this._active.trace){
this._loadTrace(obj.trace);
}else{
if(mode=="resource"&&obj.resource&&obj.resource!==this._active.resource){
this._loadResource(obj.resource);
}
}
if(obj.range&&obj.range!==this.getTimeRange().toString()){
this.setTimeRange(new insight.time.TimeRange(obj.range));
}
},_initTimeZoneOffset:function(){
var _bbf=new Date().getTimezoneOffset()/60*-1;
this.setCookie("Insight.timeZoneOffset",_bbf);
},destroyWidget:function(id){
var _bc0=dijit.byId(id);
if(_bc0){
_bc0.set("style",{display:"none"});
_bc0.destroyRecursive(true);
}
},_parseBasePath:function(_bc1){
if(_bc1.match(";jsessionid")){
_bc1=_bc1.substring(0,_bc1.indexOf(";jsessionid"));
}
if(_bc1.match("/$")){
_bc1=_bc1.substring(0,_bc1.length-1);
}
return new spring.UrlBuilder(_bc1);
},_browserWarnings:[{name:"cookies",func:function(_bc2){
return !dojo.cookie.isSupported();
},title:null,message:"Cookie support is required to use Spring Insight.  Please enable cookies or upgrade your browser.",supported:true,suppressible:false,upgradePanel:true,suppressionTimeout:null},{name:"ie6",func:function(_bc3){
return dojo.isIE<=6;
},title:null,message:"IE 6 (and eariler) is incompatible with Spring Insight.  Please upgrade your browser.<br /><br /><a href=\"http://www.google.com/chromeframe\">Google Chrome Frame</a> is strongly recommended if upgrading your browser is not otherwise possible.",supported:false,suppressible:false,upgradePanel:true,suppressionTimeout:null},{name:"ie",func:function(_bc4){
return dojo.isIE<9&&!_bc4;
},title:null,message:"IE 7 and 8 will work with Spring Insight.  However, the performance is to be blunt, slow.<br /><br /><a href=\"http://www.google.com/chromeframe\">Google Chrome Frame</a> is strongly recommended if upgrading your browser is not otherwise possible.",supported:true,suppressible:true,upgradePanel:true,suppressionTimeout:90},{name:"oldFirefox",func:function(_bc5){
return dojo.isFF&&dojo.isFF<3.6&&!_bc5;
},title:null,message:"Firefox is a great browser.  However, your using an older version.  The latest release is significantly faster.",supported:true,suppressible:true,upgradePanel:true,suppressionTimeout:30},{name:"oldSafari",func:function(_bc6){
return dojo.isSafari&&dojo.isSafari<5&&!_bc6;
},title:null,message:"Safari is a great browser.  However, your using an older version.  The latest release is significantly faster.",supported:true,suppressible:true,upgradePanel:true,suppressionTimeout:30},{name:"oldChrome",func:function(_bc7){
return dojo.isChrome&&dojo.isChrome<5&&!_bc7;
},title:null,message:"Chrome is a great browser.  However, your using an older version.  The latest release is significantly faster.",supported:true,suppressible:true,upgradePanel:true,suppressionTimeout:30},{name:"unknowBroswer",func:function(_bc8){
return !dojo.isFF&&!dojo.isSafari&&!dojo.isChrome&&!dojo.isIE&&!_bc8;
},title:null,message:"You're using a browser we havn't tested.  It should work, but if you run into strange issues, try a recomended browser.  Be sure to let us know about your experience.",supported:true,suppressible:true,upgradePanel:true,suppressionTimeout:30},{name:"firebug",func:function(_bc9){
return console&&console.firebug;
},title:null,message:"Firebug is known to cause severe performance degradation over time.  We highly recommend you disable Firebug for the Spring Insight dashboard.",supported:true,suppressible:true,upgradePanel:false,suppressionTimeout:null},{name:"chrome-speedtracer",func:function(_bca){
return dojo.isChrome&&!window.ChromeFrame;
},title:"Spring Insight + Google SpeedTracer",message:function(){
return "<a href='http://code.google.com/webtoolkit/speedtracer/' target='_blank'><img src='"+this.buildUri("/static/images/browser_logo_speedtracer.png")+"' style='float: left; padding: 0 0.5em;' /></a><p><a href='http://code.google.com/webtoolkit/speedtracer/' target='_blank'>SpeedTracer</a> is a Google Chrome extension that analyzes how your application performs inside the browser. SpringSource and Google partnered to integrate Spring Insight data into SpeedTracer, thereby creating the ultimate client-server application performance tool.</p><p>If your web application uses Ajax or other rich open web technologies, we recommend you <a href='http://code.google.com/webtoolkit/speedtracer/get-started.html' target='_blank'>try SpeedTracer</a>.</p>";
},supported:true,suppressible:true,upgradePanel:false,suppressionTimeout:90}],checkBrowserAbilities:function(){
var _bcb=this.getCookie("com.springsource.sts.run.embedded"),_bcc=false,_bcd;
for(var i=0;!_bcc&&i<this._browserWarnings.length;i++){
_bcd=this._browserWarnings[i];
if(_bcd.func.call(this,_bcb)){
if(!_bcd.supported){
this._browserSupported=false;
}
if(!_bcd.suppressible||(_bcd.suppressible&&!this.getCookie("Insight.browserwarning."+_bcd.name))){
this._browserWarning=_bcd;
this._warnBrowserAbility(_bcd.name,_bcd.title,dojo.isFunction(_bcd.message)?_bcd.message.call(this):_bcd.message,_bcd.suppressible,_bcd.upgradePanel);
_bcc=true;
}
}
}
},_warnBrowserAbility:function(type,_bce,_bcf,_bd0,_bd1){
var _bd2=dojo.byId("noscript");
if(!_bd2){
return;
}
if(_bce){
dojo.byId("noscript-title").innerHTML=_bce;
}
dojo.byId("noscript-warning").innerHTML=_bcf;
if(_bd0){
if(_bd0!==true){
dojo.byId("noscript-ignore").innerHTML=_bd0;
}
dojo.style("noscript-ignore","display","block");
}
if(!_bd1){
dojo.style("noscript-upgrade","display","none");
}
dojo.style(_bd2,"display","block");
},suppressWarning:function(){
this.setCookie("Insight.browserwarning."+this._browserWarning.name,"ignore",this._browserWarning.suppressionTimeout);
dojo.style("noscript","display","none");
}});
dojo.extend(dojox.charting.plot2d._PlotEvents,{_reconnectEvents:function(_bd3){
var a=this._eventSeries[_bd3];
if(a){
dojo.forEach(a,function(o){
if(o){
this._connectEvents(o);
}
},this);
}
}});
}
if(!dojo._hasResource["insight.resources.InsightResourceReadStore"]){
dojo._hasResource["insight.resources.InsightResourceReadStore"]=true;
dojo.provide("insight.resources.InsightResourceReadStore");
dojo.declare("insight.resources.InsightResourceReadStore",null,{url:null,_items:null,timeout:insight.runtime.getXhrTimeout("InsightResourceReadStore"),_resourceMappings:{"Server":null,"EndPoint":null,"Application":"Application.EndPoint","Application.Server":"Application.Server.EndPoint","Application.EndPoint":null,"Application.Server.EndPoint":null},constructor:function(args){
dojo.mixin(this,args);
this._items={};
if(args.root){
delete this.root;
this._items[args.root.resourceKey]={resourceKey:args.root.resourceKey,resourceLabel:args.root.resourceLabel,children:true,store:this};
}
},getItems:function(){
var _bd4=[],i;
for(i in this._items){
if(this._items.hasOwnProperty(i)){
_bd4.push(this._items[i]);
}
}
return _bd4;
},refresh:function(){
dojox.lang.functional.forIn(this._items,function(item,key){
if(dojo.isArray(item.children)){
this.loadItem({item:item});
}
},this);
},getFeatures:function(){
return {"dojo.data.api.Read":true,"dojo.data.api.Identity":true,"dojo.data.api.Notification":true};
},getValue:function(item,_bd5,_bd6){
if(!this.hasAttribute(item,_bd5)){
return _bd6;
}
var _bd7=item[_bd5];
if(dojo.isArray(_bd7)){
_bd7=_bd7[0];
}
if(!_bd7){
_bd7=_bd6;
}
return _bd7;
},getValues:function(item,_bd8){
var _bd9=item[_bd8];
if(dojo.isArray(_bd9)){
return _bd9;
}else{
if(dojo.isObject(_bd9)&&_bd9!==null){
return [_bd9];
}else{
return [];
}
}
},getAttributes:function(item){
var _bda=[];
for(name in item){
if(this.hasAttribute(item,name)){
_bda.push(name);
}
}
return _bda;
},hasAttribute:function(item,_bdb){
if(!this.isItem(item)){
throw "An item is required";
}
return item.hasOwnProperty(_bdb)&&_bdb!="store"&&_bdb!="parent";
},containsValue:function(item,_bdc,_bdd){
var _bde=false;
dojo.forEach(this.getValues(item,_bdc),function(_bdf){
if(_bdf===_bdd){
_bde=true;
}
},this);
return _bde;
},isItem:function(_be0){
return _be0&&_be0.store===this;
},isItemLoaded:function(_be1){
if(!this.isItem(_be1)){
return false;
}
if(!_be1.hasOwnProperty("children")){
return true;
}
return dojo.isArray(_be1.children);
},loadItem:function(_be2){
var _be3=new insight.resources.ResourceKey(this.getIdentity(_be2.item));
var type=_be3.getAttribute("type");
var name=_be3.getAttribute("name");
if(name&&this._resourceMappings[type]){
_be3.setAttribute(type,name);
_be3.setAttribute("type",this._resourceMappings[type]);
_be3.setAttribute("name",null);
}
_be2.query=_be3.toString();
this.fetch(_be2);
},fetch:function(_be4){
var _be5=_be4||{};
if(!_be5.query){
return _be5;
}
if(_be5.item&&_be5.item.doNotLoad){
return _be5;
}
var xhr=dojo.xhrGet({handleAs:"json",url:this.url.build({resourceKey:_be5.query}),content:_be5.queryOptions,request:_be5,load:dojo.hitch(this,"_fetchLoad"),error:dojo.hitch(this,"_fetchError"),timeout:this.timeout});
_be5.abort=dojo.hitch(xhr,"cancel");
return _be5;
},_fetchLoad:function(_be6,_be7){
var _be8=_be7.args.request,_be9=false,_bea=_be8.scope||dojo.global,_beb=[];
if(!_be8.store){
_be8.store=this;
}
if(_be8.onBegin){
_be8.onBegin.call(_bea,_beb.length,_be8);
}
if(_be8.sort&&dojo.isArray(_be6.resources)){
_be6.resources.sort(dojo.data.util.sorter.createSortFunction(_be8.sort,this));
}
var _bec=dojo.hitch(this,function(item){
var id=item[this.getIdentityAttributes()[0]];
var _bed=this._items[id];
var _bee=new insight.resources.ResourceKey(id);
item[this.getIdentityAttributes(item)[0]]=_bee.toString();
if(this._resourceMappings[_bee.getAttribute("type")]&&!item.doNotLoad){
item.children=true;
}
item.store=this;
if(_bed){
var _bef=dojo.mixin({},_bed);
_bed.invocationCount=item.invocationCount;
_bed.health=item.health;
_bed.healthLamp=item.healthLamp;
this.onSet(_bed);
}else{
if(_be8.item){
if(!dojo.isArray(_be8.item.children)){
_be8.item.children=[];
}
_be8.item.children.push(item);
item.parent=_be8.item;
}
this.onNew(item,{item:item.parent});
this._items[id]=item;
_beb.push(item);
}
if(_be8.onItem&&!_be9){
_be8.onItem.call(_bea,item.parent,_be8);
}
});
if(_be6.resource){
_bec(_be6.resource);
}else{
if(_be6.resources&&_be6.resources.length>0){
for(var i in _be6.resources){
_bec(_be6.resources[i]);
}
}else{
_bec({resourceKey:_be8.item.resourceKey+",children=notfound",resourceLabel:"no matching resources",health:{rating:"NOSAMPLE"},doNotLoad:true});
}
}
if(_be8.onComplete&&!_be9){
var _bf0=null;
if(!_be8.onItem){
_bf0=_beb;
}
_be8.onComplete.call(_bea,_bf0,_be8);
}
},_fetchError:function(_bf1,_bf2){
dojo.publish("error/xhr",arguments);
},close:function(_bf3){
console.error("Unimplemented API: dojo.data.api.Read.close",arguments);
throw new Error("Unimplemented API: dojo.data.api.Read.close");
},getLabel:function(item){
return this.getValue(item,this.getLabelAttributes(item)[0]);
},getLabelAttributes:function(item){
return ["resourceLabel"];
},getIdentity:function(item){
if(!this.isItem(item)){
return null;
}
var _bf4=this.getValue(item,this.getIdentityAttributes(item)[0]);
return _bf4;
},getIdentityAttributes:function(item){
return ["resourceKey"];
},fetchItemByIdentity:function(_bf5){
if(this._items[_bf5.identity]&&_bf5.onItem){
_bf5.onItem.call(_bf5.scope?_bf5.scope:dojo.global,this._items[_bf5.identity]);
return;
}
_bf5.query=_bf5.identity;
this.fetch(_bf5);
},onSet:function(item,_bf6,_bf7,_bf8){
},onNew:function(_bf9,_bfa){
},onDelete:function(_bfb){
}});
}
if(!dojo._hasResource["dojo.DeferredList"]){
dojo._hasResource["dojo.DeferredList"]=true;
dojo.provide("dojo.DeferredList");
dojo.DeferredList=function(list,_bfc,_bfd,_bfe,_bff){
var _c00=[];
dojo.Deferred.call(this);
var self=this;
if(list.length===0&&!_bfc){
this.resolve([0,[]]);
}
var _c01=0;
dojo.forEach(list,function(item,i){
item.then(function(_c02){
if(_bfc){
self.resolve([i,_c02]);
}else{
_c03(true,_c02);
}
},function(_c04){
if(_bfd){
self.reject(_c04);
}else{
_c03(false,_c04);
}
if(_bfe){
return null;
}
throw _c04;
});
function _c03(_c05,_c06){
_c00[i]=[_c05,_c06];
_c01++;
if(_c01===list.length){
self.resolve(_c00);
}
};
});
};
dojo.DeferredList.prototype=new dojo.Deferred();
dojo.DeferredList.prototype.gatherResults=function(_c07){
var d=new dojo.DeferredList(_c07,false,true,false);
d.addCallback(function(_c08){
var ret=[];
dojo.forEach(_c08,function(_c09){
ret.push(_c09[1]);
});
return ret;
});
return d;
};
}
if(!dojo._hasResource["dijit.tree.ForestStoreModel"]){
dojo._hasResource["dijit.tree.ForestStoreModel"]=true;
dojo.provide("dijit.tree.ForestStoreModel");
dojo.declare("dijit.tree.ForestStoreModel",dijit.tree.TreeStoreModel,{rootId:"$root$",rootLabel:"ROOT",query:null,constructor:function(_c0a){
this.root={store:this,root:true,id:_c0a.rootId,label:_c0a.rootLabel,children:_c0a.rootChildren};
},mayHaveChildren:function(item){
return item===this.root||this.inherited(arguments);
},getChildren:function(_c0b,_c0c,_c0d){
if(_c0b===this.root){
if(this.root.children){
_c0c(this.root.children);
}else{
this.store.fetch({query:this.query,onComplete:dojo.hitch(this,function(_c0e){
this.root.children=_c0e;
_c0c(_c0e);
}),onError:_c0d});
}
}else{
this.inherited(arguments);
}
},isItem:function(_c0f){
return (_c0f===this.root)?true:this.inherited(arguments);
},fetchItemByIdentity:function(_c10){
if(_c10.identity==this.root.id){
var _c11=_c10.scope?_c10.scope:dojo.global;
if(_c10.onItem){
_c10.onItem.call(_c11,this.root);
}
}else{
this.inherited(arguments);
}
},getIdentity:function(item){
return (item===this.root)?this.root.id:this.inherited(arguments);
},getLabel:function(item){
return (item===this.root)?this.root.label:this.inherited(arguments);
},newItem:function(args,_c12,_c13){
if(_c12===this.root){
this.onNewRootItem(args);
return this.store.newItem(args);
}else{
return this.inherited(arguments);
}
},onNewRootItem:function(args){
},pasteItem:function(_c14,_c15,_c16,_c17,_c18){
if(_c15===this.root){
if(!_c17){
this.onLeaveRoot(_c14);
}
}
dijit.tree.TreeStoreModel.prototype.pasteItem.call(this,_c14,_c15===this.root?null:_c15,_c16===this.root?null:_c16,_c17,_c18);
if(_c16===this.root){
this.onAddToRoot(_c14);
}
},onAddToRoot:function(item){
},onLeaveRoot:function(item){
},_requeryTop:function(){
var _c19=this.root.children||[];
this.store.fetch({query:this.query,onComplete:dojo.hitch(this,function(_c1a){
this.root.children=_c1a;
if(_c19.length!=_c1a.length||dojo.some(_c19,function(item,idx){
return _c1a[idx]!=item;
})){
this.onChildrenChange(this.root,_c1a);
}
})});
},onNewItem:function(item,_c1b){
this._requeryTop();
this.inherited(arguments);
},onDeleteItem:function(item){
if(dojo.indexOf(this.root.children,item)!=-1){
this._requeryTop();
}
this.inherited(arguments);
}});
}
if(!dojo._hasResource["dijit.Tree"]){
dojo._hasResource["dijit.Tree"]=true;
dojo.provide("dijit.Tree");
dojo.declare("dijit._TreeNode",[dijit._Widget,dijit._Templated,dijit._Container,dijit._Contained,dijit._CssStateMixin],{item:null,isTreeNode:true,label:"",isExpandable:null,isExpanded:false,state:"UNCHECKED",templateString:dojo.cache("dijit","templates/TreeNode.html","<div class=\"dijitTreeNode\" waiRole=\"presentation\"\n\t><div dojoAttachPoint=\"rowNode\" class=\"dijitTreeRow\" waiRole=\"presentation\" dojoAttachEvent=\"onmouseenter:_onMouseEnter, onmouseleave:_onMouseLeave, onclick:_onClick, ondblclick:_onDblClick\"\n\t\t><img src=\"${_blankGif}\" alt=\"\" dojoAttachPoint=\"expandoNode\" class=\"dijitTreeExpando\" waiRole=\"presentation\"\n\t\t/><span dojoAttachPoint=\"expandoNodeText\" class=\"dijitExpandoText\" waiRole=\"presentation\"\n\t\t></span\n\t\t><span dojoAttachPoint=\"contentNode\"\n\t\t\tclass=\"dijitTreeContent\" waiRole=\"presentation\">\n\t\t\t<img src=\"${_blankGif}\" alt=\"\" dojoAttachPoint=\"iconNode\" class=\"dijitIcon dijitTreeIcon\" waiRole=\"presentation\"\n\t\t\t/><span dojoAttachPoint=\"labelNode\" class=\"dijitTreeLabel\" wairole=\"treeitem\" tabindex=\"-1\" waiState=\"selected-false\" dojoAttachEvent=\"onfocus:_onLabelFocus\"></span>\n\t\t</span\n\t></div>\n\t<div dojoAttachPoint=\"containerNode\" class=\"dijitTreeContainer\" waiRole=\"presentation\" style=\"display: none;\"></div>\n</div>\n"),baseClass:"dijitTreeNode",cssStateNodes:{rowNode:"dijitTreeRow",labelNode:"dijitTreeLabel"},attributeMap:dojo.delegate(dijit._Widget.prototype.attributeMap,{label:{node:"labelNode",type:"innerText"},tooltip:{node:"rowNode",type:"attribute",attribute:"title"}}),postCreate:function(){
this.inherited(arguments);
this._setExpando();
this._updateItemClasses(this.item);
if(this.isExpandable){
dijit.setWaiState(this.labelNode,"expanded",this.isExpanded);
}
},_setIndentAttr:function(_c1c){
this.indent=_c1c;
var _c1d=(Math.max(_c1c,0)*this.tree._nodePixelIndent)+"px";
dojo.style(this.domNode,"backgroundPosition",_c1d+" 0px");
dojo.style(this.rowNode,this.isLeftToRight()?"paddingLeft":"paddingRight",_c1d);
dojo.forEach(this.getChildren(),function(_c1e){
_c1e.set("indent",_c1c+1);
});
},markProcessing:function(){
this.state="LOADING";
this._setExpando(true);
},unmarkProcessing:function(){
this._setExpando(false);
},_updateItemClasses:function(item){
var tree=this.tree,_c1f=tree.model;
if(tree._v10Compat&&item===_c1f.root){
item=null;
}
this._applyClassAndStyle(item,"icon","Icon");
this._applyClassAndStyle(item,"label","Label");
this._applyClassAndStyle(item,"row","Row");
},_applyClassAndStyle:function(item,_c20,_c21){
var _c22="_"+_c20+"Class";
var _c23=_c20+"Node";
if(this[_c22]){
dojo.removeClass(this[_c23],this[_c22]);
}
this[_c22]=this.tree["get"+_c21+"Class"](item,this.isExpanded);
if(this[_c22]){
dojo.addClass(this[_c23],this[_c22]);
}
dojo.style(this[_c23],this.tree["get"+_c21+"Style"](item,this.isExpanded)||{});
},_updateLayout:function(){
var _c24=this.getParent();
if(!_c24||_c24.rowNode.style.display=="none"){
dojo.addClass(this.domNode,"dijitTreeIsRoot");
}else{
dojo.toggleClass(this.domNode,"dijitTreeIsLast",!this.getNextSibling());
}
},_setExpando:function(_c25){
var _c26=["dijitTreeExpandoLoading","dijitTreeExpandoOpened","dijitTreeExpandoClosed","dijitTreeExpandoLeaf"],_c27=["*","-","+","*"],idx=_c25?0:(this.isExpandable?(this.isExpanded?1:2):3);
dojo.removeClass(this.expandoNode,_c26);
dojo.addClass(this.expandoNode,_c26[idx]);
this.expandoNodeText.innerHTML=_c27[idx];
},expand:function(){
if(this._expandDeferred){
return this._expandDeferred;
}
this._wipeOut&&this._wipeOut.stop();
this.isExpanded=true;
dijit.setWaiState(this.labelNode,"expanded","true");
dijit.setWaiRole(this.containerNode,"group");
dojo.addClass(this.contentNode,"dijitTreeContentExpanded");
this._setExpando();
this._updateItemClasses(this.item);
if(this==this.tree.rootNode){
dijit.setWaiState(this.tree.domNode,"expanded","true");
}
var def,_c28=dojo.fx.wipeIn({node:this.containerNode,duration:dijit.defaultDuration,onEnd:function(){
def.callback(true);
}});
def=(this._expandDeferred=new dojo.Deferred(function(){
_c28.stop();
}));
_c28.play();
return def;
},collapse:function(){
if(!this.isExpanded){
return;
}
if(this._expandDeferred){
this._expandDeferred.cancel();
delete this._expandDeferred;
}
this.isExpanded=false;
dijit.setWaiState(this.labelNode,"expanded","false");
if(this==this.tree.rootNode){
dijit.setWaiState(this.tree.domNode,"expanded","false");
}
dojo.removeClass(this.contentNode,"dijitTreeContentExpanded");
this._setExpando();
this._updateItemClasses(this.item);
if(!this._wipeOut){
this._wipeOut=dojo.fx.wipeOut({node:this.containerNode,duration:dijit.defaultDuration});
}
this._wipeOut.play();
},indent:0,setChildItems:function(_c29){
var tree=this.tree,_c2a=tree.model,defs=[];
dojo.forEach(this.getChildren(),function(_c2b){
dijit._Container.prototype.removeChild.call(this,_c2b);
},this);
this.state="LOADED";
if(_c29&&_c29.length>0){
this.isExpandable=true;
dojo.forEach(_c29,function(item){
var id=_c2a.getIdentity(item),_c2c=tree._itemNodesMap[id],node;
if(_c2c){
for(var i=0;i<_c2c.length;i++){
if(_c2c[i]&&!_c2c[i].getParent()){
node=_c2c[i];
node.set("indent",this.indent+1);
break;
}
}
}
if(!node){
node=this.tree._createTreeNode({item:item,tree:tree,isExpandable:_c2a.mayHaveChildren(item),label:tree.getLabel(item),tooltip:tree.getTooltip(item),dir:tree.dir,lang:tree.lang,indent:this.indent+1});
if(_c2c){
_c2c.push(node);
}else{
tree._itemNodesMap[id]=[node];
}
}
this.addChild(node);
if(this.tree.autoExpand||this.tree._state(item)){
defs.push(tree._expandNode(node));
}
},this);
dojo.forEach(this.getChildren(),function(_c2d,idx){
_c2d._updateLayout();
});
}else{
this.isExpandable=false;
}
if(this._setExpando){
this._setExpando(false);
}
this._updateItemClasses(this.item);
if(this==tree.rootNode){
var fc=this.tree.showRoot?this:this.getChildren()[0];
if(fc){
fc.setFocusable(true);
tree.lastFocused=fc;
}else{
tree.domNode.setAttribute("tabIndex","0");
}
}
return new dojo.DeferredList(defs);
},removeChild:function(node){
this.inherited(arguments);
var _c2e=this.getChildren();
if(_c2e.length==0){
this.isExpandable=false;
this.collapse();
}
dojo.forEach(_c2e,function(_c2f){
_c2f._updateLayout();
});
},makeExpandable:function(){
this.isExpandable=true;
this._setExpando(false);
},_onLabelFocus:function(evt){
this.tree._onNodeFocus(this);
},setSelected:function(_c30){
dijit.setWaiState(this.labelNode,"selected",_c30);
dojo.toggleClass(this.rowNode,"dijitTreeRowSelected",_c30);
},setFocusable:function(_c31){
this.labelNode.setAttribute("tabIndex",_c31?"0":"-1");
},_onClick:function(evt){
this.tree._onClick(this,evt);
},_onDblClick:function(evt){
this.tree._onDblClick(this,evt);
},_onMouseEnter:function(evt){
this.tree._onNodeMouseEnter(this,evt);
},_onMouseLeave:function(evt){
this.tree._onNodeMouseLeave(this,evt);
}});
dojo.declare("dijit.Tree",[dijit._Widget,dijit._Templated],{store:null,model:null,query:null,label:"",showRoot:true,childrenAttr:["children"],path:[],selectedItem:null,openOnClick:false,openOnDblClick:false,templateString:dojo.cache("dijit","templates/Tree.html","<div class=\"dijitTree dijitTreeContainer\" waiRole=\"tree\"\n\tdojoAttachEvent=\"onkeypress:_onKeyPress\">\n\t<div class=\"dijitInline dijitTreeIndent\" style=\"position: absolute; top: -9999px\" dojoAttachPoint=\"indentDetector\"></div>\n</div>\n"),persist:true,autoExpand:false,dndController:null,dndParams:["onDndDrop","itemCreator","onDndCancel","checkAcceptance","checkItemAcceptance","dragThreshold","betweenThreshold"],onDndDrop:null,itemCreator:null,onDndCancel:null,checkAcceptance:null,checkItemAcceptance:null,dragThreshold:5,betweenThreshold:0,_nodePixelIndent:19,_publish:function(_c32,_c33){
dojo.publish(this.id,[dojo.mixin({tree:this,event:_c32},_c33||{})]);
},postMixInProperties:function(){
this.tree=this;
if(this.autoExpand){
this.persist=false;
}
this._itemNodesMap={};
if(!this.cookieName){
this.cookieName=this.id+"SaveStateCookie";
}
this._loadDeferred=new dojo.Deferred();
this.inherited(arguments);
},postCreate:function(){
this._initState();
if(!this.model){
this._store2model();
}
this.connect(this.model,"onChange","_onItemChange");
this.connect(this.model,"onChildrenChange","_onItemChildrenChange");
this.connect(this.model,"onDelete","_onItemDelete");
this._load();
this.inherited(arguments);
if(this.dndController){
if(dojo.isString(this.dndController)){
this.dndController=dojo.getObject(this.dndController);
}
var _c34={};
for(var i=0;i<this.dndParams.length;i++){
if(this[this.dndParams[i]]){
_c34[this.dndParams[i]]=this[this.dndParams[i]];
}
}
this.dndController=new this.dndController(this,_c34);
}
},_store2model:function(){
this._v10Compat=true;
dojo.deprecated("Tree: from version 2.0, should specify a model object rather than a store/query");
var _c35={id:this.id+"_ForestStoreModel",store:this.store,query:this.query,childrenAttrs:this.childrenAttr};
if(this.params.mayHaveChildren){
_c35.mayHaveChildren=dojo.hitch(this,"mayHaveChildren");
}
if(this.params.getItemChildren){
_c35.getChildren=dojo.hitch(this,function(item,_c36,_c37){
this.getItemChildren((this._v10Compat&&item===this.model.root)?null:item,_c36,_c37);
});
}
this.model=new dijit.tree.ForestStoreModel(_c35);
this.showRoot=Boolean(this.label);
},onLoad:function(){
},_load:function(){
this.model.getRoot(dojo.hitch(this,function(item){
var rn=(this.rootNode=this.tree._createTreeNode({item:item,tree:this,isExpandable:true,label:this.label||this.getLabel(item),indent:this.showRoot?0:-1}));
if(!this.showRoot){
rn.rowNode.style.display="none";
}
this.domNode.appendChild(rn.domNode);
var _c38=this.model.getIdentity(item);
if(this._itemNodesMap[_c38]){
this._itemNodesMap[_c38].push(rn);
}else{
this._itemNodesMap[_c38]=[rn];
}
rn._updateLayout();
this._expandNode(rn).addCallback(dojo.hitch(this,function(){
this._loadDeferred.callback(true);
this.onLoad();
}));
}),function(err){
console.error(this,": error loading root: ",err);
});
},getNodesByItem:function(item){
if(!item){
return [];
}
var _c39=dojo.isString(item)?item:this.model.getIdentity(item);
return [].concat(this._itemNodesMap[_c39]);
},_setSelectedItemAttr:function(item){
var _c3a=this.get("selectedItem");
var _c3b=(!item||dojo.isString(item))?item:this.model.getIdentity(item);
if(_c3b==_c3a?this.model.getIdentity(_c3a):null){
return;
}
var _c3c=this._itemNodesMap[_c3b];
this._selectNode((_c3c&&_c3c[0])||null);
},_getSelectedItemAttr:function(){
return this.selectedNode&&this.selectedNode.item;
},_setPathAttr:function(path){
var d=new dojo.Deferred();
this._selectNode(null);
if(!path||!path.length){
d.resolve(true);
return d;
}
this._loadDeferred.addCallback(dojo.hitch(this,function(){
if(!this.rootNode){
d.reject(new Error("!this.rootNode"));
return;
}
if(path[0]!==this.rootNode.item&&(dojo.isString(path[0])&&path[0]!=this.model.getIdentity(this.rootNode.item))){
d.reject(new Error(this.id+":path[0] doesn't match this.rootNode.item.  Maybe you are using the wrong tree."));
return;
}
path.shift();
var node=this.rootNode;
function _c3d(){
var item=path.shift(),_c3e=dojo.isString(item)?item:this.model.getIdentity(item);
dojo.some(this._itemNodesMap[_c3e],function(n){
if(n.getParent()==node){
node=n;
return true;
}
return false;
});
if(path.length){
this._expandNode(node).addCallback(dojo.hitch(this,_c3d));
}else{
this._selectNode(node);
d.resolve(true);
}
};
this._expandNode(node).addCallback(dojo.hitch(this,_c3d));
}));
return d;
},_getPathAttr:function(){
if(!this.selectedNode){
return;
}
var res=[];
var _c3f=this.selectedNode;
while(_c3f&&_c3f!==this.rootNode){
res.unshift(_c3f.item);
_c3f=_c3f.getParent();
}
res.unshift(this.rootNode.item);
return res;
},mayHaveChildren:function(item){
},getItemChildren:function(_c40,_c41){
},getLabel:function(item){
return this.model.getLabel(item);
},getIconClass:function(item,_c42){
return (!item||this.model.mayHaveChildren(item))?(_c42?"dijitFolderOpened":"dijitFolderClosed"):"dijitLeaf";
},getLabelClass:function(item,_c43){
},getRowClass:function(item,_c44){
},getIconStyle:function(item,_c45){
},getLabelStyle:function(item,_c46){
},getRowStyle:function(item,_c47){
},getTooltip:function(item){
return "";
},_onKeyPress:function(e){
if(e.altKey){
return;
}
var dk=dojo.keys;
var _c48=dijit.getEnclosingWidget(e.target);
if(!_c48){
return;
}
var key=e.charOrCode;
if(typeof key=="string"){
if(!e.altKey&&!e.ctrlKey&&!e.shiftKey&&!e.metaKey){
this._onLetterKeyNav({node:_c48,key:key.toLowerCase()});
dojo.stopEvent(e);
}
}else{
if(this._curSearch){
clearTimeout(this._curSearch.timer);
delete this._curSearch;
}
var map=this._keyHandlerMap;
if(!map){
map={};
map[dk.ENTER]="_onEnterKey";
map[this.isLeftToRight()?dk.LEFT_ARROW:dk.RIGHT_ARROW]="_onLeftArrow";
map[this.isLeftToRight()?dk.RIGHT_ARROW:dk.LEFT_ARROW]="_onRightArrow";
map[dk.UP_ARROW]="_onUpArrow";
map[dk.DOWN_ARROW]="_onDownArrow";
map[dk.HOME]="_onHomeKey";
map[dk.END]="_onEndKey";
this._keyHandlerMap=map;
}
if(this._keyHandlerMap[key]){
this[this._keyHandlerMap[key]]({node:_c48,item:_c48.item,evt:e});
dojo.stopEvent(e);
}
}
},_onEnterKey:function(_c49,evt){
this._publish("execute",{item:_c49.item,node:_c49.node});
this._selectNode(_c49.node);
this.onClick(_c49.item,_c49.node,evt);
},_onDownArrow:function(_c4a){
var node=this._getNextNode(_c4a.node);
if(node&&node.isTreeNode){
this.focusNode(node);
}
},_onUpArrow:function(_c4b){
var node=_c4b.node;
var _c4c=node.getPreviousSibling();
if(_c4c){
node=_c4c;
while(node.isExpandable&&node.isExpanded&&node.hasChildren()){
var _c4d=node.getChildren();
node=_c4d[_c4d.length-1];
}
}else{
var _c4e=node.getParent();
if(!(!this.showRoot&&_c4e===this.rootNode)){
node=_c4e;
}
}
if(node&&node.isTreeNode){
this.focusNode(node);
}
},_onRightArrow:function(_c4f){
var node=_c4f.node;
if(node.isExpandable&&!node.isExpanded){
this._expandNode(node);
}else{
if(node.hasChildren()){
node=node.getChildren()[0];
if(node&&node.isTreeNode){
this.focusNode(node);
}
}
}
},_onLeftArrow:function(_c50){
var node=_c50.node;
if(node.isExpandable&&node.isExpanded){
this._collapseNode(node);
}else{
var _c51=node.getParent();
if(_c51&&_c51.isTreeNode&&!(!this.showRoot&&_c51===this.rootNode)){
this.focusNode(_c51);
}
}
},_onHomeKey:function(){
var node=this._getRootOrFirstNode();
if(node){
this.focusNode(node);
}
},_onEndKey:function(_c52){
var node=this.rootNode;
while(node.isExpanded){
var c=node.getChildren();
node=c[c.length-1];
}
if(node&&node.isTreeNode){
this.focusNode(node);
}
},multiCharSearchDuration:250,_onLetterKeyNav:function(_c53){
var cs=this._curSearch;
if(cs){
cs.pattern=cs.pattern+_c53.key;
clearTimeout(cs.timer);
}else{
cs=this._curSearch={pattern:_c53.key,startNode:_c53.node};
}
var self=this;
cs.timer=setTimeout(function(){
delete self._curSearch;
},this.multiCharSearchDuration);
var node=cs.startNode;
do{
node=this._getNextNode(node);
if(!node){
node=this._getRootOrFirstNode();
}
}while(node!==cs.startNode&&(node.label.toLowerCase().substr(0,cs.pattern.length)!=cs.pattern));
if(node&&node.isTreeNode){
if(node!==cs.startNode){
this.focusNode(node);
}
}
},_onClick:function(_c54,e){
var _c55=e.target,_c56=(_c55==_c54.expandoNode||_c55==_c54.expandoNodeText);
if((this.openOnClick&&_c54.isExpandable)||_c56){
if(_c54.isExpandable){
this._onExpandoClick({node:_c54});
}
}else{
this._publish("execute",{item:_c54.item,node:_c54,evt:e});
this.onClick(_c54.item,_c54,e);
this.focusNode(_c54);
}
if(!_c56){
this._selectNode(_c54);
}
dojo.stopEvent(e);
},_onDblClick:function(_c57,e){
var _c58=e.target,_c59=(_c58==_c57.expandoNode||_c58==_c57.expandoNodeText);
if((this.openOnDblClick&&_c57.isExpandable)||_c59){
if(_c57.isExpandable){
this._onExpandoClick({node:_c57});
}
}else{
this._publish("execute",{item:_c57.item,node:_c57,evt:e});
this.onDblClick(_c57.item,_c57,e);
this.focusNode(_c57);
}
if(!_c59){
this._selectNode(_c57);
}
dojo.stopEvent(e);
},_onExpandoClick:function(_c5a){
var node=_c5a.node;
this.focusNode(node);
if(node.isExpanded){
this._collapseNode(node);
}else{
this._expandNode(node);
}
},onClick:function(item,node,evt){
},onDblClick:function(item,node,evt){
},onOpen:function(item,node){
},onClose:function(item,node){
},_getNextNode:function(node){
if(node.isExpandable&&node.isExpanded&&node.hasChildren()){
return node.getChildren()[0];
}else{
while(node&&node.isTreeNode){
var _c5b=node.getNextSibling();
if(_c5b){
return _c5b;
}
node=node.getParent();
}
return null;
}
},_getRootOrFirstNode:function(){
return this.showRoot?this.rootNode:this.rootNode.getChildren()[0];
},_collapseNode:function(node){
if(node._expandNodeDeferred){
delete node._expandNodeDeferred;
}
if(node.isExpandable){
if(node.state=="LOADING"){
return;
}
node.collapse();
this.onClose(node.item,node);
if(node.item){
this._state(node.item,false);
this._saveState();
}
}
},_expandNode:function(node,_c5c){
if(node._expandNodeDeferred&&!_c5c){
return node._expandNodeDeferred;
}
var _c5d=this.model,item=node.item,_c5e=this;
switch(node.state){
case "UNCHECKED":
node.markProcessing();
var def=(node._expandNodeDeferred=new dojo.Deferred());
_c5d.getChildren(item,function(_c5f){
node.unmarkProcessing();
var scid=node.setChildItems(_c5f);
var ed=_c5e._expandNode(node,true);
scid.addCallback(function(){
ed.addCallback(function(){
def.callback();
});
});
},function(err){
console.error(_c5e,": error loading root children: ",err);
});
break;
default:
def=(node._expandNodeDeferred=node.expand());
this.onOpen(node.item,node);
if(item){
this._state(item,true);
this._saveState();
}
}
return def;
},focusNode:function(node){
dijit.focus(node.labelNode);
},_selectNode:function(node){
if(this.selectedNode&&!this.selectedNode._destroyed){
this.selectedNode.setSelected(false);
}
if(node){
node.setSelected(true);
}
this.selectedNode=node;
},_onNodeFocus:function(node){
if(node&&node!=this.lastFocused){
if(this.lastFocused&&!this.lastFocused._destroyed){
this.lastFocused.setFocusable(false);
}
node.setFocusable(true);
this.lastFocused=node;
}
},_onNodeMouseEnter:function(node){
},_onNodeMouseLeave:function(node){
},_onItemChange:function(item){
var _c60=this.model,_c61=_c60.getIdentity(item),_c62=this._itemNodesMap[_c61];
if(_c62){
var _c63=this.getLabel(item),_c64=this.getTooltip(item);
dojo.forEach(_c62,function(node){
node.set({item:item,label:_c63,tooltip:_c64});
node._updateItemClasses(item);
});
}
},_onItemChildrenChange:function(_c65,_c66){
var _c67=this.model,_c68=_c67.getIdentity(_c65),_c69=this._itemNodesMap[_c68];
if(_c69){
dojo.forEach(_c69,function(_c6a){
_c6a.setChildItems(_c66);
});
}
},_onItemDelete:function(item){
var _c6b=this.model,_c6c=_c6b.getIdentity(item),_c6d=this._itemNodesMap[_c6c];
if(_c6d){
dojo.forEach(_c6d,function(node){
var _c6e=node.getParent();
if(_c6e){
_c6e.removeChild(node);
}
node.destroyRecursive();
});
delete this._itemNodesMap[_c6c];
}
},_initState:function(){
if(this.persist){
var _c6f=dojo.cookie(this.cookieName);
this._openedItemIds={};
if(_c6f){
dojo.forEach(_c6f.split(","),function(item){
this._openedItemIds[item]=true;
},this);
}
}
},_state:function(item,_c70){
if(!this.persist){
return false;
}
var id=this.model.getIdentity(item);
if(arguments.length===1){
return this._openedItemIds[id];
}
if(_c70){
this._openedItemIds[id]=true;
}else{
delete this._openedItemIds[id];
}
},_saveState:function(){
if(!this.persist){
return;
}
var ary=[];
for(var id in this._openedItemIds){
ary.push(id);
}
dojo.cookie(this.cookieName,ary.join(","),{expires:365});
},destroy:function(){
if(this._curSearch){
clearTimeout(this._curSearch.timer);
delete this._curSearch;
}
if(this.rootNode){
this.rootNode.destroyRecursive();
}
if(this.dndController&&!dojo.isString(this.dndController)){
this.dndController.destroy();
}
this.rootNode=null;
this.inherited(arguments);
},destroyRecursive:function(){
this.destroy();
},resize:function(_c71){
if(_c71){
dojo.marginBox(this.domNode,_c71);
dojo.style(this.domNode,"overflow","auto");
}
this._nodePixelIndent=dojo.marginBox(this.tree.indentDetector).w;
if(this.tree.rootNode){
this.tree.rootNode.set("indent",this.showRoot?0:-1);
}
},_createTreeNode:function(args){
return new dijit._TreeNode(args);
}});
}
if(!dojo._hasResource["insight.resources.ResourceTree"]){
dojo._hasResource["insight.resources.ResourceTree"]=true;
dojo.provide("insight.resources.ResourceTree");
dojo.declare("insight.resources.ResourceTreeNode",dijit._TreeNode,{attributeMap:dojo.delegate(dijit._Widget.prototype.attributeMap,{label:{node:"labelNode",type:"innerHTML"},tooltip:{node:"rowNode",type:"attribute",attribute:"title"}}),_updateItemClasses:function(item){
this.inherited(arguments);
if(item.health&&item.health.rating=="NOSAMPLE"){
this.set("style",{display:"none"});
}else{
this.set("style",{display:""});
}
},_onMouseEnter:function(evt){
dojo.addClass(this.rowNode,"dijitTreeHighlight");
this.inherited(arguments);
},_onMouseLeave:function(evt){
dojo.removeClass(this.rowNode,"dijitTreeHighlight");
this.inherited(arguments);
}});
dojo.declare("insight.resources.ResourceTree",dijit.Tree,{persist:false,healthLampUrl:null,startup:function(){
this.inherited(arguments);
dojo.forEach(this.attr("rootNode").getChildren(),function(node){
this._expandNode(node);
},this);
this.rootNode.startup();
},getLabel:function(item){
var _c72="";
if(item.health){
_c72+="<img src='"+this.healthLampUrl.build({lamp:item.healthLamp})+"' /> ";
}
_c72+=this.model.getLabel(item);
return _c72;
},getTooltip:function(item){
return this.model.getLabel(item);
},findTreeNode:function(_c73){
return this.getNodesByItem(_c73.toString())[0];
},highlightResource:function(_c74){
var node=this.findTreeNode(_c74);
if(node){
this._selectNode(node);
}else{
this.set("path",this._resourcePath(_c74));
}
},_resourcePath:function(_c75){
_c75=new insight.resources.ResourceKey(_c75);
var path=[];
function _c76(_c77){
if(_c77){
path.push(_c77.toString());
_c76(_c77.makeParentResourceKey());
if(_c77.getName()){
if(_c77.getType()=="Application"){
path.push("insight:type=Application");
}else{
if(_c77.getType()=="Server"){
path.push("insight:type=Server");
}
}
}
}
};
_c76(_c75);
return path.reverse();
},_createTreeNode:function(args){
return new insight.resources.ResourceTreeNode(args);
},_initState:function(){
if(this.persist){
var _c78=dojo.cookie(this.cookieName);
this._openedItemIds={};
if(_c78){
dojo.forEach(_c78.split("|"),function(item){
this._openedItemIds[item]=true;
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
},_onDownArrow:function(_c79){
var node=this._getNextNode(_c79.node);
if(node&&node.isTreeNode){
if(dojo.style(node,"display")=="none"){
this._onDownArrow(dojo.delegate(_c79,{node:node}));
return;
}
this.focusNode(node);
}
},_onUpArrow:function(_c7a){
var node=_c7a.node;
var _c7b=node.getPreviousSibling();
if(_c7b){
node=_c7b;
while(node.isExpandable&&node.isExpanded&&node.hasChildren()){
var _c7c=node.getChildren();
node=_c7c[_c7c.length-1];
}
}else{
var _c7d=node.getParent();
if(!(!this.showRoot&&_c7d===this.rootNode)){
node=_c7d;
}
}
if(node&&node.isTreeNode){
if(dojo.style(node,"display")=="none"){
this._onUpArrow(dojo.delegate(_c7a,{node:node}));
return;
}
this.focusNode(node);
}
}});
}
if(!dojo._hasResource["insight.time.TimeRangeDropDownButton"]){
dojo._hasResource["insight.time.TimeRangeDropDownButton"]=true;
dojo.provide("insight.time.TimeRangeDropDownButton");
dojo.declare("insight.time.TimeRangeDropDownButton",dijit.form.DropDownButton,{times:[insight.time.millis.minute*15,insight.time.millis.minute*30,insight.time.millis.hour,insight.time.millis.hour*4,insight.time.millis.hour*8,insight.time.millis.hour*12,insight.time.millis.day,insight.time.millis.day*3,insight.time.millis.week],playPauseMenuItem:null,postCreate:function(){
this.inherited(arguments);
var menu=new dijit.Menu({},dojo.doc.createElement("div"));
this.playPauseMenuItem=this._createMenuItem(0);
menu.addChild(this.playPauseMenuItem);
dojo.forEach(this.times,function(time){
menu.addChild(this._createMenuItem(time));
},this);
this.dropDown=menu;
},_createMenuItem:function(time){
var item=new dijit.MenuItem({label:insight.time.shortLabel(time)});
this.connect(item,"onClick",function(){
this.attr("selected",time);
});
return item;
},setLabelTimeRange:function(_c7e){
var _c7f=insight.time.shortLabel(_c7e.getDuration(),true)+" @ ";
if(!_c7e.isAnchored()){
_c7f+="now";
this.playPauseMenuItem.set("label","pause");
}else{
_c7f+=insight.time.labels(_c7e.getEndDate(),new Date()).start;
this.playPauseMenuItem.set("label","now");
}
this.set("label",_c7f);
this.set("title",_c7f);
},_setSelectedAttr:function(time){
if(time!=null){
this.onChange(time);
}
},onChange:dijit._connectOnUseEventHandler});
}
if(!dojo._hasResource["insight.traces.FilterMenu"]){
dojo._hasResource["insight.traces.FilterMenu"]=true;
dojo.provide("insight.traces.FilterMenu");
dojo.extend(dijit._MenuBase,{executeOnClick:true,onItemClick:function(item,evt){
if(typeof this.isShowingNow=="undefined"){
this._markActive();
}
this.focusChild(item);
if(item.disabled){
return false;
}
if(item.popup){
this._openPopup();
}else{
if(this.executeOnClick){
this.onExecute();
}
item.onClick(evt);
}
}});
}
if(!dojo._hasResource["dojo.data.util.filter"]){
dojo._hasResource["dojo.data.util.filter"]=true;
dojo.provide("dojo.data.util.filter");
dojo.data.util.filter.patternToRegExp=function(_c80,_c81){
var rxp="^";
var c=null;
for(var i=0;i<_c80.length;i++){
c=_c80.charAt(i);
switch(c){
case "\\":
rxp+=c;
i++;
rxp+=_c80.charAt(i);
break;
case "*":
rxp+=".*";
break;
case "?":
rxp+=".";
break;
case "$":
case "^":
case "/":
case "+":
case ".":
case "|":
case "(":
case ")":
case "{":
case "}":
case "[":
case "]":
rxp+="\\";
default:
rxp+=c;
}
}
rxp+="$";
if(_c81){
return new RegExp(rxp,"mi");
}else{
return new RegExp(rxp,"m");
}
};
}
if(!dojo._hasResource["dojo.data.util.simpleFetch"]){
dojo._hasResource["dojo.data.util.simpleFetch"]=true;
dojo.provide("dojo.data.util.simpleFetch");
dojo.data.util.simpleFetch.fetch=function(_c82){
_c82=_c82||{};
if(!_c82.store){
_c82.store=this;
}
var self=this;
var _c83=function(_c84,_c85){
if(_c85.onError){
var _c86=_c85.scope||dojo.global;
_c85.onError.call(_c86,_c84,_c85);
}
};
var _c87=function(_c88,_c89){
var _c8a=_c89.abort||null;
var _c8b=false;
var _c8c=_c89.start?_c89.start:0;
var _c8d=(_c89.count&&(_c89.count!==Infinity))?(_c8c+_c89.count):_c88.length;
_c89.abort=function(){
_c8b=true;
if(_c8a){
_c8a.call(_c89);
}
};
var _c8e=_c89.scope||dojo.global;
if(!_c89.store){
_c89.store=self;
}
if(_c89.onBegin){
_c89.onBegin.call(_c8e,_c88.length,_c89);
}
if(_c89.sort){
_c88.sort(dojo.data.util.sorter.createSortFunction(_c89.sort,self));
}
if(_c89.onItem){
for(var i=_c8c;(i<_c88.length)&&(i<_c8d);++i){
var item=_c88[i];
if(!_c8b){
_c89.onItem.call(_c8e,item,_c89);
}
}
}
if(_c89.onComplete&&!_c8b){
var _c8f=null;
if(!_c89.onItem){
_c8f=_c88.slice(_c8c,_c8d);
}
_c89.onComplete.call(_c8e,_c8f,_c89);
}
};
this._fetchItems(_c82,_c87,_c83);
return _c82;
};
}
if(!dojo._hasResource["dojo.data.ItemFileReadStore"]){
dojo._hasResource["dojo.data.ItemFileReadStore"]=true;
dojo.provide("dojo.data.ItemFileReadStore");
dojo.declare("dojo.data.ItemFileReadStore",null,{constructor:function(_c90){
this._arrayOfAllItems=[];
this._arrayOfTopLevelItems=[];
this._loadFinished=false;
this._jsonFileUrl=_c90.url;
this._ccUrl=_c90.url;
this.url=_c90.url;
this._jsonData=_c90.data;
this.data=null;
this._datatypeMap=_c90.typeMap||{};
if(!this._datatypeMap["Date"]){
this._datatypeMap["Date"]={type:Date,deserialize:function(_c91){
return dojo.date.stamp.fromISOString(_c91);
}};
}
this._features={"dojo.data.api.Read":true,"dojo.data.api.Identity":true};
this._itemsByIdentity=null;
this._storeRefPropName="_S";
this._itemNumPropName="_0";
this._rootItemPropName="_RI";
this._reverseRefMap="_RRM";
this._loadInProgress=false;
this._queuedFetches=[];
if(_c90.urlPreventCache!==undefined){
this.urlPreventCache=_c90.urlPreventCache?true:false;
}
if(_c90.hierarchical!==undefined){
this.hierarchical=_c90.hierarchical?true:false;
}
if(_c90.clearOnClose){
this.clearOnClose=true;
}
if("failOk" in _c90){
this.failOk=_c90.failOk?true:false;
}
},url:"",_ccUrl:"",data:null,typeMap:null,clearOnClose:false,urlPreventCache:false,failOk:false,hierarchical:true,_assertIsItem:function(item){
if(!this.isItem(item)){
throw new Error("dojo.data.ItemFileReadStore: Invalid item argument.");
}
},_assertIsAttribute:function(_c92){
if(typeof _c92!=="string"){
throw new Error("dojo.data.ItemFileReadStore: Invalid attribute argument.");
}
},getValue:function(item,_c93,_c94){
var _c95=this.getValues(item,_c93);
return (_c95.length>0)?_c95[0]:_c94;
},getValues:function(item,_c96){
this._assertIsItem(item);
this._assertIsAttribute(_c96);
return (item[_c96]||[]).slice(0);
},getAttributes:function(item){
this._assertIsItem(item);
var _c97=[];
for(var key in item){
if((key!==this._storeRefPropName)&&(key!==this._itemNumPropName)&&(key!==this._rootItemPropName)&&(key!==this._reverseRefMap)){
_c97.push(key);
}
}
return _c97;
},hasAttribute:function(item,_c98){
this._assertIsItem(item);
this._assertIsAttribute(_c98);
return (_c98 in item);
},containsValue:function(item,_c99,_c9a){
var _c9b=undefined;
if(typeof _c9a==="string"){
_c9b=dojo.data.util.filter.patternToRegExp(_c9a,false);
}
return this._containsValue(item,_c99,_c9a,_c9b);
},_containsValue:function(item,_c9c,_c9d,_c9e){
return dojo.some(this.getValues(item,_c9c),function(_c9f){
if(_c9f!==null&&!dojo.isObject(_c9f)&&_c9e){
if(_c9f.toString().match(_c9e)){
return true;
}
}else{
if(_c9d===_c9f){
return true;
}
}
});
},isItem:function(_ca0){
if(_ca0&&_ca0[this._storeRefPropName]===this){
if(this._arrayOfAllItems[_ca0[this._itemNumPropName]]===_ca0){
return true;
}
}
return false;
},isItemLoaded:function(_ca1){
return this.isItem(_ca1);
},loadItem:function(_ca2){
this._assertIsItem(_ca2.item);
},getFeatures:function(){
return this._features;
},getLabel:function(item){
if(this._labelAttr&&this.isItem(item)){
return this.getValue(item,this._labelAttr);
}
return undefined;
},getLabelAttributes:function(item){
if(this._labelAttr){
return [this._labelAttr];
}
return null;
},_fetchItems:function(_ca3,_ca4,_ca5){
var self=this,_ca6=function(_ca7,_ca8){
var _ca9=[],i,key;
if(_ca7.query){
var _caa,_cab=_ca7.queryOptions?_ca7.queryOptions.ignoreCase:false;
var _cac={};
for(key in _ca7.query){
_caa=_ca7.query[key];
if(typeof _caa==="string"){
_cac[key]=dojo.data.util.filter.patternToRegExp(_caa,_cab);
}else{
if(_caa instanceof RegExp){
_cac[key]=_caa;
}
}
}
for(i=0;i<_ca8.length;++i){
var _cad=true;
var _cae=_ca8[i];
if(_cae===null){
_cad=false;
}else{
for(key in _ca7.query){
_caa=_ca7.query[key];
if(!self._containsValue(_cae,key,_caa,_cac[key])){
_cad=false;
}
}
}
if(_cad){
_ca9.push(_cae);
}
}
_ca4(_ca9,_ca7);
}else{
for(i=0;i<_ca8.length;++i){
var item=_ca8[i];
if(item!==null){
_ca9.push(item);
}
}
_ca4(_ca9,_ca7);
}
};
if(this._loadFinished){
_ca6(_ca3,this._getItemsArray(_ca3.queryOptions));
}else{
if(this._jsonFileUrl!==this._ccUrl){
dojo.deprecated("dojo.data.ItemFileReadStore: ","To change the url, set the url property of the store,"+" not _jsonFileUrl.  _jsonFileUrl support will be removed in 2.0");
this._ccUrl=this._jsonFileUrl;
this.url=this._jsonFileUrl;
}else{
if(this.url!==this._ccUrl){
this._jsonFileUrl=this.url;
this._ccUrl=this.url;
}
}
if(this.data!=null&&this._jsonData==null){
this._jsonData=this.data;
this.data=null;
}
if(this._jsonFileUrl){
if(this._loadInProgress){
this._queuedFetches.push({args:_ca3,filter:_ca6});
}else{
this._loadInProgress=true;
var _caf={url:self._jsonFileUrl,handleAs:"json-comment-optional",preventCache:this.urlPreventCache,failOk:this.failOk};
var _cb0=dojo.xhrGet(_caf);
_cb0.addCallback(function(data){
try{
self._getItemsFromLoadedData(data);
self._loadFinished=true;
self._loadInProgress=false;
_ca6(_ca3,self._getItemsArray(_ca3.queryOptions));
self._handleQueuedFetches();
}
catch(e){
self._loadFinished=true;
self._loadInProgress=false;
_ca5(e,_ca3);
}
});
_cb0.addErrback(function(_cb1){
self._loadInProgress=false;
_ca5(_cb1,_ca3);
});
var _cb2=null;
if(_ca3.abort){
_cb2=_ca3.abort;
}
_ca3.abort=function(){
var df=_cb0;
if(df&&df.fired===-1){
df.cancel();
df=null;
}
if(_cb2){
_cb2.call(_ca3);
}
};
}
}else{
if(this._jsonData){
try{
this._loadFinished=true;
this._getItemsFromLoadedData(this._jsonData);
this._jsonData=null;
_ca6(_ca3,this._getItemsArray(_ca3.queryOptions));
}
catch(e){
_ca5(e,_ca3);
}
}else{
_ca5(new Error("dojo.data.ItemFileReadStore: No JSON source data was provided as either URL or a nested Javascript object."),_ca3);
}
}
}
},_handleQueuedFetches:function(){
if(this._queuedFetches.length>0){
for(var i=0;i<this._queuedFetches.length;i++){
var _cb3=this._queuedFetches[i],_cb4=_cb3.args,_cb5=_cb3.filter;
if(_cb5){
_cb5(_cb4,this._getItemsArray(_cb4.queryOptions));
}else{
this.fetchItemByIdentity(_cb4);
}
}
this._queuedFetches=[];
}
},_getItemsArray:function(_cb6){
if(_cb6&&_cb6.deep){
return this._arrayOfAllItems;
}
return this._arrayOfTopLevelItems;
},close:function(_cb7){
if(this.clearOnClose&&this._loadFinished&&!this._loadInProgress){
if(((this._jsonFileUrl==""||this._jsonFileUrl==null)&&(this.url==""||this.url==null))&&this.data==null){
}
this._arrayOfAllItems=[];
this._arrayOfTopLevelItems=[];
this._loadFinished=false;
this._itemsByIdentity=null;
this._loadInProgress=false;
this._queuedFetches=[];
}
},_getItemsFromLoadedData:function(_cb8){
var _cb9=false,self=this;
function _cba(_cbb){
var _cbc=((_cbb!==null)&&(typeof _cbb==="object")&&(!dojo.isArray(_cbb)||_cb9)&&(!dojo.isFunction(_cbb))&&(_cbb.constructor==Object||dojo.isArray(_cbb))&&(typeof _cbb._reference==="undefined")&&(typeof _cbb._type==="undefined")&&(typeof _cbb._value==="undefined")&&self.hierarchical);
return _cbc;
};
function _cbd(_cbe){
self._arrayOfAllItems.push(_cbe);
for(var _cbf in _cbe){
var _cc0=_cbe[_cbf];
if(_cc0){
if(dojo.isArray(_cc0)){
var _cc1=_cc0;
for(var k=0;k<_cc1.length;++k){
var _cc2=_cc1[k];
if(_cba(_cc2)){
_cbd(_cc2);
}
}
}else{
if(_cba(_cc0)){
_cbd(_cc0);
}
}
}
}
};
this._labelAttr=_cb8.label;
var i,item;
this._arrayOfAllItems=[];
this._arrayOfTopLevelItems=_cb8.items;
for(i=0;i<this._arrayOfTopLevelItems.length;++i){
item=this._arrayOfTopLevelItems[i];
if(dojo.isArray(item)){
_cb9=true;
}
_cbd(item);
item[this._rootItemPropName]=true;
}
var _cc3={},key;
for(i=0;i<this._arrayOfAllItems.length;++i){
item=this._arrayOfAllItems[i];
for(key in item){
if(key!==this._rootItemPropName){
var _cc4=item[key];
if(_cc4!==null){
if(!dojo.isArray(_cc4)){
item[key]=[_cc4];
}
}else{
item[key]=[null];
}
}
_cc3[key]=key;
}
}
while(_cc3[this._storeRefPropName]){
this._storeRefPropName+="_";
}
while(_cc3[this._itemNumPropName]){
this._itemNumPropName+="_";
}
while(_cc3[this._reverseRefMap]){
this._reverseRefMap+="_";
}
var _cc5;
var _cc6=_cb8.identifier;
if(_cc6){
this._itemsByIdentity={};
this._features["dojo.data.api.Identity"]=_cc6;
for(i=0;i<this._arrayOfAllItems.length;++i){
item=this._arrayOfAllItems[i];
_cc5=item[_cc6];
var _cc7=_cc5[0];
if(!this._itemsByIdentity[_cc7]){
this._itemsByIdentity[_cc7]=item;
}else{
if(this._jsonFileUrl){
throw new Error("dojo.data.ItemFileReadStore:  The json data as specified by: ["+this._jsonFileUrl+"] is malformed.  Items within the list have identifier: ["+_cc6+"].  Value collided: ["+_cc7+"]");
}else{
if(this._jsonData){
throw new Error("dojo.data.ItemFileReadStore:  The json data provided by the creation arguments is malformed.  Items within the list have identifier: ["+_cc6+"].  Value collided: ["+_cc7+"]");
}
}
}
}
}else{
this._features["dojo.data.api.Identity"]=Number;
}
for(i=0;i<this._arrayOfAllItems.length;++i){
item=this._arrayOfAllItems[i];
item[this._storeRefPropName]=this;
item[this._itemNumPropName]=i;
}
for(i=0;i<this._arrayOfAllItems.length;++i){
item=this._arrayOfAllItems[i];
for(key in item){
_cc5=item[key];
for(var j=0;j<_cc5.length;++j){
_cc4=_cc5[j];
if(_cc4!==null&&typeof _cc4=="object"){
if(("_type" in _cc4)&&("_value" in _cc4)){
var type=_cc4._type;
var _cc8=this._datatypeMap[type];
if(!_cc8){
throw new Error("dojo.data.ItemFileReadStore: in the typeMap constructor arg, no object class was specified for the datatype '"+type+"'");
}else{
if(dojo.isFunction(_cc8)){
_cc5[j]=new _cc8(_cc4._value);
}else{
if(dojo.isFunction(_cc8.deserialize)){
_cc5[j]=_cc8.deserialize(_cc4._value);
}else{
throw new Error("dojo.data.ItemFileReadStore: Value provided in typeMap was neither a constructor, nor a an object with a deserialize function");
}
}
}
}
if(_cc4._reference){
var _cc9=_cc4._reference;
if(!dojo.isObject(_cc9)){
_cc5[j]=this._getItemByIdentity(_cc9);
}else{
for(var k=0;k<this._arrayOfAllItems.length;++k){
var _cca=this._arrayOfAllItems[k],_ccb=true;
for(var _ccc in _cc9){
if(_cca[_ccc]!=_cc9[_ccc]){
_ccb=false;
}
}
if(_ccb){
_cc5[j]=_cca;
}
}
}
if(this.referenceIntegrity){
var _ccd=_cc5[j];
if(this.isItem(_ccd)){
this._addReferenceToMap(_ccd,item,key);
}
}
}else{
if(this.isItem(_cc4)){
if(this.referenceIntegrity){
this._addReferenceToMap(_cc4,item,key);
}
}
}
}
}
}
}
},_addReferenceToMap:function(_cce,_ccf,_cd0){
},getIdentity:function(item){
var _cd1=this._features["dojo.data.api.Identity"];
if(_cd1===Number){
return item[this._itemNumPropName];
}else{
var _cd2=item[_cd1];
if(_cd2){
return _cd2[0];
}
}
return null;
},fetchItemByIdentity:function(_cd3){
var item,_cd4;
if(!this._loadFinished){
var self=this;
if(this._jsonFileUrl!==this._ccUrl){
dojo.deprecated("dojo.data.ItemFileReadStore: ","To change the url, set the url property of the store,"+" not _jsonFileUrl.  _jsonFileUrl support will be removed in 2.0");
this._ccUrl=this._jsonFileUrl;
this.url=this._jsonFileUrl;
}else{
if(this.url!==this._ccUrl){
this._jsonFileUrl=this.url;
this._ccUrl=this.url;
}
}
if(this.data!=null&&this._jsonData==null){
this._jsonData=this.data;
this.data=null;
}
if(this._jsonFileUrl){
if(this._loadInProgress){
this._queuedFetches.push({args:_cd3});
}else{
this._loadInProgress=true;
var _cd5={url:self._jsonFileUrl,handleAs:"json-comment-optional",preventCache:this.urlPreventCache,failOk:this.failOk};
var _cd6=dojo.xhrGet(_cd5);
_cd6.addCallback(function(data){
var _cd7=_cd3.scope?_cd3.scope:dojo.global;
try{
self._getItemsFromLoadedData(data);
self._loadFinished=true;
self._loadInProgress=false;
item=self._getItemByIdentity(_cd3.identity);
if(_cd3.onItem){
_cd3.onItem.call(_cd7,item);
}
self._handleQueuedFetches();
}
catch(error){
self._loadInProgress=false;
if(_cd3.onError){
_cd3.onError.call(_cd7,error);
}
}
});
_cd6.addErrback(function(_cd8){
self._loadInProgress=false;
if(_cd3.onError){
var _cd9=_cd3.scope?_cd3.scope:dojo.global;
_cd3.onError.call(_cd9,_cd8);
}
});
}
}else{
if(this._jsonData){
self._getItemsFromLoadedData(self._jsonData);
self._jsonData=null;
self._loadFinished=true;
item=self._getItemByIdentity(_cd3.identity);
if(_cd3.onItem){
_cd4=_cd3.scope?_cd3.scope:dojo.global;
_cd3.onItem.call(_cd4,item);
}
}
}
}else{
item=this._getItemByIdentity(_cd3.identity);
if(_cd3.onItem){
_cd4=_cd3.scope?_cd3.scope:dojo.global;
_cd3.onItem.call(_cd4,item);
}
}
},_getItemByIdentity:function(_cda){
var item=null;
if(this._itemsByIdentity){
item=this._itemsByIdentity[_cda];
}else{
item=this._arrayOfAllItems[_cda];
}
if(item===undefined){
item=null;
}
return item;
},getIdentityAttributes:function(item){
var _cdb=this._features["dojo.data.api.Identity"];
if(_cdb===Number){
return null;
}else{
return [_cdb];
}
},_forceLoad:function(){
var self=this;
if(this._jsonFileUrl!==this._ccUrl){
dojo.deprecated("dojo.data.ItemFileReadStore: ","To change the url, set the url property of the store,"+" not _jsonFileUrl.  _jsonFileUrl support will be removed in 2.0");
this._ccUrl=this._jsonFileUrl;
this.url=this._jsonFileUrl;
}else{
if(this.url!==this._ccUrl){
this._jsonFileUrl=this.url;
this._ccUrl=this.url;
}
}
if(this.data!=null&&this._jsonData==null){
this._jsonData=this.data;
this.data=null;
}
if(this._jsonFileUrl){
var _cdc={url:this._jsonFileUrl,handleAs:"json-comment-optional",preventCache:this.urlPreventCache,failOk:this.failOk,sync:true};
var _cdd=dojo.xhrGet(_cdc);
_cdd.addCallback(function(data){
try{
if(self._loadInProgress!==true&&!self._loadFinished){
self._getItemsFromLoadedData(data);
self._loadFinished=true;
}else{
if(self._loadInProgress){
throw new Error("dojo.data.ItemFileReadStore:  Unable to perform a synchronous load, an async load is in progress.");
}
}
}
catch(e){
throw e;
}
});
_cdd.addErrback(function(_cde){
throw _cde;
});
}else{
if(this._jsonData){
self._getItemsFromLoadedData(self._jsonData);
self._jsonData=null;
self._loadFinished=true;
}
}
}});
dojo.extend(dojo.data.ItemFileReadStore,dojo.data.util.simpleFetch);
}
if(!dojo._hasResource["insight.traces.FrameStack"]){
dojo._hasResource["insight.traces.FrameStack"]=true;
dojo.provide("insight.traces.FrameStack");
dojo.declare("insight.traces.FrameTreeNode",dijit._TreeNode,{templateString:dojo.cache("insight.traces","templates/FrameTreeNode.html","<div class=\"dijitTreeNode\" waiRole=\"presentation\"\n\t><div dojoAttachPoint=\"rowNode\" class=\"dijitTreeRow\" waiRole=\"presentation\" dojoAttachEvent=\"onmouseenter:_onMouseEnter, onmouseleave:_onMouseLeave, onclick:_onClick, ondblclick:_onDblClick\"\n\t\t><div class=\"insightGanttContainer\" dojoAttachPoint=\"ganttNode\"\n\t\t\t><div dojoAttachPoint=\"durationNode\" class=\"insightGanttDuration\"></div\n\t\t>&nbsp;</div\n\t\t><div class=\"operationExpando\"></div\n\t\t><img src=\"${_blankGif}\" alt=\"\" dojoAttachPoint=\"expandoNode\" class=\"dijitTreeExpando\" waiRole=\"presentation\"\n\t\t><span dojoAttachPoint=\"expandoNodeText\" class=\"dijitExpandoText\" waiRole=\"presentation\"\n\t\t></span\n\t\t><span dojoAttachPoint=\"contentNode\"\n\t\t\tclass=\"dijitTreeContent\" waiRole=\"presentation\">\n\t\t\t<img src=\"${_blankGif}\" alt=\"\" dojoAttachPoint=\"iconNode\" class=\"dijitIcon dijitTreeIcon\" waiRole=\"presentation\"\n\t\t\t/><span dojoAttachPoint=\"labelNode\" class=\"dijitTreeLabel\" wairole=\"treeitem\" tabindex=\"-1\" waiState=\"selected-false\" dojoAttachEvent=\"onfocus:_onLabelFocus\"></span\n\t\t></span\n\t></div>\n\t<div dojoAttachPoint=\"containerNode\" class=\"dijitTreeContainer\" waiRole=\"presentation\" style=\"display: none;\"></div>\n</div>\n"),ganttNode:null,attributeMap:dojo.delegate(dijit._Widget.prototype.attributeMap,{label:{node:"labelNode",type:"innerText"},tooltip:{node:"rowNode",type:"attribute",attribute:"title"},duration:{node:"durationNode",type:"innerText"}}),postCreate:function(){
this.inherited(arguments);
this._addGanttBar(this.params.traceStartNanos,this.params.traceEndNanos,this.params.frameStartNanos,this.params.frameEndNanos,true);
dojo.forEach(dojo.fromJson(this.params.selfFragments),function(_cdf){
this._addGanttBar(this.params.traceStartNanos,this.params.traceEndNanos,_cdf.startNanos,_cdf.endNanos);
},this);
if(this.params.ganttTooltip){
dojo.attr(this.ganttNode,"title",this.params.ganttTooltip);
}
},_addGanttBar:function(_ce0,_ce1,_ce2,_ce3,_ce4){
var bar=dojo.doc.createElement("div");
dojo.addClass(bar,_ce4?"insightGanttBarMaster":"insightGanttBar");
dojo.style(bar,{position:"absolute",top:"2px",bottom:"2px",left:(_ce2-_ce0)/(_ce1-_ce0)*100+"%",right:(_ce1-_ce3)/(_ce1-_ce0)*100+"%"});
dojo.place(bar,this.ganttNode,"last");
},_onMouseEnter:function(evt){
dojo.addClass(this.rowNode.parentNode,"dijitTreeHighlight");
this.inherited(arguments);
},_onMouseLeave:function(evt){
dojo.removeClass(this.rowNode.parentNode,"dijitTreeHighlight");
this.inherited(arguments);
},destroyRecursive:function(){
if(this.operation){
this.operation.destroyRecursive();
this.operation=null;
}
this.inherited(arguments);
}});
dojo.declare("insight.traces.FrameStack",null,{autoOpenThreshold:0.1,traceId:null,tree:null,timeout:insight.runtime.getXhrTimeout("FrameStack"),constructor:function(_ce5,node){
this.autoOpenThreshold=_ce5.autoOpenThreshold||this.autoOpenThreshold;
this.traceId=_ce5.traceId;
if(!this.traceId){
throw ("traceId is required to create FrameStack");
}
if(dojo.isIE<9){
this.autoOpenThreshold*=2;
}
dojo.xhrGet({url:_ce5.traceUri.build(),handleAs:"json",timeout:this.timeout,load:dojo.hitch(this,function(_ce6,_ce7){
this._build(node,_ce6,_ce5.operationUri);
}),error:function(){
dojo.publish("error/xhr",arguments);
}});
},_build:function(_ce8,_ce9,_cea){
var _ceb=this._buildTreeStore(_ce9.trace,_cea);
this.tree=new dijit.Tree({onClick:dojo.hitch(this,this.toggleOperation),model:_ceb,persist:false,_createTreeNode:function(args){
args.duration=dojo.number.format(this.model.store.getValue(args.item,"duration"))+" ms";
if(this.model.store.hasAttribute(args.item,"durationSelf")){
args.ganttTooltip=dojo.number.format(this.model.store.getValue(args.item,"durationSelf"))+" ms (self time)";
args.selfFragments=this.model.store.getValue(args.item,"selfFragments");
}
args.traceStartNanos=this.model.store.getValue(args.item,"traceStartNanos");
args.traceEndNanos=this.model.store.getValue(args.item,"traceEndNanos");
args.frameStartNanos=this.model.store.getValue(args.item,"frameStartNanos");
args.frameEndNanos=this.model.store.getValue(args.item,"frameEndNanos");
return new insight.traces.FrameTreeNode(args);
}},_ce8);
this.tree.startup();
this._expandTree(this.tree);
},_buildTreeStore:function(_cec,_ced){
var _cee={identifier:"id",label:"label",traceId:_cec.id,range:_cec.range,items:[]};
this._processFrame(_cee,_cec.frameStack,null,_ced);
return new dijit.tree.TreeStoreModel({store:new dojo.data.ItemFileReadStore({data:_cee}),query:{type:"root"}});
},_processFrame:function(_cef,_cf0,_cf1,_cf2){
var item={id:_cf0.id,traceId:_cef.traceId,frameId:_cf0.id,url:_cf2.build({traceId:_cef.traceId,frameId:_cf0.id}),label:_cf0.operation.label,duration:_cf0.range.duration,durationSelf:_cf0.range.selfDuration,traceStartNanos:_cef.range.startNanos,traceEndNanos:_cef.range.endNanos,frameStartNanos:_cf0.range.startNanos,frameEndNanos:_cf0.range.endNanos,selfFragments:dojo.toJson(_cf0.range.selfFragments)};
if(_cf1){
if(!_cf1.children){
_cf1.children=[];
}
_cf1.children.push(item);
item.parentId=_cf1.frameId;
}else{
item.type="root";
_cef.items.push(item);
}
dojo.forEach(_cf0.children,function(_cf3){
this._processFrame(_cef,_cf3,item,_cf2);
},this);
},_expandTree:function(tree){
var _cf4=this._openedOperations();
this._expandTreeNode(tree,tree.rootNode,_cf4);
if(dojo.indexOf(_cf4,tree.rootNode.item.frameId)!=-1){
this._toggleOperation(tree.rootNode.item,tree.rootNode);
}
},_expandTreeNode:function(tree,node,_cf5){
tree._expandNode(node);
dojo.forEach(node.getChildren(),function(_cf6){
var _cf7=_cf6.item.duration/node.item.duration;
if(_cf7>this.autoOpenThreshold||this._forceExpand(_cf6.item,_cf5)){
this._expandTreeNode(tree,_cf6,_cf5);
}
if(dojo.indexOf(_cf5,_cf6.item.frameId)!=-1){
this._toggleOperation(_cf6.item,_cf6);
}
},this);
node.setSelected(false);
},_forceExpand:function(item,_cf8){
if(item.forceExpand===false){
return false;
}
if(dojo.indexOf(_cf8,item.frameId)!=-1){
return true;
}
if(item.children){
for(var i=0;i<item.children.length;i++){
if(this._forceExpand(item.children[i],_cf8)){
return true;
}
}
}
item.forceExpand=false;
return false;
},toggleOperation:function(item,node){
var _cf9=this._toggleOperation(item,node);
if(dojo.cookie.isSupported()){
this._updateCookie(item.frameId,!!_cf9);
}
},_openedOperations:function(){
var _cfa=dojo.cookie(this.tree.id+"-operations");
if(!_cfa){
return [];
}
var _cfb=_cfa.split(":")[0];
if(_cfb!=this.traceId){
return [];
}
return _cfa.split(":")[1].split(",");
},_updateCookie:function(_cfc,_cfd){
var _cfe=this._openedOperations();
if(_cfd){
_cfe.push(_cfc);
}else{
_cfe=dojo.filter(_cfe,function(i){
return i!=_cfc;
},this);
}
dojo.cookie(this.tree.id+"-operations",this.traceId+":"+_cfe.join(","));
},_toggleOperation:function(item,node){
var _cff=dojo.query("> .dijitTreeRow",node.domNode)[0];
if(node.operation){
node.operation.destroyRecursive();
dojo.removeClass(_cff,"dijitTreeNodeOpen");
node.operation=null;
}else{
var _d00=function(_d01){
_d01.cancelBubble=true;
};
node.operation=new dijit.layout.ContentPane({href:item.url,id:node.id+"_operation","class":"operation",onClick:_d00,onDblClick:_d00});
dojo.addClass(node.operation.domNode,"operation");
dojo.addClass(_cff,"dijitTreeNodeOpen");
node.operation.placeAt(dojo.query("> .dijitTreeRow > .dijitTreeContent",node.domNode)[0],"last");
node.operation.startup();
}
return node.operation;
}});
}
dojo.i18n._preloadLocalizations("dojo.nls.dojo",["ROOT","ar","ca","cs","da","de","de-de","el","en","en-gb","en-us","es","es-es","fi","fi-fi","fr","fr-fr","he","he-il","hu","it","it-it","ja","ja-jp","ko","ko-kr","nb","nl","nl-nl","pl","pt","pt-br","pt-pt","ru","sk","sl","sv","th","tr","xx","zh","zh-cn","zh-tw"]);
if(dojo.isBrowser&&(document.readyState==="complete"||dojo.config.afterOnLoad)){
window.setTimeout(dojo._loadInit,100);
}
})();
