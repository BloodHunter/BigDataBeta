<%--
  Created by IntelliJ IDEA.
  User: simple_love
  Date: 2016/1/7
  Time: 9:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="include/header.jsp"%>
<head>
        <title>ProvTest</title>
</head>
<body>
        <input type="text" id="dataName">
        <input type="button" value="Submit">
        <div id="showRelation">

        </div>
       <%-- <embed id="showRelation" height="500" width="500" type="image/svg+xml"
               pluginspage="http://www.adobe.com/svg/viewer/install/"/>--%>
</body>
<%@include file="include/footer.jsp"%>
<script type="text/javascript">
        $(function(){
                //$("#showRelation").attr("src","resources/provImage/platA_platA-1_2016-01-07_14-31-20.svg").show();
                $("input[type=button]").click(function(){
                        var dataName = $("#dataName").val();
                        $.getJSON("prov/queryPlatformRelation?dataName=" + dataName,function(response){
                                if(response.RESULT == "SUCCESS"){
                                        var temp = '<embed id="showRelation" src="resources/provImage/'+response.QUERY_ID +'.svg"' +' height="500" width="500" type="image/svg+xml" pluginspage="http://www.adobe.com/svg/viewer/install/"/>';
                                        $("#showRelation").append(temp);
                                }else{
                                        alert(response.MESSAGE);
                                }
                        });
                        return false;
                })
        })
</script>
</html>
