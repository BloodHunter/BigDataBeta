<%--
  Created by IntelliJ IDEA.
  User: simple_love
  Date: 2016/4/19
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<%@include file="/WEB-INF/views/include/topNav.jsp"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<head>
        <title>数据详细信息</title>
</head>
<body>
<input type="hidden" value="${dataName}" id="dataName">
<input type="hidden" value="${queryUrl}" id="queryUrl">
<div>

        <!-- Nav tabs -->
        <ul class="nav nav-tabs" role="tablist" id="tabs">
                <li role="presentation" class="active"><a href="#dataInfo" aria-controls="dataInfo" role="tab" data-toggle="tab">基本信息</a></li>
                <li role="presentation"><a href="#details" aria-controls="details" role="tab" data-toggle="tab">数据详情</a></li>
                <li role="presentation"><a href="#analyse" aria-controls="analyse" role="tab" data-toggle="tab">数据分析</a></li>
                <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">数据推荐</a></li>
        </ul>

        <!-- Tab panes -->
        <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="dataInfo">
                        <table class="table table-hover" id="showDataInfo">
                                <%--<tr>
                                        <td>数据名称</td>
                                        <td>studentBookLend</td>
                                </tr>
                                <tr>
                                        <td>数据类型</td>
                                        <td>表格</td>
                                </tr>
                                <tr>
                                        <td>数据描述</td>
                                        <td>学生图书借阅详细信息</td>
                                </tr>--%>
                        </table>
                </div>
                <div role="tabpanel" class="tab-pane" id="details">
                        <div style="margin: 10px;float: left">
                                <label>操作类型</label><br>
                                <select id="operate_type" class="select">
                                        <option value="ALL">全部</option>
                                        <option value="DOWNLOAD">下载</option>
                                        <option value="UPLOAD">上传</option>
                                        <option value="IMPORT">导入</option>
                                        <option value="EXPORT">导出</option>
                                        <option value="AGGREGATION">合成</option>
                                </select>
                        </div>
                        <div style="margin-left: 10%;margin-right: 10%">
                                <table class="table table-hover" id="detail">
                                        <thead>
                                        <tr>
                                                <th>DataId</th>
                                                <th>Agent</th>
                                                <th>Activity</th>
                                                <th>Time</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                               <%-- <tr>
                                                        <th>platA-1</th>
                                                        <th>adminB</th>
                                                        <th>upload</th>
                                                        <th>2016-08-26 14:09:00</th>
                                                </tr>
                                                <tr>
                                                        <th>platA-2</th>
                                                        <th>adminB</th>
                                                        <th>upload</th>
                                                        <th>2016-08-26 14:09:00</th>
                                                </tr>
                                                <tr>
                                                        <th>platB-1</th>
                                                        <th>userB-1</th>
                                                        <th>aggregation</th>
                                                        <th>2016-08-26 15:09:00</th>
                                                </tr>
                                                <tr>
                                                        <th>platB-1</th>
                                                        <th>userB-1</th>
                                                        <th>aggregation</th>
                                                        <th>2016-08-26 15:09:00</th>
                                                </tr>
                                                <tr>
                                                        <th>platB-1</th>
                                                        <th>adminC</th>
                                                        <th>download</th>
                                                        <th>2016-08-26 16:09:00</th>
                                                </tr>
                                                <tr>
                                                        <th>platB-1</th>
                                                        <th>adminD</th>
                                                        <th>download</th>
                                                        <th>2016-08-27 17:09:00</th>
                                                </tr>--%>
                                        </tbody>
                                </table>
                                <div id="page"></div>
                        </div>
                </div>
                <div role="tabpanel" class="tab-pane" id="analyse">
                        <div class="container-fluid">
                                <div class="row" style="padding-top: 20px">
                                        <div class="col-md-2">
                                                <ul id="accordion" class="accordion">
                                                        <li>
                                                                <div class="link">
                                                                        <span class="glyphicon glyphicon-time"></span> 数据活跃度
                                                                        <span class="glyphicon glyphicon-chevron-down"></span>
                                                                </div>
                                                                <ul class="submenu">
                                                                        <li><span id="activityInDay">数据日活跃度</span></li>
                                                                        <li><span id="activityInMonth">数据月活跃度</span></li>
                                                                        <li><span id="activityInYear">数据年活跃度</span></li>
                                                                </ul>
                                                        </li>
                                                        <li>
                                                                <div class="link">
                                                                        <span class="glyphicon glyphicon-bookmark"></span>
                                                                        <span id="operateAnalyse">操作类型分析</span>
                                                                </div>
                                                                <%--<ul class="submenu">
                                                                        <li><a href="#">Javascript</a></li>
                                                                        <li><a href="#">jQuery</a></li>
                                                                        <li><a href="#">Frameworks javascript</a></li>
                                                                </ul>--%>
                                                        </li>
                                                        <li>
                                                                <div class="link">
                                                                        <span class="glyphicon glyphicon-user" ></span>
                                                                        <span id="userTypeAnalyse">用户类型分析</span>
                                                                </div>
                                                                <%--<ul class="submenu">
                                                                        <li><a href="#">Tablets</a></li>
                                                                        <li><a href="#">Dispositivos mobiles</a></li>
                                                                        <li><a href="#">Medios de escritorio</a></li>
                                                                        <li><a href="#">Otros dispositivos</a></li>
                                                                </ul>--%>
                                                        </li>
                                                </ul>
                                        </div>
                                        <div class="col-md-8">
                                                <div id="timePick" style="display: none">
                                                        <div id="yearArea" style="float: left">
                                                                <select name="year" id="year" style="margin-left: 50px;">
                                                                        <option value="2016">2016</option>
                                                                        <option value="2015">2015</option>
                                                                </select>
                                                                <span>年</span>
                                                        </div>
                                                        <div id="monthArea" style="float: left">
                                                                <select name="month" id="month">
                                                                        <option value="01">1</option>
                                                                        <option value="02">2</option>
                                                                        <option value="03">3</option>
                                                                        <option value="04">4</option>
                                                                        <option value="05">5</option>
                                                                        <option value="06">6</option>
                                                                        <option value="07">7</option>
                                                                        <option value="08">8</option>
                                                                        <option value="09">9</option>
                                                                        <option value="10">10</option>
                                                                        <option value="11">11</option>
                                                                        <option value="12">12</option>
                                                                </select>
                                                                <span>月</span>
                                                        </div>
                                                        <button id="submit"  class="btn btn-primary"><span>确定</span></button>
                                                </div>

                                                <div class="col-md-8">
                                                        <div id="showTable">

                                                        </div>
                                                </div>
                                        </div>
                                        <div class="col-md-1"></div>
                                </div>
                        </div>
                </div>
                <div role="tabpanel" class="tab-pane" id="messages">
                        <div class="container">
                                <div class="row" id="likeData">
                                        <h3><span class="glyphicon glyphicon-star"></span> 相似数据</h3>
                                        <%--<div class="data_space">
                                                <h3><a>student_info</a></h3>
                                        </div>
                                        <div class="data_space">
                                                <h3><a>bookLend_info</a></h3>
                                        </div>--%>
                                </div>
                                <div class="row" id="userRecommend">
                                        <h3><span class="glyphicon glyphicon-heart"></span> 用户推荐</h3>
                                        <%--<div class="data_space">
                                                <h3><a>student_info</a></h3>
                                        </div>--%>
                                </div>
                                <div class="row" id="hotData">
                                        <h3><span class="glyphicon glyphicon-fire"></span> 热门数据</h3>
                                </div>
                        </div>
                </div>
        </div>

