<%--
  Created by IntelliJ IDEA.
  User: simple_love
  Date: 2015/11/11
  Time: 9:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
        <title></title>
</head>
<body>
<svg width="100%" height="100%" version="1.1"
     xmlns="http://www.w3.org/2000/svg">

        <script>
                function create_a(evt)
                {
                        alert(123);
                        var left = evt.screenX;


                        var top = evt.screenY;


                        var client_x = evt.clientX;
                        var client_y = evt.clientY;



                        alert("left="+left);
                        alert("top="+top);

                        alert("client_x=" + client_x);
                        alert("client_y=" + client_y);

                        var obj = document.elementFromPoint(left,top);
                        alert("obj=" + obj);
                        alert("obj.id=" + obj.id);
                        alert("obj.nodeName=" + obj.nodeName);

                        var obj2 = document.elementFromPoint(client_x,client_y);
                        alert("obj2=" + obj2);
                        alert("obj2.id=" + obj2.id);
                        alert("obj2.nodeName=" + obj2.nodeName);



                        /*
                         //var SVGDoc=evt.getTarget().getOwnerDocument();
                         var SVGDoc=evt.target.ownerDocument;
                         var txt=SVGDoc.getElementById("txt");
                         var link=SVGDoc.createElement("a");
                         var text_node=SVGDoc.createTextNode("LINK");

                         link.setAttributeNS(
                         "http://www.w3.org/1999/xlink",
                         "xlink:href",
                         "http://www.w3schools.com");

                         link.appendChild(text_node);
                         txt.appendChild(link);
                         */
                }
        </script>

        <text id="txt" x="10" y="10">Click on the circle to create a ....</text>
        <circle id = "circle_1" cx="20" cy="40" r="1.5em" style="fill:blue" onclick="create_a(evt)"/>

</svg>
</body>
</html>
