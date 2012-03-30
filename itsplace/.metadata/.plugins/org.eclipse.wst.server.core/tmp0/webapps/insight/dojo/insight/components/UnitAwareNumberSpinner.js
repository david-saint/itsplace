/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.components.UnitAwareNumberSpinner"]){
dojo._hasResource["insight.components.UnitAwareNumberSpinner"]=true;
dojo.provide("insight.components.UnitAwareNumberSpinner");
dojo.require("dijit.form.NumberSpinner");
dojo.declare("insight.components.UnitAwareNumberSpinner",dijit.form.NumberSpinner,{unit:null,regExpGen:function(_1){
var re=dojo.number.regexp(_1);
if(this.unit){
re="(?:"+re+"(\\s"+this.unit+")?)";
}
return re;
},format:function(_2,_3){
if(typeof _2=="string"){
return _2;
}
if(isNaN(_2)){
return "";
}
if(this.editOptions&&this._focused){
_3=dojo.mixin(dojo.mixin({},this.editOptions),this.constraints);
}
var _4=this._formatter(_2,_3);
if(!_3.pattern&&_4&&_4!=""&&this.unit){
_4=_4+" "+this.unit;
}
return _4;
},_parser:dojo.number.parse,parse:function(_5,_6){
if(this.unit&&_5){
var _7=_5.lastIndexOf(" "+this.unit);
var _8=_5.length-(this.unit.length+1);
if(_7>0&&_7==_8){
_5=_5.substr(0,_8);
}
}
return this._parser(_5,_6);
}});
}
