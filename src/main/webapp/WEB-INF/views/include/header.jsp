<%--
  Created by IntelliJ IDEA.
  User: simple_love
  Date: 2015/10/30
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
        <base href="<%=basePath%>">
        <link rel="stylesheet" href="resources/css/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="resources/css/flat-ui.css">
        <link rel="stylesheet" href="resources/css/style.css">
</head>
<div id="head_top_nav">
        <nav class="navbar navbar-inverse navbar-embossed" style="border-radius: 0px">
                &lt;%&ndash;<div class="container-fluid">
                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                                <ul class="nav navbar-nav">
                                        <li><a href="#">首页</a></li>
                                        <li><a href="#">API列表</a></li>
                                        <li><a href="#">所有数据</a></li>
                                        <li class="dropdown">
                                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">数据应用 <span class="caret"></span></a>
                                                <ul class="dropdown-menu">
                                                        <li><a href="#">数据上传</a></li>
                                                        <li><a href="#">数据合成</a></li>
                                                        <li><a href="#">数据查看</a></li>
                                                </ul>
                                        </li>
                                </ul>
                        </div><!-- /.navbar-collapse -->
                </div><!-- /.container-fluid -->&ndash;%&gt;
                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                                <ul class="nav navbar-nav">
                                        <li><a href="#">首页</a></li>
                                        <li><a href="#">API列表</a></li>
                                        <li><a href="#">所有数据</a></li>
                                        <li class="dropdown">
                                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">数据应用 <span class="caret"></span></a>
                                                <ul class="dropdown-menu">
                                                        <li><a href="#">数据上传</a></li>
                                                        <li><a href="#">数据合成</a></li>
                                                        <li><a href="#">数据查看</a></li>
                                                </ul>
                                        </li>
                                </ul>
                        </div>
        </nav>
</div>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="header">
        <nav class="navigation">
                <div class="wrapper">
                        <ul class="navigation-list ul-reset">
                                <li class="navigation-item ib">
                                        <a href="data/list" class="navigation-link">
                                                数据列表
                                        </a>
                                </li>
                                <li class="navigation-item ib">
                                        <a href="#" class="navigation-link">
                                                API列表
                                        </a>
                                </li>
                                <li class="navigation-item ib">
                                        <a href="prov/index" class="navigation-link">
                                                供应链查询
                                        </a>
                                </li>
                                <li class="navigation-item ib">
                                        <a href="operate/upload/index" class="navigation-link">
                                                数据发布
                                        </a>
                                </li>
                                <li class="navigation-item ib">
                                        <a href="#" class="navigation-link">
                                                数据应用
                                        </a>
                                </li>
                        </ul>
                </div>
        </nav>
</header>
