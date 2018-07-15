<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="takeARide" tagdir="/WEB-INF/tags"%>

<div class="visible-xs-block xs-option-container">
 <c:if test="${language == null }">
	<a class="pull-right" data-toggle="collapse" href="#productCatalog">Product catalog <span class="caret"></span></a> 
	<a data-toggle="collapse" href="#findProducts">Find products <span class="caret"></span></a>
	</c:if>
	<c:if test="${language != null }">
	<a class="pull-right" data-toggle="collapse" href="#productCatalog">Классы автомобилей <span class="caret"></span></a> 
	<a data-toggle="collapse" href="#findProducts">Найти<span class="caret"></span></a>
	</c:if>
</div>
<%-- Search form --%>
<form class="search" action="/search">
	<div id="findProducts" class="panel panel-success collapse">
	<c:if test="${language == null }">
		<div class="panel-heading">Find products</div>
		</c:if>
		<c:if test="${language != null }">
		<div class="panel-heading">Найти</div>
		</c:if>
		<div class="panel-body">
			<div class="input-group">
				<input type="text" name="query" class="form-control" placeholder="Search query" value="${searchForm.query }"> 
				<span class="input-group-btn"> 
					<a id="goSearch" class="btn btn-default">Go!</a>
				</span>
			</div>
			<div class="more-options">
			<c:if test="${language == null }">
				<a data-toggle="collapse" href="#searchOptions">More filters <span class="caret"></span></a>
				</c:if>
				<c:if test="${language != null }">
				<a data-toggle="collapse" href="#searchOptions">Дополнительные фильтры <span class="caret"></span></a>
				</c:if>
			</div>
		</div>
		<div id="searchOptions" class="collapse ${searchForm.categoriesNotEmpty or searchForm.producersNotEmpty ? 'in' : '' }">
			<takeARide:category-filter categories="${CATEGORY_LIST }" searchForm="${searchForm}" />
			<takeARide:producer-filter producers="${PRODUCER_LIST }"  searchForm="${searchForm}" />
		</div>
	</div>
</form>
<%-- /Search form --%>
<%-- Categories --%>
<div id="productCatalog" class="panel panel-success collapse">
<c:if test="${language == null }">
	<div class="panel-heading">Product catalog</div>
	</c:if>
	<c:if test="${language != null }">
	<div class="panel-heading">Классы автомобилей</div>
	</c:if>
	<div class="list-group">
		<c:forEach var="category" items="${CATEGORY_LIST }">
			<a href="/products${category.url }" class="list-group-item ${selectedCategoryUrl == category.url ? 'active' : '' }"> 
			<c:choose>
			  <c:when test="${category.productCount > 0 }">
				<span class="badge">${category.productCount}</span> ${category.name}
			 </c:when>
			 <c:otherwise>
			${category.name}
			 </c:otherwise>
			 </c:choose>
			</a>
		</c:forEach>
	</div>
</div>
<%-- /Categories --%>