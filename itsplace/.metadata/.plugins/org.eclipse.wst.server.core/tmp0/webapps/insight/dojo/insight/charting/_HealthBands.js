/*
	Copyright (c) 2004-2010, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["insight.charting._HealthBands"]){
dojo._hasResource["insight.charting._HealthBands"]=true;
dojo.provide("insight.charting._HealthBands");
insight.charting._HealthBands.healthBands=[{from:null,to:0.5,color:[255,52,0,0.1],value:0.25,text:"Unacceptable"},{from:0.5,to:0.7,color:[255,135,0,0.1],value:0.6,text:"Poor"},{from:0.7,to:0.85,color:[234,211,0,0.1],value:0.775,text:"Fair"},{from:0.85,to:0.94,color:[89,210,0,0.1],value:0.895,text:"Good"},{from:0.94,to:null,color:[3,144,0,0.1],value:1.01,text:"Excellent"}];
}
