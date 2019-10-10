<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
	<form:form method="post" commandName="customer">
		
		<fieldset class="form-group">
			<form:label path="firstName">First Name</form:label>
			<form:input path="firstName" type="text" class="form-control"
				required="required" />
			<form:errors path="firstName" cssClass="text-warning" />
		</fieldset>
			
		<fieldset class="form-group">
			<form:label path="lastName">Last Name</form:label>
			<form:input path="lastName" type="text" class="form-control"
				required="required" />
			<form:errors path="lastName" cssClass="text-warning" />
		</fieldset>
		<fieldset class="form-group">
			<form:label path="password">Password</form:label>
			<form:input path="password" type="password" class="form-control"
				required="required" />
			<form:errors path="password" cssClass="text-warning" />
		</fieldset>
		<fieldset class="form-group">
			<form:label path="discountLev">Discount Level</form:label>
			<form:input path="discountLev" type="number" step="1" class="form-control"
				required="required" />
			<form:errors path="discountLev" cssClass="text-warning" />
		</fieldset>
		<button type="submit" class="btn btn-success">Add</button>
	</form:form>
</div>
<%@ include file="common/footer.jspf" %>