
<%@page import="models.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp"></jsp:include>

<section class="product-details spad">
	<div class="container">
		<div class="row">
			<div class="col-lg-6 col-md-6">
				<div class="product__details__pic">
					<div class="product__details__pic__item">
						<img class="product__details__pic__item--large"
							src="images/realme-c15.jpg" alt="">
					</div>
				</div>
			</div>
			<div class="col-lg-6 col-md-6">
				<div class="product__details__text">
					<h3><c:out value="${product.getName() }"/></h3>
					<div class="product__details__rating">
						<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
							class="fa fa-star"></i> <i class="fa fa-star"></i> <i
							class="fa fa-star-half-o"></i> <span>(18 reviews)</span>
					</div>
					<div class="product__details__price">$<c:out value="${product.getPrice() }"/></div>
					<p><c:out value="${product.getDesciption() }"/></p>

					<a href='<c:url value="/cart?id=${product.getId() }&action=add"/>' class="primary-btn">ADD TO CARD</a> <a href="#"
						class="heart-icon"><span class="icon_heart_alt"></span></a>

				</div>
			</div>
		</div>
	</div>
</section>

<jsp:include page="footer.jsp"></jsp:include>
</body>

</html>