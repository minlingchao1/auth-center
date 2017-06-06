<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>云贝用户登录中心</title>

  <spring:theme code="standard.custom.css.file" var="customCssFile" />
  <spring:theme code="yunbei.custom.bootstrap" var="bootrap" />
  <spring:theme code="yunbei.custom.colors" var="colors" />
  <spring:theme code="yunbei.custom.core" var="core" />
  <spring:theme code="yunbei.custom.icon" var="icon" />
  <spring:theme code="yunbei.custom.components" var="components" />

  <link rel="stylesheet" href="<c:url value="${icon}" />" />
  <link rel="stylesheet" href="<c:url value="${bootrap}" />" />
  <link rel="stylesheet" href="<c:url value="${core}" />" />
  <link rel="stylesheet" href="<c:url value="${components}" />" />
  <link rel="stylesheet" href="<c:url value="${colors}" />" />
  <link rel="stylesheet" href="<c:url value="${customCssFile}" />" />

  <link rel="stylesheet" href="<c:url value="${colors}" />" />


  <link rel="icon" href="<c:url value="/favicon.ico" />" type="image/x-icon" />

  <!--[if lt IE 9]>
    <script src="//cdnjs.cloudflare.com/ajax/libs/html5shiv/3.6.1/html5shiv.js" type="text/javascript"></script>
  <![endif]-->
</head>
<body class="pace-done sidebar-xs-indicator"><div class="pace  pace-inactive"><div class="pace-progress" data-progress-text="100%" data-progress="99" style="transform: translate3d(100%, 0px, 0px);">
  <div class="pace-progress-inner"></div>
</div>
  <div class="pace-activity"></div></div>

<!-- Main navbar -->

<!-- /main navbar -->


<!-- Page container -->
<div class="page-container login-container" style="min-height:487px">

  <!-- Page content -->
  <div class="page-content">

    <!-- Main content -->
    <div class="content-wrapper">


