<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="items" required="true" type="java.util.Collection"%>
<%@ attribute name="totalCost" required="true" type="java.lang.Number"%>
<%@ attribute name="showActionColumn" required="true" type="java.lang.Boolean"%>

<table class="table table-bordered">
<c:if test="${language == null }">
	<thead>
		<tr>
			<th>Product</th>
			<th>Price</th>
			<th>Number of Days</th>
			<c:if test="${showActionColumn }">
			<th class="hidden-print">Action</th>
			</c:if>
		</tr>
	</thead>
</c:if>
<c:if test="${language == 'Russ' }">
<thead>
		<tr>
			<th>Машина</th>
			<th>Цена</th>
			<th>Кол-во суток</th>
			<c:if test="${showActionColumn }">
			<th class="hidden-print">Action</th>
			</c:if>
		</tr>
	</thead>
</c:if>	
	<tbody>
		<c:forEach var="item" items="${items }">
			<tr id="product${item.product.id }" class="item">
				<td class="text-center"><img class="small" src="${item.product.imageLink }" alt="${item.product.name }"><br>${item.product.name }</td>
				<c:if test="${language == null }">
				<td class="price">$ ${item.product.price }/per day</td>
				</c:if>
				<c:if test="${language == 'Russ' }">
				<td class="price">$ ${item.product.price }/сутки</td>
				</c:if>
				<td class="count">${item.count }</td>
				<c:if test="${showActionColumn }">
				<td class="hidden-print">
			
				<c:choose>
				<c:when test="${item.count > 1 }">
					<a class="btn btn-danger remove-product" data-id-product="${item.product.id }" data-count="1">${language == 'Russ' ? 'Удалить 1 день': 'Remove one day'}</a><br><br>
					<a class="btn btn-danger remove-product remove-all" data-id-product="${item.product.id }" data-count="${item.count }">${language == 'Russ' ? 'Удалить все': 'Remove all'}</a>
				</c:when>
				<c:otherwise>
					<a class="btn btn-danger remove-product" data-id-product="${item.product.id }" data-count="1">${language == 'Russ' ? 'Удалить 1 день': 'Remove one day'}</a>
				</c:otherwise>
				</c:choose>
				
				
				
				
				</td>
				</c:if>
			</tr>
		</c:forEach>
		<tr>
		     <c:if test="${language == 'Russ' }">
			<td colspan="2" class="text-right"><strong>Total:</strong></td>
			</c:if>
			<c:if test="${language != null }">
			<td colspan="2" class="text-right"><strong>Общая стоимость:</strong></td>
			</c:if>
			<td colspan="${showActionColumn ? 2 : 1}" class="total">$ ${totalCost}</td>
		</tr>
	</tbody>
</table>