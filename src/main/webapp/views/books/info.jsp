<%@ include file="../../utils/header.jsp" %>
	<h3 class="title">Information du livre </h3>
	<hr/>
	<div class="row">
		<div class="col-md-6">
			<div class="card">
				<div class="card-body">
					<img src="uploads/${book.illustration }" class="card-img-top" style="height:500px"/>				
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<h4 class="title">${book.title }</h4>
			<h5 class="title">${book.author }</h5>
			<c:choose>
				<c:when test="${book.status}">
					<span class=" badge rounded-pill bg-success">disponible</span>							
				</c:when>
				<c:otherwise>
					<span class="badge rounded-pill bg-danger">non disponible</span>
				</c:otherwise>
			</c:choose>
			<p class="text">${book.description }</p>
			<p class="text"><strong>Edition</strong>: ${book.publishedAt }</p>
			<p class="text"><strong>Nombre de prêt</strong>: ${book.nbPret }</p>
		</div>
	</div>
<%@ include file="../../utils/footer.jsp" %>