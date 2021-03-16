<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp" />

<section class="checkout spad">
	<div class="container">
		<div class="checkout__form">
			<h4>Register</h4>
			<form id="form-login" action='<c:url value="/register"/>' method="post">
				<c:if test="${not empty error }">
					<div class="alert alert-danger" role="alert">
						<c:out value="${error }" />
					</div>
				</c:if>
				
				<div class="row">
					<div class="col-lg-8 col-md-6">

						<div class="checkout__input">
							<p>User Mail<span>*</span></p>
							<input id="mail" name="mail" type="email" required
								placeholder="user mail">
						</div>
						<div class="checkout__input">
							<p>Password<span>*</span></p>
							<input id="password" name="password" type="text" required
								placeholder="password" class="checkout__input__add">
						</div>
						<div class="checkout__input">
							<p>Phone</p>
							<input id="phone" name="phone" type="tel"
								placeholder="phone" class="checkout__input__add">
						</div>
						<div class="checkout__input">
							<p>Address</p>
							<input id="address" name="address" type="text"
								placeholder="address" class="checkout__input__add">
						</div>

						<button type="submit" class="site-btn">PLACE ORDER</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</section>


<jsp:include page="footer.jsp" />