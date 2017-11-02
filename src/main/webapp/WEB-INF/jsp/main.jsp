<%--
  Created by IntelliJ IDEA.
  User: 99079
  Date: 2017/9/21
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/resources/common/basePath.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%--<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>--%>

<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8" />
    <title>SacWeb</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />
    <meta name="MobileOptimized" content="320">

    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="/resources/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <link href="/resources/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="/resources/assets/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css" />
    <!-- END GLOBAL MANDATORY STYLES -->

    <!-- BEGIN THEME STYLES -->
    <link href="/resources/assets/css/style-metronic.css" rel="stylesheet" type="text/css" />
    <link href="/resources/assets/css/style.css" rel="stylesheet" type="text/css" />
    <link href="/resources/assets/css/style-responsive.css" rel="stylesheet" type="text/css" />
    <link href="/resources/assets/css/plugins.css" rel="stylesheet" type="text/css" />
    <link href="/resources/assets/css/pages/tasks.css" rel="stylesheet" type="text/css" />
    <link href="/resources/assets/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color" />
    <link href="/resources/assets/css/custom.css" rel="stylesheet" type="text/css" />
    <!-- END THEME STYLES -->

    <link rel="shortcut icon" href="/favicon.ico" />
</head>
<!-- END HEAD -->

<!--body开始 -->
<body class="page-header-fixed">
<!--header开始 -->
<div class="header navbar navbar-inverse navbar-fixed-top">
    <!-- BEGIN TOP NAVIGATION BAR -->
    <div class="header-inner">
        <!-- BEGIN LOGO -->
        <a class="navbar-brand" href="javascript:;">
            <img src="/resources/assets/img/logo.jpg" alt="logo" class="img-responsive" />
        </a>
        <!-- END LOGO -->
        <!-- BEGIN RESPONSIVE MENU TOGGLER -->
        <a href="javascript:;" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <img
                    src="/resources/assets/img/menu-toggler.png" alt="" />
        </a>
        <!-- END RESPONSIVE MENU TOGGLER -->
        <!-- BEGIN TOP NAVIGATION MENU -->
        <ul class="nav navbar-nav pull-right">
            <li class="dropdown user">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                    <img alt="" src="/resources/assets/img/avatar1_small.jpg"/>
                    <!--用户名-->
                    <span class="username"><shiro:principal property="nickName"/> </span>
                    <i class="fa fa-angle-down"></i>
                </a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="javascript:;" id="trigger_fullscreen">
                            <i class="fa fa-move"></i> 全屏
                        </a>
                    </li>
                    <li>
                        <a href="extra_lock.html">
                            <i class="fa fa-lock"></i> 锁屏
                        </a>
                    </li>
                    <li>
                        <a href="/user/logout">
                            <i class="fa fa-key"></i> 注销
                        </a>
                    </li>
                </ul>
            </li>
            <!-- END USER LOGIN DROPDOWN -->
        </ul>
        <!-- END TOP NAVIGATION MENU -->
    </div>
    <!-- END TOP NAVIGATION BAR -->
