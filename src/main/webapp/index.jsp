<%@include file="/resources/common/basePath.jsp"%>
<!DOCTYPE html>
<html lang="en" class="no-js">

<head>

    <meta charset="utf-8">
    <title>Fullscreen Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- CSS -->
    <link rel='stylesheet' href="<%=basePath%>/resources/css/login/googleeapis.css">
    <link rel="stylesheet" href="<%=basePath%>/resources/css/login/reset.css">
    <link rel="stylesheet" href="<%=basePath%>/resources/css/login/supersized.css">
    <link rel="stylesheet" href="<%=basePath%>/resources/css/login/style.css">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

</head>

<body>

<div class="page-container">
    <h1>Login</h1>
    <form action="" method="post">
        <input type="text" name="username" class="username" placeholder="Username">
        <input type="password" name="password" class="password" placeholder="Password">
        <button type="submit">Sign me in</button>
        <div class="error"><span>+</span></div>
    </form>

</div>

<!-- Javascript -->
<script src="<%=basePath%>/resources/js/jquery-1.7.1.js"></script>
<script src="<%=basePath%>/resources/js/login/supersized.3.2.7.min.js"></script>
<script src="<%=basePath%>/resources/js/login/supersized-init.js"></script>
<script src="<%=basePath%>/resources/js/login/scripts.js"></script>

</body>

</html>


