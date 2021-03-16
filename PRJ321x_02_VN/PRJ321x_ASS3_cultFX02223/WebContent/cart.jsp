
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
					<table>
						<thead>
							<tr>
								<th class="shoping__product">Products</th>
								<th>Price</th>
								<th>Quantity</th>
								<th>Total</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="cartItem" items="${cart.items}">
								<tr>
									<td class="shoping__cart__item"><img style="width: 100px;"
										src='<c:out value="${cartItem.getSrc() }"/>' alt="">
										<h5>
											<c:out value="${cartItem.getName() }" />
										</h5></td>
									<td class="shoping__cart__price">$<c:out
											value="${cartItem.getPrice() }" /></td>
									<td class="shoping__cart__quantity">
										<div class="quantity">
											<div class="my-pro-qty">
												<a
													href='<c:url value="/cart?id=${cartItem.getId()}&action=subtract"/>'>
													<span class="">-</span> <input type="text"
													value='<c:out value="${cartItem.getNumber() }"/>'>
												</a> <a
													href='<c:url value="/cart?id=${cartItem.getId()}&action=add"/>'>
													<span class="">+</span>
												</a>

											</div>
										</div>
									</td>
									<td class="shoping__cart__total">$<c:out
											value="${cartItem.getAmount() }" /></td>
									<td class="shoping__cart__item__close"><a
										href='<c:url value="/cart?id=${cartItem.getId() }&action=delete"/>'><span
											class="icon_close"></span></a></td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
			</div>
			<div class="col-lg-12">
				<div class="checkout__form">
					<h4>Chekout infomation</h4>
					<form id="form-login" action="pay" method="post">
						<c:if test="${not empty error }">
							<div class="alert alert-primary" role="alert">
							  <c:out value="${error}"/>
							</div>
						</c:if>
						
						<p>
							<mark id="error-name"></mark>
						</p>
						<p>
							<mark id="error-password"></mark>
						</p>
						<div class="row">
							<div class="col-lg-8 col-md-6">
								<!-- if user login, we can get info user from system -->
								<c:if test="${not empty account}">
									<div class="checkout__input">
									<p>
										User Email<span>*</span>
									</p>
									<input id="email" name="email" type="text"
										value='<c:out value="${account.getUsr() }"/>'
										placeholder="user email">
									</div>
									<div class="checkout__input">
										<p>
											User Address<span>*</span>
										</p>
										<input id="address" name="address" type="text"
											value='<c:out value="${account.getAddress() }"/>'
											placeholder="User address" class="checkout__input__add">
									</div>
									<div class="checkout__input">
										<p>Discount Code</p>
										<input id="discount" name="discount" type="text"
											placeholder="Discount Code" class="checkout__input__add">
									</div>
									
								</c:if>
								<c:if test="${empty account }">
									<div class="checkout__input">
									<p>
										User Email<span>*</span>
									</p>
									<input id="email" name="email" type="text"
										placeholder="user email">
									</div>
									<div class="checkout__input">
										<p>
											User Address<span>*</span>
										</p>
										<input id="address" name="address" type="text"
											placeholder="User address" class="checkout__input__add">
									</div>
									<div class="checkout__input">
										<p>Discount Code</p>
										<input id="discount" name="discount" type="text"
											placeholder="Discount Code" class="checkout__input__add">
									</div>
								</c:if>
								
								<button type="submit" class="site-btn">Checkout</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</section>

<jsp:include page="footer.jsp"></jsp:include>
</body>

</html>