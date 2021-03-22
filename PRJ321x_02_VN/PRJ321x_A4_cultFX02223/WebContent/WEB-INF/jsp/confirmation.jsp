<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp" />

<div class="container">
	<div class="alert alert-primary" role="alert">
		<h2>Welcome admin: <c:out value="${username }"></c:out></h2> 
	</div>	
</div>

<jsp:include page="footer.jsp" />