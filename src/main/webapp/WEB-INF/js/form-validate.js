/**
 * Created by Simple_love on 2016/4/8.

 */

/*字符串验证规则*/
jQuery.validator.addMethod("stringCheck",function(value,element){
        return this.optional(element) || /^[0-9a-zA-Z_\u4e00-\u9fa5]+$/.test(value);
},"只能包括中英文、数字、“_”");

jQuery.validator.addMethod("urlCheck",function(value,element){
        return this.optional(element) || /[a-zA-z]+:\/\/[^\s]* /.test(value);
});

/*上传表单验证*/
function upload_form_validate(){
        $("#myForm").validate({
                rules:{
                        dataName:{
                                required:true,
                                stringCheck:true
                        },
                        description:{
                                required:true
                        },
                        fileUpload:{
                                required:true
                        }
                },
                messages:{
                        dataName:{
                                required:"请输入数据名称"
                        },
                        description:{
                                required:"请输入数据描述"
                        },
                        fileUpload:{
                                required:"请选择上传的文件"
                        }
                },
                onkeyup:false,
                errorClass:"has_error"
        });
}

/*登录表单验证*/
function login_form_validate(){
        $(".login-form").validate({
                rules:{
                        account:{
                                required:true
                        },
                        password:{
                                required:true
                        }
                },
                messages:{
                        account:{
                                required:"请输入账号"
                        },
                        password:{
                                required:"请输入密码"
                        }
                },
                onkeyup:false,
                errorPlacement:function(error,element){
                        $(element.next()).remove(".has_error");
                        element.after("<div class='has_error'> <label>" + error.text()+"<span class='glyphicon glyphicon-remove' ></span></label></div>")
                },
                success: function (label,element) {
                        $($(element).next()).remove(".has_error");
                },
                submitHandler:function(form){
                        var options = {
                                url:"account/login",
                                type:'post',
                                resetForm:true,
                                clearForm:true,
                                timeout:5000,
                                beforeSubmit:function(){
                                        $(form).find(":submit").attr("disabled",true).attr("value","登录中..");
                                },
                                complete:function(XMLHttpRequest, textStatus){
                                        $(form).find(":submit").attr("disabled",false).attr("value","登录");
                                },
                                success:function(response){
                                        if(response.RESULT == "SUCCESS"){
                                                /*var path = $('base').attr("href");
                                                 window.location.href=path + "account/index";*/
                                                /*window.history.back();
                                                window.location.reload();*/
                                                window.location=document.referrer;
                                                console.log("dd")
                                        }else{
                                                $("#errorTip").html(response.msg)
                                        }
                                },
                                error:function(XMLHttpRequest, textStatus, errorThrown){
                                        alert(textStatus + " " + errorThrown);

                                }
                        };
                        $(form).ajaxSubmit(options);
                        return false;
                }
        });
}

/*昵称与邮箱唯一性验证*/
$.ajaxSettings.async = false;
jQuery.validator.addMethod("nameCheck",function(value,element){
        var flag;
        $.getJSON(
            "account/checkName?name=" + value ,
            function(response){
                    flag = response.RESULT!= "ERROR" ;
            });
        return flag;
},"该名称已经被注册");

jQuery.validator.addMethod("emailCheck",function(value,element){
        var flag;
        $.getJSON(
            "account/checkEmail?email=" + value ,
            function(response){
                    flag = response.RESULT!= "ERROR" ;
            })
        return flag;
},"该邮箱已经被注册");

/*注册表单验证*/
function register_form_validate(){
        $("#msform").validate({
                rules:{
                        name:{
                                required:true,
                                rangelength:[5,10],
                                stringCheck:true,
                                nameCheck:true
                        },
                        email:{
                                emailCheck:true,
                                required:true,
                                email:true
                        },
                        pass:{
                                required:true,
                                rangelength:[6,30]
                        },
                        cpass:{
                                required:true,
                                equalTo:"#pass"
                        }
                },
                messages:{
                        name:{
                                required:"请输入昵称",
                                rangelength:"昵称长度为5-10个字符"
                        },
                        email:{
                                required:"请输入邮箱地址",
                                email:"请输入正确的邮箱地址"
                        },
                        pass:{
                                required:"请输入密码",
                                rangelength:"密码长度为6-30个字符"
                        },
                        cpass:{
                                required:"请确认密码",
                                equalTo:"两次输入的密码不一致"
                        }
                },
                errorPlacement:function(error,element){
                        //element.after(error)
                        element.css("border-color","#e74c3c");
                        //$("div").remove(".has_error");
                        $(element.next()).remove(".has_error");
                        element.after("<div class='has_error'> <label>" + error.text()+"<span class='glyphicon glyphicon-remove' ></span></label></div>")
                },
                success: function (label,element) {
                        $($(element).next()).remove(".has_error");
                        $(element).css("border-color","#ccc")
                },
                onkeyup:false
        });
}