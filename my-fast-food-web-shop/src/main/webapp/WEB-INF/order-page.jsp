<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	
	<div class="container">
	<div>
			<a type="button" class="btn btn-success" href="/add-order/drinks">Add Drink</a>
			<a class="btn btn-success" href="/add-order/pizzas">Add Pizza</a>
			
		</div>
		<table class="table table-striped">
			<caption>Your's shopping card:</caption>
			<thead>
				<tr>
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
						<td>${prod.type}</td>
						<td>${prod.size}</td>
						<td>${prod.price}</td>
						
						<td></td>
						
						<td><a  type="button" class="btn btn-warning"
							href="/delete-prod?type=${prod.type}">Delete</a></td>
					</tr>
				</c:forEach>
				<tr>
				<td>TOTAL</td>
				<td></td>
				<td></td>
				<td>${total}</td>
				
			</tbody>
			
		</table>
	<a class="btn btn-success" href="/submit">Submit</a>
	</div>
<%@ include file="common/footer.jspf" %>