</div>

</body>
<%@include file="/WEB-INF/views/include/footer.jsp"%>
<script type="text/javascript" src="resources/js/reference/laypage.js"></script>
<script type="text/javascript" src="resources/js/reference/d3.js"></script>
<script type="text/javascript" src="resources/js/MeunBar.js"></script>
<script type="text/javascript">
        $(function(){
                var date = new Date();
                var dayAttr = {extend:[0,31],x:"日",y:"次/天",content:"日期",rep:"day",
                        func:function(d){return new Date(d.name).getDate()}};
                var monthAttr = {extend:[0,12],x:"月",y:"次/月",content:"月份",rep:"month",
                        func: function (d) {return new Date(d.name).getMonth()+1}};
                var yearAttr = {extend:[date.getFullYear()-10,date.getFullYear()],x:"年",y:"次/年",content:"年份",rep:"year",
                        func:function(d){return new Date(d.name).getFullYear()}};
                $("#year").val(date.getFullYear());
                $("#month").get(0).selectedIndex = date.getMonth();
                var dataName = $("#dataName").val();
                var queryUrl = $("#queryUrl").val();

                (function page(curr){
                        $.getJSON(queryUrl,{
                                dataName:dataName,
                                currentPage:curr || 1
                        },function(response){
                                if(response.RESULT == "SUCCESS"){
                                        resetTable(curr,response);
                                }
                        })
                })();

                (function pageByOperate(curr){
                        $("#operate_type").bind("change",function(){
                                var activity = $(this).find("option:selected").val();
                                var url = queryUrl.substring(0,queryUrl.indexOf("getProvByPage"))+"getProvByActivity";
                                $.getJSON(url,{
                                        dataName:dataName,
                                        activity:activity,
                                        currentPage:curr||1
                                },function(response){
                                        if(response.RESULT == "SUCCESS"){
                                                resetTable(curr,response);
                                        }
                                })
                        })
                })();
                function formatTimestamp(timestamp){
                        var year = 1900 + timestamp.year;
                        var month = timestamp.month + 1;
                        var date = (timestamp.date < 10 ? "0" : "") + timestamp.date;
                        var hours = (timestamp.hours < 10 ? "0" : "") + timestamp.hours;
                        var minutes = (timestamp.minutes < 10 ? "0" : "") + timestamp.minutes;
                        var second = (timestamp.seconds < 10 ? "0" : "") + timestamp.seconds;
                        return year + "-" + month + "-" + date + " " + hours + ":" + minutes + ":" + second;
                }

                function resetTable(curr,response){
                        var table = $("#detail").find("tbody");
                        table.empty();
                        var page_result = response.Page_result;
                        for(var i = 0; i < page_result.length; i++){
                                var prov = page_result[i];
                                table.append("<tr><td>" + prov.entity + "</td><td>" + prov.agent + "</td><td>" +
                                        prov.activity + "</td><td>" + formatTimestamp(prov.time) + "</tr>");
                        }
                        laypage({
                                cont:"page",
                                pages:response.pages,
                                curr : curr || 1,
                                skin:"molv",
                                skip:true,
                                prev:'<',
                                next:'>',
                                jump:function(obj,first){
                                        if(!first){
                                                page(obj.curr);
                                        }
                                }
                        })
                }

                function drawPie(data,name){
                        var width =document.body.clientWidth*0.8, height =500;
                        var svg = d3.select("#showTable").append("svg").attr("width",width).attr("height",height);
                        var pie = d3.layout.pie().value(function(d){return d.count;});

                        //转换数据
                        var piedata = pie(data);
                        console.log(piedata)

                        //创建弧生成器
                        var outerRadius = 150;
                        var arc = d3.svg.arc().innerRadius(0).outerRadius(outerRadius);

                        var color = d3.scale.category20();
                        //添加对应数目的弧组
                        var arcs = svg.selectAll("g").data(piedata).enter().append("g")
                                .attr("transform","translate(" + (width / 2) + "," + (height /2) + ")");
                        arcs.append("path").attr("fill",function(d,i){
                                return color(i);        //设置弧的颜色
                        }).attr("d",function(d){
                                return arc(d);          //使用弧生成器获取路径
                        });

                        //添加弧内的文字元素
                        arcs.append("text").attr("transform",function(d){
                                var x = arc.centroid(d)[0] * 1.4;
                                var y = arc.centroid(d)[1] * 1.4;
                                return "translate(" + x + "," + y + ")";
                        }).attr("text-anchor","middle").text(function(d){
                                //计算百分比
                                var percent = Number(d.value) / d3.sum(data,function(d){return d.count;}) * 100;
                                //保留1位小数点
                                return percent.toFixed(1) + "%";
                        })

                        /*arcs.append("line").attr("stroke","black")
                         .attr("x1",function(d){ return arc.centroid(d)[0] * 2 ;})
                         .attr("y1",function(d){ return arc.centroid(d)[1] * 2 ;})
                         .attr("x2",function(d){ return arc.centroid(d)[0] * 2.2 ;})
                         .attr("y2",function(d){ return arc.centroid(d)[1] * 2.2 ;});

                         arcs.append("text").attr("transform",function(d){
                         var x = arc.centroid(d)[0] * 2.5;
                         var y = arc.centroid(d)[1] * 2.5;
                         return "translate(" + x + "," + y + ")";
                         }).attr("text-anchor","middle").text(function(d){
                         return d.data.name;
                         })*/

                        for(var i = 0; i < data.length;i++){
                                svg.append("rect")
                                        .attr("x",(i+1)*150)
                                        .attr("y",height-50)
                                        .attr("fill",color(i))
                                        .attr("width","15px")
                                        .attr("height","15px");
                                svg.append("text")
                                        .attr("x",(i+1)*150+20)
                                        .attr("y",height-40)
                                        .text(data[i].name)
                        }
                        var tooltip = d3.select("body").append("div").attr("class","tooltip-content");
                        arcs.on("mouseover",function(d){
                                tooltip.html("<span>" + name + ": " + d.data.name + "</span><br>" + "<span>次数: " + d.data.count +"</span>")
                                        .style("left",(d3.event.pageX) + "px")
                                        .style("top",(d3.event.pageY - 180) + "px")
                                        .style("opacity",1.0)
                                        .style("width",300)
                                        .style("height",100)
                        })
                                .on("mousemove",function(d){
                                        tooltip.style("left",(d3.event.pageX) + "px")
                                                .style("top",(d3.event.pageY -150) + "px")
                                })
                                .on("mouseout",function(d){
                                        tooltip.style("opacity",0.0)
                                })
                }

                function drawLineChart(data,attr){
                        var margin = {top: 20, right: 20, bottom: 30, left: 50},
                                width = document.body.clientWidth*0.8 - margin.left - margin.right,
                                height = 500 - margin.top - margin.bottom;

                        $("#showTable").empty();
                        var container = d3.select("#showTable")
                                .append('svg')
                                .attr('width', width + margin.left + margin.right)
                                .attr('height', height + margin.top + margin.bottom);

                        var svg = container.append('g')
                                .attr('class', 'content')
                                .attr('transform', 'translate(' + margin.left + ',' + margin.top + ')');

                        var x = d3.scale.linear()
                                .domain(attr.extend)
                                .range([0, width]);

                        var y = d3.scale.linear()
                                .domain([0, d3.max(data, function(d) { return d.count; })])
                                .range([height, 0]);
                        var xAxis = d3.svg.axis()
                                .scale(x)
                                .orient('bottom')
                                .ticks(attr.extend[1]-attr.extend[0]-1)
                                .tickFormat(d3.format("d"));

                        var yAxis = d3.svg.axis()
                                .scale(y)
                                .orient('left')
                                .ticks(10);
                        // 横坐标
                        svg.append('g')
                                .attr('class', 'x axis')
                                .attr('transform', 'translate(0,' + height + ')')
                                .call(xAxis)
                                // 增加坐标值说明
                                .append('text')
                                .text(attr.x)
                                .attr('transform', 'translate(' + width + ', 0)');

                        // 纵坐标
                        svg.append('g')
                                .attr('class', 'y axis')
                                .call(yAxis)
                                .append('text')
                                .text(attr.y);

                        var line = d3.svg.line()
                                .x(function(d) {
                                        return x(attr.func(d))
                                })
                                .y(function(d) { return y(d.count); })
                                .interpolate('monotone');
                        var path = svg.append('path')
                                .attr('class', 'line')
                                .attr('d', line(data))
                                .attr("fill","none")
                                .attr("stroke-width",1)
                                .attr("stroke","steelblue");

                        var g = svg.selectAll('circle')
                                .data(data)
                                .enter()
                                .append('g')
                                .append('circle')
                                .attr("fill","#00EC00")
                                .attr('cx', line.x())
                                .attr('cy', line.y())
                                .attr('r', 3.5)
                                .on('mouseover', function() {
                                        d3.select(this).transition().duration(500).attr('r', 5);
                                })
                                .on('mouseout', function() {
                                        d3.select(this).transition().duration(500).attr('r', 3.5);
                                })
                                .on('mousemove', function() {
                                        var m = d3.mouse(this),
                                                cx = m[0] - margin.left;
                                        var i = 0;
                                        if(attr.rep == "day" )
                                                i = Math.ceil(x.invert(cx) + 1);
                                        if(attr.rep == "month")
                                                i = Math.floor(x.invert(cx) + 1);
                                        if(attr.rep == "year")
                                                i = Math.ceil(x.invert(cx))-2006;
                                        var d = data[i-1];
                                        function formatWording(d) {
                                                return attr.content + " :" + attr.func(d) + attr.x;
                                        }
                                        wording1.text(formatWording(d));
                                        wording2.text('次数：' + d.count);

                                        var y1 = y(d.count),x1 = x(attr.func(d));

                                        // 处理超出边界的情况
                                        var dx = x1 > width ? x1 - width + 200 : x1 + 200 > width ? 200 : 0;

                                        var dy = y1 > height ? y1 - height + 50 : y1 + 50 > height ? 50 : 0;

                                        x1 -= dx;
                                        y1 -= dy;

                                        d3.select('.tips')
                                                .attr('transform', 'translate(' + x1 + ',' + y1 + ')');

                                        d3.select('.tips').style('display', 'block');
                                })
                                .on('mouseout', function() {
                                        d3.select('.tips').style('display', 'none');
                                });

                        var tips = svg.append('g').attr('class', 'tips').style("display",'none');

                        tips.append('rect')
                                .attr("fill","#80FFFF")
                                .attr('width', 200)
                                .attr('height', 50)
                                .attr('rx', 10)
                                .attr('ry', 10);

                        var wording1 = tips.append('text')
                                .attr('class', 'tips-text')
                                .attr('x', 10)
                                .attr('y', 20)
                                .text('');

                        var wording2 = tips.append('text')
                                .attr('class', 'tips-text')
                                .attr('x', 10)
                                .attr('y', 40)
                                .text('');


                }

                (function dayActivity(){
                        $("#activityInDay").click(function(){
                                $("#showTable").empty();
                                $("#timePick").show();
                                $("#yearArea").show();
                                $("#monthArea").show();
                                $("#submit").click(function(){
                                        /*var year = $("#year").val();
                                         var month = $("#month").val();
                                         var url = queryUrl.substr(0,queryUrl.indexOf("prov"));
                                         $.ajax({
                                         url:url+"analyse/getDataOperateTimesInDay",
                                         type:"post",
                                         dataType:"json",
                                         data:{
                                         "dataName":dataName,
                                         "month":month,
                                         "year":year
                                         },
                                         success:function(response){
                                         drawLineChart(response,dayPart,dayAttr);
                                         $(this).tab('show');
                                         }
                                         });*/
                                        var data = Array.apply(0, Array(31)).map(function(item, i) {
                                                // 产生31条数据
                                                i++;
                                                return {name: '2016-12-' + (i < 10 ? '0' + i : i), count: parseInt(Math.random() * 100)}
                                        });
                                        drawLineChart(data,dayAttr)
                                })
                        })
                })();

                (function monthActivity(){
                        $("#activityInMonth").click(function(){
                                $("#showTable").empty();
                                $("#timePick").show();
                                $("#yearArea").show();
                                $("#monthArea").hide();
                                $("#submit").click(function(){
                                        var data = Array.apply(0, Array(12)).map(function(item, i) {
                                                // 产生31条数据
                                                i++;
                                                return {name: '2016-' + (i < 10 ? '0' + i : i), count: parseInt(Math.random() * 100)}
                                        });
                                        drawLineChart(data,monthAttr);
                                })
                        })
                })();

                (function yearActivity(){
                        $("#activityInYear").click(function(){
                                $("#timePick").hide();
                                $("#showTable").empty();
                                var data = Array.apply(0, Array(10)).map(function(item, i) {
                                        // 产生31条数据
                                        i++;
                                        return {name: (2006+i).toString(), count: parseInt(Math.random() * 100)}
                                });
                                drawLineChart(data,yearAttr)
                        })
                })();

                (function operateType(){
                        $("#operateAnalyse").click(function(){
                                $("#timePick").hide();
                                $("#showTable").empty();
                                var data = [{name:"upload",count:50},{name:"download",count:500},{name:"aggregation",count:200},
                                        {name:"import",count:100}];
                                drawPie(data,"操作类型");
                        })
                })();

                (function userType() {
                        $("#userTypeAnalyse").click(function () {
                                $("timePick").hide();
                                $("#showTable").empty();
                                var data = [{name:"自然科学",count:10},{name:"信息科学",count:20},{name:"工程技术",count:30},{name:"生物医药",count:40},
                                        {name:"统计年鉴数据",count:50}];
                                drawPie(data,"用户类型")
                        })
                })();

                (function getHotData(){
                        $.ajax({
                                url:"analyse/getHotData",
                                type:"post",
                                success:function(response){
                                        var datas = response.data;
                                        for(var i = 0; i<datas.length;i++){
                                                $("#hotData").append("<div class='data_space'><h3><a>" + datas[i].name.split(".")[0] + "</a></h3>" +
                                                        "<h4>使用次数：" + datas[i].count + "</h4></div>");
                                        }
                                }
                        })
                })();

                (function getLikeData(){
                        $.ajax({
                                url:"analyse/getLikeData",
                                data:{
                                        "dataName":"book_lend"
                                },
                                success:function(response){
                                        var datas = response.data;
                                        for(var i = 0; i<datas.length;i++){
                                                $("#likeData").append("<div class='data_space'><h3><a>" + datas[i].name + "</a></h3></div>");
                                        }
                                }
                        })
                })();

                (function getUserRecommendData(){
                        $.ajax({
                                url:"analyse/getUserRecommendData",
                                success:function(response){
                                        var datas = response.data;
                                        for(var i = 0; i<datas.length;i++){
                                                $("#userRecommend").append("<div class='data_space'><h3><a>" + datas[i] + "</a></h3></div>");
                                        }
                                }
                        })
                })();

                (function getDataInfo(){
                        var url = queryUrl.substring(0,queryUrl.indexOf("prov"))+"prov/getDataInfo";
                        $.ajax({
                                url:url,
                                data:{
                                        dataName:dataName
                                },
                                success:function(response){
                                        var dataInfo = response.dataInfo;
                                        var table = $("#showDataInfo").empty();
                                        table.append("<tr><td>数据名称</td><td>"+ dataInfo.dataName.split("\\.")[0] + "</td></tr>" +
                                                "<tr><td>数据类型</td><td>" + dataInfo.type + "</td></tr>" +
                                                "<tr><td>数据描述</td><td>" + dataInfo.description + "</td></tr>")
                                }
                        })
                })();
        })
</script>
</html>
