<%@ page  pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<c:set var="title" value="도서목록"/>

<script type="text/javascript">
var debounce = function(func, wait, immediate) {
    var timeout;
    return function() {
      var context = this, args = arguments;
      var later = function() {
        timeout = null;
        if (!immediate) func.apply(context, args);
      };
      if (immediate && !timeout) func.apply(context, args);
      clearTimeout(timeout);
      timeout = setTimeout(later, wait);
    };
  };
$(document).ready(function() {
	

});

</script>
<style>
#header{
position: absolute;

width:100%; 
height: 67px;
background: #2D2D2D repeat 0 0;
z-index: 10;
-webkit-box-sizing: border-box;
-moz-box-sizing: border-box;
-ms-box-sizing: border-box;
box-sizing: border-box;
border-bottom: 1px solid #101010;
border-left: 1px solid rgba(255, 255, 255, 0.07);
}
.grid {
    width: 99%;
    height: 600PX;
    position: relative;
    overflow-x: hidden;
    overflow-y: scroll;
    background-color: #ddd;
    margin: 10px 0px;
    border: 10px solid #ddd;
    clear: both;
}

.grid > div {
    background-color: #fff;
    position: absolute;
}



/* template selections for sample #2 */

.dev-tile-number, .dev-tile-size {
    font-size: 36px;
    padding: 10px;
}

.dev-tiles-templates ul {
    margin-bottom: 10px;
}

.dev-template {
    margin-right: 20px;
    height: 35px;
    display: inline-block;
    background: url(dev-tiles-sprite.png) no-repeat;
    cursor: pointer;
}

.dev-template.selected {
    background-position-y: -200px;
}

.dev-l1 {
    width: 457px;
}

.dev-l2 {
    width: 47px;
    background-position-x: -68px;
}

.dev-l3 {
    width: 39px;
    background-position-x: -135px;
}

.dev-l4 {
    width: 39px;
    background-position-x: -194px;
}

.dev-l5 {
    width: 47px;
    background-position-x: -245px;
    margin-right: 0px;
}
</style>
<div>
	<!-- <div id="header">rrr</div> -->
	


    <div id="sample2-templates" class="dev-tiles-templates">
        <ul>
            <li id="sample2-t1" class="dev-l1 selected dev-template">555</li>
            <li class="dev-l2 dev-template">3333</li>
            <li class="dev-l3 dev-template">44</li>
            <li class="dev-l4 dev-template">55</li>
            <li class="dev-l5 dev-template">66</li>
        </ul>
    </div>
    <div id="sample2-grid" class="grid"></div>


 
</div>