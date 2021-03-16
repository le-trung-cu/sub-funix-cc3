
<%@page import="models.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp"></jsp:include>

<section class="shoping-cart spad">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="shoping__cart__table">
					<p>Order Id: <c:out value="${order.getOrderId() }"/></p>
					<p>Order Date: <c:out value="${order.getOrderDate() }"/></p>
					<p>Order Address: <c:out value="${order.getAddress() }"/></p>
					<p>Discount: <c:out value="${order.getDiscount() }"/></p>
					<table>
						<thead>
							<tr>
								<th>Product Id</th>
								<th>Product Name</th>
								<th>Price</th>
								<th>Amount</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="productOrders" items="${order.getLp()}">
								<tr>
									<td><c:out value="${productOrders.getProductId() }" /></td>
									<td>
										<h5>
											<c:out value="${productOrders.getNameProduct() }" />
										</h5>
									</td>
									<td>$<c:out
											value="${productOrders.getPrice() }" /></td>
									<td>
										<c:out value="${productOrders.getAmountProduct()}" />
									</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</section>

<jsp:include page="footer.jsp"></jsp:include>
</body>

</html>