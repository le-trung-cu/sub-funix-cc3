
<%@page import="models.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp"></jsp:include>

<!-- Order Section Begin -->
<section class="spad">
	<div class="container">
		<section class="shoping-cart spad">
			<div class="container">
				<div class="row">
					<div class="col-lg-12">
						<div class="">
							<table class="table">
								<thead>
									<tr>
										<th>OrderId</th>
										<th>User mail</th>
										<th>User Address</th>
										<th>OrderDate</th>
										<th>Discount</th>
										<th>Price</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="order" items="${pagination.items}">
										<tr>
											<td><c:out value="${order.getOrderId() }" /></td>
											<td><c:out value="${order.getUserMail() }" /></td>
											<td><c:out value="${order.getAddress() }" /></td>
											<td><c:out value="${order.getOrderDate() }" /></td>
											<td><c:out value="${order.getDiscount()}" /></td>
											<td><c:out value="${order.getPrice()}" /></td>
											<td><a href='<c:url value="/orderDetail?id=${order.getOrderId() }"/>'>Detail</a></td>
										</tr>
									</c:forEach>

								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</section>

		<div class="product__pagination">
			<c:forEach var="pageNum" begin="1" end="${pagination.getCountPage()}">
				<a href='<c:url value="/orders?page=${pageNum }"/>'>${pageNum }</a>
			</c:forEach>
		</div>
	</div>
</section>
<!-- Order Section End -->

<jsp:include page="footer.jsp"></jsp:include>
</body>

</html>