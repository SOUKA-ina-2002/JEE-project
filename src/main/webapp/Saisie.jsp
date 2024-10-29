<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
<title>Product Catalog</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
  <%@include file="header.jsp" %>
  <div class="container col-md-8 col-md-offset-2 ">
	<div class="panel panel-primary">
		<div class="panel-heading">Saisir les champs d'un produit
		</div>
		<div class="panel-body">
		<form action="Saisie.as" method="post">
		
		<div class="form-group">
		<label class="control-label">Designation</label>
		<input type="text" name="designation" class="form-control" required="required" value="${produit.designation}"/>
		<span></span>
		</div>
		
		<div>
		<label class="control-label">Prix</label>
		<input type="text" name="prix" class="form-control" required="required" value="${produit.prix}"/>
		<span></span>
		</div>
		
		<div>
		<label class="control-label">Quantité</label>
		<input type="text" name="quantity" class="form-control" required="required" value="${produit.quantity}"/>
		<span></span>
	    </div>
	    
	    <div>
	    <span></span>
	    <button type="submit" class="btn btn-primary">Save</button>
	    </div>
		
		</form>
		
		 </div>
	</div>
	</div>
</body>
</html>