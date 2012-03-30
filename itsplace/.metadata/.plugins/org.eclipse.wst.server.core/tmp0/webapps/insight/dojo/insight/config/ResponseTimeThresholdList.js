/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.config.ResponseTimeThresholdList"]){
dojo._hasResource["insight.config.ResponseTimeThresholdList"]=true;
dojo.provide("insight.config.ResponseTimeThresholdList");
dojo.require("dijit.form.Button");
dojo.require("dijit.form.ValidationTextBox");
dojo.require("insight.components.SortableTable");
dojo.require("insight.components.UnitAwareNumberSpinner");
dojo.require("insight.runtime");
dojo.declare("insight.config.ResponseTimeThresholdList",insight.components.SortableTable,{_activeRule:null,_buttons:null,_cancelButton:null,timeout:insight.runtime.getXhrTimeout("ResponseTimeThresholdList"),endpoint:null,postCreate:function(){
this.updateUrlParams({endpoint:this.endpoint});
this.inherited(arguments);
this.connect(this,"onReplace",this._enhanceButtons);
this.connect(this,"onReplace",function(){
this._activeRule=null;
});
},newRule:function(){
if(this._activeRule){
return;
}
this._disableButtons();
var _1=dojo.create("tbody",{className:"thresholdRule"});
var _2=dojo.query(".precedence",this.domNode)[0];
this._activeRule={node:dojo.create("tr"),precedence:this.getPrecedence(_2)+1,patternNode:dojo.create("td"),thresholdNode:dojo.create("td")};
dojo.place(this._activeRule.patternNode,this._activeRule.node);
dojo.place(this._activeRule.thresholdNode,this._activeRule.node);
var _3=dojo.create("td");
_3.innerHTML="n/a";
dojo.place(_3,this._activeRule.node);
var _4=dojo.create("span");
var _5=dojo.create("td",{className:"buttonHolder"});
dojo.place(_4,_5);
new dijit.form.Button({iconClass:"editIcon",showLabel:false,label:"Save",onClick:dojo.hitch(this,this._saveRule,this._activeRule)},_4);
dojo.place(_5,this._activeRule.node);
dojo.place(this._activeRule.node,_1);
dojo.place(_1,_2.parentNode,"before");
this._activeRule.patternWidget=this._buildPatternWidget(this._activeRule.patternNode,"");
this._activeRule.thresholdWidget=this._buildThresholdWidget(this._activeRule.thresholdNode,"200 ms");
this._activeRule.patternWidget.focus();
},editRule:function(_6,_7){
if(this._activeRule){
if(this._activeRule.precedence!=_6){
return;
}
return this._saveRule(this._activeRule);
}
this._disableButtons();
var _8=dojo.query(".precedence-"+_6,this.domNode)[0];
this._activeRule={node:_8,precedence:_6,patternNode:dojo.query(".rulePattern",_8)[0],thresholdNode:dojo.query(".ruleThreshold",_8)[0]};
if(_6!=0){
this._activeRule.patternWidget=this._buildPatternWidget(this._activeRule.patternNode);
}
this._activeRule.thresholdWidget=this._buildThresholdWidget(this._activeRule.thresholdNode);
_7.attr("label","Save");
_7.attr("disabled",false);
if(_6!=0){
this._activeRule.patternWidget.focus();
}else{
this._activeRule.thresholdWidget.focus();
}
},_saveRule:function(_9){
if((_9.patternWidget&&!(_9.patternWidget.isValid()&&_9.thresholdWidget.isValid()))||(!_9.patternWidget&&!_9.thresholdWidget.isValid())){
return;
}
dojo.xhrPost({handleAs:"html",load:dojo.hitch(this,this.refresh),error:dojo.hitch(this,this._error),timeout:this.timeout,url:this.url.append("/{precedence}",{precedence:_9.precedence,pattern:_9.patternWidget?_9.patternWidget.attr("value"):_9.patternNode.innerHTML,threshold:_9.thresholdWidget.attr("value"),"_method":"put"}).build(this._urlParams)});
},promoteRule:function(_a){
dojo.xhrPost({handleAs:"html",load:dojo.hitch(this,this.refresh),error:dojo.hitch(this,this._error),timeout:this.timeout,url:this.url.append("/{precedence}/promote",{precedence:_a,"_method":"put"}).build(this._urlParams)});
},demoteRule:function(_b){
dojo.xhrPost({handleAs:"html",load:dojo.hitch(this,this.refresh),error:dojo.hitch(this,this._error),timeout:this.timeout,url:this.url.append("/{precedence}/demote",{precedence:_b,"_method":"put"}).build(this._urlParams)});
},deleteRule:function(_c){
dojo.xhrPost({handleAs:"html",load:dojo.hitch(this,this.refresh),error:dojo.hitch(this,this._error),timeout:this.timeout,url:this.url.append("/{precedence}",{precedence:_c,"_method":"delete"}).build(this._urlParams)});
},persistRules:function(){
dojo.xhrPost({handleAs:"html",load:dojo.hitch(this,this.refresh),error:dojo.hitch(this,this._error),timeout:this.timeout,url:this.url.build(this._urlParams),content:{"_method":"put"}});
},revertRules:function(){
dojo.xhrPost({handleAs:"html",load:dojo.hitch(this,this.refresh),error:dojo.hitch(this,this._error),timeout:this.timeout,url:this.url.build(this._urlParams),content:{"_method":"delete"}});
},getPrecedence:function(_d){
var _e;
dojo.forEach(_d.className.split(" "),function(_f){
if(_f.indexOf("precedence-")==0){
_e=_f.substring(11);
}
},this);
return parseInt(_e);
},_enhanceButtons:function(){
this._buttons=[];
dojo.query(".cancelButton",this.domNode).forEach(function(_10){
dojo.style(_10.parentNode,"display","none");
this._cancelButton=new dijit.form.Button({label:"Cancel",onClick:dojo.hitch(this,this.load)},_10);
},this);
dojo.query(".newRuleButton",this.domNode).forEach(function(_11){
this._buttons.push(new dijit.form.Button({label:"New",onClick:dojo.hitch(this,this.newRule)},_11));
},this);
dojo.query(".persistRulesButton",this.domNode).forEach(function(_12){
this._buttons.push(new dijit.form.Button({label:"Make Permanent",onClick:dojo.hitch(this,this.persistRules)},_12));
},this);
dojo.query(".revertRulesButton",this.domNode).forEach(function(_13){
new dijit.form.Button({label:"Revert Changes",onClick:dojo.hitch(this,this.revertRules)},_13);
},this);
dojo.query(".precedence",this.domNode).forEach(function(_14){
var _15=this.getPrecedence(_14);
dojo.query(".editRuleButton",_14).forEach(function(_16){
var _17=new dijit.form.Button({iconClass:"editIcon",showLabel:false,label:"Edit"},_16);
this.connect(_17,"onClick",dojo.hitch(this,this.editRule,_15,_17));
this._buttons.push(_17);
},this);
dojo.query(".promoteRuleButton",_14).forEach(function(_18){
this._buttons.push(new dijit.form.Button({iconClass:"promoteIcon",showLabel:false,label:"Promote",onClick:dojo.hitch(this,this.promoteRule,_15)},_18));
},this);
dojo.query(".demoteRuleButton",_14).forEach(function(_19){
this._buttons.push(new dijit.form.Button({iconClass:"demoteIcon",showLabel:false,label:"Demote",onClick:dojo.hitch(this,this.demoteRule,_15)},_19));
},this);
dojo.query(".deleteRuleButton",_14).forEach(function(_1a){
this._buttons.push(new dijit.form.Button({iconClass:"deleteIcon",showLabel:false,label:"Delete",onClick:dojo.hitch(this,this.deleteRule,_15)},_1a));
},this);
},this);
},_disableButtons:function(){
dojo.forEach(this._buttons,function(_1b){
_1b.attr("disabled",true);
},this);
dojo.style(this._cancelButton.domNode.parentNode,"display","");
},_buildPatternWidget:function(_1c,_1d){
var div=dojo.create("div");
_1d=_1d||_1c.innerHTML;
dojo.place(div,_1c,"only");
var _1e=new dijit.form.ValidationTextBox({value:_1d,required:true,intermediateChanges:false,invalidMessage:"A valid Regular Expression is required",validator:function(_1f){
if(_1f==""){
return false;
}
try{
new RegExp(_1f);
}
catch(e){
return false;
}
return true;
}},div);
_1e.startup();
return _1e;
},_buildThresholdWidget:function(_20,_21){
var div=dojo.create("div");
_21=_21||_20.innerHTML;
dojo.place(div,_20,"only");
var _22=new insight.components.UnitAwareNumberSpinner({unit:"ms",value:_21,required:true,rangeMessage:"Threshold must be a positive number of milliseconds",constraints:{min:1},smallDelta:50},div);
_22.startup();
return _22;
},refresh:function(){
this.load();
}});
}
