<%@ page session="false" contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="insight" uri="http://static.springsource.com/tags/insight" %>

<c:if test="${not empty timeZone}">
	<fmt:setTimeZone value="${timeZone}" />
</c:if>

<div>
	<div class="page-module">
		<div class="page-module-header">
			<h2>Applications</h2>
		</div>
		<div class="page-module-body">
			<div id="resource-tree-applications"><span class="example">Loading...</span></div>
		</div>
	</div>
	<c:if test="${not devEdition}">
		<div class="page-module">
			<div class="page-module-header">
				<h2>Servers</h2>
			</div>
			<div class="page-module-body">
				<div id="resource-tree-servers"><span class="example">Loading...</span></div>
			</div>
		</div>
	</c:if>
</div>

<script type="text/javascript">
	dojo.addOnLoad(function(){
		dojo.require("dijit.tree.TreeStoreModel");
		dojo.require("insight.resources.InsightResourceReadStore");
		dojo.require("insight.resources.ResourceKey");
		dojo.require("insight.resources.ResourceTree");

		var timeRange = Insight.getTimeRange();
		var serviceUrl = Insight.buildUri("/services/resources/{resourceKey}/{start}/{end}", { type: "json", sortField: "responseTimeScore", sortDesc: true, start: timeRange.getStart(), end: timeRange.getEnd() });
		var healthLampUrl = Insight.buildUri("/static/images/health/healthlamp-{lamp}.png", { lamp: "nosample" });

		var rootItems = [
			{ resourceKey: "insight:type=Application", resourceLabel: "All Applications", id: "resource-tree-applications" }
			<c:if test="${not devEdition}">
				, { resourceKey: "insight:type=Server", resourceLabel: "All Servers", id: "resource-tree-servers" }
			</c:if>
		];

		<c:if test="${not devEdition}">
			rootItems[0].transformer = function(key) {
				key = new insight.resources.ResourceKey(key).makeComponentlessKey("Server");
				return key ? key.toString() : "insight:type=Application";
			};
			rootItems[1].transformer = function(key) {
				key = new insight.resources.ResourceKey(key).makeComponentKey("Server");
				return key ? key.toString() : "insight:type=Server";
			};
		</c:if>

		dojo.forEach(rootItems, function(rootItem, i , a) {
			var store = new insight.resources.InsightResourceReadStore({
				url: serviceUrl,
				root: rootItem,
				updateTimeRange: function(timeRange) {
					this.url = this.url.append("", { start: timeRange.getStart(), end: timeRange.getEnd() });
				}
			});
			var model = new dijit.tree.TreeStoreModel({
				store: store,
				root: store.getItems()[0],
				deferItemLoadingUntilExpand: true
			});
			var tree = new insight.resources.ResourceTree({
				model: model,
				healthLampUrl: healthLampUrl
			}, rootItem.id);
			tree.connect(tree, "onClick", function(item, nodeWidget, e) {
				if(store.isItem(item) && !item.doNotLoad){
					// TODO account for the other tree
					var resourceKey = new insight.resources.ResourceKey(store.getIdentity(item));
					dojo.forEach(a, function(altRootItem){
						if (altRootItem !== rootItem) {
							var t = dijit.byId(altRootItem.id),
								s = t.model.store;
							resourceKey = resourceKey.merge(s.getIdentity(t.get("selectedItem")));
						}
					}, this);
					Insight.loadResource(resourceKey);
				}
			});
			tree.subscribe("Insight.timeRangeMajorPulse", function(timeRange) {
				// stagger the refresh
				if (this._refreshTimeout) {
					clearTimeout(this._refreshTimeout);
					this._refreshTimeout = null;
				}
				this.refreshTimeout = setTimeout(dojo.hitch(this, function() {
					this._refreshTimeout = null;
					store.updateTimeRange(timeRange);
					store.refresh();
				}), 2000);
			});
			tree.subscribe("Insight.highlightTreeResource", function(resourceKey) {
				if (rootItem.transformer) {
					resourceKey = rootItem.transformer(resourceKey);
				}
				tree.highlightResource(resourceKey);
			});
			tree.startup();
		});
		Insight.highlightResourceInTree();
	});
</script>
