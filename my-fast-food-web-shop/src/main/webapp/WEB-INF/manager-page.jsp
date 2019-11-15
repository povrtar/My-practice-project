<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
	Welcome ${name}!!
	We sold today for ${dailyTotal} pound.
	
	We sold ${dailyPizzas} pizzas today.
	<a href="/list-of-food">Click here</a> to manage your
	food order.
</div>
<%@ include file="common/footer.jspf"%>