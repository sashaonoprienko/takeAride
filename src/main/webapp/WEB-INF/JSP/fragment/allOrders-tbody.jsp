<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<c:if test="${CURRENT_MESSAGE != null }">
		<div class="alert alert-success hidden-print">${CURRENT_MESSAGE }</div>
	</c:if>
<c:forEach var="order" items="${orders }">
	<c:if test="${order.checked ==false }">
	<tr class="item">
		<td><a href="/order?id=${order.id }">Order # ${order.id }</a></td>
		<td><fmt:formatDate value="${order.created }" pattern="yyyy-MM-dd HH:mm" /></td>
		<td> <form >
		 <input class="reason" required>reason for rejection
		  <input class="idorder" size="5" value = ${order.id }>idProduct
		 	<input id="rejectBtn" class="btn btn-success" type="button"
				value="reject">
		 </form></td>
		 <td>
		 <form>
		 <input class="returnMessage" >return Message(If car was damaged)
		  <input class="idDoneorder" size="5" value = ${order.id }>idProduct
		 	<input id="doneBtn" class="btn btn-success" type="button"	value="DONE">
		 	<input type="button" id="checked" value =${order.id } >setChecked
		 </form>
		 </td>
	</tr>
	</c:if>
</c:forEach>