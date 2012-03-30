/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.charting._Extensions"]){
dojo._hasResource["insight.charting._Extensions"]=true;
dojo.provide("insight.charting._Extensions");
dojo.require("dojox.charting.plot2d.common");
dojo.require("dojox.lang.functional");
dojo.require("dojox.lang.utils");
(function(){
var df=dojox.lang.functional,dc=dojox.charting,_1=df.lambda("item.clear()");
dojo.require("dojox.charting.Chart2D");
dojo.extend(dojox.charting.Chart2D,{delayedRender:function(_2){
if(!this._delayedRenderHandle){
this._delayedRenderHandle=setTimeout(dojo.hitch(this,this.render),this.delayInMs);
}else{
if(this._delayedRenderHandle&&_2){
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
df.forEachRev(this.stack,function(_3){
_3.render(this.dim,this.offsets);
},this);
df.forIn(this.axes,function(_4){
_4.render(this.dim,this.offsets);
},this);
this._makeClean();
if(this.surface.render){
this.surface.render();
}
return this;
},fullGeometry:function(){
this._makeDirty();
dojo.forEach(this.stack,_1);
if(!this.theme){
this.setTheme(new dojox.charting.Theme(dojox.charting._def));
}
dojo.forEach(this.series,function(_5){
if(!(_5.plot in this.plots)){
var _6=new dc.plot2d.Default(this,{});
_6.name=_5.plot;
this.plots[_5.plot]=this.stack.length;
this.stack.push(_6);
}
this.stack[this.plots[_5.plot]].addSeries(_5);
},this);
dojo.forEach(this.stack,function(_7){
if(_7.hAxis){
_7.setAxis(this.axes[_7.hAxis]);
}
if(_7.vAxis){
_7.setAxis(this.axes[_7.vAxis]);
}
},this);
var _8=this.dim=this.surface.getDimensions();
_8.width=dojox.gfx.normalizedLength(_8.width);
_8.height=dojox.gfx.normalizedLength(_8.height);
df.forIn(this.axes,_1);
_b(this.stack,_8);
var _9=this.offsets={l:0,r:0,t:0,b:0};
df.forIn(this.axes,function(_a){
df.forIn(_a.getOffsets(),function(o,i){
_9[i]=Math.max(_9[i],o);
});
});
df.forIn(this.margins,function(o,i){
_9[i]+=o;
});
this.plotArea={width:_8.width-_9.l-_9.r,height:_8.height-_9.t-_9.b};
df.forIn(this.axes,_1);
_b(this.stack,this.plotArea);
return this;
}});
function _c(_d){
return {min:_d.hmin,max:_d.hmax};
};
function _e(_f){
return {min:_f.vmin,max:_f.vmax};
};
function _10(_11,h){
_11.hmin=h.min;
_11.hmax=h.max;
};
function _12(_13,v){
_13.vmin=v.min;
_13.vmax=v.max;
};
function _14(_15,_16){
if(_15&&_16){
_15.min=Math.min(_15.min,_16.min);
_15.max=Math.max(_15.max,_16.max);
}
return _15||_16;
};
function _b(_17,_18){
var _19={},_1a={};
dojo.forEach(_17,function(_1b){
var _1c=_19[_1b.name]=_1b.getSeriesStats();
if(_1b.hAxis){
_1a[_1b.hAxis]=_14(_1a[_1b.hAxis],_c(_1c));
}
if(_1b.vAxis){
_1a[_1b.vAxis]=_14(_1a[_1b.vAxis],_e(_1c));
}
});
dojo.forEach(_17,function(_1d){
var _1e=_19[_1d.name];
if(_1d.hAxis){
_10(_1e,_1a[_1d.hAxis]);
}
if(_1d.vAxis){
_12(_1e,_1a[_1d.vAxis]);
}
_1d.initializeScalers(_18,_1e);
});
};
})();
(function(){
var dc=dojox.charting,du=dojox.lang.utils,g=dojox.gfx,lin=dc.scaler.linear,_1f=4,_20=45;
dojo.require("dojox.charting.axis2d.Default");
dojo.extend(dojox.charting.axis2d.Default,{getOffsets:function(){
var s=this.scaler,_21={l:0,r:0,t:0,b:0};
if(!s){
return _21;
}
var o=this.opt,_22=0,a,b,c,d,gl=dc.scaler.common.getNumericLabel,_23=0,ma=s.major,mi=s.minor,ta=this.chart.theme.axis,_24=o.font||(ta.majorTick&&ta.majorTick.font)||(ta.tick&&ta.tick.font),_25=this.chart.theme.getTick("major",o),_26=this.chart.theme.getTick("minor",o),_27=_24?g.normalizedLength(g.splitFontString(_24).size):0,_28=o.rotation%360,_29=o.leftBottom,_2a=Math.abs(Math.cos(_28*Math.PI/180)),_2b=Math.abs(Math.sin(_28*Math.PI/180));
if(_28<0){
_28+=360;
}
if(_27){
if(o.maxLabelSize){
_22=o.maxLabelSize;
}else{
if(this.labels){
_22=this._groupLabelWidth(this.labels,_24);
}else{
_22=this._groupLabelWidth([o.majorTicks!==false?gl(ma.start,ma.prec,o):"",o.majorTicks!==false?gl(ma.start+ma.count*ma.tick,ma.prec,o):"",o.minorTicks!==false?gl(mi.start,mi.prec,o):"",o.minorTicks!==false?gl(mi.start+mi.count*mi.tick,mi.prec,o):""],_24);
}
}
if(this.vertical){
var _2c=_29?"l":"r";
switch(_28){
case 0:
case 180:
_21[_2c]=_22;
_21.t=_21.b=_27/2;
break;
case 90:
case 270:
_21[_2c]=_27;
_21.t=_21.b=_22/2;
break;
default:
if(_28<=_20||(180<_28&&_28<=(180+_20))){
_21[_2c]=_27*_2b/2+_22*_2a;
_21[_29?"t":"b"]=_27*_2a/2+_22*_2b;
_21[_29?"b":"t"]=_27*_2a/2;
}else{
if(_28>(360-_20)||(180>_28&&_28>(180-_20))){
_21[_2c]=_27*_2b/2+_22*_2a;
_21[_29?"b":"t"]=_27*_2a/2+_22*_2b;
_21[_29?"t":"b"]=_27*_2a/2;
}else{
if(_28<90||(180<_28&&_28<270)){
_21[_2c]=_27*_2b+_22*_2a;
_21[_29?"t":"b"]=_27*_2a+_22*_2b;
}else{
_21[_2c]=_27*_2b+_22*_2a;
_21[_29?"b":"t"]=_27*_2a+_22*_2b;
}
}
}
break;
}
_21[_2c]+=_1f+Math.max(_25.length,_26.length);
}else{
var _2c=_29?"b":"t";
switch(_28){
case 0:
case 180:
_21[_2c]=_27;
_21.l=_21.r=_22/2;
break;
case 90:
case 270:
_21[_2c]=_22;
_21.l=_21.r=_27/2;
break;
default:
if((90-_20)<=_28&&_28<=90||(270-_20)<=_28&&_28<=270){
_21[_2c]=_27*_2b/2+_22*_2a;
_21[_29?"r":"l"]=_27*_2a/2+_22*_2b;
_21[_29?"l":"r"]=_27*_2a/2;
}else{
if(90<=_28&&_28<=(90+_20)||270<=_28&&_28<=(270+_20)){
_21[_2c]=_27*_2b/2+_22*_2a;
_21[_29?"l":"r"]=_27*_2a/2+_22*_2b;
_21[_29?"r":"l"]=_27*_2a/2;
}else{
if(_28<_20||(180<_28&&_28<(180-_20))){
_21[_2c]=_27*_2b+_22*_2a;
_21[_29?"r":"l"]=_27*_2a+_22*_2b;
}else{
_21[_2c]=_27*_2b+_22*_2a;
_21[_29?"l":"r"]=_27*_2a+_22*_2b;
}
}
}
break;
}
_21[_2c]+=_1f+Math.max(_25.length,_26.length);
}
}
if(_22){
this._cachedLabelWidth=_22;
}
return _21;
}});
})();
(function(){
var df=dojox.lang.functional,du=dojox.lang.utils,dc=dojox.charting.plot2d.common,_2d=df.lambda("item.purgeGroup()");
dojo.require("dojox.charting.plot2d.Default");
dojo.extend(dojox.charting.plot2d.Default,{render:function(dim,_2e){
if(this.zoom&&!this.isDataDirty()){
return this.performZoom(dim,_2e);
}
this.resetEvents();
this.dirty=this.isDirty();
if(this.dirty){
dojo.forEach(this.series,_2d);
this._eventSeries={};
this.cleanGroup();
this.group.setTransform(null);
var s=this.group;
df.forEachRev(this.series,function(_2f){
_2f.cleanGroup(s);
});
}
var t=this.chart.theme,_30,_31,_32,_33,_34=this.events();
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
var _35=t.next(this.opt.areas?"area":"line",[this.opt,run],true),s=run.group,_36=[],_37=[],_38=null,_39,_3a,_3b=null,_3c=null,ht=this._hScaler.scaler.getTransformerFromModel(this._hScaler),vt=this._vScaler.scaler.getTransformerFromModel(this._vScaler),_3d=this._eventSeries[run.name]=new Array(run.data.length);
for(var j=0;j<run.data.length;j++){
if(run.data[j]!=null){
if(!_38){
_38=[];
_37.push(j);
_36.push(_38);
}
_38.push(run.data[j]);
}else{
_38=null;
}
}
if(_36[0]&&typeof _36[0][0]=="number"){
_3a=function(v,i){
return {x:ht(i+_37[seg]+1)+_2e.l,y:dim.height-_2e.b-vt(v)};
};
}else{
_3a=function(v,i){
return {x:ht(v.x)+_2e.l,y:dim.height-_2e.b-vt(v.y)};
};
}
for(var seg=0;seg<_36.length;seg++){
_39=dojo.map(_36[seg],_3a,this);
if(seg!=0){
_3c=[_3b[_3b.length-1],_39[0]];
}
_3b=_39;
var _3e=this.opt.tension?dc.curve(_39,this.opt.tension):"";
if(this.opt.areas&&_39.length>1){
var _3f=_35.series.fill;
var _40=dojo.clone(_39);
if(this.opt.tension){
var _41="L"+_40[_40.length-1].x+","+(dim.height-_2e.b)+" L"+_40[0].x+","+(dim.height-_2e.b)+" L"+_40[0].x+","+_40[0].y;
run.dyn.fill=s.createPath(_3e+" "+_41).setFill(_3f).getFill();
}else{
_40.push({x:_39[_39.length-1].x,y:dim.height-_2e.b});
_40.push({x:_39[0].x,y:dim.height-_2e.b});
_40.push(_39[0]);
run.dyn.fill=s.createPolyline(_40).setFill(_3f).getFill();
}
}
if(this.opt.lines||this.opt.markers){
_30=_35.series.stroke;
_32=dojo.delegate({style:"dash"},_30);
if(_35.series.outline){
_31=run.dyn.outline=dc.makeStroke(_35.series.outline);
_31.width=2*_31.width+_30.width;
}
}
if(this.opt.markers){
run.dyn.marker=_35.symbol;
}
var _42=null,_43=null,_44=null;
if(_30&&_35.series.shadow&&_39.length>1){
var _45=_35.series.shadow,_46=dojo.map(_39,function(c){
return {x:c.x+_45.dx,y:c.y+_45.dy};
});
if(this.opt.lines){
if(this.opt.tension){
run.dyn.shadow=s.createPath(dc.curve(_46,this.opt.tension)).setStroke(_45).getStroke();
}else{
run.dyn.shadow=s.createPolyline(_46).setStroke(_45).getStroke();
}
}
if(this.opt.markers&&_35.marker.shadow){
_45=_35.marker.shadow;
_44=dojo.map(_46,function(c){
return s.createPath("M"+c.x+" "+c.y+" "+_35.symbol).setStroke(_45).setFill(_45.color);
},this);
}
}
if(this.opt.lines&&_39.length>1){
if(_31){
if(this.opt.tension){
run.dyn.outline=s.createPath(_3e).setStroke(_31).getStroke();
}else{
run.dyn.outline=s.createPolyline(_39).setStroke(_31).getStroke();
}
}
if(this.opt.tension){
run.dyn.stroke=s.createPath(_3e).setStroke(_30).getStroke();
}else{
run.dyn.stroke=s.createPolyline(_39).setStroke(_30).getStroke();
}
}
if(this.opt.lines&&_3c){
run.dyn.bstroke=s.createPolyline(_3c).setStroke(_32).getStroke();
}
if(this.opt.lines&&this.opt.markers&&_36.length===1&&_39.length==1){
var _47="M"+_39[0].x+" "+_39[0].y+" "+_35.symbol;
if(_35.marker.outline){
_31=dc.makeStroke(_35.marker.outline);
_31.width=2*_31.width+(_35.marker.stroke?_35.marker.stroke.width:0);
s.createPath(_47).setStroke(_31);
}
s.createPath(_47).setStroke(_30).setFill(_30.color);
}
if(this.opt.markers){
_42=new Array(_39.length);
_43=new Array(_39.length);
_31=null;
if(_35.marker.outline){
_31=dc.makeStroke(_35.marker.outline);
_31.width=2*_31.width+(_35.marker.stroke?_35.marker.stroke.width:0);
}
dojo.forEach(_39,function(c,i){
var _48="M"+c.x+" "+c.y+" "+_35.symbol;
if(_31){
_43[i]=s.createPath(_48).setStroke(_31);
}
_42[i]=s.createPath(_48).setStroke(_35.marker.stroke).setFill(_35.marker.fill);
},this);
if(_34){
dojo.forEach(_42,function(s,i){
var o={element:"marker",index:i+_37[seg],run:run,shape:s,outline:_43[i]||null,shadow:_44&&_44[i]||null,cx:_39[i].x,cy:_39[i].y};
if(typeof _36[seg][0]=="number"){
o.x=i+_37[seg]+1;
o.y=_36[seg][i];
}else{
o.x=_36[seg][i].x;
o.y=_36[seg][i].y;
}
this._connectEvents(o);
_3d[i+_37[seg]]=o;
},this);
}else{
delete this._eventSeries[run.name];
}
if(!run.dyn.stroke){
run.dyn.stroke=_30;
}
}
}
run.dirty=false;
}
if(this.animate){
var _49=this.group;
dojox.gfx.fx.animateTransform(dojo.delegate({shape:_49,duration:DEFAULT_ANIMATION_LENGTH,transform:[{name:"translate",start:[0,dim.height-_2e.b],end:[0,0]},{name:"scale",start:[1,0],end:[1,1]},{name:"original"}]},this.animate)).play();
}
this.dirty=false;
return this;
}});
dojo.require("dojox.charting.plot2d.Bars");
dojo.extend(dojox.charting.plot2d.Bars,{minSize:5,render:function(dim,_4a){
if(this.zoom&&!this.isDataDirty()){
return this.performZoom(dim,_4a);
}
this.dirty=this.isDirty();
this.resetEvents();
if(this.dirty){
dojo.forEach(this.series,_2d);
this._eventSeries={};
this.cleanGroup();
var s=this.group;
df.forEachRev(this.series,function(_4b){
_4b.cleanGroup(s);
});
}
var t=this.chart.theme,f,gap,_4c,ht=this._hScaler.scaler.getTransformerFromModel(this._hScaler),vt=this._vScaler.scaler.getTransformerFromModel(this._vScaler),_4d=Math.max(0,this._hScaler.bounds.lower),_4e=ht(_4d),_4f=this.events();
f=dc.calculateBarSize(this._vScaler.bounds.scale,this.opt);
gap=f.gap;
_4c=f.size;
for(var i=this.series.length-1;i>=0;--i){
var run=this.series[i];
if(!this.dirty&&!run.dirty){
t.skip();
this._reconnectEvents(run.name);
continue;
}
run.cleanGroup();
var _50=t.next("bar",[this.opt,run]),s=run.group,_51=new Array(run.data.length);
for(var j=0;j<run.data.length;++j){
var _52=run.data[j];
if(_52!==null){
var v=typeof _52=="number"?_52:_52.y,hv=ht(v),_53=hv-_4e,w=Math.abs(_53),_54=typeof _52!="number"?t.addMixin(_50,"bar",_52,true):t.post(_50,"bar");
if(w>0&&_4c>0){
if(w<this.minSize){
hv+=(this.minSize-w)*_53/Math.abs(_53);
_53=hv-_4e;
w=Math.abs(_53);
}
var _55={x:_4a.l+(v<_4d?hv:_4e),y:dim.height-_4a.b-vt(j+1.5)+gap,width:w,height:_4c};
var _56=this._plotFill(_54.series.fill,dim,_4a);
_56=this._shapeFill(_56,_55);
var _57=s.createRect(_55).setFill(_56).setStroke(_54.series.stroke);
this.overrideShape(_57,{index:j,value:v});
var _58={x:_55.x,y:_55.y,height:_55.height,width:dim.width-_4a.l-_4a.r};
var _59=s.createRect(_58).setFill(new dojo.Color([0,0,0,0])).setStroke(new dojo.Color([0,0,0,0]));
run.dyn.fill=_57.getFill();
run.dyn.stroke=_57.getStroke();
if(_4f){
var o={element:"bar",index:j,run:run,shape:_57,eventMask:_59,x:v,y:j+1.5};
this._connectEvents(o);
_51[j]=o;
}
if(this.animate){
this._animateBar(_57,_4a.l+_4e,-w);
}
}
}
}
this._eventSeries[run.name]=_51;
run.dirty=false;
}
this.dirty=false;
return this;
},overrideShape:function(_5a,_5b){
}});
dojo.require("dojox.charting.plot2d.Columns");
dojo.extend(dojox.charting.plot2d.Columns,{minSize:5,render:function(dim,_5c){
if(this.zoom&&!this.isDataDirty()){
return this.performZoom(dim,_5c);
}
this.resetEvents();
this.dirty=this.isDirty();
if(this.dirty){
dojo.forEach(this.series,_2d);
this._eventSeries={};
this.cleanGroup();
var s=this.group;
df.forEachRev(this.series,function(_5d){
_5d.cleanGroup(s);
});
}
var t=this.chart.theme,f,gap,_5e,ht=this._hScaler.scaler.getTransformerFromModel(this._hScaler),vt=this._vScaler.scaler.getTransformerFromModel(this._vScaler),_5f=Math.max(0,this._vScaler.bounds.lower),_60=vt(_5f),_61=this.events();
f=dc.calculateBarSize(this._hScaler.bounds.scale,this.opt);
gap=f.gap;
_5e=f.size;
for(var i=this.series.length-1;i>=0;--i){
var run=this.series[i];
if(!this.dirty&&!run.dirty){
t.skip();
this._reconnectEvents(run.name);
continue;
}
run.cleanGroup();
var _62=t.next("column",[this.opt,run]),s=run.group,_63=new Array(run.data.length);
for(var j=0;j<run.data.length;++j){
var _64=run.data[j];
if(_64!==null){
var v=typeof _64=="number"?_64:_64.y,vv=vt(v),_65=vv-_60,h=Math.abs(_65),_66=typeof _64!="number"?t.addMixin(_62,"column",_64,true):t.post(_62,"column");
if(_5e>0&&h>0){
if(h<this.minSize){
vv+=(this.minSize-h)*_65/Math.abs(_65);
_65=vv-_60;
h=Math.abs(_65);
}
var _67={x:_5c.l+ht(j+0.5)+gap,y:dim.height-_5c.b-(v>_5f?vv:_60),width:_5e,height:h};
var _68=this._plotFill(_66.series.fill,dim,_5c);
_68=this._shapeFill(_68,_67);
var _69=s.createRect(_67).setFill(_68).setStroke(_66.series.stroke);
this.overrideShape(_69,{index:j,value:v});
var _6a={x:_67.x,width:_67.width,y:_5c.t,height:dim.height-_5c.t-_5c.b};
var _6b=s.createRect(_6a).setFill(new dojo.Color([0,0,0,0])).setStroke(new dojo.Color([0,0,0,0]));
run.dyn.fill=_69.getFill();
run.dyn.stroke=_69.getStroke();
if(_61){
var o={element:"column",index:j,run:run,shape:_69,eventMask:_6b,x:j+0.5,y:v};
this._connectEvents(o);
_63[j]=o;
}
if(this.animate){
this._animateColumn(_69,dim.height-_5c.b-_60,h);
}
}
}
}
this._eventSeries[run.name]=_63;
run.dirty=false;
}
this.dirty=false;
return this;
},overrideShape:function(_6c,_6d){
}});
dojo.require("dojox.charting.plot2d.Grid");
dojo.extend(dojox.charting.plot2d.Grid,{render:function(dim,_6e){
if(this.zoom){
return this.performZoom(dim,_6e);
}
this.dirty=this.isDirty();
if(!this.dirty){
return this;
}
this.cleanGroup();
var s=this.group,ta=this.chart.theme.axis;
try{
var _6f=this._vAxis.getScaler(),vt=_6f.scaler.getTransformerFromModel(_6f),_70=this._vAxis.getTicks();
if(this.opt.hMinorLines){
dojo.forEach(_70.minor,function(_71){
var y=dim.height-_6e.b-vt(_71.value);
var _72=s.createLine({x1:_6e.l,y1:y,x2:dim.width-_6e.r,y2:y}).setStroke(ta.minorTickLine||ta.minorTick||ta.line);
if(this.animate){
this._animateGrid(_72,"h",_6e.l,_6e.r+_6e.l-dim.width);
}
},this);
}
if(this.opt.hMajorLines){
dojo.forEach(_70.major,function(_73){
var y=dim.height-_6e.b-vt(_73.value);
var _74=s.createLine({x1:_6e.l,y1:y,x2:dim.width-_6e.r,y2:y}).setStroke(ta.majorTickLine||ta.majorTick||ta.line);
if(this.animate){
this._animateGrid(_74,"h",_6e.l,_6e.r+_6e.l-dim.width);
}
},this);
}
}
catch(e){
}
try{
var _75=this._hAxis.getScaler(),ht=_75.scaler.getTransformerFromModel(_75),_70=this._hAxis.getTicks();
if(_70&&this.opt.vMinorLines){
dojo.forEach(_70.minor,function(_76){
var x=_6e.l+ht(_76.value);
var _77=s.createLine({x1:x,y1:_6e.t,x2:x,y2:dim.height-_6e.b}).setStroke(ta.minorTickLine||ta.minorTick||ta.line);
if(this.animate){
this._animateGrid(_77,"v",dim.height-_6e.b,dim.height-_6e.b-_6e.t);
}
},this);
}
if(_70&&this.opt.vMajorLines){
dojo.forEach(_70.major,function(_78){
var x=_6e.l+ht(_78.value);
var _79=s.createLine({x1:x,y1:_6e.t,x2:x,y2:dim.height-_6e.b}).setStroke(ta.majorTickLine||ta.majorTick||ta.line);
if(this.animate){
this._animateGrid(_79,"v",dim.height-_6e.b,dim.height-_6e.b-_6e.t);
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
