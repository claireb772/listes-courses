<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<title>Listes de courses</title>
</head>
<body>
<%@include file="fragments/entete.jspf" %>
	<c:if test="${not empty requestScope.e}">
	 <p> erreur : ${e.getMessage()}</p>
	</c:if>
<h2>Listes préféfinies</h2>
<hr>
	<c:if test="${not empty listeCourses }"> 
	<c:forEach var="courses" items="${listeCourses}" >
	<ul>
		<li>${courses.nom}  <button onclick= >Panier</button>  <button onclick="window.location.href='${pageContext.request.contextPath}/supprimer/courses?idCourses=${courses.id}';" >Supprimer</button></li>
	</ul>
	</c:forEach>
</c:if>
<c:if test="${not empty requestScope.message}">
	<p>${message}</p>
</c:if>
<hr>
<p class="bouton_bas"><button onclick="window.location.href='${pageContext.request.contextPath}/ajout/article'">Nouvelle liste</button><p>

</body>
</html>