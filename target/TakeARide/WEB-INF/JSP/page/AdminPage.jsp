<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Page</title>
</head>
<body id="admin">
	<c:if test="${CURRENT_MESSAGE != null }">
		<div class="alert alert-success hidden-print">${CURRENT_MESSAGE }</div>
	</c:if>
	<form style="float: left;"">
		<p>Input Data:</p>
		<p>
			<input class="fullname" required>Full Name
		</p>
		<p>
			<input class="producer" required>Producer(Id)
		</p>
		<p>
			<input class="category" required>Category(Id)
		</p>
		<p>
			<input class="price" required>Price per day
		</p>
		<p>
			<input class="description">Description
		</p>
		<p>
			<input class="id" required>Id
		</p>
		<p>
			<input type="file" accept="image/*" class="pic" required>
		<p>
			<input id="addByadmin" class="btn btn-success" type="button"
				value="AddNewCar">
		</p>
	</form>

	<form style="float: left; margin-top: 30px;">
		<p>
			<input class="fullnameforDel">Full Name
		</p>
		<input id="DelByadmin" class="btn btn-success" type="button"
			value="DeleteCar">
	</form>

	<form style="float: left; margin-left: 10px;">
		<p>Input Data for Update:</p>
		<p>
			<input class="fullnameUp">Full Name
		</p>
		<p>
			<input class="priceUp">Price per day
		</p>
		<p>
			<input class="descriptionUp">Description
		</p>
		<p>
			<input class="idUp">Id
		</p>
		<p>
			<input id="UpByadmin" class="btn btn-success" type="button"
				value="UpdateProduct">
		</p>
	</form>
   <table style= "margin-left: 10px;">
       <tr>
        
         <form style="float: left; margin-left: 10px;">
		<p>Input User Email for Block:</p>
		<p>
			<input class="email">User Email
		</p>
		<p>
			<input id="blockByadmin" class="btn btn-success" type="button"
				value="Block User">
		</p>
	    </form>
         </tr>
        <tr>
        <form style="float: left; margin-left: 10px;">
		<p>Input User Email for UnBlock:</p>
		<p>
			<input class="emailUn">User Email
		</p>
		<p>
			<input id="UnblockByadmin" class="btn btn-success" type="button"
				value="UnBlock User">
		</p>
	</form> 
       </tr> 
        
      
  </table>
	
	
	<form style="float: right; margin-left: -40px;margin-top:-225px;">
		<p>Input User Email for Set Manager:</p>
		<p>
			<input class="emailM">User Email
		</p>
		<p>
			<input id="setManByadmin" class="btn btn-success" type="button"
				value="Set Manager">
		</p>
	</form>

	<ul style="float: right;"  >
	  <p>Producers Id:</p>
		<li>Chevrolet - 1</li>
		<li>Daewoo - 2</li>
		<li>Mercedes - 3</li>
		<li>Tayota - 4</li>
		<li>VolskWagen - 6</li>
		<li>Volvo -5</li>
		<p>Categorys Id:</p>
		<li>Econom - 1</li>
		<li>Middle - 2</li>
		<li>Buisness - 3</li>
		<li>Electro - 4</li>
		<li>Sport - 6</li>
	</ul>
</body>
</html>