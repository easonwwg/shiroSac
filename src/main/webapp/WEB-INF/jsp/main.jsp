<%--
  Created by IntelliJ IDEA.
  User: 99079
  Date: 2017/9/21
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/resources/common/basePath.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.7.1.js"></script>
</head>
<body>
<div style="float: right">
    歡迎你【<shiro:principal property="nickName"/>】
    <br/>
    用户状态【<shiro:principal property="status"/>】
</div>

<div>
    <shiro:hasRole name="admin">
        这是管理员的角色
    </shiro:hasRole>

    <shiro:hasRole name="test">
        这是测试用户的角色
    </shiro:hasRole>
</div>
<div>
    <shiro:hasPermission name="/user/add">
        此用户具有用户添加的权限
    </shiro:hasPermission>
</body>
<script>
</script>
</html>
