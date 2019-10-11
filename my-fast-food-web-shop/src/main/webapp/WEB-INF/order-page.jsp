<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	
	<div class="container">
	<div>
			<a type="button" class="btn btn-success" href="/add-drink">Add Drink</a>
			<a class="btn btn-success" href="/add-pizza">Add Pizza</a>
			
		</div>
		<table class="table table-striped">
			<caption>Your's shopping card:</caption>
			<thead>
				<tr>
					<th>Id</th>
					<th>Type</th>
					<th>Size</th>
				    <th>Price</th>
					<th>total</th>
							
	
		
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${productList}" var="prod">
					<tr>
					<td>${prod.id}</td>
						<td>${prod.type}</td>
						<td>${prod.size}</td>
						<td>${prod.price}</td>
						
						<td></td>
						
						<td><a type="button" class="btn btn-warning"
							href="/delete-prod?id=${prod.id}">Delete</a></td>
					</tr>
				</c:forEach>
				<tr>
				<td>TOTAL</td>
				<td></td>
				<td></td>
				<td></td>
				<td>${total}</td>
				
			</tbody>
			
		</table>
	<a class="btn btn-success" href="/logout">Submit</a>
	</div>
<%@ include file="common/footer.jspf" %>