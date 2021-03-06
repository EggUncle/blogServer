<%@ page import="com.test.model.UserEntity" %>
<%@ page import="com.test.model.BlogEntity" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: egguncle
  Date: 17-1-11
  Time: 下午11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html>
<html>
<head>
    <title>edit_blog</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Edit Page</a>
        </div>
    </div>
</nav>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    EDIT BLOG
                </h1>
            </div>
            <form  method="POST" modelAttribute="my_blog" action="/add_blog" enctype="multipart/form-data">
                <div class="form-group">
                    <label name="userId"><%=((UserEntity)session.getAttribute("user")).getUserId()%></label>
                </div>
                <!--标题图-->
                <div class="form-group">
                    <label for="inputImg">标题图</label>
                    <div class="col-sm-10">
                        <input type="file" id="inputImg" name="imageFile">
                    </div>
                    <%--<p class="help-block">这里是块级帮助文本的实例。</p>--%>
                </div>

                <div class="form-group">
                    <label for="blogTitle">title</label><input type="text" class="form-control" id="blogTitle"  name="blogTitle"/>
                </div>
                <div class="form-group">
                    <label for="blogContent">content</label>
                    <%--<input type="text" class="form-control" id="blogContent"  name="blogContent" />--%>
                    <textarea class="form-control" id="blogContent" name="blogContent" rows="30"></textarea>
                </div>
                <button type="submit" class="btn btn-default">submit</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
