<%@ page import="com.test.model.BlogEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.*" %>
<%--
  Created by IntelliJ IDEA.
  User: egguncle
  Date: 17-1-11
  Time: 下午10:27
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
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table  class="table table-striped">
                <thead>
                <tr>
                    <th>
                        编号
                    </th>
                    <%--<th>--%>
                        <%--作者--%>
                    <%--</th>--%>
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
                    List<BlogEntity> blogList = (List<BlogEntity>) request.getAttribute("blogList");

                    if (blogList != null) {
                        for (BlogEntity BlogEntity : blogList) {
                %>
                <tr>
                    <td>
                        <%=BlogEntity.getBlogId()%>
                    </td>
                    <%--<td>--%>
                        <%--<%=BlogEntity.getBlogAuthor()%>--%>
                    <%--</td>--%>
                    <td>
                        <%=BlogEntity.getBlogDate()%>
                    </td>
                    <td>
                        <%=BlogEntity.getBlogTitle()%>
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
