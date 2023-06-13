<%@ include file="utils/header.jsp"%>
<div class="d-flex justify-content-center align-items-center" style="height:80vh; width:100%">
	<div class="card" style="width:60%">
		<div class="card-header">
			<h4 class="card-title">Formulaire de connexion</h4>
		</div>
		<div class="card-body">
			<form class="form" action="login" method="post">
				<div class="form-group">
					<label for="username">Nom d'utilisateur</label>
					<input type="text" class="form-control" name="username"/>		
				</div>
				<div class="form-group">
					<label for="password">Mot de passe</label>
					<input type="password" class="form-control" name="password"/>		
				</div>
				<button type="submit" class="mt-2 btn btn-dark">Connexion</button>			
			</form>			
		</div>
	</div>
</div>
<%@ include file="utils/footer.jsp"%>