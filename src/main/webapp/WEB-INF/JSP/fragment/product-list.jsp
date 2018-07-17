<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="takeARide" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

	<c:forEach var="p" items="${products }">
		<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 col-xlg-2">
			<!-- PRODUCT DATA -->
			<div id="product${p.id }" class="panel panel-default product">
				<div class="panel-body">
					<div class="thumbnail">
						<img src="${p.imageLink }" alt="${p.name }">
						<div class="desc">
							<div class="cell">
								<p>
								 <c:if test="${language == null }">
									<span class="title">Details</span> ${p.description }
									</c:if>
									<c:if test="${language != null }">
									<span class="title">Описание</span> ${p.description }
									</c:if>
								</p>
							</div>
						</div>
					</div>
					<h4 class="name">${p.name }</h4>
					<div class="code">Code: ${p.id }</div>
					 <c:if test="${language == null }">
					<div class="price" style = "background-color: #FFC0CB;">$ ${p.price }</div>
					<a class="btn btn-primary pull-right buy-btn" data-id-product="${p.id }">Buy</a>
					<div class="list-group">
	
					</div>
					</c:if>
					
					 <c:if test="${language != null }">
					<div class="price">$ ${p.price }/сутки</div>
					<a class="btn btn-primary pull-right buy-btn" data-id-product="${p.id }">Арендовать</a>
					<div class="list-group">
						<span class="list-group-item"><small>Категория:</small><span class="category">${p.category }</span></span> 
						<span class="list-group-item"><small>Марка:</small><span class="producer">${p.producer }</span></span>
					</div>
					</c:if>
				</div>
			</div>
			<!-- /PRODUCT DATA -->
		</div>
	</c:forEach>
