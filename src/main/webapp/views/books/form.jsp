<%@page import="bean.BookBean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	BookBean book = null;
	String action = "save.book";
	if(request.getAttribute("book") != null){
		book = (BookBean) request.getAttribute("book");
		action = "update.book";
	}
%>
<%@ include file="../../utils/header.jsp" %>
	<h3 class="display-6">Formulaire</h3>
	
	<form class="form" action="${action}" method="post" enctype="multipart/form-data">
		<c:if test="${not empty book.id }">
			<div class="form-group">
				<input type="hidden" class="form-control" name="id" value="${book.id }"/>
			</div>
			<div class="form-group">
				<label for="id">Id</label>
				<input type="text" class="form-control" name="id" value="${book.id }" disabled/>
			</div>		
		</c:if>
		<c:if test="${empty book.id }">
			<div class="form-group">
				<label for="illustration">Illustration</label>
				<input type="file" class="form-control" name="illustration" value="${book.illustration }"/>
			</div>		
		</c:if>
		<div class="form-group">
			<label for="title">Titre</label>
			<input type="text" class="form-control" name="title" value="${book.title }"/>
		</div>
		<div class="form-group">
			<label for="author">Autheur</label>
			<input type="text" class="form-control" name="author" value="${book.author }"/>
		</div>
		<div class="form-group">
			<label for="published_at">Date d'edition</label>
			<input type="text" class="form-control" name="published_at" maxlength="4" min="4" value="${book.publishedAt }"/>
		</div>
		<div class="form-group">
			<label for="description">Description</label>
			<textarea class="form-control" name="description">${book.description }</textarea>
		</div>
		<div class="mt-2">
			<button class="btn btn-dark" type="submit">enregistrer</button>
			<a href="books" class="btn btn-secondary">Annuler</a>	
		</div>
	</form>
<%@ include file="../../utils/footer.jsp" %>
