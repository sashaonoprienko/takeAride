<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="takeARide" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h4 class="text-center">My orders</h4>
<hr />
<table id="myOrders" class="table table-bordered" data-page-number="1" data-page-count="${pageCount }">
	<thead>
		<tr>
			<th>Order id</th>
			<th>Date</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${empty orders }">
			<tr>
				<td colspan="2" class="text-center">No orders found</td>
			</tr>
		</c:if>
		<jsp:include page="../fragment/my-orders-tbody.jsp" />
	</tbody>
</table>
    <c:if test="${history == null }">
	<input id="showHistory" class="btn btn-success" type="button"	value="Show Order History">
	</c:if>
	<c:if test="${history != null }">
	<input id="hideHistory" class="btn btn-success" type="button"	value="Hide Order History">
	</c:if>
<div class="text-center hidden-print">
	<c:if test="${pageCount > 1 }">
		<a id="loadMoreMyOrders" class="btn btn-success">Load more my orders</a>
	</c:if>
</div>