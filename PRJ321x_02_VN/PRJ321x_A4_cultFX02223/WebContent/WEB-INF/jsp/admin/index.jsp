<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp"></jsp:include>

<!-- Page Heading -->
<div class="d-sm-flex align-items-center justify-content-between mb-4">
	<h1 class="h3 mb-0 text-gray-800">Accounts:</h1>
</div>

<!-- Content Row -->
<div>
	<h2>Wellcome login: <c:out value="${account.getName() }"/></h2>
</div>

<jsp:include page="footer.jsp"></jsp:include>
