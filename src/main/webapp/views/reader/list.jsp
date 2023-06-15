<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../utils/header.jsp" %>
	<h3 class="title">Tous les lecteurs</h3>
	<a href="new.reader" class="btn btn-dark">Ajouter</a>
	<hr/>
	<div class="row mt-4">
		<c:forEach items="${readers}" var="reader">
			<div class="col-md-3">
				<div class="card my-2">
					<img src="uploads/${reader.illustration }" class="card-img-top" alt="illustration" style="height:300px">
					<div class="card-body">
						<h5 class="card-title">${reader.name }</h5>
						<p class="card-text">${reader.lastname }</p>
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
