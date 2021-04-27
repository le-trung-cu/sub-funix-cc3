
<%@page import="models.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp"></jsp:include>

<!-- Featured Section Begin -->
<section class="featured spad">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="section-title">
					<h2>Featured Product</h2>
				</div>

			</div>
		</div>
		<div class="row featured__filter">
			<c:forEach var="product" items="${pagination.items}">
				<div class="col-lg-3 col-md-4 col-sm-6 mix oranges fresh-meat">
					<div class="featured__item">
						<div class="featured__item__pic set-bg"
							data-setbg='<c:out value="${product.getSrc()}" />'>
							<ul class="featured__item__pic__hover">
								<li><a href="#"><i class="fa fa-heart"></i></a></li>
								<li><a href="#"><i class="fa fa-retweet"></i></a></li>
								<li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
							</ul>
						</div>
						<div class="featured__item__text">
							<h6>
								<a href='<c:url value="/information?id=${product.getId()}"/>'><c:out
										value="${product.getName()}" /></a>
							</h6>
							<h5>
								$
								<c:out value="${product.getPrice()}" />
							</h5>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="product__pagination">
			<c:forEach var="pageNum" begin="1" end="${pagination.getCountPage()}">
				<a href='<c:url value="/index?page=${pageNum }"/>'>${pageNum }</a>
			</c:forEach>
		</div>
	</div>
</section>
<form action='<c:url value="/userdetail.jsp"/>' method="post">
	<input type="text" name="username"> 
	<input type="text" name="pass">
	<input type="number" name="age">
	<button>sumit</button>
</form>
<!-- Featured Section End -->

<jsp:include page="footer.jsp"></jsp:include>
</body>

</html>