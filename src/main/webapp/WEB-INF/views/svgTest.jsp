<%--
  Created by IntelliJ IDEA.
  User: simple_love
  Date: 2015/11/11
  Time: 9:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="include/header.jsp"%>
<head>
        <title></title>
</head>
<body>
        <div id="svgDiv" style="width: 100%;height: 100%;overflow: scroll"></div>
</body>
<%@include file="include/footer.jsp"%>
<script type="text/javascript" src="resources/js/reference/jquery.graphviz.svg.js"></script>
<script type="text/javascript">
       $(function(){
               var queryId = "platA_platA-1_2016-01-07-20-37-19";
               var bath = $("base").attr("href");
               $("#svgDiv").graphviz({
                       url:"resources/provImage/test.svg",
                       ready:function(){
                               var gv = this;
                               console.log("dd");
                                gv.nodes().each(function(key,value){
                                        var href = $(value).find("a").attr("xlink:href");
                                        $(value).click(function(e){
                                                e.preventDefault();
                                                /*IE 跳转与google跳转有区别
                                                * ie中跳转以当前路径开始跳转
                                                * 若window.location.href = prov/...
                                                * 则在IE中跳转地址为http://localhost:8090/BigDataBeta/prov/prov/...
                                                * 在google中跳转地址为：http://localhost:8090/BigDataBeta/prov/...
                                                * */
                                                window.location.href = bath +"prov/showProvDetail?" + "queryUrl=" + href + "&queryId=" + queryId;
                                                /*$.ajax({
                                                        url:href + "?queryId=" +queryId + "&jsoncallback=?",
                                                        success:function(response){
                                                                var provs = response.PROVS;
                                                                for(var i = 0; i < provs.length;i++ ){
                                                                        console.log(provs[0]);
                                                                }
                                                        }
                                                })*/
                                        })
                                })
                       }
               });

       })
</script>
</html>
