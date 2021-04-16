<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 3/9/2021
  Time: 2:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Blog Home</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/homepage.css"/>"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="<c:url value="/resources/js/HomePage.js"/>" defer></script>
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
        <div class="body-content-left">
            <div id="post-content-left">
                <c:forEach var="post" items="${allPublicPosts}">
                    <div class="post">
                        <div class="post-title"><b>${post.title}</b></div>
                        <div class="post-day-create">Posted by ${post.user.name} on ${post.dateCreate}</div>
                        <div class="post-content">${post.content}</div>
                        <div class="post-tags-cmts" id="post-tags-cmts-${post.id}">
                            <div class="post-tag">Tags: <c:forEach var="tag" items="${post.tags}">
                                <a href="">${tag.name}</a>, </c:forEach>
                            </div>
                            <div class="post-cmt"><a href="">Permalink</a> | <span id="post-comment" onclick="loadComment(${post.id})">Comments (${post.comments.size()})
                            </span>| <span class="post-day-update">Last updated on ${post.dateUpdate}</span>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <input id="LoadMore" type="submit" value="View more" onclick="loadMore()">
        </div>
        <div class="body-content-right">
            <div class="tag-part">
                <div class="title-tag">Tags</div>
                <div class="tags">
                    <c:forEach var="tag" items="${allTags}">
                        <a href="/blog/findPostByTag/${tag.id}">${tag.name}</a>,
                    </c:forEach>
                </div>
            </div></br>
            <div class="recent-cmts">
                <div class="title-tag">Recent Comments</div>
                <div class="cmts">Last Comment By <a href="">${lastComment.author.name}</a> in
                    <a href="">${lastComment.post.title}</a> post.
                </div>
            </div>
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
