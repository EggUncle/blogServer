<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: egguncle
  Date: 17-1-14
  Time: 下午1:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    request.setAttribute("basePath", basePath);
%>
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
            <a class="navbar-brand" href="#">Registered Page</a>
        </div>
    </div>
</nav>
<div class="container  center-block">
    <div class="row clearfix">
        <div class="col-md-6 column ">
            <div class="page-header">
                <h1>
                    Registered Page
                </h1>
            </div>
            <form:form  class="form-horizontal" role="form" method="POST" modelAttribute="my_user" action="/add_user">
                <!--用户名-->
                <div class="form-group">
                    <label for="username" class="col-sm-2 control-label">userName</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="username" name="username"/>
                    </div>
                </div>
                <!--密码-->
                <div class="form-group">
                    <label for="userpasswd" class="col-sm-2 control-label">password</label>
                    <div class="col-sm-10">
                        <input type="userpasswd" class="form-control" id="userpasswd" name="userpasswd"/>
                    </div>
                </div>
                <!--昵称-->
                <div class="form-group">
                    <label for="nickname" class="col-sm-2 control-label">userName</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="nickname" name="nickname"/>
                    </div>
                </div>
                <!--头像-->
                <%--<div class="form-group">--%>
                    <%--<label for="inputIcon">头像</label>--%>
                    <%--<input type="file" id="inputIcon" name="iconFile">--%>
                    <%--&lt;%&ndash;<p class="help-block">这里是块级帮助文本的实例。</p>&ndash;%&gt;--%>
                <%--</div>--%>



                <!--背景图片-->
                <div class="form-group">
                    <label for="inputBg">背景图片</label>
                    <input type="file" id="inputBg" name="bgPath">
                    <%--<p class="help-block">这里是块级帮助文本的实例。</p>--%>
                </div>

                <!--描述-->
                <div class="form-group">
                    <label for="description" class="col-sm-2 control-label">description</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="description" name="description"/>
                    </div>
                </div>


                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">Registered</button>
                    </div>
                </div>

                <%
                    if ((boolean)request.getAttribute("repeat")){
                %>
                <div class="alert alert-danger">
                    <a href="#" class="alert-link">用户名已存在</a>
                </div>
                <%
                    }
                %>
            </form:form>

            <form method="post" action="/file/upload" enctype="multipart/form-data">
                选择一个文件:
                <input type="file" name="uploadFile" />
                <br/><br/>
                <input type="submit" value="上传" />
            </form>
        </div>
    </div>
</div>

</body>
</html>