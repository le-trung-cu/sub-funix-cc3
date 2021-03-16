<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="hero__categories">
	<div class="hero__categories__all">
		<i class="fa fa-bars"></i> <span>All Carts</span>
	</div>
	<ul style="display: none;">
		<c:forEach var="cartItem" items="${cart.items }">
			<li>
				<div class="row">
					<div class="col-4">
						<img src='<c:url value="${cartItem.getSrc() }"/>' alt="">
					</div>
					<div class="col-8 featured__item__text">
						<h6>
							<span></span>
							<c:out value="${cartItem.getName() }" />
						</h6>
						<h5>
							<span></span> $
							<c:out value="${cartItem.getPrice() }" />
						</h5>
					</div>
				</div>
			</li>
		</c:forEach>
		<li><a class="site-btn" href='<c:url value="/cart"/>'>Go Cart</a></li>
	</ul>
	
</div>
