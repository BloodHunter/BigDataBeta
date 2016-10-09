<%--
  Created by IntelliJ IDEA.
  User: simple_love
  Date: 2016/1/12
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="include/header.jsp"%>
<%@ page isELIgnored="false" %>
<head>
        <title></title>
        <style>
                .node {
                        cursor: pointer;
                }

                .node circle {
                        fill: #fff;
                       /* stroke: steelblue;*/
                        stroke-width: 1px;
                }

                .node text {
                        font: 10px sans-serif;
                }

                .link {
                        fill: none;
                        stroke: #ccc;
                        stroke-width: 1.5px;
                }



                .tooltip {
                        display: inline;
                        position: relative;
                        z-index: 999;
                }

                /* Trigger text */



span{
        margin-right: 20px;
}



        </style>
</head>
<body>
        <%--<input id="json" type="hidden" value='${json}'>--%>
        <input id="queryId" type="hidden" value="${queryId}">
        <input id="url" type="hidden" value="${url}">

        <div id="show" style="width: 80%;height: 80%">

        </div>
</body>
<%@include file="include/footer.jsp"%>
<script type="text/javascript" src="resources/js/reference/d3.js"></script>
<script type="text/javascript" src="resources/js/reference/jquery.graphviz.svg.js"></script>
<script>
        /*$(function(){
                var base = $('base').attr("href");
                var margin = {top: 20, right: 120, bottom: 20, left: 120},
                        width = 960 - margin.right - margin.left,
                        height = 800 - margin.top - margin.bottom;

                var i = 0, duration = 750, root;

                var tree = d3.layout.tree()
                        .size([height, width]);

                var diagonal = d3.svg.diagonal()
                        .projection(function(d) { return [d.y, d.x]; });

                var svg = d3.select("body").append("svg")
                        .attr("width", width + margin.right + margin.left)
                        .attr("height", height + margin.top + margin.bottom)
                        .append("g")
                        .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

                var response = JSON.parse($("#json").val());
                if(response.RESULT != "ERROR"){
                        console.log(response.root)
                        root = response.root;
                        root.x0 = height / 2;
                        root.y0 = 0;

                        function collapse(d) {
                                if (d.children) {
                                        d._children = d.children;
                                        d._children.forEach(collapse);
                                        d.children = null;
                                }
                        }

                        root.children.forEach(collapse);
                        update(root);
                }

                /!*d3.json("resources/flare.json", function(error, flare) {
                        if (error) throw error;

                        root = flare;
                        root.x0 = height / 2;
                        root.y0 = 0;

                        function collapse(d) {
                                if (d.children) {
                                        d._children = d.children;
                                        d._children.forEach(collapse);
                                        d.children = null;
                                }
                        }

                        root.children.forEach(collapse);
                        update(root);
                });*!/

                d3.select(self.frameElement).style("height", "800px");

                function update(source) {

                        // Compute the new tree layout.
                        var nodes = tree.nodes(root).reverse(),
                                links = tree.links(nodes);

                        // Normalize for fixed-depth.
                        nodes.forEach(function(d) { d.y = d.depth * 180; });

                        // Update the nodes…
                        var node = svg.selectAll("g.node")
                                .data(nodes, function(d) { return d.id || (d.id = ++i); });

                        // Enter any new nodes at the parent's previous position.
                        var nodeEnter = node.enter().append("g")
                                .attr("class", "node")
                                .attr("transform", function(d) { return "translate(" + source.y0 + "," + source.x0 + ")"; })
                                .on("click", click);

                        nodeEnter.append("circle")
                                .attr("r", 1e-6)
                                .style("fill",function(d){return d._children ? "#FF9900" : "#09F709"});
                                /!*.style("fill", function(d) { return d._children ? "lightsteelblue" : "#fff"; });*!/

                        nodeEnter.append("text")
                                .attr("x", function(d) { return d.children || d._children ? -10 : 10; })
                                .attr("dy", ".35em")
                                .attr("text-anchor", function(d) { return d.children || d._children ? "end" : "start"; })
                                .text(function(d) { return d.name; })
                                .style("fill-opacity", 1e-6);

                        // Transition nodes to their new position.
                        var nodeUpdate = node.transition()
                                .duration(duration)
                                .attr("transform", function(d) { return "translate(" + d.y + "," + d.x + ")"; });

                        nodeUpdate.select("circle")
                                .attr("r", 10)
                                .style("fill",function(d){return d._children ? "#FF9900" : "#09F709"});
                                /!*.style("fill", function(d) { return d._children ? "lightsteelblue" : "#fff"; });*!/

                        nodeUpdate.select("text")
                                .style("fill-opacity", 1);

                        // Transition exiting nodes to the parent's new position.
                        var nodeExit = node.exit().transition()
                                .duration(duration)
                                .attr("transform", function(d) { return "translate(" + source.y + "," + source.x + ")"; })
                                .remove();

                        nodeExit.select("circle")
                                .attr("r", 1e-6);

                        nodeExit.select("text")
                                .style("fill-opacity", 1e-6);

                        // Update the links…
                        var link = svg.selectAll("path.link")
                                .data(links, function(d) { return d.target.id; });

                        // Enter any new links at the parent's previous position.
                        link.enter().insert("path", "g")
                                .attr("class", "link")
                                .attr("d", function(d) {
                                        var o = {x: source.x0, y: source.y0};
                                        return diagonal({source: o, target: o});
                                });

                        // Transition links to their new position.
                        link.transition()
                                .duration(duration)
                                .attr("d", diagonal);

                        // Transition exiting nodes to the parent's new position.
                        link.exit().transition()
                                .duration(duration)
                                .attr("d", function(d) {
                                        var o = {x: source.x, y: source.y};
                                        return diagonal({source: o, target: o});
                                })
                                .remove();

                        // Stash the old positions for transition.
                        nodes.forEach(function(d) {
                                d.x0 = d.x;
                                d.y0 = d.y;
                        });
                }

                // Toggle children on click.
                function click(d) {
                        if(d.name=="detail"){
                                window.location.href =base + "prov/showMoreDetail?queryUrl=111&dataName=" + d.parent.parent.name;
                        }
                        if (d.children) {
                                d._children = d.children;
                                d.children = null;
                        } else {
                                d.children = d._children;
                                d._children = null;
                        }
                        update(d);
                }
        })*/
        $(function(){
                var url = $("#url").val();
                var bath = $("base").attr("href");
                var queryUrl = url.substr(0,url.lastIndexOf("/")) + "/getProvByPage";
                var queryId = $("#queryId").val();
                $("#show").graphviz({
                        url: url + "?queryId=" + queryId + "?jsoncallback=?",
                        ready:function(){
                                d3.selectAll("text").attr("fill","black")
                                d3.selectAll("ellipse").attr("fill","steelblue").attr("stroke","none");
                                var tooltip = d3.select("body").append("div").attr("class","tooltip-content");
                                $.getJSON("prov/produceSvgJson?queryId=" + queryId,function(response){
                                        d3.selectAll(".node")
                                                .on("mouseover",function(d){
                                                        var name = $(this).attr("data-name");
                                                        tooltip.html("<span>Source</span><span>" + response[name].source  +
                                                                "</span><br><span>Times</span><span>" + response[name].times +
                                                                "</span><br><span>Time</span><span>" + formatTimestamp(response[name].time) + "</span><br>")
                                                                .style("left",(d3.event.pageX) + "px")
                                                                .style("top",(d3.event.pageY - 180) + "px")
                                                                .style("opacity",1.0);
                                                })
                                                .on("mousemove",function(d){
                                                        tooltip.style("left",(d3.event.pageX) + "px")
                                                                .style("top",(d3.event.pageY -150) + "px")
                                                })
                                                .on("mouseout",function(d){
                                                        tooltip.style("opacity",0.0)
                                                })
                                                .on("click",function(d){
                                                        var name = $(this).attr("data-name");
                                                        window.location.href = bath + "prov/showMoreDetail?queryUrl=" + queryUrl +"&dataName=" +name;
                                                })
                                })

                        }
                })
        })
        function formatTimestamp(timestamp){
                if(timestamp == "unknow")
                        return timestamp;
                var year = 1900 + timestamp.year;
                var month = timestamp.month + 1;
                var date = (timestamp.date < 10 ? "0" : "") + timestamp.date;
                var hours = (timestamp.hours < 10 ? "0" : "") + timestamp.hours;
                var minutes = (timestamp.minutes < 10 ? "0" : "") + timestamp.minutes;
                var second = (timestamp.seconds < 10 ? "0" : "") + timestamp.seconds;
                return year + "-" + month + "-" + date + " " + hours + ":" + minutes + ":" + second;
        }
</script>
</html>
