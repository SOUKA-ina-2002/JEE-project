<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
<title>Product Catalog</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
  <div class="container col-md-8 col-md-offset-2">
	<div class="panel panel-primary">
		<div class="panel-heading">Rechercher des produits
		</div>
		<div class="panel-body">
			
			<form action="chercher.as" method="get">
			<label>Mot clé</label>
			<input type="text" name="motCle" value="${modele.mc }"/>
			<button type="submit" class="btn btn-primary">chercher</button>
			</form>
			
			<table class="table">
			<tr>
			<th>id</th><th>Designation</th><th>Prix</th><th>Quantité</th>
			</tr>
			<c:forEach items="${modele.produits}" var="p">
			<tr>
			<td>${p.id}</td>
			<td>${p.designation}</td>
			<td>${p.prix}</td>
			<td>${p.quantity}</td>
			</tr>
			</c:forEach>
			</table>
		
		 </div>
	</div>
	</div>
</body>
</html>