/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.components.ErrorDialog"]){
dojo._hasResource["insight.components.ErrorDialog"]=true;
dojo.provide("insight.components.ErrorDialog");
dojo.require("dojo.date.locale");
dojo.require("dijit.TitlePane");
dojo.require("insight.components.PageModule");
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
},addError:function(_1,_2,_3,_4){
var _5=this._emptyList?"only":"first",_6=dojo.date.locale.format(new Date(),{formatLength:"medium"});
this._emptyList=false;
dojo.create("tr",{innerHTML:"<td class='nowrap'>"+_6+"</td><td class='nowrap'>"+_2+"</td><td><span class='collapse-container'><span class='collapse-display'>"+_1+"</span><span class='collapse-spacing'>"+dojo.string.pad("-",_1.length*1.25,"- ")+"</span></span></td>"},this.errorListDomNode,_5);
if(!_3||this._errorLog.indexOf(_3)==-1&&!_4){
this._errorLog.push(_3);
}
if(this._errorLog.length>=this.pauseThreshold&&Insight.playing()&&!this._pausedNode){
Insight.pause();
this._pausedDueToErrors=true;
this._pausedNode=dojo.create("p",{className:"warning",innerHTML:"Data playback paused due to excessive errors.  Close error dialog to resume playback."},this.errorList.domNode,"before");
dojo.publish("insight/error/pause");
}
}});
}
