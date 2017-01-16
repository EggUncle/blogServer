<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: egguncle
  Date: 17-1-13
  Time: 上午11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#"> Login Page</a>
        </div>
    </div>
</nav>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-6 column">
            <div class="page-header">
                <h1>
                    Login Page
                </h1>
            </div>
            <form:form  class="form-horizontal" role="form" method="POST" modelAttribute="my_login" action="/login_user">
                <div class="form-group">
                    <label for="username" class="col-sm-2 control-label">userName</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="username" name="username"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="userpasswd" class="col-sm-2 control-label">password</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="userpasswd" name="userpasswd"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">Login</button>
                    </div>
                </div>

                <%
                   if (!(boolean)request.getAttribute("login")){
                %>
                <div class="alert alert-danger">
                    <a href="#" class="alert-link">用户名或密码错误</a>
                </div>
                <%
                   }
                %>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
