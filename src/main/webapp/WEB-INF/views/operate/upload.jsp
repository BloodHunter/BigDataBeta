<%--
  Created by IntelliJ IDEA.
  User: simple_love
  Date: 2016/4/8
  Time: 9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<html>
<%@include file="/WEB-INF/views/include/topNav.jsp"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<head>
        <title></title>
        <link rel="stylesheet" href="resources/css/flat-ui.css">
        <link rel="stylesheet" href="resources/css/flat/red.css">
</head>
<body>
        <div class="container">
                <form id="myForm" method="post" enctype="multipart/form-data" action="operate/upload">
                        <div class="form-group">
                                <label for="dataName">数据名称</label>
                                <input type="text" id="dataName" name="dataName" class="form-control">
                        </div>
                        <div class="form-group">
                                <label for="description">数据描述</label>
                                <input type="text" id="description" name="description" class="form-control">
                        </div>
                        <div class="form-group">
                                <label>数据类型</label><br>
                                <input type="radio" name="type" id="type1" checked="checked" value="text">
                                <label for="type2">文本</label>
                                <input type="radio" name="type" id="type2" value="image">
                                <label for="type1">图片</label>
                                <input type="radio" name="type" id="type3" value="studio">
                                <label for="type3">语音</label>
                                <input type="radio" name="type" id="type4" value="video">
                                <label for="type4">视频</label>
                        </div>
                        <div class="form-group">
                                <label>数据分类</label><br>
                                <select id="first" name="category" ></select>
                                <select id="second" name="category"></select>
                                <select id="third" name="category"></select>
                        </div>
                        <div>
                                <!-- Nav tabs -->
                                <ul class="nav nav-tabs" role="tablist" id="upload">
                                        <li role="presentation" class="active"><a href="#browser" aria-controls="browser" role="tab" data-toggle="tab">浏览器上传</a></li>
                                        <li role="presentation"><a href="#ftp" aria-controls="ftp" role="tab" data-toggle="tab">FTP上传</a></li>
                                </ul>

                                <!-- Tab panes -->
                                <div class="tab-content">
                                        <div role="tabpanel" class="tab-pane fade in active" id="browser" style="border: 1px solid #ddd;border-top: hidden">
                                                <%--<label class="label label-danger">
                                                        <span class="glyphicon glyphicon-open"></span>
                                                        <input type="file" style="display: none">选择上传文件
                                                </label>--%>

                                                <input type="file" name="fileUpload" id="fileUpload" class="inputfile inputfile-1" data-multiple-caption="{count} files selected" multiple />
                                                        <label for="fileUpload"><svg xmlns="http://www.w3.org/2000/svg" width="20" height="17" viewBox="0 0 20 17">
                                                                <path d="M10 0l-5.2 4.9h3.3v5.1h3.8v-5.1h3.3l-5.2-4.9zm9.3 11.5l-3.2-2.1h-2l3.4 2.6h-3.5c-.1 0-.2.1-.2.1l-.8 2.3h-6l-.8-2.2c-.1-.1-.1-.2-.2-.2h-3.6l3.4-2.6h-2l-3.2 2.1c-.4.3-.7 1-.6 1.5l.6 3.1c.1.5.7.9 1.2.9h16.3c.6 0 1.1-.4 1.3-.9l.6-3.1c.1-.5-.2-1.2-.7-1.5z"/>
                                                        </svg> <span>选择上传文件&hellip;</span></label><br>
                                                        <label class="has_error">${ex.getMessage()}</label>
                                        </div>
                                        <div role="tabpanel" class="tab-pane fade" id="ftp">
                                                <span>FTP上传</span>
                                        </div>
                                </div>

                        </div>
                        <div class="form-group" style="width: 30%;margin: auto;margin-top: 50px">
                                <button class="btn btn-block btn-lg btn-success" type="submit">上传数据</button>
                        </div>
                </form>

        </div>
</body>
<script type="text/javascript" src="resources/js/reference/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="resources/js/cascade.js"></script>
<script type="text/javascript" src="resources/js/reference/jquery.validate.js"></script>
<script type="text/javascript" src="resources/js/reference/jquery.form.js"></script>
<script type="text/javascript" src="resources/js/reference/icheck.js"></script>
<script type="text/javascript" src="resources/js/reference/bootstrap.min.js"></script>
<script type="text/javascript" src="resources/js/form-validate.js"></script>
<script>
        /*上传组件显示上传文件名*/
        ;( function ( document, window, index )
        {
                var inputs = document.querySelectorAll( '.inputfile' );
                Array.prototype.forEach.call( inputs, function( input )
                {
                        var label	 = input.nextElementSibling,
                                labelVal = label.innerHTML;

                        input.addEventListener( 'change', function( e )
                        {
                                var fileName = '';
                                if( this.files && this.files.length > 1 )
                                        fileName = ( this.getAttribute( 'data-multiple-caption' ) || '' ).replace( '{count}', this.files.length );
                                else
                                        fileName = e.target.value.split( '\\' ).pop();

                                if( fileName )
                                        label.querySelector( 'span' ).innerHTML = fileName;
                                else
                                        label.innerHTML = labelVal;
                        });

                        // Firefox bug fix
                        input.addEventListener( 'focus', function(){ input.classList.add( 'has-focus' ); });
                        input.addEventListener( 'blur', function(){ input.classList.remove( 'has-focus' ); });
                });
        }( document, window, 0 ));
        $(function(){
                upload_form_validate();
                init_cascade();
                $('input').iCheck({
                        checkboxClass: 'icheckbox_flat-red',
                        radioClass: 'iradio_flat-red'
                });
                $('#upload a').click(function (e) {
                        e.preventDefault();
                        $(this).tab('show')
                });

        })

</script>
</html>
