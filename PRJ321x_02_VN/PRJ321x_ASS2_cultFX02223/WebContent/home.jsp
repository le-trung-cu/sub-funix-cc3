
<%@page import="models.Product"%>
<%@page import="java.util.List"%>
<%@page import="services.ProductService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"></jsp:include>
<%
	ProductService productService = new ProductService(getServletContext());
	List<Product> products = productService.getProducts();
%>
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
			<%
				for (Product product : products) {
			%>

			<div class="col-lg-3 col-md-4 col-sm-6 mix oranges fresh-meat">
				<div class="featured__item">
					<div class="featured__item__pic set-bg"
						data-setbg="<%=product.getPicture()%>">
						<ul class="featured__item__pic__hover">
							<li><a href="#"><i class="fa fa-heart"></i></a></li>
							<li><a href="#"><i class="fa fa-retweet"></i></a></li>
							<li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
						</ul>
					</div>
					<div class="featured__item__text">
						<h6>
							<a href="#"><%=product.getName()%></a>
						</h6>
						<h5>
							$<%=product.getDelPrice()%></h5>
					</div>
				</div>
			</div>
			<%
				}
			%>

		</div>
	</div>
</section>
<!-- Featured Section End -->


<jsp:include page="footer.jsp"></jsp:include>
</body>

</html>