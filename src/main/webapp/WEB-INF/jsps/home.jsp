<%@ page import="com.test.model.TableBlogEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.test.model.TableUserEntity" %><%--
  Created by IntelliJ IDEA.
  User: egguncle
  Date: 17-1-11
  Time: 下午2:21
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="div" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>home</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" href="#">BLOG HOME</a>
    </div>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

        <ul class="nav navbar-nav navbar-right">
            <%
                if (session.getAttribute("user") == null) {
                    //还没登录
            %>
            <li>
                <a href="/login">Login</a>
            </li>
            <li>
                <a href="/registered">Registered</a>
            </li>
            <%
            } else {
                //已经登录
                TableUserEntity user = (TableUserEntity) session.getAttribute("user");
            %>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <span class="glyphicon glyphicon-user"></span>
                    <%=user.getUsername()%>
                    <strong class="caret"></strong>
                </a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="/jsps/edit_blog">edit blog</a>
                    </li>
                    <li>
                        <a href="/myblog">my blog</a>
                    </li>
                    <li class="divider">
                    </li>
                    <li>
                        <a href="/log_out">log out</a>
                    </li>
                </ul>
            </li>
            <%
                }
            %>
        </ul>
    </div>
</nav>


<h3 class="text-center">
    BLOG LIST
</h3>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>
                        编号
                    </th>
                    <th>
                        作者
                    </th>
                    <th>
                        发布时间
                    </th>
                    <th>
                        标题
                    </th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<TableBlogEntity> blogList = (List<TableBlogEntity>) request.getAttribute("blogList");
                    if (blogList != null) {
                        for (TableBlogEntity tableBlogEntity : blogList) {
                %>
                <tr>
                    <td>
                        <%=tableBlogEntity.getBlogId()%>
                    </td>
                    <td>
                        <%
                           if (tableBlogEntity.getTableUserByUserId()==null){
                        %>
                        未知
                        <%
                           }else{
                        %>
                        <%=tableBlogEntity.getTableUserByUserId().getUsername()%>
                        <%
                        }%>
                    </td>
                    <td>
                        <%=tableBlogEntity.getBlogDate()%>
                    </td>
                    <td>
                        <a href="/get_content/<%=tableBlogEntity.getBlogId()%>"><%=tableBlogEntity.getBlogTitle()%></a>
                    </td>
                </tr>
                <%
                        }
                    }
                %>
                </tbody>
            </table>
        </div>

    </div>
</div>
</body>
</html>
