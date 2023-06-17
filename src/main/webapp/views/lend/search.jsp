<%@ include file="../../utils/header.jsp" %>
	<div class="row">
		<div class="col-md-3">
			<div class="card mb-2">
				<div class="card-header">
					<h5>recherche dans un mois</h5>
				</div>
				<div class="card-body">
					<form class="form" action="month.search" method="get">
						<div class="form-group">
							<label for="reader">Lecteur</label>
							<select name="reader" class="form-select">
								<c:forEach items="${readers}" var="reader">
									<option value="${reader.id }">${reader.name } ${reader.lastname }</option>					
								</c:forEach>
							</select>								
						</div>
						<div class="form-group">
							<label for="month">Mois</label>
							<input type="date" name="month" class="form-control" value="${month }" required/>
						</div>
						<button type="submit" class="mt-1 btn btn-dark">rechercher</button>
					</form>
				</div>
			</div>
			<div class="card mb-2">
				<div class="card-header">
					<h5>recherche dans une année</h5>
				</div>
				<div class="card-body">
					<form class="form" action="year.search" method="get">
						<div class="form-group">
							<label for="reader">Lecteur</label>
							<select name="reader" class="form-select">
								<c:forEach items="${readers}" var="reader">
									<option value="${reader.id }">${reader.name } ${reader.lastname }</option>						
								</c:forEach>
							</select>								
						</div>
						<div class="form-group">
							<label for="year">Année</label>
							<input type="date" name="year" value="${year}" class="form-control" required/>
						</div>
						<button type="submit" class="mt-1 btn btn-dark">rechercher</button>
					</form>
				</div>
			</div>
			<div class="card">
				<div class="card-header">
					<h5 class="card-title">Recherche entre deux dates</h5>
				</div>
				<div class="card-body">
					<form class="form" action="between_date.search">
						<div class="form-group">
							<label for="reader">Lecteur</label>
							<select name="reader" class="form-select">
								<c:forEach items="${readers}" var="reader">
									<option value="${reader.id }">${reader.name } ${reader.lastname }</option>						
								</c:forEach>
							</select>												
						</div>
						<div class="form-group">
							<label for="start_date" class="form-label">Date de debut</label>
							<input type="date" name="start_date" value="${start_date }" class="form-control" required/>
						</div>
						<div class="form-group">
							<label for="end_date" class="form-label">Date fin</label>
							<input type="date" name="end_date" value="${end_date }" class="form-control" required/>
						</div>
						<button type="submit" class="mt-1 btn btn-dark">rechercher</button>
					</form>
				</div>
			</div>		
		</div>
		<c:if test="${not empty reader.id }">
			<div class="col-md-9">
				<div class="card mb-2">
					<div class="card-header">
						<h3 class="card-title">Information sur le lecteur</h3>
					</div>
					<div class="card-body">
						<p class="card-text"><strong>ID</strong> ${reader.id }</p>
						<p class="card-text"><strong>Nom</strong> ${reader.name} ${reader.lastname }</p>
						<c:choose>
							<c:when test="${not empty month }">
								<a href="month.pdf?reader=${reader.id }&month=${month}" class="mb-2 btn btn-danger">Générer pdf</a>
							</c:when>
							<c:when test="${not empty year }">
								<a href="year.pdf?reader=${reader.id }&year=${year}" class="mb-2 btn btn-danger">Générer pdf</a>
							</c:when>
							<c:otherwise>
								<a href="between.pdf?reader=${reader.id }&start_date=${start_date}&end_date=${end_date}" class="mb-2 btn btn-danger">Générer pdf</a>													
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>Id</th>
							<th>Livre</th>
							<th>Autheur</th>
							<th>Date de pret</th>
							<th>Date de retour</th>
							<th>Status</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${lends }" var="lend">
							<tr>
								<td>${lend.id }</td>
								<td>${lend.book.title }</td>
								<td>${lend.book.author }</td>
								<td>${lend.startDate }</td>
								<td>${lend.endDate }</td>
								<td>
									<c:choose>
										<c:when test="${lend.finished }">
											<span class="badge bg-success">terminer</span>
										</c:when>
										<c:otherwise>
											<span class="badge bg-info">En cours</span>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>					
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:if>
	</div>
<%@ include file="../../utils/footer.jsp" %>