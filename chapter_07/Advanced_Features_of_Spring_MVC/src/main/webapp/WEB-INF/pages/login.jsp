<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>User Login</title>
</head>
<body>

<%
  String msg = (String) request.getAttribute("msg");
  if (msg != null) {
%>
<div><%= msg %></div>
<%
  }
%>

<form action="${pageContext.request.contextPath}/doLogin" method="post">
  UserName：<input type="text" name="username"><br>
  Password：<input type="password" name="password"><br>
  <input type="submit" value="Login">
</form>

</body>
</html>
