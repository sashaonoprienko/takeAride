<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 
<c:forEach var="order" items="${orders }">
 
   <c:if test="${language == null }">
	<tr class="item">
	 <c:if test="${history != null }">
	 <c:if test="${order.isRejected != null }">
		<div class="alert alert-warning hidden-print">Order with id ${order.id } REJECTED.Couse: ${order.isRejected }</div>
	   </c:if>
	   
	  
	     <c:if test="${order.message.length() > 1 }">
		<div class="alert alert-warning hidden-print">Order with id ${order.id } has damage.Our manager will contact with you soon: ${order.message }</div>
	   </c:if>
	   
	   
	   <c:if test="${order.message.length() < 1 }">
		<div class="alert alert-warning hidden-print">Order with id ${order.id } has been returned succefully</div>
	   </c:if>
	  </c:if>
	   
		<td><a href="/order?id=${order.id }">Order # ${order.id }</a></td>
		<td><fmt:formatDate value="${order.created }" pattern="yyyy-MM-dd HH:mm" /></td>
	</tr>
	</c:if>
	
	 <c:if test="${language != null }">
	<tr class="item">
	 <c:if test="${history != null }">
	  <c:if test="${order.isRejected != null }">
		<div class="alert alert-warning hidden-print">Заказ с номером ${order.id } Откланен.Причина: ${order.isRejected }</div>
	   </c:if>
	     <c:if test="${order.message.length() > 1 }">
		<div class="alert alert-warning hidden-print">Заказ с номером ${order.id } был поврежден.Наш менеджер с вами свяжется: ${order.message }</div>
	   </c:if>
	   <c:if test="${order.message.length() < 1 }">
		<div class="alert alert-warning hidden-print">Заказ с номером ${order.id } успешно возвращен</div>
	   </c:if>
	   </c:if>
		<td><a href="/order?id=${order.id }">Заказ # ${order.id }</a></td>
		<td><fmt:formatDate value="${order.created }" pattern="yyyy-MM-dd HH:mm" /></td>
	</tr>
	</c:if>
	
</c:forEach>
