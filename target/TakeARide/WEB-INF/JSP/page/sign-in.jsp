
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="takeARide" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${CURRENT_MESSAGE != null }">
		<div class="alert alert-warning hidden-print" >${CURRENT_MESSAGE }</div>
	</c:if>
<div class="row hidden-print">
	<div class="col-md-4 col-md-offset-4 col-lg-2 col-lg-offset-5">
		<takeARide:sign-in classes="btn-block" />
	</div>
</div>