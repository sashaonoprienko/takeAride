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
		<div class="panel-heading" >Find products</div>
		</c:if>
	
		<div class="panel-body">
			<div class="input-group">
				<input type="text" name="query" class="form-control" placeholder="Search query" value="${searchForm.query }"> 
				<span class="input-group-btn"> 
					<a id="goSearch" class="btn btn-default">Go!</a>
				</span>
			</div>
			
		</div>
		
	</div>
</form>
<%-- /Search form --%>
