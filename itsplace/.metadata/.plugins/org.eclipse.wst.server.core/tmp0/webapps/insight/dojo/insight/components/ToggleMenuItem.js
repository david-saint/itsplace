/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.components.ToggleMenuItem"]){
dojo._hasResource["insight.components.ToggleMenuItem"]=true;
dojo.provide("insight.components.ToggleMenuItem");
dojo.require("dijit.MenuItem");
dojo.declare("insight.components.ToggleMenuItem",dijit.MenuItem,{templateString:dojo.cache("insight.components","templates/ToggleMenuItem.html","<tr class=\"dijitReset dijitMenuItem\" dojoAttachPoint=\"focusNode\" waiRole=\"menuitemcheckbox\" tabIndex=\"-1\"\n\t\tdojoAttachEvent=\"onmouseenter:_onHover,onmouseleave:_onUnhover,ondijitclick:_onClick\">\n\t<td class=\"dijitReset dijitMenuItemIconCell\" waiRole=\"presentation\">\n\t\t<img src=\"${_blankGif}\" alt=\"\" class=\"dijitMenuItemIcon dijitCheckedMenuItemIcon\" dojoAttachPoint=\"iconNode\"/>\n\t\t<span class=\"dijitCheckedMenuItemIconChar\">&#10003;</span>\n\t</td>\n\t<td class=\"dijitReset dijitMenuItemLabel\" colspan=\"2\" dojoAttachPoint=\"containerNode,labelNode\"></td>\n\t<td class=\"dijitReset dijitMenuItemAccelKey\" style=\"display: none\" dojoAttachPoint=\"accelKeyNode\"></td>\n\t<td class=\"dijitReset dijitMenuArrowCell\" waiRole=\"presentation\">&nbsp;</td>\n</tr>\n"),value:"",toggleStates:["","+","-"],toggleCssClass:{"":"","+":"Plus","-":"Minus"},postCreate:function(){
this.inherited(arguments);
var i=dojo.indexOf(this.toggleStates,this.value);
this.set("value",this.toggleStates[i>=0?i:0]);
},_setValueAttr:function(_1){
dojo.forEach(this.toggleStates,function(_2){
var _3=this.toggleCssClass[_2];
if(_2===_1){
dojo.addClass(this.domNode,"insightToggleMenuItemChecked"+_3);
}else{
dojo.removeClass(this.domNode,"insightToggleMenuItemChecked"+_3);
}
},this);
this.value=_1;
},onChange:function(_4){
},_onClick:function(e){
if(!this.disabled){
var _5=(dojo.indexOf(this.toggleStates,this.value)+1)%this.toggleStates.length;
this.set("value",this.toggleStates[_5]);
this.onChange(this.value);
}
this.inherited(arguments);
}});
}
