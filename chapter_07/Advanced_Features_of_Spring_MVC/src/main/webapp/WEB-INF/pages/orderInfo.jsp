<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单信息</title>
</head>
<body>

您好:${USER_SESSION.username}
<a href="${pageContext.request.contextPath}/logout">退出</a>

<table border="1" width="600" cellspacing="0" cellpadding="10">
    <tr>
        <td colspan="2" align="center">订单Id:D001</td>
    </tr>
    <tr>
        <td align="center">商品Id</td>
        <td align="center">商品名称</td>
    </tr>
    <tr>
        <td align="center">P001</td>
        <td align="center">土豆</td>
    </tr>
    <tr>
        <td align="center">P002</td>
        <td align="center">西红柿</td>
    </tr>
</table>

</body>
</html>