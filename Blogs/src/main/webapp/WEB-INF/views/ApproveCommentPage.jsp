<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 3/9/2021
  Time: 2:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Blog Manage Post</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/managepostpage.css"/>"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="<c:url value="/resources/js/ApproveComment.js"/>" defer></script>
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
            <div class="page-direction"><a href="">Home</a> >> Approve Comments</div>
            <div class="manage">
                <div class="manage-title">Approve Comments</div>
                <div class="manage-content">
                    <div><i>Displaying <span id="contain-number-cmt"><span id="number-cmt">${allCmts.size()}</span></span> result(s).</i></div>
                </div>
                <table class="table table-striped table-bordered border-primary">
                    <thead>
                    <tr class="bg-info">
                        <th class="col-title">Post Title</th>
                        <th class="col-status">Comment Content</th>
                        <th class="col-time">Author</th>
                        <th class="col-status-cmt">Status</th>
                        <th class="col-delete">Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="cmt" items="${allCmts}" >
                            <tr id="cmt-${cmt.id}" class="cmts-id">
                                <td>${cmt.post.title}</td>
                                <td>${cmt.content}</td>
                                <td>${cmt.author.name}</td>
                                <td id="status-cmt-${cmt.id}">
                                    <select name="status" id="select-status-cmt-${cmt.id}" class="cmts-status" onchange="submitApprove(${cmt.id})">
                                        <c:choose>
                                            <c:when test="${cmt.status eq 'Approve'}">
                                                <option value="Approve" selected>Approve</option>
                                                <option value="Disapprove" >Disapprove</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="Approve">Approve</option>
                                                <option value="Disapprove" selected>Disapprove</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </select>
                                </td>
                                <td><span class="delete" onclick="deleteCmt(${cmt.id})">
                                    <img src="/resources/css/pic/x.png"></span></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="body-content-right">
            <div class="menu">
                <div class="title-tag">Menu</div>
                <div class="menus"><a href="/blog/createPost">Create New Post</a></div>
                <div class="menus"><a href="/blog/managePost">Manage Post</a></div>
                <div class="menus"><a href="/blog/approveCmt">Approve Comments</a>
                    (<span id="contain-number-approve-cmt"><span id="number-approve-cmt">${countCmtDisapprove}</span></span>)</div>
                <div class="menus"><a href="/blog/logout">Logout</a></div>
            </div></br>
            <div class="tag-part">
                <div class="title-tag">Tags</div>
                <div class="tags">
                    <c:forEach var="tag" items="${allTags}">
                        <a href="">${tag.name}</a>,
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
