<%--
  Created by IntelliJ IDEA.
  User: simple_love
  Date: 2016/4/1
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
        <base href="<%=basePath%>">
        <title></title>
        <link rel="stylesheet" href="resources/css/bootstrap/bootstrap.min.css">
       <%-- <link rel="stylesheet" href="resources/css/flat-ui.css">--%>
        <link rel="stylesheet" href="resources/css/style.css">
        <link rel="stylesheet" href="resources/css/flat/red.css">
        <style>
                html{
                        background: url('resources/img/gs.png');
                        background:
                        linear-gradient(rgba(196, 102, 0, 0.2), rgba(155, 89, 182, 0.2)),
                        url('resources/img/gs.png');
                }
        .has_error{
                        color: #e74c3c;
                        text-align: left;
                }

        </style>
</head>
<body style="background-color: inherit">
<header class="htmleaf-header">
        <h2>欢迎加入我们</h2>
        <a href="#"><span class="glyphicon glyphicon-home" ></span></a>
</header>
        <div>
                <form id="msform" method="post" action="account/register_user">
                        <!-- progressbar -->
                        <ul id="progressbar">
                                <li class="active">账号设置</li>
                                <li>研究领域</li>
                                <li>个人详细信息</li>
                        </ul>
                        <!-- fieldsets -->
                        <fieldset>
                                <h2 class="fs-title">创建你的账号</h2>
                                <h3 class="fs-subtitle">您可以使用邮箱或者昵称登录</h3>
                                <label for="name" class="labelfor">昵称</label>
                                <input type="text" name="name" id="name" placeholder="昵称" />
                                <label for="email" class="labelfor">Email</label>
                                <input type="text" name="email" id="email" placeholder="Email地址" />
                                <label for="pass" class="labelfor">密码</label>
                                <input type="password" name="pass" id="pass" placeholder="密码"/>
                                <label for="cpass" class="labelfor">确认密码</label>
                                <input type="password" name="cpass" id="cpass" placeholder="确认密码" />
                                <input type="button" name="next" class="next action-button" value="下一步" />
                        </fieldset>
                        <fieldset>
                                <h2 class="fs-title">填写研究领域</h2>
                                <h3 class="fs-subtitle">填写你感兴趣的领域</h3>
                                <%--<select id="first" name="first" ></select>
                                <select id="second" name="second"></select>
                                <select id="third" name="third"></select>--%>
                                <table align="center">
                                        <tr>
                                                <td><input type="checkbox" value="100000" name="hobby"><label class="labelRadio">自然科学</label></td>
                                        </tr>
                                        <tr><td><input type="checkbox" value="600000" name="hobby"><label class="labelRadio">信息科学</label></td></tr>
                                        <tr><td><input type="checkbox" value="650000" name="hobby"><label class="labelRadio">工程与技术</label></td></tr>
                                        <tr><td><input type="checkbox" value="700000" name="hobby"><label class="labelRadio">生物医药</label></td></tr>
                                        <tr><td><input type="checkbox" value="750000" name="hobby"><label class="labelRadio">农业科学</label></td></tr>
                                        <tr><td><input type="checkbox" value="800000" name="hobby"><label class="labelRadio">人文与社科</label></td></tr>
                                        <tr><td><input type="checkbox" value="900000" name="hobby"><label class="labelRadio">统计年鉴数据</label></td></tr>
                                </table>
                                <input type="button" name="previous" class="previous action-button" value="上一步" />
                                <input type="button" name="next" class="next action-button" value="下一步" />
                        </fieldset>
                        <fieldset>
                                <h2 class="fs-title">个人详细信息</h2>
                                <h3 class="fs-subtitle">个人详细信息是保密的，不会被泄露</h3>

                                <div class="form-inline" style="text-align: left">
                                        <label class="labelRadio" >职业</label><br>
                                        <div class="form-group">
                                                <input type="radio" name="profession" value="work" checked="checked"/>
                                                <label class="labelRadio">在职</label>
                                        </div>
                                        <div class="form-group">
                                                <input type="radio" name="profession" value="study"/>
                                                <label class="labelRadio">学生</label>
                                        </div>
                                        <div class="form-group">
                                                <input type="radio" name="profession" value="other"/>
                                                <label  class="labelRadio">其他</label>
                                        </div><br>
                                        <label class="labelRadio" for="telephone">联系方式</label><br>
                                        <input id="telephone" type="text" placeholder="请输入电话号码">
                                        <label class="labelRadio">用户类别</label><br>
                                        <div class="form-group">
                                                <input type="radio" name="userType" value="user" checked="checked">
                                                <label class="labelRadio">普通用户</label>
                                        </div>
                                        <div class="form-group">
                                                <input type="radio" name="userType" value="admin">
                                                <label class="labelRadio">平台管理员</label>
                                        </div>
                                        <div id="url_div" style="display: none">
                                                <input type="text" id="url" name="url" placeholder="请输入平台开放连接地址">
                                        </div>
                                </div>

                                <input type="button" name="previous" class="previous action-button" value="上一步" />
                                <input type="button" name="submit" class="submit action-button" value="注册" onclick="submit1()"/>
                        </fieldset>
                </form>
        </div>

