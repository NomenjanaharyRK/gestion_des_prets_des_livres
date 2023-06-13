<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../utils/header.jsp" %>
	<h3 class="title">Tous les livres</h3>
	<a href="new.book" class="btn btn-dark">Ajouter</a>
	<hr/>
	<div class="row mt-4">
		<c:forEach items="${books}" var="book">
			<div class="col-md-3">
				<div class="card my-2" style="width:90%">
					<img src="uploads/${book.illustration }" class="card-img-top" alt="illustration" style="height:250px">
					<c:choose>
						<c:when test="${book.status}">
							<span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-success">disponible</span>							
						</c:when>
						<c:otherwise>
							<span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">non disponible</span>
						</c:otherwise>
					</c:choose>
					<div class="card-body">
						<h5 class="card-title">${book.title }</h5>
						<p class="card-text">${book.author }</p>
					</div>
					<div class="card-footer">
						<a href="info.book?id=${book.id }" title="voir livre" class="btn btn-info">
							<i class="fas fa-eye text-white"></i>						
						</a>
						<a href="update.book?id=${book.id }" title="modifier livre" class="btn btn-secondary">
							<i class="fa fa-edit"></i>
						</a>
						<a href="delete.book?id=${book.id }" title="supprimer livre" class="btn btn-danger" onclick="return confirm('etes vous sûr de vouloir supprimer ce livre ?')">
							<i class="fas fa-trash"></i>
						</a>
					</div>
				</div>
			</div>		
		</c:forEach>
	</div>
<%@ include file="../../utils/footer.jsp" %>