</div>
<!-- header结束 -->
<div class="clearfix"></div>
<!-- container开始 -->
<div class="page-container">
    <!-- 侧边栏开始 -->
    <div class="page-sidebar-wrapper">
        <div class="page-sidebar navbar-collapse collapse">
            <!-- 侧边栏菜单按钮开始 -->
            <ul class="page-sidebar-menu" id="page-sidebar-menu">
                <li class="sidebar-toggler-wrapper">
                    <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
                    <div class="sidebar-toggler hidden-phone"></div>
                    <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
                </li>

                <!--这个暂时是不变的-->
                <li class="start active">
                    <!--跳转到控制器的地址-->
                    <a href="user/index" id="btn-dashboard">
                        <i class="fa fa-home"></i><span class="title"> 首页 </span><span
                            class="selected"> </span>
                    </a>
                </li>


                <!--下面的动态生成 js获取后台数据-->


         <%--   <li class="">
                    <!--跳转到控制器的地址-->
                    <a href="javascript:;">
                        <i class="fa fa-gears"></i><span class="title"> 系统管理 </span><span
                            class="arrow "> </span>
                    </a>
                    <ul class="sub-menu">
                        <!--跳转到控制器的地址-->
                        <li>
                            <a href="user/delete">
                                用户管理
                            </a>
                            <ul class="sub-menu">
                                <li>
                                    <a href="user/add">
                                        用户添加
                                    </a>
                                </li>
                                <li>
                                    <a href="user/delete">
                                        用户删除
                                    </a>
                                </li>
                                </ul>
                        </li>
                        <li>
                            <a href="javascript:;">
                                角色管理
                            </a>
                        </li>
                        <li>
                            <a href="javascript:;">
                                权限管理
                            </a>
                        </li>
                    </ul>
                </li>

                <li class="">
                    <a href="javascript:;">
                        <i class="fa fa-user-circle"></i>
                        <span class="title"> 个人中心 </span><span
                            class="arrow "> </span>
                    </a>
                    <ul class="sub-menu">
                        <li>
                            <a href="javascript:;">
                                信息修改
                            </a>
                        </li>
                        <li>
                            <a href="javascript:;">
                                密码修改
                            </a>
                        </li>

                        <!-- 测试权限控制 -->
                        <shiro:hasAnyRoles name="admin">
                            <li>
                                <a href="javascript:;">super_admin 拥有此角色</a>
                            </li>
                        </shiro:hasAnyRoles>

                        <shiro:hasPermission name="/user/create">
                            <li>
                                <a href="javascript:;">/user/create 拥有此权限</a>
                            </li>
                        </shiro:hasPermission>

                        <shiro:hasPermission name="/user/update">
                            <li>
                                <a href="javascript:;">/user/update 拥有此权限</a>
                            </li>
                        </shiro:hasPermission>

                    </ul>
                </li>--%>

            </ul>
            <!-- 侧边栏菜单按钮结束 -->
        </div>
    </div>
    <!-- 侧边栏结束 -->
    <!-- 主体开始 -->
    <div class="page-content-wrapper">
        <div class="page-content">
            <!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
            <div class="modal fade" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h4 class="modal-title">Modal title</h4>
                        </div>
                        <div class="modal-body">
                            Widget settings form goes here
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn blue">
                                Save changes
                            </button>
                            <button type="button" class="btn default" data-dismiss="modal">
                                Close
                            </button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
            <!-- /.modal -->
            <!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
            <!-- BEGIN STYLE CUSTOMIZER -->
            <div class="theme-panel hidden-xs hidden-sm">
                <div class="toggler"></div>
                <div class="toggler-close"></div>
                <div class="theme-options">
                    <div class="theme-option theme-colors clearfix">
                        <span> 主题颜色 </span>
                        <ul>
                            <li class="color-black current color-default" data-style="default"></li>
                            <li class="color-blue" data-style="blue"></li>
                            <li class="color-brown" data-style="brown"></li>
                            <li class="color-purple" data-style="purple"></li>
                            <li class="color-grey" data-style="grey"></li>
                            <li class="color-white color-light" data-style="light"></li>
                        </ul>
                    </div>
                    <div class="theme-option">
                        <span> 布局 </span>
                        <select class="layout-option form-control input-small">
                            <option value="fluid">顺序</option>
                            <option value="boxed">盒状</option>
                        </select>
                    </div>
                    <div class="theme-option">
                        <span> 标题 </span>
                        <select class="header-option form-control input-small">
                            <option value="fixed">固定</option>
                            <option value="default">默认</option>
                        </select>
                    </div>
                    <div class="theme-option">
                        <span> 工具栏 </span>
                        <select class="sidebar-option form-control input-small">
                            <option value="fixed">固定</option>
                            <option value="default">默认</option>
                        </select>
                    </div>
                    <div class="theme-option">
                        <span> 工具栏位置 </span>
                        <select class="sidebar-pos-option form-control input-small">
                            <option value="left">左边</option>
                            <option value="right">右边</option>
                        </select>
                    </div>
                    <div class="theme-option">
                        <span> 页脚 </span>
                        <select class="footer-option form-control input-small">
                            <option value="fixed">固定</option>
                            <option value="default">默认</option>
                        </select>
                    </div>
                </div>
            </div>
            <!-- END STYLE CUSTOMIZER -->

            <!-- BEGIN PAGE HEADER-->
            <!--菜单导航-->
            <div class="row">
                <div class="col-md-12">
                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                    <h3 class="page-title" id="index-page-title">Dashboard</h3>
                    <ul class="page-breadcrumb breadcrumb">
                        <li>
                            <i class="fa fa-home"></i>
                            <a href="javascript:;">
                                首页
                            </a>
                            <i class="fa fa-angle-right"></i>
                        </li>
                        <li>
                            <a href="javascript:;">
                                Dashboard
                            </a>
                        </li>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>
            <!-- END PAGE HEADER-->

            <!-- 页面主体 -->
            <div id="main-content">
          <%--  <iframe onload="changeFrameHeight()"  src="" id="mainContext" frameborder="0" marginheight="0"  scrolling="no" style="width: 100%"></iframe>
           --%>
            </div>

            <!-- 页面主体-->
        </div>
    </div>
    <!-- 主体结束 -->
</div>
<!-- container结束 -->
<!-- footer开始 -->
<div class="footer">
    <div class="footer-inner">
        2017-2018 &copy; sac By eason.
    </div>
    <div class="footer-tools">
        <span class="go-top"><i class="fa fa-angle-up"></i></span>
    </div>
</div>
<!-- footer结束-->
<!--[if lt IE 9]>
<script src="/resources/assets/plugins/respond.min.js"></script>
<script src="/resources/assets/plugins/excanvas.min.js"></script>
<![endif]-->
<script src="/resources/assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="/resources/assets/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<script src="/resources/assets/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
<script src="/resources/assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/resources/assets/plugins/bootstrap-hover-dropdown/twitter-bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="/resources/assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="/resources/assets/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="/resources/assets/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="/resources/assets/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>

<script src="/resources/assets/plugins/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/resources/assets/plugins/select2/select2.min.js"></script>

<script src="/resources/assets/scripts/app.js" type="text/javascript"></script>
<script type="text/javascript" src="/resources/app/js/index.js"></script>
<script type="text/javascript" src="/resources/assets/plugins/layer/layer.js"></script>
<!-- <script data-main="app/js/main" src="app/lib/requirejs/require.js"></script> -->
</body>
</html>