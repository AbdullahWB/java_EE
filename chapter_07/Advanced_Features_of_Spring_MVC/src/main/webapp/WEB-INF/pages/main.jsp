<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Admin System</title>
</head>
<body>

<ul>
  <li>您好:${USER_SESSION.username}</li>
  <li>
    <a href="${pageContext.request.contextPath}/logout">Exit</a>
  </li>
  <li>
    <a href="${pageContext.request.contextPath}/orderInfo">Order Information</a>
  </li>
</ul>

</body>
</html>
