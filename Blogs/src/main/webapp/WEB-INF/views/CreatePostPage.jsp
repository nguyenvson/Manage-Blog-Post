<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 3/9/2021
  Time: 2:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Blog Create Post</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/createpostpage.css"/>"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="<c:url value="/resources/js/CreatePostCheck.js"/>" defer></script>
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
            <div class="page-direction"><a href="">Home</a> >> Create Post</div>
            <div class="create">
                <div class="create-title">Create Post</div>
                <div class="create-content">
                    <div><i>Fields with <span style="color: red;">*</span> are required</i></div>
                </div>
                <form:form action="/blog/managePost/createOrUpdatePost" method="post" modelAttribute="postInfor"
                           class="create-form" onsubmit="return createPostCheck()">
                    <c:if test="${TitleExist}">
                        <div style="color: red">Title already exist !!!</div>
                    </c:if>
                    <form:label path="title">Title<span style="color: red;">*</span></form:label>
                    <form:errors path="title" cssClass="error"/>
                    <span class="error" id="errorTitle"></span></br>
                    <form:input path="title" type="text" id="Title"/></br>

                    <form:label path="content">Content<span style="color: red;">*</span></form:label>
                    <form:errors path="content" cssClass="error"/>
                    <span class="error" id="errorContent"></span></br>
                    <form:textarea path="content" name="content-body" id="Content" cols="60" rows="3"></form:textarea></br>
                    <div><i>You may use <a href="">Markdown syntax</a>.</i></div>

                    <label>Tags<span style="color: red;">*</span></label>
                    <span class="error" id="errorTags"></span></br>

                    <input type="text" name="tagsInput" value="<c:forEach var="tag" items="${tags}">${tag.name}, </c:forEach>" id="Tags">
                    <div><i>Please separate different tags with commas.</i></div>

                    <form:label path="status" class="lb-status">Status<span style="color: red;">*</span></form:label>

                    <form:select path="status" name="status" id="" >
                        <c:choose>
                            <c:when test="${postInfor.status eq 'draft'}">
                                <option value="draft" selected>Draft</option>
                                <option value="published" >Published</option>
                                <option value="outdated" >Outdated</option>
                            </c:when>
                            <c:when test="${(postInfor.status eq 'published')}">
                                <option value="published" selected>Published</option>
                                <option value="draft" >Draft</option>
                                <option value="outdated" >Outdated</option>
                            </c:when>
                            <c:when test="${postInfor.status eq 'outdated'}">
                                <option value="outdated" selected>Outdated</option>
                                <option value="draft" >Draft</option>
                                <option value="published" >Published</option>
                            </c:when>
                            <c:otherwise>
                                <option value="draft" >Draft</option>
                                <option value="published" >Published</option>
                                <option value="outdated" >Outdated</option>
                            </c:otherwise>
                        </c:choose>
                    </form:select></br>

                    <input type="hidden" name="idPost" value="${idPost}"/>

                    <input type="submit" value="Submit" class="create">
                </form:form>
            </div>
        </div>
        <div class="body-content-right">
            <div class="menu">
                <div class="title-tag">Menu</div>
                <div class="menus"><a href="/blog/createPost">Create New Post</a></div>
                <div class="menus"><a href="/blog/managePost">Manage Post</a></div>
                <div class="menus"><a href="/blog/approveCmt">Approve Comments</a> (${countCmtDisapprove})</div>
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
