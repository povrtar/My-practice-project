<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
	<form:form method="post" commandName="product">
		
		<fieldset class="form-group">
			<form:label path="type">Choose the type</form:label>
			<form:input path="type" type="text" class="form-control"
				required="required" />
			<form:errors path="type" cssClass="text-warning" />
		</fieldset>
			
		<fieldset class="form-group">
			<form:label path="size">Choose the size</form:label>
			<form:input path="size" type="text" class="form-control"
				required="required" />
			<form:errors path="size" cssClass="text-warning" />
		</fieldset>
		
		<button type="submit" class="btn btn-success">Add</button>
	</form:form>
</div>
<%@ include file="common/footer.jspf" %>