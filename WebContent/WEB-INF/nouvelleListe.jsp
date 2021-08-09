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
<title>Nouvelle liste de courses</title>
</head>
<body>
<%@include file="fragments/entete.jspf" %>
<c:if test="${not empty requestScope.e}">
	 <p> erreur : ${e.getMessage()}</p>
	</c:if>
<h2>Nouvelle liste</h2>
<hr>
<form action="${pageContext.request.contextPath}/ajout/article" method="post">
	<div>
		<label for="nom">Nom : </label>
		<input type="text" id="nom" name="nom" size="60px" maxlength="1000" placeholder="le nom de votre liste">
	</div>
	<div>
		<label for="article">Article : </label>
		<input type="text" id="article" name="article" size="60px" maxlength="1000">
		<input type="submit" value="Ajouter">
	</div>
</form>

<%-- Afficher les articles quand ils sont ajoutés --%>
<c:if test="${not empty articles }"> 
<c:forEach var="courses" items="${courses}">
	<c:forEach var="article" items="${courses.article}" >
	<ul>
		<li>${article.nom}  <button onclick= >Panier</button>  <button onclick="window.location.href='${pageContext.request.contextPath}/supprimer/article?idArticle=${article.id}';" >Supprimer</button></li>
	</ul>
	</c:forEach>
	</c:forEach>
</c:if>

<hr>
<p class="bouton_bas"><button onclick="window.location.href='${pageContext.request.contextPath}/listescourses'">Revenir aux listes</button></p>

</body>
</html>