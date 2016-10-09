<%--
  Created by IntelliJ IDEA.
  User: simple_love
  Date: 2016/4/21
  Time: 9:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<%@include file="/WEB-INF/views/include/topNav.jsp"%>
<head>
        <title>个人中心</title>
</head>
<body>
        <div class="container">
                <div class="row">
                        <div class="tab">
                                <ul class="nav nav-tabs">
                                        <li role="presentation" class="active">
                                                <a href="#section1" aria-controls="section1" role="tab" data-toggle="tab">
                                                        <span class="glyphicon glyphicon-cog"></span>个人资料
                                                </a></li>
                                        <li role="presentation">
                                                <a href="#section2" aria-controls="section2" role="tab" data-toggle="tab">
                                                <span class="glyphicon glyphicon-align-left"></span>数据中心
                                        </a></li>
                                </ul>
                                <div class="tab-content">
                                        <div role="tabpanel" class="tab-pane fade in active" id="section1" style="border: 1px solid #ddd;border-top: hidden">
                                                <table class="table table-bordered table-hover">
                                                        <tbody>
                                                                <tr class="info">
                                                                        <td>昵称</td>
                                                                        <td>${userInfo.userName}</td>
                                                                </tr>
                                                                <tr>
                                                                        <td>绑定邮箱</td>
                                                                        <td>${userInfo.email}</td>
                                                                </tr>
                                                                <tr>
                                                                        <td>职业</td>
                                                                        <td>${userInfo.profession}</td>
                                                                </tr>
                                                        </tbody>
                                                </table>
                                        </div>
                                        <div role="tabpanel" class="tab-pane fade in" id="section2" style="border: 1px solid #ddd;border-top: hidden">
                                                <div >
                                                        <h3 class="label-success">已下载的数据</h3>
                                                        <table class="table table-bordered">
                                                                <thead>
                                                                        <tr>
                                                                                <th>数据名称</th>
                                                                        </tr>
                                                                </thead>
                                                                <c:forEach items="${downloadInfo}" var="dataInfo">
                                                                        <tr>
                                                                                <td>${dataInfo.dataName.split("\\.")[0]}</td>
                                                                        </tr>
                                                                </c:forEach>
                                                        </table>
                                                </div>
                                                <div >
                                                        <h3 class="label-success">已发布的数据</h3>
                                                        <table class="table table-bordered">
                                                                <thead>
                                                                <tr>
                                                                        <th>数据名称</th>
                                                                </tr>
                                                                </thead>
                                                                <c:forEach items="${uploadInfo}" var="dataInfo">
                                                                        <tr>
                                                                                <td>${dataInfo.dataName.split("\\.")[0]}</td>
                                                                        </tr>
                                                                </c:forEach>
                                                        </table>
                                                </div>
                                        </div>
                                </div>
                        </div>
                </div>
        </div>
</body>
<%@include file="/WEB-INF/views/include/footer.jsp"%>
</html>
