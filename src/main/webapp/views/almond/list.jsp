<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../utils/header.jsp" %>
	<h3 class="title">Liste des amendes</h3>
	<hr/>
	<table class="table table-bordered table-striped">
		<thead>
			<tr>
				<th>N°</th>
				<th>Nom </th>
				<th>Prenom</th>
				<th>Amende</th>
				<th>Payé</th>
				<th>Actions</th>			
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${almonds }" var="almond">
				<tr>
					<td>${almond.id }</td>
					<td>${almond.reader.name }</td>
					<td>${almond.reader.lastname }</td>
					<td>${almond.amount}</td>
					<td>
 						<c:choose>
							<c:when test="${almond.payed }">
								<span class="badge bg-success">Payé</span>
							</c:when>
							<c:otherwise>
								<span class="badge bg-danger">Non payé</span>
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${almond.payed }">
								<a href="delete.almond?id=${almond.id}" class="btn btn-sm btn-danger">
									supprimer
								</a>
							</c:when>
							<c:otherwise>
								<a href="payed.almond?id=${almond.id }" class="btn btn-secondary btn-sm">Payer</a>							
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
<%@ include file="../../utils/footer.jsp" %>
