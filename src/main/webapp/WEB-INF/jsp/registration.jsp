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
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <link rel="stylesheet" href="login.css">
    <title>Title</title>
</head>
<body>
<style>
    .error {
        color: red;
    }
</style>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-login">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-6">
                            <a href="/login" id="login-form-link">Login</a>
                        </div>
                        <div class="col-xs-6">
                            <a href="/registration" class="active"id="register-form-link">Register</a>
                        </div>
                    </div>
                    <hr>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <form:form method="post" modelAttribute="user" id="register-form" action="/registration" role="form" style="display: block;">
                                <div class="form-group">
                                    <form:input path="username" type="text" name="username" id="username" tabindex="1" class="form-control" placeholder="Username" value=""/>
                                    <form:errors path="username" cssClass="error"/>
                                </div>
                                <div class="form-group">
                                    <form:input path="email" type="email" name="email" id="email" tabindex="1" class="form-control" placeholder="Email Address" value=""/>
                                    <form:errors path="email" cssClass="error"/>
                                </div>
                                <div class="form-group">
                                    <form:input path="password" type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password"/>
                                    <form:errors path="password" cssClass="error"/>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-register" value="Register Now">
                                        </div>
                                    </div>
                                </div>
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
