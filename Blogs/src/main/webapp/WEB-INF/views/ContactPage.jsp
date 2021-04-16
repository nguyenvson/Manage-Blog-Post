<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 3/9/2021
  Time: 2:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Blog Contact</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/contactpage.css"/>"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="header">
        <div class="blog"><b>Blog</b></div>
        <nav class="navbar navbar-expand-sm navbar-light bg-info">
            <ul class="nav">
                <li class="nav-item">
                    <a class="nav-link" id="home" href="${pageContext.request.contextPath}/blog/home">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="about" href="${pageContext.request.contextPath}/blog/about">About</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="contact" href="${pageContext.request.contextPath}/blog/contact">Contact</a>
                </li>
                <c:if test="${!alreadyLogin}">
                    <li class="nav-item">
                        <a class="nav-link" id="login" href="${pageContext.request.contextPath}/blog/login">Login</a>
                    </li>
                </c:if>
                <c:if test="${alreadyLogin}">
                    <li class="nav-item">
                        <a class="nav-link" id="logout" href="${pageContext.request.contextPath}/blog/logout">Logout</a>
                    </li>
                </c:if>
                <li class="nav-item">
                    <a class="nav-link" id="insertData" href="${pageContext.request.contextPath}/blog/insertData">Insert Data</a>
                </li>
                <c:if test="${admin}">
                    <li class="nav-item">
                        <a class="nav-link" id="managePost" href="${pageContext.request.contextPath}/blog/managePost">Manage Post</a>
                    </li>
                </c:if>
            </ul>
        </nav>

    </div>
    <div class="body-content">
        <div class="page-direction"><a href="">Home</a> >> Contact</div>
        <div class="contact">
            <div class="contact-title">Contact Us</div>
            <div class="contact-content">
                <div>If you have business inquiries or other questions, please fill out the following form to contact us. Thank you.</div>
                <div><i>Fields with <span style="color: red;">*</span> are required</i></div>
            </div>
            <form action="/blog/contact" method="get" class="contact-form">
                <label>Name<span style="color: red;">*</span></label></br>
                <input type="text"/></br>
                <label>Email<span style="color: red;">*</span></label></br>
                <input type="text"/></br>
                <label>Subject<span style="color: red;">*</span></label></br>
                <input type="text"/></br>
                <label>Body<span style="color: red;">*</span></label></br>
                <textarea name="body" id="" cols="60" rows="3"></textarea></br>
                <input type="submit" value="Submit" class="submit">
            </form>
        </div>
    </div>
    <div class="end">
        <div class="end-signature">
            <div><footer>Copyright &copy; 2016 by Son Company.</footer></div>
            <div>All Rights Reserved.</div>
        </div>
    </div>
</body>
</html>
