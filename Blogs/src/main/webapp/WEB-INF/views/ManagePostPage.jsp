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
    <script>
        function deletePost(idPost){
            let numberOfContent = parseInt($("#number-content").text());
            numberOfContent--;
            if (numberOfContent >= 0){
                var newNumberCmt = "<span id=\"number-content\">"+ numberOfContent + "</span>";
                $("#number-content").remove();
                $("#contain-number-content").append(newNumberCmt);
            };
            var nexUrl = "/blog/managePost/delete/" + idPost;
            $.ajax({ url: nexUrl, method: "get", success: function (result){
                    $("#delete-" + idPost).remove();
                },
                fail: function(){
                    console.log("fail");
                }
            })
        }
    </script>
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
            <div class="page-direction"><a href="">Home</a> >> Manage Posts</div>
            <div class="manage">
                <div class="manage-title">Manage Posts</div>
                <div class="manage-content">
                    <div><i>Displaying <span id="contain-number-content"><span id="number-content">${allPosts.size()}</span></span> result(s).</i></div>
                </div>
                <table class="table table-striped table-bordered border-primary">
                    <thead>
                    <tr class="bg-info">
                        <th class="col-title">Title</th>
                        <th class="col-status">Status</th>
                        <th class="col-time">Create Time</th>
                        <th class="col-option">Option</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="post" items="${allPosts}" >
                            <tr id="delete-${post.id}">
                                <td class="col-title">${post.title}</td>
                                <td class="col-status">${post.status}</td>
                                <td class="col-time">${post.dateCreate}</td>
                                <td class="col-option"><a><img src="/resources/css/pic/search.png"></a>
                                    <a href="/blog/managePost/edit/${post.id}"><img src="/resources/css/pic/pen.png"></a>
                                    <span class="delete" onclick="deletePost(${post.id})"><img src="/resources/css/pic/x.png"></span>
                                </td>
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
                <div class="menus"><a href="/blog/approveCmt">Approve Comments</a>(${countCmtDisapprove})</div>
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
