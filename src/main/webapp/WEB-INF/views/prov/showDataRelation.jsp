<%--
  Created by IntelliJ IDEA.
  User: simple_love
  Date: 2016/4/19
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<%@include file="/WEB-INF/views/include/topNav.jsp"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<head>
        <title>数据供应关系展示</title>
</head>
<body>
        <input type="hidden" id="url" value="${url}">
        <input type="hidden" id="queryId" value="${queryId}">
        <div class="container">
                <div class="row">
                        <span class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-placement="top" title="shift +  ↑/↓: 向上(向下)移动图片
                        shift + 滚轮 ：对图片进行放大缩小">温馨提示</span>
                        <div class="btn-group">
                                <button id="zoom-in" type="button" class="btn btn-lg btn-default" data-toggle="tooltip" data-placement="top" title="放大">
                                        <span class="glyphicon glyphicon-zoom-in"></span>
                                </button>
                                <button id="zoom-out" type="button" class="btn btn-lg btn-default" data-toggle="tooltip" data-placement="top" title="缩小">
                                        <span class="glyphicon glyphicon-zoom-out" ></span>
                                </button>
                                <button  id="move-left" type="button" class="btn btn-lg btn-default" data-toggle="tooltip" data-placement="top"  title="左移">
                                        <span class="glyphicon glyphicon-arrow-left"></span>
                                </button>
                                <button id="move-right" type="button" class="btn btn-lg btn-default" data-toggle="tooltip" data-placement="top" title="右移">
                                        <span  class="glyphicon glyphicon-arrow-right"></span>
                                </button>
                                <button id="move-up" type="button" class="btn btn-lg btn-default" data-toggle="tooltip" data-placement="top" title="上移">
                                        <span class="glyphicon glyphicon-arrow-up"></span>
                                </button>
                                <button id="move-down" type="button" class="btn btn-lg btn-default" data-toggle="tooltip" data-placement="top" title="下移">
                                        <span class="glyphicon glyphicon-arrow-down"></span>
                                </button>
                        </div>
                </div>
                <div class="row">
                        <div id="show_svg">

                        </div>
                </div>
        </div>
</body>
<%@include file="/WEB-INF/views/include/footer.jsp"%>
<script type="text/javascript" src="resources/js/reference/jquery.graphviz.svg.js"></script>
<script type="text/javascript">
        $(function(){
                var bath = $("base").attr("href");
                $('[data-toggle="tooltip"]').tooltip();
                var url = $("#url").val();
                var queryUrl = url.substr(0,url.lastIndexOf("/")) + "/getProvByPage";
                var queryId = $("#queryId").val();
                $("#show_svg").graphviz({
                        url: url+"?queryId=" +queryId,
                        /*url:"resources/provImage/test.svg",*/
                        ready:function(){
                                $('text').attr("fill","black");
                                //$("ellipse").attr("fill","#2ecc71").attr("stroke","none");
                                var svg = $('#show_svg').find('svg');
                                var viewBoxValues = svg[0].getAttribute("viewBox").split(" ");
                                var operate = new Operate(svg);
                                $("#zoom-in").click(function(){
                                        operate.zoomIn();
                                        //zoomIn(svg,viewBoxValues)
                                });
                                $("#zoom-out").click(function(){
                                        zoomOut(svg,viewBoxValues)
                                });
                                $("#move-left").click(function(){
                                        moveLeft(svg,viewBoxValues);
                                });
                                $("#move-right").click(function(){
                                        moveRight(svg,viewBoxValues);
                                });
                                $("#move-up").click(function(){
                                        moveUp(svg,viewBoxValues);
                                });
                                $("#move-down").click(function(){
                                        moveDown(svg,viewBoxValues);
                                });
                                $(".node").css("cursor","pointer").click(function(){
                                        var dataName = $(this).attr("data-name");
                                        window.location.href = bath + "prov/showDataInfo?queryUrl=" + queryUrl +"&dataName=" + dataName;
                                })
                        }
                })
        })
        function zoomIn(svg,viewBoxValues){
                var zoomRate = 1.1;
                viewBoxValues[2] = parseFloat(viewBoxValues[2]);
                viewBoxValues[3] = parseFloat(viewBoxValues[3]);
                viewBoxValues[2] = viewBoxValues[2]/zoomRate;
                viewBoxValues[3] = viewBoxValues[2]/zoomRate;
                svg[0].setAttribute("viewBox",viewBoxValues.join(" "));
        }
        function zoomOut(svg,viewBoxValues){
                var zoomRate = 1.1;
                viewBoxValues[2] = parseFloat(viewBoxValues[2]);
                viewBoxValues[3] = parseFloat(viewBoxValues[3]);
                viewBoxValues[2] = viewBoxValues[2]*zoomRate;
                viewBoxValues[3] = viewBoxValues[2]*zoomRate;
                svg[0].setAttribute("viewBox",viewBoxValues.join(" "));
        }
        function moveLeft(svg,viewBoxValues){
                var panRate = 10.00;
                viewBoxValues[0] = parseFloat(viewBoxValues[0]);
                viewBoxValues[0] += panRate;
                svg[0].setAttribute("viewBox",viewBoxValues.join(" "));
        }
        function moveRight(svg,viewBoxValues){
                var panRate = 10.00;
                viewBoxValues[0] = parseFloat(viewBoxValues[0]);
                viewBoxValues[0] -= panRate;
                svg[0].setAttribute("viewBox",viewBoxValues.join(" "));
        }

        function moveUp(svg,viewBoxValues){
                var panRate = 10.00;
                viewBoxValues[1] = parseFloat(viewBoxValues[1]);
                viewBoxValues[1] += panRate;
                svg[0].setAttribute("viewBox",viewBoxValues.join(" "));
        }

        function moveDown(svg,viewBoxValues){
                var panRate = 10.00;
                viewBoxValues[1] = parseFloat(viewBoxValues[1]);
                viewBoxValues[1] -= panRate;
                svg[0].setAttribute("viewBox",viewBoxValues.join(" "));
        }

        function Operate(svg){
                this.svg = svg;
                this.viewBoxValues = svg[0].getAttribute("viewBox").split(" ");
                Operate.prototype.zoomIn = function(){
                        var zoomRate = 1.1;
                        this.viewBoxValues[2] = parseFloat(this.viewBoxValues[2]);
                        this.viewBoxValues[3] = parseFloat(this.viewBoxValues[3]);
                        this.viewBoxValues[2] = this.viewBoxValues[2]/zoomRate;
                        this.viewBoxValues[3] = this.viewBoxValues[2]/zoomRate;
                        this.svg[0].setAttribute("viewBox",this.viewBoxValues.join(" "));
                }
        }
        /*Operate.prototype.zoomIn(){
                var zoomRate = 1.1;
                this.viewBoxValues[2] = parseFloat(this.viewBoxValues[2]);
                this.viewBoxValues[3] = parseFloat(this.viewBoxValues[3]);
                this.viewBoxValues[2] = this.viewBoxValues[2]/zoomRate;
                this.viewBoxValues[3] = this.viewBoxValues[2]/zoomRate;
                this.svg[0].setAttribute("viewBox",this.viewBoxValues.join(" "));
        }*/
</script>
</html>
