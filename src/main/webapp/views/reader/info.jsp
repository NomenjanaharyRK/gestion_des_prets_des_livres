<%@ include file="../../utils/header.jsp" %>
	<h3 class="title">Information du lecteur </h3>
	<hr/>
	<div class="row">
		<div class="col-md-3">
			<img alt="image" src="uploads/${reader.illustration }" class="d-flex img-fluid"/>
		</div>
		<div class="col-md-6">
			<table class="table">
				<tbody>
					<tr>
						<td><strong>Nom</strong></td>
						<td>${reader.name }</td>
					</tr>
					<tr>
						<td><strong>Prenom</strong></td>
						<td>${reader.lastname }</td>
					</tr>
					<tr>
						<td><strong>Adresse</strong></td>
						<td>${reader.address }</td>
					</tr>
					<tr>
						<td><strong>Telephone</strong></td>
						<td>${reader.phone }</td>
					</tr>
					<tr>
						<td><strong>Email</strong></td>
						<td>${reader.email }</td>
					</tr>	
					<tr>
						<td><strong>Nombre de pret actuel</strong></td>
						<td>${reader.nbPretActuel }</td>
					</tr>	
					<tr>
						<td><strong>Nombre de pret total effectué</strong></td>
						<td>${reader.nbPretTotal }</td>
					</tr>				
				</tbody>
			</table>
		</div>
	</div>
<%@ include file="../../utils/footer.jsp" %>