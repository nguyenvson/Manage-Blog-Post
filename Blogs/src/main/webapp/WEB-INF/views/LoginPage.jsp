<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 3/9/2021
  Time: 2:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Blog Login</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/loginpage.css"/>"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<%--    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>--%>
    <script src="<c:url value="/resources/js/LoginCheck.js"/>" defer></script>
</head>
<body>
    <div class="header">
        <div class="blog"><b>Blog</b></div>
        <nav class="navbar navbar-expand-lg navbar-light bg-info">
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
        <div class="page-direction"><a href="">Home</a> >> Login</div>
        <div class="login">
            <div class="login-title">Login</div>
            <div class="login-content">
                <div>Please fill out the following form with your login credentials:</div>
                <div><i>Fields with <span style="color: red;">*</span> are required</i></div>
            </div>
            <form:form method="post" action="${pageContext.request.contextPath}/blog/loginuser" class="login-form"
                       modelAttribute="userToLogin" id="formLogin" onsubmit="return loginCheck()">
                <c:if test="${LoginWrongError}">
                    <div style="color: red">Wrong username or password</div>
                </c:if>
                <form:label path="userName">Username<span style="color: red;">*</span></form:label>
                <span class="error" id="errorUsername"></span></br>
                <form:input path="userName" type="text" id="username" name="userName"/></br>

                <form:label path="password">Password<span style="color: red;">*</span></form:label>
                <span class="error" id="errorPassword"></span></br>
                <form:input path="password" type="password" id="password" name="password"/></br>

                <div class="login-hint">Hint: You may login with username = username1 in roll ADMIN, <br/>
                    roll VIEWER with username = username2, username3, username4,5,6,7. (They all the same password)</div>
                <div class="login-remember"><input type="checkbox"> Rememberme next time.</div>
                <input type="submit" value="Login" class="submit">
            </form:form>
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

