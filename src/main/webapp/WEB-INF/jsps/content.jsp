<%@ page import="com.test.model.TableBlogEntity" %><%--
  Created by IntelliJ IDEA.
  User: egguncle
  Date: 17-1-16
  Time: 下午2:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">BLOG CONTENT</a>
        </div>
    </div>
</nav>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <%
               TableBlogEntity blogEntity= (TableBlogEntity) request.getAttribute("blog");
            %>

            <div class="page-header">
                <h1>
                    <%=blogEntity.getBlogTitle()%>
                </h1>
            </div>
            <div>
                <label for="authorName" class="col-sm-2 control-label">作者:</label>
                  <p id="authorName"><%=blogEntity.getTableUserByUserId().getUsername()%></p>
            </div>
            <div>
                <label for="date" class="col-sm-2 control-label">日期:</label>
                   <p id="date"><%=blogEntity.getBlogDate()%></p>
            </div>

            <div>
                <p><%=blogEntity.getBlogContent()%></p>
            </div>


        </div>
    </div>
</div>
</body>
</html>

