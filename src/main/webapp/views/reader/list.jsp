<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../utils/header.jsp" %>
	<h3 class="title">Tous les lecteurs</h3>
	<div class="d-flex justify-content-between align-items-center">
		<a href="new.reader" class="btn btn-dark">Ajouter</a>
		<form class="form d-flex" action="search.reader" method="get">
			<div class="form-group">
				<input type="text" name="key" placeholder="nom ou prenom" class="form-control" value="${key}"/>		
			</div>
			<button type="submit" class="mx-1 btn btn-dark btn-sm">rechercher</button>
		</form>
	</div>
	<hr/>
	<div class="row mt-4">
		<c:forEach items="${readers}" var="reader">
			<div class="col-md-3">
				<div class="card my-2">
					<img src="uploads/${reader.illustration }" class="card-img-top" alt="illustration" style="height:300px">
					<div class="card-body">
						<h5 class="card-title">${reader.name }</h5>
					</div>
					<div class="card-footer">
						<a href="info.reader?id=${reader.id }" title="voir lecteur" class="btn btn-info">
							<i class="fas fa-eye text-white"></i>						
						</a>
						<a href="update.reader?id=${reader.id }" title="modifier lecteur" class="btn btn-secondary">
							<i class="fa fa-edit"></i>
						</a>
						<a href="delete.reader?id=${reader.id }" title="supprimer lecteur" class="btn btn-danger" onclick="return confirm('etes vous sûr de vouloir supprimer ce livre ?')">
							<i class="fas fa-trash"></i>
						</a>
					</div>
				</div>
			</div>		
		</c:forEach>
	</div>
<%@ include file="../../utils/footer.jsp" %>
