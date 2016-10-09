<%--
  Created by IntelliJ IDEA.
  User: simple_love
  Date: 2016/4/13
  Time: 9:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
        <title></title>
</head>
<%@include file="/WEB-INF/views/include/topNav.jsp"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<body>
<div class="container">
        <div class="row">
                <h3 style="padding-left: 15px"><a href="">所有数据</a>&gt;<a id="categoryHead" href="data/category?category=${category}"></a> </h3>
                <div class="col-md-3">
                        <div class="todo">
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
                <div class="my-col-md-9">
                        <div class="my-row row">
                                <div class="col-md-2 ">
                                        <h4>数据格式:</h4>
                                </div>
                                <div class="col-md-1">
                                        <label class="condition label_on" about="all">全部</label>
                                </div>
                                <div class="col-md-1">
                                        <label class="condition label_notOn" about="text">文本</label>
                                </div>
                                <div class="col-md-1">
                                        <label class="condition label_notOn" about="studio">语音</label>
                                </div>
                                <div class="col-md-1">
                                        <label class="condition label_notOn" about="image">图片</label>
                                </div>
                                <div class="col-md-1">
                                        <label class="condition label_notOn" about="video">视频</label>
                                </div>
                        </div>
                        <div class="my-row row">
                                <div class="col-md-2 ">
                                        <h4>数据大小:</h4>
                                </div>
                                <div class="col-md-1">
                                        <label class="condition label_on" about="0">全部</label>
                                </div>
                                <div class="col-md-2 my-col-md-2">
                                        <label class="condition label_notOn" about="1">100M以下</label>
                                </div>
                                <div class="col-md-2 my-col-md-2" >
                                        <label class="condition label_notOn" about="2">100M-500M</label>
                                </div>
                                <div class="col-md-2 my-col-md-2">
                                        <label class="condition label_notOn" about="3">500M-1G</label>
                                </div>
                                <div class="col-md-2 my-col-md-2">
                                        <label class="condition label_notOn" about="4">1G以上</label>
                                </div>
                        </div>
                        <div class="my-row row">
                                <div class="col-md-2 ">
                                        <h4>数据来源:</h4>
                                </div>
                                <div class="col-md-1">
                                        <label class="condition label_on" about="0">全部</label>
                                </div>
                                <div class="col-md-1">
                                        <label class="condition label_notOn" about="1">机构</label>
                                </div>
                                <div class="col-md-1">
                                        <label class="condition label_notOn" about="2">个人</label>
                                </div>
                        </div>
                        <div class="my-row row">
                                <div class="col-md-2">
                                        <h4>排序方式</h4>
                                </div>
                                <div class="col-md-2 my-col-md-2">
                                        <label class="condition label_on" about="0">日期<span class="glyphicon glyphicon-arrow-down"></span></label>

                                </div>
                                <div class="col-md-2 my-col-md-2">
                                        <label class="condition label_notOn" about="1">下载量 <span class="glyphicon glyphicon-arrow-down"></span></label>
                                </div>
                                <div class="col-md-2 my-col-md-2" >
                                        <label class="condition label_notOn" about="2">大小<span class="glyphicon glyphicon-arrow-down"></span></label>
                                </div>

                        </div>

                        <div class="row">
                                <div class="container" id="dataInfo">

                                </div>
                                <div class="data-row row" id="page" style="margin-top: 20px"></div>
                        </div>
                </div>

        </div>

        <input type="hidden" id="category" value="${category}">
</div>
</body>
<%@include file="/WEB-INF/views/include/footer.jsp"%>
<script type="text/javascript" src="resources/js/reference/laypage.js"></script>
<script type="text/javascript">
        $(function(){
                var category = $("#category").val();
                $("#categoryHead").text(getCategoryName(parseInt(category)));
                page();
                $(".condition").click(function () {
                        $(this).parent().siblings().find(".label_on").removeClass('label_on').addClass('label_notOn');
                        $(this).removeClass().addClass('condition label_on');
                        page();
                });
                function page(curr){
                        var condition = [];
                        $('.label_on').each(function (key,value) {
                                condition.push($(value).attr("about"))
                        });
                        $.ajax({
                                url:"data/query",
                                type:"post",
                                data:{
                                        "type":condition[0],
                                        "size":condition[1],
                                        "source":condition[2],
                                        "sort":condition[3],
                                        "category":category,
                                        "currentPage": curr || 1
                                },
                                success:function(response){
                                        var data = response.data;
                                        var  datainfo = $("#dataInfo");
                                        datainfo.empty();
                                        for(var i = 0; i < data.length;i++){
                                                var name = data[i].dataName.split(".")[0];
                                                datainfo.append("<div class='row data-row'><h2><a href=operate/download?dataName=" + data[i].dataName + ">" + name+"</a></h2><p>" + data[i].description + "</p></div>")
                                        }
                                        laypage({
                                                cont:"page",
                                                pages:response.pages,
                                                curr : curr || 1,
                                                skin:"#2ecc71",
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
                        })
                }
        })
        function getCategoryName(category){
                switch (category){
                        case 10:
                                return "自然科学";
                        case 60:
                                return "信息科学";
                        case 65:
                                return "工程与技术";
                        case 70:
                                return "生物医药";
                        case 75:
                                return "农业科学";
                        case 80:
                                return "人文与社科";
                        case 90:
                                return "统计年鉴数据";
                }
        }
</script>
</html>
