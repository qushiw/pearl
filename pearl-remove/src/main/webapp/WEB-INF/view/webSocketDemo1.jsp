<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://"
          + request.getServerName() + ":" + request.getServerPort()
          + path + "/";
%>
<html>
<head>
  <base href="<%=basePath%>">
  <title>My JSP 'index.jsp' starting page</title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <script type="text/javascript" src="<%=basePath%>/source/js/localWebSocket.js"></script>
  <script type="text/javascript" src="<%=basePath%>/source/js/jquery/jquery-3.2.1.min.js"></script>
</head>


<body onload="startServer()">
<h2>Hello World2</h2>
  <input type="button" onclick="send()" value="发送websocket信息">
  <input type="text" id="textId">
  <input type="text" id="message">

  <script>
    function send(){
        var val = $("#textId").val();
        sendMessage(val);
    }

    function receiveMessage(data){
      $("#message").val(data);
    }
  </script>


</body>
</html>
