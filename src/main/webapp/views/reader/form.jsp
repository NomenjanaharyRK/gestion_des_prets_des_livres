<%@page import="bean.ReaderBean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../utils/header.jsp" %>
	<h3 class="display-6">Formulaire</h3>
	<form class="form" action="new.reader" method="post" enctype="multipart/form-data">
		<div class="form-group">
			<label for="illustration">Illustration</label>
			<input type="file" class="form-control" name="illustration" required/>
		</div>		
		<div class="form-group">
			<label for="name">Nom</label>
			<input type="text" class="form-control" name="name" />
		</div>
		<div class="form-group">
			<label for="lastname">Prenom</label>
			<input type="text" class="form-control" name="lastname"/>
		</div>
		<div class="form-group">
			<label for="address">Adresse</label>
			<input type="text" class="form-control" name="address"/>
		</div>
		<div class="form-group">
			<label for="phone">Phone</label>
			<input type="text" class="form-control" name="phone"/>
		</div>
		<div class="form-group">
			<label for="email">Email</label>
			<input type="text" class="form-control" name="email"/>
		</div>
		<div class="form-group">
			<label for="cin">Carte d'Identit�</label>
			<input type="text" class="form-control" name="cin"/>
		</div>
		<div class="mt-2">
			<button class="btn btn-dark" type="submit">enregistrer</button>
			<a href="readers" class="btn btn-secondary">Annuler</a>	
		</div>
	</form>
<%@ include file="../../utils/footer.jsp" %>
