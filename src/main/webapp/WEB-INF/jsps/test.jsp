<%@ page import="com.test.model.TableBlogEntity" %>
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
                    List<TableBlogEntity> blogList = (List<TableBlogEntity>) request.getAttribute("blogList");

                    if (blogList != null) {
                        for (TableBlogEntity tableBlogEntity : blogList) {
                %>
                <tr>
                    <td>
                        <%=tableBlogEntity.getBlogId()%>
                    </td>
                    <%--<td>--%>
                        <%--<%=tableBlogEntity.getBlogAuthor()%>--%>
                    <%--</td>--%>
                    <td>
                        <%=tableBlogEntity.getBlogDate()%>
                    </td>
                    <td>
                        <%=tableBlogEntity.getBlogTitle()%>
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
