<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../utils/header.jsp" %>
	<h3 class="display-6">Formulaire</h3>
	
	<form class="form" action="save.lend" method="post">
		<div class="form-group">
			<label for="reader">Lecteur</label>
			<select class="form-select" name="reader">
				<c:forEach items="${readers }" var="reader">
					<option value="${reader.id }"> ${reader.name}  ${reader.lastname }</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group">
			<label for="book">Livre</label>
			<select class="form-select" name="book">
				<c:forEach items="${books }" var="book">
					<option value="${book.id }"> "${book.title}"  ${book.author }</option>
				</c:forEach>				
			</select>
		</div>
		<div class="form-group">
			<label for="start_date">Date de pret</label>
			<input type="date" name="start_date" class="form-control"/>
		</div>
		<div class="mt-2">
			<button class="btn btn-dark" type="submit">enregistrer</button>
			<a href="lends" class="btn btn-secondary">Annuler</a>	
		</div>
	</form>
<%@ include file="../../utils/footer.jsp" %>
