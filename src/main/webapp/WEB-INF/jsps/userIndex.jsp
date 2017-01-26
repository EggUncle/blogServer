<%@ page import="com.test.model.UserEntity" %>
<%@ page import="com.test.model.BlogEntity" %>
<%@ page import="java.util.List" %>
<!DOCTYPE HTML>
<!--
Strata by HTML5 UP
html5up.net | @ajlkn
Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
    <title>Strata by HTML5 UP</title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <!--[if lte IE 8]>
    <script src="/static/assets/js/ie/html5shiv.js"></script><![endif]-->
    <link rel="stylesheet" href="/static/assets/css/main.css"/>
    <!--[if lte IE 8]>
    <link rel="stylesheet" href="/static/assets/css/ie8.css"/><![endif]-->
</head>
<body id="top">
<%
    //获取用户实例
    UserEntity userEntity = (UserEntity) request.getAttribute("userInfo");
    // userEntity.getBgPath();
    String bgPath = userEntity.getBgPath();
    String iconPath = userEntity.getIconPath();
    //当没有设置图片时，使用默认图片
    if (bgPath==null) {
        bgPath = "/static/images/icon438de0bfcd9f448b8d3222fd1b2d5396.jpeg";
    }
    if (iconPath==null) {
        iconPath = "/static/images/icon438de0bfcd9f448b8d3222fd1b2d5396.jpeg";
    }
    //获取博客列表
    List<BlogEntity> blogList = (List<BlogEntity>) request.getAttribute("blogList");


%>

<!-- Header -->
<header id="header" background-image="<%=bgPath%>">
    <div class="inner">
        <a href="#" class="image avatar">
            <img src="<%=iconPath%>" alt=""/>
        </a>
        <h1><strong>I am Strata</strong>, a super simple<br/>
            responsive site template freebie<br/>
            crafted by <a href="http://html5up.net">HTML5 UP</a>.</h1>
    </div>
</header>

<!-- Main -->
<div id="main">

    <!-- One -->
    <%--<section id="one">--%>
        <%--<header class="major">--%>
            <%--<h2>Ipsum lorem dolor aliquam ante commodo<br/>--%>
                <%--magna sed accumsan arcu neque.</h2>--%>
        <%--</header>--%>
        <%--<p>Accumsan orci faucibus id eu lorem semper. Eu ac iaculis ac nunc nisi lorem vulputate lorem neque cubilia ac--%>
            <%--in adipiscing in curae lobortis tortor primis integer massa adipiscing id nisi accumsan pellentesque commodo--%>
            <%--blandit enim arcu non at amet id arcu magna. Accumsan orci faucibus id eu lorem semper nunc nisi lorem--%>
            <%--vulputate lorem neque cubilia.</p>--%>
        <%--<ul class="actions">--%>
            <%--<li><a href="#" class="button">Learn More</a></li>--%>
        <%--</ul>--%>
    <%--</section>--%>

    <!-- Two -->
    <section id="two">
        <h2>Blog List</h2>
        <div class="row">
            <%for (BlogEntity blog : blogList) {%>
            <article class="6u 12u$(xsmall) work-item">
                <a href="<%="".equals(blog.getImgPath()==null)?"/static/images/fulls/01.jpg":blog.getImgPath()%>" class="image fit thumb">
                    <img src="<%=(blog.getImgPath()==null)?"/static/images/fulls/01.jpg":blog.getImgPath()%>"
                    alt=""/></a>
                <h3><%=blog.getBlogTitle()%></h3>
                <p>Lorem ipsum dolor sit amet nisl sed nullam feugiat.</p>
            </article>
            <%
                }
            %>
        </div>
        <ul class="actions">
            <li><a href="#" class="button">Full Portfolio</a></li>
        </ul>
    </section>


</div>

<!-- Footer -->
<footer id="footer">
    <div class="inner">
        <ul class="icons">
            <li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
            <li><a href="#" class="icon fa-github"><span class="label">Github</span></a></li>
            <li><a href="#" class="icon fa-dribbble"><span class="label">Dribbble</span></a></li>
            <li><a href="#" class="icon fa-envelope-o"><span class="label">Email</span></a></li>
        </ul>
        <ul class="copyright">
            <li>&copy; Untitled</li>
            <li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
        </ul>
    </div>
</footer>

<!-- Scripts -->
<script src="/static/assets/js/jquery.min.js"></script>
<script src="/static/assets/js/jquery.poptrox.min.js"></script>
<script src="/static/assets/js/skel.min.js"></script>
<script src="/static/assets/js/util.js"></script>
<!--[if lte IE 8]>
<script src="/static/assets/js/ie/respond.min.js"></script><![endif]-->
<script src="/static/assets/js/main.js"></script>

</body>
</html>