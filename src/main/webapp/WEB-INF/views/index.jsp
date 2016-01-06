<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<style>
        body{
                overflow: hidden;
        }
        svg{
                display: block;
                margin: auto;
        }
</style>
<script type="text/javascript">
        var htmlObj,SVGDoc, SVGRoot, viewBox, goLeft, goRight, innerSVG;
        var currentSize, currentPosition, currentRoomId, currentRoomLabel;
        var leftArrow = 37;
        var upArrow = 38;
        var rightArrow = 39;
        var downArrow = 40;
        var zoomRate = 1.1;
        var panRate = 200.00;
        //viewBox = "0.00 0.00 0.00 200.00";
        function Initialize(){
                window.addEventListener('mousewheel',zoomViaMouseWheel,false);
                window.addEventListener('keydown',processKeyPress,true);
                htmlObj = document.getElementById("svg");
                SVGDoc = htmlObj.getSVGDocument();
                SVGRoot = SVGDoc.documentElement;
                //SVGRoot.setAttribute("viewBox",viewBox);
                //SVGRoot.firstElementChild.setAttribute("transform","translate(0 200)");
                var node= SVGRoot.firstElementChild.querySelectorAll('.node');
                for(var i = 0; i < node.length; i++){
                        console.log($(node[i].firstChild).text());
                        $(node[i]).click(function(){
                                alert($(this.firstChild).text());
                        });
                        //node[i].childNodes[2].firstChild.setAttribute('xlink:href','#');
                        //console.log();
                        var polygon = $(node[i].childNodes[2].firstChild).children('polygon');
                        if(polygon.attr('stroke') == "#0000ff"){
                                polygon.attr('fill',"red");
                        }else{
                                polygon.attr('fill',"black");
                        }
                        //$(node[i].childNodes[2].firstChild).find('polygon').context.firstChild.nextSibling.setAttribute('fill',"black")
                }
                console.log(SVGRoot);

        }
        function panDoc(){
                viewBox = SVGRoot.getAttribute('viewBox');
                var viewVals = viewBox.split(' ');
                currentPosition = parseFloat(viewVals[0]);
                if (goLeft == true){
                        if (currentPosition >-1152){
                                currentPosition =currentPosition - 200;
                        }
                        goLeft= false;
                }
                if(goRight== true){
                        if (currentPosition < 100 * 1152) {
                                currentPosition =currentPosition + 200;
                        }
                        goRight = false;
                }
                viewVals[0]= currentPosition;
                SVGRoot.setAttribute('viewBox',viewVals.join(' '));
        }

        function processKeyPress(evt){
                viewBox = SVGRoot.getAttribute('viewBox');
                var viewBoxValues = viewBox.split(' ');
                viewBoxValues[0] = parseFloat(viewBoxValues[0]);
                viewBoxValues[1] = parseFloat(viewBoxValues[1]);
                switch (evt.keyCode){
                        case  leftArrow:
                                viewBoxValues[0] += panRate;
                                break;
                        case  rightArrow:
                                viewBoxValues[0] -= panRate;
                                break;
                        case upArrow:
                                viewBoxValues[1] += panRate;
                                break;
                        case downArrow:
                                viewBoxValues[1] -=panRate;
                                break;
                }
                SVGRoot.setAttribute('viewBox',viewBoxValues.join(' '));
        }

        function zoom(zoomType){
                viewBox = SVGRoot.getAttribute('viewBox');
                var viewBoxValues = viewBox.split(' ');
                viewBoxValues[2] = parseFloat(viewBoxValues[2]);
                viewBoxValues[3] = parseFloat(viewBoxValues[3]);
                if(zoomType == 'zoomIn'){
                        viewBoxValues[2]  /= zoomRate;
                        viewBoxValues[3]  /= zoomRate;
                }else if(zoomType == 'zoomOut'){
                        viewBoxValues[2]  *= zoomRate;
                        viewBoxValues[3]  *= zoomRate;
                }

                SVGRoot.setAttribute("viewBox",viewBoxValues.join(' '));
        }

        function zoomViaMouseWheel(mouseWheelEvent){
                if(mouseWheelEvent.wheelDelta >0)
                        zoom('zoomIn');
                else
                        zoom('zoomOut');
                mouseWheelEvent.cancelBubble = true;
                return false;
        }
