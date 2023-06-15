<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../utils/header.jsp" %>
	<h3 class="title">Liste des prêts</h3>
	<a href="new.lend" class="btn btn-dark">Ajouter</a>
	<hr/>
	<table class="table table-bordered table-striped">
		<thead>
			<tr>
				<th>N°</th>
				<th>Titre</th>
				<th>Autheur</th>
				<th>Lecteur</th>
				<th>Date De Pret</th>
				<th>Date de Retour</th>
				<th>Status</th>
				<th>Actions</th>			
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${lends }" var="lend">
				<tr>
					<td>${lend.id }</td>
					<td>${lend.book.title }</td>
					<td>${lend.book.author }</td>
					<td>${lend.reader.name } ${lend.reader.lastname }</td>
					<td>${lend.startDate }</td>
					<td>${lend.endDate }</td>
					<td>
 						<c:choose>
							<c:when test="${lend.finished }">
								<span class="badge bg-success">Terminer</span>
							</c:when>
							<c:otherwise>
								<span class="badge bg-info">En cours</span>
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${lend.finished }">
								<a href="delete.lend?id=${lend.id}" class="btn btn-sm btn-danger">
									supprimer
								</a>
							</c:when>
							<c:otherwise>
								<a href="finished.lend?id=${lend.id }" class="btn btn-secondary btn-sm">Terminer</a>							
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
<%@ include file="../../utils/footer.jsp" %>
