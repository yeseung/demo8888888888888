<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="<span><i class='fas fa-home'></i></span> <span>HOME</span>" />

<%@ include file="../common/head.jspf" %>



<div>
    <c:forEach items="${articles}" var="articles">
        <div>
            <a href="../article/detail?id=${articles.id}">${articles.title} /
                ${articles.extra__writerName}</a>
        </div>
    </c:forEach>
</div>

<br><br>

<div>
    <c:forEach items="${article}" var="article">
        <div><a href="../article/detail?id=${article.id}">${article.board.title}</a> /
            ${article.cnt}</div>
    </c:forEach>
</div>





<%@ include file="../common/foot.jspf" %>