</script>
<body >
        <%--<h2>Hello World!</h2>--%>
        <div id="contentDiv">
                <div class="container">
                        <div class="row">
                                <div class="col-md-4">
                                        <div class="container">
                                                <div class="row">
                                                        <div class="col-sm-6 col-md-4">
                                                                <div class="todo">
                                                                        <div class="todo-search">
                                                                                <input class="todo-search-field" type="text" placeholder="Search">
                                                                                <span class="glyphicon glyphicon-search" id="search" aria-hidden="true" onclick="search()"></span>
                                                                        </div>
                                                                        <ul>
                                                                                <li onclick="searchForUser()">
                                                                                        <span class="glyphicon glyphicon-fire"></span>
                                                                                        <div class="todo-content">
                                                                                                <h4 class="todo-name">dfsdfasdf</h4>
                                                                                        </div>
                                                                                </li>

                                                                                <li onclick="searchForOperate()">
                                                                                        <span class="glyphicon glyphicon-fire"></span>
                                                                                        <div class="todo-content">
                                                                                                <h4 class="todo-name">dfsdfasdf</h4>
                                                                                        </div>
                                                                                </li>
                                                                        </ul>
                                                                </div>
                                                        </div>
                                                </div>
                                        </div>
                                </div>
                                <div class="col-md-8">
                                <div class="container prov" id="provImageShow" style="display: none">
                                        <div class="row">
                                                <img id="provImage" src="" alt="查询失败">
                                        </div>
                                        <div class="row">
                                                <div class="form-inline">
                                                        <div class="page-group">
                                                                <div class="input-group-addon">第</div>
                                                                <input name="currentPage" type="text" value="1" readonly="readonly" class="page">
                                                                <div class="input-group-addon">页</div>
                                                        </div>
                                                        <div class="page-group">
                                                                <div class="input-group-addon">共</div>
                                                                <input name="totalPage" type="text"  readonly="readonly" class="page">
                                                                <div class="input-group-addon">页</div>
                                                        </div>
                                                        <div class="btn-group" style="margin-left: 9%;margin-right: 9%">
                                                                <button class="btn btn-info" value="first">首页</button>
                                                                <button class="btn btn-info" value="previous">上一页</button>
                                                                <button class="btn btn-info" value="next">下一页</button>
                                                                <button class="btn btn-info" value="last">尾页</button>
                                                        </div>
                                                        <div class="input-group" style="width: 11%">
                                                                <div class="input-group-addon">第</div>
                                                                <input type="text" class="form-control" name="pageNum">
                                                                <div class="input-group-addon">页</div>
                                                        </div>
                                                        <button class="btn btn-primary" value="goto">跳转</button>
                                                </div>
                                        </div>
                                </div>
                                <div class="container prov" id="provDetailShow" style="display: none">
                                        <div class="row">
                                                <table class="table"></table>
                                        </div>
                                        <div class="row">
                                                <div class="form-inline">
                                                        <div class="page-group">
                                                                <div class="input-group-addon">第</div>
                                                                <input name="currentPage" type="text" value="1" readonly="readonly" class="page">
                                                                <div class="input-group-addon">页</div>
                                                        </div>
                                                        <div class="page-group">
                                                                <div class="input-group-addon">共</div>
                                                                <input name="totalPage" type="text"  readonly="readonly" class="page">
                                                                <div class="input-group-addon">页</div>
                                                        </div>
                                                        <div class="btn-group" style="margin-left: 10%;margin-right: 10%">
                                                                <button class="btn btn-info" value="first">首页</button>
                                                                <button class="btn btn-info" value="previous">上一页</button>
                                                                <button class="btn btn-info" value="next">下一页</button>
                                                                <button class="btn btn-info" value="last">尾页</button>
                                                        </div>
                                                        <div class="input-group" style="width: 11%">
                                                                <div class="input-group-addon">第</div>
                                                                <input type="text" class="form-control" name="pageNum">
                                                                <div class="input-group-addon">页</div>
                                                        </div>
                                                        <button class="btn btn-primary" value="goto">跳转</button>
                                                </div>
                                        </div>
                                </div>
                        </div>
                        </div>
                        <div class="row">
                                <embed  id="svg" src="resources/provImage/test1.svg" width="1000" height="500"
                                       type="image/svg+xml"
                                       pluginspage="http://www.adobe.com/svg/viewer/install/"
                                        onload="Initialize()"/>
                                <button class="btn btn-primary" onclick="goLeft=true;panDoc()">left</button>
                                <button class="btn btn-primary" onclick="goRight=true;panDoc()">right</button>
                        </div>

                </div>
        </div>
</body>

<%@include file="/WEB-INF/views/include/footer.jsp"%>
<script type="text/javascript" src="resources/js/provSearch.js"></script>

</html>
