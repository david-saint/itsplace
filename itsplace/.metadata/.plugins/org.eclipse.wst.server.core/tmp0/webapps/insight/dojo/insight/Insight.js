/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.Insight"]){
dojo._hasResource["insight.Insight"]=true;
dojo.provide("insight.Insight");
dojo.require("dojo.cookie");
dojo.require("dojo.hash");
dojo.require("insight.components.PageModule");
dojo.require("insight.components.SortableTable");
dojo.require("insight.resources.ResourceKey");
dojo.require("insight.time");
dojo.require("insight.time.TimeRange");
dojo.require("insight.traces.TraceList");
dojo.require("spring.UrlBuilder");
dojo.require("spring.HtmlFragmentResponseHandler");
dojo.declare("insight.Insight",null,{_basePath:null,_timeRange:null,_switchUri:null,_switchAppUri:null,_browserWarnType:null,_browserSupported:true,_hashChangeSubscription:null,refreshInterval:dojo.isIE?15000:5000,_pulseCount:0,_active:null,constructor:function(_1){
this._active={};
this._basePath=this._parseBasePath(_1);
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
},scheduleLogout:function(_2,_3){
this._logoutUrl=_2;
this._logoutDelay=_3||1800000;
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
},logout:function(_4){
_4=_4||this._logoutUrl;
if(_4){
dojo.global.location=_4;
}
},hashMode:function(_5){
if(_5){
this._hashMode=_5;
}
return this._hashMode;
},currentResource:function(){
return this.fromQueryString(dojo.hash()).resource;
},currentTrace:function(){
return this.fromQueryString(dojo.hash()).trace;
},getResourceTreeFilter:function(){
return dojo.fromJson(this.getLocal("resourceTreeFilter"));
},loadResource:function(_6){
if(_6=="insight:"){
_6="insight:type=Application";
}
if(_6!=this.currentResource()&&this.hashMode()=="resource"){
this.updateHash("resource",_6);
}else{
Insight._loadResource(_6);
}
},_loadResource:function(_7){
this.delayNextRefresh();
this._active.resource=_7;
var _8=new insight.resources.ResourceKey(_7);
Insight.hideResourceModules(_8);
Insight.loadResourceDetail("resource",_8);
this.highlightResourceInTree(_8);
},highlightResourceInTree:function(_9){
if(!_9){
_9=Insight.currentResource();
}
dojo.publish("Insight.highlightTreeResource",[_9]);
},hideResourceModules:function(_a){
if(!_a.getName()){
this._hideModule("resource");
}else{
this._hideModule("resources");
}
this._hideModule("resources_alt");
this._hideModule("traces-window");
this._hideModule("trace");
},_hideModule:function(id){
var _b=dijit.byId(id);
if(_b){
_b.hide();
_b.destroyDescendants();
}
},loadResourceDetail:function(id,_c){
this.destroyWidget(id);
var _d=this.getTimeRange();
return new insight.components.PageModule({url:Insight.buildUri("/services/resources/{resource}/{start}/{end}",{resource:_c.toString(),start:_d.getStart(),end:_d.getEnd()})},id);
},loadResourceList:function(id,_e,_f){
var _10=this.getTimeRange();
this.destroyWidget(id);
var _11=new insight.components.SortableTable({url:Insight.buildUri("/services/resources",{context:_e.toString(),resourceType:_f,start:_10.getStart(),end:_10.getEnd()}),defaultSortSettings:{field:"responseTimeScore",desc:true},resize:true,maxHeight:id=="resources_alt"?300:null},id);
_11.subscribe("Insight.timeRangeMajorPulse",function(_12){
this.updateUrlParams({start:_12.getStart(),end:_12.getEnd()});
if(!this.hidden()){
this.load();
}
});
return _11;
},loadTraces:function(_13){
this.destroyWidget("traces");
return new insight.components.PageModule({url:Insight.buildUri("/services/traces",{application:_13})},"traces");
},loadTaggedWindow:function(_14,end,_15,tag){
var _16={resource:_15,tag:tag},_17=dijit.byId("traces-window"),_18="/services/resources/{resource}/traces/{start}/{end}/tags/{tag}";
if(_14&&end){
_16.start=insight.time.toString(insight.time.normalizeToDate(_14));
_16.end=insight.time.toString(insight.time.normalizeToDate(end));
}else{
_16.start=Insight.getTimeRange().getStart();
_16.end=Insight.getTimeRange().getEnd();
}
this._hideModule("trace");
if(_17){
_17.updateUrlTemplate(Insight.buildUri(_18));
_17.updateUrlParams(_16);
_17.load();
}else{
_17=new insight.traces.TraceList({url:Insight.buildUri(_18,_16),maxHeight:150,defaultSortSettings:{field:"range.start",desc:true},resize:true},"traces-window");
_17.connect(_17,"loadTrace",dojo.hitch(Insight,Insight.loadTrace));
}
},loadWindow:function(_19,end,_1a,min,max){
var _1b={},_1c,_1d=150,_1e=dijit.byId("traces-window");
if(_19&&end){
_1b.start=insight.time.toString(insight.time.normalizeToDate(_19));
_1b.end=insight.time.toString(insight.time.normalizeToDate(end));
}else{
_1b.start=Insight.getTimeRange().getStart();
_1b.end=Insight.getTimeRange().getEnd();
}
if(_1a){
_1b.resource=_1a;
if(min||max){
_1b.min=min;
_1b.max=max;
_1c="/services/resources/{resource}/traces/{start}/{end}/{min}/{max}";
}else{
if(_1a.indexOf("insight:")==0){
_1c="/services/resources/{resource}/traces/{start}/{end}";
}
}
}
if(!_1c){
if(_1a){
_1b.application=_1a;
}
_1c="/services/traces/windows/{start}/{end}";
_1d=185;
}
this._hideModule("trace");
if(_1e){
_1e.updateUrlTemplate(Insight.buildUri(_1c));
_1e.updateUrlParams(_1b);
_1e.load();
}else{
_1e=new insight.traces.TraceList({url:Insight.buildUri(_1c,_1b),maxHeight:_1d,defaultSortSettings:{field:(min&&max)?"range.start":"range.duration",desc:true},resize:true},"traces-window");
_1e.connect(_1e,"loadTrace",dojo.hitch(Insight,Insight.loadTrace));
}
},loadTrace:function(_1f){
Insight._loadTrace(_1f);
},_loadTrace:function(_20){
this._active.trace=_20;
this.destroyWidget("trace");
return new insight.components.PageModule({url:Insight.buildUri("/services/traces/{traceId}",{traceId:_20})},"trace");
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
},switchToApplication:function(_21){
var url;
_21=this._normalizeApplication(_21);
if(_21==null&&this._switchUri){
url=this._switchUri.build();
}else{
if(_21!=null&&this._switchAppUri){
url=this._switchAppUri.build({application:_21});
}
}
if(url){
dojo.global.location=url;
}
},_normalizeApplication:function(_22){
if(_22=="*"){
return null;
}else{
return _22;
}
},getOperationFilter:function(){
return this.getCookie("Insight.operationFilter");
},_getOperationFilterArray:function(){
var _23=(this.getOperationFilter()||"").split("|");
return dojo.filter(_23,function(_24){
return !!_24;
},this);
},_getOperationFilterRegExp:function(_25){
return new RegExp("^[-+]"+dojo.regexp.escapeString(_25)+"$");
},clearOperationFilter:function(){
this.setCookie("Insight.operationFilter",null);
if(dijit.byId("trace")){
dijit.byId("trace").load();
}
},hasOperationFilter:function(_26){
var _27=this._getOperationFilterRegExp(_26),_28=dojo.filter(this._getOperationFilterArray(),function(r){
return r.match(_27);
},this);
return _28[0]&&_28[0].charAt(0);
},addOperationFilter:function(_29,_2a){
var _2b=this._getOperationFilterArray(),_2c=this._getOperationFilterRegExp(_29),_2d=[_2a+_29];
dojo.forEach(_2b,function(_2e){
if(!_2e.match(_2c)){
_2d.push(_2e);
}
},this);
this.setCookie("Insight.operationFilter",_2d.join("|"));
},removeOperationFilter:function(_2f){
var _30=(this.getOperationFilter()||"").split("|"),_31=this._getOperationFilterRegExp(_2f),_32=[];
dojo.forEach(_30,function(_33){
if(!_33.match(_31)){
_32.push(_33);
}
},this);
this.setCookie("Insight.operationFilter",_32.join("|"));
},getTimeRange:function(){
return this._timeRange.clone();
},setTimeRange:function(_34){
dojo.publish("Insight.timeRangeChange",[_34]);
if(!_34.isAnchored()){
this.play();
}else{
this.pause();
}
},incrementTimeRange:function(){
if(!this._timeRange.isAnchored()){
return;
}
var _35=this._timeRange.getDuration(),_36=_35/4,now=new Date().getTime();
if(now<this._timeRange.getEndDate().getTime()+_36+(_35*0.1)){
this._timeRange.shift().setAnchored(false);
}else{
this._timeRange.add(_36);
}
this.setTimeRange(this._timeRange);
},decrementTimeRange:function(){
var _37=this._timeRange.getDuration()*-1/4;
this._timeRange.add(_37);
this.setTimeRange(this._timeRange);
},shiftTimeRangeToNow:function(){
this.setTimeRange(this._timeRange.shift().setAnchored(false));
},_setTimeRange:function(_38){
this._timeRange=_38;
this.setLocal("Insight.timeRange",this._timeRange);
},_initTimeRange:function(){
var _39=new insight.time.TimeRange(this._defaultTimeRange());
this._setTimeRange(_39);
dojo.subscribe("Insight.timeRangeChange",this,function(_3a){
this._setTimeRange(_3a.clone());
this._pulseCount=-1;
dojo.publish("Insight.timeRangePulse",[_3a.clone()]);
});
dojo.subscribe("Insight.timeRangePulse",this,function(_3b){
this._pulseCount=(this._pulseCount+1)%6;
if(this._pulseCount===0){
dojo.publish("Insight.timeRangeMajorPulse",[_3b.clone()]);
}
});
if(!_39.isAnchored()){
dojo.publish("Insight.play",[this._timeRange.clone()]);
this._scheduleRefresh();
}
dojo.subscribe("Insight.timeRangeChange",this,function(_3c){
_3c=_3c.toString();
if(this._active.range!==_3c){
this.updateHash("range",_3c);
}
});
},_defaultTimeRange:function(){
return this.fromQueryString(dojo.hash()).range||this.getLocal("Insight.timeRange")||"900000";
},play:function(_3d){
if(_3d&&this._pausedForUser){
this._timeRange.setAnchored(false);
dojo.publish("Insight.play",[this._timeRange.clone()]);
this._pausedForUser=false;
this._scheduleRefresh();
}else{
if(!_3d&&!this.playing()){
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
},pause:function(_3e){
if(this.playing()){
clearTimeout(this._playing);
this._playing=null;
this._timeRange.setAnchored(true);
dojo.publish("Insight.pause",[this._timeRange.clone()]);
if(_3e){
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
},xhrError:function(_3f,_40,uid){
if(_3f=="Error: timeout exceeded"){
dojo.publish("insight/error",["Server took too long to respond: "+_40.url,"[TIMEOUT:"+_40.args.timeout+"]",uid]);
}else{
if(_40.xhr.status===0){
dojo.publish("insight/error",["Unable to connect to server, the server or network may be down: "+_40.url,"[NETWORK]",uid]);
}else{
dojo.publish("insight/error",["Server encountered an error: "+_40.url,"[HTTP:"+_40.xhr.status+"]",uid]);
}
}
},storeError:function(_41,_42,uid){
dojo.publish("insight/error",[_41,"[STORE:"+_42+"]",uid,true]);
},getLocal:function(_43){
var _44=this.getSession(_43);
if(_44===null){
_44=this.getCookie(_43);
}
return _44;
},getSession:function(_45){
if(dojo.global.sessionStorage){
return dojo.global.sessionStorage.getItem(_45);
}else{
return null;
}
},getCookie:function(_46){
if(dojo.cookie.isSupported()){
return dojo.cookie(_46);
}else{
return null;
}
},setLocal:function(_47,_48,_49){
this.setSession(_47,_48);
if(_49||this.getSession(_47)===null){
this.setCookie(_47,_48);
}
},setSession:function(_4a,_4b){
if(dojo.global.sessionStorage){
if(_4b===null){
dojo.global.sessionStorage.removeItem(_4a);
}else{
dojo.global.sessionStorage.setItem(_4a,_4b);
}
}
},setCookie:function(_4c,_4d,_4e){
if(dojo.cookie.isSupported()){
if(!_4d){
_4e=-1;
}
var _4f={path:this._basePath.template};
if(_4e!==undefined){
_4f.expires=_4e;
}
dojo.cookie(_4c,_4d,_4f);
}
},buildUri:function(uri,_50){
return this._basePath.append(uri,_50);
},fromQueryString:function(str){
var obj={};
if(!str){
return obj;
}
dojo.forEach(str.split("&"),function(_51){
var pos=_51.indexOf("=");
if(pos!=-1){
obj[decodeURIComponent(_51.substring(0,pos))]=decodeURIComponent(_51.substring(pos+1));
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
},updateHash:function(_52,_53){
var obj=this.fromQueryString(dojo.hash());
if(_53===null){
delete obj[_52];
}else{
obj[_52]=_53;
}
dojo.hash(this.toQueryString(obj));
},onHashChange:function(){
var obj=this.fromQueryString(dojo.hash()),_54=this.hashMode();
if(_54=="trace"&&obj.trace&&obj.trace!==this._active.trace){
this._loadTrace(obj.trace);
}else{
if(_54=="resource"&&obj.resource&&obj.resource!==this._active.resource){
this._loadResource(obj.resource);
}
}
if(obj.range&&obj.range!==this.getTimeRange().toString()){
this.setTimeRange(new insight.time.TimeRange(obj.range));
}
},_initTimeZoneOffset:function(){
var _55=new Date().getTimezoneOffset()/60*-1;
this.setCookie("Insight.timeZoneOffset",_55);
},destroyWidget:function(id){
var _56=dijit.byId(id);
if(_56){
_56.set("style",{display:"none"});
_56.destroyRecursive(true);
}
},_parseBasePath:function(_57){
if(_57.match(";jsessionid")){
_57=_57.substring(0,_57.indexOf(";jsessionid"));
}
if(_57.match("/$")){
_57=_57.substring(0,_57.length-1);
}
return new spring.UrlBuilder(_57);
},_browserWarnings:[{name:"cookies",func:function(_58){
return !dojo.cookie.isSupported();
},title:null,message:"Cookie support is required to use Spring Insight.  Please enable cookies or upgrade your browser.",supported:true,suppressible:false,upgradePanel:true,suppressionTimeout:null},{name:"ie6",func:function(_59){
return dojo.isIE<=6;
},title:null,message:"IE 6 (and eariler) is incompatible with Spring Insight.  Please upgrade your browser.<br /><br /><a href=\"http://www.google.com/chromeframe\">Google Chrome Frame</a> is strongly recommended if upgrading your browser is not otherwise possible.",supported:false,suppressible:false,upgradePanel:true,suppressionTimeout:null},{name:"ie",func:function(_5a){
return dojo.isIE<9&&!_5a;
},title:null,message:"IE 7 and 8 will work with Spring Insight.  However, the performance is to be blunt, slow.<br /><br /><a href=\"http://www.google.com/chromeframe\">Google Chrome Frame</a> is strongly recommended if upgrading your browser is not otherwise possible.",supported:true,suppressible:true,upgradePanel:true,suppressionTimeout:90},{name:"oldFirefox",func:function(_5b){
return dojo.isFF&&dojo.isFF<3.6&&!_5b;
},title:null,message:"Firefox is a great browser.  However, your using an older version.  The latest release is significantly faster.",supported:true,suppressible:true,upgradePanel:true,suppressionTimeout:30},{name:"oldSafari",func:function(_5c){
return dojo.isSafari&&dojo.isSafari<5&&!_5c;
},title:null,message:"Safari is a great browser.  However, your using an older version.  The latest release is significantly faster.",supported:true,suppressible:true,upgradePanel:true,suppressionTimeout:30},{name:"oldChrome",func:function(_5d){
return dojo.isChrome&&dojo.isChrome<5&&!_5d;
},title:null,message:"Chrome is a great browser.  However, your using an older version.  The latest release is significantly faster.",supported:true,suppressible:true,upgradePanel:true,suppressionTimeout:30},{name:"unknowBroswer",func:function(_5e){
return !dojo.isFF&&!dojo.isSafari&&!dojo.isChrome&&!dojo.isIE&&!_5e;
},title:null,message:"You're using a browser we havn't tested.  It should work, but if you run into strange issues, try a recomended browser.  Be sure to let us know about your experience.",supported:true,suppressible:true,upgradePanel:true,suppressionTimeout:30},{name:"firebug",func:function(_5f){
return console&&console.firebug;
},title:null,message:"Firebug is known to cause severe performance degradation over time.  We highly recommend you disable Firebug for the Spring Insight dashboard.",supported:true,suppressible:true,upgradePanel:false,suppressionTimeout:null},{name:"chrome-speedtracer",func:function(_60){
return dojo.isChrome&&!window.ChromeFrame;
},title:"Spring Insight + Google SpeedTracer",message:function(){
return "<a href='http://code.google.com/webtoolkit/speedtracer/' target='_blank'><img src='"+this.buildUri("/static/images/browser_logo_speedtracer.png")+"' style='float: left; padding: 0 0.5em;' /></a><p><a href='http://code.google.com/webtoolkit/speedtracer/' target='_blank'>SpeedTracer</a> is a Google Chrome extension that analyzes how your application performs inside the browser. SpringSource and Google partnered to integrate Spring Insight data into SpeedTracer, thereby creating the ultimate client-server application performance tool.</p><p>If your web application uses Ajax or other rich open web technologies, we recommend you <a href='http://code.google.com/webtoolkit/speedtracer/get-started.html' target='_blank'>try SpeedTracer</a>.</p>";
},supported:true,suppressible:true,upgradePanel:false,suppressionTimeout:90}],checkBrowserAbilities:function(){
var _61=this.getCookie("com.springsource.sts.run.embedded"),_62=false,_63;
for(var i=0;!_62&&i<this._browserWarnings.length;i++){
_63=this._browserWarnings[i];
if(_63.func.call(this,_61)){
if(!_63.supported){
this._browserSupported=false;
}
if(!_63.suppressible||(_63.suppressible&&!this.getCookie("Insight.browserwarning."+_63.name))){
this._browserWarning=_63;
this._warnBrowserAbility(_63.name,_63.title,dojo.isFunction(_63.message)?_63.message.call(this):_63.message,_63.suppressible,_63.upgradePanel);
_62=true;
}
}
}
},_warnBrowserAbility:function(_64,_65,_66,_67,_68){
var _69=dojo.byId("noscript");
if(!_69){
return;
}
if(_65){
dojo.byId("noscript-title").innerHTML=_65;
}
dojo.byId("noscript-warning").innerHTML=_66;
if(_67){
if(_67!==true){
dojo.byId("noscript-ignore").innerHTML=_67;
}
dojo.style("noscript-ignore","display","block");
}
if(!_68){
dojo.style("noscript-upgrade","display","none");
}
dojo.style(_69,"display","block");
},suppressWarning:function(){
this.setCookie("Insight.browserwarning."+this._browserWarning.name,"ignore",this._browserWarning.suppressionTimeout);
dojo.style("noscript","display","none");
}});
dojo.require("dojox.charting.plot2d._PlotEvents");
dojo.extend(dojox.charting.plot2d._PlotEvents,{_reconnectEvents:function(_6a){
var a=this._eventSeries[_6a];
if(a){
dojo.forEach(a,function(o){
if(o){
this._connectEvents(o);
}
},this);
}
}});
}
