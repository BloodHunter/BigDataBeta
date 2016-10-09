<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="WEB-INF/views/include/topNav.jsp"%>
<%@include file="WEB-INF/views/include/header.jsp"%>
<body>

<div class="container" >
        <div class="row">
                <div class="col-md-3">
                        <div class="todo" style="margin-top: 30px">
                                <ul>
                                        <li style="background: #00bdef;font-size: 2rem;font-weight: bold;color: #333">
                                                <div class="todo-content" >
                                                        <span class="glyphicon glyphicon-th-list"></span>
                                                        所有数据分类
                                                </div>
                                        </li>
                                        <li>
                                                <div class="todo-content">
                                                        <span></span>
                                                        <a href="data/category?category=10">自然科学</a>
                                                </div>
                                        </li>
                                        <li>
                                                <div class="todo-content">
                                                        <a href="data/category?category=60">信息科学</a>
                                                </div>
                                        </li>
                                        <li>
                                                <div class="todo-content">
                                                        <a href="data/category?category=65">工程技术</a>
                                                </div>
                                        </li>
                                        <li>
                                                <div class="todo-content">
                                                        <a href="data/category?category=70">生物医药</a>
                                                </div>
                                        </li>
                                        <li>
                                                <div class="todo-content">
                                                        <a href="data/category?category=75">农业科学</a>
                                                </div>
                                        </li>
                                        <li>
                                                <div class="todo-content">
                                                        <a href="data/category?category=80">人文与社科</a>
                                                </div>
                                        </li>
                                        <li>
                                                <div class="todo-content">
                                                        <a href="data/category?category=90">统计年鉴数据</a>
                                                </div>
                                        </li>
                                </ul>
                        </div>
                </div>
                <div class="col-md-9" style="margin-top: 30px">
                        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                                <!-- Indicators -->
                                <ol class="carousel-indicators">
                                        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                                        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                                        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                                </ol>

                                <!-- Wrapper for slides -->
                                <div class="carousel-inner" role="listbox">
                                        <div class="item active">
                                                <img src="resources/img/ad1.png" alt="...">
                                                <%--<div class="carousel-caption">
                                                        ...
                                                </div>--%>
                                        </div>
                                        <div class="item">
                                                <img src="resources/img/ad2.png" alt="...">
                                                <%--<div class="carousel-caption">
                                                        ...
                                                </div>--%>
                                        </div>
                                        <div class="item">
                                                <img src="resources/img/ad3.png" alt="...">
                                                <%--<div class="carousel-caption">
                                                        ...
                                                </div>--%>
                                        </div>
                                </div>

                                <!-- Controls -->
                                <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                                        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                                        <span class="sr-only">Previous</span>
                                </a>
                                <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                                        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                                        <span class="sr-only">Next</span>
                                </a>
                        </div>
                        <div class="row" id="hotData">
                                <h3><span class="glyphicon glyphicon-fire"></span>热门数据</h3>
                        </div>
                </div>
        </div>

</div>



</body>
<%@include file="WEB-INF/views/include/footer.jsp"%>
<script>
        $(function(){
                $.ajax({
                        url:"analyse/getHotData",
                        type:"post",
                        success:function(response){
                                var datas = response.data;
                                for(var i = 0; i<datas.length;i++){
                                        $("#hotData").append("<div class='data_space'><h3><a>" + datas[i].name.split(".")[0]+ "</a></h3>" +
                                        "<h4>使用次数：" + datas[i].count + "</h4></div>");
                                }
                        }
                })
        })
</script>
</html>
