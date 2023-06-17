<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String status = String.valueOf(request.getAttribute("status"));
%>
<%@ include file="../../utils/header.jsp" %>
	<h3 class="title">Liste des prêts</h3>
	<div>
		<div class="d-flex justify-content-between align-items-center">
			<a href="new.lend" class="btn btn-dark">Ajouter</a>
			<a href="search" class="btn btn-dark">recherche avancée</a>
			<form class="d-flex mb-2" action="filter.lend" method="get" id="filterStatusForm">
				<div class="form-group">
					<select class="form-select" name="status" onchange="submitForm()">
						<option value="finished" <% if(status.equals("finished")){%> <%= "selected" %> <%} %> >Terminé</option>
						<option value="current" <% if(status.equals("current")){%> <%= "selected" %> <%} %> >En cours</option>
						<option value="late" <% if(status.equals("late")){%> <%= "selected" %> <%} %> >En retard</option>
					</select>		
				</div>
			</form>		
		</div>
		<table class="mt-1 table table-bordered table-striped table-sm">
			<thead>
				<tr>
					<th>N°</th>
					<th>Titre</th>
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
	</div>
	<script type="text/javascript">
		function submitForm(){
			document.forms["filterStatusForm"].submit();
		}
	</script>
<%@ include file="../../utils/footer.jsp" %>