</body>
<script type="text/javascript" src="resources/js/reference/jquery-2.1.3.min.js"></script>
<%--<script type="text/javascript" src="resources/js/cascade.js"></script>--%>
<script type="text/javascript" src="resources/js/reference/jquery.easing.min.js"></script>
<script type="text/javascript" src="resources/js/reference/jquery.validate.js"></script>
<script type="text/javascript" src="resources/js/reference/jquery.form.js"></script>
<script type="text/javascript" src="resources/js/reference/icheck.js"></script>
<script type="text/javascript" src="resources/js/form-validate.js"></script>
<script type="text/javascript">
        var userInfo = {},hobby;
        register_form_validate();
       /* init_cascade();
        init_select_grade();*/
        $('input').iCheck({
                checkboxClass: 'icheckbox_flat-red',
                radioClass: 'iradio_flat-red'
        });
        $('input[name=profession]').on("ifChecked",function(){
                userInfo.profession = $(this).val();
        });
        $("input[name=userType]").on("ifChecked",function(){
                $("#url_div").toggle();
        })
        var current_fs, next_fs, previous_fs;
        var left, opacity, scale;
        var animating;
        $('.next').click(function () {
                if (animating)
                        return false;
                animating = true;
                current_fs = $(this).parent();
                next_fs = $(this).parent().next();
                $('#progressbar li').eq($('fieldset').index(next_fs)).addClass('active');
                next_fs.show();
                current_fs.animate({ opacity: 0 }, {
                        step: function (now, mx) {
                                scale = 1 - (1 - now) * 0.2;
                                left = now * 50 + '%';
                                opacity = 1 - now;
                                current_fs.css({ 'transform': 'scale(' + scale + ')' });
                                next_fs.css({
                                        'left': left,
                                        'opacity': opacity
                                });
                        },
                        duration: 800,
                        complete: function () {
                                current_fs.hide();
                                animating = false;
                        },
                        easing: 'easeInOutBack'
                });
        });
        $('.previous').click(function () {
                if (animating)
                        return false;
                animating = true;
                current_fs = $(this).parent();
                previous_fs = $(this).parent().prev();
                $('#progressbar li').eq($('fieldset').index(current_fs)).removeClass('active');
                previous_fs.show();
                current_fs.animate({ opacity: 0 }, {
                        step: function (now, mx) {
                                scale = 0.8 + (1 - now) * 0.2;
                                left = (1 - now) * 50 + '%';
                                opacity = 1 - now;
                                current_fs.css({ 'left': left });
                                previous_fs.css({
                                        'transform': 'scale(' + scale + ')',
                                        'opacity': opacity
                                });
                        },
                        duration: 800,
                        complete: function () {
                                current_fs.hide();
                                animating = false;
                        },
                        easing: 'easeInOutBack'
                });
        });

        function submit1(){
                var hobby="",userType,url,errorMsg="";
                userInfo.userName = $("#name").val();
                userInfo.email = $("#email").val();
                userInfo.password = $("#pass").val();
                userInfo.profession = "work";
                $('div[class="icheckbox_flat-red checked"]').each(function(){
                        hobby += $(this).find('input[type=checkbox]').val() + ",";
                });
                if(hobby.length == 0)
                        hobby="100000";
                else
                        hobby = hobby.substring(0,hobby.length - 1);
                userInfo.hobby = hobby;
                userType = $("div[class='iradio_flat-red checked']").find('input[name=userType]').val();
                if(userType == "admin"){
                        url = $("#url").val();
                        if(url == "undefined" || !/[a-zA-z]+:\/\/[^\s]*/.test(url)){
                               errorMsg = "请输入格式正确的连接地址";
                        }else{
                                userInfo.url = url;
                        }
                }
                $(document.getElementsByClassName("has_error")).remove();
                if(errorMsg.length != 0){
                        $("#url").after("<div class='has_error'> <label>" + errorMsg+"<span class='glyphicon glyphicon-remove' ></span></label></div>")
                }else{
                        $.ajax({
                                url:"account/register_user",
                                type:"post",
                                data:{
                                        "user":JSON.stringify(userInfo)
                                },
                                success:function(response){
                                        $("body").html(response)
                                },
                                error:function(){
                                        console.log("error")
                                }
                        })
                }

        }
</script>
</html>
