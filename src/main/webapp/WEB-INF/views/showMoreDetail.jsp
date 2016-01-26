<%--
  Created by IntelliJ IDEA.
  User: simple_love
  Date: 2016/1/13
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="include/header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--http://www.mkyong.com/spring-mvc/modelandviews-model-value-is-not-displayed-in-jsp-via-el/
对于JSP1.2 El默认是禁止使用的，所以需要手动设置允许使用它--%>
<%@ page isELIgnored="false" %>
<head>
        <title></title>
</head>
<body>
        <input type="hidden" value="${dataName}" id="dataName">
        <input type="hidden" value="${queryUrl}" id="queryUrl">
        <div style="margin-left: 10%;margin-right: 10%">
                <table class="table" id="detail">
                        <thead>
                                <tr>
                                        <th>DataId</th>
                                        <th>Agent</th>
                                        <th>Activity</th>
                                        <th>Time</th>
                                </tr>
                        </thead>
                        <tbody></tbody>
                </table>
                <div id="page" style="margin-left: 57%"></div>
        </div>
</body>
<%@include file="include/footer.jsp"%>
<script type="text/javascript" src="resources/js/reference/laypage.js"></script>
<script type="text/javascript">
        $(function(){
                var dataName = $("#dataName").val();
                var queryUrl = $("#queryUrl").val();
                function page(curr){
                        $.getJSON(queryUrl,{
                                dataName:dataName,
                                currentPage:curr || 1
                        },function(response){
                                if(response.RESULT == "SUCCESS"){
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
                        })
                }
                function formatTimestamp(timestamp){
                        var year = 1900 + timestamp.year;
                        var month = timestamp.month + 1;
                        var date = (timestamp.date < 10 ? "0" : "") + timestamp.date;
                        var hours = (timestamp.hours < 10 ? "0" : "") + timestamp.hours;
                        var minutes = (timestamp.minutes < 10 ? "0" : "") + timestamp.minutes;
                        var second = (timestamp.seconds < 10 ? "0" : "") + timestamp.seconds;
                        return year + "-" + month + "-" + date + " " + hours + ":" + minutes + ":" + second;
                }
                page();
        })
</script>
</html>
