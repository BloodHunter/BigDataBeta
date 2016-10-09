<%--
  Created by IntelliJ IDEA.
  User: simple_love
  Date: 2016/4/12
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
        <base href="<%=basePath%>">
        <link rel="stylesheet" href="resources/css/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="resources/css/style.css">
</head>
<div class="navbar navbar-default navbar-static-top">
        <div class="top_home">
                <span class="glyphicon glyphicon-home"><a href="" >数据平台首页</a></span>
        </div>
        <div class="top_nav">
                <%
                        if (session.getAttribute("user") != null){
                                out.print("<span>" + session.getAttribute("user") + "</span>");
                                out.print("<a href='account/logout'>&nbsp&nbsp注销&nbsp&nbsp</a>");
                                out.print("<a href='account/profile'>个人中心</a>");
                        }else {
                                out.print("<sapn class='glyphicon glyphicon-user'></span><span class='top-nav'><a href='account/index'>登录&nbsp</a></span>");
                                out.print("<span class='top-nav'><a href='account/register'>注册</a></span>");
                        }
                %>
        </div>
</div>

