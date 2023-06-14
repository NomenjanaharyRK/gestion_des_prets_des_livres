<%@page import="bean.ReaderBean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	ReaderBean reader = null;
	String action = "save.reader";
	if(request.getAttribute("reader") != null){
		reader = (ReaderBean) request.getAttribute("reader");
		action = "update.reader";
	}
%>
<%@ include file="../../utils/header.jsp" %>
	<h3 class="display-6">Formulaire</h3>
	<form class="form" action="${action}" method="post" enctype="multipart/form-data">
		<c:if test="${not empty reader.id }">
			<div class="form-group">
				<input type="hidden" class="form-control" name="id" value="${reader.id }"/>
			</div>
			<div class="form-group">
				<label for="id">Id</label>
				<input type="text" class="form-control" name="id" value="${reader.id }" disabled/>
			</div>		
		</c:if>
		<c:if test="${empty reader.id }">
			<div class="form-group">
				<label for="illustration">Illustration</label>
				<input type="file" class="form-control" name="illustration" value="${reader.illustration }" required/>
			</div>		
		</c:if>
		<div class="form-group">
			<label for="name">Nom</label>
			<input type="text" class="form-control" name="name" value="${reader.name }"/>
		</div>
		<div class="form-group">
			<label for="lastname">Prenom</label>
			<input type="text" class="form-control" name="lastname" value="${reader.lastname }"/>
		</div>
		<div class="form-group">
			<label for="address">Adresse</label>
			<input type="text" class="form-control" name="address" value="${reader.address }"/>
		</div>
		<div class="form-group">
			<label for="phone">Phone</label>
			<input type="text" class="form-control" name="phone" value="${reader.phone }"/>
		</div>
		<div class="form-group">
			<label for="email">Email</label>
			<input type="text" class="form-control" name="email" value="${reader.email }"/>
		</div>
		<div class="form-group">
			<label for="cin">Carte d'Identité</label>
			<input type="text" class="form-control" name="cin" value="${reader.cin }"/>
		</div>
		<div class="mt-2">
			<button class="btn btn-dark" type="submit">enregistrer</button>
			<a href="readers" class="btn btn-secondary">Annuler</a>	
		</div>
	</form>
<%@ include file="../../utils/footer.jsp" %>
