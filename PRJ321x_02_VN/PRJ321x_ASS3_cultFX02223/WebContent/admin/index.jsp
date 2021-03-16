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
	<table class="table table-striped">
		<thead>
			<tr>
				<th>usr</th>
				<th>Name</th>
				<th>Address</th>
				<th>Phone</th>
				<th>Role</th>
				<th>Check</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${pagination.items }">
				<tr>
					<td><c:out value="${item.getUsr() }"/></td>
					<td><c:out value="${item.getName() }"/></td>
					<td><c:out value="${item.getAddress() }"/></td>
					<td><c:out value="${item.getPhone() }"/></td>
					<td><c:out value="${item.getRole() }"/></td>
					<td><c:out value="${item.getCheck() }"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<div class="container">
	<div class="row">
		<div class="col-12">
			<ul class="pagination">
				<c:forEach var="pageNum" begin="1" end="${pagination.getCountPage()}">
					<li class="page-item">
						<a class="page-link" href='<c:url value="/list?page=${pageNum }"/>'>${pageNum }</a>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</div>

<jsp:include page="footer.jsp"></jsp:include>
