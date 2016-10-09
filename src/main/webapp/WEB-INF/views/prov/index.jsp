<%--
  Created by IntelliJ IDEA.
  User: simple_love
  Date: 2016/4/18
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="/WEB-INF/views/include/topNav.jsp"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<head>
        <title>数据供应链查询</title>
</head>
<body>
        <div class="container">
                <div class="row">
                        <div class="col-md-5">
                                <div class="form-group has-feedback">
                                        <input class="form-control" type="text" id="dataName" name="dataName" placeholder="请输入要查询数据的名称">
                                        <span class="glyphicon glyphicon-search form-control-feedback" aria-hidden="true" style="background-color:  #e74c3c;cursor: pointer;pointer-events: auto">
                                        </span>
                                </div>

                        </div>
                </div>
                <div class="row">
                       <div id="show_svg">
                               <img src="resources/provImage/test.svg">
                       </div>
                </div>
        </div>
</body>
<%@include file="/WEB-INF/views/include/footer.jsp"%>
<script type="text/javascript" src="resources/js/reference/jquery.graphviz.svg.js"></script>
<script type="text/javascript">
        $(function(){
                $("#dataName").keydown(function(event){
                        if(event.keyCode == 13)
                                queryProv();
                })
                $(".glyphicon-search").click(function(){
                        console.log(11);
                })
        })
        function queryProv(){
                var bath = $("base").attr("href");
                var dataName = $("#dataName").val();
                $.ajax({
                        url:"prov/queryPlatformRelation?dataName=" + dataName,
                        post:"get",
                        success:function(response){
                                if(response.RESULT == "SUCCESS"){
                                        var queryId = response.QUERY_ID;
                                        $("#show_svg").graphviz({
                                                url:"prov/getSvgOfRelation?queryId=" + queryId,
                                                ready:function(){
                                                        var gv = this;
                                                        gv.nodes().each(function(key,value){
                                                                var href = $(value).find("a").attr("xlink:href");
                                                                $(value).click(function(e){
                                                                        e.preventDefault();
                                                                        window.location.href = bath +"prov/showDataRelation?" + "queryUrl=" + href + "&queryId=" + queryId;
                                                                })
                                                        })
                                                }
                                        })
                                }else{
                                        if(typeof (response.MESSAGE) == "undefined")
                                                $('body').html(response);
                                        else
                                                alert("查询失败: " + response.MESSAGE);
                                }
                        }
                })
        }
</script>
</html>
