
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="takeARide" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="productList" data-page-count="${pageCount}" data-page-number="1">
	<div class="row">
		<jsp:include page="../fragment/product-list.jsp" />
	</div>
	<c:if test="${pageCount > 1 }">
	<div class="text-center hidden-print">
	<c:if test="${language == null }">
        <a id="loadMore" class="btn btn-success">Load more products</a>
        </c:if>
        <c:if test="${language != null }">
        <a id="loadMore" class="btn btn-success">Загрузить еще</a>
        </c:if>
	</div>
	</c:if>
</div>
<takeARide:add-product-popup />    