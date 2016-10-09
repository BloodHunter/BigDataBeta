<%--
  Created by IntelliJ IDEA.
  User: simple_love
  Date: 2016/4/7
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
        <base href="<%=basePath%>">
        <title></title>
        <link rel="stylesheet" href="resources/css/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="resources/css/flat-ui.css">
        <style>
                body{
                        font-family:   Tahoma,Verdana,STHeiTi,simsun,sans-serif;
                        background: url('resources/img/gs.png');
                }
                .login-div{
                        padding-left: 15px;
                        padding-right: 15px;
                        margin-left: 15%;
                        margin-top: 5%;
                }
                .has_error,#errorTip{
                        color: #e74c3c;
                        text-align: left;
                }
        </style>
</head>
<body>
        <div class="login-div">
                <div class="login" >
                        <div class="login-screen">
                                <%--<div class="login-icon">
                                        <img src="img/login/icon.png" alt="Welcome to Mail App" />
                                        <h4>Welcome to <small>Mail App</small></h4>
                                </div>--%>

                                <form class="login-form" method="post" action="account/login">
                                        <label class="has_error">${ex.getMessage()}</label>
                                        <label id="errorTip"></label>
                                        <div class="form-group">
                                                <input type="text" class="form-control login-field"  placeholder="账号" id="account" name="account"/>
                                                <label class="login-field-icon fui-user" for="account"></label>
                                        </div>

                                        <div class="form-group">
                                                <input type="password" class="form-control login-field"  placeholder="密码" id="password" name="password" />
                                                <label class="login-field-icon fui-lock" for="password"></label>
                                        </div>

                                        <button class="btn btn-primary btn-lg btn-block" id="submit" type="submit">登录</button>
                                        <a class="login-link" href="account/register">注册</a>
                                </form>
                        </div>
                </div>
        </div>
</body>
<script type="text/javascript" src="resources/js/reference/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="resources/js/reference/jquery.validate.js"></script>
<script type="text/javascript" src="resources/js/reference/jquery.form.js"></script>
<script type="text/javascript" src="resources/js/form-validate.js"></script>
<script type="text/javascript">
        $(function(){
                login_form_validate();
        })
</script>
</html>
