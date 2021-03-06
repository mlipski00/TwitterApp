<%--
  Created by IntelliJ IDEA.
  User: ML
  Date: 05.08.2018
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <link rel="stylesheet" href="login.css">
    <title>Title</title>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-login">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-6">
                            <a href="${pageContext.request.contextPath}/login" class="active" id="login-form-link">Login</a>
                        </div>
                        <div class="col-xs-6">
                            <a href="${pageContext.request.contextPath}/registration" id="register-form-link">Register</a>
                        </div>
                    </div>
                    <hr>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <c:if test="${registrationResult == 1}">
                            <div class="alert alert-success" role="alert">
                                User registered successfully
                            </div>
                        </c:if>
                        <div class="col-lg-12">
                            <form:form method="post" id="login-form" action="${pageContext.request.contextPath}/login"  modelAttribute="user"  role="form" style="display: block;">
                                <c:if test="${param.error != null}">
                                    <i class="failed">Invalid email/password</i>
                                </c:if>
                                <div class="form-group">
                                <form:input path="username" type="text" name="username" id="username" tabindex="1" class="form-control" placeholder="Email" value="m@wp.pl"/>
                                <form:errors path="username" cssClass="error"/>
                                </div>
                                <div class="form-group">
                                    <input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password" value="user123">
                                </div>
                                <%--<div class="form-group text-center">--%>
                                    <%--<input type="checkbox" tabindex="3" class="" name="remember" id="remember">--%>
                                    <%--<label for="remember"> Remember Me</label>--%>
                                <%--</div>--%>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="Log In">
                                        </div>
                                    </div>
                                </div>
                                <%--<div class="form-group">--%>
                                    <%--<div class="row">--%>
                                        <%--<div class="col-lg-12">--%>
                                            <%--<div class="text-center">--%>
                                                <%--<a href="#" tabindex="5" class="forgot-password">Forgot Password?</a>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
