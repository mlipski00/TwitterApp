<%--
  Created by IntelliJ IDEA.
  User: ML
  Date: 05.08.2018
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script
    <link rel="stylesheet" href="changePass.css">
    <title>Login Page</title>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-sm-4">

            <label>Current Password</label>
            <div class="form-group pass_show">
                <input type="password" value="faisalkhan@123" class="form-control" placeholder="Current Password">
            </div>
            <label>New Password</label>
            <div class="form-group pass_show">
                <input type="password" value="faisal.khan@123" class="form-control" placeholder="New Password">
            </div>
            <label>Confirm Password</label>
            <div class="form-group pass_show">
                <input type="password" value="faisal.khan@123" class="form-control" placeholder="Confirm Password">
            </div>

        </div>
    </div>
</div>
<script src="changePass.js"></script>
</body>
</html>
