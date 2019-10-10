<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	
	<div class="container">
	<div>
			<a type="button" class="btn btn-success" href="/addcustomer">Add more Customers</a>
			
			
		</div>
		<table class="table table-striped">
			<caption>Customer List</caption>
			<thead>
				<tr>
				<th>Id</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Password</th>
				    <th>Discount Level</th>
					
							
	
		
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${customerList}" var="customer">
					<tr>
					<td>${customer.id}</td>
						<td>${customer.firstName}</td>
						<td>${customer.lastName}</td>
						<td>${customer.password}</td>
						<td>${customer.discountLev}</td>
						<td></td>
						
						<td><a type="button" class="btn btn-warning"
							href="/delete-cust?id=${customer.id}">Delete</a></td>
					</tr>
				</c:forEach>
				<tr>
				
				<td></td>
				<td></td>
				<td></td>
				
				
			</tbody>
			
		</table>
	<a class="btn btn-success" href="/">Submit</a>
	</div>
<%@ include file="common/footer.jspf" %>