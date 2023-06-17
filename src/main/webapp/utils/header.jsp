<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%
	Cookie[] cookies = request.getCookies();
	Cookie cookie = null;
	String username = "";
	String logo_link = "login";
	
	
	if(cookies != null ){
		for(int i = 0; i < cookies.length ; i++){
			if( cookies[i].getName().equals("username")){
				username= cookies[i].getValue();
				logo_link = "books";
			}
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Gestion des prets</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="css/fontawesome.min.css"/>
<link rel="stylesheet" type="text/css" href="css/all.min.css"/>
</head>
<body class="bg-light">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark" aria-label="Eighth navbar example">
    <div class="container">
      <a class="navbar-brand" href="<%= logo_link %>">Gestion des Prets</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample07" aria-controls="navbarsExample07" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <% if(username.length()>1){ %>
      	<%= "<div class='collapse navbar-collapse' id='navbarsExample07'>" %>
	        <%="<ul class='navbar-nav me-auto mb-2 mb-lg-0'>"%>
	        <%="<li class='nav-item'>"%>
	        <%="<a class='nav-link' aria-current='page' href='books'>Livres</a>"%>
	        <%="</li>"%>
	        <%="<li class='nav-item'>"%>
	        <%="<a class='nav-link' href='readers'>Lecteurs</a>"%>
	        <%="</li>"%>
	        <%="<li class='nav-item'>"%>
	        <%="<a class='nav-link' href='lends'>Prets</a>"%>
	        <%="</li>"%>
	        <%="<li class='nav-item'>"%>
	        <%="<a class='nav-link' href='almonds'>Amandes</a>"%>
	        <%="</li>"%>
	        <%="</ul>"%>
	        <%="<div class='text-end'>"%>
	        <%="<a href='login?logout=1' class='btn btn-warning'>Deconnexion</a>"%>
	        <%="</div>"%>
      	
      	<%}%>
      </div>
    </div>
  </nav>
<div class="container p-2">